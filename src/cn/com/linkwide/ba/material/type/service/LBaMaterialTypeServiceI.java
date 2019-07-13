package cn.com.linkwide.ba.material.type.service;
import org.jeecgframework.core.common.service.CommonService;

import cn.com.linkwide.ba.material.type.entity.LBaMaterialTypeEntity;

import java.io.Serializable;

public interface LBaMaterialTypeServiceI extends CommonService{
	
 	public void delete(LBaMaterialTypeEntity entity) throws Exception;
 	
 	public Serializable save(LBaMaterialTypeEntity entity) throws Exception;
 	
 	public void saveOrUpdate(LBaMaterialTypeEntity entity) throws Exception;
 
 	/**
 	 * 递归查询某个节点下所有的子节点，不包括本级
 	 * @param parentid
 	 * @param resultlist
 	 * @return
 	 */
 	public String getchildIdsNoCurr(String parentid);
}
