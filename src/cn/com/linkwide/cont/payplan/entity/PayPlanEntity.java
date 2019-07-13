package cn.com.linkwide.cont.payplan.entity;

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
 * @Description: 付款计划
 * @author onlineGenerator
 * @date 2018-08-24 12:06:29
 * @version V1.0   
 *
 */
@Entity
@Table(name = "pay_plan", schema = "")
@SuppressWarnings("serial")
public class PayPlanEntity implements java.io.Serializable {
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
	/**合同信息主表*/
	@Excel(name="合同信息主表",width=15)
	private java.lang.String conId;
	/**资金来源*/
	@Excel(name="资金来源",width=15)
	private java.lang.String moneySource;
	/**付款日期*/
	@Excel(name="付款日期",width=15,format = "yyyy-MM-dd")
	private java.util.Date payDate;
	/**付款条件*/
	@Excel(name="付款条件",width=15)
	private java.lang.String payConditions;
	/**付款比例*/
	@Excel(name="付款比例",width=15)
	private java.lang.Double payRate;
	/**付款金额*/
	@Excel(name="付款金额",width=15)
	private java.lang.Double payMoney;
	/**付款说明*/
	@Excel(name="付款说明",width=15)
	private java.lang.String paySm;
	/**累计付款*/
	@Excel(name="累计付款",width=15)
	private java.lang.Double payNumber;
	
	/**付款申请人*/
	@Excel(name="付款申请人",width=15)
	private java.lang.String applyEmp;
	/**申请日期*/
	@Excel(name="申请日期",width=15,format = "yyyy-MM-dd")
	private java.util.Date applyTime;
	/**预付金额*/
	@Excel(name="申请金额",width=15)
	private java.lang.Double applyMoney;
	
	/**是否提交*/
	@Excel(name="是否提交",width=15)
	private java.lang.String isSender;
	@Column(name ="APPLY_EMP",nullable=true,length=50)
	public java.lang.String getApplyEmp() {
		return applyEmp;
	}

	public void setApplyEmp(java.lang.String applyEmp) {
		this.applyEmp = applyEmp;
	}
	@Column(name ="APPLY_TIME",nullable=true,length=50)
	public java.util.Date getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(java.util.Date applyTime) {
		this.applyTime = applyTime;
	}
	@Column(name ="APPLY_MONEY",nullable=true,length=50)
	public java.lang.Double getApplyMoney() {
		return applyMoney;
	}

	public void setApplyMoney(java.lang.Double applyMoney) {
		this.applyMoney = applyMoney;
	}
	@Column(name ="IS_SENDER",nullable=true,length=50)
	public java.lang.String getIsSender() {
		return isSender;
	}

	public void setIsSender(java.lang.String isSender) {
		this.isSender = isSender;
	}

	/**扩展字段1*/
	private java.lang.String extence1;
	/**扩展字段1*/
	private java.lang.String extence2;
	/**扩展字段2*/
	private java.lang.String extence3;
	/**扩展字段3*/
	private java.lang.String extence4;
	/**扩展字段4*/
	private java.lang.String extence5;
	/**扩展字段5*/
	private java.lang.String extence6;
	/**扩展字段6*/
	private java.lang.String extence7;
	/**扩展字段8*/
	private java.lang.String extence8;
	/**扩展字段9*/
	private java.lang.String extence9;
	/**扩展字段10*/
	private java.lang.String extence10;
	
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
	 *@return: java.lang.String  合同信息主表
	 */

