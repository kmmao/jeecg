package cn.com.linkwide.ba.material.record.service;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.web.system.pojo.base.TSDepart;

import cn.com.linkwide.ba.material.record.entity.LBaMaterialEntity;

public interface LBaMaterialServiceI extends CommonService{
	
 	public void delete(LBaMaterialEntity entity) throws Exception;
 	
 	public Serializable save(LBaMaterialEntity entity) throws Exception;
 	
 	public void saveOrUpdate(LBaMaterialEntity entity) throws Exception;
 	/**
 	 * 根据物资条形码获取物资基本信息
 	 */
	public LBaMaterialEntity getMaterialMsgByBarCode(String barCode,String wareHouseId,String supplierId)throws Exception;
	/**
 	 * 根据物资条形码获取物资入库基本信息
 	 */
	public LBaMaterialEntity getInWareMaterialMsgByBarCode(String barCode, String wareHouseId);
	
	/**
 	 * 根据时间获取未过期的物资id 默认当前时间
 	 * @param date //需要验证的时间
 	 * @return
 	 */
 	public List<String> getOverTimeMaterialIds(Date date);

 	/**
 	 * 导出选中的数据
 	 * @param ids
 	 * @return
 	 */
	public List<LBaMaterialEntity> getListForExportXls(String ids);
	
	/**
 	 * 数据导入保存操作
 	 * @param ids
 	 * @return
 	 */
	public List<LBaMaterialEntity> saveMaterialByList(List<LBaMaterialEntity> list);
	
	/**
	 * 根据物资字典生成hl7消息，并推送给集成平台
	 * @param entity
	 * @param map
	 */
	public void invoke(LBaMaterialEntity entity,Map map);
 	
}
