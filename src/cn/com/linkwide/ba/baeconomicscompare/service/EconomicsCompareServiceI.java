package cn.com.linkwide.ba.baeconomicscompare.service;
import cn.com.linkwide.ba.baeconomicscompare.entity.EconomicsCompareEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface EconomicsCompareServiceI extends CommonService{
	
 	public void delete(EconomicsCompareEntity entity) throws Exception;
 	
 	public Serializable save(EconomicsCompareEntity entity) throws Exception;
 	
 	public void saveOrUpdate(EconomicsCompareEntity entity) throws Exception;
 	
}
