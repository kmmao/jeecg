package cn.com.linkwide.ba.material.record.service.impl;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.ApplicationContextUtil;
import org.jeecgframework.core.util.MyClassLoader;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.web.cgform.enhance.CgformEnhanceJavaInter;
import org.jeecgframework.web.system.service.SystemService;
/*import org.jeecgframework.web.util.EsbPushDataUtil;*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/*import ca.uhn.hl7v2.HL7Exception;
import ca.uhn.hl7v2.model.v24.message.QBP_Q11;
import ca.uhn.hl7v2.model.v24.segment.MSH;*/
import cn.com.linkwide.ba.contsuppliermaterial.controller.LBaContSupplierMaterialController;
import cn.com.linkwide.ba.contsuppliermaterial.entity.LBaContSupplierMaterialEntity;
import cn.com.linkwide.ba.contwarehousematerial.entity.LBaContWarehouseMaterialEntity;
import cn.com.linkwide.ba.material.attachs.entity.LBaMaterialAttachsEntity;
import cn.com.linkwide.ba.material.images.entity.LBaMaterialImagesEntity;
import cn.com.linkwide.ba.material.measureunit.entity.LBaMaterialMeasureUnitEntity;
import cn.com.linkwide.ba.material.record.entity.LBaMaterialEntity;
import cn.com.linkwide.ba.material.record.service.LBaMaterialServiceI;
import cn.com.linkwide.ba.syn.SynchronousBa;

@Service("lBaMaterialService")
@Transactional
public class LBaMaterialServiceImpl extends CommonServiceImpl implements LBaMaterialServiceI {
	@Autowired
	private SystemService systemService;
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	
 	public void delete(LBaMaterialEntity entity) throws Exception{
 		//删除附件
 		List<LBaMaterialAttachsEntity> list4 = this.findByQueryString(" from LBaMaterialAttachsEntity where materialId = '"+entity.getId()+"'");
 		this.deleteAllEntitie(list4);
 		//删除图片
 		List<LBaMaterialImagesEntity> list5 = this.findByQueryString(" from LBaMaterialImagesEntity where materialId = '"+entity.getId()+"'");
 		this.deleteAllEntitie(list5);
 		//删除辅助计量单位
 		List<LBaMaterialMeasureUnitEntity> list6 = this.findByQueryString(" from LBaMaterialMeasureUnitEntity where materialId = '"+entity.getId()+"'");
 		this.deleteAllEntitie(list6);
 		
 		super.delete(entity);
 		
 		
 		//执行删除操作增强业务
		this.doDelBus(entity);
		
		//同步数据
		String sql1 =SynchronousBa.synDelete(entity);
		if(StringUtil.isNotEmpty(sql1)){
			this.updateBySqlString(sql1);
		}
 	}
 	
 	public Serializable save(LBaMaterialEntity entity) throws Exception{
 		if(entity.getIsHighCons() ==1){
 			entity.setIsBarCode(1);
 		}
 		if(entity.getIsBarCode() == null){
 			entity.setIsBarCode(0);
 		}
 		if(entity.getIsShelfLife() == null){
 			entity.setIsShelfLife(0);
 		}
 		Serializable t = super.save(entity);
	 	if(StringUtil.isNotEmpty(entity.getBidId())){
	 		try {
				String sql="update mate_supmate_main set inv_id='"+entity.getId()+"' where id='"+entity.getBidId()+"'";
				systemService.updateBySqlString(sql);
			} catch (Exception e) {
				e.printStackTrace();
			}
	 	}
 		//增加供应商物资对照
 		if(StringUtil.isNotEmpty(entity.getSupplierId())){
 			String smHql = " from LBaContSupplierMaterialEntity where  supplierId ='"+entity.getSupplierId()+"' and materialId ='"+entity.getId()+"'"; 
	 		List<LBaContSupplierMaterialEntity> list1 = this.findHql(smHql);
	 		if(list1 == null || list1.size()==0){
	 			LBaContSupplierMaterialEntity sm = new LBaContSupplierMaterialEntity();
	 			sm.setSupplierId(entity.getSupplierId());
	 			sm.setMaterialId(entity.getId());
	 			sm.setCreateBy(ResourceUtil.getSessionUser().getUserName());
	 			sm.setDepartId(ResourceUtil.getSessionUser().getDepartid());
	 			sm.setCreateDate(new Date());
	 			super.save(sm);
	 		}
 		}
 		//增加仓库物资对照
 		if(StringUtil.isNotEmpty(entity.getWarehouseId())){
 			String smHql = " from LBaContWarehouseMaterialEntity where  warehouseId ='"+entity.getSupplierId()+"' and materialId ='"+entity.getId()+"'"; 
	 		List<LBaContWarehouseMaterialEntity> list1 = this.findHql(smHql);
	 		if(list1 == null || list1.size()==0){
	 			LBaContWarehouseMaterialEntity sm = new LBaContWarehouseMaterialEntity();
	 			sm.setWarehouseId(entity.getWarehouseId());
	 			sm.setMaterialId(entity.getId());
	 			sm.setCreateBy(ResourceUtil.getSessionUser().getUserName());
	 			sm.setDepartId(ResourceUtil.getSessionUser().getDepartid());
	 			sm.setCreateDate(new Date());
	 			super.save(sm);
	 		}
 		}
 		//执行新增操作增强业务
 		this.doAddBus(entity);
 		
 		//同步数据
		String sql =SynchronousBa.synSave(entity);
		if(StringUtil.isNotEmpty(sql)){
			this.updateBySqlString(sql);
		}
 		return t;
 	}
 	
