package cn.com.linkwide.ba.setaccountperiod.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

/**   
 * @Title: Entity
 * @Description: 会计期间
 * @author onlineGenerator
 * @date 2018-01-17 10:57:51
 * @version V1.0   
 *
 */
@Entity
@Table(name = "ba_set_account_period", schema = "")
@SuppressWarnings("serial")
public class BaSetAccountPeriodEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**年*/
	@Excel(name="年")
	private java.lang.String periodYear;
	/**月*/
	@Excel(name="月")
	private java.lang.String periodMonth;
	private java.lang.String monthlyPeriodStatus;//月结状态
	/**月结开始日期*/
	@Excel(name="月结开始日期",format = "yyyy-MM-dd")
	private java.util.Date beginDate;
	/**月结结束日期*/
	@Excel(name="月结结束日期",format = "yyyy-MM-dd")
	private java.util.Date endDate;
	/**创建人*/
	@Excel(name="创建人")
	private java.lang.String createBy;
	/**创建时间*/
	@Excel(name="创建时间",format = "yyyy-MM-dd")
	private java.util.Date createDate;
	/**修改人*/
	@Excel(name="修改人")
	private java.lang.String updateBy;
	/**修改时间*/
	@Excel(name="修改时间",format = "yyyy-MM-dd")
	private java.util.Date updateDate;
	/**extend1*/
	@Excel(name="extend1")
	private java.lang.String extend1;
	/**extend2*/
	@Excel(name="extend2")
	private java.lang.String extend2;
	/**extend3*/
	@Excel(name="extend3")
	private java.lang.String extend3;
	/**extend4*/
	@Excel(name="extend4")
	private java.lang.String extend4;
	/**extend5*/
	@Excel(name="extend5")
	private java.lang.String extend5;
	/**extend6*/
	@Excel(name="extend6")
	private java.lang.String extend6;
	/**extend7*/
	@Excel(name="extend7")
	private java.lang.String extend7;
	/**extend8*/
	@Excel(name="extend8")
	private java.lang.String extend8;
	/**extend9*/
	@Excel(name="extend9")
	private java.lang.String extend9;
	/**extend10*/
	@Excel(name="extend10")
	private java.lang.String extend10;
	
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
	 *@return: java.lang.String  年
	 */
	@Column(name ="PERIOD_YEAR",nullable=false,length=5)
	public java.lang.String getPeriodYear(){
		return this.periodYear;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  年
	 */
	public void setPeriodYear(java.lang.String periodYear){
		this.periodYear = periodYear;
	}
	
	
	@Transient
	public java.lang.String getMonthlyPeriodStatus() {
		return monthlyPeriodStatus;
	}

	public void setMonthlyPeriodStatus(java.lang.String monthlyPeriodStatus) {
		this.monthlyPeriodStatus = monthlyPeriodStatus;
	}

	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  月
	 */
	@Column(name ="PERIOD_MONTH",nullable=false,length=36)
	public java.lang.String getPeriodMonth(){
		return this.periodMonth;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  月
	 */
	public void setPeriodMonth(java.lang.String periodMonth){
		this.periodMonth = periodMonth;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  月结开始日期
	 */
	@Column(name ="BEGIN_DATE",nullable=false)
	public java.util.Date getBeginDate(){
		return this.beginDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  月结开始日期
	 */
	public void setBeginDate(java.util.Date beginDate){
		this.beginDate = beginDate;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  月结结束日期
	 */
	@Column(name ="END_DATE",nullable=false)
	public java.util.Date getEndDate(){
		return this.endDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  月结结束日期
	 */
	public void setEndDate(java.util.Date endDate){
		this.endDate = endDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建人
	 */
	@Column(name ="CREATE_BY",nullable=false,length=50)
	public java.lang.String getCreateBy(){
		return this.createBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人
	 */
	public void setCreateBy(java.lang.String createBy){
		this.createBy = createBy;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  创建时间
	 */
	@Column(name ="CREATE_DATE",nullable=false)
	public java.util.Date getCreateDate(){
		return this.createDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  创建时间
	 */
	public void setCreateDate(java.util.Date createDate){
		this.createDate = createDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  修改人
	 */
	@Column(name ="UPDATE_BY",nullable=true,length=50)
	public java.lang.String getUpdateBy(){
		return this.updateBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  修改人
	 */
	public void setUpdateBy(java.lang.String updateBy){
		this.updateBy = updateBy;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  修改时间
	 */
	@Column(name ="UPDATE_DATE",nullable=true)
	public java.util.Date getUpdateDate(){
		return this.updateDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  修改时间
	 */
	public void setUpdateDate(java.util.Date updateDate){
		this.updateDate = updateDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  extend1
	 */
	@Column(name ="EXTEND1",nullable=true,length=200)
	public java.lang.String getExtend1(){
		return this.extend1;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  extend1
	 */
	public void setExtend1(java.lang.String extend1){
		this.extend1 = extend1;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  extend2
	 */
	@Column(name ="EXTEND2",nullable=true,length=200)
	public java.lang.String getExtend2(){
		return this.extend2;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  extend2
	 */
	public void setExtend2(java.lang.String extend2){
		this.extend2 = extend2;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  extend3
	 */
	@Column(name ="EXTEND3",nullable=true,length=200)
	public java.lang.String getExtend3(){
		return this.extend3;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  extend3
	 */
	public void setExtend3(java.lang.String extend3){
		this.extend3 = extend3;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  extend4
	 */
	@Column(name ="EXTEND4",nullable=true,length=200)
	public java.lang.String getExtend4(){
		return this.extend4;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  extend4
	 */
	public void setExtend4(java.lang.String extend4){
		this.extend4 = extend4;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  extend5
	 */
	@Column(name ="EXTEND5",nullable=true,length=200)
	public java.lang.String getExtend5(){
		return this.extend5;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  extend5
	 */
	public void setExtend5(java.lang.String extend5){
		this.extend5 = extend5;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  extend6
	 */
	@Column(name ="EXTEND6",nullable=true,length=200)
	public java.lang.String getExtend6(){
		return this.extend6;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  extend6
	 */
	public void setExtend6(java.lang.String extend6){
		this.extend6 = extend6;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  extend7
	 */
	@Column(name ="EXTEND7",nullable=true,length=200)
	public java.lang.String getExtend7(){
		return this.extend7;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  extend7
	 */
	public void setExtend7(java.lang.String extend7){
		this.extend7 = extend7;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  extend8
	 */
	@Column(name ="EXTEND8",nullable=true,length=200)
	public java.lang.String getExtend8(){
		return this.extend8;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  extend8
	 */
	public void setExtend8(java.lang.String extend8){
		this.extend8 = extend8;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  extend9
	 */
	@Column(name ="EXTEND9",nullable=true,length=200)
	public java.lang.String getExtend9(){
		return this.extend9;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  extend9
	 */
	public void setExtend9(java.lang.String extend9){
		this.extend9 = extend9;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  extend10
	 */
	@Column(name ="EXTEND10",nullable=true,length=200)
	public java.lang.String getExtend10(){
		return this.extend10;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  extend10
	 */
	public void setExtend10(java.lang.String extend10){
		this.extend10 = extend10;
	}
}
