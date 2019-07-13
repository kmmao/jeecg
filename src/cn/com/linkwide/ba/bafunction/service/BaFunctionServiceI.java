package cn.com.linkwide.ba.bafunction.service;
import cn.com.linkwide.ba.bafunction.entity.BaFunctionEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface BaFunctionServiceI extends CommonService{
	
 	public void delete(BaFunctionEntity entity) throws Exception;
 	
 	public Serializable save(BaFunctionEntity entity) throws Exception;
 	
 	public void saveOrUpdate(BaFunctionEntity entity) throws Exception;
 	
}
