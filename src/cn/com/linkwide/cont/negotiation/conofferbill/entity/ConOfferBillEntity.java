package cn.com.linkwide.cont.negotiation.conofferbill.entity;

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
 * @Description: 云平台报价单
 * @author onlineGenerator
 * @date 2019-04-20 14:11:13
 * @version V1.0   
 *
 */
@Entity
@Table(name = "con_offer_bill", schema = "")
@SuppressWarnings("serial")
public class ConOfferBillEntity implements java.io.Serializable {
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
	/**部门编码*/
	private java.lang.String deptCode;
	/**部门名称*/
	private java.lang.String deptName;
	/**申请时间*/
	private java.util.Date applyDate;
	/**设备编码*/
	private java.lang.String equCode;
	/**设备名称*/
	@Excel(name="设备名称",width=15)
	private java.lang.String equName;
	/**资产卡片*/
	private java.lang.String equCard;
	/**规格型号*/
	@Excel(name="规格型号",width=15)
	private java.lang.String specTyp;
	/**申请单号*/
	private java.lang.String applyCode;
	/**保修年限*/
	@Excel(name="保修年限",width=15)
	private java.lang.String warrantyYears;
	/**保修类型*/
	@Excel(name="保修类型",width=15)
	private java.lang.String warrantyType;
	/**单价*/
	@Excel(name="单价",width=15)
	private java.lang.String offerPrice;
	/**服务内容*/
	@Excel(name="服务内容",width=15)
	private java.lang.String serviceConten;
	/**费用占比*/
	@Excel(name="费用占比",width=15)
	private java.lang.String costRatio;
	/**单据状态*/
	private java.lang.String billState;
	/**申请单id*/
	private java.lang.String applyId;
	/**申请单id*/
	private java.util.Date purDate;
	
	/**申请单id*/
	private java.lang.String birthFactory;
	private java.lang.String isConfirm;
	
	
	
	@Column(name ="IS_CONFIRM",nullable=true,length=50)
	public java.lang.String getIsConfirm() {
		return isConfirm;
	}

	public void setIsConfirm(java.lang.String isConfirm) {
		this.isConfirm = isConfirm;
	}

	@Column(name ="PUR_DATE",nullable=true,length=50)
	public java.util.Date getPurDate() {
		return purDate;
	}

	public void setPurDate(java.util.Date purDate) {
		this.purDate = purDate;
	}
	@Column(name ="BIRTH_FACTORY",nullable=true,length=50)
	public java.lang.String getBirthFactory() {
		return birthFactory;
	}

	public void setBirthFactory(java.lang.String birthFactory) {
		this.birthFactory = birthFactory;
	}
	/**金额*/
	@Excel(name="金额",width=15)
	private java.lang.String offerMoney;
	/**确认日期*/
	@Excel(name="确认日期",width=15,format = "yyyy-MM-dd")
	private java.util.Date confirmDate;
	/**合同状态*/
	private java.lang.String conState;
	/*已占用是否合格*/
	private java.lang.String extend1;
	/**扩展字段2*/
	private java.lang.String extend2;
	/**扩展字段3*/
	private java.lang.String extend3;
	/**扩展字段4*/
	private java.lang.String extend4;
	/**扩展字段5*/
	private java.lang.String extend5;
	/**扩展字段6*/
	private java.lang.String extend6;
	/**扩展字段7*/
	private java.lang.String extend7;
	/**扩展字段8*/
	private java.lang.String extend8;
	
	private java.lang.String offerOne;
	private java.lang.String offerTwo;
	private java.lang.String offerThree;
	private java.lang.String offerFour;
	private java.lang.String offerFive;
	private java.lang.String offerSix;
	private java.lang.String offerSeven;
	private java.lang.String offerEight;
	private java.lang.String offerNine;
	private java.lang.String offerTen;
	
	
	private java.lang.Integer offerTimes;
	private java.lang.String workflowId;
	private java.lang.String confermState;
	
	@Column(name ="WORKFLOW_ID",nullable=true,length=10)
	public java.lang.String getWorkflowId() {
		return workflowId;
	}

