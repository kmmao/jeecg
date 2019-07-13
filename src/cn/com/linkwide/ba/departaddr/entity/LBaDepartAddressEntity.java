package cn.com.linkwide.ba.departaddr.entity;

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
 * @Description: 科室及送货地址对照表
 * @author onlineGenerator
 * @date 2018-09-26 10:59:11
 * @version V1.0   
 *
 */
@Entity
@Table(name = "l_ba_depart_address", schema = "")
@SuppressWarnings("serial")
public class LBaDepartAddressEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**科室*/
	@Excel(name="科室",width=15,dictTable ="t_s_depart",dicCode ="id",dicText ="departname")
	private java.lang.String departId;
	/**地址编码*/
	@Excel(name="地址编码",width=15)
	private java.lang.String addressCode;
	/**详细地址*/
	@Excel(name="详细地址",width=15)
	private java.lang.String address;
	
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
	 *@return: java.lang.String  科室
	 */

	@Column(name ="DEPART_ID",nullable=false,length=32)
	public java.lang.String getDepartId(){
		return this.departId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  科室
	 */
	public void setDepartId(java.lang.String departId){
		this.departId = departId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  地址编码
	 */

	@Column(name ="ADDRESS_CODE",nullable=true,length=32)
	public java.lang.String getAddressCode(){
		return this.addressCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  地址编码
	 */
	public void setAddressCode(java.lang.String addressCode){
		this.addressCode = addressCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  详细地址
	 */

	@Column(name ="ADDRESS",nullable=true,length=218)
	public java.lang.String getAddress(){
		return this.address;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  详细地址
	 */
	public void setAddress(java.lang.String address){
		this.address = address;
	}
}
