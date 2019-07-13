package cn.com.linkwide.cont.coninformationdetial.entity;
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
 * @Description: 合同标的
 * @author onlineGenerator
 * @date 2018-08-23 14:40:49
 * @version V1.0   
 *
 */
@Entity
@Table(name = "con_mark", schema = "")
@SuppressWarnings("serial")
public class ConMarkEntity implements java.io.Serializable {
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
	/**合同主表Id*/
	private java.lang.String conMainId;
	/**合同标的来源*/
    @Excel(name="合同标的来源",width=15,dicCode="conSource")
	private java.lang.String markSouce;
	/**编码*/
    @Excel(name="编码",width=15)
	private java.lang.String markNo;
	/**名称*/
    @Excel(name="名称",width=15)
	private java.lang.String markName;
	/**标的标志*/
    @Excel(name="标的标志",width=15)
	private java.lang.String markBz;
	
	private java.lang.String markUnit;
	/**数量*/
    @Excel(name="数量",width=15)
	private java.lang.Double markNumber;
	/**单价*/
    @Excel(name="单价",width=15)
	private java.lang.Double markPrice;
	/**金额*/
    @Excel(name="金额",width=15)
	private java.lang.Double markMoney;
	/**规格*/
    @Excel(name="规格",width=15)
	private java.lang.String markSpece;
	/**固定资产卡片id*/
	private java.lang.String quidId;
	/**扩展字段1*/
	private java.lang.String exten1;
	/**扩展字段2*/
	private java.lang.String exten2;
	/**扩展字段3*/
	private java.lang.String exten3;
	/**扩展字段4*/
	private java.lang.String exten4;
	/**扩展字段5*/
	private java.lang.String exten5;
	/**标的备注*/
    @Excel(name="标的备注",width=15)
	private java.lang.String markMs;
	/**科室id*/
	private java.lang.String deptID;
	/**标的备注*/
    @Excel(name="科室名称",width=15)
	private java.lang.String deptName;
    @Excel(name="注册证名称",width=15)
	private java.lang.String zczname;
    @Excel(name="生产制造商",width=15)
	private java.lang.String sczs;
    @Excel(name="计量单位",width=15)
    private java.lang.String unitName;
    
    
    
    
    @Column(name ="UNIT_NAME",nullable=true,length=50)
	public java.lang.String getUnitName() {
		return unitName;
	}

	public void setUnitName(java.lang.String unitName) {
		this.unitName = unitName;
	}

	@Column(name ="ZCZNAME",nullable=true,length=50)
	public java.lang.String getZczname() {
		return zczname;
	}

	public void setZczname(java.lang.String zczname) {
		this.zczname = zczname;
	}
	@Column(name ="SCZS",nullable=true,length=50)
	public java.lang.String getSczs() {
		return sczs;
	}

	public void setSczs(java.lang.String sczs) {
		this.sczs = sczs;
	}

	@Column(name ="DEPT_ID",nullable=true,length=50)
	public java.lang.String getDeptID() {
		return deptID;
	}

	public void setDeptID(java.lang.String deptID) {
		this.deptID = deptID;
	}
	@Column(name ="DEPT_NAME",nullable=true,length=50)
	public java.lang.String getDeptName() {
		return deptName;
	}

