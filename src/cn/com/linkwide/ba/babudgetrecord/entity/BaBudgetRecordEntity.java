package cn.com.linkwide.ba.babudgetrecord.entity;

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
 * @Description: 预算执行情况表
 * @author onlineGenerator
 * @date 2019-04-22 14:04:22
 * @version V1.0   
 *
 */
@Entity
@Table(name = "ba_budget_record", schema = "")
@SuppressWarnings("serial")
public class BaBudgetRecordEntity implements java.io.Serializable {
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
	private java.lang.String mkmc;
	/**菜单名称*/
	@Excel(name="菜单名称",width=15)
	private java.lang.String cdmc;
	/**支出类型*/
	@Excel(name="支出类型",width=15)
	private java.lang.String zclx;
	/**执行科室*/
	@Excel(name="执行科室",width=15)
	private java.lang.String zxks;
	/**预算科室*/
	@Excel(name="预算科室",width=15)
	private java.lang.String ysks;
	/**预算项目编码*/
	@Excel(name="预算项目编码",width=15)
	private java.lang.String ysxmbm;
	/**预算项目名称*/
	@Excel(name="预算项目名称",width=15)
	private java.lang.String ysxmmc;
	/**支出项目编码*/
	@Excel(name="支出项目编码",width=15)
	private java.lang.String zcxmbm;
	/**支出项目名称*/
	@Excel(name="支出项目名称",width=15)
	private java.lang.String zcxmmc;
	/**资金来源*/
	@Excel(name="资金来源",width=15)
	private java.lang.String zjly;
	/**资金性质*/
	@Excel(name="资金性质",width=15)
	private java.lang.String zjxz;
	/**预留1*/
	@Excel(name="收入金额",width=15)
	private java.lang.String v1;
	/**支出金额*/
	@Excel(name="支出金额",width=15)
	private java.math.BigDecimal zcje;
	/**调整金额*/
	@Excel(name="调整金额",width=15)
	private java.math.BigDecimal tz;
	/**调减*/
	private java.math.BigDecimal tj;
	/**凭证号*/
	@Excel(name="凭证号",width=15)
	private java.lang.String pzh;
	/**预留2*/
	@Excel(name="摘要",width=15)
	private java.lang.String v2;
	/**操作员*/
	@Excel(name="操作员",width=15)
	private java.lang.String name;
	/**制单日期*/
	@Excel(name="制单日期",width=15,format = "yyyy-MM-dd")
	private java.util.Date billdate;
	/**唯一主键*/
	@Excel(name="唯一主键",width=15)
	private java.lang.String pkid;
	/**预留3*/
	private java.lang.String v3;
	/**预留4*/
	private java.lang.String v4;
	/**预留5*/
	private java.lang.String v5;
	/**预留6*/
	private java.lang.String v6;
	/**预留7*/
	private java.lang.String v7;
	/**预留8*/
	private java.lang.String v8;
	/**预留9*/
	private java.lang.String v9;
	/**预留10*/
	private java.lang.String v10;
	
	public BaBudgetRecordEntity() {
		super();
	}

	//构造方法  2019-4-22 zhuhd
	public BaBudgetRecordEntity(String mkmc, String cdmc, String zclx, String zxks, String ysks, String ysxmbm,
			String ysxmmc, String zcxmbm, String zcxmmc, String zjly, String zjxz,String v1, BigDecimal zcje, BigDecimal tz, String pzh,
			String v2,String name, Date billdate, String pkid) {
		super();
		this.mkmc = mkmc;
		this.cdmc = cdmc;
		this.zclx = zclx;
		this.zxks = zxks;
		this.ysks = ysks;
		this.ysxmbm = ysxmbm;
		this.ysxmmc = ysxmmc;
		this.zcxmbm = zcxmbm;
		this.zcxmmc = zcxmmc;
		this.zjly = zjly;
		this.zjxz = zjxz;
		this.v1 = v1;
		this.zcje = zcje;
		this.tz = tz;
		this.zcje = zcje;
		this.pzh = pzh;
		this.v2 = v2;
		this.name = name;
		this.billdate = billdate;
		this.pkid = pkid;
	}
	
	

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

