package cn.com.linkwide.ba.person;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

/**   
 * @Title: Entity
 * @Description: hr_person
 * @author onlineGenerator
 * @date 2018-08-15 09:45:07
 * @version V1.0   
 *
 */
@Entity
@Table(name = "hr_person", schema = "")
@SuppressWarnings("serial")
public class HrPersonEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**创建人名称*/
	@Excel(name="创建人名称",width=15)
	private java.lang.String createName;
	/**创建人登录名称*/
	@Excel(name="创建人登录名称",width=15)
	private java.lang.String createBy;
	/**创建日期*/
	@Excel(name="创建日期",width=15,format = "yyyy-MM-dd")
	private java.util.Date createDate;
	/**更新人名称*/
	@Excel(name="更新人名称",width=15)
	private java.lang.String updateName;
	/**更新人登录名称*/
	@Excel(name="更新人登录名称",width=15)
	private java.lang.String updateBy;
	/**更新日期*/
	@Excel(name="更新日期",width=15,format = "yyyy-MM-dd")
	private java.util.Date updateDate;
	/**所属部门*/
	@Excel(name="所属部门",width=15)
	private java.lang.String sysOrgCode;
	/**所属公司*/
	@Excel(name="所属公司",width=15)
	private java.lang.String sysCompanyCode;
	/**流程状态*/
	@Excel(name="流程状态",width=15)
	private java.lang.String bpmStatus;
	/**人员编码*/
	@Excel(name="人员编码",width=15)
	private java.lang.String empCode;
	/**姓名*/
	@Excel(name="姓名",width=15)
	private java.lang.String empName;
	/**性别*/
	@Excel(name="性别",width=15)
	private java.lang.String sex;
	/**英文名*/
	@Excel(name="英文名",width=15)
	private java.lang.String empNameEn;
	/**工号*/
	@Excel(name="工号",width=15)
	private java.lang.String empNo;
	/**雇佣状态*/
	@Excel(name="雇佣状态",width=15)
	private java.lang.String empHireId;
	/**人员类别*/
	@Excel(name="人员类别",width=15)
	private java.lang.String personTypeId;
	/**行政部门*/
	@Excel(name="行政部门",width=15)
	private java.lang.String manaDeptId;
	/**班组*/
	@Excel(name="班组",width=15)
	private java.lang.String teamId;
	/**职等*/
	@Excel(name="职等",width=15)
	private java.lang.String gradeId;
	/**职务*/
	@Excel(name="职务",width=15)
	private java.lang.String dutyId;
	/**职级*/
	@Excel(name="职级",width=15)
	private java.lang.String rankId;
	/**职位*/
	@Excel(name="职位",width=15)
	private java.lang.String positionId;
	/**到职日期*/
	@Excel(name="到职日期",width=15,format = "yyyy-MM-dd")
	private java.util.Date entryDate;
	/**离职日期*/
	@Excel(name="离职日期",width=15,format = "yyyy-MM-dd")
	private java.util.Date leaveDate;
	/**证件类型*/
	@Excel(name="证件类型",width=15)
	private java.lang.String identityType;
	/**证件号码*/
	@Excel(name="证件号码",width=15)
	private java.lang.String identityCard;
	/**出生日期*/
	@Excel(name="出生日期",width=15,format = "yyyy-MM-dd")
	private java.util.Date birthDate;
	/**是否操作员*/
	@Excel(name="是否操作员",width=15)
	private java.lang.String isOperator;
	/**对应操作员*/
	@Excel(name="对应操作员",width=15)
	private java.lang.String operatorId;
	/**办公电话*/
	@Excel(name="办公电话",width=15)
	private java.lang.String officePhone;
	/**内线电话*/
	@Excel(name="内线电话",width=15)
	private java.lang.String innerPhone;
	/**工位*/
	@Excel(name="工位",width=15)
	private java.lang.String workPosition;
	/**邮政编码*/
	@Excel(name="邮政编码",width=15)
	private java.lang.String zipCode;
	/**email地址*/
	@Excel(name="email地址",width=15)
	private java.lang.String email;
	/**手机号*/
	@Excel(name="手机号",width=15)
	private java.lang.String empPhone;
	/**微信号*/
	@Excel(name="微信号",width=15)
	private java.lang.String wechatNum;
	/**QQ号*/
	@Excel(name="QQ号",width=15)
	private java.lang.String qqNum;
	/**个人网址*/
	@Excel(name="个人网址",width=15)
	private java.lang.String personalUrl;
	/**家庭电话*/
	@Excel(name="家庭电话",width=15)
	private java.lang.String homePhone;
	/**传真*/
	@Excel(name="传真",width=15)
	private java.lang.String empFax;
	/**家庭住址*/
	@Excel(name="家庭住址",width=15)
	private java.lang.String homeAddress;
	/**现居住地址*/
	@Excel(name="现居住地址",width=15)
	private java.lang.String nowAddress;
	/**通讯地址*/
	@Excel(name="通讯地址",width=15)
	private java.lang.String postalAddress;
	/**证件签发机关*/
	@Excel(name="证件签发机关",width=15)
	private java.lang.String idIssued;
	/**是否长期身份证*/
	@Excel(name="是否长期身份证",width=15)
	private java.lang.String isLongCard;
	/**证件期限开始日期*/
	@Excel(name="证件期限开始日期",width=15,format = "yyyy-MM-dd")
	private java.util.Date cardStaerDate;
	/**证件期限截止日期*/
	@Excel(name="证件期限截止日期",width=15,format = "yyyy-MM-dd")
	private java.util.Date cardEndDate;
	/**进入本行业时间*/
	@Excel(name="进入本行业时间",width=15,format = "yyyy-MM-dd")
	private java.util.Date entryIndustryDate;
	/**参加工作时间*/
	@Excel(name="参加工作时间",width=15,format = "yyyy-MM-dd")
	private java.util.Date workDate;
	/**试用开始时间*/
	@Excel(name="试用开始时间",width=15,format = "yyyy-MM-dd")
	private java.util.Date trialStartDate;
	/**试用期限*/
	@Excel(name="试用期限",width=15)
	private java.lang.String probationPeriod;
	/**转正日期*/
	@Excel(name="转正日期",width=15,format = "yyyy-MM-dd")
	private java.util.Date positiveDate;
	/**户籍*/
	@Excel(name="户籍",width=15)
	private java.lang.String houseId;
	/**籍贯*/
	@Excel(name="籍贯",width=15)
	private java.lang.String nativeId;
	/**婚姻状况*/
	@Excel(name="婚姻状况",width=15)
	private java.lang.String maritalId;
	/**民族*/
	@Excel(name="民族",width=15)
	private java.lang.String nationId;
	/**社会保障号*/
	@Excel(name="社会保障号",width=15)
	private java.lang.String empSsn;
	/**健康状况*/
	@Excel(name="健康状况",width=15)
	private java.lang.String healthId;
	/**从业情况*/
	@Excel(name="从业情况",width=15)
	private java.lang.String workCondId;
	/**曾用名*/
	@Excel(name="曾用名",width=15)
	private java.lang.String beforeName;
	/**是否试用人员*/
	@Excel(name="是否试用人员",width=15)
	private java.lang.String isProbat;
	/**用工形式*/
	@Excel(name="用工形式",width=15)
	private java.lang.String workFormId;
	/**司龄*/
	@Excel(name="司龄",width=15)
	private java.lang.String compAge;
	/**工龄*/
	@Excel(name="工龄",width=15)
	private java.lang.String workYears;
	/**户口性质*/
	@Excel(name="户口性质",width=15)
	private java.lang.String householdNatureId;
	/**离职类别*/
	@Excel(name="离职类别",width=15)
	private java.lang.String levelTypeId;
	/**离职原因*/
	@Excel(name="离职原因",width=15)
	private java.lang.String levelReason;
	/**政治面貌*/
	@Excel(name="政治面貌",width=15)
	private java.lang.String politivalStatus;
	/**照片*/
	@Excel(name="照片",width=15)
	private java.lang.String photo;
	/**预留字段1*/
	@Excel(name="预留字段1",width=15)
	private java.lang.String vdef1;
	/**预留字段2*/
	@Excel(name="预留字段2",width=15)
	private java.lang.String vdef2;
	/**预留字段3*/
	@Excel(name="预留字段3",width=15)
	private java.lang.String vdef3;
	/**预留字段4*/
	@Excel(name="预留字段4",width=15)
	private java.lang.String vdef4;
	/**预留字段5*/
	@Excel(name="预留字段5",width=15)
	private java.lang.String vdef5;
	/**预留字段6*/
	@Excel(name="预留字段6",width=15)
	private java.lang.String vdef6;
	/**预留字段7*/
	@Excel(name="预留字段7",width=15)
	private java.lang.String vdef7;
	/**预留字段8*/
	@Excel(name="预留字段8",width=15)
	private java.lang.String vdef8;
	/**预留字段9*/
	@Excel(name="预留字段9",width=15)
	private java.lang.String vdef9;
	/**预留字段10*/
	@Excel(name="预留字段10",width=15)
	private java.lang.String vdef10;
	/**预留字段11*/
	@Excel(name="预留字段11",width=15)
	private java.lang.String vdef11;
	/**预留字段12*/
	@Excel(name="预留字段12",width=15)
	private java.lang.String vdef12;
	/**预留字段13*/
	@Excel(name="预留字段13",width=15)
	private java.lang.String vde13;
	/**预留字段14*/
	@Excel(name="预留字段14",width=15)
	private java.lang.String vdef14;
	/**预留字段15*/
	@Excel(name="预留字段15",width=15)
	private java.lang.String vdef15;
	/**开户银行*/
	@Excel(name="开户银行",width=15)
	private java.lang.String openBank;
	/**银行*/
	@Excel(name="银行",width=15)
	private java.lang.String bank;
	/**银行卡号*/
	@Excel(name="银行卡号",width=15)
	private java.lang.String bankCard;
	/**单据号*/
	@Excel(name="单据号",width=15)
	private java.lang.String billCode;
	
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

	@Column(name ="UPDATE_DATE",nullable=true)
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
	 *@return: java.lang.String  人员编码
	 */

	@Column(name ="EMP_CODE",nullable=false,length=64)
	public java.lang.String getEmpCode(){
		return this.empCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  人员编码
	 */
	public void setEmpCode(java.lang.String empCode){
		this.empCode = empCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  姓名
	 */

	@Column(name ="EMP_NAME",nullable=false,length=100)
	public java.lang.String getEmpName(){
		return this.empName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  姓名
	 */
	public void setEmpName(java.lang.String empName){
		this.empName = empName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  性别
	 */

	@Column(name ="SEX",nullable=false,length=2)
	public java.lang.String getSex(){
		return this.sex;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  性别
	 */
	public void setSex(java.lang.String sex){
		this.sex = sex;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  英文名
	 */

	@Column(name ="EMP_NAME_EN",nullable=true,length=100)
	public java.lang.String getEmpNameEn(){
		return this.empNameEn;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  英文名
	 */
	public void setEmpNameEn(java.lang.String empNameEn){
		this.empNameEn = empNameEn;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  工号
	 */

	@Column(name ="EMP_NO",nullable=false,length=32)
	public java.lang.String getEmpNo(){
		return this.empNo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  工号
	 */
	public void setEmpNo(java.lang.String empNo){
		this.empNo = empNo;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  雇佣状态
	 */

	@Column(name ="EMP_HIRE_ID",nullable=true,length=32)
	public java.lang.String getEmpHireId(){
		return this.empHireId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  雇佣状态
	 */
	public void setEmpHireId(java.lang.String empHireId){
		this.empHireId = empHireId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  人员类别
	 */

	@Column(name ="PERSON_TYPE_ID",nullable=false,length=32)
	public java.lang.String getPersonTypeId(){
		return this.personTypeId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  人员类别
	 */
	public void setPersonTypeId(java.lang.String personTypeId){
		this.personTypeId = personTypeId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  行政部门
	 */

	@Column(name ="MANA_DEPT_ID",nullable=true,length=32)
	public java.lang.String getManaDeptId(){
		return this.manaDeptId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  行政部门
	 */
	public void setManaDeptId(java.lang.String manaDeptId){
		this.manaDeptId = manaDeptId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  班组
	 */

	@Column(name ="TEAM_ID",nullable=true,length=32)
	public java.lang.String getTeamId(){
		return this.teamId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  班组
	 */
	public void setTeamId(java.lang.String teamId){
		this.teamId = teamId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  职等
	 */

	@Column(name ="GRADE_ID",nullable=true,length=32)
	public java.lang.String getGradeId(){
		return this.gradeId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  职等
	 */
	public void setGradeId(java.lang.String gradeId){
		this.gradeId = gradeId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  职务
	 */

	@Column(name ="DUTY_ID",nullable=true,length=32)
	public java.lang.String getDutyId(){
		return this.dutyId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  职务
	 */
	public void setDutyId(java.lang.String dutyId){
		this.dutyId = dutyId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  职级
	 */

	@Column(name ="RANK_ID",nullable=true,length=32)
	public java.lang.String getRankId(){
		return this.rankId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  职级
	 */
	public void setRankId(java.lang.String rankId){
		this.rankId = rankId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  职位
	 */

	@Column(name ="POSITION_ID",nullable=true,length=32)
	public java.lang.String getPositionId(){
		return this.positionId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  职位
	 */
	public void setPositionId(java.lang.String positionId){
		this.positionId = positionId;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  到职日期
	 */

	@Column(name ="ENTRY_DATE",nullable=true)
	public java.util.Date getEntryDate(){
		return this.entryDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  到职日期
	 */
	public void setEntryDate(java.util.Date entryDate){
		this.entryDate = entryDate;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  离职日期
	 */

	@Column(name ="LEAVE_DATE",nullable=true)
	public java.util.Date getLeaveDate(){
		return this.leaveDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  离职日期
	 */
	public void setLeaveDate(java.util.Date leaveDate){
		this.leaveDate = leaveDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  证件类型
	 */

	@Column(name ="IDENTITY_TYPE",nullable=false,length=32)
	public java.lang.String getIdentityType(){
		return this.identityType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  证件类型
	 */
	public void setIdentityType(java.lang.String identityType){
		this.identityType = identityType;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  证件号码
	 */

	@Column(name ="IDENTITY_CARD",nullable=false,length=32)
	public java.lang.String getIdentityCard(){
		return this.identityCard;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  证件号码
	 */
	public void setIdentityCard(java.lang.String identityCard){
		this.identityCard = identityCard;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  出生日期
	 */

	@Column(name ="BIRTH_DATE",nullable=true)
	public java.util.Date getBirthDate(){
		return this.birthDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  出生日期
	 */
	public void setBirthDate(java.util.Date birthDate){
		this.birthDate = birthDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否操作员
	 */

	@Column(name ="IS_OPERATOR",nullable=true,length=2)
	public java.lang.String getIsOperator(){
		return this.isOperator;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否操作员
	 */
	public void setIsOperator(java.lang.String isOperator){
		this.isOperator = isOperator;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  对应操作员
	 */

	@Column(name ="OPERATOR_ID",nullable=true,length=32)
	public java.lang.String getOperatorId(){
		return this.operatorId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  对应操作员
	 */
	public void setOperatorId(java.lang.String operatorId){
		this.operatorId = operatorId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  办公电话
	 */

	@Column(name ="OFFICE_PHONE",nullable=true,length=20)
	public java.lang.String getOfficePhone(){
		return this.officePhone;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  办公电话
	 */
	public void setOfficePhone(java.lang.String officePhone){
		this.officePhone = officePhone;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  内线电话
	 */

	@Column(name ="INNER_PHONE",nullable=true,length=20)
	public java.lang.String getInnerPhone(){
		return this.innerPhone;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  内线电话
	 */
	public void setInnerPhone(java.lang.String innerPhone){
		this.innerPhone = innerPhone;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  工位
	 */

	@Column(name ="WORK_POSITION",nullable=true,length=20)
	public java.lang.String getWorkPosition(){
		return this.workPosition;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  工位
	 */
	public void setWorkPosition(java.lang.String workPosition){
		this.workPosition = workPosition;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  邮政编码
	 */

	@Column(name ="ZIP_CODE",nullable=true,length=10)
	public java.lang.String getZipCode(){
		return this.zipCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  邮政编码
	 */
	public void setZipCode(java.lang.String zipCode){
		this.zipCode = zipCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  email地址
	 */

	@Column(name ="EMAIL",nullable=true,length=50)
	public java.lang.String getEmail(){
		return this.email;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  email地址
	 */
	public void setEmail(java.lang.String email){
		this.email = email;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  手机号
	 */

	@Column(name ="EMP_PHONE",nullable=true,length=32)
	public java.lang.String getEmpPhone(){
		return this.empPhone;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  手机号
	 */
	public void setEmpPhone(java.lang.String empPhone){
		this.empPhone = empPhone;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  微信号
	 */

	@Column(name ="WECHAT_NUM",nullable=true,length=32)
	public java.lang.String getWechatNum(){
		return this.wechatNum;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  微信号
	 */
	public void setWechatNum(java.lang.String wechatNum){
		this.wechatNum = wechatNum;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  QQ号
	 */

	@Column(name ="QQ_NUM",nullable=true,length=32)
	public java.lang.String getQqNum(){
		return this.qqNum;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  QQ号
	 */
	public void setQqNum(java.lang.String qqNum){
		this.qqNum = qqNum;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  个人网址
	 */

	@Column(name ="PERSONAL_URL",nullable=true,length=50)
	public java.lang.String getPersonalUrl(){
		return this.personalUrl;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  个人网址
	 */
	public void setPersonalUrl(java.lang.String personalUrl){
		this.personalUrl = personalUrl;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  家庭电话
	 */

	@Column(name ="HOME_PHONE",nullable=true,length=20)
	public java.lang.String getHomePhone(){
		return this.homePhone;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  家庭电话
	 */
	public void setHomePhone(java.lang.String homePhone){
		this.homePhone = homePhone;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  传真
	 */

	@Column(name ="EMP_FAX",nullable=true,length=50)
	public java.lang.String getEmpFax(){
		return this.empFax;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  传真
	 */
	public void setEmpFax(java.lang.String empFax){
		this.empFax = empFax;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  家庭住址
	 */

	@Column(name ="HOME_ADDRESS",nullable=true,length=1000)
	public java.lang.String getHomeAddress(){
		return this.homeAddress;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  家庭住址
	 */
	public void setHomeAddress(java.lang.String homeAddress){
		this.homeAddress = homeAddress;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  现居住地址
	 */

	@Column(name ="NOW_ADDRESS",nullable=true,length=1000)
	public java.lang.String getNowAddress(){
		return this.nowAddress;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  现居住地址
	 */
	public void setNowAddress(java.lang.String nowAddress){
		this.nowAddress = nowAddress;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  通讯地址
	 */

	@Column(name ="POSTAL_ADDRESS",nullable=true,length=1000)
	public java.lang.String getPostalAddress(){
		return this.postalAddress;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  通讯地址
	 */
	public void setPostalAddress(java.lang.String postalAddress){
		this.postalAddress = postalAddress;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  证件签发机关
	 */

	@Column(name ="ID_ISSUED",nullable=true,length=1000)
	public java.lang.String getIdIssued(){
		return this.idIssued;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  证件签发机关
	 */
	public void setIdIssued(java.lang.String idIssued){
		this.idIssued = idIssued;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否长期身份证
	 */

	@Column(name ="IS_LONG_CARD",nullable=true,length=2)
	public java.lang.String getIsLongCard(){
		return this.isLongCard;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否长期身份证
	 */
	public void setIsLongCard(java.lang.String isLongCard){
		this.isLongCard = isLongCard;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  证件期限开始日期
	 */

	@Column(name ="CARD_STAER_DATE",nullable=true)
	public java.util.Date getCardStaerDate(){
		return this.cardStaerDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  证件期限开始日期
	 */
	public void setCardStaerDate(java.util.Date cardStaerDate){
		this.cardStaerDate = cardStaerDate;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  证件期限截止日期
	 */

	@Column(name ="CARD_END_DATE",nullable=true)
	public java.util.Date getCardEndDate(){
		return this.cardEndDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  证件期限截止日期
	 */
	public void setCardEndDate(java.util.Date cardEndDate){
		this.cardEndDate = cardEndDate;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  进入本行业时间
	 */

	@Column(name ="ENTRY_INDUSTRY_DATE",nullable=true)
	public java.util.Date getEntryIndustryDate(){
		return this.entryIndustryDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  进入本行业时间
	 */
	public void setEntryIndustryDate(java.util.Date entryIndustryDate){
		this.entryIndustryDate = entryIndustryDate;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  参加工作时间
	 */

	@Column(name ="WORK_DATE",nullable=true)
	public java.util.Date getWorkDate(){
		return this.workDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  参加工作时间
	 */
	public void setWorkDate(java.util.Date workDate){
		this.workDate = workDate;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  试用开始时间
	 */

	@Column(name ="TRIAL_START_DATE",nullable=true)
	public java.util.Date getTrialStartDate(){
		return this.trialStartDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  试用开始时间
	 */
	public void setTrialStartDate(java.util.Date trialStartDate){
		this.trialStartDate = trialStartDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  试用期限
	 */

	@Column(name ="PROBATION_PERIOD",nullable=true,length=10)
	public java.lang.String getProbationPeriod(){
		return this.probationPeriod;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  试用期限
	 */
	public void setProbationPeriod(java.lang.String probationPeriod){
		this.probationPeriod = probationPeriod;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  转正日期
	 */

	@Column(name ="POSITIVE_DATE",nullable=true)
	public java.util.Date getPositiveDate(){
		return this.positiveDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  转正日期
	 */
	public void setPositiveDate(java.util.Date positiveDate){
		this.positiveDate = positiveDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  户籍
	 */

	@Column(name ="HOUSE_ID",nullable=true,length=32)
	public java.lang.String getHouseId(){
		return this.houseId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  户籍
	 */
	public void setHouseId(java.lang.String houseId){
		this.houseId = houseId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  籍贯
	 */

	@Column(name ="NATIVE_ID",nullable=true,length=32)
	public java.lang.String getNativeId(){
		return this.nativeId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  籍贯
	 */
	public void setNativeId(java.lang.String nativeId){
		this.nativeId = nativeId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  婚姻状况
	 */

	@Column(name ="MARITAL_ID",nullable=true,length=32)
	public java.lang.String getMaritalId(){
		return this.maritalId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  婚姻状况
	 */
	public void setMaritalId(java.lang.String maritalId){
		this.maritalId = maritalId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  民族
	 */

	@Column(name ="NATION_ID",nullable=true,length=32)
	public java.lang.String getNationId(){
		return this.nationId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  民族
	 */
	public void setNationId(java.lang.String nationId){
		this.nationId = nationId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  社会保障号
	 */

	@Column(name ="EMP_SSN",nullable=true,length=50)
	public java.lang.String getEmpSsn(){
		return this.empSsn;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  社会保障号
	 */
	public void setEmpSsn(java.lang.String empSsn){
		this.empSsn = empSsn;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  健康状况
	 */

	@Column(name ="HEALTH_ID",nullable=true,length=32)
	public java.lang.String getHealthId(){
		return this.healthId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  健康状况
	 */
	public void setHealthId(java.lang.String healthId){
		this.healthId = healthId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  从业情况
	 */

	@Column(name ="WORK_COND_ID",nullable=true,length=32)
	public java.lang.String getWorkCondId(){
		return this.workCondId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  从业情况
	 */
	public void setWorkCondId(java.lang.String workCondId){
		this.workCondId = workCondId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  曾用名
	 */

	@Column(name ="BEFORE_NAME",nullable=true,length=100)
	public java.lang.String getBeforeName(){
		return this.beforeName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  曾用名
	 */
	public void setBeforeName(java.lang.String beforeName){
		this.beforeName = beforeName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否试用人员
	 */

	@Column(name ="IS_PROBAT",nullable=true,length=2)
	public java.lang.String getIsProbat(){
		return this.isProbat;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否试用人员
	 */
	public void setIsProbat(java.lang.String isProbat){
		this.isProbat = isProbat;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  用工形式
	 */

	@Column(name ="WORK_FORM_ID",nullable=true,length=32)
	public java.lang.String getWorkFormId(){
		return this.workFormId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  用工形式
	 */
	public void setWorkFormId(java.lang.String workFormId){
		this.workFormId = workFormId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  司龄
	 */

	@Column(name ="COMP_AGE",nullable=true,length=10)
	public java.lang.String getCompAge(){
		return this.compAge;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  司龄
	 */
	public void setCompAge(java.lang.String compAge){
		this.compAge = compAge;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  工龄
	 */

	@Column(name ="WORK_YEARS",nullable=true,length=10)
	public java.lang.String getWorkYears(){
		return this.workYears;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  工龄
	 */
	public void setWorkYears(java.lang.String workYears){
		this.workYears = workYears;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  户口性质
	 */

	@Column(name ="HOUSEHOLD_NATURE_ID",nullable=true,length=32)
	public java.lang.String getHouseholdNatureId(){
		return this.householdNatureId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  户口性质
	 */
	public void setHouseholdNatureId(java.lang.String householdNatureId){
		this.householdNatureId = householdNatureId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  离职类别
	 */

	@Column(name ="LEVEL_TYPE_ID",nullable=true,length=32)
	public java.lang.String getLevelTypeId(){
		return this.levelTypeId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  离职类别
	 */
	public void setLevelTypeId(java.lang.String levelTypeId){
		this.levelTypeId = levelTypeId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  离职原因
	 */

	@Column(name ="LEVEL_REASON",nullable=true,length=1000)
	public java.lang.String getLevelReason(){
		return this.levelReason;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  离职原因
	 */
	public void setLevelReason(java.lang.String levelReason){
		this.levelReason = levelReason;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  政治面貌
	 */

	@Column(name ="POLITIVAL_STATUS",nullable=true,length=20)
	public java.lang.String getPolitivalStatus(){
		return this.politivalStatus;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  政治面貌
	 */
	public void setPolitivalStatus(java.lang.String politivalStatus){
		this.politivalStatus = politivalStatus;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  照片
	 */

	@Column(name ="PHOTO",nullable=true,length=100)
	public java.lang.String getPhoto(){
		return this.photo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  照片
	 */
	public void setPhoto(java.lang.String photo){
		this.photo = photo;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预留字段1
	 */

	@Column(name ="VDEF1",nullable=true,length=32)
	public java.lang.String getVdef1(){
		return this.vdef1;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预留字段1
	 */
	public void setVdef1(java.lang.String vdef1){
		this.vdef1 = vdef1;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预留字段2
	 */

	@Column(name ="VDEF2",nullable=true,length=32)
	public java.lang.String getVdef2(){
		return this.vdef2;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预留字段2
	 */
	public void setVdef2(java.lang.String vdef2){
		this.vdef2 = vdef2;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预留字段3
	 */

	@Column(name ="VDEF3",nullable=true,length=32)
	public java.lang.String getVdef3(){
		return this.vdef3;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预留字段3
	 */
	public void setVdef3(java.lang.String vdef3){
		this.vdef3 = vdef3;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预留字段4
	 */

	@Column(name ="VDEF4",nullable=true,length=32)
	public java.lang.String getVdef4(){
		return this.vdef4;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预留字段4
	 */
	public void setVdef4(java.lang.String vdef4){
		this.vdef4 = vdef4;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预留字段5
	 */

	@Column(name ="VDEF5",nullable=true,length=32)
	public java.lang.String getVdef5(){
		return this.vdef5;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预留字段5
	 */
	public void setVdef5(java.lang.String vdef5){
		this.vdef5 = vdef5;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预留字段6
	 */

	@Column(name ="VDEF6",nullable=true,length=32)
	public java.lang.String getVdef6(){
		return this.vdef6;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预留字段6
	 */
	public void setVdef6(java.lang.String vdef6){
		this.vdef6 = vdef6;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预留字段7
	 */

	@Column(name ="VDEF7",nullable=true,length=32)
	public java.lang.String getVdef7(){
		return this.vdef7;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预留字段7
	 */
	public void setVdef7(java.lang.String vdef7){
		this.vdef7 = vdef7;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预留字段8
	 */

	@Column(name ="VDEF8",nullable=true,length=32)
	public java.lang.String getVdef8(){
		return this.vdef8;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预留字段8
	 */
	public void setVdef8(java.lang.String vdef8){
		this.vdef8 = vdef8;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预留字段9
	 */

	@Column(name ="VDEF9",nullable=true,length=32)
	public java.lang.String getVdef9(){
		return this.vdef9;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预留字段9
	 */
	public void setVdef9(java.lang.String vdef9){
		this.vdef9 = vdef9;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预留字段10
	 */

	@Column(name ="VDEF10",nullable=true,length=32)
	public java.lang.String getVdef10(){
		return this.vdef10;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预留字段10
	 */
	public void setVdef10(java.lang.String vdef10){
		this.vdef10 = vdef10;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预留字段11
	 */

	@Column(name ="VDEF11",nullable=true,length=32)
	public java.lang.String getVdef11(){
		return this.vdef11;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预留字段11
	 */
	public void setVdef11(java.lang.String vdef11){
		this.vdef11 = vdef11;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预留字段12
	 */

	@Column(name ="VDEF12",nullable=true,length=32)
	public java.lang.String getVdef12(){
		return this.vdef12;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预留字段12
	 */
	public void setVdef12(java.lang.String vdef12){
		this.vdef12 = vdef12;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预留字段13
	 */

	@Column(name ="VDE13",nullable=true,length=32)
	public java.lang.String getVde13(){
		return this.vde13;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预留字段13
	 */
	public void setVde13(java.lang.String vde13){
		this.vde13 = vde13;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预留字段14
	 */

	@Column(name ="VDEF14",nullable=true,length=32)
	public java.lang.String getVdef14(){
		return this.vdef14;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预留字段14
	 */
	public void setVdef14(java.lang.String vdef14){
		this.vdef14 = vdef14;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预留字段15
	 */

	@Column(name ="VDEF15",nullable=true,length=32)
	public java.lang.String getVdef15(){
		return this.vdef15;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  预留字段15
	 */
	public void setVdef15(java.lang.String vdef15){
		this.vdef15 = vdef15;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  开户银行
	 */

	@Column(name ="OPEN_BANK",nullable=true,length=32)
	public java.lang.String getOpenBank(){
		return this.openBank;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  开户银行
	 */
	public void setOpenBank(java.lang.String openBank){
		this.openBank = openBank;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  银行
	 */

	@Column(name ="BANK",nullable=true,length=32)
	public java.lang.String getBank(){
		return this.bank;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  银行
	 */
	public void setBank(java.lang.String bank){
		this.bank = bank;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  银行卡号
	 */

	@Column(name ="BANK_CARD",nullable=true,length=32)
	public java.lang.String getBankCard(){
		return this.bankCard;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  银行卡号
	 */
	public void setBankCard(java.lang.String bankCard){
		this.bankCard = bankCard;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  单据号
	 */

	@Column(name ="BILL_CODE",nullable=true,length=32)
	public java.lang.String getBillCode(){
		return this.billCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  单据号
	 */
	public void setBillCode(java.lang.String billCode){
		this.billCode = billCode;
	}
}
