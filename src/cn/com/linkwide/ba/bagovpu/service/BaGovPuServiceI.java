package cn.com.linkwide.ba.bagovpu.service;
import cn.com.linkwide.ba.bagovpu.entity.BaGovPuEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface BaGovPuServiceI extends CommonService{
	
 	public void delete(BaGovPuEntity entity) throws Exception;
 	
 	public Serializable save(BaGovPuEntity entity) throws Exception;
 	
 	public void saveOrUpdate(BaGovPuEntity entity) throws Exception;
 	
}
