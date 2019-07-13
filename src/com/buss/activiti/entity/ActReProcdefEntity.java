package com.buss.activiti.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

/**   
 * @Title: Entity
 * @Description: ACT_RE_PROCDEF
 * @author onlineGenerator
 * @date 2018-03-21 15:04:25
 * @version V1.0   
 *
 */
@Entity
@Table(name = "act_re_procdef", schema = "")
@SuppressWarnings("serial")
public class ActReProcdefEntity implements java.io.Serializable {
	/**id*/
	@Excel(name="id")
	private java.lang.String id;
	/**rev*/
	@Excel(name="rev")
	private java.lang.Integer rev;
	/**category*/
	@Excel(name="category")
	private java.lang.String category;
	/**name*/
	@Excel(name="name")
	private java.lang.String name;
	/**key*/
	@Excel(name="key")
	private java.lang.String key;
	/**version*/
	@Excel(name="version")
	private java.lang.Integer version;
	/**deploymentId*/
	@Excel(name="deploymentId")
	private java.lang.String deploymentId;
	/**resourceName*/
	@Excel(name="resourceName")
	private java.lang.String resourceName;
	/**dgrmResourceName*/
	@Excel(name="dgrmResourceName")
	private java.lang.String dgrmResourceName;
	/**description*/
	@Excel(name="description")
	private java.lang.String description;
	/**hasStartFormKey*/
	@Excel(name="hasStartFormKey")
	private java.lang.Integer hasStartFormKey;
	/**hasGraphicalNotation*/
	@Excel(name="hasGraphicalNotation")
	private java.lang.Integer hasGraphicalNotation;
	/**suspensionState*/
	@Excel(name="suspensionState")
	private java.lang.Integer suspensionState;
	/**tenantId*/
	@Excel(name="tenantId")
	private java.lang.String tenantId;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  id
	 */
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name ="ID_",nullable=false,length=64)
	public java.lang.String getId(){
		return this.id;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  id
	 */
	public void setId(java.lang.String id){
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  rev
	 */
	@Column(name ="REV_",nullable=true,length=10)
	public java.lang.Integer getRev(){
		return this.rev;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  rev
	 */
	public void setRev(java.lang.Integer rev){
		this.rev = rev;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  category
	 */
	@Column(name ="CATEGORY_",nullable=true,length=255)
	public java.lang.String getCategory(){
		return this.category;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  category
	 */
	public void setCategory(java.lang.String category){
		this.category = category;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  name
	 */
	@Column(name ="NAME_",nullable=true,length=255)
	public java.lang.String getName(){
		return this.name;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  name
	 */
	public void setName(java.lang.String name){
		this.name = name;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  key
	 */
	@Column(name ="KEY_",nullable=false,length=255)
	public java.lang.String getKey(){
		return this.key;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  key
	 */
	public void setKey(java.lang.String key){
		this.key = key;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  version
	 */
	@Column(name ="VERSION_",nullable=false,length=10)
	public java.lang.Integer getVersion(){
		return this.version;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  version
	 */
	public void setVersion(java.lang.Integer version){
		this.version = version;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  deploymentId
	 */
	@Column(name ="DEPLOYMENT_ID_",nullable=true,length=64)
	public java.lang.String getDeploymentId(){
		return this.deploymentId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  deploymentId
	 */
	public void setDeploymentId(java.lang.String deploymentId){
		this.deploymentId = deploymentId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  resourceName
	 */
	@Column(name ="RESOURCE_NAME_",nullable=true,length=2000)
	public java.lang.String getResourceName(){
		return this.resourceName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  resourceName
	 */
	public void setResourceName(java.lang.String resourceName){
		this.resourceName = resourceName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  dgrmResourceName
	 */
	@Column(name ="DGRM_RESOURCE_NAME_",nullable=true,length=4000)
	public java.lang.String getDgrmResourceName(){
		return this.dgrmResourceName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  dgrmResourceName
	 */
	public void setDgrmResourceName(java.lang.String dgrmResourceName){
		this.dgrmResourceName = dgrmResourceName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  description
	 */
	@Column(name ="DESCRIPTION_",nullable=true,length=2000)
	public java.lang.String getDescription(){
		return this.description;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  description
	 */
	public void setDescription(java.lang.String description){
		this.description = description;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  hasStartFormKey
	 */
	@Column(name ="HAS_START_FORM_KEY_",nullable=true,length=1)
	public java.lang.Integer getHasStartFormKey(){
		return this.hasStartFormKey;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  hasStartFormKey
	 */
	public void setHasStartFormKey(java.lang.Integer hasStartFormKey){
		this.hasStartFormKey = hasStartFormKey;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  hasGraphicalNotation
	 */
	@Column(name ="HAS_GRAPHICAL_NOTATION_",nullable=true,length=1)
	public java.lang.Integer getHasGraphicalNotation(){
		return this.hasGraphicalNotation;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  hasGraphicalNotation
	 */
	public void setHasGraphicalNotation(java.lang.Integer hasGraphicalNotation){
		this.hasGraphicalNotation = hasGraphicalNotation;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  suspensionState
	 */
	@Column(name ="SUSPENSION_STATE_",nullable=true,length=10)
	public java.lang.Integer getSuspensionState(){
		return this.suspensionState;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  suspensionState
	 */
	public void setSuspensionState(java.lang.Integer suspensionState){
		this.suspensionState = suspensionState;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  tenantId
	 */
	@Column(name ="TENANT_ID_",nullable=true,length=255)
	public java.lang.String getTenantId(){
		return this.tenantId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  tenantId
	 */
	public void setTenantId(java.lang.String tenantId){
		this.tenantId = tenantId;
	}
}
