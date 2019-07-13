package com.buss.activiti.util;

import java.util.Arrays;
import java.util.List;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.HistoryServiceImpl;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.constant.DataBaseConstant;
import org.jeecgframework.core.util.ApplicationContextUtil;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.service.SystemService;

public class NextTaskUtils {

	private HistoryServiceImpl historyService;
	
	/**
	 * 设置任务的完成人
	 * */
	public void setTaskAsignee(DelegateTask currTask) throws Exception {
		
		//获取实例ID
//		String processInstanceId= currTask.getProcessInstanceId();
		//获取上面的多个任务
		//List<HistoricTaskInstance> taskList=historyService.createHistoricTaskInstanceQuery().processInstanceId(processInstanceId).orderByTaskCreateTime().desc().list();
		
		//获取最后面一个任务，即本任务的上一个任务
		//HistoricTaskInstance lastTask=taskList.get(0);
		
		//获取上一个任务的执行人
		//String lastTaskAsignee=lastTask.getAssignee();
		SystemService  systemService= ApplicationContextUtil.getBean("systemService");
		//当前登录用户所在科室（行政科室）
		String sysOrgCode = ResourceUtil.getUserSystemData(DataBaseConstant.SYS_ORG_CODE);
		//获取所在科室的负责人  pkPsndoc1
		TSDepart depart = systemService.findUniqueByProperty(TSDepart.class, "orgCode", sysOrgCode);
		if(depart!= null){
			//String manager =depart.getPkPsndoc1();
//			if(StringUtil.isNotEmpty(manager)){
//				//当前任务
//				String manaArr[] = manager.split(",");
////				currTask.setAssignee(manager);
//				currTask.addCandidateUsers(Arrays.asList(manaArr));
//			}else{
//				throw new BusinessException("所在科室没有负责人，请先设置科室负责人！");
//			}
		}
		
//		if(lastTaskAsignee!=null&&lastTaskAsignee.equals("zhangsan")) {
//			currTask.setAssignee("admin");
//		}else {
//			currTask.setAssignee("lisi");
//		}
	}
}
