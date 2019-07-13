package com.buss.tdm.approve;

import com.buss.activiti.entity.ActReModelEntity;
import com.buss.activiti.entity.ActReProcdefEntity;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping({"/actReProcdefController"})
public class ActReProcdefController extends BaseController
{
  private static final Logger logger = Logger.getLogger(ActReProcdefController.class);

  @Autowired
  private SystemService systemService;
  /**
   * 
   * @param request
   * @param actReModelCode
   * @return
   */
  @RequestMapping(params={"ifHasFlow"})
  @ResponseBody
  public AjaxJson ifHasFlow(HttpServletRequest request, String actReModelCode) { 
	    AjaxJson j = new AjaxJson();
	    String hql = "from ActReModelEntity where key=?";
	    List list = this.systemService.findHql(hql, new Object[] { actReModelCode });
	    if ((list == null) || (list.size() == 0)) {
	      j.setSuccess(false);
	    } else {
	      String hql_flow = "from ActReProcdefEntity where suspensionState='1' and category=?";
	      List proList = this.systemService.findHql(hql_flow, new Object[] { ((ActReModelEntity)list.get(0)).getCategory() });
	      if ((proList != null) && (proList.size() >= 1))
	        j.setSuccess(true);
	      else {
	        j.setSuccess(false);
	      }
	    }
	    return j;
  }
  /**
   * 跳转到选择工作流页面
   * @param request
   * @return
   */
  @RequestMapping(params={"selectFlow"})
  public ModelAndView selectFlow(HttpServletRequest request){
    request.setAttribute("busiId", request.getParameter("busiId"));
    request.setAttribute("actReModelCode", request.getParameter("actReModelCode"));
    return new ModelAndView("com/buss/activiti/approve/selectWorkFlow");
  }

  @RequestMapping(params={"list"})
  public ModelAndView list(HttpServletRequest request, String actReModelCode) {
    request.setAttribute("actReModelCode", actReModelCode);
    return new ModelAndView("com/buss/activiti/procdef/actReProcdefSelectList");
  }

  @RequestMapping(params={"datagrid"})
  public void datagrid(ActReProcdefEntity actReProcdef, HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid, String actReModelCode){
    setQueryCondtion(actReProcdef, actReModelCode);
    CriteriaQuery cq = new CriteriaQuery(ActReProcdefEntity.class, dataGrid);

    HqlGenerateUtil.installHql(cq, actReProcdef, request.getParameterMap());

    cq.add();
    this.systemService.getDataGridReturn(cq, true);
    TagUtil.datagrid(response, dataGrid);
  }

  private void setQueryCondtion(ActReProcdefEntity actReProcdef, String actReModelCode){
    actReProcdef.setSuspensionState(Integer.valueOf(1));

    if (!StringUtil.isEmpty(actReModelCode)) {
      String hql = "from ActReModelEntity where key=?";
      List list = this.systemService.findHql(hql, new Object[] { actReModelCode });
      if ((list != null) && (list.size() > 0)) {
        actReProcdef.setCategory(((ActReModelEntity)list.get(0)).getCategory());
      }
    }
    if (!StringUtil.isEmpty(actReProcdef.getName()))
      actReProcdef.setName("*" + actReProcdef.getName() + "*");
  }
}