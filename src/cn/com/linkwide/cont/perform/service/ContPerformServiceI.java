package cn.com.linkwide.cont.perform.service;
import cn.com.linkwide.cont.perform.entity.ContPerformEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface ContPerformServiceI extends CommonService{
	
 	public void delete(ContPerformEntity entity) throws Exception;
 	
 	public Serializable save(ContPerformEntity entity) throws Exception;
 	
 	public void saveOrUpdate(ContPerformEntity entity) throws Exception;
 	
}
