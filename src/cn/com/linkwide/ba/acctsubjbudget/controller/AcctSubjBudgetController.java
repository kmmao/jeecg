package cn.com.linkwide.ba.acctsubjbudget.controller;
import cn.com.linkwide.ba.acctsubjbudget.entity.AcctSubjBudgetEntity;
import cn.com.linkwide.ba.acctsubjbudget.service.AcctSubjBudgetServiceI;
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.common.TreeChildCount;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;

import java.io.OutputStream;
import org.jeecgframework.core.util.BrowserUtils;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.TemplateExportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.vo.TemplateExcelConstants;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jeecgframework.core.util.ResourceUtil;
import java.io.IOException;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import java.util.Map;
import java.util.HashMap;
import org.jeecgframework.core.util.ExceptionUtil;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.jeecgframework.core.beanvalidator.BeanValidators;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.net.URI;
import org.springframework.http.MediaType;
import org.springframework.web.util.UriComponentsBuilder;
import org.apache.commons.lang3.StringUtils;
import org.jeecgframework.jwt.util.GsonUtil;
import org.jeecgframework.jwt.util.ResponseMessage;
import org.jeecgframework.jwt.util.Result;
import com.alibaba.fastjson.JSONArray;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**   
 * @Title: Controller  
 * @Description: 科目项目对应表
 * @author onlineGenerator
 * @date 2018-08-02 17:46:09
 * @version V1.0   
 *
 */
@Api(value="AcctSubjBudget",description="科目项目对应表",tags="acctSubjBudgetController")
@Controller
@RequestMapping("/acctSubjBudgetController")
public class AcctSubjBudgetController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(AcctSubjBudgetController.class);

	@Autowired
	private AcctSubjBudgetServiceI acctSubjBudgetService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 科目项目对应表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("cn/com/linkwide/ba/acctsubjbudget/acctSubjBudgetList");
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
	public void datagrid(AcctSubjBudgetEntity acctSubjBudget,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(AcctSubjBudgetEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, acctSubjBudget, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.acctSubjBudgetService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除科目项目对应表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(AcctSubjBudgetEntity acctSubjBudget, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		acctSubjBudget = systemService.getEntity(AcctSubjBudgetEntity.class, acctSubjBudget.getId());
		message = "科目项目对应表删除成功";
		try{
			acctSubjBudgetService.delete(acctSubjBudget);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "科目项目对应表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除科目项目对应表
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "科目项目对应表删除成功";
		try{
			for(String id:ids.split(",")){
				AcctSubjBudgetEntity acctSubjBudget = systemService.getEntity(AcctSubjBudgetEntity.class, 
				id
				);
				acctSubjBudgetService.delete(acctSubjBudget);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "科目项目对应表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加科目项目对应表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(AcctSubjBudgetEntity acctSubjBudget, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "科目项目对应表添加成功";
		try{
			acctSubjBudgetService.save(acctSubjBudget);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "科目项目对应表添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新科目项目对应表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(AcctSubjBudgetEntity acctSubjBudget, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "科目项目对应表更新成功";
		AcctSubjBudgetEntity t = acctSubjBudgetService.get(AcctSubjBudgetEntity.class, acctSubjBudget.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(acctSubjBudget, t);
			acctSubjBudgetService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "科目项目对应表更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 科目项目对应表新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(AcctSubjBudgetEntity acctSubjBudget, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(acctSubjBudget.getId())) {
			acctSubjBudget = acctSubjBudgetService.getEntity(AcctSubjBudgetEntity.class, acctSubjBudget.getId());
			req.setAttribute("acctSubjBudgetPage", acctSubjBudget);
		}
		return new ModelAndView("cn/com/linkwide/ba/acctsubjbudget/acctSubjBudget-add");
	}
	/**
	 * 科目项目对应表编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(AcctSubjBudgetEntity acctSubjBudget, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(acctSubjBudget.getId())) {
			acctSubjBudget = acctSubjBudgetService.getEntity(AcctSubjBudgetEntity.class, acctSubjBudget.getId());
			req.setAttribute("acctSubjBudgetPage", acctSubjBudget);
		}
		return new ModelAndView("cn/com/linkwide/ba/acctsubjbudget/acctSubjBudget-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","acctSubjBudgetController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(AcctSubjBudgetEntity acctSubjBudget,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(AcctSubjBudgetEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, acctSubjBudget, request.getParameterMap());
		List<AcctSubjBudgetEntity> acctSubjBudgets = this.acctSubjBudgetService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"科目项目对应表");
		modelMap.put(NormalExcelConstants.CLASS,AcctSubjBudgetEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("科目项目对应表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,acctSubjBudgets);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(AcctSubjBudgetEntity acctSubjBudget,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"科目项目对应表");
    	modelMap.put(NormalExcelConstants.CLASS,AcctSubjBudgetEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("科目项目对应表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<AcctSubjBudgetEntity> listAcctSubjBudgetEntitys = ExcelImportUtil.importExcel(file.getInputStream(),AcctSubjBudgetEntity.class,params);
				for (AcctSubjBudgetEntity acctSubjBudget : listAcctSubjBudgetEntitys) {
					acctSubjBudgetService.save(acctSubjBudget);
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
	@ApiOperation(value="科目项目对应表列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<AcctSubjBudgetEntity>> list() {
		List<AcctSubjBudgetEntity> listAcctSubjBudgets=acctSubjBudgetService.getList(AcctSubjBudgetEntity.class);
		return Result.success(listAcctSubjBudgets);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取科目项目对应表信息",notes="根据ID获取科目项目对应表信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		AcctSubjBudgetEntity task = acctSubjBudgetService.get(AcctSubjBudgetEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取科目项目对应表信息为空");
		}
		return Result.success(task);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建科目项目对应表")
	public ResponseMessage<?> create(@ApiParam(name="科目项目对应表对象")@RequestBody AcctSubjBudgetEntity acctSubjBudget, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<AcctSubjBudgetEntity>> failures = validator.validate(acctSubjBudget);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			acctSubjBudgetService.save(acctSubjBudget);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("科目项目对应表信息保存失败");
		}
		return Result.success(acctSubjBudget);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新科目项目对应表",notes="更新科目项目对应表")
	public ResponseMessage<?> update(@ApiParam(name="科目项目对应表对象")@RequestBody AcctSubjBudgetEntity acctSubjBudget) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<AcctSubjBudgetEntity>> failures = validator.validate(acctSubjBudget);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			acctSubjBudgetService.saveOrUpdate(acctSubjBudget);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("更新科目项目对应表信息失败");
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success("更新科目项目对应表信息成功");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除科目项目对应表")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" + id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			acctSubjBudgetService.deleteEntityById(AcctSubjBudgetEntity.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("科目项目对应表删除失败");
		}

		return Result.success();
	}
}
