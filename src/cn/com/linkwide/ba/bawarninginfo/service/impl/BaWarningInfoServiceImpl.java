package cn.com.linkwide.ba.bawarninginfo.service.impl;
import cn.com.linkwide.ba.bawarninginfo.service.BaWarningInfoServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import cn.com.linkwide.ba.bawarninginfo.entity.BaWarningInfoEntity;
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

@Service("baWarningInfoService")
@Transactional
public class BaWarningInfoServiceImpl extends CommonServiceImpl implements BaWarningInfoServiceI {

	
 	public void delete(BaWarningInfoEntity entity) throws Exception{
 		super.delete(entity);
 		//执行删除操作增强业务
		this.doDelBus(entity);
 	}
 	
 	public Serializable save(BaWarningInfoEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		//执行新增操作增强业务
 		this.doAddBus(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(BaWarningInfoEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 		//执行更新操作增强业务
 		this.doUpdateBus(entity);
 	}
 	
 	/**
	 * 新增操作增强业务
	 * @param t
	 * @return
	 */
	private void doAddBus(BaWarningInfoEntity t) throws Exception{
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
	private void doUpdateBus(BaWarningInfoEntity t) throws Exception{
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
	private void doDelBus(BaWarningInfoEntity t) throws Exception{
	    //-----------------sql增强 start----------------------------
	 	//-----------------sql增强 end------------------------------
	 	
	 	//-----------------java增强 start---------------------------
	 	//-----------------java增强 end-----------------------------
 	}
 	
 	private Map<String,Object> populationMap(BaWarningInfoEntity t){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", t.getId());
		map.put("create_name", t.getCreateName());
		map.put("create_by", t.getCreateBy());
		map.put("create_date", t.getCreateDate());
		map.put("warning_source", t.getWarningSource());
		map.put("warning_info", t.getWarningInfo());
		map.put("warning_basis", t.getWarningBasis());
		map.put("table_id", t.getTableId());
		map.put("warning_date", t.getWarningDate());
		map.put("v1", t.getV1());
		map.put("v2", t.getV2());
		map.put("v3", t.getV3());
		map.put("v4", t.getV4());
		map.put("v5", t.getV5());
		map.put("v6", t.getV6());
		map.put("v7", t.getV7());
		map.put("v8", t.getV8());
		map.put("v9", t.getV9());
		map.put("v10", t.getV10());
		return map;
	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @param t
	 * @return
	 */
 	public String replaceVal(String sql,BaWarningInfoEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{create_name}",String.valueOf(t.getCreateName()));
 		sql  = sql.replace("#{create_by}",String.valueOf(t.getCreateBy()));
 		sql  = sql.replace("#{create_date}",String.valueOf(t.getCreateDate()));
 		sql  = sql.replace("#{warning_source}",String.valueOf(t.getWarningSource()));
 		sql  = sql.replace("#{warning_info}",String.valueOf(t.getWarningInfo()));
 		sql  = sql.replace("#{warning_basis}",String.valueOf(t.getWarningBasis()));
 		sql  = sql.replace("#{table_id}",String.valueOf(t.getTableId()));
 		sql  = sql.replace("#{warning_date}",String.valueOf(t.getWarningDate()));
 		sql  = sql.replace("#{v1}",String.valueOf(t.getV1()));
 		sql  = sql.replace("#{v2}",String.valueOf(t.getV2()));
 		sql  = sql.replace("#{v3}",String.valueOf(t.getV3()));
 		sql  = sql.replace("#{v4}",String.valueOf(t.getV4()));
 		sql  = sql.replace("#{v5}",String.valueOf(t.getV5()));
 		sql  = sql.replace("#{v6}",String.valueOf(t.getV6()));
 		sql  = sql.replace("#{v7}",String.valueOf(t.getV7()));
 		sql  = sql.replace("#{v8}",String.valueOf(t.getV8()));
 		sql  = sql.replace("#{v9}",String.valueOf(t.getV9()));
 		sql  = sql.replace("#{v10}",String.valueOf(t.getV10()));
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
					javaInter.execute("ba_warning_info",data);
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("执行JAVA增强出现异常！");
			} 
		}
 	}
}