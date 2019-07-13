package cn.com.linkwide.ba.acctsubjbudget.entity;

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
 * @Description: 科目项目对应表
 * @author onlineGenerator
 * @date 2018-08-02 17:46:09
 * @version V1.0   
 *
 */
@Entity
@Table(name = "acct_subj_budget", schema = "")
@SuppressWarnings("serial")
public class AcctSubjBudgetEntity implements java.io.Serializable {
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
	/**科目编码*/
	@Excel(name="科目编码",width=15)
	private java.lang.String subjCode;
	/**科目名称*/
	@Excel(name="科目名称",width=15)
	private java.lang.String subjName;
	/**项目编码*/
	@Excel(name="项目编码",width=15)
	private java.lang.String budgetCode;
	/**项目名称*/
	@Excel(name="项目名称",width=15)
	private java.lang.String budgetName;
	/**预留1*/
	private java.lang.String vdef1;
	/**预留2*/
	private java.lang.String vdef2;
	/**预留3*/
	private java.lang.String vdef3;
	/**预留4*/
	private java.lang.String vdef4;
	/**预留5*/
	private java.lang.String vdef5;
	
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
	 *@return: java.lang.String  科目编码
	 */

	@Column(name ="SUBJ_CODE",nullable=true,length=32)
	public java.lang.String getSubjCode(){
		return this.subjCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  科目编码
	 */
	public void setSubjCode(java.lang.String subjCode){
		this.subjCode = subjCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  科目名称
	 */

	@Column(name ="SUBJ_NAME",nullable=true,length=50)
	public java.lang.String getSubjName(){
		return this.subjName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  科目名称
	 */
	public void setSubjName(java.lang.String subjName){
		this.subjName = subjName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  项目编码
	 */

	@Column(name ="BUDGET_CODE",nullable=true,length=32)
	public java.lang.String getBudgetCode(){
		return this.budgetCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  项目编码
	 */
	public void setBudgetCode(java.lang.String budgetCode){
		this.budgetCode = budgetCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  项目名称
	 */

	@Column(name ="BUDGET_NAME",nullable=true,length=50)
	public java.lang.String getBudgetName(){
		return this.budgetName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  项目名称
	 */
	public void setBudgetName(java.lang.String budgetName){
		this.budgetName = budgetName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预留1
	 */

	@Column(name ="VDEF1",nullable=true,length=32)
	public java.lang.String getVdef1(){
		return this.vdef1;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预留1
	 */
	public void setVdef1(java.lang.String vdef1){
		this.vdef1 = vdef1;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预留2
	 */

	@Column(name ="VDEF2",nullable=true,length=32)
	public java.lang.String getVdef2(){
		return this.vdef2;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预留2
	 */
	public void setVdef2(java.lang.String vdef2){
		this.vdef2 = vdef2;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预留3
	 */

	@Column(name ="VDEF3",nullable=true,length=32)
	public java.lang.String getVdef3(){
		return this.vdef3;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预留3
	 */
	public void setVdef3(java.lang.String vdef3){
		this.vdef3 = vdef3;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预留4
	 */

	@Column(name ="VDEF4",nullable=true,length=32)
	public java.lang.String getVdef4(){
		return this.vdef4;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预留4
	 */
	public void setVdef4(java.lang.String vdef4){
		this.vdef4 = vdef4;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预留5
	 */

	@Column(name ="VDEF5",nullable=true,length=32)
	public java.lang.String getVdef5(){
		return this.vdef5;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预留5
	 */
	public void setVdef5(java.lang.String vdef5){
		this.vdef5 = vdef5;
	}
}
