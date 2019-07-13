package com.buss.activiti.service;

import java.util.List;
import java.util.Map;

import org.activiti.engine.history.HistoricProcessInstance;
import org.jeecgframework.web.cgform.exception.BusinessException;

import com.buss.activiti.entity.WorkflowBean;
/**
 * 流程处理接口
 * @author Administrator
 *
 */
public interface IWorkFlowService {
	/**启动任务通过流程定义ID*/
	public <T> void startWorkFlowWithProcessId(String controllerName,T entity,String busiId,String processId,Map map) throws Exception ;
	/**启动任务通过流程定义ID*/
	public <T> void endWorkFlowWithProcessId(T entity,String workflowId, String busiId) throws Exception;
	/**启动任务*/
	public <T> void startWorkFlow(T entity,String busiId,String applyer) throws Exception ;
	/**根据业务单据ID获取流程信息*/
	public HistoricProcessInstance getProcessMsgFromBusiId(String className,String busiID) throws BusinessException ;
	/**删除流程定义*/
	public void delete(String id) throws Exception ;
	/**完成任务*/
	public void completeTakeByWorkflowBean(WorkflowBean workflowBean) throws Exception;
	/** 审批驳回 * */
	public void processReject(WorkflowBean workflowBean);
	/**认领任务*/
	public void changeAssign(String taskId, String userid);
	/**拾取任务*/
	public void claimAssign(String taskId, String userid) ;
	/**启动流程并完成第一个任务*/
	public <T> void startAndCompleteTask(T entity, String busiId) throws Exception;
	/**删除流程实例-级联删除*/
	public void deleteProcessInstance(String taskId,String deleteReason)throws Exception;
	/**启动流程并保存动态单据-map形式存储*/
	public void saveDymaticFormData(Map<String, String> formMap,String processDefinitionId) throws Exception ;
	
	/**自定义菜单工作量添加*/
 	public String addAutoForm(String formName,Map<String,Map<String,Object>> dataMap,
 			Map<String, List<Map<String, Object>>> oldDataMap,String processDefinitionId
 			) throws BusinessException ;
	
 	/**撤销提交**/
 	public String revokeWorkflowProcess(Map map);
 	
 	/**逐级退回**/
 	public void roleBackProcess(WorkflowBean workflowBean);
 	
 	/**查询流程审批记录**/
 	public List<Map<String,Object>> queryProcessRecord(Map params);
 
}
