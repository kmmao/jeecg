package cn.com.linkwide.ba.bafisconelevitem.controller;
import cn.com.linkwide.ba.baacctsubj.entity.BaAcctSubjEntity;
import cn.com.linkwide.ba.bafisconelevitem.entity.BaFiscOnelevItemEntity;
import cn.com.linkwide.ba.bafisconelevitem.service.BaFiscOnelevItemServiceI;

import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.common.TreeChildCount;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.ComboTree;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;

import java.io.OutputStream;
import org.jeecgframework.core.util.BrowserUtils;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.TemplateExportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.vo.TemplateExcelConstants;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.hibernate.criterion.Restrictions;
import org.jeecgframework.core.util.ResourceUtil;
import java.io.IOException;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import java.util.Map;
import java.util.HashMap;
import org.jeecgframework.core.util.ExceptionUtil;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.jeecgframework.core.beanvalidator.BeanValidators;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.net.URI;
import org.springframework.http.MediaType;
import org.springframework.web.util.UriComponentsBuilder;
import org.apache.commons.lang3.StringUtils;
import org.jeecgframework.jwt.util.GsonUtil;
import org.jeecgframework.jwt.util.ResponseMessage;
import org.jeecgframework.jwt.util.Result;
import com.alibaba.fastjson.JSONArray;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**   
 * @Title: Controller  
 * @Description: 财政一级项目
 * @author onlineGenerator
 * @date 2019-03-11 11:07:10
 * @version V1.0   
 *
 */
