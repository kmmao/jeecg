package cn.com.linkwide.ba.bacustomertype.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

/**   
 * @Title: Entity
 * @Description: 客户类型
 * @author onlineGenerator
 * @date 2018-08-03 09:41:00
 * @version V1.0   
 *
 */
@Entity
@Table(name = "ba_customer_type", schema = "")
@SuppressWarnings("serial")
public class BaCustomerTypeEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**编码*/
	@Excel(name="编码",width=15)
	private java.lang.String typeCode;
	/**名称*/
	@Excel(name="名称",width=15)
	private java.lang.String typeName;
	/**上级代码*/
	@Excel(name="上级编码",width=15)
	private java.lang.String parentId;
	/**是否停用 */
	@Excel(name="是否停用 ",width=15)
	private java.lang.String status;
	/**是否末级*/
	@Excel(name="是否末级",width=15)
	private java.lang.String extend1;
	/**扩展字段2*/
//	@Excel(name="扩展字段2",width=15)
	private java.lang.String extend2;
	/**扩展字段3*/
//	@Excel(name="扩展字段3",width=15)
	private java.lang.String extend3;
	/**扩展字段4*/
//	@Excel(name="扩展字段4",width=15)
	private java.lang.String extend4;
	
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
	 *@return: java.lang.String  编码
	 */

	@Column(name ="TYPE_CODE",nullable=false,length=36)
	public java.lang.String getTypeCode(){
		return this.typeCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  编码
	 */
	public void setTypeCode(java.lang.String typeCode){
		this.typeCode = typeCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  名称
	 */

	@Column(name ="TYPE_NAME",nullable=false,length=36)
	public java.lang.String getTypeName(){
		return this.typeName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  名称
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
	 *@return: java.lang.String  是否停用 
	 */

	@Column(name ="STATUS",nullable=false,length=2)
	public java.lang.String getStatus(){
		return this.status;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否停用 
	 */
	public void setStatus(java.lang.String status){
		this.status = status;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否末级
	 */

	@Column(name ="EXTEND1",nullable=true,length=255)
	public java.lang.String getExtend1(){
		return this.extend1;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否末级
	 */
	public void setExtend1(java.lang.String extend1){
		this.extend1 = extend1;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  扩展字段2
	 */

	@Column(name ="EXTEND2",nullable=true,length=255)
	public java.lang.String getExtend2(){
		return this.extend2;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  扩展字段2
	 */
	public void setExtend2(java.lang.String extend2){
		this.extend2 = extend2;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  扩展字段3
	 */

	@Column(name ="EXTEND3",nullable=true,length=255)
	public java.lang.String getExtend3(){
		return this.extend3;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  扩展字段3
	 */
	public void setExtend3(java.lang.String extend3){
		this.extend3 = extend3;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  扩展字段4
	 */

	@Column(name ="EXTEND4",nullable=true,length=255)
	public java.lang.String getExtend4(){
		return this.extend4;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  扩展字段4
	 */
	public void setExtend4(java.lang.String extend4){
		this.extend4 = extend4;
	}
}
