package cn.com.linkwide.ba.baarea.controller;
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
import org.jeecgframework.core.common.model.json.ComboTree;
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

import cn.com.linkwide.ba.baarea.entity.BaAreaEntity;
import cn.com.linkwide.ba.baarea.service.BaAreaServiceI;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**   
 * @Title: Controller  
 * @Description: 地区
 * @author onlineGenerator
 * @date 2018-09-17 15:33:28
 * @version V1.0   
 *
 */
@Api(value="BaArea",description="地区",tags="baAreaController")
@Controller
@RequestMapping("/baAreaController")
public class BaAreaController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(BaAreaController.class);

	@Autowired
	private BaAreaServiceI baAreaService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 地区列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("cn/com/linkwide/ba/baarea/baAreaList");
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
	public void datagrid(BaAreaEntity baArea,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		//根据编码和名称模糊查询
		String areaCode = baArea.getAreaCode();
		String areaName = baArea.getAreaName();
		if(StringUtil.isNotEmpty(areaCode)){
			baArea.setAreaCode("*"+areaCode+"*");
		}
		if(StringUtil.isNotEmpty(areaName)){
			baArea.setAreaName("*"+areaName+"*");
		}
		CriteriaQuery cq = new CriteriaQuery(BaAreaEntity.class, dataGrid);
		if(StringUtil.isEmpty(baArea.getId())){
//			cq.add(Restrictions.in("parentCode", new Object[]{null,""}));
			cq.isNull("parentCode");
		}else{
			cq.eq("parentCode", systemService.get(BaAreaEntity.class, baArea.getId()).getAreaCode());
			baArea.setId(null);
		}
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, baArea, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.baAreaService.getDataGridReturn(cq, true);
		TagUtil.treegrid(response, dataGrid);
	}
	
	/**
	 * 删除地区
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(BaAreaEntity baArea, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		baArea = systemService.getEntity(BaAreaEntity.class, baArea.getId());
		message = "地区删除成功";
		try{
			//删除校验  删除时校验是否存在下级
			String code = baArea.getAreaCode();
			List<BaAreaEntity> list =  systemService.findByProperty(BaAreaEntity.class, "parentCode", code);
			if(list != null && list.size()>0){
				j.setMsg("该地区存在下级地区，不能删除！");
				return j;
			}
			baAreaService.delete(baArea);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "地区删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除地区
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "地区删除成功";
		try{
			for(String id:ids.split(",")){
				BaAreaEntity baArea = systemService.getEntity(BaAreaEntity.class, 
				id
				);
				baAreaService.delete(baArea);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "地区删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加地区
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(BaAreaEntity baArea, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "地区添加成功";
		try{
			if(StringUtil.isEmpty(baArea.getParentCode())){
				baArea.setParentCode(null);
			}
			baAreaService.save(baArea);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "地区添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新地区
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(BaAreaEntity baArea, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "地区更新成功";
		BaAreaEntity t = baAreaService.get(BaAreaEntity.class, baArea.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(baArea, t);
			if(StringUtil.isEmpty(t.getParentCode())){
				t.setParentCode(null);
			}else{
				//t.setParentCode(systemService.get(BaAreaEntity.class, baArea.getParentCode()).getAreaCode());
			}
			baAreaService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "地区更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 地区新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(BaAreaEntity baArea, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(baArea.getId())) {
			baArea = baAreaService.getEntity(BaAreaEntity.class, baArea.getId());
			req.setAttribute("baAreaPage", baArea);
		}
		return new ModelAndView("cn/com/linkwide/ba/baarea/baArea-add");
	}
	/**
	 * 地区编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(BaAreaEntity baArea, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(baArea.getId())) {
			baArea = baAreaService.getEntity(BaAreaEntity.class, baArea.getId());
			req.setAttribute("baAreaPage", baArea);
		}
		return new ModelAndView("cn/com/linkwide/ba/baarea/baArea-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","baAreaController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(BaAreaEntity baArea,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(BaAreaEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, baArea, request.getParameterMap());
		List<BaAreaEntity> baAreas = this.baAreaService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"地区");
		modelMap.put(NormalExcelConstants.CLASS,BaAreaEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("地区列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,baAreas);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(BaAreaEntity baArea,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"地区");
    	modelMap.put(NormalExcelConstants.CLASS,BaAreaEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("地区列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				Map areaMap=new HashMap();
				String sql1= " select area_code \"areaCode\",area_name \"areaName\" from ba_area ";
				List<Map<String,Object>> deptListMap = systemService.findForJdbc(sql1, new Object[]{});
				if(deptListMap!= null && deptListMap.size()>0){
					for (Map<String, Object> map : deptListMap) {
						areaMap.put(map.get("areaCode"), map.get("areaName"));
					}
				}
				
				List<BaAreaEntity> listBaAreaEntitys = ExcelImportUtil.importExcel(file.getInputStream(),BaAreaEntity.class,params);
				for (BaAreaEntity baArea : listBaAreaEntitys) {
					//保存前校验
					String code = baArea.getAreaCode();
					if(areaMap!= null && StringUtil.isNotEmpty(areaMap.get(code))){
						j.setMsg("编码"+code+"已存在！");
						return j;
					}else{
						areaMap.put(code, baArea.getAreaName());
					}
//					List<BaAreaEntity> list =  systemService.findByProperty(BaAreaEntity.class, "areaCode", code);
//					if(list != null && list.size()>0){
//						j.setMsg("编码"+code+"已存在！");
//						return j;
//					}
					baAreaService.save(baArea);
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
	@ApiOperation(value="地区列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<BaAreaEntity>> list() {
		List<BaAreaEntity> listBaAreas=baAreaService.getList(BaAreaEntity.class);
		return Result.success(listBaAreas);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取地区信息",notes="根据ID获取地区信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		BaAreaEntity task = baAreaService.get(BaAreaEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取地区信息为空");
		}
		return Result.success(task);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建地区")
	public ResponseMessage<?> create(@ApiParam(name="地区对象")@RequestBody BaAreaEntity baArea, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<BaAreaEntity>> failures = validator.validate(baArea);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			baAreaService.save(baArea);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("地区信息保存失败");
		}
		return Result.success(baArea);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新地区",notes="更新地区")
	public ResponseMessage<?> update(@ApiParam(name="地区对象")@RequestBody BaAreaEntity baArea) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<BaAreaEntity>> failures = validator.validate(baArea);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			baAreaService.saveOrUpdate(baArea);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("更新地区信息失败");
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success("更新地区信息成功");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除地区")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" + id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			baAreaService.deleteEntityById(BaAreaEntity.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("地区删除失败");
		}

		return Result.success();
	}
	/**
	 * 上级地区下拉查询
	 * @param request
	 * @return
	 */
	@RequestMapping(params="loadBaArea")
	@ResponseBody
	public List<ComboTree> loadBaArea(HttpServletRequest request) {
		List<ComboTree> rootLists=new ArrayList<ComboTree>();
		//编辑页面  上级地区下拉去掉自身
		String selfId=request.getParameter("selfId");
		//查询所有一级预算科室
		String firstHql=" from BaAreaEntity a where (a.parentCode IS NULL or a.parentCode ='') ";
		if(StringUtil.isNotEmpty(selfId)){
			firstHql+=" and areaCode <> '"+selfId+"' ";
		}
		List<BaAreaEntity> firstList = systemService.findHql(firstHql);
		if(firstList!=null&&firstList.size()>0){
			//将所有 预算科室拼成预算科室树
			ComboTree comT=new ComboTree();
			ComboTree comboTree = loadAllFundsSource(firstList, comT);
			comboTree.setId(null);
			comboTree.setText("地区");
			comboTree.setState("opend");
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("isLast","N");
			comboTree.setAttributes(map);
			rootLists.add(comboTree);
		}
		return rootLists;
	}
	
	/**
	 * 根据预算科室加载下级预算科室
	 * @param list 
	 * @param combotree
	 * @return
	 */
	public ComboTree loadAllFundsSource(List<BaAreaEntity> list,ComboTree combotree){
		if(list!=null&&list.size()>0){
			List<ComboTree> comTreeList=new ArrayList<ComboTree>();
			for(BaAreaEntity ba:list){
				ComboTree com=new ComboTree();
				com.setId(ba.getAreaCode());
				com.setText(ba.getAreaName());
				Map<String, Object> map=new HashMap<String, Object>();
				map.put("isLast",ba.getIsLast());
				com.setAttributes(map);
				
				//查询所有下级地区
				String hql="from BaAreaEntity a where a.parentCode= ? ";
				List<BaAreaEntity> comebasList = systemService.findHql(hql,ba.getAreaCode());
				if(comebasList.size()>0){
					com.setState("closed");
					loadAllFundsSource(comebasList, com);
				}else{
					com.setState("open");
				}
				comTreeList.add(com);
				combotree.setChildren(comTreeList);
			}
		}
		return combotree;
	}
}
