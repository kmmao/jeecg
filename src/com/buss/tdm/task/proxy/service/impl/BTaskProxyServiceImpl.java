package com.buss.tdm.task.proxy.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.web.system.pojo.base.TSType;
import org.jeecgframework.web.system.pojo.base.TSTypegroup;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.buss.activiti.entity.WorkFlowCateEntity;
import com.buss.activiti.service.IWorkFlowService;
import com.buss.tdm.task.proxy.entity.BTaskProxyEntity;
import com.buss.tdm.task.proxy.service.BTaskProxyServiceI;
import com.buss.tdm.task.proxydata.entity.BTaskProxyDataEntity;
import com.buss.tdm.task.proxyproduce.entity.BTaskProxyProduceEntity;

@Service("bTaskProxyService")
@Transactional
public class BTaskProxyServiceImpl extends CommonServiceImpl implements BTaskProxyServiceI
{
  @Autowired
  private IWorkFlowService workFlowService;
  
  public <T> void delete(T entity)
  {
    super.delete(entity);
    
    doDelSql((BTaskProxyEntity)entity);
  }
  
  public void setDirct(BTaskProxyEntity bTaskProxy)
  {
    String hql0 = "from TSTypegroup where typegroupcode = ?";
    List<TSTypegroup> listBillStatus = findHql(hql0, new Object[] { "billstatus" });
    List<TSTypegroup> listTaskStatus = findHql(hql0, new Object[] { "taskstatus" });
    String billStatusCode = ((TSTypegroup)listBillStatus.get(0)).getId();
    String taskStatusCode = ((TSTypegroup)listTaskStatus.get(0)).getId();
    
    String hql1 = "from TSType where typegroupid = ?";
    List<TSType> billStatus = findHql(hql1, new Object[] { billStatusCode });
    List<TSType> taskStatus = findHql(hql1, new Object[] { taskStatusCode });
    for (TSType t : billStatus) {
      if (t.getTypename().equals("待审批")) {
        bTaskProxy.setBillStatus(t.getTypecode());
      }
    }
    for (TSType t : taskStatus) {
      if (t.getTypename().equals("准备")) {
        bTaskProxy.setTaskStatus(t.getTypecode());
      }
    }
  }
  
  public void addMain(BTaskProxyEntity bTaskProxy, List<BTaskProxyProduceEntity> bTaskProxyProduceList)
  {
    if (!StringUtil.isEmpty(bTaskProxy.getSystemId())) {
      for (BTaskProxyProduceEntity b : bTaskProxyProduceList) {
        b.setSystemId(bTaskProxy.getSystemId());
      }
    }
    if (!StringUtil.isEmpty(bTaskProxy.getProduceId())) {
      for (BTaskProxyProduceEntity b : bTaskProxyProduceList) {
        b.setProduceId(bTaskProxy.getProduceId());
      }
    }
    setDirct(bTaskProxy);
    

    save(bTaskProxy);
    for (BTaskProxyProduceEntity bTaskProxyProduce : bTaskProxyProduceList)
    {
      bTaskProxyProduce.setProxyId(bTaskProxy.getId());
      save(bTaskProxyProduce);
    }
    doAddSql(bTaskProxy);
  }
  
