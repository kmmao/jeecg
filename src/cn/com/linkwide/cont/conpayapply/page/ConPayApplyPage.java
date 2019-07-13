
package cn.com.linkwide.cont.conpayapply.page;
import cn.com.linkwide.cont.conpayapply.entity.ConPayApplyEntity;
import cn.com.linkwide.cont.conpayapplydetail.entity.ConPayApplyDetailEntity;

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
 * @Description: 付款申请表
 * @author onlineGenerator
 * @date 2018-11-26 13:52:13
 * @version V1.0   
 *
 */
public class ConPayApplyPage implements java.io.Serializable {
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
	/**申请日期*/
    @Excel(name="申请日期",format = "yyyy-MM-dd")
	private java.util.Date applyDate;
	/**申请单号*/
    @Excel(name="申请单号")
	private java.lang.String applyNo;
	/**供应商*/
    @Excel(name="供应商")
	private java.lang.String otherCompy;
	/**合同主表id*/
    @Excel(name="合同主表id")
	private java.lang.String conId;
	/**合同名称*/
    @Excel(name="合同名称")
	private java.lang.String conName;
    /**合同编号*/
    @Excel(name="合同编号",width=15)
	private java.lang.String conNo;
	/**合同流水号*/
    @Excel(name="合同流水号")
	private java.lang.String conNumber;
	/**申请金额*/
    @Excel(name="申请金额")
	private java.lang.String sumMoney;
	
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
	
	
	


	public java.lang.String getConNo() {
		return this.conNo;
	}

	public void setConNo(java.lang.String conNo) {
		this.conNo = conNo;
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
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  申请日期
	 */
	public java.util.Date getApplyDate(){
		return this.applyDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  申请日期
	 */
	public void setApplyDate(java.util.Date applyDate){
		this.applyDate = applyDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  申请单号
	 */
	public java.lang.String getApplyNo(){
		return this.applyNo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  申请单号
	 */
	public void setApplyNo(java.lang.String applyNo){
		this.applyNo = applyNo;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  供应商
	 */
	public java.lang.String getOtherCompy(){
		return this.otherCompy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  供应商
	 */
	public void setOtherCompy(java.lang.String otherCompy){
		this.otherCompy = otherCompy;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  合同主表id
	 */
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
	 *@return: java.lang.String  合同名称
	 */
	public java.lang.String getConName(){
		return this.conName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  合同名称
	 */
	public void setConName(java.lang.String conName){
		this.conName = conName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  合同流水号
	 */
	public java.lang.String getConNumber(){
		return this.conNumber;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  合同流水号
	 */
	public void setConNumber(java.lang.String conNumber){
		this.conNumber = conNumber;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  申请金额
	 */
	public java.lang.String getSumMoney(){
		return this.sumMoney;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  申请金额
	 */
	public void setSumMoney(java.lang.String sumMoney){
		this.sumMoney = sumMoney;
	}

	/**保存-付款申请明细*/
    @ExcelCollection(name="付款申请明细")
	private List<ConPayApplyDetailEntity> conPayApplyDetailList = new ArrayList<ConPayApplyDetailEntity>();
		public List<ConPayApplyDetailEntity> getConPayApplyDetailList() {
		return conPayApplyDetailList;
		}
		public void setConPayApplyDetailList(List<ConPayApplyDetailEntity> conPayApplyDetailList) {
		this.conPayApplyDetailList = conPayApplyDetailList;
		}
}
