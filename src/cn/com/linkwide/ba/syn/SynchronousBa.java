package cn.com.linkwide.ba.syn;

import org.jeecgframework.core.util.StringUtil;
import cn.com.linkwide.ba.contsuppliermaterial.entity.LBaContSupplierMaterialEntity;
import cn.com.linkwide.ba.material.qual.entity.LSuMaterialQualEntity;
import cn.com.linkwide.ba.material.qual.entity.LSuMaterialQualInvEntity;
import cn.com.linkwide.ba.material.record.entity.LBaMaterialEntity;
import cn.com.linkwide.ba.supplier.entity.LBaSupplierEntity;

public class SynchronousBa {
	/***
	 * 下面的方法是向物流系统同步数据
	 * zxl
	 * @param obj
	 */
	public static String synSave(Object obj){
		
		if(obj instanceof LBaSupplierEntity){//hrp供应商字典
			LBaSupplierEntity entity = (LBaSupplierEntity) obj;
			StringBuffer sql = new StringBuffer();
			sql.append(" insert into vendor_ven_dict  ");
			sql.append(" (id,create_name,create_by,create_date,update_name,update_by,update_date,bpm_status,supplier_code,supplier_full_name,supplier_short_name ");
			sql.append("  ,supplier_type_id,contacts,telphone,address,is_control,mnemonic_code, affiliated_area,uscc,grant_date,vld,legal_person,corporation_address");
			sql.append(" ,corporation_phone,website,default_currency,register_capital,is_purchase,is_outsource,is_invite_bids,supervise_salesman,payment_received_protocol ");
			sql.append("  ,auditing_state,auditor,auditing_date,supplier_source,is_login,is_cease,cease_date,cease_people,affiliated_bank,oaa_bank, bank_code,sys_org_code,password)");
			sql.append(" values ");
			
			sql.append(" ("+StringUtil.tj(entity.getId())+","+StringUtil.tj(entity.getCreateName())+","+StringUtil.tj(entity.getCreateBy())+","+StringUtil.tj(entity.getCreateDate())+","+StringUtil.tj(entity.getUpdateName())+","+StringUtil.tj(entity.getUpdateBy())+"");
			sql.append(","+StringUtil.tj(entity.getUpdateDate())+","+StringUtil.tj(entity.getBpmStatus())+","+StringUtil.tj(entity.getSupplierCode())+","+StringUtil.tj(entity.getSupplierFullName())+","+StringUtil.tj(entity.getSupplierShortName())+" ");
			sql.append("  ,"+StringUtil.tj(entity.getSupplierTypeId())+","+StringUtil.tj(entity.getContacts())+","+StringUtil.tj(entity.getTelphone())+","+StringUtil.tj(entity.getAddress())+","+StringUtil.tj(entity.getIsControl())+","+StringUtil.tj(entity.getMnemonicCode())+"");
			sql.append( ", "+StringUtil.tj(entity.getAffiliatedArea())+","+StringUtil.tj(entity.getUscc())+","+StringUtil.tj(entity.getGrantDate())+","+StringUtil.tj(entity.getVld())+","+StringUtil.tj(entity.getLegalPerson())+","+StringUtil.tj(entity.getCorporationAddress())+"");
			sql.append(" ,"+StringUtil.tj(entity.getCorporationPhone())+","+StringUtil.tj(entity.getWebsite())+","+StringUtil.tj(entity.getDefaultCurrency())+","+StringUtil.tj(entity.getRegisterCapital())+"");
			sql.append(","+StringUtil.tj(entity.getIsPurchase())+","+StringUtil.tj(entity.getIsOutsource())+","+StringUtil.tj(entity.getIsInviteBids())+","+StringUtil.tj(entity.getSuperviseSalesman())+","+StringUtil.tj(entity.getPaymentReceivedProtocol())+" ");
			sql.append("  ,"+StringUtil.tj(entity.getAuditingState())+","+StringUtil.tj(entity.getAuditor())+","+StringUtil.tj(entity.getAuditingDate())+","+StringUtil.tj(entity.getSupplierSource())+","+StringUtil.tj(entity.getIsLogin())+","+StringUtil.tj(entity.getIsCease())+"");
			sql.append(","+StringUtil.tj(entity.getCeaseDate())+","+StringUtil.tj(entity.getCeasePeople())+","+StringUtil.tj(entity.getAffiliatedBank())+","+StringUtil.tj(entity.getOaaBank())+", "+StringUtil.tj(entity.getBankCode())+","+StringUtil.tj(entity.getSysOrgCode())+","+StringUtil.tj(entity.getPassword())+")");
			
			return sql.toString();
		}else if(obj instanceof LSuMaterialQualEntity){//hrp物资资质
			LSuMaterialQualEntity LSuMaterialQual = (LSuMaterialQualEntity) obj;
			String mainSql =" insert into vendor_inv_cert "
					+ " (id,create_name,create_by,create_date,update_name,update_by,update_date, sys_org_code,sys_company_code,bpm_status,cert_type,cert_code,cert_name,cert_date,start_date, end_date, organ,cert_state,remark,supplier_id,mate_id) "
					+ "values "
					+ "("+StringUtil.tj(LSuMaterialQual.getId())+","+StringUtil.tj(LSuMaterialQual.getCertName())+","+StringUtil.tj(LSuMaterialQual.getCreateBy())+","+StringUtil.tj(LSuMaterialQual.getCertDate())+","+StringUtil.tj(LSuMaterialQual.getUpdateName())+","+StringUtil.tj(LSuMaterialQual.getUpdateBy())+","+StringUtil.tj(LSuMaterialQual.getUpdateDate())+""
					+ ", "+StringUtil.tj(LSuMaterialQual.getSupplierId())+","+StringUtil.tj(LSuMaterialQual.getSysCompanyCode())+","+StringUtil.tj(LSuMaterialQual.getBpmStatus())+","+StringUtil.tj(LSuMaterialQual.getCertType())+","+StringUtil.tj(LSuMaterialQual.getCertCode())+" "
					+ ","+StringUtil.tj(LSuMaterialQual.getCertName())+","+StringUtil.tj(LSuMaterialQual.getCertDate())+","+StringUtil.tj(LSuMaterialQual.getStartDate())+", "+StringUtil.tj(LSuMaterialQual.getEndDate())+", "+StringUtil.tj(LSuMaterialQual.getOrgan())+", "+StringUtil.tj(LSuMaterialQual.getCertState())
					+", "+StringUtil.tj(LSuMaterialQual.getRemark())+", "+StringUtil.tj(LSuMaterialQual.getSupplierId())+", "+StringUtil.tj(LSuMaterialQual.getMateId())+") ";
			return mainSql;
		}else if(obj instanceof LSuMaterialQualInvEntity){//hrp物资资质和材料对应关系
			LSuMaterialQualInvEntity ent = (LSuMaterialQualInvEntity) obj;
			String detailSql = " insert into vendor_cert_inv "
					+ " (id,update_date,sys_company_code,cert_id,inv_code) "
					+ " values "
					+ "("+StringUtil.tj(ent.getId())+","+StringUtil.tj(ent.getUpdateDate())+","+StringUtil.tj(ent.getSysCompanyCode())+","+StringUtil.tj(ent.getCertId())+","+StringUtil.tj(ent.getInvCode())+")";
			return detailSql;
		}else if(obj instanceof LBaMaterialEntity){//hrp物资字典
			LBaMaterialEntity d = (LBaMaterialEntity) obj;
			StringBuffer sqlm = new StringBuffer();
	 		sqlm.append(" insert into vendor_inv_dict ");
	 		sqlm.append(" (id,sys_org_code,sys_company_code,update_date,inv_code,inv_name,inv_type,spell,spec_model,inv_unit,inv_price,inv_attr_code,is_stop,cert_code,supplier_id)");
	 		sqlm.append(" values ");
	 		sqlm.append(" ("+StringUtil.tj(d.getId())+","+StringUtil.tj(d.getSupplierId())+","+StringUtil.tj(d.getSupplierId())+","+StringUtil.tj(d.getUpdateDate())+","+StringUtil.tj(d.getMaterialCode())+","+StringUtil.tj(d.getMaterialName())+","+StringUtil.tj(d.getMaterialTypeId())+","+StringUtil.tj(d.getMonmCode())+","+StringUtil.tj(d.getSpecModel())+","+StringUtil.tj(d.getMaterUnitId())+","+StringUtil.tj(d.getUnitPrice())+",'',0,null,"+StringUtil.tj(d.getSupplierId())+") ");
	 		return sqlm.toString();
			
		}else if(obj instanceof LBaContSupplierMaterialEntity){//hrp供应商物资对照表
			LBaContSupplierMaterialEntity d = (LBaContSupplierMaterialEntity) obj;
			StringBuffer sqlm = new StringBuffer();
	 		sqlm.append(" insert into ven_material_item ");
	 		sqlm.append(" (id,sys_org_code,supplier_id,mate_id) ");
	 		sqlm.append(" values ");
	 		sqlm.append(" ("+StringUtil.tj(d.getId())+","+StringUtil.tj(d.getSupplierId())+","+StringUtil.tj(d.getSupplierId())+","+StringUtil.tj(d.getMaterialId())+") ");
	 		return sqlm.toString();
		}
		
		return null;
	}
	/**
	 * zxl
	 * @param obj
	 */
	public static String synInsert(Object obj){
		StringBuffer sql = new StringBuffer();
		if(obj instanceof LBaSupplierEntity){//hrp供应商字典
			LBaSupplierEntity entity = (LBaSupplierEntity) obj; 
			sql.append(" insert into vendor_ven_dict  ");
			sql.append(" (id,create_name,create_by,create_date,update_name,update_by,update_date,bpm_status,supplier_code,supplier_full_name,supplier_short_name ");
			sql.append(" ,supplier_type_id,contacts,telphone,address,is_control,mnemonic_code, affiliated_area,uscc,grant_date,vld,legal_person,corporation_address");
			sql.append(" ,corporation_phone,website,default_currency,register_capital,is_purchase,is_outsource,is_invite_bids,supervise_salesman,payment_received_protocol ");
			sql.append(" ,auditing_state,auditor,auditing_date,supplier_source,is_login,is_cease,cease_date,cease_people,affiliated_bank,oaa_bank, bank_code,sys_org_code,password )");
			sql.append(" select ");
			sql.append(" id,create_name,create_by,create_date,update_name,update_by,update_date,bpm_status,supplier_code,supplier_full_name,supplier_short_name ");
			sql.append(" ,supplier_type_id,contacts,telphone,address,is_control,mnemonic_code, affiliated_area,uscc,grant_date,vld,legal_person,corporation_address");
			sql.append(" ,corporation_phone,website,default_currency,register_capital,is_purchase,is_outsource,is_invite_bids,supervise_salesman,payment_received_protocol ");
			sql.append(" ,auditing_state,auditor,auditing_date,supplier_source,is_login,is_cease,cease_date,cease_people,affiliated_bank,oaa_bank, bank_code,sys_org_code,password ");
			sql.append(" from l_ba_supplier where id ='"+entity.getId()+"'");
			return sql.toString();
		}else if(obj instanceof LSuMaterialQualEntity){//hrp物资资质
			LSuMaterialQualEntity invcert = (LSuMaterialQualEntity) obj;
			String mainSql =" insert into vendor_inv_cert "
					+ " (id,create_name,create_by,create_date,update_name,update_by,update_date, sys_org_code,sys_company_code,bpm_status,cert_type,cert_code,cert_name,cert_date,start_date, end_date, organ,cert_state,remark) "
					+ "select "
					+ "id,create_name,create_by,create_date,update_name,update_by,update_date, supplier_id,sys_company_code,bpm_status,cert_type,cert_code,cert_name,cert_date,start_date, end_date, organ,cert_state,remark "
					+ "from l_su_material_qual where id = '"+invcert.getId()+"' ";
			return mainSql;
		}else if(obj instanceof LSuMaterialQualInvEntity){//hrp物资资质和材料对应关系
			LSuMaterialQualInvEntity d = (LSuMaterialQualInvEntity) obj;
			String detailSql = " insert into l_su_material_qual_inv "
					+ " (id,update_date,sys_company_code,cert_id,inv_code) "
					+ "select id,update_date,sys_company_code,cert_id,inv_code "
					+ "from vendor_cert_inv where cert_id ='"+d.getId()+"' ";
			return detailSql;
		}else if(obj instanceof LBaMaterialEntity){//hrp物资字典
			LBaMaterialEntity d = (LBaMaterialEntity) obj;
			StringBuffer sqlm = new StringBuffer();
	 		sqlm.append(" insert into vendor_inv_dict ");
	 		sqlm.append(" (id,sys_org_code,sys_company_code,update_date,inv_code,inv_name,inv_type,spell,spec_model,inv_unit,inv_price,inv_attr_code,is_stop,cert_code,supplier_id) ");
	 		sqlm.append(" select ");
	 		sqlm.append(" id,supplier_id,supplier_id,update_date,material_code,material_name,material_type_id,monm_code,spec_model,mater_unit_id,unit_price,'',0,null,supplier_id ");
	 		sqlm.append(" from l_ba_material where id ='"+d.getId()+"' ");
	 		return sqlm.toString();
			
		}else if(obj instanceof LBaContSupplierMaterialEntity){//hrp供应商物资对照表
			LBaContSupplierMaterialEntity d = (LBaContSupplierMaterialEntity) obj;
			StringBuffer sqlm = new StringBuffer();
	 		sqlm.append(" insert into ven_material_item ");
	 		sqlm.append(" (id,sys_org_code,supplier_id,mate_id) ");
	 		sqlm.append(" select ");
	 		sqlm.append(" id,supplier_id,supplier_id,material_id ");
	 		sqlm.append(" from l_ba_cont_supplier_material where id ='"+d.getId()+"' ");
	 		return sqlm.toString();
		}
		
		
		return sql.toString();
	}
	/**
	 * zxl
	 * @param obj
	 */
	public static String synUpdate(Object obj){
		
		return null;
	}
	/**
	 * zxl
	 * @param obj
	 */
	public static String synDelete(Object obj){
		if(obj instanceof LBaSupplierEntity){//hrp供应商字典
			LBaSupplierEntity entity = (LBaSupplierEntity) obj;
			return "delete  from vendor_ven_dict  where id ='"+entity.getId()+"' ";
		}else if(obj instanceof LSuMaterialQualEntity){//hrp物资资质
			LSuMaterialQualEntity invcert = (LSuMaterialQualEntity) obj;
			return " delete  from vendor_inv_cert where id ='"+invcert.getId()+"'"; 
		}else if(obj instanceof LSuMaterialQualInvEntity){//hrp物资资质和材料对应关系
			LSuMaterialQualInvEntity d = (LSuMaterialQualInvEntity) obj;
			return " delete  from vendor_cert_inv where id ='"+d.getId()+"'"; 
		}else if(obj instanceof LBaMaterialEntity){//hrp物资字典
			LBaMaterialEntity d = (LBaMaterialEntity) obj; 
			return " delete  from vendor_inv_dict where id ='"+d.getId()+"'"; 
		}else if(obj instanceof LBaContSupplierMaterialEntity){//hrp供应商物资对照表
			LBaContSupplierMaterialEntity d = (LBaContSupplierMaterialEntity) obj;
			return " delete  from ven_material_item where id ='"+d.getId()+"'"; 
		}
		return null;
	}
}


