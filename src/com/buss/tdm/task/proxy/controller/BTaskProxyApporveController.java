package com.buss.tdm.task.proxy.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.web.system.pojo.base.TSBaseUser;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.buss.activiti.entity.WorkFlowCateEntity;
import com.buss.tdm.res.product.entity.BResProductEntity;
import com.buss.tdm.task.proxy.entity.BTaskProxyEntity;
import com.buss.tdm.task.proxy.service.BTaskProxyServiceI;

@Controller
@RequestMapping({"/bTaskProxyApporveController"})
public class BTaskProxyApporveController extends BaseController{
  private static final Logger logger = Logger.getLogger(BTaskProxyApporveController.class);
  @Autowired
  private BTaskProxyServiceI bTaskProxyService;
  @Autowired
  private SystemService systemService;
  
  @RequestMapping(params={"goApprove"})
  public ModelAndView goApprove(String id, HttpServletRequest req){
    BTaskProxyEntity bTaskProxy = (BTaskProxyEntity)this.systemService.getEntity(BTaskProxyEntity.class, id);
    if (bTaskProxy.getProduceId() != null)
    {
      BResProductEntity pro = (BResProductEntity)this.bTaskProxyService.getEntity(BResProductEntity.class, bTaskProxy.getProduceId());
      bTaskProxy.setProduceName(pro.getProductName());
      bTaskProxy.setProduceModel(pro.getProductModel());
    }
    if (bTaskProxy.getUserId() != null)
    {
      TSBaseUser user = (TSBaseUser)this.bTaskProxyService.getEntity(TSBaseUser.class, bTaskProxy.getUserId());
      bTaskProxy.setUserName(user.getUserName());
    }
    req.setAttribute("bTaskProxyPage", bTaskProxy);
    return new ModelAndView("com/buss/tdm/task/proxy/bTaskProxy-approve");
  }
  
  @RequestMapping(params={"doApprove"})
  @ResponseBody
  public AjaxJson doApprove(String ids, HttpServletRequest request){
    AjaxJson j = new AjaxJson();
    String message = "审核成功";
    try {
      for (String id : ids.split(",")){
    	  WorkFlowCateEntity entity = (WorkFlowCateEntity)this.systemService.getEntity(WorkFlowCateEntity.class, id);
//        entity.setWorkflowId(request.getParameter("workflowId"));
        this.bTaskProxyService.approve(entity);
      }
    }catch (Exception e){
      e.printStackTrace();
      message = "审核失败：" + e.getMessage();
      j.setSuccess(false);
    }
    this.systemService.addLog(message, Globals.Log_Type_APPROVE, Globals.Log_Leavel_INFO);
    j.setMsg(message);
    return j;
  }
  
  @RequestMapping(params={"doUnApprove"})
  @ResponseBody
  public AjaxJson doUnApprove(String ids, HttpServletRequest request)
  {
    AjaxJson j = new AjaxJson();
    String message = "任务委托单弃审成功";
    try
    {
      for (String id : ids.split(","))
      {
        BTaskProxyEntity entity = (BTaskProxyEntity)this.systemService.getEntity(BTaskProxyEntity.class, id);
        this.bTaskProxyService.unApprove(entity);
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
      message = "任务委托单弃审失败：" + e.getMessage();
      j.setSuccess(false);
    }
    this.systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
    j.setMsg(message);
    return j;
  }
}
