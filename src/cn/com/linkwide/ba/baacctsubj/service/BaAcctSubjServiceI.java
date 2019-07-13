package cn.com.linkwide.ba.baacctsubj.service;
import cn.com.linkwide.ba.baacctsubj.entity.BaAcctSubjEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface BaAcctSubjServiceI extends CommonService{
	
 	public void delete(BaAcctSubjEntity entity) throws Exception;
 	
 	public Serializable save(BaAcctSubjEntity entity) throws Exception;
 	
 	public void saveOrUpdate(BaAcctSubjEntity entity) throws Exception;
 	
}
