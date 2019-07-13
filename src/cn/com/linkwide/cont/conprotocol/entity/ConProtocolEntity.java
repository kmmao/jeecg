package cn.com.linkwide.cont.conprotocol.entity;
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

import cn.com.linkwide.common.delcheck.annotation.QueryParameterSettings;

/**   
 * @Title: Entity
 * @Description: 协议
 * @author onlineGenerator
 * @date 2018-09-17 18:30:36
 * @version V1.0   
 *
 */
@Entity
@Table(name = "con_protocol", schema = "")
@SuppressWarnings("serial")
public class ConProtocolEntity implements java.io.Serializable {
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
	/**协议流水号*/
    @Excel(name="协议流水号",width=15)
	private java.lang.String protocolNumber;
	/**协议编码*/
    @Excel(name="协议编码",width=15)
	private java.lang.String protocolNo;
	/**协议名称*/
    @Excel(name="协议名称",width=15)
	private java.lang.String protocolName;
	/**协议金额*/
    @Excel(name="协议金额",width=15)
	private java.lang.Double protocolMoney;
	/**协议签订时间*/
    @Excel(name="协议签订时间",width=15,format = "yyyy-MM-dd")
	private java.util.Date singeDate;
	/**协议开始时间*/
    @Excel(name="协议开始时间",width=15,format = "yyyy-MM-dd")
	private java.util.Date protocolStart;
	/**协议结束时间*/
    @Excel(name="协议结束时间",width=15,format = "yyyy-MM-dd")
	private java.util.Date protocolEnd;
	/**科室*/
    @Excel(name="科室",width=15)
	private java.lang.String deptCode;
	/**计量单位*/
    @Excel(name="计量单位",width=15)
	private java.lang.String protocolUnit;
	/**对方名称*/
    @Excel(name="对方名称",width=15)
	private java.lang.String otherName;
	/**对方身份证号*/
    @Excel(name="对方身份证号",width=15)
	private java.lang.String otherIdcode;
	/**生效人*/
    @Excel(name="生效人",width=15)
	private java.lang.String effectEmp;
	/**生效日期*/
    @Excel(name="生效日期",width=15)
	private java.util.Date effectDate;
	/**审核人*/
    @Excel(name="审核人",width=15)
	private java.lang.String approvalEmp;
	/**审核日期*/
    @Excel(name="审核日期",width=15)
	private java.util.Date approvalDate;
	/**审核状态*/
    @Excel(name="审核状态",width=15)
	private java.lang.String approvalState;
	/**审批流id*/
    @Excel(name="审批流id",width=15)
	private java.lang.String approvalId;
	/**协议状态*/
    @Excel(name="协议状态",width=15)
	private java.lang.String protocolState;
	/**协议备注*/
    @Excel(name="协议备注",width=15)
	private java.lang.String protocolBz;
    @Excel(name="协议类型",width=15)
	@QueryParameterSettings(message="当前编码已存在协议中不能删除!")
	private java.lang.String protocolExect;
	/**预留字段2*/
	private java.lang.String protocolExect2;
	/**预留字段3*/
	private java.lang.String protocolExect3;
	/**预留字段4*/
	private java.lang.String protocolExect4;
	/**预留字段5*/
	private java.lang.String protocolExect5;
	/**对方联系方式*/
    @Excel(name="对方联系方式",width=15)
	private java.lang.String otherTeleph;
	/**合同id*/
	private java.lang.String protocolCont;
	
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
	 *@return: java.lang.String  协议流水号
	 */
	
