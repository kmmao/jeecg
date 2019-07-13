package cn.com.linkwide.ba.receivetype.controller;
import cn.com.linkwide.ba.receivetype.entity.LBaReceiveTypeEntity;
import cn.com.linkwide.ba.receivetype.service.LBaReceiveTypeServiceI;
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
import org.jeecgframework.tag.vo.datatable.SortDirection;
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
 * @Description: 收发类别
 * @author onlineGenerator
 * @date 2018-07-19 18:47:51
 * @version V1.0   
 *
 */
@Api(value="LBaReceiveType",description="收发类别",tags="lBaReceiveTypeController")
@Controller
@RequestMapping("/lBaReceiveTypeController")
public class LBaReceiveTypeController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(LBaReceiveTypeController.class);

	@Autowired
	private LBaReceiveTypeServiceI lBaReceiveTypeService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 收发类别列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("cn/com/linkwide/ba/receivetype/lBaReceiveTypeList");
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
	public void datagrid(LBaReceiveTypeEntity lBaReceiveType,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(LBaReceiveTypeEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, lBaReceiveType, request.getParameterMap());
		try{
			//自定义追加查询条件
			cq.addOrder("code", SortDirection.asc);
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.lBaReceiveTypeService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除收发类别
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(LBaReceiveTypeEntity lBaReceiveType, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		lBaReceiveType = systemService.getEntity(LBaReceiveTypeEntity.class, lBaReceiveType.getId());
		message = "收发类别删除成功";
		try{
			String sql = "select 1 from l_ba_purc_type where in_type = ? ";
			List<Map<String,Object>> list = systemService.findForJdbc(sql, lBaReceiveType.getId());
			if(list != null && list.size()>0){
				j.setSuccess(false);
				j.setMsg("收发类别已经被采购类型引用,无法删除");
				return j;
			}
			lBaReceiveTypeService.delete(lBaReceiveType);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "收发类别删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除收发类别
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "收发类别删除成功";
		try{
			for(String id:ids.split(",")){
				String sql = "select 1 from l_ba_purc_type where in_type = ? ";
				List<Map<String,Object>> list = systemService.findForJdbc(sql, id);
				if(list != null && list.size()>0){
					j.setSuccess(true);
					j.setMsg("收发类别已经被采购类型引用,无法删除");
					return j;
				}
				
				LBaReceiveTypeEntity lBaReceiveType = systemService.getEntity(LBaReceiveTypeEntity.class, 
				id
				);
				lBaReceiveTypeService.delete(lBaReceiveType);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "收发类别删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加收发类别
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(LBaReceiveTypeEntity lBaReceiveType, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "收发类别添加成功";
		try{
			lBaReceiveTypeService.save(lBaReceiveType);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "收发类别添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新收发类别
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(LBaReceiveTypeEntity lBaReceiveType, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "收发类别更新成功";
		LBaReceiveTypeEntity t = lBaReceiveTypeService.get(LBaReceiveTypeEntity.class, lBaReceiveType.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(lBaReceiveType, t);
			lBaReceiveTypeService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "收发类别更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 收发类别新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(LBaReceiveTypeEntity lBaReceiveType, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(lBaReceiveType.getId())) {
			lBaReceiveType = lBaReceiveTypeService.getEntity(LBaReceiveTypeEntity.class, lBaReceiveType.getId());
			req.setAttribute("lBaReceiveTypePage", lBaReceiveType);
		}
		return new ModelAndView("cn/com/linkwide/ba/receivetype/lBaReceiveType-add");
	}
	/**
	 * 收发类别编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(LBaReceiveTypeEntity lBaReceiveType, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(lBaReceiveType.getId())) {
			lBaReceiveType = lBaReceiveTypeService.getEntity(LBaReceiveTypeEntity.class, lBaReceiveType.getId());
			req.setAttribute("lBaReceiveTypePage", lBaReceiveType);
		}
		return new ModelAndView("cn/com/linkwide/ba/receivetype/lBaReceiveType-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","lBaReceiveTypeController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(LBaReceiveTypeEntity lBaReceiveType,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(LBaReceiveTypeEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, lBaReceiveType, request.getParameterMap());
		List<LBaReceiveTypeEntity> lBaReceiveTypes = this.lBaReceiveTypeService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"收发类别");
		modelMap.put(NormalExcelConstants.CLASS,LBaReceiveTypeEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("收发类别列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,lBaReceiveTypes);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(LBaReceiveTypeEntity lBaReceiveType,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"收发类别");
    	modelMap.put(NormalExcelConstants.CLASS,LBaReceiveTypeEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("收发类别列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<LBaReceiveTypeEntity> listLBaReceiveTypeEntitys = ExcelImportUtil.importExcel(file.getInputStream(),LBaReceiveTypeEntity.class,params);
				for (LBaReceiveTypeEntity lBaReceiveType : listLBaReceiveTypeEntitys) {
					lBaReceiveTypeService.save(lBaReceiveType);
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
	@ApiOperation(value="收发类别列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<LBaReceiveTypeEntity>> list() {
		List<LBaReceiveTypeEntity> listLBaReceiveTypes=lBaReceiveTypeService.getList(LBaReceiveTypeEntity.class);
		return Result.success(listLBaReceiveTypes);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取收发类别信息",notes="根据ID获取收发类别信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		LBaReceiveTypeEntity task = lBaReceiveTypeService.get(LBaReceiveTypeEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取收发类别信息为空");
		}
		return Result.success(task);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建收发类别")
	public ResponseMessage<?> create(@ApiParam(name="收发类别对象")@RequestBody LBaReceiveTypeEntity lBaReceiveType, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<LBaReceiveTypeEntity>> failures = validator.validate(lBaReceiveType);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			lBaReceiveTypeService.save(lBaReceiveType);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("收发类别信息保存失败");
		}
		return Result.success(lBaReceiveType);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新收发类别",notes="更新收发类别")
	public ResponseMessage<?> update(@ApiParam(name="收发类别对象")@RequestBody LBaReceiveTypeEntity lBaReceiveType) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<LBaReceiveTypeEntity>> failures = validator.validate(lBaReceiveType);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			lBaReceiveTypeService.saveOrUpdate(lBaReceiveType);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("更新收发类别信息失败");
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success("更新收发类别信息成功");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除收发类别")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" + id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			lBaReceiveTypeService.deleteEntityById(LBaReceiveTypeEntity.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("收发类别删除失败");
		}

		return Result.success();
	}
}
