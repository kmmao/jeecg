package cn.com.linkwide.cont.contracttype.service.impl;
import cn.com.linkwide.cont.contracttype.service.ContractTypeServiceI;

import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import cn.com.linkwide.cont.contracttype.entity.ContractTypeEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.model.json.ComboTree;
import java.io.Serializable;
import org.jeecgframework.core.util.ApplicationContextUtil;
import org.jeecgframework.core.util.MyClassLoader;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.web.cgform.enhance.CgformEnhanceJavaInter;

@Service("contractTypeService")
@Transactional
public class ContractTypeServiceImpl extends CommonServiceImpl implements ContractTypeServiceI {

	
 	public void delete(ContractTypeEntity entity) throws Exception{
 		List<ContractTypeEntity> typeList = commonDao.findByProperty(ContractTypeEntity.class, "parentCode", entity.getCode());
 		if(typeList.size() >0 && typeList.get(0) !=null){
 			throw new BusinessException("不能删除父节点"); 
 		}
 		//查询父节点下有没有其他子节点，没有则将父节点设置为末级
 		List<ContractTypeEntity> parentList = commonDao.findByProperty(ContractTypeEntity.class, "parentCode", entity.getParentCode());
 		if(!(parentList.size() >0 && parentList.get(0) !=null)){
 			commonDao.updateBySqlString( "update contract_type set is_last = 1 where code='"+entity.getParentCode()+"' ");
 		}
 		super.delete(entity);
 		//执行删除操作增强业务
		this.doDelBus(entity);
 	}
 	
