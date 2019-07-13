package cn.com.linkwide.ba.material.attachs.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

/**   
 * @Title: Entity
 * @Description: 物资附件
 * @author onlineGenerator
 * @date 2017-12-04 15:53:22
 * @version V1.0   
 *
 */
@Entity
@Table(name = "l_ba_material_attachs", schema = "")
@SuppressWarnings("serial")
public class LBaMaterialAttachsEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**物资ID*/
	@Excel(name="物资ID")
	private java.lang.String materialId;
	/**附件名称*/
	@Excel(name="附件名称")
	private java.lang.String fileName;
	/**附件路径*/
	@Excel(name="附件路径")
	private java.lang.String filePath;
	/**登陆部门*/
	@Excel(name="登陆部门")
	private java.lang.String departId;
	/**创建人*/
	@Excel(name="创建人")
	private java.lang.String createBy;
	/**创建时间*/
	@Excel(name="创建时间",format = "yyyy-MM-dd")
	private java.util.Date createDate;
	/**更新人*/
	@Excel(name="更新人")
	private java.lang.String updateBy;
	/**更新时间*/
	@Excel(name="更新时间",format = "yyyy-MM-dd")
	private java.util.Date updateDate;
	/**扩展字段1*/
	@Excel(name="扩展字段1")
	private java.lang.String extend1;
	/**扩展字段2*/
	@Excel(name="扩展字段2")
	private java.lang.String extend2;
	/**扩展字段3*/
	@Excel(name="扩展字段3")
	private java.lang.String extend3;
	/**扩展字段4*/
	@Excel(name="扩展字段4")
	private java.lang.String extend4;
	/**扩展字段5*/
	@Excel(name="扩展字段5")
	private java.lang.String extend5;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  id
	 */
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name ="ID",nullable=false,length=36)
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
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  物资ID
	 */
	@Column(name ="MATERIAL_ID",nullable=false,length=36)
	public java.lang.String getMaterialId(){
		return this.materialId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  物资ID
	 */
	public void setMaterialId(java.lang.String materialId){
		this.materialId = materialId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  附件名称
	 */
	@Column(name ="FILE_NAME",nullable=false,length=36)
	public java.lang.String getFileName(){
		return this.fileName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  附件名称
	 */
	public void setFileName(java.lang.String fileName){
		this.fileName = fileName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  附件路径
	 */
	@Column(name ="FILE_PATH",nullable=false,length=255)
	public java.lang.String getFilePath(){
		return this.filePath;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  附件路径
	 */
	public void setFilePath(java.lang.String filePath){
		this.filePath = filePath;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  登陆部门
	 */
	@Column(name ="DEPART_ID",nullable=false,length=50)
	public java.lang.String getDepartId(){
		return this.departId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  登陆部门
	 */
	public void setDepartId(java.lang.String departId){
		this.departId = departId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建人
	 */
	@Column(name ="CREATE_BY",nullable=false,length=50)
	public java.lang.String getCreateBy(){
		return this.createBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人
	 */
	public void setCreateBy(java.lang.String createBy){
		this.createBy = createBy;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  创建时间
	 */
	@Column(name ="CREATE_DATE",nullable=false)
	public java.util.Date getCreateDate(){
		return this.createDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  创建时间
	 */
	public void setCreateDate(java.util.Date createDate){
		this.createDate = createDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  更新人
	 */
	@Column(name ="UPDATE_BY",nullable=true,length=50)
	public java.lang.String getUpdateBy(){
		return this.updateBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  更新人
	 */
	public void setUpdateBy(java.lang.String updateBy){
		this.updateBy = updateBy;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  更新时间
	 */
	@Column(name ="UPDATE_DATE",nullable=true)
	public java.util.Date getUpdateDate(){
		return this.updateDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  更新时间
	 */
	public void setUpdateDate(java.util.Date updateDate){
		this.updateDate = updateDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  扩展字段1
	 */
	@Column(name ="EXTEND1",nullable=true,length=255)
	public java.lang.String getExtend1(){
		return this.extend1;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  扩展字段1
	 */
	public void setExtend1(java.lang.String extend1){
		this.extend1 = extend1;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  扩展字段2
	 */
	@Column(name ="EXTEND2",nullable=true,length=255)
	public java.lang.String getExtend2(){
		return this.extend2;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  扩展字段2
	 */
	public void setExtend2(java.lang.String extend2){
		this.extend2 = extend2;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  扩展字段3
	 */
	@Column(name ="EXTEND3",nullable=true,length=255)
	public java.lang.String getExtend3(){
		return this.extend3;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  扩展字段3
	 */
	public void setExtend3(java.lang.String extend3){
		this.extend3 = extend3;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  扩展字段4
	 */
	@Column(name ="EXTEND4",nullable=true,length=255)
	public java.lang.String getExtend4(){
		return this.extend4;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  扩展字段4
	 */
	public void setExtend4(java.lang.String extend4){
		this.extend4 = extend4;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  扩展字段5
	 */
	@Column(name ="EXTEND5",nullable=true,length=255)
	public java.lang.String getExtend5(){
		return this.extend5;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  扩展字段5
	 */
	public void setExtend5(java.lang.String extend5){
		this.extend5 = extend5;
	}
}
