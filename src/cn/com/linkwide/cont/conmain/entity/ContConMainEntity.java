package cn.com.linkwide.cont.conmain.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

/**   
 * @Title: Entity
 * @Description: 合同主表
 * @author onlineGenerator
 * @date 2018-08-01 16:18:55
 * @version V1.0   
 *
 */
@Entity
@Table(name = "cont_con_main", schema = "")
@SuppressWarnings("serial")
public class ContConMainEntity implements java.io.Serializable {
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
	/**合同编码*/
    @Excel(name="合同编码",width=15)
	private java.lang.String contractCode;
	/**合同名称*/
    @Excel(name="合同名称",width=15)
	private java.lang.String contractName;
	/**合同类别*/
    @Excel(name="合同类别",width=15)
	private java.lang.String contractType;
	/**签订日期*/
    @Excel(name="签订日期",width=15,format = "yyyy-MM-dd")
	private java.util.Date signDate;
	/**部门*/
	private java.lang.String deptCode;
	/**贸易类别*/
    @Excel(name="贸易类别",width=15)
	private java.lang.String tradeClass;
	/**币种*/
    @Excel(name="币种",width=15)
	private java.lang.String currency;
	/**签订金额*/
    @Excel(name="签订金额",width=15)
	private java.lang.Double amountMoney;
	/**合同金额*/
    @Excel(name="合同金额",width=15)
	private java.lang.Double conMoney;
	/**医院编码*/
	private java.lang.String hospId;
	/**收付款方式*/
    @Excel(name="收付款方式",width=15)
	private java.lang.String payMeth;
	/**公司编码*/
    @Excel(name="公司编码",width=15)
	private java.lang.String company;
	/**合同状态*/
    @Excel(name="合同状态",width=15)
	private java.lang.String conState;
	/**是否经过招标*/
    @Excel(name="是否经过招标",width=15)
	private java.lang.String isBid;
	/**组织形式*/
    @Excel(name="组织形式",width=15)
	private java.lang.String orgType;
	/**采购方式*/
    @Excel(name="采购方式",width=15)
	private java.lang.String purcType;
	/**甲方负责人*/
    @Excel(name="甲方负责人",width=15)
	private java.lang.String fistParty;
	/**对方负责人*/
    @Excel(name="对方负责人",width=15)
	private java.lang.String opPerpion;
	/**开始日期*/
    @Excel(name="开始日期",width=15,format = "yyyy-MM-dd")
	private java.util.Date beginDate;
	/**结束日期*/
    @Excel(name="结束日期",width=15,format = "yyyy-MM-dd")
	private java.util.Date endDate;
	/**合同分组*/
    @Excel(name="合同分组",width=15)
	private java.lang.String conGroup;
	/**变动金额*/
    @Excel(name="变动金额",width=15)
	private java.lang.String changeMoney;
	/**保证金方式*/
    @Excel(name="保证金方式",width=15)
	private java.lang.String bondType;
	/**保证金金额*/
    @Excel(name="保证金金额",width=15)
	private java.lang.Double bondMoney;
	/**父合同*/
    @Excel(name="父合同",width=15)
	private java.lang.String parentCon;
	/**收款还是付款*/
    @Excel(name="收款还是付款",width=15)
	private java.lang.String payOrRec;
	/**备注*/
    @Excel(name="备注",width=15)
	private java.lang.String remark;
	/**是否结转*/
    @Excel(name="是否结转",width=15)
	private java.lang.String isForward;
	/**签订日期*/
    @Excel(name="签订日期",width=15,format = "yyyy-MM-dd")
	private java.util.Date casDate;
	/**签订人*/
    @Excel(name="签订人",width=15)
	private java.lang.String casEmp;
	/**审核日期*/
    @Excel(name="审核日期",width=15,format = "yyyy-MM-dd")
	private java.util.Date confirmDate;
	/**审核人*/
    @Excel(name="审核人",width=15)
	private java.lang.String confirmEmp;
	/**履行时间*/
    @Excel(name="履行时间",width=15,format = "yyyy-MM-dd")
	private java.util.Date performDate;
	/**履行人*/
    @Excel(name="履行人",width=15)
	private java.lang.String performEmp;
	/**变更日期*/
    @Excel(name="变更日期",width=15,format = "yyyy-MM-dd")
	private java.util.Date changeDate;
	/**变更人*/
    @Excel(name="变更人",width=15)
	private java.lang.String changeEmp;
	/**终止日期*/
    @Excel(name="终止日期",width=15,format = "yyyy-MM-dd")
	private java.util.Date terminationDate;
	/**终止人*/
    @Excel(name="终止人",width=15)
	private java.lang.String terminationEmp;
	/**续签日期*/
    @Excel(name="续签日期",width=15,format = "yyyy-MM-dd")
	private java.util.Date renewDate;
	/**续签人*/
    @Excel(name="续签人",width=15)
	private java.lang.String renewEmp;
	/**合同状态*/
    @Excel(name="合同状态",width=15)
	private java.lang.String contractState;
	/**供应商编码*/
    @Excel(name="供应商编码",width=15)
	private java.lang.String venCode;
	/**客户档案*/
    @Excel(name="客户档案",width=15)
	private java.lang.String customerCode;
	/**采购招标*/
    @Excel(name="采购招标",width=15)
	private java.lang.String bidCode;
	/**采购申请*/
    @Excel(name="采购申请",width=15)
	private java.lang.String puReCode;
	/**网上报销*/
    @Excel(name="网上报销",width=15)
	private java.lang.String ineCode;
	/**对方单位*/
    @Excel(name="对方单位",width=15)
	private java.lang.String opCompy;
	/**业务员*/
    @Excel(name="业务员",width=15)
	private java.lang.String ywPerpion;
	/**保修期*/
    @Excel(name="保修期",width=15)
	private java.lang.String bxDate;
	/**合同描述*/
    @Excel(name="合同描述",width=15)
	private java.lang.String contractMs;
	/**创建人*/
    @Excel(name="创建人",width=15)
	private java.lang.String crPerpion;
	/**创建日期*/
    @Excel(name="创建日期",width=15,format = "yyyy-MM-dd")
	private java.util.Date crDate;
	/**生效人*/
    @Excel(name="生效人",width=15)
	private java.lang.String sxPerpion;
	/**生效日期*/
    @Excel(name="生效日期",width=15,format = "yyyy-MM-dd")
	private java.util.Date sxDate;
	/**结案人*/
    @Excel(name="结案人",width=15)
	private java.lang.String jaPerpion;
	/**结案日期*/
    @Excel(name="结案日期",width=15,format = "yyyy-MM-dd")
	private java.util.Date jaDate;
	/**质保金比例*/
    @Excel(name="质保金比例",width=15)
	private java.lang.String zbjLate;
	/**质保金开始日期*/
    @Excel(name="质保金开始日期",width=15,format = "yyyy-MM-dd")
	private java.util.Date zbjStartDate;
	/**质保金结束日期*/
    @Excel(name="质保金结束日期",width=15,format = "yyyy-MM-dd")
	private java.util.Date zbjEndDate;
	/**履行状态*/
    @Excel(name="履行状态",width=15)
	private java.lang.String lxState;
	/**变更合同号*/
    @Excel(name="变更合同号",width=15)
	private java.lang.String bgContractNo;
	/**续签合同号*/
    @Excel(name="续签合同号",width=15)
	private java.lang.String xqContractNo;
	
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
	 *@return: java.lang.String  合同编码
	 */
	
