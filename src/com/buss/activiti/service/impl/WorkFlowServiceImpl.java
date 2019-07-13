package com.buss.activiti.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmActivity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.pvm.process.ProcessDefinitionImpl;
import org.activiti.engine.impl.pvm.process.TransitionImpl;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Attachment;
import org.activiti.engine.task.Task;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.web.cgform.exception.BusinessException;
import org.jeecgframework.web.cgform.service.autoform.AutoFormServiceI;
import org.jeecgframework.web.system.pojo.base.TSBaseUser;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.buss.activiti.entity.FormAttr;
import com.buss.activiti.entity.Node;
import com.buss.activiti.entity.WorkflowBean;
import com.buss.activiti.service.IWorkFlowQueryService;
import com.buss.activiti.service.IWorkFlowService;
import com.buss.activiti.util.BILL_STATE;
import com.buss.activiti.util.BillStatus;
import com.buss.activiti.util.JsonUtils;
@SuppressWarnings({ "unchecked", "rawtypes","hiding","unused" })
@Service("workFlowService")
public class WorkFlowServiceImpl<T> implements IWorkFlowService {
	@Autowired
	private RepositoryService repositoryService;
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private TaskService taskService;
	@Autowired
	private HistoryService historyService;
	@Autowired
	private IWorkFlowQueryService workFlowQueryService;
	@Autowired
	private SystemService systemService;
//	@Autowired
//	private AutoFormServiceI autoFormService;
	
	ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
	/**
	 * 
	 * 在业务系统端，启动工作流通过流程定义ID
	 * 实现功能：
	 * 1、创建businessKey,根据工作流定义ID，启动工作流；
	 * 2、启动完成后，为接下来的任务赋值bill,controller_name
	 */
	@Override
	public <T> void startWorkFlowWithProcessId(String controllerName,T entity, String busiId, String processId,Map map) throws Exception {
		/**1、拼接businessKey:业务实体对象类全路径+对象ID*/
		String businessKey = entity.getClass().getCanonicalName()+"."+busiId;
		/**2、启动流程*/
		ProcessInstance processInstance = runtimeService.startProcessInstanceById(processId, businessKey);
		/**3、为第一个任务赋值单据变量*/
		String processInstanceId = processInstance.getProcessInstanceId();
		//单据状态字段名称
		String stateName = map.get("stateName")==null?null:map.get("stateName").toString(); 
		//单据实体的表名
		String tableName = map.get("tableName")==null?null:map.get("tableName").toString(); 
		//审批意见字段（需要回写到业务单据的传此字段）
		String auditRemark = map.get("auditRemark")==null?null:map.get("auditRemark").toString();
		//审批人（需要回写到业务单据的传此字段）
		String auditUser = map.get("auditUser")==null?null:map.get("auditUser").toString(); 
		//流转条件判断需要的字段
		String conditionPara = map.get("conditionPara")==null?null:map.get("conditionPara").toString();
		String conditionParaType = map.get("conditionParaType")==null?null:map.get("conditionParaType").toString();
		//是否审批时展示附件
		String isFile = map.get("isFile")==null?null:map.get("isFile").toString();
		//附件展示路径
		String fileUrl = map.get("fileUrl")==null?null:map.get("fileUrl").toString();
		//是否合同会签(为了区分会签同级人审批退回过后，单据再提交上后无须再审)
		String isConSign = map.get("isConSign")==null?null:map.get("isConSign").toString();
		
		 //是否多条单据合并审批(同时提交的单子生成一个待办任务)
		 String isMerge = map.get("isMerge")==null?null:map.get("isMerge").toString();
	     //需要审批的业务单据list页面
	     String batchListUrl = map.get("batchListUrl")==null?null:map.get("batchListUrl").toString();
	     //同一待办任务审批的多条单据的主键id拼接的字符串(中间用，隔开)
	     String batchListIds = map.get("batchListIds")==null?null:map.get("batchListIds").toString();
		
	     
		Map<String, Object> variables = new HashMap<String,Object>();
		//entity.getClass().getName();
		//合同会签标识(走一步起草人空节点)
		if("true".equals(isConSign)){
			Task task = taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
			taskService.setVariable(task.getId(),"controller_name", controllerName);
			taskService.setVariable(task.getId(),"tableName", tableName);
			taskService.setVariable(task.getId(), "bill", entity);
			taskService.setVariable(task.getId(),"stateName", stateName);
			if(StringUtil.isNotEmpty(auditRemark)){
				taskService.setVariable(task.getId(),"auditRemark", auditRemark);
			}
			if(StringUtil.isNotEmpty(auditUser)){
				taskService.setVariable(task.getId(),"auditUser", auditUser);
			}
			if(StringUtil.isNotEmpty(conditionPara)){
				taskService.setVariable(task.getId(),"conditionPara", conditionPara);
				String conditionParaArr[] =conditionPara.split(",");
				//根据流转条件查询单据对应的值
				String sql =" select "+JsonUtils.HumpToUnderline(conditionPara)+" from "+tableName+" where id='"+busiId+"'";
				List<Map<String,Object>> valueList = JsonUtils.formatHump(systemService.findForJdbc(sql, null));
				String conditionParaValue=null;
				if(valueList!= null && valueList.size()>0){
					for (int i = 0; i < conditionParaArr.length; i++) {
						String value =valueList.get(0).get(conditionParaArr[i])==null?null:valueList.get(0).get(conditionParaArr[i]).toString();
						conditionParaValue=conditionParaValue==null?value:conditionParaValue+","+value;
						taskService.setVariable(task.getId(),conditionParaArr[i], value);
					}
				}
				taskService.setVariable(task.getId(), conditionPara, conditionParaValue);
			}
			if(StringUtil.isNotEmpty(conditionParaType)){
				taskService.setVariable(task.getId(),"conditionParaType", conditionParaType);
			}
			if(StringUtil.isNotEmpty(isFile)){
				taskService.setVariable(task.getId(),"isFile", isFile);
			}
			if(StringUtil.isNotEmpty(fileUrl)){
				taskService.setVariable(task.getId(),"fileUrl", fileUrl);
			}
			if(StringUtil.isNotEmpty(isConSign)){
				taskService.setVariable(task.getId(),"isConSign", isConSign);
			}
			taskService.complete(task.getId(),variables);
		}
		//下一审批任务
		Map completeMap=new HashMap();
		List<Task> taskList = taskService.createTaskQuery().processInstanceId(processInstanceId).list();
		if(taskList!=null && taskList.size()>0){
			for (Task task : taskList) {
				taskService.setVariable(task.getId(),"controller_name", controllerName);
				taskService.setVariable(task.getId(),"tableName", tableName);
				taskService.setVariable(task.getId(), "bill", entity);
				taskService.setVariable(task.getId(),"stateName", stateName);
				if(StringUtil.isNotEmpty(auditRemark)){
					taskService.setVariable(task.getId(),"auditRemark", auditRemark);
				}
				if(StringUtil.isNotEmpty(auditUser)){
					taskService.setVariable(task.getId(),"auditUser", auditUser);
				}
				if(StringUtil.isNotEmpty(conditionPara)){
					taskService.setVariable(task.getId(),"conditionPara", conditionPara);
					String conditionParaArr[] =conditionPara.split(",");
					//根据流转条件查询单据对应的值
					String sql =" select "+JsonUtils.HumpToUnderline(conditionPara)+" from "+tableName+" where id='"+busiId+"'";
					List<Map<String,Object>> valueList = JsonUtils.formatHump(systemService.findForJdbc(sql, null));
					String conditionParaValue=null;
					if(valueList!= null && valueList.size()>0){
						for (int i = 0; i < conditionParaArr.length; i++) {
							String value =valueList.get(0).get(conditionParaArr[i])==null?null:valueList.get(0).get(conditionParaArr[i]).toString();
							conditionParaValue=conditionParaValue==null?value:conditionParaValue+","+value;
							taskService.setVariable(task.getId(),conditionParaArr[i], value);
						}
					}
					taskService.setVariable(task.getId(), conditionPara, conditionParaValue);
				}
				if(StringUtil.isNotEmpty(conditionParaType)){
					taskService.setVariable(task.getId(),"conditionParaType", conditionParaType);
				}
				if(StringUtil.isNotEmpty(isFile)){
					taskService.setVariable(task.getId(),"isFile", isFile);
				}
				if(StringUtil.isNotEmpty(fileUrl)){
					taskService.setVariable(task.getId(),"fileUrl", fileUrl);
				}
				if(StringUtil.isNotEmpty(isConSign)){
					taskService.setVariable(task.getId(),"isConSign", isConSign);
				}
				
				//批量审批需要传的参数
				if(StringUtil.isNotEmpty(isMerge)){
					taskService.setVariable(task.getId(),"isMerge", isMerge);
				}
				if(StringUtil.isNotEmpty(batchListUrl)){
					taskService.setVariable(task.getId(),"batchListUrl", batchListUrl);
				}
				if(StringUtil.isNotEmpty(batchListIds)){
					taskService.setVariable(task.getId(),"batchListIds", batchListIds);
				}
				
				//合同会签标识
				if("true".equals(isConSign)){
					String assignee=task.getAssignee();
					String hisSql =" select a.id_ \"id\",a.assignee_ \"assignee\",a.proc_def_id_,a.proc_inst_id_ \"proInstId\","
							+ " a.execution_id_,a.name_,"
							+ " case when  a.start_time_ is not null and a.end_time_ is not null and a.delete_reason_ is not null then '通过'  "
							+ " when to_char(c.message_) is null  and a.start_time_ is not null and a.end_time_ is null then '审核中' "
							+ " else '退回' end \"auditStatus\" "
							+ " from act_hi_taskinst a  left join act_hi_comment c on a.proc_inst_id_ = c.proc_inst_id_ and a.id_ = c.task_id_"
							+ " inner join act_hi_procinst b on a.proc_def_id_=b.proc_def_id_ and a.proc_inst_id_=b.proc_inst_id_ "
							+ " where a.proc_def_id_=? and b.business_key_=? "
							+ " and a.assignee_ ='"+assignee+"' "
							+ " and ((a.start_time_ is not null and a.end_time_ is not null and a.delete_reason_ is not null))"
							+ " order by a.start_time_, c.time_, a.end_time_, a.assignee_ ";
					List<Map<String,Object>> hisList = systemService.findForJdbc(hisSql, new Object[]{processId,businessKey});
					if(hisList!= null && hisList.size()>0 ){
						for (Map<String, Object> map2 : hisList) {
							completeMap.put(map2.get("assignee"),task.getId());
						}
					}
				}
			}
		}
		//上次会签审批已通过的本次会签审批默认通过
		if(completeMap!=null && !completeMap.isEmpty()){
			int number=0;
			for (Object value : completeMap.values()) { 
				number++;
				taskService.complete(value.toString(),variables);
				if(taskList.size()==completeMap.size()&& number==completeMap.size() ){
					//继续传下去  判断下一审批人上次是否已审批
					Map dataMap=new HashMap();
					dataMap.put("processInstanceId", processInstanceId);
					dataMap.put("controller_name", controllerName);
					dataMap.put("stateName", stateName);
					dataMap.put("tableName", tableName);
					dataMap.put("auditRemark", auditRemark);
					dataMap.put("auditUser", auditUser);
					dataMap.put("isFile", isFile);
					dataMap.put("fileUrl", fileUrl);
					dataMap.put("isConSign", isConSign);
					dataMap.put("businessKey", businessKey);
					dataMap.put("processId", processId);
					nextTask(dataMap, variables);
				}
			} 
		}else{
			//继续传下去  判断下一审批人上次是否已审批
			Map dataMap=new HashMap();
			dataMap.put("processInstanceId", processInstanceId);
			dataMap.put("controller_name", controllerName);
			dataMap.put("stateName", stateName);
			dataMap.put("tableName", tableName);
			dataMap.put("auditRemark", auditRemark);
			dataMap.put("auditUser", auditUser);
			dataMap.put("isFile", isFile);
			dataMap.put("fileUrl", fileUrl);
			dataMap.put("isConSign", isConSign);
			dataMap.put("businessKey", businessKey);
			dataMap.put("processId", processId);
			nextTask(dataMap, variables);
		}
	}
	
