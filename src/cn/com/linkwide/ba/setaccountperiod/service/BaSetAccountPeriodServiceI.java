package cn.com.linkwide.ba.setaccountperiod.service;
import cn.com.linkwide.ba.setaccountperiod.entity.BaSetAccountPeriodEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;
import java.util.Date;

public interface BaSetAccountPeriodServiceI extends CommonService{
	
 	public void delete(BaSetAccountPeriodEntity entity) throws Exception;
 	
 	public Serializable save(BaSetAccountPeriodEntity entity) throws Exception;
 	
 	public void saveOrUpdate(BaSetAccountPeriodEntity entity) throws Exception;
 	//获取指定日期的会计期间
	public String getPeriod(Date date);
	//获取指定日期的会计年
	public String getAcctYear(Date date);
	
	//获取最小的会计期间
	public String getMinPeriod();
	
	public BaSetAccountPeriodEntity getPeriod(String acctMonth);
}
