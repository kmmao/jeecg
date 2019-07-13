package cn.com.linkwide.cont.conmanage.controller;
import cn.com.linkwide.cont.conmanage.entity.ContractManageEntity;
import cn.com.linkwide.cont.conmanage.service.ContractManageServiceI;
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
 * @Description: 合同信息表
 * @author onlineGenerator
 * @date 2018-06-01 15:02:12
 * @version V1.0   
 *
 */
@Api(value="ContractManage",description="合同信息表",tags="contractManageController")
@Controller
@RequestMapping("/contractManageController")
public class ContractManageController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ContractManageController.class);

	@Autowired
	private ContractManageServiceI contractManageService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 合同信息表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "listp")
	public ModelAndView listp(HttpServletRequest request) {
		return new ModelAndView("cn/com/linkwide/cont/conmanagep/contractManageList");
	}
	/**
	 * 合同信息表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "listr")
	public ModelAndView listr(HttpServletRequest request) {
		return new ModelAndView("cn/com/linkwide/cont/conmanager/contractManageList");
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
	public void datagrid(ContractManageEntity contractManage,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ContractManageEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, contractManage, request.getParameterMap());
		try{
		//自定义追加查询条件
			//自定义追加查询条件
			String query_signDate_begin = request.getParameter("signDate_begin");
			String query_signDate_end = request.getParameter("signDate_end");
			if(StringUtil.isNotEmpty(query_signDate_begin)){
				cq.ge("signDate", new SimpleDateFormat("yyyy-MM-dd").parse(query_signDate_begin));
			}
			if(StringUtil.isNotEmpty(query_signDate_end)){
				cq.le("signDate", new SimpleDateFormat("yyyy-MM-dd").parse(query_signDate_end));
			}
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.contractManageService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除合同信息表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(ContractManageEntity contractManage, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		contractManage = systemService.getEntity(ContractManageEntity.class, contractManage.getId());
		message = "合同信息表删除成功";
		try{
			if(!"0".equals(contractManage.getConState()) && !"4".equals(contractManage.getConState())){
				j.setMsg("不是新建和作废状态的合同信息不能删除");
				return j;
				
			}
			contractManageService.delete(contractManage);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "合同信息表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除合同信息表
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "合同信息表删除成功";
		try{
			for(String id:ids.split(",")){
				ContractManageEntity contractManage = systemService.getEntity(ContractManageEntity.class, 
				id
				);
				if(!"0".equals(contractManage.getConState()) && !"4".equals(contractManage.getConState())){
					j.setMsg("不是新建和作废状态的合同信息不能删除");
					return j;
					
				}
				contractManageService.delete(contractManage);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "合同信息表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	 /**
	 * 批量审核合同信息表
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchCheck")
	@ResponseBody
	public AjaxJson doBatchCheck(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		String state = request.getParameter("state");
		String msg = request.getParameter("msg");
		message = "合同信息"+msg+"成功";
		try{
			for(String id:ids.split(",")){
				ContractManageEntity contractManage = systemService.getEntity(ContractManageEntity.class, 
				id
				);
				if("1".equals(state) && !"0".equals(contractManage.getConState())){
					j.setMsg("不是新建状态的合同信息不能"+msg);
					return j;
					
				}
				if("2".equals(state) && !"1".equals(contractManage.getConState())){
					j.setMsg("不是提交状态的合同信息不能"+msg);
					return j;
				}
				if("0".equals(state) && !"3".equals(contractManage.getConState())){
					j.setMsg("不是中止状态的合同信息不能"+msg);
					return j;
				}
				contractManage.setConState(state);
				contractManageService.saveOrUpdate(contractManage);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "合同信息"+msg+"失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 添加合同信息表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(ContractManageEntity contractManage, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "合同信息表添加成功";
		try{
			contractManageService.save(contractManage);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "合同信息表添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新合同信息表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(ContractManageEntity contractManage, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "合同信息表更新成功";
		ContractManageEntity t = contractManageService.get(ContractManageEntity.class, contractManage.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(contractManage, t);
			contractManageService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "合同信息表更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 合同信息表新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAddp")
	public ModelAndView goAddp(ContractManageEntity contractManage, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(contractManage.getId())) {
			contractManage = contractManageService.getEntity(ContractManageEntity.class, contractManage.getId());
			req.setAttribute("contractManagePage", contractManage);
		}
		return new ModelAndView("cn/com/linkwide/cont/conmanagep/contractManage-add");
	}
	/**
	 * 合同信息表新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAddr")
	public ModelAndView goAddr(ContractManageEntity contractManage, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(contractManage.getId())) {
			contractManage = contractManageService.getEntity(ContractManageEntity.class, contractManage.getId());
			req.setAttribute("contractManagePage", contractManage);
		}
		return new ModelAndView("cn/com/linkwide/cont/conmanager/contractManage-add");
	}
	/**
	 * 合同信息表编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdatep")
	public ModelAndView goUpdatep(ContractManageEntity contractManage, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(contractManage.getId())) {
			contractManage = contractManageService.getEntity(ContractManageEntity.class, contractManage.getId());
			req.setAttribute("contractManagePage", contractManage);
		}
		return new ModelAndView("cn/com/linkwide/cont/conmanagep/contractManage-update");
	}
	
	/**
	 * 合同信息表编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdater")
	public ModelAndView goUpdater(ContractManageEntity contractManage, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(contractManage.getId())) {
			contractManage = contractManageService.getEntity(ContractManageEntity.class, contractManage.getId());
			req.setAttribute("contractManagePage", contractManage);
		}
		return new ModelAndView("cn/com/linkwide/cont/conmanager/contractManage-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "uploadp")
	public ModelAndView uploadp(HttpServletRequest req) {
		req.setAttribute("controller_name","contractManageController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "uploadr")
	public ModelAndView uploadr(HttpServletRequest req) {
		req.setAttribute("controller_name","contractManageController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(ContractManageEntity contractManage,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(ContractManageEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, contractManage, request.getParameterMap());
		List<ContractManageEntity> contractManages = this.contractManageService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"合同信息表");
		modelMap.put(NormalExcelConstants.CLASS,ContractManageEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("合同信息表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,contractManages);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(ContractManageEntity contractManage,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"合同信息表");
    	modelMap.put(NormalExcelConstants.CLASS,ContractManageEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("合同信息表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<ContractManageEntity> listContractManageEntitys = ExcelImportUtil.importExcel(file.getInputStream(),ContractManageEntity.class,params);
				for (ContractManageEntity contractManage : listContractManageEntitys) {
					contractManageService.save(contractManage);
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
	@ApiOperation(value="合同信息表列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<ContractManageEntity>> list() {
		List<ContractManageEntity> listContractManages=contractManageService.getList(ContractManageEntity.class);
		return Result.success(listContractManages);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取合同信息表信息",notes="根据ID获取合同信息表信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		ContractManageEntity task = contractManageService.get(ContractManageEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取合同信息表信息为空");
		}
		return Result.success(task);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建合同信息表")
	public ResponseMessage<?> create(@ApiParam(name="合同信息表对象")@RequestBody ContractManageEntity contractManage, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ContractManageEntity>> failures = validator.validate(contractManage);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			contractManageService.save(contractManage);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("合同信息表信息保存失败");
		}
		return Result.success(contractManage);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新合同信息表",notes="更新合同信息表")
	public ResponseMessage<?> update(@ApiParam(name="合同信息表对象")@RequestBody ContractManageEntity contractManage) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ContractManageEntity>> failures = validator.validate(contractManage);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			contractManageService.saveOrUpdate(contractManage);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("更新合同信息表信息失败");
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success("更新合同信息表信息成功");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除合同信息表")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" + id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			contractManageService.deleteEntityById(ContractManageEntity.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("合同信息表删除失败");
		}

		return Result.success();
	}
}
