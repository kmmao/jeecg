package cn.com.linkwide.ba.warehouseorg.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**   
 * @Title: Entity
 * @Description: 仓库组织机构对照
 * @author onlineGenerator
 * @date 2018-08-28 18:52:42
 * @version V1.0   
 *
 */
@Entity
@Table(name = "l_ba_warehouse_org_map", schema = "")
@SuppressWarnings("serial")
public class LBaWarehouseOrgMapEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**仓库id*/
	private java.lang.String warehouseId;
	/**机构编码*/
	private java.lang.String orgCode;
	
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
	 *@return: java.lang.String  仓库id
	 */

	@Column(name ="WAREHOUSE_ID",nullable=true,length=36)
	public java.lang.String getWarehouseId(){
		return this.warehouseId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  仓库id
	 */
	public void setWarehouseId(java.lang.String warehouseId){
		this.warehouseId = warehouseId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  机构编码
	 */

	@Column(name ="ORG_CODE",nullable=true,length=50)
	public java.lang.String getOrgCode(){
		return this.orgCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  机构编码
	 */
	public void setOrgCode(java.lang.String orgCode){
		this.orgCode = orgCode;
	}
}
