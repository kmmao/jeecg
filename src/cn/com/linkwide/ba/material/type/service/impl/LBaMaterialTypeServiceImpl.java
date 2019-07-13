package cn.com.linkwide.ba.material.type.service.impl;
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
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.linkwide.ba.material.type.entity.LBaMaterialTypeEntity;
import cn.com.linkwide.ba.material.type.service.LBaMaterialTypeServiceI;

@Service("lBaMaterialTypeService")
@Transactional
public class LBaMaterialTypeServiceImpl extends CommonServiceImpl implements LBaMaterialTypeServiceI {

	
 	public void delete(LBaMaterialTypeEntity entity) throws Exception{
 		//判断是否为根节点
	/*	List<LBaMaterialTypeEntity> list = super.findByProperty(LBaMaterialTypeEntity.class, "parentId", entity.getId());
		String ids = "";
		String id = "";
		if (list.size() > 0 ) {
			for (LBaMaterialTypeEntity baMaterialTypeEntity : list) {
				id = baMaterialTypeEntity.getId();
				ids += id + ",";
			}
			ids = ids.substring(0, ids.length() - 1);
		}
		
		if (ids.length() > 0) {
			for(String i:ids.split(",")){
				LBaMaterialTypeEntity lBaMaterialType = super.getEntity(LBaMaterialTypeEntity.class,i);
				super.delete(lBaMaterialType);
			}
		}*/
 		super.delete(entity);
 		//执行删除操作增强业务
		this.doDelBus(entity);
 	}
 	
 	public Serializable save(LBaMaterialTypeEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		//执行新增操作增强业务
 		this.doAddBus(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(LBaMaterialTypeEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 		//执行更新操作增强业务
 		this.doUpdateBus(entity);
 	}
 	
 	/**
	 * 新增操作增强业务
	 * @param t
	 * @return
	 */
	private void doAddBus(LBaMaterialTypeEntity t) throws Exception{
 	}
 	/**
	 * 更新操作增强业务
	 * @param t
	 * @return
	 */
	private void doUpdateBus(LBaMaterialTypeEntity t) throws Exception{
 	}
 	/**
	 * 删除操作增强业务
	 * @param id
	 * @return
	 */
	private void doDelBus(LBaMaterialTypeEntity t) throws Exception{
 	}
 	
 	private Map<String,Object> populationMap(LBaMaterialTypeEntity t){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", t.getId());
		map.put("type_code", t.getTypeCode());
		map.put("type_name", t.getTypeName());
		map.put("parent_id", t.getParentId());
		map.put("status", t.getStatus());
		return map;
	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @param t
	 * @return
	 */
 	public String replaceVal(String sql,LBaMaterialTypeEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{type_code}",String.valueOf(t.getTypeCode()));
 		sql  = sql.replace("#{type_name}",String.valueOf(t.getTypeName()));
 		sql  = sql.replace("#{parent_id}",String.valueOf(t.getParentId()));
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
 	 * 递归查询某个节点下所有的子节点，不包括本级
 	 * @param parentid
 	 * @param resultlist
 	 * @return
 	 */
 	public String getchildIdsNoCurr(String parentid){
 		
		String childIds = "";
		
		String sql= "SELECT ID FROM l_ba_material_type where parent_id = '" + parentid +"'";
		List<String> list = this.findListbySql(sql);
		
		if(list.size() != 0){
			for(String pid : list){
				childIds += getchild(pid) +  ",";
			}
			return childIds;
		}else{
			
			return childIds;
		}

	}
 	
	public String getchild(String childId) {
		
		String sql= "SELECT ID FROM l_ba_material_type where parent_id = '" + childId +"'";
		List<String> list = this.findListbySql(sql);
		
		
		if(list.size() != 0){
			for(String pid : list){
				childId += "," + getchild(pid);
			}
			return childId;
		}else{
			
			return childId;
		}
		
	}
}