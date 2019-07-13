package cn.com.linkwide.cont.negotiation.conwarrantybill.service;
import cn.com.linkwide.cont.negotiation.conwarrantybill.entity.ConWarrantyBillEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface ConWarrantyBillServiceI extends CommonService{
	
 	public void delete(ConWarrantyBillEntity entity) throws Exception;
 	
 	public Serializable save(ConWarrantyBillEntity entity) throws Exception;
 	
 	public void saveOrUpdate(ConWarrantyBillEntity entity) throws Exception;
 	
}
