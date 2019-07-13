package cn.com.linkwide.ba.contsuppliermaterial.entity;


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
 * @Description: 供应商物资对照
 * @author onlineGenerator
 * @date 2017-11-16 16:05:12
 * @version V1.0   
 *
 */
@Entity
@Table(name = "l_ba_cont_supplier_material", schema = "")
@SuppressWarnings("serial")
public class LBaContSupplierMaterialEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**供应商ID*/
	//@Excel(name="供应商ID")
	private java.lang.String supplierId;
	@Excel(name="供应商编码")
	private java.lang.String supplierCode;
	private java.lang.String supplierName;
	/**物资ID*/
	//@Excel(name="物资ID")
	private java.lang.String materialId;
	@Excel(name="物资编码")
	private java.lang.String materialCode;
	private java.lang.String materialName;
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
	

	@Transient
	public java.lang.String getSupplierCode() {
		return supplierCode;
	}

	public void setSupplierCode(java.lang.String supplierCode) {
		this.supplierCode = supplierCode;
	}

	@Transient
	public java.lang.String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(java.lang.String supplierName) {
		this.supplierName = supplierName;
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
	 *@return: java.lang.String  供应商ID
	 */
	@Column(name ="SUPPLIER_ID",nullable=false,length=36)
	public java.lang.String getSupplierId(){
		return this.supplierId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  供应商ID
	 */
	public void setSupplierId(java.lang.String supplierId){
		this.supplierId = supplierId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  物资ID
	 */
	@Column(name ="MATERIAL_ID",nullable=false,length=36)
	public java.lang.String getMaterialId(){
		return this.materialId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  物资ID
	 */
	public void setMaterialId(java.lang.String materialId){
		this.materialId = materialId;
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
	
}
