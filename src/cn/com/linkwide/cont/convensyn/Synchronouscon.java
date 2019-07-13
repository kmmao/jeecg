package cn.com.linkwide.cont.convensyn;

import org.jeecgframework.core.util.StringUtil;

import cn.com.linkwide.cont.coninformation.entity.ConInformationEntity;
import cn.com.linkwide.cont.coninformationdetial.entity.ConMarkEntity;
import cn.com.linkwide.cont.conmemorabilia.entity.ConMemorabiliaEntity;
import cn.com.linkwide.cont.contbank.entity.ContBankEntity;
import cn.com.linkwide.cont.payplan.entity.PayPlanEntity;

public class Synchronouscon {
	/***
	 * 下面的方法是向物流系统同步数据
	 * zxl
	 * @param obj
	 */
	public static String synSave(Object obj){
		
	if(obj instanceof ConInformationEntity){//合同信息
		ConInformationEntity entity = (ConInformationEntity) obj;
			StringBuffer sql = new StringBuffer();
			sql.append("insert into ven_con_information ");
			sql.append(" (id,create_name,create_by,create_date,update_name,update_by,update_date,sys_org_code,sys_company_code,bpm_status,con_type,con_war_id,con_state,con_number ");
			sql.append(",con_no,signed_date,start_date,end_date,other_compy,other_emp,money_type,dept_id,effect_emp,effect_date,case_emp,case_date,zbj_cycle,zbj_rate,zbj_money,ven_id,");
			sql.append("customer_id,cgzb_id,cgsq_id,online_id,acct_id,mate_id,perstate,state_date1,state_date2,state_date3,state_date4,state_date5,state_date6,exec_con_no,con_numner_z,is_bg,renew_id,defout_case,is_defout,fj,defout_date,");
			sql.append("conferm_state,conferm_date,agree_id,sign_id,spl,con_name,con_bz,con_ms,con_exec1,con_exec2,con_exec3,con_exec4,con_exec5,con_exec6,con_exec7,con_exec8,con_exec9,con_exec10)  ");
			sql.append(" values  ");
			sql.append(" ("+StringUtil.tj(entity.getId())+","+StringUtil.tj(entity.getCreateName())+","+StringUtil.tj(entity.getCreateBy())+","+StringUtil.tj(entity.getCreateDate())+","+StringUtil.tj(entity.getUpdateName())+","+StringUtil.tj(entity.getUpdateBy())+","+StringUtil.tj(entity.getUpdateDate())+","+StringUtil.tj(entity.getSysOrgCode())+" ");
			sql.append(","+StringUtil.tj(entity.getSysCompanyCode())+","+StringUtil.tj(entity.getBpmStatus())+","+StringUtil.tj(entity.getConType())+","+StringUtil.tj(entity.getConWarId())+","+StringUtil.tj(entity.getConState())+","+StringUtil.tj(entity.getConNumber())+","+StringUtil.tj(entity.getConNo())+","+StringUtil.tj(entity.getSignedDate())+" ");
			sql.append(","+StringUtil.tj(entity.getStartDate())+","+StringUtil.tj(entity.getEndDate())+","+StringUtil.tj(entity.getOtherCompy())+","+StringUtil.tj(entity.getOtherEmp())+", " + StringUtil.tj(entity.getMoneyType())+"," + StringUtil.tj(entity.getDeptId())+"," + StringUtil.tj(entity.getEffectEmp())+"," + StringUtil.tj(entity.getEffectDate())+"," + StringUtil.tj(entity.getCaseEmp())+"," + StringUtil.tj(entity.getCaseDate())+"," + StringUtil.tj(entity.getZbjCycle())+"," + StringUtil.tj(entity.getZbjRate())+"," + StringUtil.tj(entity.getZbjMoney())+"," + StringUtil.tj(entity.getVenId())+" ") ;
			sql.append(","+StringUtil.tj(entity.getCustomerId())+","+StringUtil.tj(entity.getCgzbId())+","+StringUtil.tj(entity.getCgsqId())+","+StringUtil.tj(entity.getOnlineId())+", " + StringUtil.tj(entity.getAcctId()) +"," + StringUtil.tj(entity.getMateId()) +"," + StringUtil.tj(entity.getPerstate())+"," + StringUtil.tj(entity.getStateDate1())+"," + StringUtil.tj(entity.getStateDate2())+"," + StringUtil.tj(entity.getStateDate3())+"," + StringUtil.tj(entity.getStateDate4())+"," + StringUtil.tj(entity.getStateDate5())+"," + StringUtil.tj(entity.getStateDate6())+"," + StringUtil.tj(entity.getExecConNo())+"," + StringUtil.tj(entity.getConNumnerZ())+ "," +StringUtil.tj(entity.getIsBg())+"," + StringUtil.tj(entity.getRenewId())+"," + StringUtil.tj(entity.getDefoutCase())+"," + StringUtil.tj(entity.getIsDefout())+"," + StringUtil.tj(entity.getFj())+ "," +StringUtil.tj(entity.getDefoutDate())+" ") ;
			sql.append(","+StringUtil.tj(entity.getConfermState())+","+StringUtil.tj(entity.getConfermDate())+","+StringUtil.tj(entity.getAgreeId())+","+StringUtil.tj(entity.getSignId())+", " + StringUtil.tj(entity.getSpl()) +"," + StringUtil.tj(entity.getConName()) +"," + StringUtil.tj(entity.getConBz())+"," + StringUtil.tj(entity.getConMs())+"," + StringUtil.tj(entity.getConExec1())+"," + StringUtil.tj(entity.getConExec2())+"," + StringUtil.tj(entity.getConExec3())+"," + StringUtil.tj(entity.getConExec4())+"," + StringUtil.tj(entity.getConExec5())+"," + StringUtil.tj(entity.getConExec6())+"," + StringUtil.tj(entity.getConExec7())+"," + StringUtil.tj(entity.getConExec8())+ "," +StringUtil.tj(entity.getConExec9())+"," + StringUtil.tj(entity.getConExec10())+")") ;
			return sql.toString();
		}else if(obj instanceof ConMarkEntity){//hrp合同标的
			ConMarkEntity entity = (ConMarkEntity) obj;
			StringBuffer sqlm = new StringBuffer();
	 		sqlm.append(" insert into ven_con_mark ");
	 		sqlm.append(" (id, create_name,create_by,create_date, update_name,update_by,update_date,sys_org_code,sys_company_code,bpm_status,");
	 		sqlm.append(" con_main_id,mark_souce,mark_no,mark_name,mark_bz,mark_unit,mark_number,mark_price,mark_money,mark_spece,quid_id,exten1,exten2,exten3,exten4,exten5,mark_ms) ");
	 		sqlm.append(" values ");
	 		sqlm.append(" ("+StringUtil.tj(entity.getId())+","+StringUtil.tj(entity.getCreateName())+","+StringUtil.tj(entity.getCreateBy())+","+StringUtil.tj(entity.getCreateDate())+","+StringUtil.tj(entity.getUpdateName())+","+StringUtil.tj(entity.getUpdateBy())+","+StringUtil.tj(entity.getUpdateDate())+","+StringUtil.tj(entity.getSysOrgCode())+" ");
	 		sqlm.append(","+StringUtil.tj(entity.getSysCompanyCode())+","+StringUtil.tj(entity.getBpmStatus())+","+StringUtil.tj(entity.getConMainId())+","+StringUtil.tj(entity.getMarkSouce())+","+StringUtil.tj(entity.getMarkNo())+","+StringUtil.tj(entity.getMarkName())+","+StringUtil.tj(entity.getMarkBz())+","+StringUtil.tj(entity.getMarkUnit())+" ");
	 		sqlm.append(" ,"+StringUtil.tj(entity.getMarkNumber())+","+StringUtil.tj(entity.getMarkPrice())+","+StringUtil.tj(entity.getMarkMoney())+","+StringUtil.tj(entity.getMarkSpece())+","+StringUtil.tj(entity.getQuidId())+","+StringUtil.tj(entity.getExten1())+","+StringUtil.tj(entity.getExten2())+","+StringUtil.tj(entity.getExten3())+","+StringUtil.tj(entity.getExten4())+","+StringUtil.tj(entity.getExten5())+","+StringUtil.tj(entity.getMarkMs())+" ) ");
	 		return sqlm.toString();
		}else if(obj instanceof PayPlanEntity){//hrp合同付款计划
			PayPlanEntity entity = (PayPlanEntity) obj;
			StringBuffer sqld = new StringBuffer();
			sqld.append(" insert into ven_pay_plan ");
	 		sqld.append(" (id, create_name,create_by,create_date, update_name,update_by,update_date,sys_org_code,sys_company_code,bpm_status,");
	 		sqld.append(" con_id,money_source,pay_date,pay_conditions,pay_rate,pay_money,pay_sm,pay_number,extence1,extence2,extence3,extence4,extence5,extence6,extence7,extence8,extence9,extence10,apply_emp,apply_money,apply_time,is_sender) ");
 			sqld.append(" values ");
 			sqld.append(" ("+StringUtil.tj(entity.getId())+","+StringUtil.tj(entity.getCreateName())+","+StringUtil.tj(entity.getCreateBy())+","+StringUtil.tj(entity.getCreateDate())+","+StringUtil.tj(entity.getUpdateName())+","+StringUtil.tj(entity.getUpdateBy())+","+StringUtil.tj(entity.getUpdateDate())+","+StringUtil.tj(entity.getSysOrgCode())+" ");
 			sqld.append(","+StringUtil.tj(entity.getSysCompanyCode())+","+StringUtil.tj(entity.getBpmStatus())+","+StringUtil.tj(entity.getConId())+","+StringUtil.tj(entity.getMoneySource())+","+StringUtil.tj(entity.getPayDate())+","+StringUtil.tj(entity.getPayConditions())+","+StringUtil.tj(entity.getPayRate())+","+StringUtil.tj(entity.getPayMoney())+","+StringUtil.tj(entity.getPaySm())+","+StringUtil.tj(entity.getPayNumber())+" ");
 			sqld.append(" ,"+StringUtil.tj(entity.getExtence1())+","+StringUtil.tj(entity.getExtence2())+","+StringUtil.tj(entity.getExtence3())+","+StringUtil.tj(entity.getExtence4())+","+StringUtil.tj(entity.getExtence5())+","+StringUtil.tj(entity.getExtence6())+","+StringUtil.tj(entity.getExtence7())+","+StringUtil.tj(entity.getExtence8())+","+StringUtil.tj(entity.getExtence9())+","+StringUtil.tj(entity.getExtence10())+","+StringUtil.tj(entity.getApplyEmp())+","+StringUtil.tj(entity.getApplyMoney())+","+StringUtil.tj(entity.getApplyTime())+","+StringUtil.tj(entity.getIsSender())+" ) ");
 			return sqld.toString();
		}else if(obj instanceof ConMemorabiliaEntity){//hrp合同大事件
			ConMemorabiliaEntity entity = (ConMemorabiliaEntity) obj;
			StringBuffer sqld = new StringBuffer();
 			sqld.append(" insert into  ven_con_memorabilia ");
 			sqld.append(" (id, create_name,create_by,create_date, update_name,update_by,update_date,sys_org_code,sys_company_code,bpm_status,");
	 		sqld.append("  con_id,memorabilia_no,memorabilia_content,memorabilia_bz,execten1,execten2,execten3,execten4,execten5) ");
 			sqld.append(" values ");
 			sqld.append(" ("+StringUtil.tj(entity.getId())+","+StringUtil.tj(entity.getCreateName())+","+StringUtil.tj(entity.getCreateBy())+","+StringUtil.tj(entity.getCreateDate())+","+StringUtil.tj(entity.getUpdateName())+","+StringUtil.tj(entity.getUpdateBy())+","+StringUtil.tj(entity.getUpdateDate())+","+StringUtil.tj(entity.getSysOrgCode())+" ");
 			sqld.append(","+StringUtil.tj(entity.getSysCompanyCode())+","+StringUtil.tj(entity.getBpmStatus())+","+StringUtil.tj(entity.getConId())+","+StringUtil.tj(entity.getMemorabiliaNo())+","+StringUtil.tj(entity.getMemorabiliaContent())+","+StringUtil.tj(entity.getMemorabiliaBz())+","+StringUtil.tj(entity.getExecten1())+","+StringUtil.tj(entity.getExecten2())+","+StringUtil.tj(entity.getExecten3())+","+StringUtil.tj(entity.getExecten4())+","+StringUtil.tj(entity.getExecten5())+" )");
 			return sqld.toString();
		}
	else if(obj instanceof ContBankEntity){//hrp合同银行保函
		ContBankEntity entity = (ContBankEntity) obj;
		StringBuffer sqld = new StringBuffer();
			sqld.append(" insert into  ven_cont_Bank ");
			sqld.append(" (id, create_name,create_by,create_date, update_name,update_by,update_date,sys_org_code,sys_company_code,bpm_status,");
	 		sqld.append("  con_id,bankgua_no,bankgua_name,bankgua_fj,upload_date,bigen_date,failure_date,bankgua_compy,bankgua_money,bangua_bz,execten1,execten2,execten3,execten4,execten5) ");
			sqld.append(" values ");
			sqld.append(" ("+StringUtil.tj(entity.getId())+","+StringUtil.tj(entity.getCreateName())+","+StringUtil.tj(entity.getCreateBy())+","+StringUtil.tj(entity.getCreateDate())+","+StringUtil.tj(entity.getUpdateName())+","+StringUtil.tj(entity.getUpdateBy())+","+StringUtil.tj(entity.getUpdateDate())+","+StringUtil.tj(entity.getSysOrgCode())+" ");
 			sqld.append(","+StringUtil.tj(entity.getSysCompanyCode())+","+StringUtil.tj(entity.getBpmStatus())+","+StringUtil.tj(entity.getConId())+","+StringUtil.tj(entity.getBankguaNo())+","+StringUtil.tj(entity.getBankguaName())+","+StringUtil.tj(entity.getBankguaFj())+"," +StringUtil.tj(entity.getUploadDate())+"," +StringUtil.tj(entity.getBigenDate())+"," +StringUtil.tj(entity.getFailureDate())+"," +StringUtil.tj(entity.getBankguaCompy())+"," +StringUtil.tj(entity.getBankguaMoney())+"," +StringUtil.tj(entity.getBanguaBz())+","+StringUtil.tj(entity.getExecten1())+","+StringUtil.tj(entity.getExecten2())+","+StringUtil.tj(entity.getExecten3())+","+StringUtil.tj(entity.getExecten4())+","+StringUtil.tj(entity.getExecten5())+" )");
			return sqld.toString();
	}
		return null;
	}
	/**
	 * zxl
	 * @param obj
	 */
	/*public static String synInsert(Object obj){
		StringBuffer sql = new StringBuffer();
	 if(obj instanceof MateSupBidEntity){//投标信息
			MateSupBidEntity entity = (MateSupBidEntity) obj;
			sql.append("insert into vendor_sup_bid ");
			sql.append(" (id,create_name,create_by,create_date,update_name,update_by,update_date,sys_org_code ");
			sql.append(",sys_company_code,bpm_status,bid,bid_d_id,sup_state,bond_money,bond_state,linkman ");
			sql.append(",telephone,email,offer_price,remark)  ");
			sql.append(" select  ");
			sql.append(" id,create_name,create_by,create_date,update_name,update_by,update_date,sys_org_code ");
			sql.append(",sys_company_code,bpm_status,bid,bid_d_id,sup_state,bond_money,bond_state,linkman ");
			sql.append(",telephone,email,offer_price,remark ");
			sql.append(" from vendor_sup_bid where id ='"+entity.getId()+"' ");
		}   
		
		
		return sql.toString();
	}*/
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
		  if(obj instanceof ConInformationEntity){//投标信息
			  ConInformationEntity entity = (ConInformationEntity) obj;
			return "delete  from ven_con_information where id ='"+entity.getId()+"' ";
		}   else if(obj instanceof ConMarkEntity){//hrp供应商投标物资
			ConMarkEntity d = (ConMarkEntity) obj;
	 		return " delete  from ven_con_mark where id ='"+d.getId()+"'"; 
		}else if(obj instanceof PayPlanEntity){//hrp供应商投标物资明细
			PayPlanEntity d = (PayPlanEntity) obj;
	 		return " delete  from ven_pay_plan where id ='"+d.getId()+"'"; 
		}
		else if(obj instanceof ContBankEntity){//hrp供应商投标物资明细
			ContBankEntity d = (ContBankEntity) obj;
			return " delete  from ven_cont_Bank where id ='"+d.getId()+"'"; 
		}
		else if(obj instanceof ConMemorabiliaEntity){//hrp供应商投标物资明细
			ConMemorabiliaEntity d = (ConMemorabiliaEntity) obj;
	 		return " delete  from ven_con_memorabilia where id ='"+d.getId()+"'"; 
		}
		return null;
	}
}


