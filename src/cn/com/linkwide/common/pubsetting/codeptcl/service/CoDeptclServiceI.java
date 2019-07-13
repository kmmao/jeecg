package cn.com.linkwide.common.pubsetting.codeptcl.service;
import org.jeecgframework.core.common.service.CommonService;

import cn.com.linkwide.common.pubsetting.codeptcl.entity.CoDeptclEntity;

import java.io.Serializable;

public interface CoDeptclServiceI extends CommonService{
	
 	public void delete(CoDeptclEntity entity) throws Exception;
 	
 	public Serializable save(CoDeptclEntity entity) throws Exception;
 	
 	public void saveOrUpdate(CoDeptclEntity entity) throws Exception;
 	
}
