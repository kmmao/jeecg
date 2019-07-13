package cn.com.linkwide.ba.material.standtype.service;
import java.io.Serializable;

import org.jeecgframework.core.common.service.CommonService;

import cn.com.linkwide.ba.material.standtype.entity.LBaMaterialStandTypeEntity;

public interface LBaMaterialStandTypeServiceI extends CommonService{
	
 	public void delete(LBaMaterialStandTypeEntity entity) throws Exception;
 	
 	public Serializable save(LBaMaterialStandTypeEntity entity) throws Exception;
 	
 	public void saveOrUpdate(LBaMaterialStandTypeEntity entity) throws Exception;
 	
}
