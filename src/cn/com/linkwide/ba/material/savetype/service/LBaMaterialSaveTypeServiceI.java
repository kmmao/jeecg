package cn.com.linkwide.ba.material.savetype.service;
import java.io.Serializable;

import org.jeecgframework.core.common.service.CommonService;

import cn.com.linkwide.ba.material.savetype.entity.LBaMaterialSaveTypeEntity;

public interface LBaMaterialSaveTypeServiceI extends CommonService{
	
 	public void delete(LBaMaterialSaveTypeEntity entity) throws Exception;
 	
 	public Serializable save(LBaMaterialSaveTypeEntity entity) throws Exception;
 	
 	public void saveOrUpdate(LBaMaterialSaveTypeEntity entity) throws Exception;
 	
}
