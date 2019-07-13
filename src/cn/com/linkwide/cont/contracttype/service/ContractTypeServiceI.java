package cn.com.linkwide.cont.contracttype.service;
import cn.com.linkwide.cont.contracttype.entity.ContractTypeEntity;

import org.jeecgframework.core.common.model.json.ComboTree;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public interface ContractTypeServiceI extends CommonService{
	
 	public void delete(ContractTypeEntity entity) throws Exception;
 	
 	public Serializable save(ContractTypeEntity entity) throws Exception;
 	
 	public void saveOrUpdate(ContractTypeEntity entity) throws Exception;

	public void contractTypeTree(List<ComboTree> rootLists, String code);
 	
}
