package cn.com.linkwide.ba.material.qualType.controller;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.apache.commons.lang.StringUtils;
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
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.tag.vo.datatable.SortDirection;
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

import cn.com.linkwide.ba.material.qual.entity.LSuMaterialQualEntity;
import cn.com.linkwide.ba.material.qualType.entity.LBaMaterialQualTypeEntity;
import cn.com.linkwide.ba.material.qualType.service.LBaMaterialQualTypeServiceI;
import cn.com.linkwide.ba.material.qualitem.entity.LSuMaterialQualItemEntity;

/**   
 * @Title: Controller  
 * @Description: 资质分类
 * @author onlineGenerator
 * @date 2017-11-13 13:42:24
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/lBaMaterialQualTypeController")
public class LBaMaterialQualTypeController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(LBaMaterialQualTypeController.class);

	@Autowired
	private LBaMaterialQualTypeServiceI lSuQualTypeService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 资质分类列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("cn/com/linkwide/ba/material/qualtype/lBaMaterialQualTypeList");
	}
	
	/**
	 * 加载树的全部数据
	 * @param request
	 * @return
	 */
	@RequestMapping("/getTreeAllDate")
	@ResponseBody
	public List<ComboTree> getTreeAllDate(HttpServletRequest request) {
		
		//性能考虑。查询出所有的数据到缓存，然后进行遍历处理
		CriteriaQuery cq = new CriteriaQuery(LBaMaterialQualTypeEntity.class);
		
		cq.add();
		List<LBaMaterialQualTypeEntity> LBaMaterialQualTypeEntityList = systemService.getListByCriteriaQuery(cq, false);

		List<ComboTree> comboTrees = new ArrayList<ComboTree>();
		for (LBaMaterialQualTypeEntity LBaMaterialQualTypeEntity : LBaMaterialQualTypeEntityList) {
			
			//判断是否一级节点,并且未停用的数据 
			if(LBaMaterialQualTypeEntity.getParentId() == null || LBaMaterialQualTypeEntity.getParentId().equals("")){
				comboTrees.add(peopleTypeConvertToTree(LBaMaterialQualTypeEntity,LBaMaterialQualTypeEntityList));
			}	
		}

		return comboTrees;
	}
	
	@RequestMapping("/getTreeAllDateForstate")
	@ResponseBody
	public List<ComboTree> getTreeAllDateForstate(HttpServletRequest request) {
		
		//性能考虑。查询出所有的数据到缓存，然后进行遍历处理
		CriteriaQuery cq = new CriteriaQuery(LBaMaterialQualTypeEntity.class);
		
		//是否停用0:否1:是
		cq.eq("status", "0");
		
		cq.add();
		List<LBaMaterialQualTypeEntity> LBaMaterialQualTypeEntityList = systemService.getListByCriteriaQuery(cq, false);

		List<ComboTree> comboTrees = new ArrayList<ComboTree>();
		for (LBaMaterialQualTypeEntity LBaMaterialQualTypeEntity : LBaMaterialQualTypeEntityList) {
			
			//判断是否一级节点,并且未停用的数据 
			if(LBaMaterialQualTypeEntity.getParentId() == null || LBaMaterialQualTypeEntity.getParentId().equals("")){
				comboTrees.add(peopleTypeConvertToTree(LBaMaterialQualTypeEntity,LBaMaterialQualTypeEntityList));
			}	
		}

		return comboTrees;
	}
	
	private ComboTree peopleTypeConvertToTree(LBaMaterialQualTypeEntity entity,List<LBaMaterialQualTypeEntity> LBaMaterialQualTypeEntityList) {
		ComboTree tree = new ComboTree();
		tree.setId(entity.getId());
		tree.setText(entity.getTypeName());

		//查询下级节点，过滤掉停用的数据 是否停用0:否1:是
		List<ComboTree> comboTrees = new ArrayList<ComboTree>();
		for (LBaMaterialQualTypeEntity LBaMaterialQualTypeEntity : LBaMaterialQualTypeEntityList) {
			if(LBaMaterialQualTypeEntity.getParentId() != null){
				if(LBaMaterialQualTypeEntity.getParentId().equals(entity.getId()) ){
					comboTrees.add(peopleTypeConvertToTree(LBaMaterialQualTypeEntity,LBaMaterialQualTypeEntityList)); 
				 }
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", entity.getStatus());
		tree.setAttributes(map);
		tree.setChildren(comboTrees);

		return tree;
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
	public void datagrid(LBaMaterialQualTypeEntity lSuQualType,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(LBaMaterialQualTypeEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, lSuQualType, request.getParameterMap());
		try{
		//自定义追加查询条件
			cq.addOrder("createDate", SortDirection.desc);
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.lSuQualTypeService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除资质分类
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(LBaMaterialQualTypeEntity lSuQualType, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		lSuQualType = systemService.getEntity(LBaMaterialQualTypeEntity.class, lSuQualType.getId());
		message = "200";//资质分类删除成功
		try{
			List<LSuMaterialQualEntity> l = systemService.findByQueryString(" from LSuMaterialQualEntity where certType = '"+lSuQualType.getTypeCode()+"'");
			List<LSuMaterialQualEntity> l1 = systemService.findByQueryString(" from LSuMaterialQualEntity where certType = '"+lSuQualType.getTypeCode()+"'");
			if(l.size() > 0 || l1.size() > 0){
				message = "300";//已经被引用
			}else{
				lSuQualTypeService.delete(lSuQualType);
			}
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "500";//资质分类删除失败
			j.setSuccess(false);
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除资质分类
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "资质分类删除成功";
		try{
			for(String id:ids.split(",")){
				LBaMaterialQualTypeEntity lSuQualType = systemService.getEntity(LBaMaterialQualTypeEntity.class, 
				id
				);
				lSuQualTypeService.delete(lSuQualType);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "资质分类删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加资质分类
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(LBaMaterialQualTypeEntity lSuQualType, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "资质分类添加成功";
		try{
			
			//校验
			AjaxJson ajaxJson = vailRep(lSuQualType);
			if(!ajaxJson.isSuccess()){
				return ajaxJson;
			}
			
			lSuQualTypeService.save(lSuQualType);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "资质分类添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	public AjaxJson vailRep(LBaMaterialQualTypeEntity lSuQualType){
		AjaxJson j = new AjaxJson();
		String hql = "" ;
		if(StringUtil.isNotEmpty( lSuQualType.getId())){
			hql  =" and id != '"+lSuQualType.getId()+"' ";
		}
		List<LBaMaterialQualTypeEntity> l1 = lSuQualTypeService.findByQueryString(" from LBaMaterialQualTypeEntity where  typeCode = '"+lSuQualType.getTypeCode()+"' "+hql);
		if(l1.size() > 0){
			j.setMsg("分类编码已经存在");
			j.setSuccess(false);
			return j;
		}
		
		List<LBaMaterialQualTypeEntity> l2 = lSuQualTypeService.findByQueryString(" from LBaMaterialQualTypeEntity where typeName = '"+lSuQualType.getTypeName()+"' "+hql);
		if(l2.size() > 0){
			j.setMsg("分类名称已经存在");
			j.setSuccess(false);
			return j;
		}
		
		if(StringUtil.isNotEmpty(lSuQualType.getParentId())){
			LBaMaterialQualTypeEntity p = systemService.getEntity(LBaMaterialQualTypeEntity.class, lSuQualType.getParentId());
			if(!lSuQualType.getTypeCode().startsWith(p.getTypeCode())){
				j.setMsg("分类编码要以父节点编码开始");
				j.setSuccess(false);
				return j;
			}
		}
		
		return j;
	}
	
	/**
	 * 更新资质分类
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(LBaMaterialQualTypeEntity lSuQualType, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "资质分类更新成功";
		LBaMaterialQualTypeEntity t = lSuQualTypeService.get(LBaMaterialQualTypeEntity.class, lSuQualType.getId());
		try {
			//校验
			AjaxJson ajaxJson = vailRep(lSuQualType);
			if(!ajaxJson.isSuccess()){
				return ajaxJson;
			}
			MyBeanUtils.copyBeanNotNull2Bean(lSuQualType, t);
			lSuQualTypeService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "资质分类更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	/**
	 * 更新供应商类型
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "checkState")
	@ResponseBody
	public AjaxJson checkState(LBaMaterialQualTypeEntity lSuQualType, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "200";//修改成功
		LBaMaterialQualTypeEntity t = lSuQualTypeService.get(LBaMaterialQualTypeEntity.class, lSuQualType.getId());
		try {
			boolean flog = true;
			if("1".equals(lSuQualType.getStatus())){
				//该节点是否有子节点
				List<LBaMaterialQualTypeEntity> l = lSuQualTypeService.findByQueryString(" from LBaMaterialQualTypeEntity where parentId = '"+t.getId()+"' and status = '0'");
				//该节点是否被引用
				//List<LBaSupplierEntity> ll = systemService.findByQueryString(" from LBaSupplierEntity where supplierTypeId = '"+lSuQualType.getId()+"'");
				if(l.size() > 0){
					flog = false;
					message = "300";//有不停用的子节点
				}
				
			}else if("0".equals(lSuQualType.getStatus())){
				if(StringUtil.isNotEmpty(t.getParentId())){
					LBaMaterialQualTypeEntity LBaMaterialQualTypeEntity = systemService.getEntity(LBaMaterialQualTypeEntity.class, t.getParentId());
					if("1".equals(LBaMaterialQualTypeEntity.getStatus())){
						flog = false;
						message = "400";//父節點被停用，不能啟用子節點
					}
				}
			}
			
			if(flog){
				t.setStatus(lSuQualType.getStatus());
				lSuQualTypeService.saveOrUpdate(t);
			}
			
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "500";//修改失败
			j.setSuccess(false);
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 资质分类新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(LBaMaterialQualTypeEntity lSuQualType, HttpServletRequest req) {
		String pid =req.getParameter("pid");
		
		if(StringUtils.isNotEmpty(pid)){
			req.setAttribute("pid", pid);
			LBaMaterialQualTypeEntity p = systemService.getEntity(LBaMaterialQualTypeEntity.class, pid);
			req.setAttribute("pCode", p.getTypeCode());
		}
		if (StringUtil.isNotEmpty(lSuQualType.getId())) {
			lSuQualType = lSuQualTypeService.getEntity(LBaMaterialQualTypeEntity.class, lSuQualType.getId());
			req.setAttribute("lSuQualTypePage", lSuQualType);
		}
		return new ModelAndView("cn/com/linkwide/ba/material/qualtype/lBaMaterialQualType-add");
	}
	/**
	 * 资质分类编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(LBaMaterialQualTypeEntity lSuQualType, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(lSuQualType.getId())) {
			lSuQualType = lSuQualTypeService.getEntity(LBaMaterialQualTypeEntity.class, lSuQualType.getId());
			req.setAttribute("lSuQualTypePage", lSuQualType);
		}
		return new ModelAndView("cn/com/linkwide/ba/material/qualtype/lBaMaterialQualType-update");
	}
	
	/**
	 * 供应商类型编辑页面跳转
	 * /lms/WebContent/webpage/cn/com/linkwide/mate/supplier/supplier/lsuqualtype/lSuQualType-detail.jsp
	 * @return
	 */
	@RequestMapping(params = "goDetail")
	public ModelAndView goDetail(LBaMaterialQualTypeEntity lSuQualType, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(lSuQualType.getId())) {
			lSuQualType = lSuQualTypeService.getEntity(LBaMaterialQualTypeEntity.class, lSuQualType.getId());
			req.setAttribute("lSuQualTypePage", lSuQualType);
		}
		return new ModelAndView("cn/com/linkwide/ba/material/qualtype/lBaMaterialQualType-detail");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","lSuQualTypeController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(LBaMaterialQualTypeEntity lSuQualType,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(LBaMaterialQualTypeEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, lSuQualType, request.getParameterMap());
		List<LBaMaterialQualTypeEntity> lSuQualTypes = this.lSuQualTypeService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"资质分类");
		modelMap.put(NormalExcelConstants.CLASS,LBaMaterialQualTypeEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("资质分类列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,lSuQualTypes);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(LBaMaterialQualTypeEntity lSuQualType,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"资质分类");
    	modelMap.put(NormalExcelConstants.CLASS,LBaMaterialQualTypeEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("资质分类列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
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
				List<LBaMaterialQualTypeEntity> listLBaMaterialQualTypeEntitys = ExcelImportUtil.importExcel(file.getInputStream(),LBaMaterialQualTypeEntity.class,params);
				for (LBaMaterialQualTypeEntity lSuQualType : listLBaMaterialQualTypeEntitys) {
					lSuQualTypeService.save(lSuQualType);
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
	public List<LBaMaterialQualTypeEntity> list() {
		List<LBaMaterialQualTypeEntity> listLSuQualTypes=lSuQualTypeService.getList(LBaMaterialQualTypeEntity.class);
		return listLSuQualTypes;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		LBaMaterialQualTypeEntity task = lSuQualTypeService.get(LBaMaterialQualTypeEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody LBaMaterialQualTypeEntity lSuQualType, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<LBaMaterialQualTypeEntity>> failures = validator.validate(lSuQualType);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		try{
			lSuQualTypeService.save(lSuQualType);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = lSuQualType.getId();
		URI uri = uriBuilder.path("/rest/lSuQualTypeController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody LBaMaterialQualTypeEntity lSuQualType) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<LBaMaterialQualTypeEntity>> failures = validator.validate(lSuQualType);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		try{
			lSuQualTypeService.saveOrUpdate(lSuQualType);
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
		lSuQualTypeService.deleteEntityById(LBaMaterialQualTypeEntity.class, id);
	}
	
	 /**
	 * 同步树(除去停用的节点)
	 * 
	 * @param comboTree
	 * @return
	 */
	@RequestMapping(params = "comboTreeType")
	@ResponseBody
	public List<ComboTree> comboTreeType(ComboTree comboTree) {
		 
		//性能考虑。查询出所有的数据到缓存，然后进行遍历处理
		List<Map<String,Object>> departsList = systemService.findForJdbc("select id,type_code,type_name,parent_id  from l_ba_material_qual_type order by type_code ");
		List<ComboTree> comboTrees = new ArrayList<ComboTree>();
		for (Map<String,Object> depart : departsList) {
			
			//判断是否一级节点,并且未停用的数据 是否停用0:否1:是
			if(depart.get("parent_id") == null || "".equals(depart.get("parent_id").toString())){
				comboTrees.add(typeConvertToTree(depart,departsList));
			}	
		}

		return comboTrees;
	}
	
	private ComboTree typeConvertToTree(Map<String,Object> entity,List<Map<String,Object>> list) {
		ComboTree tree = new ComboTree();
		tree.setId(entity.get("id").toString());
		tree.setText(entity.get("type_name").toString());

		//查询下级节点，过滤掉停用的数据 是否停用0:否1:是
		List<ComboTree> comboTrees = new ArrayList<ComboTree>();
		for (Map<String,Object> depart : list) {
			if(depart.get("parent_id") != null && !"".equals(depart.get("parent_id").toString())){
				if(depart.get("parent_id").toString().equals(entity.get("id").toString())){
					comboTrees.add(typeConvertToTree(depart,list));
				}
			}
		}
		tree.setChildren(comboTrees);
		return tree;
	}
}