 	public Serializable save(ContractTypeEntity entity) throws Exception{
 		if((entity.getCode().length()-3)%2!=0){
 			throw new BusinessException("编码长度不符合规则！");
 		} 
 		if(entity.getParentCode() ==null || "".equals(entity.getParentCode())){
 			if(entity.getCode().length() !=3){
 				throw new BusinessException("一级编码长度应该是3位！");
 			}
 		}else{
 			if(!entity.getCode().startsWith(entity.getParentCode())){
 				throw new BusinessException("编码规则有误，编码开头应为父节点编码！");
 			} 
 		}
 		List<ContractTypeEntity> typeList0 = commonDao.findByProperty(ContractTypeEntity.class, "code", entity.getCode());
 		if(typeList0.size()>0 && typeList0.get(0)!=null){
 			throw new BusinessException("编码已存在！");
 		}
 		//自动设置级次
 		if(StringUtil.isEmpty(entity.getParentCode())){
 			entity.setLevel("0");
 		}else{
 			List<ContractTypeEntity> typeList = commonDao.findByProperty(ContractTypeEntity.class, "code", entity.getParentCode());
 			for(ContractTypeEntity ent : typeList){
 				entity.setLevel(String.valueOf(Integer.valueOf(ent.getLevel())+1));
 			}
 		}
 		entity.setIsLast(1);
 		commonDao.updateBySqlString(" update contract_type set is_last = 0 where code='"+entity.getParentCode()+"' ");
 		Serializable t = super.save(entity);
 		//执行新增操作增强业务
 		this.doAddBus(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(ContractTypeEntity entity) throws Exception{
 		//自动设置级次
 		if(StringUtil.isEmpty(entity.getParentCode())){
 			entity.setLevel("0");
 		}else{
 			List<ContractTypeEntity> typeList = commonDao.findByProperty(ContractTypeEntity.class, "code", entity.getParentCode());
 			for(ContractTypeEntity ent : typeList){
 				entity.setLevel(String.valueOf(Integer.valueOf(ent.getLevel())+1));//父节点的级次加1
 			}
 		}
 		//设置是否末级,没有下级节点时为末级否则不是
 		List<ContractTypeEntity> sonList = commonDao.findByProperty(ContractTypeEntity.class, "parentCode", entity.getCode());
 		if(sonList.size() >0 && sonList.get(0) !=null){
 			entity.setIsLast(0);
 		}else{
 			entity.setIsLast(1);
 		}
 		super.saveOrUpdate(entity);
 		//执行更新操作增强业务
 		this.doUpdateBus(entity);
 	}
 	
 	/**
	 * 新增操作增强业务
	 * @param t
	 * @return
	 */
	private void doAddBus(ContractTypeEntity t) throws Exception{
		//-----------------sql增强 start----------------------------
	 	//-----------------sql增强 end------------------------------
	 	
	 	//-----------------java增强 start---------------------------
	 	//-----------------java增强 end-----------------------------
 	}
 	/**
	 * 更新操作增强业务
	 * @param t
	 * @return
	 */
	private void doUpdateBus(ContractTypeEntity t) throws Exception{
		//-----------------sql增强 start----------------------------
	 	//-----------------sql增强 end------------------------------
	 	
	 	//-----------------java增强 start---------------------------
	 	//-----------------java增强 end-----------------------------
 	}
 	/**
	 * 删除操作增强业务
	 * @param id
	 * @return
	 */
	private void doDelBus(ContractTypeEntity t) throws Exception{
	    //-----------------sql增强 start----------------------------
	 	//-----------------sql增强 end------------------------------
	 	
	 	//-----------------java增强 start---------------------------
	 	//-----------------java增强 end-----------------------------
 	}
 	
 	private Map<String,Object> populationMap(ContractTypeEntity t){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", t.getId());
		map.put("create_date", t.getCreateDate());
		map.put("update_date", t.getUpdateDate());
		map.put("code", t.getCode());
		map.put("name", t.getName());
		map.put("parent_code", t.getParentCode());
		map.put("level", t.getLevel());
		map.put("is_stop", t.getIsStop());
		return map;
	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @param t
	 * @return
	 */
 	public String replaceVal(String sql,ContractTypeEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{create_date}",String.valueOf(t.getCreateDate()));
 		sql  = sql.replace("#{update_date}",String.valueOf(t.getUpdateDate()));
 		sql  = sql.replace("#{code}",String.valueOf(t.getCode()));
 		sql  = sql.replace("#{name}",String.valueOf(t.getName()));
 		sql  = sql.replace("#{parent_code}",String.valueOf(t.getParentCode()));
 		sql  = sql.replace("#{level}",String.valueOf(t.getLevel()));
 		sql  = sql.replace("#{is_stop}",String.valueOf(t.getIsStop()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}
 	
 	/**
	 * 执行JAVA增强
	 */
 	private void executeJavaExtend(String cgJavaType,String cgJavaValue,Map<String,Object> data) throws Exception {
 		if(StringUtil.isNotEmpty(cgJavaValue)){
			Object obj = null;
			try {
				if("class".equals(cgJavaType)){
					//因新增时已经校验了实例化是否可以成功，所以这块就不需要再做一次判断
					obj = MyClassLoader.getClassByScn(cgJavaValue).newInstance();
				}else if("spring".equals(cgJavaType)){
					obj = ApplicationContextUtil.getContext().getBean(cgJavaValue);
				}
				if(obj instanceof CgformEnhanceJavaInter){
					CgformEnhanceJavaInter javaInter = (CgformEnhanceJavaInter) obj;
					javaInter.execute("contract_type",data);
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("执行JAVA增强出现异常！");
			} 
		}
 	}

	@Override
	public void contractTypeTree(List<org.jeecgframework.core.common.model.json.ComboTree> rootLists,String code) {  
		List<ContractTypeEntity> findHql = commonDao.findByProperty(ContractTypeEntity.class, "parentCode", code ==null ? "" :code);
		ComboTree comboTree = null; 
		for (ContractTypeEntity typeEntity : findHql) {
			// 因处理的数据不是特别大采用查询方式
			List<ContractTypeEntity> findByProperty = this.commonDao.findByProperty(ContractTypeEntity.class, "parentCode", typeEntity.getCode());
			if(findByProperty.size()>0){
				comboTree = new ComboTree();
				comboTree.setState("closed");
				comboTree.setId(typeEntity.getCode());
				comboTree.setText(typeEntity.getName()); 
			}else{
				comboTree = new ComboTree();
				comboTree.setState("open");
				comboTree.setId(typeEntity.getCode());
				comboTree.setText(typeEntity.getName());
			}
			rootLists.add(comboTree);
		}
		
	}
}