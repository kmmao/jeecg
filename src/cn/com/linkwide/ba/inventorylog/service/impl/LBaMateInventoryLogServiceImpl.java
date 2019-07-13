package cn.com.linkwide.ba.inventorylog.service.impl;
import cn.com.linkwide.ba.inventorylog.service.LBaMateInventoryLogServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import cn.com.linkwide.ba.inventorylog.entity.LBaMateInventoryLogEntity;
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

@Service("lBaMateInventoryLogService")
@Transactional
public class LBaMateInventoryLogServiceImpl extends CommonServiceImpl implements LBaMateInventoryLogServiceI {

	
 	public void delete(LBaMateInventoryLogEntity entity) throws Exception{
 		super.delete(entity);
 		//执行删除操作增强业务
		this.doDelBus(entity);
 	}
 	
 	public Serializable save(LBaMateInventoryLogEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		//执行新增操作增强业务
 		this.doAddBus(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(LBaMateInventoryLogEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 		//执行更新操作增强业务
 		this.doUpdateBus(entity);
 	}
 	
 	/**
	 * 新增操作增强业务
	 * @param t
	 * @return
	 */
	private void doAddBus(LBaMateInventoryLogEntity t) throws Exception{
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
	private void doUpdateBus(LBaMateInventoryLogEntity t) throws Exception{
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
	private void doDelBus(LBaMateInventoryLogEntity t) throws Exception{
	    //-----------------sql增强 start----------------------------
	 	//-----------------sql增强 end------------------------------
	 	
	 	//-----------------java增强 start---------------------------
	 	//-----------------java增强 end-----------------------------
 	}
 	
 	private Map<String,Object> populationMap(LBaMateInventoryLogEntity t){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", t.getId());
		map.put("create_date", t.getCreateDate());
		map.put("inventory_id", t.getInventoryId());
		map.put("warehouse_id", t.getWarehouseId());
		map.put("material_id", t.getMaterialId());
		map.put("log_date", t.getLogDate());
		map.put("now_num", t.getNowNum());
		map.put("num", t.getNum());
		map.put("bill_no", t.getBillNo());
		map.put("bill_type", t.getBillType());
		map.put("create_by", t.getCreateBy());
		return map;
	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @param t
	 * @return
	 */
 	public String replaceVal(String sql,LBaMateInventoryLogEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{create_date}",String.valueOf(t.getCreateDate()));
 		sql  = sql.replace("#{inventory_id}",String.valueOf(t.getInventoryId()));
 		sql  = sql.replace("#{warehouse_id}",String.valueOf(t.getWarehouseId()));
 		sql  = sql.replace("#{material_id}",String.valueOf(t.getMaterialId()));
 		sql  = sql.replace("#{log_date}",String.valueOf(t.getLogDate()));
 		sql  = sql.replace("#{now_num}",String.valueOf(t.getNowNum()));
 		sql  = sql.replace("#{num}",String.valueOf(t.getNum()));
 		sql  = sql.replace("#{bill_no}",String.valueOf(t.getBillNo()));
 		sql  = sql.replace("#{bill_type}",String.valueOf(t.getBillType()));
 		sql  = sql.replace("#{create_by}",String.valueOf(t.getCreateBy()));
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
					javaInter.execute("l_ba_mate_inventory_log",data);
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("执行JAVA增强出现异常！");
			} 
		}
 	}
}