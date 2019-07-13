package cn.com.linkwide.ba.bafunction.service.impl;
import cn.com.linkwide.ba.bafunction.service.BaFunctionServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import cn.com.linkwide.ba.bafunction.entity.BaFunctionEntity;
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

@Service("baFunctionService")
@Transactional
public class BaFunctionServiceImpl extends CommonServiceImpl implements BaFunctionServiceI {

	
 	public void delete(BaFunctionEntity entity) throws Exception{
 		super.delete(entity);
 		//执行删除操作增强业务
		this.doDelBus(entity);
 	}
 	
 	public Serializable save(BaFunctionEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		//执行新增操作增强业务
 		this.doAddBus(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(BaFunctionEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 		//执行更新操作增强业务
 		this.doUpdateBus(entity);
 	}
 	
 	/**
	 * 新增操作增强业务
	 * @param t
	 * @return
	 */
	private void doAddBus(BaFunctionEntity t) throws Exception{
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
	private void doUpdateBus(BaFunctionEntity t) throws Exception{
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
	private void doDelBus(BaFunctionEntity t) throws Exception{
	    //-----------------sql增强 start----------------------------
	 	//-----------------sql增强 end------------------------------
	 	
	 	//-----------------java增强 start---------------------------
	 	//-----------------java增强 end-----------------------------
 	}
 	
 	private Map<String,Object> populationMap(BaFunctionEntity t){
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
		map.put("vcode", t.getVcode());
		map.put("vname", t.getVname());
		map.put("vparent", t.getVparent());
		map.put("viflater", t.getViflater());
		map.put("vuse", t.getVuse());
		map.put("vmemo", t.getVmemo());
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
		return map;
	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @param t
	 * @return
	 */
 	public String replaceVal(String sql,BaFunctionEntity t){
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
 		sql  = sql.replace("#{vcode}",String.valueOf(t.getVcode()));
 		sql  = sql.replace("#{vname}",String.valueOf(t.getVname()));
 		sql  = sql.replace("#{vparent}",String.valueOf(t.getVparent()));
 		sql  = sql.replace("#{viflater}",String.valueOf(t.getViflater()));
 		sql  = sql.replace("#{vuse}",String.valueOf(t.getVuse()));
 		sql  = sql.replace("#{vmemo}",String.valueOf(t.getVmemo()));
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
					javaInter.execute("ba_function",data);
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("执行JAVA增强出现异常！");
			} 
		}
 	}
}