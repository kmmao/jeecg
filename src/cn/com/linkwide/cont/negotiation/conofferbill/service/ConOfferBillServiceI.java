package cn.com.linkwide.cont.negotiation.conofferbill.service;
import java.io.Serializable;

import org.jeecgframework.core.common.service.CommonService;

import cn.com.linkwide.cont.negotiation.conofferbill.entity.ConOfferBillEntity;

public interface ConOfferBillServiceI extends CommonService{
	
 	public void delete(ConOfferBillEntity entity) throws Exception;
 	
 	public Serializable save(ConOfferBillEntity entity) throws Exception;
 	
 	public void saveOrUpdate(ConOfferBillEntity entity) throws Exception;
 	void approve(ConOfferBillEntity t,String actReModelCode);
 	
}
