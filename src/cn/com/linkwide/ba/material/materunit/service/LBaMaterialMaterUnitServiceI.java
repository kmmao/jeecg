package cn.com.linkwide.ba.material.materunit.service;
import java.io.Serializable;
import java.util.List;

import org.jeecgframework.core.common.service.CommonService;

import cn.com.linkwide.ba.material.materunit.entity.LBaMaterialMaterUnitEntity;

public interface LBaMaterialMaterUnitServiceI extends CommonService{
	
 	public void delete(LBaMaterialMaterUnitEntity entity) throws Exception;
 	
 	public Serializable save(LBaMaterialMaterUnitEntity entity) throws Exception;
 	
 	public void saveOrUpdate(LBaMaterialMaterUnitEntity entity) throws Exception;

 	/**
 	 * 导出选中的数据
 	 * @param ids
 	 * @return
 	 */
	public List<LBaMaterialMaterUnitEntity> getDataForExport(String ids);
 	
}
