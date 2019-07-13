package cn.com.linkwide.ba.babudgetrecord.service;
import cn.com.linkwide.ba.babudgetrecord.entity.BaBudgetRecordEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface BaBudgetRecordServiceI extends CommonService{
	
 	public void delete(BaBudgetRecordEntity entity) throws Exception;
 	
 	public Serializable save(BaBudgetRecordEntity entity) throws Exception;
 	
 	public void saveOrUpdate(BaBudgetRecordEntity entity) throws Exception;
 	
}