  public void updateMain(BTaskProxyEntity bTaskProxy, List<BTaskProxyProduceEntity> bTaskProxyProduceList)
  {
    saveOrUpdate(bTaskProxy);
    

    Object id0 = bTaskProxy.getId();
    

    String hql0 = "from BTaskProxyProduceEntity where 1 = 1 AND pROXY_ID = ? ";
    List<BTaskProxyProduceEntity> bTaskProxyProduceOldList = findHql(hql0, new Object[] { id0 });
    if ((bTaskProxyProduceList == null) || (bTaskProxyProduceList.size() == 0))
    {
      deleteAllEntitie(bTaskProxyProduceOldList);
      return;
    }
    if ((bTaskProxyProduceList != null) && (bTaskProxyProduceList.size() > 0))
    {
      for (BTaskProxyProduceEntity oldE : bTaskProxyProduceOldList)
      {
        boolean isUpdate = false;
        for (BTaskProxyProduceEntity sendE : bTaskProxyProduceList) {
          if (oldE.getId().equals(sendE.getId()))
          {
            String[] proId = sendE.getProduceId().split(",");
            sendE.setProduceId(proId[0]);
            try
            {
              MyBeanUtils.copyBeanNotNull2Bean(sendE, oldE);
              saveOrUpdate(oldE);
            }
            catch (Exception e)
            {
              e.printStackTrace();
              throw new BusinessException(e.getMessage());
            }
            isUpdate = true;
            break;
          }
        }
        if (!isUpdate) {
          super.delete(oldE);
        }
      }
      for (BTaskProxyProduceEntity bTaskProxyProduce : bTaskProxyProduceList) {
        if (oConvertUtils.isEmpty(bTaskProxyProduce.getId()))
        {
          bTaskProxyProduce.setProxyId(bTaskProxy.getId());
          save(bTaskProxyProduce);
        }
      }
    }
    doUpdateSql(bTaskProxy);
  }
  
  public void delMain(BTaskProxyEntity bTaskProxy)
  {
    delete(bTaskProxy);
    

    Object id0 = bTaskProxy.getId();
    

    String hql0 = "from BTaskProxyProduceEntity where 1 = 1 AND pROXY_ID = ? ";
    List<BTaskProxyProduceEntity> bTaskProxyProduceOldList = findHql(hql0, new Object[] { id0 });
    deleteAllEntitie(bTaskProxyProduceOldList);
    
    String hql1 = "from BTaskProxyDataEntity where 1 = 1 and proxy_id = ?";
    List<BTaskProxyDataEntity> bTaskProxyDataEntities = findHql(hql1, new Object[] { id0 });
    deleteAllEntitie(bTaskProxyDataEntities);
  }
  
  public boolean doAddSql(BTaskProxyEntity t)
  {
    return true;
  }
  
  public boolean doUpdateSql(BTaskProxyEntity t)
  {
    return true;
  }
  
  public boolean doDelSql(BTaskProxyEntity t)
  {
    return true;
  }
  
  public String replaceVal(String sql, BTaskProxyEntity t)
  {
    sql = sql.replace("#{id}", String.valueOf(t.getId()));
    sql = sql.replace("#{bill_no}", String.valueOf(t.getBillNo()));
    sql = sql.replace("#{task_source}", String.valueOf(t.getTaskSource()));
    sql = sql.replace("#{task_level}", String.valueOf(t.getTaskLevel()));
    sql = sql.replace("#{produce_id}", String.valueOf(t.getProduceId()));
    sql = sql.replace("#{system_id}", String.valueOf(t.getSystemId()));
    sql = sql.replace("#{task_type}", String.valueOf(t.getTaskType()));
    sql = sql.replace("#{workflow_id}", String.valueOf(t.getWorkflowId()));
    sql = sql.replace("#{proxy_unit}", String.valueOf(t.getProxyUnit()));
    sql = sql.replace("#{test_unit}", String.valueOf(t.getTestUnit()));
    sql = sql.replace("#{user_id}", String.valueOf(t.getUserId()));
    sql = sql.replace("#{bill_status}", String.valueOf(t.getBillStatus()));
    sql = sql.replace("#{task_status}", String.valueOf(t.getTaskStatus()));
    sql = sql.replace("#{begain_data}", String.valueOf(t.getBegainData()));
    sql = sql.replace("#{end_data}", String.valueOf(t.getEndData()));
    sql = sql.replace("#{note}", String.valueOf(t.getNote()));
    sql = sql.replace("#{create_date}", String.valueOf(t.getCreateDate()));
    sql = sql.replace("#{create_by}", String.valueOf(t.getCreateBy()));
    sql = sql.replace("#{audit_by}", String.valueOf(t.getAuditBy()));
    sql = sql.replace("#{audit_note}", String.valueOf(t.getAuditNote()));
    sql = sql.replace("#{audit_date}", String.valueOf(t.getAuditDate()));
    sql = sql.replace("#{update_date}", String.valueOf(t.getUpdateDate()));
    sql = sql.replace("#{update_by}", String.valueOf(t.getUpdateBy()));
    sql = sql.replace("#{depart_id}", String.valueOf(t.getDepartId()));
    sql = sql.replace("#{UUID}", UUID.randomUUID().toString());
    return sql;
  }
  
