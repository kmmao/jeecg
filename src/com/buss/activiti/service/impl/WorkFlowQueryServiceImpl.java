package com.buss.activiti.service.impl;

import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.form.FormProperty;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buss.activiti.entity.FormAttr;
import com.buss.activiti.entity.Node;
import com.buss.activiti.entity.TaskCommentAndFile;
import com.buss.activiti.service.IWorkFlowQueryService;
import com.buss.activiti.util.BillStatus;
import com.buss.activiti.util.StreamTool;
import com.buss.activiti.util.XMLTools;
@Service("workFlowQueryService")
public class WorkFlowQueryServiceImpl implements IWorkFlowQueryService {
	@Autowired
	private UserService userService;
	@Autowired
	private RepositoryService repositoryService;
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private TaskService taskService;
	@Autowired
	private FormService formService;
	@Autowired
	private HistoryService historyService;
	
	
	
	@Override
	public List<ProcessDefinition> queryProcessList() {
		
		List<ProcessDefinition> list = repositoryService
				.createProcessDefinitionQuery().list();
		return list;
	}
	/**
	 * 查询流程定义
	 */
	@Override
	public List<ProcessDefinition> queryProcessList(String processKey) {
		
		List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().
				processDefinitionKey(processKey).list();
		return list;
	}
	/**
	 * 查询任务列表
	 */
	@Override
	public List<Task> queryTaskByUserId(String cUserID) {
		List<Task> list = taskService.createTaskQuery().taskAssignee(cUserID).list();
		return list;
	}
	
