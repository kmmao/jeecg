package cn.com.linkwide.ba.material.measureunit.controller;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

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
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.tag.vo.datatable.SortDirection;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

import cn.com.linkwide.ba.material.materunit.entity.LBaMaterialMaterUnitEntity;
import cn.com.linkwide.ba.material.measureunit.entity.LBaMaterialMeasureUnitEntity;
import cn.com.linkwide.ba.material.measureunit.service.LBaMaterialMeasureUnitServiceI;
import cn.com.linkwide.ba.material.qualitem.entity.LSuMaterialQualItemEntity;
import cn.com.linkwide.ba.material.record.entity.LBaMaterialEntity;

/**   
 * @Title: Controller  
 * @Description: 辅助计量单位
 * @author onlineGenerator
 * @date 2017-12-05 11:03:06
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/lBaMaterialMeasureUnitController")
public class LBaMaterialMeasureUnitController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(LBaMaterialMeasureUnitController.class);

	@Autowired
	private LBaMaterialMeasureUnitServiceI lBaMaterialMeasureUnitService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 辅助计量单位列表 页面跳转
	 * cn/com/linkwide/ba/material/measureunit
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		String lBaMaterialId = request.getParameter("lBaMaterialId");
		request.setAttribute("lBaMaterialId", lBaMaterialId);
		return new ModelAndView("cn/com/linkwide/ba/material/measureunit/lBaMaterialMeasureUnitList");
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
	public void datagrid(LBaMaterialMeasureUnitEntity lBaMaterialMeasureUnit,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(LBaMaterialMeasureUnitEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, lBaMaterialMeasureUnit, request.getParameterMap());
		try{
		//自定义追加查询条件
			cq.addOrder("createDate", SortDirection.desc);
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.lBaMaterialMeasureUnitService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除辅助计量单位
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(LBaMaterialMeasureUnitEntity lBaMaterialMeasureUnit, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		lBaMaterialMeasureUnit = systemService.getEntity(LBaMaterialMeasureUnitEntity.class, lBaMaterialMeasureUnit.getId());
		message = "辅助计量单位删除成功";
		try{
			lBaMaterialMeasureUnitService.delete(lBaMaterialMeasureUnit);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "辅助计量单位删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除辅助计量单位
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "辅助计量单位删除成功";
		try{
			for(String id:ids.split(",")){
				LBaMaterialMeasureUnitEntity lBaMaterialMeasureUnit = systemService.getEntity(LBaMaterialMeasureUnitEntity.class, 
				id
				);
				lBaMaterialMeasureUnitService.delete(lBaMaterialMeasureUnit);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "辅助计量单位删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加辅助计量单位
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(LBaMaterialMeasureUnitEntity lBaMaterialMeasureUnit, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "辅助计量单位添加成功";
		try{
			
			//校验
			AjaxJson ajaxJson = vailRep(lBaMaterialMeasureUnit);
			if(!ajaxJson.isSuccess()){
				return ajaxJson;
			}
			
			TSUser tsUser = ResourceUtil.getSessionUserName();
			lBaMaterialMeasureUnit.setCreateBy(tsUser.getId());
			lBaMaterialMeasureUnit.setCreateDate(new Date());
			lBaMaterialMeasureUnit.setDepartId(tsUser.getDepartid());
			lBaMaterialMeasureUnitService.save(lBaMaterialMeasureUnit);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "辅助计量单位添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	public AjaxJson vailRep(LBaMaterialMeasureUnitEntity lBaMaterialMeasureUnit){
		AjaxJson j = new AjaxJson();
		List<LBaMaterialMeasureUnitEntity> l1 = lBaMaterialMeasureUnitService.findByQueryString(" from LBaMaterialMeasureUnitEntity where id != '"+lBaMaterialMeasureUnit.getId()+"' and materUnitId = '"+lBaMaterialMeasureUnit.getMaterUnitId()+"' and materialId = '"+lBaMaterialMeasureUnit.getMaterialId()+"'");
		if(l1.size() > 0){
			j.setMsg("辅助计量单位已经存在");
			j.setSuccess(false);
			return j;
		}
		
		LBaMaterialEntity lm = systemService.getEntity(LBaMaterialEntity.class, lBaMaterialMeasureUnit.getMaterialId());
		
		List<LBaMaterialMeasureUnitEntity> l2 = lBaMaterialMeasureUnitService.findByQueryString(" from LBaMaterialMeasureUnitEntity where id != '"+lBaMaterialMeasureUnit.getId()+"' and materUnitId = '"+lm.getMaterUnitId()+"' and materialId = '"+lBaMaterialMeasureUnit.getMaterialId()+"'");
		if(l2.size() > 0){
			j.setMsg("辅助计量单位不能和主计量单位相同");
			j.setSuccess(false);
			return j;
		}
		
		return j;
	}
	
	/**
	 * 更新辅助计量单位
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(LBaMaterialMeasureUnitEntity lBaMaterialMeasureUnit, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "辅助计量单位更新成功";
		LBaMaterialMeasureUnitEntity t = lBaMaterialMeasureUnitService.get(LBaMaterialMeasureUnitEntity.class, lBaMaterialMeasureUnit.getId());
		try {
			//校验
			AjaxJson ajaxJson = vailRep(t);
			if(!ajaxJson.isSuccess()){
				return ajaxJson;
			}
			
			MyBeanUtils.copyBeanNotNull2Bean(lBaMaterialMeasureUnit, t);
			TSUser tsUser = ResourceUtil.getSessionUserName();
			t.setUpdateBy(tsUser.getId());
			t.setUpdateDate(new Date());
			lBaMaterialMeasureUnitService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "辅助计量单位更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 辅助计量单位新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(LBaMaterialMeasureUnitEntity lBaMaterialMeasureUnit, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(lBaMaterialMeasureUnit.getId())) {
			lBaMaterialMeasureUnit = lBaMaterialMeasureUnitService.getEntity(LBaMaterialMeasureUnitEntity.class, lBaMaterialMeasureUnit.getId());
			req.setAttribute("lBaMaterialMeasureUnitPage", lBaMaterialMeasureUnit);
		}
		String materialId = req.getParameter("materialId");
		req.setAttribute("materialId", materialId);
		return new ModelAndView("cn/com/linkwide/ba/material/measureunit/lBaMaterialMeasureUnit-add");
	}
	/**
	 * 辅助计量单位编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(LBaMaterialMeasureUnitEntity lBaMaterialMeasureUnit, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(lBaMaterialMeasureUnit.getId())) {
			lBaMaterialMeasureUnit = lBaMaterialMeasureUnitService.getEntity(LBaMaterialMeasureUnitEntity.class, lBaMaterialMeasureUnit.getId());
			req.setAttribute("lBaMaterialMeasureUnitPage", lBaMaterialMeasureUnit);
			LBaMaterialMaterUnitEntity lBaMaterialMaterUnitEntity = systemService.getEntity(LBaMaterialMaterUnitEntity.class, lBaMaterialMeasureUnit.getMaterUnitId());
			req.setAttribute("tName", lBaMaterialMaterUnitEntity.getTypeName());
		}
		return new ModelAndView("cn/com/linkwide/ba/material/measureunit/lBaMaterialMeasureUnit-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","lBaMaterialMeasureUnitController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(LBaMaterialMeasureUnitEntity lBaMaterialMeasureUnit,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(LBaMaterialMeasureUnitEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, lBaMaterialMeasureUnit, request.getParameterMap());
		List<LBaMaterialMeasureUnitEntity> lBaMaterialMeasureUnits = this.lBaMaterialMeasureUnitService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"辅助计量单位");
		modelMap.put(NormalExcelConstants.CLASS,LBaMaterialMeasureUnitEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("辅助计量单位列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,lBaMaterialMeasureUnits);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(LBaMaterialMeasureUnitEntity lBaMaterialMeasureUnit,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"辅助计量单位");
    	modelMap.put(NormalExcelConstants.CLASS,LBaMaterialMeasureUnitEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("辅助计量单位列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
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
				List<LBaMaterialMeasureUnitEntity> listLBaMaterialMeasureUnitEntitys = ExcelImportUtil.importExcel(file.getInputStream(),LBaMaterialMeasureUnitEntity.class,params);
				for (LBaMaterialMeasureUnitEntity lBaMaterialMeasureUnit : listLBaMaterialMeasureUnitEntitys) {
					lBaMaterialMeasureUnitService.save(lBaMaterialMeasureUnit);
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
	public List<LBaMaterialMeasureUnitEntity> list() {
		List<LBaMaterialMeasureUnitEntity> listLBaMaterialMeasureUnits=lBaMaterialMeasureUnitService.getList(LBaMaterialMeasureUnitEntity.class);
		return listLBaMaterialMeasureUnits;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		LBaMaterialMeasureUnitEntity task = lBaMaterialMeasureUnitService.get(LBaMaterialMeasureUnitEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody LBaMaterialMeasureUnitEntity lBaMaterialMeasureUnit, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<LBaMaterialMeasureUnitEntity>> failures = validator.validate(lBaMaterialMeasureUnit);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		try{
			lBaMaterialMeasureUnitService.save(lBaMaterialMeasureUnit);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = lBaMaterialMeasureUnit.getId();
		URI uri = uriBuilder.path("/rest/lBaMaterialMeasureUnitController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody LBaMaterialMeasureUnitEntity lBaMaterialMeasureUnit) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<LBaMaterialMeasureUnitEntity>> failures = validator.validate(lBaMaterialMeasureUnit);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		try{
			lBaMaterialMeasureUnitService.saveOrUpdate(lBaMaterialMeasureUnit);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		lBaMaterialMeasureUnitService.deleteEntityById(LBaMaterialMeasureUnitEntity.class, id);
	}
}
