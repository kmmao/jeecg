package cn.com.linkwide.cont.negotiation.conreqinstructionsmain.service.impl;
import cn.com.linkwide.cont.negotiation.conreqinstructionsmain.service.ConReqInstructionsMainServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import cn.com.linkwide.cont.negotiation.conreqinstructionsmain.entity.ConReqInstructionsMainEntity;
import cn.com.linkwide.cont.negotiation.conreqinstructionsdetail.entity.ConReqInstructionsDetailEntity;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.oConvertUtils;
import java.util.ArrayList;
import java.util.UUID;
import java.io.Serializable;


@Service("conReqInstructionsMainService")
@Transactional
public class ConReqInstructionsMainServiceImpl extends CommonServiceImpl implements ConReqInstructionsMainServiceI {
	
 	public <T> void delete(T entity) {
 		super.delete(entity);
 		//执行删除操作配置的sql增强
		this.doDelSql((ConReqInstructionsMainEntity)entity);
 	}
	
	public void addMain(ConReqInstructionsMainEntity conReqInstructionsMain,
	        List<ConReqInstructionsDetailEntity> conReqInstructionsDetailList){
			//保存主信息
			this.save(conReqInstructionsMain);
		
			/**保存-请示单明细*/
			for(ConReqInstructionsDetailEntity conReqInstructionsDetail:conReqInstructionsDetailList){
				//外键设置
				conReqInstructionsDetail.setPkId(conReqInstructionsMain.getId());
				this.save(conReqInstructionsDetail);
			}
			//执行新增操作配置的sql增强
 			this.doAddSql(conReqInstructionsMain);
	}

	
	public void updateMain(ConReqInstructionsMainEntity conReqInstructionsMain,
	        List<ConReqInstructionsDetailEntity> conReqInstructionsDetailList) {
		//保存主表信息
		if(StringUtil.isNotEmpty(conReqInstructionsMain.getId())){
			try {
				ConReqInstructionsMainEntity temp = findUniqueByProperty(ConReqInstructionsMainEntity.class, "id", conReqInstructionsMain.getId());
				MyBeanUtils.copyBeanNotNull2Bean(conReqInstructionsMain, temp);
				this.saveOrUpdate(temp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			this.saveOrUpdate(conReqInstructionsMain);
		}
		//===================================================================================
		//获取参数
		Object id0 = conReqInstructionsMain.getId();
		//===================================================================================
		//1.查询出数据库的明细数据-请示单明细
	    String hql0 = "from ConReqInstructionsDetailEntity where 1 = 1 AND pK_ID = ? ";
	    List<ConReqInstructionsDetailEntity> conReqInstructionsDetailOldList = this.findHql(hql0,id0);
		//2.筛选更新明细数据-请示单明细
		if(conReqInstructionsDetailList!=null&&conReqInstructionsDetailList.size()>0){
		for(ConReqInstructionsDetailEntity oldE:conReqInstructionsDetailOldList){
			boolean isUpdate = false;
				for(ConReqInstructionsDetailEntity sendE:conReqInstructionsDetailList){
					//需要更新的明细数据-请示单明细
					if(oldE.getId().equals(sendE.getId())){
		    			try {
							MyBeanUtils.copyBeanNotNull2Bean(sendE,oldE);
							this.saveOrUpdate(oldE);
						} catch (Exception e) {
							e.printStackTrace();
							throw new BusinessException(e.getMessage());
						}
						isUpdate= true;
		    			break;
		    		}
		    	}
	    		if(!isUpdate){
		    		//如果数据库存在的明细，前台没有传递过来则是删除-请示单明细
		    		super.delete(oldE);
	    		}
	    		
			}
			//3.持久化新增的数据-请示单明细
			for(ConReqInstructionsDetailEntity conReqInstructionsDetail:conReqInstructionsDetailList){
				if(oConvertUtils.isEmpty(conReqInstructionsDetail.getId())){
					//外键设置
					conReqInstructionsDetail.setPkId(conReqInstructionsMain.getId());
					this.save(conReqInstructionsDetail);
				}
			}
		}
		//执行更新操作配置的sql增强
 		this.doUpdateSql(conReqInstructionsMain);
	}

	
	public void delMain(ConReqInstructionsMainEntity conReqInstructionsMain) {
		//删除主表信息
		this.delete(conReqInstructionsMain);
		//===================================================================================
		//获取参数
		Object id0 = conReqInstructionsMain.getId();
		//===================================================================================
		//删除-请示单明细
	    String hql0 = "from ConReqInstructionsDetailEntity where 1 = 1 AND pK_ID = ? ";
	    List<ConReqInstructionsDetailEntity> conReqInstructionsDetailOldList = this.findHql(hql0,id0);
		this.deleteAllEntitie(conReqInstructionsDetailOldList);
	}
	
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(ConReqInstructionsMainEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(ConReqInstructionsMainEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(ConReqInstructionsMainEntity t){
	 	return true;
 	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @return
	 */
 	public String replaceVal(String sql,ConReqInstructionsMainEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{create_name}",String.valueOf(t.getCreateName()));
 		sql  = sql.replace("#{create_by}",String.valueOf(t.getCreateBy()));
 		sql  = sql.replace("#{create_date}",String.valueOf(t.getCreateDate()));
 		sql  = sql.replace("#{update_name}",String.valueOf(t.getUpdateName()));
 		sql  = sql.replace("#{update_by}",String.valueOf(t.getUpdateBy()));
 		sql  = sql.replace("#{update_date}",String.valueOf(t.getUpdateDate()));
 		sql  = sql.replace("#{sys_org_code}",String.valueOf(t.getSysOrgCode()));
 		sql  = sql.replace("#{sys_company_code}",String.valueOf(t.getSysCompanyCode()));
 		sql  = sql.replace("#{bpm_status}",String.valueOf(t.getBpmStatus()));
 		sql  = sql.replace("#{requ_date}",String.valueOf(t.getRequDate()));
 		sql  = sql.replace("#{requ_no}",String.valueOf(t.getRequNo()));
 		sql  = sql.replace("#{requ_emp}",String.valueOf(t.getRequEmp()));
 		sql  = sql.replace("#{requ_code}",String.valueOf(t.getRequCode()));
 		sql  = sql.replace("#{requ_name}",String.valueOf(t.getRequName()));
 		sql  = sql.replace("#{requ_extent1}",String.valueOf(t.getRequExtent1()));
 		sql  = sql.replace("#{requ_extent2}",String.valueOf(t.getRequExtent2()));
 		sql  = sql.replace("#{requ_extent3}",String.valueOf(t.getRequExtent3()));
 		sql  = sql.replace("#{requ_extent4}",String.valueOf(t.getRequExtent4()));
 		sql  = sql.replace("#{requ_extent5}",String.valueOf(t.getRequExtent5()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}
}