package cn.com.linkwide.ba.baitemincom.controller;
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

import cn.com.linkwide.ba.baitemincom.entity.BaItemIncomEntity;
import cn.com.linkwide.ba.baitemincom.service.BaItemIncomServiceI;
import cn.com.linkwide.common.delcheck.service.DelCheckServiceI;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**   
 * @Title: Controller  
 * @Description: 收入项目
 * @author onlineGenerator
 * @date 2018-10-17 11:50:27
 * @version V1.0   
 *
 */
@Api(value="BaItemIncom",description="收入项目",tags="baItemIncomController")
@Controller
@RequestMapping("/baItemIncomController")
public class BaItemIncomController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(BaItemIncomController.class);

	@Autowired
	private BaItemIncomServiceI baItemIncomService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	@Autowired
	private DelCheckServiceI delCheckServiceI;


	/**
	 * 收入项目列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("cn/com/linkwide/ba/baitemincom/baItemIncomList");
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
	public void datagrid(BaItemIncomEntity baItemIncom,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		String code =baItemIncom.getVcode();
		String name = baItemIncom.getVname();
		if(StringUtil.isNotEmpty(code)){
			baItemIncom.setVcode("*"+code+"*");
		}
		if(StringUtil.isNotEmpty(name)){
			baItemIncom.setVname("*"+name+"*");
		}
		
		CriteriaQuery cq = new CriteriaQuery(BaItemIncomEntity.class, dataGrid);
		if(StringUtil.isEmpty(baItemIncom.getId())){
			cq.isNull("parentCode");
		}else{
			cq.eq("parentCode", systemService.get(BaItemIncomEntity.class, baItemIncom.getId()).getVcode());
			baItemIncom.setId(null);
		}
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, baItemIncom, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.baItemIncomService.getDataGridReturn(cq, true);
		TagUtil.treegrid(response, dataGrid);
	}
	
	/**
	 * 删除收入项目
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(BaItemIncomEntity baItemIncom, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		baItemIncom = systemService.getEntity(BaItemIncomEntity.class, baItemIncom.getId());
		message = "收入项目删除成功";
		try{
			//删除校验
			String itemCode = baItemIncom.getVcode();
			if(StringUtil.isNotEmpty(itemCode)){
				List<BaItemIncomEntity> list= systemService.findByProperty(BaItemIncomEntity.class, "parentCode", itemCode);
				if(list!= null && list.size()>0){
					j.setMsg("存在下级项目，不能删除");
					return j;
				}
			}
			//校验是否被引用
			message =delCheckServiceI.generalDeleteCheck(BaItemIncomEntity.class, itemCode);
			if(StringUtil.isNotEmpty(message)){
				j.setMsg(message);
				return j;
			}
			baItemIncomService.delete(baItemIncom);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "收入项目删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除收入项目
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "收入项目删除成功";
		try{
			for(String id:ids.split(",")){
				BaItemIncomEntity baItemIncom = systemService.getEntity(BaItemIncomEntity.class, 
				id
				);
				baItemIncomService.delete(baItemIncom);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "收入项目删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加收入项目
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(BaItemIncomEntity baItemIncom, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "收入项目添加成功";
		try{
			if(StringUtil.isEmpty(baItemIncom.getParentCode())){
				baItemIncom.setParentCode(null);
			}
			baItemIncomService.save(baItemIncom);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "收入项目添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新收入项目
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(BaItemIncomEntity baItemIncom, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "收入项目更新成功";
		BaItemIncomEntity t = baItemIncomService.get(BaItemIncomEntity.class, baItemIncom.getId());
		try {
			//
			String parentCode =baItemIncom.getParentCode();
			BaItemIncomEntity item = systemService.get(BaItemIncomEntity.class, parentCode);
			if(item!= null){
				baItemIncom.setParentCode(item.getVcode());
			}
			MyBeanUtils.copyBeanNotNull2Bean(baItemIncom, t);
			if(StringUtil.isEmpty(t.getParentCode())){
				t.setParentCode(null);
			}
			baItemIncomService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "收入项目更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 收入项目新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(BaItemIncomEntity baItemIncom, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(baItemIncom.getId())) {
			baItemIncom = baItemIncomService.getEntity(BaItemIncomEntity.class, baItemIncom.getId());
			req.setAttribute("baItemIncomPage", baItemIncom);
		}
		return new ModelAndView("cn/com/linkwide/ba/baitemincom/baItemIncom-add");
	}
	/**
	 * 收入项目编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(BaItemIncomEntity baItemIncom, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(baItemIncom.getId())) {
			baItemIncom = baItemIncomService.getEntity(BaItemIncomEntity.class, baItemIncom.getId());
			req.setAttribute("baItemIncomPage", baItemIncom);
		}
		return new ModelAndView("cn/com/linkwide/ba/baitemincom/baItemIncom-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","baItemIncomController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(BaItemIncomEntity baItemIncom,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(BaItemIncomEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, baItemIncom, request.getParameterMap());
		List<BaItemIncomEntity> baItemIncoms = this.baItemIncomService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"收入项目");
		modelMap.put(NormalExcelConstants.CLASS,BaItemIncomEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("收入项目列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,baItemIncoms);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(BaItemIncomEntity baItemIncom,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"收入项目");
    	modelMap.put(NormalExcelConstants.CLASS,BaItemIncomEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("收入项目列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<BaItemIncomEntity> listBaItemIncomEntitys = ExcelImportUtil.importExcel(file.getInputStream(),BaItemIncomEntity.class,params);
				for (BaItemIncomEntity baItemIncom : listBaItemIncomEntitys) {
					//导入前校验
					//校验编码
					String code =baItemIncom.getVcode();
					if(StringUtil.isNotEmpty(code)){
						List<BaItemIncomEntity> list =systemService.findByProperty(BaItemIncomEntity.class, "vcode", code);
						if(list!= null && list.size()>0){
							j.setMsg("项目编码"+code+"已存在");
							return j;
						}
					}else{
						j.setMsg("项目编码不能为空");
						return j;
					}
					//校验名称
					String name= baItemIncom.getVname();
					if(StringUtil.isEmpty(name)){
						j.setMsg("项目名称不能为空");
						return j;
					}
					//校验是否末级
					String isLast = baItemIncom.getIsLast();
					if(StringUtil.isEmpty(isLast)){
						j.setMsg("是否末级不能为空");
						return j;
					}else{
						if(!"Y".equals(isLast)&&!"N".equals(isLast)){
							j.setMsg("是否末级只能填写‘Y’或‘N’");
							return j;
						}
					}
					//校验是否停用
					String isStop = baItemIncom.getIsStop();
					if(StringUtil.isEmpty(isStop)){
						j.setMsg("是否停用不能为空");
						return j;
					}else{
						if(!"Y".equals(isLast)&&!"N".equals(isLast)){
							j.setMsg("是否停用只能填写‘Y’或‘N’");
							return j;
						}
					}
					//资金来源校验
					String budgSource =baItemIncom.getBudgSource();
					if(StringUtil.isEmpty(budgSource)){
						j.setMsg("资金来源不能为空");
						return j;
					}
					
					baItemIncomService.save(baItemIncom);
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
	@ApiOperation(value="收入项目列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<BaItemIncomEntity>> list() {
		List<BaItemIncomEntity> listBaItemIncoms=baItemIncomService.getList(BaItemIncomEntity.class);
		return Result.success(listBaItemIncoms);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取收入项目信息",notes="根据ID获取收入项目信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		BaItemIncomEntity task = baItemIncomService.get(BaItemIncomEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取收入项目信息为空");
		}
		return Result.success(task);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建收入项目")
	public ResponseMessage<?> create(@ApiParam(name="收入项目对象")@RequestBody BaItemIncomEntity baItemIncom, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<BaItemIncomEntity>> failures = validator.validate(baItemIncom);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			baItemIncomService.save(baItemIncom);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("收入项目信息保存失败");
		}
		return Result.success(baItemIncom);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新收入项目",notes="更新收入项目")
	public ResponseMessage<?> update(@ApiParam(name="收入项目对象")@RequestBody BaItemIncomEntity baItemIncom) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<BaItemIncomEntity>> failures = validator.validate(baItemIncom);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			baItemIncomService.saveOrUpdate(baItemIncom);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("更新收入项目信息失败");
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success("更新收入项目信息成功");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除收入项目")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" + id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			baItemIncomService.deleteEntityById(BaItemIncomEntity.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("收入项目删除失败");
		}

		return Result.success();
	}
}
