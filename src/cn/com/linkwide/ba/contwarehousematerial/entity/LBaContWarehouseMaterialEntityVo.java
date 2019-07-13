package cn.com.linkwide.ba.contwarehousematerial.entity;

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
 * @Description: 仓库物资对照
 * @author onlineGenerator
 * @date 2017-11-21 15:14:05
 * @version V1.0   
 *
 */
@Entity
@Table(name = "l_ba_cont_warehouse_material", schema = "")
@SuppressWarnings("serial")
public class LBaContWarehouseMaterialEntityVo implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**仓库ID*/
	//@Excel(name="仓库ID")
	private java.lang.String warehouseId;
	@Excel(name="仓库编码")
	private java.lang.String warehouseCode;
	private java.lang.String warehouseName;
	/**物资ID*/
	//@Excel(name="物资ID")
	private java.lang.String materialId;
	private java.lang.String materialName;
	@Excel(name="物资编码")
	private java.lang.String materialCode;
	
	@Excel(name="错误信息")
	private String errMsg;
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
	
	
	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	/**扩展字段1*/
	//@Excel(name="扩展字段1")
	private java.lang.String extend1;
	/**扩展字段2*/
	//@Excel(name="扩展字段2")
	private java.lang.String extend2;
	/**扩展字段3*/
	//@Excel(name="扩展字段3")
	private java.lang.String extend3;
	/**扩展字段4*/
	//@Excel(name="扩展字段4")
	private java.lang.String extend4;
	/**扩展字段5*/
	//@Excel(name="扩展字段5")
	private java.lang.String extend5;
	/**扩展字段6*/
	//@Excel(name="扩展字段6")
	private java.lang.String extend6;
	/**扩展字段7*/
	//@Excel(name="扩展字段7")
	private java.lang.String extend7;
	/**扩展字段8*/
	//@Excel(name="扩展字段8")
	private java.lang.String extend8;
	/**扩展字段9*/
	//@Excel(name="扩展字段9")
	private java.lang.String extend9;
	/**扩展字段10*/
	//@Excel(name="扩展字段10")
	private java.lang.String extend10;
	/**扩展字段11*/
	//@Excel(name="扩展字段11")
	private java.lang.String extend11;
	/**扩展字段12*/
	//@Excel(name="扩展字段12")
	private java.lang.String extend12;
	/**扩展字段13*/
	//@Excel(name="扩展字段13")
	private java.lang.String extend13;
	/**扩展字段14*/
	//@Excel(name="扩展字段14")
	private java.lang.String extend14;
	/**扩展字段15*/
	//@Excel(name="扩展字段15")
	private java.lang.String extend15;
	/**扩展字段16*/
	//@Excel(name="扩展字段16")
	private java.lang.String extend16;
	/**扩展字段17*/
	//@Excel(name="扩展字段17")
	private java.lang.String extend17;
	/**扩展字段18*/
	//@Excel(name="扩展字段18")
	private java.lang.String extend18;
	/**扩展字段19*/
	//@Excel(name="扩展字段19")
	private java.lang.String extend19;
	/**扩展字段20*/
	//@Excel(name="扩展字段20")
	private java.lang.String extend20;
	
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
	public java.lang.String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(java.lang.String materialName) {
		this.materialName = materialName;
	}

	@Transient
	public java.lang.String getMaterialCode() {
		return materialCode;
	}

	public void setMaterialCode(java.lang.String materialCode) {
		this.materialCode = materialCode;
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
	 *@return: java.lang.String  仓库ID
	 */
	@Column(name ="WAREHOUSE_ID",nullable=false,length=36)
	public java.lang.String getWarehouseId(){
		return this.warehouseId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  仓库ID
	 */
	public void setWarehouseId(java.lang.String warehouseId){
		this.warehouseId = warehouseId;
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
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  扩展字段1
	 */
	@Column(name ="EXTEND1",nullable=true,length=255)
	public java.lang.String getExtend1(){
		return this.extend1;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  扩展字段1
	 */
	public void setExtend1(java.lang.String extend1){
		this.extend1 = extend1;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  扩展字段2
	 */
	@Column(name ="EXTEND2",nullable=true,length=255)
	public java.lang.String getExtend2(){
		return this.extend2;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  扩展字段2
	 */
	public void setExtend2(java.lang.String extend2){
		this.extend2 = extend2;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  扩展字段3
	 */
	@Column(name ="EXTEND3",nullable=true,length=255)
	public java.lang.String getExtend3(){
		return this.extend3;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  扩展字段3
	 */
	public void setExtend3(java.lang.String extend3){
		this.extend3 = extend3;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  扩展字段4
	 */
	@Column(name ="EXTEND4",nullable=true,length=255)
	public java.lang.String getExtend4(){
		return this.extend4;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  扩展字段4
	 */
	public void setExtend4(java.lang.String extend4){
		this.extend4 = extend4;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  扩展字段5
	 */
	@Column(name ="EXTEND5",nullable=true,length=255)
	public java.lang.String getExtend5(){
		return this.extend5;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  扩展字段5
	 */
	public void setExtend5(java.lang.String extend5){
		this.extend5 = extend5;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  扩展字段6
	 */
	@Column(name ="EXTEND6",nullable=true,length=255)
	public java.lang.String getExtend6(){
		return this.extend6;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  扩展字段6
	 */
	public void setExtend6(java.lang.String extend6){
		this.extend6 = extend6;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  扩展字段7
	 */
	@Column(name ="EXTEND7",nullable=true,length=255)
	public java.lang.String getExtend7(){
		return this.extend7;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  扩展字段7
	 */
	public void setExtend7(java.lang.String extend7){
		this.extend7 = extend7;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  扩展字段8
	 */
	@Column(name ="EXTEND8",nullable=true,length=255)
	public java.lang.String getExtend8(){
		return this.extend8;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  扩展字段8
	 */
	public void setExtend8(java.lang.String extend8){
		this.extend8 = extend8;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  扩展字段9
	 */
	@Column(name ="EXTEND9",nullable=true,length=255)
	public java.lang.String getExtend9(){
		return this.extend9;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  扩展字段9
	 */
	public void setExtend9(java.lang.String extend9){
		this.extend9 = extend9;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  扩展字段10
	 */
	@Column(name ="EXTEND10",nullable=true,length=255)
	public java.lang.String getExtend10(){
		return this.extend10;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  扩展字段10
	 */
	public void setExtend10(java.lang.String extend10){
		this.extend10 = extend10;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  扩展字段11
	 */
	@Column(name ="EXTEND11",nullable=true,length=255)
	public java.lang.String getExtend11(){
		return this.extend11;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  扩展字段11
	 */
	public void setExtend11(java.lang.String extend11){
		this.extend11 = extend11;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  扩展字段12
	 */
	@Column(name ="EXTEND12",nullable=true,length=255)
	public java.lang.String getExtend12(){
		return this.extend12;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  扩展字段12
	 */
	public void setExtend12(java.lang.String extend12){
		this.extend12 = extend12;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  扩展字段13
	 */
	@Column(name ="EXTEND13",nullable=true,length=255)
	public java.lang.String getExtend13(){
		return this.extend13;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  扩展字段13
	 */
	public void setExtend13(java.lang.String extend13){
		this.extend13 = extend13;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  扩展字段14
	 */
	@Column(name ="EXTEND14",nullable=true,length=255)
	public java.lang.String getExtend14(){
		return this.extend14;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  扩展字段14
	 */
	public void setExtend14(java.lang.String extend14){
		this.extend14 = extend14;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  扩展字段15
	 */
	@Column(name ="EXTEND15",nullable=true,length=255)
	public java.lang.String getExtend15(){
		return this.extend15;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  扩展字段15
	 */
	public void setExtend15(java.lang.String extend15){
		this.extend15 = extend15;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  扩展字段16
	 */
	@Column(name ="EXTEND16",nullable=true,length=255)
	public java.lang.String getExtend16(){
		return this.extend16;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  扩展字段16
	 */
	public void setExtend16(java.lang.String extend16){
		this.extend16 = extend16;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  扩展字段17
	 */
	@Column(name ="EXTEND17",nullable=true,length=255)
	public java.lang.String getExtend17(){
		return this.extend17;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  扩展字段17
	 */
	public void setExtend17(java.lang.String extend17){
		this.extend17 = extend17;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  扩展字段18
	 */
	@Column(name ="EXTEND18",nullable=true,length=255)
	public java.lang.String getExtend18(){
		return this.extend18;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  扩展字段18
	 */
	public void setExtend18(java.lang.String extend18){
		this.extend18 = extend18;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  扩展字段19
	 */
	@Column(name ="EXTEND19",nullable=true,length=255)
	public java.lang.String getExtend19(){
		return this.extend19;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  扩展字段19
	 */
	public void setExtend19(java.lang.String extend19){
		this.extend19 = extend19;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  扩展字段20
	 */
	@Column(name ="EXTEND20",nullable=true,length=255)
	public java.lang.String getExtend20(){
		return this.extend20;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  扩展字段20
	 */
	public void setExtend20(java.lang.String extend20){
		this.extend20 = extend20;
	}
}
