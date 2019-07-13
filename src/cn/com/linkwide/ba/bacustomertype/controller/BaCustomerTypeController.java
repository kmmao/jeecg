package cn.com.linkwide.ba.bacustomertype.controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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
import org.jeecgframework.core.common.model.json.ComboTree;
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
import org.jeecgframework.tag.vo.datatable.SortDirection;
import org.jeecgframework.web.system.pojo.base.TSFunction;
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

import cn.com.linkwide.ba.bacustomer.entity.BaCustomerEntity;
import cn.com.linkwide.ba.bacustomertype.entity.BaCustomerTypeEntity;
import cn.com.linkwide.ba.bacustomertype.service.BaCustomerTypeServiceI;
import cn.com.linkwide.common.tools.VcodeplanUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**   
 * @Title: Controller  
 * @Description: 客户类型
 * @author onlineGenerator
 * @date 2018-08-03 09:41:00
 * @version V1.0   
 *
 */
@Api(value="BaCustomerType",description="客户类型",tags="baCustomerTypeController")
@Controller
@RequestMapping("/baCustomerTypeController")
public class BaCustomerTypeController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(BaCustomerTypeController.class);

	@Autowired
	private BaCustomerTypeServiceI baCustomerTypeService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 客户类型列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("cn/com/linkwide/ba/bacustomertype/baCustomerTypeList");
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
		CriteriaQuery cq = new CriteriaQuery(BaCustomerTypeEntity.class);
		cq.addOrder("typeCode", SortDirection.asc);
		cq.add();
		List<BaCustomerTypeEntity> BaCustomerTypeEntityList = systemService.getListByCriteriaQuery(cq, false);

		List<ComboTree> comboTrees = new ArrayList<ComboTree>();
		for (BaCustomerTypeEntity BaCustomerTypeEntity : BaCustomerTypeEntityList) {
			
			//判断是否一级节点,并且未停用的数据 
			if(BaCustomerTypeEntity.getParentId() == null || BaCustomerTypeEntity.getParentId().equals("")){
				try {
					if(StringUtil.isNotEmpty(comboTrees)){
					comboTrees.add(peopleTypeConvertToTree(BaCustomerTypeEntity,BaCustomerTypeEntityList));
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}	
		}

		return comboTrees;
	}
	private ComboTree peopleTypeConvertToTree(BaCustomerTypeEntity entity,List<BaCustomerTypeEntity> BaCustomerTypeEntityList) {
		ComboTree tree = new ComboTree();
		tree.setId(entity.getId());
		tree.setText(entity.getTypeName());

		//查询下级节点，过滤掉停用的数据 是否停用0:否1:是
		List<ComboTree> comboTrees = new ArrayList<ComboTree>();
		for (BaCustomerTypeEntity BaCustomerTypeEntity : BaCustomerTypeEntityList) {
			 if(StringUtil.isNotEmpty(BaCustomerTypeEntity.getParentId())){
			 if(BaCustomerTypeEntity.getParentId().equals(entity.getId()) ){
				 if(StringUtil.isNotEmpty(comboTrees)){
				comboTrees.add(peopleTypeConvertToTree(BaCustomerTypeEntity,BaCustomerTypeEntityList)); 
				 }
			 }
			 }
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", entity.getStatus());
		map.put("isLast", entity.getExtend1());
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
	public void datagrid(BaCustomerTypeEntity baCustomerType,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(BaCustomerTypeEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, baCustomerType, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.baCustomerTypeService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除客户类型
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(BaCustomerTypeEntity baCustomerType, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		baCustomerType = systemService.getEntity(BaCustomerTypeEntity.class, baCustomerType.getId());
		message = "200";
		try{
			List<BaCustomerEntity> ll = systemService.findByQueryString(" from BaCustomerEntity where customerTypeId = '"+baCustomerType.getTypeCode()+"'");
			if(ll.size() > 0){
				message = "300";//类型已经被引用
			}else{
				baCustomerTypeService.delete(baCustomerType);
			}
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "500";
			j.setSuccess(false);
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除客户类型
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "客户类型删除成功";
		try{
			for(String id:ids.split(",")){
				BaCustomerTypeEntity baCustomerType = systemService.getEntity(BaCustomerTypeEntity.class, 
				id
				);
				baCustomerTypeService.delete(baCustomerType);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "客户类型删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加客户类型
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(BaCustomerTypeEntity baCustomerType, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "客户类型添加成功";
		try{
			//校验
			AjaxJson ajaxJson = vailRep(baCustomerType);
			if(!ajaxJson.isSuccess()){
				return ajaxJson;
			}
			if(StringUtil.isNotEmpty(baCustomerType.getParentId())){
				BaCustomerTypeEntity parent = systemService.get(BaCustomerTypeEntity.class, baCustomerType.getParentId());
				if(parent!= null){
					List<BaCustomerEntity> l1 = systemService.findByQueryString(" from BaCustomerEntity where customerTypeId = '"+parent.getTypeCode()+"' "); 
					if(l1.size() > 0){
						j.setMsg("该节点已存在数据，不能添加子节点");
						j.setSuccess(false);
						return j;
					}
				}
			}else{
//				List<BaCustomerTypeEntity> l2 = systemService.findByQueryString(" from BaCustomerTypeEntity");
//				if(l2.size() > 0){
//					j.setMsg("已经存在根节点，不能添加");
//					j.setSuccess(false);
//					return j;
//				}
			}
			
			
			baCustomerTypeService.save(baCustomerType);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "客户类型添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	public AjaxJson vailRep(BaCustomerTypeEntity baCustomerType){
		AjaxJson j = new AjaxJson();
		List<BaCustomerTypeEntity> l1 = systemService.findByQueryString(" from BaCustomerTypeEntity where id != '"+baCustomerType.getId()+"' and typeCode = '"+baCustomerType.getTypeCode()+"'");
		if(l1.size() > 0){
			j.setMsg("分类编码已经存在");
			j.setSuccess(false);
			return j;
		}
		
		List<BaCustomerTypeEntity> l2 = systemService.findByQueryString(" from BaCustomerTypeEntity where id != '"+baCustomerType.getId()+"' and typeName = '"+baCustomerType.getTypeName()+"'");
		if(l2.size() > 0){
			j.setMsg("分类名称已经存在");
			j.setSuccess(false);
			return j;
		}
		
		if(StringUtil.isNotEmpty(baCustomerType.getParentId())){
			BaCustomerTypeEntity lBaSupplierTypeEntity = systemService.getEntity(BaCustomerTypeEntity.class, baCustomerType.getParentId());
			if(!baCustomerType.getTypeCode().startsWith(lBaSupplierTypeEntity.getTypeCode())){
				j.setMsg("分类编码要以父节点编码开始");
				j.setSuccess(false);
				return j;
			}
		}
		return j;
	}
	
	/**
	 * 更新客户类型
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(BaCustomerTypeEntity baCustomerType, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "客户类型更新成功";
		BaCustomerTypeEntity t = baCustomerTypeService.get(BaCustomerTypeEntity.class, baCustomerType.getId());
		try {
			//校验
			AjaxJson ajaxJson = vailRep(baCustomerType);
			if(!ajaxJson.isSuccess()){
				return ajaxJson;
			}
			MyBeanUtils.copyBeanNotNull2Bean(baCustomerType, t);
			baCustomerTypeService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "客户类型更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 客户类型新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(BaCustomerTypeEntity baCustomerType, HttpServletRequest req) {
		String pid =req.getParameter("pid");
		
		if(StringUtils.isNotEmpty(pid)){
			req.setAttribute("pid", pid);
			
			BaCustomerTypeEntity p = systemService.getEntity(BaCustomerTypeEntity.class, pid);
			req.setAttribute("pCode", p.getTypeCode());
		}
		
		if (StringUtil.isNotEmpty(baCustomerType.getId())) {
			baCustomerType = baCustomerTypeService.getEntity(BaCustomerTypeEntity.class, baCustomerType.getId());
			req.setAttribute("baCustomerTypePage", baCustomerType);
		}
		//编码规则
		String strcode="";
		TSFunction function = systemService.getEntity(TSFunction.class, "4028819064fd57d30164fd5fc8900001");
		if(VcodeplanUtil.ifvcode(function.getId())){
			strcode=VcodeplanUtil.strvcode(function.getId());
		}
		req.setAttribute("strcode", strcode);
		return new ModelAndView("cn/com/linkwide/ba/bacustomertype/baCustomerType-add");
	}
	/**
	 * 客户类型编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(BaCustomerTypeEntity baCustomerType, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(baCustomerType.getId())) {
			baCustomerType = baCustomerTypeService.getEntity(BaCustomerTypeEntity.class, baCustomerType.getId());
			req.setAttribute("baCustomerTypePage", baCustomerType);
		}
		//编码规则
		String strcode="";
		TSFunction function = systemService.getEntity(TSFunction.class, "4028819064fd57d30164fd5fc8900001");
		if(VcodeplanUtil.ifvcode(function.getId())){
			strcode=VcodeplanUtil.strvcode(function.getId());
		}
		req.setAttribute("strcode", strcode);
		return new ModelAndView("cn/com/linkwide/ba/bacustomertype/baCustomerType-update");
	}
	
	/**
	 * 客户类型查看页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goDetail")
	public ModelAndView goDetail(BaCustomerTypeEntity baCustomerType, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(baCustomerType.getId())) {
			baCustomerType = baCustomerTypeService.getEntity(BaCustomerTypeEntity.class, baCustomerType.getId());
			req.setAttribute("baCustomerTypePage", baCustomerType);
		}
		//编码规则
		String strcode="";
		TSFunction function = systemService.getEntity(TSFunction.class, "4028819064fd57d30164fd5fc8900001");
		if(VcodeplanUtil.ifvcode(function.getId())){
			strcode=VcodeplanUtil.strvcode(function.getId());
		}
		req.setAttribute("strcode", strcode);
		return new ModelAndView("cn/com/linkwide/ba/bacustomertype/baCustomerType-detail");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","baCustomerTypeController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(BaCustomerTypeEntity baCustomerType,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(BaCustomerTypeEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, baCustomerType, request.getParameterMap());
		List<BaCustomerTypeEntity> baCustomerTypes = this.baCustomerTypeService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"客户类型");
		modelMap.put(NormalExcelConstants.CLASS,BaCustomerTypeEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("客户类型列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,baCustomerTypes);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(BaCustomerTypeEntity baCustomerType,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"客户类型");
    	modelMap.put(NormalExcelConstants.CLASS,BaCustomerTypeEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("客户类型列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<BaCustomerTypeEntity> listBaCustomerTypeEntitys = ExcelImportUtil.importExcel(file.getInputStream(),BaCustomerTypeEntity.class,params);
				for (BaCustomerTypeEntity baCustomerType : listBaCustomerTypeEntitys) {
					baCustomerTypeService.save(baCustomerType);
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
	@ApiOperation(value="客户类型列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<BaCustomerTypeEntity>> list() {
		List<BaCustomerTypeEntity> listBaCustomerTypes=baCustomerTypeService.getList(BaCustomerTypeEntity.class);
		return Result.success(listBaCustomerTypes);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取客户类型信息",notes="根据ID获取客户类型信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		BaCustomerTypeEntity task = baCustomerTypeService.get(BaCustomerTypeEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取客户类型信息为空");
		}
		return Result.success(task);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建客户类型")
	public ResponseMessage<?> create(@ApiParam(name="客户类型对象")@RequestBody BaCustomerTypeEntity baCustomerType, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<BaCustomerTypeEntity>> failures = validator.validate(baCustomerType);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			baCustomerTypeService.save(baCustomerType);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("客户类型信息保存失败");
		}
		return Result.success(baCustomerType);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新客户类型",notes="更新客户类型")
	public ResponseMessage<?> update(@ApiParam(name="客户类型对象")@RequestBody BaCustomerTypeEntity baCustomerType) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<BaCustomerTypeEntity>> failures = validator.validate(baCustomerType);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			baCustomerTypeService.saveOrUpdate(baCustomerType);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("更新客户类型信息失败");
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success("更新客户类型信息成功");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除客户类型")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" + id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			baCustomerTypeService.deleteEntityById(BaCustomerTypeEntity.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("客户类型删除失败");
		}

		return Result.success();
	}
	
	/**
	 * 更新客户类型
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "checkState")
	@ResponseBody
	public AjaxJson checkState(BaCustomerTypeEntity baCustomerType, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "200";//修改成功
		BaCustomerTypeEntity t = systemService.get(BaCustomerTypeEntity.class, baCustomerType.getId());
		try {
			boolean flog = true;
			if("1".equals(baCustomerType.getStatus())){
				//该节点是否有子节点
				List<BaCustomerTypeEntity> l = systemService.findByQueryString(" from BaCustomerTypeEntity where parentId = '"+t.getId()+"' and status = '0'");
				//该节点是否被引用
				List<BaCustomerEntity> ll = systemService.findByQueryString(" from BaCustomerEntity where customerTypeId = '"+baCustomerType.getId()+"'");
				if(l.size() > 0 || ll.size() > 0){
					flog = false;
					message = "300";//有不停用的子节点
				}
				
			}else if("0".equals(baCustomerType.getStatus())){
				if(StringUtil.isNotEmpty(t.getParentId())){
					BaCustomerTypeEntity baCustomerTypeEntity = systemService.getEntity(BaCustomerTypeEntity.class, t.getParentId());
					if("1".equals(baCustomerTypeEntity.getStatus())){
						flog = false;
						message = "400";//父級是停用，子級不能啟用
					}
				}
				
			}
			
			if(flog){
				t.setStatus(baCustomerType.getStatus());
				systemService.saveOrUpdate(t);
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
	 * 加载未停用的客户类型树
	 * @param request
	 * @return
	 */
	@RequestMapping("/getTreeAllDateForstate")
	@ResponseBody
	public List<ComboTree> getTreeAllDateForstate(HttpServletRequest request) {
//		//性能考虑。查询出所有的数据到缓存，然后进行遍历处理
		CriteriaQuery cq = new CriteriaQuery(BaCustomerTypeEntity.class);
		//是否停用0:否1:是
		cq.eq("status", "0");
		cq.addOrder("typeCode", SortDirection.asc);
		cq.add();
		List<BaCustomerTypeEntity> baCustomerTypeEntityList = systemService.getListByCriteriaQuery(cq, false);

		List<ComboTree> comboTrees = new ArrayList<ComboTree>();
		for (BaCustomerTypeEntity baCustomerTypeEntity : baCustomerTypeEntityList) {
			
			//判断是否一级节点,并且未停用的数据 
			if(baCustomerTypeEntity.getParentId() == null || baCustomerTypeEntity.getParentId().equals("")){
				comboTrees.add(customerTypeConvertToTree(baCustomerTypeEntity,baCustomerTypeEntityList));
			}
		}
		return comboTrees;
	}
	
	
	private ComboTree customerTypeConvertToTree(BaCustomerTypeEntity entity,List<BaCustomerTypeEntity> BaCustomerTypeEntityList) {
		ComboTree tree = new ComboTree();
		tree.setId(entity.getTypeCode());
		tree.setText(entity.getTypeName());

		//查询下级节点，过滤掉停用的数据 是否停用0:否1:是
		List<ComboTree> comboTrees = new ArrayList<ComboTree>();
		for (BaCustomerTypeEntity BaCustomerTypeEntity : BaCustomerTypeEntityList) {
			 if(StringUtil.isNotEmpty(BaCustomerTypeEntity.getParentId())){
			 if(BaCustomerTypeEntity.getParentId().equals(entity.getId()) ){
				 if(StringUtil.isNotEmpty(comboTrees)){
					 comboTrees.add(customerTypeConvertToTree(BaCustomerTypeEntity,BaCustomerTypeEntityList)); 
				 }
			 }
			 }
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", entity.getStatus());
		map.put("isLast", entity.getExtend1());
		tree.setAttributes(map);
		tree.setChildren(comboTrees);

		return tree;
	}
}
