package cn.com.linkwide.cont.contbank.service;
import cn.com.linkwide.cont.contbank.entity.ContBankEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface ContBankServiceI extends CommonService{
	
 	public void delete(ContBankEntity entity) throws Exception;
 	
 	public Serializable save(ContBankEntity entity) throws Exception;
 	
 	public void saveOrUpdate(ContBankEntity entity) throws Exception;
 	
}
