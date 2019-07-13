package cn.com.linkwide.ba.suppliertype.controller;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
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
import org.jeecgframework.core.common.model.json.TreeGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil;
import org.jeecgframework.core.util.ExceptionUtil;
import org.jeecgframework.core.util.MutiLangUtil;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.NumberComparator;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.tag.vo.datatable.SortDirection;
import org.jeecgframework.tag.vo.easyui.TreeGridModel;
import org.jeecgframework.web.system.pojo.base.TSFunction;
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

import cn.com.linkwide.ba.supplier.entity.LBaSupplierEntity;
import cn.com.linkwide.ba.suppliertype.entity.LBaSupplierTypeEntity;
import cn.com.linkwide.ba.suppliertype.service.LBaSupplierTypeServiceI;
import cn.com.linkwide.common.tools.VcodeplanUtil;

/**   
 * @Title: Controller  
 * @Description: 供应商类型
 * @author onlineGenerator
 * @date 2017-11-08 17:47:58
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/lBaSupplierTypeController")
public class LBaSupplierTypeController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(LBaSupplierTypeController.class);

	@Autowired
	private LBaSupplierTypeServiceI lBaSupplierTypeService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 供应商类型列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("cn/com/linkwide/ba/suppliertype/lBaSupplierTypeList");
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
		CriteriaQuery cq = new CriteriaQuery(LBaSupplierTypeEntity.class);
		
		cq.add();
		List<LBaSupplierTypeEntity> lBaSupplierTypeEntityList = systemService.getListByCriteriaQuery(cq, false);

		List<ComboTree> comboTrees = new ArrayList<ComboTree>();
		for (LBaSupplierTypeEntity lBaSupplierTypeEntity : lBaSupplierTypeEntityList) {
			
			//判断是否一级节点,并且未停用的数据 
			if(lBaSupplierTypeEntity.getParentId() == null || lBaSupplierTypeEntity.getParentId().equals("")){
				comboTrees.add(peopleTypeConvertToTree(lBaSupplierTypeEntity,lBaSupplierTypeEntityList));
			}	
		}

		return comboTrees;
	}
	
	@RequestMapping("/getTreeAllDateForstate")
	@ResponseBody
	public List<ComboTree> getTreeAllDateForstate(HttpServletRequest request) {
		
//		//性能考虑。查询出所有的数据到缓存，然后进行遍历处理
		CriteriaQuery cq = new CriteriaQuery(LBaSupplierTypeEntity.class);
		
		//是否停用0:否1:是
		cq.eq("status", "0");
		
		cq.add();
		List<LBaSupplierTypeEntity> lBaSupplierTypeEntityList = systemService.getListByCriteriaQuery(cq, false);

		List<ComboTree> comboTrees = new ArrayList<ComboTree>();
		for (LBaSupplierTypeEntity lBaSupplierTypeEntity : lBaSupplierTypeEntityList) {
			
			//判断是否一级节点,并且未停用的数据 
			if(lBaSupplierTypeEntity.getParentId() == null || lBaSupplierTypeEntity.getParentId().equals("")){
				comboTrees.add(peopleTypeConvertToTree(lBaSupplierTypeEntity,lBaSupplierTypeEntityList));
			}
		}

		return comboTrees;
	}
	
	private ComboTree peopleTypeConvertToTree(LBaSupplierTypeEntity entity,List<LBaSupplierTypeEntity> lBaSupplierTypeEntityList) {
		ComboTree tree = new ComboTree();
		tree.setId(entity.getId());
		tree.setText(entity.getTypeName());

		//查询下级节点，过滤掉停用的数据 是否停用0:否1:是
		List<ComboTree> comboTrees = new ArrayList<ComboTree>();
		for (LBaSupplierTypeEntity lBaSupplierTypeEntity : lBaSupplierTypeEntityList) {
			if(lBaSupplierTypeEntity.getParentId() != null){
				if(lBaSupplierTypeEntity.getParentId().equals(entity.getId()) ){
					comboTrees.add(peopleTypeConvertToTree(lBaSupplierTypeEntity,lBaSupplierTypeEntityList)); 
				 }
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", entity.getStatus());
		map.put("isLast", entity.getIsLast());
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
	public void datagrid(LBaSupplierTypeEntity lBaSupplierType,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(LBaSupplierTypeEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, lBaSupplierType, request.getParameterMap());
		try{
		//自定义追加查询条件
			cq.addOrder("createDate", SortDirection.desc);
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.lBaSupplierTypeService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	@RequestMapping(params = "datagridForTree")
	@ResponseBody
	public List<TreeGrid> datagridForTree(HttpServletRequest request, TreeGrid treegrid) {
		CriteriaQuery cq = new CriteriaQuery(LBaSupplierTypeEntity.class);
		String selfId = request.getParameter("selfId");
		if (selfId != null) {
			cq.notEq("id", selfId);
		}
		if (treegrid.getId() != null) {
			cq.eq("LBaSupplierTypeEntity.id", treegrid.getId());
		}
		if (treegrid.getId() == null) {
			cq.isNull("LBaSupplierTypeEntity");
		}
		cq.addOrder("functionOrder", SortDirection.asc);
		cq.add();

		//获取装载数据权限的条件HQL
		cq = HqlGenerateUtil.getDataAuthorConditionHql(cq, new TSFunction());
		cq.add();

		
		List<TSFunction> functionList = systemService.getListByCriteriaQuery(cq, false);

        Collections.sort(functionList, new NumberComparator());

        List<TreeGrid> treeGrids = new ArrayList<TreeGrid>();
		TreeGridModel treeGridModel = new TreeGridModel();
		treeGridModel.setIcon("TSIcon_iconPath");
		treeGridModel.setTextField("functionName");
		treeGridModel.setParentText("TSFunction_functionName");
		treeGridModel.setParentId("TSFunction_id");
		treeGridModel.setSrc("functionUrl");
		treeGridModel.setIdField("id");
		treeGridModel.setChildList("TSFunctions");
		// 添加排序字段
		treeGridModel.setOrder("functionOrder");

		treeGridModel.setFunctionType("functionType");

		treeGrids = systemService.treegrid(functionList, treeGridModel);

		MutiLangUtil.setMutiTree(treeGrids);
		return treeGrids;
	}

	/**
	 * 删除供应商类型
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(LBaSupplierTypeEntity lBaSupplierType, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		lBaSupplierType = systemService.getEntity(LBaSupplierTypeEntity.class, lBaSupplierType.getId());
		message = "200";//供应商类型删除成功
		try{
			List<LBaSupplierEntity> ll = systemService.findByQueryString(" from LBaSupplierEntity where supplierTypeId = '"+lBaSupplierType.getId()+"'");
			if(ll.size() > 0){
				message = "300";//类型已经被引用
			}else{
				lBaSupplierTypeService.delete(lBaSupplierType);
			}
			
			
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "500";//供应商类型删除失败
			j.setSuccess(false);
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除供应商类型
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "供应商类型删除成功";
		try{
			for(String id:ids.split(",")){
				LBaSupplierTypeEntity lBaSupplierType = systemService.getEntity(LBaSupplierTypeEntity.class, 
				id
				);
				lBaSupplierTypeService.delete(lBaSupplierType);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "供应商类型删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加供应商类型
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(LBaSupplierTypeEntity lBaSupplierType, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "供应商类型添加成功";
		try{
			//校验
			AjaxJson ajaxJson = vailRep(lBaSupplierType);
			if(!ajaxJson.isSuccess()){
				return ajaxJson;
			}
			
			if(StringUtil.isNotEmpty(lBaSupplierType.getParentId())){
				List<LBaSupplierEntity> l1 = systemService.findByQueryString(" from LBaSupplierEntity where supplierTypeId = '"+lBaSupplierType.getParentId()+"' "); 
				if(l1.size() > 0){
					j.setMsg("该节点已存在数据，不能添加子节点");
					j.setSuccess(false);
					return j;
				}
			}/*else{
				List<LBaSupplierTypeEntity> l2 = systemService.findByQueryString(" from LBaSupplierTypeEntity");
				if(l2.size() > 0){
					j.setMsg("已经存在根节点，不能添加");
					j.setSuccess(false);
					return j;
				}
			}*/
			
			
			
			lBaSupplierTypeService.save(lBaSupplierType);
			
			j.setSuccess(true);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "供应商类型添加失败";
			j.setSuccess(false);
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	public AjaxJson vailRep(LBaSupplierTypeEntity lBaSupplierType){
		AjaxJson j = new AjaxJson();
		List<LBaSupplierTypeEntity> l1 = lBaSupplierTypeService.findByQueryString(" from LBaSupplierTypeEntity where id != '"+lBaSupplierType.getId()+"' and typeCode = '"+lBaSupplierType.getTypeCode()+"'");
		if(l1.size() > 0){
			j.setMsg("分类编码已经存在");
			j.setSuccess(false);
			return j;
		}
		
		List<LBaSupplierTypeEntity> l2 = lBaSupplierTypeService.findByQueryString(" from LBaSupplierTypeEntity where id != '"+lBaSupplierType.getId()+"' and typeName = '"+lBaSupplierType.getTypeName()+"'");
		if(l2.size() > 0){
			j.setMsg("分类名称已经存在");
			j.setSuccess(false);
			return j;
		}
		
		/*if(StringUtil.isNotEmpty(lBaSupplierType.getParentId())){
			LBaSupplierTypeEntity lBaSupplierTypeEntity = systemService.getEntity(LBaSupplierTypeEntity.class, lBaSupplierType.getParentId());
			if(!lBaSupplierType.getTypeCode().startsWith(lBaSupplierTypeEntity.getTypeCode())){
				j.setMsg("分类编码要以父节点编码开始");
				j.setSuccess(false);
				return j;
			}
		}*/
		return j;
	}
	
	/**
	 * 更新供应商类型
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(LBaSupplierTypeEntity lBaSupplierType, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "供应商类型更新成功";
		LBaSupplierTypeEntity t = lBaSupplierTypeService.get(LBaSupplierTypeEntity.class, lBaSupplierType.getId());
		try {
			//校验
			AjaxJson ajaxJson = vailRep(lBaSupplierType);
			if(!ajaxJson.isSuccess()){
				return ajaxJson;
			}
			MyBeanUtils.copyBeanNotNull2Bean(lBaSupplierType, t);
			lBaSupplierTypeService.saveOrUpdate(t);
			j.setSuccess(true);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "供应商类型更新失败";
			j.setSuccess(false);
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
	public AjaxJson checkState(LBaSupplierTypeEntity lBaSupplierType, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "200";//修改成功
		LBaSupplierTypeEntity t = lBaSupplierTypeService.get(LBaSupplierTypeEntity.class, lBaSupplierType.getId());
		try {
			boolean flog = true;
			if("1".equals(lBaSupplierType.getStatus())){
				//该节点是否有子节点
				List<LBaSupplierTypeEntity> l = lBaSupplierTypeService.findByQueryString(" from LBaSupplierTypeEntity where parentId = '"+t.getId()+"' and status = '0'");
				//该节点是否被引用
				List<LBaSupplierEntity> ll = systemService.findByQueryString(" from LBaSupplierEntity where supplierTypeId = '"+lBaSupplierType.getId()+"'");
				if(l.size() > 0 || ll.size() > 0){
					flog = false;
					message = "300";//有不停用的子节点
				}
				
			}else if("0".equals(lBaSupplierType.getStatus())){
				if(StringUtil.isNotEmpty(t.getParentId())){
					LBaSupplierTypeEntity lBaSupplierTypeEntity = systemService.getEntity(LBaSupplierTypeEntity.class, t.getParentId());
					if("1".equals(lBaSupplierTypeEntity.getStatus())){
						flog = false;
						message = "400";//父級是停用，子級不能啟用
					}
				}
				
			}
			
			if(flog){
				t.setStatus(lBaSupplierType.getStatus());
				lBaSupplierTypeService.saveOrUpdate(t);
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
	 * 供应商类型新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(LBaSupplierTypeEntity lBaSupplierType, HttpServletRequest req) {
		String pid =req.getParameter("pid");
		
		if(StringUtils.isNotEmpty(pid)){
			req.setAttribute("pid", pid);
			
			LBaSupplierTypeEntity p = systemService.getEntity(LBaSupplierTypeEntity.class, pid);
			req.setAttribute("pCode", p.getTypeCode());
		}
		
		if (StringUtil.isNotEmpty(lBaSupplierType.getId())) {
			lBaSupplierType = lBaSupplierTypeService.getEntity(LBaSupplierTypeEntity.class, lBaSupplierType.getId());
			req.setAttribute("lBaSupplierTypePage", lBaSupplierType);
		}
		//编码规则
		String strcode="";
		TSFunction function = systemService.getEntity(TSFunction.class, "4028818e636849a301636860ef170017");
		if(VcodeplanUtil.ifvcode(function.getId())){
			strcode=VcodeplanUtil.strvcode(function.getId());
		}
		req.setAttribute("strcode", strcode);
		return new ModelAndView("cn/com/linkwide/ba/suppliertype/lBaSupplierType-add");
	}
	/**
	 * 供应商类型编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(LBaSupplierTypeEntity lBaSupplierType, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(lBaSupplierType.getId())) {
			lBaSupplierType = lBaSupplierTypeService.getEntity(LBaSupplierTypeEntity.class, lBaSupplierType.getId());
			req.setAttribute("lBaSupplierTypePage", lBaSupplierType);
		}
		String strcode="";
		TSFunction function = systemService.getEntity(TSFunction.class, "4028818e636849a301636860ef170017");
		if(VcodeplanUtil.ifvcode(function.getId())){
			strcode=VcodeplanUtil.strvcode(function.getId());
		}
		req.setAttribute("strcode", strcode);
		return new ModelAndView("cn/com/linkwide/ba/suppliertype/lBaSupplierType-update");
	}
	
	/**
	 * 供应商类型编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goDetail")
	public ModelAndView goDetail(LBaSupplierTypeEntity lBaSupplierType, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(lBaSupplierType.getId())) {
			lBaSupplierType = lBaSupplierTypeService.getEntity(LBaSupplierTypeEntity.class, lBaSupplierType.getId());
			req.setAttribute("lBaSupplierTypePage", lBaSupplierType);
		}
		String strcode="";
		TSFunction function = systemService.getEntity(TSFunction.class, "4028818e636849a301636860ef170017");
		if(VcodeplanUtil.ifvcode(function.getId())){
			strcode=VcodeplanUtil.strvcode(function.getId());
		}
		req.setAttribute("strcode", strcode);
		return new ModelAndView("cn/com/linkwide/ba/suppliertype/lBaSupplierType-detail");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","lBaSupplierTypeController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(LBaSupplierTypeEntity lBaSupplierType,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(LBaSupplierTypeEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, lBaSupplierType, request.getParameterMap());
		List<LBaSupplierTypeEntity> lBaSupplierTypes = this.lBaSupplierTypeService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"供应商类型");
		modelMap.put(NormalExcelConstants.CLASS,LBaSupplierTypeEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("供应商类型列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,lBaSupplierTypes);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(LBaSupplierTypeEntity lBaSupplierType,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"供应商类型");
    	modelMap.put(NormalExcelConstants.CLASS,LBaSupplierTypeEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("供应商类型列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
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
				List<LBaSupplierTypeEntity> listLBaSupplierTypeEntitys = ExcelImportUtil.importExcel(file.getInputStream(),LBaSupplierTypeEntity.class,params);
				for (LBaSupplierTypeEntity lBaSupplierType : listLBaSupplierTypeEntitys) {
					lBaSupplierTypeService.save(lBaSupplierType);
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
	public List<LBaSupplierTypeEntity> list() {
		System.out.println("222");
		List<LBaSupplierTypeEntity> listLBaSupplierTypes=lBaSupplierTypeService.getList(LBaSupplierTypeEntity.class);
		return listLBaSupplierTypes;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		System.out.println("111");
		LBaSupplierTypeEntity task = lBaSupplierTypeService.get(LBaSupplierTypeEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody LBaSupplierTypeEntity lBaSupplierType, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<LBaSupplierTypeEntity>> failures = validator.validate(lBaSupplierType);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		try{
			lBaSupplierTypeService.save(lBaSupplierType);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = lBaSupplierType.getId();
		URI uri = uriBuilder.path("/rest/lBaSupplierTypeController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody LBaSupplierTypeEntity lBaSupplierType) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<LBaSupplierTypeEntity>> failures = validator.validate(lBaSupplierType);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		try{
			lBaSupplierTypeService.saveOrUpdate(lBaSupplierType);
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
		lBaSupplierTypeService.deleteEntityById(LBaSupplierTypeEntity.class, id);
	}
	/*
	 * 中标供应商管理
	 */
	@RequestMapping(params = "trainlist")
	public ModelAndView trainlist(HttpServletRequest request) {
		return new ModelAndView("cn/com/linkwide/ba/supplier/IBasupperliertainlist");
	}

	@RequestMapping(params = "datagridAll")
	public void datagridAll(LBaSupplierEntity lBaSupplier, HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid) {
		//CriteriaQuery cq = new CriteriaQuery(LBaSupplierEntity.class, dataGrid);
		try {
			String supplierFullName=request.getParameter("supplierFullName");
			String uscc=request.getParameter("uscc");
			// 处理查询条件
			StringBuffer sql = new StringBuffer();
			sql.append(" from mate_sup_bid c ");
			sql.append(" left join mate_bid_detail a  on c.bid_d_id=a.id ");
			sql.append(" left join mate_bid_main b on a.main_id=b.id");
			sql.append(" left join l_ba_supplier  d on c.vendor=d.id ");
			sql.append(" where c.sup_state='4'");
			if(StringUtil.isNotEmpty(supplierFullName)){
			sql.append(" and d.supplier_full_name like '%"+supplierFullName+"%'");
			}
			if(StringUtil.isNotEmpty(uscc)){
				sql.append(" and d.uscc like '%"+uscc+"%'");
			}
			sql.append(" and d.supplier_short_name is null");
			String head = " select d.supplier_full_name  supplierfullname,c.vendor  id,d.uscc  uscc,d.contacts  contacts,d.telphone  telphone,c.sup_state  supstate";
			System.out.println(head+sql);
			dataGrid.setResults(
					systemService.findForJdbc(head + sql.toString(), dataGrid.getPage(), dataGrid.getRows()));
			Integer cont = Integer
					.valueOf(systemService.getCountForJdbc("select count(c.vendor) " + sql.toString()).toString());
			dataGrid.setTotal(cont);
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		TagUtil.datagrid(response, dataGrid);

		//this.lBaSupplierTypeService.getDataGridReturn(cq, true);
		// this.lBaSupplierService.getDataGridReturn(cq, true);
		//TagUtil.datagrid(response, dataGrid);
	}
@RequestMapping(params = "runADD")
	public ModelAndView runADD(LBaSupplierEntity lBaSupplier, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(lBaSupplier.getId())) {
			lBaSupplier = lBaSupplierTypeService.getEntity(LBaSupplierEntity.class, lBaSupplier.getId());
			req.setAttribute("lBaSupplierPage", lBaSupplier);
		}

		String supplierTypeId = req.getParameter("supplierTypeId");
		req.setAttribute("supplierTypeId", supplierTypeId);
		return new ModelAndView("cn/com/linkwide/ba/supplier/IBasupperliertainlist-add");
	}

	@RequestMapping(params = "suppliername")
	@ResponseBody
	public void suppliername(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		try {
			String q = request.getParameter("q") != null ? request.getParameter("q") : "";
			// 处理查询条件
			StringBuffer sql = new StringBuffer();
			sql.append(" from mate_sup_bid c ");
			sql.append(" left join mate_bid_detail a  on c.bid_d_id=a.id ");

			sql.append(" left join mate_bid_main b on a.main_id=b.id");
			sql.append(" left join l_ba_supplier  d on c.vendor=d.id ");
			sql.append(" where ( d.supplier_full_name like '%" + q + "%'");
			sql.append(" or c.vendor like '%" + q + "%'");
			sql.append(" or d.uscc like			 '%" + q + "%'");
			sql.append(" or d.contacts like '%" + q + "%'");
			sql.append(" or d.telphone like '%" + q + "%')");
			sql.append(" and c.sup_state='4' ");
			System.out.println(sql);
			String head = " select d.supplier_full_name  supplierfullname,c.vendor  vendor,d.uscc  uscc,d.contacts  contacts,d.telphone  telphone,c.sup_state  supstate";
			dataGrid.setResults(
					systemService.findForJdbc(head + sql.toString(), dataGrid.getPage(), dataGrid.getRows()));
			Integer cont = Integer
					.valueOf(systemService.getCountForJdbc("select count(c.vendor) " + sql.toString()).toString());
			dataGrid.setTotal(cont);
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		TagUtil.datagrid(response, dataGrid);
	}
}
