package cn.com.linkwide.cont.desgin.procolcont.service;
import cn.com.linkwide.cont.desgin.procolcont.entity.ProcolContEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface ProcolContServiceI extends CommonService{
	
 	public void delete(ProcolContEntity entity) throws Exception;
 	
 	public Serializable save(ProcolContEntity entity) throws Exception;
 	
 	public void saveOrUpdate(ProcolContEntity entity) throws Exception;
 	
}
