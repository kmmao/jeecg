package cn.com.linkwide.common.custom.billcode.manage.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

/**   
 * @Title: Entity
 * @Description: 单据号管理
 * @author onlineGenerator
 * @date 2017-09-22 11:23:19
 * @version V1.0   
 *
 */
@Entity
@Table(name = "billcode_manage", schema = "")
@SuppressWarnings("serial")
public class BillcodeManageEntity implements java.io.Serializable {
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
	/**单据号范围*/
	@Excel(name="单据号范围")
	private java.lang.String billScope ;
	/**生成单据号时检查唯一性*/
	@Excel(name="生成单据号时检查唯一性")
	private java.lang.String isUniq;
	/**删除单据时保留占用*/
	@Excel(name="删除单据时保留占用")
	private java.lang.String isKeep;
	/**自动进行断号补号*/
	@Excel(name="自动进行断号补号")
	private java.lang.String autoComplete;
	/**单据类型*/
	@Excel(name="单据类型")
	private java.lang.String billType;
	/**对象1*/
	@Excel(name="对象1")
	private java.lang.String billObj1;
	/**对象2*/
	@Excel(name="对象2")
	private java.lang.String billObj2;
	/**年份*/
	@Excel(name="年份")
	private java.lang.String codeYear;
	/**月份*/
	@Excel(name="月份")
	private java.lang.String codeMoth;
	/**日期*/
	@Excel(name="日期")
	private java.lang.String codeDay;
	/**流水号后几位位数*/
	@Excel(name="流水号后几位位数")
	private java.lang.String endNum;
	/**流水号归零标志*/
	@Excel(name="流水号归零标志")
	private java.lang.String zeroMark;
	/**应用效果*/
	@Excel(name="应用效果")
	private java.lang.String apliResult;
	/**表示*/
	@Excel(name="表示")
	private java.lang.String billDesc;
	/**单据号*/
	@Excel(name="单据号")
	private java.lang.String billCode;
	/**预留字段1*/
	@Excel(name="预留字段1")
	private java.lang.String vdef1;
	/**预留字段2*/
	@Excel(name="预留字段2")
	private java.lang.String vdef2;
	/**预留字段3*/
	@Excel(name="预留字段3")
	private java.lang.String vdef3;
	/**归零标识年份*/
	private String year;
	/**归零标识月份*/
	private String month;
	/**归零标识天*/
	private String day;
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
	 *@return: java.lang.String  单据号范围
	 */
	@Column(name ="BILL_SCOPE ",nullable=true,length=32)
	public java.lang.String getBillScope (){
		return this.billScope ;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  单据号范围
	 */
	public void setBillScope (java.lang.String billScope ){
		this.billScope  = billScope ;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  生成单据号时检查唯一性
	 */
	@Column(name ="IS_UNIQ",nullable=true,length=32)
	public java.lang.String getIsUniq(){
		return this.isUniq;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  生成单据号时检查唯一性
	 */
	public void setIsUniq(java.lang.String isUniq){
		this.isUniq = isUniq;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  删除单据时保留占用
	 */
	@Column(name ="IS_KEEP",nullable=true,length=32)
	public java.lang.String getIsKeep(){
		return this.isKeep;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  删除单据时保留占用
	 */
	public void setIsKeep(java.lang.String isKeep){
		this.isKeep = isKeep;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  自动进行断号补号
	 */
	@Column(name ="AUTO_COMPLETE",nullable=true,length=32)
	public java.lang.String getAutoComplete(){
		return this.autoComplete;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  自动进行断号补号
	 */
	public void setAutoComplete(java.lang.String autoComplete){
		this.autoComplete = autoComplete;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  单据类型
	 */
	@Column(name ="BILL_TYPE",nullable=true,length=32)
	public java.lang.String getBillType(){
		return this.billType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  单据类型
	 */
	public void setBillType(java.lang.String billType){
		this.billType = billType;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  对象1
	 */
	@Column(name ="BILL_OBJ1",nullable=true,length=32)
	public java.lang.String getBillObj1(){
		return this.billObj1;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  对象1
	 */
	public void setBillObj1(java.lang.String billObj1){
		this.billObj1 = billObj1;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  对象2
	 */
	@Column(name ="BILL_OBJ2",nullable=true,length=32)
	public java.lang.String getBillObj2(){
		return this.billObj2;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  对象2
	 */
	public void setBillObj2(java.lang.String billObj2){
		this.billObj2 = billObj2;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  年份
	 */
	@Column(name ="CODE_YEAR",nullable=true,length=32)
	public java.lang.String getCodeYear(){
		return this.codeYear;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  年份
	 */
	public void setCodeYear(java.lang.String codeYear){
		this.codeYear = codeYear;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  月份
	 */
	@Column(name ="CODE_MOTH",nullable=true,length=32)
	public java.lang.String getCodeMoth(){
		return this.codeMoth;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  月份
	 */
	public void setCodeMoth(java.lang.String codeMoth){
		this.codeMoth = codeMoth;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  日期
	 */
	@Column(name ="CODE_DAY",nullable=true,length=32)
	public java.lang.String getCodeDay(){
		return this.codeDay;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  日期
	 */
	public void setCodeDay(java.lang.String codeDay){
		this.codeDay = codeDay;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  流水号后几位位数
	 */
	@Column(name ="END_NUM",nullable=true,length=32)
	public java.lang.String getEndNum(){
		return this.endNum;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  流水号后几位位数
	 */
	public void setEndNum(java.lang.String endNum){
		this.endNum = endNum;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  流水号归零标志
	 */
	@Column(name ="ZERO_MARK",nullable=true,length=32)
	public java.lang.String getZeroMark(){
		return this.zeroMark;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  流水号归零标志
	 */
	public void setZeroMark(java.lang.String zeroMark){
		this.zeroMark = zeroMark;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  应用效果
	 */
	@Column(name ="APLI_RESULT",nullable=true,length=32)
	public java.lang.String getApliResult(){
		return this.apliResult;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  应用效果
	 */
	public void setApliResult(java.lang.String apliResult){
		this.apliResult = apliResult;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  表示
	 */
	@Column(name ="BILL_DESC",nullable=true,length=32)
	public java.lang.String getBillDesc(){
		return this.billDesc;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  表示
	 */
	public void setBillDesc(java.lang.String billDesc){
		this.billDesc = billDesc;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  单据号
	 */
	@Column(name ="BILL_CODE",nullable=true,length=32)
	public java.lang.String getBillCode(){
		return this.billCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  单据号
	 */
	public void setBillCode(java.lang.String billCode){
		this.billCode = billCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预留字段1
	 */
	@Column(name ="VDEF1",nullable=true,length=32)
	public java.lang.String getVdef1(){
		return this.vdef1;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预留字段1
	 */
	public void setVdef1(java.lang.String vdef1){
		this.vdef1 = vdef1;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预留字段2
	 */
	@Column(name ="VDEF2",nullable=true,length=32)
	public java.lang.String getVdef2(){
		return this.vdef2;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预留字段2
	 */
	public void setVdef2(java.lang.String vdef2){
		this.vdef2 = vdef2;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预留字段3
	 */
	@Column(name ="VDEF3",nullable=true,length=32)
	public java.lang.String getVdef3(){
		return this.vdef3;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预留字段3
	 */
	public void setVdef3(java.lang.String vdef3){
		this.vdef3 = vdef3;
	}
	@Column(name="YEAR")
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}
	@Column(name="MONTH")
	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}
	@Column(name="DAY")
	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}
	
}
