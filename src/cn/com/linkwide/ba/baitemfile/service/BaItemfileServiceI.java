package cn.com.linkwide.ba.baitemfile.service;
import cn.com.linkwide.ba.baitemfile.entity.BaItemfileEntity;

import org.jeecgframework.core.common.model.json.ComboTree;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public interface BaItemfileServiceI extends CommonService{
	
 	public void delete(BaItemfileEntity entity) throws Exception;
 	
 	public Serializable save(BaItemfileEntity entity) throws Exception;
 	
 	public void saveOrUpdate(BaItemfileEntity entity) throws Exception;

	public void tree1(List<ComboTree> rootLists, HttpServletRequest request);
 	
}
