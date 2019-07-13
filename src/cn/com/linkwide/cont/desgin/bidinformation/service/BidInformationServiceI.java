package cn.com.linkwide.cont.desgin.bidinformation.service;
import cn.com.linkwide.cont.desgin.bidinformation.entity.BidInformationEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface BidInformationServiceI extends CommonService{
	
 	public void delete(BidInformationEntity entity) throws Exception;
 	
 	public Serializable save(BidInformationEntity entity) throws Exception;
 	
 	public void saveOrUpdate(BidInformationEntity entity) throws Exception;
 	
}
