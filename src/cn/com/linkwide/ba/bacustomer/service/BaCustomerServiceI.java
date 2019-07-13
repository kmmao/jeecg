package cn.com.linkwide.ba.bacustomer.service;
import java.io.Serializable;
import java.util.List;

import org.jeecgframework.core.common.service.CommonService;

import cn.com.linkwide.ba.bacustomer.entity.BaCustomerEntity;

public interface BaCustomerServiceI extends CommonService{
	
 	public void delete(BaCustomerEntity entity) throws Exception;
 	
 	public Serializable save(BaCustomerEntity entity) throws Exception;
 	
 	public void saveOrUpdate(BaCustomerEntity entity) throws Exception;
 	//同步数据到u8
 	public  void synToU8(List<BaCustomerEntity> list);
}
