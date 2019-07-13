package cn.com.linkwide.cont.negotiation.conofferbill.controller;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.buss.activiti.service.IWorkFlowQueryService;
import com.buss.activiti.service.IWorkFlowService;

import cn.com.linkwide.cont.coninformation.entity.ConInformationEntity;
import cn.com.linkwide.cont.negotiation.conofferbill.entity.ConOfferBillEntity;
import cn.com.linkwide.cont.negotiation.conofferbill.service.ConOfferBillServiceI;
import cn.com.linkwide.cont.negotiation.conwarrantybill.entity.ConWarrantyBillEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**   
 * @Title: Controller  
 * @Description: 云平台报价单
 * @author onlineGenerator
 * @date 2019-04-20 14:11:13
 * @version V1.0   
 *
 */
@Api(value="ConOfferBill",description="云平台报价单",tags="conOfferBillController")
@Controller
@RequestMapping("/conOfferBillController")
public class ConOfferBillController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ConOfferBillController.class);

	@Autowired
	private ConOfferBillServiceI conOfferBillService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	@Autowired
	private IWorkFlowService workFlowService;
	


	/**
	 * 云平台报价单列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("cn/com/linkwide/cont/negotiation/conofferbill/conOfferBillList");
	}

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	/*@RequestMapping(params = "datagrid")
	public void datagrid(ConOfferBillEntity conOfferBill,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ConOfferBillEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, conOfferBill, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.conOfferBillService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}*/
	
	
	@RequestMapping(params = "datagridyb")
	public void datagridyb(ConWarrantyBillEntity conWarrantyBill,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ConWarrantyBillEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, conWarrantyBill, request.getParameterMap());
		try{
		//自定义追加查询条件
		String query_applyDate_begin = request.getParameter("applyDate_begin");
		String query_applyDate_end = request.getParameter("applyDate_end");
		if(StringUtil.isNotEmpty(query_applyDate_begin)){
			cq.ge("applyDate", new SimpleDateFormat("yyyy-MM-dd").parse(query_applyDate_begin));
		}
		if(StringUtil.isNotEmpty(query_applyDate_end)){
			cq.le("applyDate", new SimpleDateFormat("yyyy-MM-dd").parse(query_applyDate_end));
		}
		String query_purDate_begin = request.getParameter("purDate_begin");
		String query_purDate_end = request.getParameter("purDate_end");
		if(StringUtil.isNotEmpty(query_purDate_begin)){
			cq.ge("purDate", new SimpleDateFormat("yyyy-MM-dd").parse(query_purDate_begin));
		}
		if(StringUtil.isNotEmpty(query_purDate_end)){
			cq.le("purDate", new SimpleDateFormat("yyyy-MM-dd").parse(query_purDate_end));
		}
		cq.eq("billState", "3");
		cq.eq("isRelease", "1");
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.conOfferBillService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	
	@RequestMapping(params = "datagrid")
	public void datagrid(ConOfferBillEntity conOfferBill, HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid) {
		try {
			
			String venId = request.getParameter("venId");
			String equName = request.getParameter("equName");
			String equCode =request.getParameter("equCode");
			String equCard =request.getParameter("equCard");
			String deptName=request.getParameter("deptName");
			 String dept_code=ResourceUtil.getSessionUser().getDepartid();
			// 自定义追加查询条件
			String query1 = "select a.id id ,b.id bId,b.apply_code applyCode,b.apply_date applyDate,b.equ_code equCode,a.equ_name equName,b.equ_card equCard,a.spec_typ specType,b.pur_price purPrice,a.offer_price offerPrice,a.pur_date purDate,a.birth_factory birthFactory,"
					+ " b.use_type useType,b.is_charge isCharge,b.average_cost averageCost,b.annual_check_number annualCheckNumber,b.annual_income annualIncome,b.apply_category ApplyCategory,b.guarantee_type guaranteeType,b.guarantee_type_qt guaranteeTypeQt,"
					+ "b.guarantee_years guaranteeYears,a.service_conten ServiceConten,a.bill_state billState,b.bill_state pBillState,b.dept_code deptCode,b.dept_name deptName,a.sys_org_code sysOrgCode,c.supplier_full_name supplierFullName,b.release_date releaseDate,a.confirm_date confirmDate,a.is_confirm isConfirm,a.extend1 extend1,a.offer_one offerOne,a.offer_two offerTwo,a.offer_three offerThree,a.offer_four offerFour,a.offer_five offerFive,a.offer_six offerSix,a.offer_seven offerSeven,a.offer_eight offerEight,a.offer_nine offerNine,a.offer_ten offerTen,a.workflow_id workflowId,a.conferm_state confermState  ";
					
			StringBuffer sql = new StringBuffer();
			sql.append("  from con_offer_bill a left join  con_warranty_bill b  on a.apply_id=b.id ");
			sql.append(" left join l_ba_supplier c on a.sys_org_code=c.id" );
			sql.append(" where 1=1      ");
			//sql.append(" and b.dept_code='"+dept_code+"'");
			String orderStr = "";
			if (StringUtil.isNotEmpty(dataGrid.getSort())) {
				orderStr = " order by " + dataGrid.getSort() + " " + dataGrid.getOrder();
			}
			if (StringUtil.isNotEmpty(venId)) {
				sql.append(" and a.sys_org_code = '" + venId + "' ");
			}
			if (StringUtil.isNotEmpty(equName)) {
				sql.append(" and b.equ_name like '%" + equName + "%' ");
			}
			if (StringUtil.isNotEmpty(equCode)) {
				sql.append(" and b.equ_code like '%" + equCode + "%' ");
			}
			if (StringUtil.isNotEmpty(equCard)) {
				sql.append(" and b.equ_card like '%" + equCard + "%' ");
			}
			if (StringUtil.isNotEmpty(deptName)) {
				sql.append(" and b.dept_name like '%" + deptName + "%' ");
			}
		/*	if(StringUtil.isNotEmpty(money_begin)){
				sql.append(" and a.money>="+money_begin);
			}
			if(StringUtil.isNotEmpty(money_end)){
				sql.append(" and a.money<="+money_end);
			}*/
		
			List<Map<String, Object>> list = systemService.findForJdbc(query1 + sql.toString() + orderStr,
					dataGrid.getPage(), dataGrid.getRows());
			dataGrid.setResults(list);
			List<Map<String, Object>> lisCount = systemService
					.findForJdbc("select count(a.id) as  count " + sql.toString(), null);
			for (Map<String, Object> map : lisCount) {
				dataGrid.setTotal(Integer.valueOf(map.get("count").toString()));
			}

		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		TagUtil.datagrid(response, dataGrid);
	}

	
	
	/**
	 * 删除云平台报价单
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(ConOfferBillEntity conOfferBill, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		conOfferBill = systemService.getEntity(ConOfferBillEntity.class, conOfferBill.getId());
		message = "云平台报价单删除成功";
		try{
			conOfferBillService.delete(conOfferBill);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "云平台报价单删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除云平台报价单
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "云平台报价单删除成功";
		try{
			for(String id:ids.split(",")){
				ConOfferBillEntity conOfferBill = systemService.getEntity(ConOfferBillEntity.class, 
				id
				);
				conOfferBillService.delete(conOfferBill);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "云平台报价单删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加云平台报价单
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(ConOfferBillEntity conOfferBill, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "设备报价成功";
		try{
			conOfferBillService.save(conOfferBill);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "设备报价单失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新云平台报价单
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(ConOfferBillEntity conOfferBill, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "议价成功";
		ConOfferBillEntity t = conOfferBillService.get(ConOfferBillEntity.class, conOfferBill.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(conOfferBill, t);
			t.setOfferTimes(t.getOfferTimes()+1);
			conOfferBillService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "议价失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 云平台报价单新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(ConOfferBillEntity conOfferBill, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(conOfferBill.getId())) {
			conOfferBill = conOfferBillService.getEntity(ConOfferBillEntity.class, conOfferBill.getId());
			req.setAttribute("conOfferBillPage", conOfferBill);
		}
		return new ModelAndView("cn/com/linkwide/cont/negotiation/conofferbill/conOfferBill-add");
	}
	/**
	 * 云平台报价单编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdateConfirm")
	public ModelAndView goUpdateConfirm(ConOfferBillEntity conOfferBill, HttpServletRequest req) {
		ConWarrantyBillEntity	conWarrantyBill = systemService.getEntity(ConWarrantyBillEntity.class, req.getParameter("id"));
		req.setAttribute("applyId", conWarrantyBill.getId());
		req.setAttribute("equName", conWarrantyBill.getEquName());
		req.setAttribute("specType",conWarrantyBill.getSpecType());
		req.setAttribute("purDate",conWarrantyBill.getPurDate());
		req.setAttribute("birthFactory", conWarrantyBill.getBirthFactory());
		return new ModelAndView("cn/com/linkwide/cont/negotiation/conofferbill/conOfferBill-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","conOfferBillController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(ConOfferBillEntity conOfferBill,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(ConOfferBillEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, conOfferBill, request.getParameterMap());
		List<ConOfferBillEntity> conOfferBills = this.conOfferBillService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"云平台报价单");
		modelMap.put(NormalExcelConstants.CLASS,ConOfferBillEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("云平台报价单列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,conOfferBills);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(ConOfferBillEntity conOfferBill,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"云平台报价单");
    	modelMap.put(NormalExcelConstants.CLASS,ConOfferBillEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("云平台报价单列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<ConOfferBillEntity> listConOfferBillEntitys = ExcelImportUtil.importExcel(file.getInputStream(),ConOfferBillEntity.class,params);
				for (ConOfferBillEntity conOfferBill : listConOfferBillEntitys) {
					conOfferBillService.save(conOfferBill);
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
	@ApiOperation(value="云平台报价单列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<ConOfferBillEntity>> list() {
		List<ConOfferBillEntity> listConOfferBills=conOfferBillService.getList(ConOfferBillEntity.class);
		return Result.success(listConOfferBills);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取云平台报价单信息",notes="根据ID获取云平台报价单信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		ConOfferBillEntity task = conOfferBillService.get(ConOfferBillEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取云平台报价单信息为空");
		}
		return Result.success(task);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建云平台报价单")
	public ResponseMessage<?> create(@ApiParam(name="云平台报价单对象")@RequestBody ConOfferBillEntity conOfferBill, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ConOfferBillEntity>> failures = validator.validate(conOfferBill);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			conOfferBillService.save(conOfferBill);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("云平台报价单信息保存失败");
		}
		return Result.success(conOfferBill);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新云平台报价单",notes="更新云平台报价单")
	public ResponseMessage<?> update(@ApiParam(name="云平台报价单对象")@RequestBody ConOfferBillEntity conOfferBill) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ConOfferBillEntity>> failures = validator.validate(conOfferBill);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			conOfferBillService.saveOrUpdate(conOfferBill);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("更新云平台报价单信息失败");
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success("更新云平台报价单信息成功");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除云平台报价单")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" + id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			conOfferBillService.deleteEntityById(ConOfferBillEntity.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("云平台报价单删除失败");
		}

		return Result.success();
	}
	
	
	
	

	 /**
	 * 批量审核议标申请
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchCheck")
	@ResponseBody
	public AjaxJson doBatchCheck(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		String message = "";
		Integer w=Integer.valueOf(request.getParameter("flag"));
		if(w==1){
						
			message = "报价单确认成功";
					}
		if(w==2){
			
			message = "报价单取消确认成功";
		}
		if(w==3){
			
			message = "操作成功";
		}
		if(w==4){
			
			message = "操作成功";
		}
					
		try{
			for(String id:ids.split(",")){
				ConOfferBillEntity conOfferBill = systemService.getEntity(ConOfferBillEntity.class,id); 
				
				if("0".equals(conOfferBill.getExtend1())  && w==1){
					message = "不合格的报价不能做确认操作！";
					j.setMsg(message);
					return j;
				}
				if((!"1".equals(conOfferBill.getExtend1())&& !"0".equals(conOfferBill.getExtend1()))  && w==1){
					message = "请先判断该报价供应商是否合格再做确认操作！";
					j.setMsg(message);
					return j;
				}
			
				
				if("1".equals(conOfferBill.getIsConfirm()) && w==4){
					message = "该报价已经确认通过不能做不合格操作！";
					j.setMsg(message);
					return j;
				}
				
				
				if("1".equals(conOfferBill.getIsConfirm()) && w==1){
					message = "该报价已经确认不能重复确认！";
					j.setMsg(message);
					return j;
				}
			
				
				if(!"1".equals(conOfferBill.getIsConfirm()) && w==2){
					message = "不是已确认的报价不能取消确认！";
					j.setMsg(message);
					return j;
				}
				
				if(w==3){
					conOfferBill.setExtend1("1");
					 
				}
				if(w==4){
					conOfferBill.setExtend1("0");
					conOfferBill.setBillState("未中标");
				}
				if(w==1){
					int flag=conOfferBill.getOfferTimes();
					if(flag==1){
						conOfferBill.setOfferPrice(conOfferBill.getOfferOne());
					}
					if(flag==2){
						conOfferBill.setOfferPrice(conOfferBill.getOfferTwo());
					}
					if(flag==3){
						conOfferBill.setOfferPrice(conOfferBill.getOfferThree());
					}
					if(flag==4){
						conOfferBill.setOfferPrice(conOfferBill.getOfferFour());
					}
					if(flag==5){
						conOfferBill.setOfferPrice(conOfferBill.getOfferFive());
					}
					if(flag==6){
						conOfferBill.setOfferPrice(conOfferBill.getOfferSix());
					}
					if(flag==7){
						conOfferBill.setOfferPrice(conOfferBill.getOfferSeven());
					}
					if(flag==8){
						conOfferBill.setOfferPrice(conOfferBill.getOfferEight());
					}
					if(flag==9){
						conOfferBill.setOfferPrice(conOfferBill.getOfferNine());
					}
					if(flag==10){
						conOfferBill.setOfferPrice(conOfferBill.getOfferTen());
					}
					conOfferBill.setIsConfirm("1");
					conOfferBill.setBillState("已确认");
					conOfferBill.setConfirmDate((new Date(System.currentTimeMillis())));
				}
				if(w==2){
					conOfferBill.setConfirmDate(null);
					conOfferBill.setBillState(null);
					conOfferBill.setOfferPrice(null);
					conOfferBill.setIsConfirm("0");
				}
			 
				conOfferBillService.saveOrUpdate(conOfferBill);
					
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			if(w==1){
							
				message = "报价单确认失败";
						}
			if(w==2){
				
				message = "报价单取消确认失败";
			}
			if(w==3){
				
				message = "操作失败";
			}
			if(w==4){
				
				message = "操作失败";
			}
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	 
	 
	 

		@RequestMapping(params = "goUpdateExchange")
		public ModelAndView goUpdateExchange(ConOfferBillEntity conOfferBill, HttpServletRequest req) {
			ConOfferBillEntity	conOfferBillb = systemService.getEntity(ConOfferBillEntity.class,req.getParameter("id"));
			int flag=conOfferBillb.getOfferTimes();
			if(flag==1){
				req.setAttribute("offerText","offerTwo");
				req.setAttribute("offerTariff",conOfferBillb.getOfferOne());
			}
			if(flag==2){
				req.setAttribute("offerText","offerThree");	
				req.setAttribute("offerTariff",conOfferBillb.getOfferTwo());
			}
			if(flag==3){
				req.setAttribute("offerText","offerFour");	
				req.setAttribute("offerTariff",conOfferBillb.getOfferThree());
			}
			if(flag==4){
				req.setAttribute("offerText","offerFive");
				req.setAttribute("offerTariff",conOfferBillb.getOfferFour());
			}
			if(flag==5){
				req.setAttribute("offerText","offerSix");
				req.setAttribute("offerTariff",conOfferBillb.getOfferFive());
			}
			if(flag==6){
				req.setAttribute("offerText","offerSeven");	
				req.setAttribute("offerTariff",conOfferBillb.getOfferSix());
			}
			if(flag==7){
				req.setAttribute("offerText","offerEight");	
				req.setAttribute("offerTariff",conOfferBillb.getOfferSeven());
			}
			if(flag==8){
				req.setAttribute("offerText","offerNine");	
				req.setAttribute("offerTariff",conOfferBillb.getOfferEight());
			}
			if(flag==9){
				req.setAttribute("offerText","offerTen");	
				req.setAttribute("offerTariff",conOfferBillb.getOfferTen());
			}
			req.setAttribute("conOfferBillPage",conOfferBillb);
			return new ModelAndView("cn/com/linkwide/cont/negotiation/conofferbill/conOfferBill-updateChange");
		}
	
	
/**
 * 提交
 * @param request
 * @return
 */
@RequestMapping(params = "goSubmit")
@ResponseBody
public AjaxJson goSubmit(HttpServletRequest request) {
	String message = null;
	AjaxJson j = new AjaxJson();
	String ids=request.getParameter("ids");
	String workflowId ="";
	String actReModelCode = request.getParameter("actReModelCode"); //工作流id
    List<Map<String,Object>> l1 = systemService.findForJdbc("select id_ id from act_re_procdef where version_=(select max(version_) from act_re_procdef where name_=?) and  name_=?",actReModelCode,actReModelCode);
			if(l1.size() > 0 ){
			workflowId = l1.get(0).get("id").toString();
			}else{
				j.setMsg("操作失败：审批流"+actReModelCode+"不存在!");
				return j;
				}
			if(StringUtil.isNotEmpty(ids) && StringUtil.isNotEmpty(workflowId)){
				String[] idsArr = ids.split(",");
				for (int i = 0; i < idsArr.length; i++) {
					ConOfferBillEntity entity = systemService.get(ConOfferBillEntity.class, idsArr[i]);
					if(!"退回".equals(entity.getConfermState())){
						entity.setWorkflowId(workflowId);
			}
			//提交审批，启动工作流
			conOfferBillService.approve(entity,actReModelCode);
		}
	}
	message="提交成功";
	j.setMsg(message);
	return j;
}

/**
 * 云平台报价单编辑页面跳转
 * 
 * @return
 */
@RequestMapping(params = "goUpdate")
public ModelAndView goUpdate(ConOfferBillEntity conOfferBill, HttpServletRequest req) {
 conOfferBill = systemService.getEntity(ConOfferBillEntity.class,conOfferBill.getId() );
 req.setAttribute("conOfferBillPage", conOfferBill);
	return new ModelAndView("cn/com/linkwide/cont/negotiation/conofferbill/conOfferBill-update");
}


@RequestMapping(params = "chekCreateBy")
@ResponseBody
public AjaxJson chekCreateBy( ConOfferBillEntity conOfferBill , HttpServletRequest req) {
	String message = "";
	AjaxJson j = new AjaxJson();
	
	try{
		String id = req.getParameter("id");
		if(StringUtil.isNotEmpty(id)){
			conOfferBill = conOfferBillService.getEntity(ConOfferBillEntity.class, id);
		message=conOfferBill.getCreateBy();
		}
	}catch(Exception e){
		e.printStackTrace();
		message = "";
		j.setMsg(message);
		return j;
	}
	j.setMsg(message);
	return j;
}


/**
 * 批量操作合同信息
 * 
 * @return
 */
 @RequestMapping(params = "doCeHui")
@ResponseBody
public AjaxJson doCeHui(String ids,HttpServletRequest request){
	AjaxJson j = new AjaxJson();
	String message = "";
		message = "报价单撤回成功！";
	
	try{
		for(String id:ids.split(",")){
			ConOfferBillEntity conInformation = systemService.getEntity(ConOfferBillEntity.class,id); 
			if(!"待审核".equals(conInformation.getConfermState())){
				message="不是已提交的数据不能撤回";
				j.setMsg(message);
				return j;
			}
			Map map=new HashMap();
			map.put("busId", conInformation.getId());
			map.put("workflowId", conInformation.getWorkflowId());
			String returnMsg =workFlowService.revokeWorkflowProcess(map);
			if(StringUtil.isEmpty(returnMsg)){
				conInformation.setConfermState("新建");
				conOfferBillService.saveOrUpdate(conInformation);
			}else{
				j.setMsg(returnMsg);
				return j;
			}
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}
	}catch(Exception e){
		e.printStackTrace();
			message = "报价单撤回失败";
		throw new BusinessException(e.getMessage());
	}
	j.setMsg(message);
	return j;
}
	
}
