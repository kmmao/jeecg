package cn.com.linkwide.cont.conprotocol.service;
import cn.com.linkwide.cont.conprotocol.entity.ConProtocolEntity;
import cn.com.linkwide.cont.conprotocoldetail.entity.ConProtocolDetailEntity;

import java.util.List;
import org.jeecgframework.core.common.service.CommonService;
import java.io.Serializable;

public interface ConProtocolServiceI extends CommonService{
	
 	public <T> void delete(T entity);
	/**
	 * 添加一对多
	 * 
	 */
	public void addMain(ConProtocolEntity conProtocol,
	        List<ConProtocolDetailEntity> conProtocolDetailList) ;
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(ConProtocolEntity conProtocol,
	        List<ConProtocolDetailEntity> conProtocolDetailList);
	public void delMain (ConProtocolEntity conProtocol);
	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(ConProtocolEntity t);
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(ConProtocolEntity t);
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(ConProtocolEntity t);
}
