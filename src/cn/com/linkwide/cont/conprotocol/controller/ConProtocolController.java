package cn.com.linkwide.cont.conprotocol.controller;
import cn.com.linkwide.common.tools.BillCodeUtil;
import cn.com.linkwide.cont.coninformation.entity.ConInformationEntity;
import cn.com.linkwide.cont.conprotocol.entity.ConProtocolEntity;
import cn.com.linkwide.cont.conprotocol.service.ConProtocolServiceI;
import cn.com.linkwide.cont.conprotocol.page.ConProtocolPage;
import cn.com.linkwide.cont.conprotocoldetail.entity.ConProtocolDetailEntity;
import java.util.ArrayList;
import java.util.Date;
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
import org.jeecgframework.web.system.pojo.base.TSFunction;
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
 * @Description: 协议
 * @author onlineGenerator
 * @date 2018-09-17 18:30:36
 * @version V1.0   
 *
 */
@Api(value="ConProtocol",description="协议",tags="conProtocolController")
@Controller
@RequestMapping("/conProtocolController")
public class ConProtocolController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ConProtocolController.class);

	@Autowired
	private ConProtocolServiceI conProtocolService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;

	/**
	 * 协议列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("cn/com/linkwide/cont/conprotocol/conProtocolList");
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
	public void datagrid(ConProtocolEntity conProtocol,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ConProtocolEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, conProtocol);
		try{
		//自定义追加查询条件
		String query_singeDate_begin = request.getParameter("singeDate_begin");
		String query_singeDate_end = request.getParameter("singeDate_end");
		if(StringUtil.isNotEmpty(query_singeDate_begin)){
			cq.ge("singeDate", new SimpleDateFormat("yyyy-MM-dd").parse(query_singeDate_begin));
		}
		if(StringUtil.isNotEmpty(query_singeDate_end)){
			cq.le("singeDate", new SimpleDateFormat("yyyy-MM-dd").parse(query_singeDate_end));
		}
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.conProtocolService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除协议
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(ConProtocolEntity conProtocol, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		conProtocol = systemService.getEntity(ConProtocolEntity.class, conProtocol.getId());
		String message = "协议删除成功";
		try{
			conProtocolService.delMain(conProtocol);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "协议删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 批量删除协议
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		String message = "协议删除成功";
		try{
			for(String id:ids.split(",")){
				ConProtocolEntity conProtocol = systemService.getEntity(ConProtocolEntity.class,
				id
				);
				conProtocolService.delMain(conProtocol);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "协议删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 添加协议
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(ConProtocolEntity conProtocol,ConProtocolPage conProtocolPage, HttpServletRequest request) {
		List<ConProtocolDetailEntity> conProtocolDetailList =  conProtocolPage.getConProtocolDetailList();
		AjaxJson j = new AjaxJson();
		String message = "添加成功";
		try{
			conProtocol.setProtocolNumber(generateBillCode());
			conProtocolService.addMain(conProtocol, conProtocolDetailList);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "协议添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 生成单据号
	 * @return
	 *2017年12月4日
	 *@author 
	 */
	public String generateBillCode(){
		String	billcode="";
	//	String hql=" from ";
		TSFunction function = this.conProtocolService.findUniqueByProperty(TSFunction.class, "functionUrl", "conProtocolController.do?list");
		if(function==null){
			throw new BusinessException("未设置合同信息的单据类型");
		}
		billcode=BillCodeUtil.productBillCode(function.getBillType());
		return billcode;
	}
	
	
	 /**
	 * 批量操作合同信息
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchCheck")
	@ResponseBody
	public AjaxJson doBatchCheck(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		String message = "";
		Integer w=Integer.valueOf(request.getParameter("w"));
			if(w==1){
				
			message = "协议审核成功！";
					}
			if(w==2){
				message="协议弃审成功！";
			}
			if(w==3){
				message="协议履行成功！";
			}
			if(w==4){
				message="协议终止成功！";
			}
			if(w==5){
				message="协议作废成功！";
			}
			if(w==6){
				message="协议取消终止成功！";
			}
			if(w==7){
				message="协议取消作废成功！";
			}
		String  state =request.getParameter("state");
		try{
			for(String id:ids.split(",")){
				ConProtocolEntity conProtocol = systemService.getEntity(ConProtocolEntity.class,id); 
				
			
				if("03".equals(conProtocol.getProtocolState())){
					message="已终止的协议不能做其他操作！";
					j.setMsg(message);
					return j;
				}
				if("04".equals(conProtocol.getProtocolState())){
					message="已作废的协议不能做其他操作！";
					j.setMsg(message);
					return j;
				}
				if("2".equals(conProtocol.getApprovalState()) && w==1){
					
					message = "已审核的协议不能复审！";
					j.setMsg(message);
					return j;
				}
				if(!"2".equals(conProtocol.getApprovalState()) && w==2){
					
					message = "不是已审核的协议不能弃审！";
					j.setMsg(message);
					return j;
				}
				if(!"10".equals(conProtocol.getProtocolState()) && w==2){
					
					message = "不是起草状态的协议不能弃审！";
					j.setMsg(message);
					return j;
				}
				if(!"2".equals(conProtocol.getApprovalState()) && w==3){
					
					message = "不是审核的协议不能履行！";
					j.setMsg(message);
					return j;
				}
				if(!"03".equals(conProtocol.getProtocolState()) && w==6){
					message = "不是终止状态下的协议不能取消终止！";
					j.setMsg(message);
					return j;
				}
				if(!"04".equals(conProtocol.getProtocolState()) && w==7){
					message = "不是作废状态下的协议不能取消作废！";
					j.setMsg(message);
					return j;
				}
				if(w==6||w==7){
					conProtocol.setApprovalState(state);
				}
			
				if(w==1){
					conProtocol.setApprovalState(state);
					conProtocol.setApprovalDate(new Date(System.currentTimeMillis()));
					conProtocol.setEffectDate(new Date(System.currentTimeMillis()));
					conProtocol.setEffectEmp(ResourceUtil.getSessionUser().getId());
				}
				if(w==2){
					conProtocol.setApprovalState(state);
					conProtocol.setApprovalDate(null);
					conProtocol.setEffectDate(null);
					conProtocol.setEffectEmp("");
				}
				if(w==3){
					conProtocol.setProtocolState(state);
				}
				if(w==4){
					conProtocol.setProtocolState(state);
					/*conProtocol.setStateDate5(new Date(System.currentTimeMillis()));*/
				}
				if(w==5){
					conProtocol.setProtocolState(state);
					/*conProtocol.setStateDate5(new Date(System.currentTimeMillis()));*/
				}
				
				conProtocolService.saveOrUpdate(conProtocol);
				
			 
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			if(w==1){
				
				message = "协议审核失败";
						}
			if(w==2){
				message="协议弃审失败";
			}
			if(w==3){
				message="协议履行失败！";
			}
			if(w==4){
				message="协议终止失败！";
			}
			if(w==5){
				message="协议作废失败！";
			}
			if(w==6){
				message="协议取消终止失败！";
			}
			if(w==7){
				message="协议取消作废失败！";
			}
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新协议
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(ConProtocolEntity conProtocol,ConProtocolPage conProtocolPage, HttpServletRequest request) {
		List<ConProtocolDetailEntity> conProtocolDetailList =  conProtocolPage.getConProtocolDetailList();
		AjaxJson j = new AjaxJson();
		String message = "更新成功";
		try{
			conProtocolService.updateMain(conProtocol, conProtocolDetailList);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "更新协议失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 协议新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(ConProtocolEntity conProtocol, HttpServletRequest req) {
		String dept_code=	ResourceUtil.getSessionUser().getDepartid();
		req.setAttribute("deptCode", dept_code);
		req.setAttribute("signedDate",new Date(System.currentTimeMillis()));
		req.setAttribute("startDate", new Date(System.currentTimeMillis()));
		
		
		if (StringUtil.isNotEmpty(conProtocol.getId())) {
			conProtocol = conProtocolService.getEntity(ConProtocolEntity.class, conProtocol.getId());
			req.setAttribute("conProtocolPage", conProtocol);
		}
		return new ModelAndView("cn/com/linkwide/cont/conprotocol/conProtocol-add");
	}
	
	/**
	 * 协议编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(ConProtocolEntity conProtocol, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(conProtocol.getId())) {
			conProtocol = conProtocolService.getEntity(ConProtocolEntity.class, conProtocol.getId());
			req.setAttribute("conProtocolPage", conProtocol);
		}
		return new ModelAndView("cn/com/linkwide/cont/conprotocol/conProtocol-update");
	}
	
	
	/**
	 * 加载明细列表[合同协议]
	 * 
	 * @return
	 */
	@RequestMapping(params = "conProtocolDetailList")
	public ModelAndView conProtocolDetailList(ConProtocolEntity conProtocol, HttpServletRequest req) {
	
		//===================================================================================
		//获取参数
		Object id0 = conProtocol.getId();
		//===================================================================================
		//查询-合同协议
	    String hql0 = "from ConProtocolDetailEntity where 1 = 1 AND pROTOCOL_ID = ? ";
	    try{
	    	List<ConProtocolDetailEntity> conProtocolDetailEntityList = systemService.findHql(hql0,id0);
			req.setAttribute("conProtocolDetailList", conProtocolDetailEntityList);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return new ModelAndView("cn/com/linkwide/cont/conprotocoldetail/conProtocolDetailList");
	}

    /**
    * 导出excel
    *
    * @param request
    * @param response
    */
    @RequestMapping(params = "exportXls")
    public String exportXls(ConProtocolEntity conProtocol,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid,ModelMap map) {
    	CriteriaQuery cq = new CriteriaQuery(ConProtocolEntity.class, dataGrid);
    	//查询条件组装器
    	org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, conProtocol);
    	try{
    	//自定义追加查询条件
    	}catch (Exception e) {
    		throw new BusinessException(e.getMessage());
    	}
    	cq.add();
    	List<ConProtocolEntity> list=this.conProtocolService.getListByCriteriaQuery(cq, false);
    	List<ConProtocolPage> pageList=new ArrayList<ConProtocolPage>();
        if(list!=null&&list.size()>0){
        	for(ConProtocolEntity entity:list){
        		try{
        		ConProtocolPage page=new ConProtocolPage();
        		   MyBeanUtils.copyBeanNotNull2Bean(entity,page);
            	    Object id0 = entity.getId();
				    String hql0 = "from ConProtocolDetailEntity where 1 = 1 AND pROTOCOL_ID = ? ";
        	        List<ConProtocolDetailEntity> conProtocolDetailEntityList = systemService.findHql(hql0,id0);
            		page.setConProtocolDetailList(conProtocolDetailEntityList);
            		pageList.add(page);
            	}catch(Exception e){
            		logger.info(e.getMessage());
            	}
            }
        }
        map.put(NormalExcelConstants.FILE_NAME,"协议");
        map.put(NormalExcelConstants.CLASS,ConProtocolPage.class);
        map.put(NormalExcelConstants.PARAMS,new ExportParams("协议列表", "导出人:Jeecg",
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
				List<ConProtocolPage> list =  ExcelImportUtil.importExcel(file.getInputStream(), ConProtocolPage.class, params);
				ConProtocolEntity entity1=null;
				for (ConProtocolPage page : list) {
					entity1=new ConProtocolEntity();
					MyBeanUtils.copyBeanNotNull2Bean(page,entity1);
		            conProtocolService.addMain(entity1, page.getConProtocolDetailList());
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
		map.put(NormalExcelConstants.FILE_NAME,"协议");
		map.put(NormalExcelConstants.CLASS,ConProtocolPage.class);
		map.put(NormalExcelConstants.PARAMS,new ExportParams("协议列表", "导出人:"+ ResourceUtil.getSessionUser().getRealName(),
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
		req.setAttribute("controller_name", "conProtocolController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}

 	
 	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="协议列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<ConProtocolPage>> list() {
		List<ConProtocolEntity> list= conProtocolService.getList(ConProtocolEntity.class);
    	List<ConProtocolPage> pageList=new ArrayList<ConProtocolPage>();
        if(list!=null&&list.size()>0){
        	for(ConProtocolEntity entity:list){
        		try{
        			ConProtocolPage page=new ConProtocolPage();
        		   MyBeanUtils.copyBeanNotNull2Bean(entity,page);
					Object id0 = entity.getId();
				     String hql0 = "from ConProtocolDetailEntity where 1 = 1 AND pROTOCOL_ID = ? ";
	    			List<ConProtocolDetailEntity> conProtocolDetailOldList = this.conProtocolService.findHql(hql0,id0);
            		page.setConProtocolDetailList(conProtocolDetailOldList);
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
	@ApiOperation(value="根据ID获取协议信息",notes="根据ID获取协议信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		ConProtocolEntity task = conProtocolService.get(ConProtocolEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取协议信息为空");
		}
		ConProtocolPage page = new ConProtocolPage();
		try {
			MyBeanUtils.copyBeanNotNull2Bean(task, page);
				Object id0 = task.getId();
		    String hql0 = "from ConProtocolDetailEntity where 1 = 1 AND pROTOCOL_ID = ? ";
			List<ConProtocolDetailEntity> conProtocolDetailOldList = this.conProtocolService.findHql(hql0,id0);
    		page.setConProtocolDetailList(conProtocolDetailOldList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Result.success(page);
	}
 	
 	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建协议")
	public ResponseMessage<?> create(@ApiParam(name="协议对象")@RequestBody ConProtocolPage conProtocolPage, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ConProtocolPage>> failures = validator.validate(conProtocolPage);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		List<ConProtocolDetailEntity> conProtocolDetailList =  conProtocolPage.getConProtocolDetailList();
		
		ConProtocolEntity conProtocol = new ConProtocolEntity();
		try{
			MyBeanUtils.copyBeanNotNull2Bean(conProtocolPage,conProtocol);
		}catch(Exception e){
            logger.info(e.getMessage());
            return Result.error("保存协议失败");
        }
		conProtocolService.addMain(conProtocol, conProtocolDetailList);

		return Result.success(conProtocol);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新协议",notes="更新协议")
	public ResponseMessage<?> update(@RequestBody ConProtocolPage conProtocolPage) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ConProtocolPage>> failures = validator.validate(conProtocolPage);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		List<ConProtocolDetailEntity> conProtocolDetailList =  conProtocolPage.getConProtocolDetailList();
		
		ConProtocolEntity conProtocol = new ConProtocolEntity();
		try{
			MyBeanUtils.copyBeanNotNull2Bean(conProtocolPage,conProtocol);
		}catch(Exception e){
            logger.info(e.getMessage());
            return Result.error("协议更新失败");
        }
		conProtocolService.updateMain(conProtocol, conProtocolDetailList);

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除协议")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" + id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			ConProtocolEntity conProtocol = conProtocolService.get(ConProtocolEntity.class, id);
			conProtocolService.delMain(conProtocol);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("协议删除失败");
		}

		return Result.success();
	}
}
