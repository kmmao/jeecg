package cn.com.linkwide.ba.person.entity;
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
 * @Description: 人员信息
 * @author onlineGenerator
 * @date 2018-09-25 16:30:11
 * @version V1.0   
 *
 */
@Entity
@Table(name = "hr_person_main", schema = "")
@SuppressWarnings("serial")
public class HrPersonMainEntity implements java.io.Serializable {
	/**主键*/
	/**人员姓名*/
    //@Excel(name="人员id",width=15)
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
	/**人员编码*/
    @Excel(name="人员编码",width=15)
	private java.lang.String empCode;
	/**人员姓名*/
    @Excel(name="人员姓名",width=15)
	private java.lang.String empName;
	/**工号 */
    @Excel(name="工号",width=15)
	private java.lang.String empNo;
	/**行政部门*/
    @Excel(name="行政部门",width=15,dictTable ="t_s_depart",dicCode ="org_code",dicText ="departname")
	private java.lang.String deptCode;
	/**雇佣状态*/
    @Excel(name="雇佣状态",width=15,dicCode="empl_stat")
	private java.lang.String hireState;
	/**人员类别*/
    @Excel(name="人员类别",width=15,dicCode="empType")
	private java.lang.String empType;
	/**性别*/
    @Excel(name="性别",width=15,dicCode="u8Sex")
	private java.lang.String empSex;
	/**民族*/
    @Excel(name="民族",width=15,dicCode="nation")
	private java.lang.String empNation;
	/**证件类型*/
    @Excel(name="证件类型",width=15,dicCode="idType")
	private java.lang.String idType;
	/**证件号码*/
    @Excel(name="证件号码",width=15)
	private java.lang.String idNumber;
	/**出生日期*/
    @Excel(name="出生日期",width=15,format = "yyyy-MM-dd")
	private java.util.Date empBrith;
	/**年龄*/
    @Excel(name="年龄",width=15)
	private java.lang.String empAge;
	/**连续工龄*/
    @Excel(name="连续工龄",width=15)
	private java.lang.String contAge;
	/**司龄*/
    @Excel(name="司龄",width=15)
	private java.lang.String compAge;
	/**籍贯*/
    @Excel(name="籍贯",width=15)
	private java.lang.String nativePlace;
	/**户籍地址*/
    @Excel(name="户籍地址",width=15)
	private java.lang.String houseAddress;
	/**健康状况*/
    @Excel(name="健康状况",width=15,dicCode="health")
	private java.lang.String healthStatus;
	/**婚姻状况*/
    @Excel(name="婚姻状况",width=15,dicCode="marital")
	private java.lang.String maritalStatus;
	/**曾用名*/
    @Excel(name="曾用名",width=15)
	private java.lang.String beforeEmpName;
	/**外文姓名*/
    @Excel(name="外文姓名",width=15)
	private java.lang.String englishName;
	/**国籍*/
    @Excel(name="国籍",width=15)
	private java.lang.String empNationality;
	/**出生地*/
    @Excel(name="出生地",width=15)
	private java.lang.String placeBirth;
	/**专长*/
    @Excel(name="专长",width=15)
	private java.lang.String empSpecialty;
	/**爱好*/
    @Excel(name="爱好",width=15)
	private java.lang.String empHobby;
	/**入职日期*/
    @Excel(name="入职日期",width=15,format = "yyyy-MM-dd")
	private java.util.Date entryDate;
	/**参加工作日期*/
    @Excel(name="参加工作日期",width=15,format = "yyyy-MM-dd")
	private java.util.Date joinWorkDate;
	/**转正时间*/
    @Excel(name="转正时间",width=15,format = "yyyy-MM-dd")
	private java.util.Date turnDate;
	/**离职时间*/
    @Excel(name="离职时间",width=15,format = "yyyy-MM-dd")
	private java.util.Date leaveDate;
	/**离休时间*/
    @Excel(name="离休时间",width=15,format = "yyyy-MM-dd")
	private java.util.Date retireDate;
	/**退休时间*/
    @Excel(name="退休时间",width=15,format = "yyyy-MM-dd")
	private java.util.Date retiredDate;
	/**离退休备注*/
    @Excel(name="离退休备注",width=15)
	private java.lang.String retireNote;
	/**返聘开始时间*/
    @Excel(name="返聘开始时间",width=15,format = "yyyy-MM-dd")
	private java.util.Date restartBeginDate;
	/**返聘结束时间*/
    @Excel(name="返聘结束时间",width=15,format = "yyyy-MM-dd")
	private java.util.Date restartEndDate;
	/**返聘备注*/
    @Excel(name="返聘备注",width=15)
	private java.lang.String restartNote;
	/**去世时间*/
    @Excel(name="去世时间",width=15,format = "yyyy-MM-dd")
	private java.util.Date deathDate;
	/**去世地点*/
    @Excel(name="去世地点",width=15)
	private java.lang.String deathAddrss;
	/**去世备注*/
    @Excel(name="去世备注",width=15)
	private java.lang.String deathNote;
	/**去世类别*/
    @Excel(name="去世类别",width=15,dicCode="deathType")
	private java.lang.String deathType;
	/**移动电话*/
    @Excel(name="移动电话",width=15)
	private java.lang.String mobilePhone;
	/**办公电话*/
    @Excel(name="办公电话",width=15)
	private java.lang.String officePhone;
	/**电子邮箱*/
    @Excel(name="电子邮箱",width=15)
	private java.lang.String empEmail;
	/**微信号码*/
    @Excel(name="微信号码",width=15)
	private java.lang.String empWechat;
	/**QQ号码*/
    @Excel(name="QQ号码",width=15)
	private java.lang.String empQq;
	/**工位*/
    @Excel(name="工位",width=15)
	private java.lang.String empStation;
	/**通讯地址*/
    @Excel(name="通讯地址",width=15)
	private java.lang.String comAddress;
	/**家庭住址*/
    @Excel(name="家庭住址",width=15)
	private java.lang.String famliyAddrss;
	/**紧急联系人*/
    @Excel(name="紧急联系人",width=15)
	private java.lang.String emerContactPerson;
	/**紧急联系电话*/
    @Excel(name="紧急联系电话",width=15)
	private java.lang.String emerPersonPhone;
	/**住宅电话*/
    @Excel(name="住宅电话",width=15)
	private java.lang.String residentialPhone;
	/**职员身份*/
    @Excel(name="职员身份",width=15,dicCode="empIden")
	private java.lang.String staffIdentity;
	/**编制类别*/
    @Excel(name="编制类别",width=15)
	private java.lang.String compileType;
	/**岗位类别*/
    @Excel(name="岗位类别",width=15,dictTable ="hr_jobs_category_dict",dicCode ="jobs_category_code",dicText ="jobs_category_name")
	private java.lang.String jobsType;
	/**行政职务*/
    @Excel(name="行政职务",width=15,dictTable ="hr_politics_dict",dicCode ="politics_post_code",dicText ="politics_post_name")
	private java.lang.String manaPosition;
	/**职务级别*/
    @Excel(name="职务级别",width=15,dicCode="job_level")
	private java.lang.String positionLevel;
	/**职务等级*/
    @Excel(name="职务等级",width=15,dicCode="posGraDs")
	private java.lang.String positionGrade;
	/**岗位层级*/
    @Excel(name="岗位层级",width=15,dictTable ="hr_hierarchy_dict",dicCode ="hier_code",dicText ="hier_name")
	private java.lang.String jobsHier;
	/**岗位等级*/
    @Excel(name="岗位等级",width=15,dictTable ="hr_job_grade_dict",dicCode ="job_grade_code",dicText ="job_grade_name")
	private java.lang.String jobsLevel;
	/**岗位*/
    @Excel(name="岗位",width=15,dictTable ="hr_jobs_dict",dicCode ="job_code",dicText ="job_name")
	private java.lang.String jobsCode;
	/**工作证号*/
    @Excel(name="工作证号",width=15)
	private java.lang.String jobCard;
	/**来院时间*/
    @Excel(name="来院时间",width=15,format = "yyyy-MM-dd")
	private java.util.Date goSchoolDate;
	/**来院方式*/
    @Excel(name="来院方式",width=15,dictTable ="hr_go_school_way",dicCode ="way_code",dicText ="way_name")
	private java.lang.String comSchoolWay;
   
