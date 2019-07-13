/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.buss.activiti.entity;

import java.util.Date;
import java.util.Map;

import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;

import com.buss.activiti.util.Variable;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 工作流Entity
 * @author ThinkGem
 * @version 2013-11-03
 */
public class Act{
	
	private static final long serialVersionUID = 1L;
	
	
	private Task task; 			// 任务对象
	private ProcessDefinition procDef; 	// 流程定义对象
	private HistoricTaskInstance histTask; // 历史任务
	private Variable vars; 		// 流程变量
	private String status; 		// 任务状态（todo/claim/finish）

	public Act() {
		super();
	}

	
	@JsonIgnore
	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	@JsonIgnore
	public ProcessDefinition getProcDef() {
		return procDef;
	}

	public void setProcDef(ProcessDefinition procDef) {
		this.procDef = procDef;
	}

	@JsonIgnore
	public HistoricTaskInstance getHistTask() {
		return histTask;
	}

	public void setHistTask(HistoricTaskInstance histTask) {
		this.histTask = histTask;
	}
	
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public Variable getVars() {
		return vars;
	}

	public void setVars(Variable vars) {
		this.vars = vars;
	}
	/**
	 * 通过Map设置流程变量值
	 * @param map
	 */
	public void setVars(Map<String, Object> map) {
		this.vars = new Variable(map);
	}
}


