package cn.com.linkwide.ba.material.qualType.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

/**   
 * @Title: Entity
 * @Description: 资质分类
 * @author onlineGenerator
 * @date 2017-11-13 13:42:24
 * @version V1.0   
 *
 */
@Entity
@Table(name = "l_ba_material_qual_type", schema = "")
@SuppressWarnings("serial")
public class LBaMaterialQualTypeEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**资质编码*/
	@Excel(name="资质编码")
	private java.lang.String typeCode;
	/**资质名称*/
	@Excel(name="资质名称")
	private java.lang.String typeName;
	/**上级代码*/
	@Excel(name="上级代码")
	private java.lang.String parentId;
	/**是否停用 0:否1:是*/
	@Excel(name="是否停用 0:否1:是")
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
	 *@return: java.lang.String  资质编码
	 */
	@Column(name ="TYPE_CODE",nullable=false,length=36)
	public java.lang.String getTypeCode(){
		return this.typeCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  资质编码
	 */
	public void setTypeCode(java.lang.String typeCode){
		this.typeCode = typeCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  资质名称
	 */
	@Column(name ="TYPE_NAME",nullable=false,length=36)
	public java.lang.String getTypeName(){
		return this.typeName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  资质名称
	 */
	public void setTypeName(java.lang.String typeName){
		this.typeName = typeName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  上级代码
	 */
	@Column(name ="PARENT_ID",nullable=false,length=36)
	public java.lang.String getParentId(){
		return this.parentId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  上级代码
	 */
	public void setParentId(java.lang.String parentId){
		this.parentId = parentId;
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
