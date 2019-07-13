package cn.com.linkwide.ba.supplier.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

/**   
 * @Title: Entity
 * @Description: 供应商档案
 * @author onlineGenerator
 * @date 2018-06-19 19:51:58
 * @version V1.0   
 *
 */
@Entity
@Table(name = "l_ba_supplier", schema = "")
@SuppressWarnings("serial")
public class LBaSupplierEntity implements java.io.Serializable {
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
	/**所属部门*/
	private java.lang.String sysOrgCode;
	/**更新日期*/
	private java.util.Date updateDate;
	/**所属公司*/
	/*private java.lang.String sysCompanyCode;
	@Column(name ="SYS_COMPANY_CODE",nullable=true,length=50)
	public java.lang.String getSysCompanyCode(){
		return this.sysCompanyCode;
	}
	public void setSysCompanyCode(java.lang.String sysCompanyCode){
		this.sysCompanyCode = sysCompanyCode;
	}*/
	/**流程状态*/
	private java.lang.String bpmStatus;
	/**供应商编码*/
	@Excel(name="供应商编码",width=15)
	private java.lang.String supplierCode;
	/**供应商名称*/
	@Excel(name="供应商名称",width=15)
	private java.lang.String supplierFullName;
	/**供应商简称*/
	@Excel(name="供应商简称",width=15)
	private java.lang.String supplierShortName;
	/**拼音码*/
	@Excel(name="拼音码",width=15)
	private java.lang.String spllCode;
	/**供应商分类*/
	@Excel(name="供应商分类",width=15,dictTable="l_ba_supplier_type",dicCode="id",dicText="type_name")
	private java.lang.String supplierTypeId;
	/**联系人*/
	@Excel(name="联系人",width=15)
	private java.lang.String contacts;
	/**联系电话*/
	@Excel(name="联系电话",width=15)
	private java.lang.String telphone;
	/**地址*/
	@Excel(name="地址",width=15)
	private java.lang.String address;
	/**是否控制*/
	@Excel(name="是否控制",width=15)
	private java.lang.String isControl;
	/**助记码*/
	@Excel(name="助记码",width=15)
	private java.lang.String mnemonicCode;
	/**所属地区*/
	@Excel(name="所属地区",width=15)
	private java.lang.String affiliatedArea;
	/**统一社会信用代码*/
	@Excel(name="统一社会信用代码",width=15)
	private java.lang.String uscc;
	/**营业执照发证日期*/
	@Excel(name="营业执照发证日期",width=15,format = "yyyy-MM-dd")
	private java.util.Date grantDate;
	/**营业执照有效日期至*/
	@Excel(name="营业执照有效日期至",width=15,format = "yyyy-MM-dd")
	private java.util.Date vld;
	/**法人*/
	@Excel(name="法人",width=15)
	private java.lang.String legalPerson;
	/**公司地址*/
	@Excel(name="公司地址",width=15)
	private java.lang.String corporationAddress;
	/**公司电话*/
	@Excel(name="公司电话",width=15)
	private java.lang.String corporationPhone;
	/**网址*/
	@Excel(name="网址",width=15)
	private java.lang.String website;
	/**默认交易币种*/
	@Excel(name="默认交易币种",width=15)
	private java.lang.String defaultCurrency;
	/**注册资金*/
	@Excel(name="注册资金",width=15)
	private java.lang.String registerCapital;
	/**是否采购*/
	@Excel(name="是否采购",width=15)
	private java.lang.String isPurchase;
	/**是否为委外*/
	@Excel(name="是否为委外",width=15)
	private java.lang.String isOutsource;
	/**是否招标*/
	@Excel(name="是否招标",width=15)
	private java.lang.String isInviteBids;
	/**专管业务员*/
	@Excel(name="专管业务员",width=15)
	private java.lang.String superviseSalesman;
	/**收付款协议*/
	@Excel(name="收付款协议",width=15)
	private java.lang.String paymentReceivedProtocol;
	/**登陆部门*/
	@Excel(name="登陆部门")
	private java.lang.String departId;
	/**审批状态*/
	@Excel(name="审批状态",width=15)
	private java.lang.String auditingState;
	/**审批人*/
	@Excel(name="审批人",width=15)
	private java.lang.String auditor;
	/**审批时间*/
	@Excel(name="审批时间",width=15)
	private java.util.Date auditingDate;
	/**供应商来源*/
	@Excel(name="供应商来源",width=15)
	private java.lang.String supplierSource;
	/**是否允许登录供应商平台*/
	@Excel(name="是否允许登录供应商平台",width=15)
	private java.lang.String isLogin;
	/**是否停用*/
	@Excel(name="是否停用",width=15)
	private java.lang.String isCease;
	/**停用时间*/
	@Excel(name="停用时间",width=15)
	private java.util.Date ceaseDate;
	/**停用人*/
	@Excel(name="停用人",width=15)
	private java.lang.String ceasePeople;
	/**所属银行*/
	@Excel(name="所属银行",width=15)
	private java.lang.String affiliatedBank;
	/**开户银行*/
	@Excel(name="开户银行",width=15)
	private java.lang.String oaaBank;
	/**银行账号*/
	@Excel(name="银行账号",width=15)
	private java.lang.String bankCode;
	/**是否合格供应商*/
	@Excel(name="是否合格供应商",width=15)
	private java.lang.String isQualified;
	/**供应商登陆密码*/
	@Excel(name="供应商登陆密码",width=15)
	private java.lang.String password;
	