	/**来院时间*/
    @Excel(name="来校时间",width=15,format = "yyyy-MM-dd")
    private java.util.Date comSchoolDate;
    /**来院方式*/
    @Excel(name="来校方式",width=15,dictTable ="hr_go_school_way",dicCode ="way_code",dicText ="way_name")
    private java.lang.String goSchoolWay;
	/**科室类别*/
    @Excel(name="科室类别",width=15,dictTable ="hr_dept_type",dicCode ="dept_type_cdoe",dicText ="dept_type_name")
	private java.lang.String deptType;
	/**导师类别*/
    @Excel(name="导师类别",width=15,dicCode="mentorType")
	private java.lang.String teacherType;
	/**评定时间*/
    @Excel(name="评定时间",width=15,format = "yyyy-MM-dd")
	private java.util.Date evaluaDate;
	/**学科门类*/
    @Excel(name="学科门类",width=15)
	private java.lang.String studyDis;
	/**一级学科*/
    @Excel(name="一级学科",width=15)
	private java.lang.String oneSubject;
	/**二级学科*/
    @Excel(name="二级学科",width=15)
	private java.lang.String twoSubject;
	/**三级学科*/
    @Excel(name="三级学科",width=15)
	private java.lang.String threeSubject;
	/**研究方向*/
    @Excel(name="研究方向",width=15,dicCode="stuDirect")
	private java.lang.String researchDirect;
	/**档案号*/
    @Excel(name="档案号",width=15)
	private java.lang.String fileNo;
	/**材料序号*/
    @Excel(name="材料序号",width=15)
	private java.lang.String materialNumber;
	/**人员性质*/
    @Excel(name="人员性质",width=15,dictTable="hr_person_type",dicCode="type_Code",dicText="type_Name")
	private java.lang.String empNature;
	/**教师类别*/
    @Excel(name="教师类别",width=15,dicCode="techerType")
	private java.lang.String teacherNature;
	/**离职类别*/
    @Excel(name="离职类别",width=15,dicCode="leaveType")
	private java.lang.String leaveNature;
	/**离职原因*/
    @Excel(name="离职原因",width=15)
	private java.lang.String leaveReason;
	/**职务名称*/
    @Excel(name="职务名称",width=15,dictTable="hr_pos_name",dicCode="pos_Code",dicText="pos_Name")
	private java.lang.String positionName;
	/**职务级别导师*/
    @Excel(name="职务级别导师",width=15,dicCode="job_level")
	private java.lang.String positionLevelDs;
	/**职务等级导师*/
    @Excel(name="职务等级导师",width=15,dicCode="posGraDs")
	private java.lang.String positionGradeDs;
	/**职业类别*/
    @Excel(name="职业类别",width=15,dicCode="profeType")
	private java.lang.String professType;
	/**教学资质*/
    @Excel(name="教学资质",width=15,dicCode="teachQuali")
	private java.lang.String teachQuali;
	/**教师职称*/
    @Excel(name="教师职称",width=15,dicCode="techerPost")
	private java.lang.String teacherTitle;
	/**教师职称级别*/
    @Excel(name="教师职称级别",width=15,dicCode="tePoGrade")
	private java.lang.String teacherTitleLevel;
	/**专业技术职务名称*/
    @Excel(name="专业技术职务名称",width=15,dicCode="professna")
	private java.lang.String professSciencePosName;
	/**专业技术职称级别*/
    @Excel(name="专业技术职称级别",width=15,dicCode="professle")
	private java.lang.String professScienceTitleLevel;
	/**工人职称*/
    @Excel(name="工人职称",width=15,dicCode="workerPost")
	private java.lang.String workerTitle;
    /**工人职称等级 */
    @Excel(name="工人职称级别",width=15,dicCode="professle")
    private java.lang.String workLevel;
	/**所属部门*/
    @Excel(name="所属部门",width=15,dictTable="t_s_depart",dicCode="org_Code",dicText="departname")
	private java.lang.String belongDept;
	/**是否操作员*/
    @Excel(name="是否操作员",width=15,dicCode="sf_yn")
	private java.lang.String isOperator;
    /**是否操作员*/
    @Excel(name="是否转正",width=15,dicCode="sf_yn")
    private java.lang.String isTrun;
	/**用户*/
    @Excel(name="用户",width=15)
	private java.lang.String userId;
    /**用户*/
    @Excel(name="最高学历",width=15,dicCode="education")
    private java.lang.String isTopEdu;
    /**用户*/
    @Excel(name="是否最高学位",width=15,dicCode="degree")
    private java.lang.String isTopDegree;
	/**预留字段1   审核状态：0未审核，1已审核*/
	private java.lang.String v1;
	/**预留字段2  入职标识：rz*/
	private java.lang.String v2;
	/**居民性质*/
	@Excel(name="居民性质",width=15,dicCode="resident")
	private java.lang.String v3;
	/**预留字段4*/
	@Excel(name="院区",width=15,dicCode="court")
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
	//科室名称
	private java.lang.String deptName;
	
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
	 *@return: java.lang.String  人员编码
	 */
	
