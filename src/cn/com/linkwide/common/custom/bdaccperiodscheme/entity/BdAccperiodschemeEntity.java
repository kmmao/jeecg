package cn.com.linkwide.common.custom.bdaccperiodscheme.entity;

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
 * @Description: 会计期间方案
 * @author onlineGenerator
 * @date 2017-09-29 14:25:05
 * @version V1.0   
 *
 */
@Entity
@Table(name = "bd_accperiodscheme", schema = "")
@SuppressWarnings("serial")
public class BdAccperiodschemeEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**方案编码*/
	@Excel(name="方案编码")
	private java.lang.String accperiodschemecode;
	/**方案名称*/
	@Excel(name="方案名称")
	private java.lang.String accperiodschemename;
	/**删除标志*/
	@Excel(name="删除标志")
	private java.lang.String dr;
	/**备注*/
	@Excel(name="备注")
	private java.lang.String memo;
	/**时间戳*/
	@Excel(name="时间戳",format = "yyyy-MM-dd")
	private java.util.Date ts;
	
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
	 *@return: java.lang.String  方案编码
	 */
	@Column(name ="ACCPERIODSCHEMECODE",nullable=true,length=32)
	public java.lang.String getAccperiodschemecode(){
		return this.accperiodschemecode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  方案编码
	 */
	public void setAccperiodschemecode(java.lang.String accperiodschemecode){
		this.accperiodschemecode = accperiodschemecode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  方案名称
	 */
	@Column(name ="ACCPERIODSCHEMENAME",nullable=true,length=32)
	public java.lang.String getAccperiodschemename(){
		return this.accperiodschemename;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  方案名称
	 */
	public void setAccperiodschemename(java.lang.String accperiodschemename){
		this.accperiodschemename = accperiodschemename;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  删除标志
	 */
	@Column(name ="DR",nullable=true,length=32)
	public java.lang.String getDr(){
		return this.dr;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  删除标志
	 */
	public void setDr(java.lang.String dr){
		this.dr = dr;
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
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  时间戳
	 */
	@Column(name ="TS",nullable=true,length=32)
	public java.util.Date getTs(){
		return this.ts;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  时间戳
	 */
	public void setTs(java.util.Date ts){
		this.ts = ts;
	}
}
