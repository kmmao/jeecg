package cn.com.linkwide.ba.receivetype.service;
import cn.com.linkwide.ba.receivetype.entity.LBaReceiveTypeEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface LBaReceiveTypeServiceI extends CommonService{
	
 	public void delete(LBaReceiveTypeEntity entity) throws Exception;
 	
 	public Serializable save(LBaReceiveTypeEntity entity) throws Exception;
 	
 	public void saveOrUpdate(LBaReceiveTypeEntity entity) throws Exception;
 	
}
