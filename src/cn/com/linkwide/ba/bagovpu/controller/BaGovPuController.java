package cn.com.linkwide.ba.bagovpu.controller;
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

import cn.com.linkwide.ba.bagovpu.entity.BaGovPuEntity;
import cn.com.linkwide.ba.bagovpu.service.BaGovPuServiceI;
import cn.com.linkwide.common.delcheck.service.DelCheckServiceI;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**   
 * @Title: Controller  
 * @Description: 政府采购支出
 * @author onlineGenerator
 * @date 2018-10-24 20:47:36
 * @version V1.0   
 *
 */
@Api(value="BaGovPu",description="政府采购支出",tags="baGovPuController")
@Controller
@RequestMapping("/baGovPuController")
public class BaGovPuController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(BaGovPuController.class);

	@Autowired
	private BaGovPuServiceI baGovPuService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	@Autowired
	private DelCheckServiceI delCheckServiceI;
	


	/**
	 * 政府采购支出列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("cn/com/linkwide/ba/bagovpu/baGovPuList");
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
	public void datagrid(BaGovPuEntity baGovPu,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(BaGovPuEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, baGovPu, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.baGovPuService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除政府采购支出
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(BaGovPuEntity baGovPu, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		baGovPu = systemService.getEntity(BaGovPuEntity.class, baGovPu.getId());
		message = "政府采购支出删除成功";
		try{
			//删除前校验是否被引用
			message =delCheckServiceI.generalDeleteCheck(BaGovPuEntity.class, baGovPu.getCode());
			if(StringUtil.isNotEmpty(message)){
				j.setMsg(message);
				return j;
			}
			baGovPuService.delete(baGovPu);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "政府采购支出删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除政府采购支出
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "政府采购支出删除成功";
		try{
			for(String id:ids.split(",")){
				BaGovPuEntity baGovPu = systemService.getEntity(BaGovPuEntity.class, 
				id
				);
				//删除前校验是否被引用
				message =delCheckServiceI.generalDeleteCheck(BaGovPuEntity.class, baGovPu.getCode());
				if(StringUtil.isNotEmpty(message)){
					j.setMsg(message);
					return j;
				}
				baGovPuService.delete(baGovPu);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "政府采购支出删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加政府采购支出
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(BaGovPuEntity baGovPu, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "政府采购支出添加成功";
		try{
			baGovPuService.save(baGovPu);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "政府采购支出添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新政府采购支出
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(BaGovPuEntity baGovPu, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "政府采购支出更新成功";
		BaGovPuEntity t = baGovPuService.get(BaGovPuEntity.class, baGovPu.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(baGovPu, t);
			baGovPuService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "政府采购支出更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 政府采购支出新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(BaGovPuEntity baGovPu, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(baGovPu.getId())) {
			baGovPu = baGovPuService.getEntity(BaGovPuEntity.class, baGovPu.getId());
			req.setAttribute("baGovPuPage", baGovPu);
		}
		return new ModelAndView("cn/com/linkwide/ba/bagovpu/baGovPu-add");
	}
	/**
	 * 政府采购支出编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(BaGovPuEntity baGovPu, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(baGovPu.getId())) {
			baGovPu = baGovPuService.getEntity(BaGovPuEntity.class, baGovPu.getId());
			req.setAttribute("baGovPuPage", baGovPu);
		}
		return new ModelAndView("cn/com/linkwide/ba/bagovpu/baGovPu-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","baGovPuController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(BaGovPuEntity baGovPu,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(BaGovPuEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, baGovPu, request.getParameterMap());
		List<BaGovPuEntity> baGovPus = this.baGovPuService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"政府采购支出");
		modelMap.put(NormalExcelConstants.CLASS,BaGovPuEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("政府采购支出列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,baGovPus);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(BaGovPuEntity baGovPu,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"政府采购支出");
    	modelMap.put(NormalExcelConstants.CLASS,BaGovPuEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("政府采购支出列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<BaGovPuEntity> listBaGovPuEntitys = ExcelImportUtil.importExcel(file.getInputStream(),BaGovPuEntity.class,params);
				for (BaGovPuEntity baGovPu : listBaGovPuEntitys) {
					baGovPuService.save(baGovPu);
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
	@ApiOperation(value="政府采购支出列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<BaGovPuEntity>> list() {
		List<BaGovPuEntity> listBaGovPus=baGovPuService.getList(BaGovPuEntity.class);
		return Result.success(listBaGovPus);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取政府采购支出信息",notes="根据ID获取政府采购支出信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		BaGovPuEntity task = baGovPuService.get(BaGovPuEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取政府采购支出信息为空");
		}
		return Result.success(task);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建政府采购支出")
	public ResponseMessage<?> create(@ApiParam(name="政府采购支出对象")@RequestBody BaGovPuEntity baGovPu, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<BaGovPuEntity>> failures = validator.validate(baGovPu);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			baGovPuService.save(baGovPu);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("政府采购支出信息保存失败");
		}
		return Result.success(baGovPu);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新政府采购支出",notes="更新政府采购支出")
	public ResponseMessage<?> update(@ApiParam(name="政府采购支出对象")@RequestBody BaGovPuEntity baGovPu) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<BaGovPuEntity>> failures = validator.validate(baGovPu);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			baGovPuService.saveOrUpdate(baGovPu);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("更新政府采购支出信息失败");
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success("更新政府采购支出信息成功");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除政府采购支出")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" + id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			baGovPuService.deleteEntityById(BaGovPuEntity.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("政府采购支出删除失败");
		}

		return Result.success();
	}
}
