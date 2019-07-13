package cn.com.linkwide.ba.babankrollsource.controller;
import cn.com.linkwide.ba.babankrollsource.entity.BaBankrollSourceEntity;
import cn.com.linkwide.ba.babankrollsource.service.BaBankrollSourceServiceI;
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
 * @Description: 资金来源
 * @author onlineGenerator
 * @date 2018-09-19 15:02:59
 * @version V1.0   
 *
 */
@Api(value="BaBankrollSource",description="资金来源",tags="baBankrollSourceController")
@Controller
@RequestMapping("/baBankrollSourceController")
public class BaBankrollSourceController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(BaBankrollSourceController.class);

	@Autowired
	private BaBankrollSourceServiceI baBankrollSourceService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 资金来源列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("cn/com/linkwide/ba/babankrollsource/baBankrollSourceList");
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
	public void datagrid(BaBankrollSourceEntity baBankrollSource,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(BaBankrollSourceEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, baBankrollSource, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.baBankrollSourceService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除资金来源
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(BaBankrollSourceEntity baBankrollSource, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		baBankrollSource = systemService.getEntity(BaBankrollSourceEntity.class, baBankrollSource.getId());
		message = "资金来源删除成功";
		try{
			baBankrollSourceService.delete(baBankrollSource);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "资金来源删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除资金来源
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "资金来源删除成功";
		try{
			for(String id:ids.split(",")){
				BaBankrollSourceEntity baBankrollSource = systemService.getEntity(BaBankrollSourceEntity.class, 
				id
				);
				baBankrollSourceService.delete(baBankrollSource);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "资金来源删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加资金来源
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(BaBankrollSourceEntity baBankrollSource, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "资金来源添加成功";
		try{
			baBankrollSourceService.save(baBankrollSource);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "资金来源添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新资金来源
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(BaBankrollSourceEntity baBankrollSource, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "资金来源更新成功";
		BaBankrollSourceEntity t = baBankrollSourceService.get(BaBankrollSourceEntity.class, baBankrollSource.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(baBankrollSource, t);
			baBankrollSourceService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "资金来源更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 资金来源新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(BaBankrollSourceEntity baBankrollSource, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(baBankrollSource.getId())) {
			baBankrollSource = baBankrollSourceService.getEntity(BaBankrollSourceEntity.class, baBankrollSource.getId());
			req.setAttribute("baBankrollSourcePage", baBankrollSource);
		}
		return new ModelAndView("cn/com/linkwide/ba/babankrollsource/baBankrollSource-add");
	}
	/**
	 * 资金来源编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(BaBankrollSourceEntity baBankrollSource, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(baBankrollSource.getId())) {
			baBankrollSource = baBankrollSourceService.getEntity(BaBankrollSourceEntity.class, baBankrollSource.getId());
			req.setAttribute("baBankrollSourcePage", baBankrollSource);
		}
		return new ModelAndView("cn/com/linkwide/ba/babankrollsource/baBankrollSource-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","baBankrollSourceController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(BaBankrollSourceEntity baBankrollSource,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(BaBankrollSourceEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, baBankrollSource, request.getParameterMap());
		List<BaBankrollSourceEntity> baBankrollSources = this.baBankrollSourceService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"资金来源");
		modelMap.put(NormalExcelConstants.CLASS,BaBankrollSourceEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("资金来源列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,baBankrollSources);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(BaBankrollSourceEntity baBankrollSource,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"资金来源");
    	modelMap.put(NormalExcelConstants.CLASS,BaBankrollSourceEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("资金来源列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<BaBankrollSourceEntity> listBaBankrollSourceEntitys = ExcelImportUtil.importExcel(file.getInputStream(),BaBankrollSourceEntity.class,params);
				for (BaBankrollSourceEntity baBankrollSource : listBaBankrollSourceEntitys) {
					baBankrollSourceService.save(baBankrollSource);
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
	@ApiOperation(value="资金来源列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<BaBankrollSourceEntity>> list() {
		List<BaBankrollSourceEntity> listBaBankrollSources=baBankrollSourceService.getList(BaBankrollSourceEntity.class);
		return Result.success(listBaBankrollSources);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取资金来源信息",notes="根据ID获取资金来源信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		BaBankrollSourceEntity task = baBankrollSourceService.get(BaBankrollSourceEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取资金来源信息为空");
		}
		return Result.success(task);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建资金来源")
	public ResponseMessage<?> create(@ApiParam(name="资金来源对象")@RequestBody BaBankrollSourceEntity baBankrollSource, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<BaBankrollSourceEntity>> failures = validator.validate(baBankrollSource);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			baBankrollSourceService.save(baBankrollSource);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("资金来源信息保存失败");
		}
		return Result.success(baBankrollSource);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新资金来源",notes="更新资金来源")
	public ResponseMessage<?> update(@ApiParam(name="资金来源对象")@RequestBody BaBankrollSourceEntity baBankrollSource) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<BaBankrollSourceEntity>> failures = validator.validate(baBankrollSource);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			baBankrollSourceService.saveOrUpdate(baBankrollSource);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("更新资金来源信息失败");
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success("更新资金来源信息成功");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除资金来源")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" + id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			baBankrollSourceService.deleteEntityById(BaBankrollSourceEntity.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("资金来源删除失败");
		}

		return Result.success();
	}
}
