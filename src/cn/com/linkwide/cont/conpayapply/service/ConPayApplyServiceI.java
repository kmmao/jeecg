package cn.com.linkwide.cont.conpayapply.service;
import cn.com.linkwide.cont.conpayapply.entity.ConPayApplyEntity;
import cn.com.linkwide.cont.conpayapplydetail.entity.ConPayApplyDetailEntity;

import java.util.List;
import org.jeecgframework.core.common.service.CommonService;
import java.io.Serializable;

public interface ConPayApplyServiceI extends CommonService{
	
 	public <T> void delete(T entity);
	/**
	 * 添加一对多
	 * 
	 */
	public void addMain(ConPayApplyEntity conPayApply,
	        List<ConPayApplyDetailEntity> conPayApplyDetailList) ;
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(ConPayApplyEntity conPayApply,
	        List<ConPayApplyDetailEntity> conPayApplyDetailList);
	public void delMain (ConPayApplyEntity conPayApply);
	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(ConPayApplyEntity t);
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(ConPayApplyEntity t);
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(ConPayApplyEntity t);
}
