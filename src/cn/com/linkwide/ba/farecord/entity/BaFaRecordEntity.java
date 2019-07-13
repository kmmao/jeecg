package cn.com.linkwide.ba.farecord.entity;

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
 * @Description: 扣减预算操作记录插入规则
 * @author onlineGenerator
 * @date 2019-03-13 16:49:10
 * @version V1.0   
 *
 */
@Entity
@Table(name = "ba_fa_record", schema = "")
@SuppressWarnings("serial")
public class BaFaRecordEntity implements java.io.Serializable {
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
	/**模块名称*/
	@Excel(name="模块名称",width=15)
	private java.lang.String modular;
	/**支出类型*/
	@Excel(name="支出类型",width=15)
	private java.lang.String vitemtype;
	/**项目编码*/
	@Excel(name="项目编码",width=15)
	private java.lang.String vitemcode;
	/**项目名称*/
	@Excel(name="项目名称",width=15)
	private java.lang.String vitemname;
	/**明细编码*/
	@Excel(name="明细编码",width=15)
	private java.lang.String detailcode;
	/**明细名称*/
	@Excel(name="明细名称",width=15)
	private java.lang.String detailname;
	/**扣减金额*/
	@Excel(name="扣减金额",width=15)
	private java.math.BigDecimal money;
	/**操作员*/
	@Excel(name="操作员",width=15)
	private java.lang.String creatname;
	/**日期*/
	@Excel(name="日期",width=15,format = "yyyy-MM-dd")
	private java.util.Date creatdate;
	/**业务方式*/
	@Excel(name="业务方式",width=15)
	private java.lang.String structures;
	/**预留字段1*/
	private java.lang.String v1;
	/**预留字段2*/
	private java.lang.String v2;
	/**预留字段3*/
	private java.lang.String v3;
	/**预留字段4*/
	private java.lang.String v4;
	/**预留字段5*/
	private java.lang.String v5;
	/**预留字段6*/
	private java.lang.String v6;
	/**预留字段7*/
	private java.lang.String v7;
	/**预留字段8*/
	private java.lang.String v8;
	/**预留字段9*/
	private java.lang.String v9;
	/**预留字段10*/
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
	 *@return: java.lang.String  模块名称
	 */

	@Column(name ="MODULAR",nullable=true,length=32)
	public java.lang.String getModular(){
		return this.modular;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  模块名称
	 */
	public void setModular(java.lang.String modular){
		this.modular = modular;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  支出类型
	 */

	@Column(name ="VITEMTYPE",nullable=true,length=32)
	public java.lang.String getVitemtype(){
		return this.vitemtype;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  支出类型
	 */
	public void setVitemtype(java.lang.String vitemtype){
		this.vitemtype = vitemtype;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  项目编码
	 */

	@Column(name ="VITEMCODE",nullable=true,length=32)
	public java.lang.String getVitemcode(){
		return this.vitemcode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  项目编码
	 */
	public void setVitemcode(java.lang.String vitemcode){
		this.vitemcode = vitemcode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  项目名称
	 */

	@Column(name ="VITEMNAME",nullable=true,length=32)
	public java.lang.String getVitemname(){
		return this.vitemname;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  项目名称
	 */
	public void setVitemname(java.lang.String vitemname){
		this.vitemname = vitemname;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  明细编码
	 */

	@Column(name ="DETAILCODE",nullable=true,length=32)
	public java.lang.String getDetailcode(){
		return this.detailcode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  明细编码
	 */
	public void setDetailcode(java.lang.String detailcode){
		this.detailcode = detailcode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  明细名称
	 */

	@Column(name ="DETAILNAME",nullable=true,length=32)
	public java.lang.String getDetailname(){
		return this.detailname;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  明细名称
	 */
	public void setDetailname(java.lang.String detailname){
		this.detailname = detailname;
	}
	/**
	 *方法: 取得java.math.BigDecimal
	 *@return: java.math.BigDecimal  扣减金额
	 */

	@Column(name ="MONEY",nullable=true,length=32)
	public java.math.BigDecimal getMoney(){
		return this.money;
	}

	/**
	 *方法: 设置java.math.BigDecimal
	 *@param: java.math.BigDecimal  扣减金额
	 */
	public void setMoney(java.math.BigDecimal money){
		this.money = money;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  操作员
	 */

	@Column(name ="CREATNAME",nullable=true,length=32)
	public java.lang.String getCreatname(){
		return this.creatname;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  操作员
	 */
	public void setCreatname(java.lang.String creatname){
		this.creatname = creatname;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  日期
	 */

	@Column(name ="CREATDATE",nullable=true,length=32)
	public java.util.Date getCreatdate(){
		return this.creatdate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  日期
	 */
	public void setCreatdate(java.util.Date creatdate){
		this.creatdate = creatdate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  业务方式
	 */

	@Column(name ="STRUCTURES",nullable=true,length=32)
	public java.lang.String getStructures(){
		return this.structures;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  业务方式
	 */
	public void setStructures(java.lang.String structures){
		this.structures = structures;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预留字段1
	 */

	@Column(name ="V1",nullable=true,length=225)
	public java.lang.String getV1(){
		return this.v1;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预留字段1
	 */
	public void setV1(java.lang.String v1){
		this.v1 = v1;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预留字段2
	 */

	@Column(name ="V2",nullable=true,length=225)
	public java.lang.String getV2(){
		return this.v2;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预留字段2
	 */
	public void setV2(java.lang.String v2){
		this.v2 = v2;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预留字段3
	 */

	@Column(name ="V3",nullable=true,length=225)
	public java.lang.String getV3(){
		return this.v3;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预留字段3
	 */
	public void setV3(java.lang.String v3){
		this.v3 = v3;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预留字段4
	 */

	@Column(name ="V4",nullable=true,length=225)
	public java.lang.String getV4(){
		return this.v4;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预留字段4
	 */
	public void setV4(java.lang.String v4){
		this.v4 = v4;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预留字段5
	 */

	@Column(name ="V5",nullable=true,length=225)
	public java.lang.String getV5(){
		return this.v5;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预留字段5
	 */
	public void setV5(java.lang.String v5){
		this.v5 = v5;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预留字段6
	 */

	@Column(name ="V6",nullable=true,length=225)
	public java.lang.String getV6(){
		return this.v6;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预留字段6
	 */
	public void setV6(java.lang.String v6){
		this.v6 = v6;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预留字段7
	 */

	@Column(name ="V7",nullable=true,length=225)
	public java.lang.String getV7(){
		return this.v7;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预留字段7
	 */
	public void setV7(java.lang.String v7){
		this.v7 = v7;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预留字段8
	 */

	@Column(name ="V8",nullable=true,length=225)
	public java.lang.String getV8(){
		return this.v8;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预留字段8
	 */
	public void setV8(java.lang.String v8){
		this.v8 = v8;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预留字段9
	 */

	@Column(name ="V9",nullable=true,length=225)
	public java.lang.String getV9(){
		return this.v9;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预留字段9
	 */
	public void setV9(java.lang.String v9){
		this.v9 = v9;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预留字段10
	 */

	@Column(name ="V10",nullable=true,length=225)
	public java.lang.String getV10(){
		return this.v10;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预留字段10
	 */
	public void setV10(java.lang.String v10){
		this.v10 = v10;
	}
}
