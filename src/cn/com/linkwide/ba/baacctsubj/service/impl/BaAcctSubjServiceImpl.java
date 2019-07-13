package cn.com.linkwide.ba.baacctsubj.service.impl;
import cn.com.linkwide.ba.baacctsubj.service.BaAcctSubjServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import cn.com.linkwide.ba.baacctsubj.entity.BaAcctSubjEntity;
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

@Service("baAcctSubjService")
@Transactional
public class BaAcctSubjServiceImpl extends CommonServiceImpl implements BaAcctSubjServiceI {

	
 	public void delete(BaAcctSubjEntity entity) throws Exception{
 		super.delete(entity);
 		//执行删除操作增强业务
		this.doDelBus(entity);
 	}
 	
 	public Serializable save(BaAcctSubjEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		//执行新增操作增强业务
 		this.doAddBus(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(BaAcctSubjEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 		//执行更新操作增强业务
 		this.doUpdateBus(entity);
 	}
 	
 	/**
	 * 新增操作增强业务
	 * @param t
	 * @return
	 */
	private void doAddBus(BaAcctSubjEntity t) throws Exception{
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
	private void doUpdateBus(BaAcctSubjEntity t) throws Exception{
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
	private void doDelBus(BaAcctSubjEntity t) throws Exception{
	    //-----------------sql增强 start----------------------------
	 	//-----------------sql增强 end------------------------------
	 	
	 	//-----------------java增强 start---------------------------
	 	//-----------------java增强 end-----------------------------
 	}
 	
 	private Map<String,Object> populationMap(BaAcctSubjEntity t){
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
		map.put("sub_code", t.getSubCode());
		map.put("sub_name", t.getSubName());
		map.put("parent_id", t.getParentId());
		map.put("is_depart_calculate", t.getIsDepartCalculate());
		map.put("is_personal_calculate", t.getIsPersonalCalculate());
		map.put("is_supplier_calculate", t.getIsSupplierCalculate());
		map.put("is_customer_calculate", t.getIsCustomerCalculate());
		map.put("balance_direction", t.getBalanceDirection());
		map.put("is_project_calculate", t.getIsProjectCalculate());
		map.put("pac_id", t.getPacId());
		map.put("acct_year", t.getAcctYear());
		map.put("fund_source", t.getFundSource());
		map.put("patient_id", t.getPatientId());
		map.put("extend1", t.getExtend1());
		map.put("extend2", t.getExtend2());
		map.put("extend3", t.getExtend3());
		map.put("extend4", t.getExtend4());
		map.put("extend5", t.getExtend5());
		map.put("extend6", t.getExtend6());
		map.put("extend7", t.getExtend7());
		map.put("extend8", t.getExtend8());
		map.put("extend9", t.getExtend9());
		map.put("extend10", t.getExtend10());
		map.put("spell", t.getSpell());
		return map;
	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @param t
	 * @return
	 */
 	public String replaceVal(String sql,BaAcctSubjEntity t){
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
 		sql  = sql.replace("#{sub_code}",String.valueOf(t.getSubCode()));
 		sql  = sql.replace("#{sub_name}",String.valueOf(t.getSubName()));
 		sql  = sql.replace("#{parent_id}",String.valueOf(t.getParentId()));
 		sql  = sql.replace("#{is_depart_calculate}",String.valueOf(t.getIsDepartCalculate()));
 		sql  = sql.replace("#{is_personal_calculate}",String.valueOf(t.getIsPersonalCalculate()));
 		sql  = sql.replace("#{is_supplier_calculate}",String.valueOf(t.getIsSupplierCalculate()));
 		sql  = sql.replace("#{is_customer_calculate}",String.valueOf(t.getIsCustomerCalculate()));
 		sql  = sql.replace("#{balance_direction}",String.valueOf(t.getBalanceDirection()));
 		sql  = sql.replace("#{is_project_calculate}",String.valueOf(t.getIsProjectCalculate()));
 		sql  = sql.replace("#{pac_id}",String.valueOf(t.getPacId()));
 		sql  = sql.replace("#{acct_year}",String.valueOf(t.getAcctYear()));
 		sql  = sql.replace("#{fund_source}",String.valueOf(t.getFundSource()));
 		sql  = sql.replace("#{patient_id}",String.valueOf(t.getPatientId()));
 		sql  = sql.replace("#{extend1}",String.valueOf(t.getExtend1()));
 		sql  = sql.replace("#{extend2}",String.valueOf(t.getExtend2()));
 		sql  = sql.replace("#{extend3}",String.valueOf(t.getExtend3()));
 		sql  = sql.replace("#{extend4}",String.valueOf(t.getExtend4()));
 		sql  = sql.replace("#{extend5}",String.valueOf(t.getExtend5()));
 		sql  = sql.replace("#{extend6}",String.valueOf(t.getExtend6()));
 		sql  = sql.replace("#{extend7}",String.valueOf(t.getExtend7()));
 		sql  = sql.replace("#{extend8}",String.valueOf(t.getExtend8()));
 		sql  = sql.replace("#{extend9}",String.valueOf(t.getExtend9()));
 		sql  = sql.replace("#{extend10}",String.valueOf(t.getExtend10()));
 		sql  = sql.replace("#{spell}",String.valueOf(t.getSpell()));
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
					javaInter.execute("ba_acct_subj",data);
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("执行JAVA增强出现异常！");
			} 
		}
 	}
}