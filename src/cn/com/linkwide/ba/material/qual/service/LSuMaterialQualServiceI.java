package cn.com.linkwide.ba.material.qual.service;
import java.util.Date;
import java.util.List;

import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.ComboTree;
import org.jeecgframework.core.common.service.CommonService;

import cn.com.linkwide.ba.material.qual.entity.LSuMaterialQualEntity;
import cn.com.linkwide.ba.material.qual.entity.LSuMaterialQualInvEntity;

public interface LSuMaterialQualServiceI extends CommonService{
	
 	public <T> void delete(T entity);
	/**
	 * 添加一对多
	 * 
	 */
	public void addMain(LSuMaterialQualEntity lSuMaterialQual,
	        List<LSuMaterialQualInvEntity> lSuMaterialQualInvList) ;
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(LSuMaterialQualEntity lSuMaterialQual,
	        List<LSuMaterialQualInvEntity> lSuMaterialQualInvList);
	public void delMain (LSuMaterialQualEntity lSuMaterialQual);
	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(LSuMaterialQualEntity t);
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(LSuMaterialQualEntity t);
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(LSuMaterialQualEntity t);
	public AjaxJson checkQual(List<String> materialList);
	/**
	 * 查询供应商已过期的物资资质
	 * @param supplierId
	 * @param materialId
	 * @param data
	 * @return
	 */
	public List<LSuMaterialQualEntity> findInvalidMQual(String supplierId,String materialId,Date data);
	
	/**
	 * 校验供应商物资资质
	 * @param supplierId
	 * @param materialId
	 * @param data
	 * @return
	 */
	public AjaxJson checkMQual(String supplierId,String mateIds,Date data);
	
 	/***
 	 * 查询资质分类的树形下拉
 	 * @param rootLists
 	 * @param string
 	 */
	public void certTypeTree(List<ComboTree> rootLists, String string);
}
