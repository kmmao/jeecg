package cn.com.linkwide.cont.negotiation.conwarrantybill.service.impl;
import cn.com.linkwide.cont.negotiation.conwarrantybill.service.ConWarrantyBillServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import cn.com.linkwide.cont.negotiation.conwarrantybill.entity.ConWarrantyBillEntity;
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

@Service("conWarrantyBillService")
@Transactional
public class ConWarrantyBillServiceImpl extends CommonServiceImpl implements ConWarrantyBillServiceI {

	
 	public void delete(ConWarrantyBillEntity entity) throws Exception{
 		super.delete(entity);
 		//执行删除操作增强业务
		this.doDelBus(entity);
 	}
 	
 	public Serializable save(ConWarrantyBillEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		//执行新增操作增强业务
 		this.doAddBus(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(ConWarrantyBillEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 		//执行更新操作增强业务
 		this.doUpdateBus(entity);
 	}
 	
 	/**
	 * 新增操作增强业务
	 * @param t
	 * @return
	 */
	private void doAddBus(ConWarrantyBillEntity t) throws Exception{
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
	private void doUpdateBus(ConWarrantyBillEntity t) throws Exception{
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
	private void doDelBus(ConWarrantyBillEntity t) throws Exception{
	    //-----------------sql增强 start----------------------------
	 	//-----------------sql增强 end------------------------------
	 	
	 	//-----------------java增强 start---------------------------
	 	//-----------------java增强 end-----------------------------
 	}
 	
 	private Map<String,Object> populationMap(ConWarrantyBillEntity t){
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
		map.put("dept_code", t.getDeptCode());
		map.put("dept_name", t.getDeptName());
		map.put("apply_date", t.getApplyDate());
		map.put("equ_code", t.getEquCode());
		map.put("equ_name", t.getEquName());
		map.put("equ_card", t.getEquCard());
		map.put("spec_type", t.getSpecType());
		map.put("pur_price", t.getPurPrice());
		map.put("pur_date", t.getPurDate());
		map.put("birth_factory", t.getBirthFactory());
		map.put("use_type", t.getUseType());
		map.put("is_charge", t.getIsCharge());
		map.put("average_cost", t.getAverageCost());
		map.put("annual_check_number", t.getAnnualCheckNumber());
		map.put("annual_income", t.getAnnualIncome());
		map.put("apply_category", t.getApplyCategory());
		map.put("guarantee_type", t.getGuaranteeType());
		map.put("guarantee_type_qt", t.getGuaranteeTypeQt());
		map.put("guarantee_years", t.getGuaranteeYears());
		map.put("guarantee_years_qt", t.getGuaranteeYearsQt());
		map.put("factory_choice", t.getFactoryChoice());
		map.put("factory_choice_qt", t.getFactoryChoiceQt());
		map.put("cost_group", t.getCostGroup());
		map.put("dept_cost", t.getDeptCost());
		map.put("dept_cost_type", t.getDeptCostType());
		map.put("dept_cost_type_qt", t.getDeptCostTypeQt());
		map.put("cost_research", t.getCostResearch());
		map.put("research_name", t.getResearchName());
		map.put("research_version", t.getResearchVersion());
		map.put("hos_research", t.getHosResearch());
		map.put("dept_emp", t.getDeptEmp());
		map.put("emp_tel", t.getEmpTel());
		map.put("use_type_by", t.getUseTypeBy());
		map.put("apply_category_by", t.getApplyCategoryBy());
		map.put("guarantee_type_jsb", t.getGuaranteeTypeJsb());
		map.put("guarantee_type_qb", t.getGuaranteeTypeQb());
		map.put("factory_choice_yc", t.getFactoryChoiceYc());
		map.put("factory_choice_sf", t.getFactoryChoiceSf());
		map.put("factory_choice_by", t.getFactoryChoiceBy());
		map.put("extend1", t.getExtend1());
		map.put("extend2", t.getExtend2());
		map.put("extend3", t.getExtend3());
		map.put("extend4", t.getExtend4());
		map.put("extend5", t.getExtend5());
		map.put("apply_code", t.getApplyCode());
		map.put("confirm_emp", t.getConfirmEmp());
		map.put("bill_state", t.getBillState());
		map.put("confirm_date", t.getConfirmDate());
		map.put("is_release", t.getIsRelease());
		map.put("release_date", t.getReleaseDate());
		map.put("start_date", t.getStartDate());
		map.put("end_date", t.getEndDate());
		return map;
	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @param t
	 * @return
	 */
 	public String replaceVal(String sql,ConWarrantyBillEntity t){
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
 		sql  = sql.replace("#{dept_code}",String.valueOf(t.getDeptCode()));
 		sql  = sql.replace("#{dept_name}",String.valueOf(t.getDeptName()));
 		sql  = sql.replace("#{apply_date}",String.valueOf(t.getApplyDate()));
 		sql  = sql.replace("#{equ_code}",String.valueOf(t.getEquCode()));
 		sql  = sql.replace("#{equ_name}",String.valueOf(t.getEquName()));
 		sql  = sql.replace("#{equ_card}",String.valueOf(t.getEquCard()));
 		sql  = sql.replace("#{spec_type}",String.valueOf(t.getSpecType()));
 		sql  = sql.replace("#{pur_price}",String.valueOf(t.getPurPrice()));
 		sql  = sql.replace("#{pur_date}",String.valueOf(t.getPurDate()));
 		sql  = sql.replace("#{birth_factory}",String.valueOf(t.getBirthFactory()));
 		sql  = sql.replace("#{use_type}",String.valueOf(t.getUseType()));
 		sql  = sql.replace("#{is_charge}",String.valueOf(t.getIsCharge()));
 		sql  = sql.replace("#{average_cost}",String.valueOf(t.getAverageCost()));
 		sql  = sql.replace("#{annual_check_number}",String.valueOf(t.getAnnualCheckNumber()));
 		sql  = sql.replace("#{annual_income}",String.valueOf(t.getAnnualIncome()));
 		sql  = sql.replace("#{apply_category}",String.valueOf(t.getApplyCategory()));
 		sql  = sql.replace("#{guarantee_type}",String.valueOf(t.getGuaranteeType()));
 		sql  = sql.replace("#{guarantee_type_qt}",String.valueOf(t.getGuaranteeTypeQt()));
 		sql  = sql.replace("#{guarantee_years}",String.valueOf(t.getGuaranteeYears()));
 		sql  = sql.replace("#{guarantee_years_qt}",String.valueOf(t.getGuaranteeYearsQt()));
 		sql  = sql.replace("#{factory_choice}",String.valueOf(t.getFactoryChoice()));
 		sql  = sql.replace("#{factory_choice_qt}",String.valueOf(t.getFactoryChoiceQt()));
 		sql  = sql.replace("#{cost_group}",String.valueOf(t.getCostGroup()));
 		sql  = sql.replace("#{dept_cost}",String.valueOf(t.getDeptCost()));
 		sql  = sql.replace("#{dept_cost_type}",String.valueOf(t.getDeptCostType()));
 		sql  = sql.replace("#{dept_cost_type_qt}",String.valueOf(t.getDeptCostTypeQt()));
 		sql  = sql.replace("#{cost_research}",String.valueOf(t.getCostResearch()));
 		sql  = sql.replace("#{research_name}",String.valueOf(t.getResearchName()));
 		sql  = sql.replace("#{research_version}",String.valueOf(t.getResearchVersion()));
 		sql  = sql.replace("#{hos_research}",String.valueOf(t.getHosResearch()));
 		sql  = sql.replace("#{dept_emp}",String.valueOf(t.getDeptEmp()));
 		sql  = sql.replace("#{emp_tel}",String.valueOf(t.getEmpTel()));
 		sql  = sql.replace("#{use_type_by}",String.valueOf(t.getUseTypeBy()));
 		sql  = sql.replace("#{apply_category_by}",String.valueOf(t.getApplyCategoryBy()));
 		sql  = sql.replace("#{guarantee_type_jsb}",String.valueOf(t.getGuaranteeTypeJsb()));
 		sql  = sql.replace("#{guarantee_type_qb}",String.valueOf(t.getGuaranteeTypeQb()));
 		sql  = sql.replace("#{factory_choice_yc}",String.valueOf(t.getFactoryChoiceYc()));
 		sql  = sql.replace("#{factory_choice_sf}",String.valueOf(t.getFactoryChoiceSf()));
 		sql  = sql.replace("#{factory_choice_by}",String.valueOf(t.getFactoryChoiceBy()));
 		sql  = sql.replace("#{extend1}",String.valueOf(t.getExtend1()));
 		sql  = sql.replace("#{extend2}",String.valueOf(t.getExtend2()));
 		sql  = sql.replace("#{extend3}",String.valueOf(t.getExtend3()));
 		sql  = sql.replace("#{extend4}",String.valueOf(t.getExtend4()));
 		sql  = sql.replace("#{extend5}",String.valueOf(t.getExtend5()));
 		sql  = sql.replace("#{apply_code}",String.valueOf(t.getApplyCode()));
 		sql  = sql.replace("#{confirm_emp}",String.valueOf(t.getConfirmEmp()));
 		sql  = sql.replace("#{bill_state}",String.valueOf(t.getBillState()));
 		sql  = sql.replace("#{confirm_date}",String.valueOf(t.getConfirmDate()));
 		sql  = sql.replace("#{is_release}",String.valueOf(t.getIsRelease()));
 		sql  = sql.replace("#{release_date}",String.valueOf(t.getReleaseDate()));
 		sql  = sql.replace("#{start_date}",String.valueOf(t.getStartDate()));
 		sql  = sql.replace("#{end_date}",String.valueOf(t.getEndDate()));
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
					javaInter.execute("con_warranty_bill",data);
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("执行JAVA增强出现异常！");
			} 
		}
 	}
}