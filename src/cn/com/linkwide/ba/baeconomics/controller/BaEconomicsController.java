package cn.com.linkwide.ba.baeconomics.controller;
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

import cn.com.linkwide.ba.baeconomics.entity.BaEconomicsEntity;
import cn.com.linkwide.ba.baeconomics.service.BaEconomicsServiceI;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**   
 * @Title: Controller  
 * @Description: 经济分类
 * @author onlineGenerator
 * @date 2018-05-31 10:28:14
 * @version V1.0   
 *
 */
@Api(value="BaEconomics",description="经济分类",tags="baEconomicsController")
@Controller
@RequestMapping("/baEconomicsController")
public class BaEconomicsController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(BaEconomicsController.class);

	@Autowired
	private BaEconomicsServiceI baEconomicsService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 经济分类列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("cn/com/linkwide/ba/baeconomics/baEconomicsList");
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
	public void datagrid(BaEconomicsEntity baEconomics,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(BaEconomicsEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, baEconomics, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.baEconomicsService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除经济分类
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(BaEconomicsEntity baEconomics, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		baEconomics = systemService.getEntity(BaEconomicsEntity.class, baEconomics.getId());
		message = "经济分类删除成功";
		try{
			baEconomicsService.delete(baEconomics);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "经济分类删除失败";
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除经济分类
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "经济分类删除成功";
		try{
			for(String id:ids.split(",")){
				BaEconomicsEntity baEconomics = systemService.getEntity(BaEconomicsEntity.class, 
				id
				);
				baEconomicsService.delete(baEconomics);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "经济分类删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加经济分类
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(BaEconomicsEntity baEconomics, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "经济分类添加成功";
		try{
			BaEconomicsEntity findUniqueBycode = systemService.findUniqueByProperty(BaEconomicsEntity.class, "vcode", baEconomics.getVcode());
			if(findUniqueBycode !=null){
				throw new BusinessException(baEconomics.getVcode()+"编码已存在，请修改");
			}
			baEconomicsService.save(baEconomics);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(BusinessException b){
			b.getMessage();
		}catch(Exception e){
			e.printStackTrace();
			message = "经济分类添加失败";
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新经济分类
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(BaEconomicsEntity baEconomics, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "经济分类更新成功";
		BaEconomicsEntity t = baEconomicsService.get(BaEconomicsEntity.class, baEconomics.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(baEconomics, t);
			baEconomicsService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "经济分类更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 经济分类新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(BaEconomicsEntity baEconomics, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(baEconomics.getId())) {
			baEconomics = baEconomicsService.getEntity(BaEconomicsEntity.class, baEconomics.getId());
			req.setAttribute("baEconomicsPage", baEconomics);
		}
		return new ModelAndView("cn/com/linkwide/ba/baeconomics/baEconomics-add");
	}
	/**
	 * 经济分类编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(BaEconomicsEntity baEconomics, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(baEconomics.getId())) {
			baEconomics = baEconomicsService.getEntity(BaEconomicsEntity.class, baEconomics.getId());
			req.setAttribute("baEconomicsPage", baEconomics);
		}
		return new ModelAndView("cn/com/linkwide/ba/baeconomics/baEconomics-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","baEconomicsController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(BaEconomicsEntity baEconomics,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(BaEconomicsEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, baEconomics, request.getParameterMap());
		List<BaEconomicsEntity> baEconomicss = this.baEconomicsService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"经济分类");
		modelMap.put(NormalExcelConstants.CLASS,BaEconomicsEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("经济分类列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,baEconomicss);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(BaEconomicsEntity baEconomics,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"经济分类");
    	modelMap.put(NormalExcelConstants.CLASS,BaEconomicsEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("经济分类列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<BaEconomicsEntity> listBaEconomicsEntitys = ExcelImportUtil.importExcel(file.getInputStream(),BaEconomicsEntity.class,params);
				for (BaEconomicsEntity baEconomics : listBaEconomicsEntitys) {
					baEconomicsService.save(baEconomics);
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
	@ApiOperation(value="经济分类列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<BaEconomicsEntity>> list() {
		List<BaEconomicsEntity> listBaEconomicss=baEconomicsService.getList(BaEconomicsEntity.class);
		return Result.success(listBaEconomicss);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取经济分类信息",notes="根据ID获取经济分类信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		BaEconomicsEntity task = baEconomicsService.get(BaEconomicsEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取经济分类信息为空");
		}
		return Result.success(task);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建经济分类")
	public ResponseMessage<?> create(@ApiParam(name="经济分类对象")@RequestBody BaEconomicsEntity baEconomics, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<BaEconomicsEntity>> failures = validator.validate(baEconomics);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			baEconomicsService.save(baEconomics);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("经济分类信息保存失败");
		}
		return Result.success(baEconomics);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新经济分类",notes="更新经济分类")
	public ResponseMessage<?> update(@ApiParam(name="经济分类对象")@RequestBody BaEconomicsEntity baEconomics) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<BaEconomicsEntity>> failures = validator.validate(baEconomics);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			baEconomicsService.saveOrUpdate(baEconomics);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("更新经济分类信息失败");
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success("更新经济分类信息成功");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除经济分类")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" + id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			baEconomicsService.deleteEntityById(BaEconomicsEntity.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("经济分类删除失败");
		}

		return Result.success();
	}
}
