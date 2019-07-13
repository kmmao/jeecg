package cn.com.linkwide.ba.bacustomer.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

/**   
 * @Title: Entity
 * @Description: 客户档案
 * @author onlineGenerator
 * @date 2018-08-03 13:27:31
 * @version V1.0   
 *
 */
@Entity
@Table(name = "ba_customer", schema = "")
@SuppressWarnings("serial")
public class BaCustomerEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**客户编码*/
	@Excel(name="客户编码",width=15)
	private java.lang.String customerCode;
	/**客户名称*/
	@Excel(name="客户名称",width=15)
	private java.lang.String customerFullName;
	/**客户简称*/
	@Excel(name="客户简称",width=15)
	private java.lang.String customerShortName;
	/**客户分类*/
	@Excel(name="客户分类",width=15)
	private java.lang.String customerTypeId;
	/**所属类型*/
	@Excel(name="所属类型",width=15)
	private java.lang.String extend1;
	/**联系人*/
	@Excel(name="联系人",width=15)
	private java.lang.String contacts;
	/**联系电话*/
	@Excel(name="联系电话",width=15)
	private java.lang.String telphone;
	/**地址*/
	@Excel(name="地址",width=15)
	private java.lang.String address;
	/**创建人登录名称*/
	private java.lang.String createBy;
	/**创建日期*/
	private java.util.Date createDate;
	/**更新人登录名称*/
	private java.lang.String updateBy;
	/**更新日期*/
	private java.util.Date updateDate;
	/**统一社会信用代码*/
	@Excel(name="统一社会信用代码",width=15)
	private java.lang.String uscc;
	/**审批人*/
	private java.lang.String auditor;
	/**所属银行*/
	@Excel(name="所属银行",width=15)
	private java.lang.String affiliatedBank;
	/**创建人名称*/
	private java.lang.String createName;
	/**所属公司*/
	private java.lang.String sysCompanyCode;
	/**法人*/
	@Excel(name="法人",width=15)
	private java.lang.String legalPerson;
	/**停用人*/
	private java.lang.String ceasePeople;
	/**流程状态*/
	private java.lang.String bpmStatus;
	/**所属部门*/
	private java.lang.String sysOrgCode;
	/**助记码*/
	@Excel(name="助记码",width=15)
	private java.lang.String mnemonicCode;
	/**注册资金*/
	@Excel(name="注册资金",width=15)
	private java.lang.String registerCapital;
	/**默认交易币种*/
	@Excel(name="默认交易币种",width=15)
	private java.lang.String defaultCurrency;
	/**是否停用*/
	@Excel(name="是否停用",width=15)
	private java.lang.String isCease;
	/**首付款协议*/
	@Excel(name="首付款协议",width=15)
	private java.lang.String paymentReceivedProtocol;
	/**专管业务员*/
	@Excel(name="专管业务员",width=15)
	private java.lang.String superviseSalesman;
	/**公司地址*/
	@Excel(name="公司地址",width=15)
	private java.lang.String corporationAddress;
	/**网址*/
	@Excel(name="网址",width=15)
	private java.lang.String website;
	/**营业执照有效日期至*/
	@Excel(name="营业执照有效日期至",width=15,format = "yyyy-MM-dd")
	private java.util.Date vld;
	/**停用时间*/
	private java.lang.String ceaseDate;
	/**审批状态*/
	private java.lang.String auditingState;
	/**银行账号*/
	@Excel(name="银行账号",width=15)
	private java.lang.String bankCode;
	/**公司电话*/
	@Excel(name="公司电话",width=15)
	private java.lang.String corporationPhone;
	/**审批时间*/
	private java.lang.String auditingDate;
	/**营业执照发证日期*/
	@Excel(name="营业执照发证日期",width=15,format = "yyyy-MM-dd")
	private java.util.Date grantDate;
	/**更新人名称*/
	private java.lang.String updateName;
	/**开户银行*/
	@Excel(name="开户银行",width=15)
	private java.lang.String oaaBank;
	/**所属地区*/
	@Excel(name="所属地区",width=15)
	private java.lang.String affiliatedArea;
	/**创建部门*/
	private java.lang.String departId;
	
	/**扩展字段2*/
	private java.lang.String extend2;
	/**扩展字段3*/
	private java.lang.String extend3;
	/**扩展字段4*/
	private java.lang.String extend4;
	/**扩展字段5*/
	private java.lang.String extend5;
	
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
	 *@return: java.lang.String  客户编码
	 */

	@Column(name ="CUSTOMER_CODE",nullable=true,length=32)
	public java.lang.String getCustomerCode(){
		return this.customerCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  客户编码
	 */
	public void setCustomerCode(java.lang.String customerCode){
		this.customerCode = customerCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  客户名称
	 */

	@Column(name ="CUSTOMER_FULL_NAME",nullable=true,length=32)
	public java.lang.String getCustomerFullName(){
		return this.customerFullName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  客户名称
	 */
	public void setCustomerFullName(java.lang.String customerFullName){
		this.customerFullName = customerFullName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  客户简称
	 */

	@Column(name ="CUSTOMER_SHORT_NAME",nullable=true,length=32)
	public java.lang.String getCustomerShortName(){
		return this.customerShortName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  客户简称
	 */
	public void setCustomerShortName(java.lang.String customerShortName){
		this.customerShortName = customerShortName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  客户分类
	 */

	@Column(name ="CUSTOMER_TYPE_ID",nullable=true,length=32)
	public java.lang.String getCustomerTypeId(){
		return this.customerTypeId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  客户分类
	 */
	public void setCustomerTypeId(java.lang.String customerTypeId){
		this.customerTypeId = customerTypeId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  联系人
	 */

	@Column(name ="CONTACTS",nullable=true,length=32)
	public java.lang.String getContacts(){
		return this.contacts;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  联系人
	 */
	public void setContacts(java.lang.String contacts){
		this.contacts = contacts;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  联系电话
	 */

	@Column(name ="TELPHONE",nullable=true,length=32)
	public java.lang.String getTelphone(){
		return this.telphone;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  联系电话
	 */
	public void setTelphone(java.lang.String telphone){
		this.telphone = telphone;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  地址
	 */

	@Column(name ="ADDRESS",nullable=true,length=32)
	public java.lang.String getAddress(){
		return this.address;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  地址
	 */
	public void setAddress(java.lang.String address){
		this.address = address;
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

	@Column(name ="CREATE_DATE",nullable=true)
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

	@Column(name ="UPDATE_DATE",nullable=true)
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
	 *@return: java.lang.String  统一社会信用代码
	 */

	@Column(name ="USCC",nullable=true,length=32)
	public java.lang.String getUscc(){
		return this.uscc;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  统一社会信用代码
	 */
	public void setUscc(java.lang.String uscc){
		this.uscc = uscc;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  审批人
	 */

	@Column(name ="AUDITOR",nullable=true,length=32)
	public java.lang.String getAuditor(){
		return this.auditor;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  审批人
	 */
	public void setAuditor(java.lang.String auditor){
		this.auditor = auditor;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  所属银行
	 */

	@Column(name ="AFFILIATED_BANK",nullable=true,length=32)
	public java.lang.String getAffiliatedBank(){
		return this.affiliatedBank;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  所属银行
	 */
	public void setAffiliatedBank(java.lang.String affiliatedBank){
		this.affiliatedBank = affiliatedBank;
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
	 *@return: java.lang.String  法人
	 */

	@Column(name ="LEGAL_PERSON",nullable=true,length=32)
	public java.lang.String getLegalPerson(){
		return this.legalPerson;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  法人
	 */
	public void setLegalPerson(java.lang.String legalPerson){
		this.legalPerson = legalPerson;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  停用人
	 */

	@Column(name ="CEASE_PEOPLE",nullable=true,length=32)
	public java.lang.String getCeasePeople(){
		return this.ceasePeople;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  停用人
	 */
	public void setCeasePeople(java.lang.String ceasePeople){
		this.ceasePeople = ceasePeople;
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
	 *@return: java.lang.String  助记码
	 */

	@Column(name ="MNEMONIC_CODE",nullable=true,length=32)
	public java.lang.String getMnemonicCode(){
		return this.mnemonicCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  助记码
	 */
	public void setMnemonicCode(java.lang.String mnemonicCode){
		this.mnemonicCode = mnemonicCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  注册资金
	 */

	@Column(name ="REGISTER_CAPITAL",nullable=true,length=32)
	public java.lang.String getRegisterCapital(){
		return this.registerCapital;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  注册资金
	 */
	public void setRegisterCapital(java.lang.String registerCapital){
		this.registerCapital = registerCapital;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  默认交易币种
	 */

	@Column(name ="DEFAULT_CURRENCY",nullable=true,length=32)
	public java.lang.String getDefaultCurrency(){
		return this.defaultCurrency;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  默认交易币种
	 */
	public void setDefaultCurrency(java.lang.String defaultCurrency){
		this.defaultCurrency = defaultCurrency;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否停用
	 */

	@Column(name ="IS_CEASE",nullable=true,length=32)
	public java.lang.String getIsCease(){
		return this.isCease;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否停用
	 */
	public void setIsCease(java.lang.String isCease){
		this.isCease = isCease;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  首付款协议
	 */

	@Column(name ="PAYMENT_RECEIVED_PROTOCOL",nullable=true,length=32)
	public java.lang.String getPaymentReceivedProtocol(){
		return this.paymentReceivedProtocol;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  首付款协议
	 */
	public void setPaymentReceivedProtocol(java.lang.String paymentReceivedProtocol){
		this.paymentReceivedProtocol = paymentReceivedProtocol;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  专管业务员
	 */

	@Column(name ="SUPERVISE_SALESMAN",nullable=true,length=32)
	public java.lang.String getSuperviseSalesman(){
		return this.superviseSalesman;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  专管业务员
	 */
	public void setSuperviseSalesman(java.lang.String superviseSalesman){
		this.superviseSalesman = superviseSalesman;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  公司地址
	 */

	@Column(name ="CORPORATION_ADDRESS",nullable=true,length=32)
	public java.lang.String getCorporationAddress(){
		return this.corporationAddress;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  公司地址
	 */
	public void setCorporationAddress(java.lang.String corporationAddress){
		this.corporationAddress = corporationAddress;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  网址
	 */

	@Column(name ="WEBSITE",nullable=true,length=32)
	public java.lang.String getWebsite(){
		return this.website;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  网址
	 */
	public void setWebsite(java.lang.String website){
		this.website = website;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  营业执照有效日期至
	 */

	@Column(name ="VLD",nullable=true)
	public java.util.Date getVld(){
		return this.vld;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  营业执照有效日期至
	 */
	public void setVld(java.util.Date vld){
		this.vld = vld;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  停用时间
	 */

	@Column(name ="CEASE_DATE",nullable=true,length=32)
	public java.lang.String getCeaseDate(){
		return this.ceaseDate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  停用时间
	 */
	public void setCeaseDate(java.lang.String ceaseDate){
		this.ceaseDate = ceaseDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  审批状态
	 */

	@Column(name ="AUDITING_STATE",nullable=true,length=32)
	public java.lang.String getAuditingState(){
		return this.auditingState;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  审批状态
	 */
	public void setAuditingState(java.lang.String auditingState){
		this.auditingState = auditingState;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  银行账号
	 */

	@Column(name ="BANK_CODE",nullable=true,length=32)
	public java.lang.String getBankCode(){
		return this.bankCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  银行账号
	 */
	public void setBankCode(java.lang.String bankCode){
		this.bankCode = bankCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  公司电话
	 */

	@Column(name ="CORPORATION_PHONE",nullable=true,length=32)
	public java.lang.String getCorporationPhone(){
		return this.corporationPhone;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  公司电话
	 */
	public void setCorporationPhone(java.lang.String corporationPhone){
		this.corporationPhone = corporationPhone;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  审批时间
	 */

	@Column(name ="AUDITING_DATE",nullable=true,length=32)
	public java.lang.String getAuditingDate(){
		return this.auditingDate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  审批时间
	 */
	public void setAuditingDate(java.lang.String auditingDate){
		this.auditingDate = auditingDate;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  营业执照发证日期
	 */

	@Column(name ="GRANT_DATE",nullable=true)
	public java.util.Date getGrantDate(){
		return this.grantDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  营业执照发证日期
	 */
	public void setGrantDate(java.util.Date grantDate){
		this.grantDate = grantDate;
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
	 *@return: java.lang.String  开户银行
	 */

	@Column(name ="OAA_BANK",nullable=true,length=32)
	public java.lang.String getOaaBank(){
		return this.oaaBank;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  开户银行
	 */
	public void setOaaBank(java.lang.String oaaBank){
		this.oaaBank = oaaBank;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  所属地区
	 */

	@Column(name ="AFFILIATED_AREA",nullable=true,length=32)
	public java.lang.String getAffiliatedArea(){
		return this.affiliatedArea;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  所属地区
	 */
	public void setAffiliatedArea(java.lang.String affiliatedArea){
		this.affiliatedArea = affiliatedArea;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建部门
	 */

	@Column(name ="DEPART_ID",nullable=true,length=50)
	public java.lang.String getDepartId(){
		return this.departId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建部门
	 */
	public void setDepartId(java.lang.String departId){
		this.departId = departId;
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
}
