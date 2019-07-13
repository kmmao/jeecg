package cn.com.linkwide.ba.material.qualitem.service.impl;
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

import cn.com.linkwide.ba.material.qualitem.entity.LSuMaterialQualItemEntity;
import cn.com.linkwide.ba.material.qualitem.service.LSuMaterialQualItemServiceI;

@Service("lSuMaterialQualItemService")
@Transactional
public class LSuMaterialQualItemServiceImpl extends CommonServiceImpl implements LSuMaterialQualItemServiceI {

	
 	public void delete(LSuMaterialQualItemEntity entity) throws Exception{
 		super.delete(entity);
 		//执行删除操作增强业务
		this.doDelBus(entity);
 	}
 	
 	public Serializable save(LSuMaterialQualItemEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		//执行新增操作增强业务
 		this.doAddBus(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(LSuMaterialQualItemEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 		//执行更新操作增强业务
 		this.doUpdateBus(entity);
 	}
 	
 	/**
	 * 新增操作增强业务
	 * @param t
	 * @return
	 */
	private void doAddBus(LSuMaterialQualItemEntity t) throws Exception{
 	}
 	/**
	 * 更新操作增强业务
	 * @param t
	 * @return
	 */
	private void doUpdateBus(LSuMaterialQualItemEntity t) throws Exception{
 	}
 	/**
	 * 删除操作增强业务
	 * @param id
	 * @return
	 */
	private void doDelBus(LSuMaterialQualItemEntity t) throws Exception{
 	}
 	
 	private Map<String,Object> populationMap(LSuMaterialQualItemEntity t){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", t.getId());
		map.put("material_qual_id", t.getMaterialQualId());
		map.put("qual_type_id", t.getQualTypeId());
		map.put("qual_code", t.getQualCode());
		map.put("qual_full_name", t.getQualFullName());
		map.put("effect_date", t.getEffectDate());
		map.put("over_date", t.getOverDate());
		map.put("is_control", t.getIsControl());
		map.put("depart_id", t.getDepartId());
		map.put("create_by", t.getCreateBy());
		map.put("create_date", t.getCreateDate());
		map.put("update_by", t.getUpdateBy());
		map.put("update_date", t.getUpdateDate());
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
		map.put("extend11", t.getExtend11());
		map.put("extend12", t.getExtend12());
		map.put("extend13", t.getExtend13());
		map.put("extend14", t.getExtend14());
		map.put("extend15", t.getExtend15());
		map.put("extend16", t.getExtend16());
		map.put("extend17", t.getExtend17());
		map.put("extend18", t.getExtend18());
		map.put("extend19", t.getExtend19());
		map.put("extend20", t.getExtend20());
		return map;
	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @param t
	 * @return
	 */
 	public String replaceVal(String sql,LSuMaterialQualItemEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{material_qual_id}",String.valueOf(t.getMaterialQualId()));
 		sql  = sql.replace("#{qual_type_id}",String.valueOf(t.getQualTypeId()));
 		sql  = sql.replace("#{qual_code}",String.valueOf(t.getQualCode()));
 		sql  = sql.replace("#{qual_full_name}",String.valueOf(t.getQualFullName()));
 		sql  = sql.replace("#{effect_date}",String.valueOf(t.getEffectDate()));
 		sql  = sql.replace("#{over_date}",String.valueOf(t.getOverDate()));
 		sql  = sql.replace("#{is_control}",String.valueOf(t.getIsControl()));
 		sql  = sql.replace("#{depart_id}",String.valueOf(t.getDepartId()));
 		sql  = sql.replace("#{create_by}",String.valueOf(t.getCreateBy()));
 		sql  = sql.replace("#{create_date}",String.valueOf(t.getCreateDate()));
 		sql  = sql.replace("#{update_by}",String.valueOf(t.getUpdateBy()));
 		sql  = sql.replace("#{update_date}",String.valueOf(t.getUpdateDate()));
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
 		sql  = sql.replace("#{extend11}",String.valueOf(t.getExtend11()));
 		sql  = sql.replace("#{extend12}",String.valueOf(t.getExtend12()));
 		sql  = sql.replace("#{extend13}",String.valueOf(t.getExtend13()));
 		sql  = sql.replace("#{extend14}",String.valueOf(t.getExtend14()));
 		sql  = sql.replace("#{extend15}",String.valueOf(t.getExtend15()));
 		sql  = sql.replace("#{extend16}",String.valueOf(t.getExtend16()));
 		sql  = sql.replace("#{extend17}",String.valueOf(t.getExtend17()));
 		sql  = sql.replace("#{extend18}",String.valueOf(t.getExtend18()));
 		sql  = sql.replace("#{extend19}",String.valueOf(t.getExtend19()));
 		sql  = sql.replace("#{extend20}",String.valueOf(t.getExtend20()));
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