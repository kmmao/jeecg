package cn.com.linkwide.cont.desgin.procolotype.entity;

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
import cn.com.linkwide.cont.conprotocol.entity.ConProtocolEntity;

/**   
 * @Title: Entity
 * @Description: 协议类型
 * @author onlineGenerator
 * @date 2018-09-19 14:53:42
 * @version V1.0   
 *
 */
@Entity
@Table(name = "procolo_type", schema = "")
@SuppressWarnings("serial")
@DetailedEntity(entity={ConProtocolEntity.class})
public class ProcoloTypeEntity implements java.io.Serializable {
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
	private java.lang.String procoloCode;
	/**类型名称*/
	@Excel(name="类型名称",width=15)
	private java.lang.String procoloName;
	/**类型扩展字段*/
	private java.lang.String procoloExtents;
	/**类型扩展字段2*/
	private java.lang.String procoloExtents2;
	/**类型扩展字段3*/
	private java.lang.String procoloExtents3;
	/**类型扩展字段4*/
	private java.lang.String procoloExtents4;
	/**类型扩展字段5*/
	private java.lang.String procoloExtents5;
	
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

	@Column(name ="PROCOLO_CODE",nullable=true,length=32)
	public java.lang.String getProcoloCode(){
		return this.procoloCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  类型编码
	 */
	public void setProcoloCode(java.lang.String procoloCode){
		this.procoloCode = procoloCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  类型名称
	 */

	@Column(name ="PROCOLO_NAME",nullable=true,length=32)
	public java.lang.String getProcoloName(){
		return this.procoloName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  类型名称
	 */
	public void setProcoloName(java.lang.String procoloName){
		this.procoloName = procoloName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  类型扩展字段
	 */

	@Column(name ="PROCOLO_EXTENTS",nullable=true,length=32)
	public java.lang.String getProcoloExtents(){
		return this.procoloExtents;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  类型扩展字段
	 */
	public void setProcoloExtents(java.lang.String procoloExtents){
		this.procoloExtents = procoloExtents;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  类型扩展字段2
	 */

	@Column(name ="PROCOLO_EXTENTS2",nullable=true,length=32)
	public java.lang.String getProcoloExtents2(){
		return this.procoloExtents2;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  类型扩展字段2
	 */
	public void setProcoloExtents2(java.lang.String procoloExtents2){
		this.procoloExtents2 = procoloExtents2;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  类型扩展字段3
	 */

	@Column(name ="PROCOLO_EXTENTS3",nullable=true,length=32)
	public java.lang.String getProcoloExtents3(){
		return this.procoloExtents3;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  类型扩展字段3
	 */
	public void setProcoloExtents3(java.lang.String procoloExtents3){
		this.procoloExtents3 = procoloExtents3;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  类型扩展字段4
	 */

	@Column(name ="PROCOLO_EXTENTS4",nullable=true,length=32)
	public java.lang.String getProcoloExtents4(){
		return this.procoloExtents4;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  类型扩展字段4
	 */
	public void setProcoloExtents4(java.lang.String procoloExtents4){
		this.procoloExtents4 = procoloExtents4;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  类型扩展字段5
	 */

	@Column(name ="PROCOLO_EXTENTS5",nullable=true,length=32)
	public java.lang.String getProcoloExtents5(){
		return this.procoloExtents5;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  类型扩展字段5
	 */
	public void setProcoloExtents5(java.lang.String procoloExtents5){
		this.procoloExtents5 = procoloExtents5;
	}
}
