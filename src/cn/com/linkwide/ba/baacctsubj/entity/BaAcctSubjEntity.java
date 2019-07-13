package cn.com.linkwide.ba.baacctsubj.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

/**   
 * @Title: Entity
 * @Description: 会计科目
 * @author onlineGenerator
 * @date 2018-07-10 14:29:40
 * @version V1.0   
 *
 */
@Entity
@Table(name = "ba_acct_subj", schema = "")
@SuppressWarnings("serial")
public class BaAcctSubjEntity implements java.io.Serializable {
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
	/**年度*/
	@Excel(name="科目年度",width=15)
	private java.lang.String acctYear;
	/**科目编码*/
	@Excel(name="科目编码",width=15)
	private java.lang.String subCode;
	/**科目名称*/
	@Excel(name="科目名称",width=15)
	private java.lang.String subName;
	/**上级科目*/
//	@Excel(name="上级科目",width=15)
	private java.lang.String parentId;
	/**是否末级*/
	@Excel(name="是否末级",width=15)
	private java.lang.String extend1;
	/**余额方向*/
	@Excel(name="余额方向",width=15)
	private java.lang.String balanceDirection;
	/**是否现金科目*/
	@Excel(name="是否现金流量科目",width=15)
	private java.lang.String bcash;
	/**是否部门辅助核算*/
	@Excel(name="是否部门辅助核算",width=15)
	private java.lang.String isDepartCalculate;
	/**是否个人辅助核算*/
	@Excel(name="是否个人辅助核算",width=15)
	private java.lang.String isPersonalCalculate;
	/**是否供应商辅助核算*/
	@Excel(name="是否供应商辅助核算",width=15)
	private java.lang.String isSupplierCalculate;
	/**是否客户辅助核算*/
	@Excel(name="是否客户辅助核算",width=15)
	private java.lang.String isCustomerCalculate;
	/**是否项目辅助核算*/
	@Excel(name="是否项目辅助核算",width=15)
	private java.lang.String isProjectCalculate;
	/**对应项目大类*/
	@Excel(name="对应项目大类",width=15)
	private java.lang.String pacId;
	
	/**资金来源*/
	//@Excel(name="资金来源",width=15)
	private java.lang.String fundSource;
	/**患者ID*/
	//@Excel(name="患者ID",width=15)
	private java.lang.String patientId;
	
	/**扩展字段2   自定义项3*/
	//@Excel(name="扩展字段2",width=15)
	private java.lang.String extend2;
	/**扩展字段3   自定义项8*/
	//@Excel(name="扩展字段3",width=15)
	private java.lang.String extend3;
	/**扩展字段4*/
	//@Excel(name="扩展字段4",width=15)
	private java.lang.String extend4;
	/**扩展字段5*/
	//@Excel(name="扩展字段5",width=15)
	private java.lang.String extend5;
	/**扩展字段6*/
	//@Excel(name="扩展字段6",width=15)
	private java.lang.String extend6;
	/**扩展字段7*/
	//@Excel(name="扩展字段7",width=15)
	private java.lang.String extend7;
	/**扩展字段8*/
	//@Excel(name="扩展字段8",width=15)
	private java.lang.String extend8;
	/**扩展字段9*/
	//@Excel(name="扩展字段9",width=15)
	private java.lang.String extend9;
	/**扩展字段10*/
	//@Excel(name="扩展字段10",width=15)
	private java.lang.String extend10;
	/**助记码*/
	@Excel(name="助记码",width=15)
	private java.lang.String spell;
	/**编码级次*/
	//@Excel(name="编码级次",width=15)
	private java.lang.String cgrade;
	/**是否末级*/
//	@Excel(name="是否末级",width=15)
	private java.lang.String isLast;
	
	/**是否银行科目*/
	//@Excel(name="是否银行科目",width=15)
	private java.lang.String bbank;
	/**组织机构*/
	//@Excel(name="组织机构",width=15)
	private java.lang.String organization;
	/**功能分类科目*/
	//@Excel(name="功能分类科目",width=15)
	private java.lang.String function;
	/**经济分类科目*/
	//@Excel(name="经济分类科目",width=15)
	private java.lang.String enconmic;
	
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
	 *@return: java.lang.String  科目编码
	 */

