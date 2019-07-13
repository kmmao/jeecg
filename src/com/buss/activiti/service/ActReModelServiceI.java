package com.buss.activiti.service;
import java.io.Serializable;

import org.jeecgframework.core.common.service.CommonService;

import com.buss.activiti.entity.ActReModelEntity;

public interface ActReModelServiceI extends CommonService{
	
 	public void delete(ActReModelEntity entity) throws Exception;
 	
 	public Serializable save(ActReModelEntity entity) throws Exception;
 	
 	public void saveOrUpdate(ActReModelEntity entity) throws Exception;
 	
}
