package cn.com.linkwide.ba.bacapiexp.controller;
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

import cn.com.linkwide.ba.bacapiexp.entity.BaCapiExpEntity;
import cn.com.linkwide.ba.bacapiexp.service.BaCapiExpServiceI;
import cn.com.linkwide.common.delcheck.service.DelCheckServiceI;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**   
 * @Title: Controller  
 * @Description: 资本性支出
 * @author onlineGenerator
 * @date 2018-10-24 20:47:53
 * @version V1.0   
 *
 */
@Api(value="BaCapiExp",description="资本性支出",tags="baCapiExpController")
@Controller
@RequestMapping("/baCapiExpController")
public class BaCapiExpController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(BaCapiExpController.class);

	@Autowired
	private BaCapiExpServiceI baCapiExpService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	@Autowired
	private DelCheckServiceI delCheckServiceI;


	/**
	 * 资本性支出列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("cn/com/linkwide/ba/bacapiexp/baCapiExpList");
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
	public void datagrid(BaCapiExpEntity baCapiExp,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(BaCapiExpEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, baCapiExp, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.baCapiExpService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除资本性支出
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(BaCapiExpEntity baCapiExp, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		baCapiExp = systemService.getEntity(BaCapiExpEntity.class, baCapiExp.getId());
		message = "资本性支出删除成功";
		try{
			//删除前校验是否被引用
			message =delCheckServiceI.generalDeleteCheck(BaCapiExpEntity.class, baCapiExp.getCode());
			if(StringUtil.isNotEmpty(message)){
				j.setMsg(message);
				return j;
			}
			baCapiExpService.delete(baCapiExp);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "资本性支出删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除资本性支出
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "资本性支出删除成功";
		try{
			for(String id:ids.split(",")){
				BaCapiExpEntity baCapiExp = systemService.getEntity(BaCapiExpEntity.class, 
				id
				);
				//删除前校验是否被引用
				message =delCheckServiceI.generalDeleteCheck(BaCapiExpEntity.class, baCapiExp.getCode());
				if(StringUtil.isNotEmpty(message)){
					j.setMsg(message);
					return j;
				}
				baCapiExpService.delete(baCapiExp);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "资本性支出删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加资本性支出
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(BaCapiExpEntity baCapiExp, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "资本性支出添加成功";
		try{
			baCapiExpService.save(baCapiExp);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "资本性支出添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新资本性支出
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(BaCapiExpEntity baCapiExp, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "资本性支出更新成功";
		BaCapiExpEntity t = baCapiExpService.get(BaCapiExpEntity.class, baCapiExp.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(baCapiExp, t);
			baCapiExpService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "资本性支出更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 资本性支出新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(BaCapiExpEntity baCapiExp, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(baCapiExp.getId())) {
			baCapiExp = baCapiExpService.getEntity(BaCapiExpEntity.class, baCapiExp.getId());
			req.setAttribute("baCapiExpPage", baCapiExp);
		}
		return new ModelAndView("cn/com/linkwide/ba/bacapiexp/baCapiExp-add");
	}
	/**
	 * 资本性支出编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(BaCapiExpEntity baCapiExp, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(baCapiExp.getId())) {
			baCapiExp = baCapiExpService.getEntity(BaCapiExpEntity.class, baCapiExp.getId());
			req.setAttribute("baCapiExpPage", baCapiExp);
		}
		return new ModelAndView("cn/com/linkwide/ba/bacapiexp/baCapiExp-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","baCapiExpController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(BaCapiExpEntity baCapiExp,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(BaCapiExpEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, baCapiExp, request.getParameterMap());
		List<BaCapiExpEntity> baCapiExps = this.baCapiExpService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"资本性支出");
		modelMap.put(NormalExcelConstants.CLASS,BaCapiExpEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("资本性支出列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,baCapiExps);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(BaCapiExpEntity baCapiExp,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"资本性支出");
    	modelMap.put(NormalExcelConstants.CLASS,BaCapiExpEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("资本性支出列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<BaCapiExpEntity> listBaCapiExpEntitys = ExcelImportUtil.importExcel(file.getInputStream(),BaCapiExpEntity.class,params);
				for (BaCapiExpEntity baCapiExp : listBaCapiExpEntitys) {
					baCapiExpService.save(baCapiExp);
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
	@ApiOperation(value="资本性支出列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<BaCapiExpEntity>> list() {
		List<BaCapiExpEntity> listBaCapiExps=baCapiExpService.getList(BaCapiExpEntity.class);
		return Result.success(listBaCapiExps);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取资本性支出信息",notes="根据ID获取资本性支出信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		BaCapiExpEntity task = baCapiExpService.get(BaCapiExpEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取资本性支出信息为空");
		}
		return Result.success(task);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建资本性支出")
	public ResponseMessage<?> create(@ApiParam(name="资本性支出对象")@RequestBody BaCapiExpEntity baCapiExp, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<BaCapiExpEntity>> failures = validator.validate(baCapiExp);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			baCapiExpService.save(baCapiExp);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("资本性支出信息保存失败");
		}
		return Result.success(baCapiExp);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新资本性支出",notes="更新资本性支出")
	public ResponseMessage<?> update(@ApiParam(name="资本性支出对象")@RequestBody BaCapiExpEntity baCapiExp) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<BaCapiExpEntity>> failures = validator.validate(baCapiExp);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			baCapiExpService.saveOrUpdate(baCapiExp);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("更新资本性支出信息失败");
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success("更新资本性支出信息成功");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除资本性支出")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" + id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			baCapiExpService.deleteEntityById(BaCapiExpEntity.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("资本性支出删除失败");
		}

		return Result.success();
	}
}
