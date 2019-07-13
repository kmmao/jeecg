package com.buss.activiti.entity;

import java.io.File;
/**
 * 任务处理对象
 * @author Administrator
 *
 */
public class WorkflowBean {

	private File file;		//流程定义部署文件
	private String filename;//流程定义名称
	
	private String busId;//业务单据ID
	
	private String deploymentId;//部署对象ID
	private String imageName;	//资源文件名称
	private String taskId;		//任务ID
	private String outcome;		//连线名称
	private String comment;		//备注
	private String filePath;	//附件路径
	private String realFilePath;	//附件路径
	private String conditionPara; //条件流转需要判断的字段名称
	private String conditionParaType;//条件流转需要判断的字段类型
	private String conditionParaValue; //条件流转过程中判断的字段的数值
	
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}

	
	public String getBusId() {
		return busId;
	}
	public void setBusId(String busId) {
		this.busId = busId;
	}
	public String getDeploymentId() {
		return deploymentId;
	}
	public void setDeploymentId(String deploymentId) {
		this.deploymentId = deploymentId;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getOutcome() {
		return outcome;
	}
	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getRealFilePath() {
		return realFilePath;
	}
	public void setRealFilePath(String realFilePath) {
		this.realFilePath = realFilePath;
	}
	public String getConditionPara() {
		return conditionPara;
	}
	public void setConditionPara(String conditionPara) {
		this.conditionPara = conditionPara;
	}
	public String getConditionParaValue() {
		return conditionParaValue;
	}
	public void setConditionParaValue(String conditionParaValue) {
		this.conditionParaValue = conditionParaValue;
	}
	public String getConditionParaType() {
		return conditionParaType;
	}
	public void setConditionParaType(String conditionParaType) {
		this.conditionParaType = conditionParaType;
	}
	
	
}
