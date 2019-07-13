package cn.com.linkwide.cont.payplan.service;
import cn.com.linkwide.cont.payplan.entity.PayPlanEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface PayPlanServiceI extends CommonService{
	
 	public void delete(PayPlanEntity entity) throws Exception;
 	
 	public Serializable save(PayPlanEntity entity) throws Exception;
 	
 	public void saveOrUpdate(PayPlanEntity entity) throws Exception;
 	
}
