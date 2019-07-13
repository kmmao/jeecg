package com.buss.activiti.entity;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.task.Comment;

import com.buss.activiti.util.DateHandleUtil;

public class TaskCommentAndFile {

	private String taskID;//任务ID
	private String 	fullMessage;//批注内容
	private String 	userId;//批注人
	private String 	time;//批注时间
	private String 	filePath;//审批附件
	public String getTaskID() {
		return taskID;
	}
	public void setTaskID(String taskID) {
		this.taskID = taskID;
	}
	public String getFullMessage() {
		return fullMessage;
	}
	public void setFullMessage(String fullMessage) {
		this.fullMessage = fullMessage;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	/**
	 * 封装任务的审批批语和审批附件
	 * @param comentList 审批批语
	 * @param variableMap 审批附件 key:taskId value:variableValue;
	 * @return
	 */
	public static List<TaskCommentAndFile> getTasCommentAndFile(List<Comment> comentList,
			List<HistoricVariableInstance> variableList){
		List<TaskCommentAndFile> list = new ArrayList<TaskCommentAndFile>();
		if(comentList==null||comentList.size()==0)
			return list;
		for (Comment comment : comentList) {
			TaskCommentAndFile temp = new TaskCommentAndFile();
			String tasId = comment.getTaskId();
			temp.setTaskID(tasId);//任务ID
			temp.setUserId(comment.getUserId());//批注人
			temp.setFullMessage(comment.getFullMessage());//批注
			temp.setTime(DateHandleUtil.formateDate("yyyy-MM-dd", comment.getTime()));//批注时间
			String variableName = "filePath"+tasId;
			//审批附件
			for (HistoricVariableInstance variaIns : variableList) {
				String key = variaIns.getVariableName();
				if(variableName.equals(key)){
					temp.setFilePath(variaIns.getValue().toString());
					break;
				}					
			}
			list.add(temp);
		}
		return list;
		
	}
}
