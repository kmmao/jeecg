package cn.com.linkwide.cont.desgin.conruledeasgin.controller;
import cn.com.linkwide.cont.desgin.conruledeasgin.entity.ConruleDeasginEntity;
import cn.com.linkwide.cont.desgin.conruledeasgin.service.ConruleDeasginServiceI;
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
 * @Description: 合同编码设置
 * @author onlineGenerator
 * @date 2018-11-30 16:52:39
 * @version V1.0   
 *
 */
@Api(value="ConruleDeasgin",description="合同编码设置",tags="conruleDeasginController")
@Controller
@RequestMapping("/conruleDeasginController")
public class ConruleDeasginController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ConruleDeasginController.class);

	@Autowired
	private ConruleDeasginServiceI conruleDeasginService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 合同编码设置列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("cn/com/linkwide/cont/desgin/conruledeasgin/conruleDeasginList");
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
	public void datagrid(ConruleDeasginEntity conruleDeasgin,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ConruleDeasginEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, conruleDeasgin, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.conruleDeasginService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除合同编码设置
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(ConruleDeasginEntity conruleDeasgin, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		conruleDeasgin = systemService.getEntity(ConruleDeasginEntity.class, conruleDeasgin.getId());
		message = "合同编码设置删除成功";
		try{
			conruleDeasginService.delete(conruleDeasgin);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "合同编码设置删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除合同编码设置
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "合同编码设置删除成功";
		try{
			for(String id:ids.split(",")){
				ConruleDeasginEntity conruleDeasgin = systemService.getEntity(ConruleDeasginEntity.class, 
				id
				);
				conruleDeasginService.delete(conruleDeasgin);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "合同编码设置删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加合同编码设置
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(ConruleDeasginEntity conruleDeasgin, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "合同编码设置添加成功";
		try{
			conruleDeasginService.save(conruleDeasgin);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "合同编码设置添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新合同编码设置
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(ConruleDeasginEntity conruleDeasgin, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "合同编码设置更新成功";
		ConruleDeasginEntity t = conruleDeasginService.get(ConruleDeasginEntity.class, conruleDeasgin.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(conruleDeasgin, t);
			conruleDeasginService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "合同编码设置更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 合同编码设置新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(ConruleDeasginEntity conruleDeasgin, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(conruleDeasgin.getId())) {
			conruleDeasgin = conruleDeasginService.getEntity(ConruleDeasginEntity.class, conruleDeasgin.getId());
			req.setAttribute("conruleDeasginPage", conruleDeasgin);
		}
		return new ModelAndView("cn/com/linkwide/cont/desgin/conruledeasgin/conruleDeasgin-add");
	}
	/**
	 * 合同编码设置编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(ConruleDeasginEntity conruleDeasgin, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(conruleDeasgin.getId())) {
			conruleDeasgin = conruleDeasginService.getEntity(ConruleDeasginEntity.class, conruleDeasgin.getId());
			req.setAttribute("conruleDeasginPage", conruleDeasgin);
		}
		return new ModelAndView("cn/com/linkwide/cont/desgin/conruledeasgin/conruleDeasgin-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","conruleDeasginController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(ConruleDeasginEntity conruleDeasgin,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(ConruleDeasginEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, conruleDeasgin, request.getParameterMap());
		List<ConruleDeasginEntity> conruleDeasgins = this.conruleDeasginService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"合同编码设置");
		modelMap.put(NormalExcelConstants.CLASS,ConruleDeasginEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("合同编码设置列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,conruleDeasgins);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(ConruleDeasginEntity conruleDeasgin,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"合同编码设置");
    	modelMap.put(NormalExcelConstants.CLASS,ConruleDeasginEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("合同编码设置列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<ConruleDeasginEntity> listConruleDeasginEntitys = ExcelImportUtil.importExcel(file.getInputStream(),ConruleDeasginEntity.class,params);
				for (ConruleDeasginEntity conruleDeasgin : listConruleDeasginEntitys) {
					conruleDeasginService.save(conruleDeasgin);
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
	@ApiOperation(value="合同编码设置列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<ConruleDeasginEntity>> list() {
		List<ConruleDeasginEntity> listConruleDeasgins=conruleDeasginService.getList(ConruleDeasginEntity.class);
		return Result.success(listConruleDeasgins);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取合同编码设置信息",notes="根据ID获取合同编码设置信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		ConruleDeasginEntity task = conruleDeasginService.get(ConruleDeasginEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取合同编码设置信息为空");
		}
		return Result.success(task);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建合同编码设置")
	public ResponseMessage<?> create(@ApiParam(name="合同编码设置对象")@RequestBody ConruleDeasginEntity conruleDeasgin, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ConruleDeasginEntity>> failures = validator.validate(conruleDeasgin);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			conruleDeasginService.save(conruleDeasgin);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("合同编码设置信息保存失败");
		}
		return Result.success(conruleDeasgin);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新合同编码设置",notes="更新合同编码设置")
	public ResponseMessage<?> update(@ApiParam(name="合同编码设置对象")@RequestBody ConruleDeasginEntity conruleDeasgin) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ConruleDeasginEntity>> failures = validator.validate(conruleDeasgin);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			conruleDeasginService.saveOrUpdate(conruleDeasgin);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("更新合同编码设置信息失败");
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success("更新合同编码设置信息成功");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除合同编码设置")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" + id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			conruleDeasginService.deleteEntityById(ConruleDeasginEntity.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("合同编码设置删除失败");
		}

		return Result.success();
	}
}
