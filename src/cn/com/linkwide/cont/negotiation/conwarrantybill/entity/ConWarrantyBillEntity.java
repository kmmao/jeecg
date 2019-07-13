package cn.com.linkwide.cont.negotiation.conwarrantybill.entity;

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
 * @Description: 议标保修单
 * @author onlineGenerator
 * @date 2019-04-20 11:37:21
 * @version V1.0   
 *
 */
@Entity
@Table(name = "con_warranty_bill", schema = "")
@SuppressWarnings("serial")
public class ConWarrantyBillEntity implements java.io.Serializable {
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
	/**申请科室*/
	@Excel(name="申请科室",width=15)
	private java.lang.String deptCode;
	/**申请科室名称*/
	private java.lang.String deptName;
	/**申请日期*/
	@Excel(name="申请日期",width=15,format = "yyyy-MM-dd")
	private java.util.Date applyDate;
	/**设备编码*/
	@Excel(name="设备编码",width=15)
	private java.lang.String equCode;
	/**设备名称*/
	@Excel(name="设备名称",width=15)
	private java.lang.String equName;
	/**资产卡片*/
	@Excel(name="资产卡片",width=15)
	private java.lang.String equCard;
	/**规格型号*/
	@Excel(name="规格型号",width=15)
	private java.lang.String specType;
	/**采购价格*/
	@Excel(name="采购价格",width=15)
	private java.lang.String purPrice;
	/**购置日期*/
	@Excel(name="购置日期",width=15,format = "yyyy-MM-dd")
	private java.util.Date purDate;
	/**生产厂家*/
	@Excel(name="生产厂家",width=15)
	private java.lang.String birthFactory;
	/**设备使用用途*/
	@Excel(name="设备使用用途",width=15)
	private java.lang.String useType;
	/**是否收费设备*/
	@Excel(name="是否收费设备",width=15)
	private java.lang.String isCharge;
	/**每次例检平均费用*/
	@Excel(name="每次例检平均费用",width=15)
	private java.lang.String averageCost;
	/**年检量*/
	@Excel(name="年检量",width=15)
	private java.lang.String annualCheckNumber;
	/**年总收入*/
	@Excel(name="年总收入",width=15)
	private java.lang.String annualIncome;
	/**本次保修申请类别*/
	@Excel(name="本次保修申请类别",width=15)
	private java.lang.String applyCategory;
	/**保修类型*/
	@Excel(name="保修类型",width=15)
	private java.lang.String guaranteeType;
	/**保修其他类型*/
	@Excel(name="保修其他类型",width=15)
	private java.lang.String guaranteeTypeQt;
	/**保修年限*/
	@Excel(name="保修年限",width=15)
	private java.lang.String guaranteeYears;
	/**其他年限*/
	private java.lang.String guaranteeYearsQt;
	/**厂家选择*/
	@Excel(name="厂家选择",width=15)
	private java.lang.String factoryChoice;
	/**厂家选择其他*/
	private java.lang.String factoryChoiceQt;
	/**费用承担组别*/
	@Excel(name="费用承担组别",width=15)
	private java.lang.String costGroup;
	/**科室消耗*/
	@Excel(name="科室消耗",width=15)
	private java.lang.String deptCost;
	/**消耗类别*/
	private java.lang.String deptCostType;
	/**消耗类别其他*/
	private java.lang.String deptCostTypeQt;
	/**科研经费*/
	@Excel(name="科研经费",width=15)
	private java.lang.String costResearch;
	/**经费名称*/
	@Excel(name="经费名称",width=15)
	private java.lang.String researchName;
	/**经费本号*/
	@Excel(name="经费本号",width=15)
	private java.lang.String researchVersion;
	/**医院承担*/
	@Excel(name="医院承担",width=15)
	private java.lang.String hosResearch;
	/**科室负责人*/
	@Excel(name="科室负责人",width=15)
	private java.lang.String deptEmp;
	/**负责人联系方式*/
	@Excel(name="负责人联系方式",width=15)
	private java.lang.String empTel;
	/**设备用途备用*/
	@Excel(name="设备用途备用",width=15)
	private java.lang.String useTypeBy;
	/**保修申请备用*/
	private java.lang.String applyCategoryBy;
	/**技术保修*/
	private java.lang.String guaranteeTypeJsb;
	/**全保*/
	private java.lang.String guaranteeTypeQb;
	/**原厂*/
	private java.lang.String factoryChoiceYc;
	/**三方厂*/
	private java.lang.String factoryChoiceSf;
	/**厂商备用*/
	private java.lang.String factoryChoiceBy;
	/*已占用科室消耗选择框*/
	private java.lang.String extend1;
	/**扩展字段2*/
	private java.lang.String extend2;
	/**扩展字段3*/
	private java.lang.String extend3;
	/**扩展字段4*/
	private java.lang.String extend4;
	/*已占用存部门编码*/
	private java.lang.String extend5;
	/**申请单号*/
	@Excel(name="申请单号",width=15)
	private java.lang.String applyCode;
	/**审核人*/
	private java.lang.String confirmEmp;
	/**单据状态*/
	private java.lang.String billState;
	/**审核日期*/
	private java.util.Date confirmDate;
	/**是否发布*/
	private java.lang.String isRelease;
	/**发布时间*/
	private java.util.Date releaseDate;
	/**开始日期*/
	private java.util.Date startDate;
	/**结束日期*/
	private java.util.Date endDate;
	
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
	 *@return: java.lang.String  申请科室
	 */

