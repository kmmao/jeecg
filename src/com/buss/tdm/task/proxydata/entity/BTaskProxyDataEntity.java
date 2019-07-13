package com.buss.tdm.task.proxydata.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

@Entity
@Table(name="b_task_proxy_data", schema="")
public class BTaskProxyDataEntity
  implements Serializable
{
  private String id;
  @Excel(name="委托单ID")
  private String proxyId;
  @Excel(name="资料名称")
  private String fileName;
  @Excel(name="资料路径")
  private String filePath;
  @Excel(name="资料类型")
  private String fileType;
  @Excel(name="上传时间", format="yyyy-MM-dd")
  private Date createDate;
  @Excel(name="上传人")
  private String createBy;
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
  
  @Column(name="PROXY_ID", nullable=true, length=36)
  public String getProxyId()
  {
    return this.proxyId;
  }
  
  public void setProxyId(String proxyId)
  {
    this.proxyId = proxyId;
  }
  
  @Column(name="FILE_NAME", nullable=true, length=50)
  public String getFileName()
  {
    return this.fileName;
  }
  
  public void setFileName(String fileName)
  {
    this.fileName = fileName;
  }
  
  @Column(name="FILE_PATH", nullable=true, length=200)
  public String getFilePath()
  {
    return this.filePath;
  }
  
  public void setFilePath(String filePath)
  {
    this.filePath = filePath;
  }
  
  @Column(name="FILE_TYPE", nullable=true, length=50)
  public String getFileType()
  {
    return this.fileType;
  }
  
  public void setFileType(String fileType)
  {
    this.fileType = fileType;
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
}
