package cn.com.linkwide.ba.location.entity;

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
 * @Description: 货位档案
 * @author onlineGenerator
 * @date 2018-07-05 18:44:29
 * @version V1.0   
 *
 */
@Entity
@Table(name = "l_ba_location_dict", schema = "")
@SuppressWarnings("serial")
public class LBaLocationDictEntity implements java.io.Serializable {
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
	/**货位编码*/
	@Excel(name="货位编码",width=15)
	private java.lang.String code;
	/**货位名称*/
	@Excel(name="货位名称",width=15)
	private java.lang.String name;
	/**所属仓库*/
	@Excel(name="所属仓库",width=15)
	private java.lang.String wareId;
	/**最大体积*/
	@Excel(name="最大体积",width=15)
	private java.lang.String maxV;
	/**最大重量*/
	@Excel(name="最大重量",width=15)
	private java.lang.String maxG;
	/**条形码*/
	@Excel(name="条形码",width=15)
	private java.lang.String barCode;
	/**备注*/
	@Excel(name="备注",width=15)
	private java.lang.String remark;
	/**父节点编码*/
	@Excel(name="父节点编码",width=15)
	private java.lang.String parentId;
	/**级次*/
	@Excel(name="级次",width=15)
	private java.lang.Integer loLeve;
	/**是否停用*/
	@Excel(name="是否停用",width=15)
	private java.lang.Integer isStop;
	

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
	 *@return: java.lang.String  货位编码
	 */

	@Column(name ="CODE",nullable=true,length=32)
	public java.lang.String getCode(){
		return this.code;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  货位编码
	 */
	public void setCode(java.lang.String code){
		this.code = code;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  货位名称
	 */

	@Column(name ="NAME",nullable=true,length=32)
	public java.lang.String getName(){
		return this.name;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  货位名称
	 */
	public void setName(java.lang.String name){
		this.name = name;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  所属仓库
	 */

	@Column(name ="WARE_ID",nullable=true,length=32)
	public java.lang.String getWareId(){
		return this.wareId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  所属仓库
	 */
	public void setWareId(java.lang.String wareId){
		this.wareId = wareId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  最大体积
	 */

	@Column(name ="MAX_V",nullable=true,length=32)
	public java.lang.String getMaxV(){
		return this.maxV;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  最大体积
	 */
	public void setMaxV(java.lang.String maxV){
		this.maxV = maxV;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  最大重量
	 */

	@Column(name ="MAX_G",nullable=true,length=32)
	public java.lang.String getMaxG(){
		return this.maxG;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  最大重量
	 */
	public void setMaxG(java.lang.String maxG){
		this.maxG = maxG;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  条形码
	 */

	@Column(name ="BAR_CODE",nullable=true,length=32)
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
	 *@return: java.lang.String  备注
	 */

	@Column(name ="REMARK",nullable=true,length=32)
	public java.lang.String getRemark(){
		return this.remark;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  备注
	 */
	public void setRemark(java.lang.String remark){
		this.remark = remark;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  父节点编码
	 */

	@Column(name ="PARENT_ID",nullable=true,length=32)
	public java.lang.String getParentId(){
		return this.parentId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  父节点编码
	 */
	public void setParentId(java.lang.String parentId){
		this.parentId = parentId;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  级次
	 */

	@Column(name ="LO_LEVE",nullable=true,length=32)
	public java.lang.Integer getLoLeve(){
		return this.loLeve;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  级次
	 */
	public void setLoLeve(java.lang.Integer loLeve){
		this.loLeve = loLeve;
	}
	
	@Column(name ="IS_STOP",nullable=false,length=36)
	public java.lang.Integer getIsStop() {
		return isStop;
	}

	public void setIsStop(java.lang.Integer isStop) {
		this.isStop = isStop;
	}
}
