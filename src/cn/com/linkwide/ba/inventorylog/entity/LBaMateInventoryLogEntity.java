package cn.com.linkwide.ba.inventorylog.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**   
 * @Title: Entity
 * @Description: 库存管理日志
 * @author onlineGenerator
 * @date 2018-08-20 10:23:45
 * @version V1.0   
 *
 */
@Entity
@Table(name = "l_ba_mate_inventory_log", schema = "")
@SuppressWarnings("serial")
public class LBaMateInventoryLogEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**创建日期*/
	private java.util.Date createDate;
	/**库存表id*/
	private java.lang.String inventoryId;
	/**仓库*/
	private java.lang.String warehouseId;
	/**是否代管*/
	private java.lang.String isAgency;
	/**物资*/
	private java.lang.String materialId;
	/**操作时间*/
	private java.util.Date logDate;
	/**变更后库存*/
	private java.math.BigDecimal nowNum;
	/**变更数量*/
	private java.math.BigDecimal num;
	/**单据号*/
	private java.lang.String billNo;
	/**单据类型*/
	private java.lang.String billType;
	/**操作人*/
	private java.lang.String createBy;
	
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
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  创建日期
	 */

	@Column(name ="CREATE_DATE",nullable=true)
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
	 *@return: java.lang.String  库存表id
	 */

	@Column(name ="INVENTORY_ID",nullable=true,length=32)
	public java.lang.String getInventoryId(){
		return this.inventoryId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  库存表id
	 */
	public void setInventoryId(java.lang.String inventoryId){
		this.inventoryId = inventoryId;
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
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  操作时间
	 */

	@Column(name ="LOG_DATE",nullable=true,length=32)
	public java.util.Date getLogDate(){
		return this.logDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  操作时间
	 */
	public void setLogDate(java.util.Date logDate){
		this.logDate = logDate;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  当前库存
	 */

	@Column(name ="NOW_NUM",nullable=true,length=32)
	public java.math.BigDecimal getNowNum(){
		return this.nowNum;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  当前库存
	 */
	public void setNowNum(java.math.BigDecimal nowNum){
		this.nowNum = nowNum;
	}
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  变更数量
	 */

	@Column(name ="NUM",nullable=true,length=32)
	public java.math.BigDecimal getNum(){
		return this.num;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  变更数量
	 */
	public void setNum(java.math.BigDecimal num){
		this.num = num;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  单据号
	 */

	@Column(name ="BILL_NO",nullable=true,length=32)
	public java.lang.String getBillNo(){
		return this.billNo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  单据号
	 */
	public void setBillNo(java.lang.String billNo){
		this.billNo = billNo;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  单据类型
	 */

	@Column(name ="BILL_TYPE",nullable=true,length=32)
	public java.lang.String getBillType(){
		return this.billType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  单据类型
	 */
	public void setBillType(java.lang.String billType){
		this.billType = billType;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  操作人
	 */

	@Column(name ="CREATE_BY",nullable=true,length=32)
	public java.lang.String getCreateBy(){
		return this.createBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  操作人
	 */
	public void setCreateBy(java.lang.String createBy){
		this.createBy = createBy;
	}

	@Column(name ="IS_AGENCY",nullable=true,length=32)
	public java.lang.String getIsAgency() {
		return isAgency;
	}

	public void setIsAgency(java.lang.String isAgency) {
		this.isAgency = isAgency;
	}
	
}
