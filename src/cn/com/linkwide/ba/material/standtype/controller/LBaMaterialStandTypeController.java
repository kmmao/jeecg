package cn.com.linkwide.ba.material.standtype.controller;
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

import cn.com.linkwide.ba.material.record.entity.LBaMaterialEntity;
import cn.com.linkwide.ba.material.standtype.entity.LBaMaterialStandTypeEntity;
import cn.com.linkwide.ba.material.standtype.service.LBaMaterialStandTypeServiceI;

/**   
 * @Title: Controller  
 * @Description: l_ba_material_stand_type
 * @author onlineGenerator
 * @date 2017-11-14 11:05:17
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/lBaMaterialStandTypeController")
public class LBaMaterialStandTypeController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(LBaMaterialStandTypeController.class);

	@Autowired
	private LBaMaterialStandTypeServiceI lBaMaterialStandTypeService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * l_ba_material_stand_type列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("cn/com/linkwide/ba/material/standtype/lBaMaterialStandTypeList");
	}
	
	
	/**
	 * 同步树
	 * 
	 * @param comboTree
	 * @return
	 */
	@RequestMapping(params = "comboTree")
	@ResponseBody
	public List<ComboTree> tree(ComboTree comboTree) {
		
		//性能考虑。查询出所有的数据到缓存，然后进行遍历处理
		CriteriaQuery cq = new CriteriaQuery(LBaMaterialStandTypeEntity.class);
		
		
		cq.add();
		List<LBaMaterialStandTypeEntity> lBaMaterialStandTypeEntityList = systemService.getListByCriteriaQuery(cq, false);

		List<ComboTree> comboTrees = new ArrayList<ComboTree>();
		for (LBaMaterialStandTypeEntity lBaMaterialStandTypeEntity : lBaMaterialStandTypeEntityList) {
			
			//判断是否一级节点,并且未停用的数据 是否停用0:否1:是
			if((lBaMaterialStandTypeEntity.getParentId() == null || lBaMaterialStandTypeEntity.getParentId().equals(""))){
				comboTrees.add(typeConvertToTree(lBaMaterialStandTypeEntity,lBaMaterialStandTypeEntityList));
			}	
		}

		return comboTrees;
	}
	
	/**
	 * 同步树(除去停用节点)
	 * 
	 * @param comboTree
	 * @return
	 */
	@RequestMapping(params = "comboTreeForStatus")
	@ResponseBody
	public List<ComboTree> comboTreeForStatus(ComboTree comboTree) {
		//性能考虑。查询出所有的数据到缓存，然后进行遍历处理
		CriteriaQuery cq = new CriteriaQuery(LBaMaterialStandTypeEntity.class);
		cq.eq("status", "0");
		
		cq.add();
		List<LBaMaterialStandTypeEntity> lBaMaterialStandTypeEntityList = systemService.getListByCriteriaQuery(cq, false);

		List<ComboTree> comboTrees = new ArrayList<ComboTree>();
		for (LBaMaterialStandTypeEntity lBaMaterialStandTypeEntity : lBaMaterialStandTypeEntityList) {
			
			//判断是否一级节点,并且未停用的数据 是否停用0:否1:是
			if((lBaMaterialStandTypeEntity.getParentId() == null || lBaMaterialStandTypeEntity.getParentId().equals(""))){
				comboTrees.add(typeConvertToTree(lBaMaterialStandTypeEntity,lBaMaterialStandTypeEntityList));
			}	
		}

		return comboTrees;
	}

	private ComboTree typeConvertToTree(LBaMaterialStandTypeEntity entity,List<LBaMaterialStandTypeEntity> lBaMaterialStandTypeEntityList) {
		ComboTree tree = new ComboTree();
		tree.setId(entity.getId());
		tree.setText(entity.getTypeName());

		//查询下级节点，过滤掉停用的数据 是否停用0:否1:是
		List<ComboTree> comboTrees = new ArrayList<ComboTree>();
		for (LBaMaterialStandTypeEntity lBaMaterialStandTypeEntity : lBaMaterialStandTypeEntityList) {
			if(lBaMaterialStandTypeEntity.getParentId() != null && !lBaMaterialStandTypeEntity.getParentId().equals("")){
				if(lBaMaterialStandTypeEntity.getParentId().equals(entity.getId())){
					comboTrees.add(typeConvertToTree(lBaMaterialStandTypeEntity,lBaMaterialStandTypeEntityList));
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
	public void datagrid(LBaMaterialStandTypeEntity lBaMaterialStandType,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(LBaMaterialStandTypeEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, lBaMaterialStandType, request.getParameterMap());
		try{
		//自定义追加查询条件
			cq.addOrder("createDate", SortDirection.desc);
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.lBaMaterialStandTypeService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除l_ba_material_stand_type
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(LBaMaterialStandTypeEntity lBaMaterialStandType, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		lBaMaterialStandType = systemService.getEntity(LBaMaterialStandTypeEntity.class, lBaMaterialStandType.getId());
		message = "国际分类删除成功";
		try{
			
			AjaxJson j1 = validel(lBaMaterialStandType);
			if(!j1.isSuccess()){
				j1.setSuccess(true);
				return j1;
			}
			lBaMaterialStandTypeService.delete(lBaMaterialStandType);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "国际分类删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
public AjaxJson validel(LBaMaterialStandTypeEntity lBaMaterialStandType){
		
		AjaxJson json = new AjaxJson();
		json.setMsg("校验成功");
		
		List<LBaMaterialStandTypeEntity> list1 = systemService.findByQueryString(" from LBaMaterialStandTypeEntity where parentId = '"+lBaMaterialStandType.getId()+"'");
		if(list1.size() > 0){
			json.setMsg("包含子节点，不可删除");
			json.setSuccess(false);
			return json;
		}
		
		
		List<LBaMaterialEntity> list = systemService.findByQueryString(" from LBaMaterialEntity where standTypeId = '"+lBaMaterialStandType.getId()+"'");
		if(list.size() > 0){
			json.setMsg("国标分类被物资引用，不可删除");
			json.setSuccess(false);
			return json;
		}
		return json;
	}
	
	/**
	 * 批量删除l_ba_material_stand_type
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "国际分类删除成功";
		try{
			for(String id:ids.split(",")){
				LBaMaterialStandTypeEntity lBaMaterialStandType = systemService.getEntity(LBaMaterialStandTypeEntity.class, 
				id
				);
				
				AjaxJson j1 = validel(lBaMaterialStandType);
				if(!j1.isSuccess()){
					j1.setSuccess(true);
					return j1;
				}
				
				lBaMaterialStandTypeService.delete(lBaMaterialStandType);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "国际分类删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	 

	/**
	 * 添加l_ba_material_stand_type
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(LBaMaterialStandTypeEntity lBaMaterialStandType, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "国际分类添加成功";
		try{
			List<LBaMaterialStandTypeEntity> l1 = lBaMaterialStandTypeService.findByProperty(LBaMaterialStandTypeEntity.class, "typeCode", lBaMaterialStandType.getTypeCode());
			
			if(l1.size() > 0){
				message = "编码已存在";
				j.setSuccess(false);
				j.setMsg(message);
				return j;
			}
			
			
			List<LBaMaterialStandTypeEntity> l2 = lBaMaterialStandTypeService.findByProperty(LBaMaterialStandTypeEntity.class, "typeName", lBaMaterialStandType.getTypeName());
			
			if(l2.size() > 0){
				message = "名称已存在";
				j.setSuccess(false);
				j.setMsg(message);
				return j;
			}	
			
			if(StringUtil.isNotEmpty(lBaMaterialStandType.getParentId())){
				LBaMaterialStandTypeEntity p = systemService.getEntity(LBaMaterialStandTypeEntity.class, lBaMaterialStandType.getParentId());
				if(!lBaMaterialStandType.getTypeCode().startsWith(p.getTypeCode())){
					message = "分类编码要以父节点编码开始";
					j.setSuccess(false);
					j.setMsg(message);
					return j;
				}
			}
			lBaMaterialStandTypeService.save(lBaMaterialStandType);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "国际分类添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新l_ba_material_stand_type
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(LBaMaterialStandTypeEntity lBaMaterialStandType, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "国际分类更新成功";
		LBaMaterialStandTypeEntity t = lBaMaterialStandTypeService.get(LBaMaterialStandTypeEntity.class, lBaMaterialStandType.getId());
		try {
			List<LBaMaterialStandTypeEntity> l1 = lBaMaterialStandTypeService.findByProperty(LBaMaterialStandTypeEntity.class, "typeCode", lBaMaterialStandType.getTypeCode());
			if(l1.size() > 0){
				if(!t.getId().equals(l1.get(0).getId())){
					message = "编码已存在";
					j.setSuccess(false);
					j.setMsg(message);
					return j;
				}
			}
			
			List<LBaMaterialStandTypeEntity> l2 = lBaMaterialStandTypeService.findByProperty(LBaMaterialStandTypeEntity.class, "typeName", lBaMaterialStandType.getTypeName());
			if(l2.size() > 0){
				if(!t.getId().equals(l2.get(0).getId())){
					message = "名称已存在";
					j.setSuccess(false);
					j.setMsg(message);
					return j;
				}
			}		
				
			if(StringUtil.isNotEmpty(lBaMaterialStandType.getParentId())){
				LBaMaterialStandTypeEntity p = systemService.getEntity(LBaMaterialStandTypeEntity.class, lBaMaterialStandType.getParentId());
				if(!lBaMaterialStandType.getTypeCode().startsWith(p.getTypeCode())){
					message = "分类编码要以父节点编码开始";
					j.setSuccess(false);
					j.setMsg(message);
					return j;
				}
			}
			MyBeanUtils.copyBeanNotNull2Bean(lBaMaterialStandType, t);
			lBaMaterialStandTypeService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "国际分类更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * l_ba_material_stand_type新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(LBaMaterialStandTypeEntity lBaMaterialStandType, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(lBaMaterialStandType.getId())) {
			lBaMaterialStandType = lBaMaterialStandTypeService.getEntity(LBaMaterialStandTypeEntity.class, lBaMaterialStandType.getId());
			req.setAttribute("lBaMaterialStandTypePage", lBaMaterialStandType);
		}
		if(lBaMaterialStandType.getParentId() != null && !lBaMaterialStandType.getParentId().equals("")){
			req.setAttribute("lBaMaterialStandTypePage", lBaMaterialStandType);
			LBaMaterialStandTypeEntity p = systemService.getEntity(LBaMaterialStandTypeEntity.class, lBaMaterialStandType.getParentId());
			req.setAttribute("pCode", p.getTypeCode());
		}
		return new ModelAndView("cn/com/linkwide/ba/material/standtype/lBaMaterialStandType-add");
	}
	/**
	 * l_ba_material_stand_type编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(LBaMaterialStandTypeEntity lBaMaterialStandType, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(lBaMaterialStandType.getId())) {
			lBaMaterialStandType = lBaMaterialStandTypeService.getEntity(LBaMaterialStandTypeEntity.class, lBaMaterialStandType.getId());
			req.setAttribute("lBaMaterialStandTypePage", lBaMaterialStandType);
		}
		return new ModelAndView("cn/com/linkwide/ba/material/standtype/lBaMaterialStandType-update");
	}
	

	/**
	 * 国标分类展示
	 * 
	 * @return
	 */
	@RequestMapping(params = "goShow")
	public ModelAndView goShow(LBaMaterialStandTypeEntity lBaMaterialStandType, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(lBaMaterialStandType.getId())) {
			lBaMaterialStandType = lBaMaterialStandTypeService.getEntity(LBaMaterialStandTypeEntity.class, lBaMaterialStandType.getId());
			req.setAttribute("lBaMaterialStandTypePage", lBaMaterialStandType);
		}
		return new ModelAndView("cn/com/linkwide/ba/material/standtype/lBaMaterialStandType-show");
	}
	

	/**
	 * 启用 停用
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doEnDisAble")
	@ResponseBody
	public AjaxJson doEnable(String id,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		String sql = null;
		Object[] params = null;
		Map<String,Object> map = null;
		String message = "国标分类修改成功";
		try{
			String status = request.getParameter("status");

			LBaMaterialStandTypeEntity lBaMaterialStandTypeEntity = lBaMaterialStandTypeService.getEntity(LBaMaterialStandTypeEntity.class, id);
			boolean flag = true;
			//停用时判断是否存在子节点
			if(status.equals("1")){
				
				sql = "select count(1) from l_ba_material_stand_type t where t.parent_id = ? and t.status = ?";
				params = new Object[]{lBaMaterialStandTypeEntity.getId(),0};
				long count = this.systemService.getCountForJdbcParam(sql, params);
				if(count>0){
					flag = false;
					message = "exist";
				}
			}else if("0".equals(status)){
				if(StringUtil.isNotEmpty(lBaMaterialStandTypeEntity.getParentId())){
					LBaMaterialStandTypeEntity lBaMaterialStandTypeEntity2 = systemService.getEntity(LBaMaterialStandTypeEntity.class, lBaMaterialStandTypeEntity.getParentId());
					if("1".equals(lBaMaterialStandTypeEntity2.getStatus())){
						flag = false;
						message = "400";
					}
				}
			}
			if(flag){
				//是否停用 0：否 1：是
				lBaMaterialStandTypeEntity.setStatus(status);
				
				lBaMaterialStandTypeService.saveOrUpdate(lBaMaterialStandTypeEntity);
			}
				
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "物资分类修改失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","lBaMaterialStandTypeController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(LBaMaterialStandTypeEntity lBaMaterialStandType,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(LBaMaterialStandTypeEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, lBaMaterialStandType, request.getParameterMap());
		List<LBaMaterialStandTypeEntity> lBaMaterialStandTypes = this.lBaMaterialStandTypeService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"国际分类");
		modelMap.put(NormalExcelConstants.CLASS,LBaMaterialStandTypeEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("国际分类列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,lBaMaterialStandTypes);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(LBaMaterialStandTypeEntity lBaMaterialStandType,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"国际分类");
    	modelMap.put(NormalExcelConstants.CLASS,LBaMaterialStandTypeEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("国际分类列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
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
				List<LBaMaterialStandTypeEntity> listLBaMaterialStandTypeEntitys = ExcelImportUtil.importExcel(file.getInputStream(),LBaMaterialStandTypeEntity.class,params);
				for (LBaMaterialStandTypeEntity lBaMaterialStandType : listLBaMaterialStandTypeEntitys) {
					lBaMaterialStandTypeService.save(lBaMaterialStandType);
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
	public List<LBaMaterialStandTypeEntity> list() {
		List<LBaMaterialStandTypeEntity> listLBaMaterialStandTypes=lBaMaterialStandTypeService.getList(LBaMaterialStandTypeEntity.class);
		return listLBaMaterialStandTypes;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		LBaMaterialStandTypeEntity task = lBaMaterialStandTypeService.get(LBaMaterialStandTypeEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody LBaMaterialStandTypeEntity lBaMaterialStandType, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<LBaMaterialStandTypeEntity>> failures = validator.validate(lBaMaterialStandType);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		try{
			lBaMaterialStandTypeService.save(lBaMaterialStandType);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = lBaMaterialStandType.getId();
		URI uri = uriBuilder.path("/rest/lBaMaterialStandTypeController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody LBaMaterialStandTypeEntity lBaMaterialStandType) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<LBaMaterialStandTypeEntity>> failures = validator.validate(lBaMaterialStandType);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		try{
			lBaMaterialStandTypeService.saveOrUpdate(lBaMaterialStandType);
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
		lBaMaterialStandTypeService.deleteEntityById(LBaMaterialStandTypeEntity.class, id);
	}
}
