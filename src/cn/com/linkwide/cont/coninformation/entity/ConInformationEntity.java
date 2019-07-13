package cn.com.linkwide.cont.coninformation.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

import cn.com.linkwide.common.delcheck.annotation.QueryParameterSettings;

/**   
 * @Title: Entity
 * @Description: 合同信息
 * @author onlineGenerator
 * @date 2018-08-23 14:40:50
 * @version V1.0   
 *
 */
@Entity
@Table(name = "con_information", schema = "")
@SuppressWarnings("serial")
public class ConInformationEntity implements java.io.Serializable {
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
	/**合同类别*/
	private java.lang.String conType;
	/**合同预警设置*/
	private java.lang.String conWarId;
	/**合同状态*/
    @Excel(name="合同状态",width=15,dicCode="con_state")
	private java.lang.String conState;
	/**合同流水号*/
    @Excel(name="合同流水号",width=15)
	private java.lang.String conNumber;
	/**合同编码*/
    @Excel(name="合同编码",width=15)
	private java.lang.String conNo;
	/**合同签订日期*/
    @Excel(name="合同签订日期",width=15,format = "yyyy-MM-dd")
	private java.util.Date signedDate;
	/**合同开始日期*/
    @Excel(name="合同开始日期",width=15,format = "yyyy-MM-dd")
	private java.util.Date startDate;
	/**合同结束日期*/
    @Excel(name="合同结束日期",width=15,format = "yyyy-MM-dd")
	private java.util.Date endDate;
	/**对方单位*/
    @Excel(name="对方单位",width=15)
	private java.lang.String otherCompy;
	/**对方负责人*/
    @Excel(name="对方负责人",width=15)
	private java.lang.String otherEmp;
	/**币种*/
    @Excel(name="币种",width=15)
	private java.lang.String moneyType;
	/**科室*/
    @Excel(name="科室",width=15)
	private java.lang.String deptId;
	/**生效人*/
    @Excel(name="审批人",width=15)
	private java.lang.String effectEmp;
	
	private java.util.Date effectDate;

	private java.lang.String caseEmp;
	
	private java.util.Date caseDate;
	/**质保金周期*/
    @Excel(name="保修期(年)",width=15)
	private java.lang.String zbjCycle;
	/**质保金比例*/
    @Excel(name="质保金比例",width=15)
	private java.lang.Double zbjRate;
	/**质保金金额*/
    @Excel(name="质保金金额",width=15)
	private java.lang.Double zbjMoney;

	private java.lang.String venId;

	private java.lang.String customerId;
	/**采购招标id*/
	private java.lang.String cgzbId;
	/**采购申请id*/
	private java.lang.String cgsqId;
	/**网上报销id*/
	private java.lang.String onlineId;
	/**凭证中心id*/
	private java.lang.String acctId;
	/**物资档案id*/
 
	private java.lang.String mateId;
	/**合同履行状态*/
    @Excel(name="合同履行状态",width=15,dicCode="perState")
	private java.lang.String perstate;
	/**状态日期已用*/
	private java.util.Date stateDate1;
	/**状态日期已用*/
	private java.util.Date stateDate2;
	/**状态日期已用*/
	private java.util.Date stateDate3;
	/**状态日期已用*/
	private java.util.Date stateDate4;
	/**状态日期已用*/
	private java.util.Date stateDate5;
	/**状态日期已用*/
	private java.util.Date stateDate6;
	/**主合同编码*/
    @Excel(name="主合同编码",width=15)
	private java.lang.String execConNo;
	/**合同总数*/
    @Excel(name="合同总数",width=15)
	private java.lang.String conNumnerZ;
	/**是否变更*/
    @Excel(name="是否变更",width=15)
	private java.lang.String isBg;
	/**合同续签*/
    @Excel(name="合同续签",width=15)
	private java.lang.String renewId;
	/**违约原因*/
    @Excel(name="违约原因",width=15)
	private java.lang.String defoutCase;
	/**是否违约*/
    @Excel(name="是否违约",width=15)
	private java.lang.String isDefout;
	/**附件*/
	private java.lang.String fj;
	/**违约日期*/
    @Excel(name="违约日期",width=15,format = "yyyy-MM-dd")
	private java.util.Date defoutDate;
	/**审批状态*/
    @Excel(name="审批状态",width=15)
	private java.lang.String confermState;
	/**审批日期*/
    @Excel(name="审批日期",width=15,format = "yyyy-MM-dd")
	private java.util.Date confermDate;
	/**协议信息*/
    @Excel(name="协议信息",width=15)
	private java.lang.String agreeId;
	/**会签状态*/
    @Excel(name="会签状态",width=15)
	private java.lang.String signId;
	/**审批流id*/
	private java.lang.String spl;
	/**合同名称*/
    @Excel(name="合同名称",width=15)
	private java.lang.String conName;
	/**合同备注*/
	private java.lang.String conBz;
	/**合同描述*/
    @Excel(name="合同描述",width=15)
	private java.lang.String conMs;
	/**预留字段1已用*/
	private java.lang.Double conExec1;
	/**预留字段2已用*/
	private java.lang.Double conExec2;
	/**预留字段3已用*/
	private java.lang.String conExec3;
	/**预留字段4已用*/
	private java.lang.String conExec4;
	/**预留字段5已用*/
	private java.lang.String conExec5;
	/**预留字段6已用*/
	private java.lang.String conExec6;
	/**预留字段7*/
	private java.lang.String conExec7;
	/**预留字段8已用*/
	private java.lang.String conExec8;
	/**预留字段9已用*/
	private java.lang.String conExec9;
	/**预留字段10已用*/
	