	@Column(name ="DEPT_CODE",nullable=true,length=32)
	public java.lang.String getDeptCode(){
		return this.deptCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  申请科室
	 */
	public void setDeptCode(java.lang.String deptCode){
		this.deptCode = deptCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  申请科室名称
	 */

	@Column(name ="DEPT_NAME",nullable=true,length=32)
	public java.lang.String getDeptName(){
		return this.deptName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  申请科室名称
	 */
	public void setDeptName(java.lang.String deptName){
		this.deptName = deptName;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  申请日期
	 */

	@Column(name ="APPLY_DATE",nullable=true,length=32)
	public java.util.Date getApplyDate(){
		return this.applyDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  申请日期
	 */
	public void setApplyDate(java.util.Date applyDate){
		this.applyDate = applyDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  设备编码
	 */

	@Column(name ="EQU_CODE",nullable=true,length=32)
	public java.lang.String getEquCode(){
		return this.equCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  设备编码
	 */
	public void setEquCode(java.lang.String equCode){
		this.equCode = equCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  设备名称
	 */

	@Column(name ="EQU_NAME",nullable=true,length=32)
	public java.lang.String getEquName(){
		return this.equName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  设备名称
	 */
	public void setEquName(java.lang.String equName){
		this.equName = equName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  资产卡片
	 */

	@Column(name ="EQU_CARD",nullable=true,length=32)
	public java.lang.String getEquCard(){
		return this.equCard;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  资产卡片
	 */
	public void setEquCard(java.lang.String equCard){
		this.equCard = equCard;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  规格型号
	 */

	@Column(name ="SPEC_TYPE",nullable=true,length=32)
	public java.lang.String getSpecType(){
		return this.specType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  规格型号
	 */
	public void setSpecType(java.lang.String specType){
		this.specType = specType;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  采购价格
	 */

	@Column(name ="PUR_PRICE",nullable=true,length=32)
	public java.lang.String getPurPrice(){
		return this.purPrice;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  采购价格
	 */
	public void setPurPrice(java.lang.String purPrice){
		this.purPrice = purPrice;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  购置日期
	 */

	@Column(name ="PUR_DATE",nullable=true,length=32)
	public java.util.Date getPurDate(){
		return this.purDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  购置日期
	 */
	public void setPurDate(java.util.Date purDate){
		this.purDate = purDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  生产厂家
	 */

	@Column(name ="BIRTH_FACTORY",nullable=true,length=50)
	public java.lang.String getBirthFactory(){
		return this.birthFactory;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  生产厂家
	 */
	public void setBirthFactory(java.lang.String birthFactory){
		this.birthFactory = birthFactory;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  设备使用用途
	 */

	@Column(name ="USE_TYPE",nullable=true,length=32)
	public java.lang.String getUseType(){
		return this.useType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  设备使用用途
	 */
	public void setUseType(java.lang.String useType){
		this.useType = useType;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否收费设备
	 */

	@Column(name ="IS_CHARGE",nullable=true,length=32)
	public java.lang.String getIsCharge(){
		return this.isCharge;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否收费设备
	 */
	public void setIsCharge(java.lang.String isCharge){
		this.isCharge = isCharge;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  每次例检平均费用
	 */

	@Column(name ="AVERAGE_COST",nullable=true,length=32)
	public java.lang.String getAverageCost(){
		return this.averageCost;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  每次例检平均费用
	 */
	public void setAverageCost(java.lang.String averageCost){
		this.averageCost = averageCost;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  年检量
	 */

	@Column(name ="ANNUAL_CHECK_NUMBER",nullable=true,length=32)
	public java.lang.String getAnnualCheckNumber(){
		return this.annualCheckNumber;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  年检量
	 */
	public void setAnnualCheckNumber(java.lang.String annualCheckNumber){
		this.annualCheckNumber = annualCheckNumber;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  年总收入
	 */

	@Column(name ="ANNUAL_INCOME",nullable=true,length=32)
	public java.lang.String getAnnualIncome(){
		return this.annualIncome;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  年总收入
	 */
	public void setAnnualIncome(java.lang.String annualIncome){
		this.annualIncome = annualIncome;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  本次保修申请类别
	 */

	@Column(name ="APPLY_CATEGORY",nullable=true,length=32)
	public java.lang.String getApplyCategory(){
		return this.applyCategory;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  本次保修申请类别
	 */
	public void setApplyCategory(java.lang.String applyCategory){
		this.applyCategory = applyCategory;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  保修类型
	 */

	@Column(name ="GUARANTEE_TYPE",nullable=true,length=32)
	public java.lang.String getGuaranteeType(){
		return this.guaranteeType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  保修类型
	 */
	public void setGuaranteeType(java.lang.String guaranteeType){
		this.guaranteeType = guaranteeType;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  保修其他类型
	 */

	@Column(name ="GUARANTEE_TYPE_QT",nullable=true,length=32)
	public java.lang.String getGuaranteeTypeQt(){
		return this.guaranteeTypeQt;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  保修其他类型
	 */
	public void setGuaranteeTypeQt(java.lang.String guaranteeTypeQt){
		this.guaranteeTypeQt = guaranteeTypeQt;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  保修年限
	 */

	@Column(name ="GUARANTEE_YEARS",nullable=true,length=32)
	public java.lang.String getGuaranteeYears(){
		return this.guaranteeYears;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  保修年限
	 */
	public void setGuaranteeYears(java.lang.String guaranteeYears){
		this.guaranteeYears = guaranteeYears;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  其他年限
	 */

	@Column(name ="GUARANTEE_YEARS_QT",nullable=true,length=32)
	public java.lang.String getGuaranteeYearsQt(){
		return this.guaranteeYearsQt;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  其他年限
	 */
	public void setGuaranteeYearsQt(java.lang.String guaranteeYearsQt){
		this.guaranteeYearsQt = guaranteeYearsQt;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  厂家选择
	 */

	@Column(name ="FACTORY_CHOICE",nullable=true,length=32)
	public java.lang.String getFactoryChoice(){
		return this.factoryChoice;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  厂家选择
	 */
	public void setFactoryChoice(java.lang.String factoryChoice){
		this.factoryChoice = factoryChoice;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  厂家选择其他
	 */

	@Column(name ="FACTORY_CHOICE_QT",nullable=true,length=32)
	public java.lang.String getFactoryChoiceQt(){
		return this.factoryChoiceQt;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  厂家选择其他
	 */
	public void setFactoryChoiceQt(java.lang.String factoryChoiceQt){
		this.factoryChoiceQt = factoryChoiceQt;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  费用承担组别
	 */

	@Column(name ="COST_GROUP",nullable=true,length=32)
	public java.lang.String getCostGroup(){
		return this.costGroup;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  费用承担组别
	 */
	public void setCostGroup(java.lang.String costGroup){
		this.costGroup = costGroup;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  科室消耗
	 */

	@Column(name ="DEPT_COST",nullable=true,length=32)
	public java.lang.String getDeptCost(){
		return this.deptCost;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  科室消耗
	 */
	public void setDeptCost(java.lang.String deptCost){
		this.deptCost = deptCost;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  消耗类别
	 */

	@Column(name ="DEPT_COST_TYPE",nullable=true,length=32)
	public java.lang.String getDeptCostType(){
		return this.deptCostType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  消耗类别
	 */
	public void setDeptCostType(java.lang.String deptCostType){
		this.deptCostType = deptCostType;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  消耗类别其他
	 */

	@Column(name ="DEPT_COST_TYPE_QT",nullable=true,length=32)
	public java.lang.String getDeptCostTypeQt(){
		return this.deptCostTypeQt;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  消耗类别其他
	 */
	public void setDeptCostTypeQt(java.lang.String deptCostTypeQt){
		this.deptCostTypeQt = deptCostTypeQt;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  科研经费
	 */

	@Column(name ="COST_RESEARCH",nullable=true,length=32)
	public java.lang.String getCostResearch(){
		return this.costResearch;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  科研经费
	 */
	public void setCostResearch(java.lang.String costResearch){
		this.costResearch = costResearch;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  经费名称
	 */

	@Column(name ="RESEARCH_NAME",nullable=true,length=50)
	public java.lang.String getResearchName(){
		return this.researchName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  经费名称
	 */
	public void setResearchName(java.lang.String researchName){
		this.researchName = researchName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  经费本号
	 */

	@Column(name ="RESEARCH_VERSION",nullable=true,length=32)
	public java.lang.String getResearchVersion(){
		return this.researchVersion;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  经费本号
	 */
	public void setResearchVersion(java.lang.String researchVersion){
		this.researchVersion = researchVersion;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  医院承担
	 */

	@Column(name ="HOS_RESEARCH",nullable=true,length=32)
	public java.lang.String getHosResearch(){
		return this.hosResearch;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  医院承担
	 */
	public void setHosResearch(java.lang.String hosResearch){
		this.hosResearch = hosResearch;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  科室负责人
	 */

	@Column(name ="DEPT_EMP",nullable=true,length=32)
	public java.lang.String getDeptEmp(){
		return this.deptEmp;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  科室负责人
	 */
	public void setDeptEmp(java.lang.String deptEmp){
		this.deptEmp = deptEmp;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  负责人联系方式
	 */

	@Column(name ="EMP_TEL",nullable=true,length=32)
	public java.lang.String getEmpTel(){
		return this.empTel;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  负责人联系方式
	 */
	public void setEmpTel(java.lang.String empTel){
		this.empTel = empTel;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  设备用途备用
	 */

	@Column(name ="USE_TYPE_BY",nullable=true,length=32)
	public java.lang.String getUseTypeBy(){
		return this.useTypeBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  设备用途备用
	 */
	public void setUseTypeBy(java.lang.String useTypeBy){
		this.useTypeBy = useTypeBy;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  保修申请备用
	 */

	@Column(name ="APPLY_CATEGORY_BY",nullable=true,length=32)
	public java.lang.String getApplyCategoryBy(){
		return this.applyCategoryBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  保修申请备用
	 */
	public void setApplyCategoryBy(java.lang.String applyCategoryBy){
		this.applyCategoryBy = applyCategoryBy;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  技术保修
	 */

	@Column(name ="GUARANTEE_TYPE_JSB",nullable=true,length=32)
	public java.lang.String getGuaranteeTypeJsb(){
		return this.guaranteeTypeJsb;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  技术保修
	 */
	public void setGuaranteeTypeJsb(java.lang.String guaranteeTypeJsb){
		this.guaranteeTypeJsb = guaranteeTypeJsb;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  全保
	 */

	@Column(name ="GUARANTEE_TYPE_QB",nullable=true,length=32)
	public java.lang.String getGuaranteeTypeQb(){
		return this.guaranteeTypeQb;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  全保
	 */
	public void setGuaranteeTypeQb(java.lang.String guaranteeTypeQb){
		this.guaranteeTypeQb = guaranteeTypeQb;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  原厂
	 */

	@Column(name ="FACTORY_CHOICE_YC",nullable=true,length=32)
	public java.lang.String getFactoryChoiceYc(){
		return this.factoryChoiceYc;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  原厂
	 */
	public void setFactoryChoiceYc(java.lang.String factoryChoiceYc){
		this.factoryChoiceYc = factoryChoiceYc;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  三方厂
	 */

	@Column(name ="FACTORY_CHOICE_SF",nullable=true,length=32)
	public java.lang.String getFactoryChoiceSf(){
		return this.factoryChoiceSf;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  三方厂
	 */
	public void setFactoryChoiceSf(java.lang.String factoryChoiceSf){
		this.factoryChoiceSf = factoryChoiceSf;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  厂商备用
	 */

	@Column(name ="FACTORY_CHOICE_BY",nullable=true,length=32)
	public java.lang.String getFactoryChoiceBy(){
		return this.factoryChoiceBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  厂商备用
	 */
	public void setFactoryChoiceBy(java.lang.String factoryChoiceBy){
		this.factoryChoiceBy = factoryChoiceBy;
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
	 *@return: java.lang.String  申请单号
	 */

	@Column(name ="APPLY_CODE",nullable=true,length=32)
	public java.lang.String getApplyCode(){
		return this.applyCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  申请单号
	 */
	public void setApplyCode(java.lang.String applyCode){
		this.applyCode = applyCode;
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
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  单据状态
	 */

	@Column(name ="BILL_STATE",nullable=true,length=32)
	public java.lang.String getBillState(){
		return this.billState;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  单据状态
	 */
	public void setBillState(java.lang.String billState){
		this.billState = billState;
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
	 *@return: java.lang.String  是否发布
	 */

	@Column(name ="IS_RELEASE",nullable=true,length=32)
	public java.lang.String getIsRelease(){
		return this.isRelease;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否发布
	 */
	public void setIsRelease(java.lang.String isRelease){
		this.isRelease = isRelease;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  发布时间
	 */

	@Column(name ="RELEASE_DATE",nullable=true,length=32)
	public java.util.Date getReleaseDate(){
		return this.releaseDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  发布时间
	 */
	public void setReleaseDate(java.util.Date releaseDate){
		this.releaseDate = releaseDate;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  开始日期
	 */

	@Column(name ="START_DATE",nullable=true,length=32)
	public java.util.Date getStartDate(){
		return this.startDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  开始日期
	 */
	public void setStartDate(java.util.Date startDate){
		this.startDate = startDate;
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
}
