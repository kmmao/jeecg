package cn.com.linkwide.ba.minventory.entity;

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
 * @Description: 物资库存表
 * @author onlineGenerator
 * @date 2018-08-13 19:20:14
 * @version V1.0   
 *
 */
@Entity
@Table(name = "l_ba_mate_inventory", schema = "")
@SuppressWarnings("serial")
public class LBaMateInventoryEntity implements java.io.Serializable {
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
	/**仓库*/
	@Excel(name="仓库",width=15,dictTable ="l_ba_warehouse",dicCode ="id",dicText ="warehouse_name")
	private java.lang.String warehouseId;
	private java.lang.String warehouseCode;
	private java.lang.String warehouseName;
	/**物资*/
	@Excel(name="物资",width=15,dictTable ="l_ba_material",dicCode ="id",dicText ="material_name")
	private java.lang.String materialId;
	private java.lang.String materialCode;
	private java.lang.String materialName;
	/**是否代管*/
	private java.lang.String isAgency;
	
	/**数量*/
	@Excel(name="数量",width=15)
	private java.math.BigDecimal num;
	/**单价*/
	@Excel(name="单价",width=15)
	private java.math.BigDecimal unitPrice;
	/**批号*/
	@Excel(name="批号",width=15)
	private java.lang.String batchNo;
	/**物资条码*/
	@Excel(name="物资条码",width=15)
	private java.lang.String barCode;
	/**生产日期*/
	@Excel(name="生产日期",width=15,format = "yyyy-MM-dd")
	private java.util.Date makeDate;
	/**有效期*/
	@Excel(name="失效日期",width=15,format = "yyyy-MM-dd")
	private java.util.Date expirDate;
	/**序列号*/
	@Excel(name="序列号",width=15)
	private java.lang.String serial;
	//单据号
	private java.lang.String billNo;
	//单据类型
	private java.lang.String billType;
	/**批次物资ID*/
	@Excel(name="批次物资ID")
	private java.lang.String mateBatchId;

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
	 *@return: java.lang.String  仓库
	 */

	@Column(name ="WAREHOUSE_ID",nullable=true,length=32)
	public java.lang.String getWarehouseId(){
		return this.warehouseId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  仓库
	 */
	public void setWarehouseId(java.lang.String warehouseId){
		this.warehouseId = warehouseId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  物资
	 */

	@Column(name ="MATERIAL_ID",nullable=true,length=32)
	public java.lang.String getMaterialId(){
		return this.materialId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  物资
	 */
	public void setMaterialId(java.lang.String materialId){
		this.materialId = materialId;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  数量
	 */

	@Column(name ="NUM",nullable=true,length=32)
	public java.math.BigDecimal getNum(){
		return this.num;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  数量
	 */
	public void setNum(java.math.BigDecimal num){
		this.num = num;
	}
	/**
	 *方法: 取得java.math.BigDecimal
	 *@return: java.math.BigDecimal  单价
	 */

	@Column(name ="UNIT_PRICE",nullable=true,length=32)
	public java.math.BigDecimal getUnitPrice(){
		return this.unitPrice;
	}

	/**
	 *方法: 设置java.math.BigDecimal
	 *@param: java.math.BigDecimal  单价
	 */
	public void setUnitPrice(java.math.BigDecimal unitPrice){
		this.unitPrice = unitPrice;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  批号
	 */

	@Column(name ="BATCH_NO",nullable=true,length=32)
	public java.lang.String getBatchNo(){
		return this.batchNo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  批号
	 */
	public void setBatchNo(java.lang.String batchNo){
		this.batchNo = batchNo;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  物资条码
	 */

	@Column(name ="BAR_CODE",nullable=true,length=128)
	public java.lang.String getBarCode(){
		return this.barCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  物资条码
	 */
	public void setBarCode(java.lang.String barCode){
		this.barCode = barCode;
	}
	
	 
	@Column(name ="MAKE_DATE",nullable=true,length=32)
	public java.util.Date getMakeDate() {
		return makeDate;
	}
	

	public void setMakeDate(java.util.Date makeDate) {
		this.makeDate = makeDate;
	}
	
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  有效期
	 */

	@Column(name ="EXPIR_DATE",nullable=true,length=32)
	public java.util.Date getExpirDate() {
		return expirDate;
	}

	public void setExpirDate(java.util.Date expirDate) {
		this.expirDate = expirDate;
	}
	
	@Column(name ="IS_AGENCY",nullable=true,length=32)
	public java.lang.String getIsAgency() {
		return isAgency;
	}

	public void setIsAgency(java.lang.String isAgency) {
		this.isAgency = isAgency;
	}
	
	@Column(name ="SERIAL",nullable=true,length=32)
	public java.lang.String getSerial() {
		return serial;
	}

	public void setSerial(java.lang.String serial) {
		this.serial = serial;
	}

	@Transient
	public java.lang.String getBillNo() {
		return billNo;
	}

	public void setBillNo(java.lang.String billNo) {
		this.billNo = billNo;
	}
	@Transient
	public java.lang.String getBillType() {
		return billType;
	}

	public void setBillType(java.lang.String billType) {
		this.billType = billType;
	}
	@Transient
	public java.lang.String getWarehouseCode() {
		return warehouseCode;
	}

	public void setWarehouseCode(java.lang.String warehouseCode) {
		this.warehouseCode = warehouseCode;
	}
	@Transient
	public java.lang.String getWarehouseName() {
		return warehouseName;
	}

	public void setWarehouseName(java.lang.String warehouseName) {
		this.warehouseName = warehouseName;
	}
	@Transient
	public java.lang.String getMaterialCode() {
		return materialCode;
	}

	public void setMaterialCode(java.lang.String materialCode) {
		this.materialCode = materialCode;
	}
	@Transient
	public java.lang.String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(java.lang.String materialName) {
		this.materialName = materialName;
	}
	
	@Column(name ="MATE_BATCH_ID",nullable=true,length=32)
	public java.lang.String getMateBatchId() {
		return mateBatchId;
	}

	public void setMateBatchId(java.lang.String mateBatchId) {
		this.mateBatchId = mateBatchId;
	}
	
}
