package cn.com.linkwide.ba.warehouse.service;
import java.io.Serializable;

import org.jeecgframework.core.common.service.CommonService;

import cn.com.linkwide.ba.warehouse.entity.LBaWarehouseEntity;

public interface LBaWarehouseServiceI extends CommonService{
	
 	public void delete(LBaWarehouseEntity entity) throws Exception;
 	
 	public Serializable save(LBaWarehouseEntity entity) throws Exception;
 	
 	public void saveOrUpdate(LBaWarehouseEntity entity) throws Exception;
 	
}
