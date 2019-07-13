package cn.com.linkwide.cont.negotiation.conofferbill.service.impl;
import cn.com.linkwide.cont.negotiation.conofferbill.service.ConOfferBillServiceI;

import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

import cn.com.linkwide.cont.coninformation.entity.ConInformationEntity;
import cn.com.linkwide.cont.negotiation.conofferbill.entity.ConOfferBillEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.buss.activiti.entity.WorkflowBean;
import com.buss.activiti.service.IWorkFlowService;
import com.buss.activiti.util.BILL_STATE;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.io.Serializable;
import org.jeecgframework.core.util.ApplicationContextUtil;
import org.jeecgframework.core.util.MyClassLoader;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.web.cgform.enhance.CgformEnhanceJavaInter;
import org.jeecgframework.web.system.pojo.base.TSUser;

@Service("conOfferBillService")
@Transactional
public class ConOfferBillServiceImpl extends CommonServiceImpl implements ConOfferBillServiceI {
	@Autowired
	private IWorkFlowService workFlowService;
	
 	public void delete(ConOfferBillEntity entity) throws Exception{
 		super.delete(entity);
 		//执行删除操作增强业务
		this.doDelBus(entity);
 	}
 	
 	public Serializable save(ConOfferBillEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		//执行新增操作增强业务
 		this.doAddBus(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(ConOfferBillEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 		//执行更新操作增强业务
 		this.doUpdateBus(entity);
 	}
 	
 	/**
	 * 新增操作增强业务
	 * @param t
	 * @return
	 */
	private void doAddBus(ConOfferBillEntity t) throws Exception{
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
	private void doUpdateBus(ConOfferBillEntity t) throws Exception{
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
	private void doDelBus(ConOfferBillEntity t) throws Exception{
	    //-----------------sql增强 start----------------------------
	 	//-----------------sql增强 end------------------------------
	 	
	 	//-----------------java增强 start---------------------------
	 	//-----------------java增强 end-----------------------------
 	}
 	
 	private Map<String,Object> populationMap(ConOfferBillEntity t){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", t.getId());
		map.put("create_name", t.getCreateName());
		map.put("create_by", t.getCreateBy());
		map.put("create_date", t.getCreateDate());
		map.put("update_name", t.getUpdateName());
		map.put("update_by", t.getUpdateBy());
		map.put("update_date", t.getUpdateDate());
		map.put("sys_org_code", t.getSysOrgCode());
		map.put("sys_company_code", t.getSysCompanyCode());
		map.put("bpm_status", t.getBpmStatus());
		map.put("dept_code", t.getDeptCode());
		map.put("dept_name", t.getDeptName());
		map.put("apply_date", t.getApplyDate());
		map.put("equ_code", t.getEquCode());
		map.put("equ_name", t.getEquName());
		map.put("equ_card", t.getEquCard());
		map.put("spec_typ", t.getSpecTyp());
		map.put("apply_code", t.getApplyCode());
		map.put("warranty_years", t.getWarrantyYears());
		map.put("warranty_type", t.getWarrantyType());
		map.put("offer_price", t.getOfferPrice());
		map.put("service_conten", t.getServiceConten());
		map.put("cost_ratio", t.getCostRatio());
		map.put("bill_state", t.getBillState());
		map.put("apply_id", t.getApplyId());
		map.put("offer_money", t.getOfferMoney());
		map.put("confirm_date", t.getConfirmDate());
		map.put("con_state", t.getConState());
		map.put("extend1", t.getExtend1());
		map.put("extend2", t.getExtend2());
		map.put("extend3", t.getExtend3());
		map.put("extend4", t.getExtend4());
		map.put("extend5", t.getExtend5());
		map.put("extend6", t.getExtend6());
		map.put("extend7", t.getExtend7());
		map.put("extend8", t.getExtend8());
		return map;
	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @param t
	 * @return
	 */
 	public String replaceVal(String sql,ConOfferBillEntity t){
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
 		sql  = sql.replace("#{dept_code}",String.valueOf(t.getDeptCode()));
 		sql  = sql.replace("#{dept_name}",String.valueOf(t.getDeptName()));
 		sql  = sql.replace("#{apply_date}",String.valueOf(t.getApplyDate()));
 		sql  = sql.replace("#{equ_code}",String.valueOf(t.getEquCode()));
 		sql  = sql.replace("#{equ_name}",String.valueOf(t.getEquName()));
 		sql  = sql.replace("#{equ_card}",String.valueOf(t.getEquCard()));
 		sql  = sql.replace("#{spec_typ}",String.valueOf(t.getSpecTyp()));
 		sql  = sql.replace("#{apply_code}",String.valueOf(t.getApplyCode()));
 		sql  = sql.replace("#{warranty_years}",String.valueOf(t.getWarrantyYears()));
 		sql  = sql.replace("#{warranty_type}",String.valueOf(t.getWarrantyType()));
 		sql  = sql.replace("#{offer_price}",String.valueOf(t.getOfferPrice()));
 		sql  = sql.replace("#{service_conten}",String.valueOf(t.getServiceConten()));
 		sql  = sql.replace("#{cost_ratio}",String.valueOf(t.getCostRatio()));
 		sql  = sql.replace("#{bill_state}",String.valueOf(t.getBillState()));
 		sql  = sql.replace("#{apply_id}",String.valueOf(t.getApplyId()));
 		sql  = sql.replace("#{offer_money}",String.valueOf(t.getOfferMoney()));
 		sql  = sql.replace("#{confirm_date}",String.valueOf(t.getConfirmDate()));
 		sql  = sql.replace("#{con_state}",String.valueOf(t.getConState()));
 		sql  = sql.replace("#{extend1}",String.valueOf(t.getExtend1()));
 		sql  = sql.replace("#{extend2}",String.valueOf(t.getExtend2()));
 		sql  = sql.replace("#{extend3}",String.valueOf(t.getExtend3()));
 		sql  = sql.replace("#{extend4}",String.valueOf(t.getExtend4()));
 		sql  = sql.replace("#{extend5}",String.valueOf(t.getExtend5()));
 		sql  = sql.replace("#{extend6}",String.valueOf(t.getExtend6()));
 		sql  = sql.replace("#{extend7}",String.valueOf(t.getExtend7()));
 		sql  = sql.replace("#{extend8}",String.valueOf(t.getExtend8()));
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
					javaInter.execute("con_offer_bill",data);
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("执行JAVA增强出现异常！");
			} 
		}
 	}
 	
 	
 	
 	/**
 	 * 审批
 	 */
	@Override
	public void approve(ConOfferBillEntity t,String actReModelCode) {
		String budgState = t.getConfermState();
		if(StringUtil.isEmpty(budgState)){
			budgState="新建";
		}
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
	public void approveWithWorkFlow(ConOfferBillEntity entity,String actReModelCode) {
 	    try{
 	      //启动流程
 	      String controllerName = "conOfferBillController";
 	      Map map= new HashMap();
 	      map.put("tableName", "con_offer_bill"); //单据表名
 	      map.put("stateName", "conferm_state");//单据状态名称
 	      map.put("isConSign", "false");//是否合同会签
 	   //   map.put("isFile", "false");
 	    //  map.put("fileUrl", "conOfferBillController.do?goUpdateconfirm");

 	     
 	      this.workFlowService.startWorkFlowWithProcessId(controllerName, entity, entity.getId(), entity.getWorkflowId(),map);
 	      //完成一个任务
 	    }catch (Exception e){
 	      throw new BusinessException(e.getMessage());
 	    }
 	    //修改审批状态  为待审核状态
 	    try {
			updateBudgState(entity, BILL_STATE.TO_BE_CHECK);
		} catch (Exception e) {
			e.printStackTrace();
		}
 	 }
 	
	 /**
	  * 审批
	  * @param entity
	  */
	 public void approveCommon(ConOfferBillEntity entity) {
	    TSUser tsUser = ResourceUtil.getSessionUserName();
	  //  entity.setEffectEmp(tsUser.getUserName()); //审批人
	   try {
		   WorkflowBean workflowBean =get(WorkflowBean.class, entity.getWorkflowId());
		   this.workFlowService.completeTakeByWorkflowBean(workflowBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
	  }
	 /**
	  * 修改审批状态
	  * @param entity
	  * @param budgState
	 * @throws Exception 
	  */
	 public void updateBudgState(ConOfferBillEntity entity,String budgState) throws Exception{
		 if(StringUtil.isNotEmpty(budgState)){
			 entity.setConfermState(budgState);
			 saveOrUpdate(entity);
		 }
	 }	
 	
 	
 	
 	
}