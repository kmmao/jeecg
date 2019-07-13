package cn.com.linkwide.common.custom.billcode.manage.controller;
import cn.com.linkwide.common.custom.billcode.manage.entity.BillcodeManageEntity;
import cn.com.linkwide.common.custom.billcode.manage.service.BillcodeManageServiceI;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.ComboTree;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.pojo.base.TSFunction;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;

import java.io.OutputStream;

import org.jeecgframework.core.util.ApplicationContextUtil;
import org.jeecgframework.core.util.BrowserUtils;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.TemplateExportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.vo.TemplateExcelConstants;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.jeecgframework.core.util.ResourceUtil;

import java.io.IOException;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.Map;

import org.jeecgframework.core.util.ExceptionUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.jeecgframework.core.beanvalidator.BeanValidators;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import java.net.URI;

import org.springframework.http.MediaType;
import org.springframework.web.util.UriComponentsBuilder;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.util.DruidDataSourceUtils;

/**   
 * @Title: Controller  
 * @Description: 单据号管理
 * @author onlineGenerator
 * @date 2017-09-22 11:23:19
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/billcodeManageController")
public class BillcodeManageController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(BillcodeManageController.class);

	@Autowired
	private BillcodeManageServiceI billcodeManageService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 单据号管理列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("cn/com/linkwide/common/custom/billcode/manage/billcodeManageList");
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
	public void datagrid(BillcodeManageEntity billcodeManage,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
			CriteriaQuery cq = new CriteriaQuery(BillcodeManageEntity.class, dataGrid);
			//查询条件组装器
			org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, billcodeManage, request.getParameterMap());
				try{
					if(StringUtil.isNotEmpty(billcodeManage.getBillType())){
						//自定义追加查询条件
						cq.eq("billType", billcodeManage.getBillType());
					}else{
						cq.isNull("billType");
					}
				}catch (Exception e) {
					throw new BusinessException(e.getMessage());
				}
				cq.add();
				this.billcodeManageService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除单据号管理
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(BillcodeManageEntity billcodeManage, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		billcodeManage = systemService.getEntity(BillcodeManageEntity.class, billcodeManage.getId());
		message = "单据号管理删除成功";
		try{
			billcodeManageService.delete(billcodeManage);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "单据号管理删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除单据号管理
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "单据号管理删除成功";
		try{
			for(String id:ids.split(",")){
				BillcodeManageEntity billcodeManage = systemService.getEntity(BillcodeManageEntity.class, 
				id
				);
				billcodeManageService.delete(billcodeManage);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "单据号管理删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加单据号管理
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(BillcodeManageEntity billcodeManage, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "单据号管理添加成功";
		try{
			SimpleDateFormat yearsdf = new SimpleDateFormat("yy");
			SimpleDateFormat monthsdf = new SimpleDateFormat("yyMM");
			SimpleDateFormat daysdf = new SimpleDateFormat("yyMMdd");
			//获取当前的年月日
			Date date = new Date();
			String year = yearsdf.format(date);
			String month = monthsdf.format(date);
			String day = daysdf.format(date);
			
			billcodeManage.setYear(year);
			billcodeManage.setMonth(month);
			billcodeManage.setDay(day);
			billcodeManage.setBillCode(billcodeManage.getApliResult());
			billcodeManageService.save(billcodeManage);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "单据号管理添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新单据号管理
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(BillcodeManageEntity billcodeManage, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "单据号管理更新成功";
		try {
			SimpleDateFormat yearsdf = new SimpleDateFormat("yy");
			SimpleDateFormat monthsdf = new SimpleDateFormat("yyMM");
			SimpleDateFormat daysdf = new SimpleDateFormat("yyMMdd");
			//获取当前的年月日
			Date date = new Date();
			String year = yearsdf.format(date);
			String month = monthsdf.format(date);
			String day = daysdf.format(date);
			
			billcodeManage.setYear(year);
			billcodeManage.setMonth(month);
			billcodeManage.setDay(day);
			billcodeManage.setBillCode(billcodeManage.getApliResult());
			billcodeManageService.updateEntitie(billcodeManage);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "单据号管理更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 单据号管理新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(BillcodeManageEntity billcodeManage, HttpServletRequest req) {
		String billType= req.getParameter("billType");
		if (StringUtil.isNotEmpty(billcodeManage.getId())) {
			billcodeManage = billcodeManageService.getEntity(BillcodeManageEntity.class, billcodeManage.getId());
			req.setAttribute("billcodeManagePage", billcodeManage);
		}
		req.setAttribute("billType", billType);
		return new ModelAndView("cn/com/linkwide/common/custom/billcode/manage/billcodeManage-add");
	}
	/**
	 * 单据号管理编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(BillcodeManageEntity billcodeManage, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(billcodeManage.getId())) {
			billcodeManage = billcodeManageService.getEntity(BillcodeManageEntity.class, billcodeManage.getId());
			req.setAttribute("billcodeManagePage", billcodeManage);
		}
		return new ModelAndView("cn/com/linkwide/common/custom/billcode/manage/billcodeManage-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","billcodeManageController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(BillcodeManageEntity billcodeManage,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(BillcodeManageEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, billcodeManage, request.getParameterMap());
		List<BillcodeManageEntity> billcodeManages = this.billcodeManageService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"单据号管理");
		modelMap.put(NormalExcelConstants.CLASS,BillcodeManageEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("单据号管理列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,billcodeManages);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(BillcodeManageEntity billcodeManage,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"单据号管理");
    	modelMap.put(NormalExcelConstants.CLASS,BillcodeManageEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("单据号管理列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<BillcodeManageEntity> listBillcodeManageEntitys = ExcelImportUtil.importExcel(file.getInputStream(),BillcodeManageEntity.class,params);
				for (BillcodeManageEntity billcodeManage : listBillcodeManageEntitys) {
					billcodeManageService.save(billcodeManage);
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
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<BillcodeManageEntity> list() {
		List<BillcodeManageEntity> listBillcodeManages=billcodeManageService.getList(BillcodeManageEntity.class);
		return listBillcodeManages;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		BillcodeManageEntity task = billcodeManageService.get(BillcodeManageEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody BillcodeManageEntity billcodeManage, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<BillcodeManageEntity>> failures = validator.validate(billcodeManage);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		try{
			billcodeManageService.save(billcodeManage);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = billcodeManage.getId();
		URI uri = uriBuilder.path("/rest/billcodeManageController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody BillcodeManageEntity billcodeManage) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<BillcodeManageEntity>> failures = validator.validate(billcodeManage);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		try{
			billcodeManageService.saveOrUpdate(billcodeManage);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		billcodeManageService.deleteEntityById(BillcodeManageEntity.class, id);
	}
	/**
	 *加载菜单树
	 *@author chencp
	 *2017年9月25日下午5:47:37
	 * @param request
	 * @return
	 */
	@RequestMapping(params="menuTree")
	@ResponseBody
	private List<ComboTree> menuTree(HttpServletRequest request){
		List<ComboTree> comboLists=new ArrayList<ComboTree>();
		ComboTree comT=new ComboTree();
		comT.setId("");
		comT.setText("单据类型");
		List<ComboTree> treeList=new ArrayList<ComboTree>();
		//查询所有设置有单据类型的菜单
		DetachedCriteria forClass = DetachedCriteria.forClass(TSFunction.class);
		DruidDataSource dataSource= ApplicationContextUtil.getBean("dataSource_jeecg");
		String sqlDialect = dataSource.getDriverClassName();
		forClass.add(Restrictions.isNotNull("billType"));
		if(sqlDialect.indexOf("Oracle")==-1){
			forClass.add(Restrictions.ne("billType", ""));
		}
//		String hql="from TSFunction a where  a.billType is not null and a.billType !='' ";
//		List<TSFunction> firstList = systemService.findHql(hql);
		List<TSFunction> firstList = systemService.findByDetached(forClass);
		if(firstList!=null&&firstList.size()>0){
			for (TSFunction fun:firstList) {
				ComboTree tree=new ComboTree();
				tree.setId(fun.getBillType());
				tree.setText(fun.getFunctionName());
				treeList.add(tree);
			}
		}
		comT.setChildren(treeList);
		comboLists.add(comT);
		return comboLists;
	}
	
	/**
	 * 加载所有的菜单
	 *@author chencp
	 *2017年9月25日下午5:47:53
	 * @param list
	 * @param combotree
	 * @return
	 */
	/*public ComboTree loadAllChildrenTree(List<TSFunction> list,ComboTree combotree){
		if(list!=null&&list.size()>0){
			List<ComboTree> comTreeList=new ArrayList<ComboTree>();
			for(TSFunction fun:list){
				ComboTree com=new ComboTree();
				com.setId(fun.getBillType());
				com.setText(fun.getFunctionName());
				//查询所有下级菜单
				String hql1="from TSFunction a where a.billType is not null and a.TSFunction.id=?";
				List<TSFunction> funList = systemService.findHql(hql1,fun.getId());
				if(funList.size()>0){
					com.setState("closed");
					loadAllChildrenTree(funList, com);
				}else{
					com.setState("open");
				}
				comTreeList.add(com);
				combotree.setChildren(comTreeList);
			}
		}
		return combotree;
	}*/
}
