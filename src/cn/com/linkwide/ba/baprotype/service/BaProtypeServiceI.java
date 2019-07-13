package cn.com.linkwide.ba.baprotype.service;
import cn.com.linkwide.ba.baprotype.entity.BaProtypeEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface BaProtypeServiceI extends CommonService{
	
 	public void delete(BaProtypeEntity entity) throws Exception;
 	
 	public Serializable save(BaProtypeEntity entity) throws Exception;
 	
 	public void saveOrUpdate(BaProtypeEntity entity) throws Exception;
 	
}
