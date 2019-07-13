package cn.com.linkwide.ba.vouchercategory.service;
import cn.com.linkwide.ba.vouchercategory.entity.VoucherCategoryEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface VoucherCategoryServiceI extends CommonService{
	
 	public void delete(VoucherCategoryEntity entity) throws Exception;
 	
 	public Serializable save(VoucherCategoryEntity entity) throws Exception;
 	
 	public void saveOrUpdate(VoucherCategoryEntity entity) throws Exception;
 	
}
