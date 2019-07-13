package cn.com.linkwide.ba.contsuppliermaterial.service.impl;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.hibernate.criterion.Restrictions;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.ApplicationContextUtil;
import org.jeecgframework.core.util.MyClassLoader;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.web.cgform.enhance.CgformEnhanceJavaInter;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.linkwide.ba.contsuppliermaterial.entity.LBaContSupplierMaterialEntity;
import cn.com.linkwide.ba.contsuppliermaterial.service.LBaContSupplierMaterialServiceI;
import cn.com.linkwide.ba.material.record.entity.LBaMaterialEntity;
import cn.com.linkwide.ba.supplier.entity.LBaSupplierEntity;
import cn.com.linkwide.ba.syn.SynchronousBa;
import cn.com.linkwide.ba.warehouse.entity.LBaWarehouseEntity;

@Service("lBaContSupplierMaterialService")
@Transactional
public class LBaContSupplierMaterialServiceImpl extends CommonServiceImpl implements LBaContSupplierMaterialServiceI {

	@Autowired
	private SystemService systemService;
	
 	public void delete(LBaContSupplierMaterialEntity entity) throws Exception{
 		super.delete(entity);
 		//执行删除操作增强业务
		this.doDelBus(entity);
		
		//同步数据
		this.updateBySqlString(SynchronousBa.synDelete(entity));
 	}
 	
 	public Serializable save(LBaContSupplierMaterialEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		//执行新增操作增强业务
 		this.doAddBus(entity);
 		//同步数据
		this.updateBySqlString(SynchronousBa.synSave(entity));
		return t;
 	}
 	
 	public void saveOrUpdate(LBaContSupplierMaterialEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 		//执行更新操作增强业务
 		this.doUpdateBus(entity);
 		
 		//同步数据
		this.updateBySqlString(SynchronousBa.synDelete(entity)); 
		this.updateBySqlString(SynchronousBa.synSave(entity));
 	}
 	
 	/**
	 * 新增操作增强业务
	 * @param t
	 * @return
	 */
	private void doAddBus(LBaContSupplierMaterialEntity t) throws Exception{
 	}
 	/**
	 * 更新操作增强业务
	 * @param t
	 * @return
	 */
	private void doUpdateBus(LBaContSupplierMaterialEntity t) throws Exception{
 	}
 	/**
	 * 删除操作增强业务
	 * @param id
	 * @return
	 */
	private void doDelBus(LBaContSupplierMaterialEntity t) throws Exception{
 	}
 	
 	private Map<String,Object> populationMap(LBaContSupplierMaterialEntity t){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", t.getId());
		map.put("supplier_id", t.getSupplierId());
		map.put("material_id", t.getMaterialId());
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
 	public String replaceVal(String sql,LBaContSupplierMaterialEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{supplier_id}",String.valueOf(t.getSupplierId()));
 		sql  = sql.replace("#{material_id}",String.valueOf(t.getMaterialId()));
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
 	/**
 	 * 检查供应商与物资之间关系
 	 * @param supplierId 供应商ID
 	 * @param materialIdList 物资idlist
 	 * @param errorMsg 错误提示消息 ：供应商不支持物资XX,XX的供货；如有需要请在【物资与供应商对照】中维护；
 	 * @return true:归属关系 ；false :含有非归属关系
 	 * @throws Exception
 	 */
	@Override
	public boolean checkSupplierMaterial(String supplierId, List<String> materialIdList, StringBuffer errorMsg)
			throws Exception {		
		String mids = "";
		for(String id :materialIdList){
			mids += id +",";
		}
		String sql ="select MATERIAL_NAME \"materialName\" from L_BA_MATERIAL where id in ('"+mids.replace(",", "','")+"') and id not in  (select MATERIAL_ID from L_BA_CONT_SUPPLIER_MATERIAL  c where  c.SUPPLIER_ID ='"+supplierId+"'  )";
		List<Map<String,Object>> mList = systemService.findForJdbc(sql);
		//组装错误提示信息
		if(mList.size()>0){
			LBaSupplierEntity sEntity = this.getEntity(LBaSupplierEntity.class, supplierId);
			errorMsg.append(sEntity.getSupplierFullName()+"不提供以下物资：\n");
			for (Map<String,Object> m : mList) { 
				errorMsg.append(m.get("materialName").toString()+"\n"); 
			}
			return false;
		}
		return true;
	}

	/**
	 * 检查供应商与物资之间关系
 	 * @param smMap key:物资，value：供应商
 	 * @param errorMsg 错误提示消息 ：xx物资-->xx供应商；
 	 * @return true:归属关系 ；false :含有非归属关系
 	 * @throws Exception 
	 */
	@Override
	public boolean checkSupplierMaterial(Map<String, String> smMap, StringBuffer errorMsg) throws Exception {
		Set<String> set = smMap.keySet();
		String mIds ="";
		String supIds ="";
		for (String key : set) {
			mIds += key+",";
			supIds += smMap.get(key)+",";
		}
		//查询物资
		List<LBaSupplierEntity> sList =  systemService.findByProperty(LBaSupplierEntity.class, "id", supIds.split(","));
		Map<String,LBaSupplierEntity> sMap = systemService.list2Map(LBaSupplierEntity.class, sList, "id");
		//查询供应商
		List<LBaMaterialEntity> mList =  systemService.findByProperty(LBaMaterialEntity.class, "id", mIds.split(","));
		Map<String,LBaMaterialEntity> mMap = systemService.list2Map(LBaMaterialEntity.class, mList, "id");
		//查询供应商的对应关系
		List<LBaContSupplierMaterialEntity> countList = systemService.findByProperty(LBaContSupplierMaterialEntity.class, "materialId", mIds.split(","));
		Map<String,LBaContSupplierMaterialEntity> countMap = systemService.list2Map(LBaContSupplierMaterialEntity.class, countList, "materialId,supplierId");
		for (String key : set) {
			if(!countMap.containsKey(key+smMap.get(key))){
				errorMsg.append("物资“"+mMap.get(key).getMaterialName());
				errorMsg.append("”和");
				errorMsg.append("供应商“"+sMap.get(smMap.get(key)).getSupplierFullName()+"”，不存在对应关系");
				return false;
			}
		}
		return true;
	}

	/**
	 * 导出选中的数据
	 */
	@Override
	public List<LBaContSupplierMaterialEntity> getExportData(String ids) {
		List<LBaContSupplierMaterialEntity> queryResult = systemService.findByQueryString(" from LBaContSupplierMaterialEntity where id in ('"+ids+"')");
		return queryResult;
	}

	@Override
	public List<LBaSupplierEntity> getSupplierByMaterial(String materialIds) {
		if(StringUtil.isNotEmpty(materialIds)){
			String[] mateArray = materialIds.split(",");
			StringBuffer sql = new StringBuffer();
			sql.append(" select supplier_id \"id\",count(id) from l_ba_cont_supplier_material c where  1 = 1");
			sql.append(" and material_id in ('"+materialIds.replace(",", "','")+"')");
			sql.append(" group by supplier_id HAVING count(id) >" +(mateArray.length-1) ); 
			List<Map<String,Object>> list = systemService.findForJdbc(sql.toString());
			if(list != null && list.size() >0){
				String ids = "";
				for(Map<String,Object> map :list){
					ids+=map.get("id").toString()+","; 
				}
				List<LBaSupplierEntity> listsp = this.findHql(" from LBaSupplierEntity where id in ('"+ids.replace(",", "','")+"')");
				return listsp;
			}
		}
		return null;
	}
	
	
 	
 	
 	
}