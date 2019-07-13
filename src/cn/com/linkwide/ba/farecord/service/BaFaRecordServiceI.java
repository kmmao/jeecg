package cn.com.linkwide.ba.farecord.service;
import cn.com.linkwide.ba.farecord.entity.BaFaRecordEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface BaFaRecordServiceI extends CommonService{
	
 	public void delete(BaFaRecordEntity entity) throws Exception;
 	
 	public Serializable save(BaFaRecordEntity entity) throws Exception;
 	
 	public void saveOrUpdate(BaFaRecordEntity entity) throws Exception;
 	
}
