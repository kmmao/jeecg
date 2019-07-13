package cn.com.linkwide.ba.babudgetrecord.service.impl;
import cn.com.linkwide.ba.babudgetrecord.service.BaBudgetRecordServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import cn.com.linkwide.ba.babudgetrecord.entity.BaBudgetRecordEntity;
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

@Service("baBudgetRecordService")
@Transactional
public class BaBudgetRecordServiceImpl extends CommonServiceImpl implements BaBudgetRecordServiceI {

	
 	public void delete(BaBudgetRecordEntity entity) throws Exception{
 		super.delete(entity);
 		//执行删除操作增强业务
		this.doDelBus(entity);
 	}
 	
 	public Serializable save(BaBudgetRecordEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		//执行新增操作增强业务
 		this.doAddBus(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(BaBudgetRecordEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 		//执行更新操作增强业务
 		this.doUpdateBus(entity);
 	}
 	
 	/**
	 * 新增操作增强业务
	 * @param t
	 * @return
	 */
	private void doAddBus(BaBudgetRecordEntity t) throws Exception{
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
	private void doUpdateBus(BaBudgetRecordEntity t) throws Exception{
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
	private void doDelBus(BaBudgetRecordEntity t) throws Exception{
	    //-----------------sql增强 start----------------------------
	 	//-----------------sql增强 end------------------------------
	 	
	 	//-----------------java增强 start---------------------------
	 	//-----------------java增强 end-----------------------------
 	}
 	
 	private Map<String,Object> populationMap(BaBudgetRecordEntity t){
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
		map.put("mkmc", t.getMkmc());
		map.put("cdmc", t.getCdmc());
		map.put("zclx", t.getZclx());
		map.put("zxks", t.getZxks());
		map.put("ysks", t.getYsks());
		map.put("ysxmbm", t.getYsxmbm());
		map.put("ysxmmc", t.getYsxmmc());
		map.put("zcxmbm", t.getZcxmbm());
		map.put("zcxmmc", t.getZcxmmc());
		map.put("zjly", t.getZjly());
		map.put("zjxz", t.getZjxz());
		map.put("zcje", t.getZcje());
		map.put("tz", t.getTz());
		map.put("tj", t.getTj());
		map.put("pzh", t.getPzh());
		map.put("name", t.getName());
		map.put("billdate", t.getBilldate());
		map.put("pkid", t.getPkid());
		map.put("v1", t.getV1());
		map.put("v2", t.getV2());
		map.put("v3", t.getV3());
		map.put("v4", t.getV4());
		map.put("v5", t.getV5());
		map.put("v6", t.getV6());
		map.put("v7", t.getV7());
		map.put("v8", t.getV8());
		map.put("v9", t.getV9());
		map.put("v10", t.getV10());
		return map;
	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @param t
	 * @return
	 */
 	public String replaceVal(String sql,BaBudgetRecordEntity t){
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
 		sql  = sql.replace("#{mkmc}",String.valueOf(t.getMkmc()));
 		sql  = sql.replace("#{cdmc}",String.valueOf(t.getCdmc()));
 		sql  = sql.replace("#{zclx}",String.valueOf(t.getZclx()));
 		sql  = sql.replace("#{zxks}",String.valueOf(t.getZxks()));
 		sql  = sql.replace("#{ysks}",String.valueOf(t.getYsks()));
 		sql  = sql.replace("#{ysxmbm}",String.valueOf(t.getYsxmbm()));
 		sql  = sql.replace("#{ysxmmc}",String.valueOf(t.getYsxmmc()));
 		sql  = sql.replace("#{zcxmbm}",String.valueOf(t.getZcxmbm()));
 		sql  = sql.replace("#{zcxmmc}",String.valueOf(t.getZcxmmc()));
 		sql  = sql.replace("#{zjly}",String.valueOf(t.getZjly()));
 		sql  = sql.replace("#{zjxz}",String.valueOf(t.getZjxz()));
 		sql  = sql.replace("#{zcje}",String.valueOf(t.getZcje()));
 		sql  = sql.replace("#{tz}",String.valueOf(t.getTz()));
 		sql  = sql.replace("#{tj}",String.valueOf(t.getTj()));
 		sql  = sql.replace("#{pzh}",String.valueOf(t.getPzh()));
 		sql  = sql.replace("#{name}",String.valueOf(t.getName()));
 		sql  = sql.replace("#{billdate}",String.valueOf(t.getBilldate()));
 		sql  = sql.replace("#{pkid}",String.valueOf(t.getPkid()));
 		sql  = sql.replace("#{v1}",String.valueOf(t.getV1()));
 		sql  = sql.replace("#{v2}",String.valueOf(t.getV2()));
 		sql  = sql.replace("#{v3}",String.valueOf(t.getV3()));
 		sql  = sql.replace("#{v4}",String.valueOf(t.getV4()));
 		sql  = sql.replace("#{v5}",String.valueOf(t.getV5()));
 		sql  = sql.replace("#{v6}",String.valueOf(t.getV6()));
 		sql  = sql.replace("#{v7}",String.valueOf(t.getV7()));
 		sql  = sql.replace("#{v8}",String.valueOf(t.getV8()));
 		sql  = sql.replace("#{v9}",String.valueOf(t.getV9()));
 		sql  = sql.replace("#{v10}",String.valueOf(t.getV10()));
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
					javaInter.execute("ba_budget_record",data);
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("执行JAVA增强出现异常！");
			} 
		}
 	}
}