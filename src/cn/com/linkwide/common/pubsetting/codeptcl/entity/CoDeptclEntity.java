package cn.com.linkwide.common.pubsetting.codeptcl.entity;

import java.util.List;

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
 * @Description: 科室分类
 * @author onlineGenerator
 * @date 2017-08-30 09:14:21
 * @version V1.0   
 *
 */
@Entity
@Table(name = "co_deptcl", schema = "")
@SuppressWarnings("serial")
public class CoDeptclEntity implements java.io.Serializable {
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
	/**科室分类编码*/
	@Excel(name="科室分类编码",width = 20)
	private java.lang.String vcode;
	/**科室分类名称*/
	@Excel(name="科室分类名称",width = 20)
	private java.lang.String vname;
	/**备注*/
	@Excel(name="备注",width = 20)
	private java.lang.String memo;
	/**时间戳*/
	private java.util.Date ts;
	/**预留字段1*/
	private java.lang.String vdef1;
	/**预留字段2*/
	@Excel(name="上级编码",width = 20)
	private java.lang.String vdef2;
	/**预留字段3*/
	private java.lang.String vdef3;
	/**预留字段4*/
	private java.lang.String vdef4;
	/**预留字段5*/
	private java.lang.String vdef5;
	
	//新增属性
	private List<CoDeptclEntity> coDeptclList;
	
	
	
	@Transient
	public List<CoDeptclEntity> getCoDeptclList() {
		return coDeptclList;
	}

	public void setCoDeptclList(List<CoDeptclEntity> coDeptclList) {
		this.coDeptclList = coDeptclList;
	}

	public java.util.Date getTs() {
		return ts;
	}

	public void setTs(java.util.Date ts) {
		this.ts = ts;
	}

	public java.lang.String getVdef1() {
		return vdef1;
	}

	public void setVdef1(java.lang.String vdef1) {
		this.vdef1 = vdef1;
	}

	public java.lang.String getVdef2() {
		return vdef2;
	}

	public void setVdef2(java.lang.String vdef2) {
		this.vdef2 = vdef2;
	}

	public java.lang.String getVdef3() {
		return vdef3;
	}

	public void setVdef3(java.lang.String vdef3) {
		this.vdef3 = vdef3;
	}

	public java.lang.String getVdef4() {
		return vdef4;
	}

	public void setVdef4(java.lang.String vdef4) {
		this.vdef4 = vdef4;
	}

	public java.lang.String getVdef5() {
		return vdef5;
	}

	public void setVdef5(java.lang.String vdef5) {
		this.vdef5 = vdef5;
	}

	public java.lang.String getVdef6() {
		return vdef6;
	}

	public void setVdef6(java.lang.String vdef6) {
		this.vdef6 = vdef6;
	}

	public java.lang.String getVdef7() {
		return vdef7;
	}

	public void setVdef7(java.lang.String vdef7) {
		this.vdef7 = vdef7;
	}

	public java.lang.String getVdef8() {
		return vdef8;
	}

	public void setVdef8(java.lang.String vdef8) {
		this.vdef8 = vdef8;
	}

	public java.lang.String getVdef9() {
		return vdef9;
	}

	public void setVdef9(java.lang.String vdef9) {
		this.vdef9 = vdef9;
	}

	public java.lang.String getVdef10() {
		return vdef10;
	}

	public void setVdef10(java.lang.String vdef10) {
		this.vdef10 = vdef10;
	}

	/**预留字段6*/
	private java.lang.String vdef6;
	/**预留字段7*/
	private java.lang.String vdef7;
	/**预留字段8*/
	private java.lang.String vdef8;
	/**预留字段9*/
	private java.lang.String vdef9;
	/**预留字段10*/
	private java.lang.String vdef10;
	
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
	 *@return: java.lang.String  科室分类编码
	 */
	@Column(name ="VCODE",nullable=true,length=32)
	public java.lang.String getVcode(){
		return this.vcode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  科室分类编码
	 */
	public void setVcode(java.lang.String vcode){
		this.vcode = vcode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  科室分类名称
	 */
	@Column(name ="VNAME",nullable=true,length=32)
	public java.lang.String getVname(){
		return this.vname;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  科室分类名称
	 */
	public void setVname(java.lang.String vname){
		this.vname = vname;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  备注
	 */
	@Column(name ="MEMO",nullable=true,length=32)
	public java.lang.String getMemo(){
		return this.memo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  备注
	 */
	public void setMemo(java.lang.String memo){
		this.memo = memo;
	}
}
