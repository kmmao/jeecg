package com.buss.activiti.controller;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricTaskInstanceQuery;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.task.Task;
import org.activiti.image.ProcessDiagramGenerator;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.FtpUtil;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSBaseUser;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.buss.activiti.entity.Act;
import com.buss.activiti.entity.FormAttr;
import com.buss.activiti.entity.Node;
import com.buss.activiti.entity.TaskCommentAndFile;
import com.buss.activiti.entity.WorkflowBean;
import com.buss.activiti.service.IWorkFlowQueryService;
import com.buss.activiti.service.IWorkFlowService;

import cn.com.linkwide.common.fileUpload.entity.FileDictEntity;

/**   
 * @Title: Controller  
 * @Description: actController
 * @author onlineGenerator
 * @date 2017-07-17 15:20:14
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/actTaskController")
public class ActTaskController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ActTaskController.class);
	
	@Autowired
	private RepositoryService repositoryService;
	
	@Autowired
	private HistoryService historyService;
	
	@Autowired 
	private IWorkFlowQueryService workFlowQueryService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private IWorkFlowService workFlowService;
	@Autowired
	private TaskService taskService;
	@Autowired
    ProcessEngineConfiguration processEngineConfiguration;
    @Autowired
    ProcessEngineFactoryBean processEngine;
	
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	protected static Map<String, ProcessDefinition> PROCESS_DEFINITION_CACHE = new HashMap<String, ProcessDefinition>();
	/**
	 * 流程定义列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {

		return new ModelAndView("com/buss/activiti/actTaskTodoList");
	}
	
	/**
	 * 待办任务 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "needDealInit")
	public ModelAndView needDealInit(HttpServletRequest request) {

		return new ModelAndView("com/buss/activiti/actTaskTodoNeedList");
	}
	
	/**
	 * 待办任务列表
	 */

	@RequestMapping(params = "datagridNeed")
	public void datagridNeed(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        // 已经签收的任务
		List<Act> result = new ArrayList<Act>();
		TSUser currentUser = ResourceUtil.getSessionUserName();
		List<Task> todoList = workFlowQueryService.queryTaskByUserId(currentUser.getUserName());
        for (Task task : todoList) {
            result.add(taskToAct(task, "todo"));
        }
        // 等待签收的任务
        List<Task> toClaimList = workFlowQueryService.queryGroupTaskByBuSIUserId(currentUser);
        for (Task task : toClaimList) {
        	result.add(taskToAct(task, "claim"));
        }
        //塞入返回值
		dataGrid.setTotal(result.size());
		dataGrid.setResults(result);
		TagUtil.datagrid(response, dataGrid);
	}
	//对象转换
	public Act taskToAct(Task task,String status){
		Act act = new Act();
    	act.setTask(task);
		act.setVars(task.getProcessVariables());
        String processDefinitionId = task.getProcessDefinitionId();
        ProcessDefinition processDefinition = getProcessDefinition(processDefinitionId);
        act.setProcDef(processDefinition);
        act.setStatus(status);
        return act;
	}
	
	/**
	 * 已办任务 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "haveDealInit")
	public ModelAndView haveDealInit(HttpServletRequest request) {

		return new ModelAndView("com/buss/activiti/actTaskTodoHaveList");
	}
	/**
	 * 已办任务列表
	 */

	@RequestMapping(params = "datagridHave")
	public void datagridHave(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
        // 已经处理的任务
		List<Act> result = new ArrayList<Act>();
		String userId = ResourceUtil.getSessionUserName().getUserName();
		HistoricTaskInstanceQuery histTaskQuery = historyService.createHistoricTaskInstanceQuery().taskAssignee(userId).finished()
				.includeProcessVariables().orderByHistoricTaskInstanceEndTime().desc();
		// 设置查询条件
		// 查询列表
		List<HistoricTaskInstance> histList = histTaskQuery.listPage(dataGrid.getFirstResult(), dataGrid.getRows());
		for (HistoricTaskInstance histTask : histList) {
			Act e = new Act();
			e.setHistTask(histTask);
			e.setVars(histTask.getProcessVariables());
            String processDefinitionId = histTask.getProcessDefinitionId();
            ProcessDefinition processDefinition = getProcessDefinition(processDefinitionId);
            
            e.setProcDef(processDefinition);

			e.setStatus("finish");
			result.add(e);
		}
		
		dataGrid.setTotal(result.size());
		dataGrid.setResults(result);

		TagUtil.datagrid(response, dataGrid);
	}
	
	
	/**
	 * 新建任务 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "newTaskInit")
	public ModelAndView newTaskInit(HttpServletRequest request) {
		return new ModelAndView("com/buss/activiti/actTaskTodoNewList");
	}
	
	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "datagridNew")
	public void datagridNew(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery().latestVersion().active().orderByProcessDefinitionKey().asc();
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
	
    private ProcessDefinition getProcessDefinition(String processDefinitionId) {
        ProcessDefinition processDefinition = PROCESS_DEFINITION_CACHE.get(processDefinitionId);
        if (processDefinition == null) {
            processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).singleResult();
            PROCESS_DEFINITION_CACHE.put(processDefinitionId, processDefinition);
        }
        return processDefinition;
    }
    
    /**
     *  签收任务
     */
    @RequestMapping(params = "doClaimTask")
	@ResponseBody
    public AjaxJson doClaimTask(String taskId,HttpServletRequest req) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "签收成功";
		try{
	        String userId = ResourceUtil.getSessionUserName().getUserName();
	        workFlowService.claimAssign(taskId, userId);
		}catch(Exception e){
			e.printStackTrace();
			message = "签收失败";
		}
		j.setMsg(message);
		return j;
    }
    /**
	 * 修改办理人调整界面
	 * @return
	 */
	@RequestMapping(params = "goChangeAssign")
	public ModelAndView goChangeAssign(String taskId, HttpServletRequest req) {
		//回传任务ID
		req.setAttribute("taskId", taskId);
		return new ModelAndView("com/buss/activiti/handler/changeAssign");
	}
    /**
	 * 指派任务
	 * @param taskId
	 * @param userid
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "doClaimAssign")
	@ResponseBody
	public AjaxJson doClaimAssign(String taskId, String userid,HttpServletRequest req){
		String message = "指派成功";
		AjaxJson j = new AjaxJson();
		try {
			workFlowService.claimAssign(taskId, userid);
		} catch (Exception e) {
			message = "指派失败："+e.getMessage();
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;	
	}
	/**
	 * 处理任务跳转界面
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(params = "goHandTask")
	public ModelAndView goHandTask(String taskId, HttpServletRequest req) throws Exception {
		//1、判断 待处理任务的单据类型：业务类型、动态单据
		String businessKey = workFlowQueryService.getBusiIdByTaskId(taskId);
		StringBuffer modelAndView = new StringBuffer("com/buss/activiti/handler/");
		
		//查询历史审批意见
		String sql =" select c.realname \"auditUser\",a.message_ \"comment\" from act_hi_comment a inner join act_ru_task b on a.proc_inst_id_=b.proc_inst_id_  "
				+ "left join t_s_base_user c on a.user_id_=c.id "
				+ "where b.id_='"+taskId+"' and a.type_='comment' order by a.time_ ";
		List<Map<String,Object>> commentList = systemService.findForJdbc(sql, new Object[]{});
//		List<TaskCommentAndFile> commentList =  workFlowQueryService.getWorkFlowComment(taskId);
//		setUrlValue(commentList);
		req.setAttribute("commentList", commentList);
		
		if(!StringUtil.isEmpty(businessKey)){
			if(businessKey.contains(".")){//业务单据				
				returnViewWhenBusiness(taskId, businessKey, req);
				String isFile =req.getParameter("isFile");
				String isMerge=null;
//				if(StringUtil.isEmpty(isFile)){
					String sql1 = "SELECT a.NAME_ \"name\",TEXT_  \"text\" from ACT_RU_VARIABLE a inner join ACT_RU_TASK b on a.PROC_INST_ID_=b.PROC_INST_ID_ "
							+ " where b.ID_='"+taskId+"' "
							+ "and a.NAME_ in('isFile','isMerge') ";
					List<Map<String,Object>> list = systemService.findForJdbc(sql1, new Object[]{});
					if(list!= null && list.size()>0){
						for (Map<String, Object> map2 : list) {
							String name = map2.get("name")==null?null:map2.get("name").toString();
							if("isFile".equals(name)){
								isFile = list.get(0).get("text")==null?null:list.get(0).get("text").toString();
							}else if("isMerge".equals(name)){
								isMerge = map2.get("text")==null?null:map2.get("text").toString();
							}
						}
					}
//				}
				
				int index = businessKey.lastIndexOf(".");
				//业务单据主键id
				String busiId = businessKey.substring(index+1, businessKey.length());
				if(StringUtil.isNotEmpty(busiId)){
					//是否展现附件
					if("true".equals(isFile)){
						//获取当前登录用户
						TSBaseUser currentUser = ResourceUtil.getSessionUserName();
						String auditUser = currentUser.getUserName();
						req.setAttribute("auditUser", auditUser);
						modelAndView.append("busiDocApprove_file");
					}else{
						//多条单据一个待办任务审批
						if("Y".equals(isMerge)){
							modelAndView.append("busiApprove_batch");
						}else{
							modelAndView.append("busiDocApprove_handler");
						}
					}
				}
			}else{
				String url = "autoFormController.do?viewContent&formName=wipeOut&dbKey=&id="
						+businessKey+"&op=view&taskId="+taskId;
				//重定向-业务表单			
				return new ModelAndView("redirect:"+url);
			}
		}else{
			//动态单据
			modelAndView.append("startDymaticForm_handler");
			//获取上一动作处理的参数
			List<FormAttr> list1 = workFlowQueryService.getDymaticFormXml_taskId(taskId);
			req.setAttribute("formAttrlist", list1);
		}
//		modelAndView.append("_handler");
		//回传任务ID
		req.setAttribute("taskId", taskId);
		List<String> comeList = new ArrayList<>();
		comeList.add("退回");
		comeList.add("通过");
		//workFlowQueryService.getComeListByTaskId(taskId);
		req.setAttribute("comeList", comeList);
		return new ModelAndView(modelAndView.toString());
	}
	
	/**
	 * 处理任务跳转界面
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(params = "goHandTaskBatch")
	public ModelAndView goHandTaskBatch(String taskId, HttpServletRequest req) throws Exception {
		String busiIDs = req.getParameter("busiID");
		//回传任务ID
		req.setAttribute("taskId", taskId);
		req.setAttribute("busiID", busiIDs);
		req.setAttribute("batchListIds", busiIDs);
		//批量审批查看单据列表的路径
		String batchListUrl = req.getParameter("batchListUrl");
		req.setAttribute("batchListUrl", batchListUrl);
		//首页待办任务需要查询此处
		String businessKey = workFlowQueryService.getBusiIdByTaskId(taskId);
		if(StringUtil.isNotEmpty(businessKey)){
			returnViewWhenBusiness(taskId, businessKey, req);
		}
		List<String> comeList = new ArrayList<>();
		comeList.add("退回");
		comeList.add("通过");
		req.setAttribute("comeList", comeList);
		return new ModelAndView("com/buss/activiti/handler/busiApprove_batch");
	}
	
	/***
	 * 返回业务单据的任务处理界面
	 * @param taskId ：待处理任务ID
	 * @param businessKey 流程业务键：业务实体类全路径+对象ID；
	 */
	private void returnViewWhenBusiness(String taskId,String businessKey, HttpServletRequest req) throws ClassNotFoundException{
		int index = businessKey.lastIndexOf(".");
		/**1、获取业务实体对象ID*/
		String busiId = businessKey.substring(index+1, businessKey.length());
		String className = businessKey.substring(0,index);
		/**2、获取业务实体对象*/
		Object entity = systemService.getEntity(Class.forName(className), busiId);
		/**3、获取业务实体controller名称，为调用update界面做准备数据*/
		//Object controller_name1 = taskService.getVariable(taskId, "controller_name");
		String controller_name=null;
		String tableName=null;
		String conditionPara =null; //判断流转条件需要的字段名称
		String conditionParaType =null; //判断流转条件需要的字段名称
		String isFile=null; //审批是否需要展示附件
		String fileUrl=null; //展示附件详情的路径
		String isMerge=null; //是否多条单据合一审批
    	String batchListUrl=null;
    	String batchListIds =null;
		String sql = "SELECT a.NAME_ \"name\",TEXT_  \"text\" from ACT_RU_VARIABLE a inner join ACT_RU_TASK b on a.PROC_INST_ID_=b.PROC_INST_ID_ "
				+ " where b.ID_='"+taskId+"' ";
//				+ "and a.NAME_='controller_name' ";
		List<Map<String,Object>> list = systemService.findForJdbc(sql, new Object[]{});
		if(list!= null && list.size()>0){
			for (Map<String, Object> map : list) {
				String name =map.get("name").toString();
				if("controller_name".equals(name)){
					controller_name = map.get("text")==null?null:map.get("text").toString();
				}else if("tableName".equals(name)){
					tableName = map.get("text")==null?null:map.get("text").toString();
				}else if ("conditionPara".equals(name)){
					conditionPara = map.get("text")==null?null:map.get("text").toString();
				}else if ("conditionParaType".equals(name)){
					conditionParaType = map.get("text")==null?null:map.get("text").toString();
				}else if("isFile".equals(name)){
					isFile = map.get("text")==null?null:map.get("text").toString();
				}else if("fileUrl".equals(name)){
					fileUrl = map.get("text")==null?null:map.get("text").toString();
				}else if("isMerge".equals(name)){
					isMerge = map.get("text")==null?null:map.get("text").toString();
				}else if("batchListUrl".equals(name)){
					batchListUrl = map.get("text")==null?null:map.get("text").toString();
				}else if("batchListIds".equals(name)){
					batchListIds = map.get("text")==null?null:map.get("text").toString();
				}
			}
		}
		/**4、返回处理任务界面参数*/
		req.setAttribute("conditionPara",conditionPara);
		req.setAttribute("conditionParaType",conditionParaType);
		req.setAttribute("conditionParaValue",null);
		req.setAttribute("controller_name",controller_name);
		req.setAttribute("busiID",busiId);
		req.setAttribute("tableName", tableName);
		req.setAttribute("isFile",isFile);
		req.setAttribute("fileUrl",fileUrl);
		req.setAttribute("isMerge",isMerge);
		req.setAttribute("batchListUrl",batchListUrl);
		req.setAttribute("batchListIds",batchListIds);
		/**5、此处有一约定；业务单据的update界面，显示实体数据的前缀必须为实体类类名*/
		req.setAttribute(className.substring(className.lastIndexOf(".")+1,className.length()), entity);
	}
	
	/**
	 * 删除流程定义
	 * @return
	 */
	@RequestMapping(params = "doDelProcess")
	@ResponseBody
	public AjaxJson doDelProcess(String deploymentId, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "流程定义删除成功";
		try{
			workFlowService.delete(deploymentId);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "流程定义删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	/**
	 * 启动自定义表单流程
	 * @return
	 */
	@RequestMapping(params = "startFlowFromProcess")
	public ModelAndView startFlowFromProcess(String processDefinitaion, HttpServletRequest req){
		Node userTaskNode = workFlowQueryService.getFirstTaskNode(processDefinitaion);
		String formKey = getFormKeyFromNode(userTaskNode);
		req.setAttribute("processDefinitionId", processDefinitaion);
		if(StringUtil.isEmpty(formKey)){
			//动态表单
			List<FormAttr> result = getDymaticFormFromNode(userTaskNode);
			req.setAttribute("formAttrlist", result);
			return new ModelAndView("com/buss/activiti/handler/startDymaticForm");
		}else if(formKey.indexOf(".do?")>0){
			//重定向-业务表单			
			return new ModelAndView("redirect:"+formKey+"&processDefinitionId="+processDefinitaion);
		}else{
			//重定向-外置表单
			return new ModelAndView(formKey);
		}
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
	/**
	 * 自用户任务信息中获取formKey
	 * @param userTask
	 * @return
	 */
	public String getFormKeyFromNode(Node userTaskNode){
		Map<String,String> attrMap = userTaskNode.getAttrMap();  
        String formKey = attrMap.get("formKey");  
        return formKey;
	}
	/**
	 * 我的任务-完成我的任务
	 * @param workflowBean
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "completeTake")
	@ResponseBody
	public AjaxJson completeTake(WorkflowBean workflowBean,HttpServletRequest req){
		String message = "处理任务成功";
		AjaxJson j = new AjaxJson();
		boolean flag = true;
		try {
			//审批附件
			String filePath = workflowBean.getFilePath();
			if(!StringUtils.isEmpty(filePath)){
				// 文件的硬盘真实路径
				String realPath = req.getSession().getServletContext().getRealPath("/") + "/" + filePath ;
				workflowBean.setRealFilePath(realPath);
			}
			//审批批语
			workflowBean.setComment(EcodeString(workflowBean.getComment()));
			workFlowService.completeTakeByWorkflowBean(workflowBean);
		} catch (Exception e) {
			message = "处理任务失败："+e.getMessage();
			flag = false;
			throw new BusinessException(message);
		}
		j.setSuccess(flag);
		j.setMsg(message);
		return j;	
	}
	/**
	 * 批量审批业务单据
	 * @param workflowBean
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "completeTakeBatch")
	@ResponseBody
	public AjaxJson completeTakeBatch(WorkflowBean workflowBean,HttpServletRequest req){
		String message = "处理任务成功";
		AjaxJson j = new AjaxJson();
		boolean flag = true;
		try {
			//审批批语
			workflowBean.setComment(EcodeString(workflowBean.getComment()));
			String taskIds = workflowBean.getTaskId();//获取任务id
			String ids = workflowBean.getBusId();//业务单据ID
			if(StringUtil.isNotEmpty(taskIds)){
				String[] taskIdArr=taskIds.split(",");
				String[] busIdsArr = ids.split(",");
				for (int i = 0; i < taskIdArr.length; i++) {
					WorkflowBean bean = new WorkflowBean();
					bean.setBusId(busIdsArr[i]);
					bean.setTaskId(taskIdArr[i]);
					bean.setComment(workflowBean.getComment());
					bean.setOutcome(workflowBean.getOutcome());
					workFlowService.completeTakeByWorkflowBean(bean);
				}
			}
		} catch (Exception e) {
			message = "处理任务失败："+e.getMessage();
			flag = false;
			throw new BusinessException(message);
		}
		j.setSuccess(flag);
		j.setMsg(message);
		return j;	
	}
	
	/**
	 * 我的任务 --驳回
	 * @param workflowBean
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "rejectTask")
	@ResponseBody
	public AjaxJson rejectTask(WorkflowBean workflowBean,HttpServletRequest req){
		String message = "处理任务成功";
		AjaxJson j = new AjaxJson();
		boolean flag = true;
		try {
			//审批附件
			String filePath = workflowBean.getFilePath();
			if(!StringUtils.isEmpty(filePath)){
				// 文件的硬盘真实路径
				String realPath = req.getSession().getServletContext().getRealPath("/") + "/" + filePath ;
				workflowBean.setRealFilePath(realPath);
			}
			//审批批语
			workflowBean.setComment(EcodeString(workflowBean.getComment()));
			//审批驳回
			workFlowService.processReject(workflowBean); //退回到发起人
//			workFlowService.roleBackProcess(workflowBean);//逐级退回
		} catch (Exception e) {
			message = "处理任务失败："+e.getMessage();
			flag = false;
			throw new BusinessException(message);
		}
		j.setSuccess(flag);
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量审批驳回
	 * @param workflowBean
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "rejectTaskBatch")
	@ResponseBody
	public AjaxJson rejectTaskBatch(WorkflowBean workflowBean,HttpServletRequest req){
		String message = "处理任务成功";
		AjaxJson j = new AjaxJson();
		boolean flag = true;
		try {
			//审批批语
			workflowBean.setComment(EcodeString(workflowBean.getComment()));
			String taskIds = workflowBean.getTaskId();//获取任务id
			String ids = workflowBean.getBusId();//业务单据ID
			if(StringUtil.isNotEmpty(taskIds)){
				String[] taskIdArr=taskIds.split(",");
				String[] busIdsArr = ids.split(",");
				for (int i = 0; i < taskIdArr.length; i++) {
					WorkflowBean bean = new WorkflowBean();
					bean.setBusId(busIdsArr[i]);
					bean.setTaskId(taskIdArr[i]);
					bean.setComment(workflowBean.getComment());
					bean.setOutcome(workflowBean.getOutcome());
					//审批驳回
					workFlowService.processReject(bean);
				}
			}
		} catch (Exception e) {
			message = "处理任务失败："+e.getMessage();
			flag = false;
			throw new BusinessException(message);
		}
		j.setSuccess(flag);
		j.setMsg(message);
		return j;
	}
	
	public String EcodeString(String comment){
		if(comment!=null){  
		    if(!(java.nio.charset.Charset.forName("GBK").newEncoder().canEncode(comment))){  
		        try {  
		        	comment =  new String(comment.getBytes("ISO-8859-1"),"UTF-8");  
		        } catch (UnsupportedEncodingException e) {  
		            e.printStackTrace();  
		        }     
		    }     
		}
		return comment;
	}
	/**历史审批意见datagrid*/
	@RequestMapping(params = "approveDatagrid")
	public void approvedatagrid(String taskId,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		List<TaskCommentAndFile> list =  workFlowQueryService.getWorkFlowComment(taskId);
		setUrlValue(list);
		dataGrid.setResults(list);
		TagUtil.datagrid(response, dataGrid);
	}
	
	private void setUrlValue(List<TaskCommentAndFile> list){		
		for (TaskCommentAndFile entity : list) {
			String filePath = entity.getFilePath(); 
			if(StringUtil.isEmpty(filePath))
				continue;
			String fileN = filePath.substring(filePath.lastIndexOf("/")+1);
			String fileName = "<a class=\"a_style\" href=\"fileUpAndDownLoadController.do?viewDoc&filePath="+entity.getFilePath()+"\" >"+fileN+"</a>";
			entity.setFilePath(fileName);
		}
	}
	/**
	 * 添加动态表单
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "动态表单添加成功";
		try{
			String processDefinitionId = request.getParameter("processDefinitionId");
			Map<String,String> formMap = getFormParams(request);
			workFlowService.saveDymaticFormData(formMap,processDefinitionId);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "动态表单添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	/**
	 * 获取动态表单界面的所有值
	 * @param request
	 */
	private Map<String,String> getFormParams(HttpServletRequest request) {  
        Map<String,String> map = new HashMap<String,String>();  
        Enumeration<String> paramNames = request.getParameterNames();  
        while (paramNames.hasMoreElements()) {  
            String paramName = (String) paramNames.nextElement();  
  
            String[] paramValues = request.getParameterValues(paramName);  
            if (paramValues.length == 1) {  
                String paramValue = paramValues[0];  
                if (paramValue.length() != 0) {  
                    map.put(paramName, paramValue);  
                }  
            }  
        }  
        return map;
    }
	
	/**
	 * 由业务单据ID获取流程信息
	 * @param userid
	 * @return
	 */
	@RequestMapping(params = "getProcessMsg")
	@ResponseBody
	public HistoricProcessInstance getProcessMsg(String className,String busiID){
		HistoricProcessInstance processDef = null;
		try {
			processDef = workFlowService.getProcessMsgFromBusiId(className,busiID);
		} catch (org.jeecgframework.web.cgform.exception.BusinessException e) {
			e.printStackTrace();
		}
		return processDef;
	}
	/**
	 * 修改办理人调整界面
	 * @return
	 */
	@RequestMapping(params = "goViewComent")
	public ModelAndView goViewComent(String processInstanceId, HttpServletRequest req) {
		//回传任务ID
		req.setAttribute("processInstanceId", processInstanceId);
		return new ModelAndView("com/buss/activiti/handler/commentListForBusy");
	}
	@RequestMapping(params = "viewApproveDatagrid")
	public ModelAndView viewApproveDatagrid(String taskId, HttpServletRequest req) {
		//回传任务ID
		req.setAttribute("taskId", taskId);
		return new ModelAndView("com/buss/activiti/handler/commentListForTask");
	}
	
	/**
	 * 流程记录主页面跳转
	 * @param req
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "goProcessRecordMain")
	public ModelAndView goProcessRecordMain(HttpServletRequest req) throws Exception {
		//业务主键id
		String busId = req.getParameter("busId");
		//工作流id
		String workflowId = req.getParameter("workflowId");
		//单据提交人
		String submitUser= req.getParameter("submitUser");
		//打印审批意见的路径
		String printUrl = req.getParameter("printUrl");
		req.setAttribute("busId", busId);
		req.setAttribute("workflowId", workflowId);
		req.setAttribute("submitUser", submitUser);
		req.setAttribute("printUrl", printUrl);
		
		return new ModelAndView("com/buss/activiti/procdef/processRecordMainList");
	}
	
	/**
	 * 查看流程记录线跳转页面
	 * @param req
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(params = "goProcessRecord")
	public ModelAndView goProcessRecord(HttpServletRequest req) throws Exception {
		//业务主键id
		String busId = req.getParameter("busId");
		//工作流id
		String workflowId = req.getParameter("workflowId");
		//单据提交人
		String submitUser= req.getParameter("submitUser");
		//打印审批意见的路径
		String printUrl = req.getParameter("printUrl");
		req.setAttribute("busId", busId);
		req.setAttribute("workflowId", workflowId);
		req.setAttribute("submitUser", submitUser);
		req.setAttribute("printUrl", printUrl);
		if(StringUtil.isNotEmpty(busId)&&StringUtil.isNotEmpty(workflowId)){
			Map params = new HashMap();
			params.put("busId", busId);
			params.put("workflowId", workflowId);
//			String sql =" select  a.name_ \"name\",a.proc_inst_id_ \"procInstId\",a.assignee_ \"assign\",d.realname \"realName\",a.start_time_ \"startTime\","
//					+ " b.time_ \"time\",to_char(b.message_) \"message\",a.end_time_ \"endTime\",a.delete_reason_ \"deleteReason\","
//					+ " case when  a.start_time_ is not null and a.end_time_ is not null and a.delete_reason_ is not null then '通过'  "
//					+ " when to_char(b.message_) is null  and a.start_time_ is not null and a.end_time_ is null then '审核中' "
//					+ " else '退回' end \"auditStatus\" "
//					+ " from act_hi_taskinst a left join act_hi_comment b on a.proc_inst_id_=b.proc_inst_id_ and a.id_=b.task_id_ "
//					+ " inner join act_hi_procinst c on a.proc_inst_id_=c.proc_inst_id_ and a.proc_def_id_=c.proc_def_id_ "
//					+ " left join t_s_base_user d on d.username=a.assignee_ "
//					+ " where c.business_key_ like '%"+busId+"' "
//					+ " and c.proc_def_id_='"+workflowId+"' "
//					+ " order by a.start_time_,b.time_,a.end_time_,a.assignee_ ";
			List<Map<String,Object>> processList= new ArrayList<>();
			List<Map<String,Object>> commentList= new ArrayList<>();
			List<Map<String,Object>> list = workFlowService.queryProcessRecord(params);
			// systemService.findForJdbc(sql, new Object[]{});
			if(list!= null && list.size()>0){
				Map submitMap=new HashMap();
				submitMap.put("name", "提交");
				//提交人
				submitMap.put("assign",submitUser); 
				String realName ="";
				TSBaseUser user = systemService.findUniqueByProperty(TSBaseUser.class, "userName", submitUser);
				if(user!= null){
					realName=user.getRealName();
					submitMap.put("realName", realName);
					String shortName=realName;
					if(realName.length()>1){
						shortName=realName.substring(realName.length()-2);
					}
					submitMap.put("shortName", shortName);
				}
				submitMap.put("time", list.get(0).get("startTime")==null?"":list.get(0).get("startTime").toString()); //提交时间
				submitMap.put("message", ""); //审批意见（无）
				submitMap.put("auditStatus", "提交"); //状态
				processList.add(submitMap);
				for (int i = 0; i < list.size(); i++) {
					//若此条记录为退回状态，则在此之前需要加上提交信息
					String auditStatus=list.get(i).get("auditStatus")==null?"":list.get(i).get("auditStatus").toString();
					Map m =list.get(i);
					String realName1 =m.get("realName")==null?"":m.get("realName").toString();
					String shortName1=realName1;
					if(realName1.length()>1){
						shortName1=realName1.substring(realName1.length()-2);
					}
					m.put("shortName", shortName1);
					processList.add(m);
					commentList.add(m);
					//退回又提交后，加一条记录
//					if("退回".equals(auditStatus) && i+1<list.size() && !"退回".equals(list.get(i+1).get("auditStatus")) && !list.get(i).get("procInstId").equals(list.get(i+1).get("procInstId"))){
					if(i+1<list.size() &&  !list.get(i).get("procInstId").equals(list.get(i+1).get("procInstId"))){
						Map map= new HashMap();
						map.put("name", "提交");
						//提交人
						map.put("assign",submitUser);
						map.put("realName", realName);
						String shortName=realName;
						if(realName.length()>1){
							shortName=realName.substring(realName.length()-2);
						}
						map.put("shortName", shortName);
						map.put("time",list.get(i+1).get("startTime").toString()); //提交时间
						map.put("message", ""); //审批意见（无）
						map.put("auditStatus", "提交"); //状态
						processList.add(map);
					}
				}
			}
			req.setAttribute("processList", processList);
			req.setAttribute("commentList", commentList);
		}
		//区分是单个页面跳转还是两个页签式的跳转
		String isLine = req.getParameter("isLine");
		if("Y".equals(isLine)){
			return new ModelAndView("com/buss/activiti/procdef/processRecordLineList");
		}
		return new ModelAndView("com/buss/activiti/procdef/processRecordList");
	}
	
	/**
	 * 查看流程图进展跳转页面
	 * @param req
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "goProcessRecordImage")
	public ModelAndView goProcessRecordImage(HttpServletRequest req) throws Exception {
		//业务主键id
		String busId = req.getParameter("busId");
		//工作流id
		String workflowId = req.getParameter("workflowId");
		//单据提交人
		String submitUser= req.getParameter("submitUser");
		req.setAttribute("busId", busId);
		req.setAttribute("workflowId", workflowId);
		req.setAttribute("submitUser", submitUser);
		
		return new ModelAndView("com/buss/activiti/procdef/processRecordImageList");
	}
	
	/**
	 * 查看流程图进度
	 * @param busId
	 * @param workflowId
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(params = "queryProcessImage")
	public void queryProcessImage(String busId,String workflowId,HttpServletRequest request,HttpServletResponse response) throws IOException{
		//根据业务单据主键id和工作流程定义id查询历史流程实例id
//		String busId = request.getParameter("busId");
//		String workflowId = request.getParameter("workflowId");
//		String processInstanceId = request.getParameter("ProcessInstanceId");
		if(StringUtil.isNotEmpty(busId)&&StringUtil.isNotEmpty(workflowId)){
			String sql =" select max(id_) \"processInstanceId\" from act_hi_procinst "
					+ " where proc_def_id_='"+workflowId+"' "
					+ " and business_key_ like '%"+busId+"' ";
			List<Map<String,Object>> listMap=systemService.findForJdbc(sql, new Object[]{});
			if(listMap!= null && listMap.size()>0){
				String processInstanceId = listMap.get(0).get("processInstanceId")==null?null:listMap.get(0).get("processInstanceId").toString();
				if(StringUtil.isNotEmpty(processInstanceId)){
					//获取历史流程实例
					HistoricProcessInstance processInstance =  historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
					//获取流程图
					BpmnModel bpmnModel = repositoryService.getBpmnModel(processInstance.getProcessDefinitionId());
					processEngineConfiguration = processEngine.getProcessEngineConfiguration();
					Context.setProcessEngineConfiguration((ProcessEngineConfigurationImpl) processEngineConfiguration);
					
					ProcessDiagramGenerator diagramGenerator = processEngineConfiguration.getProcessDiagramGenerator();
					ProcessDefinitionEntity definitionEntity = (ProcessDefinitionEntity)repositoryService.getProcessDefinition(processInstance.getProcessDefinitionId());
					
					List<HistoricActivityInstance> highLightedActivitList =  historyService.createHistoricActivityInstanceQuery().processInstanceId(processInstanceId).list();
					//高亮环节id集合
					List<String> highLightedActivitis = new ArrayList<String>();
					
					//高亮线路id集合
					List<String> highLightedFlows = getHighLightedFlows(definitionEntity,highLightedActivitList);
					
					for(HistoricActivityInstance tempActivity : highLightedActivitList){
						String activityId = tempActivity.getActivityId();
						highLightedActivitis.add(activityId);
					}
					//中文会乱码，设置字体
					InputStream imageStream = diagramGenerator.generateDiagram(bpmnModel, "png", highLightedActivitis,highLightedFlows,"宋体","宋体",null,1.0);
					//单独返回流程图，不高亮显示
//        			InputStream imageStream = diagramGenerator.generatePngDiagram(bpmnModel);
					// 输出资源内容到相应对象
					byte[] b = new byte[1024];
					int len;
//					response.setHeader("Content-type","text/html;charset=UTF-8");
//					response.setContentType("com/buss/activiti/procdef/processRecordImageList");  
					while ((len = imageStream.read(b, 0, 1024)) != -1) {
						response.getOutputStream().write(b, 0, len);
					}
					response.getOutputStream().close();
				}
			}
		}
	}
	 /**
     * 获取需要高亮的线
     * @param processDefinitionEntity
     * @param historicActivityInstances
     * @return
     */
    private List<String> getHighLightedFlows(ProcessDefinitionEntity processDefinitionEntity,List<HistoricActivityInstance> historicActivityInstances) {
    	
        List<String> highFlows = new ArrayList<String>();// 用以保存高亮的线flowId
        for (int i = 0; i < historicActivityInstances.size() - 1; i++) {// 对历史流程节点进行遍历
            ActivityImpl activityImpl = processDefinitionEntity .findActivity(historicActivityInstances.get(i).getActivityId());// 得到节点定义的详细信息
            List<ActivityImpl> sameStartTimeNodes = new ArrayList<ActivityImpl>();// 用以保存后需开始时间相同的节点
            ActivityImpl sameActivityImpl1 = processDefinitionEntity.findActivity(historicActivityInstances.get(i + 1).getActivityId());
            // 将后面第一个节点放在时间相同节点的集合里
            sameStartTimeNodes.add(sameActivityImpl1);
            for (int j = i + 1; j < historicActivityInstances.size() - 1; j++) {
                HistoricActivityInstance activityImpl1 = historicActivityInstances.get(j);// 后续第一个节点
                HistoricActivityInstance activityImpl2 = historicActivityInstances.get(j + 1);// 后续第二个节点
                if (activityImpl1.getStartTime().equals(activityImpl2.getStartTime())) {
                    // 如果第一个节点和第二个节点开始时间相同保存
                    ActivityImpl sameActivityImpl2 = processDefinitionEntity.findActivity(activityImpl2.getActivityId());
                    sameStartTimeNodes.add(sameActivityImpl2);
                } else {
                    // 有不相同跳出循环
                    break;
                }
            }
            List<PvmTransition> pvmTransitions = activityImpl.getOutgoingTransitions();// 取出节点的所有出去的线
            for (PvmTransition pvmTransition : pvmTransitions) {
                // 对所有的线进行遍历
                ActivityImpl pvmActivityImpl = (ActivityImpl) pvmTransition.getDestination();
                // 如果取出的线的目标节点存在时间相同的节点里，保存该线的id，进行高亮显示
                if (sameStartTimeNodes.contains(pvmActivityImpl)) {
                    highFlows.add(pvmTransition.getId());
                }
            }
        }
        return highFlows;
    }

    /**
     * 审批查看附件跳转页面
     * @param fileDict
     * @param req
     * @return
     */
    @RequestMapping(params = "fileEdit")
	public ModelAndView fileEdit(FileDictEntity fileDict, HttpServletRequest req) {
	
		//===================================================================================
		//获取参数 
		String tableName = req.getParameter("tableName");
		String tableId = req.getParameter("tableId");
		String isEdit = req.getParameter("isEdit");
		String modName = req.getParameter("modName");
		//===================================================================================
		//查询-附件
	   String hql0 = "from FileDictEntity where 1 = 1 AND  tableName ='"+tableName+"' AND tableId = ? ";
	   try{
	   	List<FileDictEntity> FileDictEntityList = systemService.findHql(hql0,tableId);
	   		String realPath = "http://"+FtpUtil.getFtpUrl()+"/upload/";
		   	if(!FtpUtil.isStart()){
	    		realPath ="";
	    	}
			req.setAttribute("fileDictList", FileDictEntityList);
			req.setAttribute("tableName", tableName);
			req.setAttribute("tableId", tableId);
			req.setAttribute("isEdit", isEdit);
			req.setAttribute("modName", modName);
			req.setAttribute("realPath", realPath);
		}catch(Exception e){
			logger.info(e.getMessage());
		} 
		return new ModelAndView("com/buss/activiti/handler/busiApproveFilePreview");
	}

    
}
