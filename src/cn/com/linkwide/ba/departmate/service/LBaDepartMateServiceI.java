package cn.com.linkwide.ba.departmate.service;
import cn.com.linkwide.ba.departmate.entity.LBaDepartMateEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface LBaDepartMateServiceI extends CommonService{
	
 	public void delete(LBaDepartMateEntity entity) throws Exception;
 	
 	public Serializable save(LBaDepartMateEntity entity) throws Exception;
 	
 	public void saveOrUpdate(LBaDepartMateEntity entity) throws Exception;
 	
}