	@Column(name ="CON_ID",nullable=true,length=32)
	public java.lang.String getConId(){
		return this.conId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  合同信息主表
	 */
	public void setConId(java.lang.String conId){
		this.conId = conId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  资金来源
	 */

	@Column(name ="MONEY_SOURCE",nullable=true,length=32)
	public java.lang.String getMoneySource(){
		return this.moneySource;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  资金来源
	 */
	public void setMoneySource(java.lang.String moneySource){
		this.moneySource = moneySource;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  付款日期
	 */

	@Column(name ="PAY_DATE",nullable=true,length=32)
	public java.util.Date getPayDate(){
		return this.payDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  付款日期
	 */
	public void setPayDate(java.util.Date payDate){
		this.payDate = payDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  付款条件
	 */

	@Column(name ="PAY_CONDITIONS",nullable=true,length=32)
	public java.lang.String getPayConditions(){
		return this.payConditions;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  付款条件
	 */
	public void setPayConditions(java.lang.String payConditions){
		this.payConditions = payConditions;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  付款比例
	 */

	@Column(name ="PAY_RATE",nullable=true,length=32)
	public java.lang.Double getPayRate(){
		return this.payRate;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  付款比例
	 */
	public void setPayRate(java.lang.Double payRate){
		this.payRate = payRate;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  付款金额
	 */

	@Column(name ="PAY_MONEY",nullable=true,length=32)
	public java.lang.Double getPayMoney(){
		return this.payMoney;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  付款金额
	 */
	public void setPayMoney(java.lang.Double payMoney){
		this.payMoney = payMoney;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  付款说明
	 */

	@Column(name ="PAY_SM",nullable=true,length=100)
	public java.lang.String getPaySm(){
		return this.paySm;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  付款说明
	 */
	public void setPaySm(java.lang.String paySm){
		this.paySm = paySm;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  累计付款
	 */

	@Column(name ="PAY_NUMBER",nullable=true,length=32)
	public java.lang.Double getPayNumber(){
		return this.payNumber;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  累计付款
	 */
	public void setPayNumber(java.lang.Double payNumber){
		this.payNumber = payNumber;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  扩展字段1
	 */

	@Column(name ="EXTENCE1",nullable=true,length=50)
	public java.lang.String getExtence1(){
		return this.extence1;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  扩展字段1
	 */
	public void setExtence1(java.lang.String extence1){
		this.extence1 = extence1;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  扩展字段1
	 */

	@Column(name ="EXTENCE2",nullable=true,length=50)
	public java.lang.String getExtence2(){
		return this.extence2;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  扩展字段1
	 */
	public void setExtence2(java.lang.String extence2){
		this.extence2 = extence2;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  扩展字段2
	 */

	@Column(name ="EXTENCE3",nullable=true,length=50)
	public java.lang.String getExtence3(){
		return this.extence3;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  扩展字段2
	 */
	public void setExtence3(java.lang.String extence3){
		this.extence3 = extence3;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  扩展字段3
	 */

	@Column(name ="EXTENCE4",nullable=true,length=50)
	public java.lang.String getExtence4(){
		return this.extence4;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  扩展字段3
	 */
	public void setExtence4(java.lang.String extence4){
		this.extence4 = extence4;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  扩展字段4
	 */

	@Column(name ="EXTENCE5",nullable=true,length=50)
	public java.lang.String getExtence5(){
		return this.extence5;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  扩展字段4
	 */
	public void setExtence5(java.lang.String extence5){
		this.extence5 = extence5;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  扩展字段5
	 */

	@Column(name ="EXTENCE6",nullable=true,length=50)
	public java.lang.String getExtence6(){
		return this.extence6;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  扩展字段5
	 */
	public void setExtence6(java.lang.String extence6){
		this.extence6 = extence6;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  扩展字段6
	 */

	@Column(name ="EXTENCE7",nullable=true,length=50)
	public java.lang.String getExtence7(){
		return this.extence7;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  扩展字段6
	 */
	public void setExtence7(java.lang.String extence7){
		this.extence7 = extence7;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  扩展字段8
	 */

	@Column(name ="EXTENCE8",nullable=true,length=50)
	public java.lang.String getExtence8(){
		return this.extence8;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  扩展字段8
	 */
	public void setExtence8(java.lang.String extence8){
		this.extence8 = extence8;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  扩展字段9
	 */

	@Column(name ="EXTENCE9",nullable=true,length=50)
	public java.lang.String getExtence9(){
		return this.extence9;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  扩展字段9
	 */
	public void setExtence9(java.lang.String extence9){
		this.extence9 = extence9;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  扩展字段10
	 */

	@Column(name ="EXTENCE10",nullable=true,length=50)
	public java.lang.String getExtence10(){
		return this.extence10;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  扩展字段10
	 */
	public void setExtence10(java.lang.String extence10){
		this.extence10 = extence10;
	}
}
