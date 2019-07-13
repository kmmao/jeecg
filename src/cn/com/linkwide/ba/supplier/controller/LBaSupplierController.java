package cn.com.linkwide.ba.supplier.controller;
import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
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
import org.jeecgframework.core.util.PasswordUtil;
import org.jeecgframework.core.util.PinyinUtil;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.poi.exception.excel.ExcelImportException;
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

import com.alibaba.fastjson.JSONObject;

import cn.com.linkwide.ba.supplier.entity.LBaSupplierEntity;
import cn.com.linkwide.ba.supplier.service.LBaSupplierServiceI;
import cn.com.linkwide.ba.suppliertype.entity.LBaSupplierTypeEntity;
import cn.com.linkwide.ba.syn.SynchronousBa;

/**   
 * @Title: Controller  
 * @Description: 供应商
 * @author onlineGenerator
 * @date 2017-11-08 17:48:45
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/lBaSupplierController")
public class LBaSupplierController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(LBaSupplierController.class);
	@Autowired
	private LBaSupplierServiceI lBaSupplierService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 供应商列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("cn/com/linkwide/ba/supplier/lBaSupplierList");
	}
	
	@RequestMapping(params = "listForSelect")
	public ModelAndView listForSelect(HttpServletRequest request) {
		String mid = request.getParameter("mid");//物资id
		request.setAttribute("mid", mid);
		return new ModelAndView("cn/com/linkwide/ba/supplier/listForSelect");
	}
	
	@RequestMapping(params = "listForSelectAll")
	public ModelAndView listForSelectAll(HttpServletRequest request) {
		String mid = request.getParameter("mid");//物资id
		request.setAttribute("mid", mid);
		return new ModelAndView("cn/com/linkwide/ba/supplier/listForSelectAll");
	}

	
	
	

	/**
	 * 供应商列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "listzb")
	public ModelAndView listzb(HttpServletRequest request) {
		return new ModelAndView("cn/com/linkwide/bid/supfile/lBaSupplierList");
	}
	
	@RequestMapping(params = "listForSelectzb")
	public ModelAndView listForSelectzb(HttpServletRequest request) {
		String mid = request.getParameter("mid");//鐗╄祫id
		request.setAttribute("mid", mid);
		return new ModelAndView("cn/com/linkwide/bid/supfile/listForSelect");
	}
	
	@RequestMapping(params = "listForSelectAllzb")
	public ModelAndView listForSelectAllzb(HttpServletRequest request) {
		String mid = request.getParameter("mid");//鐗╄祫id
		request.setAttribute("mid", mid);
		return new ModelAndView("cn/com/linkwide/bid/supfile/listForSelectAll");
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
	public void datagrid(LBaSupplierEntity lBaSupplier,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(LBaSupplierEntity.class, dataGrid);
		
		if(StringUtils.isNotEmpty(lBaSupplier.getSupplierFullName())){
			lBaSupplier.setSupplierFullName("*"+lBaSupplier.getSupplierFullName()+"*");
		}
		
		if(StringUtils.isNotEmpty(lBaSupplier.getSupplierCode())){
			lBaSupplier.setSupplierCode("*"+lBaSupplier.getSupplierCode()+"*");
		}
		
		if(StringUtils.isNotEmpty(lBaSupplier.getSupplierShortName())){
			lBaSupplier.setSupplierShortName("*"+lBaSupplier.getSupplierShortName()+"*");
		}
		
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, lBaSupplier, request.getParameterMap());
		try{
		//自定义追加查询条件
			cq.addOrder("createDate", SortDirection.desc);
			//如果物资id不为空
			String mid = request.getParameter("mid");
			Set<String> bsid = new HashSet<String>();
			if(StringUtil.isNotEmpty(mid)){
				List<String> ids = systemService.findListbySql("select supplier_id from l_ba_cont_supplier_material where material_id = '"+mid+"'");
//				bsid.addAll(ids);
				cq.in("id", ids.toArray());
			}
			
			String supplierTypeIds = request.getParameter("supplierTypeIds");
			if(StringUtil.isNotEmpty(supplierTypeIds)){
				cq.in("supplierTypeId", supplierTypeIds.split(","));
			}
			
			//资质合格供应商id
//			List<String> sids = lSuSupplierQualItemService.getOverTimeSupplierIds(null);
////			bsid.addAll(sids);
//			if(sids.size() > 0){
//				cq.in("id", sids.toArray());
//			}
			
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.lBaSupplierService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	@RequestMapping(params = "datagridAll")
	public void datagridAll(LBaSupplierEntity lBaSupplier,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(LBaSupplierEntity.class, dataGrid); 
		if(StringUtils.isNotEmpty(lBaSupplier.getSupplierFullName())){
			lBaSupplier.setSupplierFullName("*"+lBaSupplier.getSupplierFullName()+"*");
		}
		
		if(StringUtils.isNotEmpty(lBaSupplier.getSupplierCode())){
			lBaSupplier.setSupplierCode("*"+lBaSupplier.getSupplierCode()+"*");
		}
		
		if(StringUtils.isNotEmpty(lBaSupplier.getSupplierShortName())){
			lBaSupplier.setSupplierShortName("*"+lBaSupplier.getSupplierShortName()+"*");
		}
		
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, lBaSupplier, request.getParameterMap());
		try{
		//自定义追加查询条件
			cq.addOrder("createDate", SortDirection.desc);
			
			String flag=request.getParameter("flag");
			if("1".equals(flag)){
					//lBaSupplier.setIsInviteBids("1");
				cq.eq("isInviteBids", "1");
				//cq.eq("auditingState", "审核");
				//cq.in("auditingState", new Object[]{"待审核",""});
			    //cq.isNull("auditingState");
			  //  cq.or(Restrictions.eq("auditingState","待审核"), Restrictions.isNull("auditingState"));
			    
			}else{
				//如果物资id不为空
				String mid = request.getParameter("mid");
				if(StringUtil.isNotEmpty(mid)){
					List<String> ids = systemService.findListbySql("select supplier_id from l_ba_cont_supplier_material where material_id = '"+mid+"'");
					if(ids.size() > 1){
						cq.in("id", ids.toArray());
					}else if(ids.size() == 1){
						cq.eq("id", ids.get(0));
					}else{
						cq.eq("id", "0");
					}
				}
				String supplierTypeIds = request.getParameter("supplierTypeIds");
				if(StringUtil.isNotEmpty(supplierTypeIds)){
					cq.in("supplierTypeId", supplierTypeIds.split(","));
				}
			}
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.lBaSupplierService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除供应商
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(LBaSupplierEntity lBaSupplier, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		lBaSupplier = systemService.getEntity(LBaSupplierEntity.class, lBaSupplier.getId());
		message = "供应商删除成功";
		try{
			//删除校验
			AjaxJson jj = valiDel(lBaSupplier.getId());
			if(!jj.isSuccess()){
				jj.setSuccess(true);
				return jj;
			}
			
			lBaSupplierService.delete(lBaSupplier);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "供应商删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	public AjaxJson valiDel(String id){
		AjaxJson j = new AjaxJson();
		//供应商和物资参照
		String sql = "select '供应商物资参照'  bill from l_ba_cont_supplier_material where supplier_id = '"+id+"' "
				+ "UNION select '物资档案'  bill from l_ba_material where supplier_id = '"+id+"' "
				+ "UNION select '领用申请'  bill from l_good_request_detail where supplier_id = '"+id+"' "
				+ "UNION select '需求申请'  bill from l_pu_demand_apply_detail where supplier_id = '"+id+"' "
				+ "UNION select '订单'  bill from l_pu_order where supplier_id = '"+id+"' "
				+ "UNION select '采购计划'  bill from l_pu_plan_detail where supplier_id = '"+id+"' "
				+ "UNION select '到货验收单'  bill from l_st_arri_acce where supplier_id = '"+id+"' "
				+ "UNION select '入库单'  bill from l_st_in_ware where supplier_id = '"+id+"' "
				+ "UNION select '盘点单'  bill from l_st_inventory_detail where supplier_id = '"+id+"' "
				+ "UNION select '月结'  bill from l_st_monthly_period_balance where supplier_id = '"+id+"' "
				+ "UNION select '快捷出入库'  bill from l_st_quickinout where supplier_id = '"+id+"' "
				+ "UNION select '期初库存'  bill from l_st_ware_balance_detail where supplier_id = '"+id+"' "
				+ "UNION select '供应商资质'  bill from l_su_supplier_qual where supplier_id = '"+id+"' ";
		List<String> list = systemService.findListbySql(sql);
		if(list.size() > 0){
			j.setMsg(list.toString()+"引用供应商，不可删除");
			j.setSuccess(false);
			return j;
		}
		return j;
	}
	
	/**
	 * 批量删除供应商
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "供应商删除成功";
		try{
			for(String id:ids.split(",")){
				//删除校验
				AjaxJson jj = valiDel(id);
				if(!jj.isSuccess()){
					jj.setSuccess(true);
					return jj;
				}
				
				LBaSupplierEntity lBaSupplier = systemService.getEntity(LBaSupplierEntity.class, 
				id
				);
				
				lBaSupplierService.delete(lBaSupplier);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "供应商删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加供应商
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(LBaSupplierEntity lBaSupplier, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "供应商添加成功";
		try{
			
			//校验
			AjaxJson ajaxJson = vailRep(lBaSupplier);
			if(!ajaxJson.isSuccess()){
				return ajaxJson;
			}
			
			TSUser tsUser = ResourceUtil.getSessionUserName();
			lBaSupplier.setCreateBy(tsUser.getId());
			lBaSupplier.setDepartId(tsUser.getDepartid());
			lBaSupplier.setCreateDate(new Date());
			lBaSupplier.setAuditingState("待审核");
			lBaSupplier.setIsCease("0");
			lBaSupplier.setPassword(PasswordUtil.encrypt("123456",lBaSupplier.getId(), PasswordUtil.getStaticSalt()));
			lBaSupplier.setSpllCode(PinyinUtil.getPinYinHeadChar(lBaSupplier.getSupplierFullName()));
			lBaSupplierService.save(lBaSupplier);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "供应商添加失败";
			j.setSuccess(false);
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	public AjaxJson vailRep(LBaSupplierEntity lBaSupplier){
		AjaxJson j = new AjaxJson();
		List<LBaSupplierEntity> lBaSupplierEntities1 = lBaSupplierService.findByQueryString(" from LBaSupplierEntity where id != '"+lBaSupplier.getId()+"' and supplierCode = '"+lBaSupplier.getSupplierCode()+"'");
		if(lBaSupplierEntities1.size() > 0){
			j.setMsg("供应商编码已经存在");
			j.setSuccess(false);
			return j;
		}
		
		List<LBaSupplierEntity> lBaSupplierEntities2 = lBaSupplierService.findByQueryString(" from LBaSupplierEntity where id != '"+lBaSupplier.getId()+"' and supplierFullName = '"+lBaSupplier.getSupplierFullName()+"'");
		if(lBaSupplierEntities2.size() > 0){
			j.setMsg("供应商名称已经存在");
			j.setSuccess(false);
			return j;
		}
		
		List<LBaSupplierEntity> lBaSupplierEntities3 = lBaSupplierService.findByQueryString(" from LBaSupplierEntity where id != '"+lBaSupplier.getId()+"' and supplierShortName = '"+lBaSupplier.getSupplierShortName()+"'");
		if(lBaSupplierEntities3.size() > 0){
			j.setMsg("供应商简称已经存在");
			j.setSuccess(false);
			return j;
		}
		return j;
	}
	
	/**
	 * 更新供应商
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(LBaSupplierEntity lBaSupplier, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "供应商更新成功";
		LBaSupplierEntity t = lBaSupplierService.get(LBaSupplierEntity.class, lBaSupplier.getId());
		try {
			
			//校验
			AjaxJson ajaxJson = vailRep(lBaSupplier);
			if(!ajaxJson.isSuccess()){
				return ajaxJson;
			}
			
			MyBeanUtils.copyBeanNotNull2Bean(lBaSupplier, t);
			TSUser tsUser = ResourceUtil.getSessionUserName();
			t.setUpdateBy(tsUser.getId());
			t.setUpdateDate(new Date());
			lBaSupplier.setSpllCode(PinyinUtil.getPinYinHeadChar(lBaSupplier.getSupplierFullName()));
			lBaSupplierService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "供应商更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 供应商新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(LBaSupplierEntity lBaSupplier, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(lBaSupplier.getId())) {
			lBaSupplier = lBaSupplierService.getEntity(LBaSupplierEntity.class, lBaSupplier.getId());
			req.setAttribute("lBaSupplierPage", lBaSupplier);
		}
		String supplierTypeId = req.getParameter("supplierTypeId");
		req.setAttribute("supplierTypeId", supplierTypeId);
		return new ModelAndView("cn/com/linkwide/ba/supplier/lBaSupplier-add");
	}
	/**
	 * 供应商编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(LBaSupplierEntity lBaSupplier, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(lBaSupplier.getId())) {
			lBaSupplier = lBaSupplierService.getEntity(LBaSupplierEntity.class, lBaSupplier.getId());
			req.setAttribute("lBaSupplierPage", lBaSupplier);
		}
		return new ModelAndView("cn/com/linkwide/ba/supplier/lBaSupplier-update");
	}
	
	
	/**
	 * 供应商编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdatebid")
	public ModelAndView goUpdatebid(LBaSupplierEntity lBaSupplier, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(lBaSupplier.getId())) {
			lBaSupplier = lBaSupplierService.getEntity(LBaSupplierEntity.class, lBaSupplier.getId());
			req.setAttribute("lBaSupplierPage", lBaSupplier);
		}
		return new ModelAndView("cn/com/linkwide/ba/supplier/lBaSupplier-updatebid");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","lBaSupplierController");
		req.setAttribute("method_name", "importExcel");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 更新式导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "updateUpload")
	public ModelAndView updateUpload(HttpServletRequest req) {
		req.setAttribute("controller_name","lBaSupplierController");
		req.setAttribute("method_name", "updateImportExcel");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(LBaSupplierEntity lBaSupplier,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(LBaSupplierEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, lBaSupplier, request.getParameterMap());
		List<LBaSupplierEntity> lBaSuppliers = this.lBaSupplierService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"供应商");
		modelMap.put(NormalExcelConstants.CLASS,LBaSupplierEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("供应商列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,lBaSuppliers);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(LBaSupplierEntity lBaSupplier,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"供应商");
    	modelMap.put(NormalExcelConstants.CLASS,LBaSupplierEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("供应商列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
    	"导出信息"));
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
			int row =4;
			String msg ="";
			try {
				List<LBaSupplierEntity> supList = new ArrayList<LBaSupplierEntity>();
				List<LBaSupplierEntity> listLBaSupplierEntitys = ExcelImportUtil.importExcel(file.getInputStream(),LBaSupplierEntity.class,params);
				if(listLBaSupplierEntitys.isEmpty() || listLBaSupplierEntitys.size() <1 ){
					j.setMsg("导入数据为空,请先维护档案");
					return j;
				}
				String hql =" from LBaSupplierTypeEntity where 1=1 ";
				String hql0 =" from LBaSupplierEntity where 1=1 ";
				Map<String,LBaSupplierTypeEntity> typeMap = systemService.getMapByHql(LBaSupplierTypeEntity.class, hql, "typeName");
				Map<String,LBaSupplierEntity> supMap = systemService.getMapByHql(LBaSupplierEntity.class, hql0, "uscc");
				Map<String,LBaSupplierEntity> cMap = systemService.getMapByHql(LBaSupplierEntity.class, hql0, "supplierCode");
				for (LBaSupplierEntity lBaSupplier : listLBaSupplierEntitys) {
					if(typeMap.get(lBaSupplier.getSupplierTypeId()) != null){
						lBaSupplier.setSupplierTypeId(typeMap.get(lBaSupplier.getSupplierTypeId()).getId()); 
					}else{   
						j.setMsg("第"+row+"行,供应商分类“"+lBaSupplier.getSupplierTypeId()+"”不存在!");
						return j;
					}
					if(StringUtil.isEmpty(lBaSupplier.getIsCease())){
						lBaSupplier.setIsCease("0");
					}
					if(supMap.containsKey(lBaSupplier.getUscc())){
						j.setMsg("第"+row+"行,统一社会信用代码“"+lBaSupplier.getUscc()+"已经被注册”!");
						return j;
					}else{
						supMap.put(lBaSupplier.getUscc(), lBaSupplier);
					}
					if(cMap.containsKey(lBaSupplier.getSupplierCode())){
						j.setMsg("第"+row+"行,供应商编码“"+lBaSupplier.getSupplierCode()+"已经被注册”!");
						return j;
					}else{
						cMap.put(lBaSupplier.getSupplierCode(), null);
					}
					row ++;
					lBaSupplier.setPassword(PasswordUtil.encrypt("123456",lBaSupplier.getId(), PasswordUtil.getStaticSalt()));
					lBaSupplier.setSpllCode(PinyinUtil.getPinYinHeadChar(lBaSupplier.getSupplierFullName()));
					supList.add(lBaSupplier);
				}
				//开始保存数据
				lBaSupplierService.save(supList);
				
				j.setMsg("文件导入成功！");
			}catch (ExcelImportException e) {
				j.setMsg(e.getErrorMsg());
				logger.error(ExceptionUtil.getExceptionMessage(e)); 
				
			} catch (Exception e) {
				j.setMsg("文件导入失败！"+e.getMessage());
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
	
	/**
	 * 更新式导入
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "updateImportExcel", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson updateImportExcel(HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		//供应商档案编码idmap
		String sql =" select id,supplier_code \"supplierCode\" from l_ba_supplier ";
		Map suppMap= new HashMap();
		List<Map<String,Object>> suppList =systemService.findForJdbc(sql, new Object[]{});
//		suppMap=systemService.list2Map(suppList, "supplierCode");
		if(suppList!=null && suppList.size()>0){
			for (Map<String, Object> map : suppList) {
				suppMap.put(map.get("supplierCode"), map.get("id"));
			}
		}
		
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			MultipartFile file = entity.getValue();// 获取上传文件对象
			ImportParams params = new ImportParams();
			params.setTitleRows(2);
			params.setHeadRows(1);
			params.setNeedSave(true);
			int row =4;
			String msg ="";
			try {
				List<LBaSupplierEntity> supList = new ArrayList<LBaSupplierEntity>();
				List<LBaSupplierEntity> listLBaSupplierEntitys = ExcelImportUtil.importExcel(file.getInputStream(),LBaSupplierEntity.class,params);
				String hql =" from LBaSupplierTypeEntity where 1=1 ";
				Map<String,LBaSupplierTypeEntity> typeMap = systemService.getMapByHql(LBaSupplierTypeEntity.class, hql, "typeName");
				Map<String,LBaSupplierEntity> supMap = systemService.getMapByHql(LBaSupplierEntity.class, hql, "uscc");
				Map<String,LBaSupplierEntity> supCodeMap = systemService.getMapByHql(LBaSupplierEntity.class, hql, "supplierCode");
				for (LBaSupplierEntity lBaSupplier : listLBaSupplierEntitys) {
					
					lBaSupplier.setPassword(PasswordUtil.encrypt("123456",lBaSupplier.getId(), PasswordUtil.getStaticSalt()));
					lBaSupplier.setSpllCode(PinyinUtil.getPinYinHeadChar(lBaSupplier.getSupplierFullName()));
					//供应商编码
					String supplierCode = lBaSupplier.getSupplierCode();
					if(StringUtil.isNotEmpty(supplierCode)){
						if(suppMap!= null && supplierCode.equals(supplierCode)&&StringUtil.isNotEmpty(suppMap.get(supplierCode))){ //修改
							lBaSupplier.setId(suppMap.get(supplierCode).toString());
							lBaSupplierService.saveOrUpdate(lBaSupplier);
						}else{ //添加
							if(typeMap.get(lBaSupplier.getSupplierTypeId()) != null){
								lBaSupplier.setSupplierTypeId(typeMap.get(lBaSupplier.getSupplierTypeId()).getId()); 
							}else{   
								j.setMsg("第"+row+"行,供应商分类“"+lBaSupplier.getSupplierTypeId()+"”不存在!");
								return j;
							}
							row ++;
							if(StringUtil.isEmpty(lBaSupplier.getIsCease())){
								lBaSupplier.setIsCease("0");
							}
							if(supMap.containsKey(lBaSupplier.getUscc())){
								j.setMsg("第"+row+"行,统一社会信用代码“"+lBaSupplier.getUscc()+"已经被注册”!");
								return j;
							}else{
								supMap.put(lBaSupplier.getUscc(), lBaSupplier);
							}
							if(supCodeMap.containsKey(lBaSupplier.getSupplierCode())){
								j.setMsg("第"+row+"行,供应商编码“"+lBaSupplier.getSupplierCode()+"已存在,或重复”!");
								return j;
							}else{
								supCodeMap.put(lBaSupplier.getSupplierCode(), null);
							}
							supList.add(lBaSupplier);
						}
					}else{
						j.setMsg("第"+row+"行,供应商编码不能为空");
						return j;
					}
					
				}
				//开始保存数据
				lBaSupplierService.save(supList);
				
				j.setMsg("文件导入成功！");
			}catch (ExcelImportException e) {
				j.setMsg(e.getErrorMsg());
				logger.error(ExceptionUtil.getExceptionMessage(e)); 
				
			} catch (Exception e) {
				j.setMsg("文件导入失败！"+e.getMessage());
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
	public List<LBaSupplierEntity> list() {
		List<LBaSupplierEntity> listLBaSuppliers=lBaSupplierService.getList(LBaSupplierEntity.class);
		return listLBaSuppliers;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		LBaSupplierEntity task = lBaSupplierService.get(LBaSupplierEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody LBaSupplierEntity lBaSupplier, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<LBaSupplierEntity>> failures = validator.validate(lBaSupplier);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		try{
			lBaSupplierService.save(lBaSupplier);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = lBaSupplier.getId();
		URI uri = uriBuilder.path("/rest/lBaSupplierController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody LBaSupplierEntity lBaSupplier) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<LBaSupplierEntity>> failures = validator.validate(lBaSupplier);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		try{
			lBaSupplierService.saveOrUpdate(lBaSupplier);
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
		lBaSupplierService.deleteEntityById(LBaSupplierEntity.class, id);
	}
	
	
	/**
	 * 供应商下拉
	 * 
	 * @author zhangxilong 2018-6-19
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "supplierTreeList")
	@ResponseBody
	public Object supplierTreeList(HttpServletRequest request) { 
		String name = request.getParameter("q") != null ? request.getParameter("q") : "";
		String hql = "FROM LBaSupplierEntity WHERE  supplierFullName like '%"+name+"%' ";
		List<LBaSupplierEntity> list = systemService.findHqlPage(hql,0,50);
		Object json = JSONObject.toJSON(list);
		return json;
	}
	
	/**
	 * 人员档案查询
	 * @param request
	 * @return
	 */
	@RequestMapping(params="loadUser")
	@ResponseBody
	public Object loadUser(HttpServletRequest request) {
		String q=request.getParameter("q")!=null?request.getParameter("q"):"";
		q=q.replaceAll("'", "");
		//查询所有人员
		String hql="from TSUser  ";
		List<TSUser> userList = systemService.findHql(hql);
		return userList;
	}
	
	
	/**
	 * 供应商档案审核
	 * @param year
	 * @param request
	 * @return
	  *@author liuxt
	  *2018年1月15日
	 */
		@RequestMapping(params = "doChecked")
		@ResponseBody
		public AjaxJson doChecked(HttpServletRequest request) {
			AjaxJson j = new AjaxJson();
			String message ="弃审成功";
			//主键集合
			try {
				String ids=request.getParameter("ids");
				if(StringUtil.isEmpty(ids)){
					throw new BusinessException("请选择数据");
				}
				String[] split = ids.split(",");
				//TSUser user = ResourceUtil.getSessionUser();
				//String userName = user.getUserName();
				//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				for (int i = 0; i < split.length; i++) {
					LBaSupplierEntity t = systemService.get(LBaSupplierEntity.class, split[i]);
					//是否停用
					String isCease = t.getIsCease();
					if("1".equals(isCease)){ //停用
						throw new BusinessException("供应商已停用，不能审核");
					}
					if(!"审核".equals(t.getAuditingState())){
						throw new BusinessException("不是审核状态的供应商不能弃审");
					}
					t.setAuditingState("待审核");
					t.setAuditor("");
					t.setIsLogin("0");
					t.setAuditingDate(null);
					lBaSupplierService.saveOrUpdate(t);
					String sql1 =SynchronousBa.synDelete(t);
					if(StringUtil.isNotEmpty(sql1)){
						systemService.updateBySqlString(sql1);
					}
					String sql2 =SynchronousBa.synSave(t);
					if(StringUtil.isNotEmpty(sql2)){
						systemService.updateBySqlString(sql2);
					}
				}
			}catch(BusinessException b){
				message=b.getMessage();
			} catch (Exception e) {
				message="弃审失败";
			}
			j.setMsg(message);
			return j;
		}
	
	
	
	
	
	/**
	 * 供应商档案审核
	 * @param year
	 * @param request
	 * @return
	  *@author liuxt
	  *2018年1月15日
	 */
		@RequestMapping(params = "doCheck")
		@ResponseBody
		public AjaxJson doCheck(HttpServletRequest request) {
			AjaxJson j = new AjaxJson();
			String message ="审核成功";
			//主键集合
			try {
				String ids=request.getParameter("ids");
				if(StringUtil.isEmpty(ids)){
					throw new BusinessException("请选择数据");
				}
				String[] split = ids.split(",");
				TSUser user = ResourceUtil.getSessionUser();
				String userName = user.getUserName();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				for (int i = 0; i < split.length; i++) {
					LBaSupplierEntity t = systemService.get(LBaSupplierEntity.class, split[i]);
					//是否停用
					String isCease = t.getIsCease();
					if("1".equals(isCease)){ //停用
						throw new BusinessException("供应商已停用，不能审核");
					}
					t.setAuditingState("审核");
					t.setAuditor(userName);
					t.setIsLogin("1");
					t.setAuditingDate(new Date());
					systemService.saveOrUpdate(t);
				}
			}catch(BusinessException b){
				message=b.getMessage();
			} catch (Exception e) {
				message="审核失败";
			}
			j.setMsg(message);
			return j;
		}
		
