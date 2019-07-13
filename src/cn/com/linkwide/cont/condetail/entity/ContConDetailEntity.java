package cn.com.linkwide.cont.condetail.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

/**   
 * @Title: Entity
 * @Description: 合同明细
 * @author onlineGenerator
 * @date 2018-08-03 16:48:55
 * @version V1.0   
 *
 */
@Entity
@Table(name = "cont_con_detail", schema = "")
@SuppressWarnings("serial")
public class ContConDetailEntity implements java.io.Serializable {
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
	/**合同档案设置*/
    @Excel(name="合同档案设置",width=15)
	private java.lang.String conDacode;
	/**项目编码*/
    @Excel(name="项目编码",width=15)
	private java.lang.String debugCode;
	/**项目名称*/
    @Excel(name="项目名称",width=15)
	private java.lang.String debugName;
	/**标志*/
    @Excel(name="标志",width=15)
	private java.lang.String sign;
	/**计量单位*/
    @Excel(name="计量单位",width=15)
	private java.lang.String munit;
	/**税率*/
    @Excel(name="税率",width=15)
	private java.lang.Double rate;
	/**折扣率*/
    @Excel(name="折扣率",width=15)
	private java.lang.Double disrate;
	/**数量*/
    @Excel(name="数量",width=15)
	private java.lang.Double amount;
	/**无税原币单价*/
    @Excel(name="无税原币单价",width=15)
	private java.lang.Double ntrcup;
	/**无税原币金额*/
    @Excel(name="无税原币金额",width=15)
	private java.lang.Double tfmoney;
	/**含税原币单价*/
    @Excel(name="含税原币单价",width=15)
	private java.lang.Double tupotomwt;
	/**含税原币金额*/
    @Excel(name="含税原币金额",width=15)
	private java.lang.Double taotomwt;
	/**开始时间*/
    @Excel(name="开始时间",width=15,format = "yyyy-MM-dd")
	private java.util.Date startDate;
	/**结束时间*/
    @Excel(name="结束时间",width=15,format = "yyyy-MM-dd")
	private java.util.Date endDate;
	/**主表外键*/
    @Excel(name="主表外键",width=15)
	private java.lang.String pkId;
	/**备注*/
    @Excel(name="备注",width=15)
	private java.lang.String maker;
	
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
	 *@return: java.lang.String  合同档案设置
	 */
	
	@Column(name ="CON_DACODE",nullable=true,length=32)
	public java.lang.String getConDacode(){
		return this.conDacode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  合同档案设置
	 */
	public void setConDacode(java.lang.String conDacode){
		this.conDacode = conDacode;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  项目编码
	 */
	
	@Column(name ="DEBUG_CODE",nullable=true,length=32)
	public java.lang.String getDebugCode(){
		return this.debugCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  项目编码
	 */
	public void setDebugCode(java.lang.String debugCode){
		this.debugCode = debugCode;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  项目名称
	 */
	
	@Column(name ="DEBUG_NAME",nullable=true,length=32)
	public java.lang.String getDebugName(){
		return this.debugName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  项目名称
	 */
	public void setDebugName(java.lang.String debugName){
		this.debugName = debugName;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  标志
	 */
	
	@Column(name ="SIGN",nullable=true,length=32)
	public java.lang.String getSign(){
		return this.sign;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  标志
	 */
	public void setSign(java.lang.String sign){
		this.sign = sign;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  计量单位
	 */
	
	@Column(name ="MUNIT",nullable=true,length=32)
	public java.lang.String getMunit(){
		return this.munit;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  计量单位
	 */
	public void setMunit(java.lang.String munit){
		this.munit = munit;
	}
	
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  税率
	 */
	
	@Column(name ="RATE",nullable=true,length=32)
	public java.lang.Double getRate(){
		return this.rate;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  税率
	 */
	public void setRate(java.lang.Double rate){
		this.rate = rate;
	}
	
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  折扣率
	 */
	
	@Column(name ="DISRATE",nullable=true,length=32)
	public java.lang.Double getDisrate(){
		return this.disrate;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  折扣率
	 */
	public void setDisrate(java.lang.Double disrate){
		this.disrate = disrate;
	}
	
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  数量
	 */
	
	@Column(name ="AMOUNT",nullable=true,length=32)
	public java.lang.Double getAmount(){
		return this.amount;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  数量
	 */
	public void setAmount(java.lang.Double amount){
		this.amount = amount;
	}
	
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  无税原币单价
	 */
	
	@Column(name ="NTRCUP",nullable=true,length=32)
	public java.lang.Double getNtrcup(){
		return this.ntrcup;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  无税原币单价
	 */
	public void setNtrcup(java.lang.Double ntrcup){
		this.ntrcup = ntrcup;
	}
	
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  无税原币金额
	 */
	
	@Column(name ="TFMONEY",nullable=true,length=32)
	public java.lang.Double getTfmoney(){
		return this.tfmoney;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  无税原币金额
	 */
	public void setTfmoney(java.lang.Double tfmoney){
		this.tfmoney = tfmoney;
	}
	
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  含税原币单价
	 */
	
	@Column(name ="TUPOTOMWT",nullable=true,length=32)
	public java.lang.Double getTupotomwt(){
		return this.tupotomwt;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  含税原币单价
	 */
	public void setTupotomwt(java.lang.Double tupotomwt){
		this.tupotomwt = tupotomwt;
	}
	
	/**
	 *方法: 取得java.lang.Double
	 *@return: java.lang.Double  含税原币金额
	 */
	
	@Column(name ="TAOTOMWT",nullable=true,length=32)
	public java.lang.Double getTaotomwt(){
		return this.taotomwt;
	}

	/**
	 *方法: 设置java.lang.Double
	 *@param: java.lang.Double  含税原币金额
	 */
	public void setTaotomwt(java.lang.Double taotomwt){
		this.taotomwt = taotomwt;
	}
	
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  开始时间
	 */
	
	@Column(name ="START_DATE",nullable=true,length=32)
	public java.util.Date getStartDate(){
		return this.startDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  开始时间
	 */
	public void setStartDate(java.util.Date startDate){
		this.startDate = startDate;
	}
	
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  结束时间
	 */
	
	@Column(name ="END_DATE",nullable=true,length=32)
	public java.util.Date getEndDate(){
		return this.endDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  结束时间
	 */
	public void setEndDate(java.util.Date endDate){
		this.endDate = endDate;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  主表外键
	 */
	
	@Column(name ="PK_ID",nullable=true,length=32)
	public java.lang.String getPkId(){
		return this.pkId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  主表外键
	 */
	public void setPkId(java.lang.String pkId){
		this.pkId = pkId;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  备注
	 */
	
	@Column(name ="MAKER",nullable=true,length=50)
	public java.lang.String getMaker(){
		return this.maker;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  备注
	 */
	public void setMaker(java.lang.String maker){
		this.maker = maker;
	}
	
}
