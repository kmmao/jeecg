package cn.com.linkwide.ba.inventorylog.controller;
import cn.com.linkwide.ba.inventorylog.entity.LBaMateInventoryLogEntity;
import cn.com.linkwide.ba.inventorylog.service.LBaMateInventoryLogServiceI;
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.synth.SynthStyleFactory;

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
import org.jeecgframework.core.common.model.common.TreeChildCount;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;

import java.io.OutputStream;
import org.jeecgframework.core.util.BrowserUtils;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.TemplateExportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.vo.TemplateExcelConstants;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jeecgframework.core.util.ResourceUtil;
import java.io.IOException;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import java.util.Map;
import java.util.HashMap;
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
import org.apache.commons.lang3.StringUtils;
import org.jeecgframework.jwt.util.GsonUtil;
import org.jeecgframework.jwt.util.ResponseMessage;
import org.jeecgframework.jwt.util.Result;
import com.alibaba.fastjson.JSONArray;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**   
 * @Title: Controller  
 * @Description: 库存管理日志
 * @author onlineGenerator
 * @date 2018-08-20 10:23:45
 * @version V1.0   
 *
 */
@Api(value="LBaMateInventoryLog",description="库存管理日志",tags="lBaMateInventoryLogController")
@Controller
@RequestMapping("/lBaMateInventoryLogController")
public class LBaMateInventoryLogController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(LBaMateInventoryLogController.class);

	@Autowired
	private LBaMateInventoryLogServiceI lBaMateInventoryLogService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 库存管理日志列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("cn/com/linkwide/ba/inventorylog/lBaMateInventoryLogList");
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
	public void datagrid(LBaMateInventoryLogEntity lBaMateInventoryLog,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
	 
		try{
		//自定义追加查询条件
		StringBuffer head = new StringBuffer();
		head.append(" select DISTINCT l.id  id ,l.warehouse_id  warehouseId,h.warehouse_name  warehouseName");
		head.append(" ,l.material_id  materialId , m.material_name  materialName,inv.batch_no  batchNo,inv.expir_date  expirDate");
		head.append(" ,l.num ,l.now_num  nowNum,l.bill_no  billNo ,l.bill_type   billType,l.create_by  createBy,l.log_date  logDate,l.is_agency isAgency ");
		StringBuffer sql = new StringBuffer();
		sql.append("  from l_ba_mate_inventory_log l");
		//集团化业务过滤
		/*sql.append(" inner join l_ba_warehouse_org_map map  on map.warehouse_id = l.warehouse_id  and map.org_code like '"+ResourceUtil.getUserComponyCode()+"%'" );*/
		sql.append(" left join l_ba_mate_inventory inv  on inv.id = l.inventory_id ");
		sql.append(" left join l_ba_warehouse h on h.id = l.warehouse_id ");
		sql.append(" left join l_ba_material m on m.id = l.material_id ");
		sql.append(" where 1=1 "); 
		if(StringUtil.isNotEmpty(lBaMateInventoryLog.getWarehouseId())){
			sql.append(" and l.warehouse_id ='"+lBaMateInventoryLog.getWarehouseId()+"' ");
		}
		if(StringUtil.isNotEmpty(lBaMateInventoryLog.getMaterialId())){
			sql.append(" and l.material_id ='"+lBaMateInventoryLog.getMaterialId()+"'");
		}
		String batchNo = request.getParameter("batchNo");
		if(StringUtil.isNotEmpty(batchNo)){
			sql.append(" and inv.batch_no ='"+batchNo+"'");
		}
		String expirDate = request.getParameter("expirDate");
		if(StringUtil.isNotEmpty(expirDate)){
			sql.append(" and inv.batch_no ='"+new SimpleDateFormat("yyyy-MM-dd").parse(expirDate)+"'");
		}
		String billNo = request.getParameter("billNo");
		if(StringUtil.isNotEmpty(billNo)){
			sql.append(" and l.bill_no ='"+billNo+"'");
		}
		if(StringUtil.isNotEmpty(lBaMateInventoryLog.getCreateBy())){
			sql.append(" and l.create_by ='"+lBaMateInventoryLog.getCreateBy()+"'");
		}
		String query_logDate_begin = request.getParameter("logDate_begin");
		String query_logDate_end = request.getParameter("logDate_end");
		if(StringUtil.isNotEmpty(query_logDate_begin)){ 
			sql.append(" and l.log_date <='"+new SimpleDateFormat("yyyy-MM-dd").parse(query_logDate_begin)+"'");
		}
		if(StringUtil.isNotEmpty(query_logDate_end)){
			sql.append(" and l.log_date >='"+new SimpleDateFormat("yyyy-MM-dd").parse(query_logDate_end)+"'");
		}
		if(StringUtil.isNotEmpty(dataGrid.getOrder())){
			sql.append(" order by l.log_date " +dataGrid.getOrder()+",l.now_num ");
			if("desc".equals(dataGrid.getOrder())){
				sql.append(" asc");
			}else{
				sql.append(" desc");
			}
		}else{
			sql.append(" order by l.log_date desc ,l.now_num asc");
		}
		
		List<Map<String,Object>> list = systemService.findForJdbc(head.toString() + sql.toString(),dataGrid.getPage(),dataGrid.getRows());
		String count = systemService.findOneForJdbc("select count(DISTINCT l.id) count  "+ sql.toString()).get("count").toString();
		dataGrid.setResults(list);
		dataGrid.setTotal(Integer.valueOf(count));
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除库存管理日志
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(LBaMateInventoryLogEntity lBaMateInventoryLog, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		lBaMateInventoryLog = systemService.getEntity(LBaMateInventoryLogEntity.class, lBaMateInventoryLog.getId());
		message = "库存管理日志删除成功";
		try{
			lBaMateInventoryLogService.delete(lBaMateInventoryLog);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "库存管理日志删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除库存管理日志
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "库存管理日志删除成功";
		try{
			for(String id:ids.split(",")){
				LBaMateInventoryLogEntity lBaMateInventoryLog = systemService.getEntity(LBaMateInventoryLogEntity.class, 
				id
				);
				lBaMateInventoryLogService.delete(lBaMateInventoryLog);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "库存管理日志删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加库存管理日志
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(LBaMateInventoryLogEntity lBaMateInventoryLog, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "库存管理日志添加成功";
		try{
			lBaMateInventoryLogService.save(lBaMateInventoryLog);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "库存管理日志添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新库存管理日志
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(LBaMateInventoryLogEntity lBaMateInventoryLog, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "库存管理日志更新成功";
		LBaMateInventoryLogEntity t = lBaMateInventoryLogService.get(LBaMateInventoryLogEntity.class, lBaMateInventoryLog.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(lBaMateInventoryLog, t);
			lBaMateInventoryLogService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "库存管理日志更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 库存管理日志新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(LBaMateInventoryLogEntity lBaMateInventoryLog, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(lBaMateInventoryLog.getId())) {
			lBaMateInventoryLog = lBaMateInventoryLogService.getEntity(LBaMateInventoryLogEntity.class, lBaMateInventoryLog.getId());
			req.setAttribute("lBaMateInventoryLogPage", lBaMateInventoryLog);
		}
		return new ModelAndView("cn/com/linkwide/ba/inventorylog/lBaMateInventoryLog-add");
	}
	/**
	 * 库存管理日志编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(LBaMateInventoryLogEntity lBaMateInventoryLog, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(lBaMateInventoryLog.getId())) {
			lBaMateInventoryLog = lBaMateInventoryLogService.getEntity(LBaMateInventoryLogEntity.class, lBaMateInventoryLog.getId());
			req.setAttribute("lBaMateInventoryLogPage", lBaMateInventoryLog);
		}
		return new ModelAndView("cn/com/linkwide/ba/inventorylog/lBaMateInventoryLog-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","lBaMateInventoryLogController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(LBaMateInventoryLogEntity lBaMateInventoryLog,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(LBaMateInventoryLogEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, lBaMateInventoryLog, request.getParameterMap());
		List<LBaMateInventoryLogEntity> lBaMateInventoryLogs = this.lBaMateInventoryLogService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"库存管理日志");
		modelMap.put(NormalExcelConstants.CLASS,LBaMateInventoryLogEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("库存管理日志列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,lBaMateInventoryLogs);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(LBaMateInventoryLogEntity lBaMateInventoryLog,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"库存管理日志");
    	modelMap.put(NormalExcelConstants.CLASS,LBaMateInventoryLogEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("库存管理日志列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<LBaMateInventoryLogEntity> listLBaMateInventoryLogEntitys = ExcelImportUtil.importExcel(file.getInputStream(),LBaMateInventoryLogEntity.class,params);
				for (LBaMateInventoryLogEntity lBaMateInventoryLog : listLBaMateInventoryLogEntitys) {
					lBaMateInventoryLogService.save(lBaMateInventoryLog);
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
	@ApiOperation(value="库存管理日志列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<LBaMateInventoryLogEntity>> list() {
		List<LBaMateInventoryLogEntity> listLBaMateInventoryLogs=lBaMateInventoryLogService.getList(LBaMateInventoryLogEntity.class);
		return Result.success(listLBaMateInventoryLogs);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取库存管理日志信息",notes="根据ID获取库存管理日志信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		LBaMateInventoryLogEntity task = lBaMateInventoryLogService.get(LBaMateInventoryLogEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取库存管理日志信息为空");
		}
		return Result.success(task);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建库存管理日志")
	public ResponseMessage<?> create(@ApiParam(name="库存管理日志对象")@RequestBody LBaMateInventoryLogEntity lBaMateInventoryLog, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<LBaMateInventoryLogEntity>> failures = validator.validate(lBaMateInventoryLog);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			lBaMateInventoryLogService.save(lBaMateInventoryLog);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("库存管理日志信息保存失败");
		}
		return Result.success(lBaMateInventoryLog);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新库存管理日志",notes="更新库存管理日志")
	public ResponseMessage<?> update(@ApiParam(name="库存管理日志对象")@RequestBody LBaMateInventoryLogEntity lBaMateInventoryLog) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<LBaMateInventoryLogEntity>> failures = validator.validate(lBaMateInventoryLog);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			lBaMateInventoryLogService.saveOrUpdate(lBaMateInventoryLog);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("更新库存管理日志信息失败");
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success("更新库存管理日志信息成功");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除库存管理日志")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" + id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			lBaMateInventoryLogService.deleteEntityById(LBaMateInventoryLogEntity.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("库存管理日志删除失败");
		}

		return Result.success();
	}
}
