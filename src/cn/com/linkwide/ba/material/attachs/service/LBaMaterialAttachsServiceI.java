package cn.com.linkwide.ba.material.attachs.service;
import java.io.Serializable;

import org.jeecgframework.core.common.service.CommonService;

import cn.com.linkwide.ba.material.attachs.entity.LBaMaterialAttachsEntity;

public interface LBaMaterialAttachsServiceI extends CommonService{
	
 	public void delete(LBaMaterialAttachsEntity entity) throws Exception;
 	
 	public Serializable save(LBaMaterialAttachsEntity entity) throws Exception;
 	
 	public void saveOrUpdate(LBaMaterialAttachsEntity entity) throws Exception;
 	
}
