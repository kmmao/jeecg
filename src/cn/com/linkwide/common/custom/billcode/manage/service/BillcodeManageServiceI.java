package cn.com.linkwide.common.custom.billcode.manage.service;
import org.jeecgframework.core.common.service.CommonService;

import cn.com.linkwide.common.custom.billcode.manage.entity.BillcodeManageEntity;

import java.io.Serializable;

public interface BillcodeManageServiceI extends CommonService{
	
 	public void delete(BillcodeManageEntity entity) throws Exception;
 	
 	public Serializable save(BillcodeManageEntity entity) throws Exception;
 	
 	public void saveOrUpdate(BillcodeManageEntity entity) throws Exception;
 	
}