	/**
	 * 
	 * 在业务系统端，停止正在流程中的进程
	 * 实现功能：
	 * @throws BusinessException 
	 */
	@Override
	public <T> void endWorkFlowWithProcessId(T entity,String workflowId, String busiID) throws BusinessException {
		HistoricProcessInstance hisPorcessIns =
				getProcessMsgFromBusiId(entity.getClass().getCanonicalName(), busiID);
		String processInstanceId = hisPorcessIns.getSuperProcessInstanceId();
		Task task = taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
		taskService.deleteTask(task.getId());
	}


	/**
	 * 
	 * 在业务系统端，启动工作流
	 * 实现功能：
	 * 1、启动流程前指定办理人，一般作为下一个任务的处理人；【统一命名：userid】
	 * 2、根据工作流定义ID，启动工作流；【工作量定义ID与业务单据实体类类名一致】
	 * 3、启动完成后，将单据数据作为量赋给工作量；【统一名称：bill】
	 */
	@Override
	public <T> void startWorkFlow(T entity,String busiId,String applyer) throws Exception {
		String businessKey = entity.getClass().getCanonicalName()+"."+busiId;
		Map<String,Object> map = new HashMap<String,Object>();
		TSUser tsUser = systemService.getEntity(TSUser.class, applyer);
		map.put("userid", tsUser.getUserName());
		//启动流程
		ProcessInstance processInstance = runtimeService
				.startProcessInstanceByKey(entity.getClass().getSimpleName(), businessKey, map);
		
		//为第一个任务赋值单据变量
		String processInstanceId = processInstance.getProcessInstanceId();
		Task task = taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
		taskService.setVariable(task.getId(), "bill", entity);
	}
	/**
	 * 根据业务单据ID，获取流程信息
	 * businessKey规则：业务实体类类名+"."+busiId;
	 */
	@Override
	public HistoricProcessInstance getProcessMsgFromBusiId(String className,String busiID)
			throws BusinessException {
		String businessKey = className+"."+busiID;
		HistoricProcessInstance hisPorcessIns = historyService
				.createHistoricProcessInstanceQuery()
				.processInstanceBusinessKey(businessKey)
				.singleResult();
		return hisPorcessIns;
	}

	/**
	 * 删除流程定义--级联删除
	 */
	@Override
	public void delete(String id) throws Exception {
		repositoryService.deleteDeployment(id, true);
		
	}