	@Column(name ="CONTRACT_CODE",nullable=true,length=32)
	public java.lang.String getContractCode(){
		return this.contractCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  合同编码
	 */
	public void setContractCode(java.lang.String contractCode){
		this.contractCode = contractCode;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  合同名称
	 */
	
	@Column(name ="CONTRACT_NAME",nullable=true,length=50)
	public java.lang.String getContractName(){
		return this.contractName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  合同名称
	 */
	public void setContractName(java.lang.String contractName){
		this.contractName = contractName;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  合同类别
	 */
	
	@Column(name ="CONTRACT_TYPE",nullable=true,length=32)
	public java.lang.String getContractType(){
		return this.contractType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  合同类别
	 */
	public void setContractType(java.lang.String contractType){
		this.contractType = contractType;
	}
	
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  签订日期
	 */
	
	@Column(name ="SIGN_DATE",nullable=true,length=32)
	public java.util.Date getSignDate(){
		return this.signDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  签订日期
	 */
	public void setSignDate(java.util.Date signDate){
		this.signDate = signDate;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  部门
	 */
	
	@Column(name ="DEPT_CODE",nullable=true,length=32)
	public java.lang.String getDeptCode(){
		return this.deptCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  部门
	 */
	public void setDeptCode(java.lang.String deptCode){
		this.deptCode = deptCode;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  贸易类别
	 */
	
	@Column(name ="TRADE_CLASS",nullable=true,length=32)
	public java.lang.String getTradeClass(){
		return this.tradeClass;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  贸易类别
	 */
	public void setTradeClass(java.lang.String tradeClass){
		this.tradeClass = tradeClass;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  币种
	 */
	
	@Column(name ="CURRENCY",nullable=true,length=32)
	public java.lang.String getCurrency(){
		return this.currency;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  币种
	 */
	public void setCurrency(java.lang.String currency){
		this.currency = currency;
	}
	
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  签订金额
	 */
	
	@Column(name ="AMOUNT_MONEY",nullable=true,length=32)
	public java.lang.Double getAmountMoney(){
		return this.amountMoney;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  签订金额
	 */
	public void setAmountMoney(java.lang.Double amountMoney){
		this.amountMoney = amountMoney;
	}
	
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  合同金额
	 */
	
	@Column(name ="CON_MONEY",nullable=true,length=32)
	public java.lang.Double getConMoney(){
		return this.conMoney;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  合同金额
	 */
	public void setConMoney(java.lang.Double conMoney){
		this.conMoney = conMoney;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  医院编码
	 */
	
	@Column(name ="HOSP_ID",nullable=true,length=32)
	public java.lang.String getHospId(){
		return this.hospId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  医院编码
	 */
	public void setHospId(java.lang.String hospId){
		this.hospId = hospId;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  收付款方式
	 */
	
	@Column(name ="PAY_METH",nullable=true,length=32)
	public java.lang.String getPayMeth(){
		return this.payMeth;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  收付款方式
	 */
	public void setPayMeth(java.lang.String payMeth){
		this.payMeth = payMeth;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  公司编码
	 */
	
	@Column(name ="COMPANY",nullable=true,length=32)
	public java.lang.String getCompany(){
		return this.company;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  公司编码
	 */
	public void setCompany(java.lang.String company){
		this.company = company;
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
	 *@return: java.lang.String  是否经过招标
	 */
	
	@Column(name ="IS_BID",nullable=true,length=32)
	public java.lang.String getIsBid(){
		return this.isBid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否经过招标
	 */
	public void setIsBid(java.lang.String isBid){
		this.isBid = isBid;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  组织形式
	 */
	
	@Column(name ="ORG_TYPE",nullable=true,length=32)
	public java.lang.String getOrgType(){
		return this.orgType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  组织形式
	 */
	public void setOrgType(java.lang.String orgType){
		this.orgType = orgType;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  采购方式
	 */
	
	@Column(name ="PURC_TYPE",nullable=true,length=32)
	public java.lang.String getPurcType(){
		return this.purcType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  采购方式
	 */
	public void setPurcType(java.lang.String purcType){
		this.purcType = purcType;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  甲方负责人
	 */
	
	@Column(name ="FIST_PARTY",nullable=true,length=32)
	public java.lang.String getFistParty(){
		return this.fistParty;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  甲方负责人
	 */
	public void setFistParty(java.lang.String fistParty){
		this.fistParty = fistParty;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  对方负责人
	 */
	
	@Column(name ="OP_PERPION",nullable=true,length=32)
	public java.lang.String getOpPerpion(){
		return this.opPerpion;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  对方负责人
	 */
	public void setOpPerpion(java.lang.String opPerpion){
		this.opPerpion = opPerpion;
	}
	
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  开始日期
	 */
	
	@Column(name ="BEGIN_DATE",nullable=true,length=32)
	public java.util.Date getBeginDate(){
		return this.beginDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  开始日期
	 */
	public void setBeginDate(java.util.Date beginDate){
		this.beginDate = beginDate;
	}
	
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  结束日期
	 */
	
	@Column(name ="END_DATE",nullable=true,length=32)
	public java.util.Date getEndDate(){
		return this.endDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  结束日期
	 */
	public void setEndDate(java.util.Date endDate){
		this.endDate = endDate;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  合同分组
	 */
	
	@Column(name ="CON_GROUP",nullable=true,length=32)
	public java.lang.String getConGroup(){
		return this.conGroup;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  合同分组
	 */
	public void setConGroup(java.lang.String conGroup){
		this.conGroup = conGroup;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  变动金额
	 */
	
	@Column(name ="CHANGE_MONEY",nullable=true,length=32)
	public java.lang.String getChangeMoney(){
		return this.changeMoney;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  变动金额
	 */
	public void setChangeMoney(java.lang.String changeMoney){
		this.changeMoney = changeMoney;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  保证金方式
	 */
	
	@Column(name ="BOND_TYPE",nullable=true,length=32)
	public java.lang.String getBondType(){
		return this.bondType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  保证金方式
	 */
	public void setBondType(java.lang.String bondType){
		this.bondType = bondType;
	}
	
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  保证金金额
	 */
	
	@Column(name ="BOND_MONEY",nullable=true,length=32)
	public java.lang.Double getBondMoney(){
		return this.bondMoney;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  保证金金额
	 */
	public void setBondMoney(java.lang.Double bondMoney){
		this.bondMoney = bondMoney;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  父合同
	 */
	
	@Column(name ="PARENT_CON",nullable=true,length=32)
	public java.lang.String getParentCon(){
		return this.parentCon;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  父合同
	 */
	public void setParentCon(java.lang.String parentCon){
		this.parentCon = parentCon;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  收款还是付款
	 */
	
	@Column(name ="PAY_OR_REC",nullable=true,length=32)
	public java.lang.String getPayOrRec(){
		return this.payOrRec;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  收款还是付款
	 */
	public void setPayOrRec(java.lang.String payOrRec){
		this.payOrRec = payOrRec;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  备注
	 */
	
	@Column(name ="REMARK",nullable=true,length=255)
	public java.lang.String getRemark(){
		return this.remark;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  备注
	 */
	public void setRemark(java.lang.String remark){
		this.remark = remark;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否结转
	 */
	
	@Column(name ="IS_FORWARD",nullable=true,length=2)
	public java.lang.String getIsForward(){
		return this.isForward;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否结转
	 */
	public void setIsForward(java.lang.String isForward){
		this.isForward = isForward;
	}
	
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  签订日期
	 */
	
	@Column(name ="CAS_DATE",nullable=true,length=32)
	public java.util.Date getCasDate(){
		return this.casDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  签订日期
	 */
	public void setCasDate(java.util.Date casDate){
		this.casDate = casDate;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  签订人
	 */
	
	@Column(name ="CAS_EMP",nullable=true,length=32)
	public java.lang.String getCasEmp(){
		return this.casEmp;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  签订人
	 */
	public void setCasEmp(java.lang.String casEmp){
		this.casEmp = casEmp;
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
	 *@return: java.lang.String  审核人
	 */
	
	@Column(name ="CONFIRM_EMP",nullable=true,length=32)
	public java.lang.String getConfirmEmp(){
		return this.confirmEmp;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  审核人
	 */
	public void setConfirmEmp(java.lang.String confirmEmp){
		this.confirmEmp = confirmEmp;
	}
	
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  履行时间
	 */
	
	@Column(name ="PERFORM_DATE",nullable=true,length=32)
	public java.util.Date getPerformDate(){
		return this.performDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  履行时间
	 */
	public void setPerformDate(java.util.Date performDate){
		this.performDate = performDate;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  履行人
	 */
	
	@Column(name ="PERFORM_EMP",nullable=true,length=32)
	public java.lang.String getPerformEmp(){
		return this.performEmp;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  履行人
	 */
	public void setPerformEmp(java.lang.String performEmp){
		this.performEmp = performEmp;
	}
	
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  变更日期
	 */
	
	@Column(name ="CHANGE_DATE",nullable=true,length=32)
	public java.util.Date getChangeDate(){
		return this.changeDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  变更日期
	 */
	public void setChangeDate(java.util.Date changeDate){
		this.changeDate = changeDate;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  变更人
	 */
	
	@Column(name ="CHANGE_EMP",nullable=true,length=32)
	public java.lang.String getChangeEmp(){
		return this.changeEmp;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  变更人
	 */
	public void setChangeEmp(java.lang.String changeEmp){
		this.changeEmp = changeEmp;
	}
	
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  终止日期
	 */
	
	@Column(name ="TERMINATION_DATE",nullable=true,length=32)
	public java.util.Date getTerminationDate(){
		return this.terminationDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  终止日期
	 */
	public void setTerminationDate(java.util.Date terminationDate){
		this.terminationDate = terminationDate;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  终止人
	 */
	
	@Column(name ="TERMINATION_EMP",nullable=true,length=32)
	public java.lang.String getTerminationEmp(){
		return this.terminationEmp;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  终止人
	 */
	public void setTerminationEmp(java.lang.String terminationEmp){
		this.terminationEmp = terminationEmp;
	}
	
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  续签日期
	 */
	
	@Column(name ="RENEW_DATE",nullable=true,length=32)
	public java.util.Date getRenewDate(){
		return this.renewDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  续签日期
	 */
	public void setRenewDate(java.util.Date renewDate){
		this.renewDate = renewDate;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  续签人
	 */
	
	@Column(name ="RENEW_EMP",nullable=true,length=32)
	public java.lang.String getRenewEmp(){
		return this.renewEmp;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  续签人
	 */
	public void setRenewEmp(java.lang.String renewEmp){
		this.renewEmp = renewEmp;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  合同状态
	 */
	
	@Column(name ="CONTRACT_STATE",nullable=true,length=32)
	public java.lang.String getContractState(){
		return this.contractState;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  合同状态
	 */
	public void setContractState(java.lang.String contractState){
		this.contractState = contractState;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  供应商编码
	 */
	
	@Column(name ="VEN_CODE",nullable=true,length=32)
	public java.lang.String getVenCode(){
		return this.venCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  供应商编码
	 */
	public void setVenCode(java.lang.String venCode){
		this.venCode = venCode;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  客户档案
	 */
	
	@Column(name ="CUSTOMER_CODE",nullable=true,length=32)
	public java.lang.String getCustomerCode(){
		return this.customerCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  客户档案
	 */
	public void setCustomerCode(java.lang.String customerCode){
		this.customerCode = customerCode;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  采购招标
	 */
	
	@Column(name ="BID_CODE",nullable=true,length=32)
	public java.lang.String getBidCode(){
		return this.bidCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  采购招标
	 */
	public void setBidCode(java.lang.String bidCode){
		this.bidCode = bidCode;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  采购申请
	 */
	
	@Column(name ="PU_RE_CODE",nullable=true,length=32)
	public java.lang.String getPuReCode(){
		return this.puReCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  采购申请
	 */
	public void setPuReCode(java.lang.String puReCode){
		this.puReCode = puReCode;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  网上报销
	 */
	
	@Column(name ="INE_CODE",nullable=true,length=32)
	public java.lang.String getIneCode(){
		return this.ineCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  网上报销
	 */
	public void setIneCode(java.lang.String ineCode){
		this.ineCode = ineCode;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  对方单位
	 */
	
	@Column(name ="OP_COMPY",nullable=true,length=32)
	public java.lang.String getOpCompy(){
		return this.opCompy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  对方单位
	 */
	public void setOpCompy(java.lang.String opCompy){
		this.opCompy = opCompy;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  业务员
	 */
	
	@Column(name ="YW_PERPION",nullable=true,length=32)
	public java.lang.String getYwPerpion(){
		return this.ywPerpion;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  业务员
	 */
	public void setYwPerpion(java.lang.String ywPerpion){
		this.ywPerpion = ywPerpion;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  保修期
	 */
	
	@Column(name ="BX_DATE",nullable=true,length=32)
	public java.lang.String getBxDate(){
		return this.bxDate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  保修期
	 */
	public void setBxDate(java.lang.String bxDate){
		this.bxDate = bxDate;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  合同描述
	 */
	
	@Column(name ="CONTRACT_MS",nullable=true,length=32)
	public java.lang.String getContractMs(){
		return this.contractMs;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  合同描述
	 */
	public void setContractMs(java.lang.String contractMs){
		this.contractMs = contractMs;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建人
	 */
	
	@Column(name ="CR_PERPION",nullable=true,length=32)
	public java.lang.String getCrPerpion(){
		return this.crPerpion;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人
	 */
	public void setCrPerpion(java.lang.String crPerpion){
		this.crPerpion = crPerpion;
	}
	
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  创建日期
	 */
	
	@Column(name ="CR_DATE",nullable=true,length=32)
	public java.util.Date getCrDate(){
		return this.crDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  创建日期
	 */
	public void setCrDate(java.util.Date crDate){
		this.crDate = crDate;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  生效人
	 */
	
	@Column(name ="SX_PERPION",nullable=true,length=32)
	public java.lang.String getSxPerpion(){
		return this.sxPerpion;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  生效人
	 */
	public void setSxPerpion(java.lang.String sxPerpion){
		this.sxPerpion = sxPerpion;
	}
	
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  生效日期
	 */
	
	@Column(name ="SX_DATE",nullable=true,length=32)
	public java.util.Date getSxDate(){
		return this.sxDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  生效日期
	 */
	public void setSxDate(java.util.Date sxDate){
		this.sxDate = sxDate;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  结案人
	 */
	
	@Column(name ="JA_PERPION",nullable=true,length=32)
	public java.lang.String getJaPerpion(){
		return this.jaPerpion;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  结案人
	 */
	public void setJaPerpion(java.lang.String jaPerpion){
		this.jaPerpion = jaPerpion;
	}
	
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  结案日期
	 */
	
	@Column(name ="JA_DATE",nullable=true,length=32)
	public java.util.Date getJaDate(){
		return this.jaDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  结案日期
	 */
	public void setJaDate(java.util.Date jaDate){
		this.jaDate = jaDate;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  质保金比例
	 */
	
	@Column(name ="ZBJ_LATE",nullable=true,length=32)
	public java.lang.String getZbjLate(){
		return this.zbjLate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  质保金比例
	 */
	public void setZbjLate(java.lang.String zbjLate){
		this.zbjLate = zbjLate;
	}
	
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  质保金开始日期
	 */
	
	@Column(name ="ZBJ_START_DATE",nullable=true,length=32)
	public java.util.Date getZbjStartDate(){
		return this.zbjStartDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  质保金开始日期
	 */
	public void setZbjStartDate(java.util.Date zbjStartDate){
		this.zbjStartDate = zbjStartDate;
	}
	
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  质保金结束日期
	 */
	
	@Column(name ="ZBJ_END_DATE",nullable=true,length=32)
	public java.util.Date getZbjEndDate(){
		return this.zbjEndDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  质保金结束日期
	 */
	public void setZbjEndDate(java.util.Date zbjEndDate){
		this.zbjEndDate = zbjEndDate;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  履行状态
	 */
	
	@Column(name ="LX_STATE",nullable=true,length=32)
	public java.lang.String getLxState(){
		return this.lxState;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  履行状态
	 */
	public void setLxState(java.lang.String lxState){
		this.lxState = lxState;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  变更合同号
	 */
	
	@Column(name ="BG_CONTRACT_NO",nullable=true,length=32)
	public java.lang.String getBgContractNo(){
		return this.bgContractNo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  变更合同号
	 */
	public void setBgContractNo(java.lang.String bgContractNo){
		this.bgContractNo = bgContractNo;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  续签合同号
	 */
	
	@Column(name ="XQ_CONTRACT_NO",nullable=true,length=32)
	public java.lang.String getXqContractNo(){
		return this.xqContractNo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  续签合同号
	 */
	public void setXqContractNo(java.lang.String xqContractNo){
		this.xqContractNo = xqContractNo;
	}
	
}
