package cn.com.linkwide.ba.warehouse.service.impl;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.ApplicationContextUtil;
import org.jeecgframework.core.util.MyClassLoader;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.web.cgform.enhance.CgformEnhanceJavaInter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.linkwide.ba.warehouse.entity.LBaWarehouseEntity;
import cn.com.linkwide.ba.warehouse.service.LBaWarehouseServiceI;
import cn.com.linkwide.ba.warehouseorg.entity.LBaWarehouseOrgMapEntity;

@Service("lBaWarehouseService")
@Transactional
public class LBaWarehouseServiceImpl extends CommonServiceImpl implements LBaWarehouseServiceI {

	
 	public void delete(LBaWarehouseEntity entity) throws Exception{
 		//this.updateBySqlString("delete from l_ba_warehouse_org_map where warehouse_id ='"+entity.getId()+"' and org_code like '"+ResourceUtil.getUserComponyCode()+"%'");
 		this.updateBySqlString("delete from l_ba_warehouse_org_map where warehouse_id ='"+entity.getId()+"' ");
 		super.delete(entity);
 		//执行删除操作增强业务
		this.doDelBus(entity);
 	}
 	
 	public Serializable save(LBaWarehouseEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		//执行新增操作增强业务
 		this.doAddBus(entity);
 		String[] orgs = entity.getOrgCodes().split(",");
 		for(int i=0;i<orgs.length;i++){
 			LBaWarehouseOrgMapEntity map = new LBaWarehouseOrgMapEntity();
 			map.setOrgCode(orgs[i]);
 			map.setWarehouseId(entity.getId());
 			super.save(map);
 		}
 		return t;
 	}
 	
 	public void saveOrUpdate(LBaWarehouseEntity entity) throws Exception{
 		//this.updateBySqlString("delete from l_ba_warehouse_org_map where warehouse_id ='"+entity.getId()+"' and org_code like '"+ResourceUtil.getUserComponyCode()+"%' ");
 		this.updateBySqlString("delete from l_ba_warehouse_org_map where warehouse_id ='"+entity.getId()+"'  ");
 		if(StringUtil.isNotEmpty(entity.getOrgCodes())){
 			String[] orgs = entity.getOrgCodes().split(",");
 	 		for(int i=0;i<orgs.length;i++){
 	 			LBaWarehouseOrgMapEntity map = new LBaWarehouseOrgMapEntity();
 	 			map.setOrgCode(orgs[i]);
 	 			map.setWarehouseId(entity.getId());
 	 			super.save(map);
 	 		}
 		} 
 		super.saveOrUpdate(entity);
 		//执行更新操作增强业务
 		this.doUpdateBus(entity);
 	}
 	
 	/**
	 * 新增操作增强业务
	 * @param t
	 * @return
	 */
	private void doAddBus(LBaWarehouseEntity t) throws Exception{
 	}
 	/**
	 * 更新操作增强业务
	 * @param t
	 * @return
	 */
	private void doUpdateBus(LBaWarehouseEntity t) throws Exception{
 	}
 	/**
	 * 删除操作增强业务
	 * @param id
	 * @return
	 */
	private void doDelBus(LBaWarehouseEntity t) throws Exception{
 	}
 	
 	private Map<String,Object> populationMap(LBaWarehouseEntity t){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", t.getId());
		map.put("warehouse_code", t.getWarehouseCode());
		map.put("warehouse_name", t.getWarehouseName());
		map.put("is_include_cost", t.getIsIncludeCost());
		map.put("is_assets", t.getIsAssets());
		map.put("depart_id", t.getDepartId());
		map.put("create_by", t.getCreateBy());
		map.put("create_date", t.getCreateDate());
		map.put("update_by", t.getUpdateBy());
		map.put("update_date", t.getUpdateDate());
		
		return map;
	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @param t
	 * @return
	 */
 	public String replaceVal(String sql,LBaWarehouseEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{warehouse_code}",String.valueOf(t.getWarehouseCode()));
 		sql  = sql.replace("#{warehouse_name}",String.valueOf(t.getWarehouseName()));
 		sql  = sql.replace("#{is_include_cost}",String.valueOf(t.getIsIncludeCost()));
 		sql  = sql.replace("#{is_assets}",String.valueOf(t.getIsAssets()));
 		sql  = sql.replace("#{depart_id}",String.valueOf(t.getDepartId()));
 		sql  = sql.replace("#{create_by}",String.valueOf(t.getCreateBy()));
 		sql  = sql.replace("#{create_date}",String.valueOf(t.getCreateDate()));
 		sql  = sql.replace("#{update_by}",String.valueOf(t.getUpdateBy()));
 		sql  = sql.replace("#{update_date}",String.valueOf(t.getUpdateDate()));
 		
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