package cn.com.linkwide.cont.coninformation.service.impl;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.buss.activiti.entity.WorkflowBean;
import com.buss.activiti.service.IWorkFlowService;
import com.buss.activiti.util.BILL_STATE;

import cn.com.linkwide.cont.coninformation.entity.ConInformationEntity;
import cn.com.linkwide.cont.coninformation.service.ConInformationServiceI;
import cn.com.linkwide.cont.coninformationdetial.entity.ConMarkEntity;
import cn.com.linkwide.cont.conmemorabilia.entity.ConMemorabiliaEntity;
import cn.com.linkwide.cont.contbank.entity.ContBankEntity;
import cn.com.linkwide.cont.payplan.entity.PayPlanEntity;


@Service("conInformationService")
@Transactional
public class ConInformationServiceImpl extends CommonServiceImpl implements ConInformationServiceI {
	@Autowired
	private IWorkFlowService workFlowService;
 	public <T> void delete(T entity) {
 		super.delete(entity);
 		//执行删除操作配置的sql增强
		this.doDelSql((ConInformationEntity)entity);
 	}
	
	public void addMain(ConInformationEntity conInformation,
	        List<ConMarkEntity> conMarkList,List<PayPlanEntity> payPlanList,List<ConMemorabiliaEntity> conMemorabiliaList,List<ContBankEntity> contBankList){
			//保存主信息
			this.save(conInformation);
			
			if(("".equals(conInformation.getConNumnerZ())||null==conInformation.getConNumnerZ()||"0".equals(conInformation.getConNumnerZ()))&& !"1".equals(conInformation.getIsBg())){
					conInformation.setRenewId(conInformation.getId());
					this.saveOrUpdate(conInformation);
				 
			}
			
			
			/**保存-合同标的*/
			for(ConMarkEntity conMark:conMarkList){
				//外键设置
			
				conMark.setConMainId(conInformation.getId());
				this.save(conMark);
			}
			
			this.saveOrUpdate(conInformation);
			
			/**保存-付款计划*/
			for(PayPlanEntity payPlan:payPlanList){
				//外键设置
				payPlan.setConId(conInformation.getId());
				this.save(payPlan);
			}
			/**保存-大事记记录*/
			for(ConMemorabiliaEntity conMemorabilia:conMemorabiliaList){
				//外键设置
				conMemorabilia.setConId(conInformation.getId());
				this.save(conMemorabilia);
			}
			/**保存-银行保函*/
			for(ContBankEntity contBank:contBankList){
				//外键设置
				contBank.setConId(conInformation.getId());
				this.save(contBank);
			}
			//执行新增操作配置的sql增强
 			this.doAddSql(conInformation);
	}
	
	
	
	
	public void addMain(ConInformationEntity conInformation,
	        List<ConMarkEntity> conMarkList){
			//保存主信息
			this.save(conInformation);
			
			/**保存-合同标的*/
			for(ConMarkEntity conMark:conMarkList){
				//外键设置
			
				conMark.setConMainId(conInformation.getId());
				this.save(conMark);
			}
			
			this.saveOrUpdate(conInformation);
			
		
			//执行新增操作配置的sql增强
 			this.doAddSql(conInformation);
	}

	
	public void updateMain(ConInformationEntity conInformation,
	        List<ConMarkEntity> conMarkList,List<PayPlanEntity> payPlanList,List<ConMemorabiliaEntity> conMemorabiliaList,List<ContBankEntity> contBankList) {
		//保存主表信息
		if(StringUtil.isNotEmpty(conInformation.getId())){
			try {
				ConInformationEntity temp = findUniqueByProperty(ConInformationEntity.class, "id", conInformation.getId());
				MyBeanUtils.copyBeanNotNull2Bean(conInformation, temp);
				this.saveOrUpdate(temp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			this.saveOrUpdate(conInformation);
		}
		//===================================================================================
		//获取参数
		Object id0 = conInformation.getId();
		//===================================================================================
		//1.查询出数据库的明细数据-合同标的
	    String hql0 = "from ConMarkEntity where 1 = 1 AND cON_MAIN_ID = ? ";
	    List<ConMarkEntity> conMarkOldList = this.findHql(hql0,id0);
	  //1.查询出数据库的明细数据-付款计划
	    String hql1 = "from PayPlanEntity where 1 = 1 AND cON_ID = ? ";
	    List<PayPlanEntity> payPlanOldList = this.findHql(hql1,id0);
	    //1.查询出数据库的明细数据-大事记记录
	    String hql2 = "from ConMemorabiliaEntity where 1 = 1 AND cON_ID = ? ";
	    List<ConMemorabiliaEntity> conMemorabiliaOldList = this.findHql(hql2,id0);
	    //1.查询出数据库的明细数据-银行保函
	    String hql3 = "from ContBankEntity where 1 = 1 AND cON_ID = ? ";
	    List<ContBankEntity> contBankOldList = this.findHql(hql3,id0);
		//2.筛选更新明细数据-合同标的
		if(conMarkList!=null&&conMarkList.size()>0){
		
		for(ConMarkEntity oldE:conMarkOldList){
			boolean isUpdate = false;
				for(ConMarkEntity sendE:conMarkList){
					//需要更新的明细数据-合同标的
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
		    		//如果数据库存在的明细，前台没有传递过来则是删除-合同标的
		    		super.delete(oldE);
		    		
	    		}
	    		
	    		try {
					ConInformationEntity temp = findUniqueByProperty(ConInformationEntity.class, "id", conInformation.getId());
					MyBeanUtils.copyBeanNotNull2Bean(conInformation, temp);
					
					this.saveOrUpdate(temp);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			//3.持久化新增的数据-合同标的
			for(ConMarkEntity conMark:conMarkList){
				if(oConvertUtils.isEmpty(conMark.getId())){
					//外键设置
					conMark.setConMainId(conInformation.getId());
					this.save(conMark);
				}
			}
		}
		
		//2.筛选更新明细数据-付款计划
				if(payPlanList!=null&&payPlanList.size()>0){
				for(PayPlanEntity oldE:payPlanOldList){
					boolean isUpdate = false;
						for(PayPlanEntity sendE:payPlanList){
							//需要更新的明细数据-合同付款计划
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
				    		//如果数据库存在的明细，前台没有传递过来则是删除-合同付款计划
				    		super.delete(oldE);
			    		}
			    		
					}
					//3.持久化新增的数据-合同付款计划
					for(PayPlanEntity payPlan:payPlanList){
						if(oConvertUtils.isEmpty(payPlan.getId())){
							//外键设置
							payPlan.setConId(conInformation.getId());
							this.save(payPlan);
						}
					}
				}
		
				//2.筛选更新明细数据-大事记记录
				if(conMemorabiliaList!=null&&conMemorabiliaList.size()>0){
				for(ConMemorabiliaEntity oldE:conMemorabiliaOldList){
					boolean isUpdate = false;
						for(ConMemorabiliaEntity sendE:conMemorabiliaList){
							//需要更新的明细数据-大事记记录
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
				    		//如果数据库存在的明细，前台没有传递过来则是删除-合同大事记记录
				    		super.delete(oldE);
			    		}
			    		
					}
					//3.持久化新增的数据-合同大事记记录
					for(ConMemorabiliaEntity conMemorabilia:conMemorabiliaList){
						if(oConvertUtils.isEmpty(conMemorabilia.getId())){
							//外键设置
							conMemorabilia.setConId(conInformation.getId());
							this.save(conMemorabilia);
						}
					}
				}
				
				
				
				//2.筛选更新明细数据-银行保函
				if(contBankList!=null&&contBankList.size()>0){
				for(ContBankEntity oldE:contBankOldList){
					boolean isUpdate = false;
						for(ContBankEntity sendE:contBankList){
							//需要更新的明细数据-银行保函
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
				    		//如果数据库存在的明细，前台没有传递过来则是删除-银行保函
				    		super.delete(oldE);
			    		}
			    		
					}
					//3.持久化新增的数据-银行保函
					for(ContBankEntity contBank:contBankList){
						if(oConvertUtils.isEmpty(contBank.getId())){
							//外键设置
							contBank.setConId(conInformation.getId());
							this.save(contBank);
						}
					}
				}
		
		
		
		//执行更新操作配置的sql增强
 		this.doUpdateSql(conInformation);
	}
	
	
	
	

	public void updateMain(ConInformationEntity conInformation,
	        List<ConMarkEntity> conMarkList) {
		//保存主表信息
		if(StringUtil.isNotEmpty(conInformation.getId())){
			try {
				ConInformationEntity temp = findUniqueByProperty(ConInformationEntity.class, "id", conInformation.getId());
				MyBeanUtils.copyBeanNotNull2Bean(conInformation, temp);
				this.saveOrUpdate(temp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			this.saveOrUpdate(conInformation);
		}
		//===================================================================================
		//获取参数
		Object id0 = conInformation.getId();
		//===================================================================================
		//1.查询出数据库的明细数据-合同标的
	    String hql0 = "from ConMarkEntity where 1 = 1 AND cON_MAIN_ID = ? ";
	    List<ConMarkEntity> conMarkOldList = this.findHql(hql0,id0);
	
		//2.筛选更新明细数据-合同标的
		if(conMarkList!=null&&conMarkList.size()>0){
		
		for(ConMarkEntity oldE:conMarkOldList){
			boolean isUpdate = false;
				for(ConMarkEntity sendE:conMarkList){
					//需要更新的明细数据-合同标的
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
		    		//如果数据库存在的明细，前台没有传递过来则是删除-合同标的
		    		super.delete(oldE);
		    		
	    		}
	    		
	    		try {
					ConInformationEntity temp = findUniqueByProperty(ConInformationEntity.class, "id", conInformation.getId());
					MyBeanUtils.copyBeanNotNull2Bean(conInformation, temp);
					
					this.saveOrUpdate(temp);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			//3.持久化新增的数据-合同标的
			for(ConMarkEntity conMark:conMarkList){
				if(oConvertUtils.isEmpty(conMark.getId())){
					//外键设置
					conMark.setConMainId(conInformation.getId());
					this.save(conMark);
				}
			}
		}
				
				
			
		
		
		//执行更新操作配置的sql增强
 		this.doUpdateSql(conInformation);
	}


	
	public void delMain(ConInformationEntity conInformation) {
		//删除主表信息
		this.delete(conInformation);
		//===================================================================================
		//获取参数
		Object id0 = conInformation.getId();
		//===================================================================================
		//删除-合同标的
	    String hql0 = "from ConMarkEntity where 1 = 1 AND cON_MAIN_ID = ? ";
	    List<ConMarkEntity> conMarkOldList = this.findHql(hql0,id0);
		this.deleteAllEntitie(conMarkOldList);
		//删除-合同付款计划
	    String hql1 = "from PayPlanEntity where 1 = 1 AND cON_ID = ? ";
	    List<PayPlanEntity> payPlanOldList = this.findHql(hql1,id0);
		this.deleteAllEntitie(payPlanOldList);
		//删除-合同大事记记录
	    String hql2 = "from ConMemorabiliaEntity where 1 = 1 AND cON_ID = ? ";
	    List<ConMemorabiliaEntity> conMemorabiliaOldList = this.findHql(hql2,id0);
		this.deleteAllEntitie(conMemorabiliaOldList);
		//删除-合同银行保函
	    String hql3 = "from ContBankEntity where 1 = 1 AND cON_ID = ? ";
	    List<ContBankEntity> contBankOldList = this.findHql(hql3,id0);
		this.deleteAllEntitie(contBankOldList);
	}
	
 	
 	/**
	 * 默认按钮-sql增强-新增操作
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(ConInformationEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-更新操作
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(ConInformationEntity t){
	 	return true;
 	}
 	/**
	 * 默认按钮-sql增强-删除操作
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(ConInformationEntity t){
	 	return true;
 	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @return
	 */
 	public String replaceVal(String sql,ConInformationEntity t){
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
 		sql  = sql.replace("#{con_type}",String.valueOf(t.getConType()));
 		sql  = sql.replace("#{con_war_id}",String.valueOf(t.getConWarId()));
 		sql  = sql.replace("#{con_state}",String.valueOf(t.getConState()));
 		sql  = sql.replace("#{con_number}",String.valueOf(t.getConNumber()));
 		sql  = sql.replace("#{con_no}",String.valueOf(t.getConNo()));
 		sql  = sql.replace("#{signed_date}",String.valueOf(t.getSignedDate()));
 		sql  = sql.replace("#{start_date}",String.valueOf(t.getStartDate()));
 		sql  = sql.replace("#{end_date}",String.valueOf(t.getEndDate()));
 		sql  = sql.replace("#{other_compy}",String.valueOf(t.getOtherCompy()));
 		sql  = sql.replace("#{other_emp}",String.valueOf(t.getOtherEmp()));
 		sql  = sql.replace("#{money_type}",String.valueOf(t.getMoneyType()));
 		sql  = sql.replace("#{dept_id}",String.valueOf(t.getDeptId()));
 		sql  = sql.replace("#{effect_emp}",String.valueOf(t.getEffectEmp()));
 		sql  = sql.replace("#{effect_date}",String.valueOf(t.getEffectDate()));
 		sql  = sql.replace("#{case_emp}",String.valueOf(t.getCaseEmp()));
 		sql  = sql.replace("#{case_date}",String.valueOf(t.getCaseDate()));
 		sql  = sql.replace("#{zbj_cycle}",String.valueOf(t.getZbjCycle()));
 		sql  = sql.replace("#{zbj_rate}",String.valueOf(t.getZbjRate()));
 		sql  = sql.replace("#{zbj_money}",String.valueOf(t.getZbjMoney()));
 		sql  = sql.replace("#{ven_id}",String.valueOf(t.getVenId()));
 		sql  = sql.replace("#{customer_id}",String.valueOf(t.getCustomerId()));
 		sql  = sql.replace("#{cgzb_id}",String.valueOf(t.getCgzbId()));
 		sql  = sql.replace("#{cgsq_id}",String.valueOf(t.getCgsqId()));
 		sql  = sql.replace("#{online_id}",String.valueOf(t.getOnlineId()));
 		sql  = sql.replace("#{acct_id}",String.valueOf(t.getAcctId()));
 		sql  = sql.replace("#{mate_id}",String.valueOf(t.getMateId()));
 		sql  = sql.replace("#{perstate}",String.valueOf(t.getPerstate()));
 		sql  = sql.replace("#{state_date1}",String.valueOf(t.getStateDate1()));
 		sql  = sql.replace("#{state_date2}",String.valueOf(t.getStateDate2()));
 		sql  = sql.replace("#{state_date3}",String.valueOf(t.getStateDate3()));
 		sql  = sql.replace("#{state_date4}",String.valueOf(t.getStateDate4()));
 		sql  = sql.replace("#{state_date5}",String.valueOf(t.getStateDate5()));
 		sql  = sql.replace("#{state_date6}",String.valueOf(t.getStateDate6()));
 		sql  = sql.replace("#{exec_con_no}",String.valueOf(t.getExecConNo()));
 		sql  = sql.replace("#{con_numner_z}",String.valueOf(t.getConNumnerZ()));
 		sql  = sql.replace("#{is_bg}",String.valueOf(t.getIsBg()));
 		sql  = sql.replace("#{renew_id}",String.valueOf(t.getRenewId()));
 		sql  = sql.replace("#{defout_case}",String.valueOf(t.getDefoutCase()));
 		sql  = sql.replace("#{is_defout}",String.valueOf(t.getIsDefout()));
 		sql  = sql.replace("#{fj}",String.valueOf(t.getFj()));
 		sql  = sql.replace("#{defout_date}",String.valueOf(t.getDefoutDate()));
 		sql  = sql.replace("#{conferm_state}",String.valueOf(t.getConfermState()));
 		sql  = sql.replace("#{conferm_date}",String.valueOf(t.getConfermDate()));
 		sql  = sql.replace("#{agree_id}",String.valueOf(t.getAgreeId()));
 		sql  = sql.replace("#{sign_id}",String.valueOf(t.getSignId()));
 		sql  = sql.replace("#{spl}",String.valueOf(t.getSpl()));
 		sql  = sql.replace("#{con_name}",String.valueOf(t.getConName()));
 		sql  = sql.replace("#{con_bz}",String.valueOf(t.getConBz()));
 		sql  = sql.replace("#{con_ms}",String.valueOf(t.getConMs()));
 		sql  = sql.replace("#{con_exec1}",String.valueOf(t.getConExec1()));
 		sql  = sql.replace("#{con_exec2}",String.valueOf(t.getConExec2()));
 		sql  = sql.replace("#{con_exec3}",String.valueOf(t.getConExec3()));
 		sql  = sql.replace("#{con_exec4}",String.valueOf(t.getConExec4()));
 		sql  = sql.replace("#{con_exec5}",String.valueOf(t.getConExec5()));
 		sql  = sql.replace("#{con_exec6}",String.valueOf(t.getConExec6()));
 		sql  = sql.replace("#{con_exec7}",String.valueOf(t.getConExec7()));
 		sql  = sql.replace("#{con_exec8}",String.valueOf(t.getConExec8()));
 		sql  = sql.replace("#{con_exec9}",String.valueOf(t.getConExec9()));
 		sql  = sql.replace("#{con_exec10}",String.valueOf(t.getConExec10()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}
 	
 	

 	/**
 	 * 审批
 	 */
	@Override
	public void approve(ConInformationEntity t,String actReModelCode) {
		String budgState = t.getConfermState();
		if(BILL_STATE.TO_BE_SUBMIT.equals(budgState) || BILL_STATE.REJECT.equals(budgState)){//新建或驳回状态
			approveWithWorkFlow(t,actReModelCode); //启动流程
		}else{
			approveCommon(t); //审批
		}
		
	}
	
	
	/**
	 * 启动流程
	 * @param entity
	 */
	public void approveWithWorkFlow(ConInformationEntity entity,String actReModelCode) {
 	    try{
 	      //启动流程
 	      String controllerName = "conInformationController";
 	      Map map= new HashMap();
 	      map.put("tableName", "con_information"); //单据表名
 	      map.put("stateName", "conferm_state");//单据状态名称
 	      map.put("isConSign", "true");//是否合同会签
 	      map.put("isFile", "true");
 	      map.put("fileUrl", "conInformationController.do?goUpdateconfirm");
 /*	      map.put("auditRemark", "audit_remark");//审批意见字段
 	      map.put("auditUser", "budg_audit");//审核人字段
*/ 	      /*条件流转需要传这两个参数*/
 	      if("基建合同审批".equals(actReModelCode)){
 	    	 map.put("conditionPara", "conName,isAssier,zrCode,isKyl"); //流转条件判断需要的字段
 	 	      map.put("conditionParaType", "String,String,String,String"); //流转条件判断需要的字段类型
 	      }
 	      else if("后勤合同审批".equals(actReModelCode)){
 	    	 map.put("conditionPara", "conName,isAssier,isSpice"); //流转条件判断需要的字段
 	 	      map.put("conditionParaType", "String,String,String"); //流转条件判断需要的字段类型
 	      }
 	      else if("信息合同审批".equals(actReModelCode)){
 	    	  
 	      }else{
 	    	 map.put("conditionPara", "conName,isAssier"); //流转条件判断需要的字段
 	 	      map.put("conditionParaType", "String,String"); //流转条件判断需要的字段类型
 	      }
 	   /*   if("采购合同审批".equals(actReModelCode)){
 	    	 map.put("conditionPara", "conName,isAssier"); //流转条件判断需要的字段
 	 	      map.put("conditionParaType", "String,String"); //流转条件判断需要的字段类型
 	      }
 	      if("消防合同审批".equals(actReModelCode)){
 	    	 map.put("conditionPara", "conName,isAssier"); //流转条件判断需要的字段
 	 	      map.put("conditionParaType", "String,String"); //流转条件判断需要的字段类型
 	      }
 	     
 	      if("安保合同审批".equals(actReModelCode)){
 	    	 map.put("conditionPara", "conName,isAssier"); //流转条件判断需要的字段
	 	      map.put("conditionParaType", "String,String"); //流转条件判断需要的字段类型
 	      }*/
 	     
 	      this.workFlowService.startWorkFlowWithProcessId(controllerName, entity, entity.getId(), entity.getWorkflowId(),map);
 	      //完成一个任务
// 	      WorkflowBean workflowBean =get(WorkflowBean.class, entity.getWorkflowId());
//		  this.workFlowService.completeTakeByWorkflowBean(workflowBean);
 	    }catch (Exception e){
 	      throw new BusinessException(e.getMessage());
 	    }
 	    //修改审批状态  为待审核状态
 	    updateBudgState(entity, BILL_STATE.TO_BE_CHECK);
 	 }
 	
	 /**
	  * 审批
	  * @param entity
	  */
	 public void approveCommon(ConInformationEntity entity) {
	    TSUser tsUser = ResourceUtil.getSessionUserName();
	    entity.setEffectEmp(tsUser.getUserName()); //审批人
	   try {
		   WorkflowBean workflowBean =get(WorkflowBean.class, entity.getWorkflowId());
		   this.workFlowService.completeTakeByWorkflowBean(workflowBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	    //updateBillStatus(entity, "3");
	  }
	 /**
	  * 修改审批状态
	  * @param entity
	  * @param budgState
	  */
	 public void updateBudgState(ConInformationEntity entity,String budgState){
		 if(StringUtil.isNotEmpty(budgState)){
			 entity.setConfermState(budgState);
			 entity.setConfermDate(new Date());
			 saveOrUpdate(entity);
		 }
	 }
 	
 	
}