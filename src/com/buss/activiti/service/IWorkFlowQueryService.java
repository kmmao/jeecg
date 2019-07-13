package com.buss.activiti.service;

import java.util.List;

import org.activiti.engine.form.FormProperty;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;
import org.jeecgframework.web.system.pojo.base.TSUser;

import com.buss.activiti.entity.FormAttr;
import com.buss.activiti.entity.Node;
import com.buss.activiti.entity.TaskCommentAndFile;

/**
 * 流程查询接口
 * @author Administrator
 *
 */
public interface IWorkFlowQueryService {
	/***/
	public List<ProcessDefinition> queryProcessList(String processKey);
	/***/
	public List<ProcessDefinition> queryProcessList();
	
	/**任务查询********************************************************/
	/**根据人员ID，获取未处理任务*/
	public List<Task> queryTaskByUserId(String cUserID);
	/***根据业务系统结构组织，获取当前人的未处理的组任务*/
	public List<Task> queryGroupTaskByBuSIUserId(TSUser queryUser);
	/**根据人员ID，获取当前人的组任务（activiti组织架构）*/
	public List<Task> queryGroupTaskByUserId(String cUserID);
	/**根据人员ID获取其历史处理任务*/
	public List<Task> queryHistoryTaskByUser(String cUserID);
	
	/***工作流与业务单据关系***********************************************/
	/**根据任务ID，获取业务单据ID*/
	public String getBusiIdByTaskId(String taskId);
	
	
	/**任务信息查询********************************************************/
	/**根据任务ID获取其后的分支名称*/
	public List<String> getComeListByTaskId(String taskId);
	/**根据任务ID，获取流程实例的所有审批批注*/
	public List<TaskCommentAndFile> getWorkFlowComment(String taskId);
	/**依据流程定义xml 获取任务的动态表单信息*/
	public List<FormAttr> getDymaticFormXml_taskId(String taskId)  throws Exception ;;	
	/**依据流程定义xml 获取第一个用户任务信息*/
	public Node getFirstTaskNode(String processDefinitionId);
	/**依据流程定义xml 获取当前用户任务信息*/
	public Node getCurrentUserTaskNode(String taskId);
	
	/***/
	public List<FormProperty> getFormProperties(String taskId);
	

	
}
