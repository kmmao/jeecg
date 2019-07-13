package cn.com.linkwide.ba.material.record.entity;

import java.math.BigDecimal;

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
 * @Description: 物资档案
 * @author onlineGenerator
 * @date 2018-07-04 17:42:26
 * @version V1.0   
 *
 */
@Entity
@Table(name = "l_ba_material", schema = "")
@SuppressWarnings("serial")
public class LBaMaterialEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**所属公司*/
	private java.lang.String sysCompanyCode;
	@Column(name ="SYS_COMPANY_CODE",nullable=true,length=50)
	public java.lang.String getSysCompanyCode(){
		return this.sysCompanyCode;
	}
	public void setSysCompanyCode(java.lang.String sysCompanyCode){
		this.sysCompanyCode = sysCompanyCode;
	}
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
	
	/**流程状态*/
	private java.lang.String bpmStatus;
	/**物资编码*/
	@Excel(name="物资编码",width=15)
	private java.lang.String materialCode;
	/**物资名称*/
	@Excel(name="物资名称",width=15)
	private java.lang.String materialName;
	/**物资分类*/
	@Excel(name="物资分类",width=15)
	private java.lang.String materialTypeId;
	private java.lang.String typeName;
	/**规格型号*/
	@Excel(name="规格型号",width=15)
	private java.lang.String specModel;
	/**助记码*/
	@Excel(name="助记码",width=15)
	private java.lang.String monmCode;
	/**计量单位*/
	@Excel(name="计量单位",width=15,dictTable="l_ba_material_mater_unit",dicCode="id",dicText="type_name")
	private java.lang.String materUnitId;
	private java.lang.String materUnitName;
	/**器械分类*/
	@Excel(name="器械分类",width=15)
	private java.lang.String appaTypeId;
	/**财务分类*/
	@Excel(name="财务分类",width=15)
	private java.lang.String financeTypeId;
	/**国标分类*/
	@Excel(name="国标分类",width=15)
	private java.lang.String standTypeId;
	/**生产国别*/
	@Excel(name="生产国别",width=15)
	private java.lang.String countryOfOrigin;
	/**生产企业*/
	@Excel(name="生产企业",width=15)
	private java.lang.String manufacturer;
	/**生产地点*/
	@Excel(name="生产地点",width=15)
	private java.lang.String manufacturingAddress;
	/**产地*/
	@Excel(name="产地",width=15)
	private java.lang.String producingArea;
	/**品牌*/
	@Excel(name="品牌",width=15)
	private java.lang.String brand;
	/**批准文号*/
	@Excel(name="批准文号",width=15)
	private java.lang.String approvalNumber;
	/**是否自制*/
	@Excel(name="是否自制",width=15)
	private java.lang.String isSelfrestraint;
	/**是否委外*/
	@Excel(name="是否委外",width=15)
	private java.lang.String isOutsourcing;
	/**物资保存方式*/
	@Excel(name="物资保存方式",width=15)
	private java.lang.String materialSaveMode;
	/**是否先收费后出库*/
	@Excel(name="是否先收费后出库",width=15)
	private java.lang.String isPaymentDelivery;
	/**请购超额上限*/
	@Excel(name="请购超额上限",width=15)
	private java.lang.Double applyExcessToplimit;
	/**订货超额上限*/
	@Excel(name="订货超额上限",width=15)
	private java.lang.Double orderExcessToplimit;
	/**采购数量上限*/
	@Excel(name="采购数量上限",width=15)
	private java.lang.Double purchaseExcessToplimit;
	/**入库超额上限*/
	@Excel(name="入库超额上限",width=15)
	private java.lang.Double enterExcessToplimit;
	/**出库超额上限*/
	@Excel(name="出库超额上限",width=15)
	private java.lang.Double deliveryExcessToplimit;
	/**盘点周期*/
	@Excel(name="盘点周期",width=15)
	private java.lang.Integer cycleTime;
	/**合理损耗率*/
	@Excel(name="合理损耗率",width=15)
	private java.lang.Double reasonableLossRate;
	/**是否序列号管理*/
	@Excel(name="是否序列号管理",width=15)
	private java.lang.String isSequenceManage;
	/**是否允许产品结构母件*/
	@Excel(name="是否允许产品结构母件",width=15)
	private java.lang.String isPmMain;
	/**是否允许产品结构子件*/
	@Excel(name="是否允许产品结构子件",width=15)
	private java.lang.String isPmFrom;
	/**包装规格*/
	@Excel(name="包装规格",width=15)
	private java.lang.String packingSpec;
	/**注册商标*/
	@Excel(name="注册商标",width=15)
	private java.lang.String registeredTrademark;
	/**合格证号*/
	@Excel(name="合格证号",width=15)
	private java.lang.String qualifiedCode;
	/**入关证号*/
	@Excel(name="入关证号",width=15)
	private java.lang.String customsCode;
	/**许可证号*/
	@Excel(name="许可证号",width=15)
	private java.lang.String permitCode;
	/**注册商品批件*/
	@Excel(name="注册商品批件",width=15)
	private java.lang.String registerGoodsBatch;
	/**国际非专利名*/
	@Excel(name="国际非专利名",width=15)
	private java.lang.String intlNonPatentName;
	/**质量要求*/
	@Excel(name="质量要求",width=15)
	private java.lang.String qualityRequirement;
	/**长度*/
	@Excel(name="长度",width=15)
	private java.lang.Double longcm;
	/**宽度*/
	@Excel(name="宽度",width=15)
	private java.lang.Double widecm;
	/**高度*/
	@Excel(name="高度",width=15)
	private java.lang.Double highcm;
	/**单位体积*/
	@Excel(name="单位体积",width=15)
	private java.lang.String unitVolume;
	/**启用日期*/
	@Excel(name="启用日期",width=15,format = "yyyy-MM-dd")
	private java.util.Date startDate;
	/**停用日期*/
	@Excel(name="停用日期",width=15,format = "yyyy-MM-dd")
	private java.util.Date ceaseDate;
	/**是否服务配件*/
	@Excel(name="是否服务配件",width=15)
	private java.lang.Integer isServiceParts;
	/**是否劳务*/
	@Excel(name="是否劳务",width=15)
	private java.lang.Integer isLabourService;
	/**是否资产*/
	@Excel(name="是否资产",width=15)
	private java.lang.Integer isAssets;
	@Excel(name="是否安装",width=15)
	private java.lang.Integer isInstall; 
	/**是否条形码管理*/
	@Excel(name="是否条形码管理",width=15)
	private java.lang.Integer isBarCode;
	/**对应条形码*/
	@Excel(name="对应条形码",width=15)
	private java.lang.String barCode;
	/**是否批次管理*/
	@Excel(name="是否批次管理",width=15)
	private java.lang.Integer isBatch;
	/**是否采购*/
	@Excel(name="是否采购",width=15)
	private java.lang.Integer isPurc;
	/**是否耗材*/
	@Excel(name="是否耗材",width=15)
	private java.lang.Integer isCons;
	/**是否耐用品*/
	@Excel(name="是否耐用品",width=15)
	private java.lang.Integer isDurable;
	/**是否控制资质*/
	@Excel(name="是否控制资质",width=15)
	private java.lang.Integer isControl;
	/**是否高值耗材*/
	@Excel(name="是否高值耗材",width=15)
	private java.lang.Integer isHighCons;
	/**是否代管*/
	@Excel(name="是否代管",width=15)
	private java.lang.Integer isInstead;
	/**是否计量*/
	@Excel(name="是否计量",width=15)
	private java.lang.Integer isMater;
	/**是否器械*/
	@Excel(name="是否器械",width=15)
	private java.lang.Integer isInstrur;
	/**是否质检*/
	@Excel(name="是否质检",width=15)
	private java.lang.Integer isQuality;
	/**是否保质期管理*/
	@Excel(name="是否保质期管理",width=15)
	private java.lang.Integer isShelfLife;
	/**保质期*/
	@Excel(name="保质期",width=15)
	private java.lang.Integer shelfLife;
	/**保质期单位*/
	@Excel(name="保质期单位",width=15)
	private java.lang.String expUnit;
	/**有效期推算方式*/
	@Excel(name="有效期推算方式",width=15)
	private java.lang.String expReckoningMode;
	/**最高库存*/
	@Excel(name="最高库存",width=15)
	private java.lang.Double maxStock;
	/**最低库存*/
	@Excel(name="最低库存",width=15)
	private java.lang.Double minStock;
	/**安全库存*/
	@Excel(name="安全库存",width=15)
	private java.lang.Double safeStock;
	/**默认仓库*/
	@Excel(name="默认仓库",width=15)
	private java.lang.String warehouseId;
	private java.lang.String warehouseName;
	/**主供应商*/
	@Excel(name="主供应商",width=15,dictTable="l_ba_supplier",dicCode="id",dicText="supplier_full_name")
	private java.lang.String supplierId;
	private java.lang.String supplierName;
	/**创建部门*/
