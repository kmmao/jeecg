package cn.com.linkwide.ba.material.qual.entity;
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
 * @Description: 证件材料对应关系
 * @author onlineGenerator
 * @date 2018-05-17 11:13:05
 * @version V1.0   
 *
 */
@Entity
@Table(name = "l_su_material_qual_inv", schema = "")
@SuppressWarnings("serial")
public class LSuMaterialQualInvEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**更新日期*/
	private java.util.Date updateDate;
	/**所属公司*/
	private java.lang.String sysCompanyCode;
	/**证件*/
	private java.lang.String certId;
	/**材料编码*/
    @Excel(name="材料编码",width=15)
	private java.lang.String invCode;
	
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
	 *@return: java.lang.String  证件
	 */
	
	@Column(name ="CERT_ID",nullable=false,length=32)
	public java.lang.String getCertId(){
		return this.certId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  证件
	 */
	public void setCertId(java.lang.String certId){
		this.certId = certId;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  材料编码
	 */
	
	@Column(name ="INV_CODE",nullable=false,length=32)
	public java.lang.String getInvCode(){
		return this.invCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  材料编码
	 */
	public void setInvCode(java.lang.String invCode){
		this.invCode = invCode;
	}
	
}
