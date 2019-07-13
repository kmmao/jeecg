package cn.com.linkwide.ba.contwarehousematerial.service.impl;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
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

import cn.com.linkwide.ba.contwarehousematerial.entity.LBaContWarehouseMaterialEntity;
import cn.com.linkwide.ba.contwarehousematerial.service.LBaContWarehouseMaterialServiceI;
import cn.com.linkwide.ba.material.record.entity.LBaMaterialEntity;
import cn.com.linkwide.ba.warehouse.entity.LBaWarehouseEntity;

@Service("lBaContWarehouseMaterialService")
@Transactional
public class LBaContWarehouseMaterialServiceImpl extends CommonServiceImpl implements LBaContWarehouseMaterialServiceI {

	@Autowired
	private SystemService systemService;
	
 	public void delete(LBaContWarehouseMaterialEntity entity) throws Exception{
 		super.delete(entity);
 		//执行删除操作增强业务
		this.doDelBus(entity);
 	}
 	
 	public Serializable save(LBaContWarehouseMaterialEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		//执行新增操作增强业务
 		this.doAddBus(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(LBaContWarehouseMaterialEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 		//执行更新操作增强业务
 		this.doUpdateBus(entity);
 	}
 	
 	/**
	 * 新增操作增强业务
	 * @param t
	 * @return
	 */
	private void doAddBus(LBaContWarehouseMaterialEntity t) throws Exception{
 	}
 	/**
	 * 更新操作增强业务
	 * @param t
	 * @return
	 */
	private void doUpdateBus(LBaContWarehouseMaterialEntity t) throws Exception{
 	}
 	/**
	 * 删除操作增强业务
	 * @param id
	 * @return
	 */
	private void doDelBus(LBaContWarehouseMaterialEntity t) throws Exception{
 	}
 	
 	private Map<String,Object> populationMap(LBaContWarehouseMaterialEntity t){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", t.getId());
		map.put("warehouse_id", t.getWarehouseId());
		map.put("material_id", t.getMaterialId());
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
 	public String replaceVal(String sql,LBaContWarehouseMaterialEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{warehouse_id}",String.valueOf(t.getWarehouseId()));
 		sql  = sql.replace("#{material_id}",String.valueOf(t.getMaterialId()));
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
 	
 	/**
 	 * 检查仓库与物资之间关系
 	 * @param wareId 仓库ID
 	 * @param materialIdList 物资idlist
 	 * @param errorMsg 错误提示消息 ：仓库不支持物资XX,XX的存储；如有需要请在【物资与仓库对照】中维护；
 	 * @return true:归属关系 ；false :含有非归属关系
 	 * @throws Exception
 	 */
	@Override
	public boolean checkWareMaterial(String wareId, List<String> materialIdList, StringBuffer errorMsg)
			throws Exception {
		String mids = "";
		for(String id :materialIdList){
			mids += id +",";
		}
		String sql ="select MATERIAL_NAME \"materialName\" from L_BA_MATERIAL where id in ('"+mids.replace(",", "','")+"') and id not in  (select MATERIAL_ID from L_BA_CONT_WAREHOUSE_MATERIAL  c where  c.WAREHOUSE_ID ='"+wareId+"'  )";
		
		List<Map<String,Object>> mList = systemService.findForJdbc(sql);
		//组装错误提示信息
		if(mList.size()>0){
			LBaWarehouseEntity sEntity = this.getEntity(LBaWarehouseEntity.class, wareId);
			errorMsg.append(sEntity.getWarehouseName()+"不支持以下物资的存储：\n"); 
			for (Map<String,Object> m : mList) { 
				errorMsg.append(m.get("materialName").toString()+"\n"); 
			}
			return false;
		}
		return true;
	}
	/**
	 * 检查仓库与物资之间关系
 	 * @param smMap key:物资，value：供应商
 	 * @param errorMsg 错误提示消息 ：xx物资-->xx供应商；
 	 * @return true:归属关系 ；false :含有非归属关系
 	 * @throws Exception 
	 */
	@Override
	public boolean checkWareMaterial(Map<String, String> smMap, StringBuffer errorMsg) throws Exception {
		List<LBaContWarehouseMaterialEntity> list = this.loadAll(LBaContWarehouseMaterialEntity.class);
		Set<String> set = smMap.keySet();
		//不存在归属关系的物资
		Map<String, String> noInMap = new HashMap<String, String>();
		for (String key : set) {
			boolean flag = false;
			for (LBaContWarehouseMaterialEntity sm : list) {				
				if(key.equals(sm.getMaterialId()) && smMap.get(key).equals(sm.getWarehouseId())){
					flag = true;
					break;
				}
			}
			if(!flag)
				noInMap.put(key, smMap.get(key));
		}
		if(!noInMap.isEmpty()){
			Set<String> notInset = noInMap.keySet();
			//组装错误提示信息
			List<LBaWarehouseEntity> wEntity = this.loadAll(LBaWarehouseEntity.class);
			Map<String, String> wareMap = new HashMap<String, String>();
			for (LBaWarehouseEntity wareEntity : wEntity) {
				wareMap.put(wareEntity.getId(), wareEntity.getWarehouseName());
			}
			
			List<LBaMaterialEntity> mList = this.loadAll(LBaMaterialEntity.class);
			Map<String, String> materialMap = new HashMap<String, String>();
			for (LBaMaterialEntity lBaMaterialEntity : mList) {
				materialMap.put(lBaMaterialEntity.getId(), lBaMaterialEntity.getMaterialName());
			}
			
			errorMsg.append("下列物资与仓库不存在归属关系：\n");
			for (String string : notInset) {
				errorMsg.append("物资："+materialMap.get(string));
				errorMsg.append("-->");
				errorMsg.append("仓库："+wareMap.get(noInMap.get(string))+"\n");
			}
			return false;	
		}	
		return true;
	}

	/*** 导出选中的数据*/
	@Override
	public List<LBaContWarehouseMaterialEntity> getDataForExport(String ids) {
		return systemService.findByQueryString(" from LBaContWarehouseMaterialEntity where id in ('"+ids+"')");
	}
}