package cn.com.linkwide.cont.negotiation.confinalconfirmbill.service;
import cn.com.linkwide.cont.negotiation.confinalconfirmbill.entity.ConFinalConfirmbillEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface ConFinalConfirmbillServiceI extends CommonService{
	
 	public void delete(ConFinalConfirmbillEntity entity) throws Exception;
 	
 	public Serializable save(ConFinalConfirmbillEntity entity) throws Exception;
 	
 	public void saveOrUpdate(ConFinalConfirmbillEntity entity) throws Exception;
 	
}
