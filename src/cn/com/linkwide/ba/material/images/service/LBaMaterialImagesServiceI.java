package cn.com.linkwide.ba.material.images.service;
import java.io.Serializable;

import org.jeecgframework.core.common.service.CommonService;

import cn.com.linkwide.ba.material.images.entity.LBaMaterialImagesEntity;

public interface LBaMaterialImagesServiceI extends CommonService{
	
 	public void delete(LBaMaterialImagesEntity entity) throws Exception;
 	
 	public Serializable save(LBaMaterialImagesEntity entity) throws Exception;
 	
 	public void saveOrUpdate(LBaMaterialImagesEntity entity) throws Exception;
 	
}
