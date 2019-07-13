package cn.com.linkwide.cont.contracttype.controller;
import cn.com.linkwide.cont.contracttype.entity.ContractTypeEntity;
import cn.com.linkwide.cont.contracttype.service.ContractTypeServiceI;
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
import org.jeecgframework.core.common.model.json.ComboTree;
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
import org.apache.batik.css.engine.SystemColorSupport;
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
 * @Description: 合同类型表
 * @author onlineGenerator
 * @date 2018-05-28 15:00:16
 * @version V1.0   
 *
 */
@Api(value="ContractType",description="合同类型表",tags="contractTypeController")
@Controller
@RequestMapping("/contractTypeController")
public class ContractTypeController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ContractTypeController.class);

	@Autowired
	private ContractTypeServiceI contractTypeService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 合同类型表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("cn/com/linkwide/cont/contracttype/contractTypeList");
	}
	
	/**
	 * 查询服务器信息
	 * 
	 * @return
	 */
	/*@RequestMapping(params = "systemInfo")
	public ModelAndView systemInfo(HttpServletRequest request) { 
		request.setAttribute("cpuid", SystemInfo.GetCpuID());
		request.setAttribute("mac", SystemInfo.GetMac());
		request.setAttribute("diskC", SystemInfo.getSerialNumber("c"));
		System.out.println(SystemInfo.getSerialNumber("c"));
		System.out.println(SystemInfo.getHdSerialInfo());
		request.setAttribute("diskC", SystemInfo.getHdSerialInfo());
		request.setAttribute("boardSN", SystemInfo.getMotherboardSN());
		return new ModelAndView("cn/com/linkwide/cont/contracttype/systemInfo");
	}*/

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(ContractTypeEntity contractType,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ContractTypeEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, contractType, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.contractTypeService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除合同类型表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(ContractTypeEntity contractType, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		contractType = systemService.getEntity(ContractTypeEntity.class, contractType.getId());
		message = "合同类型表删除成功";
		try{
			contractTypeService.delete(contractType);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "合同类型表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除合同类型表
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "合同类型表删除成功";
		try{
			for(String id:ids.split(",")){
				ContractTypeEntity contractType = systemService.getEntity(ContractTypeEntity.class, 
				id
				);
				contractTypeService.delete(contractType);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "合同类型表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加合同类型表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(ContractTypeEntity contractType, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "合同类型表添加成功";
		try{
			contractTypeService.save(contractType);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "合同类型表添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新合同类型表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(ContractTypeEntity contractType, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "合同类型表更新成功";
		ContractTypeEntity t = contractTypeService.get(ContractTypeEntity.class, contractType.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(contractType, t);
			contractTypeService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "合同类型表更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 合同类型表新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(ContractTypeEntity contractType, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(contractType.getId())) {
			contractType = contractTypeService.getEntity(ContractTypeEntity.class, contractType.getId());
			req.setAttribute("contractTypePage", contractType);
		}
		return new ModelAndView("cn/com/linkwide/cont/contracttype/contractType-add");
	}
	/**
	 * 合同类型表编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(ContractTypeEntity contractType, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(contractType.getId())) {
			contractType = contractTypeService.getEntity(ContractTypeEntity.class, contractType.getId());
			req.setAttribute("contractTypePage", contractType);
		}
		return new ModelAndView("cn/com/linkwide/cont/contracttype/contractType-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","contractTypeController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(ContractTypeEntity contractType,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(ContractTypeEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, contractType, request.getParameterMap());
		List<ContractTypeEntity> contractTypes = this.contractTypeService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"合同类型表");
		modelMap.put(NormalExcelConstants.CLASS,ContractTypeEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("合同类型表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,contractTypes);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(ContractTypeEntity contractType,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"合同类型表");
    	modelMap.put(NormalExcelConstants.CLASS,ContractTypeEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("合同类型表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<ContractTypeEntity> listContractTypeEntitys = ExcelImportUtil.importExcel(file.getInputStream(),ContractTypeEntity.class,params);
				for (ContractTypeEntity contractType : listContractTypeEntitys) {
					contractTypeService.save(contractType);
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
	@ApiOperation(value="合同类型表列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<ContractTypeEntity>> list() {
		List<ContractTypeEntity> listContractTypes=contractTypeService.getList(ContractTypeEntity.class);
		return Result.success(listContractTypes);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取合同类型表信息",notes="根据ID获取合同类型表信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		ContractTypeEntity task = contractTypeService.get(ContractTypeEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取合同类型表信息为空");
		}
		return Result.success(task);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建合同类型表")
	public ResponseMessage<?> create(@ApiParam(name="合同类型表对象")@RequestBody ContractTypeEntity contractType, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ContractTypeEntity>> failures = validator.validate(contractType);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			contractTypeService.save(contractType);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("合同类型表信息保存失败");
		}
		return Result.success(contractType);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新合同类型表",notes="更新合同类型表")
	public ResponseMessage<?> update(@ApiParam(name="合同类型表对象")@RequestBody ContractTypeEntity contractType) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ContractTypeEntity>> failures = validator.validate(contractType);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			contractTypeService.saveOrUpdate(contractType);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("更新合同类型表信息失败");
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success("更新合同类型表信息成功");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除合同类型表")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" + id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			contractTypeService.deleteEntityById(ContractTypeEntity.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("合同类型表删除失败");
		}

		return Result.success();
	}
	
	/**
	 * 加载树形结构
	 * 
	 * @return
	 */
	@RequestMapping(params = "contractTypeTree")
	@ResponseBody
	public List<ComboTree> contractTypeTree(HttpServletRequest request,ComboTree comboTree) {
		List<ComboTree> rootLists = new ArrayList<ComboTree>(); 
		this.contractTypeService.contractTypeTree(rootLists, comboTree.getId() == null ? request.getParameter("code") :  comboTree.getId());
		return rootLists;
	}

}