/**
 * 供应商档案停用
 * @param year
 * @param request
 * @return
  *@author liuxt
  *2018年1月15日
 */
	@RequestMapping(params = "doStop")
	@ResponseBody
	public AjaxJson doStop(HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		String message ="停用成功";
		//主键集合
		try {
			String ids=request.getParameter("ids");
			if(StringUtil.isEmpty(ids)){
				throw new BusinessException("请选择数据");
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			TSUser user = ResourceUtil.getSessionUser();
			String userName = user.getUserName();
			String[] split = ids.split(",");
			for (int i = 0; i < split.length; i++) {
				LBaSupplierEntity t = systemService.get(LBaSupplierEntity.class, split[i]);
				t.setIsCease("1");
				t.setCeasePeople(userName);
				t.setCeaseDate(new Date());
				lBaSupplierService.saveOrUpdate(t);
			}
		}catch(BusinessException b){
			message=b.getMessage();
		} catch (Exception e) {
			message="停用失败";
		}
		j.setMsg(message);
		return j;
}
			
			
/**
 * 供应商档案启用
 * @param year
 * @param request
 * @return
  *@author liuxt
  *2018年1月15日
 */
	@RequestMapping(params = "doStart")
	@ResponseBody
	public AjaxJson doStart(HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		String message ="启用成功";
		//主键集合
		try {
			String ids=request.getParameter("ids");
			if(StringUtil.isEmpty(ids)){
				throw new BusinessException("请选择数据");
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			TSUser user = ResourceUtil.getSessionUser();
			String userName = user.getUserName();
			String[] split = ids.split(",");
			for (int i = 0; i < split.length; i++) {
				LBaSupplierEntity t = systemService.get(LBaSupplierEntity.class, split[i]);
				t.setIsCease("0");
				t.setCeasePeople(userName);
				t.setCeaseDate(new Date());
				systemService.updateEntitie(t);
			}
		}catch(BusinessException b){
			message=b.getMessage();
		} catch (Exception e) {
			message="停用失败";
		}
		j.setMsg(message);
		return j;
	}			
/**
 * pda获取供应商档案
 * @return
  *@author zxl
  *2018年7月15日
 */
	@RequestMapping(params = "pdaGetSuppliers")
	@ResponseBody
	public AjaxJson pdaGetSuppliers(HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		String message ="查询成功";
		String code = request.getParameter("code");
		//主键集合
		try {
			String sql = " select id  \"id\" ,supplier_code  \"code\",supplier_full_name  \"name\" from l_ba_supplier ";
			if(StringUtil.isNotEmpty(code)){
				sql +=" where mnemonic_code like '"+code+"%' or supplier_code like  '%"+code+"%' or supplier_full_name like '%"+code+"%'";
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
	/**
	 * 供应商参照模糊查询
	 * 
	 * @author zhuhd 2018-3-27
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "incl")
	@ResponseBody
	public void incl(LBaSupplierEntity lBaSupplier, HttpServletRequest request, DataGrid dataGrid,
			HttpServletResponse response) throws Exception {
		dataGrid.setField("id,id_begin,id_end,supplierCode,supplierFullName,supplierShortName,supplierTypeId,supplierTypeId_begin,supplierTypeId_end,contacts,contacts_begin,contacts_end,telphone,telphone_begin,telphone_end,address,address_begin,address_end,isControl,auditingState,auditingState_begin,auditingState_end,isCease,isCease_begin,isCease_end,");
		String q = request.getParameter("q") != null ? request.getParameter("q") : "";
		CriteriaQuery cq = new CriteriaQuery(LBaSupplierEntity.class, dataGrid);
		//cq.setIsCollectivize(false);
		// 查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, lBaSupplier);
		try {
			// 自定义追加查询条件
			Disjunction disjunction = Restrictions.disjunction();
			disjunction.add(Restrictions.like("supplierCode", q, MatchMode.ANYWHERE).ignoreCase());
			disjunction.add(Restrictions.like("supplierFullName", q, MatchMode.ANYWHERE).ignoreCase());
			disjunction.add(Restrictions.like("supplierShortName", q, MatchMode.ANYWHERE).ignoreCase());
			cq.add(disjunction);
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.lBaSupplierService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	
	/**
	 * 批量删除供应商
	 * 
	 * @return
	 */
	 @RequestMapping(params = "resetPwd")
	@ResponseBody
	public AjaxJson resetPwd(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "供应商密码已重置为123456";
		try{
			for(String id:ids.split(",")){
				LBaSupplierEntity lBaSupplier = systemService.getEntity(LBaSupplierEntity.class, id);
				lBaSupplier.setPassword(PasswordUtil.encrypt("123456",lBaSupplier.getId(), PasswordUtil.getStaticSalt()));
				lBaSupplierService.saveOrUpdate(lBaSupplier);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "供应商密码已重置失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	 @RequestMapping(params = "synToU8")
		@ResponseBody
		public AjaxJson synToU8(HttpServletRequest request) {
			String message = null;
			AjaxJson j = new AjaxJson();
			message = "同步成功";
			try{
				String hql ="from LBaSupplierEntity ";
				List<LBaSupplierEntity> list = systemService.findHql(hql, null);
				if(list!= null && list.size()>0){
					lBaSupplierService.sendVouch(list);
				}
			}catch(Exception e){
				e.printStackTrace();
				message = "同步失败";
				j.setSuccess(false);
				throw new BusinessException(e.getMessage());
			}
			j.setMsg(message);
			return j;
		}
}
