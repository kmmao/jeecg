package cn.com.linkwide.ba.vouchercategory.controller;
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

import cn.com.linkwide.ba.vouchercategory.entity.VoucherCategoryEntity;
import cn.com.linkwide.ba.vouchercategory.service.VoucherCategoryServiceI;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**   
 * @Title: Controller  
 * @Description: 凭证类别
 * @author onlineGenerator
 * @date 2018-07-10 19:31:50
 * @version V1.0   
 *
 */
@Api(value="VoucherCategory",description="凭证类别",tags="voucherCategoryController")
@Controller
@RequestMapping("/voucherCategoryController")
public class VoucherCategoryController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(VoucherCategoryController.class);

	@Autowired
	private VoucherCategoryServiceI voucherCategoryService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 凭证类别列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("cn/com/linkwide/ba/vouchercategory/voucherCategoryList");
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
	public void datagrid(VoucherCategoryEntity voucherCategory,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(VoucherCategoryEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, voucherCategory, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.voucherCategoryService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除凭证类别
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(VoucherCategoryEntity voucherCategory, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		voucherCategory = systemService.getEntity(VoucherCategoryEntity.class, voucherCategory.getId());
		message = "凭证类别删除成功";
		try{
			voucherCategoryService.delete(voucherCategory);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "凭证类别删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除凭证类别
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "凭证类别删除成功";
		try{
			for(String id:ids.split(",")){
				VoucherCategoryEntity voucherCategory = systemService.getEntity(VoucherCategoryEntity.class, 
				id
				);
				voucherCategoryService.delete(voucherCategory);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "凭证类别删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加凭证类别
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(VoucherCategoryEntity voucherCategory, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "凭证类别添加成功";
		try{
			voucherCategoryService.save(voucherCategory);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "凭证类别添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新凭证类别
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(VoucherCategoryEntity voucherCategory, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "凭证类别更新成功";
		VoucherCategoryEntity t = voucherCategoryService.get(VoucherCategoryEntity.class, voucherCategory.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(voucherCategory, t);
			voucherCategoryService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "凭证类别更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 凭证类别新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(VoucherCategoryEntity voucherCategory, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(voucherCategory.getId())) {
			voucherCategory = voucherCategoryService.getEntity(VoucherCategoryEntity.class, voucherCategory.getId());
			req.setAttribute("voucherCategoryPage", voucherCategory);
		}
		return new ModelAndView("cn/com/linkwide/ba/vouchercategory/voucherCategory-add");
	}
	/**
	 * 凭证类别编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(VoucherCategoryEntity voucherCategory, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(voucherCategory.getId())) {
			voucherCategory = voucherCategoryService.getEntity(VoucherCategoryEntity.class, voucherCategory.getId());
			req.setAttribute("voucherCategoryPage", voucherCategory);
		}
		return new ModelAndView("cn/com/linkwide/ba/vouchercategory/voucherCategory-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","voucherCategoryController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(VoucherCategoryEntity voucherCategory,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(VoucherCategoryEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, voucherCategory, request.getParameterMap());
		List<VoucherCategoryEntity> voucherCategorys = this.voucherCategoryService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"凭证类别");
		modelMap.put(NormalExcelConstants.CLASS,VoucherCategoryEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("凭证类别列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,voucherCategorys);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(VoucherCategoryEntity voucherCategory,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"凭证类别");
    	modelMap.put(NormalExcelConstants.CLASS,VoucherCategoryEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("凭证类别列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<VoucherCategoryEntity> listVoucherCategoryEntitys = ExcelImportUtil.importExcel(file.getInputStream(),VoucherCategoryEntity.class,params);
				for (VoucherCategoryEntity voucherCategory : listVoucherCategoryEntitys) {
					voucherCategoryService.save(voucherCategory);
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
	@ApiOperation(value="凭证类别列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<VoucherCategoryEntity>> list() {
		List<VoucherCategoryEntity> listVoucherCategorys=voucherCategoryService.getList(VoucherCategoryEntity.class);
		return Result.success(listVoucherCategorys);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取凭证类别信息",notes="根据ID获取凭证类别信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		VoucherCategoryEntity task = voucherCategoryService.get(VoucherCategoryEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取凭证类别信息为空");
		}
		return Result.success(task);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建凭证类别")
	public ResponseMessage<?> create(@ApiParam(name="凭证类别对象")@RequestBody VoucherCategoryEntity voucherCategory, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<VoucherCategoryEntity>> failures = validator.validate(voucherCategory);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			voucherCategoryService.save(voucherCategory);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("凭证类别信息保存失败");
		}
		return Result.success(voucherCategory);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新凭证类别",notes="更新凭证类别")
	public ResponseMessage<?> update(@ApiParam(name="凭证类别对象")@RequestBody VoucherCategoryEntity voucherCategory) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<VoucherCategoryEntity>> failures = validator.validate(voucherCategory);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			voucherCategoryService.saveOrUpdate(voucherCategory);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("更新凭证类别信息失败");
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success("更新凭证类别信息成功");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除凭证类别")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" + id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			voucherCategoryService.deleteEntityById(VoucherCategoryEntity.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("凭证类别删除失败");
		}

		return Result.success();
	}
}
