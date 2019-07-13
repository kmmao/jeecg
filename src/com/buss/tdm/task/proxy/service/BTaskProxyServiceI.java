package com.buss.tdm.task.proxy.service;

import java.util.List;

import org.jeecgframework.core.common.service.CommonService;

import com.buss.activiti.entity.WorkFlowCateEntity;
import com.buss.tdm.task.proxy.entity.BTaskProxyEntity;
import com.buss.tdm.task.proxyproduce.entity.BTaskProxyProduceEntity;

public abstract interface BTaskProxyServiceI
  extends CommonService
{
  public abstract <T> void delete(T paramT);
  
  public abstract void addMain(BTaskProxyEntity paramBTaskProxyEntity, List<BTaskProxyProduceEntity> paramList);
  
  public abstract void updateMain(BTaskProxyEntity paramBTaskProxyEntity, List<BTaskProxyProduceEntity> paramList);
  
  public abstract void delMain(BTaskProxyEntity paramBTaskProxyEntity);
  
  public abstract boolean doAddSql(BTaskProxyEntity paramBTaskProxyEntity);
  
  public abstract boolean doUpdateSql(BTaskProxyEntity paramBTaskProxyEntity);
  
  public abstract boolean doDelSql(BTaskProxyEntity paramBTaskProxyEntity);
  
  public abstract void approve(WorkFlowCateEntity entity);
  
  public abstract void unApprove(BTaskProxyEntity paramBTaskProxyEntity);
  
  public abstract void updateProxyStatus(String paramString1, String paramString2);
}