	private java.lang.String isBid;
	private java.lang.String bidNo;
	private java.lang.String workflowId;
	private java.lang.String header;
	private java.lang.String htelphone;
	
	
	
	
	
	@Column(name ="HEADER",nullable=true,length=10)
	public java.lang.String getHeader() {
		return header;
	}

	public void setHeader(java.lang.String header) {
		this.header = header;
	}
	@Column(name ="HTELPHONE",nullable=true,length=10)
	public java.lang.String getHtelphone() {
		return htelphone;
	}

	public void setHtelphone(java.lang.String htelphone) {
		this.htelphone = htelphone;
	}

	@Column(name ="WORKFLOW_ID",nullable=true,length=10)
	public java.lang.String getWorkflowId() {
		return workflowId;
	}

	public void setWorkflowId(java.lang.String workflowId) {
		this.workflowId = workflowId;
	}

	@Column(name ="IS_BID",nullable=true,length=10)
	public java.lang.String getIsBid() {
		return isBid;
	}

	public void setIsBid(java.lang.String isBid) {
		this.isBid = isBid;
	}
	@Column(name ="BID_NO",nullable=true,length=50)
	public java.lang.String getBidNo() {
		return bidNo;
	}

	public void setBidNo(java.lang.String bidNo) {
		this.bidNo = bidNo;
	}

	@Excel(name="合同类型",width=15)
	@QueryParameterSettings(message="当前编码已存在合同中不能删除!")
	private java.lang.String conExec10;
	
	
	private java.lang.String caseOld;
	@Transient
	public java.lang.String getCaseOld() {
		return caseOld;
	}

	public void setCaseOld(java.lang.String caseOld) {
		this.caseOld = caseOld;
	}
	
	
	
	private java.lang.String isAssier;
	private java.lang.String isZcsb;
	private java.lang.String zrCode;
	private java.lang.String isSpice;
 	private java.lang.String isSbxl;
 	private java.lang.String qxZz;
 	private java.lang.String qxJc;
	
	
	@Column(name ="QX_ZZ",nullable=false,length=32)
	public java.lang.String getQxZz() {
		return qxZz;
	}

	public void setQxZz(java.lang.String qxZz) {
		this.qxZz = qxZz;
	}
	@Column(name ="QX_JC",nullable=false,length=32)
	public java.lang.String getQxJc() {
		return qxJc;
	}

	public void setQxJc(java.lang.String qxJc) {
		this.qxJc = qxJc;
	}

	@Column(name ="IS_SBXL",nullable=false,length=32)
	public java.lang.String getIsSbxl() {
		return isSbxl;
	}

	public void setIsSbxl(java.lang.String isSbxl) {
		this.isSbxl = isSbxl;
	} 
	
	
	private java.lang.String isKyl;
	
	
	
	@Column(name ="IS_KYL",nullable=false,length=32)
	public java.lang.String getIsKyl() {
		return isKyl;
	}

