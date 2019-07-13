package cn.com.linkwide.ba.bawarninginfo.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.lang.String;
import java.lang.Double;
import java.lang.Integer;
import java.math.BigDecimal;
import javax.xml.soap.Text;
import java.sql.Blob;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.SequenceGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

/**   
 * @Title: Entity
 * @Description: 预警信息
 * @author onlineGenerator
 * @date 2019-01-08 19:45:53
 * @version V1.0   
 *
 */
@Entity
@Table(name = "ba_warning_info", schema = "")
@SuppressWarnings("serial")
public class BaWarningInfoEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**创建人名称*/
	private java.lang.String createName;
	/**创建人登录名称*/
	private java.lang.String createBy;
	/**创建日期*/
	private java.util.Date createDate;
	/**预警源*/
	@Excel(name="预警源",width=15)
	private java.lang.String warningSource;
	/**预警信息*/
	@Excel(name="预警信息",width=15)
	private java.lang.String warningInfo;
	/**预警依据*/
	@Excel(name="预警依据",width=15)
	private java.lang.String warningBasis;
	/**模块预警单据id*/
	@Excel(name="模块预警单据id",width=15)
	private java.lang.String tableId;
	/**预警时间*/
	@Excel(name="预警时间",width=15,format = "yyyy-MM-dd")
	private java.util.Date warningDate;
	/**业务单据主键id*/
	@Excel(name="业务单据主键id",width=15)
	private java.lang.String v1;
	/**预留2*/
	@Excel(name="预留2",width=15)
	private java.lang.String v2;
	/**预留3*/
	@Excel(name="预留3",width=15)
	private java.lang.String v3;
	/**预留4*/
	@Excel(name="预留4",width=15)
	private java.lang.String v4;
	/**预留5*/
	@Excel(name="预留5",width=15)
	private java.lang.String v5;
	/**预留6*/
	@Excel(name="预留6",width=15)
	private java.lang.String v6;
	/**预留7*/
	@Excel(name="预留7",width=15)
	private java.lang.String v7;
	/**预留8*/
	@Excel(name="预留8",width=15)
	private java.lang.String v8;
	/**预留9*/
	@Excel(name="预留9",width=15)
	private java.lang.String v9;
	/**预留10*/
	@Excel(name="预留10",width=15)
	private java.lang.String v10;
	
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
	 *@return: java.lang.String  预警源
	 */

	@Column(name ="WARNING_SOURCE",nullable=true,length=32)
	public java.lang.String getWarningSource(){
		return this.warningSource;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预警源
	 */
	public void setWarningSource(java.lang.String warningSource){
		this.warningSource = warningSource;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预警信息
	 */

	@Column(name ="WARNING_INFO",nullable=true,length=32)
	public java.lang.String getWarningInfo(){
		return this.warningInfo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预警信息
	 */
	public void setWarningInfo(java.lang.String warningInfo){
		this.warningInfo = warningInfo;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预警依据
	 */

	@Column(name ="WARNING_BASIS",nullable=true,length=32)
	public java.lang.String getWarningBasis(){
		return this.warningBasis;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预警依据
	 */
	public void setWarningBasis(java.lang.String warningBasis){
		this.warningBasis = warningBasis;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  模块预警单据id
	 */

	@Column(name ="TABLE_ID",nullable=true,length=32)
	public java.lang.String getTableId(){
		return this.tableId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  模块预警单据id
	 */
	public void setTableId(java.lang.String tableId){
		this.tableId = tableId;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  预警时间
	 */

	@Column(name ="WARNING_DATE",nullable=true,length=32)
	public java.util.Date getWarningDate(){
		return this.warningDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  预警时间
	 */
	public void setWarningDate(java.util.Date warningDate){
		this.warningDate = warningDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  业务单据主键id
	 */

	@Column(name ="V1",nullable=true,length=32)
	public java.lang.String getV1(){
		return this.v1;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  业务单据主键id
	 */
	public void setV1(java.lang.String v1){
		this.v1 = v1;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预留2
	 */

	@Column(name ="V2",nullable=true,length=32)
	public java.lang.String getV2(){
		return this.v2;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预留2
	 */
	public void setV2(java.lang.String v2){
		this.v2 = v2;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预留3
	 */

	@Column(name ="V3",nullable=true,length=32)
	public java.lang.String getV3(){
		return this.v3;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预留3
	 */
	public void setV3(java.lang.String v3){
		this.v3 = v3;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预留4
	 */

	@Column(name ="V4",nullable=true,length=32)
	public java.lang.String getV4(){
		return this.v4;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预留4
	 */
	public void setV4(java.lang.String v4){
		this.v4 = v4;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预留5
	 */

	@Column(name ="V5",nullable=true,length=32)
	public java.lang.String getV5(){
		return this.v5;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预留5
	 */
	public void setV5(java.lang.String v5){
		this.v5 = v5;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预留6
	 */

	@Column(name ="V6",nullable=true,length=32)
	public java.lang.String getV6(){
		return this.v6;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预留6
	 */
	public void setV6(java.lang.String v6){
		this.v6 = v6;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预留7
	 */

	@Column(name ="V7",nullable=true,length=32)
	public java.lang.String getV7(){
		return this.v7;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预留7
	 */
	public void setV7(java.lang.String v7){
		this.v7 = v7;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预留8
	 */

	@Column(name ="V8",nullable=true,length=32)
	public java.lang.String getV8(){
		return this.v8;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预留8
	 */
	public void setV8(java.lang.String v8){
		this.v8 = v8;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预留9
	 */

	@Column(name ="V9",nullable=true,length=32)
	public java.lang.String getV9(){
		return this.v9;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预留9
	 */
	public void setV9(java.lang.String v9){
		this.v9 = v9;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预留10
	 */

	@Column(name ="V10",nullable=true,length=32)
	public java.lang.String getV10(){
		return this.v10;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预留10
	 */
	public void setV10(java.lang.String v10){
		this.v10 = v10;
	}
}
