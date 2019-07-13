package cn.com.linkwide.cont.contracttype.entity;

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
 * @Description: 合同类型表
 * @author onlineGenerator
 * @date 2018-05-28 16:36:55
 * @version V1.0   
 *
 */
@Entity
@Table(name = "contract_type", schema = "")
@SuppressWarnings("serial")
public class ContractTypeEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**创建日期*/
	private java.util.Date createDate;
	/**更新日期*/
	private java.util.Date updateDate;
	/**分类编码*/
	@Excel(name="分类编码",width=15)
	private java.lang.String code;
	/**分类名称*/
	@Excel(name="分类名称",width=15)
	private java.lang.String name;
	/**父节点编码*/
	@Excel(name="父节点编码",width=15,dictTable ="contract_type",dicCode ="code",dicText ="name")
	private java.lang.String parentCode;
	/**级次*/
	private java.lang.String level;
	/**是否停用*/
	@Excel(name="是否停用",width=15,dicCode="yes_no_int")
	private java.lang.Integer isStop;
	/**是否末级*/
	@Excel(name="是否末级",width=15)
	private java.lang.Integer isLast;
	
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
	 *@return: java.util.Date  创建日期
	 */

	@Column(name ="CREATE_DATE",nullable=true,length=20)
	public java.util.Date getCreateDate(){
		return this.createDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  创建日期
	 */
	public void setCreateDate(java.util.Date createDate){
		this.createDate = createDate;
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
	 *@return: java.lang.String  分类编码
	 */

	@Column(name ="CODE",nullable=true,length=32)
	public java.lang.String getCode(){
		return this.code;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  分类编码
	 */
	public void setCode(java.lang.String code){
		this.code = code;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  分类名称
	 */

	@Column(name ="NAME",nullable=true,length=32)
	public java.lang.String getName(){
		return this.name;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  分类名称
	 */
	public void setName(java.lang.String name){
		this.name = name;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  父节点编码
	 */

	@Column(name ="PARENT_CODE",nullable=true,length=32)
	public java.lang.String getParentCode(){
		return this.parentCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  父节点编码
	 */
	public void setParentCode(java.lang.String parentCode){
		this.parentCode = parentCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  级次
	 */

	@Column(name ="LEVEL",nullable=true,length=32)
	public java.lang.String getLevel(){
		return this.level;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  级次
	 */
	public void setLevel(java.lang.String level){
		this.level = level;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  是否停用
	 */

	@Column(name ="IS_STOP",nullable=true,length=32)
	public java.lang.Integer getIsStop(){
		return this.isStop;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  是否停用
	 */
	public void setIsStop(java.lang.Integer isStop){
		this.isStop = isStop;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  是否末级
	 */

	@Column(name ="IS_LAST",nullable=true,length=10)
	public java.lang.Integer getIsLast(){
		return this.isLast;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  是否末级
	 */
	public void setIsLast(java.lang.Integer isLast){
		this.isLast = isLast;
	}
}
