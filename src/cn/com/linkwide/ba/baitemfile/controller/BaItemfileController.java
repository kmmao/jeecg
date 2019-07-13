package cn.com.linkwide.ba.baitemfile.controller;
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

import cn.com.linkwide.ba.baitemfile.entity.BaItemfileEntity;
import cn.com.linkwide.ba.baitemfile.service.BaItemfileServiceI;
//import cn.com.linkwide.budget.projectapply.entity.ProjectInfoEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**   
 * @Title: Controller  
 * @Description: 项目档案
 * @author onlineGenerator
 * @date 2018-05-31 14:54:03
 * @version V1.0   
 *
 */
@Api(value="BaItemfile",description="项目档案",tags="baItemfileController")
@Controller
@RequestMapping("/baItemfileController")
public class BaItemfileController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(BaItemfileController.class);

	@Autowired
	private BaItemfileServiceI baItemfileService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 项目档案列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("cn/com/linkwide/ba/baitemfile/baItemfileList");
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
	public void datagrid(BaItemfileEntity baItemfile,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(BaItemfileEntity.class, dataGrid);
//		if(StringUtil.isEmpty(baItemfile.getId())){
//			cq.isNull("extend5");
//		}else{
//			cq.eq("extend5", baItemfile.getId());
//			baItemfile.setId(null);
//		}
		//项目分类
		String parentCode = request.getParameter("pkbaprotypes");
		if(StringUtil.isNotEmpty(parentCode)){
//			cq.eq("pkbaprotype", parentCode);
			cq.in("pkbaprotype", parentCode.split(","));
		}
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, baItemfile, request.getParameterMap());
		try{
			//自定义追加查询条件
			cq.addOrder("vcode", SortDirection.asc);
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.baItemfileService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
//		TagUtil.treegrid(response, dataGrid);
	}
	
	/**
	 * 删除项目档案
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(BaItemfileEntity baItemfile, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		baItemfile = systemService.getEntity(BaItemfileEntity.class, baItemfile.getId());
		message = "项目档案删除成功";
		try{
			baItemfileService.delete(baItemfile);
			//项目信息
		/*	ProjectInfoEntity projectInfo= systemService.getEntity(ProjectInfoEntity.class, baItemfile.getExtend2());
			if(projectInfo!=null){
				systemService.delete(projectInfo);
			}*/
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "项目档案删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除项目档案
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "项目档案删除成功";
		try{
			for(String id:ids.split(",")){
				BaItemfileEntity baItemfile = systemService.getEntity(BaItemfileEntity.class, 
				id
				);
				baItemfileService.delete(baItemfile);
				//项目信息
				/*ProjectInfoEntity projectInfo= systemService.getEntity(ProjectInfoEntity.class, baItemfile.getExtend2());
				if(projectInfo!=null){
					systemService.delete(projectInfo);
				}*/
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "项目档案删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加项目档案
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(BaItemfileEntity baItemfile, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "项目档案添加成功";
		try{
			BaItemfileEntity findUniqueByCode = baItemfileService.findUniqueByProperty(BaItemfileEntity.class, "vcode", baItemfile.getVcode());
			if(findUniqueByCode !=null){
				throw new BusinessException(baItemfile.getVcode()+"编码已存在，请修改");
			}
			
			baItemfileService.save(baItemfile);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(BusinessException b){
			b.getMessage();
		}catch(Exception e){
			e.printStackTrace();
			message = "项目档案添加失败";
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新项目档案
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(BaItemfileEntity baItemfile, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "项目档案更新成功";
		BaItemfileEntity t = baItemfileService.get(BaItemfileEntity.class, baItemfile.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(baItemfile, t);
			baItemfileService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "项目档案更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 项目档案新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(BaItemfileEntity baItemfile, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(baItemfile.getId())) {
			baItemfile = baItemfileService.getEntity(BaItemfileEntity.class, baItemfile.getId());
			req.setAttribute("baItemfilePage", baItemfile);
		}
		String pkbaprotypes = req.getParameter("pkbaprotypes");
		req.setAttribute("pkbaprotypes", pkbaprotypes);
		return new ModelAndView("cn/com/linkwide/ba/baitemfile/baItemfile-add");
	}
	/**
	 * 项目档案编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(BaItemfileEntity baItemfile, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(baItemfile.getId())) {
			baItemfile = baItemfileService.getEntity(BaItemfileEntity.class, baItemfile.getId());
			req.setAttribute("baItemfilePage", baItemfile);
		}
		return new ModelAndView("cn/com/linkwide/ba/baitemfile/baItemfile-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","baItemfileController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(BaItemfileEntity baItemfile,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(BaItemfileEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, baItemfile, request.getParameterMap());
		List<BaItemfileEntity> baItemfiles = this.baItemfileService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"项目档案");
		modelMap.put(NormalExcelConstants.CLASS,BaItemfileEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("项目档案列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,baItemfiles);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(BaItemfileEntity baItemfile,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"项目档案");
    	modelMap.put(NormalExcelConstants.CLASS,BaItemfileEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("项目档案列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
    	"导出信息"));
    	modelMap.put(NormalExcelConstants.DATA_LIST,new ArrayList());
    	return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	
	@RequestMapping(params = "importExcel", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson importExcel(HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		Map codeMap = new HashMap();
		String sql  = " select vcode \"code\",vname \"name\" from ba_itemfile ";
		List<Map<String,Object>> listMap=systemService.findForJdbc(sql, new Object[]{});
		if(listMap!= null && listMap.size()>0){
			for (Map<String, Object> map : listMap) {
				codeMap.put(map.get("code"),map.get("name"));
			}
		}
		
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			MultipartFile file = entity.getValue();// 获取上传文件对象
			ImportParams params = new ImportParams();
			params.setTitleRows(2);
			params.setHeadRows(1);
			params.setNeedSave(true);
			try {
				List<BaItemfileEntity> listBaItemfileEntitys = ExcelImportUtil.importExcel(file.getInputStream(),BaItemfileEntity.class,params);
				List<BaItemfileEntity> saveList = new ArrayList<>();
				for (BaItemfileEntity item : listBaItemfileEntitys) {
					//校验编码
					String code = item.getVcode();
					if(StringUtil.isNotEmpty(code)){
						if(codeMap!= null && StringUtil.isNotEmpty(codeMap.get(code))){
							j.setMsg("项目编码："+code+"已存在");
							return j;
						}else{
							codeMap.put(code, item.getVname());
						}
//						List<BaItemfileEntity> list = systemService.findByProperty(BaItemfileEntity.class, "vcode", code);
//						if(list!= null && list.size()>0){
//							j.setMsg("项目编码："+code+"已存在");
//							return j;
//						}
					}else{
						j.setMsg("项目编码不能为空");
						return j;
					}
					//校验名称
					String name = item.getVname();
					if(StringUtil.isEmpty(name)){
						j.setMsg("项目名称不能为空");
						return j;
					}
					saveList.add(item);
				}
				baItemfileService.batchSave(saveList);
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
	@ApiOperation(value="项目档案列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<BaItemfileEntity>> list() {
		List<BaItemfileEntity> listBaItemfiles=baItemfileService.getList(BaItemfileEntity.class);
		return Result.success(listBaItemfiles);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取项目档案信息",notes="根据ID获取项目档案信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		BaItemfileEntity task = baItemfileService.get(BaItemfileEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取项目档案信息为空");
		}
		return Result.success(task);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建项目档案")
	public ResponseMessage<?> create(@ApiParam(name="项目档案对象")@RequestBody BaItemfileEntity baItemfile, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<BaItemfileEntity>> failures = validator.validate(baItemfile);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			baItemfileService.save(baItemfile);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("项目档案信息保存失败");
		}
		return Result.success(baItemfile);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新项目档案",notes="更新项目档案")
	public ResponseMessage<?> update(@ApiParam(name="项目档案对象")@RequestBody BaItemfileEntity baItemfile) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<BaItemfileEntity>> failures = validator.validate(baItemfile);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			baItemfileService.saveOrUpdate(baItemfile);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("更新项目档案信息失败");
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success("更新项目档案信息成功");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除项目档案")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" + id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			baItemfileService.deleteEntityById(BaItemfileEntity.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("项目档案删除失败");
		}

		return Result.success();
	}
	
	/**
	 * 加载树形结构
	 * 
	 * @return
	 */
	@RequestMapping(params = "getTree")
	@ResponseBody
	public List<ComboTree> getTree(HttpServletRequest request) {
		List<ComboTree> rootLists = new ArrayList<ComboTree>();
		this.baItemfileService.tree1(rootLists, request);
		return rootLists;
	}
	
}
