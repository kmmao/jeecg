package cn.com.linkwide.ba.rpmap.service;
import cn.com.linkwide.ba.rpmap.entity.LBaRecePurcMapEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface LBaRecePurcMapServiceI extends CommonService{
	
 	public void delete(LBaRecePurcMapEntity entity) throws Exception;
 	
 	public Serializable save(LBaRecePurcMapEntity entity) throws Exception;
 	
 	public void saveOrUpdate(LBaRecePurcMapEntity entity) throws Exception;
 	
}
