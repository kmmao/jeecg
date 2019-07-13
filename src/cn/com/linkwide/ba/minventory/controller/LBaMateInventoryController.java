package cn.com.linkwide.ba.minventory.controller;
import cn.com.linkwide.ba.inventorylog.entity.LBaMateInventoryLogEntity;
import cn.com.linkwide.ba.material.record.entity.LBaMaterialEntity;
import cn.com.linkwide.ba.minventory.entity.LBaMateInventoryEntity;
import cn.com.linkwide.ba.minventory.service.LBaMateInventoryServiceI;
import cn.com.linkwide.ba.warehouse.entity.LBaWarehouseEntity;

import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Restrictions;
import org.jeecgframework.core.util.ResourceUtil;
import java.io.IOException;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import java.util.Map;
import java.util.HashMap;
import org.jeecgframework.core.util.ExceptionUtil;
import org.jeecgframework.core.util.ListUtils;
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
 * @Description: 物资库存表
 * @author onlineGenerator
 * @date 2018-08-13 19:20:14
 * @version V1.0   
 *
 */
@Api(value="LBaMateInventory",description="物资库存表",tags="lBaMateInventoryController")
@Controller
@RequestMapping("/lBaMateInventoryController")
public class LBaMateInventoryController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(LBaMateInventoryController.class);

	@Autowired
	private LBaMateInventoryServiceI lBaMateInventoryService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 物资库存表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("cn/com/linkwide/ba/minventory/lBaMateInventoryList");
	}
	
	/**
	 * 批次库存
	 * 
	 * @return
	 */
	@RequestMapping(params = "listPCKC")
	public ModelAndView listPCKC(HttpServletRequest request) {
		return new ModelAndView("cn/com/linkwide/ba/minventory/lBaMateInventoryListPCKC");
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
	public void datagrid(LBaMateInventoryEntity lBaMateInventory,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(LBaMateInventoryEntity.class, dataGrid);
		//查询条件组装器
		lBaMateInventory.setWarehouseId(request.getParameter("warehouseId"));
		lBaMateInventory.setMaterialId(request.getParameter("materialId"));
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, lBaMateInventory, request.getParameterMap());
		
		try{
		//自定义追加查询条件
			//集团化业务过滤
			/*cq.setIsCollectivize(false);
			String sql ="select DISTINCT warehouse_id from l_ba_warehouse_org_map where org_code like '"+ResourceUtil.getUserComponyCode()+"%'";
			List<Map<String,Object>> list = systemService.findForJdbc(sql);
			String[] ids = new String[list.size()];
			int i =0;
			for(Map<String,Object> map : list){
				ids[i]=map.get("warehouse_id").toString();
				i++;
			}
			//条件与
			Conjunction criteria =  Restrictions.conjunction();
			criteria.add(Restrictions.in("warehouseId", ids)); 
			cq.add(criteria);*/
			cq.add();
			this.lBaMateInventoryService.getDataGridReturn(cq, true);
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		//设置仓库和物资
		List<LBaMateInventoryEntity> list = dataGrid.getResults();
		Map<String,LBaWarehouseEntity> wMap = new HashMap<String,LBaWarehouseEntity>();
		Map<String,LBaMaterialEntity> mMap = new HashMap<String,LBaMaterialEntity>();
		if(list != null && !list.isEmpty()){
			try {
				List<String> wids = ListUtils.extractProperty(list, "warehouseId");
				List<String> mids = ListUtils.extractProperty(list, "materialId");
				List<LBaWarehouseEntity> wList = systemService.findByProperty(LBaWarehouseEntity.class, "id", wids.toArray());
				wMap = systemService.list2Map(LBaWarehouseEntity.class, wList, "id");
				List<LBaMaterialEntity> mList = systemService.findByProperty(LBaMaterialEntity.class, "id", mids.toArray());
				mMap = systemService.list2Map(LBaMaterialEntity.class, mList, "id");
			} catch (Exception e) {
				e.printStackTrace();
			}
				
			for(LBaMateInventoryEntity entity : list){
				if(wMap.containsKey(entity.getWarehouseId())){
					entity.setWarehouseCode(wMap.get(entity.getWarehouseId()).getWarehouseCode());
					entity.setWarehouseName(wMap.get(entity.getWarehouseId()).getWarehouseName());
				}
				if(mMap.containsKey(entity.getMaterialId())){
					entity.setMaterialCode((mMap.get(entity.getMaterialId()).getMaterialCode()));
					entity.setMaterialName((mMap.get(entity.getMaterialId()).getMaterialName()));
				}
			}
		}
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除物资库存表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(LBaMateInventoryEntity lBaMateInventory, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		lBaMateInventory = systemService.getEntity(LBaMateInventoryEntity.class, lBaMateInventory.getId());
		message = "物资库存表删除成功";
		try{
			lBaMateInventoryService.delete(lBaMateInventory);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "物资库存表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除物资库存表
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "物资库存表删除成功";
		try{
			for(String id:ids.split(",")){
				LBaMateInventoryEntity lBaMateInventory = systemService.getEntity(LBaMateInventoryEntity.class, 
				id
				);
				lBaMateInventoryService.delete(lBaMateInventory);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "物资库存表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加物资库存表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(LBaMateInventoryEntity lBaMateInventory, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "物资库存表添加成功";
		try{
			lBaMateInventoryService.save(lBaMateInventory);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "物资库存表添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新物资库存表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(LBaMateInventoryEntity lBaMateInventory, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "物资库存表更新成功";
		LBaMateInventoryEntity t = lBaMateInventoryService.get(LBaMateInventoryEntity.class, lBaMateInventory.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(lBaMateInventory, t);
			lBaMateInventoryService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "物资库存表更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 物资库存表新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(LBaMateInventoryEntity lBaMateInventory, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(lBaMateInventory.getId())) {
			lBaMateInventory = lBaMateInventoryService.getEntity(LBaMateInventoryEntity.class, lBaMateInventory.getId());
			req.setAttribute("lBaMateInventoryPage", lBaMateInventory);
		}
		return new ModelAndView("cn/com/linkwide/ba/minventory/lBaMateInventory-add");
	}
	/**
	 * 物资库存表编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(LBaMateInventoryEntity lBaMateInventory, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(lBaMateInventory.getId())) {
			lBaMateInventory = lBaMateInventoryService.getEntity(LBaMateInventoryEntity.class, lBaMateInventory.getId());
			req.setAttribute("lBaMateInventoryPage", lBaMateInventory);
		}
		return new ModelAndView("cn/com/linkwide/ba/minventory/lBaMateInventory-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","lBaMateInventoryController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(LBaMateInventoryEntity lBaMateInventory,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(LBaMateInventoryEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, lBaMateInventory, request.getParameterMap());
		List<LBaMateInventoryEntity> lBaMateInventorys = this.lBaMateInventoryService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"物资库存表");
		modelMap.put(NormalExcelConstants.CLASS,LBaMateInventoryEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("物资库存表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,lBaMateInventorys);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(LBaMateInventoryEntity lBaMateInventory,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"物资库存表");
    	modelMap.put(NormalExcelConstants.CLASS,LBaMateInventoryEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("物资库存表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<LBaMateInventoryEntity> listLBaMateInventoryEntitys = ExcelImportUtil.importExcel(file.getInputStream(),LBaMateInventoryEntity.class,params);
				for (LBaMateInventoryEntity lBaMateInventory : listLBaMateInventoryEntitys) {
					lBaMateInventoryService.save(lBaMateInventory);
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
	@ApiOperation(value="物资库存表列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<LBaMateInventoryEntity>> list() {
		List<LBaMateInventoryEntity> listLBaMateInventorys=lBaMateInventoryService.getList(LBaMateInventoryEntity.class);
		return Result.success(listLBaMateInventorys);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取物资库存表信息",notes="根据ID获取物资库存表信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		LBaMateInventoryEntity task = lBaMateInventoryService.get(LBaMateInventoryEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取物资库存表信息为空");
		}
		return Result.success(task);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建物资库存表")
	public ResponseMessage<?> create(@ApiParam(name="物资库存表对象")@RequestBody LBaMateInventoryEntity lBaMateInventory, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<LBaMateInventoryEntity>> failures = validator.validate(lBaMateInventory);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			lBaMateInventoryService.save(lBaMateInventory);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("物资库存表信息保存失败");
		}
		return Result.success(lBaMateInventory);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新物资库存表",notes="更新物资库存表")
	public ResponseMessage<?> update(@ApiParam(name="物资库存表对象")@RequestBody LBaMateInventoryEntity lBaMateInventory) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<LBaMateInventoryEntity>> failures = validator.validate(lBaMateInventory);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			lBaMateInventoryService.saveOrUpdate(lBaMateInventory);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("更新物资库存表信息失败");
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success("更新物资库存表信息成功");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除物资库存表")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" + id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			lBaMateInventoryService.deleteEntityById(LBaMateInventoryEntity.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("物资库存表删除失败");
		}

		return Result.success();
	}
	
	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "datagridPCKC")
	public void datagridPCKC(LBaMateInventoryEntity lBaMateInventory,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
	 
		try{
		//自定义追加查询条件
		StringBuffer sql = new StringBuffer();
		sql.append("  select bam.id,h.warehouse_name ,m.material_code ,m.material_name ,m.spec_model  specModel,u.type_name  unit,batch_no"
				+ ",bam.bar_code, make_date,expir_date,init.num  init,inware.num  inware ,outware.num  outware ,bam.num  "); 
		
		sql.append(" from l_ba_mate_inventory bam "); 
		sql.append(" left join "); 
		sql.append(" ( "); 
		sql.append(" select inventory_id ,sum(num)  num from l_ba_mate_inventory_log lg "); 
		sql.append(" inner join l_st_ware_balance ba  on lg.bill_no = ba.bill_no "); 
		sql.append(" group by inventory_id "); 
		sql.append(" )  init on init.inventory_id = bam.id "); 
		sql.append(" left JOIN "); 
		sql.append(" ( "); 
		sql.append(" select inventory_id ,sum(num)  num from l_ba_mate_inventory_log lg1  where num >0 "); 
		sql.append(" and not EXISTS (select id  from l_st_ware_balance ba  where lg1.bill_no = ba.bill_no) "); 
		sql.append(" group by inventory_id "); 
		sql.append(" )  inware on inware.inventory_id = bam.id "); 
		sql.append(" left join  "); 
		sql.append(" ( "); 
		sql.append(" select inventory_id ,sum(num)  num from l_ba_mate_inventory_log  lg2 where num <0 "); 
		sql.append(" and not EXISTS (select id  from l_st_ware_balance ba  where lg2.bill_no = ba.bill_no) "); 
		sql.append(" group by inventory_id "); 
		sql.append(" )  outWare on outware.inventory_id = bam.id "); 
		sql.append(" left join l_ba_warehouse h on h.id = bam.warehouse_id "); 
		sql.append(" left join l_ba_material m on bam.material_id = m.id  "); 
		sql.append(" left join l_ba_material_mater_unit u on m.mater_unit_id = u.id "); 
		sql.append("     "); 
		sql.append("    "); 
		sql.append("  where 1=1  "); 
		if(StringUtil.isNotEmpty(lBaMateInventory.getWarehouseId())){
			sql.append(" and bam.warehouse_id ='"+lBaMateInventory.getWarehouseId()+"' ");
		}
		if(StringUtil.isNotEmpty(lBaMateInventory.getMaterialId())){
			sql.append(" and bam.material_id ='"+lBaMateInventory.getMaterialId()+"'");
		}
		String batchNo = request.getParameter("batchNo");
		if(StringUtil.isNotEmpty(batchNo)){
			sql.append(" and bam.batch_no ='"+batchNo+"'");
		}
		
		List<Map<String,Object>> list = systemService.findForJdbc( sql.toString(),dataGrid.getPage(),dataGrid.getRows());
		String count = systemService.findOneForJdbc("select count(*) count FROM (  "+ sql.toString()+")  AAA").get("count").toString();
		dataGrid.setField("warehouse_name,material_code,material_name,spec_model,type_name,batch_no,bar_code,make_date,expir_date,init,inware,outware,num");
		dataGrid.setResults(list);
		dataGrid.setTotal(Integer.valueOf(count));
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		TagUtil.datagrid(response, dataGrid);
	}
}
