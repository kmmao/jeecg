package cn.com.linkwide.cont.desgin.conruledeasgin.service;
import cn.com.linkwide.cont.desgin.conruledeasgin.entity.ConruleDeasginEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface ConruleDeasginServiceI extends CommonService{
	
 	public void delete(ConruleDeasginEntity entity) throws Exception;
 	
 	public Serializable save(ConruleDeasginEntity entity) throws Exception;
 	
 	public void saveOrUpdate(ConruleDeasginEntity entity) throws Exception;
 	
}
