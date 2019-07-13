package cn.com.linkwide.cont.negotiation.confinalconfirmbill.service.impl;
import cn.com.linkwide.cont.negotiation.confinalconfirmbill.service.ConFinalConfirmbillServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import cn.com.linkwide.cont.negotiation.confinalconfirmbill.entity.ConFinalConfirmbillEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.io.Serializable;
import org.jeecgframework.core.util.ApplicationContextUtil;
import org.jeecgframework.core.util.MyClassLoader;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.web.cgform.enhance.CgformEnhanceJavaInter;

@Service("conFinalConfirmbillService")
@Transactional
public class ConFinalConfirmbillServiceImpl extends CommonServiceImpl implements ConFinalConfirmbillServiceI {

	
 	public void delete(ConFinalConfirmbillEntity entity) throws Exception{
 		super.delete(entity);
 		//执行删除操作增强业务
		this.doDelBus(entity);
 	}
 	
 	public Serializable save(ConFinalConfirmbillEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		//执行新增操作增强业务
 		this.doAddBus(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(ConFinalConfirmbillEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 		//执行更新操作增强业务
 		this.doUpdateBus(entity);
 	}
 	
 	/**
	 * 新增操作增强业务
	 * @param t
	 * @return
	 */
	private void doAddBus(ConFinalConfirmbillEntity t) throws Exception{
		//-----------------sql增强 start----------------------------
	 	//-----------------sql增强 end------------------------------
	 	
	 	//-----------------java增强 start---------------------------
	 	//-----------------java增强 end-----------------------------
 	}
 	/**
	 * 更新操作增强业务
	 * @param t
	 * @return
	 */
	private void doUpdateBus(ConFinalConfirmbillEntity t) throws Exception{
		//-----------------sql增强 start----------------------------
	 	//-----------------sql增强 end------------------------------
	 	
	 	//-----------------java增强 start---------------------------
	 	//-----------------java增强 end-----------------------------
 	}
 	/**
	 * 删除操作增强业务
	 * @param id
	 * @return
	 */
	private void doDelBus(ConFinalConfirmbillEntity t) throws Exception{
	    //-----------------sql增强 start----------------------------
	 	//-----------------sql增强 end------------------------------
	 	
	 	//-----------------java增强 start---------------------------
	 	//-----------------java增强 end-----------------------------
 	}
 	
 	private Map<String,Object> populationMap(ConFinalConfirmbillEntity t){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", t.getId());
		map.put("create_name", t.getCreateName());
		map.put("create_by", t.getCreateBy());
		map.put("create_date", t.getCreateDate());
		map.put("update_name", t.getUpdateName());
		map.put("update_by", t.getUpdateBy());
		map.put("update_date", t.getUpdateDate());
		map.put("sys_org_code", t.getSysOrgCode());
		map.put("sys_company_code", t.getSysCompanyCode());
		map.put("bpm_status", t.getBpmStatus());
		map.put("equ_name", t.getEquName());
		map.put("equ_code", t.getEquCode());
		map.put("ven_id", t.getVenId());
		map.put("ven_name", t.getVenName());
		map.put("dept_id", t.getDeptId());
		map.put("dept_name", t.getDeptName());
		map.put("spec_type", t.getSpecType());
		map.put("offer_price", t.getOfferPrice());
		map.put("pur_price", t.getPurPrice());
		map.put("sevice_content", t.getSeviceContent());
		map.put("req_id", t.getReqId());
		map.put("apply_id", t.getApplyId());
		map.put("offer_id", t.getOfferId());
		map.put("final_price", t.getFinalPrice());
		map.put("final_content", t.getFinalContent());
		map.put("bx_years", t.getBxYears());
		map.put("extend1", t.getExtend1());
		map.put("extend2", t.getExtend2());
		map.put("extend3", t.getExtend3());
		map.put("extend4", t.getExtend4());
		map.put("extend5", t.getExtend5());
		map.put("extend6", t.getExtend6());
		map.put("is_resend", t.getIsResend());
		map.put("is_confirm", t.getIsConfirm());
		map.put("bill_state", t.getBillState());
		map.put("confirm_date", t.getConfirmDate());
		map.put("resend_emp", t.getResendEmp());
		return map;
	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @param t
	 * @return
	 */
 	public String replaceVal(String sql,ConFinalConfirmbillEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{create_name}",String.valueOf(t.getCreateName()));
 		sql  = sql.replace("#{create_by}",String.valueOf(t.getCreateBy()));
 		sql  = sql.replace("#{create_date}",String.valueOf(t.getCreateDate()));
 		sql  = sql.replace("#{update_name}",String.valueOf(t.getUpdateName()));
 		sql  = sql.replace("#{update_by}",String.valueOf(t.getUpdateBy()));
 		sql  = sql.replace("#{update_date}",String.valueOf(t.getUpdateDate()));
 		sql  = sql.replace("#{sys_org_code}",String.valueOf(t.getSysOrgCode()));
 		sql  = sql.replace("#{sys_company_code}",String.valueOf(t.getSysCompanyCode()));
 		sql  = sql.replace("#{bpm_status}",String.valueOf(t.getBpmStatus()));
 		sql  = sql.replace("#{equ_name}",String.valueOf(t.getEquName()));
 		sql  = sql.replace("#{equ_code}",String.valueOf(t.getEquCode()));
 		sql  = sql.replace("#{ven_id}",String.valueOf(t.getVenId()));
 		sql  = sql.replace("#{ven_name}",String.valueOf(t.getVenName()));
 		sql  = sql.replace("#{dept_id}",String.valueOf(t.getDeptId()));
 		sql  = sql.replace("#{dept_name}",String.valueOf(t.getDeptName()));
 		sql  = sql.replace("#{spec_type}",String.valueOf(t.getSpecType()));
 		sql  = sql.replace("#{offer_price}",String.valueOf(t.getOfferPrice()));
 		sql  = sql.replace("#{pur_price}",String.valueOf(t.getPurPrice()));
 		sql  = sql.replace("#{sevice_content}",String.valueOf(t.getSeviceContent()));
 		sql  = sql.replace("#{req_id}",String.valueOf(t.getReqId()));
 		sql  = sql.replace("#{apply_id}",String.valueOf(t.getApplyId()));
 		sql  = sql.replace("#{offer_id}",String.valueOf(t.getOfferId()));
 		sql  = sql.replace("#{final_price}",String.valueOf(t.getFinalPrice()));
 		sql  = sql.replace("#{final_content}",String.valueOf(t.getFinalContent()));
 		sql  = sql.replace("#{bx_years}",String.valueOf(t.getBxYears()));
 		sql  = sql.replace("#{extend1}",String.valueOf(t.getExtend1()));
 		sql  = sql.replace("#{extend2}",String.valueOf(t.getExtend2()));
 		sql  = sql.replace("#{extend3}",String.valueOf(t.getExtend3()));
 		sql  = sql.replace("#{extend4}",String.valueOf(t.getExtend4()));
 		sql  = sql.replace("#{extend5}",String.valueOf(t.getExtend5()));
 		sql  = sql.replace("#{extend6}",String.valueOf(t.getExtend6()));
 		sql  = sql.replace("#{is_resend}",String.valueOf(t.getIsResend()));
 		sql  = sql.replace("#{is_confirm}",String.valueOf(t.getIsConfirm()));
 		sql  = sql.replace("#{bill_state}",String.valueOf(t.getBillState()));
 		sql  = sql.replace("#{confirm_date}",String.valueOf(t.getConfirmDate()));
 		sql  = sql.replace("#{resend_emp}",String.valueOf(t.getResendEmp()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}
 	
 	/**
	 * 执行JAVA增强
	 */
 	private void executeJavaExtend(String cgJavaType,String cgJavaValue,Map<String,Object> data) throws Exception {
 		if(StringUtil.isNotEmpty(cgJavaValue)){
			Object obj = null;
			try {
				if("class".equals(cgJavaType)){
					//因新增时已经校验了实例化是否可以成功，所以这块就不需要再做一次判断
					obj = MyClassLoader.getClassByScn(cgJavaValue).newInstance();
				}else if("spring".equals(cgJavaType)){
					obj = ApplicationContextUtil.getContext().getBean(cgJavaValue);
				}
				if(obj instanceof CgformEnhanceJavaInter){
					CgformEnhanceJavaInter javaInter = (CgformEnhanceJavaInter) obj;
					javaInter.execute("con_final_confirmBill",data);
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("执行JAVA增强出现异常！");
			} 
		}
 	}
}