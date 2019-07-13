package com.buss.tdm.task.proxyproduce.entity;

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
@Table(name="b_task_proxy_produce", schema="")
public class BTaskProxyProduceEntity
  implements Serializable
{
  private String id;
  @Excel(name="委托单ID")
  private String proxyId;
  @Excel(name="产品型号ID")
  private String produceId;
  private String produceName;
  private String produceModel;
  @Excel(name="系统型号ID")
  private String systemId;
  private String systemName;
  private String systemModel;
  @Excel(name="产品编号")
  private String produceNo;
  @Excel(name="备注")
  private String note;
  @Excel(name="创建时间", format="yyyy-MM-dd")
  private Date createDate;
  @Excel(name="创建人")
  private String createBy;
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
  @Excel(name="预留字段17")
  private String extend17;
  @Excel(name="预留字段18")
  private String extend18;
  @Excel(name="预留字段19")
  private String extend19;
  @Excel(name="预留字段20")
  private String extend20;
  
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
  
  @Column(name="PRODUCE_NO", nullable=true, length=50)
  public String getProduceNo()
  {
    return this.produceNo;
  }
  
  public void setProduceNo(String produceNo)
  {
    this.produceNo = produceNo;
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
  
  public String toString()
  {
    return 
    


      "BTaskProxyProduceEntity [id=" + this.id + ", proxyId=" + this.proxyId + ", produceId=" + this.produceId + ", produceName=" + this.produceName + ", produceModel=" + this.produceModel + ", systemId=" + this.systemId + ", systemName=" + this.systemName + ", systemModel=" + this.systemModel + ", produceNo=" + this.produceNo + ", note=" + this.note + ", createDate=" + this.createDate + ", createBy=" + this.createBy + ", updateDate=" + this.updateDate + ", updateBy=" + this.updateBy + ", departId=" + this.departId + "]";
  }
}
