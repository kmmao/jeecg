package cn.com.linkwide.ba.baitemtype.controller;
import java.io.IOException;
import java.util.ArrayList;
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

import cn.com.linkwide.ba.baitemtype.entity.BaItemtypeEntity;
import cn.com.linkwide.ba.baitemtype.service.BaItemtypeServiceI;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**   
 * @Title: Controller  
 * @Description: 项目大类
 * @author onlineGenerator
 * @date 2018-05-31 14:33:37
 * @version V1.0   
 *
 */
@Api(value="BaItemtype",description="项目大类",tags="baItemtypeController")
@Controller
@RequestMapping("/baItemtypeController")
public class BaItemtypeController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(BaItemtypeController.class);

	@Autowired
	private BaItemtypeServiceI baItemtypeService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 项目大类列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("cn/com/linkwide/ba/baitemtype/baItemtypeList");
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
	public void datagrid(BaItemtypeEntity baItemtype,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(BaItemtypeEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, baItemtype, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.baItemtypeService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除项目大类
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(BaItemtypeEntity baItemtype, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		baItemtype = systemService.getEntity(BaItemtypeEntity.class, baItemtype.getId());
		if("00".equals(baItemtype.getVitemcode())){
//			throw new BusinessException("该项为内置数据，不能删除");
			message = "该项为内置数据，不能删除";
		}else{
			message = "项目大类删除成功";
			try{
				baItemtypeService.delete(baItemtype);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}catch(Exception e){
				e.printStackTrace();
				message = "项目大类删除失败";
				throw new BusinessException(e.getMessage());
			}
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除项目大类
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "项目大类删除成功";
		try{
			for(String id:ids.split(",")){
				BaItemtypeEntity baItemtype = systemService.getEntity(BaItemtypeEntity.class, 
				id
				);
				baItemtypeService.delete(baItemtype);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "项目大类删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加项目大类
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(BaItemtypeEntity baItemtype, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "项目大类添加成功";
		try{
			BaItemtypeEntity findUniqueByCode = baItemtypeService.findUniqueByProperty(BaItemtypeEntity.class, "vitemcode", baItemtype.getVitemcode());
			if(findUniqueByCode !=null){
				throw new BusinessException(baItemtype.getVitemcode()+"编码已存在，请修改");
			}
			baItemtypeService.save(baItemtype);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(BusinessException b){
			b.getMessage();
		}catch(Exception e){
			e.printStackTrace();
			message = "项目大类添加失败";
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新项目大类
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(BaItemtypeEntity baItemtype, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "项目大类更新成功";
		BaItemtypeEntity t = baItemtypeService.get(BaItemtypeEntity.class, baItemtype.getId());
		try {
			if("00".equals(t.getVitemcode())){
				throw new RuntimeException("该项为内置数据，不能修改");
			}
			MyBeanUtils.copyBeanNotNull2Bean(baItemtype, t);
			baItemtypeService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "项目大类更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 项目大类新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(BaItemtypeEntity baItemtype, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(baItemtype.getId())) {
			baItemtype = baItemtypeService.getEntity(BaItemtypeEntity.class, baItemtype.getId());
			req.setAttribute("baItemtypePage", baItemtype);
		}
		return new ModelAndView("cn/com/linkwide/ba/baitemtype/baItemtype-add");
	}
	/**
	 * 项目大类编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(BaItemtypeEntity baItemtype, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(baItemtype.getId())) {
			baItemtype = baItemtypeService.getEntity(BaItemtypeEntity.class, baItemtype.getId());
			req.setAttribute("baItemtypePage", baItemtype);
		}
		return new ModelAndView("cn/com/linkwide/ba/baitemtype/baItemtype-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","baItemtypeController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(BaItemtypeEntity baItemtype,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(BaItemtypeEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, baItemtype, request.getParameterMap());
		List<BaItemtypeEntity> baItemtypes = this.baItemtypeService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"项目大类");
		modelMap.put(NormalExcelConstants.CLASS,BaItemtypeEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("项目大类列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,baItemtypes);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(BaItemtypeEntity baItemtype,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"项目大类");
    	modelMap.put(NormalExcelConstants.CLASS,BaItemtypeEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("项目大类列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<BaItemtypeEntity> listBaItemtypeEntitys = ExcelImportUtil.importExcel(file.getInputStream(),BaItemtypeEntity.class,params);
				for (BaItemtypeEntity baItemtype : listBaItemtypeEntitys) {
					baItemtypeService.save(baItemtype);
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
	@ApiOperation(value="项目大类列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<BaItemtypeEntity>> list() {
		List<BaItemtypeEntity> listBaItemtypes=baItemtypeService.getList(BaItemtypeEntity.class);
		return Result.success(listBaItemtypes);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取项目大类信息",notes="根据ID获取项目大类信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		BaItemtypeEntity task = baItemtypeService.get(BaItemtypeEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取项目大类信息为空");
		}
		return Result.success(task);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建项目大类")
	public ResponseMessage<?> create(@ApiParam(name="项目大类对象")@RequestBody BaItemtypeEntity baItemtype, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<BaItemtypeEntity>> failures = validator.validate(baItemtype);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			baItemtypeService.save(baItemtype);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("项目大类信息保存失败");
		}
		return Result.success(baItemtype);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新项目大类",notes="更新项目大类")
	public ResponseMessage<?> update(@ApiParam(name="项目大类对象")@RequestBody BaItemtypeEntity baItemtype) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<BaItemtypeEntity>> failures = validator.validate(baItemtype);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			baItemtypeService.saveOrUpdate(baItemtype);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("更新项目大类信息失败");
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success("更新项目大类信息成功");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除项目大类")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" + id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			baItemtypeService.deleteEntityById(BaItemtypeEntity.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("项目大类删除失败");
		}

		return Result.success();
	}
	
	/**
	 * 加载未停用的客户类型树
	 * @param request
	 * @return
	 */
	@RequestMapping("/getTreeAllDateForstate")
	@ResponseBody
	public List<ComboTree> getTreeAllDateForstate(HttpServletRequest request) {
//		//性能考虑。查询出所有的数据到缓存，然后进行遍历处理
		CriteriaQuery cq = new CriteriaQuery(BaItemtypeEntity.class);
		cq.addOrder("vitemcode", SortDirection.asc);
		cq.add();
		List<BaItemtypeEntity> baCustomerTypeEntityList = systemService.getListByCriteriaQuery(cq, false);

		List<ComboTree> comboTrees = new ArrayList<ComboTree>();
		for (BaItemtypeEntity entity : baCustomerTypeEntityList) {
			ComboTree tree = new ComboTree();
			tree.setId(entity.getVitemcode());
			tree.setText(entity.getVitemname());
			comboTrees.add(tree);
		}
		return comboTrees;
	}
}