 	public void saveOrUpdate(LBaMaterialEntity entity) throws Exception{
 		if(entity.getIsHighCons() ==1){
 			entity.setIsBarCode(1);
 		}
 		if(entity.getIsBarCode() == null){
 			entity.setIsBarCode(0);
 		}
 		if(entity.getIsShelfLife() == null){
 			entity.setIsShelfLife(0);
 		}
 		super.saveOrUpdate(entity);
 		
 		//增加供应商物资对照
 		if(StringUtil.isNotEmpty(entity.getSupplierId())){
 			String smHql = " from LBaContSupplierMaterialEntity where  supplierId ='"+entity.getSupplierId()+"' and materialId ='"+entity.getId()+"'"; 
	 		List<LBaContSupplierMaterialEntity> list1 = this.findHql(smHql);
	 		if(list1 == null || list1.size()==0){
	 			LBaContSupplierMaterialEntity sm = new LBaContSupplierMaterialEntity();
	 			sm.setSupplierId(entity.getSupplierId());
	 			sm.setMaterialId(entity.getId());
	 			sm.setCreateBy(ResourceUtil.getSessionUser().getUserName());
	 			sm.setDepartId(ResourceUtil.getSessionUser().getDepartid());
	 			sm.setCreateDate(new Date());
	 			super.save(sm);
	 		}
 		}
 		
 		//增加仓库物资对照
 		if(StringUtil.isNotEmpty(entity.getWarehouseId())){
 			String smHql = " from LBaContWarehouseMaterialEntity where  warehouseId ='"+entity.getWarehouseId()+"' and materialId ='"+entity.getId()+"'"; 
	 		List<LBaContWarehouseMaterialEntity> list1 = this.findHql(smHql);
	 		if(list1 == null || list1.size()==0){
	 			LBaContWarehouseMaterialEntity sm = new LBaContWarehouseMaterialEntity();
	 			sm.setWarehouseId(entity.getWarehouseId());
	 			sm.setMaterialId(entity.getId());
	 			sm.setCreateBy(ResourceUtil.getSessionUser().getUserName());
	 			sm.setDepartId(ResourceUtil.getSessionUser().getDepartid());
	 			sm.setCreateDate(new Date());
	 			super.save(sm);
	 		}
 		}
 		
 		//执行更新操作增强业务
 		this.doUpdateBus(entity);
 		
 		//同步数据
		String sql1 =SynchronousBa.synDelete(entity);
		if(StringUtil.isNotEmpty(sql1)){
			this.updateBySqlString(sql1);
		}
		String sql2 =SynchronousBa.synSave(entity);
		if(StringUtil.isNotEmpty(sql2)){
			this.updateBySqlString(sql2);
		}
 	}
 	

	/**
	 * 新增操作增强业务
	 * @param t 
	 * @return
	 */
	private void doAddBus(LBaMaterialEntity t) throws Exception{
 	}
 	/**
	 * 更新操作增强业务
	 * @param t
	 * @return
	 */
	private void doUpdateBus(LBaMaterialEntity t) throws Exception{
 	}
 	/**
	 * 删除操作增强业务
	 * @param id
	 * @return
	 */
	private void doDelBus(LBaMaterialEntity t) throws Exception{
 	}
 	
