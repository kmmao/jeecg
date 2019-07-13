package cn.com.linkwide.cont.conmemorabilia.service;
import cn.com.linkwide.cont.conmemorabilia.entity.ConMemorabiliaEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface ConMemorabiliaServiceI extends CommonService{
	
 	public void delete(ConMemorabiliaEntity entity) throws Exception;
 	
 	public Serializable save(ConMemorabiliaEntity entity) throws Exception;
 	
 	public void saveOrUpdate(ConMemorabiliaEntity entity) throws Exception;
 	
}
