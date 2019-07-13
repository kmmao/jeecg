package cn.com.linkwide.ba.babankrollsource.service;
import cn.com.linkwide.ba.babankrollsource.entity.BaBankrollSourceEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface BaBankrollSourceServiceI extends CommonService{
	
 	public void delete(BaBankrollSourceEntity entity) throws Exception;
 	
 	public Serializable save(BaBankrollSourceEntity entity) throws Exception;
 	
 	public void saveOrUpdate(BaBankrollSourceEntity entity) throws Exception;
 	
}