	/**
	 * 完成任务
	 * 1、完成任务
	 * 2、保存审批批语
	 * 3、判断是否为最后一个流程，若是，改变单据状态为审批完成
	 * @throws Exception 
	 */
	@Override
	public void completeTakeByWorkflowBean(WorkflowBean workflowBean) throws Exception {
		String taskId = workflowBean.getTaskId();//获取任务id
		String outcome = workflowBean.getOutcome();//获取连线的名称
		String message = workflowBean.getComment()==null?outcome:workflowBean.getComment();//批注信息--设置默认批语
		String id = workflowBean.getBusId();//业务单据ID
		//若不存在单据ID，即为动态单据，返回；
		if(StringUtils.isEmpty(id))
			return;
		//流转条件需要判断的参数名称
		String conditionPara=workflowBean.getConditionPara();
		if(StringUtil.isNotEmpty(conditionPara)){
			String [] conditionParaArr =conditionPara.split(",");
			//参数的类型
			String conditionParaType = workflowBean.getConditionParaType();
			//流转条件判断需要的参数值
			String conditionParaValue = workflowBean.getConditionParaValue();
			if(StringUtil.isNotEmpty(conditionParaValue)){
				String [] conditionParaTypeArr =conditionParaType.split(",");
				String [] conditionParaValueArr=conditionParaValue.split(",");
				for (int i = 0; i < conditionParaArr.length; i++) {
					if("double".equals(conditionParaTypeArr[i])){
						taskService.setVariable(taskId, conditionParaArr[i], new Double(conditionParaValueArr[i].replaceAll(",", "")));
					}else if("long".equals(conditionParaTypeArr[i])){
						taskService.setVariable(taskId, conditionParaArr[i], Long.parseLong(conditionParaValueArr[i].replaceAll(",", "")));
					}else{
						taskService.setVariable(taskId,  conditionParaArr[i], conditionParaValueArr[i]);
					}
				}
			}
		}
		
		String businessKey = workFlowQueryService.getBusiIdByTaskId(taskId);
//		T entity = (T) taskService.getVariable(taskId, "bill");
		String controller_name =null; //(String) taskService.getVariable(taskId, "controller_name");
		String stateName = null;//(String) taskService.getVariable(taskId, "stateName"); //单据状态名称
    	String tableName = null;//(String) taskService.getVariable(taskId, "tableName");
    	String auditRemark =null;//(String) taskService.getVariable(taskId, "auditRemark"); //审批意见字段
    	String auditUser = null;//(String) taskService.getVariable(taskId, "auditUser"); //审批人字段
    	String isFile=null;
    	String fileUrl=null;
    	String isConSign=null;
    	String isMerge=null; //是否多条单据合一审批
    	String batchListUrl=null;
    	String batchListIds =null;
    	//a.EXECUTION_ID_=b.EXECUTION_ID_ and
    	String sql = "SELECT a.NAME_ \"name\", TEXT_  \"text\" from ACT_RU_VARIABLE a inner join ACT_RU_TASK b on  a.PROC_INST_ID_=b.PROC_INST_ID_ "
    			+ " where b.ID_='"+taskId+"'  ";
		List<Map<String,Object>> list = systemService.findForJdbc(sql, new Object[]{});
		if(list!= null && list.size()>0){
			for (Map<String, Object> map : list) {
				String name = map.get("name")==null?null:map.get("name").toString();
				if("controller_name".equals(name)){
					controller_name = map.get("text")==null?null:map.get("text").toString();
				}else if("stateName".equals(name)){
					stateName = map.get("text")==null?null:map.get("text").toString();
				}else if("tableName".equals(name)){
					tableName = map.get("text")==null?null:map.get("text").toString();
				}else if("auditRemark".equals(name)){
					auditRemark = map.get("text")==null?null:map.get("text").toString();
				}else if("auditUser".equals(name)){
					auditUser = map.get("text")==null?null:map.get("text").toString();
				}else if("isFile".equals(name)){
					isFile = map.get("text")==null?null:map.get("text").toString();
				}else if("fileUrl".equals(name)){
					fileUrl = map.get("text")==null?null:map.get("text").toString();
				}else if("isConSign".equals(name)){
					isConSign = map.get("text")==null?null:map.get("text").toString();
				}else if("isMerge".equals(name)){
					isMerge = map.get("text")==null?null:map.get("text").toString();
				}else if("batchListUrl".equals(name)){
					batchListUrl = map.get("text")==null?null:map.get("text").toString();
				}else if("batchListIds".equals(name)){
					batchListIds = map.get("text")==null?null:map.get("text").toString();
				}
			}
		}
    	
    	int index = businessKey.lastIndexOf(".");
		String className = businessKey.substring(0,index); //单据实体名称
		//修改单据的审批意见
		Map auditMap = new HashMap();
    	auditMap.put("busiId", id);
    	auditMap.put("className", className);
    	auditMap.put("message", message);
    	auditMap.put("auditRemark", auditRemark);
    	auditMap.put("auditUser", auditUser);
    	updateBusiAudit(auditMap);
		//1、获取任务对象
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		//流程定义id
		String processId =task.getProcessDefinitionId();
		//判断是否权限审批
		//获取任务审批人
		String assign = task.getAssignee();
//		//获取当前登录用户
		TSBaseUser currentUser = ResourceUtil.getSessionUserName();
		if(!currentUser.getUserName().equals(assign)){ //无权限审批 
			throw new BusinessException("当前没有审批该节点的权限！");
		}
		
		//向批注表中插入审批批语
		String processInstanceId = task.getProcessInstanceId();
		Authentication.setAuthenticatedUserId(ResourceUtil.getSessionUserName().getId());//设置当前审批人
		if(StringUtil.isEmpty(message)){
			message="同意";
		}
		taskService.addComment(taskId, processInstanceId, message);//保存审批批语
		
		Map<String, Object> variables = new HashMap<String,Object>();
		 // 取得流程实例
        ProcessInstance instance = runtimeService .createProcessInstanceQuery() .processInstanceId(processInstanceId).singleResult();
        variables = instance.getProcessVariables();
		//2、根据连线名称设置变量，自动选择下一个任务
		if(outcome!=null && !outcome.equals(BillStatus.defaultComment)){
			variables.put("outcome", outcome);
		}
		//3、上传附件
		//判断有无附件		
		String filePath = workflowBean.getFilePath();
		if(!StringUtils.isEmpty(filePath)){
			//保存路径
			variables.put("filePath"+taskId, filePath);
			//保存附件信息
			String userId =currentUser.getId();
			String fileName=workflowBean.getFilename(); //附件名称
			String type=filePath.substring(filePath.lastIndexOf(".")+1,filePath.length()); //附件类型
			createAttachment(userId, type, taskId, processInstanceId, fileName, null, filePath);
		}
		//4、完成任务
		if ((BillStatus.defaultComment).equals(outcome)) { //同意
			try {
				taskService.complete(workflowBean.getTaskId(), variables);
			} catch (Exception e) {
				e.printStackTrace();
				// XXX 打印出错信息--因不定时出错。定位不到原因所有打印报错信息    注释人:李伟华 解决后取消注释
				this.systemService.addLog("审批出错:"+e.getMessage(), Globals.Log_Type_OTHER, Globals.Log_Leavel_INFO);
				// 暂时注释掉驳回流程 ---作者:李伟华
				//processReject(workflowBean);
				return;
			}
		}else{ //驳回
			processReject(workflowBean);
		}
		
		//5、判断流程是否结束
		ProcessInstance  processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
		if(processInstance==null){
			if (outcome.equals(BillStatus.defaultComment)) {
				if("Y".equals(isMerge) && StringUtil.isNotEmpty(batchListIds)){ //多条单据同一待办任务审批
					String[] idsArr =batchListIds.split(",");
					for (int i = 0; i < idsArr.length; i++) { //批量修改单据状态为已审核
						updateBusiStatus(className,stateName, BILL_STATE.CHECKED, idsArr[i],null);
					}
				}else{ //单条待办任务审批
					updateBusiStatus(className,stateName, BILL_STATE.CHECKED, id,null);
				}
			}else{
				updateBusiStatus(className,stateName, BILL_STATE.REJECT, id,null);
			}			
		}else{
			//将业务controllerName，继续传下去；
			Map dataMap=new HashMap();
			dataMap.put("processInstanceId", processInstanceId);
			dataMap.put("controller_name", controller_name);
			dataMap.put("stateName", stateName);
			dataMap.put("tableName", tableName);
			dataMap.put("auditRemark", auditRemark);
			dataMap.put("auditUser", auditUser);
			dataMap.put("isFile", isFile);
			dataMap.put("fileUrl", fileUrl);
			dataMap.put("isConSign", isConSign);
			dataMap.put("businessKey", businessKey);
			dataMap.put("processId", processId);
			nextTask(dataMap, variables);
		}
	}
	
