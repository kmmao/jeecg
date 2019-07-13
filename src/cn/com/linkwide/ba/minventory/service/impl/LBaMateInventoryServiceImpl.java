package cn.com.linkwide.ba.minventory.service.impl;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.ApplicationContextUtil;
import org.jeecgframework.core.util.MyClassLoader;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.SqlUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.web.cgform.enhance.CgformEnhanceJavaInter;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.com.linkwide.ba.inventorylog.entity.LBaMateInventoryLogEntity;
import cn.com.linkwide.ba.material.record.entity.LBaMaterialEntity;
import cn.com.linkwide.ba.minventory.entity.LBaMateInventoryEntity;
import cn.com.linkwide.ba.minventory.service.LBaMateInventoryServiceI;

@Service("lBaMateInventoryService")
@Transactional
public class LBaMateInventoryServiceImpl extends CommonServiceImpl implements LBaMateInventoryServiceI {

	
 	public void delete(LBaMateInventoryEntity entity) throws Exception{
 		super.delete(entity);
 		//执行删除操作增强业务
		this.doDelBus(entity);
 	}
 	
 	public Serializable save(LBaMateInventoryEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		//执行新增操作增强业务
 		this.doAddBus(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(LBaMateInventoryEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 		//执行更新操作增强业务
 		this.doUpdateBus(entity);
 	}
 	
 	/**
	 * 新增操作增强业务
	 * @param t
	 * @return
	 */
	private void doAddBus(LBaMateInventoryEntity t) throws Exception{
		//-----------------sql增强 start----------------------------
	 	//-----------------sql增强 end------------------------------
	 	
	 	//-----------------java增强 start---------------------------
	 	//-----------------java增强 end-----------------------------
 	}
 	/**
	 * 更新操作增强业务
	 * @param t
	 * @return
	 */
	private void doUpdateBus(LBaMateInventoryEntity t) throws Exception{
		//-----------------sql增强 start----------------------------
	 	//-----------------sql增强 end------------------------------
	 	
	 	//-----------------java增强 start---------------------------
	 	//-----------------java增强 end-----------------------------
 	}
 	/**
	 * 删除操作增强业务
	 * @param id
	 * @return
	 */
	private void doDelBus(LBaMateInventoryEntity t) throws Exception{
	    //-----------------sql增强 start----------------------------
	 	//-----------------sql增强 end------------------------------
	 	
	 	//-----------------java增强 start---------------------------
	 	//-----------------java增强 end-----------------------------
 	}
 	
 	private Map<String,Object> populationMap(LBaMateInventoryEntity t){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", t.getId());
		map.put("create_name", t.getCreateName());
		map.put("create_by", t.getCreateBy());
		map.put("create_date", t.getCreateDate());
		map.put("update_name", t.getUpdateName());
		map.put("update_by", t.getUpdateBy());
		map.put("update_date", t.getUpdateDate());
		map.put("sys_org_code", t.getSysOrgCode());
		map.put("sys_company_code", t.getSysCompanyCode());
		map.put("warehouse_id", t.getWarehouseId());
		map.put("material_id", t.getMaterialId());
		map.put("num", t.getNum());
		map.put("unit_price", t.getUnitPrice());
		map.put("batch_no", t.getBatchNo());
		map.put("bar_code", t.getBarCode());
		map.put("expir_date", t.getExpirDate());
		return map;
	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @param t
	 * @return
	 */
 	public String replaceVal(String sql,LBaMateInventoryEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{create_name}",String.valueOf(t.getCreateName()));
 		sql  = sql.replace("#{create_by}",String.valueOf(t.getCreateBy()));
 		sql  = sql.replace("#{create_date}",String.valueOf(t.getCreateDate()));
 		sql  = sql.replace("#{update_name}",String.valueOf(t.getUpdateName()));
 		sql  = sql.replace("#{update_by}",String.valueOf(t.getUpdateBy()));
 		sql  = sql.replace("#{update_date}",String.valueOf(t.getUpdateDate()));
 		sql  = sql.replace("#{sys_org_code}",String.valueOf(t.getSysOrgCode()));
 		sql  = sql.replace("#{sys_company_code}",String.valueOf(t.getSysCompanyCode()));
 		sql  = sql.replace("#{warehouse_id}",String.valueOf(t.getWarehouseId()));
 		sql  = sql.replace("#{material_id}",String.valueOf(t.getMaterialId()));
 		sql  = sql.replace("#{num}",String.valueOf(t.getNum()));
 		sql  = sql.replace("#{unit_price}",String.valueOf(t.getUnitPrice()));
 		sql  = sql.replace("#{batch_no}",String.valueOf(t.getBatchNo()));
 		sql  = sql.replace("#{bar_code}",String.valueOf(t.getBarCode()));
 		sql  = sql.replace("#{expir_date}",String.valueOf(t.getExpirDate()));
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
					javaInter.execute("l_ba_mate_inventory",data);
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("执行JAVA增强出现异常！");
			} 
		}
 	}

	@Override
	public void changeInventory(LBaMateInventoryEntity entity) throws Exception {
		//加锁
		synchronized (entity) {
			//校验物资属性
			LBaMaterialEntity mate = this.findUniqueByProperty(LBaMaterialEntity.class, "id", entity.getMaterialId());
			if(mate.getIsBatch() !=1){
				entity.setBatchNo(null);
			}
			if(mate.getIsShelfLife() !=1){
				entity.setExpirDate(null);
			}
			if(mate.getIsBarCode() !=1){
				entity.setBarCode(null);
			}
			if(StringUtil.isEmpty(entity.getMateBatchId())){
				entity.setMateBatchId(getMateBatchId(entity));
			}
			StringBuffer hql = new StringBuffer(); 
			hql.append(" from LBaMateInventoryEntity where  warehouseId ='"+entity.getWarehouseId()+"' and materialId ='"+entity.getMaterialId()+"' and isAgency='"+entity.getIsAgency()+"' ");
			/*if(StringUtil.isNotEmpty(ResourceUtil.getUserComponyCode())){
				hql.append(" and sysCompanyCode='"+ResourceUtil.getUserComponyCode()+"'");
			}*/
			hql.append(" and mateBatchId ='"+entity.getMateBatchId()+"' ");
			List<LBaMateInventoryEntity> inventoryList = super.findHql(hql.toString());
			
			if(inventoryList == null || inventoryList.size() <1){
				if(entity.getNum().doubleValue() <0){
					throw new Exception("库存不足!");
				}
				entity.setUpdateDate(new Date());	
				save(entity);
				//保存日志
				LBaMateInventoryLogEntity invLog = new LBaMateInventoryLogEntity();
				invLog.setWarehouseId(entity.getWarehouseId());
				invLog.setMaterialId(entity.getMaterialId());
				invLog.setBillNo(entity.getBillNo());
				invLog.setBillType(entity.getBillType());
				invLog.setNowNum(entity.getNum());
				invLog.setNum(entity.getNum());
				invLog.setCreateBy(ResourceUtil.getSessionUser().getId());
				invLog.setCreateDate(new Date());
				invLog.setLogDate(new Date());
				invLog.setInventoryId(entity.getId());
				invLog.setIsAgency(entity.getIsAgency());
				this.save(invLog);
				//修改库存
				save(entity);
				return;
			}else if(inventoryList.size()>1){
				throw new Exception("无法确定库存物资唯一性");
			}
			LBaMateInventoryEntity inv = inventoryList.get(0);
			if(inv.getNum().doubleValue()+entity.getNum().doubleValue() <0){
				throw new Exception("库存不足!");
			}
			inv.setNum(inv.getNum().add(entity.getNum()));
			inv.setUpdateDate(new Date());
			//修改库存
			super.saveOrUpdate(inv);
			//保存日志
			LBaMateInventoryLogEntity invLog = new LBaMateInventoryLogEntity();
			invLog.setWarehouseId(inv.getWarehouseId());
			invLog.setMaterialId(inv.getMaterialId());
			invLog.setBillNo(entity.getBillNo());
			invLog.setBillType(entity.getBillType());
			invLog.setNowNum(inv.getNum());
			invLog.setNum(entity.getNum());
			invLog.setCreateBy(ResourceUtil.getSessionUser().getId());
			invLog.setCreateDate(new Date());
			invLog.setLogDate(new Date());
			invLog.setInventoryId(inv.getId());
			invLog.setIsAgency(entity.getIsAgency());
			this.save(invLog);
		
		} 
	}

	@Override
	public double queryInventory(LBaMateInventoryEntity entity) {
		//加锁
		synchronized (entity) {
			/*LBaMaterialEntity mate = this.findUniqueByProperty(LBaMaterialEntity.class, "id", entity.getMaterialId());
			if(mate.getIsBatch() !=1){
				entity.setBatchNo(null);
			}
			if(mate.getIsShelfLife() !=1){
				entity.setExpirDate(null);
			}*/
			StringBuffer sql = new StringBuffer(); 
			sql.append("select sum(num)  num  from l_ba_mate_inventory where  warehouse_id ='"+entity.getWarehouseId()+"' and material_id ='"+entity.getMaterialId()+"' ");
			if(StringUtil.isNotEmpty(entity.getIsAgency())){
				sql.append(" and is_agency ='"+entity.getIsAgency()+"' ");
			}
			if(StringUtil.isNotEmpty(entity.getBatchNo())){
				sql.append(" and batch_no ='"+entity.getBatchNo()+"' ");
			}else{
				sql.append(" and  " +SqlUtil.isNull("batch_no"));
			}
			if(StringUtil.isNotEmpty(entity.getExpirDate())){
				sql.append(" and expir_date ="+SqlUtil.tjDate(entity.getExpirDate())+" ");
			}
			if(StringUtil.isNotEmpty(entity.getMakeDate())){
				sql.append(" and make_date ="+SqlUtil.tjDate(entity.getMakeDate())+" ");
			}
			if(StringUtil.isNotEmpty(entity.getBarCode())){
				sql.append(" and bar_code ='"+entity.getBarCode()+"' ");
			}else{
				sql.append(" and  "+SqlUtil.isNull("bar_code"));
			}
			if(StringUtil.isNotEmpty(entity.getUnitPrice())){
				sql.append(" and unit_price ="+StringUtil.tj(entity.getUnitPrice())+" ");
			}
			Map<String,Object> dd = this.findOneForJdbc(sql.toString());
			if(dd.get("num") !=null){
				return Double.valueOf(dd.get("num").toString());
			}
			return 0;
		}
	}

	@Override
	public void quickInOutInventory(LBaMateInventoryEntity entity,String inBillNo,String outBillNo) throws Exception {
		//加锁
		synchronized (entity) {
			StringBuffer hql = new StringBuffer(); 
			entity.setMateBatchId(getMateBatchId(entity));
			hql.append(" from LBaMateInventoryEntity where  warehouseId ='"+entity.getWarehouseId()+"' and materialId ='"+entity.getMaterialId()+"' ");
			hql.append(" and  mateBatchId ='"+entity.getMateBatchId()+"' ");
			
			List<LBaMateInventoryEntity> inventoryList = super.findHql(hql.toString());
			TSUser  user = ResourceUtil.getSessionUser();
			BigDecimal nowNum = entity.getNum();
			BigDecimal oldNum = new BigDecimal(0.0);
			if(inventoryList != null && inventoryList.size() >1){ 
				entity= inventoryList.get(0);
				oldNum = entity.getNum(); 
			}else{
				entity.setCreateBy(user.getId());
				entity.setCreateName(user.getRealName());
				entity.setUpdateDate(new Date()); 
				entity.setNum(new BigDecimal(0));
				//设置物资批次标识
				entity.setMateBatchId(getMateBatchId(entity));
				this.save(entity);
			} 
			//保存日志
			LBaMateInventoryLogEntity invLog = new LBaMateInventoryLogEntity();
			invLog.setWarehouseId(entity.getWarehouseId());
			invLog.setMaterialId(entity.getMaterialId());
			invLog.setBillNo(inBillNo);
			invLog.setBillType("快捷入库审核");
			invLog.setNowNum(oldNum.add(nowNum) );
			invLog.setNum(nowNum);
			invLog.setCreateBy(user.getId());
			invLog.setCreateDate(new Date());
			invLog.setLogDate(new Date());
			invLog.setInventoryId(entity.getId());
			invLog.setIsAgency(entity.getIsAgency());
			this.save(invLog);
			LBaMateInventoryLogEntity invLog2 = new LBaMateInventoryLogEntity();
			invLog2.setWarehouseId(entity.getWarehouseId());
			invLog2.setMaterialId(entity.getMaterialId());
			invLog2.setBillNo(outBillNo);
			invLog2.setBillType("快捷出库审核");
			invLog2.setNowNum(oldNum);
			invLog2.setNum(new BigDecimal(- nowNum.doubleValue()) );
			invLog2.setCreateBy(user.getId());
			invLog2.setCreateDate(new Date());
			invLog2.setLogDate(new Date());
			invLog2.setInventoryId(entity.getId());
			invLog2.setIsAgency(entity.getIsAgency());
			this.save(invLog2);
		}
	}
	

	@Override
	public List<LBaMateInventoryEntity> queryInventory(String warehouseId, String materialIds,String isAgency) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select a.warehouse_id,a.material_id,sum(a.num) num  ,b.material_code,b.material_name");
		sql.append(" from  l_ba_mate_inventory  a");
		sql.append(" left join l_ba_material  b on b.id = a.material_id");
		sql.append(" where a.warehouse_id ='"+warehouseId+"' ");
		if(StringUtil.isNotEmpty(materialIds)){
			materialIds = materialIds.replaceAll("'", "");
			sql.append(" and a.material_id in ('"+materialIds.replace(",", "','")+"')");
		}
		sql.append(" group by a.warehouse_id,a.material_id,b.material_code,b.material_name");
		List<Map<String,Object>> list = this.findForJdbc(sql.toString());
		List<LBaMateInventoryEntity> result = new ArrayList<>();
		for(Map<String,Object> map: list){
			LBaMateInventoryEntity m = new LBaMateInventoryEntity();
			m.setWarehouseId(map.get("warehouse_id").toString());
			m.setMaterialId(map.get("material_id").toString());
			m.setMaterialCode(map.get("material_code").toString());
			m.setMaterialName(map.get("material_name").toString());
			m.setNum(new BigDecimal(map.get("num").toString()));
			result.add(m);
		}
		return result;
	}
	
