package cn.com.linkwide.cont.condemage.controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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

import cn.com.linkwide.cont.condemage.entity.ConDemageEntity;
import cn.com.linkwide.cont.condemage.service.ConDemageServiceI;
import cn.com.linkwide.cont.coninformation.entity.ConInformationEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**   
 * @Title: Controller  
 * @Description: 合同索赔
 * @author onlineGenerator
 * @date 2018-08-28 10:54:37
 * @version V1.0   
 *
 */
@Api(value="ConDemage",description="合同索赔",tags="conDemageFrameController")
@Controller
@RequestMapping("/conDemageFrameController")
public class ConDemageFrameController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ConDemageController.class);

	@Autowired
	private ConDemageServiceI conDemageService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 合同索赔列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("cn/com/linkwide/cont/framecont/condemage/conDemageList");
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
	public void datagrid(ConDemageEntity conDemage, HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid) {
		try {
			String conNumber = request.getParameter("conNo");
			String conName = request.getParameter("conName");
			String money_begin =request.getParameter("money_begin");
			String money_end =request.getParameter("money_end");
			// 自定义追加查询条件
			String query1 = "select a.id as id,b.id as ids,b.con_number as conNumber,b.con_no as conNo,b.con_name as conName,b.other_compy as otherCompy,"
					+ "b.con_exec1 as conExec1,a.money as money, b.defout_case as defoutCase"
					+ ",b.defout_date as defoutDate,b.start_date as startDate,b.end_date as endDate,a.demage_bz as demageBz";
					
			StringBuffer sql = new StringBuffer();
			sql.append(" from con_demage a  LEFT JOIN con_information b  on a.con_id=b.id ");
			sql.append(" where 1=1 and b.is_defout='1' and b.con_type='05'  ");
			sql.append(" and b.create_by='"+ResourceUtil.getSessionUser().getUserName()+"' ");
			String orderStr = "";
			if (StringUtil.isNotEmpty(dataGrid.getSort())) {
				orderStr = " order by " + dataGrid.getSort() + " " + dataGrid.getOrder();
			}
			if (StringUtil.isNotEmpty(conNumber)) {
				sql.append(" and b.con_number like '%" + conNumber + "%' ");
			}
			if (StringUtil.isNotEmpty(conName)) {
				sql.append(" and b.con_name like '%" + conName + "%' ");
			}
			if(StringUtil.isNotEmpty(money_begin)){
				sql.append(" and a.money>="+money_begin);
			}
			if(StringUtil.isNotEmpty(money_end)){
				sql.append(" and a.money<="+money_end);
			}
		
			List<Map<String, Object>> list = systemService.findForJdbc(query1 + sql.toString() + orderStr,
					dataGrid.getPage(), dataGrid.getRows());
			dataGrid.setResults(list);
			List<Map<String, Object>> lisCount = systemService
					.findForJdbc("select count(*) as  count " + sql.toString(), null);
			for (Map<String, Object> map : lisCount) {
				dataGrid.setTotal(Integer.valueOf(map.get("count").toString()));
			}

		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		TagUtil.datagrid(response, dataGrid);
	}

	
	
	
	
	
	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 *//*

	@RequestMapping(params = "datagrid")
	public void datagrid(ConDemageEntity conDemage,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ConDemageEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, conDemage, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.conDemageService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	*/
	/**
	 * 删除合同索赔
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(ConDemageEntity conDemage, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		conDemage = systemService.getEntity(ConDemageEntity.class, conDemage.getId());
		message = "合同索赔删除成功";
		try{
			conDemageService.delete(conDemage);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "合同索赔删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除合同索赔
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "合同索赔删除成功";
		try{
			for(String id:ids.split(",")){
				ConDemageEntity conDemage = systemService.getEntity(ConDemageEntity.class, 
				id
				);
				conDemageService.delete(conDemage);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "合同索赔删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加合同索赔
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(ConDemageEntity conDemage, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "合同索赔添加成功";
		try{
			conDemageService.save(conDemage);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "合同索赔添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新合同索赔
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(ConDemageEntity conDemage, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "合同索赔更新成功";
		ConDemageEntity t = conDemageService.get(ConDemageEntity.class, conDemage.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(conDemage, t);
			conDemageService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "合同索赔更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	
	
	/**
	 * 合同索赔跳转页面
	 * 
	 * @return
	 */
	@RequestMapping(params = "confirmNum")
	public ModelAndView confirmNum(ConDemageEntity conDemage, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(conDemage.getId())) {
			conDemage = conDemageService.getEntity(ConDemageEntity.class, conDemage.getId());
			req.setAttribute("conDemagePage", conDemage);
			conDemage.setDemageDate(new Date(System.currentTimeMillis()));
		}
		return new ModelAndView("cn/com/linkwide/cont/framecont/condemage/condemage-suoPei");
	}
	
	
	/**
	 * 合同违约原因变更
	 * 
	 * @return
	 */
	@RequestMapping(params = "gochecked")
	public ModelAndView gochecked(ConInformationEntity conInformation, HttpServletRequest req) {
		
		conInformation = conDemageService.getEntity(ConInformationEntity.class, req.getParameter("id"));
			req.setAttribute("conInformationPage", conInformation);
		return new ModelAndView("cn/com/linkwide/cont/framecont/condemage/condemage-change");
	}
	
	

	/**
	 * 合同索赔
	 * 
	 * @return
	 */
	@RequestMapping(params = "dochecked")
	@ResponseBody
	public AjaxJson dochecked(ConInformationEntity conInformation, HttpServletRequest request) {
		String message="";
		AjaxJson j = new AjaxJson();
		try{
			ConInformationEntity conInformationr = conDemageService.getEntity(ConInformationEntity.class, conInformation.getId());
			conInformationr.setDefoutCase(conInformation.getDefoutCase());
			message = "合同违约原因变更成功";
			MyBeanUtils.copyBean2Bean(conInformation, conInformationr);
			conDemageService.updateEntitie(conInformationr);
		}catch(Exception e){
			e.printStackTrace();
			message = "合同违约原因变更失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	
	/**
	 * 合同索赔
	 * 
	 * @return
	 */
	@RequestMapping(params = "doSuoPei")
	@ResponseBody
	public AjaxJson doSuoPei(ConDemageEntity conDemage, HttpServletRequest request) {
		String message="";
		AjaxJson j = new AjaxJson();
		try{
			
			ConDemageEntity ConDema = conDemageService.getEntity(ConDemageEntity.class, conDemage.getId());
		
			ConDema.setMoney(conDemage.getMoney());
			ConDema.setDemageBz(conDemage.getDemageBz());
			ConDema.setDemageDate(conDemage.getDemageDate());
			message = "索赔成功";
			MyBeanUtils.copyBean2Bean(conDemage, ConDema);
			conDemageService.updateEntitie(ConDema);
			
			/*//在索赔业务表中新增违约的合同
			ConDemageEntity conDemageEntity=new ConDemageEntity();
			conDemageEntity.setConId(conInformation.getId());
			systemService.save(conDemageEntity);*/
		//	systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "合同索赔失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	

	/**
	 * 合同索赔新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(ConDemageEntity conDemage, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(conDemage.getId())) {
			conDemage = conDemageService.getEntity(ConDemageEntity.class, conDemage.getId());
			req.setAttribute("conDemagePage", conDemage);
		}
		return new ModelAndView("cn/com/linkwide/cont/framecont/condemage/conDemage-add");
	}
	/**
	 * 合同索赔编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(ConDemageEntity conDemage, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(conDemage.getId())) {
			String id=req.getParameter("conId");
			ConInformationEntity conInformation = systemService.getEntity(ConInformationEntity.class, id);
			req.setAttribute("conInformationPage", conInformation);
		}
		return new ModelAndView("cn/com/linkwide/cont/framecont/coninformation/conInformation-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","conDemageController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(ConDemageEntity conDemage,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(ConDemageEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, conDemage, request.getParameterMap());
		List<ConDemageEntity> conDemages = this.conDemageService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"合同索赔");
		modelMap.put(NormalExcelConstants.CLASS,ConDemageEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("合同索赔列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,conDemages);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(ConDemageEntity conDemage,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"合同索赔");
    	modelMap.put(NormalExcelConstants.CLASS,ConDemageEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("合同索赔列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<ConDemageEntity> listConDemageEntitys = ExcelImportUtil.importExcel(file.getInputStream(),ConDemageEntity.class,params);
				for (ConDemageEntity conDemage : listConDemageEntitys) {
					conDemageService.save(conDemage);
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
	@ApiOperation(value="合同索赔列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<ConDemageEntity>> list() {
		List<ConDemageEntity> listConDemages=conDemageService.getList(ConDemageEntity.class);
		return Result.success(listConDemages);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取合同索赔信息",notes="根据ID获取合同索赔信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		ConDemageEntity task = conDemageService.get(ConDemageEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取合同索赔信息为空");
		}
		return Result.success(task);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建合同索赔")
	public ResponseMessage<?> create(@ApiParam(name="合同索赔对象")@RequestBody ConDemageEntity conDemage, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ConDemageEntity>> failures = validator.validate(conDemage);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			conDemageService.save(conDemage);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("合同索赔信息保存失败");
		}
		return Result.success(conDemage);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新合同索赔",notes="更新合同索赔")
	public ResponseMessage<?> update(@ApiParam(name="合同索赔对象")@RequestBody ConDemageEntity conDemage) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ConDemageEntity>> failures = validator.validate(conDemage);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			conDemageService.saveOrUpdate(conDemage);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("更新合同索赔信息失败");
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success("更新合同索赔信息成功");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除合同索赔")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" + id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			conDemageService.deleteEntityById(ConDemageEntity.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("合同索赔删除失败");
		}

		return Result.success();
	}
}
