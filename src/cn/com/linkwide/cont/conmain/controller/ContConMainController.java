package cn.com.linkwide.cont.conmain.controller;
import cn.com.linkwide.cont.conmain.entity.ContConMainEntity;
import cn.com.linkwide.cont.conmain.service.ContConMainServiceI;
import cn.com.linkwide.cont.conmain.page.ContConMainPage;
import cn.com.linkwide.cont.condetail.entity.ContConDetailEntity;
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.ExceptionUtil;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import java.io.IOException;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.jeecgframework.jwt.util.GsonUtil;
import org.jeecgframework.jwt.util.ResponseMessage;
import org.jeecgframework.jwt.util.Result;
import com.alibaba.fastjson.JSONArray;
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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**   
 * @Title: Controller
 * @Description: 合同主表
 * @author onlineGenerator
 * @date 2018-08-03 16:48:56
 * @version V1.0   
 *
 */
@Api(value="ContConMain",description="合同主表",tags="contConMainController")
@Controller
@RequestMapping("/contConMainController")
public class ContConMainController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ContConMainController.class);

	@Autowired
	private ContConMainServiceI contConMainService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;

	/**
	 * 合同主表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("cn/com/linkwide/cont/conmain/conP/contConMainList");
		                       //  cn\com\linkwide\cont\conmain\conP\contConMainList
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
	public void datagrid(ContConMainEntity contConMain,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ContConMainEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, contConMain);
		try{
		//自定义追加查询条件
		String query_signDate_begin = request.getParameter("signDate_begin");
		String query_signDate_end = request.getParameter("signDate_end");
		if(StringUtil.isNotEmpty(query_signDate_begin)){
			cq.ge("signDate", new SimpleDateFormat("yyyy-MM-dd").parse(query_signDate_begin));
		}
		if(StringUtil.isNotEmpty(query_signDate_end)){
			cq.le("signDate", new SimpleDateFormat("yyyy-MM-dd").parse(query_signDate_end));
		}
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.contConMainService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除合同主表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(ContConMainEntity contConMain, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		contConMain = systemService.getEntity(ContConMainEntity.class, contConMain.getId());
		String message = "合同主表删除成功";
		try{
			contConMainService.delMain(contConMain);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "合同主表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	
	/**
	 * 删除合同主表
	 * 
	 * @return
	 */
	@RequestMapping(params = "ceshi")
	@ResponseBody
	public AjaxJson ceshi(ContConMainEntity contConMain, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		//contConMain = systemService.getEntity(ContConMainEntity.class, contConMain.getId());
		String message = "老曹是";
		
		j.setMsg(message);
		return j;
	}
	
	
	
	
	
	
	/**
	 * 批量删除合同主表
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		String message = "合同主表删除成功";
		try{
			for(String id:ids.split(",")){
				ContConMainEntity contConMain = systemService.getEntity(ContConMainEntity.class,
				id
				);
				contConMainService.delMain(contConMain);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "合同主表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 添加合同主表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(ContConMainEntity contConMain,ContConMainPage contConMainPage, HttpServletRequest request) {
		List<ContConDetailEntity> contConDetailList =  contConMainPage.getContConDetailList();
		AjaxJson j = new AjaxJson();
		String message = "添加成功";
		try{
			contConMainService.addMain(contConMain, contConDetailList);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "合同主表添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	/**
	 * 更新合同主表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(ContConMainEntity contConMain,ContConMainPage contConMainPage, HttpServletRequest request) {
		List<ContConDetailEntity> contConDetailList =  contConMainPage.getContConDetailList();
		AjaxJson j = new AjaxJson();
		String message = "更新成功";
		try{
			contConMainService.updateMain(contConMain, contConDetailList);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "更新合同主表失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 合同主表新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(ContConMainEntity contConMain, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(contConMain.getId())) {
			contConMain = contConMainService.getEntity(ContConMainEntity.class, contConMain.getId());
			req.setAttribute("contConMainPage", contConMain);
		}
		return new ModelAndView("cn/com/linkwide/cont/conmain/conP/contConMain-add");
								
	}
	
	/**
	 * 合同主表编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(ContConMainEntity contConMain, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(contConMain.getId())) {
			contConMain = contConMainService.getEntity(ContConMainEntity.class, contConMain.getId());
			req.setAttribute("contConMainPage", contConMain);
		}
		return new ModelAndView("cn/com/linkwide/cont/conmain/conP/contConMain-update");
	}
	
	
	/**
	 * 加载明细列表[合同明细]
	 * 
	 * @return
	 */
	@RequestMapping(params = "contConDetailList")
	public ModelAndView contConDetailList(ContConMainEntity contConMain, HttpServletRequest req) {
	
		//===================================================================================
		//获取参数
		Object id0 = contConMain.getId();
		//===================================================================================
		//查询-合同明细
	    String hql0 = "from ContConDetailEntity where 1 = 1 AND pK_ID = ? ";
	    try{
	    	List<ContConDetailEntity> contConDetailEntityList = systemService.findHql(hql0,id0);
			req.setAttribute("contConDetailList", contConDetailEntityList);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return new ModelAndView("cn/com/linkwide/cont/condetail/contConDetailPList");
	}

    /**
    * 导出excel
    *
    * @param request
    * @param response
    */
    @RequestMapping(params = "exportXls")
    public String exportXls(ContConMainEntity contConMain,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid,ModelMap map) {
    	CriteriaQuery cq = new CriteriaQuery(ContConMainEntity.class, dataGrid);
    	//查询条件组装器
    	org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, contConMain);
    	try{
    	//自定义追加查询条件
    	}catch (Exception e) {
    		throw new BusinessException(e.getMessage());
    	}
    	cq.add();
    	List<ContConMainEntity> list=this.contConMainService.getListByCriteriaQuery(cq, false);
    	List<ContConMainPage> pageList=new ArrayList<ContConMainPage>();
        if(list!=null&&list.size()>0){
        	for(ContConMainEntity entity:list){
        		try{
        		ContConMainPage page=new ContConMainPage();
        		   MyBeanUtils.copyBeanNotNull2Bean(entity,page);
            	    Object id0 = entity.getId();
				    String hql0 = "from ContConDetailEntity where 1 = 1 AND pK_ID = ? ";
        	        List<ContConDetailEntity> contConDetailEntityList = systemService.findHql(hql0,id0);
            		page.setContConDetailList(contConDetailEntityList);
            		pageList.add(page);
            	}catch(Exception e){
            		logger.info(e.getMessage());
            	}
            }
        }
        map.put(NormalExcelConstants.FILE_NAME,"合同主表");
        map.put(NormalExcelConstants.CLASS,ContConMainPage.class);
        map.put(NormalExcelConstants.PARAMS,new ExportParams("合同主表列表", "导出人:Jeecg",
            "导出信息"));
        map.put(NormalExcelConstants.DATA_LIST,pageList);
        return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}

    /**
	 * 通过excel导入数据
	 * @param request
	 * @param
	 * @return
	 */
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
			params.setHeadRows(2);
			params.setNeedSave(true);
			try {
				List<ContConMainPage> list =  ExcelImportUtil.importExcel(file.getInputStream(), ContConMainPage.class, params);
				ContConMainEntity entity1=null;
				for (ContConMainPage page : list) {
					entity1=new ContConMainEntity();
					MyBeanUtils.copyBeanNotNull2Bean(page,entity1);
		            contConMainService.addMain(entity1, page.getContConDetailList());
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
	/**
	* 导出excel 使模板
	*/
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(ModelMap map) {
		map.put(NormalExcelConstants.FILE_NAME,"合同主表");
		map.put(NormalExcelConstants.CLASS,ContConMainPage.class);
		map.put(NormalExcelConstants.PARAMS,new ExportParams("合同主表列表", "导出人:"+ ResourceUtil.getSessionUser().getRealName(),
		"导出信息"));
		map.put(NormalExcelConstants.DATA_LIST,new ArrayList());
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	* 导入功能跳转
	*
	* @return
	*/
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name", "contConMainController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}

 	
 	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="合同主表列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<ContConMainPage>> list() {
		List<ContConMainEntity> list= contConMainService.getList(ContConMainEntity.class);
    	List<ContConMainPage> pageList=new ArrayList<ContConMainPage>();
        if(list!=null&&list.size()>0){
        	for(ContConMainEntity entity:list){
        		try{
        			ContConMainPage page=new ContConMainPage();
        		   MyBeanUtils.copyBeanNotNull2Bean(entity,page);
					Object id0 = entity.getId();
				     String hql0 = "from ContConDetailEntity where 1 = 1 AND pK_ID = ? ";
	    			List<ContConDetailEntity> contConDetailOldList = this.contConMainService.findHql(hql0,id0);
            		page.setContConDetailList(contConDetailOldList);
            		pageList.add(page);
            	}catch(Exception e){
            		logger.info(e.getMessage());
            	}
            }
        }
		return Result.success(pageList);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取合同主表信息",notes="根据ID获取合同主表信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		ContConMainEntity task = contConMainService.get(ContConMainEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取合同主表信息为空");
		}
		ContConMainPage page = new ContConMainPage();
		try {
			MyBeanUtils.copyBeanNotNull2Bean(task, page);
				Object id0 = task.getId();
		    String hql0 = "from ContConDetailEntity where 1 = 1 AND pK_ID = ? ";
			List<ContConDetailEntity> contConDetailOldList = this.contConMainService.findHql(hql0,id0);
    		page.setContConDetailList(contConDetailOldList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Result.success(page);
	}
 	
 	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建合同主表")
	public ResponseMessage<?> create(@ApiParam(name="合同主表对象")@RequestBody ContConMainPage contConMainPage, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ContConMainPage>> failures = validator.validate(contConMainPage);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		List<ContConDetailEntity> contConDetailList =  contConMainPage.getContConDetailList();
		
		ContConMainEntity contConMain = new ContConMainEntity();
		try{
			MyBeanUtils.copyBeanNotNull2Bean(contConMainPage,contConMain);
		}catch(Exception e){
            logger.info(e.getMessage());
            return Result.error("保存合同主表失败");
        }
		contConMainService.addMain(contConMain, contConDetailList);

		return Result.success(contConMain);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新合同主表",notes="更新合同主表")
	public ResponseMessage<?> update(@RequestBody ContConMainPage contConMainPage) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ContConMainPage>> failures = validator.validate(contConMainPage);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		List<ContConDetailEntity> contConDetailList =  contConMainPage.getContConDetailList();
		
		ContConMainEntity contConMain = new ContConMainEntity();
		try{
			MyBeanUtils.copyBeanNotNull2Bean(contConMainPage,contConMain);
		}catch(Exception e){
            logger.info(e.getMessage());
            return Result.error("合同主表更新失败");
        }
		contConMainService.updateMain(contConMain, contConDetailList);

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除合同主表")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" + id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			ContConMainEntity contConMain = contConMainService.get(ContConMainEntity.class, id);
			contConMainService.delMain(contConMain);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("合同主表删除失败");
		}

		return Result.success();
	}
}
