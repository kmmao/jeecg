package cn.com.linkwide.ba.suppliertype.service;
import java.io.Serializable;

import org.jeecgframework.core.common.service.CommonService;

import cn.com.linkwide.ba.suppliertype.entity.LBaSupplierTypeEntity;

public interface LBaSupplierTypeServiceI extends CommonService{
	
 	public void delete(LBaSupplierTypeEntity entity) throws Exception;
 	
 	public Serializable save(LBaSupplierTypeEntity entity) throws Exception;
 	
 	public void saveOrUpdate(LBaSupplierTypeEntity entity) throws Exception;
 	
}
