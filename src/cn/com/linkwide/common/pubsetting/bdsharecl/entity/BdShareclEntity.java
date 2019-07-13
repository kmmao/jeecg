package cn.com.linkwide.common.pubsetting.bdsharecl.entity;

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
 * @Description: 核算属性
 * @author onlineGenerator
 * @date 2017-09-07 12:00:33
 * @version V1.0   
 *
 */
@Entity
@Table(name = "bd_sharecl", schema = "")
@SuppressWarnings("serial")
public class BdShareclEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**核算属性编码*/
	@Excel(name="核算属性编码")
	private java.lang.String vcode;
	/**核算属性名称*/
	@Excel(name="核算属性名称")
	private java.lang.String vname;
	/**备注*/
	@Excel(name="备注")
	private java.lang.String memo;
	/**公司主键*/
	private java.lang.String pkCorp;
	/**合并后科室主键*/
	private java.lang.String pkMerger;
	/**上级主键*/
	private java.lang.String pkParent;
	/**时间戳*/
	private java.util.Date ts;
	/**预留字段1*/
	private java.lang.String vdef1;
	/**预留字段2*/
	private java.lang.String vdef2;
	/**预留字段3*/
	private java.lang.String vdef3;
	/**预留字段4*/
	private java.lang.String vdef4;
	/**预留字段5*/
	private java.lang.String vdef5;
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
	 *@return: java.lang.String  核算属性编码
	 */
	@Column(name ="VCODE",nullable=true,length=32)
	public java.lang.String getVcode(){
		return this.vcode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  核算属性编码
	 */
	public void setVcode(java.lang.String vcode){
		this.vcode = vcode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  核算属性名称
	 */
	@Column(name ="VNAME",nullable=true,length=32)
	public java.lang.String getVname(){
		return this.vname;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  核算属性名称
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
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  公司主键
	 */
	@Column(name ="PK_CORP",nullable=true,length=32)
	public java.lang.String getPkCorp(){
		return this.pkCorp;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  公司主键
	 */
	public void setPkCorp(java.lang.String pkCorp){
		this.pkCorp = pkCorp;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  合并后科室主键
	 */
	@Column(name ="PK_MERGER",nullable=true,length=32)
	public java.lang.String getPkMerger(){
		return this.pkMerger;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  合并后科室主键
	 */
	public void setPkMerger(java.lang.String pkMerger){
		this.pkMerger = pkMerger;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  上级主键
	 */
	@Column(name ="PK_PARENT",nullable=true,length=32)
	public java.lang.String getPkParent(){
		return this.pkParent;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  上级主键
	 */
	public void setPkParent(java.lang.String pkParent){
		this.pkParent = pkParent;
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
}