 	private Map<String,Object> populationMap(LBaMaterialEntity t){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", t.getId());
		map.put("material_code", t.getMaterialCode());
		map.put("material_name", t.getMaterialName());
		map.put("material_type_id", t.getMaterialTypeId());
		map.put("spec_model", t.getSpecModel());
		map.put("monm_code", t.getMonmCode());
		map.put("mater_unit_id", t.getMaterUnitId());
		map.put("appa_type_id", t.getAppaTypeId());
		map.put("finance_type_id", t.getFinanceTypeId());
		map.put("stand_type_id", t.getStandTypeId());
		map.put("is_cons", t.getIsCons());
		map.put("is_assets", t.getIsAssets());
		map.put("is_mater", t.getIsMater());
		map.put("supplier_id", t.getSupplierId());
		map.put("warehouse_id", t.getWarehouseId());
		map.put("max_stock", t.getMaxStock());
		map.put("safe_stock", t.getSafeStock());
		map.put("min_stock", t.getMinStock());
		map.put("is_batch", t.getIsBatch());
		map.put("is_shelf_life", t.getIsShelfLife());
		map.put("shelf_life", t.getShelfLife());
		map.put("is_high_cons", t.getIsHighCons());
		map.put("is_bar_code", t.getIsBarCode());
		map.put("bar_code", t.getBarCode());
		map.put("depart_id", t.getDepartId());
		map.put("create_by", t.getCreateBy());
		map.put("create_date", t.getCreateDate());
		map.put("update_by", t.getUpdateBy());
		map.put("update_date", t.getUpdateDate());
		map.put("is_instead", t.getIsInstead());
		map.put("unit_price", t.getUnitPrice());
		return map;
	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @param t
	 * @return
	 */
 	public String replaceVal(String sql,LBaMaterialEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{material_code}",String.valueOf(t.getMaterialCode()));
 		sql  = sql.replace("#{material_name}",String.valueOf(t.getMaterialName()));
 		sql  = sql.replace("#{material_type_id}",String.valueOf(t.getMaterialTypeId()));
 		sql  = sql.replace("#{spec_model}",String.valueOf(t.getSpecModel()));
 		sql  = sql.replace("#{monm_code}",String.valueOf(t.getMonmCode()));
 		sql  = sql.replace("#{mater_unit_id}",String.valueOf(t.getMaterUnitId()));
 		sql  = sql.replace("#{appa_type_id}",String.valueOf(t.getAppaTypeId()));
 		sql  = sql.replace("#{finance_type_id}",String.valueOf(t.getFinanceTypeId()));
 		sql  = sql.replace("#{stand_type_id}",String.valueOf(t.getStandTypeId()));
 		sql  = sql.replace("#{is_cons}",String.valueOf(t.getIsCons()));
 		sql  = sql.replace("#{is_assets}",String.valueOf(t.getIsAssets()));
 		sql  = sql.replace("#{is_mater}",String.valueOf(t.getIsMater()));
 		sql  = sql.replace("#{supplier_id}",String.valueOf(t.getSupplierId()));
 		sql  = sql.replace("#{warehouse_id}",String.valueOf(t.getWarehouseId()));
 		sql  = sql.replace("#{max_stock}",String.valueOf(t.getMaxStock()));
 		sql  = sql.replace("#{safe_stock}",String.valueOf(t.getSafeStock()));
 		sql  = sql.replace("#{min_stock}",String.valueOf(t.getMinStock()));
 		sql  = sql.replace("#{is_batch}",String.valueOf(t.getIsBatch()));
 		sql  = sql.replace("#{is_shelf_life}",String.valueOf(t.getIsShelfLife()));
 		sql  = sql.replace("#{shelf_life}",String.valueOf(t.getShelfLife()));
 		sql  = sql.replace("#{is_high_cons}",String.valueOf(t.getIsHighCons()));
 		sql  = sql.replace("#{is_bar_code}",String.valueOf(t.getIsBarCode()));
 		sql  = sql.replace("#{bar_code}",String.valueOf(t.getBarCode()));
 		sql  = sql.replace("#{depart_id}",String.valueOf(t.getDepartId()));
 		sql  = sql.replace("#{create_by}",String.valueOf(t.getCreateBy()));
 		sql  = sql.replace("#{create_date}",String.valueOf(t.getCreateDate()));
 		sql  = sql.replace("#{update_by}",String.valueOf(t.getUpdateBy()));
 		sql  = sql.replace("#{update_date}",String.valueOf(t.getUpdateDate()));
 		sql  = sql.replace("#{is_instead}",String.valueOf(t.getIsInstead()));
 		sql  = sql.replace("#{unit_price}",String.valueOf(t.getUnitPrice()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}
 	
 	/**
	 * 执行JAVA增强
	 */
 	private void executeJavaExtend(String cgJavaType,String cgJavaValue,Map<String,Object> data) throws Exception {
 		if(StringUtil.isNotEmpty(cgJavaValue)){
			Object obj = null;
			try {
				if("class".equals(cgJavaType)){
					//因新增时已经校验了实例化是否可以成功，所以这块就不需要再做一次判断
					obj = MyClassLoader.getClassByScn(cgJavaValue).newInstance();
				}else if("spring".equals(cgJavaType)){
					obj = ApplicationContextUtil.getContext().getBean(cgJavaValue);
				}
				if(obj instanceof CgformEnhanceJavaInter){
					CgformEnhanceJavaInter javaInter = (CgformEnhanceJavaInter) obj;
					javaInter.execute(data);
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("执行JAVA增强出现异常！");
			} 
		}
 	}
 	/**根据物资条形码获取物资基本信息*/
	@Override
	public LBaMaterialEntity getMaterialMsgByBarCode(String barCode,String wareHouseId,String supplierId) throws Exception {
		StringBuffer sql = new StringBuffer();
		spellBarCodeSql(sql, barCode,wareHouseId,supplierId);
		List<Map<String, Object>> list = this.findForJdbc(sql.toString());
		if(list==null || list.size()==0 || list.size()>1){
			throw new Exception("根据物资条形码查询物资信息出错：查无信息或查出多条信息");
		}
		LBaMaterialEntity entity = new LBaMaterialEntity();
		for (Map<String, Object> map : list) {
			if(map.get("bartype")==null)
				throw new Exception("根据物资条形码查询物资信息出错:物资无条形码类型");
			entity.setId((String)map.get("id"));//主键
			entity.setMaterialCode((String)map.get("material_code"));//物资编码
			entity.setMaterialName((String)map.get("material_name"));//物资名称 
			entity.setSpecModel(map.get("spec_model")==null?"":(String)map.get("spec_model"));//规格型号
			entity.setMonmCode(map.get("monm_code")==null?"":(String)map.get("monm_code"));//助记码 
			entity.setIsBarCode(((BigDecimal)map.get("is_bar_code")).intValue());//条形码管理
			entity.setBartype((String)map.get("bartype"));//条形码规则
			entity.setIsBatch(Integer.valueOf( map.get("is_batch").toString()));//批次号管理
			entity.setIsShelfLife(Integer.valueOf(map.get("is_shelf_life").toString()));//有效期管理
			entity.setMonmCode((String)map.get("id"));//主键
			if(map.get("unit_price") != null)
			entity.setUnitPrice( BigDecimal.valueOf((Double) map.get("unit_price")) );//预估单价
			entity.setBatchNo((String)map.get("batch_no"));
			entity.setMakeDate((Date)map.get("make_date"));
			entity.setExpiryDate((Date)map.get("expirydate"));
		}
		return entity;
	}
	/**拼接sql:根据物资条形码获取物资基本信息*/
 	public void spellBarCodeSql(StringBuffer sql,String barCode,String wareHouseId,String supplierId) {
 		sql.append(
 				"select bam.id,bam.material_code,bam.material_name,bam.spec_model,bam.monm_code,\n" +
				"	mau.type_name,bam.bartype,bam.unit_price,bam.is_bar_code,bam.is_batch,bam.is_shelf_life,batch_no,make_date,bar.expirydate \n" +
				"from l_ba_barcode bar\n" +
				"inner join l_ba_material bam on bar.material_id = bam.ID\n" +
				"inner join l_ba_material_mater_unit mau on mau.id = bam.mater_unit_id\n" +
				"where bar.barCode  = '"+barCode+"'"
 				);
 		if(!StringUtil.isEmpty(wareHouseId)){
 			sql.append(" and bam.ID in (select material_id from l_ba_cont_warehouse_material where warehouse_id='"+wareHouseId+"')");
 			
 		}
 		if(!StringUtil.isEmpty(supplierId)){
 			sql.append(" and bam.ID in (select material_id from l_ba_cont_supplier_material where supplier_id='"+supplierId+"')");
 			
 		}
 	}
 	/**根据物资条形码获取物资入库基本信息*/
 	//基础字典表引用的其他业务屏蔽掉zxl
	@Override
	public LBaMaterialEntity getInWareMaterialMsgByBarCode(String barCode, String wareHouseId) {
		/*if(StringUtil.isEmpty(barCode.trim()))
			return null;
		
		//1、根据条形码查询物资主键
		String hql_barCode = "from LBaBarcodeEntity where barcode=?";
		List<LBaBarcodeEntity> list_barCode = this.findHql(hql_barCode, barCode);
		if(list_barCode==null || list_barCode.size()==0){
			throw new BusinessException("根据物资条形码查询物资信息出错");
		}
		//2、查询物资当前库存
		String materialId = list_barCode.get(0).getMaterialId();
		List<LStMonthlyPeriodBalanceEntity> storeList = 
				lStMonthlyPeriodBalanceService.getCurrentStoreBanlance(new Date(), wareHouseId, "'"+materialId+"'");
		if(storeList==null || storeList.size()==0){11
			throw new BusinessException("条形码没有对应的库存");
		}
		//组装返回值
		LBaMaterialEntity entity = this.getEntity(LBaMaterialEntity.class, materialId);
		boolean flag = false;
		LStMonthlyPeriodBalanceEntity storeEntity = null;
		for (LStMonthlyPeriodBalanceEntity balanceEntity : storeList) {
			if ("1".equals(entity.getBartype())) {//个体码
				if (barCode.equals(balanceEntity.getBarCode())) {
					storeEntity = balanceEntity;
					flag = true;
					break;
				}
			}else{//品种码
				storeEntity = balanceEntity;
				flag = true;
				break;
			}
		}
		
		if (flag) {
			entity.setUnitPrice(storeEntity.getUnitPrice());//单价
			LBaMaterialMaterUnitEntity uEntity = this.getEntity(LBaMaterialMaterUnitEntity.class, entity.getMaterUnitId());
			
		}else{
			throw new BusinessException("条形码没有对应的库存");
		}
		
		return entity;*/
		
		return null;
	}
	/**拼接sql:根据物资条形码获取物资基本信息*/
 	public void spellInWareBarCodeSql(StringBuffer sql,String barCode,String wareHouseId){
 		sql.append(
 				"select bam.id,bam.material_code,bam.material_name,bam.spec_model,bam.monm_code,\n" +
				"	mau.type_name,bam.bartype,ind.unit_price,bam.is_bar_code,bam.is_batch,bam.is_shelf_life,"
				+ "ind.batch_no,ind.expirydate\n" +
				"from l_st_in_ware_detail ind\n" +
				"inner join l_st_in_ware ware on ind.in_ware_id = ware.id\n" +
				"inner join  l_ba_barcode bar on ind.bar_code = bar.barCode\n" +
				"inner join l_ba_material bam on bar.material_id = bam.ID\n" +
				"inner join l_ba_material_mater_unit mau on mau.id = bam.mater_unit_id\n" +
				"where bar.barCode  = '"+barCode.trim()+"'"
 				);
 		if(!StringUtil.isEmpty(wareHouseId)){
 			sql.append(" and bam.ID in (select material_id from l_ba_cont_warehouse_material where warehouse_id='"+wareHouseId+"')");
 			
 		}
 	}

	@Override
	public List<String> getOverTimeMaterialIds(Date date) {
		if(date == null){
			date = new Date();
		}
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
 		String date1 = simpleDateFormat.format(date);
		
		StringBuffer sql = new StringBuffer();
		sql.append(" select material.ID from l_ba_material material ");
		/*sql.append(" where id not IN ( ");
		sql.append(" select DISTINCT qual.material_id ");
		sql.append(" from l_su_material_qual qual ");
		//sql.append(" LEFT JOIN l_su_material_qual_item item on item.material_qual_id = qual.ID ");
		sql.append(" LEFT JOIN l_su_qual_type type on type.id = item.qual_type_id ");
		sql.append(" where qual.audit_status = '"+BillStatus.APPROVED+"' ");
		sql.append(" AND item.is_control = '1' ");
		//sql.append(" and (item.effect_date > '"+date1+"' OR item.over_date < '"+date1+"' ) ");
		sql.append(" GROUP BY type.id,qual.material_id ");
		sql.append(" ) or material.is_control = '0' ");*/
		
		List<String> qids = this.findListbySql(sql.toString());
		return qids;
	}

	/**
 	 * 导出选中的数据
 	 * @param ids
 	 * @return
 	 */
	@Override
	public List<LBaMaterialEntity> getListForExportXls(String ids) {
		String hql = "from LBaMaterialEntity where 1=1 " ;
		if(StringUtil.isNotEmpty(ids)){
			hql += " and id in ('"+ids+"') ";
		}
		List<LBaMaterialEntity> findListbySql = systemService.findByQueryString( hql );
		return findListbySql;
	}

	/**
	 * 根据物资字典生成hl7消息，并推送给集成平台
	 * @param entity
	 * @param map
	 */
	@Override
	public void invoke(LBaMaterialEntity entity, Map map) {
		if(true) return;
		/*if(entity != null && map != null){
			String operate = map.get("operation")==null?null:map.get("operation").toString();
			if(StringUtil.isNotEmpty(operate)){
				try {
					//生成消息头
					QBP_Q11 qbp_q11 = new QBP_Q11();
					MSH msh = qbp_q11.getMSH();
					setHeader(msh,null);
					String MSHStr = qbp_q11.encode();
					String MFIStr = "MFI|LOC||UPD|||AL";
					String MFEStr = ""; //( MAD:增加，MDL：删除，MUP：更新)
					String ZStr = "";
					ZStr = "Z01|"+entity.getId()+"|"+entity.getMaterialCode()+"|"+entity.getMaterialName()+"|"+entity.getMaterialTypeId()+"|"+
					entity.getSpecModel()+"|"+entity.getMonmCode()+"|"+entity.getMaterUnitId()+"|"+entity.getAppaTypeId()+"|"+entity.getFinanceTypeId()+"|"+
					entity.getStandTypeId()+"|"+entity.getIsCons()+"|"+entity.getIsAssets()+"|"+entity.getIsMater()+"|"+entity.getMaxStock()+"|"+
					entity.getSafeStock()+"|"+entity.getMinStock()+"|"+entity.getIsBatch()+"|"+entity.getIsShelfLife()+"|"+entity.getShelfLife()+"|"+
					entity.getIsInstall()+"|"+entity.getIsQuality()+"|"+entity.getIsHighCons()+"|"+entity.getIsBarCode()+"|"+entity.getBarCode()+"|"+
					entity.getIsControl()+"|"+entity.getCreateBy()+"|"+entity.getCreateDate()+"|"+entity.getUpdateBy()+"|"+entity.getUpdateDate()+"|"+
					entity.getIsInstead()+"|"+entity.getIsPaymentDelivery()+"|"+entity.getIsPmMain()+"|"+entity.getCreateName()+"|"+entity.getExpReckoningMode()+"|"+
					entity.getIsSelfrestraint()+"|"+entity.getBpmStatus()+"|"+entity.getQualifiedCode()+"|"+entity.getReasonableLossRate()+"|"+
					entity.getPurchaseExcessToplimit()+"|"+entity.getOrderExcessToplimit()+"|"+entity.getCycleTime()+"|"+entity.getIsPmFrom()+"|"+
					entity.getLongcm()+"|"+entity.getPackingSpec()+"|"+entity.getRegisteredTrademark()+"|"+entity.getIsOutsourcing()+"|"+
					entity.getIsServiceParts()+"|"+entity.getCustomsCode()+"|"+entity.getManufacturer()+"|"+entity.getDeliveryExcessToplimit()+"|"+
					entity.getCountryOfOrigin()+"|"+entity.getApprovalNumber()+"|"+entity.getSysCompanyCode()+"|"+entity.getExpUnit()+"|"+
					entity.getPermitCode()+"|"+entity.getProducingArea()+"|"+entity.getQualityRequirement()+"|"+entity.getHighcm()+"|"+
					entity.getUnitVolume()+"|"+entity.getSysOrgCode()+"|"+entity.getIntlNonPatentName()+"|"+entity.getManufacturingAddress()+"|"+
					entity.getMaterialSaveMode()+"|"+entity.getCeaseDate()+"|"+entity.getIsSequenceManage()+"|"+entity.getEnterExcessToplimit()+"|"+
					entity.getRegisterGoodsBatch()+"|"+entity.getIsLabourService()+"|"+entity.getApplyExcessToplimit()+"|"+entity.getWidecm()+"|"+
					entity.getUpdateName()+"|"+entity.getBrand()+"|"+entity.getStartDate()+"|"+entity.getWarehouseId()+"|"+entity.getDepartId()+"|"+
					entity.getSupplierId()+"|"+entity.getUnitPrice()+"|"+entity.getBartype()+"|"+entity.getValueMethod()+"|"+entity.getIsPurc();
					
					if("ADD".equals(operate)){ //添加
						MFEStr="MFE|MAD|||"+entity.getId()+"|CWE||";
					}else if("UPDATE".equals(operate)){ //修改
						MFEStr="MFE|MUP|||"+entity.getId()+"|CWE||";
					}else if ("DEL".equals(operate)){ //删除
						MFEStr="MFE|MDL|||"+entity.getId()+"|CWE||";
					}
					String str =MSHStr+"\n"+MFIStr+"\n"+MFEStr+"\n"+ZStr;
					//调用接口  推送数据
					EsbPushDataUtil.invokeRemoteFuc(str,null);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				
			}
		}*/
		
	}
	/**
	 * 主数据推送生成MSH消息头
	 * @param msh
	 * @param map 参数
	 *//*
	public void setHeader(MSH msh,Map map){
		try {
			String nowDate = sdf.format(new Date());
			msh.getFieldSeparator().setValue("|");// 分隔符
			msh.getEncodingCharacters().setValue("^~\\&");// MSH-2
			msh.getMsh3_SendingApplication().getHd1_NamespaceID().setValue("HRP");
			msh.getMsh5_ReceivingApplication().getHd1_NamespaceID().setValue("CDR");
			msh.getDateTimeOfMessage().getTimeOfAnEvent().setValue(nowDate);// MSH-7
			msh.getMessageType().getMessageType().setValue("ZKS");  
            msh.getMessageType().getTriggerEvent().setValue("Z01");  
            msh.getMessageType().getMessageStructure().setValue("ZKS_Z01");
			msh.getMsh10_MessageControlID().setValue("DIC_Material-"+nowDate + new Random().nextInt(100));// MSH-10
			msh.getMsh11_ProcessingID().getProcessingID().setValue("P");
			msh.getMsh12_VersionID().getVersionID().setValue("2.7");
		} catch (Exception e) {
			e.printStackTrace();
		}
   }
*/
	@Override
	public List<LBaMaterialEntity> saveMaterialByList(List<LBaMaterialEntity> materialList) {
		for(int start = 0 ;start<materialList.size();start+=100){
			String sql =getInsertSql(materialList ,start, start+100);
			System.out.println(sql);
			if(StringUtil.isNotEmpty(sql)){
				this.updateBySqlString(sql);
			}
		}
		//供应商物资对照插入数据
		String dzSql ="INSERT INTO l_ba_cont_supplier_material (ID,supplier_id,material_id,depart_id,create_date,create_by) "
				+ "select id,supplier_id,id,depart_id,"+StringUtil.tj(new Date())+","+ StringUtil.tj(ResourceUtil.getSessionUser().getUserName())
				+ " from l_ba_material m where  supplier_id is not null  and  "
				+ "not EXISTS ( select 1 from l_ba_cont_supplier_material s where s.material_id = m.id  )";
		this.updateBySqlString(dzSql);
		return null;
	}
 	
	/**
	 * 生成批量插入物资字典的sql
	 * @param sql
	 * @param list
	 */
	public static String  getInsertSql(List<LBaMaterialEntity> list ,int start, int end){
		if(start>list.size()){
			return null;
		}
		if(end>list.size()){
			end= list.size();
		}
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT ALL ");
		
		for( int i = start; i< end ;i++){
			LBaMaterialEntity e = list.get(i);
			String uuid = UUID.randomUUID().toString().replaceAll("-","");
			sql.append(" INTO l_ba_material ");
			sql.append("(id, material_code, material_name, material_type_id, spec_model, monm_code, mater_unit_id, appa_type_id, finance_type_id, stand_type_id, is_cons, is_assets, is_mater");
			sql.append(", max_stock, safe_stock, min_stock, is_batch, is_shelf_life, shelf_life, is_install, is_quality, is_high_cons, is_bar_code, bar_code, is_control, create_by, create_date");
			sql.append(", update_by, update_date, is_instead, is_payment_delivery, is_pm_main, create_name, exp_reckoning_mode, is_selfrestraint, bpm_status, qualified_code");
			sql.append(", reasonable_loss_rate, purchase_excess_toplimit, order_excess_toplimit, cycle_time, is_pm_from, longcm, packing_spec, registered_trademark, is_outsourcing");
			sql.append(", is_service_parts, customs_code, manufacturer, delivery_excess_toplimit, country_of_origin, approval_number, sys_company_code, exp_unit, permit_code");
			sql.append(", producing_area, quality_requirement, highcm, unit_volume, sys_org_code, intl_non_patent_name, manufacturing_address, material_save_mode, cease_date");
			sql.append(", is_sequence_manage, enter_excess_toplimit, register_goods_batch, is_labour_service, apply_excess_toplimit, widecm, update_name, brand, start_date, warehouse_id");
			sql.append(", depart_id, supplier_id, unit_price, bartype, value_method, is_purc) ");
			sql.append(" VALUES \n ");
			sql.append("("+StringUtil.tj(uuid)+", "+StringUtil.tj(e.getMaterialCode())+", "+StringUtil.tj(e.getMaterialName())+", "+StringUtil.tj(e.getMaterialTypeId())+", "+StringUtil.tj(e.getSpecModel())+", "+StringUtil.tj(e.getMonmCode())+", "+StringUtil.tj(e.getMaterUnitId())+"");
			sql.append(", "+StringUtil.tj(e.getAppaTypeId())+", "+StringUtil.tj(e.getFinanceTypeId())+", "+StringUtil.tj(e.getStandTypeId())+", "+StringUtil.tj(e.getIsCons())+", "+StringUtil.tj(e.getIsAssets())+", "+StringUtil.tj(e.getIsMater())+"");
			sql.append(", "+StringUtil.tj(e.getMaxStock())+", "+StringUtil.tj(e.getSafeStock())+", "+StringUtil.tj(e.getMinStock())+", "+StringUtil.tj(e.getIsBatch() == null ? 0 :e.getIsBatch())+", "+StringUtil.tj(e.getIsShelfLife() == null ? 0 :e.getIsShelfLife())+", "+StringUtil.tj(e.getShelfLife())+", "+StringUtil.tj(e.getIsInstall())+"");
			sql.append(", "+StringUtil.tj(e.getIsQuality())+", "+StringUtil.tj(e.getIsHighCons())+", "+StringUtil.tj(e.getIsBarCode())+", "+StringUtil.tj(e.getBarCode())+", "+StringUtil.tj(e.getIsControl())+", "+StringUtil.tj(e.getCreateBy())+", "+StringUtil.tj(e.getCeaseDate())+"");
			sql.append(", "+StringUtil.tj(e.getUpdateBy())+", "+StringUtil.tj(e.getUpdateDate())+", "+StringUtil.tj(e.getIsInstead())+", "+StringUtil.tj(e.getIsPaymentDelivery())+", "+StringUtil.tj(e.getIsPmMain())+", "+StringUtil.tj(e.getCreateName())+"");
			sql.append(", "+StringUtil.tj(e.getExpReckoningMode())+", "+StringUtil.tj(e.getIsSelfrestraint())+", "+StringUtil.tj(e.getBpmStatus())+", "+StringUtil.tj(e.getQualifiedCode())+"");
			sql.append(", "+StringUtil.tj(e.getReasonableLossRate())+", "+StringUtil.tj(e.getPurchaseExcessToplimit())+", "+StringUtil.tj(e.getOrderExcessToplimit())+", "+StringUtil.tj(e.getCycleTime())+"");
			sql.append(", "+StringUtil.tj(e.getIsPmFrom())+", "+StringUtil.tj(e.getLongcm())+", "+StringUtil.tj(e.getPackingSpec())+", "+StringUtil.tj(e.getRegisteredTrademark())+", "+StringUtil.tj(e.getIsOutsourcing())+"");
			sql.append(", "+StringUtil.tj(e.getIsServiceParts())+", "+StringUtil.tj(e.getCustomsCode())+", "+StringUtil.tj(e.getManufacturer())+", "+StringUtil.tj(e.getDeliveryExcessToplimit())+"");
			sql.append(", "+StringUtil.tj(e.getCountryOfOrigin())+", "+StringUtil.tj(e.getApprovalNumber())+", "+StringUtil.tj(e.getSysCompanyCode())+", "+StringUtil.tj(e.getExpUnit())+", "+StringUtil.tj(e.getPermitCode())+"");
			sql.append(", "+StringUtil.tj(e.getProducingArea())+", "+StringUtil.tj(e.getQualityRequirement())+", "+StringUtil.tj(e.getHighcm())+", "+StringUtil.tj(e.getUnitVolume())+", "+StringUtil.tj(e.getSysOrgCode())+"");
			sql.append(", "+StringUtil.tj(e.getIntlNonPatentName())+", "+StringUtil.tj(e.getManufacturingAddress())+", "+StringUtil.tj(e.getMaterialSaveMode())+", "+StringUtil.tj(e.getCeaseDate())+"");
			sql.append(", "+StringUtil.tj(e.getIsSequenceManage())+", "+StringUtil.tj(e.getEnterExcessToplimit())+", "+StringUtil.tj(e.getRegisterGoodsBatch())+"");
			sql.append(", "+StringUtil.tj(e.getIsLabourService())+", "+StringUtil.tj(e.getApplyExcessToplimit())+", "+StringUtil.tj(e.getWidecm())+", "+StringUtil.tj(e.getUpdateName())+", "+StringUtil.tj(e.getBrand())+", "+StringUtil.tj(e.getStartDate())+", "+StringUtil.tj(e.getWarehouseId())+"");
			sql.append(", "+StringUtil.tj(e.getDepartId())+", "+StringUtil.tj(e.getSupplierId())+", "+StringUtil.tj(e.getUnitPrice())+", "+StringUtil.tj(e.getBartype())+", "+StringUtil.tj(e.getValueMethod())+", "+StringUtil.tj(e.getIsPurc())+") ");
			
		}
		sql.append("SELECT 1 FROM dual ");
		return sql.toString();
	}
}