  public void updateBillStatus(WorkFlowCateEntity entity, String billStatus)
  {
//    entity.setBillStatus(billStatus);
    saveOrUpdate(entity);
  }
  
  public void approve(WorkFlowCateEntity entity){
//    if (!"1".equals(entity.getBillStatus())) {
//      return;
//    }
//    if (StringUtil.isEmpty(entity.getWorkflowId())) {
//      approveCommon(entity);
//    } else {
//      approveWithWorkFlow(entity);
//    }
  }
  
  public void approveWithWorkFlow(WorkFlowCateEntity entity) {
    try{
      String controllerName = "workFlowCateController";
//      this.workFlowService.startWorkFlowWithProcessId(controllerName, entity, entity.getId(), entity.getWorkflowId());
    }catch (Exception e){
      throw new BusinessException(e.getMessage());
    }
    updateBillStatus(entity, "2");
  }
  
  public void approveCommon(WorkFlowCateEntity entity)
  {
    TSUser tsUser = ResourceUtil.getSessionUserName();
//    entity.setAuditBy(tsUser.getId());
//    entity.setAuditDate(new Date());
    
    updateBillStatus(entity, "3");
  }
  
  public void unApprove(BTaskProxyEntity entity)
  {
    if (!"3".equals(entity.getBillStatus())) {
      return;
    }
    StringBuffer errmsg = new StringBuffer();
    if (!ifHasAfterBill(entity, errmsg)) {
      throw new BusinessException("已生成下游单据，" + errmsg.toString());
    }
    entity.setAuditBy(null);
    entity.setAuditDate(null);
    
//    updateBillStatus(entity, "1");
  }
  
  private boolean ifHasAfterBill(BTaskProxyEntity entity, StringBuffer errmsg)
  {
    StringBuffer sqlBuffer = new StringBuffer();
    sqlBuffer.append("SELECT DISTINCT bill_no,'任务项目管理' AS billType,1 as num\nFROM b_task_project WHERE PROXY_ID='" + 
      entity.getId() + "'\n" + "UNION ALL\n" + "SELECT DISTINCT bill_no,'任务报告' AS billType,1 as num\n" + 
      "FROM b_task_report WHERE PROXY_ID='" + entity.getId() + "'");
    List<Map<String, Object>> list = findForJdbc(sqlBuffer.toString(), new Object[0]);
    if ((list == null) || (list.size() == 0)) {
      return true;
    }
    boolean flag = true;
    for (Map<String, Object> map : list) {
      if (map.get("num") != null)
      {
        errmsg.append((String)map.get("billType") + 
          "\n");
        flag = false;
      }
    }
    return flag;
  }
  
  public void updateProxyStatus(String proxyId, String billStatus)
  {
    BTaskProxyEntity entity = (BTaskProxyEntity)getEntity(BTaskProxyEntity.class, proxyId);
    if ("3".equals(billStatus)) {
      entity.setRealEndData(new Date());
    }
    if ("2".equals(billStatus))
    {
      if (entity.getRealBegainData() != null) {
        return;
      }
      entity.setRealBegainData(new Date());
    }
    entity.setTaskStatus(billStatus);
    save(entity);
  }
}
