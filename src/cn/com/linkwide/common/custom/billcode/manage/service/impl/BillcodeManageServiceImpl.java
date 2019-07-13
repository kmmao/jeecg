package cn.com.linkwide.common.custom.billcode.manage.service.impl;
import cn.com.linkwide.common.custom.billcode.manage.entity.BillcodeManageEntity;
import cn.com.linkwide.common.custom.billcode.manage.service.BillcodeManageServiceI;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
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

@Service("billcodeManageService")
@Transactional
public class BillcodeManageServiceImpl extends CommonServiceImpl implements BillcodeManageServiceI {

	
 	public void delete(BillcodeManageEntity entity) throws Exception{
 		super.delete(entity);
 		//执行删除操作增强业务
		this.doDelBus(entity);
 	}
 	
 	public Serializable save(BillcodeManageEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		//执行新增操作增强业务
 		this.doAddBus(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(BillcodeManageEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 		//执行更新操作增强业务
 		this.doUpdateBus(entity);
 	}
 	
 	/**
	 * 新增操作增强业务
	 * @param t
	 * @return
	 */
	private void doAddBus(BillcodeManageEntity t) throws Exception{
 	}
 	/**
	 * 更新操作增强业务
	 * @param t
	 * @return
	 */
	private void doUpdateBus(BillcodeManageEntity t) throws Exception{
 	}
 	/**
	 * 删除操作增强业务
	 * @param id
	 * @return
	 */
	private void doDelBus(BillcodeManageEntity t) throws Exception{
 	}
 	
 	private Map<String,Object> populationMap(BillcodeManageEntity t){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", t.getId());
		map.put("create_name", t.getCreateName());
		map.put("create_by", t.getCreateBy());
		map.put("create_date", t.getCreateDate());
		map.put("update_name", t.getUpdateName());
		map.put("update_by", t.getUpdateBy());
		map.put("update_date", t.getUpdateDate());
		map.put("bill_scope ", t.getBillScope ());
		map.put("is_uniq", t.getIsUniq());
		map.put("is_keep", t.getIsKeep());
		map.put("auto_complete", t.getAutoComplete());
		map.put("bill_type", t.getBillType());
		map.put("bill_obj1", t.getBillObj1());
		map.put("bill_obj2", t.getBillObj2());
		map.put("code_year", t.getCodeYear());
		map.put("code_moth", t.getCodeMoth());
		map.put("code_day", t.getCodeDay());
		map.put("end_num", t.getEndNum());
		map.put("zero_mark", t.getZeroMark());
		map.put("apli_result", t.getApliResult());
		map.put("bill_desc", t.getBillDesc());
		map.put("bill_code", t.getBillCode());
		map.put("vdef1", t.getVdef1());
		map.put("vdef2", t.getVdef2());
		map.put("vdef3", t.getVdef3());
		return map;
	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @param t
	 * @return
	 */
 	public String replaceVal(String sql,BillcodeManageEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{create_name}",String.valueOf(t.getCreateName()));
 		sql  = sql.replace("#{create_by}",String.valueOf(t.getCreateBy()));
 		sql  = sql.replace("#{create_date}",String.valueOf(t.getCreateDate()));
 		sql  = sql.replace("#{update_name}",String.valueOf(t.getUpdateName()));
 		sql  = sql.replace("#{update_by}",String.valueOf(t.getUpdateBy()));
 		sql  = sql.replace("#{update_date}",String.valueOf(t.getUpdateDate()));
 		sql  = sql.replace("#{bill_scope }",String.valueOf(t.getBillScope ()));
 		sql  = sql.replace("#{is_uniq}",String.valueOf(t.getIsUniq()));
 		sql  = sql.replace("#{is_keep}",String.valueOf(t.getIsKeep()));
 		sql  = sql.replace("#{auto_complete}",String.valueOf(t.getAutoComplete()));
 		sql  = sql.replace("#{bill_type}",String.valueOf(t.getBillType()));
 		sql  = sql.replace("#{bill_obj1}",String.valueOf(t.getBillObj1()));
 		sql  = sql.replace("#{bill_obj2}",String.valueOf(t.getBillObj2()));
 		sql  = sql.replace("#{code_year}",String.valueOf(t.getCodeYear()));
 		sql  = sql.replace("#{code_moth}",String.valueOf(t.getCodeMoth()));
 		sql  = sql.replace("#{code_day}",String.valueOf(t.getCodeDay()));
 		sql  = sql.replace("#{end_num}",String.valueOf(t.getEndNum()));
 		sql  = sql.replace("#{zero_mark}",String.valueOf(t.getZeroMark()));
 		sql  = sql.replace("#{apli_result}",String.valueOf(t.getApliResult()));
 		sql  = sql.replace("#{bill_desc}",String.valueOf(t.getBillDesc()));
 		sql  = sql.replace("#{bill_code}",String.valueOf(t.getBillCode()));
 		sql  = sql.replace("#{vdef1}",String.valueOf(t.getVdef1()));
 		sql  = sql.replace("#{vdef2}",String.valueOf(t.getVdef2()));
 		sql  = sql.replace("#{vdef3}",String.valueOf(t.getVdef3()));
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
					javaInter.execute("",data);
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("执行JAVA增强出现异常！");
			} 
		}
 	}
}