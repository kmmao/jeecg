package cn.com.linkwide.cont.negotiation.confinalconfirmbill.entity;

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
 * @Description: 终审单
 * @author onlineGenerator
 * @date 2019-04-28 19:18:04
 * @version V1.0   
 *
 */
@Entity
@Table(name = "con_final_confirmBill", schema = "")
@SuppressWarnings("serial")
public class ConFinalConfirmbillEntity implements java.io.Serializable {
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
	/**资产名称*/
	@Excel(name="资产名称",width=15)
	private java.lang.String equName;
	/**资产编码*/
	@Excel(name="资产编码",width=15)
	private java.lang.String equCode;
	/**供应商Id*/
	@Excel(name="供应商Id",width=15)
	private java.lang.String venId;
	/**供应商名称*/
	@Excel(name="供应商名称",width=15)
	private java.lang.String venName;
	/**部门Id*/
	@Excel(name="部门Id",width=15)
	private java.lang.String deptId;
	/**部门名称*/
	@Excel(name="部门名称",width=15)
	private java.lang.String deptName;
	/**规格型号*/
	@Excel(name="规格型号",width=15)
	private java.lang.String specType;
	/**供应商报价*/
	@Excel(name="供应商报价",width=15)
	private java.lang.String offerPrice;
	/**采购价格*/
	@Excel(name="采购价格",width=15)
	private java.lang.String purPrice;
	/**服务内容*/
	@Excel(name="服务内容",width=15)
	private java.lang.String seviceContent;
	/**请示单id*/
	@Excel(name="请示单id",width=15)
	private java.lang.String reqId;
	/**申请单id*/
	@Excel(name="申请单id",width=15)
	private java.lang.String applyId;
	/**报价单id*/
	@Excel(name="报价单id",width=15)
	private java.lang.String offerId;
	/**最终报价*/
	private java.lang.String finalPrice;
	/**最终服务内容*/
	private java.lang.String finalContent;
	/**保修年限*/
	private java.lang.String bxYears;
	/**扩展字段1*/
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
	/**是否提交*/
	private java.lang.String isResend;
	/**是否审核*/
	private java.lang.String isConfirm;
	/**单据状态*/
	private java.lang.String billState;
	/**审核日期*/
	private java.util.Date confirmDate;
	/**提交人*/
	private java.lang.String resendEmp;
	private java.lang.String offerOne;
	
	@Column(name ="OFFER_ONE",nullable=false,length=36)
	public java.lang.String getOfferOne() {
		return offerOne;
	}

	public void setOfferOne(java.lang.String offerOne) {
		this.offerOne = offerOne;
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
	 *@return: java.lang.String  资产名称
	 */

	@Column(name ="EQU_NAME",nullable=true,length=50)
	public java.lang.String getEquName(){
		return this.equName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  资产名称
	 */
	public void setEquName(java.lang.String equName){
		this.equName = equName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  资产编码
	 */

	@Column(name ="EQU_CODE",nullable=true,length=32)
	public java.lang.String getEquCode(){
		return this.equCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  资产编码
	 */
	public void setEquCode(java.lang.String equCode){
		this.equCode = equCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  供应商Id
	 */

	@Column(name ="VEN_ID",nullable=true,length=32)
	public java.lang.String getVenId(){
		return this.venId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  供应商Id
	 */
	public void setVenId(java.lang.String venId){
		this.venId = venId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  供应商名称
	 */

	@Column(name ="VEN_NAME",nullable=true,length=32)
	public java.lang.String getVenName(){
		return this.venName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  供应商名称
	 */
	public void setVenName(java.lang.String venName){
		this.venName = venName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  部门Id
	 */

	@Column(name ="DEPT_ID",nullable=true,length=32)
	public java.lang.String getDeptId(){
		return this.deptId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  部门Id
	 */
	public void setDeptId(java.lang.String deptId){
		this.deptId = deptId;
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
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  规格型号
	 */

	@Column(name ="SPEC_TYPE",nullable=true,length=32)
	public java.lang.String getSpecType(){
		return this.specType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  规格型号
	 */
	public void setSpecType(java.lang.String specType){
		this.specType = specType;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  供应商报价
	 */

	@Column(name ="OFFER_PRICE",nullable=true,length=32)
	public java.lang.String getOfferPrice(){
		return this.offerPrice;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  供应商报价
	 */
	public void setOfferPrice(java.lang.String offerPrice){
		this.offerPrice = offerPrice;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  采购价格
	 */

	@Column(name ="PUR_PRICE",nullable=true,length=32)
	public java.lang.String getPurPrice(){
		return this.purPrice;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  采购价格
	 */
	public void setPurPrice(java.lang.String purPrice){
		this.purPrice = purPrice;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  服务内容
	 */

	@Column(name ="SEVICE_CONTENT",nullable=true,length=255)
	public java.lang.String getSeviceContent(){
		return this.seviceContent;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  服务内容
	 */
	public void setSeviceContent(java.lang.String seviceContent){
		this.seviceContent = seviceContent;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  请示单id
	 */

	@Column(name ="REQ_ID",nullable=true,length=32)
	public java.lang.String getReqId(){
		return this.reqId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  请示单id
	 */
	public void setReqId(java.lang.String reqId){
		this.reqId = reqId;
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
	 *@return: java.lang.String  报价单id
	 */

	@Column(name ="OFFER_ID",nullable=true,length=32)
	public java.lang.String getOfferId(){
		return this.offerId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  报价单id
	 */
	public void setOfferId(java.lang.String offerId){
		this.offerId = offerId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  最终报价
	 */

	@Column(name ="FINAL_PRICE",nullable=true,length=32)
	public java.lang.String getFinalPrice(){
		return this.finalPrice;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  最终报价
	 */
	public void setFinalPrice(java.lang.String finalPrice){
		this.finalPrice = finalPrice;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  最终服务内容
	 */

	@Column(name ="FINAL_CONTENT",nullable=true,length=32)
	public java.lang.String getFinalContent(){
		return this.finalContent;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  最终服务内容
	 */
	public void setFinalContent(java.lang.String finalContent){
		this.finalContent = finalContent;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  保修年限
	 */

	@Column(name ="BX_YEARS",nullable=true,length=32)
	public java.lang.String getBxYears(){
		return this.bxYears;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  保修年限
	 */
	public void setBxYears(java.lang.String bxYears){
		this.bxYears = bxYears;
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
	 *@return: java.lang.String  是否提交
	 */

	@Column(name ="IS_RESEND",nullable=true,length=32)
	public java.lang.String getIsResend(){
		return this.isResend;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否提交
	 */
	public void setIsResend(java.lang.String isResend){
		this.isResend = isResend;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否审核
	 */

	@Column(name ="IS_CONFIRM",nullable=true,length=32)
	public java.lang.String getIsConfirm(){
		return this.isConfirm;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否审核
	 */
	public void setIsConfirm(java.lang.String isConfirm){
		this.isConfirm = isConfirm;
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
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  审核日期
	 */

	@Column(name ="CONFIRM_DATE",nullable=true,length=32)
	public java.util.Date getConfirmDate(){
		return this.confirmDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  审核日期
	 */
	public void setConfirmDate(java.util.Date confirmDate){
		this.confirmDate = confirmDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  提交人
	 */

	@Column(name ="RESEND_EMP",nullable=true,length=32)
	public java.lang.String getResendEmp(){
		return this.resendEmp;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  提交人
	 */
	public void setResendEmp(java.lang.String resendEmp){
		this.resendEmp = resendEmp;
	}
}
