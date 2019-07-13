package cn.com.linkwide.ba.budg.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

import cn.com.linkwide.common.delcheck.annotation.DetailedEntity;

/**   
 * @Title: Entity
 * @Description: 支出项目
 * @author onlineGenerator
 * @date 2018-07-27 11:07:30
 * @version V1.0   
 *
 */
@Entity
@Table(name = "budg_expend_item", schema = "")
@SuppressWarnings("serial")
@DetailedEntity(strEntity={"BudgExpendOrgBEntity","BudgExpendHisEntity"})
public class BudgExpendItemEntity implements java.io.Serializable {
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
	/**项目档案id*/
	private java.lang.String baItemId;
	/**支出项目编码*/
	@Excel(name="支出项目编码",width=15)
	private java.lang.String itemCode;
	/**支出项目名称*/
	@Excel(name="支出项目名称",width=20)
	private java.lang.String itemName;
	/**上级项目*/
	@Excel(name="上级项目编码",width=20)
	private java.lang.String parentCode;
	/**支出项目分类*/
	private java.lang.String itemType;
	/**归口科室*/
	private java.lang.String manaDept;
	/**编制方法*/
	private java.lang.String compMethod;
	/**编制公式*/
	private java.lang.String calFormula;
	/**会计科目*/
	private java.lang.String acctSubj;
	/**数据来源*/
	private java.lang.String source;
	/**控制程度*/
	private java.lang.String controlDegree;
	/**是否末级*/
	@Excel(name="是否末级",width=15,replace={"是_Y","否_N"})
	private java.lang.String isLast;
	/**是否末级*/
	@Excel(name="是否停用",width=15,replace={"是_Y","否_N"})
	private java.lang.String extend3;
	/**编制级次*/
	private java.lang.String budgLevel;
	/**所属类型*/
	@Excel(name="所属类型",width=15,databaseFormat="itemType")
	private java.lang.String extend1;
	/**项目说明*/
	@Excel(name="项目说明",width=15)
	private java.lang.String extend2;
	/**备注*/
	@Excel(name="备注",width=15)
	private java.lang.String remark;
	/**年度*/
	private java.lang.Integer budgYear;
	/**业务科室*/
	private java.lang.String deptCode;
	
	/**测算公式英文*/
	private java.lang.String calFormulaEng;
	/**扩展字段4*/
	private java.lang.String extend4;
	/**扩展字段5*/
	private java.lang.String extend5;
	/**扩展字段6*/
	private java.lang.String extend6;
	
	
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
	 *@return: java.lang.String  项目档案id
	 */

	@Column(name ="BA_ITEM_ID",nullable=true,length=64)
	public java.lang.String getBaItemId(){
		return this.baItemId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  项目档案id
	 */
	public void setBaItemId(java.lang.String baItemId){
		this.baItemId = baItemId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  支出项目编码
	 */

	@Column(name ="ITEM_CODE",nullable=true,length=64)
	public java.lang.String getItemCode(){
		return this.itemCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  支出项目编码
	 */
	public void setItemCode(java.lang.String itemCode){
		this.itemCode = itemCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  收入项目名称
	 */

	@Column(name ="ITEM_NAME",nullable=true,length=64)
	public java.lang.String getItemName(){
		return this.itemName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  收入项目名称
	 */
	public void setItemName(java.lang.String itemName){
		this.itemName = itemName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  收入项目分类
	 */

	@Column(name ="ITEM_TYPE",nullable=true,length=64)
	public java.lang.String getItemType(){
		return this.itemType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  收入项目分类
	 */
	public void setItemType(java.lang.String itemType){
		this.itemType = itemType;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  归口科室
	 */

	@Column(name ="MANA_DEPT",nullable=true,length=64)
	public java.lang.String getManaDept(){
		return this.manaDept;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  归口科室
	 */
	public void setManaDept(java.lang.String manaDept){
		this.manaDept = manaDept;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  编制公式
	 */

	@Column(name ="CAL_FORMULA",nullable=true,length=64)
	public java.lang.String getCalFormula(){
		return this.calFormula;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  编制公式
	 */
	public void setCalFormula(java.lang.String calFormula){
		this.calFormula = calFormula;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  会计科目
	 */

	@Column(name ="ACCT_SUBJ",nullable=true,length=64)
	public java.lang.String getAcctSubj(){
		return this.acctSubj;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  会计科目
	 */
	public void setAcctSubj(java.lang.String acctSubj){
		this.acctSubj = acctSubj;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  数据来源
	 */

	@Column(name ="SOURCE",nullable=true,length=64)
	public java.lang.String getSource(){
		return this.source;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  数据来源
	 */
	public void setSource(java.lang.String source){
		this.source = source;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  控制程度
	 */

	@Column(name ="CONTROL_DEGREE",nullable=true,length=64)
	public java.lang.String getControlDegree(){
		return this.controlDegree;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  控制程度
	 */
	public void setControlDegree(java.lang.String controlDegree){
		this.controlDegree = controlDegree;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否末级
	 */

	@Column(name ="IS_LAST",nullable=true,length=64)
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
	 *@return: java.lang.String  编制级次
	 */

	@Column(name ="BUDG_LEVEL",nullable=true,length=64)
	public java.lang.String getBudgLevel(){
		return this.budgLevel;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  编制级次
	 */
	public void setBudgLevel(java.lang.String budgLevel){
		this.budgLevel = budgLevel;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  备注
	 */

	@Column(name ="REMARK",nullable=true,length=64)
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
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  年度
	 */

	@Column(name ="BUDG_YEAR",nullable=true,length=11)
	public java.lang.Integer getBudgYear(){
		return this.budgYear;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  年度
	 */
	public void setBudgYear(java.lang.Integer budgYear){
		this.budgYear = budgYear;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  业务科室
	 */

	@Column(name ="DEPT_CODE",nullable=true,length=500)
	public java.lang.String getDeptCode(){
		return this.deptCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  业务科室
	 */
	public void setDeptCode(java.lang.String deptCode){
		this.deptCode = deptCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  上级项目
	 */

	@Column(name ="PARENT_CODE",nullable=true,length=32)
	public java.lang.String getParentCode(){
		return this.parentCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  上级项目
	 */
	public void setParentCode(java.lang.String parentCode){
		this.parentCode = parentCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  测算公式英文
	 */

	@Column(name ="CAL_FORMULA_ENG",nullable=true,length=255)
	public java.lang.String getCalFormulaEng(){
		return this.calFormulaEng;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  测算公式英文
	 */
	public void setCalFormulaEng(java.lang.String calFormulaEng){
		this.calFormulaEng = calFormulaEng;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  扩展字段1
	 */

	@Column(name ="EXTEND1",nullable=true,length=255)
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

	@Column(name ="EXTEND2",nullable=true,length=255)
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

	@Column(name ="EXTEND3",nullable=true,length=255)
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

	@Column(name ="EXTEND4",nullable=true,length=255)
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

	@Column(name ="EXTEND5",nullable=true,length=255)
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

	@Column(name ="EXTEND6",nullable=true,length=255)
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
	 *@return: java.lang.String  编制方法
	 */

	@Column(name ="COMP_METHOD",nullable=true,length=32)
	public java.lang.String getCompMethod(){
		return this.compMethod;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  编制方法
	 */
	public void setCompMethod(java.lang.String compMethod){
		this.compMethod = compMethod;
	}
}
