package cn.com.linkwide.ba.tsforeigncurrency.service.impl;
import cn.com.linkwide.ba.tsforeigncurrency.service.TSForeignCurrencyServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import cn.com.linkwide.ba.tsforeigncurrency.entity.TSForeignCurrencyEntity;
import cn.com.linkwide.ba.tscurrencyexch.entity.TSCurrencyExchEntity;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.oConvertUtils;
import java.util.ArrayList;
import java.util.UUID;
import java.io.Serializable;


@Service("tSForeignCurrencyService")
@Transactional
public class TSForeignCurrencyServiceImpl extends CommonServiceImpl implements TSForeignCurrencyServiceI {
	
 	public <T> void delete(T entity) {
 		super.delete(entity);
 		//执行删除操作配置的sql增强
		this.doDelSql((TSForeignCurrencyEntity)entity);
 	}
	
	public void addMain(TSForeignCurrencyEntity tSForeignCurrency,
	        List<TSCurrencyExchEntity> tSCurrencyExchList){
			//保存主信息
			this.save(tSForeignCurrency);
		
			/**保存-币种汇率*/
			for(TSCurrencyExchEntity tSCurrencyExch:tSCurrencyExchList){
				//外键设置
				tSCurrencyExch.setCurrencyId(tSForeignCurrency.getId());
				this.save(tSCurrencyExch);
			}
			//执行新增操作配置的sql增强
 			this.doAddSql(tSForeignCurrency);
	}

	
	public void updateMain(TSForeignCurrencyEntity tSForeignCurrency,
	        List<TSCurrencyExchEntity> tSCurrencyExchList) {
		//保存主表信息
		if(StringUtil.isNotEmpty(tSForeignCurrency.getId())){
			try {
				TSForeignCurrencyEntity temp = findUniqueByProperty(TSForeignCurrencyEntity.class, "id", tSForeignCurrency.getId());
				MyBeanUtils.copyBeanNotNull2Bean(tSForeignCurrency, temp);
				this.saveOrUpdate(temp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			this.saveOrUpdate(tSForeignCurrency);
		}
		//===================================================================================
		//获取参数
		Object id0 = tSForeignCurrency.getId();
		//===================================================================================
		//1.查询出数据库的明细数据-币种汇率
	    String hql0 = "from TSCurrencyExchEntity where 1 = 1 AND cURRENCY_ID = ? ";
	    List<TSCurrencyExchEntity> tSCurrencyExchOldList = this.findHql(hql0,id0);
		//2.筛选更新明细数据-币种汇率
		if(tSCurrencyExchList!=null&&tSCurrencyExchList.size()>0){
		for(TSCurrencyExchEntity oldE:tSCurrencyExchOldList){
			boolean isUpdate = false;
				for(TSCurrencyExchEntity sendE:tSCurrencyExchList){
					//需要更新的明细数据-币种汇率
					if(oldE.getId().equals(sendE.getId())){
		    			try {
							MyBeanUtils.copyBeanNotNull2Bean(sendE,oldE);
							this.saveOrUpdate(oldE);
						} catch (Exception e) {
							e.printStackTrace();
							throw new BusinessException(e.getMessage());
						}
						isUpdate= true;
		    			break;
		    		}
		    	}
	    		if(!isUpdate){
		    		//如果数据库存在的明细，前台没有传递过来则是删除-币种汇率
		    		super.delete(oldE);
	    		}
	    		
			}
			//3.持久化新增的数据-币种汇率
			for(TSCurrencyExchEntity tSCurrencyExch:tSCurrencyExchList){
				if(oConvertUtils.isEmpty(tSCurrencyExch.getId())){
					//外键设置
					tSCurrencyExch.setCurrencyId(tSForeignCurrency.getId());
					this.save(tSCurrencyExch);
				}
			}
		}
		//执行更新操作配置的sql增强
 		this.doUpdateSql(tSForeignCurrency);
	}

	
	public void delMain(TSForeignCurrencyEntity tSForeignCurrency) {
		//删除主表信息
		this.delete(tSForeignCurrency);
		//===================================================================================
		//获取参数
		Object id0 = tSForeignCurrency.getId();
		//===================================================================================
		//删除-币种汇率
	    String hql0 = "from TSCurrencyExchEntity where 1 = 1 AND cURRENCY_ID = ? ";
	    List<TSCurrencyExchEntity> tSCurrencyExchOldList = this.findHql(hql0,id0);
		this.deleteAllEntitie(tSCurrencyExchOldList);
	}
	
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(TSForeignCurrencyEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(TSForeignCurrencyEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(TSForeignCurrencyEntity t){
	 	return true;
 	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @return
	 */
 	public String replaceVal(String sql,TSForeignCurrencyEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{bpm_status}",String.valueOf(t.getBpmStatus()));
 		sql  = sql.replace("#{create_name}",String.valueOf(t.getCreateName()));
 		sql  = sql.replace("#{create_by}",String.valueOf(t.getCreateBy()));
 		sql  = sql.replace("#{update_name}",String.valueOf(t.getUpdateName()));
 		sql  = sql.replace("#{update_by}",String.valueOf(t.getUpdateBy()));
 		sql  = sql.replace("#{sys_org_code}",String.valueOf(t.getSysOrgCode()));
 		sql  = sql.replace("#{sys_company_code}",String.valueOf(t.getSysCompanyCode()));
 		sql  = sql.replace("#{create_date}",String.valueOf(t.getCreateDate()));
 		sql  = sql.replace("#{update_date}",String.valueOf(t.getUpdateDate()));
 		sql  = sql.replace("#{currency_code}",String.valueOf(t.getCurrencyCode()));
 		sql  = sql.replace("#{currency_name}",String.valueOf(t.getCurrencyName()));
 		sql  = sql.replace("#{convert_type}",String.valueOf(t.getConvertType()));
 		sql  = sql.replace("#{decimal_num}",String.valueOf(t.getDecimalNum()));
 		sql  = sql.replace("#{max_error}",String.valueOf(t.getMaxError()));
 		sql  = sql.replace("#{is_otherused}",String.valueOf(t.getIsOtherused()));
 		sql  = sql.replace("#{pubufts}",String.valueOf(t.getPubufts()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}
}