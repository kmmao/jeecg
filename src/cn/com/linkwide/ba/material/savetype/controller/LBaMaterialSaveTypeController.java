package cn.com.linkwide.ba.material.savetype.controller;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.apache.log4j.Logger;
import org.jeecgframework.core.beanvalidator.BeanValidators;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.ExceptionUtil;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

import cn.com.linkwide.ba.material.savetype.entity.LBaMaterialSaveTypeEntity;
import cn.com.linkwide.ba.material.savetype.service.LBaMaterialSaveTypeServiceI;

/**   
 * @Title: Controller  
 * @Description: 物资保存方式
 * @author onlineGenerator
 * @date 2018-01-12 10:07:52
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/lBaMaterialSaveTypeController")
public class LBaMaterialSaveTypeController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(LBaMaterialSaveTypeController.class);

	@Autowired
	private LBaMaterialSaveTypeServiceI lBaMaterialSaveTypeService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 物资保存方式列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("cn/com/linkwide/ba/material/savetype/lBaMaterialSaveTypeList");
	}
	
	/**
	 * 查询不停用的
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "getSaveDateType")
	@ResponseBody
	public List<LBaMaterialSaveTypeEntity> getSaveDateType(HttpServletRequest request) {
		return systemService.findByQueryString(" from LBaMaterialSaveTypeEntity where status = '0'");
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
	public void datagrid(LBaMaterialSaveTypeEntity lBaMaterialSaveType,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(LBaMaterialSaveTypeEntity.class, dataGrid);
		//查询条件组装器
		if(StringUtil.isNotEmpty(lBaMaterialSaveType.getTypeCode())){
			lBaMaterialSaveType.setTypeCode("*"+lBaMaterialSaveType.getTypeCode()+"*");
		}
		
		if(StringUtil.isNotEmpty(lBaMaterialSaveType.getTypeName())){
			lBaMaterialSaveType.setTypeName("*"+lBaMaterialSaveType.getTypeName()+"*");
		}
		
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, lBaMaterialSaveType, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.lBaMaterialSaveTypeService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除物资保存方式
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(LBaMaterialSaveTypeEntity lBaMaterialSaveType, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		lBaMaterialSaveType = systemService.getEntity(LBaMaterialSaveTypeEntity.class, lBaMaterialSaveType.getId());
		message = "物资保存方式删除成功";
		try{
//			List<LStArriAcceDetailEntity> l1 = systemService.findByQueryString(" from LStArriAcceDetailEntity where wareType = '"+lBaMaterialSaveType.getId()+"'");
//			if(l1.size() > 0){
//				j.setMsg("保存方式被引用，不可删除");
//				j.setSuccess(true);
//				return j;
//			}
			
			lBaMaterialSaveTypeService.delete(lBaMaterialSaveType);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "物资保存方式删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除物资保存方式
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "物资保存方式删除成功";
		
		boolean flog = false;
		try{
			for(String id:ids.split(",")){
				LBaMaterialSaveTypeEntity lBaMaterialSaveType = systemService.getEntity(LBaMaterialSaveTypeEntity.class, 
				id
				);
				
//				List<LStArriAcceDetailEntity> l1 = systemService.findByQueryString(" from LStArriAcceDetailEntity where wareType = '"+lBaMaterialSaveType.getId()+"'");
//				if(l1.size() > 0){
//					flog = true;
//					continue;
//				}
				
				lBaMaterialSaveTypeService.delete(lBaMaterialSaveType);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
				
			}
			
			if(flog){
				message = "物资保存方式删除成功";
			}
			
		}catch(Exception e){
			e.printStackTrace();
			message = "物资保存方式删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加物资保存方式
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(LBaMaterialSaveTypeEntity lBaMaterialSaveType, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "物资保存方式添加成功";
		try{
			//校验
			List<LBaMaterialSaveTypeEntity> l1 = systemService.findByQueryString(" from LBaMaterialSaveTypeEntity where typeCode = '"+lBaMaterialSaveType.getTypeCode()+"'");
			if(l1.size() > 0){
				j.setMsg("编码已存在，保存失败");
				j.setSuccess(false);
				return j;
			}
			
			List<LBaMaterialSaveTypeEntity> l2 = systemService.findByQueryString(" from LBaMaterialSaveTypeEntity where typeName = '"+lBaMaterialSaveType.getTypeName()+"'");
			if(l2.size() > 0){
				j.setMsg("名称已存在，保存失败");
				j.setSuccess(false);
				return j;
			}
			
			lBaMaterialSaveTypeService.save(lBaMaterialSaveType);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "物资保存方式添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新物资保存方式
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(LBaMaterialSaveTypeEntity lBaMaterialSaveType, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "物资保存方式更新成功";
		LBaMaterialSaveTypeEntity t = lBaMaterialSaveTypeService.get(LBaMaterialSaveTypeEntity.class, lBaMaterialSaveType.getId());
		try {
			//校验
			List<LBaMaterialSaveTypeEntity> l1 = systemService.findByQueryString(" from LBaMaterialSaveTypeEntity where typeCode = '"+lBaMaterialSaveType.getTypeCode()+"' and id != '"+lBaMaterialSaveType.getId()+"' ");
			if(l1.size() > 0){
				j.setMsg("编码已存在，保存失败");
				j.setSuccess(false);
				return j;
			}
			
			List<LBaMaterialSaveTypeEntity> l2 = systemService.findByQueryString(" from LBaMaterialSaveTypeEntity where typeName = '"+lBaMaterialSaveType.getTypeName()+"' and id != '"+lBaMaterialSaveType.getId()+"' ");
			if(l2.size() > 0){
				j.setMsg("名称已存在，保存失败");
				j.setSuccess(false);
				return j;
			}
			
			MyBeanUtils.copyBeanNotNull2Bean(lBaMaterialSaveType, t);
			lBaMaterialSaveTypeService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "物资保存方式更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 物资保存方式新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(LBaMaterialSaveTypeEntity lBaMaterialSaveType, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(lBaMaterialSaveType.getId())) {
			lBaMaterialSaveType = lBaMaterialSaveTypeService.getEntity(LBaMaterialSaveTypeEntity.class, lBaMaterialSaveType.getId());
			req.setAttribute("lBaMaterialSaveTypePage", lBaMaterialSaveType);
		}
		return new ModelAndView("cn/com/linkwide/ba/material/savetype/lBaMaterialSaveType-add");
	}
	/**
	 * 物资保存方式编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(LBaMaterialSaveTypeEntity lBaMaterialSaveType, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(lBaMaterialSaveType.getId())) {
			lBaMaterialSaveType = lBaMaterialSaveTypeService.getEntity(LBaMaterialSaveTypeEntity.class, lBaMaterialSaveType.getId());
			req.setAttribute("lBaMaterialSaveTypePage", lBaMaterialSaveType);
		}
		return new ModelAndView("cn/com/linkwide/ba/material/savetype/lBaMaterialSaveType-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","lBaMaterialSaveTypeController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(LBaMaterialSaveTypeEntity lBaMaterialSaveType,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(LBaMaterialSaveTypeEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, lBaMaterialSaveType, request.getParameterMap());
		List<LBaMaterialSaveTypeEntity> lBaMaterialSaveTypes = this.lBaMaterialSaveTypeService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"物资保存方式");
		modelMap.put(NormalExcelConstants.CLASS,LBaMaterialSaveTypeEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("物资保存方式列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,lBaMaterialSaveTypes);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(LBaMaterialSaveTypeEntity lBaMaterialSaveType,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"物资保存方式");
    	modelMap.put(NormalExcelConstants.CLASS,LBaMaterialSaveTypeEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("物资保存方式列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
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
				List<LBaMaterialSaveTypeEntity> listLBaMaterialSaveTypeEntitys = ExcelImportUtil.importExcel(file.getInputStream(),LBaMaterialSaveTypeEntity.class,params);
				for (LBaMaterialSaveTypeEntity lBaMaterialSaveType : listLBaMaterialSaveTypeEntitys) {
					lBaMaterialSaveTypeService.save(lBaMaterialSaveType);
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
	public List<LBaMaterialSaveTypeEntity> list() {
		List<LBaMaterialSaveTypeEntity> listLBaMaterialSaveTypes=lBaMaterialSaveTypeService.getList(LBaMaterialSaveTypeEntity.class);
		return listLBaMaterialSaveTypes;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		LBaMaterialSaveTypeEntity task = lBaMaterialSaveTypeService.get(LBaMaterialSaveTypeEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody LBaMaterialSaveTypeEntity lBaMaterialSaveType, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<LBaMaterialSaveTypeEntity>> failures = validator.validate(lBaMaterialSaveType);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		try{
			lBaMaterialSaveTypeService.save(lBaMaterialSaveType);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = lBaMaterialSaveType.getId();
		URI uri = uriBuilder.path("/rest/lBaMaterialSaveTypeController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody LBaMaterialSaveTypeEntity lBaMaterialSaveType) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<LBaMaterialSaveTypeEntity>> failures = validator.validate(lBaMaterialSaveType);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		try{
			lBaMaterialSaveTypeService.saveOrUpdate(lBaMaterialSaveType);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		lBaMaterialSaveTypeService.deleteEntityById(LBaMaterialSaveTypeEntity.class, id);
	}
}
