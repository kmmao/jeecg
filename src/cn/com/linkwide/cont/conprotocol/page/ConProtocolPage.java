
package cn.com.linkwide.cont.conprotocol.page;
import cn.com.linkwide.cont.conprotocol.entity.ConProtocolEntity;
import cn.com.linkwide.cont.conprotocoldetail.entity.ConProtocolDetailEntity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.SequenceGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;

/**   
 * @Title: Entity
 * @Description: 协议
 * @author onlineGenerator
 * @date 2018-09-17 18:30:36
 * @version V1.0   
 *
 */
public class ConProtocolPage implements java.io.Serializable {
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
    @Excel(name="协议流水号")
	private java.lang.String protocolNumber;
	/**协议编码*/
    @Excel(name="协议编码")
	private java.lang.String protocolNo;
	/**协议名称*/
    @Excel(name="协议名称")
	private java.lang.String protocolName;
	/**协议金额*/
    @Excel(name="协议金额")
	private java.lang.Double protocolMoney;
	/**协议签订时间*/
    @Excel(name="协议签订时间",format = "yyyy-MM-dd")
	private java.util.Date singeDate;
	/**协议开始时间*/
    @Excel(name="协议开始时间",format = "yyyy-MM-dd")
	private java.util.Date protocolStart;
	/**协议结束时间*/
    @Excel(name="协议结束时间",format = "yyyy-MM-dd")
	private java.util.Date protocolEnd;
	/**科室*/
    @Excel(name="科室")
	private java.lang.String deptCode;
	/**计量单位*/
    @Excel(name="计量单位")
	private java.lang.String protocolUnit;
	/**对方名称*/
    @Excel(name="对方名称")
	private java.lang.String otherName;
	/**对方身份证号*/
    @Excel(name="对方身份证号")
	private java.lang.String otherIdcode;
	/**生效人*/
    @Excel(name="生效人")
	private java.lang.String effectEmp;
	/**生效日期*/
    @Excel(name="生效日期")
	private java.lang.String effectDate;
	/**审核人*/
    @Excel(name="审核人")
	private java.lang.String approvalEmp;
	/**审核日期*/
    @Excel(name="审核日期")
	private java.lang.String approvalDate;
	/**审核状态*/
    @Excel(name="审核状态")
	private java.lang.String approvalState;
	/**审批流id*/
    @Excel(name="审批流id")
	private java.lang.String approvalId;
	/**协议状态*/
    @Excel(name="协议状态")
	private java.lang.String protocolState;
	/**协议备注*/
    @Excel(name="协议备注")
	private java.lang.String protocolBz;
	/**预留字段1*/
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
    @Excel(name="对方联系方式")
	private java.lang.String otherTeleph;
	/**合同id*/
	private java.lang.String protocolCont;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  主键
	 */
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
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  生效日期
	 */
	public java.lang.String getEffectDate(){
		return this.effectDate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  生效日期
	 */
	public void setEffectDate(java.lang.String effectDate){
		this.effectDate = effectDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  审核人
	 */
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
	public java.lang.String getApprovalDate(){
		return this.approvalDate;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  审核日期
	 */
	public void setApprovalDate(java.lang.String approvalDate){
		this.approvalDate = approvalDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  审核状态
	 */
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

	/**保存-合同协议*/
    @ExcelCollection(name="合同协议")
	private List<ConProtocolDetailEntity> conProtocolDetailList = new ArrayList<ConProtocolDetailEntity>();
		public List<ConProtocolDetailEntity> getConProtocolDetailList() {
		return conProtocolDetailList;
		}
		public void setConProtocolDetailList(List<ConProtocolDetailEntity> conProtocolDetailList) {
		this.conProtocolDetailList = conProtocolDetailList;
		}
}
