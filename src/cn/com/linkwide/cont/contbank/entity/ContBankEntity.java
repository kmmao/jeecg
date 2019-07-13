package cn.com.linkwide.cont.contbank.entity;

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
 * @Description: 银行保函
 * @author onlineGenerator
 * @date 2018-08-24 14:47:04
 * @version V1.0   
 *
 */
@Entity
@Table(name = "cont_Bank", schema = "")
@SuppressWarnings("serial")
public class ContBankEntity implements java.io.Serializable {
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
	/**银行保函编码*/
	@Excel(name="银行保函编码",width=15)
	private java.lang.String bankguaNo;
	/**银行保函名称*/
	@Excel(name="银行保函名称",width=15)
	private java.lang.String bankguaName;
	/**银行保函附件*/
	@Excel(name="银行保函附件",width=15)
	private java.lang.String bankguaFj;
	/**上传日期*/
	@Excel(name="上传日期",width=15,format = "yyyy-MM-dd")
	private java.util.Date uploadDate;
	/**开始日期*/
	@Excel(name="开始日期",width=15,format = "yyyy-MM-dd")
	private java.util.Date bigenDate;
	/**失效日期*/
	@Excel(name="失效日期",width=15,format = "yyyy-MM-dd")
	private java.util.Date failureDate;
	/**保函出具单位*/
	@Excel(name="保函出具单位",width=15)
	private java.lang.String bankguaCompy;
	/**保函金额*/
	@Excel(name="保函金额",width=15)
	private java.lang.Double bankguaMoney;
	/**保函备注*/
	@Excel(name="保函备注",width=15)
	private java.lang.String banguaBz;
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
	 *@return: java.lang.String  银行保函编码
	 */

	@Column(name ="BANKGUA_NO",nullable=true,length=32)
	public java.lang.String getBankguaNo(){
		return this.bankguaNo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  银行保函编码
	 */
	public void setBankguaNo(java.lang.String bankguaNo){
		this.bankguaNo = bankguaNo;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  银行保函名称
	 */

	@Column(name ="BANKGUA_NAME",nullable=true,length=32)
	public java.lang.String getBankguaName(){
		return this.bankguaName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  银行保函名称
	 */
	public void setBankguaName(java.lang.String bankguaName){
		this.bankguaName = bankguaName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  银行保函附件
	 */

	@Column(name ="BANKGUA_FJ",nullable=true,length=32)
	public java.lang.String getBankguaFj(){
		return this.bankguaFj;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  银行保函附件
	 */
	public void setBankguaFj(java.lang.String bankguaFj){
		this.bankguaFj = bankguaFj;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  上传日期
	 */

	@Column(name ="UPLOAD_DATE",nullable=true,length=32)
	public java.util.Date getUploadDate(){
		return this.uploadDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  上传日期
	 */
	public void setUploadDate(java.util.Date uploadDate){
		this.uploadDate = uploadDate;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  开始日期
	 */

	@Column(name ="BIGEN_DATE",nullable=true,length=32)
	public java.util.Date getBigenDate(){
		return this.bigenDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  开始日期
	 */
	public void setBigenDate(java.util.Date bigenDate){
		this.bigenDate = bigenDate;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  失效日期
	 */

	@Column(name ="FAILURE_DATE",nullable=true,length=32)
	public java.util.Date getFailureDate(){
		return this.failureDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  失效日期
	 */
	public void setFailureDate(java.util.Date failureDate){
		this.failureDate = failureDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  保函出具单位
	 */

	@Column(name ="BANKGUA_COMPY",nullable=true,length=32)
	public java.lang.String getBankguaCompy(){
		return this.bankguaCompy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  保函出具单位
	 */
	public void setBankguaCompy(java.lang.String bankguaCompy){
		this.bankguaCompy = bankguaCompy;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  保函金额
	 */

	@Column(name ="BANKGUA_MONEY",nullable=true,length=32)
	public java.lang.Double getBankguaMoney(){
		return this.bankguaMoney;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  保函金额
	 */
	public void setBankguaMoney(java.lang.Double bankguaMoney){
		this.bankguaMoney = bankguaMoney;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  保函备注
	 */

	@Column(name ="BANGUA_BZ",nullable=true,length=100)
	public java.lang.String getBanguaBz(){
		return this.banguaBz;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  保函备注
	 */
	public void setBanguaBz(java.lang.String banguaBz){
		this.banguaBz = banguaBz;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  扩展字段1
	 */

	@Column(name ="EXECTEN1",nullable=true,length=50)
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

	@Column(name ="EXECTEN2",nullable=true,length=50)
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

	@Column(name ="EXECTEN3",nullable=true,length=50)
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

	@Column(name ="EXECTEN4",nullable=true,length=50)
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

	@Column(name ="EXECTEN5",nullable=true,length=50)
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