	public void setDeptName(java.lang.String deptName) {
		this.deptName = deptName;
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
	 *@return: java.lang.String  合同主表Id
	 */
	
	@Column(name ="CON_MAIN_ID",nullable=true,length=32)
	public java.lang.String getConMainId(){
		return this.conMainId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  合同主表Id
	 */
	public void setConMainId(java.lang.String conMainId){
		this.conMainId = conMainId;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  合同标的来源
	 */
	
	@Column(name ="MARK_SOUCE",nullable=true,length=32)
	public java.lang.String getMarkSouce(){
		return this.markSouce;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  合同标的来源
	 */
	public void setMarkSouce(java.lang.String markSouce){
		this.markSouce = markSouce;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  编码
	 */
	
	@Column(name ="MARK_NO",nullable=true,length=32)
	public java.lang.String getMarkNo(){
		return this.markNo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  编码
	 */
	public void setMarkNo(java.lang.String markNo){
		this.markNo = markNo;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  名称
	 */
	
	@Column(name ="MARK_NAME",nullable=true,length=32)
	public java.lang.String getMarkName(){
		return this.markName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  名称
	 */
	public void setMarkName(java.lang.String markName){
		this.markName = markName;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  标的标志
	 */
	
	@Column(name ="MARK_BZ",nullable=true,length=32)
	public java.lang.String getMarkBz(){
		return this.markBz;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  标的标志
	 */
	public void setMarkBz(java.lang.String markBz){
		this.markBz = markBz;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  计量单位
	 */
	
	@Column(name ="MARK_UNIT",nullable=true,length=32)
	public java.lang.String getMarkUnit(){
		return this.markUnit;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  计量单位
	 */
	public void setMarkUnit(java.lang.String markUnit){
		this.markUnit = markUnit;
	}
	
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  数量
	 */
	
	@Column(name ="MARK_NUMBER",nullable=true,length=32)
	public java.lang.Double getMarkNumber(){
		return this.markNumber;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  数量
	 */
	public void setMarkNumber(java.lang.Double markNumber){
		this.markNumber = markNumber;
	}
	
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  单价
	 */
	
	@Column(name ="MARK_PRICE",nullable=true,length=32)
	public java.lang.Double getMarkPrice(){
		return this.markPrice;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  单价
	 */
	public void setMarkPrice(java.lang.Double markPrice){
		this.markPrice = markPrice;
	}
	
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  金额
	 */
	
	@Column(name ="MARK_MONEY",nullable=true,length=32)
	public java.lang.Double getMarkMoney(){
		return this.markMoney;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  金额
	 */
	public void setMarkMoney(java.lang.Double markMoney){
		this.markMoney = markMoney;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  规格
	 */
	
	@Column(name ="MARK_SPECE",nullable=true,length=32)
	public java.lang.String getMarkSpece(){
		return this.markSpece;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  规格
	 */
	public void setMarkSpece(java.lang.String markSpece){
		this.markSpece = markSpece;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  固定资产卡片id
	 */
	
	@Column(name ="QUID_ID",nullable=true,length=32)
	public java.lang.String getQuidId(){
		return this.quidId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  固定资产卡片id
	 */
	public void setQuidId(java.lang.String quidId){
		this.quidId = quidId;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  扩展字段1
	 */
	
	@Column(name ="EXTEN1",nullable=true,length=32)
	public java.lang.String getExten1(){
		return this.exten1;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  扩展字段1
	 */
	public void setExten1(java.lang.String exten1){
		this.exten1 = exten1;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  扩展字段2
	 */
	
	@Column(name ="EXTEN2",nullable=true,length=32)
	public java.lang.String getExten2(){
		return this.exten2;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  扩展字段2
	 */
	public void setExten2(java.lang.String exten2){
		this.exten2 = exten2;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  扩展字段3
	 */
	
	@Column(name ="EXTEN3",nullable=true,length=32)
	public java.lang.String getExten3(){
		return this.exten3;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  扩展字段3
	 */
	public void setExten3(java.lang.String exten3){
		this.exten3 = exten3;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  扩展字段4
	 */
	
	@Column(name ="EXTEN4",nullable=true,length=32)
	public java.lang.String getExten4(){
		return this.exten4;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  扩展字段4
	 */
	public void setExten4(java.lang.String exten4){
		this.exten4 = exten4;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  扩展字段5
	 */
	
	@Column(name ="EXTEN5",nullable=true,length=32)
	public java.lang.String getExten5(){
		return this.exten5;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  扩展字段5
	 */
	public void setExten5(java.lang.String exten5){
		this.exten5 = exten5;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  标的备注
	 */
	
	@Column(name ="MARK_MS",nullable=true,length=100)
	public java.lang.String getMarkMs(){
		return this.markMs;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  标的备注
	 */
	public void setMarkMs(java.lang.String markMs){
		this.markMs = markMs;
	}
	
}
