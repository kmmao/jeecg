package cn.com.linkwide.ba.rpmap.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

/**   
 * @Title: Entity
 * @Description: 出入库类别采购类型对照表
 * @author onlineGenerator
 * @date 2018-07-19 19:27:46
 * @version V1.0   
 *
 */
@Entity
@Table(name = "l_ba_rece_purc_map", schema = "")
@SuppressWarnings("serial")
public class LBaRecePurcMapEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**采购类型*/
	@Excel(name="采购类型",width=15,dictTable ="table1",dicCode ="id",dicText ="name")
	private java.lang.String purcId;
	/**收发类型*/
	@Excel(name="收发类型",width=15,dictTable ="table2",dicCode ="id",dicText ="name")
	private java.lang.String receiveId;
	
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
	 *@return: java.lang.String  采购类型
	 */

	@Column(name ="PURC_ID",nullable=true,length=32)
	public java.lang.String getPurcId(){
		return this.purcId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  采购类型
	 */
	public void setPurcId(java.lang.String purcId){
		this.purcId = purcId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  收发类型
	 */

	@Column(name ="RECEIVE_ID",nullable=true,length=32)
	public java.lang.String getReceiveId(){
		return this.receiveId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  收发类型
	 */
	public void setReceiveId(java.lang.String receiveId){
		this.receiveId = receiveId;
	}
}
