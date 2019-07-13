package cn.com.linkwide.ba.material.measureunit.service;
import java.io.Serializable;

import org.jeecgframework.core.common.service.CommonService;

import cn.com.linkwide.ba.material.measureunit.entity.LBaMaterialMeasureUnitEntity;

public interface LBaMaterialMeasureUnitServiceI extends CommonService{
	
 	public void delete(LBaMaterialMeasureUnitEntity entity) throws Exception;
 	
 	public Serializable save(LBaMaterialMeasureUnitEntity entity) throws Exception;
 	
 	public void saveOrUpdate(LBaMaterialMeasureUnitEntity entity) throws Exception;
 	
}
