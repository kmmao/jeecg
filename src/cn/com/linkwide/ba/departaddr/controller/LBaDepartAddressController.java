package cn.com.linkwide.ba.departaddr.controller;
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
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.jwt.util.ResponseMessage;
import org.jeecgframework.jwt.util.Result;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSDepart;
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

import cn.com.linkwide.ba.departaddr.entity.LBaDepartAddressEntity;
import cn.com.linkwide.ba.departaddr.service.LBaDepartAddressServiceI;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**   
 * @Title: Controller  
 * @Description: 科室及送货地址对照表
 * @author onlineGenerator
 * @date 2018-09-26 10:59:11
 * @version V1.0   
 *
 */
@Api(value="LBaDepartAddress",description="科室及送货地址对照表",tags="lBaDepartAddressController")
@Controller
@RequestMapping("/lBaDepartAddressController")
public class LBaDepartAddressController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(LBaDepartAddressController.class);

	@Autowired
	private LBaDepartAddressServiceI lBaDepartAddressService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 科室及送货地址对照表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("cn/com/linkwide/ba/departaddr/lBaDepartAddressList");
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
	public void datagrid(LBaDepartAddressEntity lBaDepartAddress,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(LBaDepartAddressEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, lBaDepartAddress, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.lBaDepartAddressService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除科室及送货地址对照表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(LBaDepartAddressEntity lBaDepartAddress, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		lBaDepartAddress = systemService.getEntity(LBaDepartAddressEntity.class, lBaDepartAddress.getId());
		message = "科室及送货地址对照表删除成功";
		try{
			lBaDepartAddressService.delete(lBaDepartAddress);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "科室及送货地址对照表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除科室及送货地址对照表
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "科室及送货地址对照表删除成功";
		try{
			for(String id:ids.split(",")){
				LBaDepartAddressEntity lBaDepartAddress = systemService.getEntity(LBaDepartAddressEntity.class, 
				id
				);
				lBaDepartAddressService.delete(lBaDepartAddress);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "科室及送货地址对照表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加科室及送货地址对照表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(LBaDepartAddressEntity lBaDepartAddress, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "科室及送货地址对照表添加成功";
		try{
			lBaDepartAddressService.save(lBaDepartAddress);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "科室及送货地址对照表添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新科室及送货地址对照表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(LBaDepartAddressEntity lBaDepartAddress, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "科室及送货地址对照表更新成功";
		LBaDepartAddressEntity t = lBaDepartAddressService.get(LBaDepartAddressEntity.class, lBaDepartAddress.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(lBaDepartAddress, t);
			lBaDepartAddressService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "科室及送货地址对照表更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 科室及送货地址对照表新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(LBaDepartAddressEntity lBaDepartAddress, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(lBaDepartAddress.getId())) {
			lBaDepartAddress = lBaDepartAddressService.getEntity(LBaDepartAddressEntity.class, lBaDepartAddress.getId());
			req.setAttribute("lBaDepartAddressPage", lBaDepartAddress);
		}
		return new ModelAndView("cn/com/linkwide/ba/departaddr/lBaDepartAddress-add");
	}
	/**
	 * 科室及送货地址对照表编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(LBaDepartAddressEntity lBaDepartAddress, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(lBaDepartAddress.getId())) {
			lBaDepartAddress = lBaDepartAddressService.getEntity(LBaDepartAddressEntity.class, lBaDepartAddress.getId());
			req.setAttribute("lBaDepartAddressPage", lBaDepartAddress);
		}
		return new ModelAndView("cn/com/linkwide/ba/departaddr/lBaDepartAddress-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","lBaDepartAddressController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(LBaDepartAddressEntity lBaDepartAddress,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(LBaDepartAddressEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, lBaDepartAddress, request.getParameterMap());
		List<LBaDepartAddressEntity> lBaDepartAddresss = this.lBaDepartAddressService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"科室及送货地址对照表");
		modelMap.put(NormalExcelConstants.CLASS,LBaDepartAddressEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("科室及送货地址对照表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,lBaDepartAddresss);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(LBaDepartAddressEntity lBaDepartAddress,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"科室及送货地址对照表");
    	modelMap.put(NormalExcelConstants.CLASS,LBaDepartAddressEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("科室及送货地址对照表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<LBaDepartAddressEntity> listLBaDepartAddressEntitys = ExcelImportUtil.importExcel(file.getInputStream(),LBaDepartAddressEntity.class,params);
				for (LBaDepartAddressEntity lBaDepartAddress : listLBaDepartAddressEntitys) {
					lBaDepartAddressService.save(lBaDepartAddress);
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
	@ApiOperation(value="科室及送货地址对照表列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<LBaDepartAddressEntity>> list() {
		List<LBaDepartAddressEntity> listLBaDepartAddresss=lBaDepartAddressService.getList(LBaDepartAddressEntity.class);
		return Result.success(listLBaDepartAddresss);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取科室及送货地址对照表信息",notes="根据ID获取科室及送货地址对照表信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		LBaDepartAddressEntity task = lBaDepartAddressService.get(LBaDepartAddressEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取科室及送货地址对照表信息为空");
		}
		return Result.success(task);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建科室及送货地址对照表")
	public ResponseMessage<?> create(@ApiParam(name="科室及送货地址对照表对象")@RequestBody LBaDepartAddressEntity lBaDepartAddress, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<LBaDepartAddressEntity>> failures = validator.validate(lBaDepartAddress);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			lBaDepartAddressService.save(lBaDepartAddress);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("科室及送货地址对照表信息保存失败");
		}
		return Result.success(lBaDepartAddress);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新科室及送货地址对照表",notes="更新科室及送货地址对照表")
	public ResponseMessage<?> update(@ApiParam(name="科室及送货地址对照表对象")@RequestBody LBaDepartAddressEntity lBaDepartAddress) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<LBaDepartAddressEntity>> failures = validator.validate(lBaDepartAddress);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			lBaDepartAddressService.saveOrUpdate(lBaDepartAddress);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("更新科室及送货地址对照表信息失败");
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success("更新科室及送货地址对照表信息成功");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除科室及送货地址对照表")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" + id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			lBaDepartAddressService.deleteEntityById(LBaDepartAddressEntity.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("科室及送货地址对照表删除失败");
		}

		return Result.success();
	}
	
	/**
	 * 科室分页参照模糊查询
	 * 
	 * @author zhuhd 2018-3-27
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "departAddress")
	@ResponseBody
	public void departAddress(LBaDepartAddressEntity depart,HttpServletRequest request,DataGrid dataGrid, HttpServletResponse response) {
		String q = request.getParameter("q") != null ? request.getParameter("q") : "";
		CriteriaQuery cq = new CriteriaQuery(LBaDepartAddressEntity.class, dataGrid);
		String defaultVal = request.getParameter("defaultVal") != null ? request.getParameter("defaultVal") : "";
		// 查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, depart);
		try {
			Disjunction disjunction = Restrictions.disjunction();
			disjunction.add(Restrictions.like("addressCode", q,MatchMode.ANYWHERE).ignoreCase());
			disjunction.add(Restrictions.like("address", q,MatchMode.ANYWHERE).ignoreCase());
			cq.add(disjunction);
			cq.add();
			this.systemService.getDataGridReturn(cq, true); 
			String[]  defArray = defaultVal.split(",");
			if(defArray.length>1 && StringUtil.isNotEmpty(defArray[1]) ){
				LBaDepartAddressEntity d = systemService.findUniqueByProperty(LBaDepartAddressEntity.class, defArray[0], defArray[1]);
				List<LBaDepartAddressEntity> results = dataGrid.getResults();
				if(d !=null){
					for(LBaDepartAddressEntity dept : results){
						//如果有就结束
						if(dept.getId().equals(d.getId())){
							TagUtil.datagrid(response, dataGrid);
							return ;
						}
					}
					results.add(d);
				}
				dataGrid.setResults(results);
			} 
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		TagUtil.datagrid(response, dataGrid);
	}
}
