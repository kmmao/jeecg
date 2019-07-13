package cn.com.linkwide.ba.baeconomicscompare.controller;
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

import cn.com.linkwide.ba.baeconomicscompare.entity.EconomicsCompareEntity;
import cn.com.linkwide.ba.baeconomicscompare.service.EconomicsCompareServiceI;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**   
 * @Title: Controller  
 * @Description: 经济分类对照关系
 * @author onlineGenerator
 * @date 2018-09-11 18:36:29
 * @version V1.0   
 *
 */
@Api(value="EconomicsCompare",description="经济分类对照关系",tags="economicsCompareController")
@Controller
@RequestMapping("/economicsCompareController")
public class EconomicsCompareController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(EconomicsCompareController.class);

	@Autowired
	private EconomicsCompareServiceI economicsCompareService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 经济分类对照关系列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("cn/com/linkwide/ba/baeconomicscompare/economicsCompareList");
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
	public void datagrid(EconomicsCompareEntity economicsCompare,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		//按编码和名称模糊查询
		String vCode = economicsCompare.getVcode();
		String vName = economicsCompare.getVname();
		if(StringUtil.isNotEmpty(vCode)){
			economicsCompare.setVcode("*"+vCode+"*");
		}
		if(StringUtil.isNotEmpty(vName)){
			economicsCompare.setVname("*"+vName+"*");
		}
		CriteriaQuery cq = new CriteriaQuery(EconomicsCompareEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, economicsCompare, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.economicsCompareService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除经济分类对照关系
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(EconomicsCompareEntity economicsCompare, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		economicsCompare = systemService.getEntity(EconomicsCompareEntity.class, economicsCompare.getId());
		message = "经济分类对照关系删除成功";
		try{
			economicsCompareService.delete(economicsCompare);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "经济分类对照关系删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除经济分类对照关系
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "经济分类对照关系删除成功";
		try{
			for(String id:ids.split(",")){
				EconomicsCompareEntity economicsCompare = systemService.getEntity(EconomicsCompareEntity.class, 
				id
				);
				economicsCompareService.delete(economicsCompare);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "经济分类对照关系删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加经济分类对照关系
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(EconomicsCompareEntity economicsCompare, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "经济分类对照关系添加成功";
		try{
			//校验编码重复
			String code = economicsCompare.getVcode();
			List<EconomicsCompareEntity> list = systemService.findByProperty(EconomicsCompareEntity.class, "vcode", code);
			if(list != null && list.size()>0){
				message = "费用项目编码已存在";
				j.setMsg(message);
				return j;
			}
			economicsCompareService.save(economicsCompare);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "经济分类对照关系添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新经济分类对照关系
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(EconomicsCompareEntity economicsCompare, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "经济分类对照关系更新成功";
		EconomicsCompareEntity t = economicsCompareService.get(EconomicsCompareEntity.class, economicsCompare.getId());
		try {
			//校验编码重复
			String hql = " from EconomicsCompareEntity where vcode=? and id <> ?";
			List<EconomicsCompareEntity> list = systemService.findHql(hql, new Object[]{t.getVcode(),t.getId()});
			if(list != null && list.size()>0){
				message = "费用编码已存在";
				j.setMsg(message);
				return j;
			}
			MyBeanUtils.copyBeanNotNull2Bean(economicsCompare, t);
			economicsCompareService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "经济分类对照关系更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 经济分类对照关系新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(EconomicsCompareEntity economicsCompare, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(economicsCompare.getId())) {
			economicsCompare = economicsCompareService.getEntity(EconomicsCompareEntity.class, economicsCompare.getId());
			req.setAttribute("economicsComparePage", economicsCompare);
		}
		return new ModelAndView("cn/com/linkwide/ba/baeconomicscompare/economicsCompare-add");
	}
	/**
	 * 经济分类对照关系编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(EconomicsCompareEntity economicsCompare, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(economicsCompare.getId())) {
			economicsCompare = economicsCompareService.getEntity(EconomicsCompareEntity.class, economicsCompare.getId());
			req.setAttribute("economicsComparePage", economicsCompare);
		}
		return new ModelAndView("cn/com/linkwide/ba/baeconomicscompare/economicsCompare-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","economicsCompareController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(EconomicsCompareEntity economicsCompare,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(EconomicsCompareEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, economicsCompare, request.getParameterMap());
		List<EconomicsCompareEntity> economicsCompares = this.economicsCompareService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"经济分类对照关系");
		modelMap.put(NormalExcelConstants.CLASS,EconomicsCompareEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("经济分类对照关系列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,economicsCompares);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(EconomicsCompareEntity economicsCompare,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"经济分类对照关系");
    	modelMap.put(NormalExcelConstants.CLASS,EconomicsCompareEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("经济分类对照关系列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<EconomicsCompareEntity> listEconomicsCompareEntitys = ExcelImportUtil.importExcel(file.getInputStream(),EconomicsCompareEntity.class,params);
				for (EconomicsCompareEntity economicsCompare : listEconomicsCompareEntitys) {
					economicsCompareService.save(economicsCompare);
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
	@ApiOperation(value="经济分类对照关系列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<EconomicsCompareEntity>> list() {
		List<EconomicsCompareEntity> listEconomicsCompares=economicsCompareService.getList(EconomicsCompareEntity.class);
		return Result.success(listEconomicsCompares);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取经济分类对照关系信息",notes="根据ID获取经济分类对照关系信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		EconomicsCompareEntity task = economicsCompareService.get(EconomicsCompareEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取经济分类对照关系信息为空");
		}
		return Result.success(task);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建经济分类对照关系")
	public ResponseMessage<?> create(@ApiParam(name="经济分类对照关系对象")@RequestBody EconomicsCompareEntity economicsCompare, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<EconomicsCompareEntity>> failures = validator.validate(economicsCompare);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			economicsCompareService.save(economicsCompare);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("经济分类对照关系信息保存失败");
		}
		return Result.success(economicsCompare);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新经济分类对照关系",notes="更新经济分类对照关系")
	public ResponseMessage<?> update(@ApiParam(name="经济分类对照关系对象")@RequestBody EconomicsCompareEntity economicsCompare) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<EconomicsCompareEntity>> failures = validator.validate(economicsCompare);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			economicsCompareService.saveOrUpdate(economicsCompare);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("更新经济分类对照关系信息失败");
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success("更新经济分类对照关系信息成功");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除经济分类对照关系")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" + id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			economicsCompareService.deleteEntityById(EconomicsCompareEntity.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("经济分类对照关系删除失败");
		}

		return Result.success();
	}
}
