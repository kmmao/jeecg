package cn.com.linkwide.ba.departaddr.service;
import cn.com.linkwide.ba.departaddr.entity.LBaDepartAddressEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface LBaDepartAddressServiceI extends CommonService{
	
 	public void delete(LBaDepartAddressEntity entity) throws Exception;
 	
 	public Serializable save(LBaDepartAddressEntity entity) throws Exception;
 	
 	public void saveOrUpdate(LBaDepartAddressEntity entity) throws Exception;
 	
}
