package cn.com.linkwide.ba.minventory.service;
import cn.com.linkwide.ba.minventory.entity.LBaMateInventoryEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface LBaMateInventoryServiceI extends CommonService{
	
 	public void delete(LBaMateInventoryEntity entity) throws Exception;
 	
 	public Serializable save(LBaMateInventoryEntity entity) throws Exception;
 	
 	public void saveOrUpdate(LBaMateInventoryEntity entity) throws Exception;
 	
 	/**
 	 * 调整库存
 	 * @param entity
 	 * @throws Exception
 	 */
 	public void changeInventory(LBaMateInventoryEntity entity) throws Exception;
 	
 	/**
 	 * 快捷入出库调整库存
 	 * @param entity
 	 * @throws Exception
 	 */
 	public void quickInOutInventory(LBaMateInventoryEntity entity,String inBillNo,String outBillNo) throws Exception;
 	
 	/**
 	 * 查询库存
 	 * @param entity
 	 * @return
 	 */
 	public double queryInventory(LBaMateInventoryEntity entity);
 	
 	/**
 	 * 查询库存
 	 * @param entity
 	 * @return
 	 */
 	public List<LBaMateInventoryEntity> queryInventory(String warehouseId,String materialIds,String isAgency);
 	
 	/**
 	 * 查询指定仓库库存
 	 * @param entity
 	 * @return
 	 */
 	public List<Map<String, Object>> queryInventory(String warehouseId,String materialId,String supplierId,String isBarCode,String... params);
 	
 	
 	/**
 	 * (代管)查询指定仓库库存 is_agency ='1'
 	 * @param entity
 	 * @return
 	 */
 	public List<Map<String, Object>> queryAgencyInventory(String warehouseId,String materialId,String supplierId,String isBarCode,String... params);
 	
}
