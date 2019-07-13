package cn.com.linkwide.ba.location.controller;
import cn.com.linkwide.ba.location.entity.LBaLocationDictEntity;
import cn.com.linkwide.ba.location.service.LBaLocationDictServiceI;
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
import org.jeecgframework.web.system.pojo.base.TSFunction;
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
 * @Description: 货位档案
 * @author onlineGenerator
 * @date 2018-07-05 18:44:29
 * @version V1.0   
 *
 */
@Api(value="LBaLocationDict",description="货位档案",tags="lBaLocationDictController")
@Controller
@RequestMapping("/lBaLocationDictController")
public class LBaLocationDictController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(LBaLocationDictController.class);

	@Autowired
	private LBaLocationDictServiceI lBaLocationDictService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 货位档案列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("cn/com/linkwide/ba/location/lBaLocationDictList");
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
	public void datagrid(LBaLocationDictEntity lBaLocationDict,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(LBaLocationDictEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, lBaLocationDict, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.lBaLocationDictService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除货位档案
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(LBaLocationDictEntity lBaLocationDict, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		lBaLocationDict = systemService.getEntity(LBaLocationDictEntity.class, lBaLocationDict.getId());
		message = "货位档案删除成功";
		try{
			lBaLocationDictService.delete(lBaLocationDict);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "货位档案删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除货位档案
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "货位档案删除成功";
		try{
			for(String id:ids.split(",")){
				LBaLocationDictEntity lBaLocationDict = systemService.getEntity(LBaLocationDictEntity.class, 
				id
				);
				lBaLocationDictService.delete(lBaLocationDict);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "货位档案删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加货位档案
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(LBaLocationDictEntity lBaLocationDict, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "货位档案添加成功";
		try{
			if("".equals(lBaLocationDict.getParentId()))
				lBaLocationDict.setParentId(null);
			lBaLocationDictService.save(lBaLocationDict);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "货位档案添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新货位档案
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(LBaLocationDictEntity lBaLocationDict, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "货位档案更新成功";
		LBaLocationDictEntity t = lBaLocationDictService.get(LBaLocationDictEntity.class, lBaLocationDict.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(lBaLocationDict, t);
			lBaLocationDictService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "货位档案更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 停用启用
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doEnDisAble")
	@ResponseBody
	public AjaxJson doEnDisAble(LBaLocationDictEntity lBaLocationDict, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "货位档案更新成功";
		LBaLocationDictEntity t = lBaLocationDictService.get(LBaLocationDictEntity.class, lBaLocationDict.getId());
		try {
			if(lBaLocationDict.getParentId() != null){
				LBaLocationDictEntity t0 = lBaLocationDictService.get(LBaLocationDictEntity.class, lBaLocationDict.getParentId());
				if(t0 !=null){
					if(t0.getIsStop()==1){
						j.setMsg("400");
						return j;
						
					}
				}
			}
			
			List<LBaLocationDictEntity> t2 = systemService.findByProperty(LBaLocationDictEntity.class,"parentId", lBaLocationDict.getId());
			if(t2.size()>0 && t2.get(0) !=null){
					j.setMsg("exist");
					return j;
			}
			Integer isStop = Integer.valueOf(request.getParameter("status").toString());
			t.setIsStop(isStop);
			lBaLocationDictService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "货位档案更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 货位档案新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(LBaLocationDictEntity lBaLocationDict, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(lBaLocationDict.getId())) {
			lBaLocationDict = lBaLocationDictService.getEntity(LBaLocationDictEntity.class, lBaLocationDict.getId());
			req.setAttribute("lBaLocationDictPage", lBaLocationDict);
		}
		if (StringUtil.isNotEmpty(lBaLocationDict.getParentId())) { 
			if(lBaLocationDict.getLoLeve()!=null)
				lBaLocationDict.setLoLeve(lBaLocationDict.getLoLeve()+1);
			req.setAttribute("lBaLocationDictPage", lBaLocationDict);
		}
		return new ModelAndView("cn/com/linkwide/ba/location/lBaLocationDict-add");
	}
	/**
	 * 货位档案编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(LBaLocationDictEntity lBaLocationDict, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(lBaLocationDict.getId())) {
			lBaLocationDict = lBaLocationDictService.getEntity(LBaLocationDictEntity.class, lBaLocationDict.getId());
			req.setAttribute("lBaLocationDictPage", lBaLocationDict);
		}
		return new ModelAndView("cn/com/linkwide/ba/location/lBaLocationDict-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","lBaLocationDictController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(LBaLocationDictEntity lBaLocationDict,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(LBaLocationDictEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, lBaLocationDict, request.getParameterMap());
		List<LBaLocationDictEntity> lBaLocationDicts = this.lBaLocationDictService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"货位档案");
		modelMap.put(NormalExcelConstants.CLASS,LBaLocationDictEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("货位档案列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,lBaLocationDicts);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(LBaLocationDictEntity lBaLocationDict,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"货位档案");
    	modelMap.put(NormalExcelConstants.CLASS,LBaLocationDictEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("货位档案列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<LBaLocationDictEntity> listLBaLocationDictEntitys = ExcelImportUtil.importExcel(file.getInputStream(),LBaLocationDictEntity.class,params);
				for (LBaLocationDictEntity lBaLocationDict : listLBaLocationDictEntitys) {
					lBaLocationDictService.save(lBaLocationDict);
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
	@ApiOperation(value="货位档案列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<LBaLocationDictEntity>> list() {
		List<LBaLocationDictEntity> listLBaLocationDicts=lBaLocationDictService.getList(LBaLocationDictEntity.class);
		return Result.success(listLBaLocationDicts);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取货位档案信息",notes="根据ID获取货位档案信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		LBaLocationDictEntity task = lBaLocationDictService.get(LBaLocationDictEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取货位档案信息为空");
		}
		return Result.success(task);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建货位档案")
	public ResponseMessage<?> create(@ApiParam(name="货位档案对象")@RequestBody LBaLocationDictEntity lBaLocationDict, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<LBaLocationDictEntity>> failures = validator.validate(lBaLocationDict);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			lBaLocationDictService.save(lBaLocationDict);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("货位档案信息保存失败");
		}
		return Result.success(lBaLocationDict);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新货位档案",notes="更新货位档案")
	public ResponseMessage<?> update(@ApiParam(name="货位档案对象")@RequestBody LBaLocationDictEntity lBaLocationDict) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<LBaLocationDictEntity>> failures = validator.validate(lBaLocationDict);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			lBaLocationDictService.saveOrUpdate(lBaLocationDict);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("更新货位档案信息失败");
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success("更新货位档案信息成功");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除货位档案")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" + id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			lBaLocationDictService.deleteEntityById(LBaLocationDictEntity.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("货位档案删除失败");
		}

		return Result.success();
	}
	
	/**
	 *加载菜单树
	 *@author chencp
	 *2017年9月25日下午5:47:37
	 * @param request
	 * @return
	 */
	@RequestMapping(params="locationTree")
	@ResponseBody
	private List<ComboTree> locationTree(HttpServletRequest request){
		return getTree("");
	}
	
	private   List<ComboTree> getTree(String pId){
		List<ComboTree> comboLists=new ArrayList<ComboTree>();
		String hql0 =" from LBaLocationDictEntity where parentId ='"+pId+"' ";
		if(pId == null || "".equals(pId)){
			 hql0 =" from LBaLocationDictEntity where parentId is null  or parentId =''  ";
		}
		List<LBaLocationDictEntity> list0 = systemService.findHql(hql0);
		for(LBaLocationDictEntity loc : list0){
			ComboTree comT=new ComboTree();
			comT.setId(loc.getId());
			comT.setText(loc.getName());
			comT.setChildren(getTree(loc.getId()));
			Map map = new HashMap();
			map.put("status", loc.getIsStop());
			map.put("loLeve", loc.getLoLeve());
			map.put("code", loc.getCode());
			map.put("wareId", loc.getWareId());
			comT.setAttributes(map);
			comboLists.add(comT);
		}
		return comboLists;
	}
}
