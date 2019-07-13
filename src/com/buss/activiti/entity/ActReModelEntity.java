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
 * @Description: ACT_RE_MODEL
 * @author onlineGenerator
 * @date 2018-03-21 15:04:35
 * @version V1.0   
 *
 */
@Entity
@Table(name = "act_re_model", schema = "")
@SuppressWarnings("serial")
public class ActReModelEntity implements java.io.Serializable {
	/**id*/
	@Excel(name="id")
	private java.lang.String id;
	/**rev*/
	@Excel(name="rev")
	private java.lang.Integer rev;
	/**name*/
	@Excel(name="name")
	private java.lang.String name;
	/**key*/
	@Excel(name="key")
	private java.lang.String key;
	/**category*/
	@Excel(name="category")
	private java.lang.String category;
	/**createTime*/
	@Excel(name="createTime",format = "yyyy-MM-dd")
	private java.util.Date createTime;
	/**lastUpdateTime*/
	@Excel(name="lastUpdateTime",format = "yyyy-MM-dd")
	private java.util.Date lastUpdateTime;
	/**version*/
	@Excel(name="version")
	private java.lang.Integer version;
	/**metaInfo*/
	@Excel(name="metaInfo")
	private java.lang.String metaInfo;
	/**deploymentId*/
	@Excel(name="deploymentId")
	private java.lang.String deploymentId;
	/**editorSourceValueId*/
	@Excel(name="editorSourceValueId")
	private java.lang.String editorSourceValueId;
	/**editorSourceExtraValueId*/
	@Excel(name="editorSourceExtraValueId")
	private java.lang.String editorSourceExtraValueId;
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
	@Column(name ="KEY_",nullable=true,length=255)
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
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  createTime
	 */
	@Column(name ="CREATE_TIME_",nullable=true,scale=6)
	public java.util.Date getCreateTime(){
		return this.createTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  createTime
	 */
	public void setCreateTime(java.util.Date createTime){
		this.createTime = createTime;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  lastUpdateTime
	 */
	@Column(name ="LAST_UPDATE_TIME_",nullable=true,scale=6)
	public java.util.Date getLastUpdateTime(){
		return this.lastUpdateTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  lastUpdateTime
	 */
	public void setLastUpdateTime(java.util.Date lastUpdateTime){
		this.lastUpdateTime = lastUpdateTime;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  version
	 */
	@Column(name ="VERSION_",nullable=true,length=10)
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
	 *@return: java.lang.String  metaInfo
	 */
	@Column(name ="META_INFO_",nullable=true,length=2000)
	public java.lang.String getMetaInfo(){
		return this.metaInfo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  metaInfo
	 */
	public void setMetaInfo(java.lang.String metaInfo){
		this.metaInfo = metaInfo;
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
	 *@return: java.lang.String  editorSourceValueId
	 */
	@Column(name ="EDITOR_SOURCE_VALUE_ID_",nullable=true,length=64)
	public java.lang.String getEditorSourceValueId(){
		return this.editorSourceValueId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  editorSourceValueId
	 */
	public void setEditorSourceValueId(java.lang.String editorSourceValueId){
		this.editorSourceValueId = editorSourceValueId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  editorSourceExtraValueId
	 */
	@Column(name ="EDITOR_SOURCE_EXTRA_VALUE_ID_",nullable=true,length=64)
	public java.lang.String getEditorSourceExtraValueId(){
		return this.editorSourceExtraValueId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  editorSourceExtraValueId
	 */
	public void setEditorSourceExtraValueId(java.lang.String editorSourceExtraValueId){
		this.editorSourceExtraValueId = editorSourceExtraValueId;
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
