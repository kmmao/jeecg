package cn.com.linkwide.common.pubsetting.bdsharecl.service.impl;
import cn.com.linkwide.common.pubsetting.bdsharecl.entity.BdShareclEntity;
import cn.com.linkwide.common.pubsetting.bdsharecl.service.BdShareclServiceI;

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

@Service("bdShareclService")
@Transactional
public class BdShareclServiceImpl extends CommonServiceImpl implements BdShareclServiceI {

	
 	public void delete(BdShareclEntity entity) throws Exception{
 		super.delete(entity);
 		//执行删除操作增强业务
		this.doDelBus(entity);
 	}
 	
 	public Serializable save(BdShareclEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		//执行新增操作增强业务
 		this.doAddBus(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(BdShareclEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 		//执行更新操作增强业务
 		this.doUpdateBus(entity);
 	}
 	
 	/**
	 * 新增操作增强业务
	 * @param t
	 * @return
	 */
	private void doAddBus(BdShareclEntity t) throws Exception{
 	}
 	/**
	 * 更新操作增强业务
	 * @param t
	 * @return
	 */
	private void doUpdateBus(BdShareclEntity t) throws Exception{
 	}
 	/**
	 * 删除操作增强业务
	 * @param id
	 * @return
	 */
	private void doDelBus(BdShareclEntity t) throws Exception{
 	}
 	
 	private Map<String,Object> populationMap(BdShareclEntity t){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", t.getId());
		map.put("vcode", t.getVcode());
		map.put("vname", t.getVname());
		map.put("memo", t.getMemo());
		map.put("pk_corp", t.getPkCorp());
		map.put("pk_merger", t.getPkMerger());
		map.put("pk_parent", t.getPkParent());
		map.put("ts", t.getTs());
		map.put("vdef1", t.getVdef1());
		map.put("vdef2", t.getVdef2());
		map.put("vdef3", t.getVdef3());
		map.put("vdef4", t.getVdef4());
		map.put("vdef5", t.getVdef5());
		map.put("vdef6", t.getVdef6());
		map.put("vdef7", t.getVdef7());
		map.put("vdef8", t.getVdef8());
		map.put("vdef9", t.getVdef9());
		map.put("vdef10", t.getVdef10());
		return map;
	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @param t
	 * @return
	 */
 	public String replaceVal(String sql,BdShareclEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{vcode}",String.valueOf(t.getVcode()));
 		sql  = sql.replace("#{vname}",String.valueOf(t.getVname()));
 		sql  = sql.replace("#{memo}",String.valueOf(t.getMemo()));
 		sql  = sql.replace("#{pk_corp}",String.valueOf(t.getPkCorp()));
 		sql  = sql.replace("#{pk_merger}",String.valueOf(t.getPkMerger()));
 		sql  = sql.replace("#{pk_parent}",String.valueOf(t.getPkParent()));
 		sql  = sql.replace("#{ts}",String.valueOf(t.getTs()));
 		sql  = sql.replace("#{vdef1}",String.valueOf(t.getVdef1()));
 		sql  = sql.replace("#{vdef2}",String.valueOf(t.getVdef2()));
 		sql  = sql.replace("#{vdef3}",String.valueOf(t.getVdef3()));
 		sql  = sql.replace("#{vdef4}",String.valueOf(t.getVdef4()));
 		sql  = sql.replace("#{vdef5}",String.valueOf(t.getVdef5()));
 		sql  = sql.replace("#{vdef6}",String.valueOf(t.getVdef6()));
 		sql  = sql.replace("#{vdef7}",String.valueOf(t.getVdef7()));
 		sql  = sql.replace("#{vdef8}",String.valueOf(t.getVdef8()));
 		sql  = sql.replace("#{vdef9}",String.valueOf(t.getVdef9()));
 		sql  = sql.replace("#{vdef10}",String.valueOf(t.getVdef10()));
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