	/**
	 * 将参数继续传下去
	 * @param dataMap
	 * @param variables
	 */
	private void nextTask(Map dataMap,Map<String,Object> variables){
		String processInstanceId =dataMap.get("processInstanceId")==null?null:dataMap.get("processInstanceId").toString();
		String controller_name =dataMap.get("controller_name")==null?null:dataMap.get("controller_name").toString();
		String stateName =dataMap.get("stateName")==null?null:dataMap.get("stateName").toString();
		String tableName =dataMap.get("tableName")==null?null:dataMap.get("tableName").toString();
		String auditRemark =dataMap.get("auditRemark")==null?null:dataMap.get("auditRemark").toString();
		String auditUser =dataMap.get("auditUser")==null?null:dataMap.get("auditUser").toString();
		String isFile =dataMap.get("isFile")==null?null:dataMap.get("isFile").toString();
		String fileUrl=dataMap.get("fileUrl")==null?null:dataMap.get("fileUrl").toString();
		String isConSign =dataMap.get("isConSign")==null?null:dataMap.get("isConSign").toString();
		String businessKey=dataMap.get("businessKey")==null?null:dataMap.get("businessKey").toString();
		String processId=dataMap.get("processId")==null?null:dataMap.get("processId").toString();
		//将业务controllerName，继续传下去；
		List<Task> nextTaskList = taskService.createTaskQuery().processInstanceId(processInstanceId).list();
		//会签的审批人们
		String assignees=null;
		Map hisMap = new HashMap();
		if(nextTaskList!=null && nextTaskList.size()>0){
			for (Task nextTask : nextTaskList) {
				//审批人
				String assignee =nextTask.getAssignee();
				if(nextTaskList.size()>0){
					hisMap.put(assignee, nextTask.getId());
					assignees=assignees==null?"'"+assignee+"'":assignees+",'"+assignee+"'";
				}
				taskService.setVariable(nextTask.getId(),"controller_name", controller_name);
				taskService.setVariable(nextTask.getId(),"stateName", stateName);
				taskService.setVariable(nextTask.getId(),"tableName", tableName);
				taskService.setVariable(nextTask.getId(),"auditRemark", auditRemark);
				taskService.setVariable(nextTask.getId(),"auditUser", auditUser);
				taskService.setVariable(nextTask.getId(), "isFile", isFile);
				taskService.setVariable(nextTask.getId(), "fileUrl", fileUrl);
				taskService.setVariable(nextTask.getId(), "isConSign", isConSign);
			}
			
			//是否是合同会签的需求,同一条单据统计会签人审批通过下次无须再审
			if("true".equals(isConSign)){
				//查询历史会签通过的数据
				String hisSql =" select a.id_ \"id\",a.assignee_ \"assignee\",a.proc_def_id_,a.proc_inst_id_ \"proInstId\","
						+ " a.execution_id_,a.name_,"
						+ " case when  a.start_time_ is not null and a.end_time_ is not null and a.delete_reason_ is not null then '通过'  "
						+ " when to_char(c.message_) is null  and a.start_time_ is not null and a.end_time_ is null then '审核中' "
						+ " else '退回' end \"auditStatus\" "
						+ " from act_hi_taskinst a  left join act_hi_comment c on a.proc_inst_id_ = c.proc_inst_id_ and a.id_ = c.task_id_"
						+ " inner join act_hi_procinst b on a.proc_def_id_=b.proc_def_id_ and a.proc_inst_id_=b.proc_inst_id_ "
						+ " where a.proc_def_id_=? and b.business_key_=? "
						+ " and a.assignee_ in ("+assignees+") "
						+ " and ((a.start_time_ is not null and a.end_time_ is not null and a.delete_reason_ is not null)"
						+ "  or ( to_char(c.message_) is null and a.start_time_ is not null and a.end_time_ is null ))"
						+ " order by a.start_time_, c.time_, a.end_time_, a.assignee_ ";
				List<Map<String,Object>> hisList = systemService.findForJdbc(hisSql, new Object[]{processId,businessKey});
				if(hisList!= null && hisList.size()>0 ){ //&& nextTaskList.size()!=hisList.size()
					Map completeMap=new HashMap(); 
					Map notCompleteMap=new HashMap(); //上次未审批的记录
					for (Map<String, Object> map : hisList) {
						String nextTaskId= hisMap.get(map.get("assignee"))==null?null:hisMap.get(map.get("assignee")).toString();
						if(StringUtil.isNotEmpty(nextTaskId)){
							//deleteSignHisTask(nextTaskId);
							//deleteProcessInstance(nextTaskId, "会签已审过");
							String auditStatus = map.get("auditStatus")==null?null:map.get("auditStatus").toString();
							if("通过".equals(auditStatus)){
								completeMap.put(map.get("assignee"),nextTaskId); 
							}else if("审核中".equals(auditStatus)){
								if(notCompleteMap!= null && !notCompleteMap.isEmpty()){
									if(!StringUtil.isNotEmpty(notCompleteMap.get(map.get("assignee")))){
										notCompleteMap.put(map.get("assignee"),map.get("id"));
									}
								}else{
									notCompleteMap.put(map.get("assignee"),map.get("id")); 
								}
							}
						}else{
							taskService.setVariable(nextTaskId,"controller_name", controller_name);
							taskService.setVariable(nextTaskId,"stateName", stateName);
							taskService.setVariable(nextTaskId,"tableName", tableName);
							taskService.setVariable(nextTaskId,"auditRemark", auditRemark);
							taskService.setVariable(nextTaskId,"auditUser", auditUser);
							taskService.setVariable(nextTaskId, "isFile", isFile);
							taskService.setVariable(nextTaskId, "fileUrl", fileUrl);
							taskService.setVariable(nextTaskId, "isConSign", isConSign);
						}
					}
					//上次会签审批已通过的本次会签审批默认通过
					if(completeMap!=null && !completeMap.isEmpty()){
						for (Object value : completeMap.values()) { 
							Task task = taskService.createTaskQuery().taskId(value.toString()).singleResult();
							if(task!= null){
								taskService.complete(value.toString(), variables);
							}
							nextTask(dataMap, variables);
						} 
					}
					//每个用户尚未审核的只保留一个待办任务
					if(notCompleteMap!=null && !notCompleteMap.isEmpty()){
						for (Object value : notCompleteMap.values()) { 
							if(!hisMap.containsValue(value)){
								String querySql= "select execution_id_ \"executionId\" from act_ru_task where id_=?";
								List<Map<String,Object>> listMap=systemService.findForJdbc(querySql, new Object[]{value.toString()});
								if(listMap!= null && listMap.size()>0){
									String executionId=listMap.get(0).get("executionId").toString();
									if(StringUtil.isNotEmpty(executionId)){
										//删除待办任务和执行实例
										String delSql1 =" delete from act_ru_task where id_=?";
										systemService.executeSql(delSql1, new Object[]{value.toString()});
										
										String delSql2 =" delete from act_ru_execution where id_=?";
										systemService.executeSql(delSql2, new Object[]{executionId});
										
										String delSql3 =" delete from act_ru_variable where execution_id_=?";
										systemService.executeSql(delSql3, new Object[]{executionId});
									}
								}
							}
						} 
					}
				}
			}
			
		}
	}
	
	/**
	 * 删除会签已经通过的待办任务
	 * @param taskId
	 */
	private void deleteSignHisTask(String taskId){
		//删除待办任务
		String delSql1=" delete from act_ru_task where  ID_ =? ";
		systemService.executeSql(delSql1, new Object[]{taskId});
		
		//删除历史待办任务
		String delSql2=" delete from act_hi_taskinst where id_=? ";
		systemService.executeSql(delSql2, new Object[]{taskId});
		
	}
	
