package cn.com.linkwide.cont.conpayapply.service.impl;
import cn.com.linkwide.cont.conpayapply.service.ConPayApplyServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import cn.com.linkwide.cont.conpayapply.entity.ConPayApplyEntity;
import cn.com.linkwide.cont.conpayapplydetail.entity.ConPayApplyDetailEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.web.system.service.SystemService;

import java.util.ArrayList;
import java.util.UUID;
import java.io.Serializable;


@Service("conPayApplyService")
@Transactional
public class ConPayApplyServiceImpl extends CommonServiceImpl implements ConPayApplyServiceI {
	@Autowired
	private SystemService systemService;
	
 	public <T> void delete(T entity) {
 		super.delete(entity);
 		//执行删除操作配置的sql增强
		this.doDelSql((ConPayApplyEntity)entity);
 	}
	
	public void addMain(ConPayApplyEntity conPayApply,
	        List<ConPayApplyDetailEntity> conPayApplyDetailList){
			//保存主信息
			this.save(conPayApply);
		
			/**保存-付款申请明细*/
			for(ConPayApplyDetailEntity conPayApplyDetail:conPayApplyDetailList){
				//外键设置
				conPayApplyDetail.setMianId(conPayApply.getId());
				try {
					String SQL="";
					if(StringUtil.isOrcla()){
					 SQL=" update pay_plan set apply_money=nvl(apply_money,0)+"+Double.parseDouble(conPayApplyDetail.getApplyMoney())+" where id='"+conPayApplyDetail.getPlanId()+"'";
					}else{
						 SQL=" update pay_plan set apply_money=ifnull(apply_money,0)+"+Double.parseDouble(conPayApplyDetail.getApplyMoney())+" where id='"+conPayApplyDetail.getPlanId()+"'";
					}
					systemService.updateBySqlString(SQL);
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
				this.save(conPayApplyDetail);
			}
			//执行新增操作配置的sql增强
 			this.doAddSql(conPayApply);
	}

	
	public void updateMain(ConPayApplyEntity conPayApply,
	        List<ConPayApplyDetailEntity> conPayApplyDetailList) {
		//保存主表信息
		if(StringUtil.isNotEmpty(conPayApply.getId())){
			try {
				ConPayApplyEntity temp = findUniqueByProperty(ConPayApplyEntity.class, "id", conPayApply.getId());
				MyBeanUtils.copyBeanNotNull2Bean(conPayApply, temp);
				this.saveOrUpdate(temp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			this.saveOrUpdate(conPayApply);
		}
		//===================================================================================
		//获取参数
		Object id0 = conPayApply.getId();
		//===================================================================================
		//1.查询出数据库的明细数据-付款申请明细
	    String hql0 = "from ConPayApplyDetailEntity where 1 = 1 AND mIAN_ID = ? ";
	    List<ConPayApplyDetailEntity> conPayApplyDetailOldList = this.findHql(hql0,id0);
		//2.筛选更新明细数据-付款申请明细
		if(conPayApplyDetailList!=null&&conPayApplyDetailList.size()>0){
		for(ConPayApplyDetailEntity oldE:conPayApplyDetailOldList){
			boolean isUpdate = false;
				for(ConPayApplyDetailEntity sendE:conPayApplyDetailList){
					//需要更新的明细数据-付款申请明细
					if(oldE.getId().equals(sendE.getId())){
		    			try {
		    			 double money=	Double.parseDouble(sendE.getApplyMoney())-Double.parseDouble(oldE.getApplyMoney());
		    			 String sql="";
		    			 if(money>0){
		    				 if(StringUtil.isOrcla()){
				    				sql=" update pay_plan set apply_money=nvl(apply_money,0)-"+money+" where id='"+sendE.getPlanId()+"'";
				    			}else{
				    				sql=" update pay_plan set apply_money=ifnull(apply_money,0)-"+money+" where id='"+sendE.getPlanId()+"'";
				    			} 
		    			 }else if(money<0){
		    				 if(StringUtil.isOrcla()){
				    				sql=" update pay_plan set apply_money=nvl(apply_money,0)+"+Math.abs(money)+" where id='"+sendE.getPlanId()+"'";
				    			}else{
				    				sql=" update pay_plan set apply_money=ifnull(apply_money,0)+"+Math.abs(money)+" where id='"+sendE.getPlanId()+"'";
				    			} 
		    			 }
		    			
		    				
		    					systemService.updateBySqlString(sql);
		    					
		    				
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
		    		//如果数据库存在的明细，前台没有传递过来则是删除-付款申请明细
		    		super.delete(oldE);
	    		}
	    		
			}
			//3.持久化新增的数据-付款申请明细
			for(ConPayApplyDetailEntity conPayApplyDetail:conPayApplyDetailList){
				if(oConvertUtils.isEmpty(conPayApplyDetail.getId())){
					//外键设置
					conPayApplyDetail.setMianId(conPayApply.getId());
					this.save(conPayApplyDetail);
				}
			}
		}
		//执行更新操作配置的sql增强
 		this.doUpdateSql(conPayApply);
	}

	
	public void delMain(ConPayApplyEntity conPayApply) {
		//删除主表信息
		this.delete(conPayApply);
		//===================================================================================
		//获取参数
		Object id0 = conPayApply.getId();
		//===================================================================================
		//删除-付款申请明细
	    String hql0 = "from ConPayApplyDetailEntity where 1 = 1 AND mIAN_ID = ? ";
	    List<ConPayApplyDetailEntity> conPayApplyDetailOldList = this.findHql(hql0,id0);
	    for (ConPayApplyDetailEntity conPayApplyDetailEntity : conPayApplyDetailOldList) {
			try {
				String sql="";
				if(StringUtil.isOrcla()){
					sql=" update pay_plan set apply_money=nvl(apply_money,0)-"+Double.parseDouble(conPayApplyDetailEntity.getApplyMoney())+" where id='"+conPayApplyDetailEntity.getPlanId()+"'";
				}else{
					sql=" update pay_plan set apply_money=ifnull(apply_money,0)-"+Double.parseDouble(conPayApplyDetailEntity.getApplyMoney())+" where id='"+conPayApplyDetailEntity.getPlanId()+"'";
				}
				
				systemService.updateBySqlString(sql);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
	    }
		this.deleteAllEntitie(conPayApplyDetailOldList);
	}
	
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(ConPayApplyEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(ConPayApplyEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(ConPayApplyEntity t){
	 	return true;
 	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @return
	 */
 	public String replaceVal(String sql,ConPayApplyEntity t){
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
 		sql  = sql.replace("#{apply_date}",String.valueOf(t.getApplyDate()));
 		sql  = sql.replace("#{apply_no}",String.valueOf(t.getApplyNo()));
 		sql  = sql.replace("#{other_compy}",String.valueOf(t.getOtherCompy()));
 		sql  = sql.replace("#{con_id}",String.valueOf(t.getConId()));
 		sql  = sql.replace("#{con_name}",String.valueOf(t.getConName()));
 		sql  = sql.replace("#{con_number}",String.valueOf(t.getConNumber()));
 		sql  = sql.replace("#{sum_money}",String.valueOf(t.getSumMoney()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}
}