	@Column(name ="MKMC",nullable=true,length=320)
	public java.lang.String getMkmc(){
		return this.mkmc;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  模块名称
	 */
	public void setMkmc(java.lang.String mkmc){
		this.mkmc = mkmc;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  菜单名称
	 */

	@Column(name ="CDMC",nullable=true,length=320)
	public java.lang.String getCdmc(){
		return this.cdmc;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  菜单名称
	 */
	public void setCdmc(java.lang.String cdmc){
		this.cdmc = cdmc;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  支出类型
	 */

	@Column(name ="ZCLX",nullable=true,length=320)
	public java.lang.String getZclx(){
		return this.zclx;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  支出类型
	 */
	public void setZclx(java.lang.String zclx){
		this.zclx = zclx;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  执行科室
	 */

	@Column(name ="ZXKS",nullable=true,length=320)
	public java.lang.String getZxks(){
		return this.zxks;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  执行科室
	 */
	public void setZxks(java.lang.String zxks){
		this.zxks = zxks;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预算科室
	 */

	@Column(name ="YSKS",nullable=true,length=320)
	public java.lang.String getYsks(){
		return this.ysks;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预算科室
	 */
	public void setYsks(java.lang.String ysks){
		this.ysks = ysks;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预算项目编码
	 */

	@Column(name ="YSXMBM",nullable=true,length=320)
	public java.lang.String getYsxmbm(){
		return this.ysxmbm;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预算项目编码
	 */
	public void setYsxmbm(java.lang.String ysxmbm){
		this.ysxmbm = ysxmbm;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预算项目名称
	 */

	@Column(name ="YSXMMC",nullable=true,length=320)
	public java.lang.String getYsxmmc(){
		return this.ysxmmc;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预算项目名称
	 */
	public void setYsxmmc(java.lang.String ysxmmc){
		this.ysxmmc = ysxmmc;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  支出项目编码
	 */

	@Column(name ="ZCXMBM",nullable=true,length=320)
	public java.lang.String getZcxmbm(){
		return this.zcxmbm;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  支出项目编码
	 */
	public void setZcxmbm(java.lang.String zcxmbm){
		this.zcxmbm = zcxmbm;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  支出项目名称
	 */

	@Column(name ="ZCXMMC",nullable=true,length=320)
	public java.lang.String getZcxmmc(){
		return this.zcxmmc;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  支出项目名称
	 */
	public void setZcxmmc(java.lang.String zcxmmc){
		this.zcxmmc = zcxmmc;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  资金来源
	 */

	@Column(name ="ZJLY",nullable=true,length=3200)
	public java.lang.String getZjly(){
		return this.zjly;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  资金来源
	 */
	public void setZjly(java.lang.String zjly){
		this.zjly = zjly;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  资金性质
	 */

	@Column(name ="ZJXZ",nullable=true,length=320)
	public java.lang.String getZjxz(){
		return this.zjxz;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  资金性质
	 */
	public void setZjxz(java.lang.String zjxz){
		this.zjxz = zjxz;
	}
	/**
	 *方法: 取得java.math.BigDecimal
	 *@return: java.math.BigDecimal  支出金额
	 */

	@Column(name ="ZCJE",nullable=true,length=32)
	public java.math.BigDecimal getZcje(){
		return this.zcje;
	}

	/**
	 *方法: 设置java.math.BigDecimal
	 *@param: java.math.BigDecimal  支出金额
	 */
	public void setZcje(java.math.BigDecimal zcje){
		this.zcje = zcje;
	}
	/**
	 *方法: 取得java.math.BigDecimal
	 *@return: java.math.BigDecimal  调增
	 */

	@Column(name ="TZ",nullable=true,length=32)
	public java.math.BigDecimal getTz(){
		return this.tz;
	}

	/**
	 *方法: 设置java.math.BigDecimal
	 *@param: java.math.BigDecimal  调增
	 */
	public void setTz(java.math.BigDecimal tz){
		this.tz = tz;
	}
	/**
	 *方法: 取得java.math.BigDecimal
	 *@return: java.math.BigDecimal  调减
	 */

	@Column(name ="TJ",nullable=true,length=32)
	public java.math.BigDecimal getTj(){
		return this.tj;
	}

	/**
	 *方法: 设置java.math.BigDecimal
	 *@param: java.math.BigDecimal  调减
	 */
	public void setTj(java.math.BigDecimal tj){
		this.tj = tj;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  凭证号
	 */

	@Column(name ="PZH",nullable=true,length=320)
	public java.lang.String getPzh(){
		return this.pzh;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  凭证号
	 */
	public void setPzh(java.lang.String pzh){
		this.pzh = pzh;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  操作员
	 */

	@Column(name ="NAME",nullable=true,length=320)
	public java.lang.String getName(){
		return this.name;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  操作员
	 */
	public void setName(java.lang.String name){
		this.name = name;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  制单日期
	 */

	@Column(name ="BILLDATE",nullable=true,length=32)
	public java.util.Date getBilldate(){
		return this.billdate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  制单日期
	 */
	public void setBilldate(java.util.Date billdate){
		this.billdate = billdate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  唯一主键
	 */

	@Column(name ="PKID",nullable=true,length=320)
	public java.lang.String getPkid(){
		return this.pkid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  唯一主键
	 */
	public void setPkid(java.lang.String pkid){
		this.pkid = pkid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预留1
	 */

	@Column(name ="V1",nullable=true,length=320)
	public java.lang.String getV1(){
		return this.v1;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预留1
	 */
	public void setV1(java.lang.String v1){
		this.v1 = v1;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预留2
	 */

	@Column(name ="V2",nullable=true,length=320)
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

	@Column(name ="V3",nullable=true,length=320)
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

	@Column(name ="V4",nullable=true,length=320)
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

	@Column(name ="V5",nullable=true,length=320)
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

	@Column(name ="V6",nullable=true,length=320)
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

	@Column(name ="V7",nullable=true,length=320)
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

	@Column(name ="V8",nullable=true,length=320)
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

	@Column(name ="V9",nullable=true,length=320)
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

	@Column(name ="V10",nullable=true,length=320)
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
