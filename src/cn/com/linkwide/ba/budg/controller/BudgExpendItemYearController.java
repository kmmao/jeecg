package cn.com.linkwide.ba.budg.controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.criterion.Restrictions;
import org.jeecgframework.core.beanvalidator.BeanValidators;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.ExceptionUtil;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.jwt.util.ResponseMessage;
import org.jeecgframework.jwt.util.Result;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.tag.vo.datatable.SortDirection;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import com.alibaba.fastjson.JSONArray;

import cn.com.linkwide.ba.budg.entity.BudgExpendItemEntity;
import cn.com.linkwide.common.delcheck.service.DelCheckServiceI;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**   
 * @Title: Controller  
 * @Description: 支出项目维护
 * @author onlineGenerator
 * @date 2018-07-27 11:07:29
 * @version V1.0   
 *
 */
@Api(value="budgExpendItemYear",description="支出项目维护",tags="budgExpendItemYearController")
@Controller
@RequestMapping("/budgExpendItemYearController")
public class BudgExpendItemYearController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(BudgExpendItemYearController.class);

	@Autowired
	private DelCheckServiceI delCheckServiceI;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 支出项目列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("cn/com/linkwide/ba/budg/budgExpendItemListyear");
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
	public void datagrid(BudgExpendItemEntity budgExpendItem,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		if(StringUtil.isNotEmpty(budgExpendItem.getItemName())){
			budgExpendItem.setItemName("*"+budgExpendItem.getItemName()+"*");
		}
		if(StringUtil.isNotEmpty(budgExpendItem.getItemCode())){
			budgExpendItem.setItemCode("*"+budgExpendItem.getItemCode()+"*");
		}
		CriteriaQuery cq = new CriteriaQuery(BudgExpendItemEntity.class, dataGrid);
		if(StringUtil.isEmpty(budgExpendItem.getId())){
//			cq.add(Restrictions.in("parentCode", new Object[]{null,""}));
			cq.add(Restrictions.isNull("parentCode"));
		}else{
			cq.add(Restrictions.eq("parentCode", systemService.get(BudgExpendItemEntity.class, budgExpendItem.getId()).getItemCode()));
			budgExpendItem.setId(null);
		}
		
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, budgExpendItem, request.getParameterMap());
		try{
		//自定义追加查询条件
		cq.eq("budgYear", 9999);
		cq.addOrder("itemCode",SortDirection.asc);
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		systemService.getDataGridReturn(cq, true);
		//TagUtil.datagrid(response, dataGrid);
		TagUtil.treegrid(response, dataGrid);
	}
	
	/**
	 * 删除支出项目
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(BudgExpendItemEntity budgExpendItem, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		budgExpendItem = systemService.getEntity(BudgExpendItemEntity.class, budgExpendItem.getId());
		message = "支出项目删除成功";
		try{
			//删除前校验  是否有下级项目
			String returnMsg =checkBeforeDel(budgExpendItem, null);
			if(StringUtil.isNotEmpty(returnMsg)){
				j.setMsg(returnMsg);
				return j;
			}
			returnMsg =delCheckServiceI.generalDeleteCheck(BudgExpendItemEntity.class, budgExpendItem.getItemCode());
			if(StringUtil.isNotEmpty(returnMsg)){
				j.setMsg(returnMsg);
				return j;
			}
			systemService.delete(budgExpendItem);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "支出项目删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 支出项目删除前的校验
	 * @param budgExpendItem
	 * @param map
	 * @return
	 */
	private String checkBeforeDel(BudgExpendItemEntity item,Map map){
		if(item!=null){
			String itemCode =item.getItemCode();
			List<BudgExpendItemEntity> list = systemService.findByProperty(BudgExpendItemEntity.class, "parentCode", itemCode);
			if(list!= null && list.size()>0){
				return item.getItemName()+"存在下级支出项目，不能删除";
			}
		}
		
		return null;
	}
	
	/**
	 * 批量删除支出项目
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "支出项目删除成功";
		try{
			for(String id:ids.split(",")){
				BudgExpendItemEntity budgExpendItem = systemService.getEntity(BudgExpendItemEntity.class, 
				id
				);
				systemService.delete(budgExpendItem);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "支出项目删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加支出项目
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(BudgExpendItemEntity budgExpendItem, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "支出项目添加成功";
		try{
			//判断支出项目编码是否重复
			String codeHql="from BudgExpendItemEntity where budgYear=? and itemCode=? ";
			List<BudgExpendItemEntity> itemCodeList = systemService.findHql(codeHql,budgExpendItem.getBudgYear(), budgExpendItem.getItemCode());
			if(itemCodeList!=null&&itemCodeList.size()>0){
				throw new BusinessException(budgExpendItem.getItemCode()+"已经存在");
			}
			//判断支出项目名称是否重复
//			String nameHql="from  BudgExpendItemEntity where budgYear=? and  itemName=? ";
//			List<BudgExpendItemEntity> itemNameList = systemService.findHql(nameHql,budgExpendItem.getBudgYear(), budgExpendItem.getItemName());
//			if(itemNameList!=null&&itemNameList.size()>0){
//				throw new BusinessException(budgExpendItem.getItemName()+"已经存在");
//			}
			//是否系统维护项目标识
			budgExpendItem.setExtend1("Y");
			systemService.save(budgExpendItem);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(BusinessException b){
			message="支出项目添加失败-"+b.getMessage();
		}catch(Exception e){
			e.printStackTrace();
			message = "支出项目添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新支出项目
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(BudgExpendItemEntity budgExpendItem, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "支出项目更新成功";
		BudgExpendItemEntity t = systemService.get(BudgExpendItemEntity.class, budgExpendItem.getId());
		try {
			//判断支出项目编码是否重复
			String codeHql="from BudgExpendItemEntity where budgYear=? and itemCode=? and id <> ?";
			List<BudgExpendItemEntity> itemCodeList = systemService.findHql(codeHql,budgExpendItem.getBudgYear(), budgExpendItem.getItemCode(),t.getId());
			//先判断支出项目编目是否改变
//			if(budgExpendItem.getItemCode().equals(t.getItemCode())){//没有改变时
//				if(itemCodeList!=null&&itemCodeList.size()>1){
//					throw new BusinessException(budgExpendItem.getItemCode()+"已经存在");
//				}
//			}else{//改变时
				if(itemCodeList!=null&&itemCodeList.size()>0){
					throw new BusinessException(budgExpendItem.getItemCode()+"已经存在");
				}
//			}
			//判断支出项目名称是否重复
//			String nameHql="from  BudgExpendItemEntity where budgYear=? and  itemName=? ";
//			List<BudgExpendItemEntity> itemNameList = systemService.findHql(nameHql,budgExpendItem.getBudgYear(), budgExpendItem.getItemName());
//			//判断支出项目名称是否改变
//			if(budgExpendItem.getItemName().equals(t.getItemName())){
//				if(itemNameList!=null&&itemNameList.size()>1){
//					throw new BusinessException(budgExpendItem.getItemName()+"已经存在");
//				}
//			}else{
//				if(itemNameList!=null&&itemNameList.size()>0){
//					throw new BusinessException(budgExpendItem.getItemName()+"已经存在");
//				}
//			}
			MyBeanUtils.copyBeanNotNull2Bean(budgExpendItem, t);
			systemService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "支出项目更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 支出项目新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(BudgExpendItemEntity budgExpendItem, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(budgExpendItem.getId())) {
			budgExpendItem = systemService.getEntity(BudgExpendItemEntity.class, budgExpendItem.getId());
			req.setAttribute("budgExpendItemPage", budgExpendItem);
		}
		return new ModelAndView("cn/com/linkwide/ba/budg/budgExpendItemyear-add");
	}
	/**
	 * 支出项目编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(BudgExpendItemEntity budgExpendItem, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(budgExpendItem.getId())) {
			budgExpendItem = systemService.getEntity(BudgExpendItemEntity.class, budgExpendItem.getId());
			req.setAttribute("budgExpendItemPage", budgExpendItem);
		}
		return new ModelAndView("cn/com/linkwide/ba/budg/budgExpendItemyear-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","budgExpendItemYearController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(BudgExpendItemEntity budgExpendItem,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(BudgExpendItemEntity.class, dataGrid);
		cq.eq("budgYear", 9999);
		cq.addOrder("itemCode", SortDirection.asc);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, budgExpendItem, request.getParameterMap());
		List<BudgExpendItemEntity> budgExpendItems = systemService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"支出项目");
		modelMap.put(NormalExcelConstants.CLASS,BudgExpendItemEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("支出项目列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,budgExpendItems);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(BudgExpendItemEntity budgExpendItem,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"支出项目");
    	modelMap.put(NormalExcelConstants.CLASS,BudgExpendItemEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("支出项目列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
		Map itemMap= new HashMap();
		String hql =" from BudgExpendItemEntity where budgYear =? ";
		List<BudgExpendItemEntity> list = systemService.findHql(hql, new Object[]{9999});
		if(list != null && list.size()>0){
			for (BudgExpendItemEntity item : list) {
				itemMap.put(item.getItemCode(), item.getItemName());
			}
		}
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			MultipartFile file = entity.getValue();// 获取上传文件对象
			ImportParams params = new ImportParams();
			params.setTitleRows(2);
			params.setHeadRows(1);
			params.setNeedSave(true);
			try {
				List<BudgExpendItemEntity> listBudgExpendItemEntitys = ExcelImportUtil.importExcel(file.getInputStream(),BudgExpendItemEntity.class,params);
				for (BudgExpendItemEntity budgExpendItem : listBudgExpendItemEntitys) {
					String itemCode =budgExpendItem.getItemCode();
					String itemName =budgExpendItem.getItemName();
					if(StringUtil.isEmpty(itemCode)){
						j.setMsg("支出项目编码不能为空！");
						return j;
					}
					
					if(StringUtil.isEmpty(itemName)){
						j.setMsg("支出项目名称不能为空！");
						return j;
					}
					
					String value = itemMap.get(itemCode)==null?null:itemMap.get(itemCode).toString();
					if(StringUtil.isEmpty(value)){
						itemMap.put(itemCode, itemName);
					}else{
						j.setMsg(itemCode+"支出项目编码重复！");
						return j;
					}
					
					budgExpendItem.setBudgYear(9999);
					systemService.save(budgExpendItem);
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
	@ApiOperation(value="支出项目列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<BudgExpendItemEntity>> list() {
		List<BudgExpendItemEntity> listBudgExpendItems=systemService.getList(BudgExpendItemEntity.class);
		return Result.success(listBudgExpendItems);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取支出项目信息",notes="根据ID获取支出项目信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		BudgExpendItemEntity task = systemService.get(BudgExpendItemEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取支出项目信息为空");
		}
		return Result.success(task);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建支出项目")
	public ResponseMessage<?> create(@ApiParam(name="支出项目对象")@RequestBody BudgExpendItemEntity budgExpendItem, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<BudgExpendItemEntity>> failures = validator.validate(budgExpendItem);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			systemService.save(budgExpendItem);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("支出项目信息保存失败");
		}
		return Result.success(budgExpendItem);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新支出项目",notes="更新支出项目")
	public ResponseMessage<?> update(@ApiParam(name="支出项目对象")@RequestBody BudgExpendItemEntity budgExpendItem) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<BudgExpendItemEntity>> failures = validator.validate(budgExpendItem);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			systemService.saveOrUpdate(budgExpendItem);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("更新支出项目信息失败");
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success("更新支出项目信息成功");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除支出项目")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" + id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			systemService.deleteEntityById(BudgExpendItemEntity.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("支出项目删除失败");
		}

		return Result.success();
	}
	
	/**
	 * 批量设置跳转页面
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "batchSet")
	public ModelAndView batchSet(HttpServletRequest request) {
		String ids = request.getParameter("ids");
		request.setAttribute("ids", ids);
		return new ModelAndView("cn/com/linkwide/ef/budgexpend/budgexpenditemyear/budgExpendItemyear-batchSet");
	}
	
	/**
	 * 批量设置业务科室
	 * @param budgQuotaStandard
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "doBatchSet")
	@ResponseBody
	public AjaxJson doBatchSet(BudgExpendItemEntity budgExpendItem, HttpServletRequest request) {
		String message = null;
		message = "支出项目设置成功";
		AjaxJson j = new AjaxJson();
		try {
			//批量设置的支出项目id字符串
			String ids = budgExpendItem.getId();
			//批量设置的业务科室的id字符串
			String depts = budgExpendItem.getDeptCode();
			if(StringUtil.isNotEmpty(ids) && StringUtil.isNotEmpty(depts)){
				String idsArr[] = ids.split(",");
				String [] deptIdsArr = depts.split(",");
				for (int i=0;i< idsArr.length;i++) {
					BudgExpendItemEntity entity = systemService.get(BudgExpendItemEntity.class, idsArr[i]);
					//一个项目 一个科室一行数据
					for (int z=0;z< deptIdsArr.length;z++) {
						if(z!=0){
							BudgExpendItemEntity item=new BudgExpendItemEntity();
							item.setBudgYear(entity.getBudgYear());
							item.setManaDept(entity.getManaDept());
							item.setSource(entity.getSource());
							item.setCompMethod(entity.getCompMethod());
							item.setCalFormula(entity.getCalFormula());
							item.setCalFormulaEng(entity.getCalFormulaEng());
							item.setBudgLevel(entity.getBudgLevel());
							item.setItemCode(entity.getItemCode());
							item.setItemName(entity.getItemName());
							item.setDeptCode(deptIdsArr[z]);
							systemService.save(item);
						}else{
							entity.setDeptCode(deptIdsArr[z]);
							systemService.saveOrUpdate(entity);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			message = "支出项目批量设置失败";
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 编辑系统项目时校验项目是否被引用
	 * @param budgIncomItem
	 * @return
	 */
	@RequestMapping(params="checkItemIsUsed")
	@ResponseBody
	public AjaxJson checkItemIsUsed(BudgExpendItemEntity entity,HttpServletRequest req){
		AjaxJson j = new AjaxJson();
		String itemCode = req.getParameter("itemCode"); 
		if(StringUtil.isNotEmpty(itemCode)){
			String hql =" from BudgExpendItemEntity where budgYear<> '9999' and itemCode=?";
			List<BudgExpendItemEntity> list = systemService.findHql(hql, new Object[]{itemCode});
			if(list !=null && list.size()>0){
				j.setMsg("项目被引用，不能编辑");
				return j;
			}
		}
		return j;
	}
}

