package cn.com.linkwide.ba.departmate.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

/**   
 * @Title: Entity
 * @Description: 科室物资对照表
 * @author onlineGenerator
 * @date 2018-07-06 11:12:12
 * @version V1.0   
 *
 */
@Entity
@Table(name = "l_ba_depart_mate", schema = "")
@SuppressWarnings("serial")
public class LBaDepartMateEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**创建人登录名称*/
	private java.lang.String createBy;
	/**创建日期*/
	private java.util.Date createDate;
	/**所属部门*/
	private java.lang.String sysOrgCode;
	/**科室*/
	@Excel(name="科室",width=15)
	private java.lang.String departId;
	/**物资*/
	@Excel(name="物资",width=15)
	private java.lang.String mateId;
	private java.lang.String materialCode;
	private java.lang.String materialName;

	/**所属公司*/
/*	private java.lang.String sysCompanyCode;
	@Column(name ="SYS_COMPANY_CODE",nullable=true,length=50)
	public java.lang.String getSysCompanyCode(){
		return this.sysCompanyCode;
	}
	public void setSysCompanyCode(java.lang.String sysCompanyCode){
		this.sysCompanyCode = sysCompanyCode;
	}*/
	
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
	 *@return: java.lang.String  科室
	 */

	@Column(name ="DEPART_ID",nullable=false,length=32)
	public java.lang.String getDepartId(){
		return this.departId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  科室
	 */
	public void setDepartId(java.lang.String departId){
		this.departId = departId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  物资
	 */

	@Column(name ="MATE_ID",nullable=false,length=32)
	public java.lang.String getMateId(){
		return this.mateId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  物资
	 */
	public void setMateId(java.lang.String mateId){
		this.mateId = mateId;
	}
	@Transient
	public java.lang.String getMaterialCode() {
		return materialCode;
	}

	public void setMaterialCode(java.lang.String materialCode) {
		this.materialCode = materialCode;
	}
	@Transient
	public java.lang.String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(java.lang.String materialName) {
		this.materialName = materialName;
	}
}