	public void setIsKyl(java.lang.String isKyl) {
		this.isKyl = isKyl;
	}

	@Column(name ="IS_SPICE",nullable=false,length=32)
	public java.lang.String getIsSpice() {
		return isSpice;
	}

	public void setIsSpice(java.lang.String isSpice) {
		this.isSpice = isSpice;
	}

	@Column(name ="ZR_CODE",nullable=false,length=32)
	public java.lang.String getZrCode() {
		return zrCode;
	}

	public void setZrCode(java.lang.String zrCode) {
		this.zrCode = zrCode;
	}

	@Column(name ="IS_ZCSB",nullable=false,length=10)
	public java.lang.String getIsZcsb() {
		return isZcsb;
	}

	public void setIsZcsb(java.lang.String isZcsb) {
		this.isZcsb = isZcsb;
	}

	@Column(name ="IS_ASSIER",nullable=false,length=10)
	public java.lang.String getIsAssier() {
		return isAssier;
	}

	public void setIsAssier(java.lang.String isAssier) {
		this.isAssier = isAssier;
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
	 *@return: java.lang.String  合同类别
	 */
	
	@Column(name ="CON_TYPE",nullable=true,length=32)
	public java.lang.String getConType(){
		return this.conType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  合同类别
	 */
	public void setConType(java.lang.String conType){
		this.conType = conType;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  合同预警设置
	 */
	
	@Column(name ="CON_WAR_ID",nullable=true,length=32)
	public java.lang.String getConWarId(){
		return this.conWarId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  合同预警设置
	 */
	public void setConWarId(java.lang.String conWarId){
		this.conWarId = conWarId;
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
	 *@return: java.lang.String  合同流水号
	 */
	
	@Column(name ="CON_NUMBER",nullable=true,length=32)
	public java.lang.String getConNumber(){
		return this.conNumber;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  合同流水号
	 */
	public void setConNumber(java.lang.String conNumber){
		this.conNumber = conNumber;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  合同编码
	 */
	
	@Column(name ="CON_NO",nullable=true,length=32)
	public java.lang.String getConNo(){
		return this.conNo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  合同编码
	 */
	public void setConNo(java.lang.String conNo){
		this.conNo = conNo;
	}
	
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  合同签订日期
	 */
	
	@Column(name ="SIGNED_DATE",nullable=true,length=32)
	public java.util.Date getSignedDate(){
		return this.signedDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  合同签订日期
	 */
	public void setSignedDate(java.util.Date signedDate){
		this.signedDate = signedDate;
	}
	
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  合同开始日期
	 */
	
	@Column(name ="START_DATE",nullable=true,length=32)
	public java.util.Date getStartDate(){
		return this.startDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  合同开始日期
	 */
	public void setStartDate(java.util.Date startDate){
		this.startDate = startDate;
	}
	
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  合同结束日期
	 */
	
	@Column(name ="END_DATE",nullable=true,length=32)
	public java.util.Date getEndDate(){
		return this.endDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  合同结束日期
	 */
	public void setEndDate(java.util.Date endDate){
		this.endDate = endDate;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  对方单位
	 */
	
	@Column(name ="OTHER_COMPY",nullable=true,length=50)
	public java.lang.String getOtherCompy(){
		return this.otherCompy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  对方单位
	 */
	public void setOtherCompy(java.lang.String otherCompy){
		this.otherCompy = otherCompy;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  对方负责人
	 */
	
	@Column(name ="OTHER_EMP",nullable=true,length=32)
	public java.lang.String getOtherEmp(){
		return this.otherEmp;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  对方负责人
	 */
	public void setOtherEmp(java.lang.String otherEmp){
		this.otherEmp = otherEmp;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  币种
	 */
	
	@Column(name ="MONEY_TYPE",nullable=true,length=32)
	public java.lang.String getMoneyType(){
		return this.moneyType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  币种
	 */
	public void setMoneyType(java.lang.String moneyType){
		this.moneyType = moneyType;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  科室
	 */
	
	@Column(name ="DEPT_ID",nullable=true,length=32)
	public java.lang.String getDeptId(){
		return this.deptId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  科室
	 */
	public void setDeptId(java.lang.String deptId){
		this.deptId = deptId;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  生效人
	 */
	
	@Column(name ="EFFECT_EMP",nullable=true,length=32)
	public java.lang.String getEffectEmp(){
		return this.effectEmp;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  生效人
	 */
	public void setEffectEmp(java.lang.String effectEmp){
		this.effectEmp = effectEmp;
	}
	
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  生效日期
	 */
	
	@Column(name ="EFFECT_DATE",nullable=true,length=32)
	public java.util.Date getEffectDate(){
		return this.effectDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  生效日期
	 */
	public void setEffectDate(java.util.Date effectDate){
		this.effectDate = effectDate;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  结案人
	 */
	
	@Column(name ="CASE_EMP",nullable=true,length=32)
	public java.lang.String getCaseEmp(){
		return this.caseEmp;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  结案人
	 */
	public void setCaseEmp(java.lang.String caseEmp){
		this.caseEmp = caseEmp;
	}
	
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  结案日期
	 */
	
	@Column(name ="CASE_DATE",nullable=true,length=32)
	public java.util.Date getCaseDate(){
		return this.caseDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  结案日期
	 */
	public void setCaseDate(java.util.Date caseDate){
		this.caseDate = caseDate;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  质保金周期
	 */
	
	@Column(name ="ZBJ_CYCLE",nullable=true,length=32)
	public java.lang.String getZbjCycle(){
		return this.zbjCycle;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  质保金周期
	 */
	public void setZbjCycle(java.lang.String zbjCycle){
		this.zbjCycle = zbjCycle;
	}
	
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  质保金比例
	 */
	
	@Column(name ="ZBJ_RATE",nullable=true,length=32)
	public java.lang.Double getZbjRate(){
		return this.zbjRate;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  质保金比例
	 */
	public void setZbjRate(java.lang.Double zbjRate){
		this.zbjRate = zbjRate;
	}
	
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  质保金金额
	 */
	
	@Column(name ="ZBJ_MONEY",nullable=true,length=32)
	public java.lang.Double getZbjMoney(){
		return this.zbjMoney;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  质保金金额
	 */
	public void setZbjMoney(java.lang.Double zbjMoney){
		this.zbjMoney = zbjMoney;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  供应商档案
	 */
	
	@Column(name ="VEN_ID",nullable=true,length=32)
	public java.lang.String getVenId(){
		return this.venId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  供应商档案
	 */
	public void setVenId(java.lang.String venId){
		this.venId = venId;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  客户档案
	 */
	
	@Column(name ="CUSTOMER_ID",nullable=true,length=32)
	public java.lang.String getCustomerId(){
		return this.customerId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  客户档案
	 */
	public void setCustomerId(java.lang.String customerId){
		this.customerId = customerId;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  采购招标id
	 */
	
	@Column(name ="CGZB_ID",nullable=true,length=32)
	public java.lang.String getCgzbId(){
		return this.cgzbId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  采购招标id
	 */
	public void setCgzbId(java.lang.String cgzbId){
		this.cgzbId = cgzbId;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  采购申请id
	 */
	
	@Column(name ="CGSQ_ID",nullable=true,length=32)
	public java.lang.String getCgsqId(){
		return this.cgsqId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  采购申请id
	 */
	public void setCgsqId(java.lang.String cgsqId){
		this.cgsqId = cgsqId;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  网上报销id
	 */
	
	@Column(name ="ONLINE_ID",nullable=true,length=32)
	public java.lang.String getOnlineId(){
		return this.onlineId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  网上报销id
	 */
	public void setOnlineId(java.lang.String onlineId){
		this.onlineId = onlineId;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  凭证中心id
	 */
	
	@Column(name ="ACCT_ID",nullable=true,length=32)
	public java.lang.String getAcctId(){
		return this.acctId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  凭证中心id
	 */
	public void setAcctId(java.lang.String acctId){
		this.acctId = acctId;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  物资档案id
	 */
	
	@Column(name ="MATE_ID",nullable=true,length=32)
	public java.lang.String getMateId(){
		return this.mateId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  物资档案id
	 */
	public void setMateId(java.lang.String mateId){
		this.mateId = mateId;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  合同履行状态
	 */
	
	@Column(name ="PERSTATE",nullable=true,length=32)
	public java.lang.String getPerstate(){
		return this.perstate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  合同履行状态
	 */
	public void setPerstate(java.lang.String perstate){
		this.perstate = perstate;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  状态日期
	 */
	
	@Column(name ="STATE_DATE1",nullable=true,length=32)
	public java.util.Date getStateDate1(){
		return this.stateDate1;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  状态日期
	 */
	public void setStateDate1(java.util.Date stateDate1){
		this.stateDate1 = stateDate1;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  状态日期
	 */
	
	@Column(name ="STATE_DATE2",nullable=true,length=32)
	public java.util.Date getStateDate2(){
		return this.stateDate2;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  状态日期
	 */
	public void setStateDate2(java.util.Date stateDate2){
		this.stateDate2 = stateDate2;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  状态日期
	 */
	
	@Column(name ="STATE_DATE3",nullable=true,length=32)
	public java.util.Date getStateDate3(){
		return this.stateDate3;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  状态日期
	 */
	public void setStateDate3(java.util.Date stateDate3){
		this.stateDate3 = stateDate3;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  状态日期
	 */
	
	@Column(name ="STATE_DATE4",nullable=true,length=32)
	public java.util.Date getStateDate4(){
		return this.stateDate4;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  状态日期
	 */
	public void setStateDate4(java.util.Date stateDate4){
		this.stateDate4 = stateDate4;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  状态日期
	 */
	
	@Column(name ="STATE_DATE5",nullable=true,length=32)
	public java.util.Date getStateDate5(){
		return this.stateDate5;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  状态日期
	 */
	public void setStateDate5(java.util.Date date){
		this.stateDate5 = date;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  状态日期
	 */
	
	@Column(name ="STATE_DATE6",nullable=true,length=32)
	public java.util.Date getStateDate6(){
		return this.stateDate6;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  状态日期
	 */
	public void setStateDate6(java.util.Date stateDate6){
		this.stateDate6 = stateDate6;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  主合同编码
	 */
	
	@Column(name ="EXEC_CON_NO",nullable=true,length=32)
	public java.lang.String getExecConNo(){
		return this.execConNo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  主合同编码
	 */
	public void setExecConNo(java.lang.String execConNo){
		this.execConNo = execConNo;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  合同总数
	 */
	
	@Column(name ="CON_NUMNER_Z",nullable=true,length=32)
	public java.lang.String getConNumnerZ(){
		return this.conNumnerZ;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  合同总数
	 */
	public void setConNumnerZ(java.lang.String conNumnerZ){
		this.conNumnerZ = conNumnerZ;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否变更
	 */
	
	@Column(name ="IS_BG",nullable=true,length=32)
	public java.lang.String getIsBg(){
		return this.isBg;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否变更
	 */
	public void setIsBg(java.lang.String isBg){
		this.isBg = isBg;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  合同续签
	 */
	
	@Column(name ="RENEW_ID",nullable=true,length=32)
	public java.lang.String getRenewId(){
		return this.renewId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  合同续签
	 */
	public void setRenewId(java.lang.String renewId){
		this.renewId = renewId;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  违约原因
	 */
	
	@Column(name ="DEFOUT_CASE",nullable=true,length=2000)
	public java.lang.String getDefoutCase(){
		return this.defoutCase;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  违约原因
	 */
	public void setDefoutCase(java.lang.String defoutCase){
		this.defoutCase = defoutCase;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否违约
	 */
	
	@Column(name ="IS_DEFOUT",nullable=true,length=32)
	public java.lang.String getIsDefout(){
		return this.isDefout;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否违约
	 */
	public void setIsDefout(java.lang.String isDefout){
		this.isDefout = isDefout;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  附件
	 */
	
	@Column(name ="FJ",nullable=true,length=32)
	public java.lang.String getFj(){
		return this.fj;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  附件
	 */
	public void setFj(java.lang.String fj){
		this.fj = fj;
	}
	
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  违约日期
	 */
	
	@Column(name ="DEFOUT_DATE",nullable=true,length=32)
	public java.util.Date getDefoutDate(){
		return this.defoutDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  违约日期
	 */
	public void setDefoutDate(java.util.Date defoutDate){
		this.defoutDate = defoutDate;
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
	
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  审批日期
	 */
	
	@Column(name ="CONFERM_DATE",nullable=true,length=32)
	public java.util.Date getConfermDate(){
		return this.confermDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  审批日期
	 */
	public void setConfermDate(java.util.Date confermDate){
		this.confermDate = confermDate;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  协议信息
	 */
	
	@Column(name ="AGREE_ID",nullable=true,length=32)
	public java.lang.String getAgreeId(){
		return this.agreeId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  协议信息
	 */
	public void setAgreeId(java.lang.String agreeId){
		this.agreeId = agreeId;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  会签状态
	 */
	
	@Column(name ="SIGN_ID",nullable=true,length=32)
	public java.lang.String getSignId(){
		return this.signId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  会签状态
	 */
	public void setSignId(java.lang.String signId){
		this.signId = signId;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  审批流id
	 */
	
	@Column(name ="SPL",nullable=true,length=32)
	public java.lang.String getSpl(){
		return this.spl;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  审批流id
	 */
	public void setSpl(java.lang.String spl){
		this.spl = spl;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  合同名称
	 */
	
	@Column(name ="CON_NAME",nullable=true,length=32)
	public java.lang.String getConName(){
		return this.conName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  合同名称
	 */
	public void setConName(java.lang.String conName){
		this.conName = conName;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  合同备注
	 */
	
	@Column(name ="CON_BZ",nullable=true,length=100)
	public java.lang.String getConBz(){
		return this.conBz;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  合同备注
	 */
	public void setConBz(java.lang.String conBz){
		this.conBz = conBz;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  合同描述
	 */
	
	@Column(name ="CON_MS",nullable=true,length=100)
	public java.lang.String getConMs(){
		return this.conMs;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  合同描述
	 */
	public void setConMs(java.lang.String conMs){
		this.conMs = conMs;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预留字段1
	 */
	
	@Column(name ="CON_EXEC1",nullable=true,length=32)
	public java.lang.Double getConExec1(){
		return this.conExec1;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预留字段1
	 */
	public void setConExec1(java.lang.Double conExec1){
		this.conExec1 = conExec1;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预留字段2
	 */
	
	@Column(name ="CON_EXEC2",nullable=true,length=32)
	public java.lang.Double getConExec2(){
		return this.conExec2;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预留字段2
	 */
	public void setConExec2(java.lang.Double conExec2){
		this.conExec2 = conExec2;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预留字段3
	 */
	
	@Column(name ="CON_EXEC3",nullable=true,length=32)
	public java.lang.String getConExec3(){
		return this.conExec3;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预留字段3
	 */
	public void setConExec3(java.lang.String conExec3){
		this.conExec3 = conExec3;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预留字段4
	 */
	
	@Column(name ="CON_EXEC4",nullable=true,length=32)
	public java.lang.String getConExec4(){
		return this.conExec4;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预留字段4
	 */
	public void setConExec4(java.lang.String conExec4){
		this.conExec4 = conExec4;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预留字段5
	 */
	
	@Column(name ="CON_EXEC5",nullable=true,length=32)
	public java.lang.String getConExec5(){
		return this.conExec5;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预留字段5
	 */
	public void setConExec5(java.lang.String conExec5){
		this.conExec5 = conExec5;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预留字段6
	 */
	
	@Column(name ="CON_EXEC6",nullable=true,length=32)
	public java.lang.String getConExec6(){
		return this.conExec6;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预留字段6
	 */
	public void setConExec6(java.lang.String conExec6){
		this.conExec6 = conExec6;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预留字段7
	 */
	
	@Column(name ="CON_EXEC7",nullable=true,length=32)
	public java.lang.String getConExec7(){
		return this.conExec7;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预留字段7
	 */
	public void setConExec7(java.lang.String conExec7){
		this.conExec7 = conExec7;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预留字段8
	 */
	
	@Column(name ="CON_EXEC8",nullable=true,length=32)
	public java.lang.String getConExec8(){
		return this.conExec8;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预留字段8
	 */
	public void setConExec8(java.lang.String conExec8){
		this.conExec8 = conExec8;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预留字段9
	 */
	
	@Column(name ="CON_EXEC9",nullable=true,length=32)
	public java.lang.String getConExec9(){
		return this.conExec9;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预留字段9
	 */
	public void setConExec9(java.lang.String conExec9){
		this.conExec9 = conExec9;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预留字段10
	 */
	
	@Column(name ="CON_EXEC10",nullable=true,length=32)
	public java.lang.String getConExec10(){
		return this.conExec10;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预留字段10
	 */
	public void setConExec10(java.lang.String conExec10){
		this.conExec10 = conExec10;
	}
	
}
