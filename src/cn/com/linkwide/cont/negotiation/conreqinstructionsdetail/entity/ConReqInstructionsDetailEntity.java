package cn.com.linkwide.cont.negotiation.conreqinstructionsdetail.entity;
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
 * @Description: 请示单明细
 * @author onlineGenerator
 * @date 2019-04-26 11:11:09
 * @version V1.0   
 *
 */
@Entity
@Table(name = "con_req_instructions_detail", schema = "")
@SuppressWarnings("serial")
public class ConReqInstructionsDetailEntity implements java.io.Serializable {
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
	/**设备名称*/
    @Excel(name="设备名称",width=15)
	private java.lang.String equName;
	/**保修类型*/
    @Excel(name="保修类型",width=15)
	private java.lang.String guaranteeType;
	/**供应商id*/
    @Excel(name="供应商id",width=15)
	private java.lang.String venId;
	/**供应商名称*/
    @Excel(name="供应商名称",width=15)
	private java.lang.String venName;
	/**部门id*/
    @Excel(name="部门id",width=15)
	private java.lang.String deptId;
	/**部门名称*/
    @Excel(name="部门名称",width=15)
	private java.lang.String deptName;
	/**供应商报价*/
    @Excel(name="供应商报价",width=15)
	private java.lang.String offerPrice;
	/**采购价格*/
    @Excel(name="采购价格",width=15)
	private java.lang.String purPrice;
	/**服务内容*/
    @Excel(name="服务内容",width=15)
	private java.lang.String serviceConten;
	/**费用占比*/
    @Excel(name="费用占比",width=15)
	private java.lang.String costGroup;
	/**报价单id*/
    @Excel(name="报价单id",width=15)
	private java.lang.String offerId;
	/**申请单id*/
    @Excel(name="申请单id",width=15)
	private java.lang.String applyId;
	/**设备编码*/
    @Excel(name="设备编码",width=15)
	private java.lang.String equeCode;
	/**资产卡片*/
    @Excel(name="资产卡片",width=15)
	private java.lang.String equeCard;
	/**使用用途*/
    @Excel(name="使用用途",width=15)
	private java.lang.String useType;
	/**保修类型其他*/
    @Excel(name="保修类型其他",width=15)
	private java.lang.String guaranteeTypeQt;
	/*已占用初始报价*/
	private java.lang.String extend1;
	/**扩展字段2*/
	private java.lang.String extend2;
	/**扩展字段3*/
	private java.lang.String extend3;
	/**扩展字段4*/
	private java.lang.String extend4;
	/**扩展字段5*/
	private java.lang.String extend5;
	/**主表id*/
	private java.lang.String pkId;
	/**是否通过*/
	private java.lang.String isPass;
	/**确定日期*/
    @Excel(name="确定日期",width=15,format = "yyyy-MM-dd")
	private java.util.Date confirmDate;
	/**确认人*/
    @Excel(name="确认人",width=15)
	private java.lang.String confirmEmp;
    
    
	private java.lang.String isFinalPass;
	
       
	
	
	
	@Column(name ="IS_FINAL_PASS",nullable=false,length=36)
	public java.lang.String getIsFinalPass() {
		return isFinalPass;
	}