	@Column(name ="SUB_CODE",nullable=false,length=32)
	public java.lang.String getSubCode(){
		return this.subCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  科目编码
	 */
	public void setSubCode(java.lang.String subCode){
		this.subCode = subCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  科目名称
	 */

	@Column(name ="SUB_NAME",nullable=false,length=32)
	public java.lang.String getSubName(){
		return this.subName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  科目名称
	 */
	public void setSubName(java.lang.String subName){
		this.subName = subName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  上级科目
	 */

	@Column(name ="PARENT_ID",nullable=true,length=32)
	public java.lang.String getParentId(){
		return this.parentId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  上级科目
	 */
	public void setParentId(java.lang.String parentId){
		this.parentId = parentId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否部门辅助核算
	 */

	@Column(name ="IS_DEPART_CALCULATE",nullable=true,length=32)
	public java.lang.String getIsDepartCalculate(){
		return this.isDepartCalculate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否部门辅助核算
	 */
	public void setIsDepartCalculate(java.lang.String isDepartCalculate){
		this.isDepartCalculate = isDepartCalculate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否个人辅助核算
	 */

	@Column(name ="IS_PERSONAL_CALCULATE",nullable=true,length=32)
	public java.lang.String getIsPersonalCalculate(){
		return this.isPersonalCalculate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否个人辅助核算
	 */
	public void setIsPersonalCalculate(java.lang.String isPersonalCalculate){
		this.isPersonalCalculate = isPersonalCalculate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否供应商辅助核算
	 */

	@Column(name ="IS_SUPPLIER_CALCULATE",nullable=true,length=32)
	public java.lang.String getIsSupplierCalculate(){
		return this.isSupplierCalculate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否供应商辅助核算
	 */
	public void setIsSupplierCalculate(java.lang.String isSupplierCalculate){
		this.isSupplierCalculate = isSupplierCalculate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否客户辅助核算
	 */

	@Column(name ="IS_CUSTOMER_CALCULATE",nullable=true,length=32)
	public java.lang.String getIsCustomerCalculate(){
		return this.isCustomerCalculate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否客户辅助核算
	 */
	public void setIsCustomerCalculate(java.lang.String isCustomerCalculate){
		this.isCustomerCalculate = isCustomerCalculate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  余额方向
	 */

	@Column(name ="BALANCE_DIRECTION",nullable=true,length=32)
	public java.lang.String getBalanceDirection(){
		return this.balanceDirection;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  余额方向
	 */
	public void setBalanceDirection(java.lang.String balanceDirection){
		this.balanceDirection = balanceDirection;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否项目辅助核算
	 */

	@Column(name ="IS_PROJECT_CALCULATE",nullable=true,length=32)
	public java.lang.String getIsProjectCalculate(){
		return this.isProjectCalculate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否项目辅助核算
	 */
	public void setIsProjectCalculate(java.lang.String isProjectCalculate){
		this.isProjectCalculate = isProjectCalculate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  对应项目大类
	 */

	@Column(name ="PAC_ID",nullable=true,length=32)
	public java.lang.String getPacId(){
		return this.pacId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  对应项目大类
	 */
	public void setPacId(java.lang.String pacId){
		this.pacId = pacId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  年度
	 */

	@Column(name ="ACCT_YEAR",nullable=true,length=32)
	public java.lang.String getAcctYear(){
		return this.acctYear;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  年度
	 */
	public void setAcctYear(java.lang.String acctYear){
		this.acctYear = acctYear;
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
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  患者ID
	 */

	@Column(name ="PATIENT_ID",nullable=true,length=32)
	public java.lang.String getPatientId(){
		return this.patientId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  患者ID
	 */
	public void setPatientId(java.lang.String patientId){
		this.patientId = patientId;
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
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  扩展字段9
	 */

	@Column(name ="EXTEND9",nullable=true,length=32)
	public java.lang.String getExtend9(){
		return this.extend9;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  扩展字段9
	 */
	public void setExtend9(java.lang.String extend9){
		this.extend9 = extend9;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  扩展字段10
	 */

	@Column(name ="EXTEND10",nullable=true,length=32)
	public java.lang.String getExtend10(){
		return this.extend10;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  扩展字段10
	 */
	public void setExtend10(java.lang.String extend10){
		this.extend10 = extend10;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  助记码
	 */

	@Column(name ="SPELL",nullable=true,length=32)
	public java.lang.String getSpell(){
		return this.spell;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  助记码
	 */
	public void setSpell(java.lang.String spell){
		this.spell = spell;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  编码级次
	 */

	@Column(name ="CGRADE",nullable=true,length=32)
	public java.lang.String getCgrade(){
		return this.cgrade;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  编码级次
	 */
	public void setCgrade(java.lang.String cgrade){
		this.cgrade = cgrade;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否末级
	 */

	@Column(name ="IS_LAST",nullable=true,length=32)
	public java.lang.String getIsLast(){
		return this.isLast;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否末级
	 */
	public void setIsLast(java.lang.String isLast){
		this.isLast = isLast;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否现金科目
	 */

	@Column(name ="BCASH",nullable=true,length=32)
	public java.lang.String getBcash(){
		return this.bcash;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否现金科目
	 */
	public void setBcash(java.lang.String bcash){
		this.bcash = bcash;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否银行科目
	 */

	@Column(name ="BBANK",nullable=true,length=32)
	public java.lang.String getBbank(){
		return this.bbank;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否银行科目
	 */
	public void setBbank(java.lang.String bbank){
		this.bbank = bbank;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  组织机构
	 */

	@Column(name ="ORGANIZATION",nullable=true,length=32)
	public java.lang.String getOrganization(){
		return this.organization;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  组织机构
	 */
	public void setOrganization(java.lang.String organization){
		this.organization = organization;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  功能分类科目
	 */

	@Column(name ="FUNCTION",nullable=true,length=32)
	public java.lang.String getFunction(){
		return this.function;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  功能分类科目
	 */
	public void setFunction(java.lang.String function){
		this.function = function;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  经济分类科目
	 */

	@Column(name ="ENCONMIC",nullable=true,length=32)
	public java.lang.String getEnconmic(){
		return this.enconmic;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  经济分类科目
	 */
	public void setEnconmic(java.lang.String enconmic){
		this.enconmic = enconmic;
	}
}
