package cn.com.linkwide.ba.bacustomertype.service;
import cn.com.linkwide.ba.bacustomertype.entity.BaCustomerTypeEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface BaCustomerTypeServiceI extends CommonService{
	
 	public void delete(BaCustomerTypeEntity entity) throws Exception;
 	
 	public Serializable save(BaCustomerTypeEntity entity) throws Exception;
 	
 	public void saveOrUpdate(BaCustomerTypeEntity entity) throws Exception;
 	
}
