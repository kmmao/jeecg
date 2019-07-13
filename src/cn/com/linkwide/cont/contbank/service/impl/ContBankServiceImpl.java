package cn.com.linkwide.cont.contbank.service.impl;
import cn.com.linkwide.cont.contbank.service.ContBankServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import cn.com.linkwide.cont.contbank.entity.ContBankEntity;
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

@Service("contBankService")
@Transactional
public class ContBankServiceImpl extends CommonServiceImpl implements ContBankServiceI {

	
 	public void delete(ContBankEntity entity) throws Exception{
 		super.delete(entity);
 		//执行删除操作增强业务
		this.doDelBus(entity);
 	}
 	
 	public Serializable save(ContBankEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		//执行新增操作增强业务
 		this.doAddBus(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(ContBankEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 		//执行更新操作增强业务
 		this.doUpdateBus(entity);
 	}
 	
 	/**
	 * 新增操作增强业务
	 * @param t
	 * @return
	 */
	private void doAddBus(ContBankEntity t) throws Exception{
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
	private void doUpdateBus(ContBankEntity t) throws Exception{
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
	private void doDelBus(ContBankEntity t) throws Exception{
	    //-----------------sql增强 start----------------------------
	 	//-----------------sql增强 end------------------------------
	 	
	 	//-----------------java增强 start---------------------------
	 	//-----------------java增强 end-----------------------------
 	}
 	
 	private Map<String,Object> populationMap(ContBankEntity t){
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
		map.put("bankgua_no", t.getBankguaNo());
		map.put("bankgua_name", t.getBankguaName());
		map.put("bankgua_fj", t.getBankguaFj());
		map.put("upload_date", t.getUploadDate());
		map.put("bigen_date", t.getBigenDate());
		map.put("failure_date", t.getFailureDate());
		map.put("bankgua_compy", t.getBankguaCompy());
		map.put("bankgua_money", t.getBankguaMoney());
		map.put("bangua_bz", t.getBanguaBz());
		map.put("execten1", t.getExecten1());
		map.put("execten2", t.getExecten2());
		map.put("execten3", t.getExecten3());
		map.put("execten4", t.getExecten4());
		map.put("execten5", t.getExecten5());
		return map;
	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @param t
	 * @return
	 */
 	public String replaceVal(String sql,ContBankEntity t){
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
 		sql  = sql.replace("#{bankgua_no}",String.valueOf(t.getBankguaNo()));
 		sql  = sql.replace("#{bankgua_name}",String.valueOf(t.getBankguaName()));
 		sql  = sql.replace("#{bankgua_fj}",String.valueOf(t.getBankguaFj()));
 		sql  = sql.replace("#{upload_date}",String.valueOf(t.getUploadDate()));
 		sql  = sql.replace("#{bigen_date}",String.valueOf(t.getBigenDate()));
 		sql  = sql.replace("#{failure_date}",String.valueOf(t.getFailureDate()));
 		sql  = sql.replace("#{bankgua_compy}",String.valueOf(t.getBankguaCompy()));
 		sql  = sql.replace("#{bankgua_money}",String.valueOf(t.getBankguaMoney()));
 		sql  = sql.replace("#{bangua_bz}",String.valueOf(t.getBanguaBz()));
 		sql  = sql.replace("#{execten1}",String.valueOf(t.getExecten1()));
 		sql  = sql.replace("#{execten2}",String.valueOf(t.getExecten2()));
 		sql  = sql.replace("#{execten3}",String.valueOf(t.getExecten3()));
 		sql  = sql.replace("#{execten4}",String.valueOf(t.getExecten4()));
 		sql  = sql.replace("#{execten5}",String.valueOf(t.getExecten5()));
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
					javaInter.execute("cont_Bank",data);
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("执行JAVA增强出现异常！");
			} 
		}
 	}
}