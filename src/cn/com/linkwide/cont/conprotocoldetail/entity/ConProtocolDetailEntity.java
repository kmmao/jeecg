package cn.com.linkwide.cont.conprotocoldetail.entity;
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
 * @Description: 合同协议
 * @author onlineGenerator
 * @date 2018-09-17 18:30:35
 * @version V1.0   
 *
 */
@Entity
@Table(name = "con_protocol_detail", schema = "")
@SuppressWarnings("serial")
public class ConProtocolDetailEntity implements java.io.Serializable {
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
	/**协议主表id*/
    @Excel(name="协议主表id",width=15)
	private java.lang.String protocolId;
	/**协议大事记*/
    @Excel(name="协议大事记",width=15)
	private java.lang.String protocolContNo;
	/**协议内容*/
    @Excel(name="协议内容",width=15)
	private java.lang.String protocolContent;
	/**协议扩展字段*/
	private java.lang.String protocolExtent;
	/**协议扩展字段1*/
	private java.lang.String protocolExtent1;
	/**协议扩展字段2*/
	private java.lang.String protocolExtent2;
	/**协议扩展字段3*/
	private java.lang.String protocolExtent3;
	/**协议扩展字段4*/
	private java.lang.String protocolExtent4;
	/**协议扩展字段5*/
	private java.lang.String protocolExtent5;
	/**大事记日期*/
	private java.util.Date protocolBigDate;
	
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
	 *@return: java.lang.String  协议主表id
	 */
	
	@Column(name ="PROTOCOL_ID",nullable=true,length=32)
	public java.lang.String getProtocolId(){
		return this.protocolId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  协议主表id
	 */
	public void setProtocolId(java.lang.String protocolId){
		this.protocolId = protocolId;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  协议大事记
	 */
	
	@Column(name ="PROTOCOL_CONT_NO",nullable=true,length=32)
	public java.lang.String getProtocolContNo(){
		return this.protocolContNo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  协议大事记
	 */
	public void setProtocolContNo(java.lang.String protocolContNo){
		this.protocolContNo = protocolContNo;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  协议内容
	 */
	
	@Column(name ="PROTOCOL_CONTENT",nullable=true,length=32)
	public java.lang.String getProtocolContent(){
		return this.protocolContent;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  协议内容
	 */
	public void setProtocolContent(java.lang.String protocolContent){
		this.protocolContent = protocolContent;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  协议扩展字段
	 */
	
	@Column(name ="PROTOCOL_EXTENT",nullable=true,length=32)
	public java.lang.String getProtocolExtent(){
		return this.protocolExtent;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  协议扩展字段
	 */
	public void setProtocolExtent(java.lang.String protocolExtent){
		this.protocolExtent = protocolExtent;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  协议扩展字段1
	 */
	
	@Column(name ="PROTOCOL_EXTENT1",nullable=true,length=32)
	public java.lang.String getProtocolExtent1(){
		return this.protocolExtent1;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  协议扩展字段1
	 */
	public void setProtocolExtent1(java.lang.String protocolExtent1){
		this.protocolExtent1 = protocolExtent1;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  协议扩展字段2
	 */
	
	@Column(name ="PROTOCOL_EXTENT2",nullable=true,length=32)
	public java.lang.String getProtocolExtent2(){
		return this.protocolExtent2;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  协议扩展字段2
	 */
	public void setProtocolExtent2(java.lang.String protocolExtent2){
		this.protocolExtent2 = protocolExtent2;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  协议扩展字段3
	 */
	
	@Column(name ="PROTOCOL_EXTENT3",nullable=true,length=32)
	public java.lang.String getProtocolExtent3(){
		return this.protocolExtent3;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  协议扩展字段3
	 */
	public void setProtocolExtent3(java.lang.String protocolExtent3){
		this.protocolExtent3 = protocolExtent3;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  协议扩展字段4
	 */
	
	@Column(name ="PROTOCOL_EXTENT4",nullable=true,length=32)
	public java.lang.String getProtocolExtent4(){
		return this.protocolExtent4;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  协议扩展字段4
	 */
	public void setProtocolExtent4(java.lang.String protocolExtent4){
		this.protocolExtent4 = protocolExtent4;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  协议扩展字段5
	 */
	
	@Column(name ="PROTOCOL_EXTENT5",nullable=true,length=32)
	public java.lang.String getProtocolExtent5(){
		return this.protocolExtent5;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  协议扩展字段5
	 */
	public void setProtocolExtent5(java.lang.String protocolExtent5){
		this.protocolExtent5 = protocolExtent5;
	}
	
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  大事记日期
	 */
	
	@Column(name ="PROTOCOL_BIG_DATE",nullable=true,length=32)
	public java.util.Date getProtocolBigDate(){
		return this.protocolBigDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  大事记日期
	 */
	public void setProtocolBigDate(java.util.Date protocolBigDate){
		this.protocolBigDate = protocolBigDate;
	}
	
}
