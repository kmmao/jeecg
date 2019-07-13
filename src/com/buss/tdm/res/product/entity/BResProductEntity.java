package com.buss.tdm.res.product.entity;

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
@Table(name="b_res_product", schema="")
public class BResProductEntity
  implements Serializable
{
  private String extend3;
  private String extend4;
  private String extend5;
  private String extend6;
  private String extend7;
  private String extend8;
  private String extend9;
  private String extend10;
  private String extend11;
  private String extend12;
  private String extend13;
  private String extend14;
  private String extend15;
  private String extend16;
  private String extend17;
  private String extend18;
  private String extend19;
  private String extend20;
  private String id;
  @Excel(name="产品型号")
  private String productModel;
  @Excel(name="产品名称")
  private String productName;
  @Excel(name="产品图号")
  private String productFigureNo;
  private String productType;
  @Excel(name="产品类型")
  private String productTypeEx;
  private String technologyStatus;
  @Excel(name="技术状态")
  private String technologyStatusEx;
  @Excel(name="创建时间", format="yyyy-MM-dd")
  private Date createDate;
  private String createBy;
  @Excel(name="创建人")
  private String createName;
  @Excel(name="修改时间", format="yyyy-MM-dd")
  private Date updateDate;
  @Excel(name="修改人")
  private String updateBy;
  @Excel(name="备注")
  private String describe;
  private String extend2;
  private String departId;
  
  @Transient
  public String getCreateName()
  {
    return this.createName;
  }
  
  public void setCreateName(String createName)
  {
    this.createName = createName;
  }
  
  @Transient
  public String getProductTypeEx()
  {
    return this.productTypeEx;
  }
  
  public void setProductTypeEx(String productTypeEx)
  {
    this.productTypeEx = productTypeEx;
  }
  
  @Transient
  public String getTechnologyStatusEx()
  {
    return this.technologyStatusEx;
  }
  
  public void setTechnologyStatusEx(String technologyStatusEx)
  {
    this.technologyStatusEx = technologyStatusEx;
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
  
  @Column(name="PRODUCT_MODEL", nullable=true, length=50)
  public String getProductModel()
  {
    return this.productModel;
  }
  
  public void setProductModel(String productModel)
  {
    this.productModel = productModel;
  }
  
  @Column(name="PRODUCT_NAME", nullable=true, length=100)
  public String getProductName()
  {
    return this.productName;
  }
  
  public void setProductName(String productName)
  {
    this.productName = productName;
  }
  
  @Column(name="PRODUCT_FIGURE_NO", nullable=true, length=100)
  public String getProductFigureNo()
  {
    return this.productFigureNo;
  }
  
  public void setProductFigureNo(String productFigureNo)
  {
    this.productFigureNo = productFigureNo;
  }
  
  @Column(name="PRODUCT_TYPE", nullable=true, length=2)
  public String getProductType()
  {
    return this.productType;
  }
  
  public void setProductType(String productType)
  {
    this.productType = productType;
  }
  
  @Column(name="TECHNOLOGY_STATUS", nullable=true, length=2)
  public String getTechnologyStatus()
  {
    return this.technologyStatus;
  }
  
  public void setTechnologyStatus(String technologyStatus)
  {
    this.technologyStatus = technologyStatus;
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
  
  @Column(name="DESCRIBE", nullable=true, length=200)
  public String getDescribe()
  {
    return this.describe;
  }
  
  public void setDescribe(String describe)
  {
    this.describe = describe;
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
  
  @Column(name="DEPART_ID", nullable=true, length=50)
  public String getDepartId()
  {
    return this.departId;
  }
  
  public void setDepartId(String departId)
  {
    this.departId = departId;
  }
}
