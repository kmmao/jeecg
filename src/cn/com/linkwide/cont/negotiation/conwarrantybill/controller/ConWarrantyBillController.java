package cn.com.linkwide.cont.negotiation.conwarrantybill.controller;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import org.jeecgframework.tag.vo.datatable.SortDirection;
import org.jeecgframework.web.system.pojo.base.TSFunction;
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

import cn.com.linkwide.common.tools.BillCodeUtil;
import cn.com.linkwide.cont.negotiation.conwarrantybill.entity.ConWarrantyBillEntity;
import cn.com.linkwide.cont.negotiation.conwarrantybill.service.ConWarrantyBillServiceI;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**   
 * @Title: Controller  
 * @Description: 议标保修单
 * @author onlineGenerator
 * @date 2019-04-20 11:37:21
 * @version V1.0   
 *
 */
@Api(value="ConWarrantyBill",description="议标保修单",tags="conWarrantyBillController")
@Controller
@RequestMapping("/conWarrantyBillController")
public class ConWarrantyBillController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ConWarrantyBillController.class);

	@Autowired
	private ConWarrantyBillServiceI conWarrantyBillService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 议标保修单列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("cn/com/linkwide/cont/negotiation/conwarrantybill/conWarrantyBillList");
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
	public void datagrid(ConWarrantyBillEntity conWarrantyBill,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
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
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.addOrder("createDate", SortDirection.desc);
		cq.add();
		
		this.conWarrantyBillService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除议标保修单
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(ConWarrantyBillEntity conWarrantyBill, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		conWarrantyBill = systemService.getEntity(ConWarrantyBillEntity.class, conWarrantyBill.getId());
		message = "议标保修单删除成功";
		try{
			conWarrantyBillService.delete(conWarrantyBill);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "议标保修单删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除议标保修单
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "议标保修单删除成功";
		try{
			for(String id:ids.split(",")){
				ConWarrantyBillEntity conWarrantyBill = systemService.getEntity(ConWarrantyBillEntity.class, 
				id
				);
				conWarrantyBillService.delete(conWarrantyBill);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "议标保修单删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	 
		/**
		 * 生成单据号
		 * @return
		 *2017年12月4日
		 *@author 
		 */
		public String generateBillCode(){
			String	billcode="";
		//	String hql=" from ";
			TSFunction function = this.conWarrantyBillService.findUniqueByProperty(TSFunction.class, "functionUrl", "conWarrantyBillController.do?list");
			if(function==null){
				throw new BusinessException("未设置合同信息的单据类型");
			}
			billcode=BillCodeUtil.productBillCode(function.getBillType());
			return billcode;
		}

	/**
	 * 添加议标保修单
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(ConWarrantyBillEntity conWarrantyBill, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "议标保修单添加成功";
		try{
			if((!"".equals(conWarrantyBill.getFactoryChoiceQt())&& conWarrantyBill.getFactoryChoiceQt()!=null)&& "0".equals(conWarrantyBill.getFactoryChoice())){
				conWarrantyBill.setFactoryChoice(conWarrantyBill.getFactoryChoiceQt());
			}
			if((!"".equals(conWarrantyBill.getGuaranteeTypeQt()) && conWarrantyBill.getGuaranteeTypeQt()!=null) && "0".equals(conWarrantyBill.getGuaranteeType()) ){
				conWarrantyBill.setGuaranteeType(conWarrantyBill.getGuaranteeTypeQt());
			}
			if((!"".equals(conWarrantyBill.getGuaranteeYearsQt())&& conWarrantyBill.getGuaranteeYearsQt()!=null)&& "0".equals(conWarrantyBill.getGuaranteeYears()) ){
				conWarrantyBill.setGuaranteeYears(conWarrantyBill.getGuaranteeYearsQt());
			}
			
			conWarrantyBill.setIsRelease("0");
			conWarrantyBill.setBillState("0");
			conWarrantyBill.setApplyCode(generateBillCode());
			conWarrantyBillService.save(conWarrantyBill);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "议标保修单添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新议标保修单
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(ConWarrantyBillEntity conWarrantyBill, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "议标保修单更新成功";
		ConWarrantyBillEntity t = conWarrantyBillService.get(ConWarrantyBillEntity.class, conWarrantyBill.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(conWarrantyBill, t);
			if((!"".equals(t.getFactoryChoiceQt())&& t.getFactoryChoiceQt()!=null)&& "0".equals(t.getFactoryChoice())){
				conWarrantyBill.setFactoryChoice(t.getFactoryChoiceQt());
			}
			if((!"".equals(t.getGuaranteeTypeQt())&& t.getGuaranteeTypeQt()!=null)&&"0".equals(t.getGuaranteeType()) ){
				conWarrantyBill.setGuaranteeType(t.getGuaranteeTypeQt());
			}
			if((!"".equals(t.getGuaranteeYearsQt())&& t.getGuaranteeYearsQt()!=null)&&"0".equals(t.getGuaranteeYears()) ){
				conWarrantyBill.setGuaranteeYears(t.getGuaranteeYearsQt());
			}
			conWarrantyBillService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "议标保修单更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 议标保修单新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(ConWarrantyBillEntity conWarrantyBill, HttpServletRequest req) {
		req.setAttribute("reqDate",new Date(System.currentTimeMillis()));
	/*	req.setAttribute("reqDept",ResourceUtil.getSessionUser().getDepartid());*/
		if (StringUtil.isNotEmpty(conWarrantyBill.getId())) {
			conWarrantyBill = conWarrantyBillService.getEntity(ConWarrantyBillEntity.class, conWarrantyBill.getId());
			req.setAttribute("conWarrantyBillPage", conWarrantyBill);
		}
		return new ModelAndView("cn/com/linkwide/cont/negotiation/conwarrantybill/conWarrantyBill-add");
	}
	/**
	 * 议标保修单编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(ConWarrantyBillEntity conWarrantyBill, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(conWarrantyBill.getId())) {
			conWarrantyBill = conWarrantyBillService.getEntity(ConWarrantyBillEntity.class, conWarrantyBill.getId());
			if(!"全保".equals(conWarrantyBill.getGuaranteeType()) &&!"技术保".equals(conWarrantyBill.getGuaranteeType())){
				conWarrantyBill.setGuaranteeType("0");
			}
			if(!"原厂家保修".equals(conWarrantyBill.getFactoryChoice()) && !"第三方厂家保修".equals(conWarrantyBill.getFactoryChoice())){
				conWarrantyBill.setFactoryChoice("0");
			}
			if(!"1".equals(conWarrantyBill.getGuaranteeYears()) &&!"2".equals(conWarrantyBill.getGuaranteeYears())&&!"3".equals(conWarrantyBill.getGuaranteeYears())&&!"4".equals(conWarrantyBill.getGuaranteeYears())&&!"5".equals(conWarrantyBill.getGuaranteeYears())){
				conWarrantyBill.setGuaranteeYears("0");
			}
			req.setAttribute("conWarrantyBillPage", conWarrantyBill);
		}
		return new ModelAndView("cn/com/linkwide/cont/negotiation/conwarrantybill/conWarrantyBill-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","conWarrantyBillController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(ConWarrantyBillEntity conWarrantyBill,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(ConWarrantyBillEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, conWarrantyBill, request.getParameterMap());
		List<ConWarrantyBillEntity> conWarrantyBills = this.conWarrantyBillService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"议标保修单");
		modelMap.put(NormalExcelConstants.CLASS,ConWarrantyBillEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("议标保修单列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,conWarrantyBills);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(ConWarrantyBillEntity conWarrantyBill,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"议标保修单");
    	modelMap.put(NormalExcelConstants.CLASS,ConWarrantyBillEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("议标保修单列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<ConWarrantyBillEntity> listConWarrantyBillEntitys = ExcelImportUtil.importExcel(file.getInputStream(),ConWarrantyBillEntity.class,params);
				for (ConWarrantyBillEntity conWarrantyBill : listConWarrantyBillEntitys) {
					conWarrantyBillService.save(conWarrantyBill);
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
	@ApiOperation(value="议标保修单列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<ConWarrantyBillEntity>> list() {
		List<ConWarrantyBillEntity> listConWarrantyBills=conWarrantyBillService.getList(ConWarrantyBillEntity.class);
		return Result.success(listConWarrantyBills);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取议标保修单信息",notes="根据ID获取议标保修单信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		ConWarrantyBillEntity task = conWarrantyBillService.get(ConWarrantyBillEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取议标保修单信息为空");
		}
		return Result.success(task);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建议标保修单")
	public ResponseMessage<?> create(@ApiParam(name="议标保修单对象")@RequestBody ConWarrantyBillEntity conWarrantyBill, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ConWarrantyBillEntity>> failures = validator.validate(conWarrantyBill);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			conWarrantyBillService.save(conWarrantyBill);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("议标保修单信息保存失败");
		}
		return Result.success(conWarrantyBill);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新议标保修单",notes="更新议标保修单")
	public ResponseMessage<?> update(@ApiParam(name="议标保修单对象")@RequestBody ConWarrantyBillEntity conWarrantyBill) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ConWarrantyBillEntity>> failures = validator.validate(conWarrantyBill);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			conWarrantyBillService.saveOrUpdate(conWarrantyBill);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("更新议标保修单信息失败");
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success("更新议标保修单信息成功");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除议标保修单")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" + id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			conWarrantyBillService.deleteEntityById(ConWarrantyBillEntity.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("议标保修单删除失败");
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
			if(w==0){
				
			message = "申请单提交成功";
					}
		if(w==1){
						
			message = "申请单撤回成功";
					}
		if(w==2){
			
			message = "申请单审核成功";
		}
		if(w==3){
			
			message = "申请单弃审成功";
		}
		if(w==4){
			
			message = "申请单发布成功";
		}
		if(w==5){
			
			message = "申请单取消发布成功";
		}
					
		String state = request.getParameter("state");
		try{
			for(String id:ids.split(",")){
				ConWarrantyBillEntity conwarrantybill = systemService.getEntity(ConWarrantyBillEntity.class,id); 
				
				
				if((conwarrantybill.getBillState()!=null &&  !"0".equals(conwarrantybill.getBillState())  ) && "1".equals(state)){
					
					message = "不是新建状态的单据不能提交！";
					j.setMsg(message);
					return j;
				}
			
		
				
			
					if(!"1".equals(conwarrantybill.getBillState())    && w==1){
				
					message = "不是提交状态的单据不能撤回！";
					j.setMsg(message);
					return j;
				}
			
				
			
				if( !"2".equals(conwarrantybill.getBillState())  && w==3){
					message = "不是审核状态的单据不能弃审！";
					j.setMsg(message);
					return j;
				}
				
				if(!"2".equals(conwarrantybill.getBillState())   && "3".equals(state) ){
					message ="不是审核通过的单据不能发布！";
					j.setMsg(message);
					return j;
				}
				

                if( !"3" .equals(conwarrantybill.getBillState()) && w==5){
                	
                	message ="不是招标状态的单据不能取消发布！";
                	j.setMsg(message);
					return j;
                }
			
				if("3".equals(conwarrantybill.getBillState()) && w==5){
					String sql="select count(a.id) from con_offer_bill a left join  con_warranty_bill b  on a.apply_id=b.id where  "
                			+ " b.id='"+conwarrantybill.getId()+"'";
                	try {
						Integer cont = Integer.valueOf(systemService.getCountForJdbc(sql.toString()).toString());
						if(cont>=1){
							message = "该议标已产生报价不能取消发布！";
							j.setMsg(message);
							return j;
						}
					} catch (Exception e) {
					
						e.printStackTrace();
					}
				}
				
		
			
				if(!"1".equals(conwarrantybill.getBillState())   && !"3".equals(conwarrantybill.getBillState())  &&"2".equals(state)  ){
					conwarrantybill.setConfirmEmp("");
					conwarrantybill.setConfirmDate(null);
					message ="不是提交状态的单据不能审核！";
				 	j.setMsg(message);
					return j;
				}
			
				
				
			
				if(w==2){
					
					conwarrantybill.setConfirmEmp(ResourceUtil.getSessionUser().getId());
					conwarrantybill.setConfirmDate((new Date(System.currentTimeMillis())));
				}
				if(w==3){
					conwarrantybill.setConfirmEmp("");
					conwarrantybill.setConfirmDate(null);
				}
				
				
				//只有开始招标和招标完成的是否发布才能是1
				if("3".equals(state)  ||"6".equals(state)){
					String appDate =request.getParameter("appDate");
					Date date;
					DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					date = format1.parse(appDate);
					conwarrantybill.setEndDate(date);
					conwarrantybill.setIsRelease("1");
					conwarrantybill.setReleaseDate((new Date(System.currentTimeMillis())));
				}else{
					conwarrantybill.setIsRelease("0");
					conwarrantybill.setEndDate(null);
					conwarrantybill.setReleaseDate(null);
				} 
				conwarrantybill.setBillState(state);
				conWarrantyBillService.saveOrUpdate(conwarrantybill);
					
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			if(w==0){
				
				message = "申请单提交失败";
						}
			if(w==1){
							
				message = "申请单撤回失败";
						}
			if(w==2){
				
				message = "申请单审核失败";
			}
			if(w==3){
				
				message = "申请单弃审失败";
			}
			if(w==4){
				
				message = "申请单发布失败";
			}
			if(w==5){
				
				message = "申请单发布失败";
			}
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	
	 /**
		 *招标发布
		 * 
		 * @return
		 */
		@RequestMapping(params = "deadLine")
		public ModelAndView deadLine(HttpServletRequest request) {
			return new ModelAndView("cn/com/linkwide/cont/negotiation/conwarrantybill/deadLineList");
		}
	
	
	
	
	
}
