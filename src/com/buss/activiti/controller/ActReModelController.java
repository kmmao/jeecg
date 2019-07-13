package com.buss.activiti.controller;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ProcessDefinition;
import org.apache.commons.io.IOUtils;
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
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

import com.buss.activiti.entity.ActReModelEntity;
import com.buss.activiti.entity.ActReProcdefEntity;
import com.buss.activiti.entity.WorkFlowCateEntity;
import com.buss.activiti.service.ActReModelServiceI;
import com.buss.activiti.service.WorkFlowCateServiceI;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**   
 * @Title: Controller  
 * @Description: actReModelController
 * @author onlineGenerator
 * @date 2017-07-17 15:20:14
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/actReModelController")
public class ActReModelController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ActReModelController.class);

	@Autowired
	private ActReModelServiceI actReModelService;
	
	@Autowired
	private SystemService systemService;
	
	@Autowired
	private Validator validator;
	
	@Autowired
	private RepositoryService repositoryService;


	@Autowired
	private WorkFlowCateServiceI workFlowCateService;
	
	protected ObjectMapper objectMapper = new ObjectMapper();
	/**
	 * act_re_model列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "actMain")
	public ModelAndView actMain(HttpServletRequest request) {
		return new ModelAndView("com/buss/activiti/actMain");
	}
	
	/**
	 * 根据分类,加载对应的流程
	 * 
	 * @return
	 */
	@RequestMapping(params = "cateModel")
	public ModelAndView cateModel(HttpServletRequest request) {
		String cateId = request.getParameter("cateId");
		WorkFlowCateEntity workFlowCateEntity = workFlowCateService.get(WorkFlowCateEntity.class, cateId);
		return new ModelAndView("redirect:/" + "actReModelController.do?list&category=" + workFlowCateEntity.getId());
	}
	
	
	/**
	 * act_re_model列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		String category = request.getParameter("category");
		if(category != null && !category.equals("")){
			request.setAttribute("category", category);
		}
		return new ModelAndView("com/buss/activiti/actReModelList");
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
	public void datagrid(ActReModelEntity actReModel,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {

		CriteriaQuery cq = new CriteriaQuery(ActReModelEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, actReModel, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.actReModelService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}


	/**
	 * 删除act_re_model
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(ActReModelEntity actReModel, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		actReModel = systemService.getEntity(ActReModelEntity.class, actReModel.getId());
		message = "模型删除成功";
		try{
			repositoryService.deleteModel(actReModel.getId());
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "模型删除失败";
		}
		j.setMsg(message);
		return j;
	}
		
	
	/**
	 * 批量删除act_re_model
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "模型删除成功";
		try{
			for(String id:ids.split(",")){
				repositoryService.deleteModel(id);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "模型删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	 
	/**
	 * 部署模型
	 * 
	 * @return
	 */
	@RequestMapping(params = "deploy")
	@ResponseBody
	public AjaxJson deploy(ActReModelEntity actReModel, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		actReModel = systemService.getEntity(ActReModelEntity.class, actReModel.getId());
		message = "模型部署成功";
		try{
            Model modelData = repositoryService.getModel(actReModel.getId());
            ObjectNode modelNode = (ObjectNode) new ObjectMapper().readTree(repositoryService.getModelEditorSource(modelData.getId()));
            byte[] bpmnBytes = null;

            BpmnModel model = new BpmnJsonConverter().convertToBpmnModel(modelNode);
            bpmnBytes = new BpmnXMLConverter().convertToXML(model);

            String processName = modelData.getName() + ".bpmn20.xml";
            ByteArrayInputStream in = new ByteArrayInputStream(bpmnBytes);
            Deployment deployment = repositoryService.createDeployment().name(modelData.getName())
            						.addInputStream(processName, in).deploy();
            						//.addString(processName, new String(bpmnBytes)).deploy();
           //流程部署后  给流程定义表赋名称
            ActReProcdefEntity actReProcdefEntity = systemService.findUniqueByProperty(ActReProcdefEntity.class, "deploymentId",deployment.getId() );
            actReProcdefEntity.setName(modelData.getName());
            systemService.saveOrUpdate(actReProcdefEntity);
            // 设置流程分类
			List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().deploymentId(deployment.getId()).list();
			for (ProcessDefinition processDefinition : list) {
				repositoryService.setProcessDefinitionCategory(processDefinition.getId(), modelData.getCategory());
			}
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "模型部署失败";
		}
		j.setMsg(message);
		return j;
	}
		

	/**
	 * 添加act_re_model
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(String name, String key,  String description,String category,ActReModelEntity actReModel, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		Map<String, Object> map = new HashMap<String, Object>();
		message = "模型添加成功";
		try{
			ObjectNode editorNode = objectMapper.createObjectNode();
			editorNode.put("id", "canvas");
			editorNode.put("resourceId", "canvas");
			ObjectNode properties = objectMapper.createObjectNode();
			properties.put("process_author", "jeesite");
			editorNode.put("properties", properties);
			ObjectNode stencilset = objectMapper.createObjectNode();
			stencilset.put("namespace", "http://b3mn.org/stencilset/bpmn2.0#");
			editorNode.put("stencilset", stencilset);
			
			Model modelData = repositoryService.newModel();
			description = StringUtils.defaultString(description);
			modelData.setKey(StringUtils.defaultString(key));
			modelData.setName(name);
			modelData.setCategory(category);
			modelData.setVersion(Integer.parseInt(String.valueOf(repositoryService.createModelQuery().modelKey(modelData.getKey()).count()+1)));

			ObjectNode modelObjectNode = objectMapper.createObjectNode();
			modelObjectNode.put(ModelDataJsonConstants.MODEL_NAME, name);
			modelObjectNode.put(ModelDataJsonConstants.MODEL_REVISION, modelData.getVersion());
			modelObjectNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, description);
			modelData.setMetaInfo(modelObjectNode.toString());
			
			repositoryService.saveModel(modelData);
			repositoryService.addModelEditorSource(modelData.getId(), editorNode.toString().getBytes("utf-8"));
			
			map.put("modelId", modelData.getId());
			j.setAttributes(map);
			
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "模型添加失败";
		}
		j.setMsg(message);
		return j;
	}

	
	/**
	 * 更新act_re_model
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(ActReModelEntity actReModel, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "模型更新成功";
		ActReModelEntity t = actReModelService.get(ActReModelEntity.class, actReModel.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(actReModel, t);
			actReModelService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "模型更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * act_re_model新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(ActReModelEntity actReModel, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(actReModel.getId())) {
			actReModel = actReModelService.getEntity(ActReModelEntity.class, actReModel.getId());
			req.setAttribute("actReModelPage", actReModel);
		}
		
		req.setAttribute("category", actReModel.getCategory());
		return new ModelAndView("com/buss/activiti/actReModel-add");
	}
	/**
	 * act_re_model编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(ActReModelEntity actReModel, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(actReModel.getId())) {
			actReModel = actReModelService.getEntity(ActReModelEntity.class, actReModel.getId());
			req.setAttribute("actReModelPage", actReModel);
		}
		return new ModelAndView("com/buss/activiti/actReModel-update");
	}
	
    /**
     * 导出model对象为指定类型
     *
     * @param modelId 模型ID
     * @param type    导出文件类型(bpmn\json)
     */
    @RequestMapping(value = "export/{modelId}/{type}")
    public void export(@PathVariable("modelId") String modelId,
                       @PathVariable("type") String type,
                       HttpServletResponse response) {
        try {
            Model modelData = repositoryService.getModel(modelId);
            BpmnJsonConverter jsonConverter = new BpmnJsonConverter();
            byte[] modelEditorSource = repositoryService.getModelEditorSource(modelData.getId());

            JsonNode editorNode = new ObjectMapper().readTree(modelEditorSource);
            BpmnModel bpmnModel = jsonConverter.convertToBpmnModel(editorNode);

            // 处理异常
            if (bpmnModel.getMainProcess() == null) {
                response.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());
                response.getOutputStream().println("no main process, can't export for type: " + type);
                response.flushBuffer();
                return;
            }

            String filename = "";
            byte[] exportBytes = null;

            String mainProcessId = bpmnModel.getMainProcess().getId();

            if (type.equals("bpmn")) {

                BpmnXMLConverter xmlConverter = new BpmnXMLConverter();
                exportBytes = xmlConverter.convertToXML(bpmnModel);

                filename = mainProcessId + ".bpmn20.xml";
            } else if (type.equals("json")) {

                exportBytes = modelEditorSource;
                filename = mainProcessId + ".json";

            }

            ByteArrayInputStream in = new ByteArrayInputStream(exportBytes);
            IOUtils.copy(in, response.getOutputStream());

            response.setHeader("Content-Disposition", "attachment; filename=" + filename);
            response.flushBuffer();
        } catch (Exception e) {
        	e.printStackTrace();
        	logger.error("导出model的xml文件失败!" + e);
        }
    }

	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","actReModelController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(ActReModelEntity actReModel,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(ActReModelEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, actReModel, request.getParameterMap());
		List<ActReModelEntity> actReModels = this.actReModelService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"act_re_model");
		modelMap.put(NormalExcelConstants.CLASS,ActReModelEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("act_re_model列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,actReModels);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(ActReModelEntity actReModel,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"act_re_model");
    	modelMap.put(NormalExcelConstants.CLASS,ActReModelEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("act_re_model列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
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
				List<ActReModelEntity> listActReModelEntitys = ExcelImportUtil.importExcel(file.getInputStream(),ActReModelEntity.class,params);
				for (ActReModelEntity actReModel : listActReModelEntitys) {
					actReModelService.save(actReModel);
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
	public List<ActReModelEntity> list() {
		List<ActReModelEntity> listActReModels=actReModelService.getList(ActReModelEntity.class);
		return listActReModels;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		ActReModelEntity task = actReModelService.get(ActReModelEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody ActReModelEntity actReModel, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ActReModelEntity>> failures = validator.validate(actReModel);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		try{
			actReModelService.save(actReModel);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = actReModel.getId();
		URI uri = uriBuilder.path("/rest/actReModelController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody ActReModelEntity actReModel) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ActReModelEntity>> failures = validator.validate(actReModel);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		try{
			actReModelService.saveOrUpdate(actReModel);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		actReModelService.deleteEntityById(ActReModelEntity.class, id);
	}
}
