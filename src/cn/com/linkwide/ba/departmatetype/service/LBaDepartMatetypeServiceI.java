package cn.com.linkwide.ba.departmatetype.service;
import cn.com.linkwide.ba.departmatetype.entity.LBaDepartMatetypeEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface LBaDepartMatetypeServiceI extends CommonService{
	
 	public void delete(LBaDepartMatetypeEntity entity) throws Exception;
 	
 	public Serializable save(LBaDepartMatetypeEntity entity) throws Exception;
 	
 	public void saveOrUpdate(LBaDepartMatetypeEntity entity) throws Exception;
 	
}
