package cn.com.linkwide.ba.bavoucher.entity;

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
 * @Description: 凭证表
 * @author onlineGenerator
 * @date 2018-06-26 10:51:01
 * @version V1.0   
 *
 */
@Entity
@Table(name = "ba_voucher", schema = "")
@SuppressWarnings("serial")
public class BaVoucherEntity implements java.io.Serializable {
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
	/**业务模块*/
	@Excel(name="业务模块",width=15)
	private java.lang.String busModel;
	/**业务功能*/
	@Excel(name="业务功能",width=15)
	private java.lang.String busFunction;
	/**单据编号*/
	@Excel(name="单据编号",width=15)
	private java.lang.String billCode;
	/**单据日期*/
	@Excel(name="单据日期",width=15,format = "yyyy-MM-dd")
	private java.util.Date billDate;
	/**凭证摘要*/
	@Excel(name="凭证摘要",width=15)
	private java.lang.String voucSummary;
	/**科目*/
	@Excel(name="科目",width=15)
	private java.lang.String subject;
	/**借贷方向*/
	@Excel(name="借贷方向",width=15)
	private java.lang.String balanceDirection;
	/**金额*/
	@Excel(name="金额",width=15)
	private Double money;
	/**部门*/
	@Excel(name="部门",width=15)
	private java.lang.String deptId;
	/**人员*/
	@Excel(name="人员",width=15)
	private java.lang.String empId;
	/**供应商*/
	@Excel(name="供应商",width=15)
	private java.lang.String vendor;
	/**客户患者*/
	@Excel(name="客户患者",width=15)
	private java.lang.String customer;
	/**项目大类*/
	@Excel(name="项目大类",width=15)
	private java.lang.String projClasses;
	/**项目分类*/
	@Excel(name="项目分类",width=15)
	private java.lang.String projType;
	/**项目*/
	@Excel(name="项目",width=15)
	private java.lang.String project;
	/**业务号*/
	@Excel(name="业务号",width=15)
	private java.lang.String busNum;
	/**财务分类*/
	@Excel(name="财务分类",width=15)
	private java.lang.String financialType;
	/**资金来源*/
	@Excel(name="资金来源",width=15)
	private java.lang.String fundSource;
	
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
	 *@return: java.lang.String  业务模块
	 */

	@Column(name ="BUS_MODEL",nullable=true,length=32)
	public java.lang.String getBusModel(){
		return this.busModel;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  业务模块
	 */
	public void setBusModel(java.lang.String busModel){
		this.busModel = busModel;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  业务功能
	 */

	@Column(name ="BUS_FUNCTION",nullable=true,length=32)
	public java.lang.String getBusFunction(){
		return this.busFunction;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  业务功能
	 */
	public void setBusFunction(java.lang.String busFunction){
		this.busFunction = busFunction;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  单据编号
	 */

	@Column(name ="BILL_CODE",nullable=true,length=32)
	public java.lang.String getBillCode(){
		return this.billCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  单据编号
	 */
	public void setBillCode(java.lang.String billCode){
		this.billCode = billCode;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  单据日期
	 */

	@Column(name ="BILL_DATE",nullable=true,length=32)
	public java.util.Date getBillDate(){
		return this.billDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  单据日期
	 */
	public void setBillDate(java.util.Date billDate){
		this.billDate = billDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  凭证摘要
	 */

	@Column(name ="VOUC_SUMMARY",nullable=true,length=32)
	public java.lang.String getVoucSummary(){
		return this.voucSummary;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  凭证摘要
	 */
	public void setVoucSummary(java.lang.String voucSummary){
		this.voucSummary = voucSummary;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  科目
	 */

	@Column(name ="SUBJECT",nullable=true,length=32)
	public java.lang.String getSubject(){
		return this.subject;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  科目
	 */
	public void setSubject(java.lang.String subject){
		this.subject = subject;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  借贷方向
	 */

	@Column(name ="BALANCE_DIRECTION",nullable=true,length=32)
	public java.lang.String getBalanceDirection(){
		return this.balanceDirection;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  借贷方向
	 */
	public void setBalanceDirection(java.lang.String balanceDirection){
		this.balanceDirection = balanceDirection;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  金额
	 */

	@Column(name ="MONEY",nullable=true,length=32)
	public Double getMoney(){
		return this.money;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  金额
	 */
	public void setMoney(Double money){
		this.money = money;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  部门
	 */

	@Column(name ="DEPT_ID",nullable=true,length=32)
	public java.lang.String getDeptId(){
		return this.deptId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  部门
	 */
	public void setDeptId(java.lang.String deptId){
		this.deptId = deptId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  人员
	 */

	@Column(name ="EMP_ID",nullable=true,length=32)
	public java.lang.String getEmpId(){
		return this.empId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  人员
	 */
	public void setEmpId(java.lang.String empId){
		this.empId = empId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  供应商
	 */

	@Column(name ="VENDOR",nullable=true,length=32)
	public java.lang.String getVendor(){
		return this.vendor;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  供应商
	 */
	public void setVendor(java.lang.String vendor){
		this.vendor = vendor;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  客户患者
	 */

	@Column(name ="CUSTOMER",nullable=true,length=32)
	public java.lang.String getCustomer(){
		return this.customer;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  客户患者
	 */
	public void setCustomer(java.lang.String customer){
		this.customer = customer;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  项目大类
	 */

	@Column(name ="PROJ_CLASSES",nullable=true,length=32)
	public java.lang.String getProjClasses(){
		return this.projClasses;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  项目大类
	 */
	public void setProjClasses(java.lang.String projClasses){
		this.projClasses = projClasses;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  项目分类
	 */

	@Column(name ="PROJ_TYPE",nullable=true,length=32)
	public java.lang.String getProjType(){
		return this.projType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  项目分类
	 */
	public void setProjType(java.lang.String projType){
		this.projType = projType;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  项目
	 */

	@Column(name ="PROJECT",nullable=true,length=32)
	public java.lang.String getProject(){
		return this.project;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  项目
	 */
	public void setProject(java.lang.String project){
		this.project = project;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  业务号
	 */

	@Column(name ="BUS_NUM",nullable=true,length=32)
	public java.lang.String getBusNum(){
		return this.busNum;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  业务号
	 */
	public void setBusNum(java.lang.String busNum){
		this.busNum = busNum;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  财务分类
	 */

	@Column(name ="FINANCIAL_TYPE",nullable=true,length=32)
	public java.lang.String getFinancialType(){
		return this.financialType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  财务分类
	 */
	public void setFinancialType(java.lang.String financialType){
		this.financialType = financialType;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  资金来源
	 */

	@Column(name ="FUND_SOURCE",nullable=true,length=32)
	public java.lang.String getFundSource(){
		return this.fundSource;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  资金来源
	 */
	public void setFundSource(java.lang.String fundSource){
		this.fundSource = fundSource;
	}
}
