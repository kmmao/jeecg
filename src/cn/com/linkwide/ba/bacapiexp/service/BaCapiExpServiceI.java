package cn.com.linkwide.ba.bacapiexp.service;
import cn.com.linkwide.ba.bacapiexp.entity.BaCapiExpEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface BaCapiExpServiceI extends CommonService{
	
 	public void delete(BaCapiExpEntity entity) throws Exception;
 	
 	public Serializable save(BaCapiExpEntity entity) throws Exception;
 	
 	public void saveOrUpdate(BaCapiExpEntity entity) throws Exception;
 	
}
