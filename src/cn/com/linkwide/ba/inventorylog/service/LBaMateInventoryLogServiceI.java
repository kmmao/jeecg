package cn.com.linkwide.ba.inventorylog.service;
import cn.com.linkwide.ba.inventorylog.entity.LBaMateInventoryLogEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface LBaMateInventoryLogServiceI extends CommonService{
	
 	public void delete(LBaMateInventoryLogEntity entity) throws Exception;
 	
 	public Serializable save(LBaMateInventoryLogEntity entity) throws Exception;
 	
 	public void saveOrUpdate(LBaMateInventoryLogEntity entity) throws Exception;
 	
}
