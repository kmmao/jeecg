package org.jeecgframework.web.system.company.entity;

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
 * @Description: 单位
 * @author onlineGenerator
 * @date 2019-05-23 16:08:19
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_s_company", schema = "")
@SuppressWarnings("serial")
public class TSCompany implements java.io.Serializable {
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
	/**公司编码*/
	@Excel(name="公司编码",width=15)
	private java.lang.String companyCode;
	/**公司名称*/
	@Excel(name="公司名称",width=15)
	private java.lang.String companyName;
	/**英文名称*/
	@Excel(name="英文名称",width=15)
	private java.lang.String englishName;
	/**简称*/
	@Excel(name="简称",width=15)
	private java.lang.String shortName;
	/**单位描述*/
	@Excel(name="单位描述",width=15)
	private java.lang.String companyDescribe;
	/**管理员*/
	@Excel(name="管理员",width=15)
	private java.lang.String administrator;
	/**单位类型*/
	@Excel(name="单位类型",width=15)
	private java.lang.String companyType;
	/**法人*/
	@Excel(name="法人",width=15)
	private java.lang.String legalPerson;
	/**上级单位*/
	@Excel(name="上级单位",width=15)
	private java.lang.String parentId;
	/**上级主管部门编码*/
	@Excel(name="上级主管部门编码",width=15)
	private java.lang.String parentOrgCode;
	/**上级主管部门名称*/
	@Excel(name="上级主管部门名称",width=15)
	private java.lang.String parentOrgName;
	/**所在省*/
	@Excel(name="所在省",width=15)
	private java.lang.String province;
	/**所在市*/
	@Excel(name="所在市",width=15)
	private java.lang.String city;
	/**所在区*/
	@Excel(name="所在区",width=15)
	private java.lang.String area;
	/**经度*/
	private java.lang.String coordinateX;
	/**纬度*/
	private java.lang.String coordinateY;
	
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
	 *@return: java.lang.String  公司编码
	 */

	@Column(name ="COMPANY_CODE",nullable=false,length=50)
	public java.lang.String getCompanyCode(){
		return this.companyCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  公司编码
	 */
	public void setCompanyCode(java.lang.String companyCode){
		this.companyCode = companyCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  公司名称
	 */

	@Column(name ="COMPANY_NAME",nullable=false,length=64)
	public java.lang.String getCompanyName(){
		return this.companyName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  公司名称
	 */
	public void setCompanyName(java.lang.String companyName){
		this.companyName = companyName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  英文名称
	 */

	@Column(name ="ENGLISH_NAME",nullable=true,length=128)
	public java.lang.String getEnglishName(){
		return this.englishName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  英文名称
	 */
	public void setEnglishName(java.lang.String englishName){
		this.englishName = englishName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  简称
	 */

	@Column(name ="SHORT_NAME",nullable=false,length=32)
	public java.lang.String getShortName(){
		return this.shortName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  简称
	 */
	public void setShortName(java.lang.String shortName){
		this.shortName = shortName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  单位描述
	 */

	@Column(name ="COMPANY_DESCRIBE",nullable=true,length=512)
	public java.lang.String getCompanyDescribe(){
		return this.companyDescribe;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  单位描述
	 */
	public void setCompanyDescribe(java.lang.String companyDescribe){
		this.companyDescribe = companyDescribe;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  管理员
	 */

	@Column(name ="ADMINISTRATOR",nullable=true,length=32)
	public java.lang.String getAdministrator(){
		return this.administrator;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  管理员
	 */
	public void setAdministrator(java.lang.String administrator){
		this.administrator = administrator;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  单位类型
	 */

	@Column(name ="COMPANY_TYPE",nullable=true,length=32)
	public java.lang.String getCompanyType(){
		return this.companyType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  单位类型
	 */
	public void setCompanyType(java.lang.String companyType){
		this.companyType = companyType;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  法人
	 */

	@Column(name ="LEGAL_PERSON",nullable=true,length=32)
	public java.lang.String getLegalPerson(){
		return this.legalPerson;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  法人
	 */
	public void setLegalPerson(java.lang.String legalPerson){
		this.legalPerson = legalPerson;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  上级单位
	 */

	@Column(name ="PARENT_ID",nullable=true,length=32)
	public java.lang.String getParentId(){
		return this.parentId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  上级单位
	 */
	public void setParentId(java.lang.String parentId){
		this.parentId = parentId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  上级主管部门编码
	 */

	@Column(name ="PARENT_ORG_CODE",nullable=true,length=32)
	public java.lang.String getParentOrgCode(){
		return this.parentOrgCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  上级主管部门编码
	 */
	public void setParentOrgCode(java.lang.String parentOrgCode){
		this.parentOrgCode = parentOrgCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  所在省
	 */

	@Column(name ="PROVINCE",nullable=true,length=32)
	public java.lang.String getProvince() {
		return province;
	}

	public void setProvince(java.lang.String province) {
		this.province = province;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  所在市
	 */

	@Column(name ="CITY",nullable=true,length=32)
	public java.lang.String getCity(){
		return this.city;
	}

	

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  所在市
	 */
	public void setCity(java.lang.String city){
		this.city = city;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  所在区
	 */

	@Column(name ="AREA",nullable=true,length=32)
	public java.lang.String getArea(){
		return this.area;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  所在区
	 */
	public void setArea(java.lang.String area){
		this.area = area;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  经度
	 */

	@Column(name ="COORDINATE_X",nullable=true,length=32)
	public java.lang.String getCoordinateX(){
		return this.coordinateX;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  经度
	 */
	public void setCoordinateX(java.lang.String coordinateX){
		this.coordinateX = coordinateX;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  纬度
	 */

	@Column(name ="COORDINATE_Y",nullable=true,length=32)
	public java.lang.String getCoordinateY(){
		return this.coordinateY;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  纬度
	 */
	public void setCoordinateY(java.lang.String coordinateY){
		this.coordinateY = coordinateY;
	}
	
	@Column(name ="PARENT_ORG_NAME",nullable=true,length=32)
	public java.lang.String getParentOrgName() {
		return parentOrgName;
	}

	public void setParentOrgName(java.lang.String parentOrgName) {
		this.parentOrgName = parentOrgName;
	}
	
	
}
