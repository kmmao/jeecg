package cn.com.linkwide.cont.conmanage.service.impl;
import cn.com.linkwide.cont.conmanage.service.ContractManageServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import cn.com.linkwide.cont.conmanage.entity.ContractManageEntity;
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

@Service("contractManageService")
@Transactional
public class ContractManageServiceImpl extends CommonServiceImpl implements ContractManageServiceI {

	
 	public void delete(ContractManageEntity entity) throws Exception{
 		super.delete(entity);
 		//执行删除操作增强业务
		this.doDelBus(entity);
 	}
 	
 	public Serializable save(ContractManageEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		//执行新增操作增强业务
 		this.doAddBus(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(ContractManageEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 		//执行更新操作增强业务
 		this.doUpdateBus(entity);
 	}
 	
 	/**
	 * 新增操作增强业务
	 * @param t
	 * @return
	 */
	private void doAddBus(ContractManageEntity t) throws Exception{
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
	private void doUpdateBus(ContractManageEntity t) throws Exception{
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
	private void doDelBus(ContractManageEntity t) throws Exception{
	    //-----------------sql增强 start----------------------------
	 	//-----------------sql增强 end------------------------------
	 	
	 	//-----------------java增强 start---------------------------
	 	//-----------------java增强 end-----------------------------
 	}
 	
 	private Map<String,Object> populationMap(ContractManageEntity t){
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
		map.put("code", t.getCode());
		map.put("name", t.getName());
		map.put("con_type", t.getConType());
		map.put("sign_date", t.getSignDate());
		map.put("dept", t.getDept());
		map.put("amount_money", t.getAmountMoney());
		map.put("hosp_id", t.getHospId());
		map.put("pay_meth", t.getPayMeth());
		map.put("item_id", t.getItemId());
		map.put("ven_id", t.getVenId());
		map.put("con_state", t.getConState());
		map.put("is_bid", t.getIsBid());
		map.put("org_type", t.getOrgType());
		map.put("purc_type", t.getPurcType());
		map.put("fist_party", t.getFistParty());
		map.put("second_party", t.getSecondParty());
		map.put("begin_date", t.getBeginDate());
		map.put("end_date", t.getEndDate());
		map.put("con_group", t.getConGroup());
		map.put("change_money", t.getChangeMoney());
		map.put("bond_type", t.getBondType());
		map.put("bond_money", t.getBondMoney());
		map.put("remark", t.getRemark());
		map.put("parent_con", t.getParentCon());
		map.put("pay_or_rec", t.getPayOrRec());
		return map;
	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @param t
	 * @return
	 */
 	public String replaceVal(String sql,ContractManageEntity t){
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
 		sql  = sql.replace("#{code}",String.valueOf(t.getCode()));
 		sql  = sql.replace("#{name}",String.valueOf(t.getName()));
 		sql  = sql.replace("#{con_type}",String.valueOf(t.getConType()));
 		sql  = sql.replace("#{sign_date}",String.valueOf(t.getSignDate()));
 		sql  = sql.replace("#{dept}",String.valueOf(t.getDept()));
 		sql  = sql.replace("#{amount_money}",String.valueOf(t.getAmountMoney()));
 		sql  = sql.replace("#{hosp_id}",String.valueOf(t.getHospId()));
 		sql  = sql.replace("#{pay_meth}",String.valueOf(t.getPayMeth()));
 		sql  = sql.replace("#{item_id}",String.valueOf(t.getItemId()));
 		sql  = sql.replace("#{ven_id}",String.valueOf(t.getVenId()));
 		sql  = sql.replace("#{con_state}",String.valueOf(t.getConState()));
 		sql  = sql.replace("#{is_bid}",String.valueOf(t.getIsBid()));
 		sql  = sql.replace("#{org_type}",String.valueOf(t.getOrgType()));
 		sql  = sql.replace("#{purc_type}",String.valueOf(t.getPurcType()));
 		sql  = sql.replace("#{fist_party}",String.valueOf(t.getFistParty()));
 		sql  = sql.replace("#{second_party}",String.valueOf(t.getSecondParty()));
 		sql  = sql.replace("#{begin_date}",String.valueOf(t.getBeginDate()));
 		sql  = sql.replace("#{end_date}",String.valueOf(t.getEndDate()));
 		sql  = sql.replace("#{con_group}",String.valueOf(t.getConGroup()));
 		sql  = sql.replace("#{change_money}",String.valueOf(t.getChangeMoney()));
 		sql  = sql.replace("#{bond_type}",String.valueOf(t.getBondType()));
 		sql  = sql.replace("#{bond_money}",String.valueOf(t.getBondMoney()));
 		sql  = sql.replace("#{remark}",String.valueOf(t.getRemark()));
 		sql  = sql.replace("#{parent_con}",String.valueOf(t.getParentCon()));
 		sql  = sql.replace("#{pay_or_rec}",String.valueOf(t.getPayOrRec()));
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
					javaInter.execute("contract_manage",data);
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("执行JAVA增强出现异常！");
			} 
		}
 	}
}