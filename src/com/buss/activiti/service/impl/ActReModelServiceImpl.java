package com.buss.activiti.service.impl;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.ApplicationContextUtil;
import org.jeecgframework.core.util.MyClassLoader;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.web.cgform.enhance.CgformEnhanceJavaInter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.buss.activiti.entity.ActReModelEntity;
import com.buss.activiti.service.ActReModelServiceI;

@Service("actReModelService")
@Transactional
public class ActReModelServiceImpl extends CommonServiceImpl implements ActReModelServiceI {

	
 	public void delete(ActReModelEntity entity) throws Exception{
 		super.delete(entity);
 		//执行删除操作增强业务
		this.doDelBus(entity);
 	}
 	
 	public Serializable save(ActReModelEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		//执行新增操作增强业务
 		this.doAddBus(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(ActReModelEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 		//执行更新操作增强业务
 		this.doUpdateBus(entity);
 	}
 	
 	/**
	 * 新增操作增强业务
	 * @param t
	 * @return
	 */
	private void doAddBus(ActReModelEntity t) throws Exception{
 	}
 	/**
	 * 更新操作增强业务
	 * @param t
	 * @return
	 */
	private void doUpdateBus(ActReModelEntity t) throws Exception{
 	}
 	/**
	 * 删除操作增强业务
	 * @param id
	 * @return
	 */
	private void doDelBus(ActReModelEntity t) throws Exception{
 	}
 	
 	private Map<String,Object> populationMap(ActReModelEntity t){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id_", t.getId());
		map.put("rev_", t.getRev());
		map.put("name_", t.getName());
		map.put("key_", t.getKey());
		map.put("category_", t.getCategory());
		map.put("create_time_", t.getCreateTime());
		map.put("last_update_time_", t.getLastUpdateTime());
		map.put("version_", t.getVersion());
		map.put("meta_info_", t.getMetaInfo());
		map.put("deployment_id_", t.getDeploymentId());
		map.put("editor_source_value_id_", t.getEditorSourceValueId());
		map.put("editor_source_extra_value_id_", t.getEditorSourceExtraValueId());
		map.put("tenant_id_", t.getTenantId());
		return map;
	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @param t
	 * @return
	 */
 	public String replaceVal(String sql,ActReModelEntity t){
 		sql  = sql.replace("#{id_}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{rev_}",String.valueOf(t.getRev()));
 		sql  = sql.replace("#{name_}",String.valueOf(t.getName()));
 		sql  = sql.replace("#{key_}",String.valueOf(t.getKey()));
 		sql  = sql.replace("#{category_}",String.valueOf(t.getCategory()));
 		sql  = sql.replace("#{create_time_}",String.valueOf(t.getCreateTime()));
 		sql  = sql.replace("#{last_update_time_}",String.valueOf(t.getLastUpdateTime()));
 		sql  = sql.replace("#{version_}",String.valueOf(t.getVersion()));
 		sql  = sql.replace("#{meta_info_}",String.valueOf(t.getMetaInfo()));
 		sql  = sql.replace("#{deployment_id_}",String.valueOf(t.getDeploymentId()));
 		sql  = sql.replace("#{editor_source_value_id_}",String.valueOf(t.getEditorSourceValueId()));
 		sql  = sql.replace("#{editor_source_extra_value_id_}",String.valueOf(t.getEditorSourceExtraValueId()));
 		sql  = sql.replace("#{tenant_id_}",String.valueOf(t.getTenantId()));
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
					javaInter.execute(data);
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("执行JAVA增强出现异常！");
			} 
		}
 	}
}