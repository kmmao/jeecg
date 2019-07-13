package cn.com.linkwide.ba.bafisconelevitem.service;
import cn.com.linkwide.ba.bafisconelevitem.entity.BaFiscOnelevItemEntity;

import org.jeecgframework.core.common.model.json.ComboTree;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public interface BaFiscOnelevItemServiceI extends CommonService{
	
 	public void delete(BaFiscOnelevItemEntity entity) throws Exception;
 	
 	public Serializable save(BaFiscOnelevItemEntity entity) throws Exception;
 	
 	public void saveOrUpdate(BaFiscOnelevItemEntity entity) throws Exception;

	public void tree1(List<ComboTree> rootLists, HttpServletRequest request);

	public void treeOnelev(List<ComboTree> rootLists, HttpServletRequest request);
 	
}
