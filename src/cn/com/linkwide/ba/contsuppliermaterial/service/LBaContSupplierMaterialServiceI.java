package cn.com.linkwide.ba.contsuppliermaterial.service;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.jeecgframework.core.common.service.CommonService;

import cn.com.linkwide.ba.contsuppliermaterial.entity.LBaContSupplierMaterialEntity;
import cn.com.linkwide.ba.supplier.entity.LBaSupplierEntity;

public interface LBaContSupplierMaterialServiceI extends CommonService{
	
 	public void delete(LBaContSupplierMaterialEntity entity) throws Exception;
 	
 	public Serializable save(LBaContSupplierMaterialEntity entity) throws Exception;
 	
 	public void saveOrUpdate(LBaContSupplierMaterialEntity entity) throws Exception;
 	
 	/*** 检查供应商与物资之间关系*/
 	public boolean checkSupplierMaterial(String supplierId,List<String> materialIdList,StringBuffer errorMsg)throws Exception;
 	/*** 检查供应商与物资之间关系key:物资，value：供应商*/
 	public boolean checkSupplierMaterial(Map<String, String> smMap,StringBuffer errorMsg)throws Exception;

 	/**
 	 * 导出选中的数据
 	 * @param ids
 	 * @return
 	 */
	public List<LBaContSupplierMaterialEntity> getExportData(String ids);
	/**
	 * 获取物资对应的供应商
	 */
	public List<LBaSupplierEntity> getSupplierByMaterial(String materialIds);
}
