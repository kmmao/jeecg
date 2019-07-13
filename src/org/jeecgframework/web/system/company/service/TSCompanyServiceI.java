package org.jeecgframework.web.system.company.service;
import org.jeecgframework.web.system.company.entity.TSCompany;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface TSCompanyServiceI extends CommonService{
	
 	public void delete(TSCompany entity) throws Exception;
 	
 	public Serializable save(TSCompany entity) throws Exception;
 	
 	public void saveOrUpdate(TSCompany entity) throws Exception;
 	
}
