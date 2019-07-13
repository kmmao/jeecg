package cn.com.linkwide.ba.bacustomer.service.impl;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.ApplicationContextUtil;
import org.jeecgframework.core.util.DateHandleUtil;
import org.jeecgframework.core.util.DynamicDBUtil;
import org.jeecgframework.core.util.MyClassLoader;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.web.cgform.enhance.CgformEnhanceJavaInter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.linkwide.ba.bacustomer.entity.BaCustomerEntity;
import cn.com.linkwide.ba.bacustomer.service.BaCustomerServiceI;

@Service("baCustomerService")
@Transactional
public class BaCustomerServiceImpl extends CommonServiceImpl implements BaCustomerServiceI {

	
 	public void delete(BaCustomerEntity entity) throws Exception{
 		super.delete(entity);
 		//执行删除操作增强业务
		this.doDelBus(entity);
 	}
 	
 	public Serializable save(BaCustomerEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		//执行新增操作增强业务
 		this.doAddBus(entity);
 		//同步到u8
 		List<BaCustomerEntity> list = new ArrayList<>();
 		list.add(entity);
 		synToU8(list);
 		return t;
 	}
 	
 	public void saveOrUpdate(BaCustomerEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 		//执行更新操作增强业务
 		this.doUpdateBus(entity);
 		
 		//同步到u8
 		List<BaCustomerEntity> list = new ArrayList<>();
 		list.add(entity);
 		synToU8(list);
 	}
 	

