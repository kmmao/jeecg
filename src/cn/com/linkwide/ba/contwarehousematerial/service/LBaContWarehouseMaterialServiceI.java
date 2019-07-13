package cn.com.linkwide.ba.contwarehousematerial.service;
import cn.com.linkwide.ba.contwarehousematerial.entity.LBaContWarehouseMaterialEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface LBaContWarehouseMaterialServiceI extends CommonService{
	
 	public void delete(LBaContWarehouseMaterialEntity entity) throws Exception;
 	
 	public Serializable save(LBaContWarehouseMaterialEntity entity) throws Exception;
 	
 	public void saveOrUpdate(LBaContWarehouseMaterialEntity entity) throws Exception;
 	/*** 检查仓库与物资之间关系*/
 	public boolean checkWareMaterial(String wareId,List<String> materialIdList,StringBuffer errorMsg)throws Exception;
 	/*** 检查仓库与物资之间关系*/
 	public boolean checkWareMaterial(Map<String, String> smMap,StringBuffer errorMsg)throws Exception;

 	/*** 导出选中的数据*/
	public List<LBaContWarehouseMaterialEntity> getDataForExport(String ids);
}
