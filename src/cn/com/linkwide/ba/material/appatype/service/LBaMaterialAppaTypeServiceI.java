package cn.com.linkwide.ba.material.appatype.service;
import cn.com.linkwide.ba.material.appatype.entity.LBaMaterialAppaTypeEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface LBaMaterialAppaTypeServiceI extends CommonService{
	
 	public void delete(LBaMaterialAppaTypeEntity entity) throws Exception;
 	
 	public Serializable save(LBaMaterialAppaTypeEntity entity) throws Exception;
 	
 	public void saveOrUpdate(LBaMaterialAppaTypeEntity entity) throws Exception;
 	
}
