package cn.com.linkwide.common.custom.vcodeplan.service.impl;
import cn.com.linkwide.common.custom.vcodeplan.entity.VcodeplanEntity;
import cn.com.linkwide.common.custom.vcodeplan.service.VcodeplanServiceI;

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

@Service("vcodeplanService")
@Transactional
public class VcodeplanServiceImpl extends CommonServiceImpl implements VcodeplanServiceI {

	
 	public void delete(VcodeplanEntity entity) throws Exception{
 		super.delete(entity);
 		//执行删除操作增强业务
		this.doDelBus(entity);
 	}
 	
 	public Serializable save(VcodeplanEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		//执行新增操作增强业务
 		this.doAddBus(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(VcodeplanEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 		//执行更新操作增强业务
 		this.doUpdateBus(entity);
 	}
 	
 	/**
	 * 新增操作增强业务
	 * @param t
	 * @return
	 */
	private void doAddBus(VcodeplanEntity t) throws Exception{
 	}
 	/**
	 * 更新操作增强业务
	 * @param t
	 * @return
	 */
	private void doUpdateBus(VcodeplanEntity t) throws Exception{
 	}
 	/**
	 * 删除操作增强业务
	 * @param id
	 * @return
	 */
	private void doDelBus(VcodeplanEntity t) throws Exception{
 	}
 	
 	private Map<String,Object> populationMap(VcodeplanEntity t){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", t.getId());
		map.put("biglevel", t.getBiglevel());
		map.put("biglength", t.getBiglength());
		map.put("bigeachlength", t.getBigeachlength());
		map.put("onelevel", t.getOnelevel());
		map.put("twolevel", t.getTwolevel());
		map.put("threelevel", t.getThreelevel());
		map.put("fourlevel", t.getFourlevel());
		map.put("fivelevel", t.getFivelevel());
		map.put("sixlevel", t.getSixlevel());
		map.put("sevenlevel", t.getSevenlevel());
		map.put("eightlevel", t.getEightlevel());
		map.put("ninelevel", t.getNinelevel());
		map.put("tenlevel", t.getTenlevel());
		map.put("vdef1", t.getVdef1());
		map.put("vdef2", t.getVdef2());
		map.put("vdef3", t.getVdef3());
		map.put("vdef4", t.getVdef4());
		map.put("vdef5", t.getVdef5());
		return map;
	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @param t
	 * @return
	 */
 	public String replaceVal(String sql,VcodeplanEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{biglevel}",String.valueOf(t.getBiglevel()));
 		sql  = sql.replace("#{biglength}",String.valueOf(t.getBiglength()));
 		sql  = sql.replace("#{bigeachlength}",String.valueOf(t.getBigeachlength()));
 		sql  = sql.replace("#{onelevel}",String.valueOf(t.getOnelevel()));
 		sql  = sql.replace("#{twolevel}",String.valueOf(t.getTwolevel()));
 		sql  = sql.replace("#{threelevel}",String.valueOf(t.getThreelevel()));
 		sql  = sql.replace("#{fourlevel}",String.valueOf(t.getFourlevel()));
 		sql  = sql.replace("#{fivelevel}",String.valueOf(t.getFivelevel()));
 		sql  = sql.replace("#{sixlevel}",String.valueOf(t.getSixlevel()));
 		sql  = sql.replace("#{sevenlevel}",String.valueOf(t.getSevenlevel()));
 		sql  = sql.replace("#{eightlevel}",String.valueOf(t.getEightlevel()));
 		sql  = sql.replace("#{ninelevel}",String.valueOf(t.getNinelevel()));
 		sql  = sql.replace("#{tenlevel}",String.valueOf(t.getTenlevel()));
 		sql  = sql.replace("#{vdef1}",String.valueOf(t.getVdef1()));
 		sql  = sql.replace("#{vdef2}",String.valueOf(t.getVdef2()));
 		sql  = sql.replace("#{vdef3}",String.valueOf(t.getVdef3()));
 		sql  = sql.replace("#{vdef4}",String.valueOf(t.getVdef4()));
 		sql  = sql.replace("#{vdef5}",String.valueOf(t.getVdef5()));
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