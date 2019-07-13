package cn.com.linkwide.cont.conpayapplydetail.entity;
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
 * @Description: 付款申请明细
 * @author onlineGenerator
 * @date 2018-11-26 13:52:13
 * @version V1.0   
 *
 */
@Entity
@Table(name = "con_pay_apply_detail", schema = "")
@SuppressWarnings("serial")
public class ConPayApplyDetailEntity implements java.io.Serializable {
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
	/**主表id*/
	private java.lang.String mianId;
	/**计划id*/
	private java.lang.String planId;
	/**款项类型*/
    @Excel(name="款项类型",width=15)
	private java.lang.String paymentType;
	/**计划付款金额*/
    @Excel(name="计划付款金额",width=15)
	private java.lang.String planMoney;
	/**申请金额*/
    @Excel(name="申请金额",width=15)
	private java.lang.String applyMoney;
	/**扩展字段1*/
	private java.lang.String extendone;
	/**扩展字段2*/
	private java.lang.String extendtwo;
	/**扩展字段3*/
	private java.lang.String extendthree;
	/**扩展字段4*/
	private java.lang.String extendfour;
	/**扩展字段5*/
	private java.lang.String extendfives;
	/**资金来源*/
    @Excel(name="资金来源",width=15)
	private java.lang.String fundsSource;
	/**计划日期*/
    @Excel(name="计划日期",width=15,format = "yyyy-MM-dd")
	private java.util.Date applyDate;
	
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
	 *@return: java.lang.String  主表id
	 */
	
	@Column(name ="MIAN_ID",nullable=true,length=32)
	public java.lang.String getMianId(){
		return this.mianId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  主表id
	 */
	public void setMianId(java.lang.String mianId){
		this.mianId = mianId;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  计划id
	 */
	
	@Column(name ="PLAN_ID",nullable=true,length=32)
	public java.lang.String getPlanId(){
		return this.planId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  计划id
	 */
	public void setPlanId(java.lang.String planId){
		this.planId = planId;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  款项类型
	 */
	
	@Column(name ="PAYMENT_TYPE",nullable=true,length=32)
	public java.lang.String getPaymentType(){
		return this.paymentType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  款项类型
	 */
	public void setPaymentType(java.lang.String paymentType){
		this.paymentType = paymentType;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  计划付款金额
	 */
	
	@Column(name ="PLAN_MONEY",nullable=true,length=32)
	public java.lang.String getPlanMoney(){
		return this.planMoney;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  计划付款金额
	 */
	public void setPlanMoney(java.lang.String planMoney){
		this.planMoney = planMoney;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  申请金额
	 */
	
	@Column(name ="APPLY_MONEY",nullable=true,length=32)
	public java.lang.String getApplyMoney(){
		return this.applyMoney;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  申请金额
	 */
	public void setApplyMoney(java.lang.String applyMoney){
		this.applyMoney = applyMoney;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  扩展字段1
	 */
	
	@Column(name ="EXTENDONE",nullable=true,length=32)
	public java.lang.String getExtendone(){
		return this.extendone;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  扩展字段1
	 */
	public void setExtendone(java.lang.String extendone){
		this.extendone = extendone;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  扩展字段2
	 */
	
	@Column(name ="EXTENDTWO",nullable=true,length=32)
	public java.lang.String getExtendtwo(){
		return this.extendtwo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  扩展字段2
	 */
	public void setExtendtwo(java.lang.String extendtwo){
		this.extendtwo = extendtwo;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  扩展字段3
	 */
	
	@Column(name ="EXTENDTHREE",nullable=true,length=32)
	public java.lang.String getExtendthree(){
		return this.extendthree;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  扩展字段3
	 */
	public void setExtendthree(java.lang.String extendthree){
		this.extendthree = extendthree;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  扩展字段4
	 */
	
	@Column(name ="EXTENDFOUR",nullable=true,length=32)
	public java.lang.String getExtendfour(){
		return this.extendfour;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  扩展字段4
	 */
	public void setExtendfour(java.lang.String extendfour){
		this.extendfour = extendfour;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  扩展字段5
	 */
	
	@Column(name ="EXTENDFIVES",nullable=true,length=32)
	public java.lang.String getExtendfives(){
		return this.extendfives;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  扩展字段5
	 */
	public void setExtendfives(java.lang.String extendfives){
		this.extendfives = extendfives;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  资金来源
	 */
	
	@Column(name ="FUNDS_SOURCE",nullable=true,length=32)
	public java.lang.String getFundsSource(){
		return this.fundsSource;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  资金来源
	 */
	public void setFundsSource(java.lang.String fundsSource){
		this.fundsSource = fundsSource;
	}
	
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  计划日期
	 */
	
	@Column(name ="APPLY_DATE",nullable=true,length=32)
	public java.util.Date getApplyDate(){
		return this.applyDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  计划日期
	 */
	public void setApplyDate(java.util.Date applyDate){
		this.applyDate = applyDate;
	}
	
}
