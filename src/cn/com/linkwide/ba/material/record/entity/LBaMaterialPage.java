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
public class LBaMaterialPage implements java.io.Serializable {
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
	public java.lang.String getId() {
		return id;
	}
	public void setId(java.lang.String id) {
		this.id = id;
	}
	public java.lang.String getCreateName() {
		return createName;
	}
	public void setCreateName(java.lang.String createName) {
		this.createName = createName;
	}
	public java.lang.String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(java.lang.String createBy) {
		this.createBy = createBy;
	}
	public java.util.Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}
	public java.lang.String getUpdateName() {
		return updateName;
	}
	public void setUpdateName(java.lang.String updateName) {
		this.updateName = updateName;
	}
	public java.lang.String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(java.lang.String updateBy) {
		this.updateBy = updateBy;
	}
	public java.util.Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(java.util.Date updateDate) {
		this.updateDate = updateDate;
	}
	public java.lang.String getSysOrgCode() {
		return sysOrgCode;
	}
	public void setSysOrgCode(java.lang.String sysOrgCode) {
		this.sysOrgCode = sysOrgCode;
	}
	public java.lang.String getBpmStatus() {
		return bpmStatus;
	}
	public void setBpmStatus(java.lang.String bpmStatus) {
		this.bpmStatus = bpmStatus;
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
	public java.lang.String getMaterialTypeId() {
		return materialTypeId;
	}
	public void setMaterialTypeId(java.lang.String materialTypeId) {
		this.materialTypeId = materialTypeId;
	}
	public java.lang.String getTypeName() {
		return typeName;
	}
	public void setTypeName(java.lang.String typeName) {
		this.typeName = typeName;
	}
	public java.lang.String getSpecModel() {
		return specModel;
	}
	public void setSpecModel(java.lang.String specModel) {
		this.specModel = specModel;
	}
	public java.lang.String getMonmCode() {
		return monmCode;
	}
	public void setMonmCode(java.lang.String monmCode) {
		this.monmCode = monmCode;
	}
	public java.lang.String getMaterUnitId() {
		return materUnitId;
	}
	public void setMaterUnitId(java.lang.String materUnitId) {
		this.materUnitId = materUnitId;
	}
	public java.lang.String getMaterUnitName() {
		return materUnitName;
	}
	public void setMaterUnitName(java.lang.String materUnitName) {
		this.materUnitName = materUnitName;
	}
	public java.lang.String getAppaTypeId() {
		return appaTypeId;
	}
	public void setAppaTypeId(java.lang.String appaTypeId) {
		this.appaTypeId = appaTypeId;
	}
	public java.lang.String getFinanceTypeId() {
		return financeTypeId;
	}
	public void setFinanceTypeId(java.lang.String financeTypeId) {
		this.financeTypeId = financeTypeId;
	}
	public java.lang.String getStandTypeId() {
		return standTypeId;
	}
	public void setStandTypeId(java.lang.String standTypeId) {
		this.standTypeId = standTypeId;
	}
	public java.lang.String getCountryOfOrigin() {
		return countryOfOrigin;
	}
	public void setCountryOfOrigin(java.lang.String countryOfOrigin) {
		this.countryOfOrigin = countryOfOrigin;
	}
	public java.lang.String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(java.lang.String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public java.lang.String getManufacturingAddress() {
		return manufacturingAddress;
	}
	public void setManufacturingAddress(java.lang.String manufacturingAddress) {
		this.manufacturingAddress = manufacturingAddress;
	}
	public java.lang.String getProducingArea() {
		return producingArea;
	}
	public void setProducingArea(java.lang.String producingArea) {
		this.producingArea = producingArea;
	}
	public java.lang.String getBrand() {
		return brand;
	}
	public void setBrand(java.lang.String brand) {
		this.brand = brand;
	}
	public java.lang.String getApprovalNumber() {
		return approvalNumber;
	}
	public void setApprovalNumber(java.lang.String approvalNumber) {
		this.approvalNumber = approvalNumber;
	}
	public java.lang.String getIsSelfrestraint() {
		return isSelfrestraint;
	}
	public void setIsSelfrestraint(java.lang.String isSelfrestraint) {
		this.isSelfrestraint = isSelfrestraint;
	}
	public java.lang.String getIsOutsourcing() {
		return isOutsourcing;
	}
	public void setIsOutsourcing(java.lang.String isOutsourcing) {
		this.isOutsourcing = isOutsourcing;
	}
	public java.lang.String getMaterialSaveMode() {
		return materialSaveMode;
	}
	public void setMaterialSaveMode(java.lang.String materialSaveMode) {
		this.materialSaveMode = materialSaveMode;
	}
	public java.lang.String getIsPaymentDelivery() {
		return isPaymentDelivery;
	}
	public void setIsPaymentDelivery(java.lang.String isPaymentDelivery) {
		this.isPaymentDelivery = isPaymentDelivery;
	}
	public java.lang.Double getApplyExcessToplimit() {
		return applyExcessToplimit;
	}
	public void setApplyExcessToplimit(java.lang.Double applyExcessToplimit) {
		this.applyExcessToplimit = applyExcessToplimit;
	}
	public java.lang.Double getOrderExcessToplimit() {
		return orderExcessToplimit;
	}
	public void setOrderExcessToplimit(java.lang.Double orderExcessToplimit) {
		this.orderExcessToplimit = orderExcessToplimit;
	}
	public java.lang.Double getPurchaseExcessToplimit() {
		return purchaseExcessToplimit;
	}
	public void setPurchaseExcessToplimit(java.lang.Double purchaseExcessToplimit) {
		this.purchaseExcessToplimit = purchaseExcessToplimit;
	}
	public java.lang.Double getEnterExcessToplimit() {
		return enterExcessToplimit;
	}
	public void setEnterExcessToplimit(java.lang.Double enterExcessToplimit) {
		this.enterExcessToplimit = enterExcessToplimit;
	}
	public java.lang.Double getDeliveryExcessToplimit() {
		return deliveryExcessToplimit;
	}
	public void setDeliveryExcessToplimit(java.lang.Double deliveryExcessToplimit) {
		this.deliveryExcessToplimit = deliveryExcessToplimit;
	}
	public java.lang.Integer getCycleTime() {
		return cycleTime;
	}
	public void setCycleTime(java.lang.Integer cycleTime) {
		this.cycleTime = cycleTime;
	}
	public java.lang.Double getReasonableLossRate() {
		return reasonableLossRate;
	}
	public void setReasonableLossRate(java.lang.Double reasonableLossRate) {
		this.reasonableLossRate = reasonableLossRate;
	}
	public java.lang.String getIsSequenceManage() {
		return isSequenceManage;
	}
	public void setIsSequenceManage(java.lang.String isSequenceManage) {
		this.isSequenceManage = isSequenceManage;
	}
	public java.lang.String getIsPmMain() {
		return isPmMain;
	}
	public void setIsPmMain(java.lang.String isPmMain) {
		this.isPmMain = isPmMain;
	}
	public java.lang.String getIsPmFrom() {
		return isPmFrom;
	}
	public void setIsPmFrom(java.lang.String isPmFrom) {
		this.isPmFrom = isPmFrom;
	}
	public java.lang.String getPackingSpec() {
		return packingSpec;
	}
	public void setPackingSpec(java.lang.String packingSpec) {
		this.packingSpec = packingSpec;
	}
	public java.lang.String getRegisteredTrademark() {
		return registeredTrademark;
	}
	public void setRegisteredTrademark(java.lang.String registeredTrademark) {
		this.registeredTrademark = registeredTrademark;
	}
	public java.lang.String getQualifiedCode() {
		return qualifiedCode;
	}
	public void setQualifiedCode(java.lang.String qualifiedCode) {
		this.qualifiedCode = qualifiedCode;
	}
	public java.lang.String getCustomsCode() {
		return customsCode;
	}
	public void setCustomsCode(java.lang.String customsCode) {
		this.customsCode = customsCode;
	}
	public java.lang.String getPermitCode() {
		return permitCode;
	}
	public void setPermitCode(java.lang.String permitCode) {
		this.permitCode = permitCode;
	}
	public java.lang.String getRegisterGoodsBatch() {
		return registerGoodsBatch;
	}
	public void setRegisterGoodsBatch(java.lang.String registerGoodsBatch) {
		this.registerGoodsBatch = registerGoodsBatch;
	}
	public java.lang.String getIntlNonPatentName() {
		return intlNonPatentName;
	}
	public void setIntlNonPatentName(java.lang.String intlNonPatentName) {
		this.intlNonPatentName = intlNonPatentName;
	}
	public java.lang.String getQualityRequirement() {
		return qualityRequirement;
	}
	public void setQualityRequirement(java.lang.String qualityRequirement) {
		this.qualityRequirement = qualityRequirement;
	}
	public java.lang.Double getLongcm() {
		return longcm;
	}
	public void setLongcm(java.lang.Double longcm) {
		this.longcm = longcm;
	}
	public java.lang.Double getWidecm() {
		return widecm;
	}
	public void setWidecm(java.lang.Double widecm) {
		this.widecm = widecm;
	}
	public java.lang.Double getHighcm() {
		return highcm;
	}
	public void setHighcm(java.lang.Double highcm) {
		this.highcm = highcm;
	}
	public java.lang.String getUnitVolume() {
		return unitVolume;
	}
	public void setUnitVolume(java.lang.String unitVolume) {
		this.unitVolume = unitVolume;
	}
	public java.util.Date getStartDate() {
		return startDate;
	}
	public void setStartDate(java.util.Date startDate) {
		this.startDate = startDate;
	}
	public java.util.Date getCeaseDate() {
		return ceaseDate;
	}
	public void setCeaseDate(java.util.Date ceaseDate) {
		this.ceaseDate = ceaseDate;
	}
	public java.lang.Integer getIsServiceParts() {
		return isServiceParts;
	}
	public void setIsServiceParts(java.lang.Integer isServiceParts) {
		this.isServiceParts = isServiceParts;
	}
	public java.lang.Integer getIsLabourService() {
		return isLabourService;
	}
	public void setIsLabourService(java.lang.Integer isLabourService) {
		this.isLabourService = isLabourService;
	}
	public java.lang.Integer getIsAssets() {
		return isAssets;
	}
	public void setIsAssets(java.lang.Integer isAssets) {
		this.isAssets = isAssets;
	}
	public java.lang.Integer getIsInstall() {
		return isInstall;
	}
	public void setIsInstall(java.lang.Integer isInstall) {
		this.isInstall = isInstall;
	}
	public java.lang.Integer getIsBarCode() {
		return isBarCode;
	}
	public void setIsBarCode(java.lang.Integer isBarCode) {
		this.isBarCode = isBarCode;
	}
	public java.lang.String getBarCode() {
		return barCode;
	}
	public void setBarCode(java.lang.String barCode) {
		this.barCode = barCode;
	}
	public java.lang.Integer getIsBatch() {
		return isBatch;
	}
	public void setIsBatch(java.lang.Integer isBatch) {
		this.isBatch = isBatch;
	}
	public java.lang.Integer getIsPurc() {
		return isPurc;
	}
	public void setIsPurc(java.lang.Integer isPurc) {
		this.isPurc = isPurc;
	}
	public java.lang.Integer getIsCons() {
		return isCons;
	}
	public void setIsCons(java.lang.Integer isCons) {
		this.isCons = isCons;
	}
	public java.lang.Integer getIsDurable() {
		return isDurable;
	}
	public void setIsDurable(java.lang.Integer isDurable) {
		this.isDurable = isDurable;
	}
	public java.lang.Integer getIsControl() {
		return isControl;
	}
	public void setIsControl(java.lang.Integer isControl) {
		this.isControl = isControl;
	}
	public java.lang.Integer getIsHighCons() {
		return isHighCons;
	}
	public void setIsHighCons(java.lang.Integer isHighCons) {
		this.isHighCons = isHighCons;
	}
	public java.lang.Integer getIsInstead() {
		return isInstead;
	}
	public void setIsInstead(java.lang.Integer isInstead) {
		this.isInstead = isInstead;
	}
	public java.lang.Integer getIsMater() {
		return isMater;
	}
	public void setIsMater(java.lang.Integer isMater) {
		this.isMater = isMater;
	}
	public java.lang.Integer getIsInstrur() {
		return isInstrur;
	}
	public void setIsInstrur(java.lang.Integer isInstrur) {
		this.isInstrur = isInstrur;
	}
	public java.lang.Integer getIsQuality() {
		return isQuality;
	}
	public void setIsQuality(java.lang.Integer isQuality) {
		this.isQuality = isQuality;
	}
	public java.lang.Integer getIsShelfLife() {
		return isShelfLife;
	}
	public void setIsShelfLife(java.lang.Integer isShelfLife) {
		this.isShelfLife = isShelfLife;
	}
	public java.lang.Integer getShelfLife() {
		return shelfLife;
	}
	public void setShelfLife(java.lang.Integer shelfLife) {
		this.shelfLife = shelfLife;
	}
	public java.lang.String getExpUnit() {
		return expUnit;
	}
	public void setExpUnit(java.lang.String expUnit) {
		this.expUnit = expUnit;
	}
	public java.lang.String getExpReckoningMode() {
		return expReckoningMode;
	}
	public void setExpReckoningMode(java.lang.String expReckoningMode) {
		this.expReckoningMode = expReckoningMode;
	}
	public java.lang.Double getMaxStock() {
		return maxStock;
	}
	public void setMaxStock(java.lang.Double maxStock) {
		this.maxStock = maxStock;
	}
	public java.lang.Double getMinStock() {
		return minStock;
	}
	public void setMinStock(java.lang.Double minStock) {
		this.minStock = minStock;
	}
	public java.lang.Double getSafeStock() {
		return safeStock;
	}
	public void setSafeStock(java.lang.Double safeStock) {
		this.safeStock = safeStock;
	}
	public java.lang.String getWarehouseId() {
		return warehouseId;
	}
	public void setWarehouseId(java.lang.String warehouseId) {
		this.warehouseId = warehouseId;
	}
	public java.lang.String getWarehouseName() {
		return warehouseName;
	}
	public void setWarehouseName(java.lang.String warehouseName) {
		this.warehouseName = warehouseName;
	}
	public java.lang.String getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(java.lang.String supplierId) {
		this.supplierId = supplierId;
	}
	public java.lang.String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(java.lang.String supplierName) {
		this.supplierName = supplierName;
	}
	public java.lang.String getDepartId() {
		return departId;
	}
	public void setDepartId(java.lang.String departId) {
		this.departId = departId;
	}
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
	public java.lang.String getBartype() {
		return bartype;
	}
	public void setBartype(java.lang.String bartype) {
		this.bartype = bartype;
	}
	public java.lang.String getValueMethod() {
		return valueMethod;
	}
	public void setValueMethod(java.lang.String valueMethod) {
		this.valueMethod = valueMethod;
	}
	public java.lang.String getSaveTemperature() {
		return saveTemperature;
	}
	public void setSaveTemperature(java.lang.String saveTemperature) {
		this.saveTemperature = saveTemperature;
	}
	public java.lang.String getBidId() {
		return bidId;
	}
	public void setBidId(java.lang.String bidId) {
		this.bidId = bidId;
	}
	public java.lang.String getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(java.lang.String batchNo) {
		this.batchNo = batchNo;
	}
	public java.util.Date getMakeDate() {
		return makeDate;
	}
	public void setMakeDate(java.util.Date makeDate) {
		this.makeDate = makeDate;
	}
	public java.util.Date getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(java.util.Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	public java.lang.String getMaterialStatus() {
		return materialStatus;
	}
	public void setMaterialStatus(java.lang.String materialStatus) {
		this.materialStatus = materialStatus;
	}
	
	
	
}
