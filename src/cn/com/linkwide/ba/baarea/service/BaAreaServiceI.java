package cn.com.linkwide.ba.baarea.service;
import cn.com.linkwide.ba.baarea.entity.BaAreaEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface BaAreaServiceI extends CommonService{
	
 	public void delete(BaAreaEntity entity) throws Exception;
 	
 	public Serializable save(BaAreaEntity entity) throws Exception;
 	
 	public void saveOrUpdate(BaAreaEntity entity) throws Exception;
 	
}
