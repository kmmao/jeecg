package cn.com.linkwide.common.custom.vcodeplan.entity;

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
 * @Description: 编码方案
 * @author onlineGenerator
 * @date 2018-01-23 16:18:36
 * @version V1.0   
 *
 */
@Entity
@Table(name = "vcodeplan", schema = "")
@SuppressWarnings("serial")
public class VcodeplanEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**最大级数*/
	@Excel(name="最大级数")
	private java.lang.Integer biglevel;
	/**最大长度*/
	@Excel(name="最大长度")
	private java.lang.Integer biglength;
	/**单级最大长度*/
	@Excel(name="单级最大长度")
	private java.lang.Integer bigeachlength;
	/**第一级*/
	@Excel(name="第一级")
	private java.lang.Integer onelevel;
	/**第二级*/
	@Excel(name="第二级")
	private java.lang.Integer twolevel;
	/**第三级*/
	@Excel(name="第三级")
	private java.lang.Integer threelevel;
	/**第四级*/
	@Excel(name="第四级")
	private java.lang.Integer fourlevel;
	/**第五级*/
	@Excel(name="第五级")
	private java.lang.Integer fivelevel;
	/**第六级*/
	@Excel(name="第六级")
	private java.lang.Integer sixlevel;
	/**第七级*/
	@Excel(name="第七级")
	private java.lang.Integer sevenlevel;
	/**第八级*/
	@Excel(name="第八级")
	private java.lang.Integer eightlevel;
	/**第九级*/
	@Excel(name="第九级")
	private java.lang.Integer ninelevel;
	/**第十级*/
	@Excel(name="第十级")
	private java.lang.Integer tenlevel;
	/**预留字段1*/
	private java.lang.Integer vdef1;
	/**预留字段2*/
	private java.lang.Integer vdef2;
	/**预留字段3*/
	private java.lang.Integer vdef3;
	/**预留字段4*/
	private java.lang.String vdef4;
	/**预留字段5*/
	private java.lang.String vdef5;
	
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
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  最大级数
	 */
	@Column(name ="BIGLEVEL",nullable=true,length=32)
	public java.lang.Integer getBiglevel(){
		return this.biglevel;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  最大级数
	 */
	public void setBiglevel(java.lang.Integer biglevel){
		this.biglevel = biglevel;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  最大长度
	 */
	@Column(name ="BIGLENGTH",nullable=true,length=32)
	public java.lang.Integer getBiglength(){
		return this.biglength;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  最大长度
	 */
	public void setBiglength(java.lang.Integer biglength){
		this.biglength = biglength;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  单级最大长度
	 */
	@Column(name ="BIGEACHLENGTH",nullable=true,length=32)
	public java.lang.Integer getBigeachlength(){
		return this.bigeachlength;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  单级最大长度
	 */
	public void setBigeachlength(java.lang.Integer bigeachlength){
		this.bigeachlength = bigeachlength;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  第一级
	 */
	@Column(name ="ONELEVEL",nullable=true,length=32)
	public java.lang.Integer getOnelevel(){
		return this.onelevel;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  第一级
	 */
	public void setOnelevel(java.lang.Integer onelevel){
		this.onelevel = onelevel;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  第二级
	 */
	@Column(name ="TWOLEVEL",nullable=true,length=32)
	public java.lang.Integer getTwolevel(){
		return this.twolevel;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  第二级
	 */
	public void setTwolevel(java.lang.Integer twolevel){
		this.twolevel = twolevel;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  第三级
	 */
	@Column(name ="THREELEVEL",nullable=true,length=32)
	public java.lang.Integer getThreelevel(){
		return this.threelevel;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  第三级
	 */
	public void setThreelevel(java.lang.Integer threelevel){
		this.threelevel = threelevel;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  第四级
	 */
	@Column(name ="FOURLEVEL",nullable=true,length=32)
	public java.lang.Integer getFourlevel(){
		return this.fourlevel;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  第四级
	 */
	public void setFourlevel(java.lang.Integer fourlevel){
		this.fourlevel = fourlevel;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  第五级
	 */
	@Column(name ="FIVELEVEL",nullable=true,length=32)
	public java.lang.Integer getFivelevel(){
		return this.fivelevel;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  第五级
	 */
	public void setFivelevel(java.lang.Integer fivelevel){
		this.fivelevel = fivelevel;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  第六级
	 */
	@Column(name ="SIXLEVEL",nullable=true,length=32)
	public java.lang.Integer getSixlevel(){
		return this.sixlevel;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  第六级
	 */
	public void setSixlevel(java.lang.Integer sixlevel){
		this.sixlevel = sixlevel;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  第七级
	 */
	@Column(name ="SEVENLEVEL",nullable=true,length=32)
	public java.lang.Integer getSevenlevel(){
		return this.sevenlevel;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  第七级
	 */
	public void setSevenlevel(java.lang.Integer sevenlevel){
		this.sevenlevel = sevenlevel;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  第八级
	 */
	@Column(name ="EIGHTLEVEL",nullable=true,length=32)
	public java.lang.Integer getEightlevel(){
		return this.eightlevel;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  第八级
	 */
	public void setEightlevel(java.lang.Integer eightlevel){
		this.eightlevel = eightlevel;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  第九级
	 */
	@Column(name ="NINELEVEL",nullable=true,length=32)
	public java.lang.Integer getNinelevel(){
		return this.ninelevel;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  第九级
	 */
	public void setNinelevel(java.lang.Integer ninelevel){
		this.ninelevel = ninelevel;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  第十级
	 */
	@Column(name ="TENLEVEL",nullable=true,length=32)
	public java.lang.Integer getTenlevel(){
		return this.tenlevel;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  第十级
	 */
	public void setTenlevel(java.lang.Integer tenlevel){
		this.tenlevel = tenlevel;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  预留字段1
	 */
	@Column(name ="VDEF1",nullable=true,length=32)
	public java.lang.Integer getVdef1(){
		return this.vdef1;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  预留字段1
	 */
	public void setVdef1(java.lang.Integer vdef1){
		this.vdef1 = vdef1;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  预留字段2
	 */
	@Column(name ="VDEF2",nullable=true,length=32)
	public java.lang.Integer getVdef2(){
		return this.vdef2;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  预留字段2
	 */
	public void setVdef2(java.lang.Integer vdef2){
		this.vdef2 = vdef2;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  预留字段3
	 */
	@Column(name ="VDEF3",nullable=true,length=32)
	public java.lang.Integer getVdef3(){
		return this.vdef3;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  预留字段3
	 */
	public void setVdef3(java.lang.Integer vdef3){
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
}
