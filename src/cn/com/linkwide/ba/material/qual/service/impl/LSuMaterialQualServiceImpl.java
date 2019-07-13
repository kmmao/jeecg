package cn.com.linkwide.ba.material.qual.service.impl;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.ComboTree;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.linkwide.ba.material.qual.entity.LSuMaterialQualEntity;
import cn.com.linkwide.ba.material.qual.entity.LSuMaterialQualInvEntity;
import cn.com.linkwide.ba.material.qual.service.LSuMaterialQualServiceI;
import cn.com.linkwide.ba.syn.SynchronousBa;



@Service("LSuMaterialQualService")
@Transactional
public class LSuMaterialQualServiceImpl extends CommonServiceImpl implements LSuMaterialQualServiceI {
	
 	public <T> void delete(T entity) {
 		super.delete(entity);
 		//执行删除操作配置的sql增强
		this.doDelSql((LSuMaterialQualEntity)entity);
		//同步数据
		String sql1 =SynchronousBa.synDelete(entity);
		if(StringUtil.isNotEmpty(sql1)){
			this.updateBySqlString(sql1);
		}
 	}
	
	public void addMain(LSuMaterialQualEntity LSuMaterialQual,
	        List<LSuMaterialQualInvEntity> lSuMaterialQualInvList){
			//保存主信息
			this.save(LSuMaterialQual);
		
			/**保存-证件材料对应关系*/
			for(LSuMaterialQualInvEntity entity:lSuMaterialQualInvList){
				//外键设置
				entity.setCertId(LSuMaterialQual.getId());
				this.save(entity);
			}
			//执行新增操作配置的sql增强
 			this.doAddSql(LSuMaterialQual);
	}

	
	public void updateMain(LSuMaterialQualEntity LSuMaterialQual,
	        List<LSuMaterialQualInvEntity> lSuMaterialQualInvList) {
		//保存主表信息
		if(StringUtil.isNotEmpty(LSuMaterialQual.getId())){
			try {
				LSuMaterialQualEntity temp = findUniqueByProperty(LSuMaterialQualEntity.class, "id", LSuMaterialQual.getId());
				MyBeanUtils.copyBeanNotNull2Bean(LSuMaterialQual, temp);
				this.saveOrUpdate(temp);
				//同步数据
				String sql1 =SynchronousBa.synDelete(temp);
				if(StringUtil.isNotEmpty(sql1)){
					this.updateBySqlString(sql1);
				}
				String sql2 =SynchronousBa.synSave(temp);
				if(StringUtil.isNotEmpty(sql2)){
					this.updateBySqlString(sql2);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			this.saveOrUpdate(LSuMaterialQual);
			
			//同步数据
			String sql1 =SynchronousBa.synDelete(LSuMaterialQual);
			if(StringUtil.isNotEmpty(sql1)){
				this.updateBySqlString(sql1);
			}
			String sql2 =SynchronousBa.synSave(LSuMaterialQual);
			if(StringUtil.isNotEmpty(sql2)){
				this.updateBySqlString(sql2);
			}
		}
		//===================================================================================
		//获取参数
		Object id0 = LSuMaterialQual.getId();
		//===================================================================================
		//1.查询出数据库的明细数据-证件材料对应关系
	    String hql0 = "from LSuMaterialQualInvEntity where 1 = 1 AND cERT_ID = ? ";
	    List<LSuMaterialQualInvEntity> vendorCertInvOldList = this.findHql(hql0,id0);
		//2.筛选更新明细数据-证件材料对应关系
		if(lSuMaterialQualInvList!=null&&lSuMaterialQualInvList.size()>0){
		for(LSuMaterialQualInvEntity oldE:vendorCertInvOldList){
			boolean isUpdate = false;
				for(LSuMaterialQualInvEntity sendE:lSuMaterialQualInvList){
					//需要更新的明细数据-证件材料对应关系
					if(oldE.getId().equals(sendE.getId())){
		    			try {
							MyBeanUtils.copyBeanNotNull2Bean(sendE,oldE);
							this.saveOrUpdate(oldE);
							
							//同步数据
							String sql1 =SynchronousBa.synDelete(oldE);
							if(StringUtil.isNotEmpty(sql1)){
								this.updateBySqlString(sql1);
							}
							String sql2 =SynchronousBa.synSave(oldE);
							if(StringUtil.isNotEmpty(sql2)){
								this.updateBySqlString(sql2);
							}
						} catch (Exception e) {
							e.printStackTrace();
							throw new BusinessException(e.getMessage());
						}
						isUpdate= true;
		    			break;
		    		}
		    	}
	    		if(!isUpdate){
		    		//如果数据库存在的明细，前台没有传递过来则是删除-证件材料对应关系
		    		super.delete(oldE);
		    		//同步数据
		    		String sql1 =SynchronousBa.synDelete(oldE);
		    		if(StringUtil.isNotEmpty(sql1)){
		    			this.updateBySqlString(sql1);
		    		}
	    		}
	    		
			}
			//3.持久化新增的数据-证件材料对应关系
			for(LSuMaterialQualInvEntity vendorCertInv:lSuMaterialQualInvList){
				if(oConvertUtils.isEmpty(vendorCertInv.getId())){
					//外键设置
					vendorCertInv.setCertId(LSuMaterialQual.getId());
					this.save(vendorCertInv);
					//同步数据
					String sql =SynchronousBa.synSave(vendorCertInv);
					if(StringUtil.isNotEmpty(sql)){
						this.updateBySqlString(sql);
					}
				}
			}
		}
		//执行更新操作配置的sql增强
 		this.doUpdateSql(LSuMaterialQual); 
	}

	
	public void delMain(LSuMaterialQualEntity LSuMaterialQual) {
		//删除主表信息
		this.delete(LSuMaterialQual);
		//===================================================================================
		//获取参数
		Object id0 = LSuMaterialQual.getId();
		//===================================================================================
		//删除-证件材料对应关系
	    String hql0 = "from LSuMaterialQualInvEntity where 1 = 1 AND cERT_ID = ? ";
	    List<LSuMaterialQualInvEntity> vendorCertInvOldList = this.findHql(hql0,id0);
	    
		this.deleteAllEntitie(vendorCertInvOldList); 
	}
	
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(LSuMaterialQualEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(LSuMaterialQualEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(LSuMaterialQualEntity t){
	 	return true;
 	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @return
	 */
 	public String replaceVal(String sql,LSuMaterialQualEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{create_name}",String.valueOf(t.getCreateName()));
 		sql  = sql.replace("#{create_by}",String.valueOf(t.getCreateBy()));
 		sql  = sql.replace("#{create_date}",String.valueOf(t.getCreateDate()));
 		sql  = sql.replace("#{update_name}",String.valueOf(t.getUpdateName()));
 		sql  = sql.replace("#{update_by}",String.valueOf(t.getUpdateBy()));
 		sql  = sql.replace("#{update_date}",String.valueOf(t.getUpdateDate()));
 		sql  = sql.replace("#{sys_org_code}",String.valueOf(t.getSysOrgCode()));
 		/*sql  = sql.replace("#{sys_company_code}",String.valueOf(t.getSysCompanyCode()));*/
 		sql  = sql.replace("#{bpm_status}",String.valueOf(t.getBpmStatus()));
 		sql  = sql.replace("#{cert_type}",String.valueOf(t.getCertType()));
 		sql  = sql.replace("#{cert_code}",String.valueOf(t.getCertCode()));
 		sql  = sql.replace("#{cert_name}",String.valueOf(t.getCertName()));
 		sql  = sql.replace("#{cert_date}",String.valueOf(t.getCertDate()));
 		sql  = sql.replace("#{start_date}",String.valueOf(t.getStartDate()));
 		sql  = sql.replace("#{end_date}",String.valueOf(t.getEndDate()));
 		sql  = sql.replace("#{organ}",String.valueOf(t.getOrgan()));
 		sql  = sql.replace("#{cert_state}",String.valueOf(t.getCertState()));
 		sql  = sql.replace("#{remark}",String.valueOf(t.getRemark()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}

	@Override
	public AjaxJson checkQual(List<String> materialList) {
		// TODO Auto-generated method stub
		AjaxJson json = new AjaxJson();
		return json;
	}

	@Override
	public List<LSuMaterialQualEntity> findInvalidMQual(String supplierId, String materialId, Date data) {
		// TODO Auto-generated method stub
		if(data==null){
			data = new Date();
		}
		String hql =" from LSuMaterialQualEntity where (isStop is null or isStop =0) and  supplierId ='"+supplierId+"' and mateId ='"+materialId+"' and startDate >? and endDate <?";
		return this.findHql(hql, data,data);
	}

	@Override
	public AjaxJson checkMQual(String supplierId, String mateIds, Date date) {
		//校验供应商物资资质 
		if(date==null){
			date = new Date();
		}
		mateIds = mateIds.replaceAll(",", "','"); 
		//校验过期资质
		String hql ="select a  from LSuMaterialQualEntity a,LBaMaterialEntity b where  a.mateId = b.id and b.isControl=1 and b.id in ('"+mateIds+"') and  (a.isStop is null or a.isStop =0) and  a.supplierId ='"+supplierId+"'   and a.startDate >? and a.endDate <?";
		List<LSuMaterialQualEntity> mQList = this.findHql(hql, date,date);
		for(LSuMaterialQualEntity mate : mQList){ 
			AjaxJson j = new AjaxJson();
			j.setMsg("供应商物资资质["+mQList.get(0).getCertCode()+":"+mQList.get(0).getCertName()+"]无效或者已过期! ");
			j.setSuccess(false);
			return j;
		}
		//校验 需要资质校验的物资没有资质的情况（停用也算没有）
		String sql ="select id  id ,material_code  code ,material_name  name  from l_ba_material m where m.is_control=1 and m.id in ('"+mateIds+"') "
				+ "and not exists ( select id from l_su_material_qual q where q.supplier_id = '"+supplierId+"' and q.mate_id = m.id and  (q.is_stop is null or q.is_stop =0)    and q.start_date <"+StringUtil.tj(date)+" and q.end_date >"+StringUtil.tj(date)+") ";
		List<Map<String,Object>> mList = this.findForJdbc(sql, null);
		if(mList != null && mList.size()>0){
			AjaxJson j = new AjaxJson();
			j.setMsg("供应商缺少物资["+mList.get(0).get("code")+":"+mList.get(0).get("name")+"]的资质 ");
			j.setSuccess(false);
			return j;
		}
		return null;
	}

	@Override
	public void certTypeTree(List<org.jeecgframework.core.common.model.json.ComboTree> rootLists, String code) {
		List<Map<String, Object>> list = this.commonDao.findForJdbc("select id  id ,type_code  typeCode ,type_name  typeName ,parent_id  parentId from l_su_qual_type where parent_id ='"+(code ==null ? "" :code)+"' or parent_id is null   ", null);
		ComboTree comboTree = null; 
		for (Map<String, Object> typeEntity : list) {
			// 因处理的数据不是特别大采用查询方式
			//查询有没有子节点
			List<Map<String, Object>> list2 = this.commonDao.findForJdbc("select id  id ,type_code  typeCode ,type_name  typeName ,parent_id  parentId from l_su_qual_type where parent_id ='"+typeEntity.get("id")+"'  ", null);
			if(list2.size()>0 && list2.get(0)!=null){ 
				comboTree = new ComboTree();
				comboTree.setState("closed");
				comboTree.setId(typeEntity.get("id").toString());
				comboTree.setText(typeEntity.get("typeName").toString());  
					
			}else{
				comboTree = new ComboTree();
				comboTree.setState("open");
				comboTree.setId(typeEntity.get("id").toString());
				comboTree.setText(typeEntity.get("typeName").toString()); 
			}
			rootLists.add(comboTree); 
		}
		
	}
	 
	
}