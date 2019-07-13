package cn.com.linkwide.ba.material.qualitem.service;
import java.io.Serializable;

import org.jeecgframework.core.common.service.CommonService;

import cn.com.linkwide.ba.material.qualitem.entity.LSuMaterialQualItemEntity;

public interface LSuMaterialQualItemServiceI extends CommonService{
	
 	public void delete(LSuMaterialQualItemEntity entity) throws Exception;
 	
 	public Serializable save(LSuMaterialQualItemEntity entity) throws Exception;
 	
 	public void saveOrUpdate(LSuMaterialQualItemEntity entity) throws Exception;
 	
}
