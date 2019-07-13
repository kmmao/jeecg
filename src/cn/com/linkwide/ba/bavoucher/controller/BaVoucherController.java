package cn.com.linkwide.ba.bavoucher.controller;
import java.io.IOException;
import java.util.ArrayList;
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

import cn.com.linkwide.ba.bavoucher.entity.BaVoucherEntity;
import cn.com.linkwide.ba.bavoucher.service.BaVoucherServiceI;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**   
 * @Title: Controller  
 * @Description: 凭证表
 * @author onlineGenerator
 * @date 2018-06-26 10:51:01
 * @version V1.0   
 *
 */
@Api(value="BaVoucher",description="凭证表",tags="baVoucherController")
@Controller
@RequestMapping("/baVoucherController")
public class BaVoucherController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(BaVoucherController.class);

	@Autowired
	private BaVoucherServiceI baVoucherService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 凭证表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("cn/com/linkwide/ba/bavoucher/baVoucherList");
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
	public void datagrid(BaVoucherEntity baVoucher,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		//业务模块
		String busModel = request.getParameter("busModel");
		//业务功能
		String busFunction = request.getParameter("busFunction");
		String sql = " ";
		if("ppe".equals(busModel)){//固定资产
			sql="select id,'"+busModel+"' as busModel,bus_type as busFunction,dept_id  as deptId,fund_resource as fundSource,fina_type as financialType,money as money,distribution_type as type "
					+ "from ppe_push_voucher where 1=1 ";
			String countSql  =" select count(*) from ppe_push_voucher where 1=1 ";
			
			if(StringUtil.isNotEmpty(busFunction)){
				sql+=" and bus_type='"+busFunction+"' ";
				countSql+=" and bus_type='"+busFunction+"' ";
			}
			List list =systemService.findListbySql(countSql);
			dataGrid.setResults(list);
			dataGrid.setTotal(Integer.valueOf(list.get(0).toString()));
			dataGrid.setResults(systemService.findForJdbc(sql, dataGrid.getPage(), dataGrid.getRows()));
//			if("jtzj".equals(busFunction)){ //计提折旧
//			}else if("zczj".equals(busFunction)){ //资产增加
//			}else if("zcbd".equals(busFunction)){ //资产变动
//			}else if("zccz".equals(busFunction)){ //资产处置
//			}else{//资产评估
//			}
		}else if("cont".equals(busModel)){//合同管理
			if("htfk".equals(busFunction)){ //合同付款
				
			}else{ //合同收款
				
			}
		}else if("bid".equals(busModel)){//招标管理
			if("tbbzj".equals(busFunction)){ //投标保证金
				
			}
		}else if("fa".equals(busModel)){//网上报销
			if("jkd".equals(busFunction)){ //借款单
				
			}else if("zfd".equals(busFunction)){ //支付单
				
			}else if("hkd".equals(busFunction)){ //还款单
				
			}else if("bxd".equals(busFunction)){ //报销单
				
			}else{//报销冲借款
				
			}
		}else if("cost".equals(busModel)){//成本管理
			if("zjcb".equals(busFunction)){ //直接成本
				
			}else if("rlcb".equals(busFunction)){ //人力成本
				
			}else{ //成本分摊
				
			}		
		}else if("ef".equals(busModel)){//预算管理
			if("srys".equals(busFunction)){ //收入预算
				
			}else if("zcys".equals(busFunction)){ //支出预算
				
			}else if("kyys".equals(busFunction)){ //科研预算
				
			}else{ //项目预算
				
			}
		}else if("hr".equals(busModel)){//人力资源
			if("gzft".equals(busFunction)){ //工资分摊
				
			}
		}else{//物资记账
			if("cgrkd".equals(busFunction)){ //采购入库单
				
			}else if("gzgzd".equals(busFunction)){ //高值挂账单
				
			}else if("dbd".equals(busFunction)){ //调拨单
				
			}else if("qtrkd".equals(busFunction)){ //其他入库单
				
			}else if("qtckd".equals(busFunction)){ //其他出库单
				
			}else{//出库单
				
			}
		}
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除凭证表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(BaVoucherEntity baVoucher, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		baVoucher = systemService.getEntity(BaVoucherEntity.class, baVoucher.getId());
		message = "凭证表删除成功";
		try{
			baVoucherService.delete(baVoucher);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "凭证表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除凭证表
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "凭证表删除成功";
		try{
			for(String id:ids.split(",")){
				BaVoucherEntity baVoucher = systemService.getEntity(BaVoucherEntity.class, 
				id
				);
				baVoucherService.delete(baVoucher);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "凭证表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	 public AjaxJson queryHeaderByBusModel(BaVoucherEntity baVoucher, HttpServletRequest request){
		//业务模块
		String busModel = request.getParameter("busModel");
		//业务功能
		String busFunction = request.getParameter("busFunction");
		if("ppe".equals(busModel)){//固定资产
			if("jtzj".equals(busFunction)){ //计提折旧
				
			}else if("zczj".equals(busFunction)){ //资产增加
				
			}else if("zcbd".equals(busFunction)){ //资产变动
				
			}else if("zccz".equals(busFunction)){ //资产处置
				
			}else{//资产评估
				
			}
		}
		 return null;
	 }
	/**
	 * 生成凭证表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(BaVoucherEntity baVoucher, HttpServletRequest request) {
		//业务模块
		String busModel = request.getParameter("busModel");
		//业务功能
		String busFunction = request.getParameter("busFunction");
		String sql="";
		if("ppe".equals(busModel)){//固定资产
			sql = " insert into ba_voucher (id,create_by,create_date,update_by,update_date,bus_model,bus_function,dept_id,financial_type,fund_source,money) "
					+ "select id,create_by,create_date,update_by,update_date,'ppe' as bus_model,bus_type as bus_function,dept_id  as dept_id,fina_type as financial_type,fund_resource as fund_source,money as money "
					+ "from ppe_push_voucher where 1=1 ";
			if(StringUtil.isNotEmpty(busFunction)){
				sql+=" and bus_type='"+busFunction+"' ";
			}
			systemService.executeSql(sql, new Object[]{});
			//修改固定资产推送凭证表 是否生成凭证字段为是
			String updateSql = " update ppe_push_voucher set is_voucher='y' where 1=1 ";
			if(StringUtil.isNotEmpty(busFunction)){
				sql+=" and bus_type='"+busFunction+"' ";
			}
			systemService.executeSql(updateSql, new Object[]{});
//			if("jtzj".equals(busFunction)){ //计提折旧
//			}else if("zczj".equals(busFunction)){ //资产增加
//			}else if("zcbd".equals(busFunction)){ //资产变动
//			}else if("zccz".equals(busFunction)){ //资产处置
//			}else{//资产评估
//			}
		}else if("cont".equals(busModel)){//合同管理
			if("htfk".equals(busFunction)){ //合同付款
				
			}else{ //合同收款
				
			}
		}else if("bid".equals(busModel)){//招标管理
			if("tbbzj".equals(busFunction)){ //投标保证金
				
			}
		}else if("fa".equals(busModel)){//网上报销
			if("jkd".equals(busFunction)){ //借款单
				
			}else if("zfd".equals(busFunction)){ //支付单
				
			}else if("hkd".equals(busFunction)){ //还款单
				
			}else if("bxd".equals(busFunction)){ //报销单
				
			}else{//报销冲借款
				
			}
		}else if("cost".equals(busModel)){//成本管理
			if("zjcb".equals(busFunction)){ //直接成本
				
			}else if("rlcb".equals(busFunction)){ //人力成本
				
			}else{ //成本分摊
				
			}		
		}else if("ef".equals(busModel)){//预算管理
			if("srys".equals(busFunction)){ //收入预算
				
			}else if("zcys".equals(busFunction)){ //支出预算
				
			}else if("kyys".equals(busFunction)){ //科研预算
				
			}else{ //项目预算
				
			}
		}else if("hr".equals(busModel)){//人力资源
			if("gzft".equals(busFunction)){ //工资分摊
				
			}
		}else{//物资记账
			if("cgrkd".equals(busFunction)){ //采购入库单
				
			}else if("gzgzd".equals(busFunction)){ //高值挂账单
				
			}else if("dbd".equals(busFunction)){ //调拨单
				
			}else if("qtrkd".equals(busFunction)){ //其他入库单
				
			}else if("qtckd".equals(busFunction)){ //其他出库单
				
			}else{//出库单
				
			}
		}
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "凭证表添加成功";
		try{
			baVoucherService.save(baVoucher);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "凭证表添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新凭证表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(BaVoucherEntity baVoucher, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "凭证表更新成功";
		BaVoucherEntity t = baVoucherService.get(BaVoucherEntity.class, baVoucher.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(baVoucher, t);
			baVoucherService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "凭证表更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 凭证表新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(BaVoucherEntity baVoucher, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(baVoucher.getId())) {
			baVoucher = baVoucherService.getEntity(BaVoucherEntity.class, baVoucher.getId());
			req.setAttribute("baVoucherPage", baVoucher);
		}
		return new ModelAndView("cn/com/linkwide/ba/bavoucher/baVoucher-add");
	}
	/**
	 * 凭证表编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(BaVoucherEntity baVoucher, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(baVoucher.getId())) {
			baVoucher = baVoucherService.getEntity(BaVoucherEntity.class, baVoucher.getId());
			req.setAttribute("baVoucherPage", baVoucher);
		}
		return new ModelAndView("cn/com/linkwide/ba/bavoucher/baVoucher-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","baVoucherController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(BaVoucherEntity baVoucher,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(BaVoucherEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, baVoucher, request.getParameterMap());
		List<BaVoucherEntity> baVouchers = this.baVoucherService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"凭证表");
		modelMap.put(NormalExcelConstants.CLASS,BaVoucherEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("凭证表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,baVouchers);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(BaVoucherEntity baVoucher,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"凭证表");
    	modelMap.put(NormalExcelConstants.CLASS,BaVoucherEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("凭证表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
			try {
				List<BaVoucherEntity> listBaVoucherEntitys = ExcelImportUtil.importExcel(file.getInputStream(),BaVoucherEntity.class,params);
				for (BaVoucherEntity baVoucher : listBaVoucherEntitys) {
					baVoucherService.save(baVoucher);
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
	@ApiOperation(value="凭证表列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<BaVoucherEntity>> list() {
		List<BaVoucherEntity> listBaVouchers=baVoucherService.getList(BaVoucherEntity.class);
		return Result.success(listBaVouchers);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取凭证表信息",notes="根据ID获取凭证表信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		BaVoucherEntity task = baVoucherService.get(BaVoucherEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取凭证表信息为空");
		}
		return Result.success(task);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建凭证表")
	public ResponseMessage<?> create(@ApiParam(name="凭证表对象")@RequestBody BaVoucherEntity baVoucher, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<BaVoucherEntity>> failures = validator.validate(baVoucher);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			baVoucherService.save(baVoucher);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("凭证表信息保存失败");
		}
		return Result.success(baVoucher);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新凭证表",notes="更新凭证表")
	public ResponseMessage<?> update(@ApiParam(name="凭证表对象")@RequestBody BaVoucherEntity baVoucher) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<BaVoucherEntity>> failures = validator.validate(baVoucher);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			baVoucherService.saveOrUpdate(baVoucher);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("更新凭证表信息失败");
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success("更新凭证表信息成功");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除凭证表")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" + id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			baVoucherService.deleteEntityById(BaVoucherEntity.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("凭证表删除失败");
		}

		return Result.success();
	}
}
