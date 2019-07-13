package cn.com.linkwide.cont.conmain.service.impl;
import cn.com.linkwide.cont.conmain.service.ContConMainServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import cn.com.linkwide.cont.conmain.entity.ContConMainEntity;
import cn.com.linkwide.cont.condetail.entity.ContConDetailEntity;

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


@Service("contConMainService")
@Transactional
public class ContConMainServiceImpl extends CommonServiceImpl implements ContConMainServiceI {
	
 	public <T> void delete(T entity) {
 		super.delete(entity);
 		//执行删除操作配置的sql增强
		this.doDelSql((ContConMainEntity)entity);
 	}
	
	public void addMain(ContConMainEntity contConMain,
	        List<ContConDetailEntity> contConDetailList){
			//保存主信息
			this.save(contConMain);
		
			/**保存-合同明细*/
			for(ContConDetailEntity contConDetail:contConDetailList){
				//外键设置
				contConDetail.setPkId(contConMain.getId());
				this.save(contConDetail);
			}
			//执行新增操作配置的sql增强
 			this.doAddSql(contConMain);
	}

	
	public void updateMain(ContConMainEntity contConMain,
	        List<ContConDetailEntity> contConDetailList) {
		//保存主表信息
		if(StringUtil.isNotEmpty(contConMain.getId())){
			try {
				ContConMainEntity temp = findUniqueByProperty(ContConMainEntity.class, "id", contConMain.getId());
				MyBeanUtils.copyBeanNotNull2Bean(contConMain, temp);
				this.saveOrUpdate(temp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			this.saveOrUpdate(contConMain);
		}
		//===================================================================================
		//获取参数
		Object id0 = contConMain.getId();
		//===================================================================================
		//1.查询出数据库的明细数据-合同明细
	    String hql0 = "from ContConDetailEntity where 1 = 1 AND pK_ID = ? ";
	    List<ContConDetailEntity> contConDetailOldList = this.findHql(hql0,id0);
		//2.筛选更新明细数据-合同明细
		if(contConDetailList!=null&&contConDetailList.size()>0){
		for(ContConDetailEntity oldE:contConDetailOldList){
			boolean isUpdate = false;
				for(ContConDetailEntity sendE:contConDetailList){
					//需要更新的明细数据-合同明细
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
		    		//如果数据库存在的明细，前台没有传递过来则是删除-合同明细
		    		super.delete(oldE);
	    		}
	    		
			}
			//3.持久化新增的数据-合同明细
			for(ContConDetailEntity contConDetail:contConDetailList){
				if(oConvertUtils.isEmpty(contConDetail.getId())){
					//外键设置
					contConDetail.setPkId(contConMain.getId());
					this.save(contConDetail);
				}
			}
		}
		//执行更新操作配置的sql增强
 		this.doUpdateSql(contConMain);
	}

	
	public void delMain(ContConMainEntity contConMain) {
		//删除主表信息
		this.delete(contConMain);
		//===================================================================================
		//获取参数
		Object id0 = contConMain.getId();
		//===================================================================================
		//删除-合同明细
	    String hql0 = "from ContConDetailEntity where 1 = 1 AND pK_ID = ? ";
	    List<ContConDetailEntity> contConDetailOldList = this.findHql(hql0,id0);
		this.deleteAllEntitie(contConDetailOldList);
	}
	
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(ContConMainEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(ContConMainEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(ContConMainEntity t){
	 	return true;
 	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @return
	 */
 	public String replaceVal(String sql,ContConMainEntity t){
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
 		sql  = sql.replace("#{contract_code}",String.valueOf(t.getContractCode()));
 		sql  = sql.replace("#{contract_name}",String.valueOf(t.getContractName()));
 		sql  = sql.replace("#{contract_type}",String.valueOf(t.getContractType()));
 		sql  = sql.replace("#{sign_date}",String.valueOf(t.getSignDate()));
 		sql  = sql.replace("#{dept_code}",String.valueOf(t.getDeptCode()));
 		sql  = sql.replace("#{trade_class}",String.valueOf(t.getTradeClass()));
 		sql  = sql.replace("#{currency}",String.valueOf(t.getCurrency()));
 		sql  = sql.replace("#{amount_money}",String.valueOf(t.getAmountMoney()));
 		sql  = sql.replace("#{con_money}",String.valueOf(t.getConMoney()));
 		sql  = sql.replace("#{hosp_id}",String.valueOf(t.getHospId()));
 		sql  = sql.replace("#{pay_meth}",String.valueOf(t.getPayMeth()));
 		sql  = sql.replace("#{company}",String.valueOf(t.getCompany()));
 		sql  = sql.replace("#{con_state}",String.valueOf(t.getConState()));
 		sql  = sql.replace("#{is_bid}",String.valueOf(t.getIsBid()));
 		sql  = sql.replace("#{org_type}",String.valueOf(t.getOrgType()));
 		sql  = sql.replace("#{purc_type}",String.valueOf(t.getPurcType()));
 		sql  = sql.replace("#{fist_party}",String.valueOf(t.getFistParty()));
 		sql  = sql.replace("#{op_perpion}",String.valueOf(t.getOpPerpion()));
 		sql  = sql.replace("#{begin_date}",String.valueOf(t.getBeginDate()));
 		sql  = sql.replace("#{end_date}",String.valueOf(t.getEndDate()));
 		sql  = sql.replace("#{con_group}",String.valueOf(t.getConGroup()));
 		sql  = sql.replace("#{change_money}",String.valueOf(t.getChangeMoney()));
 		sql  = sql.replace("#{bond_type}",String.valueOf(t.getBondType()));
 		sql  = sql.replace("#{bond_money}",String.valueOf(t.getBondMoney()));
 		sql  = sql.replace("#{parent_con}",String.valueOf(t.getParentCon()));
 		sql  = sql.replace("#{pay_or_rec}",String.valueOf(t.getPayOrRec()));
 		sql  = sql.replace("#{remark}",String.valueOf(t.getRemark()));
 		sql  = sql.replace("#{is_forward}",String.valueOf(t.getIsForward()));
 		sql  = sql.replace("#{cas_date}",String.valueOf(t.getCasDate()));
 		sql  = sql.replace("#{cas_emp}",String.valueOf(t.getCasEmp()));
 		sql  = sql.replace("#{confirm_date}",String.valueOf(t.getConfirmDate()));
 		sql  = sql.replace("#{confirm_emp}",String.valueOf(t.getConfirmEmp()));
 		sql  = sql.replace("#{perform_date}",String.valueOf(t.getPerformDate()));
 		sql  = sql.replace("#{perform_emp}",String.valueOf(t.getPerformEmp()));
 		sql  = sql.replace("#{change_date}",String.valueOf(t.getChangeDate()));
 		sql  = sql.replace("#{change_emp}",String.valueOf(t.getChangeEmp()));
 		sql  = sql.replace("#{termination_date}",String.valueOf(t.getTerminationDate()));
 		sql  = sql.replace("#{termination_emp}",String.valueOf(t.getTerminationEmp()));
 		sql  = sql.replace("#{renew_date}",String.valueOf(t.getRenewDate()));
 		sql  = sql.replace("#{renew_emp}",String.valueOf(t.getRenewEmp()));
 		sql  = sql.replace("#{contract_state}",String.valueOf(t.getContractState()));
 		sql  = sql.replace("#{ven_code}",String.valueOf(t.getVenCode()));
 		sql  = sql.replace("#{customer_code}",String.valueOf(t.getCustomerCode()));
 		sql  = sql.replace("#{bid_code}",String.valueOf(t.getBidCode()));
 		sql  = sql.replace("#{pu_re_code}",String.valueOf(t.getPuReCode()));
 		sql  = sql.replace("#{ine_code}",String.valueOf(t.getIneCode()));
 		sql  = sql.replace("#{op_compy}",String.valueOf(t.getOpCompy()));
 		sql  = sql.replace("#{yw_perpion}",String.valueOf(t.getYwPerpion()));
 		sql  = sql.replace("#{bx_date}",String.valueOf(t.getBxDate()));
 		sql  = sql.replace("#{contract_ms}",String.valueOf(t.getContractMs()));
 		sql  = sql.replace("#{cr_perpion}",String.valueOf(t.getCrPerpion()));
 		sql  = sql.replace("#{cr_date}",String.valueOf(t.getCrDate()));
 		sql  = sql.replace("#{sx_perpion}",String.valueOf(t.getSxPerpion()));
 		sql  = sql.replace("#{sx_date}",String.valueOf(t.getSxDate()));
 		sql  = sql.replace("#{ja_perpion}",String.valueOf(t.getJaPerpion()));
 		sql  = sql.replace("#{ja_date}",String.valueOf(t.getJaDate()));
 		sql  = sql.replace("#{zbj_late}",String.valueOf(t.getZbjLate()));
 		sql  = sql.replace("#{zbj_start_date}",String.valueOf(t.getZbjStartDate()));
 		sql  = sql.replace("#{zbj_end_date}",String.valueOf(t.getZbjEndDate()));
 		sql  = sql.replace("#{lx_state}",String.valueOf(t.getLxState()));
 		sql  = sql.replace("#{bg_contract_no}",String.valueOf(t.getBgContractNo()));
 		sql  = sql.replace("#{xq_contract_no}",String.valueOf(t.getXqContractNo()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}
}