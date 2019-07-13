package cn.com.linkwide.common.list.service; 
import org.jeecgframework.core.common.model.json.ComboTree;
import org.jeecgframework.core.common.service.CommonService;

import cn.com.linkwide.common.fileUpload.entity.FileDictEntity;

import java.io.Serializable;
import java.util.List;

public interface DictListServiceI extends CommonService{
	 

	public void departTree(List<ComboTree> rootLists, String code);
 	
}