	@Column(name ="EMP_CODE",nullable=false,length=32)
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
	 *@return: java.lang.String  人员姓名
	 */
	
	@Column(name ="EMP_NAME",nullable=false,length=32)
	public java.lang.String getEmpName(){
		return this.empName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  人员姓名
	 */
	public void setEmpName(java.lang.String empName){
		this.empName = empName;
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
	 *@return: java.lang.String  行政部门
	 */
	
	@Column(name ="DEPT_CODE",nullable=false,length=32)
	public java.lang.String getDeptCode(){
		return this.deptCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  行政部门
	 */
	public void setDeptCode(java.lang.String deptCode){
		this.deptCode = deptCode;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  雇佣状态
	 */
	
	@Column(name ="HIRE_STATE",nullable=false,length=32)
	public java.lang.String getHireState(){
		return this.hireState;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  雇佣状态
	 */
	public void setHireState(java.lang.String hireState){
		this.hireState = hireState;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  人员类别
	 */
	
	@Column(name ="EMP_TYPE",nullable=false,length=32)
	public java.lang.String getEmpType(){
		return this.empType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  人员类别
	 */
	public void setEmpType(java.lang.String empType){
		this.empType = empType;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  性别
	 */
	
	@Column(name ="EMP_SEX",nullable=false,length=32)
	public java.lang.String getEmpSex(){
		return this.empSex;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  性别
	 */
	public void setEmpSex(java.lang.String empSex){
		this.empSex = empSex;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  民族
	 */
	
	@Column(name ="EMP_NATION",nullable=true,length=32)
	public java.lang.String getEmpNation(){
		return this.empNation;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  民族
	 */
	public void setEmpNation(java.lang.String empNation){
		this.empNation = empNation;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  证件类型
	 */
	
	@Column(name ="ID_TYPE",nullable=false,length=32)
	public java.lang.String getIdType(){
		return this.idType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  证件类型
	 */
	public void setIdType(java.lang.String idType){
		this.idType = idType;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  证件号码
	 */
	
	@Column(name ="ID_NUMBER",nullable=false,length=32)
	public java.lang.String getIdNumber(){
		return this.idNumber;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  证件号码
	 */
	public void setIdNumber(java.lang.String idNumber){
		this.idNumber = idNumber;
	}
	
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  出生日期
	 */
	
	@Column(name ="EMP_BRITH",nullable=true,length=32)
	public java.util.Date getEmpBrith(){
		return this.empBrith;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  出生日期
	 */
	public void setEmpBrith(java.util.Date empBrith){
		this.empBrith = empBrith;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  年龄
	 */
	
	@Column(name ="EMP_AGE",nullable=true,length=32)
	public java.lang.String getEmpAge(){
		return this.empAge;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  年龄
	 */
	public void setEmpAge(java.lang.String empAge){
		this.empAge = empAge;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  连续工龄
	 */
	
	@Column(name ="CONT_AGE",nullable=true,length=32)
	public java.lang.String getContAge(){
		return this.contAge;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  连续工龄
	 */
	public void setContAge(java.lang.String contAge){
		this.contAge = contAge;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  司龄
	 */
	
	@Column(name ="COMP_AGE",nullable=true,length=32)
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
	 *@return: java.lang.String  籍贯
	 */
	
	@Column(name ="NATIVE_PLACE",nullable=true,length=32)
	public java.lang.String getNativePlace(){
		return this.nativePlace;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  籍贯
	 */
	public void setNativePlace(java.lang.String nativePlace){
		this.nativePlace = nativePlace;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  户籍地址
	 */
	
	@Column(name ="HOUSE_ADDRESS",nullable=true,length=32)
	public java.lang.String getHouseAddress(){
		return this.houseAddress;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  户籍地址
	 */
	public void setHouseAddress(java.lang.String houseAddress){
		this.houseAddress = houseAddress;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  健康状况
	 */
	
	@Column(name ="HEALTH_STATUS",nullable=true,length=32)
	public java.lang.String getHealthStatus(){
		return this.healthStatus;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  健康状况
	 */
	public void setHealthStatus(java.lang.String healthStatus){
		this.healthStatus = healthStatus;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  婚姻状况
	 */
	
	@Column(name ="MARITAL_STATUS",nullable=true,length=32)
	public java.lang.String getMaritalStatus(){
		return this.maritalStatus;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  婚姻状况
	 */
	public void setMaritalStatus(java.lang.String maritalStatus){
		this.maritalStatus = maritalStatus;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  曾用名
	 */
	
	@Column(name ="BEFORE_EMP_NAME",nullable=true,length=32)
	public java.lang.String getBeforeEmpName(){
		return this.beforeEmpName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  曾用名
	 */
	public void setBeforeEmpName(java.lang.String beforeEmpName){
		this.beforeEmpName = beforeEmpName;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  外文姓名
	 */
	
	@Column(name ="ENGLISH_NAME",nullable=true,length=32)
	public java.lang.String getEnglishName(){
		return this.englishName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  外文姓名
	 */
	public void setEnglishName(java.lang.String englishName){
		this.englishName = englishName;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  国籍
	 */
	
	@Column(name ="EMP_NATIONALITY",nullable=true,length=32)
	public java.lang.String getEmpNationality(){
		return this.empNationality;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  国籍
	 */
	public void setEmpNationality(java.lang.String empNationality){
		this.empNationality = empNationality;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  出生地
	 */
	
	@Column(name ="PLACE_BIRTH",nullable=true,length=32)
	public java.lang.String getPlaceBirth(){
		return this.placeBirth;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  出生地
	 */
	public void setPlaceBirth(java.lang.String placeBirth){
		this.placeBirth = placeBirth;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  专长
	 */
	
	@Column(name ="EMP_SPECIALTY",nullable=true,length=32)
	public java.lang.String getEmpSpecialty(){
		return this.empSpecialty;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  专长
	 */
	public void setEmpSpecialty(java.lang.String empSpecialty){
		this.empSpecialty = empSpecialty;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  爱好
	 */
	
	@Column(name ="EMP_HOBBY",nullable=true,length=32)
	public java.lang.String getEmpHobby(){
		return this.empHobby;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  爱好
	 */
	public void setEmpHobby(java.lang.String empHobby){
		this.empHobby = empHobby;
	}
	
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  入职日期
	 */
	
	@Column(name ="ENTRY_DATE",nullable=true,length=32)
	public java.util.Date getEntryDate(){
		return this.entryDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  入职日期
	 */
	public void setEntryDate(java.util.Date entryDate){
		this.entryDate = entryDate;
	}
	
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  参加工作日期
	 */
	
	@Column(name ="JOIN_WORK_DATE",nullable=true,length=32)
	public java.util.Date getJoinWorkDate(){
		return this.joinWorkDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  参加工作日期
	 */
	public void setJoinWorkDate(java.util.Date joinWorkDate){
		this.joinWorkDate = joinWorkDate;
	}
	
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  转正时间
	 */
	
	@Column(name ="TURN_DATE",nullable=true,length=32)
	public java.util.Date getTurnDate(){
		return this.turnDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  转正时间
	 */
	public void setTurnDate(java.util.Date turnDate){
		this.turnDate = turnDate;
	}
	
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  离职时间
	 */
	
	@Column(name ="LEAVE_DATE",nullable=true,length=32)
	public java.util.Date getLeaveDate(){
		return this.leaveDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  离职时间
	 */
	public void setLeaveDate(java.util.Date leaveDate){
		this.leaveDate = leaveDate;
	}
	
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  离休时间
	 */
	
	@Column(name ="RETIRE_DATE",nullable=true,length=32)
	public java.util.Date getRetireDate(){
		return this.retireDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  离休时间
	 */
	public void setRetireDate(java.util.Date retireDate){
		this.retireDate = retireDate;
	}
	
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  退休时间
	 */
	
	@Column(name ="RETIRED_DATE",nullable=true,length=32)
	public java.util.Date getRetiredDate(){
		return this.retiredDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  退休时间
	 */
	public void setRetiredDate(java.util.Date retiredDate){
		this.retiredDate = retiredDate;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  离退休备注
	 */
	
	@Column(name ="RETIRE_NOTE",nullable=true,length=100)
	public java.lang.String getRetireNote(){
		return this.retireNote;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  离退休备注
	 */
	public void setRetireNote(java.lang.String retireNote){
		this.retireNote = retireNote;
	}
	
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  返聘开始时间
	 */
	
	@Column(name ="RESTART_BEGIN_DATE",nullable=true,length=32)
	public java.util.Date getRestartBeginDate(){
		return this.restartBeginDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  返聘开始时间
	 */
	public void setRestartBeginDate(java.util.Date restartBeginDate){
		this.restartBeginDate = restartBeginDate;
	}
	
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  返聘结束时间
	 */
	
	@Column(name ="RESTART_END_DATE",nullable=true,length=32)
	public java.util.Date getRestartEndDate(){
		return this.restartEndDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  返聘结束时间
	 */
	public void setRestartEndDate(java.util.Date restartEndDate){
		this.restartEndDate = restartEndDate;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  返聘备注
	 */
	
	@Column(name ="RESTART_NOTE",nullable=true,length=100)
	public java.lang.String getRestartNote(){
		return this.restartNote;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  返聘备注
	 */
	public void setRestartNote(java.lang.String restartNote){
		this.restartNote = restartNote;
	}
	
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  去世时间
	 */
	
	@Column(name ="DEATH_DATE",nullable=true,length=32)
	public java.util.Date getDeathDate(){
		return this.deathDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  去世时间
	 */
	public void setDeathDate(java.util.Date deathDate){
		this.deathDate = deathDate;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  去世地点
	 */
	
	@Column(name ="DEATH_ADDRSS",nullable=true,length=32)
	public java.lang.String getDeathAddrss(){
		return this.deathAddrss;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  去世地点
	 */
	public void setDeathAddrss(java.lang.String deathAddrss){
		this.deathAddrss = deathAddrss;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  去世备注
	 */
	
	@Column(name ="DEATH_NOTE",nullable=true,length=32)
	public java.lang.String getDeathNote(){
		return this.deathNote;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  去世备注
	 */
	public void setDeathNote(java.lang.String deathNote){
		this.deathNote = deathNote;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  去世类别
	 */
	
	@Column(name ="DEATH_TYPE",nullable=true,length=32)
	public java.lang.String getDeathType(){
		return this.deathType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  去世类别
	 */
	public void setDeathType(java.lang.String deathType){
		this.deathType = deathType;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  移动电话
	 */
	
	@Column(name ="MOBILE_PHONE",nullable=true,length=32)
	public java.lang.String getMobilePhone(){
		return this.mobilePhone;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  移动电话
	 */
	public void setMobilePhone(java.lang.String mobilePhone){
		this.mobilePhone = mobilePhone;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  办公电话
	 */
	
	@Column(name ="OFFICE_PHONE",nullable=true,length=32)
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
	 *@return: java.lang.String  电子邮箱
	 */
	
	@Column(name ="EMP_EMAIL",nullable=true,length=32)
	public java.lang.String getEmpEmail(){
		return this.empEmail;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  电子邮箱
	 */
	public void setEmpEmail(java.lang.String empEmail){
		this.empEmail = empEmail;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  微信号码
	 */
	
	@Column(name ="EMP_WECHAT",nullable=true,length=32)
	public java.lang.String getEmpWechat(){
		return this.empWechat;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  微信号码
	 */
	public void setEmpWechat(java.lang.String empWechat){
		this.empWechat = empWechat;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  QQ号码
	 */
	
	@Column(name ="EMP_QQ",nullable=true,length=32)
	public java.lang.String getEmpQq(){
		return this.empQq;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  QQ号码
	 */
	public void setEmpQq(java.lang.String empQq){
		this.empQq = empQq;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  工位
	 */
	
	@Column(name ="EMP_STATION",nullable=true,length=32)
	public java.lang.String getEmpStation(){
		return this.empStation;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  工位
	 */
	public void setEmpStation(java.lang.String empStation){
		this.empStation = empStation;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  通讯地址
	 */
	
	@Column(name ="COM_ADDRESS",nullable=true,length=100)
	public java.lang.String getComAddress(){
		return this.comAddress;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  通讯地址
	 */
	public void setComAddress(java.lang.String comAddress){
		this.comAddress = comAddress;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  家庭住址
	 */
	
	@Column(name ="FAMLIY_ADDRSS",nullable=true,length=100)
	public java.lang.String getFamliyAddrss(){
		return this.famliyAddrss;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  家庭住址
	 */
	public void setFamliyAddrss(java.lang.String famliyAddrss){
		this.famliyAddrss = famliyAddrss;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  紧急联系人
	 */
	
	@Column(name ="EMER_CONTACT_PERSON",nullable=true,length=32)
	public java.lang.String getEmerContactPerson(){
		return this.emerContactPerson;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  紧急联系人
	 */
	public void setEmerContactPerson(java.lang.String emerContactPerson){
		this.emerContactPerson = emerContactPerson;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  紧急联系电话
	 */
	
	@Column(name ="EMER_PERSON_PHONE",nullable=true,length=32)
	public java.lang.String getEmerPersonPhone(){
		return this.emerPersonPhone;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  紧急联系电话
	 */
	public void setEmerPersonPhone(java.lang.String emerPersonPhone){
		this.emerPersonPhone = emerPersonPhone;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  住宅电话
	 */
	
	@Column(name ="RESIDENTIAL_PHONE",nullable=true,length=32)
	public java.lang.String getResidentialPhone(){
		return this.residentialPhone;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  住宅电话
	 */
	public void setResidentialPhone(java.lang.String residentialPhone){
		this.residentialPhone = residentialPhone;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  职员身份
	 */
	
	@Column(name ="STAFF_IDENTITY",nullable=true,length=32)
	public java.lang.String getStaffIdentity(){
		return this.staffIdentity;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  职员身份
	 */
	public void setStaffIdentity(java.lang.String staffIdentity){
		this.staffIdentity = staffIdentity;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  编制类别
	 */
	
	@Column(name ="COMPILE_TYPE",nullable=true,length=32)
	public java.lang.String getCompileType(){
		return this.compileType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  编制类别
	 */
	public void setCompileType(java.lang.String compileType){
		this.compileType = compileType;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  岗位类别
	 */
	
	@Column(name ="JOBS_TYPE",nullable=true,length=32)
	public java.lang.String getJobsType(){
		return this.jobsType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  岗位类别
	 */
	public void setJobsType(java.lang.String jobsType){
		this.jobsType = jobsType;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  行政职务
	 */
	
	@Column(name ="MANA_POSITION",nullable=true,length=32)
	public java.lang.String getManaPosition(){
		return this.manaPosition;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  行政职务
	 */
	public void setManaPosition(java.lang.String manaPosition){
		this.manaPosition = manaPosition;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  职务级别
	 */
	
	@Column(name ="POSITION_LEVEL",nullable=true,length=32)
	public java.lang.String getPositionLevel(){
		return this.positionLevel;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  职务级别
	 */
	public void setPositionLevel(java.lang.String positionLevel){
		this.positionLevel = positionLevel;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  职务等级
	 */
	
	@Column(name ="POSITION_GRADE",nullable=true,length=32)
	public java.lang.String getPositionGrade(){
		return this.positionGrade;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  职务等级
	 */
	public void setPositionGrade(java.lang.String positionGrade){
		this.positionGrade = positionGrade;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  岗位层级
	 */
	
	@Column(name ="JOBS_HIER",nullable=true,length=32)
	public java.lang.String getJobsHier(){
		return this.jobsHier;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  岗位层级
	 */
	public void setJobsHier(java.lang.String jobsHier){
		this.jobsHier = jobsHier;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  岗位等级
	 */
	
	@Column(name ="JOBS_LEVEL",nullable=true,length=32)
	public java.lang.String getJobsLevel(){
		return this.jobsLevel;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  岗位等级
	 */
	public void setJobsLevel(java.lang.String jobsLevel){
		this.jobsLevel = jobsLevel;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  岗位
	 */
	
	@Column(name ="JOBS_CODE",nullable=true,length=32)
	public java.lang.String getJobsCode(){
		return this.jobsCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  岗位
	 */
	public void setJobsCode(java.lang.String jobsCode){
		this.jobsCode = jobsCode;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  工作证号
	 */
	
	@Column(name ="JOB_CARD",nullable=true,length=32)
	public java.lang.String getJobCard(){
		return this.jobCard;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  工作证号
	 */
	public void setJobCard(java.lang.String jobCard){
		this.jobCard = jobCard;
	}
	
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  来院时间
	 */
	
	@Column(name ="GO_SCHOOL_DATE",nullable=true,length=32)
	public java.util.Date getGoSchoolDate(){
		return this.goSchoolDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  来院时间
	 */
	public void setGoSchoolDate(java.util.Date goSchoolDate){
		this.goSchoolDate = goSchoolDate;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  来院方式
	 */
	
	@Column(name ="GO_SCHOOL_WAY",nullable=true,length=32)
	public java.lang.String getGoSchoolWay(){
		return this.goSchoolWay;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  来院方式
	 */
	public void setGoSchoolWay(java.lang.String goSchoolWay){
		this.goSchoolWay = goSchoolWay;
	}
	@Column(name ="COM_SCHOOL_WAY",nullable=true,length=32)
	 public java.lang.String getComSchoolWay() {
			return comSchoolWay;
		}

		public void setComSchoolWay(java.lang.String comSchoolWay) {
			this.comSchoolWay = comSchoolWay;
		}
		@Column(name ="COM_SCHOOL_DATE",nullable=true,length=32)
		public java.util.Date getComSchoolDate() {
			return comSchoolDate;
		}

		public void setComSchoolDate(java.util.Date comSchoolDate) {
			this.comSchoolDate = comSchoolDate;
		}

	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  科室类别
	 */
	
	@Column(name ="DEPT_TYPE",nullable=true,length=32)
	public java.lang.String getDeptType(){
		return this.deptType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  科室类别
	 */
	public void setDeptType(java.lang.String deptType){
		this.deptType = deptType;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  导师类别
	 */
	
	@Column(name ="TEACHER_TYPE",nullable=true,length=32)
	public java.lang.String getTeacherType(){
		return this.teacherType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  导师类别
	 */
	public void setTeacherType(java.lang.String teacherType){
		this.teacherType = teacherType;
	}
	
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  评定时间
	 */
	
	@Column(name ="EVALUA_DATE",nullable=true,length=32)
	public java.util.Date getEvaluaDate(){
		return this.evaluaDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  评定时间
	 */
	public void setEvaluaDate(java.util.Date evaluaDate){
		this.evaluaDate = evaluaDate;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  学科门类
	 */
	
	@Column(name ="STUDY_DIS",nullable=true,length=32)
	public java.lang.String getStudyDis(){
		return this.studyDis;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  学科门类
	 */
	public void setStudyDis(java.lang.String studyDis){
		this.studyDis = studyDis;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  一级学科
	 */
	
	@Column(name ="ONE_SUBJECT",nullable=true,length=32)
	public java.lang.String getOneSubject(){
		return this.oneSubject;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  一级学科
	 */
	public void setOneSubject(java.lang.String oneSubject){
		this.oneSubject = oneSubject;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  二级学科
	 */
	
	@Column(name ="TWO_SUBJECT",nullable=true,length=32)
	public java.lang.String getTwoSubject(){
		return this.twoSubject;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  二级学科
	 */
	public void setTwoSubject(java.lang.String twoSubject){
		this.twoSubject = twoSubject;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  三级学科
	 */
	
	@Column(name ="THREE_SUBJECT",nullable=true,length=32)
	public java.lang.String getThreeSubject(){
		return this.threeSubject;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  三级学科
	 */
	public void setThreeSubject(java.lang.String threeSubject){
		this.threeSubject = threeSubject;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  研究方向
	 */
	
	@Column(name ="RESEARCH_DIRECT",nullable=true,length=32)
	public java.lang.String getResearchDirect(){
		return this.researchDirect;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  研究方向
	 */
	public void setResearchDirect(java.lang.String researchDirect){
		this.researchDirect = researchDirect;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  档案号
	 */
	
	@Column(name ="FILE_NO",nullable=true,length=32)
	public java.lang.String getFileNo(){
		return this.fileNo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  档案号
	 */
	public void setFileNo(java.lang.String fileNo){
		this.fileNo = fileNo;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  材料序号
	 */
	
	@Column(name ="MATERIAL_NUMBER",nullable=true,length=32)
	public java.lang.String getMaterialNumber(){
		return this.materialNumber;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  材料序号
	 */
	public void setMaterialNumber(java.lang.String materialNumber){
		this.materialNumber = materialNumber;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  人员性质
	 */
	
	@Column(name ="EMP_NATURE",nullable=true,length=32)
	public java.lang.String getEmpNature(){
		return this.empNature;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  人员性质
	 */
	public void setEmpNature(java.lang.String empNature){
		this.empNature = empNature;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  教师类别
	 */
	
	@Column(name ="TEACHER_NATURE",nullable=true,length=32)
	public java.lang.String getTeacherNature(){
		return this.teacherNature;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  教师类别
	 */
	public void setTeacherNature(java.lang.String teacherNature){
		this.teacherNature = teacherNature;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  离职类别
	 */
	
	@Column(name ="LEAVE_NATURE",nullable=true,length=32)
	public java.lang.String getLeaveNature(){
		return this.leaveNature;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  离职类别
	 */
	public void setLeaveNature(java.lang.String leaveNature){
		this.leaveNature = leaveNature;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  离职原因
	 */
	
	@Column(name ="LEAVE_REASON",nullable=true,length=32)
	public java.lang.String getLeaveReason(){
		return this.leaveReason;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  离职原因
	 */
	public void setLeaveReason(java.lang.String leaveReason){
		this.leaveReason = leaveReason;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  职务名称
	 */
	
	@Column(name ="POSITION_NAME",nullable=true,length=32)
	public java.lang.String getPositionName(){
		return this.positionName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  职务名称
	 */
	public void setPositionName(java.lang.String positionName){
		this.positionName = positionName;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  职务级别导师
	 */
	
	@Column(name ="POSITION_LEVEL_DS",nullable=true,length=32)
	public java.lang.String getPositionLevelDs(){
		return this.positionLevelDs;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  职务级别导师
	 */
	public void setPositionLevelDs(java.lang.String positionLevelDs){
		this.positionLevelDs = positionLevelDs;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  职务等级导师
	 */
	
	@Column(name ="POSITION_GRADE_DS",nullable=true,length=32)
	public java.lang.String getPositionGradeDs(){
		return this.positionGradeDs;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  职务等级导师
	 */
	public void setPositionGradeDs(java.lang.String positionGradeDs){
		this.positionGradeDs = positionGradeDs;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  职业类别
	 */
	
	@Column(name ="PROFESS_TYPE",nullable=true,length=32)
	public java.lang.String getProfessType(){
		return this.professType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  职业类别
	 */
	public void setProfessType(java.lang.String professType){
		this.professType = professType;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  教学资质
	 */
	
	@Column(name ="TEACH_QUALI",nullable=true,length=32)
	public java.lang.String getTeachQuali(){
		return this.teachQuali;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  教学资质
	 */
	public void setTeachQuali(java.lang.String teachQuali){
		this.teachQuali = teachQuali;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  教师职称
	 */
	
	@Column(name ="TEACHER_TITLE",nullable=true,length=32)
	public java.lang.String getTeacherTitle(){
		return this.teacherTitle;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  教师职称
	 */
	public void setTeacherTitle(java.lang.String teacherTitle){
		this.teacherTitle = teacherTitle;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  教师职称级别
	 */
	
	@Column(name ="TEACHER_TITLE_LEVEL",nullable=true,length=32)
	public java.lang.String getTeacherTitleLevel(){
		return this.teacherTitleLevel;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  教师职称级别
	 */
	public void setTeacherTitleLevel(java.lang.String teacherTitleLevel){
		this.teacherTitleLevel = teacherTitleLevel;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  专业技术职务名称
	 */
	
	@Column(name ="PROFESS_SCIENCE_POS_NAME",nullable=true,length=32)
	public java.lang.String getProfessSciencePosName(){
		return this.professSciencePosName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  专业技术职务名称
	 */
	public void setProfessSciencePosName(java.lang.String professSciencePosName){
		this.professSciencePosName = professSciencePosName;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  专业技术职称级别
	 */
	
	@Column(name ="PROFESS_SCIENCE_TITLE_LEVEL",nullable=true,length=32)
	public java.lang.String getProfessScienceTitleLevel(){
		return this.professScienceTitleLevel;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  专业技术职称级别
	 */
	public void setProfessScienceTitleLevel(java.lang.String professScienceTitleLevel){
		this.professScienceTitleLevel = professScienceTitleLevel;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  工人职称
	 */
	
	@Column(name ="WORKER_TITLE",nullable=true,length=32)
	public java.lang.String getWorkerTitle(){
		return this.workerTitle;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  工人职称
	 */
	public void setWorkerTitle(java.lang.String workerTitle){
		this.workerTitle = workerTitle;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  所属部门
	 */
	
	@Column(name ="BELONG_DEPT",nullable=true,length=32)
	public java.lang.String getBelongDept(){
		return this.belongDept;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  所属部门
	 */
	public void setBelongDept(java.lang.String belongDept){
		this.belongDept = belongDept;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否操作员
	 */
	
	@Column(name ="IS_OPERATOR",nullable=true,length=32)
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
	 *@return: java.lang.String  用户
	 */
	
	@Column(name ="USER_ID",nullable=true,length=32)
	public java.lang.String getUserId(){
		return this.userId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  用户
	 */
	
	public void setUserId(java.lang.String userId){
		this.userId = userId;
	}
	@Column(name ="WORK_LEVEL",nullable=true,length=32)
	public java.lang.String getWorkLevel() {
		return workLevel;
	}

	public void setWorkLevel(java.lang.String workLevel) {
		this.workLevel = workLevel;
	}
	@Column(name ="IS_TOP_EDU",nullable=true,length=32)
	public java.lang.String getIsTopEdu() {
		return isTopEdu;
	}

	public void setIsTopEdu(java.lang.String isTopEdu) {
		this.isTopEdu = isTopEdu;
	}
	@Column(name ="IS_TOP_DEGREE",nullable=true,length=100)
	public java.lang.String getIsTopDegree() {
		return isTopDegree;
	}

	public void setIsTopDegree(java.lang.String isTopDegree) {
		this.isTopDegree = isTopDegree;
	}

	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预留字段1
	 */
	
	@Column(name ="V1",nullable=true,length=32)
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
	
	@Column(name ="V2",nullable=true,length=32)
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
	 *@return: java.lang.String  居民性质
	 */
	
	@Column(name ="V3",nullable=true,length=32)
	public java.lang.String getV3(){
		return this.v3;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  居民性质
	 */
	public void setV3(java.lang.String v3){
		this.v3 = v3;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  预留字段4
	 */
	
	@Column(name ="V4",nullable=true,length=32)
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
	
	@Column(name ="V5",nullable=true,length=32)
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
	
	@Column(name ="V6",nullable=true,length=32)
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
	
	@Column(name ="V7",nullable=true,length=32)
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
	
	@Column(name ="V8",nullable=true,length=32)
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
	
	@Column(name ="V9",nullable=true,length=32)
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
	
	@Column(name ="V10",nullable=true,length=32)
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
	@Column(name ="is_trun",nullable=true,length=32)
	public java.lang.String getIsTrun() {
		return isTrun;
	}

	public void setIsTrun(java.lang.String isTrun) {
		this.isTrun = isTrun;
	}

	@Transient
	public java.lang.String getDeptName() {
		return deptName;
	}

	public void setDeptName(java.lang.String deptName) {
		this.deptName = deptName;
	}
	
	
	
}
