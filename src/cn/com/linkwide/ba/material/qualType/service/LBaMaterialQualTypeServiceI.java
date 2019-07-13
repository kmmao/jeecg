package cn.com.linkwide.ba.material.qualType.service;
import java.io.Serializable;
import java.util.List;

import org.jeecgframework.core.common.model.json.ComboTree;
import org.jeecgframework.core.common.service.CommonService;

import cn.com.linkwide.ba.material.qualType.entity.LBaMaterialQualTypeEntity;

public interface LBaMaterialQualTypeServiceI extends CommonService{
	
 	public void delete(LBaMaterialQualTypeEntity entity) throws Exception;
 	
 	public Serializable save(LBaMaterialQualTypeEntity entity) throws Exception;
 	
 	public void saveOrUpdate(LBaMaterialQualTypeEntity entity) throws Exception;
 	 
}