	public void setIsFinalPass(java.lang.String isFinalPass) {
		this.isFinalPass = isFinalPass;
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
	 *@return: java.lang.String  设备名称
	 */
	
	@Column(name ="EQU_NAME",nullable=true,length=50)
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
	 *@return: java.lang.String  供应商id
	 */
	
	@Column(name ="VEN_ID",nullable=true,length=32)
	public java.lang.String getVenId(){
		return this.venId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  供应商id
	 */
	public void setVenId(java.lang.String venId){
		this.venId = venId;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  供应商名称
	 */
	
	@Column(name ="VEN_NAME",nullable=true,length=32)
	public java.lang.String getVenName(){
		return this.venName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  供应商名称
	 */
	public void setVenName(java.lang.String venName){
		this.venName = venName;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  部门id
	 */
	
	@Column(name ="DEPT_ID",nullable=true,length=32)
	public java.lang.String getDeptId(){
		return this.deptId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  部门id
	 */
	public void setDeptId(java.lang.String deptId){
		this.deptId = deptId;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  部门名称
	 */
	
	@Column(name ="DEPT_NAME",nullable=true,length=32)
	public java.lang.String getDeptName(){
		return this.deptName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  部门名称
	 */
	public void setDeptName(java.lang.String deptName){
		this.deptName = deptName;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  供应商报价
	 */
	
	@Column(name ="OFFER_PRICE",nullable=true,length=32)
	public java.lang.String getOfferPrice(){
		return this.offerPrice;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  供应商报价
	 */
	public void setOfferPrice(java.lang.String offerPrice){
		this.offerPrice = offerPrice;
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
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  服务内容
	 */
	
	@Column(name ="SERVICE_CONTEN",nullable=true,length=500)
	public java.lang.String getServiceConten(){
		return this.serviceConten;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  服务内容
	 */
	public void setServiceConten(java.lang.String serviceConten){
		this.serviceConten = serviceConten;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  费用占比
	 */
	
	@Column(name ="COST_GROUP",nullable=true,length=32)
	public java.lang.String getCostGroup(){
		return this.costGroup;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  费用占比
	 */
	public void setCostGroup(java.lang.String costGroup){
		this.costGroup = costGroup;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  报价单id
	 */
	
	@Column(name ="OFFER_ID",nullable=true,length=32)
	public java.lang.String getOfferId(){
		return this.offerId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  报价单id
	 */
	public void setOfferId(java.lang.String offerId){
		this.offerId = offerId;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  申请单id
	 */
	
	@Column(name ="APPLY_ID",nullable=true,length=32)
	public java.lang.String getApplyId(){
		return this.applyId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  申请单id
	 */
	public void setApplyId(java.lang.String applyId){
		this.applyId = applyId;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  设备编码
	 */
	
	@Column(name ="EQUE_CODE",nullable=true,length=32)
	public java.lang.String getEqueCode(){
		return this.equeCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  设备编码
	 */
	public void setEqueCode(java.lang.String equeCode){
		this.equeCode = equeCode;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  资产卡片
	 */
	
	@Column(name ="EQUE_CARD",nullable=true,length=32)
	public java.lang.String getEqueCard(){
		return this.equeCard;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  资产卡片
	 */
	public void setEqueCard(java.lang.String equeCard){
		this.equeCard = equeCard;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  使用用途
	 */
	
	@Column(name ="USE_TYPE",nullable=true,length=32)
	public java.lang.String getUseType(){
		return this.useType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  使用用途
	 */
	public void setUseType(java.lang.String useType){
		this.useType = useType;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  保修类型其他
	 */
	
	@Column(name ="GUARANTEE_TYPE_QT",nullable=true,length=32)
	public java.lang.String getGuaranteeTypeQt(){
		return this.guaranteeTypeQt;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  保修类型其他
	 */
	public void setGuaranteeTypeQt(java.lang.String guaranteeTypeQt){
		this.guaranteeTypeQt = guaranteeTypeQt;
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
	 *@return: java.lang.String  主表id
	 */
	
	@Column(name ="PK_ID",nullable=true,length=32)
	public java.lang.String getPkId(){
		return this.pkId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  主表id
	 */
	public void setPkId(java.lang.String pkId){
		this.pkId = pkId;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否通过
	 */
	
	@Column(name ="IS_PASS",nullable=true,length=32)
	public java.lang.String getIsPass(){
		return this.isPass;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否通过
	 */
	public void setIsPass(java.lang.String isPass){
		this.isPass = isPass;
	}
	
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  确定日期
	 */
	
	@Column(name ="CONFIRM_DATE",nullable=true,length=32)
	public java.util.Date getConfirmDate(){
		return this.confirmDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  确定日期
	 */
	public void setConfirmDate(java.util.Date confirmDate){
		this.confirmDate = confirmDate;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  确认人
	 */
	
	@Column(name ="CONFIRM_EMP",nullable=true,length=32)
	public java.lang.String getConfirmEmp(){
		return this.confirmEmp;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  确认人
	 */
	public void setConfirmEmp(java.lang.String confirmEmp){
		this.confirmEmp = confirmEmp;
	}
	
}
