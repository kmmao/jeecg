package cn.com.linkwide.ba.tsforeigncurrency.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

/**   
 * @Title: Entity
 * @Description: 币种档案
 * @author onlineGenerator
 * @date 2018-07-12 16:57:06
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_s_foreign_currency", schema = "")
@SuppressWarnings("serial")
public class TSForeignCurrencyEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**流程状态*/
	private java.lang.String bpmStatus;
	/**创建人名称*/
	private java.lang.String createName;
	/**创建人登录名称*/
	private java.lang.String createBy;
	/**更新人名称*/
	private java.lang.String updateName;
	/**更新人登录名称*/
	private java.lang.String updateBy;
	/**所属部门*/
	private java.lang.String sysOrgCode;
	/**所属公司*/
	private java.lang.String sysCompanyCode;
	/**创建日期*/
	private java.util.Date createDate;
	/**更新日期*/
	private java.util.Date updateDate;
	/**币种编码*/
    @Excel(name="币种编码",width=15)
	private java.lang.String currencyCode;
	/**币种名称*/
    @Excel(name="币种名称",width=15)
	private java.lang.String currencyName;
	/**折算方式*/
    @Excel(name="折算方式",width=15)
	private java.lang.String convertType;
	/**小数位数*/
    @Excel(name="小数位数",width=15)
	private java.lang.Integer decimalNum;
	/**最大误差*/
    @Excel(name="最大误差",width=15)
	private java.lang.Double maxError;
	/**是否本位币*/
    @Excel(name="是否本位币",width=15)
	private java.lang.String isOtherused;
	/**时间戳*/
	private java.lang.String pubufts;
	
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
	 *@return: java.lang.String  币种编码
	 */
	
	@Column(name ="CURRENCY_CODE",nullable=false,length=32)
	public java.lang.String getCurrencyCode(){
		return this.currencyCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  币种编码
	 */
	public void setCurrencyCode(java.lang.String currencyCode){
		this.currencyCode = currencyCode;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  币种名称
	 */
	
	@Column(name ="CURRENCY_NAME",nullable=false,length=32)
	public java.lang.String getCurrencyName(){
		return this.currencyName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  币种名称
	 */
	public void setCurrencyName(java.lang.String currencyName){
		this.currencyName = currencyName;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  折算方式
	 */
	
	@Column(name ="CONVERT_TYPE",nullable=true,length=32)
	public java.lang.String getConvertType(){
		return this.convertType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  折算方式
	 */
	public void setConvertType(java.lang.String convertType){
		this.convertType = convertType;
	}
	
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  小数位数
	 */
	
	@Column(name ="DECIMAL_NUM",nullable=true,length=32)
	public java.lang.Integer getDecimalNum(){
		return this.decimalNum;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  小数位数
	 */
	public void setDecimalNum(java.lang.Integer decimalNum){
		this.decimalNum = decimalNum;
	}
	
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  最大误差
	 */
	
	@Column(name ="MAX_ERROR",nullable=true,length=32)
	public java.lang.Double getMaxError(){
		return this.maxError;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  最大误差
	 */
	public void setMaxError(java.lang.Double maxError){
		this.maxError = maxError;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否本位币
	 */
	
	@Column(name ="IS_OTHERUSED",nullable=true,length=32)
	public java.lang.String getIsOtherused(){
		return this.isOtherused;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否本位币
	 */
	public void setIsOtherused(java.lang.String isOtherused){
		this.isOtherused = isOtherused;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  时间戳
	 */
	
	@Column(name ="PUBUFTS",nullable=true,length=32)
	public java.lang.String getPubufts(){
		return this.pubufts;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  时间戳
	 */
	public void setPubufts(java.lang.String pubufts){
		this.pubufts = pubufts;
	}
	
}
