package com.buss.activiti.controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Validator;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.ComboTree;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.common.model.json.TreeGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.ExceptionUtil;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.tag.vo.easyui.TreeGridModel;
import org.jeecgframework.web.system.pojo.base.TSCategoryEntity;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.buss.activiti.entity.WorkFlowCateEntity;
import com.buss.activiti.service.WorkFlowCateServiceI;

/**   
 * @Title: Controller
 * @Description: 档案门类
 * @author onlineGenerator
 * @date 2016-05-29 10:32:48
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/workFlowCateController")
public class WorkFlowCateController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(WorkFlowCateController.class);

	@Autowired
	private WorkFlowCateServiceI workFlowCateService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	
	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * 测试流程发起主页面
	 * @param request
	 * @return
	 */
//	@RequestMapping(params = "test")
//	public ModelAndView test(HttpServletRequest request) {
//		return new ModelAndView("com/buss/activiti/workFlowCateList2");
//	} 
	
	/**
	 * 档案门类列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/buss/activiti/workFlowCateList");
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
	@ResponseBody
	public Object datagrid(WorkFlowCateEntity workFlowCate,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(WorkFlowCateEntity.class, dataGrid);
		if (workFlowCate.getId() == null || StringUtils.isEmpty(workFlowCate.getId())) {
			cq.isNull("parent");
		} else {
			cq.eq("parent.id", workFlowCate.getId());
			workFlowCate.setId(null);
		}
		
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, workFlowCate, request.getParameterMap());
		
		List<TSCategoryEntity> list = this.workFlowCateService.getListByCriteriaQuery(cq, false);
		List<TreeGrid> treeGrids = new ArrayList<TreeGrid>();
		TreeGridModel treeGridModel = new TreeGridModel();
		treeGridModel.setIdField("id");
		treeGridModel.setSrc("id");
		treeGridModel.setTextField("name");
		treeGridModel.setParentText("parent_name");
		treeGridModel.setParentId("parent_id");
		treeGridModel.setChildList("list");

        Map<String,Object> fieldMap = new HashMap<String, Object>();
        fieldMap.put("cgformHeadId", "cgformHeadId");
        fieldMap.put("level", "level");
        treeGridModel.setFieldMap(fieldMap);
        
		treeGrids = systemService.treegrid(list, treeGridModel);
        JSONArray jsonArray = new JSONArray();
        for (TreeGrid treeGrid : treeGrids) {
            jsonArray.add(JSON.parse(treeGrid.toJson()));
        }
        return jsonArray;
	}

	
	@RequestMapping(params = "tree")
	@ResponseBody
	public List<ComboTree> tree(String selfCode,ComboTree comboTree, boolean isNew) {
		CriteriaQuery cq = new CriteriaQuery(WorkFlowCateEntity.class);
		cq.isNull("parent");
		cq.add();
		List<WorkFlowCateEntity> categoryList = systemService.getListByCriteriaQuery(cq, false);
		List<ComboTree> comboTrees = new ArrayList<ComboTree>();
		for (int i = 0; i < categoryList.size(); i++) {
			comboTrees.add(workFlowCateEntityConvertToTree(categoryList.get(i)));
		}
		return comboTrees;
	}
	
	private ComboTree workFlowCateEntityConvertToTree(WorkFlowCateEntity entity) {
		ComboTree tree = new ComboTree();
		tree.setId(entity.getId());
		tree.setText(entity.getName());
		
		Map<String, Object> attributes = new HashMap<String, Object>();

		tree.setAttributes(attributes);
		
		if (entity.getList() != null && entity.getList().size() > 0) {
			List<ComboTree> comboTrees = new ArrayList<ComboTree>();
			for (int i = 0; i < entity.getList().size(); i++) {
				comboTrees.add(workFlowCateEntityConvertToTree(entity.getList().get(i)));
			}
			tree.setChildren(comboTrees);
		}
		return tree;
	}
	
	
	/**
	 * 删除档案门类
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(WorkFlowCateEntity archiveCate, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		archiveCate = systemService.getEntity(WorkFlowCateEntity.class, archiveCate.getId());
		message = "流程分类删除成功";
		try{
			workFlowCateService.delete(archiveCate);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "流程分类删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除档案门类
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		message = "流程分类删除成功";
		try{
			for(String id:ids.split(",")){
				WorkFlowCateEntity workFlowCate = systemService.getEntity(WorkFlowCateEntity.class, 
				id
				);
				workFlowCateService.delete(workFlowCate);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "流程分类删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加档案门类
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(WorkFlowCateEntity workFlowCate, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		
		boolean flag = StringUtil.isEmpty(workFlowCate.getParent().getId());
		
		message = "流程分类添加成功";
		try{
			if (flag) {
				workFlowCate.setParent(null);
			}
			
			workFlowCateService.save(workFlowCate);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "流程分类添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新档案门类
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(WorkFlowCateEntity workFlowCate, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		
		boolean flag = StringUtil.isEmpty(workFlowCate.getParent().getId());
		
		
		message = "流程分类更新成功";
		WorkFlowCateEntity t = workFlowCateService.get(WorkFlowCateEntity.class, workFlowCate.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(workFlowCate, t);
			if (flag) {
				t.setParent(null);
			}
			
			workFlowCateService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "流程分类更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 档案门类新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(WorkFlowCateEntity workFlowCate, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(workFlowCate.getId())) {
			workFlowCate = workFlowCateService.getEntity(WorkFlowCateEntity.class, workFlowCate.getId());
			req.setAttribute("workFlowCatePage", workFlowCate);
		}
		return new ModelAndView("com/buss/activiti/workFlowCate-add");
	}
	/**
	 * 档案门类编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(WorkFlowCateEntity workFlowCate, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(workFlowCate.getId())) {
			workFlowCate = workFlowCateService.getEntity(WorkFlowCateEntity.class, workFlowCate.getId());
			req.setAttribute("workFlowCatePage", workFlowCate);
		}
		return new ModelAndView("com/buss/activiti/workFlowCate-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","workFlowCateController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(WorkFlowCateEntity workFlowCate,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(WorkFlowCateEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, workFlowCate, request.getParameterMap());
		List<WorkFlowCateEntity> workFlowCates = this.workFlowCateService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"档案门类");
		modelMap.put(NormalExcelConstants.CLASS,WorkFlowCateEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("档案门类列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,workFlowCates);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(WorkFlowCateEntity workFlowCate,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"档案门类");
    	modelMap.put(NormalExcelConstants.CLASS,WorkFlowCateEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("档案门类列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
    	"导出信息"));
    	modelMap.put(NormalExcelConstants.DATA_LIST,new ArrayList());
    	return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "importExcel", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson importExcel(HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			MultipartFile file = entity.getValue();// 获取上传文件对象
			ImportParams params = new ImportParams();
			params.setTitleRows(2);
			params.setHeadRows(1);
			params.setNeedSave(true);
			try {
				List<WorkFlowCateEntity> listWorkFlowCateEntitys = ExcelImportUtil.importExcel(file.getInputStream(),WorkFlowCateEntity.class,params);
				for (WorkFlowCateEntity workFlowCate : listWorkFlowCateEntitys) {
					workFlowCateService.save(workFlowCate);
				}
				j.setMsg("文件导入成功！");
			} catch (Exception e) {
				j.setMsg("文件导入失败！");
				logger.error(ExceptionUtil.getExceptionMessage(e));
			}finally{
				try {
					file.getInputStream().close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return j;
	}

}
