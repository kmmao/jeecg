package cn.com.linkwide.ba.warehouse.controller;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
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
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.tag.vo.datatable.SortDirection;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

import cn.com.linkwide.ba.location.entity.LBaLocationDictEntity;

import cn.com.linkwide.ba.warehouse.entity.LBaWarehouseEntity;
import cn.com.linkwide.ba.warehouse.service.LBaWarehouseServiceI;

/**   
 * @Title: Controller  
 * @Description: 仓库表
 * @author onlineGenerator
 * @date 2017-11-08 17:15:34
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/lBaWarehouseController")
public class LBaWarehouseController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(LBaWarehouseController.class);

	@Autowired
	private LBaWarehouseServiceI lBaWarehouseService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 仓库表列表 页面跳转
	 * /lms/WebContent/webpage/cn/com/linkwide/ba/warehouse/lBaWarehouse-add.jsp
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("cn/com/linkwide/ba/warehouse/lBaWarehouseList");
	}
	
	@RequestMapping(params = "listForSelect")
	public ModelAndView listForSelect(HttpServletRequest request,boolean filterUser) {
		request.setAttribute("filterUser", filterUser);
		return new ModelAndView("cn/com/linkwide/ba/warehouse/lBaWarehouseListForSelect");
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
	public void datagrid(LBaWarehouseEntity lBaWarehouse,HttpServletRequest request, HttpServletResponse response
			, DataGrid dataGrid,boolean filterUser) {
		CriteriaQuery cq = new CriteriaQuery(LBaWarehouseEntity.class, dataGrid);
		/*cq.setIsCollectivize(false);*/
		
		if(StringUtils.isNotEmpty(lBaWarehouse.getWarehouseCode())){
			lBaWarehouse.setWarehouseCode("*"+lBaWarehouse.getWarehouseCode()+"*");
		}
		
		if(StringUtils.isNotEmpty(lBaWarehouse.getWarehouseName())){
			lBaWarehouse.setWarehouseName("*"+lBaWarehouse.getWarehouseName()+"*");
		}
		
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, lBaWarehouse, request.getParameterMap());
		try{
			if(filterUser){
				addFilterUser(cq);
			}
		//自定义追加查询条件
			cq.addOrder("createDate", SortDirection.desc);
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		try {
			this.lBaWarehouseService.getDataGridReturn(cq, true);
			List<LBaWarehouseEntity> list =  dataGrid.getResults();
			List<Map<String,Object>> mList =systemService.findForJdbc("select m.warehouse_id \"warehouse_id\",d.departname \"departname\",m.org_code \"org_code\" from l_ba_warehouse_org_map m left join t_s_depart d on  m.org_code = d.org_code where  m.warehouse_id is not null   ");
			for(LBaWarehouseEntity h : list){
				for(Map<String,Object> m : mList){
					if(h.getId().equals(m.get("warehouse_id").toString())){
						if(StringUtil.isNotEmpty(h.getOrgNames())){
							h.setOrgNames(h.getOrgNames()+","+m.get("departname").toString());
						}else{
							h.setOrgNames(m.get("departname").toString());
						}
						
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		TagUtil.datagrid(response, dataGrid);
	}

	public void addFilterUser(CriteriaQuery cq){
//		TSUser user = ResourceUtil.getSessionUserName();
//		String hql = " from LBaContOperatorWarehouseEntity where operatorId=? ";
//		List<LBaContOperatorWarehouseEntity> list = systemService.findHql(hql, user.getId());
//		if(list==null || list.size()==0)
//			return;
//		List<String> wareHouseId = new ArrayList<String>();
//		for (LBaContOperatorWarehouseEntity entity : list) {
//			wareHouseId.add(entity.getWarehouseId());
//		}
//		cq.in("id", wareHouseId.toArray(new String[0]));
	}
	/**
	 * 删除仓库表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(LBaWarehouseEntity lBaWarehouse, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		lBaWarehouse = systemService.getEntity(LBaWarehouseEntity.class, lBaWarehouse.getId());
		message = "仓库表删除成功";
		try{
			AjaxJson j1 = validel(lBaWarehouse.getId());
			if(!j1.isSuccess()){
				j1.setSuccess(true);
				return j1;
			}
			
			lBaWarehouseService.delete(lBaWarehouse);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "仓库表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	
	public AjaxJson validel(String id){
		AjaxJson json = new AjaxJson();
		
		String sql = "select '操作员仓库对照'  bill from l_ba_cont_operator_warehouse where warehouse_id = '"+id+"' "
				+ "UNION select '仓库物资对照'  bill from l_ba_cont_warehouse_material where warehouse_id = '"+id+"' "
				+ "UNION select '物资档案表'  bill from l_ba_material where warehouse_id = '"+id+"' "
				+ "UNION select '科室仓库对照'  bill from l_hi_depart_warehouse_rela where warehouse_id = '"+id+"' "
				+ "UNION select '需求申请详'  bill from l_pu_demand_apply_detail where warehouse_id = '"+id+"' "
				+ "UNION select '入库'  bill from l_st_in_ware where warehouse_id = '"+id+"' "
				+ "UNION select '盘点'  bill from l_st_inventory where warehouse_id = '"+id+"' "
				+ "UNION select '调拨'  bill from l_st_mobilise where out_warehouse_id = '"+id+"' or in_warehouse_id = '"+id+"' "
				+ "UNION select '调拨申请'  bill from l_st_mobilise_apply  where out_warehouse_id = '"+id+"' or in_warehouse_id = '"+id+"' "
				+ "UNION select '月结'  bill from l_st_monthly_period_balance where warehouse_id = '"+id+"' "
				+ "UNION select '出库'  bill from l_st_out_ware where warehouse_id = '"+id+"' "
				+ "UNION select '快捷出入库'  bill from l_st_quickinout where warehouse_id = '"+id+"' "
				+ "UNION select '库存期初'  bill from l_st_ware_balance where warehouse_id = '"+id+"' ";
		List<String> list = systemService.findListbySql(sql);
		if(list.size() > 0){
			json.setMsg(list.toString()+"引用仓库，删除失败");
			json.setSuccess(false);
			return json;
		}
		
		return json;
	}
	
	
	/**
	 * 批量删除仓库表
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "仓库表删除成功";
		try{
			for(String id:ids.split(",")){
				LBaWarehouseEntity lBaWarehouse = systemService.getEntity(LBaWarehouseEntity.class, 
				id
				);
				
				AjaxJson j1 = validel(id);
				if(!j1.isSuccess()){
					j1.setSuccess(true);
					return j1;
				}
				
				
				lBaWarehouseService.delete(lBaWarehouse);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "仓库表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加仓库表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(LBaWarehouseEntity lBaWarehouse, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "仓库表添加成功";
		try{
			//校验
			AjaxJson ajaxJson = vailRep(lBaWarehouse);
			if(!ajaxJson.isSuccess()){
				return ajaxJson;
			}
			
			TSUser tsUser = ResourceUtil.getSessionUserName();
			lBaWarehouse.setCreateBy(tsUser.getId());
			lBaWarehouse.setDepartId(tsUser.getDepartid());
			lBaWarehouse.setCreateDate(new Date());
			lBaWarehouseService.save(lBaWarehouse);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "仓库表添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新仓库表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(LBaWarehouseEntity lBaWarehouse, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "仓库表更新成功";
		LBaWarehouseEntity t = lBaWarehouseService.get(LBaWarehouseEntity.class, lBaWarehouse.getId());
		try {
			//校验
			AjaxJson ajaxJson = vailRep(lBaWarehouse);
			if(!ajaxJson.isSuccess()){
				return ajaxJson;
			}
			
			TSUser tsUser = ResourceUtil.getSessionUserName();
			lBaWarehouse.setUpdateDate(new Date());
			lBaWarehouse.setUpdateBy(tsUser.getId());
			
			MyBeanUtils.copyBeanNotNull2Bean(lBaWarehouse, t);
			lBaWarehouseService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "仓库表更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	public AjaxJson vailRep(LBaWarehouseEntity lBaWarehouse){
		AjaxJson j = new AjaxJson();
		List<LBaWarehouseEntity> l1 = lBaWarehouseService.findByQueryString(" from LBaWarehouseEntity where id != '"+lBaWarehouse.getId()+"' and warehouseCode = '"+lBaWarehouse.getWarehouseCode()+"'");
		if(l1.size() > 0){
			j.setMsg("仓库编码已经存在");
			j.setSuccess(false);
			return j;
		}
		
		List<LBaWarehouseEntity> l2 = lBaWarehouseService.findByQueryString(" from LBaWarehouseEntity where id != '"+lBaWarehouse.getId()+"' and warehouseName = '"+lBaWarehouse.getWarehouseName()+"'");
		if(l2.size() > 0){
			j.setMsg("仓库名称已经存在");
			j.setSuccess(false);
			return j;
		}
		
		if(lBaWarehouse.getIsLocation() ==0){
			List<LBaLocationDictEntity> l3 = lBaWarehouseService.findByQueryString(" from LBaLocationDictEntity where wareId = '"+lBaWarehouse.getId()+"' ");
			if(l3.size() > 0){
				j.setMsg("仓库已经存在货位,货位管理必须为“是”");
				j.setSuccess(false);
				return j;
			}
		}
		
		return j;
	}

	/**
	 * 仓库表新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(LBaWarehouseEntity lBaWarehouse, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(lBaWarehouse.getId())) {
			lBaWarehouse = lBaWarehouseService.getEntity(LBaWarehouseEntity.class, lBaWarehouse.getId());
			req.setAttribute("lBaWarehousePage", lBaWarehouse);
		}
		return new ModelAndView("cn/com/linkwide/ba/warehouse/lBaWarehouse-add");
	}
	/**
	 * 仓库表编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(LBaWarehouseEntity lBaWarehouse, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(lBaWarehouse.getId())) {
			lBaWarehouse = lBaWarehouseService.getEntity(LBaWarehouseEntity.class, lBaWarehouse.getId());
			//集团化业务过滤
			//List<Map<String,Object>> mList =systemService.findForJdbc("select m.warehouse_id,d.departname,m.org_code from l_ba_warehouse_org_map m left join t_s_depart d on  m.org_code = d.org_code where warehouse_id ='"+lBaWarehouse.getId()+"' and d.org_code like '"+ResourceUtil.getUserComponyCode()+"%' and d.org_type =1");
			List<Map<String,Object>> mList =systemService.findForJdbc("select m.warehouse_id,d.departname,m.org_code from l_ba_warehouse_org_map m left join t_s_depart d on  m.org_code = d.org_code where warehouse_id ='"+lBaWarehouse.getId()+"'  and d.org_type =1");
			for(Map<String,Object> m : mList){
				if(lBaWarehouse.getId().equals(m.get("warehouse_id").toString())){
					if(StringUtil.isNotEmpty(lBaWarehouse.getOrgCodes())){
						lBaWarehouse.setOrgCodes(lBaWarehouse.getOrgCodes()+","+m.get("org_code").toString()+"");
						lBaWarehouse.setOrgNames(lBaWarehouse.getOrgNames()+","+m.get("departname").toString());
					}else{
						lBaWarehouse.setOrgCodes(""+m.get("org_code").toString()+"");
						lBaWarehouse.setOrgNames(m.get("departname").toString());
					}
					
				}
			} 
			List<LBaLocationDictEntity> l3 = lBaWarehouseService.findByQueryString(" from LBaLocationDictEntity where wareId = '"+lBaWarehouse.getId()+"' ");
			if(l3.size() > 0){
				req.setAttribute("isLocation", 1);
			} 
			req.setAttribute("lBaWarehousePage", lBaWarehouse);
		}
		return new ModelAndView("cn/com/linkwide/ba/warehouse/lBaWarehouse-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","lBaWarehouseController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(LBaWarehouseEntity lBaWarehouse,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(LBaWarehouseEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, lBaWarehouse, request.getParameterMap());
		List<LBaWarehouseEntity> lBaWarehouses = this.lBaWarehouseService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"仓库表");
		modelMap.put(NormalExcelConstants.CLASS,LBaWarehouseEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("仓库表列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,lBaWarehouses);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(LBaWarehouseEntity lBaWarehouse,HttpServletRequest request,HttpServletResponse response,DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"仓库表");
    	modelMap.put(NormalExcelConstants.CLASS,LBaWarehouseEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("仓库表列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),"导出信息"));
    	modelMap.put(NormalExcelConstants.DATA_LIST,new ArrayList());
    	return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	
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
				List<LBaWarehouseEntity> listLBaWarehouseEntitys = ExcelImportUtil.importExcel(file.getInputStream(),LBaWarehouseEntity.class,params);
				for (LBaWarehouseEntity lBaWarehouse : listLBaWarehouseEntitys) {
					lBaWarehouseService.save(lBaWarehouse);
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
	public List<LBaWarehouseEntity> list() {
		List<LBaWarehouseEntity> listLBaWarehouses=lBaWarehouseService.getList(LBaWarehouseEntity.class);
		return listLBaWarehouses;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		LBaWarehouseEntity task = lBaWarehouseService.get(LBaWarehouseEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody LBaWarehouseEntity lBaWarehouse, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<LBaWarehouseEntity>> failures = validator.validate(lBaWarehouse);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		try{
			lBaWarehouseService.save(lBaWarehouse);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = lBaWarehouse.getId();
		URI uri = uriBuilder.path("/rest/lBaWarehouseController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody LBaWarehouseEntity lBaWarehouse) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<LBaWarehouseEntity>> failures = validator.validate(lBaWarehouse);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		try{
			lBaWarehouseService.saveOrUpdate(lBaWarehouse);
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
		lBaWarehouseService.deleteEntityById(LBaWarehouseEntity.class, id);
	}
	
	/**
	 * pda获取仓库档案
	 * @return
	  *@author zxl
	  *2018年7月27日
	 */
	@RequestMapping(params = "pdaGetWarehouse")
	@ResponseBody
	public AjaxJson pdaGetWarehouse(HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		String message ="查询成功";
		//主键集合
		String code = request.getParameter("code");
		try {
			String sql = " select id  \"id\" ,warehouse_code  \"code\" ,warehouse_name  \"name\" from l_ba_warehouse ";
			if(StringUtil.isNotEmpty(code)){
				sql +=" where  warehouse_code like  '%"+code+"%' or warehouse_name like '%"+code+"%'";
			}
			List<Map<String,Object>> list = systemService.findForJdbc(sql);
			j.setObj(list);
			j.setSuccess(true);
			
		}catch(BusinessException b){
			message=b.getMessage();
			j.setSuccess(false);
		}
		j.setMsg(message); 
		return j;
	}
}
