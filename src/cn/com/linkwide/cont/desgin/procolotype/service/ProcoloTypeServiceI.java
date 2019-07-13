package cn.com.linkwide.cont.desgin.procolotype.service;
import cn.com.linkwide.cont.desgin.procolotype.entity.ProcoloTypeEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface ProcoloTypeServiceI extends CommonService{
	
 	public void delete(ProcoloTypeEntity entity) throws Exception;
 	
 	public Serializable save(ProcoloTypeEntity entity) throws Exception;
 	
 	public void saveOrUpdate(ProcoloTypeEntity entity) throws Exception;
 	
}
