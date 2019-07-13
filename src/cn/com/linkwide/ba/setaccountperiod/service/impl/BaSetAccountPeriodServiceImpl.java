package cn.com.linkwide.ba.setaccountperiod.service.impl;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.ApplicationContextUtil;
import org.jeecgframework.core.util.DateUtils;
import org.jeecgframework.core.util.MyClassLoader;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.web.cgform.enhance.CgformEnhanceJavaInter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.linkwide.ba.setaccountperiod.entity.BaSetAccountPeriodEntity;
import cn.com.linkwide.ba.setaccountperiod.service.BaSetAccountPeriodServiceI;

@Service("baSetAccountPeriodService")
@Transactional
public class BaSetAccountPeriodServiceImpl extends CommonServiceImpl implements BaSetAccountPeriodServiceI {

//	@Autowired
//	private LStMonthlyPeriodServiceI lStMonthlyPeriodService;
	
 	public void delete(BaSetAccountPeriodEntity entity) throws Exception{
 		super.delete(entity);
 		//执行删除操作增强业务
		this.doDelBus(entity);
 	}
 	
 	public Serializable save(BaSetAccountPeriodEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		//执行新增操作增强业务
 		this.doAddBus(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(BaSetAccountPeriodEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 		//执行更新操作增强业务
 		this.doUpdateBus(entity);
 	}
 	
 	/**
	 * 新增操作增强业务
	 * @param t
	 * @return
	 */
	private void doAddBus(BaSetAccountPeriodEntity t) throws Exception{
 	}
 	/**
	 * 更新操作增强业务
	 * @param t
	 * @return
	 */
	private void doUpdateBus(BaSetAccountPeriodEntity t) throws Exception{
 	}
 	/**
	 * 删除操作增强业务
	 * @param id
	 * @return
	 */
	private void doDelBus(BaSetAccountPeriodEntity t) throws Exception{
 	}
 	
 	private Map<String,Object> populationMap(BaSetAccountPeriodEntity t){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", t.getId());
		map.put("period_year", t.getPeriodYear());
		map.put("period_month", t.getPeriodMonth());
		map.put("begin_date", t.getBeginDate());
		map.put("end_date", t.getEndDate());
		map.put("create_by", t.getCreateBy());
		map.put("create_date", t.getCreateDate());
		map.put("update_by", t.getUpdateBy());
		map.put("update_date", t.getUpdateDate());
		map.put("extend1", t.getExtend1());
		map.put("extend2", t.getExtend2());
		map.put("extend3", t.getExtend3());
		map.put("extend4", t.getExtend4());
		map.put("extend5", t.getExtend5());
		map.put("extend6", t.getExtend6());
		map.put("extend7", t.getExtend7());
		map.put("extend8", t.getExtend8());
		map.put("extend9", t.getExtend9());
		map.put("extend10", t.getExtend10());
		return map;
	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @param t
	 * @return
	 */
 	public String replaceVal(String sql,BaSetAccountPeriodEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{period_year}",String.valueOf(t.getPeriodYear()));
 		sql  = sql.replace("#{period_month}",String.valueOf(t.getPeriodMonth()));
 		sql  = sql.replace("#{begin_date}",String.valueOf(t.getBeginDate()));
 		sql  = sql.replace("#{end_date}",String.valueOf(t.getEndDate()));
 		sql  = sql.replace("#{create_by}",String.valueOf(t.getCreateBy()));
 		sql  = sql.replace("#{create_date}",String.valueOf(t.getCreateDate()));
 		sql  = sql.replace("#{update_by}",String.valueOf(t.getUpdateBy()));
 		sql  = sql.replace("#{update_date}",String.valueOf(t.getUpdateDate()));
 		sql  = sql.replace("#{extend1}",String.valueOf(t.getExtend1()));
 		sql  = sql.replace("#{extend2}",String.valueOf(t.getExtend2()));
 		sql  = sql.replace("#{extend3}",String.valueOf(t.getExtend3()));
 		sql  = sql.replace("#{extend4}",String.valueOf(t.getExtend4()));
 		sql  = sql.replace("#{extend5}",String.valueOf(t.getExtend5()));
 		sql  = sql.replace("#{extend6}",String.valueOf(t.getExtend6()));
 		sql  = sql.replace("#{extend7}",String.valueOf(t.getExtend7()));
 		sql  = sql.replace("#{extend8}",String.valueOf(t.getExtend8()));
 		sql  = sql.replace("#{extend9}",String.valueOf(t.getExtend9()));
 		sql  = sql.replace("#{extend10}",String.valueOf(t.getExtend10()));
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
 	
	@Override
	public String getPeriod(Date date) {
		String hql = "from BaSetAccountPeriodEntity where beginDate<=? and endDate>=?";
		List<BaSetAccountPeriodEntity> list = this.findHql(hql,date,date);
		if(list.size()>0)
		return list.get(0).getPeriodMonth();
		return "";
	}

	@Override
	public String getMinPeriod() {
		String sql = "select min(period_month) period_month from ba_set_account_period";
		List<Map<String, Object>> list = this.findForJdbc(sql);
		for (Map<String, Object> map : list) {
			return (String)map.get("period_month");
		}
		return null;
	}

	@Override
	public BaSetAccountPeriodEntity getPeriod(String acctMonth) {
		// TODO Auto-generated method stub
		BaSetAccountPeriodEntity period = this.findUniqueByProperty(BaSetAccountPeriodEntity.class, "periodMonth", acctMonth);
		return period;
	}

	@Override
	public String getAcctYear(Date date) {
		date=DateUtils.str2Date(DateUtils.date2Str(date, DateUtils.date_sdf), DateUtils.date_sdf);
		String hql = "from BaSetAccountPeriodEntity where ? between beginDate and endDate";
		List<BaSetAccountPeriodEntity> list = this.findHql(hql,date);
		if(list.size()>0)
		return list.get(0).getPeriodYear();
		return "";
	}
 	
 	
}