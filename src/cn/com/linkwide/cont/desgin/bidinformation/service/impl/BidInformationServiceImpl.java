package cn.com.linkwide.cont.desgin.bidinformation.service.impl;
import cn.com.linkwide.cont.desgin.bidinformation.service.BidInformationServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import cn.com.linkwide.cont.desgin.bidinformation.entity.BidInformationEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.io.Serializable;
import org.jeecgframework.core.util.ApplicationContextUtil;
import org.jeecgframework.core.util.MyClassLoader;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.web.cgform.enhance.CgformEnhanceJavaInter;

@Service("bidInformationService")
@Transactional
public class BidInformationServiceImpl extends CommonServiceImpl implements BidInformationServiceI {

	
 	public void delete(BidInformationEntity entity) throws Exception{
 		super.delete(entity);
 		//执行删除操作增强业务
		this.doDelBus(entity);
 	}
 	
 	public Serializable save(BidInformationEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		//执行新增操作增强业务
 		this.doAddBus(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(BidInformationEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 		//执行更新操作增强业务
 		this.doUpdateBus(entity);
 	}
 	
 	/**
	 * 新增操作增强业务
	 * @param t
	 * @return
	 */
	private void doAddBus(BidInformationEntity t) throws Exception{
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
	private void doUpdateBus(BidInformationEntity t) throws Exception{
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
	private void doDelBus(BidInformationEntity t) throws Exception{
	    //-----------------sql增强 start----------------------------
	 	//-----------------sql增强 end------------------------------
	 	
	 	//-----------------java增强 start---------------------------
	 	//-----------------java增强 end-----------------------------
 	}
 	
 	private Map<String,Object> populationMap(BidInformationEntity t){
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
		map.put("bpm_status", t.getBpmStatus());
		map.put("bid_code", t.getBidCode());
		map.put("con_bid_code", t.getConBidCode());
		map.put("ven_code", t.getVenCode());
		map.put("ven_name", t.getVenName());
		map.put("inv_code", t.getInvCode());
		map.put("inv_name", t.getInvName());
		map.put("unit_code", t.getUnitCode());
		map.put("unit_name", t.getUnitName());
		map.put("num", t.getNum());
		map.put("price", t.getPrice());
		map.put("money", t.getMoney());
		map.put("dept_code", t.getDeptCode());
		map.put("budg_code", t.getBudgCode());
		map.put("register_code", t.getRegisterCode());
		map.put("manufacturer", t.getManufacturer());
		map.put("remark", t.getRemark());
		map.put("receive_time", t.getReceiveTime());
		map.put("exetens1", t.getExetens1());
		map.put("exetens2", t.getExetens2());
		map.put("exetens3", t.getExetens3());
		map.put("exetens4", t.getExetens4());
		map.put("exetens5", t.getExetens5());
		return map;
	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @param t
	 * @return
	 */
 	public String replaceVal(String sql,BidInformationEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{create_name}",String.valueOf(t.getCreateName()));
 		sql  = sql.replace("#{create_by}",String.valueOf(t.getCreateBy()));
 		sql  = sql.replace("#{create_date}",String.valueOf(t.getCreateDate()));
 		sql  = sql.replace("#{update_name}",String.valueOf(t.getUpdateName()));
 		sql  = sql.replace("#{update_by}",String.valueOf(t.getUpdateBy()));
 		sql  = sql.replace("#{update_date}",String.valueOf(t.getUpdateDate()));
 		sql  = sql.replace("#{sys_org_code}",String.valueOf(t.getSysOrgCode()));
 		sql  = sql.replace("#{sys_company_code}",String.valueOf(t.getSysCompanyCode()));
 		sql  = sql.replace("#{bpm_status}",String.valueOf(t.getBpmStatus()));
 		sql  = sql.replace("#{bid_code}",String.valueOf(t.getBidCode()));
 		sql  = sql.replace("#{con_bid_code}",String.valueOf(t.getConBidCode()));
 		sql  = sql.replace("#{ven_code}",String.valueOf(t.getVenCode()));
 		sql  = sql.replace("#{ven_name}",String.valueOf(t.getVenName()));
 		sql  = sql.replace("#{inv_code}",String.valueOf(t.getInvCode()));
 		sql  = sql.replace("#{inv_name}",String.valueOf(t.getInvName()));
 		sql  = sql.replace("#{unit_code}",String.valueOf(t.getUnitCode()));
 		sql  = sql.replace("#{unit_name}",String.valueOf(t.getUnitName()));
 		sql  = sql.replace("#{num}",String.valueOf(t.getNum()));
 		sql  = sql.replace("#{price}",String.valueOf(t.getPrice()));
 		sql  = sql.replace("#{money}",String.valueOf(t.getMoney()));
 		sql  = sql.replace("#{dept_code}",String.valueOf(t.getDeptCode()));
 		sql  = sql.replace("#{budg_code}",String.valueOf(t.getBudgCode()));
 		sql  = sql.replace("#{register_code}",String.valueOf(t.getRegisterCode()));
 		sql  = sql.replace("#{manufacturer}",String.valueOf(t.getManufacturer()));
 		sql  = sql.replace("#{remark}",String.valueOf(t.getRemark()));
 		sql  = sql.replace("#{receive_time}",String.valueOf(t.getReceiveTime()));
 		sql  = sql.replace("#{exetens1}",String.valueOf(t.getExetens1()));
 		sql  = sql.replace("#{exetens2}",String.valueOf(t.getExetens2()));
 		sql  = sql.replace("#{exetens3}",String.valueOf(t.getExetens3()));
 		sql  = sql.replace("#{exetens4}",String.valueOf(t.getExetens4()));
 		sql  = sql.replace("#{exetens5}",String.valueOf(t.getExetens5()));
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
					javaInter.execute("bid_information",data);
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("执行JAVA增强出现异常！");
			} 
		}
 	}
}