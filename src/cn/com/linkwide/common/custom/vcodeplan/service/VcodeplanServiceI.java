package cn.com.linkwide.common.custom.vcodeplan.service;
import org.jeecgframework.core.common.service.CommonService;

import cn.com.linkwide.common.custom.vcodeplan.entity.VcodeplanEntity;

import java.io.Serializable;

public interface VcodeplanServiceI extends CommonService{
	
 	public void delete(VcodeplanEntity entity) throws Exception;
 	
 	public Serializable save(VcodeplanEntity entity) throws Exception;
 	
 	public void saveOrUpdate(VcodeplanEntity entity) throws Exception;
 	
}
