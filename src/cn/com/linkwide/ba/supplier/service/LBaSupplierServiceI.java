package cn.com.linkwide.ba.supplier.service;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.service.CommonService;

import cn.com.linkwide.ba.supplier.entity.LBaSupplierEntity;

public interface LBaSupplierServiceI extends CommonService{
	
 	public void delete(LBaSupplierEntity entity) throws Exception;
 	
 	public Serializable save(LBaSupplierEntity entity) throws Exception;
 	
 	public Serializable save(List<LBaSupplierEntity> list) throws Exception;
 	
 	public void saveOrUpdate(LBaSupplierEntity entity) throws Exception;
 	
 	/**
 	 * 判断供应商在查询日期间，是否存在不在资质有效期内的情况，若有效期已过期，则false封装提示信息;无资质/资质有效，返回true
 	 * @param supplierIds 供应商id
 	 * @return
 	 * @throws BusinessException
 	 */
 	public AjaxJson checkQual(List<String> supplierIds) throws BusinessException;
 	/**
 	 * 向u8推送数据
 	 * @param entity
 	 * @return
 	 */
 	public boolean sendVouch(List<LBaSupplierEntity> list);
}
