package cn.com.linkwide.ba.bafunction.controller;
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

import cn.com.linkwide.ba.bafunction.entity.BaFunctionEntity;
import cn.com.linkwide.ba.bafunction.service.BaFunctionServiceI;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**   
 * @Title: Controller  
 * @Description: 功能分类
 * @author onlineGenerator
 * @date 2018-05-31 10:28:00
 * @version V1.0   
 *
 */
@Api(value="BaFunction",description="功能分类",tags="baFunctionController")
@Controller
@RequestMapping("/baFunctionController")
public class BaFunctionController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(BaFunctionController.class);

	@Autowired
	private BaFunctionServiceI baFunctionService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 功能分类列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("cn/com/linkwide/ba/bafunction/baFunctionList");
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
	public void datagrid(BaFunctionEntity baFunction,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(BaFunctionEntity.class, dataGrid);
		
		if(StringUtil.isEmpty(baFunction.getId())){
			cq.isNull("vparent");
		}else{
			cq.eq("vparent", systemService.get(BaFunctionEntity.class, baFunction.getId()).getVcode());
			baFunction.setId(null);
		}
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, baFunction, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.baFunctionService.getDataGridReturn(cq, true);
		TagUtil.treegrid(response, dataGrid);
//		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除功能分类
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(BaFunctionEntity baFunction, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		baFunction = systemService.getEntity(BaFunctionEntity.class, baFunction.getId());
		message = "功能分类删除成功";
		try{
			//删除前校验  是否存在下级科目
			String code = baFunction.getVcode();
			if(StringUtil.isNotEmpty(code)){
				List<BaFunctionEntity> list = systemService.findByProperty(BaFunctionEntity.class, "vparent", code);
				if(list!= null && list.size()>0){
					j.setMsg("存在下级科目，不能直接删除");
					return j;
				}
			}
			baFunctionService.delete(baFunction);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "功能分类删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除功能分类
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "功能分类删除成功";
		try{
			for(String id:ids.split(",")){
				BaFunctionEntity baFunction = systemService.getEntity(BaFunctionEntity.class, 
				id
				);
				baFunctionService.delete(baFunction);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "功能分类删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加功能分类
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(BaFunctionEntity baFunction, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "功能分类添加成功";
		try{
			BaFunctionEntity findUniqueByCode= systemService.findUniqueByProperty(BaFunctionEntity.class, "vcode", baFunction.getVcode());
			if(findUniqueByCode !=null){
				throw new BusinessException(baFunction.getVcode()+"编码已存在，请修改");
			}
			baFunctionService.save(baFunction);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(BusinessException b){
			b.getMessage();
		}catch(Exception e){
			e.printStackTrace();
			message = "功能分类添加失败";
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新功能分类
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(BaFunctionEntity baFunction, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "功能分类更新成功";
		BaFunctionEntity t = baFunctionService.get(BaFunctionEntity.class, baFunction.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(baFunction, t);
			baFunctionService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "功能分类更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 功能分类新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(BaFunctionEntity baFunction, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(baFunction.getId())) {
			baFunction = baFunctionService.getEntity(BaFunctionEntity.class, baFunction.getId());
			req.setAttribute("baFunctionPage", baFunction);
		}
		return new ModelAndView("cn/com/linkwide/ba/bafunction/baFunction-add");
	}
	/**
	 * 功能分类编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(BaFunctionEntity baFunction, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(baFunction.getId())) {
			baFunction = baFunctionService.getEntity(BaFunctionEntity.class, baFunction.getId());
			req.setAttribute("baFunctionPage", baFunction);
		}
		return new ModelAndView("cn/com/linkwide/ba/bafunction/baFunction-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","baFunctionController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(BaFunctionEntity baFunction,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(BaFunctionEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, baFunction, request.getParameterMap());
		List<BaFunctionEntity> baFunctions = this.baFunctionService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"功能分类");
		modelMap.put(NormalExcelConstants.CLASS,BaFunctionEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("功能分类列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,baFunctions);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(BaFunctionEntity baFunction,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"功能分类");
    	modelMap.put(NormalExcelConstants.CLASS,BaFunctionEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("功能分类列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
    	"导出信息"));
    	modelMap.put(NormalExcelConstants.DATA_LIST,new ArrayList());
    	return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	
	@RequestMapping(params = "importExcel", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson importExcel(HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		Map funMap =new HashMap();
		String sql =" select vcode \"vcode\",vname \"vname\" from ba_function ";
		List<Map<String,Object>> funMapList = systemService.findForJdbc(sql, new Object[]{});
		if(funMapList!= null &&funMapList.size()>0){
			for (Map<String, Object> map : funMapList) {
				funMap.put(map.get("vcode"), map.get("vname"));
			}
		}
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			MultipartFile file = entity.getValue();// 获取上传文件对象
			ImportParams params = new ImportParams();
			params.setTitleRows(2);
			params.setHeadRows(1);
			params.setNeedSave(true);
			try {
				List<BaFunctionEntity> listBaFunctionEntitys = ExcelImportUtil.importExcel(file.getInputStream(),BaFunctionEntity.class,params);
				for (BaFunctionEntity baFunction : listBaFunctionEntitys) {
					//校验重复
					String code =baFunction.getVcode();
					if(StringUtil.isNotEmpty(code)){
						String vname = funMap.get(code)==null?null:funMap.get(code).toString();
						if(StringUtil.isNotEmpty(vname)){
							j.setMsg("科目编码："+code+"重复");
							return j;
						}
					}else{
						j.setMsg("科目编码不能为空");
						return j;
					}
					
					String name = baFunction.getVname();
					if(StringUtil.isNotEmpty(name)){
						funMap.put(code, name);
					}else{
						j.setMsg("科目名称不能为空");
						return j;
					}
					
					baFunctionService.save(baFunction);
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
	@ApiOperation(value="功能分类列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<BaFunctionEntity>> list() {
		List<BaFunctionEntity> listBaFunctions=baFunctionService.getList(BaFunctionEntity.class);
		return Result.success(listBaFunctions);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取功能分类信息",notes="根据ID获取功能分类信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		BaFunctionEntity task = baFunctionService.get(BaFunctionEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取功能分类信息为空");
		}
		return Result.success(task);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建功能分类")
	public ResponseMessage<?> create(@ApiParam(name="功能分类对象")@RequestBody BaFunctionEntity baFunction, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<BaFunctionEntity>> failures = validator.validate(baFunction);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			baFunctionService.save(baFunction);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("功能分类信息保存失败");
		}
		return Result.success(baFunction);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新功能分类",notes="更新功能分类")
	public ResponseMessage<?> update(@ApiParam(name="功能分类对象")@RequestBody BaFunctionEntity baFunction) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<BaFunctionEntity>> failures = validator.validate(baFunction);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			baFunctionService.saveOrUpdate(baFunction);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("更新功能分类信息失败");
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success("更新功能分类信息成功");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除功能分类")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" + id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			baFunctionService.deleteEntityById(BaFunctionEntity.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("功能分类删除失败");
		}

		return Result.success();
	}
}
