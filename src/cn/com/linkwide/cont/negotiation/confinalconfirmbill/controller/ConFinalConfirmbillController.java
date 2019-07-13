package cn.com.linkwide.cont.negotiation.confinalconfirmbill.controller;
import java.io.IOException;
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

import cn.com.linkwide.cont.negotiation.confinalconfirmbill.entity.ConFinalConfirmbillEntity;
import cn.com.linkwide.cont.negotiation.confinalconfirmbill.service.ConFinalConfirmbillServiceI;
import cn.com.linkwide.cont.negotiation.conofferbill.entity.ConOfferBillEntity;
import cn.com.linkwide.cont.negotiation.conwarrantybill.entity.ConWarrantyBillEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**   
 * @Title: Controller  
 * @Description: 终审单
 * @author onlineGenerator
 * @date 2019-04-28 19:18:04
 * @version V1.0   
 *
 */
@Api(value="ConFinalConfirmbill",description="终审单",tags="conFinalConfirmbillController")
@Controller
@RequestMapping("/conFinalConfirmbillController")
public class ConFinalConfirmbillController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ConFinalConfirmbillController.class);

	@Autowired
	private ConFinalConfirmbillServiceI conFinalConfirmbillService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 终审单列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("cn/com/linkwide/cont/negotiation/confinalconfirmbill/conFinalConfirmbillList");
	}

	
	@RequestMapping(params = "refDetailf")
	public ModelAndView refDetailf(HttpServletRequest req) {
		Date date = new Date();
		SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
		req.setAttribute("jhDate", dateFormat.format(date));
		return new ModelAndView("cn/com/linkwide/cont/negotiation/confinalconfirmbill/importReqBillList");
	}
	
	
	
	@RequestMapping(params = "impotfift")
	@ResponseBody
	public void impotfift(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		
		try{
			String equName=request.getParameter("equName");
			String specType=request.getParameter("specType");
			String deptName=request.getParameter("deptName");
			//处理查询条件
			StringBuffer sql = new StringBuffer();
			
			String  head =" select a.id id,a.equ_name equName,b.spec_type specType,a.ven_name venName,a.dept_name deptName,a.service_conten serviceConten,a.offer_price offerPrice,b.pur_price purPrice,a.guarantee_type guaranteeType,a.extend1 extend1  ";
			
			sql.append(" from con_req_instructions_detail a left join  con_warranty_bill b on a.apply_id=b.id   ");
			sql.append(" where 1=1  ");
			
			sql.append("  and is_final_pass='1' and  a.id not in (select id from con_final_confirmBill ) ");
			if(StringUtil.isNotEmpty(deptName)){
				sql.append(" and  a.dept_name like '%"+deptName+ "%'");
			}
			if(StringUtil.isNotEmpty(equName)){
				sql.append(" and a.equ_name like '%"+equName+"'");
			}
			if(StringUtil.isNotEmpty(specType)){
				sql.append(" and b.spec_type like '%"+specType+"'");
			}
			
			dataGrid.setResults(systemService.findForJdbc(head+sql.toString(), dataGrid.getPage(), dataGrid.getRows()));
			Integer cont = Integer.valueOf(systemService.getCountForJdbc("select count(1) "+sql.toString()).toString());
			dataGrid.setTotal(cont);
		 
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		} 
		TagUtil.datagrid(response, dataGrid);
	}
	
	
	
	
	
	
	/**
	 * 添加招标计划
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "goimportfifAdd")
	@ResponseBody
	public AjaxJson goimportfifAdd( HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		
		String tableids=request.getParameter("demendDetails");
		message = "终审单添加成功";
		String[] split = tableids.split(",");
		for (int i = 0; i < split.length; i++) {
			StringBuffer sql = new StringBuffer();
			sql.append(" select  a.id \"id\", a.offer_id \"offerId\",a.apply_id \"applyId\",c.equ_code \"equCode\",a.equ_name \"equName\",c.spec_type \"specType\",a.guarantee_type \"graranteeType\",a.ven_id \"venId\",a.ven_name \"venName\",a.dept_id \"deptId\" ,a.dept_name \"deptName\" ,a.offer_price \"offerPrice\",a.PUR_PRICE \"purPrice\",a.service_conten \"serviceConten\",a.extend1 \"extend1\" from con_req_instructions_detail a left  join   CON_WARRANTY_BILL c on a.apply_id =c.id  ");
			sql.append(" where a.id='"+split[i]+"'"  );
		 
	 
		try{
			ConFinalConfirmbillEntity conFinalConfirmbill =null;
			List<Map<String,Object>>list =systemService.findForJdbc(sql.toString(), null);
			for(Map<String,Object> map:list){
		    conFinalConfirmbill =new ConFinalConfirmbillEntity();
			conFinalConfirmbill.setReqId((map.get("id").toString()));
			if(StringUtil.isNotEmpty(map.get("offerId"))){
				conFinalConfirmbill.setOfferId(map.get("offerId").toString());
			}
			if(StringUtil.isNotEmpty(map.get("applyId"))){
				conFinalConfirmbill.setApplyId((map.get("applyId").toString()));
			}
			if(StringUtil.isNotEmpty(map.get("equName"))){
				conFinalConfirmbill.setEquName(map.get("equName").toString());
			}
			if(StringUtil.isNotEmpty(map.get("specType"))){
				conFinalConfirmbill.setSpecType(map.get("specType").toString());
			}
			if(StringUtil.isNotEmpty(map.get("equCode"))){
				conFinalConfirmbill.setEquCode(map.get("equCode").toString());
			}
			if(StringUtil.isNotEmpty(map.get("deptId"))){
				conFinalConfirmbill.setDeptId(map.get("deptId").toString());
			}
			if(StringUtil.isNotEmpty(map.get("deptName"))){
				conFinalConfirmbill.setDeptName(map.get("deptName").toString());
			}
			if(StringUtil.isNotEmpty(map.get("venId"))){
				conFinalConfirmbill.setVenId(map.get("venId").toString());
			}
			if(StringUtil.isNotEmpty(map.get("venName"))){
				conFinalConfirmbill.setVenName(map.get("venName").toString());
			}
			if(StringUtil.isNotEmpty(map.get("offerPrice"))){
				conFinalConfirmbill.setOfferPrice(map.get("offerPrice").toString());
			}
			if(StringUtil.isNotEmpty(map.get("serviceConten"))){
				conFinalConfirmbill.setSeviceContent(map.get("serviceConten").toString());
			}
			if(StringUtil.isNotEmpty(map.get("purPrice"))){
				conFinalConfirmbill.setPurPrice(map.get("purPrice").toString());
			}
			if(StringUtil.isNotEmpty(map.get("extend1"))){
				conFinalConfirmbill.setOfferOne(map.get("extend1").toString());
			}
			
			conFinalConfirmbillService.save(conFinalConfirmbill);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		}catch(Exception e){
			e.printStackTrace();
			message = "终身单添加失败";
			throw new BusinessException(e.getMessage());
		}
		
	}
		j.setMsg(message);
		return j;
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
	public void datagrid(ConFinalConfirmbillEntity conFinalConfirmbill,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ConFinalConfirmbillEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, conFinalConfirmbill, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.conFinalConfirmbillService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除终审单
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(ConFinalConfirmbillEntity conFinalConfirmbill, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		conFinalConfirmbill = systemService.getEntity(ConFinalConfirmbillEntity.class, conFinalConfirmbill.getId());
		message = "终审单删除成功";
		try{
			conFinalConfirmbillService.delete(conFinalConfirmbill);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "终审单删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除终审单
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "终审单删除成功";
		try{
			for(String id:ids.split(",")){
				ConFinalConfirmbillEntity conFinalConfirmbill = systemService.getEntity(ConFinalConfirmbillEntity.class, 
				id
				);
				conFinalConfirmbillService.delete(conFinalConfirmbill);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "终审单删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加终审单
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(ConFinalConfirmbillEntity conFinalConfirmbill, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "终审单添加成功";
		try{
			conFinalConfirmbillService.save(conFinalConfirmbill);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "终审单添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新终审单
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(ConFinalConfirmbillEntity conFinalConfirmbill, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "终审单更新成功";
		ConFinalConfirmbillEntity t = conFinalConfirmbillService.get(ConFinalConfirmbillEntity.class, conFinalConfirmbill.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(conFinalConfirmbill, t);
			conFinalConfirmbillService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "终审单更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 终审单新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(ConFinalConfirmbillEntity conFinalConfirmbill, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(conFinalConfirmbill.getId())) {
			conFinalConfirmbill = conFinalConfirmbillService.getEntity(ConFinalConfirmbillEntity.class, conFinalConfirmbill.getId());
			req.setAttribute("conFinalConfirmbillPage", conFinalConfirmbill);
		}
		return new ModelAndView("cn/com/linkwide/cont/negotiation/confinalconfirmbill/conFinalConfirmbill-add");
	}
	/**
	 * 终审单编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(ConFinalConfirmbillEntity conFinalConfirmbill, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(conFinalConfirmbill.getId())) {
			conFinalConfirmbill = conFinalConfirmbillService.getEntity(ConFinalConfirmbillEntity.class, conFinalConfirmbill.getId());
			req.setAttribute("conFinalConfirmbillPage", conFinalConfirmbill);
		}
		return new ModelAndView("cn/com/linkwide/cont/negotiation/confinalconfirmbill/conFinalConfirmbill-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","conFinalConfirmbillController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(ConFinalConfirmbillEntity conFinalConfirmbill,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(ConFinalConfirmbillEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, conFinalConfirmbill, request.getParameterMap());
		List<ConFinalConfirmbillEntity> conFinalConfirmbills = this.conFinalConfirmbillService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"终审单");
		modelMap.put(NormalExcelConstants.CLASS,ConFinalConfirmbillEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("终审单列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,conFinalConfirmbills);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(ConFinalConfirmbillEntity conFinalConfirmbill,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"终审单");
    	modelMap.put(NormalExcelConstants.CLASS,ConFinalConfirmbillEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("终审单列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<ConFinalConfirmbillEntity> listConFinalConfirmbillEntitys = ExcelImportUtil.importExcel(file.getInputStream(),ConFinalConfirmbillEntity.class,params);
				for (ConFinalConfirmbillEntity conFinalConfirmbill : listConFinalConfirmbillEntitys) {
					conFinalConfirmbillService.save(conFinalConfirmbill);
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
	@ApiOperation(value="终审单列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<ConFinalConfirmbillEntity>> list() {
		List<ConFinalConfirmbillEntity> listConFinalConfirmbills=conFinalConfirmbillService.getList(ConFinalConfirmbillEntity.class);
		return Result.success(listConFinalConfirmbills);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取终审单信息",notes="根据ID获取终审单信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		ConFinalConfirmbillEntity task = conFinalConfirmbillService.get(ConFinalConfirmbillEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取终审单信息为空");
		}
		return Result.success(task);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建终审单")
	public ResponseMessage<?> create(@ApiParam(name="终审单对象")@RequestBody ConFinalConfirmbillEntity conFinalConfirmbill, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ConFinalConfirmbillEntity>> failures = validator.validate(conFinalConfirmbill);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			conFinalConfirmbillService.save(conFinalConfirmbill);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("终审单信息保存失败");
		}
		return Result.success(conFinalConfirmbill);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新终审单",notes="更新终审单")
	public ResponseMessage<?> update(@ApiParam(name="终审单对象")@RequestBody ConFinalConfirmbillEntity conFinalConfirmbill) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ConFinalConfirmbillEntity>> failures = validator.validate(conFinalConfirmbill);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			conFinalConfirmbillService.saveOrUpdate(conFinalConfirmbill);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("更新终审单信息失败");
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success("更新终审单信息成功");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除终审单")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" + id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			conFinalConfirmbillService.deleteEntityById(ConFinalConfirmbillEntity.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("终审单删除失败");
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
				
			message = "单据单提交成功";
					}
		if(w==1){
						
			message = "单据单撤回成功";
					}
		if(w==2){
			
			message = "单据审核通过";
					}
					
		String state = request.getParameter("state");
		try{
			for(String id:ids.split(",")){
				ConFinalConfirmbillEntity conFinalConfirmbill  = systemService.getEntity(ConFinalConfirmbillEntity.class,id); 
			
				
				if( "1".equals(conFinalConfirmbill.getIsResend()) && w==0){
					
					message = "已提交的单据不能重复提交！";
					j.setMsg(message);
					return j;
				}
			
		
				if( !"1".equals(conFinalConfirmbill.getIsResend()) && w==1){
					
					message = "不是已提交的单据不能撤回！";
					j.setMsg(message);
					return j;
				}
				if( !"1".equals(conFinalConfirmbill.getIsResend()) && w==2){
					
					message = "该单据未提交不能审核！";
					j.setMsg(message);
					return j;
				}
			
				if("1".equals(conFinalConfirmbill.getIsConfirm())&& w==1){
					message = "该单据已审核通过不能撤回！";
					j.setMsg(message);
					return j;
				}
		
			if(w==0){
				conFinalConfirmbill.setResendEmp(ResourceUtil.getSessionUser().getId());
				conFinalConfirmbill.setBillState("已提交");
				conFinalConfirmbill.setIsResend (state);
			}
			if(w==1){
				conFinalConfirmbill.setResendEmp(null);
				conFinalConfirmbill.setBillState("新建");
				conFinalConfirmbill.setIsResend (state);
			}
			if(w==2){
				conFinalConfirmbill.setIsConfirm(state);
				conFinalConfirmbill.setConfirmDate(new Date(System.currentTimeMillis()));
				conFinalConfirmbill.setBillState("已中标");
				ConOfferBillEntity conOfferBill = systemService.getEntity(ConOfferBillEntity.class,conFinalConfirmbill.getOfferId()); 
				conOfferBill.setBillState("已中标");
				ConWarrantyBillEntity conWarrantyBill = systemService.getEntity(ConWarrantyBillEntity.class,conFinalConfirmbill.getApplyId());
				conWarrantyBill.setBillState("6");
				systemService.saveOrUpdate(conOfferBill);
				systemService.saveOrUpdate(conWarrantyBill);
				
			}
				
				conFinalConfirmbillService.saveOrUpdate(conFinalConfirmbill);
					
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			if(w==0){
				
				message = "单据单提交失败";
						}
			if(w==1){
							
				message = "单据单撤回失败";
						}
			if(w==2){
				
				message = "单据单审核失败";
						}
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	 
	 /**
	  * 查询中标单的内容
	  * @param request
	  * @param response
	  * @param dataGrid
	  */
	 @RequestMapping(params = "impotWinn")
	@ResponseBody
	public void impotWinn(ConFinalConfirmbillEntity conFinalConfirmbill,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		
		CriteriaQuery cq = new CriteriaQuery(ConFinalConfirmbillEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, conFinalConfirmbill, request.getParameterMap());
		try{
		//自定义追加查询条件
			String sql = " select t.id from con_final_confirmBill T where t.id not in (select DISTINCT d.EXTEN1 from con_mark d where D.EXTEN1 is not null) ";
			List<Map<String, Object>> findForJdbc = this.conFinalConfirmbillService.findForJdbc(sql);
			List<String> findByQueryString = new ArrayList<>();
			for(Map<String, Object> map : findForJdbc){
				if(map.get("id") != null)
				findByQueryString.add(map.get("id").toString());
			}
			cq.add(Restrictions.eq("isConfirm", "1"));
			cq.add(Restrictions.in("id", findByQueryString));
			
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.conFinalConfirmbillService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	
	
}
