package cn.com.linkwide.ba.location.service;
import cn.com.linkwide.ba.location.entity.LBaLocationDictEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface LBaLocationDictServiceI extends CommonService{
	
 	public void delete(LBaLocationDictEntity entity) throws Exception;
 	
 	public Serializable save(LBaLocationDictEntity entity) throws Exception;
 	
 	public void saveOrUpdate(LBaLocationDictEntity entity) throws Exception;
 	
}
