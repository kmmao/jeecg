package cn.com.linkwide.ba.baprotype.controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.jeecgframework.core.beanvalidator.BeanValidators;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.ComboTree;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.ExceptionUtil;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.jwt.util.ResponseMessage;
import org.jeecgframework.jwt.util.Result;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.tag.vo.datatable.SortDirection;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import com.alibaba.fastjson.JSONArray;

import cn.com.linkwide.ba.baprotype.entity.BaProtypeEntity;
import cn.com.linkwide.ba.baprotype.service.BaProtypeServiceI;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**   
 * @Title: Controller  
 * @Description: 项目分类
 * @author onlineGenerator
 * @date 2018-05-31 15:21:16
 * @version V1.0   
 *
 */
@Api(value="BaProtype",description="项目分类",tags="baProtypeController")
@Controller
@RequestMapping("/baProtypeController")
public class BaProtypeController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(BaProtypeController.class);

	@Autowired
	private BaProtypeServiceI baProtypeService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 项目分类列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("cn/com/linkwide/ba/baprotype/baProtypeList");
	}

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(BaProtypeEntity baProtype,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(BaProtypeEntity.class, dataGrid);
		if(StringUtil.isEmpty(baProtype.getId())){
			cq.isNull("pkparent");
		}else{
			cq.eq("pkparent", systemService.get(BaProtypeEntity.class,baProtype.getId()).getVcode());
			baProtype.setId(null);
		}
		String typeIds = request.getParameter("typeIds");
		if(StringUtil.isNotEmpty(typeIds)){
			cq.in("pkbaitemtype", typeIds.split(","));
		}
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, baProtype, request.getParameterMap());
		try{
			//自定义追加查询条件
			cq.addOrder("vcode", SortDirection.asc);
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.baProtypeService.getDataGridReturn(cq, true);
//		TagUtil.datagrid(response, dataGrid);
		TagUtil.treegrid(response, dataGrid);
	}
	
	/**
	 * 删除项目分类
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(BaProtypeEntity baProtype, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		baProtype = systemService.getEntity(BaProtypeEntity.class, baProtype.getId());
		message = "项目分类删除成功";
//		if("0101".equals(baProtype.getVcode())||"0102".equals(baProtype.getVcode())||"0103".equals(baProtype.getVcode())||"0104".equals(baProtype.getVcode())){
////			throw new BusinessException("内置数据不能删除");
//			message = "内置数据不能删除";
//		}else{
			String hql="from BaProtypeEntity where pkparent=?";
			List list = systemService.findHql(hql, new Object[]{baProtype.getVcode()});
			if(list != null && list.size()>0){
				message = "该项目分类存在下级分类，不能删除";
			}else{
				try{
					baProtypeService.delete(baProtype);
					systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
				}catch(Exception e){
					e.printStackTrace();
					message = "项目分类删除失败";
					throw new BusinessException(e.getMessage());
				}
			}
//		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除项目分类
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "项目分类删除成功";
		try{
			for(String id:ids.split(",")){
				BaProtypeEntity baProtype = systemService.getEntity(BaProtypeEntity.class, 
				id
				);
				baProtypeService.delete(baProtype);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "项目分类删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加项目分类
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(BaProtypeEntity baProtype, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "项目分类添加成功";
		try{
			BaProtypeEntity findUniqueByCode = baProtypeService.findUniqueByProperty(BaProtypeEntity.class, "vcode", baProtype.getVcode());
			if(findUniqueByCode != null){
				throw new BusinessException( baProtype.getVcode()+"编码已存在，请修改");
			}
			baProtypeService.save(baProtype);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(BusinessException b){
			b.getMessage();
		}catch(Exception e){
			e.printStackTrace();
			message = "项目分类添加失败";
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新项目分类
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(BaProtypeEntity baProtype, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "项目分类更新成功";
		BaProtypeEntity t = baProtypeService.get(BaProtypeEntity.class, baProtype.getId());
		try {
			if("0101".equals(t.getVcode())||"0102".equals(t.getVcode())||"0103".equals(t.getVcode())||"0104".equals(t.getVcode())){
				throw new BusinessException("内置数据不能修改");
			}
			MyBeanUtils.copyBeanNotNull2Bean(baProtype, t);
			baProtypeService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "项目分类更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 项目分类新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(BaProtypeEntity baProtype, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(baProtype.getId())) {
			baProtype = baProtypeService.getEntity(BaProtypeEntity.class, baProtype.getId());
			req.setAttribute("baProtypePage", baProtype);
		}
		String pkbaitemtype = req.getParameter("pkbaitemtype");
		req.setAttribute("pkbaitemtype", pkbaitemtype);
		return new ModelAndView("cn/com/linkwide/ba/baprotype/baProtype-add");
	}
	/**
	 * 项目分类编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(BaProtypeEntity baProtype, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(baProtype.getId())) {
			baProtype = baProtypeService.getEntity(BaProtypeEntity.class, baProtype.getId());
			req.setAttribute("baProtypePage", baProtype);
		}
		return new ModelAndView("cn/com/linkwide/ba/baprotype/baProtype-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","baProtypeController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(BaProtypeEntity baProtype,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(BaProtypeEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, baProtype, request.getParameterMap());
		List<BaProtypeEntity> baProtypes = this.baProtypeService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"项目分类");
		modelMap.put(NormalExcelConstants.CLASS,BaProtypeEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("项目分类列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,baProtypes);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(BaProtypeEntity baProtype,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"项目分类");
    	modelMap.put(NormalExcelConstants.CLASS,BaProtypeEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("项目分类列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
    	"导出信息"));
    	modelMap.put(NormalExcelConstants.DATA_LIST,new ArrayList());
    	return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "importExcel", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson importExcel(HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			MultipartFile file = entity.getValue();// 获取上传文件对象
			ImportParams params = new ImportParams();
			params.setTitleRows(2);
			params.setHeadRows(1);
			params.setNeedSave(true);
			try {
				List<BaProtypeEntity> listBaProtypeEntitys = ExcelImportUtil.importExcel(file.getInputStream(),BaProtypeEntity.class,params);
				for (BaProtypeEntity type : listBaProtypeEntitys) {
					//校验编码
					String code = type.getVcode();
					if(StringUtil.isNotEmpty(code)){
						List<BaProtypeEntity> list = systemService.findByProperty(BaProtypeEntity.class, "vcode", code);
						if(list!= null && list.size()>0){
							j.setMsg("分类编码："+code+"已存在");
							return j;
						}
					}else{
						j.setMsg("分类编码不能为空");
						return j;
					}
					//校验名称
					String name=type.getVname();
					if(StringUtil.isEmpty(name)){
						j.setMsg("分类名称不能为空");
						return j;
					}
					//校验是否末级
					String isLast = type.getViflater();
					if(StringUtil.isEmpty(isLast)){
						j.setMsg("是否末级不能为空");
						return j;
					}else{
						if(!"Y".equals(isLast)&&!"N".equals(isLast)){
							j.setMsg("是否末级只能是‘Y’或‘N’");
							return j;
						}
					}
					
					
					baProtypeService.save(type);
				}
				j.setMsg("文件导入成功！");
			} catch (Exception e) {
				j.setMsg("文件导入失败！");
				logger.error(ExceptionUtil.getExceptionMessage(e));
			}finally{
				try {
					file.getInputStream().close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return j;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="项目分类列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<BaProtypeEntity>> list() {
		List<BaProtypeEntity> listBaProtypes=baProtypeService.getList(BaProtypeEntity.class);
		return Result.success(listBaProtypes);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取项目分类信息",notes="根据ID获取项目分类信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		BaProtypeEntity task = baProtypeService.get(BaProtypeEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取项目分类信息为空");
		}
		return Result.success(task);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建项目分类")
	public ResponseMessage<?> create(@ApiParam(name="项目分类对象")@RequestBody BaProtypeEntity baProtype, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<BaProtypeEntity>> failures = validator.validate(baProtype);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			baProtypeService.save(baProtype);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("项目分类信息保存失败");
		}
		return Result.success(baProtype);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新项目分类",notes="更新项目分类")
	public ResponseMessage<?> update(@ApiParam(name="项目分类对象")@RequestBody BaProtypeEntity baProtype) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<BaProtypeEntity>> failures = validator.validate(baProtype);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			baProtypeService.saveOrUpdate(baProtype);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("更新项目分类信息失败");
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success("更新项目分类信息成功");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除项目分类")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" + id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			baProtypeService.deleteEntityById(BaProtypeEntity.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("项目分类删除失败");
		}

		return Result.success();
	}
	
	/**
	 * 查询项目分类树形结构数据
	 * @param comboTree
	 * @return
	 */
	@RequestMapping(params = "comboTreeForState")
	@ResponseBody
	public List<ComboTree> comboTreeForState(ComboTree comboTree) {
		
		//性能考虑。查询出所有的数据到缓存，然后进行遍历处理
		CriteriaQuery cq = new CriteriaQuery(BaProtypeEntity.class);
		//编码排序
		cq.addOrder("vcode", SortDirection.asc);
		cq.add();
		List<BaProtypeEntity> list = systemService.getListByCriteriaQuery(cq, false);

		List<ComboTree> comboTrees = new ArrayList<ComboTree>();
		for (BaProtypeEntity entity : list) {
			//判断是否一级节点
			if(entity.getPkparent() == null || "".equals(entity.getPkparent())){
				comboTrees.add(typeConvertToTree(entity,list));
			}	
		}

		return comboTrees;
	}

	/**
	 * 根据上级项目分类查询下级项目分类
	 * @param entity
	 * @param list
	 * @return
	 */
	private ComboTree typeConvertToTree(BaProtypeEntity entity,List<BaProtypeEntity> list) {
		ComboTree tree = new ComboTree();
		tree.setId(entity.getVcode());
		tree.setText(entity.getVname());

		//查询下级节点
		List<ComboTree> comboTrees = new ArrayList<ComboTree>();
		for (BaProtypeEntity baEntity : list) {
			if(baEntity.getPkparent() != null && !"".equals(baEntity.getPkparent())){
				if(baEntity.getPkparent().equals(entity.getVcode())){
					comboTrees.add(typeConvertToTree(baEntity,list));
				}
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		tree.setAttributes(map);
		tree.setChildren(comboTrees);

		return tree;
	}
}
