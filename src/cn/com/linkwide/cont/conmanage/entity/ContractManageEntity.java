package cn.com.linkwide.cont.conmanage.entity;

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
 * @Description: 合同信息表
 * @author onlineGenerator
 * @date 2018-06-01 17:40:33
 * @version V1.0   
 *
 */
@Entity
@Table(name = "contract_manage", schema = "")
@SuppressWarnings("serial")
public class ContractManageEntity implements java.io.Serializable {
	/**id*/
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
	/**合同编号*/
	@Excel(name="合同编号",width=15)
	private java.lang.String code;
	/**合同名称*/
	@Excel(name="合同名称",width=15)
	private java.lang.String name;
	/**合同类别*/
	@Excel(name="合同类别",width=15)
	private java.lang.String conType;
	/**签订日期*/
	@Excel(name="签订日期",width=15,format = "yyyy-MM-dd")
	private java.util.Date signDate;
	/**签订科室*/
	@Excel(name="签订科室",width=15)
	private java.lang.String dept;
	/**签订金额*/
	@Excel(name="签订金额",width=15)
	private java.lang.Double amountMoney;
	/**医院编码*/
	@Excel(name="医院编码",width=15)
	private java.lang.String hospId;
	/**收付款方式*/
	@Excel(name="收付款方式",width=15)
	private java.lang.String payMeth;
	/**项目编码*/
	@Excel(name="项目编码",width=15)
	private java.lang.String itemId;
	/**供应商编码*/
	@Excel(name="供应商编码",width=15)
	private java.lang.String venId;
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
	/**乙方负责人*/
	@Excel(name="乙方负责人",width=15)
	private java.lang.String secondParty;
	/**合同开始日期*/
	@Excel(name="合同开始日期",width=15,format = "yyyy-MM-dd")
	private java.util.Date beginDate;
	/**合同截止日期*/
	@Excel(name="合同截止日期",width=15,format = "yyyy-MM-dd")
	private java.util.Date endDate;
	/**合同分组*/
	@Excel(name="合同分组",width=15)
	private java.lang.String conGroup;
	/**变动金额*/
	@Excel(name="变动金额",width=15)
	private java.lang.Double changeMoney;
	/**保证金方式*/
	@Excel(name="保证金方式",width=15)
	private java.lang.String bondType;
	/**保证金额*/
	@Excel(name="保证金额",width=15)
	private java.lang.Double bondMoney;
	/**备注*/
	@Excel(name="备注",width=15)
	private java.lang.String remark;
	/**父合同编码*/
	@Excel(name="父合同编码",width=15)
	private java.lang.String parentCon;
	/**收款还是付款*/
	@Excel(name="收款还是付款",width=15)
	private java.lang.String payOrRec;
	/**收款还是付款*/
	@Excel(name="是否期初",width=2)
	private java.lang.String isEarly;
	
	@Column(name ="IS_EARLY",nullable=true,length=50)
	public java.lang.String getIsEarly() {
		return isEarly;
	}

	public void setIsEarly(java.lang.String isEarly) {
		this.isEarly = isEarly;
	}

	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  id
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
	 *@param: java.lang.String  id
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
	 *@return: java.lang.String  合同编号
	 */

	@Column(name ="CODE",nullable=true,length=32)
	public java.lang.String getCode(){
		return this.code;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  合同编号
	 */
	public void setCode(java.lang.String code){
		this.code = code;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  合同名称
	 */

	@Column(name ="NAME",nullable=true,length=100)
	public java.lang.String getName(){
		return this.name;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  合同名称
	 */
	public void setName(java.lang.String name){
		this.name = name;
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
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  签订日期
	 */

	@Column(name ="SIGN_DATE",nullable=true)
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
	 *@return: java.lang.String  签订科室
	 */

	@Column(name ="DEPT",nullable=true,length=32)
	public java.lang.String getDept(){
		return this.dept;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  签订科室
	 */
	public void setDept(java.lang.String dept){
		this.dept = dept;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  签订金额
	 */

	@Column(name ="AMOUNT_MONEY",nullable=true,length=22)
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
	 *@return: java.lang.String  项目编码
	 */

	@Column(name ="ITEM_ID",nullable=true,length=32)
	public java.lang.String getItemId(){
		return this.itemId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  项目编码
	 */
	public void setItemId(java.lang.String itemId){
		this.itemId = itemId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  供应商编码
	 */

	@Column(name ="VEN_ID",nullable=true,length=32)
	public java.lang.String getVenId(){
		return this.venId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  供应商编码
	 */
	public void setVenId(java.lang.String venId){
		this.venId = venId;
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

	@Column(name ="IS_BID",nullable=true,length=2)
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
	 *@return: java.lang.String  乙方负责人
	 */

	@Column(name ="SECOND_PARTY",nullable=true,length=32)
	public java.lang.String getSecondParty(){
		return this.secondParty;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  乙方负责人
	 */
	public void setSecondParty(java.lang.String secondParty){
		this.secondParty = secondParty;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  合同开始日期
	 */

	@Column(name ="BEGIN_DATE",nullable=true)
	public java.util.Date getBeginDate(){
		return this.beginDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  合同开始日期
	 */
	public void setBeginDate(java.util.Date beginDate){
		this.beginDate = beginDate;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  合同截止日期
	 */

	@Column(name ="END_DATE",nullable=true)
	public java.util.Date getEndDate(){
		return this.endDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  合同截止日期
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
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  变动金额
	 */

	@Column(name ="CHANGE_MONEY",nullable=true,length=22)
	public java.lang.Double getChangeMoney(){
		return this.changeMoney;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  变动金额
	 */
	public void setChangeMoney(java.lang.Double changeMoney){
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
	 *@return: java.lang.Double  保证金额
	 */

	@Column(name ="BOND_MONEY",nullable=true,length=22)
	public java.lang.Double getBondMoney(){
		return this.bondMoney;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  保证金额
	 */
	public void setBondMoney(java.lang.Double bondMoney){
		this.bondMoney = bondMoney;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  备注
	 */

	@Column(name ="REMARK",nullable=true,length=200)
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
	 *@return: java.lang.String  父合同编码
	 */

	@Column(name ="PARENT_CON",nullable=true,length=32)
	public java.lang.String getParentCon(){
		return this.parentCon;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  父合同编码
	 */
	public void setParentCon(java.lang.String parentCon){
		this.parentCon = parentCon;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  收款还是付款
	 */

	@Column(name ="PAY_OR_REC",nullable=false,length=2)
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
}