	// 收款账号地区号(收款账号是他行时显示0000)
	private String areaNum;
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
	@Column(name ="DEPART_ID",nullable=true,length=50)
	public java.lang.String getDepartId() {
		return departId;
	}

	public void setDepartId(java.lang.String departId) {
		this.departId = departId;
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
	 *@return: java.lang.String  供应商编码
	 */

	@Column(name ="SUPPLIER_CODE",nullable=true,length=32)
	public java.lang.String getSupplierCode(){
		return this.supplierCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  供应商编码
	 */
	public void setSupplierCode(java.lang.String supplierCode){
		this.supplierCode = supplierCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  供应商名称
	 */

	@Column(name ="SUPPLIER_FULL_NAME",nullable=true,length=32)
	public java.lang.String getSupplierFullName(){
		return this.supplierFullName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  供应商名称
	 */
	public void setSupplierFullName(java.lang.String supplierFullName){
		this.supplierFullName = supplierFullName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  供应商简称
	 */

	@Column(name ="SUPPLIER_SHORT_NAME",nullable=true,length=32)
	public java.lang.String getSupplierShortName(){
		return this.supplierShortName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  供应商简称
	 */
	public void setSupplierShortName(java.lang.String supplierShortName){
		this.supplierShortName = supplierShortName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  供应商分类
	 */

	@Column(name ="SUPPLIER_TYPE_ID",nullable=true,length=32)
	public java.lang.String getSupplierTypeId(){
		return this.supplierTypeId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  供应商分类
	 */
	public void setSupplierTypeId(java.lang.String supplierTypeId){
		this.supplierTypeId = supplierTypeId;
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

	@Column(name ="ADDRESS",nullable=true,length=255)
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
	 *@return: java.lang.String  是否控制
	 */

	@Column(name ="IS_CONTROL",nullable=true,length=32)
	public java.lang.String getIsControl(){
		return this.isControl;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否控制
	 */
	public void setIsControl(java.lang.String isControl){
		this.isControl = isControl;
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
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  营业执照发证日期
	 */

	@Column(name ="GRANT_DATE",nullable=true,length=32)
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
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  营业执照有效日期至
	 */

	@Column(name ="VLD",nullable=true,length=32)
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
	 *@return: java.lang.String  公司地址
	 */

	@Column(name ="CORPORATION_ADDRESS",nullable=true,length=255)
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
	 *@return: java.lang.String  是否采购
	 */

	@Column(name ="IS_PURCHASE",nullable=true,length=32)
	public java.lang.String getIsPurchase(){
		return this.isPurchase;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否采购
	 */
	public void setIsPurchase(java.lang.String isPurchase){
		this.isPurchase = isPurchase;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否为委外
	 */

	@Column(name ="IS_OUTSOURCE",nullable=true,length=32)
	public java.lang.String getIsOutsource(){
		return this.isOutsource;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否为委外
	 */
	public void setIsOutsource(java.lang.String isOutsource){
		this.isOutsource = isOutsource;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否招标
	 */

	@Column(name ="IS_INVITE_BIDS",nullable=true,length=32)
	public java.lang.String getIsInviteBids(){
		return this.isInviteBids;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否招标
	 */
	public void setIsInviteBids(java.lang.String isInviteBids){
		this.isInviteBids = isInviteBids;
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
	 *@return: java.lang.String  审批时间
	 */

	@Column(name ="AUDITING_DATE",nullable=true,length=32)
	public java.util.Date getAuditingDate(){
		return this.auditingDate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  审批时间
	 */
	public void setAuditingDate(java.util.Date auditingDate){
		this.auditingDate = auditingDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  供应商来源
	 */

	@Column(name ="SUPPLIER_SOURCE",nullable=true,length=32)
	public java.lang.String getSupplierSource(){
		return this.supplierSource;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  供应商来源
	 */
	public void setSupplierSource(java.lang.String supplierSource){
		this.supplierSource = supplierSource;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否允许登录供应商平台
	 */

	@Column(name ="IS_LOGIN",nullable=true,length=32)
	public java.lang.String getIsLogin(){
		return this.isLogin;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否允许登录供应商平台
	 */
	public void setIsLogin(java.lang.String isLogin){
		this.isLogin = isLogin;
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
	 *@return: java.lang.String  停用时间
	 */

	@Column(name ="CEASE_DATE",nullable=true,length=32)
	public java.util.Date getCeaseDate(){
		return this.ceaseDate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  停用时间
	 */
	public void setCeaseDate(java.util.Date ceaseDate){
		this.ceaseDate = ceaseDate;
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
	 *@return: java.lang.String  是否合格供应商
	 */

	@Column(name ="IS_QUALIFIED",nullable=true,length=32)
	public java.lang.String getIsQualified(){
		return this.isQualified;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否合格供应商
	 */
	public void setIsQualified(java.lang.String isQualified){
		this.isQualified = isQualified;
	}
	
	@Column(name ="PASSWORD",nullable=true,length=50)
	public java.lang.String getPassword() {
		return password;
	}

	public void setPassword(java.lang.String password) {
		this.password = password;
	}
	@Column(name ="SPLL_CODE",nullable=true,length=50)
	public java.lang.String getSpllCode() {
		return spllCode;
	}

	public void setSpllCode(java.lang.String spllCode) {
		this.spllCode = spllCode;
	}

	@Column(name ="area_Num",nullable=true,length=10)
	public String getAreaNum() {
		return areaNum;
	}

	public void setAreaNum(String areaNum) {
		this.areaNum = areaNum;
	}
	
	
}
