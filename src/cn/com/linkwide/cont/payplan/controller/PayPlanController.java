package cn.com.linkwide.cont.payplan.controller;
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

import cn.com.linkwide.cont.coninformation.entity.ConInformationEntity;
import cn.com.linkwide.cont.payplan.entity.PayPlanEntity;
import cn.com.linkwide.cont.payplan.service.PayPlanServiceI;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import com.alibaba.fastjson.JSONArray;
import net.sf.json.JSONObject;


/**   
 * @Title: Controller  
 * @Description: 付款计划
 * @author onlineGenerator
 * @date 2018-08-24 12:06:29
 * @version V1.0   
 *
 */
@Api(value="PayPlan",description="付款计划",tags="payPlanController")
@Controller
@RequestMapping("/payPlanController")
public class PayPlanController extends BaseController {
	/**
	 * 
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(PayPlanController.class);

	@Autowired
	private PayPlanServiceI payPlanService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 付款计划列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("cn/com/linkwide/cont/payplan/payPlanList");
	}

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 *//*

	@RequestMapping(params = "datagrid")
	public void datagrid(PayPlanEntity payPlan,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(PayPlanEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, payPlan, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.payPlanService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}*/
	
	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(PayPlanEntity payPlan, HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid) {
		try {
			String conNumber = request.getParameter("conNumber");
			String conName = request.getParameter("conName");
			// 自定义追加查询条件
			String query1 = "select a.id as id,b.id as ids,b.con_number as conNumber,b.con_no as conNo,b.con_name as conName,b.con_exec1 as conExec1,a.money_source as moneySource,a.apply_emp as applyEmp,a.apply_time as applyTime,a.pay_date as payDate,a.pay_money as payMoney,a.pay_rate as payRate,"
					+ "a.pay_sm as paySm,a.is_sender as isSender"
					+ ",b.start_date as startDate,b.end_date as endDate";
					
			StringBuffer sql = new StringBuffer();
			sql.append(" from pay_plan a  LEFT JOIN con_information b  on a.con_id=b.id ");
			sql.append(" where 1=1 and  a.is_sender in ('0','1') ");
			String orderStr = "";
			if (StringUtil.isNotEmpty(dataGrid.getSort())) {
				orderStr = " order by " + dataGrid.getSort() + " " + dataGrid.getOrder();
			}
			if (StringUtil.isNotEmpty(conNumber)) {
				sql.append(" and b.con_number like '%" + conNumber + "%' ");
			}
			if (StringUtil.isNotEmpty(conName)) {
				sql.append(" and b.con_name like '%" + conName + "%' ");
			}
		
			List<Map<String, Object>> list = systemService.findForJdbc(query1 + sql.toString() + orderStr,
					dataGrid.getPage(), dataGrid.getRows());
			dataGrid.setResults(list);
			List<Map<String, Object>> lisCount = systemService
					.findForJdbc("select count(*) as  count " + sql.toString(), null);
			for (Map<String, Object> map : lisCount) {
				dataGrid.setTotal(Integer.valueOf(map.get("count").toString()));
			}

		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		TagUtil.datagrid(response, dataGrid);
	}
	
	
	
	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "datagriddata")
	public void datagriddata(PayPlanEntity payPlan, HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid) {
		try {
			String conNumber = request.getParameter("conNumber");
			String conName = request.getParameter("conName");
			String conNo = request.getParameter("conNo");
			String payMoney=request.getParameter("payMoney");
			String flag =request.getParameter("flag");
			String id= request.getParameter("id");
			
			// 自定义追加查询条件
			String query1 = "select a.id as id,b.id as ids,b.con_number as conNumber,b.con_no as conNo,b.con_name as conName,b.con_exec1 as conExec1,a.money_source as moneySource,a.pay_money as payMoney,a.pay_date as payDate,a.pay_rate as payRate,"
					+ "a.pay_sm as paySm"
					+ ",b.start_date as startDate,b.end_date as endDate";
					
			StringBuffer sql = new StringBuffer();
			sql.append(" from pay_plan a  LEFT JOIN con_information b  on a.con_id=b.id ");
			sql.append(" where 1=1 and b.con_type='04' and a.pay_conditions='02'   ");
			String orderStr = "";
			if (StringUtil.isNotEmpty(dataGrid.getSort())) {
				orderStr = " order by " + dataGrid.getSort() + " " + dataGrid.getOrder();
			}
			if("1".equals(flag)){
				sql.append(" and a.is_sender is null");
			}else{
				if (StringUtil.isNotEmpty(id)) {
				sql.append(" and a.id = '" + id + "' ");
			}
			}
			if (StringUtil.isNotEmpty(conNumber)) {
				sql.append(" and b.con_number like '%" + conNumber + "%' ");
			}
			if (StringUtil.isNotEmpty(conNo)) {
				sql.append(" and b.con_no like '%" + conNo + "%' ");
			}
			if (StringUtil.isNotEmpty(conName)) {
				sql.append(" and b.con_name like '%" + conName + "%' ");
			}
			if (StringUtil.isNotEmpty(payMoney)) {
				sql.append(" and a.pay_money = ' "+ payMoney+"'"  );
			}
		
			List<Map<String, Object>> list = systemService.findForJdbc(query1 + sql.toString() + orderStr,
					dataGrid.getPage(), dataGrid.getRows());
			dataGrid.setResults(list);
			List<Map<String, Object>> lisCount = systemService
					.findForJdbc("select count(*) as  count " + sql.toString(), null);
			for (Map<String, Object> map : lisCount) {
				dataGrid.setTotal(Integer.valueOf(map.get("count").toString()));
			}

		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		TagUtil.datagrid(response, dataGrid);
	}

	
	 /**
	 * 批量提交预算申请
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
				
			message = "付款申请提交成功";
					}
		if(w==1){
						
			message = "付款申请撤回成功";
					}
		
					
		String state = request.getParameter("state");
		try{
			for(String id:ids.split(",")){
				PayPlanEntity payPlan = systemService.getEntity(PayPlanEntity.class,id); 
				
			
				
				if(payPlan.getIsSender()== "1" && w==0 ){
					
					message = "已提交的申请不能重复提交！";
					j.setMsg(message);
					return j;
				}
					if(payPlan.getIsSender() == "0"  && w==1){
				
					message = "不是提交状态的单据不能撤回！";
					j.setMsg(message);
					return j;
				}
			
					payPlan.setIsSender(state);		
					payPlanService.saveOrUpdate(payPlan);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			if(w==0){
				
				message = "付款申请提交失败";
						}
			if(w==1){
							
				message = "付款申请撤回失败";
						}
		
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	 /**
		 * 更新付款计划
		 * 
		 * @param ids
		 * @return
		 */
		@RequestMapping(params = "doUpdate")
		@ResponseBody
		public AjaxJson doUpdate(String ids,PayPlanEntity payPlan, HttpServletRequest request) {
			String message = null;
			AjaxJson j = new AjaxJson();
			message = "付款申请操作成功";
		 String	ds =request.getParameter("json");
			
			try {
				
				
				
				net.sf.json.JSONArray json=net.sf.json.JSONArray.fromObject(ds);
				JSONObject jsonOne;
		

				for(int i=0;i<json.size();i++){
				          jsonOne = json.getJSONObject(i); 
				          PayPlanEntity t = payPlanService.get(PayPlanEntity.class, (String)jsonOne.get("id"));
				          payPlan.setIsSender("0");
				          payPlan.setApplyEmp(ResourceUtil.getSessionUser().getId());
				          payPlan.setApplyTime(new Date(System.currentTimeMillis()));
				          SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//yyyy-mm-dd, 会出现时间不对, 因为小写的mm是代表: 秒
				          Date utilDate = sdf.parse((String)jsonOne.get("payDate"));
				          payPlan.setPayDate(utilDate);
				          payPlan.setPayMoney(Double.parseDouble(jsonOne.get("payMoney").toString()));
				          payPlan.setPayRate(Double.parseDouble(jsonOne.get("payRate").toString()));
				          MyBeanUtils.copyBeanNotNull2Bean(payPlan, t);
				          payPlanService.saveOrUpdate(t);
						systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
				 }
				
				
				
				
			} catch (Exception e) {
				e.printStackTrace();
				message = "付款申请操作失败";
				throw new BusinessException(e.getMessage());
			}
			j.setMsg(message);
			return j;
		}
	
	
	/**
	 * 删除付款计划
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(PayPlanEntity payPlan, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		payPlan = systemService.getEntity(PayPlanEntity.class, payPlan.getId());
		message = "付款计划删除成功";
		try{
			payPlanService.delete(payPlan);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "付款计划删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除付款计划
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "付款计划删除成功";
		try{
			for(String id:ids.split(",")){
				PayPlanEntity payPlan = systemService.getEntity(PayPlanEntity.class, 
				id
				);
				payPlanService.delete(payPlan);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "付款计划删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加付款计划
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(PayPlanEntity payPlan, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "付款计划添加成功";
		try{
			payPlanService.save(payPlan);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "付款计划添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	
	

	/**
	 * 付款计划新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(PayPlanEntity payPlan, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(payPlan.getId())) {
			payPlan = payPlanService.getEntity(PayPlanEntity.class, payPlan.getId());
			req.setAttribute("payPlanPage", payPlan);
		}
		return new ModelAndView("cn/com/linkwide/cont/payplan/payPlan-add");
	}
	/**
	 * 付款计划编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(ConInformationEntity conInformation, HttpServletRequest req) {
if (StringUtil.isNotEmpty(conInformation.getId())) {
			
			if("".equals(conInformation.getConNumnerZ())||null==conInformation.getConNumnerZ()||"0".equals(conInformation.getConNumnerZ())){
				conInformation.setRenewId(conInformation.getId());
			}
			
			conInformation = systemService.getEntity(ConInformationEntity.class, conInformation.getId());
			req.setAttribute("conInformationPage", conInformation);
		}
		return new ModelAndView("cn/com/linkwide/cont/coninformation/conInformation-update");
	}
	
	
	
	/**
	 * 付款计划编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdatet")
	public ModelAndView goUpdatet(PayPlanEntity payPlan, HttpServletRequest req) {
    if (StringUtil.isNotEmpty(payPlan.getId())) {
    	payPlan = systemService.getEntity(PayPlanEntity.class, payPlan.getId());
			req.setAttribute("payPlanPage", payPlan);
		}
		return new ModelAndView("cn/com/linkwide/cont/payplan/payPlan-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","payPlanController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(PayPlanEntity payPlan,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(PayPlanEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, payPlan, request.getParameterMap());
		List<PayPlanEntity> payPlans = this.payPlanService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"付款计划");
		modelMap.put(NormalExcelConstants.CLASS,PayPlanEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("付款计划列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,payPlans);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(PayPlanEntity payPlan,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"付款计划");
    	modelMap.put(NormalExcelConstants.CLASS,PayPlanEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("付款计划列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<PayPlanEntity> listPayPlanEntitys = ExcelImportUtil.importExcel(file.getInputStream(),PayPlanEntity.class,params);
				for (PayPlanEntity payPlan : listPayPlanEntitys) {
					payPlanService.save(payPlan);
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
	@ApiOperation(value="付款计划列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<PayPlanEntity>> list() {
		List<PayPlanEntity> listPayPlans=payPlanService.getList(PayPlanEntity.class);
		return Result.success(listPayPlans);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取付款计划信息",notes="根据ID获取付款计划信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		PayPlanEntity task = payPlanService.get(PayPlanEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取付款计划信息为空");
		}
		return Result.success(task);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建付款计划")
	public ResponseMessage<?> create(@ApiParam(name="付款计划对象")@RequestBody PayPlanEntity payPlan, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<PayPlanEntity>> failures = validator.validate(payPlan);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			payPlanService.save(payPlan);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("付款计划信息保存失败");
		}
		return Result.success(payPlan);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新付款计划",notes="更新付款计划")
	public ResponseMessage<?> update(@ApiParam(name="付款计划对象")@RequestBody PayPlanEntity payPlan) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<PayPlanEntity>> failures = validator.validate(payPlan);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			payPlanService.saveOrUpdate(payPlan);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("更新付款计划信息失败");
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success("更新付款计划信息成功");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除付款计划")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" + id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			payPlanService.deleteEntityById(PayPlanEntity.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("付款计划删除失败");
		}

		return Result.success();
	}
}
