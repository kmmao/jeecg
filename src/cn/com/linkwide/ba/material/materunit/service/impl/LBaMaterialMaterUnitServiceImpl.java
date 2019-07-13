package cn.com.linkwide.ba.material.materunit.service.impl;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.ApplicationContextUtil;
import org.jeecgframework.core.util.MyClassLoader;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.web.cgform.enhance.CgformEnhanceJavaInter;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.linkwide.ba.material.materunit.entity.LBaMaterialMaterUnitEntity;
import cn.com.linkwide.ba.material.materunit.service.LBaMaterialMaterUnitServiceI;

@Service("lBaMaterialMaterUnitService")
@Transactional
public class LBaMaterialMaterUnitServiceImpl extends CommonServiceImpl implements LBaMaterialMaterUnitServiceI {

	@Autowired
	private SystemService systemService;
	
 	public void delete(LBaMaterialMaterUnitEntity entity) throws Exception{
 		super.delete(entity);
 		//执行删除操作增强业务
		this.doDelBus(entity);
 	}
 	
 	public Serializable save(LBaMaterialMaterUnitEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		//执行新增操作增强业务
 		this.doAddBus(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(LBaMaterialMaterUnitEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 		//执行更新操作增强业务
 		this.doUpdateBus(entity);
 	}
 	
 	/**
	 * 新增操作增强业务
	 * @param t
	 * @return
	 */
	private void doAddBus(LBaMaterialMaterUnitEntity t) throws Exception{
 	}
 	/**
	 * 更新操作增强业务
	 * @param t
	 * @return
	 */
	private void doUpdateBus(LBaMaterialMaterUnitEntity t) throws Exception{
 	}
 	/**
	 * 删除操作增强业务
	 * @param id
	 * @return
	 */
	private void doDelBus(LBaMaterialMaterUnitEntity t) throws Exception{
 	}
 	
 	private Map<String,Object> populationMap(LBaMaterialMaterUnitEntity t){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", t.getId());
		map.put("type_code", t.getTypeCode());
		map.put("type_name", t.getTypeName());
		map.put("english_name_singular", t.getEnglishNameSingular());
		map.put("english_name_complex", t.getEnglishNameComplex());
		map.put("status", t.getStatus());
		return map;
	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @param t
	 * @return
	 */
 	public String replaceVal(String sql,LBaMaterialMaterUnitEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{type_code}",String.valueOf(t.getTypeCode()));
 		sql  = sql.replace("#{type_name}",String.valueOf(t.getTypeName()));
 		sql  = sql.replace("#{english_name_singular}",String.valueOf(t.getEnglishNameSingular()));
 		sql  = sql.replace("#{english_name_complex}",String.valueOf(t.getEnglishNameComplex()));
 		sql  = sql.replace("#{status}",String.valueOf(t.getStatus()));
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

 	/**
 	 * 导出选中的数据
 	 */
	@Override
	public List<LBaMaterialMaterUnitEntity> getDataForExport(String ids) {
		List<LBaMaterialMaterUnitEntity> queryResult = systemService.findByQueryString(" from LBaMaterialMaterUnitEntity where id in ('"+ids+"')");
		return queryResult;
	}
}