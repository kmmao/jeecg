package cn.com.linkwide.ba.bacustomertype.service.impl;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.ApplicationContextUtil;
import org.jeecgframework.core.util.DynamicDBUtil;
import org.jeecgframework.core.util.MyClassLoader;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.web.cgform.enhance.CgformEnhanceJavaInter;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.linkwide.ba.bacustomertype.entity.BaCustomerTypeEntity;
import cn.com.linkwide.ba.bacustomertype.service.BaCustomerTypeServiceI;

@Service("baCustomerTypeService")
@Transactional
public class BaCustomerTypeServiceImpl extends CommonServiceImpl implements BaCustomerTypeServiceI {

	@Autowired
	private SystemService systemService;
 	public void delete(BaCustomerTypeEntity entity) throws Exception{
 		super.delete(entity);
 		//执行删除操作增强业务
		this.doDelBus(entity);
 	}
 	
 	public Serializable save(BaCustomerTypeEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		//执行新增操作增强业务
 		this.doAddBus(entity);
 		//推送到u8
 		synToU8(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(BaCustomerTypeEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 		//执行更新操作增强业务
 		this.doUpdateBus(entity);
 		//推送到u8
 		synToU8(entity);
 	}
 	
 	/**
	 * 向U8推送客户分类数据(编码规则2-3-4)
	 * @param entity
	 */
	private void synToU8(BaCustomerTypeEntity entity){
		try {
			if(entity!= null){
				String typeCode = entity.getTypeCode();
				Integer isLast = 1; //是否末级
				List<BaCustomerTypeEntity> typeList = systemService.findByProperty(BaCustomerTypeEntity.class, "parentId", typeCode);
				if(typeList != null && typeList.size()>0){
					isLast=0;
				}
				Integer level = typeCode.length()-2==0?1:(typeCode.length()-5<0?2:3); //级次
				String sql =" select cCCCode from CustomerClass where cCCCode='"+typeCode+"' ";
				List<BaCustomerTypeEntity> list = DynamicDBUtil.findListEntrys("U8", sql, BaCustomerTypeEntity.class);
				if(list!= null && list.size()>0){ //u8中存在,更新数据
					String updateSql =" update CustomerClass set cCCName='"+entity.getTypeName()+"',"
							+ " iCCGrade='"+level+"',bCCEnd='"+isLast+"' "
							+ " where cCCCode='"+typeCode+"'";
					DynamicDBUtil.update("U8", updateSql, null);
				}else{// u8中不存在，新增
					String insertSql =" insert into CustomerClass (cCCCode,cCCName,iCCGrade,bCCEnd) "
							+ "values('"+typeCode+"','"+entity.getTypeName()+"','"+level+"','"+isLast+"')";
					DynamicDBUtil.update("U8", insertSql, null);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
 	
 	/**
	 * 新增操作增强业务
	 * @param t
	 * @return
	 */
	private void doAddBus(BaCustomerTypeEntity t) throws Exception{
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
	private void doUpdateBus(BaCustomerTypeEntity t) throws Exception{
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
	private void doDelBus(BaCustomerTypeEntity t) throws Exception{
	    //-----------------sql增强 start----------------------------
	 	//-----------------sql增强 end------------------------------
	 	
	 	//-----------------java增强 start---------------------------
	 	//-----------------java增强 end-----------------------------
 	}
 	
 	private Map<String,Object> populationMap(BaCustomerTypeEntity t){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", t.getId());
		map.put("type_code", t.getTypeCode());
		map.put("type_name", t.getTypeName());
		map.put("parent_id", t.getParentId());
		map.put("status", t.getStatus());
		map.put("extend1", t.getExtend1());
		map.put("extend2", t.getExtend2());
		map.put("extend3", t.getExtend3());
		map.put("extend4", t.getExtend4());
		return map;
	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @param t
	 * @return
	 */
 	public String replaceVal(String sql,BaCustomerTypeEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{type_code}",String.valueOf(t.getTypeCode()));
 		sql  = sql.replace("#{type_name}",String.valueOf(t.getTypeName()));
 		sql  = sql.replace("#{parent_id}",String.valueOf(t.getParentId()));
 		sql  = sql.replace("#{status}",String.valueOf(t.getStatus()));
 		sql  = sql.replace("#{extend1}",String.valueOf(t.getExtend1()));
 		sql  = sql.replace("#{extend2}",String.valueOf(t.getExtend2()));
 		sql  = sql.replace("#{extend3}",String.valueOf(t.getExtend3()));
 		sql  = sql.replace("#{extend4}",String.valueOf(t.getExtend4()));
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
					javaInter.execute("ba_customer_type",data);
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("执行JAVA增强出现异常！");
			} 
		}
 	}
}