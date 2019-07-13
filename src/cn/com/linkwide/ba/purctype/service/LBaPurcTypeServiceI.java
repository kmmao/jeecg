package cn.com.linkwide.ba.purctype.service;
import cn.com.linkwide.ba.purctype.entity.LBaPurcTypeEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface LBaPurcTypeServiceI extends CommonService{
	
 	public void delete(LBaPurcTypeEntity entity) throws Exception;
 	
 	public Serializable save(LBaPurcTypeEntity entity) throws Exception;
 	
 	public void saveOrUpdate(LBaPurcTypeEntity entity) throws Exception;
 	/**
 	 * 根据采购类型查询入库类型
 	 * @param purcType
 	 * @return
 	 */
 	public String getInTypeByPurcType(String purcType);
 	
}
