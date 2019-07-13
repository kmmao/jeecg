package cn.com.linkwide.cont.desgin.procolcont.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.lang.String;
import java.lang.Double;
import java.lang.Integer;
import java.math.BigDecimal;
import javax.xml.soap.Text;
import java.sql.Blob;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.SequenceGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

import cn.com.linkwide.common.delcheck.annotation.DetailedEntity;
import cn.com.linkwide.cont.coninformation.entity.ConInformationEntity;

/**   
 * @Title: Entity
 * @Description: 合同类别
 * @author onlineGenerator
 * @date 2018-09-19 09:36:16
 * @version V1.0   
 *
 */
@Entity
@Table(name = "procol_cont", schema = "")
@SuppressWarnings("serial")
@DetailedEntity(entity={ConInformationEntity.class})
public class ProcolContEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**创建人名称*/
	private java.lang.String createName;
	/**创建人登录名称*/
	private java.lang.String createBy;
	/**创建日期*/
	private java.util.Date createDate;
	/**更新人名称*/
	private java.lang.String updateName;
	/**更新人登录名称*/
	private java.lang.String updateBy;
	/**更新日期*/
	private java.util.Date updateDate;
	/**所属部门*/
	private java.lang.String sysOrgCode;
	/**所属公司*/
	private java.lang.String sysCompanyCode;
	/**流程状态*/
	private java.lang.String bpmStatus;
	/**类型编码*/
	@Excel(name="类型编码",width=15)
	private java.lang.String typeCode;
	/**类型名称*/
	@Excel(name="类型名称",width=15)
	private java.lang.String typeName;
	/**合同或协议*/
	@Excel(name="合同或协议",width=15)
	private java.lang.String conCode;
	/**类型扩展*/
	private java.lang.String typeExect;
	/**类型扩展2*/
	private java.lang.String typeExect2;
	/**类型扩展3*/
	private java.lang.String typeExect3;
	/**类型扩展4*/
	private java.lang.String typeExect4;
	/**类型扩展5*/
	private java.lang.String typeExect5;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  主键
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
	 *@param: java.lang.String  主键
	 */
	public void setId(java.lang.String id){
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建人名称
	 */

	@Column(name ="CREATE_NAME",nullable=true,length=50)
	public java.lang.String getCreateName(){
		return this.createName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人名称
	 */
	public void setCreateName(java.lang.String createName){
		this.createName = createName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建人登录名称
	 */

	@Column(name ="CREATE_BY",nullable=true,length=50)
	public java.lang.String getCreateBy(){
		return this.createBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人登录名称
	 */
	public void setCreateBy(java.lang.String createBy){
		this.createBy = createBy;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  创建日期
	 */

	@Column(name ="CREATE_DATE",nullable=true,length=20)
	public java.util.Date getCreateDate(){
		return this.createDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  创建日期
	 */
	public void setCreateDate(java.util.Date createDate){
		this.createDate = createDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  更新人名称
	 */

	@Column(name ="UPDATE_NAME",nullable=true,length=50)
	public java.lang.String getUpdateName(){
		return this.updateName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  更新人名称
	 */
	public void setUpdateName(java.lang.String updateName){
		this.updateName = updateName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  更新人登录名称
	 */

	@Column(name ="UPDATE_BY",nullable=true,length=50)
	public java.lang.String getUpdateBy(){
		return this.updateBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  更新人登录名称
	 */
	public void setUpdateBy(java.lang.String updateBy){
		this.updateBy = updateBy;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  更新日期
	 */

	@Column(name ="UPDATE_DATE",nullable=true,length=20)
	public java.util.Date getUpdateDate(){
		return this.updateDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  更新日期
	 */
	public void setUpdateDate(java.util.Date updateDate){
		this.updateDate = updateDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  所属部门
	 */

	@Column(name ="SYS_ORG_CODE",nullable=true,length=50)
	public java.lang.String getSysOrgCode(){
		return this.sysOrgCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  所属部门
	 */
	public void setSysOrgCode(java.lang.String sysOrgCode){
		this.sysOrgCode = sysOrgCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  所属公司
	 */

	@Column(name ="SYS_COMPANY_CODE",nullable=true,length=50)
	public java.lang.String getSysCompanyCode(){
		return this.sysCompanyCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  所属公司
	 */
	public void setSysCompanyCode(java.lang.String sysCompanyCode){
		this.sysCompanyCode = sysCompanyCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  流程状态
	 */

	@Column(name ="BPM_STATUS",nullable=true,length=32)
	public java.lang.String getBpmStatus(){
		return this.bpmStatus;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  流程状态
	 */
	public void setBpmStatus(java.lang.String bpmStatus){
		this.bpmStatus = bpmStatus;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  类型编码
	 */

	@Column(name ="TYPE_CODE",nullable=true,length=32)
	public java.lang.String getTypeCode(){
		return this.typeCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  类型编码
	 */
	public void setTypeCode(java.lang.String typeCode){
		this.typeCode = typeCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  类型名称
	 */

	@Column(name ="TYPE_NAME",nullable=true,length=32)
	public java.lang.String getTypeName(){
		return this.typeName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  类型名称
	 */
	public void setTypeName(java.lang.String typeName){
		this.typeName = typeName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  合同或协议
	 */

	@Column(name ="CON_CODE",nullable=true,length=32)
	public java.lang.String getConCode(){
		return this.conCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  合同或协议
	 */
	public void setConCode(java.lang.String conCode){
		this.conCode = conCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  类型扩展
	 */

	@Column(name ="TYPE_EXECT",nullable=true,length=32)
	public java.lang.String getTypeExect(){
		return this.typeExect;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  类型扩展
	 */
	public void setTypeExect(java.lang.String typeExect){
		this.typeExect = typeExect;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  类型扩展2
	 */

	@Column(name ="TYPE_EXECT2",nullable=true,length=32)
	public java.lang.String getTypeExect2(){
		return this.typeExect2;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  类型扩展2
	 */
	public void setTypeExect2(java.lang.String typeExect2){
		this.typeExect2 = typeExect2;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  类型扩展3
	 */

	@Column(name ="TYPE_EXECT3",nullable=true,length=32)
	public java.lang.String getTypeExect3(){
		return this.typeExect3;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  类型扩展3
	 */
	public void setTypeExect3(java.lang.String typeExect3){
		this.typeExect3 = typeExect3;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  类型扩展4
	 */

	@Column(name ="TYPE_EXECT4",nullable=true,length=32)
	public java.lang.String getTypeExect4(){
		return this.typeExect4;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  类型扩展4
	 */
	public void setTypeExect4(java.lang.String typeExect4){
		this.typeExect4 = typeExect4;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  类型扩展5
	 */

	@Column(name ="TYPE_EXECT5",nullable=true,length=32)
	public java.lang.String getTypeExect5(){
		return this.typeExect5;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  类型扩展5
	 */
	public void setTypeExect5(java.lang.String typeExect5){
		this.typeExect5 = typeExect5;
	}
}
