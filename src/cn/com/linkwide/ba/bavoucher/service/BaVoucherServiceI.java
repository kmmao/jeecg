package cn.com.linkwide.ba.bavoucher.service;
import cn.com.linkwide.ba.bavoucher.entity.BaVoucherEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface BaVoucherServiceI extends CommonService{
	
 	public void delete(BaVoucherEntity entity) throws Exception;
 	
 	public Serializable save(BaVoucherEntity entity) throws Exception;
 	
 	public void saveOrUpdate(BaVoucherEntity entity) throws Exception;
 	
}