@Api(value="BaFiscOnelevItem",description="财政一级项目",tags="baFiscOnelevItemController")
@Controller
@RequestMapping("/baFiscOnelevItemController")
public class BaFiscOnelevItemController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(BaFiscOnelevItemController.class);

	@Autowired
	private BaFiscOnelevItemServiceI baFiscOnelevItemService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 财政一级项目列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("cn/com/linkwide/ba/bafisconelevitem/baFiscOnelevItemList");
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
	public void datagrid(BaFiscOnelevItemEntity baFiscOnelevItem,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		
		String code =baFiscOnelevItem.getVcode();
		String name = baFiscOnelevItem.getVname();
		if(StringUtil.isNotEmpty(code)){
			baFiscOnelevItem.setVcode("*"+code+"*");
		}
		if(StringUtil.isNotEmpty(name)){
			baFiscOnelevItem.setVname("*"+name+"*");
		}
		
		CriteriaQuery cq = new CriteriaQuery(BaFiscOnelevItemEntity.class, dataGrid);
		if(StringUtil.isEmpty(baFiscOnelevItem.getId())){
			cq.isNull("parentCode");
		}else{
			cq.add(Restrictions.eq("parentCode", systemService.get(BaFiscOnelevItemEntity.class, baFiscOnelevItem.getId()).getVcode()));
			cq.add(Restrictions.eq("budgYear", systemService.get(BaFiscOnelevItemEntity.class, baFiscOnelevItem.getId()).getBudgYear()));
			baFiscOnelevItem.setId(null);
			
			/*cq.eq("parentCode", systemService.get(BaFiscOnelevItemEntity.class, baFiscOnelevItem.getId()).getVcode());
			baFiscOnelevItem.setId(null);*/
		}
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, baFiscOnelevItem, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.baFiscOnelevItemService.getDataGridReturn(cq, true);
		TagUtil.treegrid(response, dataGrid);
	}
	
	/**
	 * 删除财政一级项目
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(BaFiscOnelevItemEntity baFiscOnelevItem, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		baFiscOnelevItem = systemService.getEntity(BaFiscOnelevItemEntity.class, baFiscOnelevItem.getId());
		message = "财政一级项目删除成功";
		try{
			baFiscOnelevItemService.delete(baFiscOnelevItem);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "财政一级项目删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除财政一级项目
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "财政一级项目删除成功";
		try{
			for(String id:ids.split(",")){
				BaFiscOnelevItemEntity baFiscOnelevItem = systemService.getEntity(BaFiscOnelevItemEntity.class, 
				id
				);
				baFiscOnelevItemService.delete(baFiscOnelevItem);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "财政一级项目删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加财政一级项目
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(BaFiscOnelevItemEntity baFiscOnelevItem, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "财政一级项目添加成功";
		try{
			if(StringUtil.isEmpty(baFiscOnelevItem.getParentCode())){
				baFiscOnelevItem.setParentCode(null);
			}else{
				BaFiscOnelevItemEntity fiscOnelevItem = this.systemService.getEntity(BaFiscOnelevItemEntity.class, baFiscOnelevItem.getParentCode());
				if(fiscOnelevItem == null){
//					List<BaFiscOnelevItemEntity> findByProperty = this.systemService.findByProperty(BaFiscOnelevItemEntity.class, "", baFiscOnelevItem.getParentCode());
//					fiscOnelevItem = findByProperty.get(0);
					
				}else{
					baFiscOnelevItem.setParentCode(fiscOnelevItem.getVcode());
				}
				
			}
			baFiscOnelevItemService.save(baFiscOnelevItem);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "财政一级项目添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新财政一级项目
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(BaFiscOnelevItemEntity baFiscOnelevItem, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "财政一级项目更新成功";
		BaFiscOnelevItemEntity t = baFiscOnelevItemService.get(BaFiscOnelevItemEntity.class, baFiscOnelevItem.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(baFiscOnelevItem, t);
			baFiscOnelevItemService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "财政一级项目更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 财政一级项目新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(BaFiscOnelevItemEntity baFiscOnelevItem, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(baFiscOnelevItem.getId())) {
			baFiscOnelevItem = baFiscOnelevItemService.getEntity(BaFiscOnelevItemEntity.class, baFiscOnelevItem.getId());
			req.setAttribute("baFiscOnelevItemPage", baFiscOnelevItem);
		}
		return new ModelAndView("cn/com/linkwide/ba/bafisconelevitem/baFiscOnelevItem-add");
	}
	/**
	 * 财政一级项目编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(BaFiscOnelevItemEntity baFiscOnelevItem, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(baFiscOnelevItem.getId())) {
			baFiscOnelevItem = baFiscOnelevItemService.getEntity(BaFiscOnelevItemEntity.class, baFiscOnelevItem.getId());
			req.setAttribute("baFiscOnelevItemPage", baFiscOnelevItem);
		}
		return new ModelAndView("cn/com/linkwide/ba/bafisconelevitem/baFiscOnelevItem-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","baFiscOnelevItemController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(BaFiscOnelevItemEntity baFiscOnelevItem,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(BaFiscOnelevItemEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, baFiscOnelevItem, request.getParameterMap());
		List<BaFiscOnelevItemEntity> baFiscOnelevItems = this.baFiscOnelevItemService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"财政一级项目");
		modelMap.put(NormalExcelConstants.CLASS,BaFiscOnelevItemEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("财政一级项目列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,baFiscOnelevItems);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(BaFiscOnelevItemEntity baFiscOnelevItem,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"财政一级项目");
    	modelMap.put(NormalExcelConstants.CLASS,BaFiscOnelevItemEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("财政一级项目列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<BaFiscOnelevItemEntity> listBaFiscOnelevItemEntitys = ExcelImportUtil.importExcel(file.getInputStream(),BaFiscOnelevItemEntity.class,params);
				for (BaFiscOnelevItemEntity baFiscOnelevItem : listBaFiscOnelevItemEntitys) {
					baFiscOnelevItemService.save(baFiscOnelevItem);
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
	@ApiOperation(value="财政一级项目列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<BaFiscOnelevItemEntity>> list() {
		List<BaFiscOnelevItemEntity> listBaFiscOnelevItems=baFiscOnelevItemService.getList(BaFiscOnelevItemEntity.class);
		return Result.success(listBaFiscOnelevItems);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取财政一级项目信息",notes="根据ID获取财政一级项目信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		BaFiscOnelevItemEntity task = baFiscOnelevItemService.get(BaFiscOnelevItemEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取财政一级项目信息为空");
		}
		return Result.success(task);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建财政一级项目")
	public ResponseMessage<?> create(@ApiParam(name="财政一级项目对象")@RequestBody BaFiscOnelevItemEntity baFiscOnelevItem, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<BaFiscOnelevItemEntity>> failures = validator.validate(baFiscOnelevItem);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			baFiscOnelevItemService.save(baFiscOnelevItem);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("财政一级项目信息保存失败");
		}
		return Result.success(baFiscOnelevItem);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新财政一级项目",notes="更新财政一级项目")
	public ResponseMessage<?> update(@ApiParam(name="财政一级项目对象")@RequestBody BaFiscOnelevItemEntity baFiscOnelevItem) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<BaFiscOnelevItemEntity>> failures = validator.validate(baFiscOnelevItem);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			baFiscOnelevItemService.saveOrUpdate(baFiscOnelevItem);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("更新财政一级项目信息失败");
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success("更新财政一级项目信息成功");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除财政一级项目")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" + id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			baFiscOnelevItemService.deleteEntityById(BaFiscOnelevItemEntity.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("财政一级项目删除失败");
		}

		return Result.success();
	}
	
	/**
	 * 加载树形结构
	 * 
	 * @return
	 */
	@RequestMapping(params = "onelevItemTree")
	@ResponseBody
	public List<ComboTree> onelevItemTree(HttpServletRequest request) {
		List<ComboTree> rootLists = new ArrayList<ComboTree>();
		this.baFiscOnelevItemService.tree1(rootLists, request);
		return rootLists;
	}
	
	/**
	 * 加载树形结构
	 * 
	 * @return
	 */
	@RequestMapping(params = "getOnelevTree")
	@ResponseBody
	public List<ComboTree> getOnelevTree(HttpServletRequest request) {
		List<ComboTree> rootLists = new ArrayList<ComboTree>();
		this.baFiscOnelevItemService.treeOnelev(rootLists, request);
		return rootLists;
	}
	
}