	public void setWorkflowId(java.lang.String workflowId) {
		this.workflowId = workflowId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  审批状态
	 */
	
	@Column(name ="CONFERM_STATE",nullable=true,length=32)
	public java.lang.String getConfermState(){
		return this.confermState;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  审批状态
	 */
	public void setConfermState(java.lang.String confermState){
		this.confermState = confermState;
	}
	
	@Column(name ="OFFER_TIMES",nullable=true,length=50)
	public java.lang.Integer getOfferTimes() {
		return offerTimes;
	}

	public void setOfferTimes(java.lang.Integer offerTimes) {
		this.offerTimes = offerTimes;
	}
	
	
	
	
	
	
	
	
	@Column(name ="OFFER_ONE",nullable=true,length=50)
	public java.lang.String getOfferOne() {
		return offerOne;
	}

	public void setOfferOne(java.lang.String offerOne) {
		this.offerOne = offerOne;
	}
	@Column(name ="OFFER_TWO",nullable=true,length=50)
	public java.lang.String getOfferTwo() {
		return offerTwo;
	}

	public void setOfferTwo(java.lang.String offerTwo) {
		this.offerTwo = offerTwo;
	}
	@Column(name ="OFFER_THREE",nullable=true,length=50)
	public java.lang.String getOfferThree() {
		return offerThree;
	}

	public void setOfferThree(java.lang.String offerThree) {
		this.offerThree = offerThree;
	}
	@Column(name ="OFFER_FOUR",nullable=true,length=50)
	public java.lang.String getOfferFour() {
		return offerFour;
	}

	public void setOfferFour(java.lang.String offerFour) {
		this.offerFour = offerFour;
	}
	@Column(name ="OFFER_FIVE",nullable=true,length=50)
	public java.lang.String getOfferFive() {
		return offerFive;
	}

	public void setOfferFive(java.lang.String offerFive) {
		this.offerFive = offerFive;
	}
	@Column(name ="OFFER_SIX",nullable=true,length=50)
	public java.lang.String getOfferSix() {
		return offerSix;
	}

	public void setOfferSix(java.lang.String offerSix) {
		this.offerSix = offerSix;
	}
	@Column(name ="OFFER_SEVEN",nullable=true,length=50)
	public java.lang.String getOfferSeven() {
		return offerSeven;
	}

	public void setOfferSeven(java.lang.String offerSeven) {
		this.offerSeven = offerSeven;
	}
	@Column(name ="OFFER_EIGHT",nullable=true,length=50)
	public java.lang.String getOfferEight() {
		return offerEight;
	}

	public void setOfferEight(java.lang.String offerEight) {
		this.offerEight = offerEight;
	}
	@Column(name ="OFFER_NINE",nullable=true,length=50)
	public java.lang.String getOfferNine() {
		return offerNine;
	}

	public void setOfferNine(java.lang.String offerNine) {
		this.offerNine = offerNine;
	}
	@Column(name ="OFFER_TEN",nullable=true,length=50)
	public java.lang.String getOfferTen() {
		return offerTen;
	}

	public void setOfferTen(java.lang.String offerTen) {
		this.offerTen = offerTen;
	}

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
	 *@return: java.lang.String  部门编码
	 */

	@Column(name ="DEPT_CODE",nullable=true,length=32)
	public java.lang.String getDeptCode(){
		return this.deptCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  部门编码
	 */
	public void setDeptCode(java.lang.String deptCode){
		this.deptCode = deptCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  部门名称
	 */

	@Column(name ="DEPT_NAME",nullable=true,length=32)
	public java.lang.String getDeptName(){
		return this.deptName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  部门名称
	 */
	public void setDeptName(java.lang.String deptName){
		this.deptName = deptName;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  申请时间
	 */

	@Column(name ="APPLY_DATE",nullable=true,length=32)
	public java.util.Date getApplyDate(){
		return this.applyDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  申请时间
	 */
	public void setApplyDate(java.util.Date applyDate){
		this.applyDate = applyDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  设备编码
	 */

	@Column(name ="EQU_CODE",nullable=true,length=32)
	public java.lang.String getEquCode(){
		return this.equCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  设备编码
	 */
	public void setEquCode(java.lang.String equCode){
		this.equCode = equCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  设备名称
	 */

	@Column(name ="EQU_NAME",nullable=true,length=32)
	public java.lang.String getEquName(){
		return this.equName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  设备名称
	 */
	public void setEquName(java.lang.String equName){
		this.equName = equName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  资产卡片
	 */

	@Column(name ="EQU_CARD",nullable=true,length=32)
	public java.lang.String getEquCard(){
		return this.equCard;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  资产卡片
	 */
	public void setEquCard(java.lang.String equCard){
		this.equCard = equCard;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  规格型号
	 */

	@Column(name ="SPEC_TYP",nullable=true,length=32)
	public java.lang.String getSpecTyp(){
		return this.specTyp;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  规格型号
	 */
	public void setSpecTyp(java.lang.String specTyp){
		this.specTyp = specTyp;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  申请单号
	 */

	@Column(name ="APPLY_CODE",nullable=true,length=32)
	public java.lang.String getApplyCode(){
		return this.applyCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  申请单号
	 */
	public void setApplyCode(java.lang.String applyCode){
		this.applyCode = applyCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  保修年限
	 */

	@Column(name ="WARRANTY_YEARS",nullable=true,length=32)
	public java.lang.String getWarrantyYears(){
		return this.warrantyYears;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  保修年限
	 */
	public void setWarrantyYears(java.lang.String warrantyYears){
		this.warrantyYears = warrantyYears;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  保修类型
	 */

	@Column(name ="WARRANTY_TYPE",nullable=true,length=32)
	public java.lang.String getWarrantyType(){
		return this.warrantyType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  保修类型
	 */
	public void setWarrantyType(java.lang.String warrantyType){
		this.warrantyType = warrantyType;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  单价
	 */

	@Column(name ="OFFER_PRICE",nullable=true,length=32)
	public java.lang.String getOfferPrice(){
		return this.offerPrice;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  单价
	 */
	public void setOfferPrice(java.lang.String offerPrice){
		this.offerPrice = offerPrice;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  服务内容
	 */

	@Column(name ="SERVICE_CONTEN",nullable=true,length=500)
	public java.lang.String getServiceConten(){
		return this.serviceConten;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  服务内容
	 */
	public void setServiceConten(java.lang.String serviceConten){
		this.serviceConten = serviceConten;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  费用占比
	 */

	@Column(name ="COST_RATIO",nullable=true,length=500)
	public java.lang.String getCostRatio(){
		return this.costRatio;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  费用占比
	 */
	public void setCostRatio(java.lang.String costRatio){
		this.costRatio = costRatio;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  单据状态
	 */

	@Column(name ="BILL_STATE",nullable=true,length=32)
	public java.lang.String getBillState(){
		return this.billState;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  单据状态
	 */
	public void setBillState(java.lang.String billState){
		this.billState = billState;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  申请单id
	 */

	@Column(name ="APPLY_ID",nullable=true,length=32)
	public java.lang.String getApplyId(){
		return this.applyId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  申请单id
	 */
	public void setApplyId(java.lang.String applyId){
		this.applyId = applyId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  金额
	 */

	@Column(name ="OFFER_MONEY",nullable=true,length=32)
	public java.lang.String getOfferMoney(){
		return this.offerMoney;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  金额
	 */
	public void setOfferMoney(java.lang.String offerMoney){
		this.offerMoney = offerMoney;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  确认日期
	 */

	@Column(name ="CONFIRM_DATE",nullable=true,length=32)
	public java.util.Date getConfirmDate(){
		return this.confirmDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  确认日期
	 */
	public void setConfirmDate(java.util.Date confirmDate){
		this.confirmDate = confirmDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  合同状态
	 */

	@Column(name ="CON_STATE",nullable=true,length=32)
	public java.lang.String getConState(){
		return this.conState;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  合同状态
	 */
	public void setConState(java.lang.String conState){
		this.conState = conState;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  扩展字段1
	 */

	@Column(name ="EXTEND1",nullable=true,length=32)
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

	@Column(name ="EXTEND2",nullable=true,length=32)
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

	@Column(name ="EXTEND3",nullable=true,length=32)
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

	@Column(name ="EXTEND4",nullable=true,length=32)
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

	@Column(name ="EXTEND5",nullable=true,length=32)
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
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  扩展字段6
	 */

	@Column(name ="EXTEND6",nullable=true,length=32)
	public java.lang.String getExtend6(){
		return this.extend6;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  扩展字段6
	 */
	public void setExtend6(java.lang.String extend6){
		this.extend6 = extend6;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  扩展字段7
	 */

	@Column(name ="EXTEND7",nullable=true,length=32)
	public java.lang.String getExtend7(){
		return this.extend7;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  扩展字段7
	 */
	public void setExtend7(java.lang.String extend7){
		this.extend7 = extend7;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  扩展字段8
	 */

	@Column(name ="EXTEND8",nullable=true,length=32)
	public java.lang.String getExtend8(){
		return this.extend8;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  扩展字段8
	 */
	public void setExtend8(java.lang.String extend8){
		this.extend8 = extend8;
	}
}
