package cn.com.linkwide.common.pubsetting.bdsharecl.service;
import org.jeecgframework.core.common.service.CommonService;

import cn.com.linkwide.common.pubsetting.bdsharecl.entity.BdShareclEntity;

import java.io.Serializable;

public interface BdShareclServiceI extends CommonService{
	
 	public void delete(BdShareclEntity entity) throws Exception;
 	
 	public Serializable save(BdShareclEntity entity) throws Exception;
 	
 	public void saveOrUpdate(BdShareclEntity entity) throws Exception;
 	
}
