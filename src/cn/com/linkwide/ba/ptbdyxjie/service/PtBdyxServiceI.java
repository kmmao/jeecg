package cn.com.linkwide.ba.ptbdyxjie.service;
import cn.com.linkwide.ba.ptbdyxjie.entity.PtBdyxEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface PtBdyxServiceI extends CommonService{
	
 	public void delete(PtBdyxEntity entity) throws Exception;
 	
 	public Serializable save(PtBdyxEntity entity) throws Exception;
 	
 	public void saveOrUpdate(PtBdyxEntity entity) throws Exception;
 	
}
