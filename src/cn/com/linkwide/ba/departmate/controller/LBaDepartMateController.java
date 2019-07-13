package cn.com.linkwide.ba.departmate.controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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
import org.jeecgframework.core.util.ListUtils;
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

import cn.com.linkwide.ba.departmate.entity.LBaDepartMateEntity;
import cn.com.linkwide.ba.departmate.service.LBaDepartMateServiceI;
import cn.com.linkwide.ba.material.record.entity.LBaMaterialEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**   
 * @Title: Controller  
 * @Description: 科室物资对照表
 * @author onlineGenerator
 * @date 2018-07-06 11:12:12
 * @version V1.0   
 *
 */
@Api(value="LBaDepartMate",description="科室物资对照表",tags="lBaDepartMateController")
@Controller
@RequestMapping("/lBaDepartMateController")
public class LBaDepartMateController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(LBaDepartMateController.class);

	@Autowired
	private LBaDepartMateServiceI lBaDepartMateService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 科室物资对照表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("cn/com/linkwide/ba/departmate/lBaDepartMateList");
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
	public void datagrid(LBaDepartMateEntity lBaDepartMate,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(LBaDepartMateEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, lBaDepartMate, request.getParameterMap());
		try{
			//自定义追加查询条件
			//过滤当前医院下的科室
			/*String sql ="SELECT id from t_s_depart where org_code like '"+ResourceUtil.getUserComponyCode()+"%'";
			List<Map<String,Object>> departsList = systemService.findForJdbc(sql);
			if(departsList != null && departsList.size() >0){
				String[] ids = new String[departsList.size()];
				for(int i=0 ;i<departsList.size() ;i++){
					ids[i] = departsList.get(i).get("id").toString();
				}
				Conjunction criteria =  Restrictions.conjunction();
				criteria.add(Restrictions.in("departId", ids)); 
				cq.add(criteria);
			}*/
			cq.add();
			this.lBaDepartMateService.getDataGridReturn(cq, true);
			Map<String,Map<String,Object>> expMap = new HashMap<String,Map<String,Object>>();
			List<LBaDepartMateEntity> results = dataGrid.getResults();
			Map<String,LBaMaterialEntity> mMap= null;
			if(results != null && !results.isEmpty()){
				List<String> mList = ListUtils.extractProperty(results, "mateId");
				List<LBaMaterialEntity>  mateList =systemService.findByProperty(LBaMaterialEntity.class, "id", mList.toArray());
				mMap = systemService.list2Map(LBaMaterialEntity.class,mateList , "id");
			}
			for(LBaDepartMateEntity rul: results){
				Map<String,Object> map = new HashMap();
				map.put("dept", rul.getDepartId());
				map.put("mate", rul.getMateId());
				map.put("mate1", rul.getMateId());
				if(mMap.get(rul.getMateId()) != null){
					rul.setMaterialCode(mMap.get(rul.getMateId()).getMaterialCode());
					rul.setMaterialName(mMap.get(rul.getMateId()).getMaterialName());
				}
				expMap.put(rul.getId(),map); 
			}
			TagUtil.datagrid(response, dataGrid,expMap);
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		
	} 
		
	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "mateDatagrid")
	public void mateDatagrid(LBaDepartMateEntity lBaDepartMate,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		try{
		//自定义追加查询条件
			String departId = lBaDepartMate.getDepartId();
			if(StringUtil.isEmpty(departId)){
				TagUtil.datagrid(response, dataGrid);
				return;
			}
			StringBuffer sql = new StringBuffer();
			sql.append(" from 	l_ba_material a  WHERE id  not in  ( select  mate_id from l_ba_depart_mate b where b.depart_id = '"+departId+"' ) ");
			if(StringUtil.isNotEmpty(lBaDepartMate.getMateId())){
				sql.append(" and  ( a.material_code like '%"+lBaDepartMate.getMateId()+"%' or  a.material_name like '%"+lBaDepartMate.getMateId()+"%' )");
			}
			List<Map<String,Object>> list = systemService.findForJdbc("select a.id  id ,a.material_code  code,a.material_name  name "+sql.toString(), dataGrid.getPage(), dataGrid.getRows());
			dataGrid.setResults(list);
			List<Map<String,Object>> listC = systemService.findForJdbc("select count(a.id)  con "+sql.toString());
			dataGrid.setTotal(Integer.valueOf(listC.get(0).get("con").toString()));
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除科室物资对照表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(LBaDepartMateEntity lBaDepartMate, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		lBaDepartMate = systemService.getEntity(LBaDepartMateEntity.class, lBaDepartMate.getId());
		message = "科室物资对照表删除成功";
		try{
			lBaDepartMateService.delete(lBaDepartMate);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "科室物资对照表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除科室物资对照表
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "科室物资对照表删除成功";
		try{
			for(String id:ids.split(",")){
				LBaDepartMateEntity lBaDepartMate = systemService.getEntity(LBaDepartMateEntity.class, 
				id
				);
				lBaDepartMateService.delete(lBaDepartMate);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "科室物资对照表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加科室物资对照表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(LBaDepartMateEntity lBaDepartMate, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "科室物资对照表添加成功";
		try{
			String ids = request.getParameter("ids");
			String departId = request.getParameter("departId");
			String[] idArray = ids.split(",");
			for(int i=0;i<idArray.length;i++){
				LBaDepartMateEntity ent = new LBaDepartMateEntity();
				ent.setDepartId(departId);
				ent.setMateId(idArray[i]);
				lBaDepartMateService.save(ent);
			}
			
			
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "科室物资对照表添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新科室物资对照表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(LBaDepartMateEntity lBaDepartMate, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "科室物资对照表更新成功";
		LBaDepartMateEntity t = lBaDepartMateService.get(LBaDepartMateEntity.class, lBaDepartMate.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(lBaDepartMate, t);
			lBaDepartMateService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "科室物资对照表更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 科室物资对照表新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(LBaDepartMateEntity lBaDepartMate, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(lBaDepartMate.getId())) {
			lBaDepartMate = lBaDepartMateService.getEntity(LBaDepartMateEntity.class, lBaDepartMate.getId());
			req.setAttribute("lBaDepartMatePage", lBaDepartMate);
		}
		return new ModelAndView("cn/com/linkwide/ba/departmate/lBaDepartMate-add");
	}
	/**
	 * 科室物资对照表编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(LBaDepartMateEntity lBaDepartMate, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(lBaDepartMate.getId())) {
			lBaDepartMate = lBaDepartMateService.getEntity(LBaDepartMateEntity.class, lBaDepartMate.getId());
			req.setAttribute("lBaDepartMatePage", lBaDepartMate);
		}
		return new ModelAndView("cn/com/linkwide/ba/departmate/lBaDepartMate-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","lBaDepartMateController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(LBaDepartMateEntity lBaDepartMate,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(LBaDepartMateEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, lBaDepartMate, request.getParameterMap());
		List<LBaDepartMateEntity> lBaDepartMates = this.lBaDepartMateService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"科室物资对照表");
		modelMap.put(NormalExcelConstants.CLASS,LBaDepartMateEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("科室物资对照表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,lBaDepartMates);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(LBaDepartMateEntity lBaDepartMate,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"科室物资对照表");
    	modelMap.put(NormalExcelConstants.CLASS,LBaDepartMateEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("科室物资对照表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				//科室
				List<TSDepart> tList = systemService.getList(TSDepart.class);
				Map<String,TSDepart> tMap = systemService.list2Map(TSDepart.class, tList, "orgCode");
				//物资
				List<LBaMaterialEntity> mList = systemService.getList(LBaMaterialEntity.class);
				Map<String,LBaMaterialEntity> mMap = systemService.list2Map(LBaMaterialEntity.class, mList, "materialCode");
				List<LBaDepartMateEntity> listLBaDepartMateEntitys = ExcelImportUtil.importExcel(file.getInputStream(),LBaDepartMateEntity.class,params);
				for (LBaDepartMateEntity lBaDepartMate : listLBaDepartMateEntitys) {
					if(tMap.containsKey(lBaDepartMate.getDepartId())){
						lBaDepartMate.setDepartId(tMap.get(lBaDepartMate.getDepartId()).getId());
					}else{
						j.setSuccess(false);
						j.setMsg("科室“"+lBaDepartMate.getDepartId()+"”不存在!");
						return j;
					}
					if(mMap.containsKey(lBaDepartMate.getMateId())){
						lBaDepartMate.setMateId(mMap.get(lBaDepartMate.getMateId()).getId());
					}else{
						j.setSuccess(false);
						j.setMsg("物资“"+lBaDepartMate.getMateId()+"”不存在!");
						return j;
					}
				}
				for (LBaDepartMateEntity lBaDepartMate : listLBaDepartMateEntitys) {
					lBaDepartMateService.save(lBaDepartMate);
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
	@ApiOperation(value="科室物资对照表列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<LBaDepartMateEntity>> list() {
		List<LBaDepartMateEntity> listLBaDepartMates=lBaDepartMateService.getList(LBaDepartMateEntity.class);
		return Result.success(listLBaDepartMates);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取科室物资对照表信息",notes="根据ID获取科室物资对照表信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		LBaDepartMateEntity task = lBaDepartMateService.get(LBaDepartMateEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取科室物资对照表信息为空");
		}
		return Result.success(task);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建科室物资对照表")
	public ResponseMessage<?> create(@ApiParam(name="科室物资对照表对象")@RequestBody LBaDepartMateEntity lBaDepartMate, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<LBaDepartMateEntity>> failures = validator.validate(lBaDepartMate);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			lBaDepartMateService.save(lBaDepartMate);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("科室物资对照表信息保存失败");
		}
		return Result.success(lBaDepartMate);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新科室物资对照表",notes="更新科室物资对照表")
	public ResponseMessage<?> update(@ApiParam(name="科室物资对照表对象")@RequestBody LBaDepartMateEntity lBaDepartMate) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<LBaDepartMateEntity>> failures = validator.validate(lBaDepartMate);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			lBaDepartMateService.saveOrUpdate(lBaDepartMate);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("更新科室物资对照表信息失败");
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success("更新科室物资对照表信息成功");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除科室物资对照表")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" + id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			lBaDepartMateService.deleteEntityById(LBaDepartMateEntity.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("科室物资对照表删除失败");
		}

		return Result.success();
	}
}
