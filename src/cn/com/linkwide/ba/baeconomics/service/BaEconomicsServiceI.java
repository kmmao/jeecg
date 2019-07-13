package cn.com.linkwide.ba.baeconomics.service;
import cn.com.linkwide.ba.baeconomics.entity.BaEconomicsEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface BaEconomicsServiceI extends CommonService{
	
 	public void delete(BaEconomicsEntity entity) throws Exception;
 	
 	public Serializable save(BaEconomicsEntity entity) throws Exception;
 	
 	public void saveOrUpdate(BaEconomicsEntity entity) throws Exception;
 	
}
