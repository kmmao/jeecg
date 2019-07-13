package cn.com.linkwide.common.pubsetting.bdsharecl.controller;
import cn.com.linkwide.common.pubsetting.bdsharecl.entity.BdShareclEntity;
import cn.com.linkwide.common.pubsetting.bdsharecl.service.BdShareclServiceI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
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
import org.jeecgframework.core.util.ResourceUtil;

import java.io.IOException;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.Map;

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

import com.alibaba.fastjson.JSONObject;

/**   
 * @Title: Controller  
 * @Description: 核算属性
 * @author onlineGenerator
 * @date 2017-09-07 12:00:33
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/bdShareclController")
public class BdShareclController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(BdShareclController.class);

	@Autowired
	private BdShareclServiceI bdShareclService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 核算属性列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("cn/com/linkwide/cost/pubsetting/bdsharecl/bdShareclList");
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
	public void datagrid(BdShareclEntity bdSharecl,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(BdShareclEntity.class, dataGrid);
		//查询条件组装器
		if(StringUtil.isNotEmpty(bdSharecl.getVcode())){
			bdSharecl.setVcode("*"+bdSharecl.getVcode()+"*");
		}
		if(StringUtil.isNotEmpty(bdSharecl.getVname())){
			bdSharecl.setVname("*"+bdSharecl.getVname()+"*");
		}
		if(StringUtil.isNotEmpty(bdSharecl.getMemo())){
			bdSharecl.setMemo("*"+bdSharecl.getMemo()+"*");
		}
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, bdSharecl, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.bdShareclService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除核算属性
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(BdShareclEntity bdSharecl, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		bdSharecl = systemService.getEntity(BdShareclEntity.class, bdSharecl.getId());
		message = "核算属性删除成功";
		try{
			String vname = bdSharecl.getVname();
			if(vname.equals("临床服务类")||vname.equals("医疗技术类")||vname.equals("医疗辅助类")||vname.equals("行政后勤类")){
				message = "预置数据,不能删除";
				throw new BusinessException(message);
			}
			bdShareclService.delete(bdSharecl);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch( BusinessException b){
			message="删除失败："+b.getMessage();
		}catch(Exception e){
			e.printStackTrace();
			message = "核算属性删除失败";
			
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除核算属性
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "核算属性删除成功";
		try{
			for(String id:ids.split(",")){
				BdShareclEntity bdSharecl = systemService.getEntity(BdShareclEntity.class, 
				id
				);
				bdShareclService.delete(bdSharecl);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "核算属性删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加核算属性
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(BdShareclEntity bdSharecl, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "核算属性添加成功";
		try{
			BdShareclEntity uniqueByVcode = bdShareclService.findUniqueByProperty(BdShareclEntity.class, "vcode", bdSharecl.getVcode());
			if(uniqueByVcode!=null){
				message = "核算属性编码"+"'"+bdSharecl.getVcode()+"'"+"已经存在";
				throw new BusinessException(message);
			}
			//判断vName是否存在
			BdShareclEntity uniqueByVname = bdShareclService.findUniqueByProperty(BdShareclEntity.class, "vname", bdSharecl.getVname());
			if(uniqueByVname!=null){
				message = "核算属性名称"+"'"+bdSharecl.getVname()+"'"+"已经存在";
				throw new BusinessException(message);
			}
			bdShareclService.save(bdSharecl);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(BusinessException b){
			message="添加失败："+b.getMessage();
	    }catch(Exception e){
			e.printStackTrace();
			message = "核算属性添加失败";
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新核算属性
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(BdShareclEntity bdSharecl, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "核算属性更新成功";
		BdShareclEntity t = bdShareclService.get(BdShareclEntity.class, bdSharecl.getId());
		try {
			String vname = bdSharecl.getVname();
			if(vname.equals("临床服务类")||vname.equals("医疗技术类")||vname.equals("医疗辅助类")||vname.equals("行政后勤类")||vname.equals("制剂")||vname.equals("病区")||vname.equals("其他")){
				message = "预置数据,不能编辑！";
				j.setMsg(message);
				return j;
			}
			MyBeanUtils.copyBeanNotNull2Bean(bdSharecl, t);
			bdShareclService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "核算属性更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 核算属性新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(BdShareclEntity bdSharecl, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(bdSharecl.getId())) {
			bdSharecl = bdShareclService.getEntity(BdShareclEntity.class, bdSharecl.getId());
			req.setAttribute("bdShareclPage", bdSharecl);
		}
		return new ModelAndView("cn/com/linkwide/cost/pubsetting/bdsharecl/bdSharecl-add");
	}
	/**
	 * 核算属性编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(BdShareclEntity bdSharecl, HttpServletRequest req) {
		ModelAndView view=new ModelAndView("cn/com/linkwide/cost/pubsetting/bdsharecl/bdSharecl-update");
		if (StringUtil.isNotEmpty(bdSharecl.getId())) {
			bdSharecl = bdShareclService.getEntity(BdShareclEntity.class, bdSharecl.getId());
			req.setAttribute("bdShareclPage", bdSharecl);
		}
		String sql="select distinct pk_sharecl from co_dep_costrel";
		List<String> sharecl=systemService.findListbySql(sql);
		try {
			if(sharecl.size()>0){
				if(sharecl.contains(bdSharecl.getId())){
					view=new ModelAndView("cn/com/linkwide/cost/pubsetting/cocostdeptbas/coCostdeptbas-check");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
			return view;
	}
	
	@RequestMapping(params = "goDetail")
	public ModelAndView goDetail(BdShareclEntity bdSharecl, HttpServletRequest req) {
		ModelAndView view=new ModelAndView("cn/com/linkwide/cost/pubsetting/bdsharecl/bdSharecl-update");
		if (StringUtil.isNotEmpty(bdSharecl.getId())) {
			bdSharecl = bdShareclService.getEntity(BdShareclEntity.class, bdSharecl.getId());
			req.setAttribute("bdShareclPage", bdSharecl);
		}
		return view;
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","bdShareclController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(BdShareclEntity bdSharecl,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(BdShareclEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, bdSharecl, request.getParameterMap());
		List<BdShareclEntity> bdSharecls = this.bdShareclService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"核算属性");
		modelMap.put(NormalExcelConstants.CLASS,BdShareclEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("核算属性列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,bdSharecls);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(BdShareclEntity bdSharecl,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"核算属性");
    	modelMap.put(NormalExcelConstants.CLASS,BdShareclEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("核算属性列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<BdShareclEntity> listBdShareclEntitys = ExcelImportUtil.importExcel(file.getInputStream(),BdShareclEntity.class,params);
				for (BdShareclEntity bdSharecl : listBdShareclEntitys) {
					bdShareclService.save(bdSharecl);
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
	public List<BdShareclEntity> list() {
		List<BdShareclEntity> listBdSharecls=bdShareclService.getList(BdShareclEntity.class);
		return listBdSharecls;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		BdShareclEntity task = bdShareclService.get(BdShareclEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody BdShareclEntity bdSharecl, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<BdShareclEntity>> failures = validator.validate(bdSharecl);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		try{
			bdShareclService.save(bdSharecl);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = bdSharecl.getId();
		URI uri = uriBuilder.path("/rest/bdShareclController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody BdShareclEntity bdSharecl) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<BdShareclEntity>> failures = validator.validate(bdSharecl);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		try{
			bdShareclService.saveOrUpdate(bdSharecl);
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
		bdShareclService.deleteEntityById(BdShareclEntity.class, id);
	}
	/**
	 * 核算属性树
	 *@author chencp
	 *2017年9月7日下午7:43:18
	 * @param request
	 * @return rootList 
	 */
	@RequestMapping(params="bdShareclTree")
	@ResponseBody
	public List<ComboTree> bdShareclTree(HttpServletRequest request){
		List<ComboTree> rootList=new ArrayList<ComboTree>();
		try {
			List<BdShareclEntity> shareclList = bdShareclService.getList(BdShareclEntity.class);
			List<ComboTree> list=new ArrayList<ComboTree>();
			if(shareclList!=null&&shareclList.size()>0){
				for(BdShareclEntity share:shareclList ){
					ComboTree comb=new ComboTree();
					comb.setId(share.getId());
					comb.setText(share.getVname());
					comb.setState("open");
					list.add(comb);
				}
			}
			ComboTree co=new ComboTree();
			co.setId("核算属性");
			co.setText("核算属性");
			co.setChildren(list);
			co.setState("open");
			rootList.add(co);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return rootList;
	}
	/**
	 * 核算属性弹出框
	 *@author chencp
	 *2017年9月7日下午7:44:21
	 * @param request
	 * @return
	 */
	@RequestMapping(params="bdShareclSelect")
	public ModelAndView bdShareclSelect(HttpServletRequest request){
		return new ModelAndView("cn/com/linkwide/cost/pubsetting/bdsharecl/bdShareclSelect");
	}
	
	/**
	 *查询核算属性树
	 *@author heyc
	 *
	 * @return
	 */
//	@RequestMapping(params = "addTree")
//	@ResponseBody
//	public List<ComboTree> addTree(HttpServletRequest request) {
//		List<?> list = bdShareclService.getList(BdShareclEntity.class);
//		List<Map<String, String>> listMap=new ArrayList<Map<String, String>>();
//		for(Object obj : list){
//			Map<String, String> map = new HashMap<String, String>();
//			String id = ((BdShareclEntity) obj).getId();
//			String name = ((BdShareclEntity) obj).getVname();
//			if(!map.containsKey(id)){
//				map.put(id, name);
//			}
//			listMap.add(map);
//		}
//		List<ComboTree> rootList=PubToolUtil.addTree(listMap) ;
//		return rootList;
//	}
	/**
	 * @author zhuhd
	 * @param 2017/9/23
	 * @param request
	 * @param coIncomebasId
	 * @return
	 */
	@RequestMapping(params = "getById")
	@ResponseBody
	public AjaxJson getById(HttpServletRequest request,String bdShareclId){
		AjaxJson j=new AjaxJson();
		if(StringUtils.isNotEmpty(bdShareclId)){
			BdShareclEntity bdSharecl =bdShareclService.findUniqueByProperty(BdShareclEntity.class, "id", bdShareclId);
			/*BdShareclEntity bdSharecl =  bdShareclService.getEntity(BdShareclEntity.class, bdShareclId);*/
				if(bdSharecl!=null){
					Map<String,Object> map=new HashMap<String, Object>();
					String[] cloums={"核算编码","核算名称"};
					map.put("cloums", cloums);
					j.setAttributes(map);
					if(bdSharecl!=null){
						String[] names={bdSharecl.getId(),bdSharecl.getVcode(),bdSharecl.getVname()};
						j.setObj(names);
						j.setSuccess(true);
					}
				}
		}
		return j;
	}
	
	/**
	 * 核算属性参照模糊查询
	 * @author heyc
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "incl")
	@ResponseBody
	public Object incl(HttpServletRequest request) {
//		String str=null;
		Object json =null;
		String q=request.getParameter("q")!=null?request.getParameter("q"):"";
		q=q.replaceAll("'", "");
		String hql="from BdShareclEntity where vname like '%"+q+"%' or vcode like '%"+q+"%' ";
		List<BdShareclEntity> list=systemService.findHql(hql);
		json = JSONObject.toJSON(list);
//		str = json.toString();
		return json;
	}
	
}
