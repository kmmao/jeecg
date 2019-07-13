package cn.com.linkwide.ba.bavoucher.service.impl;
import cn.com.linkwide.ba.bavoucher.service.BaVoucherServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import cn.com.linkwide.ba.bavoucher.entity.BaVoucherEntity;
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

@Service("baVoucherService")
@Transactional
public class BaVoucherServiceImpl extends CommonServiceImpl implements BaVoucherServiceI {

	
 	public void delete(BaVoucherEntity entity) throws Exception{
 		super.delete(entity);
 		//执行删除操作增强业务
		this.doDelBus(entity);
 	}
 	
 	public Serializable save(BaVoucherEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		//执行新增操作增强业务
 		this.doAddBus(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(BaVoucherEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 		//执行更新操作增强业务
 		this.doUpdateBus(entity);
 	}
 	
 	/**
	 * 新增操作增强业务
	 * @param t
	 * @return
	 */
	private void doAddBus(BaVoucherEntity t) throws Exception{
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
	private void doUpdateBus(BaVoucherEntity t) throws Exception{
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
	private void doDelBus(BaVoucherEntity t) throws Exception{
	    //-----------------sql增强 start----------------------------
	 	//-----------------sql增强 end------------------------------
	 	
	 	//-----------------java增强 start---------------------------
	 	//-----------------java增强 end-----------------------------
 	}
 	
 	private Map<String,Object> populationMap(BaVoucherEntity t){
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
		map.put("bus_model", t.getBusModel());
		map.put("bus_function", t.getBusFunction());
		map.put("bill_code", t.getBillCode());
		map.put("bill_date", t.getBillDate());
		map.put("vouc_summary", t.getVoucSummary());
		map.put("subject", t.getSubject());
		map.put("balance_direction", t.getBalanceDirection());
		map.put("money", t.getMoney());
		map.put("dept_id", t.getDeptId());
		map.put("emp_id", t.getEmpId());
		map.put("vendor", t.getVendor());
		map.put("customer", t.getCustomer());
		map.put("proj_classes", t.getProjClasses());
		map.put("proj_type", t.getProjType());
		map.put("project", t.getProject());
		map.put("bus_num", t.getBusNum());
		map.put("financial_type", t.getFinancialType());
		map.put("fund_source", t.getFundSource());
		return map;
	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @param t
	 * @return
	 */
 	public String replaceVal(String sql,BaVoucherEntity t){
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
 		sql  = sql.replace("#{bus_model}",String.valueOf(t.getBusModel()));
 		sql  = sql.replace("#{bus_function}",String.valueOf(t.getBusFunction()));
 		sql  = sql.replace("#{bill_code}",String.valueOf(t.getBillCode()));
 		sql  = sql.replace("#{bill_date}",String.valueOf(t.getBillDate()));
 		sql  = sql.replace("#{vouc_summary}",String.valueOf(t.getVoucSummary()));
 		sql  = sql.replace("#{subject}",String.valueOf(t.getSubject()));
 		sql  = sql.replace("#{balance_direction}",String.valueOf(t.getBalanceDirection()));
 		sql  = sql.replace("#{money}",String.valueOf(t.getMoney()));
 		sql  = sql.replace("#{dept_id}",String.valueOf(t.getDeptId()));
 		sql  = sql.replace("#{emp_id}",String.valueOf(t.getEmpId()));
 		sql  = sql.replace("#{vendor}",String.valueOf(t.getVendor()));
 		sql  = sql.replace("#{customer}",String.valueOf(t.getCustomer()));
 		sql  = sql.replace("#{proj_classes}",String.valueOf(t.getProjClasses()));
 		sql  = sql.replace("#{proj_type}",String.valueOf(t.getProjType()));
 		sql  = sql.replace("#{project}",String.valueOf(t.getProject()));
 		sql  = sql.replace("#{bus_num}",String.valueOf(t.getBusNum()));
 		sql  = sql.replace("#{financial_type}",String.valueOf(t.getFinancialType()));
 		sql  = sql.replace("#{fund_source}",String.valueOf(t.getFundSource()));
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
					javaInter.execute("ba_voucher",data);
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("执行JAVA增强出现异常！");
			} 
		}
 	}
}