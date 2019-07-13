package cn.com.linkwide.ba.material.materunit.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

/**   
 * @Title: Entity
 * @Description: l_ba_material_mater_unit
 * @author onlineGenerator
 * @date 2017-11-14 13:37:29
 * @version V1.0   
 *
 */
@Entity
@Table(name = "l_ba_material_mater_unit", schema = "")
@SuppressWarnings("serial")
public class LBaMaterialMaterUnitEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**计量单位编码*/
	@Excel(name="计量单位编码")
	private java.lang.String typeCode;
	/**计量单位名称*/
	@Excel(name="计量单位名称")
	private java.lang.String typeName;
	/**英文名称单数*/
	@Excel(name="英文名称单数")
	private java.lang.String englishNameSingular;
	/**英文名称复数*/
	@Excel(name="英文名称复数")
	private java.lang.String englishNameComplex;
	/**是否停用 0:否1:是*/
	@Excel(name="是否停用")
	private java.lang.String status;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  id
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
	 *@param: java.lang.String  id
	 */
	public void setId(java.lang.String id){
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  计量单位编码
	 */
	@Column(name ="TYPE_CODE",nullable=false,length=36)
	public java.lang.String getTypeCode(){
		return this.typeCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  计量单位编码
	 */
	public void setTypeCode(java.lang.String typeCode){
		this.typeCode = typeCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  计量单位名称
	 */
	@Column(name ="TYPE_NAME",nullable=false,length=36)
	public java.lang.String getTypeName(){
		return this.typeName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  计量单位名称
	 */
	public void setTypeName(java.lang.String typeName){
		this.typeName = typeName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  英文名称单数
	 */
	@Column(name ="ENGLISH_NAME_SINGULAR",nullable=false,length=36)
	public java.lang.String getEnglishNameSingular(){
		return this.englishNameSingular;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  英文名称单数
	 */
	public void setEnglishNameSingular(java.lang.String englishNameSingular){
		this.englishNameSingular = englishNameSingular;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  英文名称复数
	 */
	@Column(name ="ENGLISH_NAME_COMPLEX",nullable=false,length=36)
	public java.lang.String getEnglishNameComplex(){
		return this.englishNameComplex;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  英文名称复数
	 */
	public void setEnglishNameComplex(java.lang.String englishNameComplex){
		this.englishNameComplex = englishNameComplex;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否停用 0:否1:是
	 */
	@Column(name ="STATUS",nullable=false,length=2)
	public java.lang.String getStatus(){
		return this.status;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否停用 0:否1:是
	 */
	public void setStatus(java.lang.String status){
		this.status = status;
	}
}
