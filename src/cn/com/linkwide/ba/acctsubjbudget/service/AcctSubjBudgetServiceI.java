package cn.com.linkwide.ba.acctsubjbudget.service;
import cn.com.linkwide.ba.acctsubjbudget.entity.AcctSubjBudgetEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface AcctSubjBudgetServiceI extends CommonService{
	
 	public void delete(AcctSubjBudgetEntity entity) throws Exception;
 	
 	public Serializable save(AcctSubjBudgetEntity entity) throws Exception;
 	
 	public void saveOrUpdate(AcctSubjBudgetEntity entity) throws Exception;
 	
}
