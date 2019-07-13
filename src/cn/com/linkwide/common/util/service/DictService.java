package cn.com.linkwide.common.util.service;
import org.jeecgframework.core.common.service.CommonService;

public interface DictService extends CommonService{
	
	/**
	 * 对数据字典计量单位进行缓存
	 */
	public void initAllMateUnit();
	
	/**
	 * 刷新计量单位缓存
	 */
	public void refreshMateUnit();
 	
	
	public void initAll();
}
