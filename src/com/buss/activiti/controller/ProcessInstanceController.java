package com.buss.activiti.controller;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**   
 * @Title: Controller  
 * @Description: processInstanceController
 * @author onlineGenerator
 * @date 2017-07-17 15:20:14
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/processInstanceController")
public class ProcessInstanceController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ProcessInstanceController.class);
	
	@Autowired
	private RuntimeService runtimeService;
	
	/**
	 * 运行中流程列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {

		return new ModelAndView("com/buss/activiti/actProcessRunningList");
	}
	
	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		
	    ProcessInstanceQuery processInstanceQuery = runtimeService.createProcessInstanceQuery();

	    if (StringUtils.isNotBlank(request.getParameter("procInsId"))){
		    processInstanceQuery.processInstanceId(request.getParameter("procInsId"));
	    }
	    
	    if (StringUtils.isNotBlank(request.getParameter("procDefKey"))){
		    processInstanceQuery.processDefinitionKey(request.getParameter("procDefKey"));
	    }

		List<ProcessInstance> processInstanceList = processInstanceQuery.listPage(dataGrid.getFirstResult(), dataGrid.getRows());

		dataGrid.setTotal(Integer.parseInt(String.valueOf(processInstanceQuery.count())));
		dataGrid.setResults(processInstanceList);
		TagUtil.datagrid(response, dataGrid);
	}

}
