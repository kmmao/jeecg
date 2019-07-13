
package cn.com.linkwide.ba.material.qual.page;

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

import cn.com.linkwide.ba.material.qual.entity.LSuMaterialQualInvEntity;

/**   
 * @Title: Entity
 * @Description: 供应商证件表
 * @author onlineGenerator
 * @date 2018-05-17 11:13:05
 * @version V1.0   
 *
 */
public class LSuMaterialQualPage implements java.io.Serializable {
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
	/**证件类型*/
    @Excel(name="证件类型" ,dictTable="l_ba_material_qual_type",dicCode="id", dicText="type_code")
	private java.lang.String certType;
	/**证件编码*/
    @Excel(name="证件编码")
	private java.lang.String certCode;
	/**证件名称*/
    @Excel(name="证件名称")
	private java.lang.String certName;
	/**发证日期*/
    @Excel(name="发证日期",format = "yyyy-MM-dd")
	private java.util.Date certDate;
	/**启用日期*/
    @Excel(name="启用日期",format = "yyyy-MM-dd")
	private java.util.Date startDate;
	/**停用日期*/
    @Excel(name="停用日期",format = "yyyy-MM-dd")
	private java.util.Date endDate;
	/**发证单位*/
    @Excel(name="发证单位")
	private java.lang.String organ;
	/**证件状态*/
    @Excel(name="证件状态")
	private java.lang.Integer certState;
	/**备注*/
    @Excel(name="备注")
	private java.lang.String remark;
    /**供应商*/
    private java.lang.String supplierId;
    @Excel(name="供应商")
    private java.lang.String supplierName;
    
    /**物资id*/
    private java.lang.String mateId; 
    @Excel(name="物资",width=15)
    private java.lang.String materialCode;
	private java.lang.String materialName;
    
    /**是否停用*/
    @Excel(name="是否停用")
    private java.lang.Integer isStop;
   

	/**
	 *方法: 获取java.lang.String
	 *@param: java.lang.String 供应商
	 */
	public java.lang.String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(java.lang.String supplierId) {
		this.supplierId = supplierId;
	} 

	public List<LSuMaterialQualInvEntity> getlSuMaterialQualInvList() {
		return lSuMaterialQualInvList;
	}


	public void setlSuMaterialQualInvList(List<LSuMaterialQualInvEntity> lSuMaterialQualInvList) {
		this.lSuMaterialQualInvList = lSuMaterialQualInvList;
	}

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
	 *@return: java.lang.String  证件类型
	 */
	public java.lang.String getCertType(){
		return this.certType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  证件类型
	 */
	public void setCertType(java.lang.String certType){
		this.certType = certType;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  证件编码
	 */
	public java.lang.String getCertCode(){
		return this.certCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  证件编码
	 */
	public void setCertCode(java.lang.String certCode){
		this.certCode = certCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  证件名称
	 */
	public java.lang.String getCertName(){
		return this.certName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  证件名称
	 */
	public void setCertName(java.lang.String certName){
		this.certName = certName;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  发证日期
	 */
	public java.util.Date getCertDate(){
		return this.certDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  发证日期
	 */
	public void setCertDate(java.util.Date certDate){
		this.certDate = certDate;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  启用日期
	 */
	public java.util.Date getStartDate(){
		return this.startDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  启用日期
	 */
	public void setStartDate(java.util.Date startDate){
		this.startDate = startDate;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  停用日期
	 */
	public java.util.Date getEndDate(){
		return this.endDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  停用日期
	 */
	public void setEndDate(java.util.Date endDate){
		this.endDate = endDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  发证单位
	 */
	public java.lang.String getOrgan(){
		return this.organ;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  发证单位
	 */
	public void setOrgan(java.lang.String organ){
		this.organ = organ;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  证件状态
	 */
	public java.lang.Integer getCertState(){
		return this.certState;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  证件状态
	 */
	public void setCertState(java.lang.Integer certState){
		this.certState = certState;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  备注
	 */
	public java.lang.String getRemark(){
		return this.remark;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  备注
	 */
	public void setRemark(java.lang.String remark){
		this.remark = remark;
	}
	
	 public java.lang.Integer getIsStop() {
		return isStop;
	}

	public void setIsStop(java.lang.Integer isStop) {
		this.isStop = isStop;
	}

	
	public java.lang.String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(java.lang.String supplierName) {
		this.supplierName = supplierName;
	}

	public java.lang.String getMateId() {
		return mateId;
	}

	public void setMateId(java.lang.String mateId) {
		this.mateId = mateId;
	}

	public java.lang.String getMaterialCode() {
		return materialCode;
	}

	public void setMaterialCode(java.lang.String materialCode) {
		this.materialCode = materialCode;
	}

	public java.lang.String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(java.lang.String materialName) {
		this.materialName = materialName;
	}


	/**保存-证件材料对应关系*/
    /*@ExcelCollection(name="证件材料对应关系")*/
	private List<LSuMaterialQualInvEntity> lSuMaterialQualInvList = new ArrayList<LSuMaterialQualInvEntity>();
		public List<LSuMaterialQualInvEntity> getLSuMaterialQualInvList() {
		return lSuMaterialQualInvList;
		}
		public void setLSuMaterialQualInvList(List<LSuMaterialQualInvEntity> lSuMaterialQualInvList) {
		this.lSuMaterialQualInvList = lSuMaterialQualInvList;
		}
}
