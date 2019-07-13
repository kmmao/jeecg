package cn.com.linkwide.common.custom.bdaccperiodscheme.service;
import org.jeecgframework.core.common.service.CommonService;

import cn.com.linkwide.common.custom.bdaccperiodscheme.entity.BdAccperiodschemeEntity;

import java.io.Serializable;

public interface BdAccperiodschemeServiceI extends CommonService{
	
 	public void delete(BdAccperiodschemeEntity entity) throws Exception;
 	
 	public Serializable save(BdAccperiodschemeEntity entity) throws Exception;
 	
 	public void saveOrUpdate(BdAccperiodschemeEntity entity) throws Exception;
 	
}
