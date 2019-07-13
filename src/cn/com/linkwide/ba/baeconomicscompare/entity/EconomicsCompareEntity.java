package cn.com.linkwide.ba.baeconomicscompare.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

/**   
 * @Title: Entity
 * @Description: 经济分类对照关系
 * @author onlineGenerator
 * @date 2018-09-11 18:36:29
 * @version V1.0   
 *
 */
@Entity
@Table(name = "economics_compare", schema = "")
@SuppressWarnings("serial")
public class EconomicsCompareEntity implements java.io.Serializable {
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
	/**预算经济分类编码*/
	@Excel(name="预算经济分类编码",width=22)
	private java.lang.String vcode;
	/**预算经济分类名称*/
	@Excel(name="预算经济分类名称",width=22)
	private java.lang.String vname;
	/**会计科目*/
	@Excel(name="会计科目",width=15)
	private java.lang.String acctSubj;
	/**部门预算经济分类编码*/
	@Excel(name="部门预算经济分类编码",width=25)
	private java.lang.String deptEcCode;
	/**部门预算经济分类名称*/
	@Excel(name="部门预算经济分类名称",width=25)
	private java.lang.String deptEcName;
	/**政府预算经济分类编码*/
	@Excel(name="政府预算经济分类编码",width=25)
	private java.lang.String govCode;
	/**政府预算经济分类名称*/
	@Excel(name="政府预算经济分类名称",width=25)
	private java.lang.String govName;
	/**预留字段1*/
	private java.lang.String extend1;
	/**预留字段2*/
	private java.lang.String extend2;
	/**预留字段3*/
	private java.lang.String extend3;
	/**预留字段4*/
	private java.lang.String extend4;
	/**预留字段5*/
	private java.lang.String extend5;
	/**预留字段6*/
	private java.lang.String extend6;
	/**预留字段7*/
	private java.lang.String extend7;
	/**预留字段8*/
	private java.lang.String extend8;
	/**预留字段9*/
	private java.lang.String extend9;
	/**预留字段10*/
	private java.lang.String extend10;
	
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
	 *@return: java.lang.String  预算经济分类编码
	 */

	@Column(name ="V_CODE",nullable=true,length=32)
	public java.lang.String getVcode() {
		return vcode;
	}

	public void setVcode(java.lang.String vcode) {
		this.vcode = vcode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预算经济分类名称
	 */

	@Column(name ="V_NAME",nullable=true,length=32)
	public java.lang.String getVname() {
		return vname;
	}

	public void setVname(java.lang.String vname) {
		this.vname = vname;
	}

	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  会计科目
	 */

	@Column(name ="ACCT_SUBJ",nullable=true,length=32)
	public java.lang.String getAcctSubj(){
		return this.acctSubj;
	}

	

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  会计科目
	 */
	public void setAcctSubj(java.lang.String acctSubj){
		this.acctSubj = acctSubj;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  部门预算经济分类编码
	 */

	@Column(name ="DEPT_EC_CODE",nullable=true,length=32)
	public java.lang.String getDeptEcCode(){
		return this.deptEcCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  部门预算经济分类编码
	 */
	public void setDeptEcCode(java.lang.String deptEcCode){
		this.deptEcCode = deptEcCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  部门预算经济分类名称
	 */

	@Column(name ="DEPT_EC_NAME",nullable=true,length=32)
	public java.lang.String getDeptEcName(){
		return this.deptEcName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  部门预算经济分类名称
	 */
	public void setDeptEcName(java.lang.String deptEcName){
		this.deptEcName = deptEcName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  政府预算经济分类编码
	 */

	@Column(name ="GOV_CODE",nullable=true,length=32)
	public java.lang.String getGovCode(){
		return this.govCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  政府预算经济分类编码
	 */
	public void setGovCode(java.lang.String govCode){
		this.govCode = govCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  政府预算经济分类名称
	 */

	@Column(name ="GOV_NAME",nullable=true,length=32)
	public java.lang.String getGovName(){
		return this.govName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  政府预算经济分类名称
	 */
	public void setGovName(java.lang.String govName){
		this.govName = govName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预留字段1
	 */

	@Column(name ="EXTEND1",nullable=true,length=255)
	public java.lang.String getExtend1(){
		return this.extend1;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预留字段1
	 */
	public void setExtend1(java.lang.String extend1){
		this.extend1 = extend1;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预留字段2
	 */

	@Column(name ="EXTEND2",nullable=true,length=255)
	public java.lang.String getExtend2(){
		return this.extend2;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预留字段2
	 */
	public void setExtend2(java.lang.String extend2){
		this.extend2 = extend2;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预留字段3
	 */

	@Column(name ="EXTEND3",nullable=true,length=255)
	public java.lang.String getExtend3(){
		return this.extend3;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预留字段3
	 */
	public void setExtend3(java.lang.String extend3){
		this.extend3 = extend3;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预留字段4
	 */

	@Column(name ="EXTEND4",nullable=true,length=255)
	public java.lang.String getExtend4(){
		return this.extend4;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预留字段4
	 */
	public void setExtend4(java.lang.String extend4){
		this.extend4 = extend4;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预留字段5
	 */

	@Column(name ="EXTEND5",nullable=true,length=255)
	public java.lang.String getExtend5(){
		return this.extend5;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预留字段5
	 */
	public void setExtend5(java.lang.String extend5){
		this.extend5 = extend5;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预留字段6
	 */

	@Column(name ="EXTEND6",nullable=true,length=255)
	public java.lang.String getExtend6(){
		return this.extend6;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预留字段6
	 */
	public void setExtend6(java.lang.String extend6){
		this.extend6 = extend6;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预留字段7
	 */

	@Column(name ="EXTEND7",nullable=true,length=255)
	public java.lang.String getExtend7(){
		return this.extend7;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预留字段7
	 */
	public void setExtend7(java.lang.String extend7){
		this.extend7 = extend7;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预留字段8
	 */

	@Column(name ="EXTEND8",nullable=true,length=255)
	public java.lang.String getExtend8(){
		return this.extend8;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预留字段8
	 */
	public void setExtend8(java.lang.String extend8){
		this.extend8 = extend8;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预留字段9
	 */

	@Column(name ="EXTEND9",nullable=true,length=255)
	public java.lang.String getExtend9(){
		return this.extend9;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预留字段9
	 */
	public void setExtend9(java.lang.String extend9){
		this.extend9 = extend9;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预留字段10
	 */

	@Column(name ="EXTEND10",nullable=true,length=255)
	public java.lang.String getExtend10(){
		return this.extend10;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预留字段10
	 */
	public void setExtend10(java.lang.String extend10){
		this.extend10 = extend10;
	}
}
