package cn.com.linkwide.cont.perform.entity;

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
 * @Description: 合同履行表
 * @author onlineGenerator
 * @date 2018-06-09 10:33:54
 * @version V1.0   
 *
 */
@Entity
@Table(name = "cont_perform", schema = "")
@SuppressWarnings("serial")
public class ContPerformEntity implements java.io.Serializable {
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
	/**合同编号*/
	@Excel(name="合同编号",width=15,dictTable ="cont_con_main",dicCode ="id",dicText ="code")
	private java.lang.String conId;
	/**合同类别*/
	@Excel(name="合同类别",width=15,dictTable ="contract_type",dicCode ="code",dicText ="name")
	private java.lang.String conType;
	/**付款计划*/
	@Excel(name="付款计划",width=15,dictTable ="cont_pay_plan",dicCode ="id",dicText ="id")
	private java.lang.String payId;
	/**期初执行*/
	@Excel(name="期初执行",width=15)
	private java.lang.Double earlyExec;
	/**执行金额*/
	@Excel(name="执行金额",width=15)
	private java.lang.Double execMoney;
	/**执行进度*/
	@Excel(name="执行进度",width=15)
	private java.lang.Double execSche;
	/**是否结转*/
	@Excel(name="是否结转",width=15)
	private java.lang.String isKnots;
	
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
	 *@return: java.lang.String  合同编号
	 */

	@Column(name ="CON_ID",nullable=true,length=32)
	public java.lang.String getConId(){
		return this.conId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  合同编号
	 */
	public void setConId(java.lang.String conId){
		this.conId = conId;
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
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  付款计划
	 */

	@Column(name ="PAY_ID",nullable=true,length=32)
	public java.lang.String getPayId(){
		return this.payId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  付款计划
	 */
	public void setPayId(java.lang.String payId){
		this.payId = payId;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  期初执行
	 */

	@Column(name ="EARLY_EXEC",nullable=true,length=32)
	public java.lang.Double getEarlyExec(){
		return this.earlyExec;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  期初执行
	 */
	public void setEarlyExec(java.lang.Double earlyExec){
		this.earlyExec = earlyExec;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  执行金额
	 */

	@Column(name ="EXEC_MONEY",nullable=true,length=32)
	public java.lang.Double getExecMoney(){
		return this.execMoney;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  执行金额
	 */
	public void setExecMoney(java.lang.Double execMoney){
		this.execMoney = execMoney;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  执行进度
	 */

	@Column(name ="EXEC_SCHE",nullable=true,length=32)
	public java.lang.Double getExecSche(){
		return this.execSche;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  执行进度
	 */
	public void setExecSche(java.lang.Double execSche){
		this.execSche = execSche;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否结转
	 */

	@Column(name ="IS_KNOTS",nullable=true,length=32)
	public java.lang.String getIsKnots(){
		return this.isKnots;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否结转
	 */
	public void setIsKnots(java.lang.String isKnots){
		this.isKnots = isKnots;
	}
}
