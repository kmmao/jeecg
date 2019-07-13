
package cn.com.linkwide.cont.negotiation.conreqinstructionsmain.page;
import cn.com.linkwide.cont.negotiation.conreqinstructionsmain.entity.ConReqInstructionsMainEntity;
import cn.com.linkwide.cont.negotiation.conreqinstructionsdetail.entity.ConReqInstructionsDetailEntity;

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
 * @Description: 请示单主表
 * @author onlineGenerator
 * @date 2019-04-26 11:11:09
 * @version V1.0   
 *
 */
public class ConReqInstructionsMainPage implements java.io.Serializable {
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
	/**请示日期*/
    @Excel(name="请示日期",format = "yyyy-MM-dd")
	private java.util.Date requDate;
	/**请示单号*/
    @Excel(name="请示单号")
	private java.lang.String requNo;
	/**请示人*/
	private java.lang.String requEmp;
	/**请示编码*/
	private java.lang.String requCode;
	/**请示名称*/
	private java.lang.String requName;
	/**预留字段1*/
	private java.lang.String requExtent1;
	/**预留字段2*/
	private java.lang.String requExtent2;
	/**预留字段3*/
	private java.lang.String requExtent3;
	/**预留字段4*/
	private java.lang.String requExtent4;
	/**预留字段5*/
	private java.lang.String requExtent5;
	
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
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  请示日期
	 */
	public java.util.Date getRequDate(){
		return this.requDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  请示日期
	 */
	public void setRequDate(java.util.Date requDate){
		this.requDate = requDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  请示单号
	 */
	public java.lang.String getRequNo(){
		return this.requNo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  请示单号
	 */
	public void setRequNo(java.lang.String requNo){
		this.requNo = requNo;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  请示人
	 */
	public java.lang.String getRequEmp(){
		return this.requEmp;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  请示人
	 */
	public void setRequEmp(java.lang.String requEmp){
		this.requEmp = requEmp;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  请示编码
	 */
	public java.lang.String getRequCode(){
		return this.requCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  请示编码
	 */
	public void setRequCode(java.lang.String requCode){
		this.requCode = requCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  请示名称
	 */
	public java.lang.String getRequName(){
		return this.requName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  请示名称
	 */
	public void setRequName(java.lang.String requName){
		this.requName = requName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预留字段1
	 */
	public java.lang.String getRequExtent1(){
		return this.requExtent1;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预留字段1
	 */
	public void setRequExtent1(java.lang.String requExtent1){
		this.requExtent1 = requExtent1;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预留字段2
	 */
	public java.lang.String getRequExtent2(){
		return this.requExtent2;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预留字段2
	 */
	public void setRequExtent2(java.lang.String requExtent2){
		this.requExtent2 = requExtent2;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预留字段3
	 */
	public java.lang.String getRequExtent3(){
		return this.requExtent3;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预留字段3
	 */
	public void setRequExtent3(java.lang.String requExtent3){
		this.requExtent3 = requExtent3;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预留字段4
	 */
	public java.lang.String getRequExtent4(){
		return this.requExtent4;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预留字段4
	 */
	public void setRequExtent4(java.lang.String requExtent4){
		this.requExtent4 = requExtent4;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预留字段5
	 */
	public java.lang.String getRequExtent5(){
		return this.requExtent5;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预留字段5
	 */
	public void setRequExtent5(java.lang.String requExtent5){
		this.requExtent5 = requExtent5;
	}

	/**保存-请示单明细*/
    @ExcelCollection(name="请示单明细")
	private List<ConReqInstructionsDetailEntity> conReqInstructionsDetailList = new ArrayList<ConReqInstructionsDetailEntity>();
		public List<ConReqInstructionsDetailEntity> getConReqInstructionsDetailList() {
		return conReqInstructionsDetailList;
		}
		public void setConReqInstructionsDetailList(List<ConReqInstructionsDetailEntity> conReqInstructionsDetailList) {
		this.conReqInstructionsDetailList = conReqInstructionsDetailList;
		}
}