	/**
	 * 查询组任务
	 */
	@Override
	public List<Task> queryGroupTaskByUserId(String cUserID) {
		List<Task> list = taskService.createTaskQuery()
				.taskCandidateUser(cUserID).list();
		return list;
	}
	/**
	 * 根据taskID获取业务单据ID,business_key
	 */
	@Override
	public String getBusiIdByTaskId(String taskId) {
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		if(task!=null){
			String processInstance = task.getProcessInstanceId();
			ProcessInstance process =   runtimeService.createProcessInstanceQuery().processInstanceId(processInstance).singleResult();
			String businessKey = process.getBusinessKey();
			return businessKey;
		}
		return null;
	}
	/**
	 * 根据taskID获取任务之后的所有分支名称
	 */
	@Override
	public List<String> getComeListByTaskId(String taskId) {
		
		//获取任务对象
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		//获取流程实例对象
		String processInstance = task.getProcessInstanceId();
		ProcessInstance process =   runtimeService.createProcessInstanceQuery()
				.processInstanceId(processInstance).singleResult();
		//获取当前活动ID		
		String activityId = process.getActivityId();
		
		//获取流程定义对象
		String processDefinitionId = task.getProcessDefinitionId();
		ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity) repositoryService
				.getProcessDefinition(processDefinitionId);
		//获取正在活动节点对象
		ActivityImpl activityImpl =  processDefinition.findActivity(activityId);
		List<PvmTransition>  list = activityImpl.getOutgoingTransitions();
		if(list==null||list.size()==0)
			return null;
		List<String> result = new ArrayList<String>();
		for (PvmTransition pvmTransition : list) {
			String name = (String) pvmTransition.getProperty("name");
			if(StringUtil.isNotEmpty(name))
				result.add(name);
			else if(result.contains(BillStatus.defaultComment)){
				continue;
			}else
				result.add(BillStatus.defaultComment);
		}
		return result;
	}
	/**
	 * 根据当前任务ID，获取工作流的历史批注信息
	 */
	@Override
	public List<TaskCommentAndFile> getWorkFlowComment(String taskId) {
		//审批批注
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		String processInstanceId = task.getProcessInstanceId();
		List<Comment> list = taskService.getProcessInstanceComments(processInstanceId);
		//审批附件
		List<HistoricVariableInstance> variableList = historyService.createHistoricVariableInstanceQuery()
        		.processInstanceId(processInstanceId).list();
		List<TaskCommentAndFile> resultList = TaskCommentAndFile.getTasCommentAndFile(list, variableList);
		return resultList;
	}
	
	@Override
	public List<FormProperty> getFormProperties(String taskId){
		List<FormProperty> list = formService.getTaskFormData(taskId).getFormProperties();
		return list;
	}
	
	/**
	 * 根据taskID 获取当前活动节点信息
	 */
	@Override
	public Node getCurrentUserTaskNode(String taskId) {
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		//获取流程定义资源XML
        String processXml = getResoureXml(task.getProcessDefinitionId());
        //获取当前执行的任务定义节点
        Node currentNode = getCurrentNode(processXml,task);
		return currentNode;
	}
	/**
	 * 根据流程实例ID，获取第一个用户任务节点信息
	 */
	@Override
	public Node getFirstTaskNode(String processDefinitionId) {
		//获取流程定义资源XML
        String processXml = getResoureXml(processDefinitionId);
        Node currentNode = getFirstUserTaskNode(processXml);
		return currentNode;
	}
	/**
	 * 获取任务的form 定义的xml文件
	 * @throws ParseException 
	 */
	@Override
	public List<FormAttr> getDymaticFormXml_taskId(String taskId)  throws Exception {
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		if(task!= null){
			//获取流程定义资源XML
			String processXml = getResoureXml(task.getProcessDefinitionId());
			//获取当前执行的任务定义节点
			Node currentNode = getFirstUserTaskNode(processXml);
			Node extensionElements = currentNode.getSubNodeByName("extensionElements");  
			if(extensionElements == null){
				return null;
			}
			//获取流程变量
			List<HistoricVariableInstance> variableList = historyService.createHistoricVariableInstanceQuery()
					.processInstanceId(task.getProcessInstanceId()).list();
			Map<String,Object> valueMap = new HashMap<String,Object>();
			for (HistoricVariableInstance historicVariableInstance : variableList) {
				Object o = historicVariableInstance.getValue();
				String key = historicVariableInstance.getVariableName();
				valueMap.put(key, o);
			}
			//返回自定义对象集合
			List<FormAttr> formMap = new ArrayList<FormAttr>();
			List<Node> formPropertyList = extensionElements.getChildrenList();  
			for (Node formProperty : formPropertyList) {  
				FormAttr fa = new FormAttr(formProperty);  
				fa.setDefaultValue(valueMap.get(fa.getId()));
				fa.setWritable(false);
				formMap.add(fa);  
			}  
			return formMap;
		}
		return null;
	}
	/**
	 * 获取当前执行任务定义Node
	 * @param processXml
	 * @param task
	 * @return
	 */
	public Node getCurrentNode(String processXml,Task task){
		//解析xml文件获取指定任务的form 属性
        Node root = XMLTools.Dom2Map(processXml);//使用dom4j解析xml文件 
        Node processNode = root.getSubNodeByName("process");    
        List<Node> childrenList = processNode.getChildrenList();
        //当前任务的任务定义ID
      	String taskDefinitionKey  = task.getTaskDefinitionKey();
        Node  currentNode=null ;
        for (Node node : childrenList) {
			if(!node.getName().equals("userTask"))
				continue;
			Map<String, String> map = node.getAttrMap();
			if(!map.get("id").equals(taskDefinitionKey))
				continue;
			currentNode = node;
			break;
		} 
        return currentNode;
	}
	/**
	 * 获取当前执行任务定义Node
	 * @param processXml
	 * @param task
	 * @return
	 */
	public Node getFirstUserTaskNode(String processXml){
		//解析xml文件获取指定任务的form 属性
        Node root = XMLTools.Dom2Map(processXml);//使用dom4j解析xml文件 
        Node processNode = root.getSubNodeByName("process");    
        List<Node> childrenList = processNode.getChildrenList();
        //当前任务的任务定义ID
        Node  currentNode=null ;
        for (Node node : childrenList) {
			if(!node.getName().equals("startEvent"))
				continue;
			currentNode = node;
			break;
		} 
        return currentNode;
	}
	/**
	 * 获取资源文件xml
	 * @param taskId
	 * @return
	 */
	public String getResoureXml(String processDefinitionId){		
		//获取流程定义对象
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).singleResult();
        
		//获取流程定义xml文件流
        String resourceName = processDefinition.getResourceName();   
        InputStream resourceAsStream = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(), resourceName); 
        return StreamTool.convertStreamToString(resourceAsStream);
	}
	
	
	/**
	 * 根据业务系统的人员组织架构获取人员的组任务
	 * 组任务分为两种：
	 * 1、a,b,c 人员字符串以','分割；
	 * 2、组ID；
	 */
	@Override
	public List<Task> queryGroupTaskByBuSIUserId(TSUser queryUser) {
		List<Task> tasks = new ArrayList<Task>();
		//1、人员s
		tasks.addAll(taskService.createTaskQuery().taskCandidateUser(queryUser.getUserName()).list());
		//2、组
		//获取查询人员的坐在角色组编码,多个角色组以‘，’分割 
		String roles = userService.getUserRole(queryUser);
		if(StringUtil.isEmpty(roles))
			return tasks;		
		List<String> candidateGroups = new ArrayList<String>();
		String[] roleArr = roles.split(",");
		Collections.addAll(candidateGroups, roleArr);
		tasks.addAll(taskService.createTaskQuery().taskCandidateGroupIn(candidateGroups).list());
		return tasks;
	}
	/**
	 * 查询cUserID，已办理任务
	 */
	@Override
	public List<Task> queryHistoryTaskByUser(String cUserID) {
//		historyService.createHistoricTaskInstanceQuery().
		return null;
	}
	
	
	
	
	
}