 	/**
 	 * 同步数据到u8
 	 */
	@Override
	public void synToU8(List<BaCustomerEntity> list) {
		if(true) return;
		if(list!= null && list.size()>0){
			for (BaCustomerEntity entity : list) {
				String sql =" select cCusCode from Customer where cCusCode='"+entity.getCustomerCode()+"' ";
				List<BaCustomerEntity> clist = DynamicDBUtil.findListEntrys("U8", sql, BaCustomerEntity.class);
				if(clist!= null && clist.size()>0){ //u8中存在，修改
					String updateSql =" update Customer set cCusName='"+entity.getCustomerFullName()+"',"
							+ " cCusAbbName='"+entity.getCustomerShortName()+"',cCCCode='"+entity.getCustomerTypeId()+"',"
							+ " dCusDevDate='"+DateHandleUtil.formateDate("yyyy-MM-dd HH:mm:ss", entity.getCreateDate()==null?new Date():entity.getCreateDate())+"',"
							+ " cCusMngTypeCode='999',cCusExch_name='人民币',bCusDomestic='1' "
							+ " where cCusCode='"+entity.getCustomerCode()+"'";
					DynamicDBUtil.update("U8", updateSql, null);
				}else{ //u8中不存在，新增
					String insertSql =" insert into Customer (cCusCode,cCusName,cCusAbbName,cCCCode,dCusDevDate,cCusMngTypeCode,cCusExch_name,bCusDomestic)"
							+ " values ('"+entity.getCustomerCode()+"','"+entity.getCustomerFullName()+"','"+entity.getCustomerShortName()+"',"
							+ " '"+entity.getCustomerTypeId()+"','"+DateHandleUtil.formateDate("yyyy-MM-dd HH:mm:ss", entity.getCreateDate()==null?new Date():entity.getCreateDate())+"',"
									+ " '999','人民币','1') ";
					DynamicDBUtil.update("U8", insertSql, null);
				}
			}
		}
		
	}
 	/**
	 * 新增操作增强业务
	 * @param t
	 * @return
	 */
	private void doAddBus(BaCustomerEntity t) throws Exception{
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
	private void doUpdateBus(BaCustomerEntity t) throws Exception{
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
	private void doDelBus(BaCustomerEntity t) throws Exception{
	    //-----------------sql增强 start----------------------------
	 	//-----------------sql增强 end------------------------------
	 	
	 	//-----------------java增强 start---------------------------
	 	//-----------------java增强 end-----------------------------
 	}
 	
 	private Map<String,Object> populationMap(BaCustomerEntity t){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", t.getId());
		map.put("customer_code", t.getCustomerCode());
		map.put("customer_full_name", t.getCustomerFullName());
		map.put("customer_short_name", t.getCustomerShortName());
		map.put("customer_type_id", t.getCustomerTypeId());
		map.put("contacts", t.getContacts());
		map.put("telphone", t.getTelphone());
		map.put("address", t.getAddress());
		map.put("create_by", t.getCreateBy());
		map.put("create_date", t.getCreateDate());
		map.put("update_by", t.getUpdateBy());
		map.put("update_date", t.getUpdateDate());
		map.put("uscc", t.getUscc());
		map.put("auditor", t.getAuditor());
		map.put("affiliated_bank", t.getAffiliatedBank());
		map.put("create_name", t.getCreateName());
		map.put("sys_company_code", t.getSysCompanyCode());
		map.put("legal_person", t.getLegalPerson());
		map.put("cease_people", t.getCeasePeople());
		map.put("bpm_status", t.getBpmStatus());
		map.put("sys_org_code", t.getSysOrgCode());
		map.put("mnemonic_code", t.getMnemonicCode());
		map.put("register_capital", t.getRegisterCapital());
		map.put("default_currency", t.getDefaultCurrency());
		map.put("is_cease", t.getIsCease());
		map.put("payment_received_protocol", t.getPaymentReceivedProtocol());
		map.put("supervise_salesman", t.getSuperviseSalesman());
		map.put("corporation_address", t.getCorporationAddress());
		map.put("website", t.getWebsite());
		map.put("vld", t.getVld());
		map.put("cease_date", t.getCeaseDate());
		map.put("auditing_state", t.getAuditingState());
		map.put("bank_code", t.getBankCode());
		map.put("corporation_phone", t.getCorporationPhone());
		map.put("auditing_date", t.getAuditingDate());
		map.put("grant_date", t.getGrantDate());
		map.put("update_name", t.getUpdateName());
		map.put("oaa_bank", t.getOaaBank());
		map.put("affiliated_area", t.getAffiliatedArea());
		map.put("depart_id", t.getDepartId());
		map.put("extend1", t.getExtend1());
		map.put("extend2", t.getExtend2());
		map.put("extend3", t.getExtend3());
		map.put("extend4", t.getExtend4());
		map.put("extend5", t.getExtend5());
		return map;
	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @param t
	 * @return
	 */
 	public String replaceVal(String sql,BaCustomerEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{customer_code}",String.valueOf(t.getCustomerCode()));
 		sql  = sql.replace("#{customer_full_name}",String.valueOf(t.getCustomerFullName()));
 		sql  = sql.replace("#{customer_short_name}",String.valueOf(t.getCustomerShortName()));
 		sql  = sql.replace("#{customer_type_id}",String.valueOf(t.getCustomerTypeId()));
 		sql  = sql.replace("#{contacts}",String.valueOf(t.getContacts()));
 		sql  = sql.replace("#{telphone}",String.valueOf(t.getTelphone()));
 		sql  = sql.replace("#{address}",String.valueOf(t.getAddress()));
 		sql  = sql.replace("#{create_by}",String.valueOf(t.getCreateBy()));
 		sql  = sql.replace("#{create_date}",String.valueOf(t.getCreateDate()));
 		sql  = sql.replace("#{update_by}",String.valueOf(t.getUpdateBy()));
 		sql  = sql.replace("#{update_date}",String.valueOf(t.getUpdateDate()));
 		sql  = sql.replace("#{uscc}",String.valueOf(t.getUscc()));
 		sql  = sql.replace("#{auditor}",String.valueOf(t.getAuditor()));
 		sql  = sql.replace("#{affiliated_bank}",String.valueOf(t.getAffiliatedBank()));
 		sql  = sql.replace("#{create_name}",String.valueOf(t.getCreateName()));
 		sql  = sql.replace("#{sys_company_code}",String.valueOf(t.getSysCompanyCode()));
 		sql  = sql.replace("#{legal_person}",String.valueOf(t.getLegalPerson()));
 		sql  = sql.replace("#{cease_people}",String.valueOf(t.getCeasePeople()));
 		sql  = sql.replace("#{bpm_status}",String.valueOf(t.getBpmStatus()));
 		sql  = sql.replace("#{sys_org_code}",String.valueOf(t.getSysOrgCode()));
 		sql  = sql.replace("#{mnemonic_code}",String.valueOf(t.getMnemonicCode()));
 		sql  = sql.replace("#{register_capital}",String.valueOf(t.getRegisterCapital()));
 		sql  = sql.replace("#{default_currency}",String.valueOf(t.getDefaultCurrency()));
 		sql  = sql.replace("#{is_cease}",String.valueOf(t.getIsCease()));
 		sql  = sql.replace("#{payment_received_protocol}",String.valueOf(t.getPaymentReceivedProtocol()));
 		sql  = sql.replace("#{supervise_salesman}",String.valueOf(t.getSuperviseSalesman()));
 		sql  = sql.replace("#{corporation_address}",String.valueOf(t.getCorporationAddress()));
 		sql  = sql.replace("#{website}",String.valueOf(t.getWebsite()));
 		sql  = sql.replace("#{vld}",String.valueOf(t.getVld()));
 		sql  = sql.replace("#{cease_date}",String.valueOf(t.getCeaseDate()));
 		sql  = sql.replace("#{auditing_state}",String.valueOf(t.getAuditingState()));
 		sql  = sql.replace("#{bank_code}",String.valueOf(t.getBankCode()));
 		sql  = sql.replace("#{corporation_phone}",String.valueOf(t.getCorporationPhone()));
 		sql  = sql.replace("#{auditing_date}",String.valueOf(t.getAuditingDate()));
 		sql  = sql.replace("#{grant_date}",String.valueOf(t.getGrantDate()));
 		sql  = sql.replace("#{update_name}",String.valueOf(t.getUpdateName()));
 		sql  = sql.replace("#{oaa_bank}",String.valueOf(t.getOaaBank()));
 		sql  = sql.replace("#{affiliated_area}",String.valueOf(t.getAffiliatedArea()));
 		sql  = sql.replace("#{depart_id}",String.valueOf(t.getDepartId()));
 		sql  = sql.replace("#{extend1}",String.valueOf(t.getExtend1()));
 		sql  = sql.replace("#{extend2}",String.valueOf(t.getExtend2()));
 		sql  = sql.replace("#{extend3}",String.valueOf(t.getExtend3()));
 		sql  = sql.replace("#{extend4}",String.valueOf(t.getExtend4()));
 		sql  = sql.replace("#{extend5}",String.valueOf(t.getExtend5()));
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
					javaInter.execute("ba_customer",data);
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("执行JAVA增强出现异常！");
			} 
		}
 	}
}