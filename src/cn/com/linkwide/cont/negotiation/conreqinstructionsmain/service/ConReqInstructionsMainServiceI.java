package cn.com.linkwide.cont.negotiation.conreqinstructionsmain.service;
import cn.com.linkwide.cont.negotiation.conreqinstructionsmain.entity.ConReqInstructionsMainEntity;
import cn.com.linkwide.cont.negotiation.conreqinstructionsdetail.entity.ConReqInstructionsDetailEntity;

import java.util.List;
import org.jeecgframework.core.common.service.CommonService;
import java.io.Serializable;

public interface ConReqInstructionsMainServiceI extends CommonService{
	
 	public <T> void delete(T entity);
	/**
	 * 添加一对多
	 * 
	 */
	public void addMain(ConReqInstructionsMainEntity conReqInstructionsMain,
	        List<ConReqInstructionsDetailEntity> conReqInstructionsDetailList) ;
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(ConReqInstructionsMainEntity conReqInstructionsMain,
	        List<ConReqInstructionsDetailEntity> conReqInstructionsDetailList);
	public void delMain (ConReqInstructionsMainEntity conReqInstructionsMain);
	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(ConReqInstructionsMainEntity t);
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(ConReqInstructionsMainEntity t);
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(ConReqInstructionsMainEntity t);
}
