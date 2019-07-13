package cn.com.linkwide.ba.ptbdyxjie.entity;

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

/**   
 * @Title: Entity
 * @Description: MS025科室字典数据更新通知服务
 * @author onlineGenerator
 * @date 2019-05-13 14:56:05
 * @version V1.0   
 *
 */
@Entity
@Table(name = "pt_bdyx", schema = "")
@SuppressWarnings("serial")
public class PtBdyxEntity implements java.io.Serializable {
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
	/**科室编码*/
	@Excel(name="科室编码",width=15)
	private java.lang.String code;
	/**科室名称*/
	@Excel(name="科室名称",width=15)
	private java.lang.String name;
	/**停用标志*/
	@Excel(name="停用标志",width=15)
	private java.lang.String stopflag;
	/**拼音码*/
	@Excel(name="拼音码",width=15)
	private java.lang.String pycode;
	/**上级机构编码*/
	@Excel(name="上级机构编码",width=15)
	private java.lang.String upid;
	/**组织机构代码*/
	@Excel(name="组织机构代码",width=15)
	private java.lang.String orgcode;
	/**机构地址*/
	@Excel(name="机构地址",width=15)
	private java.lang.String orgaddress;
	/**科室位置*/
	@Excel(name="科室位置",width=15)
	private java.lang.String deptlocation;
	/**科室国际编码*/
	@Excel(name="科室国际编码",width=15)
	private java.lang.String deptinternationalcode;
	/**科室HIS编码*/
	@Excel(name="科室HIS编码",width=15)
	private java.lang.String depthiscode;
	/**科室HIS名称*/
	@Excel(name="科室HIS名称",width=15)
	private java.lang.String depthisname;
	/**预留字段1*/
	@Excel(name="预留字段1",width=15)
	private java.lang.String reservedfields1;
	/**预留字段2*/
	@Excel(name="预留字段2",width=15)
	private java.lang.String reservedfields2;
	/**操作类型*/
	@Excel(name="操作类型",width=15)
	private java.lang.String actiontype;
	
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
	 *@return: java.lang.String  科室编码
	 */

	@Column(name ="CODE",nullable=true,length=32)
	public java.lang.String getCode(){
		return this.code;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  科室编码
	 */
	public void setCode(java.lang.String code){
		this.code = code;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  科室名称
	 */

	@Column(name ="NAME",nullable=true,length=32)
	public java.lang.String getName(){
		return this.name;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  科室名称
	 */
	public void setName(java.lang.String name){
		this.name = name;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  停用标志
	 */

	@Column(name ="STOPFLAG",nullable=true,length=32)
	public java.lang.String getStopflag(){
		return this.stopflag;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  停用标志
	 */
	public void setStopflag(java.lang.String stopflag){
		this.stopflag = stopflag;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  拼音码
	 */

	@Column(name ="PYCODE",nullable=true,length=32)
	public java.lang.String getPycode(){
		return this.pycode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  拼音码
	 */
	public void setPycode(java.lang.String pycode){
		this.pycode = pycode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  上级机构编码
	 */

	@Column(name ="UPID",nullable=true,length=32)
	public java.lang.String getUpid(){
		return this.upid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  上级机构编码
	 */
	public void setUpid(java.lang.String upid){
		this.upid = upid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  组织机构代码
	 */

	@Column(name ="ORGCODE",nullable=true,length=32)
	public java.lang.String getOrgcode(){
		return this.orgcode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  组织机构代码
	 */
	public void setOrgcode(java.lang.String orgcode){
		this.orgcode = orgcode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  机构地址
	 */

	@Column(name ="ORGADDRESS",nullable=true,length=32)
	public java.lang.String getOrgaddress(){
		return this.orgaddress;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  机构地址
	 */
	public void setOrgaddress(java.lang.String orgaddress){
		this.orgaddress = orgaddress;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  科室位置
	 */

	@Column(name ="DEPTLOCATION",nullable=true,length=32)
	public java.lang.String getDeptlocation(){
		return this.deptlocation;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  科室位置
	 */
	public void setDeptlocation(java.lang.String deptlocation){
		this.deptlocation = deptlocation;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  科室国际编码
	 */

	@Column(name ="DEPTINTERNATIONALCODE",nullable=true,length=32)
	public java.lang.String getDeptinternationalcode(){
		return this.deptinternationalcode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  科室国际编码
	 */
	public void setDeptinternationalcode(java.lang.String deptinternationalcode){
		this.deptinternationalcode = deptinternationalcode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  科室HIS编码
	 */

	@Column(name ="DEPTHISCODE",nullable=true,length=32)
	public java.lang.String getDepthiscode(){
		return this.depthiscode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  科室HIS编码
	 */
	public void setDepthiscode(java.lang.String depthiscode){
		this.depthiscode = depthiscode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  科室HIS名称
	 */

	@Column(name ="DEPTHISNAME",nullable=true,length=32)
	public java.lang.String getDepthisname(){
		return this.depthisname;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  科室HIS名称
	 */
	public void setDepthisname(java.lang.String depthisname){
		this.depthisname = depthisname;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预留字段1
	 */

	@Column(name ="RESERVEDFIELDS1",nullable=true,length=32)
	public java.lang.String getReservedfields1(){
		return this.reservedfields1;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预留字段1
	 */
	public void setReservedfields1(java.lang.String reservedfields1){
		this.reservedfields1 = reservedfields1;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预留字段2
	 */

	@Column(name ="RESERVEDFIELDS2",nullable=true,length=32)
	public java.lang.String getReservedfields2(){
		return this.reservedfields2;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预留字段2
	 */
	public void setReservedfields2(java.lang.String reservedfields2){
		this.reservedfields2 = reservedfields2;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  操作类型
	 */

	@Column(name ="ACTIONTYPE",nullable=true,length=32)
	public java.lang.String getActiontype(){
		return this.actiontype;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  操作类型
	 */
	public void setActiontype(java.lang.String actiontype){
		this.actiontype = actiontype;
	}
}
