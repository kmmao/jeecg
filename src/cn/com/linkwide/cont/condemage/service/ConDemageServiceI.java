package cn.com.linkwide.cont.condemage.service;
import cn.com.linkwide.cont.condemage.entity.ConDemageEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface ConDemageServiceI extends CommonService{
	
 	public void delete(ConDemageEntity entity) throws Exception;
 	
 	public Serializable save(ConDemageEntity entity) throws Exception;
 	
 	public void saveOrUpdate(ConDemageEntity entity) throws Exception;
 	
}
