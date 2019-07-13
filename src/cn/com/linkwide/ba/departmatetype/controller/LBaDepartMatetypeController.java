package cn.com.linkwide.ba.departmatetype.controller;
import cn.com.linkwide.ba.departmatetype.entity.LBaDepartMatetypeEntity;
import cn.com.linkwide.ba.departmatetype.service.LBaDepartMatetypeServiceI;
import cn.com.linkwide.ba.material.type.entity.LBaMaterialTypeEntity;

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
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.MatchMode;
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
 * @Description: 科室物资分类对照
 * @author onlineGenerator
 * @date 2018-09-06 17:08:47
 * @version V1.0   
 *
 */
@Api(value="LBaDepartMatetype",description="科室物资分类对照",tags="lBaDepartMatetypeController")
@Controller
@RequestMapping("/lBaDepartMatetypeController")
public class LBaDepartMatetypeController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(LBaDepartMatetypeController.class);

	@Autowired
	private LBaDepartMatetypeServiceI lBaDepartMatetypeService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 科室物资分类对照列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("cn/com/linkwide/ba/departmatetype/lBaDepartMatetypeList");
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
	public void datagrid(LBaDepartMatetypeEntity lBaDepartMatetype,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(LBaDepartMatetypeEntity.class, dataGrid);
		lBaDepartMatetype.setDepartId(request.getParameter("departIdQ")); 
		String types = request.getParameter("matetypeIdQ");
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, lBaDepartMatetype, request.getParameterMap());
		try{
			//自定义追加查询条件
			if(StringUtil.isNotEmpty(types)){
				String[] ids  = types.split(",");
				LBaMaterialTypeEntity type = systemService.findUniqueByProperty(LBaMaterialTypeEntity.class, "id", ids[0]);
				if(type != null){
					List<Map<String,Object>> list = systemService.findForJdbc("SELECT id FROM l_ba_material_type where type_code like '"+type.getTypeCode()+"%'");
					String[] typeIds = new String[list.size()];
					for(int i=0;i<list.size();i++){
						typeIds[i] = list.get(i).get("id").toString();
					}
					Conjunction criteria =  Restrictions.conjunction();
					//criteria.add(Restrictions.in("matetypeId", ids)); 
					criteria.add(Restrictions.in("matetypeId", typeIds)); 
					cq.add(criteria);
				}
			}
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.lBaDepartMatetypeService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除科室物资分类对照
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(LBaDepartMatetypeEntity lBaDepartMatetype, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		lBaDepartMatetype = systemService.getEntity(LBaDepartMatetypeEntity.class, lBaDepartMatetype.getId());
		message = "科室物资分类对照删除成功";
		try{
			lBaDepartMatetypeService.delete(lBaDepartMatetype);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "科室物资分类对照删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除科室物资分类对照
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "科室物资分类对照删除成功";
		try{
			for(String id:ids.split(",")){
				LBaDepartMatetypeEntity lBaDepartMatetype = systemService.getEntity(LBaDepartMatetypeEntity.class, 
				id
				);
				lBaDepartMatetypeService.delete(lBaDepartMatetype);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "科室物资分类对照删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加科室物资分类对照
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(LBaDepartMatetypeEntity lBaDepartMatetype, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "科室物资分类对照添加成功";
		try{
			
			String types = lBaDepartMatetype.getMatetypeId();
			String[] ids = types.split(",");
			systemService.updateBySqlString("delete from l_ba_depart_matetype where depart_id ='"+lBaDepartMatetype.getDepartId()+"' and matetype_id in ('"+lBaDepartMatetype.getMatetypeId().replace(",", "','")+"')");
			for(int i=0;i<ids.length;i++){
				LBaDepartMatetypeEntity entity = new LBaDepartMatetypeEntity(); 
				entity.setDepartId(lBaDepartMatetype.getDepartId());
				entity.setMatetypeId(ids[i]);
				lBaDepartMatetypeService.save(entity);
			}
			
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "科室物资分类对照添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新科室物资分类对照
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(LBaDepartMatetypeEntity lBaDepartMatetype, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "科室物资分类对照更新成功";
		LBaDepartMatetypeEntity t = lBaDepartMatetypeService.get(LBaDepartMatetypeEntity.class, lBaDepartMatetype.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(lBaDepartMatetype, t);
			String hql =" from LBaDepartMatetypeEntity where departId ='"+lBaDepartMatetype.getDepartId()+"' and matetypeId ='"+lBaDepartMatetype.getMatetypeId()+"'";
			List<LBaDepartMatetypeEntity> entity = systemService.findHql(hql);
			if(entity != null && entity.size()>0){
				lBaDepartMatetypeService.delete(t);
			}else{
				lBaDepartMatetypeService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			}
		} catch (Exception e) {
			e.printStackTrace();
			message = "科室物资分类对照更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 科室物资分类对照新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(LBaDepartMatetypeEntity lBaDepartMatetype, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(lBaDepartMatetype.getId())) {
			lBaDepartMatetype = lBaDepartMatetypeService.getEntity(LBaDepartMatetypeEntity.class, lBaDepartMatetype.getId());
			req.setAttribute("lBaDepartMatetypePage", lBaDepartMatetype);
		}
		return new ModelAndView("cn/com/linkwide/ba/departmatetype/lBaDepartMatetype-add");
	}
	/**
	 * 科室物资分类对照编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(LBaDepartMatetypeEntity lBaDepartMatetype, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(lBaDepartMatetype.getId())) {
			lBaDepartMatetype = lBaDepartMatetypeService.getEntity(LBaDepartMatetypeEntity.class, lBaDepartMatetype.getId());
			req.setAttribute("lBaDepartMatetypePage", lBaDepartMatetype);
		}
		return new ModelAndView("cn/com/linkwide/ba/departmatetype/lBaDepartMatetype-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","lBaDepartMatetypeController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(LBaDepartMatetypeEntity lBaDepartMatetype,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(LBaDepartMatetypeEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, lBaDepartMatetype, request.getParameterMap());
		List<LBaDepartMatetypeEntity> lBaDepartMatetypes = this.lBaDepartMatetypeService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"科室物资分类对照");
		modelMap.put(NormalExcelConstants.CLASS,LBaDepartMatetypeEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("科室物资分类对照列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,lBaDepartMatetypes);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(LBaDepartMatetypeEntity lBaDepartMatetype,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"科室物资分类对照");
    	modelMap.put(NormalExcelConstants.CLASS,LBaDepartMatetypeEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("科室物资分类对照列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<LBaDepartMatetypeEntity> listLBaDepartMatetypeEntitys = ExcelImportUtil.importExcel(file.getInputStream(),LBaDepartMatetypeEntity.class,params);
				for (LBaDepartMatetypeEntity lBaDepartMatetype : listLBaDepartMatetypeEntitys) {
					lBaDepartMatetypeService.save(lBaDepartMatetype);
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
	@ApiOperation(value="科室物资分类对照列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<LBaDepartMatetypeEntity>> list() {
		List<LBaDepartMatetypeEntity> listLBaDepartMatetypes=lBaDepartMatetypeService.getList(LBaDepartMatetypeEntity.class);
		return Result.success(listLBaDepartMatetypes);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取科室物资分类对照信息",notes="根据ID获取科室物资分类对照信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		LBaDepartMatetypeEntity task = lBaDepartMatetypeService.get(LBaDepartMatetypeEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取科室物资分类对照信息为空");
		}
		return Result.success(task);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建科室物资分类对照")
	public ResponseMessage<?> create(@ApiParam(name="科室物资分类对照对象")@RequestBody LBaDepartMatetypeEntity lBaDepartMatetype, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<LBaDepartMatetypeEntity>> failures = validator.validate(lBaDepartMatetype);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			lBaDepartMatetypeService.save(lBaDepartMatetype);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("科室物资分类对照信息保存失败");
		}
		return Result.success(lBaDepartMatetype);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新科室物资分类对照",notes="更新科室物资分类对照")
	public ResponseMessage<?> update(@ApiParam(name="科室物资分类对照对象")@RequestBody LBaDepartMatetypeEntity lBaDepartMatetype) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<LBaDepartMatetypeEntity>> failures = validator.validate(lBaDepartMatetype);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			lBaDepartMatetypeService.saveOrUpdate(lBaDepartMatetype);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("更新科室物资分类对照信息失败");
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success("更新科室物资分类对照信息成功");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除科室物资分类对照")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" + id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			lBaDepartMatetypeService.deleteEntityById(LBaDepartMatetypeEntity.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("科室物资分类对照删除失败");
		}

		return Result.success();
	}
}
