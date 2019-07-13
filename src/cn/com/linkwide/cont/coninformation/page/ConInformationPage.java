
package cn.com.linkwide.cont.coninformation.page;
import java.util.ArrayList;
import java.util.List;

import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;

import cn.com.linkwide.cont.coninformationdetial.entity.ConMarkEntity;
import cn.com.linkwide.cont.conmemorabilia.entity.ConMemorabiliaEntity;
import cn.com.linkwide.cont.contbank.entity.ContBankEntity;
import cn.com.linkwide.cont.payplan.entity.PayPlanEntity;

/**   
 * @Title: Entity
 * @Description: 合同信息
 * @author onlineGenerator
 * @date 2018-08-23 14:40:50
 * @version V1.0   
 *
 */
public class ConInformationPage implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**创建人名称*/
	/**创建人登录名称*/
	private java.lang.String createBy;
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
	/**合同流水号*/
    @Excel(name="合同流水号",width=20)
	private java.lang.String conNumber;
	/**合同编码*/
    @Excel(name="合同编码",width=20)
	private java.lang.String conNo;
	/**合同名称*/
    @Excel(name="合同名称",width=20)
	private java.lang.String conName;
	/**预留字段10*/
    @Excel(name="合同类型",width=15,dictTable="procol_cont",dicCode="id",dicText="type_name")
	private java.lang.String conExec10;
    /**审批状态*/
    @Excel(name="流程状态",width=15)
	private java.lang.String confermState;
    /**审批日期*/
    @Excel(name="审核日期",width=15,format = "yyyy-MM-dd")
	private java.util.Date confermDate;
	/**合同状态*/
    @Excel(name="合同状态",width=15,dicCode="con_state")
	private java.lang.String conState;
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
    @Excel(name="对方单位",width=20)
	private java.lang.String otherCompy;
	/**对方负责人*/
    @Excel(name="对方负责人",width=15)
	private java.lang.String otherEmp;
	/**会签状态*/
    @Excel(name="手机号",width=15)
	private java.lang.String spl;
    @Excel(name="合同金额",width=15)
	private java.lang.String conExec1;
	/**币种*/
    @Excel(name="币种",width=15 ,dicCode="currency")
	private java.lang.String moneyType;
	/**科室*/
    @Excel(name="科室",width=20,dictTable="t_s_depart",dicCode="id",dicText="departname")
	private java.lang.String deptId;
    /**创建人名称*/
    @Excel(name="起草人",width=15)
	private java.lang.String createName;
    @Excel(name="起草日期",width=15,format = "yyyy-MM-dd")
    private java.util.Date createDate;
    @Excel(name="保修期",width=15)
	private java.lang.String perstate;
	/**质保金周期*/
    @Excel(name="质保金周期(年)",width=15)
	private java.lang.String zbjCycle;
    /**质保金比例*/
    @Excel(name="质保金比例",width=15)
	private java.lang.Double zbjRate;
    /**质保金金额*/
    @Excel(name="质保金金额",width=15)
	private java.lang.Double zbjMoney;
    @Excel(name="解除日期",width=15,format = "yyyy-MM-dd")
	private java.lang.String stateDate3;
    @Excel(name="终止日期",width=15,format = "yyyy-MM-dd")
	private java.lang.String stateDate5;

    @Excel(name="作废日期",width=15,format = "yyyy-MM-dd")
	private java.lang.String stateDate6;
    /**是否变更*/
    @Excel(name="是否变更",width=15,dicCode="whether")
	private java.lang.String isBg;
    /**合同总数*/
    @Excel(name="续签次数",width=15)
	private java.lang.String conNumnerZ;
    @Excel(name="原合同流水号",width=15)
    private java.lang.String execConNo;
    @Excel(name="招标编号",width=15)
    private java.lang.String bidNo;
	/**违约原因*/
    @Excel(name="违约原因",width=15)
	private java.lang.String defoutCase;
    /**是否违约*/
    @Excel(name="是否违约",width=15,dicCode="whether")
	private java.lang.String isDefout;
    /**违约日期*/
    @Excel(name="违约日期",width=15,format = "yyyy-MM-dd")
	private java.util.Date defoutDate;
    /**生效人*/
    
	private java.lang.String effectEmp;
	/**生效日期*/
   
	private java.util.Date effectDate;
	/**结案人*/
 
	private java.lang.String caseEmp;
	/**结案日期*/
     
	private java.util.Date caseDate;

	
	
	/**供应商档案*/
  
	private java.lang.String venId;
	/**客户档案*/
   
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
  

	/**状态日期*/
	private java.lang.String stateDate1;
	/**状态日期*/
	private java.lang.String stateDate2;
	
	/**状态日期*/
	private java.lang.String stateDate4;
	
	/**主合同编码*/
    
	
	
	
	/**合同续签*/
 
	private java.lang.String renewId;

	
	/**附件*/
	private java.lang.String fj;
	
   
	
	/**协议信息*/
   
	private java.lang.String agreeId;
	
	private java.lang.String signId;

	
	/**合同备注*/
	@Excel(name="合同备注" ,width=15)
	private java.lang.String conBz;
	/**合同描述*/
    @Excel(name="合同描述",width=15)
	private java.lang.String conMs;
	
	/**预留字段2*/
	private java.lang.String conExec2;
	/**预留字段3*/
	private java.lang.String conExec3;
	/**预留字段4*/
	private java.lang.String conExec4;
	/**预留字段5*/
	private java.lang.String conExec5;
	/**预留字段6*/
	private java.lang.String conExec6;
	/**预留字段7*/
	private java.lang.String conExec7;
	/**预留字段8*/
	private java.lang.String conExec8;
	/**预留字段9*/
	private java.lang.String conExec9;

	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  主键
	 */
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
	public java.lang.String getStateDate1(){
		return this.stateDate1;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  状态日期
	 */
	public void setStateDate1(java.lang.String stateDate1){
		this.stateDate1 = stateDate1;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  状态日期
	 */
	public java.lang.String getStateDate2(){
		return this.stateDate2;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  状态日期
	 */
	public void setStateDate2(java.lang.String stateDate2){
		this.stateDate2 = stateDate2;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  状态日期
	 */
	public java.lang.String getStateDate3(){
		return this.stateDate3;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  状态日期
	 */
	public void setStateDate3(java.lang.String stateDate3){
		this.stateDate3 = stateDate3;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  状态日期
	 */
	public java.lang.String getStateDate4(){
		return this.stateDate4;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  状态日期
	 */
	public void setStateDate4(java.lang.String stateDate4){
		this.stateDate4 = stateDate4;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  状态日期
	 */
	public java.lang.String getStateDate5(){
		return this.stateDate5;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  状态日期
	 */
	public void setStateDate5(java.lang.String stateDate5){
		this.stateDate5 = stateDate5;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  状态日期
	 */
	public java.lang.String getStateDate6(){
		return this.stateDate6;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  状态日期
	 */
	public void setStateDate6(java.lang.String stateDate6){
		this.stateDate6 = stateDate6;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  主合同编码
	 */
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
	public java.lang.String getConExec1(){
		return this.conExec1;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预留字段1
	 */
	public void setConExec1(java.lang.String conExec1){
		this.conExec1 = conExec1;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预留字段2
	 */
	public java.lang.String getConExec2(){
		return this.conExec2;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预留字段2
	 */
	public void setConExec2(java.lang.String conExec2){
		this.conExec2 = conExec2;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预留字段3
	 */
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
	

		public java.lang.String getBidNo() {
		return bidNo;
	}

	public void setBidNo(java.lang.String bidNo) {
		this.bidNo = bidNo;
	}


		/**保存-合同标的*/
		
		private List<ConMarkEntity> conMarkList = new ArrayList<ConMarkEntity>();
		public List<ConMarkEntity> getConMarkList() {
		return conMarkList;
		}
		public void setConMarkList(List<ConMarkEntity> conMarkList) {
		this.conMarkList = conMarkList;
		}
	
 		private List<PayPlanEntity> payPlanList = new ArrayList<PayPlanEntity>();
		public List<PayPlanEntity> getPayPlanList() {
			return payPlanList;
		}

		public void setPayPlanList(List<PayPlanEntity> payPlanList) {
			this.payPlanList = payPlanList;
		}

	
	 	private List<ConMemorabiliaEntity> conMemorabiliaList=new ArrayList<ConMemorabiliaEntity>();
	 	public List<ConMemorabiliaEntity> getConMemorabiliaList(){
		 return conMemorabiliaList;
	 	}
	 	public void setConMemorabiliaList(List<ConMemorabiliaEntity> conMemorabiliaList){
		 this.conMemorabiliaList=conMemorabiliaList;
	 	}
	 	
	 	private List<ContBankEntity> contBankList=new ArrayList<ContBankEntity>();
		 public List<ContBankEntity> getContBankList(){
			 return contBankList;
		 }
		 public void setContBankList(List<ContBankEntity> contBankList){
			 this.contBankList=contBankList;
		 }
}