//	@Excel(name="创建部门",width=15)
	private java.lang.String departId; 
	/**单价*/
	@Excel(name="预估单价")
	private BigDecimal unitPrice;
	/**条码类型*/
	@Excel(name="条码类型")
	private java.lang.String bartype;
	
	/**计价方式*/
	@Excel(name="计价方式")
	private java.lang.String valueMethod;
	
	/**保存温度*/
	@Excel(name="保存温度")
	private java.lang.String saveTemperature;
	
	/**中标单id*/
	@Excel(name="中标单id")
	private java.lang.String bidId;
	
	/**批号*/
	private java.lang.String batchNo;
	/**生产日期*/
	private java.util.Date makeDate;
	/**失效日期*/
	private java.util.Date expiryDate;
	/**物资是否维护*/
	private java.lang.String materialStatus;
	
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
	 *@return: java.lang.String  物资编码
	 */

	@Column(name ="MATERIAL_CODE",nullable=true,length=32)
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

	@Column(name ="MATERIAL_NAME",nullable=true,length=32)
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

	@Column(name ="MATERIAL_TYPE_ID",nullable=true,length=32)
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

	@Column(name ="SPEC_MODEL",nullable=true,length=32)
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

	@Column(name ="MONM_CODE",nullable=true,length=32)
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

	@Column(name ="MATER_UNIT_ID",nullable=true,length=32)
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

	@Column(name ="APPA_TYPE_ID",nullable=true,length=32)
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

	@Column(name ="FINANCE_TYPE_ID",nullable=true,length=32)
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

	@Column(name ="STAND_TYPE_ID",nullable=true,length=32)
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
	 *@return: java.lang.String  生产国别
	 */

	@Column(name ="COUNTRY_OF_ORIGIN",nullable=true,length=32)
	public java.lang.String getCountryOfOrigin(){
		return this.countryOfOrigin;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  生产国别
	 */
	public void setCountryOfOrigin(java.lang.String countryOfOrigin){
		this.countryOfOrigin = countryOfOrigin;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  生产企业
	 */

	@Column(name ="MANUFACTURER",nullable=true,length=32)
	public java.lang.String getManufacturer(){
		return this.manufacturer;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  生产企业
	 */
	public void setManufacturer(java.lang.String manufacturer){
		this.manufacturer = manufacturer;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  生产地点
	 */

	@Column(name ="MANUFACTURING_ADDRESS",nullable=true,length=32)
	public java.lang.String getManufacturingAddress(){
		return this.manufacturingAddress;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  生产地点
	 */
	public void setManufacturingAddress(java.lang.String manufacturingAddress){
		this.manufacturingAddress = manufacturingAddress;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  产地
	 */

	@Column(name ="PRODUCING_AREA",nullable=true,length=32)
	public java.lang.String getProducingArea(){
		return this.producingArea;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  产地
	 */
	public void setProducingArea(java.lang.String producingArea){
		this.producingArea = producingArea;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  品牌
	 */

	@Column(name ="BRAND",nullable=true,length=32)
	public java.lang.String getBrand(){
		return this.brand;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  品牌
	 */
	public void setBrand(java.lang.String brand){
		this.brand = brand;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  批准文号
	 */

	@Column(name ="APPROVAL_NUMBER",nullable=true,length=32)
	public java.lang.String getApprovalNumber(){
		return this.approvalNumber;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  批准文号
	 */
	public void setApprovalNumber(java.lang.String approvalNumber){
		this.approvalNumber = approvalNumber;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否自制
	 */

	@Column(name ="IS_SELFRESTRAINT",nullable=true,length=32)
	public java.lang.String getIsSelfrestraint(){
		return this.isSelfrestraint;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否自制
	 */
	public void setIsSelfrestraint(java.lang.String isSelfrestraint){
		this.isSelfrestraint = isSelfrestraint;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否委外
	 */

	@Column(name ="IS_OUTSOURCING",nullable=true,length=32)
	public java.lang.String getIsOutsourcing(){
		return this.isOutsourcing;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否委外
	 */
	public void setIsOutsourcing(java.lang.String isOutsourcing){
		this.isOutsourcing = isOutsourcing;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  物资保存方式
	 */

	@Column(name ="MATERIAL_SAVE_MODE",nullable=true,length=32)
	public java.lang.String getMaterialSaveMode(){
		return this.materialSaveMode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  物资保存方式
	 */
	public void setMaterialSaveMode(java.lang.String materialSaveMode){
		this.materialSaveMode = materialSaveMode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否先收费后出库
	 */

	@Column(name ="IS_PAYMENT_DELIVERY",nullable=true,length=1)
	public java.lang.String getIsPaymentDelivery(){
		return this.isPaymentDelivery;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否先收费后出库
	 */
	public void setIsPaymentDelivery(java.lang.String isPaymentDelivery){
		this.isPaymentDelivery = isPaymentDelivery;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  请购超额上限
	 */

	@Column(name ="APPLY_EXCESS_TOPLIMIT",nullable=true,length=32)
	public java.lang.Double getApplyExcessToplimit(){
		return this.applyExcessToplimit;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  请购超额上限
	 */
	public void setApplyExcessToplimit(java.lang.Double applyExcessToplimit){
		this.applyExcessToplimit = applyExcessToplimit;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  订货超额上限
	 */

	@Column(name ="ORDER_EXCESS_TOPLIMIT",nullable=true,length=32)
	public java.lang.Double getOrderExcessToplimit(){
		return this.orderExcessToplimit;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  订货超额上限
	 */
	public void setOrderExcessToplimit(java.lang.Double orderExcessToplimit){
		this.orderExcessToplimit = orderExcessToplimit;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  采购数量上限
	 */

	@Column(name ="PURCHASE_EXCESS_TOPLIMIT",nullable=true,length=32)
	public java.lang.Double getPurchaseExcessToplimit(){
		return this.purchaseExcessToplimit;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  采购数量上限
	 */
	public void setPurchaseExcessToplimit(java.lang.Double purchaseExcessToplimit){
		this.purchaseExcessToplimit = purchaseExcessToplimit;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  入库超额上限
	 */

	@Column(name ="ENTER_EXCESS_TOPLIMIT",nullable=true,length=32)
	public java.lang.Double getEnterExcessToplimit(){
		return this.enterExcessToplimit;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  入库超额上限
	 */
	public void setEnterExcessToplimit(java.lang.Double enterExcessToplimit){
		this.enterExcessToplimit = enterExcessToplimit;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  出库超额上限
	 */

	@Column(name ="DELIVERY_EXCESS_TOPLIMIT",nullable=true,length=32)
	public java.lang.Double getDeliveryExcessToplimit(){
		return this.deliveryExcessToplimit;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  出库超额上限
	 */
	public void setDeliveryExcessToplimit(java.lang.Double deliveryExcessToplimit){
		this.deliveryExcessToplimit = deliveryExcessToplimit;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  盘点周期
	 */

	@Column(name ="CYCLE_TIME",nullable=true,length=32)
	public java.lang.Integer getCycleTime(){
		return this.cycleTime;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  盘点周期
	 */
	public void setCycleTime(java.lang.Integer cycleTime){
		this.cycleTime = cycleTime;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  合理损耗率
	 */

	@Column(name ="REASONABLE_LOSS_RATE",nullable=true,length=32)
	public java.lang.Double getReasonableLossRate(){
		return this.reasonableLossRate;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  合理损耗率
	 */
	public void setReasonableLossRate(java.lang.Double reasonableLossRate){
		this.reasonableLossRate = reasonableLossRate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否序列号管理
	 */

	@Column(name ="IS_SEQUENCE_MANAGE",nullable=true,length=2)
	public java.lang.String getIsSequenceManage(){
		return this.isSequenceManage;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否序列号管理
	 */
	public void setIsSequenceManage(java.lang.String isSequenceManage){
		this.isSequenceManage = isSequenceManage;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否允许产品结构母件
	 */

	@Column(name ="IS_PM_MAIN",nullable=true,length=2)
	public java.lang.String getIsPmMain(){
		return this.isPmMain;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否允许产品结构母件
	 */
	public void setIsPmMain(java.lang.String isPmMain){
		this.isPmMain = isPmMain;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否允许产品结构子件
	 */

	@Column(name ="IS_PM_FROM",nullable=true,length=2)
	public java.lang.String getIsPmFrom(){
		return this.isPmFrom;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否允许产品结构子件
	 */
	public void setIsPmFrom(java.lang.String isPmFrom){
		this.isPmFrom = isPmFrom;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  包装规格
	 */

	@Column(name ="PACKING_SPEC",nullable=true,length=32)
	public java.lang.String getPackingSpec(){
		return this.packingSpec;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  包装规格
	 */
	public void setPackingSpec(java.lang.String packingSpec){
		this.packingSpec = packingSpec;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  注册商标
	 */

	@Column(name ="REGISTERED_TRADEMARK",nullable=true,length=32)
	public java.lang.String getRegisteredTrademark(){
		return this.registeredTrademark;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  注册商标
	 */
	public void setRegisteredTrademark(java.lang.String registeredTrademark){
		this.registeredTrademark = registeredTrademark;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  合格证号
	 */

	@Column(name ="QUALIFIED_CODE",nullable=true,length=32)
	public java.lang.String getQualifiedCode(){
		return this.qualifiedCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  合格证号
	 */
	public void setQualifiedCode(java.lang.String qualifiedCode){
		this.qualifiedCode = qualifiedCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  入关证号
	 */

	@Column(name ="CUSTOMS_CODE",nullable=true,length=32)
	public java.lang.String getCustomsCode(){
		return this.customsCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  入关证号
	 */
	public void setCustomsCode(java.lang.String customsCode){
		this.customsCode = customsCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  许可证号
	 */

	@Column(name ="PERMIT_CODE",nullable=true,length=32)
	public java.lang.String getPermitCode(){
		return this.permitCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  许可证号
	 */
	public void setPermitCode(java.lang.String permitCode){
		this.permitCode = permitCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  注册商品批件
	 */

	@Column(name ="REGISTER_GOODS_BATCH",nullable=true,length=32)
	public java.lang.String getRegisterGoodsBatch(){
		return this.registerGoodsBatch;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  注册商品批件
	 */
	public void setRegisterGoodsBatch(java.lang.String registerGoodsBatch){
		this.registerGoodsBatch = registerGoodsBatch;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  国际非专利名
	 */

	@Column(name ="INTL_NON_PATENT_NAME",nullable=true,length=32)
	public java.lang.String getIntlNonPatentName(){
		return this.intlNonPatentName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  国际非专利名
	 */
	public void setIntlNonPatentName(java.lang.String intlNonPatentName){
		this.intlNonPatentName = intlNonPatentName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  质量要求
	 */

	@Column(name ="QUALITY_REQUIREMENT",nullable=true,length=32)
	public java.lang.String getQualityRequirement(){
		return this.qualityRequirement;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  质量要求
	 */
	public void setQualityRequirement(java.lang.String qualityRequirement){
		this.qualityRequirement = qualityRequirement;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  长度
	 */

	@Column(name ="LONGCM",nullable=true,length=32)
	public java.lang.Double getLongcm(){
		return this.longcm;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  长度
	 */
	public void setLongcm(java.lang.Double longcm){
		this.longcm = longcm;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  宽度
	 */

	@Column(name ="WIDECM",nullable=true,length=32)
	public java.lang.Double getWidecm(){
		return this.widecm;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  宽度
	 */
	public void setWidecm(java.lang.Double widecm){
		this.widecm = widecm;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  高度
	 */

	@Column(name ="HIGHCM",nullable=true,length=32)
	public java.lang.Double getHighcm(){
		return this.highcm;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  高度
	 */
	public void setHighcm(java.lang.Double highcm){
		this.highcm = highcm;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  单位体积
	 */

	@Column(name ="UNIT_VOLUME",nullable=true,length=32)
	public java.lang.String getUnitVolume(){
		return this.unitVolume;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  单位体积
	 */
	public void setUnitVolume(java.lang.String unitVolume){
		this.unitVolume = unitVolume;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  启用日期
	 */

	@Column(name ="START_DATE",nullable=true,length=32)
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

	@Column(name ="CEASE_DATE",nullable=true,length=32)
	public java.util.Date getCeaseDate(){
		return this.ceaseDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  停用日期
	 */
	public void setCeaseDate(java.util.Date ceaseDate){
		this.ceaseDate = ceaseDate;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  是否服务配件
	 */

	@Column(name ="IS_SERVICE_PARTS",nullable=true,length=32)
	public java.lang.Integer getIsServiceParts(){
		return this.isServiceParts;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  是否服务配件
	 */
	public void setIsServiceParts(java.lang.Integer isServiceParts){
		this.isServiceParts = isServiceParts;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  是否劳务
	 */

	@Column(name ="IS_LABOUR_SERVICE",nullable=true,length=32)
	public java.lang.Integer getIsLabourService(){
		return this.isLabourService;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  是否劳务
	 */
	public void setIsLabourService(java.lang.Integer isLabourService){
		this.isLabourService = isLabourService;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  是否资产
	 */

	@Column(name ="IS_ASSETS",nullable=true,length=32)
	public java.lang.Integer getIsAssets(){
		return this.isAssets;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  是否资产
	 */
	public void setIsAssets(java.lang.Integer isAssets){
		this.isAssets = isAssets;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  是否条形码管理
	 */

	@Column(name ="IS_BAR_CODE",nullable=true,length=32)
	public java.lang.Integer getIsBarCode(){
		return this.isBarCode;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  是否条形码管理
	 */
	public void setIsBarCode(java.lang.Integer isBarCode){
		this.isBarCode = isBarCode;
	}
	/**
	 

	 
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  是否耗材
	 */

	@Column(name ="IS_CONS",nullable=true,length=32)
	public java.lang.Integer getIsCons(){
		return this.isCons;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  是否耗材
	 */
	public void setIsCons(java.lang.Integer isCons){
		this.isCons = isCons;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  是否控制资质
	 */

	@Column(name ="IS_CONTROL",nullable=true,length=32)
	public java.lang.Integer getIsControl(){
		return this.isControl;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  是否控制资质
	 */
	public void setIsControl(java.lang.Integer isControl){
		this.isControl = isControl;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  是否高值耗材
	 */

	@Column(name ="IS_HIGH_CONS",nullable=true,length=32)
	public java.lang.Integer getIsHighCons(){
		return this.isHighCons;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  是否高值耗材
	 */
	public void setIsHighCons(java.lang.Integer isHighCons){
		this.isHighCons = isHighCons;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  是否代管
	 */

	@Column(name ="IS_INSTEAD",nullable=true,length=32)
	public java.lang.Integer getIsInstead(){
		return this.isInstead;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  是否代管
	 */
	public void setIsInstead(java.lang.Integer isInstead){
		this.isInstead = isInstead;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  是否计量
	 */

	@Column(name ="IS_MATER",nullable=true,length=32)
	public java.lang.Integer getIsMater(){
		return this.isMater;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  是否计量
	 */
	public void setIsMater(java.lang.Integer isMater){
		this.isMater = isMater;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  是否质检
	 */

	@Column(name ="IS_QUALITY",nullable=true,length=32)
	public java.lang.Integer getIsQuality(){
		return this.isQuality;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  是否质检
	 */
	public void setIsQuality(java.lang.Integer isQuality){
		this.isQuality = isQuality;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  是否保质期管理
	 */

	@Column(name ="IS_SHELF_LIFE",nullable=true,length=32)
	public java.lang.Integer getIsShelfLife(){
		return this.isShelfLife;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  是否保质期管理
	 */
	public void setIsShelfLife(java.lang.Integer isShelfLife){
		this.isShelfLife = isShelfLife;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  保质期
	 */

	@Column(name ="SHELF_LIFE",nullable=true,length=32)
	public java.lang.Integer getShelfLife(){
		return this.shelfLife;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  保质期
	 */
	public void setShelfLife(java.lang.Integer shelfLife){
		this.shelfLife = shelfLife;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  保质期单位
	 */

	@Column(name ="EXP_UNIT",nullable=true,length=4)
	public java.lang.String getExpUnit(){
		return this.expUnit;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  保质期单位
	 */
	public void setExpUnit(java.lang.String expUnit){
		this.expUnit = expUnit;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  有效期推算方式
	 */

	@Column(name ="EXP_RECKONING_MODE",nullable=true,length=32)
	public java.lang.String getExpReckoningMode(){
		return this.expReckoningMode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  有效期推算方式
	 */
	public void setExpReckoningMode(java.lang.String expReckoningMode){
		this.expReckoningMode = expReckoningMode;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  最高库存
	 */

	@Column(name ="MAX_STOCK",nullable=true,length=32)
	public java.lang.Double getMaxStock(){
		return this.maxStock;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  最高库存
	 */
	public void setMaxStock(java.lang.Double maxStock){
		this.maxStock = maxStock;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  最低库存
	 */

	@Column(name ="MIN_STOCK",nullable=true,length=32)
	public java.lang.Double getMinStock(){
		return this.minStock;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  最低库存
	 */
	public void setMinStock(java.lang.Double minStock){
		this.minStock = minStock;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  安全库存
	 */

	@Column(name ="SAFE_STOCK",nullable=true,length=32)
	public java.lang.Double getSafeStock(){
		return this.safeStock;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  安全库存
	 */
	public void setSafeStock(java.lang.Double safeStock){
		this.safeStock = safeStock;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  默认仓库
	 */

	@Column(name ="WAREHOUSE_ID",nullable=true,length=32)
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
	 *@return: java.lang.String  主供应商
	 */

	@Column(name ="SUPPLIER_ID",nullable=true,length=32)
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
	 *@return: java.lang.String  创建部门
	 */

	@Column(name ="DEPART_ID",nullable=true,length=32)
	public java.lang.String getDepartId(){
		return this.departId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建部门
	 */
	public void setDepartId(java.lang.String departId){
		this.departId = departId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  条形码
	 */

	@Column(name ="BAR_CODE",nullable=true,length=32)
	public java.lang.String getBarCode(){
		return this.barCode;
	}
	@Column(name ="IS_DURABLE",nullable=true,length=32)
	public java.lang.Integer getIsDurable() {
		return isDurable;
	}
	public void setIsDurable(java.lang.Integer isDurable) {
		this.isDurable = isDurable;
	}
	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  条形码
	 */
	public void setBarCode(java.lang.String barCode){
		this.barCode = barCode;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  是否批次管理
	 */

	@Column(name ="IS_BATCH",nullable=true,length=32)
	public java.lang.Integer getIsBatch(){
		return this.isBatch;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  是否批次管理
	 */
	public void setIsBatch(java.lang.Integer isBatch){
		this.isBatch = isBatch;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  单价
	 */
	@Column(name ="UNIT_PRICE",nullable=true,scale=2,length=20)
	public BigDecimal getUnitPrice(){
		return this.unitPrice;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  单价
	 */
	public void setUnitPrice(BigDecimal unitPrice){
		this.unitPrice = unitPrice;
	}
	
	@Column(name ="BARTYPE",nullable=false,length=36)
	public java.lang.String getBartype() {
		return bartype;
	}

	public void setBartype(java.lang.String bartype) {
		this.bartype = bartype;
	}
	
	@Column(name ="VALUE_METHOD",nullable=false,length=36)
	public java.lang.String getValueMethod() {
		return valueMethod;
	}

	public void setValueMethod(java.lang.String valueMethod) {
		this.valueMethod = valueMethod;
	}
	@Column(name ="IS_PURC",nullable=false,length=36)
	public java.lang.Integer getIsPurc() {
		return isPurc;
	}

	public void setIsPurc(java.lang.Integer isPurc) {
		this.isPurc = isPurc;
	}
	@Column(name ="IS_INSTALL",nullable=false,length=10)
	public java.lang.Integer getIsInstall() {
		return isInstall;
	}

	public void setIsInstall(java.lang.Integer isInstall) {
		this.isInstall = isInstall;
	}
	@Column(name ="IS_INSTRUR",nullable=false,length=10)
	public java.lang.Integer getIsInstrur() {
		return isInstrur;
	}
	public void setIsInstrur(java.lang.Integer isInstrur) {
		this.isInstrur = isInstrur;
	}
 	@Transient
	public java.lang.String getTypeName() {
		return typeName;
	}

	public void setTypeName(java.lang.String typeName) {
		this.typeName = typeName;
	}
	@Transient
	public java.lang.String getWarehouseName() {
		return warehouseName;
	}

	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
		
	}
	@Transient
	public java.lang.String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(java.lang.String supplierName) {
		this.supplierName = supplierName;
	}
	

	@Column(name ="SAVE_TEMPERATURE",nullable=false,length=10)
	public java.lang.String getSaveTemperature() {
		return saveTemperature;
	}
	public void setSaveTemperature(java.lang.String saveTemperature) {
		this.saveTemperature = saveTemperature;
	}
	
	@Column(name ="BID_ID",nullable=true,length=50)
	public java.lang.String getBidId() {
		return bidId;
	}
	public void setBidId(java.lang.String bidId) {
		this.bidId = bidId;
	}
	
	@Transient
	public java.lang.String getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(java.lang.String batchNo) {
		this.batchNo = batchNo;
	}
	@Transient
	public java.util.Date getMakeDate() {
		return makeDate;
	}
	public void setMakeDate(java.util.Date makeDate) {
		this.makeDate = makeDate;
	}
	@Transient
	public java.util.Date getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(java.util.Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	@Column(name ="MATERIAL_STATUS",nullable=true,length=50)
	public java.lang.String getMaterialStatus() {
		return materialStatus;
	}
	public void setMaterialStatus(java.lang.String materialStatus) {
		this.materialStatus = materialStatus;
	}
	@Transient
	public java.lang.String getMaterUnitName() {
		return materUnitName;
	}
	public void setMaterUnitName(java.lang.String materUnitName) {
		this.materUnitName = materUnitName;
	}
	
}
