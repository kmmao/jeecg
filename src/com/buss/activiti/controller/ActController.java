package com.buss.activiti.controller;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipInputStream;

import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;

import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**   
 * @Title: Controller  
 * @Description: actController
 * @author onlineGenerator
 * @date 2017-07-17 15:20:14
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/actController")
public class ActController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ActController.class);

	@Autowired
	private RepositoryService repositoryService;
	
	@Autowired
	private SystemService systemService;
	
	/**
	 * 流程定义列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {

		return new ModelAndView("com/buss/activiti/actProcessList");
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
	public void datagrid(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		
		ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery().orderByDeploymentId().desc();
		
		//类别
	    if (StringUtils.isNotEmpty(request.getParameter("category"))){
	    	processDefinitionQuery.processDefinitionCategory(request.getParameter("category"));
		}
	    
	    //流程定义id
	    if (StringUtils.isNotEmpty(request.getParameter("id"))){
	    	processDefinitionQuery.processDefinitionId(request.getParameter("id"));
		} 
	    
	    //名称
	    if (StringUtils.isNotEmpty(request.getParameter("name"))){
	    	processDefinitionQuery.processDefinitionNameLike("%" + request.getParameter("name") + "%");
		}
	    
	    
		List<ProcessDefinition> processDefinitionList = processDefinitionQuery.listPage(dataGrid.getFirstResult(), dataGrid.getRows());

		dataGrid.setTotal(Integer.parseInt(String.valueOf(processDefinitionQuery.count())));
		dataGrid.setResults(processDefinitionList);
		TagUtil.datagrid(response, dataGrid);
	}
	
    /**
     * 读取资源，通过部署ID
     *
     * @param processDefinitionId 流程定义
     * @param resourceType        资源类型(xml|image)
     * @throws Exception
     */
    @RequestMapping(params = "resourceRead")
    public void resourceRead(String processDefinitionId, String resourceType, HttpServletResponse response) throws Exception {
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).singleResult();
        String resourceName = "";
        if (resourceType.equals("image")) {
            resourceName = processDefinition.getDiagramResourceName();
        } else if (resourceType.equals("xml")) {
            resourceName = processDefinition.getResourceName();
            response.setHeader("Content-Disposition", "attachment; filename=" + resourceName);
            response.flushBuffer();
        }
        InputStream resourceAsStream = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(), resourceName);
        byte[] b = new byte[1024];
        int len = -1;
        while ((len = resourceAsStream.read(b, 0, 1024)) != -1) {
            response.getOutputStream().write(b, 0, len);
        }
        response.getOutputStream().close();
    }
    
    /**
     * 挂起、激活流程实例
     */
    @RequestMapping(params = "updateState")
    @ResponseBody
    public AjaxJson updateState(String state, String processDefinitionId,HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		try{
	        if ("active".equals(state)) {
	    		message = "已激活ID为[" + processDefinitionId + "]的流程定义。";
	            repositoryService.activateProcessDefinitionById(processDefinitionId, true, null);
	        } else if ("suspend".equals(state)) {
	        	message = "已挂起ID为[" + processDefinitionId + "]的流程定义。";
	            repositoryService.suspendProcessDefinitionById(processDefinitionId, true, null);
	        }
		}catch(Exception e){
			e.printStackTrace();
			message = "操作失败";
		}
		j.setMsg(message);
		return j;
    }
    
	/**
	 * 删除部署的流程，级联删除流程实例
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(String deploymentId, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "部署删除成功";
		try{
			repositoryService.deleteDeployment(deploymentId, true);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "部署删除失败";
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 将部署的流程转换为模型
	 * 
	 * @return
	 */
	@RequestMapping(params = "convertToModel")
	@ResponseBody
	public AjaxJson convertToModel(String processDefinitionId, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "转换成功";
		try{
			
			ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).singleResult();
			InputStream bpmnStream = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(),
			processDefinition.getResourceName());
			XMLInputFactory xif = XMLInputFactory.newInstance();
			InputStreamReader in = new InputStreamReader(bpmnStream, "UTF-8");
			XMLStreamReader xtr = xif.createXMLStreamReader(in);
			BpmnModel bpmnModel = new BpmnXMLConverter().convertToBpmnModel(xtr);
		
			BpmnJsonConverter converter = new BpmnJsonConverter();
			ObjectNode modelNode = converter.convertToJson(bpmnModel);
			org.activiti.engine.repository.Model modelData = repositoryService.newModel();
			modelData.setKey(processDefinition.getKey());
			modelData.setName(processDefinition.getResourceName());
			modelData.setCategory(processDefinition.getCategory());//.getDeploymentId());
			modelData.setDeploymentId(processDefinition.getDeploymentId());
			modelData.setVersion(Integer.parseInt(String.valueOf(repositoryService.createModelQuery().modelKey(modelData.getKey()).count()+1)));
		
			ObjectNode modelObjectNode = new ObjectMapper().createObjectNode();
			modelObjectNode.put(ModelDataJsonConstants.MODEL_NAME, processDefinition.getName());
			modelObjectNode.put(ModelDataJsonConstants.MODEL_REVISION, modelData.getVersion());
			modelObjectNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, processDefinition.getDescription());
			modelData.setMetaInfo(modelObjectNode.toString());
		
			repositoryService.saveModel(modelData);
		
			repositoryService.addModelEditorSource(modelData.getId(), modelNode.toString().getBytes("utf-8"));

			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "转换失败";
		}
		j.setMsg(message);
		return j;
	}
	
	
	/**
	 * 流程部署页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goDeploy")
	public ModelAndView goDeploy(HttpServletRequest req) {

		return new ModelAndView("com/buss/activiti/actProcessDeploy");
	}
	
	/**
	 * 流程部署导入
	 * @param category 流程分类
	 * @param fileName 文件名称
	 * @param filePath 文件路径
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(String category,String fileName,String filePath, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "流程定义部署成功";
		try{
	
			if (StringUtils.isBlank(fileName)){
				message = "请选择要部署的流程文件";
				j.setMsg(message);
				return j;
			}else{
			 	// 文件的硬盘真实路径
				String realPath = request.getSession().getServletContext().getRealPath("/") + "/" + filePath ;
				
				File file = new File(realPath);
				FileInputStream fileInputStream = new FileInputStream(file);
				Deployment deployment;
				String extension = FilenameUtils.getExtension(fileName);
				if (extension.equals("zip") || extension.equals("bar")) {
					ZipInputStream zip = new ZipInputStream(fileInputStream);
					deployment = repositoryService.createDeployment().addZipInputStream(zip).deploy();
				} else if (extension.equals("png")) {
					deployment = repositoryService.createDeployment().addInputStream(fileName, fileInputStream).deploy();
				} else if (fileName.indexOf("bpmn20.xml") != -1) {
					deployment = repositoryService.createDeployment().addInputStream(fileName, fileInputStream).deploy();
				} else if (extension.equals("bpmn")) { // bpmn扩展名特殊处理，转换为bpmn20.xml
					String baseName = FilenameUtils.getBaseName(fileName); 
					deployment = repositoryService.createDeployment().addInputStream(baseName + ".bpmn20.xml", fileInputStream).deploy();
				} else {
					message = "不支持的文件类型：" + extension;
					j.setMsg(message);
					return j;
				}
				
				
				List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().deploymentId(deployment.getId()).list();

				// 设置流程分类
				for (ProcessDefinition processDefinition : list) {
					repositoryService.setProcessDefinitionCategory(processDefinition.getId(), category);
					message += "部署成功，流程ID=" + processDefinition.getId() + "<br/>";
				}
				
				if (list.size() == 0){
					message = "部署失败，没有流程。";
				}

			}	

			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "流程定义部署失败";
		}
		j.setMsg(message);
		return j;
	}
}
