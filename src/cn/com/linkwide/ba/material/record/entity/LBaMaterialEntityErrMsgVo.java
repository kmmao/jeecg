package cn.com.linkwide.ba.material.record.entity;

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
 * @Description: 物资基础档案管理
 * @author onlineGenerator
 * @date 2017-11-15 10:28:43
 * @version V1.0   
 *
 */
@Entity
@Table(name = "l_ba_material", schema = "")
@SuppressWarnings("serial")
public class LBaMaterialEntityErrMsgVo implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**物资编码*/
	@Excel(name="物资编码")
	private java.lang.String materialCode;
	/**物资名称*/
	@Excel(name="物资名称")
	private java.lang.String materialName;
	/**物资分类*/
	@Excel(name="物资分类")
	private java.lang.String materialTypeId;
	/**规格型号*/
	@Excel(name="规格型号")
	private java.lang.String specModel;
	/**助记码*/
	@Excel(name="助记码")
	private java.lang.String monmCode;
	/**计量单位*/
	private java.lang.String materUnitId;
	@Excel(name="计量单位")
	private java.lang.String materUnitName;
	/**器械分类*/
	@Excel(name="器械分类")
	private java.lang.String appaTypeId;
	/**财务分类*/
	@Excel(name="财务分类")
	private java.lang.String financeTypeId;
	/**国标分类*/
	@Excel(name="国标分类")
	private java.lang.String standTypeId;
	/**是否耗材*/
	@Excel(name="是否耗材")
	private java.lang.String isCons;
	/**是否资产*/
	@Excel(name="是否资产")
	private java.lang.String isAssets;
	/**是否计量*/
	@Excel(name="是否计量")
	private java.lang.String isMater;
	/**主供应商*/
	private java.lang.String supplierId;
	@Excel(name="主供应商")
	private java.lang.String supplierName;
	/**默认仓库*/
	private java.lang.String warehouseId;
	@Excel(name="默认仓库")
	private java.lang.String warehouseName;
	/**最高库存*/
	@Excel(name="最高库存")
	private java.lang.String maxStock;
	/**安全库存*/
	@Excel(name="安全库存")
	private java.lang.String safeStock;
	/**最低库存*/
	@Excel(name="最低库存")
	private java.lang.String minStock;
	/**是否批次管理*/
	@Excel(name="是否批次管理")
	private java.lang.String isBatch;
	/**是否出库跟踪入库*/
	@Excel(name="是否出库跟踪入库")
	private java.lang.String isOutIn;
	/**是否保质期管理*/
	@Excel(name="是否保质期管理")
	private java.lang.String isShelfLife;
	/**保质期*/
	@Excel(name="保质期")
	private java.lang.String shelfLife;
	/**预警天数*/
	@Excel(name="预警天数")
	private java.lang.String warningDay;
	/**是否高值耗材*/
	@Excel(name="是否高值耗材")
	private java.lang.String isHighCons;
	/**是否条形码管理*/
	@Excel(name="是否条形码管理")
	private java.lang.String isBarCode;
	/**条形码类型*/
	@Excel(name="条形码类型")
	private java.lang.String bartype;
	/**条形码*/
	//@Excel(name="条形码")
	private java.lang.String barCode;
	/**计价方式*/
	@Excel(name="计价方式")
	private java.lang.String valueMethod;
	/**登陆部门*/
	//@Excel(name="登陆部门")
	private java.lang.String departId;
	/**创建人*/
	//@Excel(name="创建人")
	private java.lang.String createBy;
	/**创建时间*/
	//@Excel(name="创建时间",format = "yyyy-MM-dd")
	private java.util.Date createDate;
	/**更新人*/
	//@Excel(name="更新人")
	private java.lang.String updateBy;
	/**更新时间*/
	//@Excel(name="更新时间",format = "yyyy-MM-dd")
	private java.util.Date updateDate;
	/**是否代管*/
	@Excel(name="是否代管")
	private java.lang.String isInstead;
	/**单价*/
	@Excel(name="预估单价")
	private String unitPrice;
	@Excel(name="是否质检")
	private java.lang.String isQuality;
	
	@Excel(name="错误信息")
	private String errMsg;
	
	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
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
	 *@return: java.lang.String  物资编码
	 */
	@Column(name ="MATERIAL_CODE",nullable=false,length=36)
	public java.lang.String getMaterialCode(){
		return this.materialCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  物资编码
	 */
	public void setMaterialCode(java.lang.String materialCode){
		this.materialCode = materialCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  物资名称
	 */
	@Column(name ="MATERIAL_NAME",nullable=false,length=36)
	public java.lang.String getMaterialName(){
		return this.materialName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  物资名称
	 */
	public void setMaterialName(java.lang.String materialName){
		this.materialName = materialName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  物资分类
	 */
	@Column(name ="MATERIAL_TYPE_ID",nullable=false,length=36)
	public java.lang.String getMaterialTypeId(){
		return this.materialTypeId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  物资分类
	 */
	public void setMaterialTypeId(java.lang.String materialTypeId){
		this.materialTypeId = materialTypeId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  规格型号
	 */
	@Column(name ="SPEC_MODEL",nullable=true,length=36)
	public java.lang.String getSpecModel(){
		return this.specModel;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  规格型号
	 */
	public void setSpecModel(java.lang.String specModel){
		this.specModel = specModel;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  助记码
	 */
	@Column(name ="MONM_CODE",nullable=true,length=60)
	public java.lang.String getMonmCode(){
		return this.monmCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  助记码
	 */
	public void setMonmCode(java.lang.String monmCode){
		this.monmCode = monmCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  计量单位
	 */
	@Column(name ="MATER_UNIT_ID",nullable=false,length=36)
	public java.lang.String getMaterUnitId(){
		return this.materUnitId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  计量单位
	 */
	public void setMaterUnitId(java.lang.String materUnitId){
		this.materUnitId = materUnitId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  器械分类
	 */
	@Column(name ="APPA_TYPE_ID",nullable=false,length=36)
	public java.lang.String getAppaTypeId(){
		return this.appaTypeId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  器械分类
	 */
	public void setAppaTypeId(java.lang.String appaTypeId){
		this.appaTypeId = appaTypeId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  财务分类
	 */
	@Column(name ="FINANCE_TYPE_ID",nullable=false,length=36)
	public java.lang.String getFinanceTypeId(){
		return this.financeTypeId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  财务分类
	 */
	public void setFinanceTypeId(java.lang.String financeTypeId){
		this.financeTypeId = financeTypeId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  国标分类
	 */
	@Column(name ="STAND_TYPE_ID",nullable=false,length=36)
	public java.lang.String getStandTypeId(){
		return this.standTypeId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  国标分类
	 */
	public void setStandTypeId(java.lang.String standTypeId){
		this.standTypeId = standTypeId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否耗材
	 */
	@Column(name ="IS_CONS",nullable=true,length=1)
	public java.lang.String getIsCons(){
		return this.isCons;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否耗材
	 */
	public void setIsCons(java.lang.String isCons){
		this.isCons = isCons;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否资产
	 */
	@Column(name ="IS_ASSETS",nullable=true,length=1)
	public java.lang.String getIsAssets(){
		return this.isAssets;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否资产
	 */
	public void setIsAssets(java.lang.String isAssets){
		this.isAssets = isAssets;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否计量
	 */
	@Column(name ="IS_MATER",nullable=true,length=1)
	public java.lang.String getIsMater(){
		return this.isMater;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否计量
	 */
	public void setIsMater(java.lang.String isMater){
		this.isMater = isMater;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  主供应商
	 */
	@Column(name ="SUPPLIER_ID",nullable=false,length=36)
	public java.lang.String getSupplierId(){
		return this.supplierId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  主供应商
	 */
	public void setSupplierId(java.lang.String supplierId){
		this.supplierId = supplierId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  默认仓库
	 */
	@Column(name ="WAREHOUSE_ID",nullable=false,length=36)
	public java.lang.String getWarehouseId(){
		return this.warehouseId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  默认仓库
	 */
	public void setWarehouseId(java.lang.String warehouseId){
		this.warehouseId = warehouseId;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否批次管理
	 */
	@Column(name ="IS_BATCH",nullable=true,length=1)
	public java.lang.String getIsBatch(){
		return this.isBatch;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否批次管理
	 */
	public void setIsBatch(java.lang.String isBatch){
		this.isBatch = isBatch;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否出库跟踪入库
	 */
	@Column(name ="IS_OUT_IN",nullable=true,length=1)
	public java.lang.String getIsOutIn(){
		return this.isOutIn;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否出库跟踪入库
	 */
	public void setIsOutIn(java.lang.String isOutIn){
		this.isOutIn = isOutIn;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否保质期管理
	 */
	@Column(name ="IS_SHELF_LIFE",nullable=true,length=1)
	public java.lang.String getIsShelfLife(){
		return this.isShelfLife;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否保质期管理
	 */
	public void setIsShelfLife(java.lang.String isShelfLife){
		this.isShelfLife = isShelfLife;
	}
	
	
	public java.lang.String getMaxStock() {
		return maxStock;
	}

	public void setMaxStock(java.lang.String maxStock) {
		this.maxStock = maxStock;
	}

	public java.lang.String getSafeStock() {
		return safeStock;
	}

	public void setSafeStock(java.lang.String safeStock) {
		this.safeStock = safeStock;
	}

	public java.lang.String getMinStock() {
		return minStock;
	}

	public void setMinStock(java.lang.String minStock) {
		this.minStock = minStock;
	}

	public java.lang.String getShelfLife() {
		return shelfLife;
	}

	public void setShelfLife(java.lang.String shelfLife) {
		this.shelfLife = shelfLife;
	}

	public java.lang.String getWarningDay() {
		return warningDay;
	}

	public void setWarningDay(java.lang.String warningDay) {
		this.warningDay = warningDay;
	}

	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否高值耗材
	 */
	@Column(name ="IS_HIGH_CONS",nullable=true,length=1)
	public java.lang.String getIsHighCons(){
		return this.isHighCons;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否高值耗材
	 */
	public void setIsHighCons(java.lang.String isHighCons){
		this.isHighCons = isHighCons;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否条形码管理
	 */
	@Column(name ="IS_BAR_CODE",nullable=true,length=1)
	public java.lang.String getIsBarCode(){
		return this.isBarCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否条形码管理
	 */
	public void setIsBarCode(java.lang.String isBarCode){
		this.isBarCode = isBarCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  条形码
	 */
	@Column(name ="BAR_CODE",nullable=false,length=60)
	public java.lang.String getBarCode(){
		return this.barCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  条形码
	 */
	public void setBarCode(java.lang.String barCode){
		this.barCode = barCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  计价方式
	 */
	@Column(name ="VALUE_METHOD",nullable=true,length=2)
	public java.lang.String getValueMethod(){
		return this.valueMethod;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  计价方式
	 */
	public void setValueMethod(java.lang.String valueMethod){
		this.valueMethod = valueMethod;
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
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否代管
	 */
	@Column(name ="IS_INSTEAD",nullable=false,length=2)
	public java.lang.String getIsInstead(){
		return this.isInstead;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否代管
	 */
	public void setIsInstead(java.lang.String isInstead){
		this.isInstead = isInstead;
	}
	@Transient
	public java.lang.String getMaterUnitName() {
		return materUnitName;
	}

	public void setMaterUnitName(java.lang.String materUnitName) {
		this.materUnitName = materUnitName;
	}
	@Transient
	public java.lang.String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(java.lang.String supplierName) {
		this.supplierName = supplierName;
	}
	@Transient
	public java.lang.String getWarehouseName() {
		return warehouseName;
	}

	public void setWarehouseName(java.lang.String warehouseName) {
		this.warehouseName = warehouseName;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  单价
	 */
	
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否质检0否1是
	 */
	@Column(name ="IS_QUALITY",nullable=true,length=2)
	public java.lang.String getIsQuality(){
		return this.isQuality;
	}

	public String getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否质检0否1是
	 */
	public void setIsQuality(java.lang.String isQuality){
		this.isQuality = isQuality;
	}
	@Column(name ="bartype",nullable=true,length=2)
	public java.lang.String getBartype() {
		return bartype;
	}

	public void setBartype(java.lang.String bartype) {
		this.bartype = bartype;
	}
	/**出库单时使用starting***************************************************/
	private java.lang.String batchNo;
	private java.util.Date expirydate;
	@Transient
	public java.lang.String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(java.lang.String batchNo) {
		this.batchNo = batchNo;
	}
	@Transient
	public java.util.Date getExpirydate() {
		return expirydate;
	}

	public void setExpirydate(java.util.Date expirydate) {
		this.expirydate = expirydate;
	}
	/**出库单时使用end***************************************************/
}
