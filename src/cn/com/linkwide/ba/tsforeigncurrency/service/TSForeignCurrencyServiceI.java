package cn.com.linkwide.ba.tsforeigncurrency.service;
import cn.com.linkwide.ba.tsforeigncurrency.entity.TSForeignCurrencyEntity;
import cn.com.linkwide.ba.tscurrencyexch.entity.TSCurrencyExchEntity;

import java.util.List;
import org.jeecgframework.core.common.service.CommonService;
import java.io.Serializable;

public interface TSForeignCurrencyServiceI extends CommonService{
	
 	public <T> void delete(T entity);
	/**
	 * 添加一对多
	 * 
	 */
	public void addMain(TSForeignCurrencyEntity tSForeignCurrency,
	        List<TSCurrencyExchEntity> tSCurrencyExchList) ;
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(TSForeignCurrencyEntity tSForeignCurrency,
	        List<TSCurrencyExchEntity> tSCurrencyExchList);
	public void delMain (TSForeignCurrencyEntity tSForeignCurrency);
	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(TSForeignCurrencyEntity t);
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(TSForeignCurrencyEntity t);
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(TSForeignCurrencyEntity t);
}
