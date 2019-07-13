package cn.com.linkwide.ba.baitemincom.service;
import cn.com.linkwide.ba.baitemincom.entity.BaItemIncomEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface BaItemIncomServiceI extends CommonService{
	
 	public void delete(BaItemIncomEntity entity) throws Exception;
 	
 	public Serializable save(BaItemIncomEntity entity) throws Exception;
 	
 	public void saveOrUpdate(BaItemIncomEntity entity) throws Exception;
 	
}
