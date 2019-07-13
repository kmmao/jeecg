package cn.com.linkwide.ba.material.type.controller;
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
import cn.com.linkwide.ba.material.type.entity.LBaMaterialTypeEntity;
import cn.com.linkwide.ba.material.type.service.LBaMaterialTypeServiceI;

/**   
 * @Title: Controller  
 * @Description: type
 * @author onlineGenerator
 * @date 2017-11-09 10:38:01
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/lBaMaterialTypeController")
public class LBaMaterialTypeController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(LBaMaterialTypeController.class);

	@Autowired
	private LBaMaterialTypeServiceI lBaMaterialTypeService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	
	String message = "";

	/**
	 * type列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("cn/com/linkwide/ba/material/type/lBaMaterialTypeList");
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
		CriteriaQuery cq = new CriteriaQuery(LBaMaterialTypeEntity.class);
		//编码排序
		cq.addOrder("typeCode", SortDirection.asc);
		cq.add();
		List<LBaMaterialTypeEntity> lBaMaterialTypeEntityList = systemService.getListByCriteriaQuery(cq, false);

		List<ComboTree> comboTrees = new ArrayList<ComboTree>();
		for (LBaMaterialTypeEntity lBaMaterialTypeEntity : lBaMaterialTypeEntityList) {
			//判断是否一级节点,并且未停用的数据 是否停用0:否1:是
			if((lBaMaterialTypeEntity.getParentId() == null || lBaMaterialTypeEntity.getParentId().equals(""))){
				comboTrees.add(typeConvertToTree(lBaMaterialTypeEntity,lBaMaterialTypeEntityList));
			}	
		}
		return comboTrees;
	}
	
	
	
	/**
	 * 同步树(除去停用的节点)
	 * 
	 * @param comboTree
	 * @return
	 */
	@RequestMapping(params = "comboTreeForState")
	@ResponseBody
	public List<ComboTree> comboTreeForState(ComboTree comboTree) {
		
		//性能考虑。查询出所有的数据到缓存，然后进行遍历处理
		CriteriaQuery cq = new CriteriaQuery(LBaMaterialTypeEntity.class);
		cq.eq("status", "0");
		//编码排序
		cq.addOrder("typeCode", SortDirection.asc);
		cq.add();
		List<LBaMaterialTypeEntity> lBaMaterialTypeEntityList = systemService.getListByCriteriaQuery(cq, false);

		List<ComboTree> comboTrees = new ArrayList<ComboTree>();
		for (LBaMaterialTypeEntity lBaMaterialTypeEntity : lBaMaterialTypeEntityList) {
			
			//判断是否一级节点,并且未停用的数据 是否停用0:否1:是
			if((lBaMaterialTypeEntity.getParentId() == null || lBaMaterialTypeEntity.getParentId().equals(""))){
				comboTrees.add(typeConvertToTree(lBaMaterialTypeEntity,lBaMaterialTypeEntityList));
			}	
		}

		return comboTrees;
	}

	private ComboTree typeConvertToTree(LBaMaterialTypeEntity entity,List<LBaMaterialTypeEntity> lBaMaterialTypeEntityList) {
		ComboTree tree = new ComboTree();
		tree.setId(entity.getId());
		tree.setText(entity.getTypeName());

		//查询下级节点，过滤掉停用的数据 是否停用0:否1:是
		List<ComboTree> comboTrees = new ArrayList<ComboTree>();
		for (LBaMaterialTypeEntity lBaMaterialTypeEntity : lBaMaterialTypeEntityList) {
			if(lBaMaterialTypeEntity.getParentId() != null && !lBaMaterialTypeEntity.getParentId().equals("")){
				if(lBaMaterialTypeEntity.getParentId().equals(entity.getId())){
					comboTrees.add(typeConvertToTree(lBaMaterialTypeEntity,lBaMaterialTypeEntityList));
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
	public void datagrid(LBaMaterialTypeEntity lBaMaterialType,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(LBaMaterialTypeEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, lBaMaterialType, request.getParameterMap());
		try{
		//自定义追加查询条件
			cq.addOrder("createDate", SortDirection.desc);
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.lBaMaterialTypeService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除type
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(LBaMaterialTypeEntity lBaMaterialType, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		lBaMaterialType = systemService.getEntity(LBaMaterialTypeEntity.class, lBaMaterialType.getId());
		message = "type删除成功";
		try{
			AjaxJson j1 = validel(lBaMaterialType);
			if(!j1.isSuccess()){
				j1.setSuccess(false);
				return j1;
			}
			List<LBaMaterialTypeEntity> li = systemService.findByProperty(LBaMaterialTypeEntity.class, "parentId", lBaMaterialType.getId());
			if(li != null && li.size() >0){
				j.setMsg("请先删除子节点数据");
				j.setSuccess(false);
				return j;
			}
			lBaMaterialTypeService.delete(lBaMaterialType);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "type删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	public AjaxJson validel(LBaMaterialTypeEntity lBaMaterialType){
		
		AjaxJson json = new AjaxJson();
		json.setMsg("校验成功");
		
		List<LBaMaterialTypeEntity> list1 = systemService.findByQueryString(" from LBaMaterialTypeEntity where parentId = '"+lBaMaterialType.getId()+"'");
		if(list1.size() > 0){
			json.setMsg("包含子节点，不可删除");
			json.setSuccess(false);
			return json;
		}
		
		
		List<LBaMaterialEntity> list = systemService.findByQueryString(" from LBaMaterialEntity where materialTypeId = '"+lBaMaterialType.getId()+"'");
		if(list.size() > 0){
			json.setMsg("物资分类被物资引用，不可删除");
			json.setSuccess(false);
			return json;
		}
		return json;
	}
	
	/**
	 * 批量删除type
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "type删除成功";
		try{
			for(String id:ids.split(",")){
				LBaMaterialTypeEntity lBaMaterialType = systemService.getEntity(LBaMaterialTypeEntity.class, 
				id
				);
				AjaxJson j1 = validel(lBaMaterialType);
				if(!j1.isSuccess()){
					j1.setSuccess(true);
					return j1;
				}
				
				lBaMaterialTypeService.delete(lBaMaterialType);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "type删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 添加type
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(LBaMaterialTypeEntity lBaMaterialType, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "添加成功";
		try{
			List<LBaMaterialTypeEntity> l1 = lBaMaterialTypeService.findByProperty(LBaMaterialTypeEntity.class, "typeCode", lBaMaterialType.getTypeCode());
			
			if(l1.size() > 0){
				message = "编码已存在";
				j.setSuccess(false);
				j.setMsg(message);
				return j;
			}
			
			
			List<LBaMaterialTypeEntity> l2 = lBaMaterialTypeService.findByProperty(LBaMaterialTypeEntity.class, "typeName", lBaMaterialType.getTypeName());
			
			if(l2.size() > 0){
				message = "名称已存在";
				j.setSuccess(false);
				j.setMsg(message);
				return j;
			}
			
			if(StringUtil.isNotEmpty(lBaMaterialType.getParentId())){
				LBaMaterialTypeEntity p = systemService.getEntity(LBaMaterialTypeEntity.class, lBaMaterialType.getParentId());
				if(!lBaMaterialType.getTypeCode().startsWith(p.getTypeCode())){
					message = "分类编码要以父节点编码开始";
					j.setSuccess(false);
					j.setMsg(message);
					return j;
				}
			}
			
			//修改级别
			if(StringUtil.isNotEmpty(lBaMaterialType.getParentId())){
				//判断是否存在物资，存在不允许添加子节点
				List<LBaMaterialEntity> l3 = systemService.findByQueryString(" from LBaMaterialEntity where materialTypeId = '"+lBaMaterialType.getParentId()+"'");
				if(l3.size() > 0){
					message = "该节点已存在数据，不能添加子节点";
					j.setSuccess(false);
					j.setMsg(message);
					return j;
				}
				
				LBaMaterialTypeEntity l = systemService.getEntity(LBaMaterialTypeEntity.class, lBaMaterialType.getParentId());
				int s = 0;
				if(StringUtil.isEmpty(lBaMaterialType.getParentId())){
					s = 1;
				}else{
					if(l != null){
						s =  Integer.valueOf(l.getTlevel() == null ? "0" : l.getTlevel()) + 1;
					}
				}
				lBaMaterialType.setTlevel(s+"");
			}/*else{
				lBaMaterialType.setTlevel("0");
				//只能有一个根节点
				List<LBaMaterialTypeEntity> l4 = systemService.findByQueryString(" from LBaMaterialTypeEntity");
				if(l4.size() > 0){
					message = "已经存在根节点，不能继续添加。";
					j.setSuccess(false);
					j.setMsg(message);
					return j;
				}
			}*/
			
			lBaMaterialTypeService.save(lBaMaterialType);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "type添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新type
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(LBaMaterialTypeEntity lBaMaterialType, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "type更新成功";
		LBaMaterialTypeEntity t = lBaMaterialTypeService.get(LBaMaterialTypeEntity.class, lBaMaterialType.getId());
		try {
			List<LBaMaterialTypeEntity> l1 = lBaMaterialTypeService.findByProperty(LBaMaterialTypeEntity.class, "typeCode", lBaMaterialType.getTypeCode());
			if(l1.size() > 0){
				if(!t.getId().equals(l1.get(0).getId())){
					message = "编码已存在";
					j.setSuccess(false);
					j.setMsg(message);
					return j;
				}
			}
			
			List<LBaMaterialTypeEntity> l2 = lBaMaterialTypeService.findByProperty(LBaMaterialTypeEntity.class, "typeName", lBaMaterialType.getTypeName());
			if(l2.size() > 0){
				if(!t.getId().equals(l2.get(0).getId())){
					message = "名称已存在";
					j.setSuccess(false);
					j.setMsg(message);
					return j;
				}
			}
			
			if(StringUtil.isNotEmpty(lBaMaterialType.getParentId())){
				LBaMaterialTypeEntity p = systemService.getEntity(LBaMaterialTypeEntity.class, lBaMaterialType.getParentId());
				if(!lBaMaterialType.getTypeCode().startsWith(p.getTypeCode())){
					message = "分类编码要以父节点编码开始";
					j.setSuccess(false);
					j.setMsg(message);
					return j;
				}
			}
			
			MyBeanUtils.copyBeanNotNull2Bean(lBaMaterialType, t);
			lBaMaterialTypeService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "type更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * type新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(LBaMaterialTypeEntity lBaMaterialType, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(lBaMaterialType.getId())) {
			lBaMaterialType = lBaMaterialTypeService.getEntity(LBaMaterialTypeEntity.class, lBaMaterialType.getId());
			req.setAttribute("lBaMaterialTypePage", lBaMaterialType);
		}
		if(lBaMaterialType.getParentId() != null && !lBaMaterialType.getParentId().equals("")){
			req.setAttribute("lBaMaterialTypePage", lBaMaterialType);
			LBaMaterialTypeEntity p = systemService.getEntity(LBaMaterialTypeEntity.class, lBaMaterialType.getParentId());
			req.setAttribute("pCode", p.getTypeCode());
		}
		return new ModelAndView("cn/com/linkwide/ba/material/type/lBaMaterialType-add");
	}
	/**
	 * type编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(LBaMaterialTypeEntity lBaMaterialType, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(lBaMaterialType.getId())) {
			lBaMaterialType = lBaMaterialTypeService.getEntity(LBaMaterialTypeEntity.class, lBaMaterialType.getId());
			req.setAttribute("lBaMaterialTypePage", lBaMaterialType);
		}
		return new ModelAndView("cn/com/linkwide/ba/material/type/lBaMaterialType-update");
	}
	
	
	/**
	 * 物资分类展示
	 * 
	 * @return
	 */
	@RequestMapping(params = "goShow")
	public ModelAndView goShow(LBaMaterialTypeEntity lBaMaterialType, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(lBaMaterialType.getId())) {
			lBaMaterialType = lBaMaterialTypeService.getEntity(LBaMaterialTypeEntity.class, lBaMaterialType.getId());
			req.setAttribute("lBaMaterialTypePage", lBaMaterialType);
		}
		return new ModelAndView("cn/com/linkwide/ba/material/type/lBaMaterialType-show");
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
		message = "物资分类修改成功";
		try{
			String status = request.getParameter("status");

			LBaMaterialTypeEntity lBaMaterialTypeEntity = lBaMaterialTypeService.getEntity(LBaMaterialTypeEntity.class, id);
			boolean flag = true;
			//停用时判断是否存在子节点
			if(status.equals("1")){
				
				sql = "select count(1) from l_ba_material_type t where t.parent_id = ? and t.status = ?";
				params = new Object[]{lBaMaterialTypeEntity.getId(),0};
				long count = this.systemService.getCountForJdbcParam(sql, params);
				if(count>0){
					flag = false;
					message = "exist";
				}
			}
			// 停用时判断是否存在物资档案
			if (status.equals("1")) {
				sql = "select count(1) from l_ba_material b where b.material_type_id = ?";
				params = new Object[]{lBaMaterialTypeEntity.getId()};
				Long count = this.systemService.getCountForJdbcParam(sql, params);
				if (count > 0) {
					flag = false;
					message = "existData";
				}
			}
			
			if("0".equals(status)){
				if(StringUtil.isNotEmpty(lBaMaterialTypeEntity.getParentId())){
					LBaMaterialTypeEntity lBaMaterialTypeEntity2 = systemService.getEntity(LBaMaterialTypeEntity.class, lBaMaterialTypeEntity.getParentId());
					if("1".equals(lBaMaterialTypeEntity2.getStatus())){
						flag = false;
						message = "400";//父節點停用，子節點不可以啟用
					}
				}
			}
			
			if(flag){
				//是否停用 0：否 1：是
				lBaMaterialTypeEntity.setStatus(status);
				
				lBaMaterialTypeService.saveOrUpdate(lBaMaterialTypeEntity);
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
		req.setAttribute("controller_name","lBaMaterialTypeController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(LBaMaterialTypeEntity lBaMaterialType,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(LBaMaterialTypeEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, lBaMaterialType, request.getParameterMap());
		List<LBaMaterialTypeEntity> lBaMaterialTypes = this.lBaMaterialTypeService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"type");
		modelMap.put(NormalExcelConstants.CLASS,LBaMaterialTypeEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("type列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,lBaMaterialTypes);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(LBaMaterialTypeEntity lBaMaterialType,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"type");
    	modelMap.put(NormalExcelConstants.CLASS,LBaMaterialTypeEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("type列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
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
				List<LBaMaterialTypeEntity> listLBaMaterialTypeEntitys = ExcelImportUtil.importExcel(file.getInputStream(),LBaMaterialTypeEntity.class,params);
				for (LBaMaterialTypeEntity lBaMaterialType : listLBaMaterialTypeEntitys) {
					lBaMaterialTypeService.save(lBaMaterialType);
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
	public List<LBaMaterialTypeEntity> list() {
		List<LBaMaterialTypeEntity> listLBaMaterialTypes=lBaMaterialTypeService.getList(LBaMaterialTypeEntity.class);
		return listLBaMaterialTypes;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		LBaMaterialTypeEntity task = lBaMaterialTypeService.get(LBaMaterialTypeEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody LBaMaterialTypeEntity lBaMaterialType, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<LBaMaterialTypeEntity>> failures = validator.validate(lBaMaterialType);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		try{
			lBaMaterialTypeService.save(lBaMaterialType);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = lBaMaterialType.getId();
		URI uri = uriBuilder.path("/rest/lBaMaterialTypeController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody LBaMaterialTypeEntity lBaMaterialType) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<LBaMaterialTypeEntity>> failures = validator.validate(lBaMaterialType);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		try{
			lBaMaterialTypeService.saveOrUpdate(lBaMaterialType);
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
		lBaMaterialTypeService.deleteEntityById(LBaMaterialTypeEntity.class, id);
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
			List<Map<String,Object>> departsList = systemService.findForJdbc("select id,type_code,type_name,parent_id  from l_ba_material_type order by type_code ");
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
