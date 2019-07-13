package cn.com.linkwide.cont.conmemorabilia.entity;

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
 * @Description: 大事记
 * @author onlineGenerator
 * @date 2018-08-24 11:41:14
 * @version V1.0   
 *
 */
@Entity
@Table(name = "con_memorabilia", schema = "")
@SuppressWarnings("serial")
public class ConMemorabiliaEntity implements java.io.Serializable {
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
	/**合同主表id*/
	@Excel(name="合同主表id",width=15)
	private java.lang.String conId;
	/**大事件编码*/
	@Excel(name="大事件编码",width=15)
	private java.lang.String memorabiliaNo;
	/**大事件内容*/
	@Excel(name="大事件内容",width=15)
	private java.lang.String memorabiliaContent;
	/**大事件备注*/
	@Excel(name="大事件备注",width=15)
	private java.lang.String memorabiliaBz;
	/**扩展字段1*/
	@Excel(name="扩展字段1",width=15)
	private java.lang.String execten1;
	/**扩展字段2*/
	@Excel(name="扩展字段2",width=15)
	private java.lang.String execten2;
	/**扩展字段3*/
	@Excel(name="扩展字段3",width=15)
	private java.lang.String execten3;
	/**扩展字段4*/
	@Excel(name="扩展字段4",width=15)
	private java.lang.String execten4;
	/**扩展字段5*/
	@Excel(name="扩展字段5",width=15)
	private java.lang.String execten5;
	
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
	 *@return: java.lang.String  合同主表id
	 */

	@Column(name ="CON_ID",nullable=true,length=32)
	public java.lang.String getConId(){
		return this.conId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  合同主表id
	 */
	public void setConId(java.lang.String conId){
		this.conId = conId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  大事件编码
	 */

	@Column(name ="MEMORABILIA_NO",nullable=true,length=32)
	public java.lang.String getMemorabiliaNo(){
		return this.memorabiliaNo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  大事件编码
	 */
	public void setMemorabiliaNo(java.lang.String memorabiliaNo){
		this.memorabiliaNo = memorabiliaNo;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  大事件内容
	 */

	@Column(name ="MEMORABILIA_CONTENT",nullable=true,length=32)
	public java.lang.String getMemorabiliaContent(){
		return this.memorabiliaContent;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  大事件内容
	 */
	public void setMemorabiliaContent(java.lang.String memorabiliaContent){
		this.memorabiliaContent = memorabiliaContent;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  大事件备注
	 */

	@Column(name ="MEMORABILIA_BZ",nullable=true,length=32)
	public java.lang.String getMemorabiliaBz(){
		return this.memorabiliaBz;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  大事件备注
	 */
	public void setMemorabiliaBz(java.lang.String memorabiliaBz){
		this.memorabiliaBz = memorabiliaBz;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  扩展字段1
	 */

	@Column(name ="EXECTEN1",nullable=true,length=32)
	public java.lang.String getExecten1(){
		return this.execten1;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  扩展字段1
	 */
	public void setExecten1(java.lang.String execten1){
		this.execten1 = execten1;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  扩展字段2
	 */

	@Column(name ="EXECTEN2",nullable=true,length=32)
	public java.lang.String getExecten2(){
		return this.execten2;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  扩展字段2
	 */
	public void setExecten2(java.lang.String execten2){
		this.execten2 = execten2;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  扩展字段3
	 */

	@Column(name ="EXECTEN3",nullable=true,length=32)
	public java.lang.String getExecten3(){
		return this.execten3;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  扩展字段3
	 */
	public void setExecten3(java.lang.String execten3){
		this.execten3 = execten3;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  扩展字段4
	 */

	@Column(name ="EXECTEN4",nullable=true,length=32)
	public java.lang.String getExecten4(){
		return this.execten4;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  扩展字段4
	 */
	public void setExecten4(java.lang.String execten4){
		this.execten4 = execten4;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  扩展字段5
	 */

	@Column(name ="EXECTEN5",nullable=true,length=32)
	public java.lang.String getExecten5(){
		return this.execten5;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  扩展字段5
	 */
	public void setExecten5(java.lang.String execten5){
		this.execten5 = execten5;
	}
}
