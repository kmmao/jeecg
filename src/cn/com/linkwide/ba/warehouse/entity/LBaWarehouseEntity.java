package cn.com.linkwide.ba.warehouse.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

/**   
 * @Title: Entity
 * @Description: 仓库表
 * @author onlineGenerator
 * @date 2017-11-08 17:15:34
 * @version V1.0   
 *
 */
@Entity
@Table(name = "l_ba_warehouse", schema = "")
@SuppressWarnings("serial")
public class LBaWarehouseEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**仓库编码*/
	@Excel(name="仓库编码")
	private java.lang.String warehouseCode;
	/**仓库名称*/
	@Excel(name="仓库名称")
	private java.lang.String warehouseName;
	/**是否计入成本0:否1:是*/
	@Excel(name="是否计入成本0:否1:是")
	private java.lang.String isIncludeCost;
	/**是否资产仓0:否1:是*/
	@Excel(name="是否资产仓0:否1:是")
	private java.lang.String isAssets;
	/**登陆部门*/
	@Excel(name="登陆部门")
	private java.lang.String departId;
	/**创建人*/
	@Excel(name="创建人")
	private java.lang.String createBy;
	/**创建时间*/
	@Excel(name="创建时间",format = "yyyy-MM-dd")
	private java.util.Date createDate;
	/**更新人*/
	@Excel(name="更新人")
	private java.lang.String updateBy;
	/**更新时间*/
	@Excel(name="更新时间",format = "yyyy-MM-dd")
	private java.util.Date updateDate;
	
	/**负责人*/
	@Excel(name="负责人")
	private java.lang.String manager;
	/**仓库属性*/
	@Excel(name="仓库属性")
	private java.lang.String attr;
	/**部门*/
	@Excel(name="部门")
	private java.lang.String deptId;
	/**条形码*/
	@Excel(name="条形码")
	private java.lang.String barCode;
	

	/**地址*/
	@Excel(name="地址")
	private java.lang.String address;
	/**是否控制*/
	@Excel(name="是否控制")
	private java.lang.Integer isCtrl;
	/**是否控制*/
	@Excel(name="是否货位管理")
	private java.lang.Integer isLocation;
	/**备注*/
	@Excel(name="备注")
	private java.lang.String remark;
	private java.lang.String orgCodes;
	private java.lang.String orgNames;
	
	

	/**所属公司*/
	private java.lang.String sysCompanyCode;
	@Column(name ="SYS_COMPANY_CODE",nullable=true,length=50)
	public java.lang.String getSysCompanyCode(){
		return this.sysCompanyCode;
	}
	public void setSysCompanyCode(java.lang.String sysCompanyCode){
		this.sysCompanyCode = sysCompanyCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  id
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
	 *@param: java.lang.String  id
	 */
	public void setId(java.lang.String id){
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  仓库编码
	 */
	@Column(name ="WAREHOUSE_CODE",nullable=false,length=36)
	public java.lang.String getWarehouseCode(){
		return this.warehouseCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  仓库编码
	 */
	public void setWarehouseCode(java.lang.String warehouseCode){
		this.warehouseCode = warehouseCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  仓库名称
	 */
	@Column(name ="WAREHOUSE_NAME",nullable=false,length=36)
	public java.lang.String getWarehouseName(){
		return this.warehouseName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  仓库名称
	 */
	public void setWarehouseName(java.lang.String warehouseName){
		this.warehouseName = warehouseName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否计入成本0:否1:是
	 */
	@Column(name ="IS_INCLUDE_COST",nullable=false,length=2)
	public java.lang.String getIsIncludeCost(){
		return this.isIncludeCost;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否计入成本0:否1:是
	 */
	public void setIsIncludeCost(java.lang.String isIncludeCost){
		this.isIncludeCost = isIncludeCost;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否资产仓0:否1:是
	 */
	@Column(name ="IS_ASSETS",nullable=false,length=2)
	public java.lang.String getIsAssets(){
		return this.isAssets;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否资产仓0:否1:是
	 */
	public void setIsAssets(java.lang.String isAssets){
		this.isAssets = isAssets;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  登陆部门
	 */
	@Column(name ="DEPART_ID",nullable=false,length=50)
	public java.lang.String getDepartId(){
		return this.departId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  登陆部门
	 */
	public void setDepartId(java.lang.String departId){
		this.departId = departId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建人
	 */
	@Column(name ="CREATE_BY",nullable=false,length=50)
	public java.lang.String getCreateBy(){
		return this.createBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人
	 */
	public void setCreateBy(java.lang.String createBy){
		this.createBy = createBy;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  创建时间
	 */
	@Column(name ="CREATE_DATE",nullable=false)
	public java.util.Date getCreateDate(){
		return this.createDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  创建时间
	 */
	public void setCreateDate(java.util.Date createDate){
		this.createDate = createDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  更新人
	 */
	@Column(name ="UPDATE_BY",nullable=true,length=50)
	public java.lang.String getUpdateBy(){
		return this.updateBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  更新人
	 */
	public void setUpdateBy(java.lang.String updateBy){
		this.updateBy = updateBy;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  更新时间
	 */
	@Column(name ="UPDATE_DATE",nullable=true)
	public java.util.Date getUpdateDate(){
		return this.updateDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  更新时间
	 */
	public void setUpdateDate(java.util.Date updateDate){
		this.updateDate = updateDate;
	}
	
	@Column(name ="MANAGER",nullable=true,length=32)
	public java.lang.String getManager() {
		return manager;
	}

	public void setManager(java.lang.String manager) {
		this.manager = manager;
	}
	@Column(name ="ATTR",nullable=true,length=32)
	public java.lang.String getAttr() {
		return attr;
	}

	public void setAttr(java.lang.String attr) {
		this.attr = attr;
	}
	@Column(name ="DEPT_ID",nullable=true,length=32)
	public java.lang.String getDeptId() {
		return deptId;
	}

	public void setDeptId(java.lang.String deptId) {
		this.deptId = deptId;
	}
	@Column(name ="BAR_CODE",nullable=true,length=60)
	public java.lang.String getBarCode() {
		return barCode;
	}

	public void setBarCode(java.lang.String barCode) {
		this.barCode = barCode;
	}
	@Column(name ="ADDRESS",nullable=true,length=255)
	public java.lang.String getAddress() {
		return address;
	}

	public void setAddress(java.lang.String address) {
		this.address = address;
	}
	@Column(name ="IS_CTRL",nullable=true,length=32)
	public java.lang.Integer getIsCtrl() {
		return isCtrl;
	}

	public void setIsCtrl(java.lang.Integer isCtrl) {
		this.isCtrl = isCtrl;
	}
	@Column(name ="REMARK",nullable=true,length=255)
	public java.lang.String getRemark() {
		return remark;
	}

	public void setRemark(java.lang.String remark) {
		this.remark = remark;
	}
	@Column(name ="IS_LOCATION",nullable=true,length=2)
	public java.lang.Integer getIsLocation() {
		return isLocation;
	}
	public void setIsLocation(java.lang.Integer isLocation) {
		this.isLocation = isLocation;
	}
	@Transient
	public java.lang.String getOrgCodes() {
		return orgCodes;
	}
	public void setOrgCodes(java.lang.String orgCodes) {
		this.orgCodes = orgCodes;
	}
	@Transient
	public java.lang.String getOrgNames() {
		return orgNames;
	}
	public void setOrgNames(java.lang.String orgNames) {
		this.orgNames = orgNames;
	}
}
