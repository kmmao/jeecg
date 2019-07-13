package cn.com.linkwide.ba.baitemtype.service;
import cn.com.linkwide.ba.baitemtype.entity.BaItemtypeEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface BaItemtypeServiceI extends CommonService{
	
 	public void delete(BaItemtypeEntity entity) throws Exception;
 	
 	public Serializable save(BaItemtypeEntity entity) throws Exception;
 	
 	public void saveOrUpdate(BaItemtypeEntity entity) throws Exception;
 	
}
