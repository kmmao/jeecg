package cn.com.linkwide.cont.conmain.service;
import cn.com.linkwide.cont.conmain.entity.ContConMainEntity;
import cn.com.linkwide.cont.condetail.entity.ContConDetailEntity;

import java.util.List;
import org.jeecgframework.core.common.service.CommonService;
import java.io.Serializable;

public interface ContConMainServiceI extends CommonService{
	
 	public <T> void delete(T entity);
	/**
	 * 添加一对多
	 * 
	 */
	public void addMain(ContConMainEntity contConMain,
	        List<ContConDetailEntity> contConDetailList) ;
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(ContConMainEntity contConMain,
	        List<ContConDetailEntity> contConDetailList);
	public void delMain (ContConMainEntity contConMain);
	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(ContConMainEntity t);
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(ContConMainEntity t);
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(ContConMainEntity t);
}
