package cn.com.linkwide.ba.bawarninginfo.service;
import cn.com.linkwide.ba.bawarninginfo.entity.BaWarningInfoEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface BaWarningInfoServiceI extends CommonService{
	
 	public void delete(BaWarningInfoEntity entity) throws Exception;
 	
 	public Serializable save(BaWarningInfoEntity entity) throws Exception;
 	
 	public void saveOrUpdate(BaWarningInfoEntity entity) throws Exception;
 	
}
