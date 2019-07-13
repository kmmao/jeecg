package cn.com.linkwide.cont.payplan.service.impl;
import cn.com.linkwide.cont.payplan.service.PayPlanServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import cn.com.linkwide.cont.payplan.entity.PayPlanEntity;
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

@Service("payPlanService")
@Transactional
public class PayPlanServiceImpl extends CommonServiceImpl implements PayPlanServiceI {

	
 	public void delete(PayPlanEntity entity) throws Exception{
 		super.delete(entity);
 		//执行删除操作增强业务
		this.doDelBus(entity);
 	}
 	
 	public Serializable save(PayPlanEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		//执行新增操作增强业务
 		this.doAddBus(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(PayPlanEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 		//执行更新操作增强业务
 		this.doUpdateBus(entity);
 	}
 	
 	/**
	 * 新增操作增强业务
	 * @param t
	 * @return
	 */
	private void doAddBus(PayPlanEntity t) throws Exception{
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
	private void doUpdateBus(PayPlanEntity t) throws Exception{
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
	private void doDelBus(PayPlanEntity t) throws Exception{
	    //-----------------sql增强 start----------------------------
	 	//-----------------sql增强 end------------------------------
	 	
	 	//-----------------java增强 start---------------------------
	 	//-----------------java增强 end-----------------------------
 	}
 	
 	private Map<String,Object> populationMap(PayPlanEntity t){
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
		map.put("con_id", t.getConId());
		map.put("money_source", t.getMoneySource());
		map.put("pay_date", t.getPayDate());
		map.put("pay_conditions", t.getPayConditions());
		map.put("pay_rate", t.getPayRate());
		map.put("pay_money", t.getPayMoney());
		map.put("pay_sm", t.getPaySm());
		map.put("pay_number", t.getPayNumber());
		map.put("extence1", t.getExtence1());
		map.put("extence2", t.getExtence2());
		map.put("extence3", t.getExtence3());
		map.put("extence4", t.getExtence4());
		map.put("extence5", t.getExtence5());
		map.put("extence6", t.getExtence6());
		map.put("extence7", t.getExtence7());
		map.put("extence8", t.getExtence8());
		map.put("extence9", t.getExtence9());
		map.put("extence10", t.getExtence10());
		return map;
	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @param t
	 * @return
	 */
 	public String replaceVal(String sql,PayPlanEntity t){
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
 		sql  = sql.replace("#{con_id}",String.valueOf(t.getConId()));
 		sql  = sql.replace("#{money_source}",String.valueOf(t.getMoneySource()));
 		sql  = sql.replace("#{pay_date}",String.valueOf(t.getPayDate()));
 		sql  = sql.replace("#{pay_conditions}",String.valueOf(t.getPayConditions()));
 		sql  = sql.replace("#{pay_rate}",String.valueOf(t.getPayRate()));
 		sql  = sql.replace("#{pay_money}",String.valueOf(t.getPayMoney()));
 		sql  = sql.replace("#{pay_sm}",String.valueOf(t.getPaySm()));
 		sql  = sql.replace("#{pay_number}",String.valueOf(t.getPayNumber()));
 		sql  = sql.replace("#{extence1}",String.valueOf(t.getExtence1()));
 		sql  = sql.replace("#{extence2}",String.valueOf(t.getExtence2()));
 		sql  = sql.replace("#{extence3}",String.valueOf(t.getExtence3()));
 		sql  = sql.replace("#{extence4}",String.valueOf(t.getExtence4()));
 		sql  = sql.replace("#{extence5}",String.valueOf(t.getExtence5()));
 		sql  = sql.replace("#{extence6}",String.valueOf(t.getExtence6()));
 		sql  = sql.replace("#{extence7}",String.valueOf(t.getExtence7()));
 		sql  = sql.replace("#{extence8}",String.valueOf(t.getExtence8()));
 		sql  = sql.replace("#{extence9}",String.valueOf(t.getExtence9()));
 		sql  = sql.replace("#{extence10}",String.valueOf(t.getExtence10()));
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
					javaInter.execute("pay_plan",data);
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("执行JAVA增强出现异常！");
			} 
		}
 	}
}