	@Override
	public List<Map<String, Object>> queryInventory(String warehouseId, String materialId, String supplierId,String isBarCode,String... params) {
		String outWareId = "";
		if(!StringUtil.isEmpty(warehouseId) ){
			if(warehouseId.contains(",")){
				String[] wIds  = warehouseId.split(",");
				outWareId = wIds[0];
			}else{
				outWareId = warehouseId;
			}
		}
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT   pbalance.warehouse_id  \"warehouseId\",pbalance.material_id \"materialId\",bm.material_code  \"materialCode\",bm.material_name \"materialName\",bm.monm_code \"monmCode\",bm.spec_model \"specModel\",");
		sql.append(" mu.type_name \"unitName\",bm.supplier_id  \"supplierId\",bs.supplier_full_name \"supplierName\",bm.is_batch \"isBatch\",bm.is_shelf_life \"isShelfLife\",");
		sql.append(" bm.is_bar_code \"isBarCode\",bm.bartype \"bartype\",inware.unit_price \"unitPrice\",	case when inware.quantity is null then pbalance.num else  inware.quantity end  \"quantity\",pbalance.batch_no  \"batchNo\"");
		sql.append(" ,pbalance.bar_code  \"barCode\",pbalance.make_date  \"makeDate\",pbalance.expir_date  \"expirydate\",inware.bill_no  \"inwareBillno\" ,inware.mid \"inWareId\", inware.bill_no \"billNo\", inware.did \"inwareDetailId\",pbalance.mate_batch_id \"mateBatchId\" ");
		sql.append(" ,case when inware.quantity is null then pbalance.num else  inware.quantity end - nvl(occ.quantity,0)  \"justquantity\" ,inware.BILL_DATE ");
		sql.append(" FROM  l_ba_mate_inventory  pbalance");
		sql.append(" INNER JOIN l_ba_material bm ON bm.id = pbalance.material_id");
		sql.append(" INNER JOIN l_ba_material_mater_unit mu ON mu.id = bm.mater_unit_id");
		sql.append(" INNER JOIN l_ba_warehouse bw ON bw.id = pbalance.warehouse_id");
		sql.append(" LEFT JOIN l_ba_supplier bs ON bs.id = bm.supplier_id");
		//关联入库单和库存初始账
		sql.append(" left join ");
		sql.append(" (");
		sql.append(" 	select m.bill_no,m.id  mid,d.id did,d.quantity - d.outnum  - d.return_num  quantity ,case when d.outnum is null then 0 else d.outnum end outnum ,d.material_id,d.bar_code,d.batch_no,d.unit_price ,d.expirydate,d.mate_batch_id,M.BILL_DATE ");
		sql.append(" 	from l_st_in_ware m inner join l_st_in_ware_detail d  on d.in_ware_id = m.id ");
		sql.append(" 	where m.is_agency ='0' and  m.warehouse_id ="+outWareId);
		sql.append(" 	and m.bill_status =3 and m.is_red ='0' and (  d.outnum <d.quantity ) and ( d.quantity >d.return_num ) ");
		sql.append(" 	union all ");
		sql.append(" 	select bm.bill_no,bm.id  mid,bd.id did,bd.quantity - case when bd.outnum is null then 0 else bd.outnum end   quantity,");
		sql.append(" 	case when bd.outnum is null then 0 else bd.outnum end outnum,bd.material_id,bd.bar_code,bd.batch_no,bd.unit_price,bd.expirydate,bd.mate_batch_id,BM.CREATE_DATE ");
		sql.append(" 	from l_st_ware_balance bm ");
		sql.append("    inner join  l_st_ware_balance_detail bd on bd.balanceId= bm.id");
		sql.append(" 	WHERE bm.warehouse_id = "+outWareId+" AND bm.ifaccount = '1' AND ( bd.outnum is null or bd.outnum  < bd.quantity )");
		sql.append(" )");
		sql.append("  inware on inware.material_id = pbalance.material_id ");
		sql.append(" and inware.mate_batch_id = pbalance.mate_batch_id ");
		sql.append(" left join L_ST_OCCUPY_VIEW occ on occ.in_ware_detail_id = inware.did ");
		sql.append(" ");
		sql.append(" ");
		sql.append(" where pbalance.is_agency ='0' and  pbalance.warehouse_id = "+outWareId+" ");
		sql.append(" and pbalance.num >0");
		if(StringUtil.isNotEmpty(isBarCode)){
			sql.append(" and bm.is_bar_code = '"+isBarCode+"' ");
		}
		if(StringUtil.isNotEmpty(materialId)){
			sql.append(" and pbalance.material_id = '"+materialId+"' ");
		}
		//params第一个参数为物资条码
		if(params.length>0 && StringUtil.isNotEmpty(params[0])){
			sql.append(" and pbalance.bar_code = '"+params[0]+"' ");
		}
		//调拨时，物资应属于两仓库共有物资
		if(!StringUtil.isEmpty(warehouseId) && warehouseId.contains(",")){
			String[] wIds  = warehouseId.split(",");
			if(!wIds[0].equals(wIds[1])){
				sql.append(" AND pbalance.material_id IN (");
				sql.append(" SELECT material_id FROM l_ba_cont_warehouse_material WHERE");
				sql.append(" warehouse_id IN ( "+warehouseId+" ) )"); 
			}
		}
		sql.append(" ORDER BY inware.BILL_DATE ");
		List<Map<String, Object>> list = this.findForJdbc(sql.toString());
		return list;
	}
	
	@Override
	public List<Map<String, Object>> queryAgencyInventory(String warehouseId, String materialId, String supplierId,String isBarCode,String... params) {
		String outWareId = "";
		if(!StringUtil.isEmpty(warehouseId) ){
			if(warehouseId.contains(",")){
				String[] wIds  = warehouseId.split(",");
				outWareId = wIds[0];
			}else{
				outWareId = warehouseId;
			}
		}
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT   pbalance.warehouse_id  \"warehouseId\",pbalance.material_id \"materialId\",bm.material_code  \"materialCode\",bm.material_name \"materialName\",bm.monm_code \"monmCode\",bm.spec_model \"specModel\",");
		sql.append(" mu.type_name \"unitName\",bm.supplier_id  \"supplierId\",bs.supplier_full_name \"supplierName\",bm.is_batch \"isBatch\",bm.is_shelf_life \"isShelfLife\",");
		sql.append(" bm.is_bar_code \"isBarCode\",bm.bartype \"bartype\",inware.unit_price \"unitPrice\",	case when inware.quantity is null then pbalance.num else  inware.quantity end  \"quantity\",pbalance.batch_no  \"batchNo\"");
		sql.append(" ,pbalance.bar_code  \"barCode\",pbalance.make_date  \"makeDate\",pbalance.expir_date  \"expirydate\",inware.bill_no  \"inwareBillno\" ,inware.mid \"inWareId\", inware.bill_no \"billNo\", inware.did \"inwareDetailId\",pbalance.mate_batch_id \"mateBatchId\" ");
		sql.append(" FROM  l_ba_mate_inventory  pbalance");
		sql.append(" INNER JOIN l_ba_material bm ON bm.id = pbalance.material_id");
		sql.append(" INNER JOIN l_ba_material_mater_unit mu ON mu.id = bm.mater_unit_id");
		sql.append(" INNER JOIN l_ba_warehouse bw ON bw.id = pbalance.warehouse_id");
		sql.append(" LEFT JOIN l_ba_supplier bs ON bs.id = bm.supplier_id");
		//关联入库单和库存初始账
		sql.append(" left join ");
		sql.append(" (");
		sql.append(" 	select m.bill_no,m.id  mid,d.id did,d.quantity - d.outnum  - d.return_num  quantity ,case when d.outnum is null then 0 else d.outnum end outnum ,d.material_id,d.bar_code,d.batch_no,d.unit_price ,d.expirydate,d.mate_batch_id");
		sql.append(" 	from l_st_in_ware m inner join l_st_in_ware_detail d  on d.in_ware_id = m.id ");
		sql.append(" 	where m.is_agency ='1' and m.warehouse_id ="+outWareId);
		sql.append(" 	and m.bill_status =3 and m.is_red ='0' and (  d.outnum <d.quantity ) and ( d.quantity >d.return_num ) ");
		sql.append(" 	union all ");
		sql.append(" 	select bm.bill_no,bm.id  mid,bd.id did,bd.quantity - case when bd.outnum is null then 0 else bd.outnum end   quantity,");
		sql.append(" 	case when bd.outnum is null then 0 else bd.outnum end outnum,bd.material_id,bd.bar_code,bd.batch_no,bd.unit_price,bd.expirydate,bd.mate_batch_id ");
		sql.append(" 	from l_st_ware_balance bm ");
		sql.append("    inner join  l_st_ware_balance_detail bd on bd.balanceId= bm.id");
		sql.append(" 	WHERE bm.warehouse_id = "+outWareId+" AND bm.ifaccount = '1' AND ( bd.outnum is null or bd.outnum  < bd.quantity )");
		sql.append(" )");
		sql.append("  inware on inware.material_id = pbalance.material_id ");
		sql.append(" and inware.mate_batch_id = pbalance.mate_batch_id ");
		sql.append(" where pbalance.is_agency ='1' and pbalance.warehouse_id = "+outWareId+" ");
		sql.append(" and pbalance.num >0");
		if(StringUtil.isNotEmpty(isBarCode)){
			sql.append(" and bm.is_bar_code = '"+isBarCode+"' ");
		}
		if(StringUtil.isNotEmpty(materialId)){
			sql.append(" and pbalance.material_id = '"+materialId+"' ");
		}
		//params第一个参数为物资条码
		if(params.length>0 && StringUtil.isNotEmpty(params[0])){
			sql.append(" and pbalance.bar_code = '"+params[0]+"' ");
		}
		//调拨时，物资应属于两仓库共有物资
		if(!StringUtil.isEmpty(warehouseId) && warehouseId.contains(",")){
			String[] wIds  = warehouseId.split(",");
			if(!wIds[0].equals(wIds[1])){
				sql.append(" AND pbalance.material_id IN (");
				sql.append(" SELECT material_id FROM l_ba_cont_warehouse_material WHERE");
				sql.append(" warehouse_id IN ( "+warehouseId+" ) )"); 
			}
		}
		
		List<Map<String, Object>> list = this.findForJdbc(sql.toString());
		return list;
	}
	
	public static String getMateBatchId(Object obj){
		String MateBatchId="";
		if(obj instanceof LBaMateInventoryEntity){//库存表
			LBaMateInventoryEntity o  = (LBaMateInventoryEntity) obj;
			if(StringUtil.isNotEmpty(o.getBarCode())){
				MateBatchId = "B"+obj2str(o.getBatchNo())+"C"+obj2str(o.getBarCode());
			}else{
				MateBatchId = "B"+obj2str(o.getBatchNo())+"M"+obj2str(o.getMakeDate()).replaceAll("-", "")+"E"+obj2str(o.getExpirDate()).replaceAll("-", "");
			}
		}
		return MateBatchId;
	}
	
	public static String obj2str(Object value){
		if(value == null){
			return "";
		}else if(value instanceof String ){ 
			return value.toString();
		}else if(value instanceof Integer ){
			return ""+value;
		}else if(value instanceof Double ){
			return ""+value;
		}else if(value instanceof Date ){
			SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
			return fm.format(value);
		}
		return value.toString();
	}
}