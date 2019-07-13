package cn.com.linkwide.common.fileUpload.service; 
import org.jeecgframework.core.common.service.CommonService;

import cn.com.linkwide.common.fileUpload.entity.FileDictEntity;

import java.io.Serializable;

public interface FileDictServiceI extends CommonService{
	
 	public void delete(FileDictEntity entity) throws Exception;
 	
 	public Serializable save(FileDictEntity entity) throws Exception;
 	
 	public void saveOrUpdate(FileDictEntity entity) throws Exception;
 	
}
