package com.buss.tdm.task.proxy.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

@Entity
@Table(name="b_task_proxy", schema="")
public class BTaskProxyEntity
  implements Serializable
{
  private static final long serialVersionUID = 123456789L;
  @Excel(name="预留字段17")
  private String extend17;
  @Excel(name="预留字段18")
  private String extend18;
  @Excel(name="预留字段19")
  private String extend19;
  @Excel(name="预留字段20")
  private String extend20;
  private String id;
  @Excel(name="任务编号")
  private String billNo;
  @Excel(name="试验来源")
  private String taskSource;
  @Excel(name="试验级别")
  private String taskLevel;
  @Excel(name="产品型号")
  private String produceId;
  private String produceName;
  private String produceModel;
  @Excel(name="系统名称")
  private String systemId;
  private String systemName;
  private String systemModel;
  private String systemModelOrproduceModel;
  private String systemOrProductName;
  private String exeProject;
  @Excel(name="试验类型")
  private String taskType;
  @Excel(name="审批流程")
  private String workflowId;
  @Excel(name="委托单位")
  private String proxyUnit;
  @Excel(name="承试单位")
  private String testUnit;
  @Excel(name="任务负责人")
  private String userId;
  private String userName;
  @Excel(name="单据状态")
  private String billStatus;
  @Excel(name="任务状态")
  private String taskStatus;
  private String taskNum;
  @Excel(name="计划开始日期", format="yyyy-MM-dd")
  private Date begainData;
  @Excel(name="计划结束日期", format="yyyy-MM-dd")
  private Date endData;
  @Excel(name="实际开始日期", format="yyyy-MM-dd")
  private Date realBegainData;
  @Excel(name="实际结束日期", format="yyyy-MM-dd")
  private Date realEndData;
  @Excel(name="备注")
  private String note;
  @Excel(name="创建时间", format="yyyy-MM-dd")
  private Date createDate;
  @Excel(name="创建人")
  private String createBy;
  @Excel(name="审核人")
  private String auditBy;
  @Excel(name="审核批语")
  private String auditNote;
  @Excel(name="审核日期", format="yyyy-MM-dd")
  private Date auditDate;
  @Excel(name="修改时间", format="yyyy-MM-dd")
  private Date updateDate;
  @Excel(name="修改人")
  private String updateBy;
  @Excel(name="所属部门")
  private String departId;
  @Excel(name="预留字段1")
  private String extend1;
  @Excel(name="预留字段2")
  private String extend2;
  @Excel(name="预留字段3")
  private String extend3;
  @Excel(name="预留字段4")
  private String extend4;
  @Excel(name="预留字段5")
  private String extend5;
  @Excel(name="预留字段6")
  private String extend6;
  @Excel(name="预留字段7")
  private String extend7;
  @Excel(name="预留字段8")
  private String extend8;
  @Excel(name="预留字段9")
  private String extend9;
  @Excel(name="预留字段10")
  private String extend10;
  @Excel(name="预留字段11")
  private String extend11;
  @Excel(name="预留字段12")
  private String extend12;
  @Excel(name="预留字段13")
  private String extend13;
  @Excel(name="预留字段14")
  private String extend14;
  @Excel(name="预留字段15")
  private String extend15;
  @Excel(name="预留字段16")
  private String extend16;
  private String urgentLevel;
  
  @Transient
  public String getTaskNum()
  {
    return this.taskNum;
  }
  
  public void setTaskNum(String taskNum)
  {
    this.taskNum = taskNum;
  }
  
  @Transient
  public String getUserName()
  {
    return this.userName;
  }
  
  public void setUserName(String userName)
  {
    this.userName = userName;
  }
  
  @Transient
  public String getProduceModel()
  {
    return this.produceModel;
  }
  
  public void setProduceModel(String produceModel)
  {
    this.produceModel = produceModel;
  }
  
  @Transient
  public String getSystemModel()
  {
    return this.systemModel;
  }
  
  public void setSystemModel(String systemModel)
  {
    this.systemModel = systemModel;
  }
  
  @Transient
  public String getProduceName()
  {
    return this.produceName;
  }
  
  public void setProduceName(String produceName)
  {
    this.produceName = produceName;
  }
  
  @Transient
  public String getSystemName()
  {
    return this.systemName;
  }
  
  public void setSystemName(String systemName)
  {
    this.systemName = systemName;
  }
  
  @Column(name="EXTEND17", nullable=true, length=200)
  public String getExtend17()
  {
    return this.extend17;
  }
  
  public void setExtend17(String extend17)
  {
    this.extend17 = extend17;
  }
  
  @Column(name="EXTEND18", nullable=true, length=200)
  public String getExtend18()
  {
    return this.extend18;
  }
  
  public void setExtend18(String extend18)
  {
    this.extend18 = extend18;
  }
  
  @Column(name="EXTEND19", nullable=true, length=200)
  public String getExtend19()
  {
    return this.extend19;
  }
  
  public void setExtend19(String extend19)
  {
    this.extend19 = extend19;
  }
  
  @Column(name="EXTEND20", nullable=true, length=200)
  public String getExtend20()
  {
    return this.extend20;
  }
  
  public void setExtend20(String extend20)
  {
    this.extend20 = extend20;
  }
  
  @Id
  @GeneratedValue(generator="paymentableGenerator")
  @GenericGenerator(name="paymentableGenerator", strategy="uuid")
  @Column(name="ID", nullable=false, length=36)
  public String getId()
  {
    return this.id;
  }
  
  public void setId(String id)
  {
    this.id = id;
  }
  
  @Column(name="BILL_NO", nullable=true, length=50)
  public String getBillNo()
  {
    return this.billNo;
  }
  
  public void setBillNo(String billNo)
  {
    this.billNo = billNo;
  }
  
  @Column(name="TASK_SOURCE", nullable=true, length=2)
  public String getTaskSource()
  {
    return this.taskSource;
  }
  
  public void setTaskSource(String taskSource)
  {
    this.taskSource = taskSource;
  }
  
  @Column(name="TASK_LEVEL", nullable=true, length=2)
  public String getTaskLevel()
  {
    return this.taskLevel;
  }
  
  public void setTaskLevel(String taskLevel)
  {
    this.taskLevel = taskLevel;
  }
  
  @Column(name="PRODUCE_ID", nullable=true, length=36)
  public String getProduceId()
  {
    return this.produceId;
  }
  
  public void setProduceId(String produceId)
  {
    this.produceId = produceId;
  }
  
  @Column(name="SYSTEM_ID", nullable=true, length=36)
  public String getSystemId()
  {
    return this.systemId;
  }
  
  public void setSystemId(String systemId)
  {
    this.systemId = systemId;
  }
  
  @Column(name="TASK_TYPE", nullable=true, length=2)
  public String getTaskType()
  {
    return this.taskType;
  }
  
  public void setTaskType(String taskType)
  {
    this.taskType = taskType;
  }
  
  @Column(name="WORKFLOW_ID", nullable=true, length=36)
  public String getWorkflowId()
  {
    return this.workflowId;
  }
  
  public void setWorkflowId(String workflowId)
  {
    this.workflowId = workflowId;
  }
  
  @Column(name="PROXY_UNIT", nullable=true, length=200)
  public String getProxyUnit()
  {
    return this.proxyUnit;
  }
  
  public void setProxyUnit(String proxyUnit)
  {
    this.proxyUnit = proxyUnit;
  }
  
  @Column(name="TEST_UNIT", nullable=true, length=200)
  public String getTestUnit()
  {
    return this.testUnit;
  }
  
  public void setTestUnit(String testUnit)
  {
    this.testUnit = testUnit;
  }
  
  @Column(name="USER_ID", nullable=true, length=36)
  public String getUserId()
  {
    return this.userId;
  }
  
  public void setUserId(String userId)
  {
    this.userId = userId;
  }
  
  @Column(name="BILL_STATUS", nullable=true, length=2)
  public String getBillStatus()
  {
    return this.billStatus;
  }
  
  public void setBillStatus(String billStatus)
  {
    this.billStatus = billStatus;
  }
  
  @Column(name="TASK_STATUS", nullable=true, length=2)
  public String getTaskStatus()
  {
    return this.taskStatus;
  }
  
  public void setTaskStatus(String taskStatus)
  {
    this.taskStatus = taskStatus;
  }
  
  @Column(name="BEGAIN_DATA", nullable=true)
  public Date getBegainData()
  {
    return this.begainData;
  }
  
  public void setBegainData(Date begainData)
  {
    this.begainData = begainData;
  }
  
  @Column(name="END_DATA", nullable=true)
  public Date getEndData()
  {
    return this.endData;
  }
  
  public void setEndData(Date endData)
  {
    this.endData = endData;
  }
  
  @Column(name="REAL_BEGAIN_DATA", nullable=true)
  public Date getRealBegainData()
  {
    return this.realBegainData;
  }
  
  public void setRealBegainData(Date realBegainData)
  {
    this.realBegainData = realBegainData;
  }
  
  @Column(name="REAL_END_DATA", nullable=true)
  public Date getRealEndData()
  {
    return this.realEndData;
  }
  
  public void setRealEndData(Date realEndData)
  {
    this.realEndData = realEndData;
  }
  
  @Column(name="NOTE", nullable=true, length=600)
  public String getNote()
  {
    return this.note;
  }
  
  public void setNote(String note)
  {
    this.note = note;
  }
  
  @Column(name="CREATE_DATE", nullable=true)
  public Date getCreateDate()
  {
    return this.createDate;
  }
  
  public void setCreateDate(Date createDate)
  {
    this.createDate = createDate;
  }
  
  @Column(name="CREATE_BY", nullable=true, length=50)
  public String getCreateBy()
  {
    return this.createBy;
  }
  
  public void setCreateBy(String createBy)
  {
    this.createBy = createBy;
  }
  
  @Column(name="AUDIT_BY", nullable=true, length=50)
  public String getAuditBy()
  {
    return this.auditBy;
  }
  
  public void setAuditBy(String auditBy)
  {
    this.auditBy = auditBy;
  }
  
  @Column(name="AUDIT_NOTE", nullable=true, length=200)
  public String getAuditNote()
  {
    return this.auditNote;
  }
  
  public void setAuditNote(String auditNote)
  {
    this.auditNote = auditNote;
  }
  
  @Column(name="AUDIT_DATE", nullable=true)
  public Date getAuditDate()
  {
    return this.auditDate;
  }
  
  public void setAuditDate(Date auditDate)
  {
    this.auditDate = auditDate;
  }
  
  @Column(name="UPDATE_DATE", nullable=true)
  public Date getUpdateDate()
  {
    return this.updateDate;
  }
  
  public void setUpdateDate(Date updateDate)
  {
    this.updateDate = updateDate;
  }
  
  @Column(name="UPDATE_BY", nullable=true, length=50)
  public String getUpdateBy()
  {
    return this.updateBy;
  }
  
  public void setUpdateBy(String updateBy)
  {
    this.updateBy = updateBy;
  }
  
  @Column(name="DEPART_ID", nullable=true, length=50)
  public String getDepartId()
  {
    return this.departId;
  }
  
  public void setDepartId(String departId)
  {
    this.departId = departId;
  }
  
  @Column(name="EXTEND1", nullable=true, length=200)
  public String getExtend1()
  {
    return this.extend1;
  }
  
  public void setExtend1(String extend1)
  {
    this.extend1 = extend1;
  }
  
  @Column(name="EXTEND2", nullable=true, length=200)
  public String getExtend2()
  {
    return this.extend2;
  }
  
  public void setExtend2(String extend2)
  {
    this.extend2 = extend2;
  }
  
  @Column(name="EXTEND3", nullable=true, length=200)
  public String getExtend3()
  {
    return this.extend3;
  }
  
  public void setExtend3(String extend3)
  {
    this.extend3 = extend3;
  }
  
  @Column(name="EXTEND4", nullable=true, length=200)
  public String getExtend4()
  {
    return this.extend4;
  }
  
  public void setExtend4(String extend4)
  {
    this.extend4 = extend4;
  }
  
  @Column(name="EXTEND5", nullable=true, length=200)
  public String getExtend5()
  {
    return this.extend5;
  }
  
  public void setExtend5(String extend5)
  {
    this.extend5 = extend5;
  }
  
  @Column(name="EXTEND6", nullable=true, length=200)
  public String getExtend6()
  {
    return this.extend6;
  }
  
  public void setExtend6(String extend6)
  {
    this.extend6 = extend6;
  }
  
  @Column(name="EXTEND7", nullable=true, length=200)
  public String getExtend7()
  {
    return this.extend7;
  }
  
  public void setExtend7(String extend7)
  {
    this.extend7 = extend7;
  }
  
  @Column(name="EXTEND8", nullable=true, length=200)
  public String getExtend8()
  {
    return this.extend8;
  }
  
  public void setExtend8(String extend8)
  {
    this.extend8 = extend8;
  }
  
  @Column(name="EXTEND9", nullable=true, length=200)
  public String getExtend9()
  {
    return this.extend9;
  }
  
  public void setExtend9(String extend9)
  {
    this.extend9 = extend9;
  }
  
  @Column(name="EXTEND10", nullable=true, length=200)
  public String getExtend10()
  {
    return this.extend10;
  }
  
  public void setExtend10(String extend10)
  {
    this.extend10 = extend10;
  }
  
  @Column(name="EXTEND11", nullable=true, length=200)
  public String getExtend11()
  {
    return this.extend11;
  }
  
  public void setExtend11(String extend11)
  {
    this.extend11 = extend11;
  }
  
  @Column(name="EXTEND12", nullable=true, length=200)
  public String getExtend12()
  {
    return this.extend12;
  }
  
  public void setExtend12(String extend12)
  {
    this.extend12 = extend12;
  }
  
  @Column(name="EXTEND13", nullable=true, length=200)
  public String getExtend13()
  {
    return this.extend13;
  }
  
  public void setExtend13(String extend13)
  {
    this.extend13 = extend13;
  }
  
  @Column(name="EXTEND14", nullable=true, length=200)
  public String getExtend14()
  {
    return this.extend14;
  }
  
  public void setExtend14(String extend14)
  {
    this.extend14 = extend14;
  }
  
  @Column(name="EXTEND15", nullable=true, length=200)
  public String getExtend15()
  {
    return this.extend15;
  }
  
  public void setExtend15(String extend15)
  {
    this.extend15 = extend15;
  }
  
  @Column(name="EXTEND16", nullable=true, length=200)
  public String getExtend16()
  {
    return this.extend16;
  }
  
  public void setExtend16(String extend16)
  {
    this.extend16 = extend16;
  }
  
  @Transient
  public String getSystemOrProductName()
  {
    return this.systemOrProductName;
  }
  
  public void setSystemOrProductName(String systemOrProductName)
  {
    this.systemOrProductName = systemOrProductName;
  }
  
  @Transient
  public String getSystemModelOrproduceModel()
  {
    return this.systemModelOrproduceModel;
  }
  
  public void setSystemModelOrproduceModel(String systemModelOrproduceModel)
  {
    this.systemModelOrproduceModel = systemModelOrproduceModel;
  }
  
  @Transient
  public String getExeProject()
  {
    return this.exeProject;
  }
  
  public void setExeProject(String exeProject)
  {
    this.exeProject = exeProject;
  }
  
  @Column(name="urgentLevel", nullable=true, length=2)
  public String getUrgentLevel()
  {
    return this.urgentLevel;
  }
  
  public void setUrgentLevel(String urgentLevel)
  {
    this.urgentLevel = urgentLevel;
  }
}
