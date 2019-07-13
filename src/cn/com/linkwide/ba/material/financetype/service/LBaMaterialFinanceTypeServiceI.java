package cn.com.linkwide.ba.material.financetype.service;
import java.io.Serializable;

import org.jeecgframework.core.common.service.CommonService;

import cn.com.linkwide.ba.material.financetype.entity.LBaMaterialFinanceTypeEntity;

public interface LBaMaterialFinanceTypeServiceI extends CommonService{
	
 	public void delete(LBaMaterialFinanceTypeEntity entity) throws Exception;
 	
 	public Serializable save(LBaMaterialFinanceTypeEntity entity) throws Exception;
 	
 	public void saveOrUpdate(LBaMaterialFinanceTypeEntity entity) throws Exception;
 	
}
