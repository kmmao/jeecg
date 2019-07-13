package cn.com.linkwide.ba.material.financetype.controller;
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

import cn.com.linkwide.ba.material.financetype.entity.LBaMaterialFinanceTypeEntity;
import cn.com.linkwide.ba.material.financetype.service.LBaMaterialFinanceTypeServiceI;
import cn.com.linkwide.ba.material.record.entity.LBaMaterialEntity;

/**   
 * @Title: Controller  
 * @Description: l_ba_material_finance_type
 * @author onlineGenerator
 * @date 2017-11-13 16:39:15
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/lBaMaterialFinanceTypeController")
public class LBaMaterialFinanceTypeController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(LBaMaterialFinanceTypeController.class);

	@Autowired
	private LBaMaterialFinanceTypeServiceI lBaMaterialFinanceTypeService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	
	String message = "";

	/**
	 * 财务分类列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("cn/com/linkwide/ba/material/financetype/lBaMaterialFinanceTypeList");
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
		CriteriaQuery cq = new CriteriaQuery(LBaMaterialFinanceTypeEntity.class);
		
		
		cq.add();
		List<LBaMaterialFinanceTypeEntity> lBaMaterialFinanceTypeEntityList = systemService.getListByCriteriaQuery(cq, false);

		List<ComboTree> comboTrees = new ArrayList<ComboTree>();
		for (LBaMaterialFinanceTypeEntity lBaMaterialFinanceTypeEntity : lBaMaterialFinanceTypeEntityList) {
			
			//判断是否一级节点,并且未停用的数据 是否停用0:否1:是
			if((lBaMaterialFinanceTypeEntity.getParentId() == null || lBaMaterialFinanceTypeEntity.getParentId().equals(""))){
				comboTrees.add(financeTypeConvertToTree(lBaMaterialFinanceTypeEntity,lBaMaterialFinanceTypeEntityList));
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
		CriteriaQuery cq = new CriteriaQuery(LBaMaterialFinanceTypeEntity.class);
		cq.eq("status", "0");
		
		cq.add();
		List<LBaMaterialFinanceTypeEntity> lBaMaterialFinanceTypeEntityList = systemService.getListByCriteriaQuery(cq, false);

		List<ComboTree> comboTrees = new ArrayList<ComboTree>();
		for (LBaMaterialFinanceTypeEntity lBaMaterialFinanceTypeEntity : lBaMaterialFinanceTypeEntityList) {
			
			//判断是否一级节点,并且未停用的数据 是否停用0:否1:是
			if((lBaMaterialFinanceTypeEntity.getParentId() == null || lBaMaterialFinanceTypeEntity.getParentId().equals(""))){
				comboTrees.add(financeTypeConvertToTree(lBaMaterialFinanceTypeEntity,lBaMaterialFinanceTypeEntityList));
			}	
		}

		return comboTrees;
	}


	private ComboTree financeTypeConvertToTree(LBaMaterialFinanceTypeEntity entity,List<LBaMaterialFinanceTypeEntity> lBaMaterialFinanceTypeEntityList) {
		ComboTree tree = new ComboTree();
		tree.setId(entity.getId());
		tree.setText(entity.getTypeName());

		//查询下级节点，过滤掉停用的数据 是否停用0:否1:是
		List<ComboTree> comboTrees = new ArrayList<ComboTree>();
		for (LBaMaterialFinanceTypeEntity lBaMaterialFinanceTypeEntity : lBaMaterialFinanceTypeEntityList) {
			if(StringUtil.isNotEmpty(lBaMaterialFinanceTypeEntity.getParentId())) {
				if(lBaMaterialFinanceTypeEntity.getParentId().equals(entity.getId())){
					comboTrees.add(financeTypeConvertToTree(lBaMaterialFinanceTypeEntity,lBaMaterialFinanceTypeEntityList)); 
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
	public void datagrid(LBaMaterialFinanceTypeEntity lBaMaterialFinanceType,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(LBaMaterialFinanceTypeEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, lBaMaterialFinanceType, request.getParameterMap());
		try{
		//自定义追加查询条件
			cq.addOrder("createDate", SortDirection.desc);
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.lBaMaterialFinanceTypeService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除财务分类
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(LBaMaterialFinanceTypeEntity lBaMaterialFinanceType, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		lBaMaterialFinanceType = systemService.getEntity(LBaMaterialFinanceTypeEntity.class, lBaMaterialFinanceType.getId());
		message = "财务分类删除成功";
		try{
			//删除校验
			AjaxJson jj = valiDel(lBaMaterialFinanceType.getId());
			if(!jj.isSuccess()){
				jj.setSuccess(true);
				return jj;
			}
			
			lBaMaterialFinanceTypeService.delete(lBaMaterialFinanceType);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "财务分类删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	public AjaxJson valiDel(String id){
		AjaxJson j = new AjaxJson();
		List<LBaMaterialEntity> lBaMaterialEntities = systemService.findByQueryString(" from LBaMaterialEntity where financeTypeId = '"+id+"'");
		if(lBaMaterialEntities.size() > 0){
			j.setMsg("财务分类已经被引用，不可删除");
			j.setSuccess(false);
			return j;
		}
		return j;
	}
	
	/**
	 * 批量删除财务分类
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "财务分类删除成功";
		try{
			for(String id:ids.split(",")){
				LBaMaterialFinanceTypeEntity lBaMaterialFinanceType = systemService.getEntity(LBaMaterialFinanceTypeEntity.class, 
				id
				);
				
				//删除校验
				AjaxJson jj = valiDel(lBaMaterialFinanceType.getId());
				if(!jj.isSuccess()){
					jj.setSuccess(true);
					return jj;
				}
				
				lBaMaterialFinanceTypeService.delete(lBaMaterialFinanceType);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "财务分类删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	 


	/**
	 * 添加财务分类
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(LBaMaterialFinanceTypeEntity lBaMaterialFinanceType, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "财务分类添加成功";
		try{
			List<LBaMaterialFinanceTypeEntity> l1 = lBaMaterialFinanceTypeService.findByProperty(LBaMaterialFinanceTypeEntity.class, "typeCode", lBaMaterialFinanceType.getTypeCode());
			
			if(l1.size() > 0){
				message = "编码已存在";
				j.setSuccess(false);
				j.setMsg(message);
				return j;
			}
			
			
			List<LBaMaterialFinanceTypeEntity> l2 = lBaMaterialFinanceTypeService.findByProperty(LBaMaterialFinanceTypeEntity.class, "typeName", lBaMaterialFinanceType.getTypeName());
			
			if(l2.size() > 0){
				message = "名称已存在";
				j.setSuccess(false);
				j.setMsg(message);
				return j;
			}	
			
			if(StringUtil.isNotEmpty(lBaMaterialFinanceType.getParentId())){
				LBaMaterialFinanceTypeEntity p = systemService.getEntity(LBaMaterialFinanceTypeEntity.class, lBaMaterialFinanceType.getParentId());
				if(!lBaMaterialFinanceType.getTypeCode().startsWith(p.getTypeCode())){
					message = "分类编码要以父节点编码开始";
					j.setSuccess(false);
					j.setMsg(message);
					return j;
				}
			}
			
			lBaMaterialFinanceTypeService.save(lBaMaterialFinanceType);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "财务分类添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新财务分类
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(LBaMaterialFinanceTypeEntity lBaMaterialFinanceType, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "财务分类更新成功";
		LBaMaterialFinanceTypeEntity t = lBaMaterialFinanceTypeService.get(LBaMaterialFinanceTypeEntity.class, lBaMaterialFinanceType.getId());
		try {
			List<LBaMaterialFinanceTypeEntity> l1 = lBaMaterialFinanceTypeService.findByProperty(LBaMaterialFinanceTypeEntity.class, "typeCode", lBaMaterialFinanceType.getTypeCode());
			if(l1.size() > 0){
				if(!t.getId().equals(l1.get(0).getId())){
					message = "编码已存在";
					j.setSuccess(false);
					j.setMsg(message);
					return j;
				}
			}
			
			List<LBaMaterialFinanceTypeEntity> l2 = lBaMaterialFinanceTypeService.findByProperty(LBaMaterialFinanceTypeEntity.class, "typeName", lBaMaterialFinanceType.getTypeName());
			if(l2.size() > 0){
				if(!t.getId().equals(l2.get(0).getId())){
					message = "名称已存在";
					j.setSuccess(false);
					j.setMsg(message);
					return j;
				}
			}		
			if(StringUtil.isNotEmpty(lBaMaterialFinanceType.getParentId())){
				LBaMaterialFinanceTypeEntity p = systemService.getEntity(LBaMaterialFinanceTypeEntity.class, lBaMaterialFinanceType.getParentId());
				if(!lBaMaterialFinanceType.getTypeCode().startsWith(p.getTypeCode())){
					message = "分类编码要以父节点编码开始";
					j.setSuccess(false);
					j.setMsg(message);
					return j;
				}
			}	
			MyBeanUtils.copyBeanNotNull2Bean(lBaMaterialFinanceType, t);
			lBaMaterialFinanceTypeService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "财务分类更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 财务分类新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(LBaMaterialFinanceTypeEntity lBaMaterialFinanceType, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(lBaMaterialFinanceType.getId())) {
			lBaMaterialFinanceType = lBaMaterialFinanceTypeService.getEntity(LBaMaterialFinanceTypeEntity.class, lBaMaterialFinanceType.getId());
			req.setAttribute("lBaMaterialFinanceTypePage", lBaMaterialFinanceType);
		}
		if(lBaMaterialFinanceType.getParentId() != null && !lBaMaterialFinanceType.getParentId().equals("")){
			req.setAttribute("lBaMaterialFinanceTypePage", lBaMaterialFinanceType);
			LBaMaterialFinanceTypeEntity p = systemService.getEntity(LBaMaterialFinanceTypeEntity.class, lBaMaterialFinanceType.getParentId());
			req.setAttribute("pCode", p.getTypeCode());
			
		}
		return new ModelAndView("cn/com/linkwide/ba/material/financetype/lBaMaterialFinanceType-add");
	}
	/**
	 * 财务分类编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(LBaMaterialFinanceTypeEntity lBaMaterialFinanceType, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(lBaMaterialFinanceType.getId())) {
			lBaMaterialFinanceType = lBaMaterialFinanceTypeService.getEntity(LBaMaterialFinanceTypeEntity.class, lBaMaterialFinanceType.getId());
			req.setAttribute("lBaMaterialFinanceTypePage", lBaMaterialFinanceType);
		}
		return new ModelAndView("cn/com/linkwide/ba/material/financetype/lBaMaterialFinanceType-update");
	}
	
	
	/**
	 * 财务分类展示
	 * 
	 * @return
	 */
	@RequestMapping(params = "goShow")
	public ModelAndView goShow(LBaMaterialFinanceTypeEntity lBaMaterialFinanceType, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(lBaMaterialFinanceType.getId())) {
			lBaMaterialFinanceType = lBaMaterialFinanceTypeService.getEntity(LBaMaterialFinanceTypeEntity.class, lBaMaterialFinanceType.getId());
			req.setAttribute("lBaMaterialFinanceTypePage", lBaMaterialFinanceType);
		}
		return new ModelAndView("cn/com/linkwide/ba/material/financetype/lBaMaterialFinanceType-show");
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

			LBaMaterialFinanceTypeEntity LBaMaterialFinanceTypeEntity = lBaMaterialFinanceTypeService.getEntity(LBaMaterialFinanceTypeEntity.class, id);
			
			boolean flag = true;
			//停用时判断是否存在子节点
			if(status.equals("1")){
				
				sql = "select count(1) from l_ba_material_finance_type t where t.parent_id = ? and t.status = ?";
				params = new Object[]{LBaMaterialFinanceTypeEntity.getId(),0};
				long count = this.systemService.getCountForJdbcParam(sql, params);
				if(count>0){
					flag = false;
					message = "exist";
				}
			}else if("0".equals(status)){
				if(StringUtil.isNotEmpty(LBaMaterialFinanceTypeEntity.getParentId())){
					LBaMaterialFinanceTypeEntity lBaMaterialFinanceTypeEntity2 = systemService.getEntity(LBaMaterialFinanceTypeEntity.getClass(), LBaMaterialFinanceTypeEntity.getParentId());
					if("1".equals(lBaMaterialFinanceTypeEntity2.getStatus())){
						flag = false;
						message = "400";
					}
				}
			}
			if(flag){
				//是否停用 0：否 1：是
				LBaMaterialFinanceTypeEntity.setStatus(status);
				
				lBaMaterialFinanceTypeService.saveOrUpdate(LBaMaterialFinanceTypeEntity);
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
		req.setAttribute("controller_name","lBaMaterialFinanceTypeController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(LBaMaterialFinanceTypeEntity lBaMaterialFinanceType,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(LBaMaterialFinanceTypeEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, lBaMaterialFinanceType, request.getParameterMap());
		List<LBaMaterialFinanceTypeEntity> lBaMaterialFinanceTypes = this.lBaMaterialFinanceTypeService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"财务分类");
		modelMap.put(NormalExcelConstants.CLASS,LBaMaterialFinanceTypeEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("财务分类列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,lBaMaterialFinanceTypes);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(LBaMaterialFinanceTypeEntity lBaMaterialFinanceType,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"财务分类");
    	modelMap.put(NormalExcelConstants.CLASS,LBaMaterialFinanceTypeEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("财务分类列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
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
				List<LBaMaterialFinanceTypeEntity> listLBaMaterialFinanceTypeEntitys = ExcelImportUtil.importExcel(file.getInputStream(),LBaMaterialFinanceTypeEntity.class,params);
				for (LBaMaterialFinanceTypeEntity lBaMaterialFinanceType : listLBaMaterialFinanceTypeEntitys) {
					lBaMaterialFinanceTypeService.save(lBaMaterialFinanceType);
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
	public List<LBaMaterialFinanceTypeEntity> list() {
		List<LBaMaterialFinanceTypeEntity> listLBaMaterialFinanceTypes=lBaMaterialFinanceTypeService.getList(LBaMaterialFinanceTypeEntity.class);
		return listLBaMaterialFinanceTypes;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		LBaMaterialFinanceTypeEntity task = lBaMaterialFinanceTypeService.get(LBaMaterialFinanceTypeEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody LBaMaterialFinanceTypeEntity lBaMaterialFinanceType, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<LBaMaterialFinanceTypeEntity>> failures = validator.validate(lBaMaterialFinanceType);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		try{
			lBaMaterialFinanceTypeService.save(lBaMaterialFinanceType);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = lBaMaterialFinanceType.getId();
		URI uri = uriBuilder.path("/rest/lBaMaterialFinanceTypeController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody LBaMaterialFinanceTypeEntity lBaMaterialFinanceType) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<LBaMaterialFinanceTypeEntity>> failures = validator.validate(lBaMaterialFinanceType);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		try{
			lBaMaterialFinanceTypeService.saveOrUpdate(lBaMaterialFinanceType);
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
		lBaMaterialFinanceTypeService.deleteEntityById(LBaMaterialFinanceTypeEntity.class, id);
	}
}