	@Column(name ="PROTOCOL_NUMBER",nullable=true,length=32)
	public java.lang.String getProtocolNumber(){
		return this.protocolNumber;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  协议流水号
	 */
	public void setProtocolNumber(java.lang.String protocolNumber){
		this.protocolNumber = protocolNumber;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  协议编码
	 */
	
	@Column(name ="PROTOCOL_NO",nullable=true,length=32)
	public java.lang.String getProtocolNo(){
		return this.protocolNo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  协议编码
	 */
	public void setProtocolNo(java.lang.String protocolNo){
		this.protocolNo = protocolNo;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  协议名称
	 */
	
	@Column(name ="PROTOCOL_NAME",nullable=true,length=50)
	public java.lang.String getProtocolName(){
		return this.protocolName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  协议名称
	 */
	public void setProtocolName(java.lang.String protocolName){
		this.protocolName = protocolName;
	}
	
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  协议金额
	 */
	
	@Column(name ="PROTOCOL_MONEY",nullable=true,scale=2,length=32)
	public java.lang.Double getProtocolMoney(){
		return this.protocolMoney;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  协议金额
	 */
	public void setProtocolMoney(java.lang.Double protocolMoney){
		this.protocolMoney = protocolMoney;
	}
	
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  协议签订时间
	 */
	
	@Column(name ="SINGE_DATE",nullable=true,length=32)
	public java.util.Date getSingeDate(){
		return this.singeDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  协议签订时间
	 */
	public void setSingeDate(java.util.Date singeDate){
		this.singeDate = singeDate;
	}
	
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  协议开始时间
	 */
	
	@Column(name ="PROTOCOL_START",nullable=true,length=32)
	public java.util.Date getProtocolStart(){
		return this.protocolStart;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  协议开始时间
	 */
	public void setProtocolStart(java.util.Date protocolStart){
		this.protocolStart = protocolStart;
	}
	
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  协议结束时间
	 */
	
	@Column(name ="PROTOCOL_END",nullable=true,length=32)
	public java.util.Date getProtocolEnd(){
		return this.protocolEnd;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  协议结束时间
	 */
	public void setProtocolEnd(java.util.Date protocolEnd){
		this.protocolEnd = protocolEnd;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  科室
	 */
	
	@Column(name ="DEPT_CODE",nullable=true,length=32)
	public java.lang.String getDeptCode(){
		return this.deptCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  科室
	 */
	public void setDeptCode(java.lang.String deptCode){
		this.deptCode = deptCode;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  计量单位
	 */
	
	@Column(name ="PROTOCOL_UNIT",nullable=true,length=32)
	public java.lang.String getProtocolUnit(){
		return this.protocolUnit;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  计量单位
	 */
	public void setProtocolUnit(java.lang.String protocolUnit){
		this.protocolUnit = protocolUnit;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  对方名称
	 */
	
	@Column(name ="OTHER_NAME",nullable=true,length=32)
	public java.lang.String getOtherName(){
		return this.otherName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  对方名称
	 */
	public void setOtherName(java.lang.String otherName){
		this.otherName = otherName;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  对方身份证号
	 */
	
	@Column(name ="OTHER_IDCODE",nullable=true,length=32)
	public java.lang.String getOtherIdcode(){
		return this.otherIdcode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  对方身份证号
	 */
	public void setOtherIdcode(java.lang.String otherIdcode){
		this.otherIdcode = otherIdcode;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  生效人
	 */
	
	@Column(name ="EFFECT_EMP",nullable=true,length=32)
	public java.lang.String getEffectEmp(){
		return this.effectEmp;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  生效人
	 */
	public void setEffectEmp(java.lang.String effectEmp){
		this.effectEmp = effectEmp;
	}
	
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  生效日期
	 */
	
	@Column(name ="EFFECT_DATE",nullable=true,length=32)
	public java.util.Date getEffectDate(){
		return this.effectDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  生效日期
	 */
	public void setEffectDate(java.util.Date effectDate){
		this.effectDate = effectDate;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  审核人
	 */
	
	@Column(name ="APPROVAL_EMP",nullable=true,length=32)
	public java.lang.String getApprovalEmp(){
		return this.approvalEmp;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  审核人
	 */
	public void setApprovalEmp(java.lang.String approvalEmp){
		this.approvalEmp = approvalEmp;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  审核日期
	 */
	
	@Column(name ="APPROVAL_DATE",nullable=true,length=32)
	public java.util.Date getApprovalDate(){
		return this.approvalDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  审核日期
	 */
	public void setApprovalDate(java.util.Date approvalDate){
		this.approvalDate = approvalDate;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  审核状态
	 */
	
	@Column(name ="APPROVAL_STATE",nullable=true,length=32)
	public java.lang.String getApprovalState(){
		return this.approvalState;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  审核状态
	 */
	public void setApprovalState(java.lang.String approvalState){
		this.approvalState = approvalState;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  审批流id
	 */
	
	@Column(name ="APPROVAL_ID",nullable=true,length=32)
	public java.lang.String getApprovalId(){
		return this.approvalId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  审批流id
	 */
	public void setApprovalId(java.lang.String approvalId){
		this.approvalId = approvalId;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  协议状态
	 */
	
	@Column(name ="PROTOCOL_STATE",nullable=true,length=32)
	public java.lang.String getProtocolState(){
		return this.protocolState;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  协议状态
	 */
	public void setProtocolState(java.lang.String protocolState){
		this.protocolState = protocolState;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  协议备注
	 */
	
	@Column(name ="PROTOCOL_BZ",nullable=true,length=32)
	public java.lang.String getProtocolBz(){
		return this.protocolBz;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  协议备注
	 */
	public void setProtocolBz(java.lang.String protocolBz){
		this.protocolBz = protocolBz;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预留字段1
	 */
	
	@Column(name ="PROTOCOL_EXECT",nullable=true,length=32)
	public java.lang.String getProtocolExect(){
		return this.protocolExect;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预留字段1
	 */
	public void setProtocolExect(java.lang.String protocolExect){
		this.protocolExect = protocolExect;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预留字段2
	 */
	
	@Column(name ="PROTOCOL_EXECT2",nullable=true,length=32)
	public java.lang.String getProtocolExect2(){
		return this.protocolExect2;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预留字段2
	 */
	public void setProtocolExect2(java.lang.String protocolExect2){
		this.protocolExect2 = protocolExect2;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预留字段3
	 */
	
	@Column(name ="PROTOCOL_EXECT3",nullable=true,length=32)
	public java.lang.String getProtocolExect3(){
		return this.protocolExect3;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预留字段3
	 */
	public void setProtocolExect3(java.lang.String protocolExect3){
		this.protocolExect3 = protocolExect3;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预留字段4
	 */
	
	@Column(name ="PROTOCOL_EXECT4",nullable=true,length=32)
	public java.lang.String getProtocolExect4(){
		return this.protocolExect4;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预留字段4
	 */
	public void setProtocolExect4(java.lang.String protocolExect4){
		this.protocolExect4 = protocolExect4;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预留字段5
	 */
	
	@Column(name ="PROTOCOL_EXECT5",nullable=true,length=32)
	public java.lang.String getProtocolExect5(){
		return this.protocolExect5;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预留字段5
	 */
	public void setProtocolExect5(java.lang.String protocolExect5){
		this.protocolExect5 = protocolExect5;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  对方联系方式
	 */
	
	@Column(name ="OTHER_TELEPH",nullable=true,length=32)
	public java.lang.String getOtherTeleph(){
		return this.otherTeleph;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  对方联系方式
	 */
	public void setOtherTeleph(java.lang.String otherTeleph){
		this.otherTeleph = otherTeleph;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  合同id
	 */
	
	@Column(name ="PROTOCOL_CONT",nullable=true,length=32)
	public java.lang.String getProtocolCont(){
		return this.protocolCont;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  合同id
	 */
	public void setProtocolCont(java.lang.String protocolCont){
		this.protocolCont = protocolCont;
	}
	
}
