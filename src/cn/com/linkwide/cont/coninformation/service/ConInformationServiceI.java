package cn.com.linkwide.cont.coninformation.service;
import java.util.List;

import org.jeecgframework.core.common.service.CommonService;

import cn.com.linkwide.cont.coninformation.entity.ConInformationEntity;
import cn.com.linkwide.cont.coninformationdetial.entity.ConMarkEntity;
import cn.com.linkwide.cont.conmemorabilia.entity.ConMemorabiliaEntity;
import cn.com.linkwide.cont.contbank.entity.ContBankEntity;
import cn.com.linkwide.cont.payplan.entity.PayPlanEntity;

public interface ConInformationServiceI extends CommonService{
	
 	public <T> void delete(T entity);
	/**
	 * 添加一对多
	 * 
	 */
	public void addMain(ConInformationEntity conInformation,
	        List<ConMarkEntity> conMarkList,List<PayPlanEntity> payPlanList,List<ConMemorabiliaEntity> conMemorabiliaList,List<ContBankEntity> contBankList) ;
	
	
	/**
	 * 添加一对一
	 * 
	 */
	public void addMain(ConInformationEntity conInformation,
	        List<ConMarkEntity> conMarkList) ;
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(ConInformationEntity conInformation,
	        List<ConMarkEntity> conMarkList,List<PayPlanEntity> payPlanList,List<ConMemorabiliaEntity> conMemorabiliaList,List<ContBankEntity> contBankList );
	
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(ConInformationEntity conInformation,
	        List<ConMarkEntity> conMarkList);
	public void delMain (ConInformationEntity conInformation);
	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(ConInformationEntity t);
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(ConInformationEntity t);
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(ConInformationEntity t);
	void approve(ConInformationEntity t,String actReModelCode);
}
