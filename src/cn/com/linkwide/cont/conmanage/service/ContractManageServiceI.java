package cn.com.linkwide.cont.conmanage.service;
import cn.com.linkwide.cont.conmanage.entity.ContractManageEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface ContractManageServiceI extends CommonService{
	
 	public void delete(ContractManageEntity entity) throws Exception;
 	
 	public Serializable save(ContractManageEntity entity) throws Exception;
 	
 	public void saveOrUpdate(ContractManageEntity entity) throws Exception;
 	
}
