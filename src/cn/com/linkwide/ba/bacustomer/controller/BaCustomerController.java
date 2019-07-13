package cn.com.linkwide.ba.bacustomer.controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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
import org.jeecgframework.web.system.pojo.base.TSUser;
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

import cn.com.linkwide.ba.bacustomer.entity.BaCustomerEntity;
import cn.com.linkwide.ba.bacustomer.service.BaCustomerServiceI;
import cn.com.linkwide.ba.bacustomertype.entity.BaCustomerTypeEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**   
 * @Title: Controller  
 * @Description: 客户档案
 * @author onlineGenerator
 * @date 2018-08-03 13:27:31
 * @version V1.0   
 *
 */
@Api(value="BaCustomer",description="客户档案",tags="baCustomerController")
@Controller
@RequestMapping("/baCustomerController")
public class BaCustomerController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(BaCustomerController.class);

	@Autowired
	private BaCustomerServiceI baCustomerService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 客户档案列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("cn/com/linkwide/ba/bacustomer/baCustomerList");
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
	public void datagrid(BaCustomerEntity baCustomer,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		if(StringUtils.isNotEmpty(baCustomer.getCustomerFullName())){
			baCustomer.setCustomerFullName("*"+baCustomer.getCustomerFullName()+"*");
		}
		
		if(StringUtils.isNotEmpty(baCustomer.getCustomerCode())){
			baCustomer.setCustomerCode("*"+baCustomer.getCustomerCode()+"*");
		}
		
		if(StringUtils.isNotEmpty(baCustomer.getCustomerShortName())){
			baCustomer.setCustomerShortName("*"+baCustomer.getCustomerShortName()+"*");
		}
		CriteriaQuery cq = new CriteriaQuery(BaCustomerEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, baCustomer, request.getParameterMap());
		try{
			
			String customerTypeIds = request.getParameter("customerTypeIds");
			if(StringUtil.isNotEmpty(customerTypeIds)){
				cq.in("customerTypeId", customerTypeIds.split(","));
			}
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.baCustomerService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除客户档案
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(BaCustomerEntity baCustomer, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		baCustomer = systemService.getEntity(BaCustomerEntity.class, baCustomer.getId());
		message = "客户档案删除成功";
		try{
			baCustomerService.delete(baCustomer);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "客户档案删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除客户档案
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "客户档案删除成功";
		try{
			for(String id:ids.split(",")){
				BaCustomerEntity baCustomer = systemService.getEntity(BaCustomerEntity.class, 
				id
				);
				baCustomerService.delete(baCustomer);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "客户档案删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加客户档案
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(BaCustomerEntity baCustomer, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "客户档案添加成功";
		try{
			TSUser tsUser = ResourceUtil.getSessionUserName();
			baCustomer.setCreateBy(tsUser.getId());
			baCustomer.setDepartId(tsUser.getDepartid());
			baCustomer.setCreateDate(new Date());
			baCustomer.setAuditingState("待审核");
			baCustomer.setIsCease("0");
			baCustomerService.save(baCustomer);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "客户档案添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新客户档案
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(BaCustomerEntity baCustomer, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "客户档案更新成功";
		BaCustomerEntity t = baCustomerService.get(BaCustomerEntity.class, baCustomer.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(baCustomer, t);
			baCustomerService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "客户档案更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 客户档案新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(BaCustomerEntity baCustomer, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(baCustomer.getId())) {
			baCustomer = baCustomerService.getEntity(BaCustomerEntity.class, baCustomer.getId());
			req.setAttribute("baCustomerPage", baCustomer);
		}
		String customerTypeId = req.getParameter("customerTypeIds");
		req.setAttribute("customerTypeId", customerTypeId);
		return new ModelAndView("cn/com/linkwide/ba/bacustomer/baCustomer-add");
	}
	/**
	 * 客户档案编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(BaCustomerEntity baCustomer, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(baCustomer.getId())) {
			baCustomer = baCustomerService.getEntity(BaCustomerEntity.class, baCustomer.getId());
			req.setAttribute("baCustomerPage", baCustomer);
		}
		return new ModelAndView("cn/com/linkwide/ba/bacustomer/baCustomer-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","baCustomerController");
		req.setAttribute("method_name", "importExcel");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 更新式导入功能跳转
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "updateUpload")
	public ModelAndView updateUpload(HttpServletRequest req) {
		req.setAttribute("controller_name","baCustomerController");
		req.setAttribute("method_name", "updateImportExcel");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(BaCustomerEntity baCustomer,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(BaCustomerEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, baCustomer, request.getParameterMap());
		cq.addOrder("customerCode", SortDirection.asc);
		List<BaCustomerEntity> baCustomers = this.baCustomerService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"客户档案");
		modelMap.put(NormalExcelConstants.CLASS,BaCustomerEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("客户档案列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,baCustomers);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(BaCustomerEntity baCustomer,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"客户档案");
    	modelMap.put(NormalExcelConstants.CLASS,BaCustomerEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("客户档案列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
    	"导出信息"));
    	modelMap.put(NormalExcelConstants.DATA_LIST,new ArrayList());
    	return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	
	/**
	 * 客户档案导入
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "importExcel", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson importExcel(HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		//编码
		Map codeMap =new HashMap();
		//名称
		Map nameMap = new HashMap();
		//客户类型
		Map typeMap = new HashMap();
		String codeHql = " from BaCustomerEntity ";
		List<BaCustomerEntity> list = systemService.findHql(codeHql, new Object[]{});
		if(list!= null && list.size()>0){
			for (BaCustomerEntity entity : list) {
				codeMap.put(entity.getCustomerCode(), "Y");
				nameMap.put(entity.getCustomerFullName(), "Y");
			}
		}
		
		String typeHql =" from BaCustomerTypeEntity where status='0' ";
		List<BaCustomerTypeEntity> typeList = systemService.findHql(typeHql, null);
		if(typeList!=null && typeList.size()>0){
			for (BaCustomerTypeEntity entity : typeList) {
				typeMap.put(entity.getTypeCode(), entity.getTypeName());
			}
		}
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			MultipartFile file = entity.getValue();// 获取上传文件对象
			ImportParams params = new ImportParams();
			params.setTitleRows(2);
			params.setHeadRows(1);
			params.setNeedSave(true);
			try {
				List<BaCustomerEntity> listBaCustomerEntitys = ExcelImportUtil.importExcel(file.getInputStream(),BaCustomerEntity.class,params);
				for (BaCustomerEntity baCustomer : listBaCustomerEntitys) {
					//校验客户编码是否重复
					String code = baCustomer.getCustomerCode();
					if(StringUtil.isNotEmpty(code)){
						if(StringUtil.isNotEmpty(codeMap.get(code))){
							j.setMsg(code+"客户编码重复");
							return j;
						}else{
							codeMap.put(code, "Y");
						}
					}else{
						j.setMsg("客户编码不能为空");
						return j;
					}
					
					String name = baCustomer.getCustomerFullName();
					if(StringUtil.isNotEmpty(name)){
						if(StringUtil.isNotEmpty(nameMap.get(name))){
							j.setMsg(name+"客户名称重复");
							return j;
						}else{
							codeMap.put(name, "Y");
						}
					}else{
						j.setMsg("客户名称不能为空");
						return j;
					}
					//客户类型
					String type =baCustomer.getCustomerTypeId();
					if(StringUtil.isNotEmpty(type)){
						if(!StringUtil.isNotEmpty(typeMap.get(type))){
							j.setMsg(type+"客户类型不存在");
							return j;
						}
					}else{
						j.setMsg("客户类型不能为空");
						return j;
					}
					
					baCustomerService.save(baCustomer);
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
	
	/**
	 * 客户档案更新式导入
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "updateImportExcel", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson updateImportExcel(HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		//编码
		Map codeMap =new HashMap();
		//名称
		Map nameMap = new HashMap();
		//客户类型
		Map typeMap = new HashMap();
		String codeHql = " from BaCustomerEntity ";
		List<BaCustomerEntity> list = systemService.findHql(codeHql, new Object[]{});
		if(list!= null && list.size()>0){
			for (BaCustomerEntity entity : list) {
				codeMap.put(entity.getCustomerCode(), entity.getId());
				nameMap.put(entity.getCustomerFullName(), entity.getId());
			}
		}
		
		String typeHql =" from BaCustomerTypeEntity where status='0' ";
		List<BaCustomerTypeEntity> typeList = systemService.findHql(typeHql, null);
		if(typeList!=null && typeList.size()>0){
			for (BaCustomerTypeEntity entity : typeList) {
				typeMap.put(entity.getTypeCode(), entity.getTypeName());
			}
		}
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			MultipartFile file = entity.getValue();// 获取上传文件对象
			ImportParams params = new ImportParams();
			params.setTitleRows(2);
			params.setHeadRows(1);
			params.setNeedSave(true);
			try {
				List<BaCustomerEntity> listBaCustomerEntitys = ExcelImportUtil.importExcel(file.getInputStream(),BaCustomerEntity.class,params);
				for (BaCustomerEntity baCustomer : listBaCustomerEntitys) {
					//客户类型
					String type =baCustomer.getCustomerTypeId();
					if(StringUtil.isNotEmpty(type)){
						if(!StringUtil.isNotEmpty(typeMap.get(type))){
							j.setMsg(type+"客户类型不存在");
							return j;
						}
					}else{
						j.setMsg("客户类型不能为空");
						return j;
					}
					
					//校验客户编码是否重复
					String code = baCustomer.getCustomerCode();
					if(StringUtil.isNotEmpty(code)){
						if(StringUtil.isNotEmpty(codeMap.get(code))){
//							j.setMsg(code+"客户编码重复");
//							return j;
							baCustomer.setId(codeMap.get(code).toString());
							BaCustomerEntity t = baCustomerService.get(BaCustomerEntity.class, baCustomer.getId());
							MyBeanUtils.copyBeanNotNull2Bean(baCustomer, t);
							baCustomerService.saveOrUpdate(t);
						}else{
							baCustomerService.save(baCustomer);
							codeMap.put(code, baCustomer.getId());
						}
					}else{
						j.setMsg("客户编码不能为空");
						return j;
					}
					
//					String name = baCustomer.getCustomerFullName();
//					if(StringUtil.isNotEmpty(name)){
//						if(StringUtil.isNotEmpty(nameMap.get(name))){
////							j.setMsg(name+"客户名称重复");
////							return j;
//						}else{
//							codeMap.put(name, "Y");
//						}
//					}else{
//						j.setMsg("客户名称不能为空");
//						return j;
//					}
//					baCustomerService.saveOrUpdate(baCustomer);
//					baCustomerService.save(baCustomer);
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
	@ApiOperation(value="客户档案列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<BaCustomerEntity>> list() {
		List<BaCustomerEntity> listBaCustomers=baCustomerService.getList(BaCustomerEntity.class);
		return Result.success(listBaCustomers);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取客户档案信息",notes="根据ID获取客户档案信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		BaCustomerEntity task = baCustomerService.get(BaCustomerEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取客户档案信息为空");
		}
		return Result.success(task);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建客户档案")
	public ResponseMessage<?> create(@ApiParam(name="客户档案对象")@RequestBody BaCustomerEntity baCustomer, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<BaCustomerEntity>> failures = validator.validate(baCustomer);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			baCustomerService.save(baCustomer);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("客户档案信息保存失败");
		}
		return Result.success(baCustomer);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新客户档案",notes="更新客户档案")
	public ResponseMessage<?> update(@ApiParam(name="客户档案对象")@RequestBody BaCustomerEntity baCustomer) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<BaCustomerEntity>> failures = validator.validate(baCustomer);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			baCustomerService.saveOrUpdate(baCustomer);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("更新客户档案信息失败");
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success("更新客户档案信息成功");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除客户档案")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" + id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			baCustomerService.deleteEntityById(BaCustomerEntity.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("客户档案删除失败");
		}

		return Result.success();
	}
	
	/**
	 * 同步数据到u8
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "synToU8")
	@ResponseBody
	public AjaxJson synToU8( HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "客户档案同步成功";
		try{
			String hql =" from BaCustomerEntity ";
			List<BaCustomerEntity> list = systemService.findHql(hql, null);
			if(list!= null && list.size()>0){
				baCustomerService.synToU8(list);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "客户档案同步失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
}
