package cn.com.linkwide.ba.material.appatype.controller;
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

import cn.com.linkwide.ba.material.appatype.entity.LBaMaterialAppaTypeEntity;
import cn.com.linkwide.ba.material.appatype.service.LBaMaterialAppaTypeServiceI;
import cn.com.linkwide.ba.material.record.entity.LBaMaterialEntity;
/**   
 * @Title: Controller  
 * @Description: l_ba_material_appa_type
 * @author onlineGenerator
 * @date 2017-11-13 13:20:30
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/lBaMaterialAppaTypeController")
public class LBaMaterialAppaTypeController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(LBaMaterialAppaTypeController.class);

	@Autowired
	private LBaMaterialAppaTypeServiceI lBaMaterialAppaTypeService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	
	String message = "";


	/**
	 * 器械分类列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("cn/com/linkwide/ba/material/appatype/lBaMaterialAppaTypeList");
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
		CriteriaQuery cq = new CriteriaQuery(LBaMaterialAppaTypeEntity.class);
		
		
		cq.add();
		List<LBaMaterialAppaTypeEntity> lBaMaterialAppaTypeEntityList = systemService.getListByCriteriaQuery(cq, false);

		List<ComboTree> comboTrees = new ArrayList<ComboTree>();
		for (LBaMaterialAppaTypeEntity lBaMaterialAppaTypeEntity : lBaMaterialAppaTypeEntityList) {
			
			//判断是否一级节点,并且未停用的数据 是否停用0:否1:是
			if((lBaMaterialAppaTypeEntity.getParentId() == null || lBaMaterialAppaTypeEntity.getParentId().equals(""))){
				comboTrees.add(appaTypeConvertToTree(lBaMaterialAppaTypeEntity,lBaMaterialAppaTypeEntityList));
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
		CriteriaQuery cq = new CriteriaQuery(LBaMaterialAppaTypeEntity.class);
		cq.eq("status", "0");
		
		cq.add();
		List<LBaMaterialAppaTypeEntity> lBaMaterialAppaTypeEntityList = systemService.getListByCriteriaQuery(cq, false);

		List<ComboTree> comboTrees = new ArrayList<ComboTree>();
		for (LBaMaterialAppaTypeEntity lBaMaterialAppaTypeEntity : lBaMaterialAppaTypeEntityList) {
			
			//判断是否一级节点,并且未停用的数据 是否停用0:否1:是
			if((lBaMaterialAppaTypeEntity.getParentId() == null || lBaMaterialAppaTypeEntity.getParentId().equals(""))){
				comboTrees.add(appaTypeConvertToTree(lBaMaterialAppaTypeEntity,lBaMaterialAppaTypeEntityList));
			}	
		}

		return comboTrees;
	}

	private ComboTree appaTypeConvertToTree(LBaMaterialAppaTypeEntity entity,List<LBaMaterialAppaTypeEntity> lBaMaterialAppaTypeEntityList) {
		ComboTree tree = new ComboTree();
		tree.setId(entity.getId());
		tree.setText(entity.getTypeName());

		//查询下级节点，过滤掉停用的数据 是否停用0:否1:是
		List<ComboTree> comboTrees = new ArrayList<ComboTree>();
		for (LBaMaterialAppaTypeEntity lBaMaterialAppaTypeEntity : lBaMaterialAppaTypeEntityList) {
			 if(entity.getId().equals( lBaMaterialAppaTypeEntity.getParentId())){
				comboTrees.add(appaTypeConvertToTree(lBaMaterialAppaTypeEntity,lBaMaterialAppaTypeEntityList)); 
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
	public void datagrid(LBaMaterialAppaTypeEntity lBaMaterialAppaType,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(LBaMaterialAppaTypeEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, lBaMaterialAppaType, request.getParameterMap());
		try{
		//自定义追加查询条件
			cq.addOrder("createDate", SortDirection.desc);
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.lBaMaterialAppaTypeService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	
	/**
	 * 器械分类展示
	 * 
	 * @return
	 */
	@RequestMapping(params = "goShow")
	public ModelAndView goShow(LBaMaterialAppaTypeEntity lBaMaterialAppaType, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(lBaMaterialAppaType.getId())) {
			lBaMaterialAppaType = lBaMaterialAppaTypeService.getEntity(LBaMaterialAppaTypeEntity.class, lBaMaterialAppaType.getId());
			req.setAttribute("lBaMaterialAppaTypePage", lBaMaterialAppaType);
		}
		return new ModelAndView("cn/com/linkwide/ba/material/appatype/lBaMaterialAppaType-show");
	}
	

	/**
	 * 删除器械分类
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(LBaMaterialAppaTypeEntity lBaMaterialAppaType, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		lBaMaterialAppaType = systemService.getEntity(LBaMaterialAppaTypeEntity.class, lBaMaterialAppaType.getId());
		message = "器械分类删除成功";
		try{
			//删除校验
			AjaxJson jj = valiDel(lBaMaterialAppaType.getId());
			if(!jj.isSuccess()){
				jj.setSuccess(true);
				return jj;
			}
			
			lBaMaterialAppaTypeService.delete(lBaMaterialAppaType);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "器械分类删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	public AjaxJson valiDel(String id){
		AjaxJson j = new AjaxJson();
		List<LBaMaterialEntity> lBaMaterialEntities = systemService.findByQueryString(" from LBaMaterialEntity where appaTypeId = '"+id+"'");
		if(lBaMaterialEntities.size() > 0){
			j.setMsg("器械分类已经被引用，不可删除");
			j.setSuccess(false);
			return j;
		}
		return j;
	}
	
	
	/**
	 * 批量删除器械分类
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "器械分类删除成功";
		try{
			for(String id:ids.split(",")){
				//删除校验
				AjaxJson jj = valiDel(id);
				if(!jj.isSuccess()){
					jj.setSuccess(true);
					return jj;
				}
				
				LBaMaterialAppaTypeEntity lBaMaterialAppaType = systemService.getEntity(LBaMaterialAppaTypeEntity.class, 
				id
				);
				lBaMaterialAppaTypeService.delete(lBaMaterialAppaType);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "器械分类删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加器械分类
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(LBaMaterialAppaTypeEntity lBaMaterialAppaType, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "器械分类添加成功";
		try{
			List<LBaMaterialAppaTypeEntity> l1 = lBaMaterialAppaTypeService.findByProperty(LBaMaterialAppaTypeEntity.class, "typeCode", lBaMaterialAppaType.getTypeCode());
			
			if(l1.size() > 0){
				message = "编码已存在";
				j.setSuccess(false);
				j.setMsg(message);
				return j;
			}
			
			
			List<LBaMaterialAppaTypeEntity> l2 = lBaMaterialAppaTypeService.findByProperty(LBaMaterialAppaTypeEntity.class, "typeName", lBaMaterialAppaType.getTypeName());
			
			if(l2.size() > 0){
				message = "名称已存在";
				j.setSuccess(false);
				j.setMsg(message);
				return j;
			}
			
			if(StringUtil.isNotEmpty(lBaMaterialAppaType.getParentId())){
				LBaMaterialAppaTypeEntity p = systemService.getEntity(LBaMaterialAppaTypeEntity.class, lBaMaterialAppaType.getParentId());
				if(!lBaMaterialAppaType.getTypeCode().startsWith(p.getTypeCode())){
					message = "分类编码要以父节点编码开始";
					j.setSuccess(false);
					j.setMsg(message);
					return j;
				}
			}
			lBaMaterialAppaTypeService.save(lBaMaterialAppaType);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "器械分类添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新器械分类
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(LBaMaterialAppaTypeEntity lBaMaterialAppaType, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "器械分类更新成功";
		LBaMaterialAppaTypeEntity t = lBaMaterialAppaTypeService.get(LBaMaterialAppaTypeEntity.class, lBaMaterialAppaType.getId());
		try {
			List<LBaMaterialAppaTypeEntity> l1 = lBaMaterialAppaTypeService.findByProperty(LBaMaterialAppaTypeEntity.class, "typeCode", lBaMaterialAppaType.getTypeCode());
			if(l1.size() > 0){
				if(!t.getId().equals(l1.get(0).getId())){
					message = "编码已存在";
					j.setSuccess(false);
					j.setMsg(message);
					return j;
				}
			}
			
			List<LBaMaterialAppaTypeEntity> l2 = lBaMaterialAppaTypeService.findByProperty(LBaMaterialAppaTypeEntity.class, "typeName", lBaMaterialAppaType.getTypeName());
			if(l2.size() > 0){
				if(!t.getId().equals(l2.get(0).getId())){
					message = "名称已存在";
					j.setSuccess(false);
					j.setMsg(message);
					return j;
				}
			}	
			
			if(StringUtil.isNotEmpty(lBaMaterialAppaType.getParentId())){
				LBaMaterialAppaTypeEntity p = systemService.getEntity(LBaMaterialAppaTypeEntity.class, lBaMaterialAppaType.getParentId());
				if(!lBaMaterialAppaType.getTypeCode().startsWith(p.getTypeCode())){
					message = "分类编码要以父节点编码开始";
					j.setSuccess(false);
					j.setMsg(message);
					return j;
				}
			}
			MyBeanUtils.copyBeanNotNull2Bean(lBaMaterialAppaType, t);
			lBaMaterialAppaTypeService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "器械分类更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 器械分类新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(LBaMaterialAppaTypeEntity lBaMaterialAppaType, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(lBaMaterialAppaType.getId())) {
			lBaMaterialAppaType = lBaMaterialAppaTypeService.getEntity(LBaMaterialAppaTypeEntity.class, lBaMaterialAppaType.getId());
			req.setAttribute("lBaMaterialAppaTypePage", lBaMaterialAppaType);
			
		}
		if(lBaMaterialAppaType.getParentId() != null && !lBaMaterialAppaType.getParentId().equals("")){
			req.setAttribute("lBaMaterialAppaTypePage", lBaMaterialAppaType);
			
			LBaMaterialAppaTypeEntity p = systemService.getEntity(LBaMaterialAppaTypeEntity.class, lBaMaterialAppaType.getParentId());
			req.setAttribute("pCode", p.getTypeCode());
			
		}
		return new ModelAndView("cn/com/linkwide/ba/material/appatype/lBaMaterialAppaType-add");
	}
	/**
	 * 器械分类编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(LBaMaterialAppaTypeEntity lBaMaterialAppaType, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(lBaMaterialAppaType.getId())) {
			lBaMaterialAppaType = lBaMaterialAppaTypeService.getEntity(LBaMaterialAppaTypeEntity.class, lBaMaterialAppaType.getId());
			req.setAttribute("lBaMaterialAppaTypePage", lBaMaterialAppaType);
		}
		return new ModelAndView("cn/com/linkwide/ba/material/appatype/lBaMaterialAppaType-update");
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
		message = "器械分类修改成功";
		try{
			String status = request.getParameter("status");

			LBaMaterialAppaTypeEntity lBaMaterialAppaTypeEntity = lBaMaterialAppaTypeService.getEntity(LBaMaterialAppaTypeEntity.class, id);

			
			boolean flag = true;
			//停用时判断是否存在子节点
			if(status.equals("1")){
				
				sql = "select count(1) from l_ba_material_appa_type t where t.parent_id = ? and t.status = ?";
				params = new Object[]{lBaMaterialAppaTypeEntity.getId(),0};
				long count = this.systemService.getCountForJdbcParam(sql, params);
				if(count>0){
					flag = false;
					message = "exist";
				}
			}else if("0".equals(status)){
				if(StringUtil.isNotEmpty(lBaMaterialAppaTypeEntity.getParentId())){
					LBaMaterialAppaTypeEntity lBaMaterialAppaTypeEntity2 = systemService.getEntity(LBaMaterialAppaTypeEntity.class, lBaMaterialAppaTypeEntity.getParentId());
					if("1".equals(lBaMaterialAppaTypeEntity2.getStatus())){
						flag = false;
						message = "400";
					}
				}
			}
			if(flag){
				//是否停用 0：否 1：是
				lBaMaterialAppaTypeEntity.setStatus(status);
				
				lBaMaterialAppaTypeService.saveOrUpdate(lBaMaterialAppaTypeEntity);
			}
			
				
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "器械分类修改失败";
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
		req.setAttribute("controller_name","lBaMaterialAppaTypeController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(LBaMaterialAppaTypeEntity lBaMaterialAppaType,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(LBaMaterialAppaTypeEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, lBaMaterialAppaType, request.getParameterMap());
		List<LBaMaterialAppaTypeEntity> lBaMaterialAppaTypes = this.lBaMaterialAppaTypeService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"器械分类");
		modelMap.put(NormalExcelConstants.CLASS,LBaMaterialAppaTypeEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("器械分类列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,lBaMaterialAppaTypes);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(LBaMaterialAppaTypeEntity lBaMaterialAppaType,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"器械分类");
    	modelMap.put(NormalExcelConstants.CLASS,LBaMaterialAppaTypeEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("器械分类列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
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
				List<LBaMaterialAppaTypeEntity> listLBaMaterialAppaTypeEntitys = ExcelImportUtil.importExcel(file.getInputStream(),LBaMaterialAppaTypeEntity.class,params);
				for (LBaMaterialAppaTypeEntity lBaMaterialAppaType : listLBaMaterialAppaTypeEntitys) {
					lBaMaterialAppaTypeService.save(lBaMaterialAppaType);
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
	public List<LBaMaterialAppaTypeEntity> list() {
		List<LBaMaterialAppaTypeEntity> listLBaMaterialAppaTypes=lBaMaterialAppaTypeService.getList(LBaMaterialAppaTypeEntity.class);
		return listLBaMaterialAppaTypes;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		LBaMaterialAppaTypeEntity task = lBaMaterialAppaTypeService.get(LBaMaterialAppaTypeEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody LBaMaterialAppaTypeEntity lBaMaterialAppaType, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<LBaMaterialAppaTypeEntity>> failures = validator.validate(lBaMaterialAppaType);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		try{
			lBaMaterialAppaTypeService.save(lBaMaterialAppaType);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = lBaMaterialAppaType.getId();
		URI uri = uriBuilder.path("/rest/lBaMaterialAppaTypeController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody LBaMaterialAppaTypeEntity lBaMaterialAppaType) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<LBaMaterialAppaTypeEntity>> failures = validator.validate(lBaMaterialAppaType);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		try{
			lBaMaterialAppaTypeService.saveOrUpdate(lBaMaterialAppaType);
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
		lBaMaterialAppaTypeService.deleteEntityById(LBaMaterialAppaTypeEntity.class, id);
	}
}
