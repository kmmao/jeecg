package cn.com.linkwide.cont.desgin.bidinformation.entity;

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
 * @Description: 招标传输表
 * @author onlineGenerator
 * @date 2018-12-24 17:32:09
 * @version V1.0   
 *
 */
@Entity
@Table(name = "bid_information", schema = "")
@SuppressWarnings("serial")
public class BidInformationEntity implements java.io.Serializable {
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
	/**招标编号*/
	private java.lang.String bidCode;
	/**中标标号*/
	private java.lang.String conBidCode;
	/**供应商编码*/
	private java.lang.String venCode;
	/**供应商名称*/
	private java.lang.String venName;
	/**中标物资编码*/
	private java.lang.String invCode;
	/**中标物资名称*/
	private java.lang.String invName;
	/**计量单位编码*/
	private java.lang.String unitCode;
	/**计量单位名称*/
	private java.lang.String unitName;
	/**数量*/
	private java.lang.String num;
	/**单价*/
	private java.lang.Double price;
	/**金额*/
	private java.lang.Double money;
	/**需求科室*/
	private java.lang.String deptCode;
	/**预算编号*/
	private java.lang.String budgCode;
	/**注册证号*/
	private java.lang.String registerCode;
	/**生产制造商*/
	private java.lang.String manufacturer;
	/**备注*/
	private java.lang.String remark;
	/**传输时间*/
	private java.util.Date receiveTime;
	/**扩展字段1*/
	private java.lang.String exetens1;
	/**扩展字段2*/
	private java.lang.String exetens2;
	/**扩展字段3*/
	private java.lang.String exetens3;
	/**扩展字段4*/
	private java.lang.String exetens4;
	/**扩展字段5*/
	private java.lang.String exetens5;
	
	private java.lang.String adressFj;
	
	
	
	
	
	@Column(name ="ADRESSFJ",nullable=true,length=255)
	public java.lang.String getAdressFj() {
		return adressFj;
	}

	public void setAdressFj(java.lang.String adressFj) {
		this.adressFj = adressFj;
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
	 *@return: java.lang.String  招标编号
	 */

	@Column(name ="BID_CODE",nullable=true,length=32)
	public java.lang.String getBidCode(){
		return this.bidCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  招标编号
	 */
	public void setBidCode(java.lang.String bidCode){
		this.bidCode = bidCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  中标标号
	 */

	@Column(name ="CON_BID_CODE",nullable=true,length=32)
	public java.lang.String getConBidCode(){
		return this.conBidCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  中标标号
	 */
	public void setConBidCode(java.lang.String conBidCode){
		this.conBidCode = conBidCode;
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
	 *@return: java.lang.String  中标物资编码
	 */

	@Column(name ="INV_CODE",nullable=true,length=32)
	public java.lang.String getInvCode(){
		return this.invCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  中标物资编码
	 */
	public void setInvCode(java.lang.String invCode){
		this.invCode = invCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  中标物资名称
	 */

	@Column(name ="INV_NAME",nullable=true,length=32)
	public java.lang.String getInvName(){
		return this.invName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  中标物资名称
	 */
	public void setInvName(java.lang.String invName){
		this.invName = invName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  计量单位编码
	 */

	@Column(name ="UNIT_CODE",nullable=true,length=32)
	public java.lang.String getUnitCode(){
		return this.unitCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  计量单位编码
	 */
	public void setUnitCode(java.lang.String unitCode){
		this.unitCode = unitCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  计量单位名称
	 */

	@Column(name ="UNIT_NAME",nullable=true,length=32)
	public java.lang.String getUnitName(){
		return this.unitName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  计量单位名称
	 */
	public void setUnitName(java.lang.String unitName){
		this.unitName = unitName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  数量
	 */

	@Column(name ="NUM",nullable=true,length=32)
	public java.lang.String getNum(){
		return this.num;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  数量
	 */
	public void setNum(java.lang.String num){
		this.num = num;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  单价
	 */

	@Column(name ="PRICE",nullable=true,length=32)
	public java.lang.Double getPrice(){
		return this.price;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  单价
	 */
	public void setPrice(java.lang.Double price){
		this.price = price;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  金额
	 */

	@Column(name ="MONEY",nullable=true,length=32)
	public java.lang.Double getMoney(){
		return this.money;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  金额
	 */
	public void setMoney(java.lang.Double money){
		this.money = money;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  需求科室
	 */

	@Column(name ="DEPT_CODE",nullable=true,length=32)
	public java.lang.String getDeptCode(){
		return this.deptCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  需求科室
	 */
	public void setDeptCode(java.lang.String deptCode){
		this.deptCode = deptCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预算编号
	 */

	@Column(name ="BUDG_CODE",nullable=true,length=32)
	public java.lang.String getBudgCode(){
		return this.budgCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预算编号
	 */
	public void setBudgCode(java.lang.String budgCode){
		this.budgCode = budgCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  注册证号
	 */

	@Column(name ="REGISTER_CODE",nullable=true,length=32)
	public java.lang.String getRegisterCode(){
		return this.registerCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  注册证号
	 */
	public void setRegisterCode(java.lang.String registerCode){
		this.registerCode = registerCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  生产制造商
	 */

	@Column(name ="MANUFACTURER",nullable=true,length=32)
	public java.lang.String getManufacturer(){
		return this.manufacturer;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  生产制造商
	 */
	public void setManufacturer(java.lang.String manufacturer){
		this.manufacturer = manufacturer;
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
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  传输时间
	 */

	@Column(name ="RECEIVE_TIME",nullable=true,length=32)
	public java.util.Date getReceiveTime(){
		return this.receiveTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  传输时间
	 */
	public void setReceiveTime(java.util.Date receiveTime){
		this.receiveTime = receiveTime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  扩展字段1
	 */

	@Column(name ="EXETENS1",nullable=true,length=32)
	public java.lang.String getExetens1(){
		return this.exetens1;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  扩展字段1
	 */
	public void setExetens1(java.lang.String exetens1){
		this.exetens1 = exetens1;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  扩展字段2
	 */

	@Column(name ="EXETENS2",nullable=true,length=32)
	public java.lang.String getExetens2(){
		return this.exetens2;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  扩展字段2
	 */
	public void setExetens2(java.lang.String exetens2){
		this.exetens2 = exetens2;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  扩展字段3
	 */

	@Column(name ="EXETENS3",nullable=true,length=32)
	public java.lang.String getExetens3(){
		return this.exetens3;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  扩展字段3
	 */
	public void setExetens3(java.lang.String exetens3){
		this.exetens3 = exetens3;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  扩展字段4
	 */

	@Column(name ="EXETENS4",nullable=true,length=32)
	public java.lang.String getExetens4(){
		return this.exetens4;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  扩展字段4
	 */
	public void setExetens4(java.lang.String exetens4){
		this.exetens4 = exetens4;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  扩展字段5
	 */

	@Column(name ="EXETENS5",nullable=true,length=32)
	public java.lang.String getExetens5(){
		return this.exetens5;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  扩展字段5
	 */
	public void setExetens5(java.lang.String exetens5){
		this.exetens5 = exetens5;
	}
}
