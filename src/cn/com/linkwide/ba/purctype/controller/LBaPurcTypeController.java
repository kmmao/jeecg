package cn.com.linkwide.ba.purctype.controller;
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

import cn.com.linkwide.ba.purctype.entity.LBaPurcTypeEntity;
import cn.com.linkwide.ba.purctype.service.LBaPurcTypeServiceI;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**   
 * @Title: Controller  
 * @Description: 采购类型
 * @author onlineGenerator
 * @date 2018-07-05 20:52:53
 * @version V1.0   
 *
 */
@Api(value="LBaPurcType",description="采购类型",tags="lBaPurcTypeController")
@Controller
@RequestMapping("/lBaPurcTypeController")
public class LBaPurcTypeController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(LBaPurcTypeController.class);

	@Autowired
	private LBaPurcTypeServiceI lBaPurcTypeService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 采购类型列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("cn/com/linkwide/ba/purctype/lBaPurcTypeList");
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
	public void datagrid(LBaPurcTypeEntity lBaPurcType,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(LBaPurcTypeEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, lBaPurcType, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.lBaPurcTypeService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除采购类型
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(LBaPurcTypeEntity lBaPurcType, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		lBaPurcType = systemService.getEntity(LBaPurcTypeEntity.class, lBaPurcType.getId());
		message = "采购类型删除成功";
		try{
			lBaPurcTypeService.delete(lBaPurcType);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "采购类型删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除采购类型
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "采购类型删除成功";
		try{
			for(String id:ids.split(",")){
				LBaPurcTypeEntity lBaPurcType = systemService.getEntity(LBaPurcTypeEntity.class, 
				id
				);
				lBaPurcTypeService.delete(lBaPurcType);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "采购类型删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加采购类型
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(LBaPurcTypeEntity lBaPurcType, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "采购类型添加成功";
		try{
			lBaPurcTypeService.save(lBaPurcType);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "采购类型添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新采购类型
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(LBaPurcTypeEntity lBaPurcType, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "采购类型更新成功";
		LBaPurcTypeEntity t = lBaPurcTypeService.get(LBaPurcTypeEntity.class, lBaPurcType.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(lBaPurcType, t);
			lBaPurcTypeService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "采购类型更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 采购类型新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(LBaPurcTypeEntity lBaPurcType, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(lBaPurcType.getId())) {
			lBaPurcType = lBaPurcTypeService.getEntity(LBaPurcTypeEntity.class, lBaPurcType.getId());
			req.setAttribute("lBaPurcTypePage", lBaPurcType);
		}
		return new ModelAndView("cn/com/linkwide/ba/purctype/lBaPurcType-add");
	}
	/**
	 * 采购类型编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(LBaPurcTypeEntity lBaPurcType, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(lBaPurcType.getId())) {
			lBaPurcType = lBaPurcTypeService.getEntity(LBaPurcTypeEntity.class, lBaPurcType.getId());
			req.setAttribute("lBaPurcTypePage", lBaPurcType);
		}
		return new ModelAndView("cn/com/linkwide/ba/purctype/lBaPurcType-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","lBaPurcTypeController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(LBaPurcTypeEntity lBaPurcType,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(LBaPurcTypeEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, lBaPurcType, request.getParameterMap());
		List<LBaPurcTypeEntity> lBaPurcTypes = this.lBaPurcTypeService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"采购类型");
		modelMap.put(NormalExcelConstants.CLASS,LBaPurcTypeEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("采购类型列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,lBaPurcTypes);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(LBaPurcTypeEntity lBaPurcType,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"采购类型");
    	modelMap.put(NormalExcelConstants.CLASS,LBaPurcTypeEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("采购类型列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<LBaPurcTypeEntity> listLBaPurcTypeEntitys = ExcelImportUtil.importExcel(file.getInputStream(),LBaPurcTypeEntity.class,params);
				for (LBaPurcTypeEntity lBaPurcType : listLBaPurcTypeEntitys) {
					lBaPurcTypeService.save(lBaPurcType);
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
	@ApiOperation(value="采购类型列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<LBaPurcTypeEntity>> list() {
		List<LBaPurcTypeEntity> listLBaPurcTypes=lBaPurcTypeService.getList(LBaPurcTypeEntity.class);
		return Result.success(listLBaPurcTypes);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取采购类型信息",notes="根据ID获取采购类型信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		LBaPurcTypeEntity task = lBaPurcTypeService.get(LBaPurcTypeEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取采购类型信息为空");
		}
		return Result.success(task);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建采购类型")
	public ResponseMessage<?> create(@ApiParam(name="采购类型对象")@RequestBody LBaPurcTypeEntity lBaPurcType, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<LBaPurcTypeEntity>> failures = validator.validate(lBaPurcType);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			lBaPurcTypeService.save(lBaPurcType);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("采购类型信息保存失败");
		}
		return Result.success(lBaPurcType);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新采购类型",notes="更新采购类型")
	public ResponseMessage<?> update(@ApiParam(name="采购类型对象")@RequestBody LBaPurcTypeEntity lBaPurcType) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<LBaPurcTypeEntity>> failures = validator.validate(lBaPurcType);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			lBaPurcTypeService.saveOrUpdate(lBaPurcType);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("更新采购类型信息失败");
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success("更新采购类型信息成功");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除采购类型")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" + id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			lBaPurcTypeService.deleteEntityById(LBaPurcTypeEntity.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("采购类型删除失败");
		}

		return Result.success();
	}
}
