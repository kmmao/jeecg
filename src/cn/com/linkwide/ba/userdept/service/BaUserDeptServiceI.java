package cn.com.linkwide.ba.userdept.service;
import cn.com.linkwide.ba.userdept.entity.BaUserDeptEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface BaUserDeptServiceI extends CommonService{
	
 	public void delete(BaUserDeptEntity entity) throws Exception;
 	
 	public Serializable save(BaUserDeptEntity entity) throws Exception;
 	
 	public void saveOrUpdate(BaUserDeptEntity entity) throws Exception;
 	
}