	/**
	 * 审批驳回
	 * @param workflowBean
	 */
	@Override
	public void processReject(WorkflowBean workflowBean){
		String taskId = workflowBean.getTaskId();//获取任务id
		String id = workflowBean.getBusId();//业务单据ID
		String outcome = workflowBean.getOutcome();//获取连线的名称
		String message = workflowBean.getComment()==null?outcome:workflowBean.getComment();//批注信息--设置默认批语
        //ProcessEngine engine =  ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("config/activiti.cfg.xml").buildProcessEngine();
        HistoryService historyService = processEngine.getHistoryService();
        RuntimeService runtimeService = processEngine.getRuntimeService();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        TaskService taskService = processEngine.getTaskService();
        try {
            Map<String, Object> variables;
            // 取得当前任务
            HistoricTaskInstance currTask = historyService.createHistoricTaskInstanceQuery().taskId(taskId).singleResult();
            //向批注表中插入审批批语
            if(currTask!=null){
            	
            	String processInstanceId = currTask.getProcessInstanceId();
            	Authentication.setAuthenticatedUserId(ResourceUtil.getSessionUserName().getId());//设置当前审批人
            	taskService.addComment(taskId, processInstanceId, message);//保存审批批语
            	// 取得流程实例
            	ProcessInstance instance = runtimeService.createProcessInstanceQuery().processInstanceId(currTask.getProcessInstanceId()).singleResult();
            	String currentActivityId = instance.getActivityId();  //当前活动节点id
            	//流程已经结束
            	if (instance == null) {
            		throw new Exception("流程已经结束");
            	}
            	variables = instance.getProcessVariables();
            	// 取得流程定义
            	ProcessDefinitionEntity definition = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService).getDeployedProcessDefinition(currTask.getProcessDefinitionId());
            	if (definition == null) {
            		throw new Exception("流程定义未找到");
            	}
            	// 取得上一步活动   sid-C90CCFFD-EC8B-4053-BA9F-5792772B7F7A
            	ActivityImpl currActivity = ((ProcessDefinitionImpl) definition) .findActivity(currTask.getTaskDefinitionKey());
            	String preActivityId = currActivity.getId(); //上一步活动节点id
//            	if(currentActivityId.equals(preActivityId)){ 
            		//修改单据状态为驳回状态
            		//T entity = (T) taskService.getVariable(taskId, "bill");//对应单据实体
            		String stateName =null; //(String) taskService.getVariable(taskId, "stateName"); //单据状态名称
            		String tableName =null; //(String) taskService.getVariable(taskId, "tableName");
            		String auditRemark=null; //审批意见
            		String auditUser=null; //审批人  a.EXECUTION_ID_=b.EXECUTION_ID_ and
            		String isMerge=null;
                	String batchListUrl=null;
                	String batchListIds =null;
            		String sql = "SELECT a.NAME_ \"name\", TEXT_  \"text\" from ACT_RU_VARIABLE a inner join ACT_RU_TASK b on a.PROC_INST_ID_=b.PROC_INST_ID_ "
            				+ " where b.ID_='"+taskId+"'  ";
            		List<Map<String,Object>> list = systemService.findForJdbc(sql, new Object[]{});
            		if(list!= null && list.size()>0){
            			for (Map<String, Object> map : list) {
            				String name = map.get("name")==null?null:map.get("name").toString();
            				if("stateName".equals(name)){
            					stateName = map.get("text")==null?null:map.get("text").toString();
            				}else if("tableName".equals(name)){
            					tableName = map.get("text")==null?null:map.get("text").toString();
            				}else if("auditRemark".equals(name)){
            					auditRemark = map.get("text")==null?null:map.get("text").toString();
            				}else if("auditUser".equals(name)){
            					auditUser = map.get("text")==null?null:map.get("text").toString();
            				}else if("isMerge".equals(name)){
            					isMerge = map.get("text")==null?null:map.get("text").toString();
            				}else if("batchListUrl".equals(name)){
            					batchListUrl = map.get("text")==null?null:map.get("text").toString();
            				}else if("batchListIds".equals(name)){
            					batchListIds = map.get("text")==null?null:map.get("text").toString();
            				}
            			}
            		}
            		//修改单据状态为退回
            		if("Y".equals(isMerge) && StringUtil.isNotEmpty(batchListIds)){ //多条单据同一待办任务审批
            			String[] idsArr =batchListIds.split(",");
    					for (int i = 0; i < idsArr.length; i++) { //批量修改单据状态为退回
    						String sql1 = " update "+tableName+" set "+stateName+"='"+BILL_STATE.REJECT+"' ";
                			if(StringUtil.isNotEmpty(auditRemark)&& StringUtil.isNotEmpty(auditUser)){
                				sql1+= ","+auditRemark+"='"+message+"',"+auditUser+"='"+ResourceUtil.getSessionUserName().getUserName()+"' ";
                			}
                			sql1+= "where id ='"+idsArr[i]+"'";
                			systemService.updateBySqlString(sql1);
    					}
            		}else{
            			String sql1 = " update "+tableName+" set "+stateName+"='"+BILL_STATE.REJECT+"' ";
            			if(StringUtil.isNotEmpty(auditRemark)&& StringUtil.isNotEmpty(auditUser)){
            				sql1+= ","+auditRemark+"='"+message+"',"+auditUser+"='"+ResourceUtil.getSessionUserName().getUserName()+"' ";
            			}
            			sql1+= "where id ='"+id+"'";
            			systemService.updateBySqlString(sql1);
            		}
            		
            		//上一步为开始节点，删除任务，重新提交
            		deleteProcessInstance(taskId, "退回");
            		return;
//            	}
//            	List<PvmTransition> nextTransitionList = currActivity.getIncomingTransitions();
//            	// 清除当前活动的出口
//            	List<PvmTransition> oriPvmTransitionList = new ArrayList<PvmTransition>();
//            	List<PvmTransition> pvmTransitionList = currActivity.getOutgoingTransitions();
//            	for (PvmTransition pvmTransition : pvmTransitionList) {
//            		oriPvmTransitionList.add(pvmTransition);
//            	}
//            	pvmTransitionList.clear();
//            	
//            	// 建立新出口
//            	List<TransitionImpl> newTransitions = new ArrayList<TransitionImpl>();
//            	for (PvmTransition nextTransition : nextTransitionList) {
//            		PvmActivity nextActivity = nextTransition.getSource();
//            		ActivityImpl nextActivityImpl = ((ProcessDefinitionImpl) definition)
//            				.findActivity(nextActivity.getId());
//            		TransitionImpl newTransition = currActivity
//            				.createOutgoingTransition();
//            		newTransition.setDestination(nextActivityImpl);
//            		newTransitions.add(newTransition);
//            	}
//            	// 完成任务
//            	List<Task> tasks = taskService.createTaskQuery()
//            			.processInstanceId(instance.getId())
//            			.taskDefinitionKey(currTask.getTaskDefinitionKey()).list();
//            	for (Task task : tasks) {
//            		taskService.complete(task.getId(), variables);
//            		//deleteProcessInstance(task.getId(), "退回");
//            		//historyService.deleteHistoricTaskInstance(task.getId());
//            	}
//            	//恢复方向
//            	for (TransitionImpl transitionImpl : newTransitions) {
//            		currActivity.getOutgoingTransitions().remove(transitionImpl);
//            	}
//            	for (PvmTransition pvmTransition : oriPvmTransitionList) {
//            		pvmTransitionList.add(pvmTransition);
//            	}
            }
        } catch (Exception e) {
           //失败
        }
    }
	
	/**
	 * 更新单据状态
	 * @param className 单据实体类全名称
	 * @param busiStatus 单据状态
	 * @param busiId 单据ID
	 */
	public void updateBusiStatus(String className,String stateName,String busiStatus,String busiId,Map map){
		if(StringUtil.isNotEmpty(busiStatus)){
			String hql = "update "+className+" set "+stateName+"='"+busiStatus+"' where id ='"+busiId+"'";
			systemService.executeHql(hql);
		}
	}
	/**
	 * 更新单据的审批数据(审批人、审批意见)
	 * @param map
	 */
	public void updateBusiAudit(Map map){
		String className =map.get("className")==null?null:map.get("className").toString(); //单据实体类全名称
		String busiId =map.get("busiId")==null?null:map.get("busiId").toString(); //单据ID
		String message = map.get("message")==null?"无":map.get("message").toString(); //审批意见
		String auditRemark = map.get("auditRemark")==null?null:map.get("auditRemark").toString(); //单据审批意见的字段
		String auditUser = map.get("auditUser")==null?null:map.get("auditUser").toString(); //单据审批人的字段
		TSUser currentUser = ResourceUtil.getSessionUserName(); //获取当前登录用户
		if(StringUtil.isNotEmpty(auditRemark)&& StringUtil.isNotEmpty(auditUser)){
			String hql = "update "+className+" set "+auditRemark+"='"+message+"', "+auditUser+"='"+currentUser.getUserName()+"' where id ='"+busiId+"'";
			systemService.executeHql(hql);
		}
	}   
	/***
	 * 认领任务
	 */
	@Override
	public void changeAssign(String taskId, String userid) {
		taskService.setAssignee(taskId, userid);
	}
	/***
	 * 认领任务
	 */
	@Override
	public void claimAssign(String taskId, String userid) {
		taskService.claim(taskId, userid);
	}
	/**
	 * 在流程定义处启动流程，业务流程保存后的完成任务
	 * 1、启动流程
	 * 2、赋值变量
	 * 3、完成第一个任务
	 */
	@Override
	public <T> void startAndCompleteTask(T entity, String busiId) throws Exception {
		String businessKey = entity.getClass().getCanonicalName()+"."+busiId;
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userid", ResourceUtil.getSessionUserName().getId());
		//1、启动流程
		ProcessInstance processInstance = runtimeService
				.startProcessInstanceByKey(entity.getClass().getSimpleName(), businessKey, map);
		
		//2、为第一个任务赋值单据变量
		String processInstanceId = processInstance.getProcessInstanceId();
		Task task = taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
		taskService.setVariable(task.getId(), "bill", entity);
		//3、完成任务
		taskService.complete(task.getId());//完成任务
//		String stateName = (String) taskService.getVariable(task.getId(), "stateName"); //单据状态名称
//    	String tableName = (String) taskService.getVariable(task.getId(), "tableName");	
		//4、判断流程是否结束		
		ProcessInstance  processInstance2 = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
		if(processInstance2==null){
			//updateBusiStatus(entity.getClass().getCanonicalName(),stateName, BILL_STATE.TO_BE_CHECK, busiId);
		}	
		
	}
	/**
	 * 根据任务ID删除流程实例
	 */
	@Override
	public void deleteProcessInstance(String taskId,String deleteReason) {
		try {
//			Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
//			String processInstanceId = task.getProcessInstanceId();
//			runtimeService.deleteProcessInstance(processInstanceId, deleteReason);
			String sql =" select proc_inst_id_ \"procInstId\",execution_id_ \"executionId\" from act_ru_task where ID_ =?";
			List<Map<String,Object>> instIdList =systemService.findForJdbc(sql, new Object[]{taskId});
			String instIds =null;
			String executionIds=null;
			if(instIdList!=null && instIdList.size()>0){
				for (Map<String, Object> map : instIdList) {
					String procInstId=map.get("procInstId").toString();
					String executionId=map.get("executionId").toString();
					instIds=instIds==null?"'"+procInstId+"'":instIds+",'"+procInstId+"'";
					executionIds=executionIds==null?"'"+executionId+"'":executionIds+",'"+executionId+"'";
				}
			}
			
			if(StringUtil.isNotEmpty(executionIds)){
				//删除运行时流程人员数据
				String delSql3=" delete from act_ru_identitylink where proc_inst_id_ in ("+instIds+") ";
				//systemService.executeSql(delSql3, new Object[]{});
				
				//删除运行时流程变量数据
				String delSql = " delete from act_ru_variable where execution_id_ in (" +executionIds+")";
				systemService.executeSql(delSql, new Object[]{});
				//删除待办任务
//				String delSql2=" delete from act_ru_task where proc_inst_id_ in ("+instIds+")";
				String delSql2=" delete from act_ru_task where id_=?";
				systemService.executeSql(delSql2, new Object[]{taskId});
				
				//删除运行时流程执行实例	
				String delSql1 = " delete from act_ru_execution where id_ in ("+executionIds+") ";
				systemService.executeSql(delSql1, new Object[]{});
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	/**
	 * 存储动态单据数据
	 * 1、启动流程
	 * 2、保存动态表单数据
	 * 3、完成任务
	 */
	@Override
	public void saveDymaticFormData(Map<String, String> formMap,String processDefinitionId)throws Exception  {
		//启动流程
		Task currentTask = startDymaticFormFlow(processDefinitionId);
		String taskId = currentTask.getId();
		//表单数据赋值task
		List<FormAttr> result = workFlowQueryService.getDymaticFormXml_taskId(taskId);
		for (FormAttr formAttr : result) {
			setFormValueToTask(taskId, formAttr, formMap);
		}
		//完成任务
		taskService.complete(taskId);
	}
	//将表单数据塞入Task
	public void setFormValueToTask(String taskId,FormAttr formAttr,Map<String, String> formMap) throws ParseException{
		String tempColumn = formAttr.getId();
		String type = formAttr.getType();
		String tempValue = formMap.get(tempColumn);
		if(StringUtils.isEmpty(tempValue))
			return;			
		if("string".equals(type)||"enum".equals(type)){
			taskService.setVariableLocal(taskId, tempColumn, tempValue);
			return;
		}else if("double".equals(type)){
			taskService.setVariableLocal(taskId, tempColumn, Double.parseDouble(tempValue));
			return;
		}else if("long".equals(type)){
			taskService.setVariableLocal(taskId, tempColumn, Long.parseLong(tempValue));
			return;
		}else if("boolean".equals(type)){
			taskService.setVariableLocal(taskId, tempColumn, new Boolean(tempValue==null?"false":tempValue));
			return;
		}else if("date".equals(type)){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			taskService.setVariableLocal(taskId, tempColumn, sdf.parse(tempValue));
		}
	}
	/**
	 * 根据流程定义ID 启动流程
	 * @param processDefinitaion
	 * @return
	 */
	public Task startDymaticFormFlow(String processDefinitionId){
		//获取流程key		
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).singleResult();
		String processDefinitionKey = processDefinition.getKey();
		Map<String,Object> variables =  new HashMap<String,Object>();
		variables.put("userid", ResourceUtil.getSessionUserName().getId());
		//启动流程
		ProcessInstance processInstance = runtimeService
				.startProcessInstanceByKey(processDefinitionKey, variables);
		//获取第一个任务
		String processInstanceId = processInstance.getProcessInstanceId();
		Task task = taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
		return task;
	}
	
	/**
	 * 从用户任务节点中获取动态单据信息
	 * @param currentNode
	 * @return
	 */
	public List<FormAttr> getDymaticFormFromNode(Node userTaskNode ){
		Node extensionElements = userTaskNode.getSubNodeByName("extensionElements");  
        if(extensionElements == null)
            return null;  
        //返回自定义对象集合
        List<FormAttr> formMap = new ArrayList<FormAttr>();
        List<Node> formPropertyList = extensionElements.getChildrenList();  
        for (Node formProperty : formPropertyList) {  
            FormAttr fa = new FormAttr(formProperty);  
            formMap.add(fa);  
        }  
		return formMap;
	}
	@Override
	public String addAutoForm(String formName,Map<String, Map<String, Object>> dataMap,
			Map<String, List<Map<String, Object>>> oldDataMap,
			String processDefinitionId) throws BusinessException {
//		//保存自定义菜单
//		String id = this.autoFormService.doUpdateTable(formName,dataMap,oldDataMap);
//		//启动工作流程
//		startTaskWithBusiId(processDefinitionId, id);
//		return id;
		return null;
	}
	
	public void startTaskWithBusiId(String processDefinitionId,String id){
		//获取流程key		
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).singleResult();
		String processDefinitionKey = processDefinition.getKey();
		//启动流程
		ProcessInstance processInstance = runtimeService
				.startProcessInstanceByKey(processDefinitionKey, id);
	}

	 /**
     * 创建附件
     * @param attachmentType - 类型
     * @param taskId
     * @param processInstanceId
     * @param attachmentName
     * @param attachmentDescription
     * @param String url ，文件上传成功后返回的可下载的url地址
     */
    public Attachment createAttachment(String userId, String attachmentType,String taskId,String processInstanceId,String attachmentName,String attachmentDescription,String url){
        try{
//            identityService.setAuthenticatedUserId(userId);
            return taskService.createAttachment(attachmentType, taskId, processInstanceId, attachmentName, attachmentDescription, url);
        }finally{
//            identityService.setAuthenticatedUserId(null);
        }
    }

    /**
     * 单据撤销提交
     */
	@Override
	public String revokeWorkflowProcess(Map map) {
		String returnMsg=null;
		//业务主键id
		String busId=map.get("busId")==null?null:map.get("busId").toString();
		//流程定义id
		String workflowId=map.get("workflowId")==null?null:map.get("workflowId").toString();
		if(StringUtil.isNotEmpty(busId)&& StringUtil.isNotEmpty(workflowId)){
			//查询最新审核记录
			String sql =" select a.name_ \"name\",max(a.proc_inst_id_) \"procInstId\",a.assignee_ \"assign\" "
					+ "  from act_hi_taskinst a "
					+ "  inner join act_hi_procinst c on a.proc_inst_id_ = c.proc_inst_id_ and a.proc_def_id_ = c.proc_def_id_  "
					+ "  where c.business_key_ like '%"+busId+"' "
					+ "  and c.proc_def_id_ = '"+workflowId+"' "
					+ "  and a.end_time_ is not null and a.assignee_ is not null "
					+ "  group by a.name_,a.assignee_ ";
			List<Map<String,Object>> list= systemService.findForJdbc(sql, new Object[]{});
			if(list != null && list.size()>0){ //已有审核
				returnMsg= "该单据已审核，不能撤销";
			}else{ //尚未审核--删除对应的待办任务
				String esql =" select id_ \"id\" from act_ru_execution where proc_def_id_='"+workflowId+"' "
						+ "  and business_key_ like '%"+busId+"' ";
				List<Map<String,Object>> execList =systemService.findForJdbc(esql, null);
				String parentExecId =null;
				if(execList!= null && execList.size()>0){
					parentExecId=execList.get(0).get("id")==null?null:execList.get(0).get("id").toString();
				}
				if(StringUtil.isNotEmpty(parentExecId)){
					
					//删除待办任务
					String delSql1=" delete from act_ru_task where execution_id_ in "
							+ " (select id_ from act_ru_execution where proc_def_id_='"+workflowId+"' "
							+ "  and business_key_ like '%"+busId+"' "
							+ "  union all "
							+ "  select id_ from act_ru_execution where proc_def_id_='"+workflowId+"' "
							+ " and parent_id_ ='"+parentExecId+"')";
					systemService.executeSql(delSql1);
					
					//删除运行时变量
					String delSql3=" delete from act_ru_variable where  execution_id_ in "
							+ " (select id_ from act_ru_execution where proc_def_id_='"+workflowId+"' "
							+ "  and business_key_ like '%"+busId+"' "
							+ "  union all "
							+ "  select id_ from act_ru_execution where proc_def_id_='"+workflowId+"' "
							+ " and parent_id_ ='"+parentExecId+"')";
					systemService.executeSql(delSql3);
					
					//删除运行时流程人员数据
					String delSql4 =" delete from act_ru_identitylink where proc_inst_id_ in "
							+ " (select proc_inst_id_ from act_ru_execution where proc_def_id_='"+workflowId+"' "
							+ "  and business_key_ like '%"+busId+"' "
							+ "  union all "
							+ "  select id_ from act_ru_execution where proc_def_id_='"+workflowId+"' "
							+ " and parent_id_ ='"+parentExecId+"')";
					systemService.executeSql(delSql4);
					
					//删除运行时流程执行实例	
					String delSql2 =" delete from act_ru_execution where proc_def_id_='"+workflowId+"' "
								+ " and parent_id_ ='"+parentExecId+"' ";
//							+ " and business_key_ like '%"+busId+"' ";
					systemService.executeSql(delSql2);
					
					String parentSql = " delete from act_ru_execution where id_='"+parentExecId+"'";
					systemService.executeSql(parentSql);
					//删除状态记录
					String parentSql2 = " DELETE  FROM act_hi_procinst  WHERE business_key_ LIKE  '%"+busId+"'";
					systemService.executeSql(parentSql2);
				}
			}
			
		}
		return returnMsg;
	}

	/**
	 * 逐级退回
	 */
	@Override
	public void roleBackProcess(WorkflowBean workflowBean) {
		String taskId = workflowBean.getTaskId();//获取任务id
		String id = workflowBean.getBusId();//业务单据ID
		String outcome = workflowBean.getOutcome();//获取连线的名称
		String message = workflowBean.getComment()==null?outcome:workflowBean.getComment();//批注信息--设置默认批语
        //ProcessEngine engine =  ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("config/activiti.cfg.xml").buildProcessEngine();
        HistoryService historyService = processEngine.getHistoryService();
        RuntimeService runtimeService = processEngine.getRuntimeService();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        TaskService taskService = processEngine.getTaskService();
        try {
            Map<String, Object> variables;
            // 取得当前任务
            HistoricTaskInstance currTask = historyService.createHistoricTaskInstanceQuery().taskId(taskId).singleResult();
            
            //向批注表中插入审批批语
            if(currTask!=null){
            	String processInstanceId = currTask.getProcessInstanceId();
            	//查询此条单据的开始活动节点
            	String startActId =null;
            	String startSql=" select start_act_id_ \"startActId\" from act_hi_procinst where PROC_INST_ID_ =? ";
            	List<Map<String,Object>> startList =systemService.findForJdbc(startSql, new Object[]{processInstanceId});
            	if(startList!= null && startList.size()>0){
            		startActId=startList.get(0).get("startActId")==null?null:startList.get(0).get("startActId").toString();
            	}
            	Authentication.setAuthenticatedUserId(ResourceUtil.getSessionUserName().getId());//设置当前审批人
            	taskService.addComment(taskId, processInstanceId, message);//保存审批批语
            	// 取得流程实例
            	ProcessInstance instance = runtimeService.createProcessInstanceQuery().processInstanceId(currTask.getProcessInstanceId()).singleResult();
            	String currentActivityId = instance.getActivityId();  //当前活动节点id
            	//流程已经结束
            	if (instance == null) {
            		throw new Exception("流程已经结束");
            	}
            	variables = instance.getProcessVariables();
            	// 取得流程定义
            	ProcessDefinitionEntity definition = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService).getDeployedProcessDefinition(currTask.getProcessDefinitionId());
            	if (definition == null) {
            		throw new Exception("流程定义未找到");
            	}
            	// 取得上一步活动   
            	ActivityImpl currActivity = ((ProcessDefinitionImpl) definition) .findActivity(currTask.getTaskDefinitionKey());
            	String preActivityId = currActivity.getId(); //上一步活动节点id
            	
            	List<PvmTransition> nextTransitionList = currActivity.getIncomingTransitions();
            	boolean isStart=false;
            	//判断上一节点是否是开始节点
            	for (PvmTransition nextTransition : nextTransitionList) {
            		PvmActivity nextActivity = nextTransition.getSource();
            		String nextActId =nextActivity.getId();
            		if(nextActId.equals(startActId)){
            			isStart=true;
            		}
            	}
            	//回退的不是开始节点
            	if(!isStart){
	            	// 清除当前活动的出口
	            	List<PvmTransition> oriPvmTransitionList = new ArrayList<PvmTransition>();
	            	List<PvmTransition> pvmTransitionList = currActivity.getOutgoingTransitions();
	            	for (PvmTransition pvmTransition : pvmTransitionList) {
	            		oriPvmTransitionList.add(pvmTransition);
	            	}
	            	pvmTransitionList.clear();
	            	
	            	// 建立新出口
	            	List<TransitionImpl> newTransitions = new ArrayList<TransitionImpl>();
	            	for (PvmTransition nextTransition : nextTransitionList) {
	            		PvmActivity nextActivity = nextTransition.getSource();
	            		String nextActId =nextActivity.getId();
	            		if(!nextActId.equals(startActId)){
	            			ActivityImpl nextActivityImpl = ((ProcessDefinitionImpl) definition).findActivity(nextActivity.getId());
	            			TransitionImpl newTransition = currActivity.createOutgoingTransition();
	            			newTransition.setDestination(nextActivityImpl);
	            			newTransitions.add(newTransition);
	            		}else{
	            			isStart=true;
	            		}
	            	}
	            	// 完成任务
	            	List<Task> tasks = taskService.createTaskQuery().processInstanceId(instance.getId())
	            			.taskDefinitionKey(currTask.getTaskDefinitionKey()).list();
            	
            		for (Task task : tasks) {
            			taskService.complete(task.getId(), variables);
//            			deleteProcessInstance(task.getId(), "退回");
//            			historyService.deleteHistoricTaskInstance(task.getId());
//            			String updateSql =" update act_hi_taskinst set end_time_=null,delete_reason=null where id_=?";
//            			systemService.executeSql(updateSql, new Object[]{task.getId()});
            		}
            		//恢复方向
            		for (TransitionImpl transitionImpl : newTransitions) {
            			currActivity.getOutgoingTransitions().remove(transitionImpl);
            		}
            		for (PvmTransition pvmTransition : oriPvmTransitionList) {
            			pvmTransitionList.add(pvmTransition);
            		}
            		for (Task task : tasks) {
            			String updateSql =" update act_hi_taskinst set end_time_=null,delete_reason=null where id_=?";
            			systemService.executeSql(updateSql, new Object[]{task.getId()});
            		}
            	}else{ 
            		//修改单据状态为驳回状态
            		String stateName =null; //(String) taskService.getVariable(taskId, "stateName"); //单据状态名称
            		String tableName =null; //(String) taskService.getVariable(taskId, "tableName");
            		String auditRemark=null; //审批意见
            		String auditUser=null; //审批人  a.EXECUTION_ID_=b.EXECUTION_ID_ and 
            		String sql = "SELECT a.NAME_ \"name\", TEXT_  \"text\" from ACT_RU_VARIABLE a inner join ACT_RU_TASK b on a.PROC_INST_ID_=b.PROC_INST_ID_ "
            				+ " where b.ID_='"+taskId+"'  ";
            		List<Map<String,Object>> list = systemService.findForJdbc(sql, new Object[]{});
            		if(list!= null && list.size()>0){
            			for (Map<String, Object> map : list) {
            				String name = map.get("name")==null?null:map.get("name").toString();
            				if("stateName".equals(name)){
            					stateName = map.get("text")==null?null:map.get("text").toString();
            				}else if("tableName".equals(name)){
            					tableName = map.get("text")==null?null:map.get("text").toString();
            				}else if("auditRemark".equals(name)){
            					auditRemark = map.get("text")==null?null:map.get("text").toString();
            				}else if("auditUser".equals(name)){
            					auditUser = map.get("text")==null?null:map.get("text").toString();
            				}
            			}
            		}
            		//修改单据状态为退回
            		String sql1 = " update "+tableName+" set "+stateName+"='"+BILL_STATE.REJECT+"' ";
            		if(StringUtil.isNotEmpty(auditRemark)&& StringUtil.isNotEmpty(auditUser)){
            			sql1+= ","+auditRemark+"='"+message+"',"+auditUser+"='"+ResourceUtil.getSessionUserName().getUserName()+"' ";
            		}
            		sql1+= "where id ='"+id+"'";
            		systemService.updateBySqlString(sql1);
            	}
            	List<Task> tasks = taskService.createTaskQuery().processInstanceId(instance.getId())
            			.taskDefinitionKey(currTask.getTaskDefinitionKey()).list();
            	for (Task task : tasks) {
        			deleteProcessInstance(task.getId(), "退回");
//        			historyService.deleteHistoricTaskInstance(task.getId());
        		}
            	
            }
        } catch (Exception e) {
           //失败
        }
    
		
	}

	/**
	 * 查询流程审批记录
	 */
	@Override
	public List<Map<String, Object>> queryProcessRecord(Map params) {
		//业务主键id
		String busId = params.get("busId")==null?null:params.get("busId").toString();
		//工作流id
		String workflowId = params.get("workflowId")==null?null:params.get("workflowId").toString();
		//是否只查询审批完成的节点
		String isComplete =params.get("isComplete")==null?null:params.get("isComplete").toString();
		
		if(StringUtil.isNotEmpty(busId)&&StringUtil.isNotEmpty(workflowId)){
			String sql =" select  a.name_ \"name\",a.proc_inst_id_ \"procInstId\",a.assignee_ \"assign\",d.realname \"realName\",a.start_time_ \"startTime\","
					+ " b.time_ \"time\",to_char(b.message_) \"message\",a.end_time_ \"endTime\",a.delete_reason_ \"deleteReason\","
					+ " case when  a.start_time_ is not null and a.end_time_ is not null and a.delete_reason_ is not null then '通过'  "
					+ " when to_char(b.message_) is null  and a.start_time_ is not null and a.end_time_ is null then '审核中' "
					+ " else '退回' end \"auditStatus\" "
					+ " from act_hi_taskinst a left join act_hi_comment b on a.proc_inst_id_=b.proc_inst_id_ and a.id_=b.task_id_ "
					+ " inner join act_hi_procinst c on a.proc_inst_id_=c.proc_inst_id_ and a.proc_def_id_=c.proc_def_id_ "
					+ " left join t_s_base_user d on d.username=a.assignee_ "
					+ " where c.business_key_ like '%"+busId+"' "
					+ " and c.proc_def_id_='"+workflowId+"' "
					+ " and a.assignee_ is not null";
			if(StringUtil.isNotEmpty(isComplete)&&"true".equals(isComplete)){ //查询审批通过和退回的记录
				sql+=" and ( (a.start_time_ is not null and a.end_time_ is not null and a.delete_reason_ is not null) "
						+ "or (a.start_time_ is not null and  a.end_time_ is null and to_char(b.message_) is not null) ) ";
			}
			sql+= " order by a.start_time_,b.time_,a.end_time_,a.assignee_ ";
			List<Map<String,Object>> list = systemService.findForJdbc(sql, new Object[]{});
			return list;
		}
		return null;
	}
	
}
