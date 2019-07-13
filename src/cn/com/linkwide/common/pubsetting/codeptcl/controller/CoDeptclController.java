package cn.com.linkwide.common.pubsetting.codeptcl.controller;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

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
import org.jeecgframework.core.util.ListUtils;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.tag.core.easyui.TagUtil;
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

import com.alibaba.fastjson.JSONObject;

import cn.com.linkwide.common.pubsetting.codeptcl.entity.CoDeptclEntity;
import cn.com.linkwide.common.pubsetting.codeptcl.service.CoDeptclServiceI;
import cn.com.linkwide.common.tools.VcodeplanUtil;

/**   
 * @Title: Controller  
 * @Description: 科室分类
 * @author onlineGenerator
 * @date 2017-08-30 09:14:21
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/coDeptclController")
public class CoDeptclController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(CoDeptclController.class);

	@Autowired
	private CoDeptclServiceI coDeptclService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 科室分类列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("cn/com/linkwide/cost/pubsetting/codeptcl/coDeptclList");
	}
	

	/**
	 * 父级DEMO下拉菜单
	 */
//	@RequestMapping(params = "pDemoList")
//	@ResponseBody
//	public List<ComboTree> pDemoList(HttpServletRequest request, ComboTree comboTree) {
//		CriteriaQuery cq = new CriteriaQuery(CoTreeTestEntity.class);
//		if (comboTree.getId() != null) {
//			cq.eq("coDeptclEntity.id", comboTree.getId());
//		}
//		if (comboTree.getId() == null) {
//			cq.isNull("coDeptclEntity");
//		}
//		cq.add();
////		List<CoTreeTestEntity> demoList = systemService.getListByCriteriaQuery(cq, false);
//		List<CoTreeTestEntity> demoList=coDeptclService.getList(CoTreeTestEntity.class);
//		List<ComboTree> comboTrees = new ArrayList<ComboTree>();
//		ComboTreeModel comboTreeModel = new ComboTreeModel("id", "vcode", "coDeptclEntity", "vcode");
//		comboTrees = systemService.ComboTree(demoList, comboTreeModel, null, false);
//		return comboTrees;
//	}

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(CoDeptclEntity coDeptcl,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(CoDeptclEntity.class, dataGrid);
		//查询条件组装器
		if(StringUtil.isNotEmpty(coDeptcl.getVcode())){
			coDeptcl.setVcode("*"+coDeptcl.getVcode()+"*");
		}
		if(StringUtil.isNotEmpty(coDeptcl.getVname())){
			coDeptcl.setVname("*"+coDeptcl.getVname()+"*");
		}
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, coDeptcl, request.getParameterMap());
		try{
		//自定义追加查询条件
			String id = request.getParameter("parentid");
			if(id!=null){
				CoDeptclEntity co=coDeptclService.getEntity(CoDeptclEntity.class, id);
				if(co!=null){
					cq.like("vcode",co.getVcode()+"%");
				}
			}
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.coDeptclService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除科室分类
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(CoDeptclEntity coDeptcl, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		String sql="select distinct pk_deptcl from co_costdepart";
		List<String> deptcl=systemService.findListbySql(sql);
		try {
			if(deptcl.size()>0){
				if(deptcl.contains(coDeptcl.getId())){
					message = "数据已被引用，无法删除！";
					j.setMsg(message);
					return j;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	    List<CoDeptclEntity> list=coDeptclService.getList(CoDeptclEntity.class);
		if(list.size()>0){
			for(CoDeptclEntity co :list){
		    	String pkparent=co.getVdef1()!=null?co.getVdef1():"";
		    	if(pkparent.equals(coDeptcl.getId())){
		    		message = "请先删除末级！";
					j.setMsg(message);
					return j;
		    	}
		    }	
		}
		coDeptcl = systemService.getEntity(CoDeptclEntity.class, coDeptcl.getId());
		if(coDeptcl.getVcode().equals("01")||coDeptcl.getVcode().equals("02")){
			message = "预置数据，不能删除！";
			j.setMsg(message);
			return j;
		}
		message = "科室分类删除成功";
		try{
			coDeptclService.delete(coDeptcl);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "科室分类删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除科室分类
	 * 
	 * @return
	 */
	@RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "科室分类删除成功";
		String sql="select distinct pk_deptcl from co_costdepart";
		List<String> deptcl=systemService.findListbySql(sql);
		int size=deptcl.size();
		try{
			for(String id:ids.split(",")){
				CoDeptclEntity coDeptcl = systemService.getEntity(CoDeptclEntity.class, 
				id
				);
				if(coDeptcl.getVcode().equals("01")||coDeptcl.getVcode().equals("02")){
					message = "存在预置数据，不能删除！";
					j.setMsg(message);
					return j;
				}
				if(size>0){
					if(deptcl.contains(coDeptcl.getId())){
						message = "存在已被引用的数据，无法批量删除！";
						j.setMsg(message);
						return j;
					}
				}
			}
			for(String id:ids.split(",")){
				CoDeptclEntity coDeptcl = systemService.getEntity(CoDeptclEntity.class, 
				id
				);
				coDeptclService.delete(coDeptcl);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "科室分类删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加科室分类
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(CoDeptclEntity coDeptcl, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "科室分类添加成功";
		try{
			/*
			 *  zhuhd
			 *  唯一校验
			 */
			//判断Vcode是否存在
			CoDeptclEntity uniqueByVcode = coDeptclService.findUniqueByProperty(CoDeptclEntity.class, "vcode", coDeptcl.getVcode());
			if(uniqueByVcode!=null){
				message = "科室分类编码"+"'"+coDeptcl.getVcode()+"'"+"已经存在";
				throw new BusinessException(message);
			}
			//判断vName是否存在
//			CoDeptclEntity uniqueByVname = coDeptclService.findUniqueByProperty(CoDeptclEntity.class, "vname", coDeptcl.getVname());
//			if(uniqueByVname!=null){
//				message = "科室分类名称"+"'"+coDeptcl.getVname()+"'"+"已经存在";
//				throw new BusinessException(message);
//			}
			
			//科室等级
			if(StringUtil.isEmpty(coDeptcl.getVdef1())){
				coDeptcl.setVdef3("1");
			}else{
				CoDeptclEntity co=coDeptclService.getEntity(CoDeptclEntity.class, coDeptcl.getVdef1());
				coDeptcl.setVdef3(String.valueOf(Integer.valueOf(co.getVdef3())+1));
			}
			coDeptclService.save(coDeptcl);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(BusinessException b){
				message="添加失败："+b.getMessage();
		}catch(Exception e){
			e.printStackTrace();
			message = "科室分类添加失败";
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新科室分类
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(CoDeptclEntity coDeptcl, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "科室分类更新成功";
		CoDeptclEntity t = coDeptclService.get(CoDeptclEntity.class, coDeptcl.getId());
		try {
			CoDeptclEntity uniqueByVcode = coDeptclService.findUniqueByProperty(CoDeptclEntity.class, "vcode", coDeptcl.getVcode());
			if(uniqueByVcode!=null){
				if(!uniqueByVcode.getId().equals(coDeptcl.getId())){
					message = "科室分类编码"+"'"+coDeptcl.getVcode()+"'"+"已经存在";
					throw new BusinessException(message);
				}	
			}
			//判断vName是否存在
			CoDeptclEntity uniqueByVname = coDeptclService.findUniqueByProperty(CoDeptclEntity.class, "vname", coDeptcl.getVname());
			if(uniqueByVname!=null){
				if(!uniqueByVname.getId().equals(coDeptcl.getId())){
					message = "科室分类名称"+"'"+coDeptcl.getVname()+"'"+"已经存在";
					throw new BusinessException(message);
				}	
			}
			MyBeanUtils.copyBeanNotNull2Bean(coDeptcl, t);
			coDeptclService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		}catch(BusinessException b){
			message="更新失败："+b.getMessage();
	    }catch (Exception e) {
			e.printStackTrace();
			message = "科室分类更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 科室分类新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(CoDeptclEntity coDeptcl, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(coDeptcl.getId())) {
			coDeptcl = coDeptclService.getEntity(CoDeptclEntity.class, coDeptcl.getId());
			req.setAttribute("coDeptclPage", coDeptcl);
		}
		String strcode="";
		int level=1;
		String parentcode="";
		TSFunction function = systemService.getEntity(TSFunction.class, "4028818e5f4c5dd8015f4c8405dc000a");
		if(VcodeplanUtil.ifvcode(function.getId())){
			strcode=VcodeplanUtil.strvcode(function.getId());
		}
		String parentid=req.getParameter("parentid");
//		String parentname=req.getParameter("parentname");
//		try {
//			parentname=new String(parentname.getBytes("iso-8859-1"),"utf-8");
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
		if(StringUtil.isNotEmpty(parentid)){
			CoDeptclEntity co=coDeptclService.getEntity(CoDeptclEntity.class, parentid);
			parentcode=co.getVcode();
			level=Integer.valueOf(co.getVdef3())+1;
		}
		req.setAttribute("parentid", parentid);
		req.setAttribute("strcode", strcode);
		req.setAttribute("parentcode", parentcode);
		req.setAttribute("level", level);
		return new ModelAndView("cn/com/linkwide/cost/pubsetting/codeptcl/coDeptcl-add");
	}
	/**
	 * 科室分类编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(CoDeptclEntity coDeptcl, HttpServletRequest req) {
		ModelAndView view=new ModelAndView("cn/com/linkwide/cost/pubsetting/codeptcl/coDeptcl-update");
		if (StringUtil.isNotEmpty(coDeptcl.getId())) {
			coDeptcl = coDeptclService.getEntity(CoDeptclEntity.class, coDeptcl.getId());
			req.setAttribute("coDeptclPage", coDeptcl);
		}
		String sql="select distinct pk_deptcl from co_costdepart";
		List<String> deptcl=systemService.findListbySql(sql);
		try {
			if(deptcl.size()>0){
				if(deptcl.contains(coDeptcl.getId())){
					view=new ModelAndView("cn/com/linkwide/cost/pubsetting/cocostdeptbas/coCostdeptbas-check");
					return view;
				}
			}
			if(coDeptcl.getVcode().equals("01")||coDeptcl.getVcode().equals("02")){
				view=new ModelAndView("cn/com/linkwide/cost/pubsetting/cocostdeptbas/coCostdeptbas-check4");
				return view;
			}
			String strcode="";
			TSFunction function = systemService.getEntity(TSFunction.class, "4028818e5f4c5dd8015f4c8405dc000a");
			if(VcodeplanUtil.ifvcode(function.getId())){
				strcode=VcodeplanUtil.strvcode(function.getId());
			}
			req.setAttribute("strcode", strcode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return view;
	}
	
	@RequestMapping(params = "goDetail")
	public ModelAndView goDetail(CoDeptclEntity coDeptcl, HttpServletRequest req) {
		ModelAndView view=new ModelAndView("cn/com/linkwide/cost/pubsetting/codeptcl/coDeptcl-update");
		if (StringUtil.isNotEmpty(coDeptcl.getId())) {
			coDeptcl = coDeptclService.getEntity(CoDeptclEntity.class, coDeptcl.getId());
			req.setAttribute("coDeptclPage", coDeptcl);
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
		req.setAttribute("controller_name","coDeptclController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(CoDeptclEntity coDeptcl,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(CoDeptclEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, coDeptcl, request.getParameterMap());
		List<CoDeptclEntity> coDeptcls = this.coDeptclService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"科室分类");
		modelMap.put(NormalExcelConstants.CLASS,CoDeptclEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("科室分类列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,coDeptcls);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(CoDeptclEntity coDeptcl,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"科室分类");
    	modelMap.put(NormalExcelConstants.CLASS,CoDeptclEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("科室分类列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<CoDeptclEntity> listCoDeptclEntitys = ExcelImportUtil.importExcel(file.getInputStream(),CoDeptclEntity.class,params);
				List<CoDeptclEntity> listCoDeptcl =new ArrayList<CoDeptclEntity>();
				if(listCoDeptclEntitys.size()==0){
					throw new BusinessException("导入数据为空，导入失败");
				}
				for (CoDeptclEntity coDeptcl : listCoDeptclEntitys) {
					if(StringUtil.isEmpty(coDeptcl.getVcode())){
						throw new BusinessException("科室分类编码不能为空");
					}
					if(Pattern.compile("[\u4e00-\u9fa5]").matcher(coDeptcl.getVcode()).find()){
						throw new BusinessException("科室分类编码不能为中文");
					}
					//判断Vcode是否存在
					CoDeptclEntity uniqueByVcode = coDeptclService.findUniqueByProperty(CoDeptclEntity.class, "vcode", coDeptcl.getVcode());
					if(uniqueByVcode!=null){
						String message = "科室分类编码"+"'"+coDeptcl.getVcode()+"'"+"已经存在";
						throw new BusinessException(message);
					}
					
					if(StringUtil.isEmpty(coDeptcl.getVname())){
						throw new BusinessException("科室分类名称不能为空");
					}
					coDeptcl.setVcode(StringUtil.removeTrim(coDeptcl.getVcode()));
					coDeptcl.setVdef2(StringUtil.removeTrim(coDeptcl.getVdef2()));
					//判断vName是否存在
//					CoDeptclEntity uniqueByVname = coDeptclService.findUniqueByProperty(CoDeptclEntity.class, "vname", coDeptcl.getVname());
//					if(uniqueByVname!=null){
//						String message = "科室分类名称"+"'"+coDeptcl.getVname()+"'"+"已经存在";
//						throw new BusinessException(message);
//					}
//					coDeptclService.save(coDeptcl);
					listCoDeptcl.add(coDeptcl);
				}
				List<Object> extractProperty = ListUtils.extractProperty(listCoDeptcl, "vcode");
				if(ListUtils.isRepeat(extractProperty)){
					throw new BusinessException("科室分类编码存在重复，导入失败");
				}
				coDeptclService.batchSave(listCoDeptcl);
				List<CoDeptclEntity> list = coDeptclService.getList(CoDeptclEntity.class);
				for (CoDeptclEntity coDeptcl : list) {
					if(StringUtil.isNotEmpty(coDeptcl.getVdef2())){
						CoDeptclEntity co=coDeptclService.findUniqueByProperty(CoDeptclEntity.class,"vcode",coDeptcl.getVdef2());
						if(co!=null){
							coDeptcl.setVdef1(co.getId());
							coDeptclService.updateEntitie(coDeptcl);
						}else{
							throw new BusinessException("导入成功，但没有找到上级为"+coDeptcl.getVdef2()+"的记录");
						}
					}
				}
				addDeptLevel();
				j.setMsg("文件导入成功！");
			} catch (BusinessException b) {
				j.setMsg(b.getMessage());
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
	 * 
	 * 添加级次字段
	 */
	public void addDeptLevel(){
		int j=-1;
		do{
		String sql="update co_deptcl set vdef3='1' where vdef1='' or vdef1 is null";
		j = coDeptclService.updateBySqlString(sql);
		}while(j<0);
		String sql1="select id,vdef3  from co_deptcl where vdef1='' or  vdef1 is null";
		//查询所有一级科室集合
		List<Object> list = coDeptclService.findListbySql(sql1);
		if(list!=null&&list.size()>0){
			 addDeptChildLevel(list);
		}
		
	}
	
	/**
	 * 根据ID加载子集
	 * @param costdeptbasList
	 */
	public void  addDeptChildLevel(List<Object> list){
		for(Object obj:list){
			Object [] objArr=(Object[]) obj;
			//科室id
			String Id=objArr[0].toString();
			String sql1="select count(*) from co_deptcl where vdef1='"+Id+"'";
			//根据父级id查询子集数量
			Long counts = coDeptclService.getCountForJdbc(sql1);
			if(counts>0){
				int i=2;
				//科室级次
				String vdef3 = objArr[1].toString();
				if(StringUtil.isNotEmpty(vdef3)){
					i= Integer.parseInt(vdef3)+1;
				}
				String sql2="update co_deptcl set vdef3='"+i+"' where vdef1='"+Id+"'";
				coDeptclService.updateBySqlString(sql2);
				 
				 //根据父级id查询子集
				 String sql3="select id,vdef3  from co_deptcl where vdef1='"+Id+"'";
				 
				 List<Object> childenList = coDeptclService.findListbySql(sql3);
				 addDeptChildLevel(childenList);
			}
		}
	}
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<CoDeptclEntity> list() {
		List<CoDeptclEntity> listCoDeptcls=coDeptclService.getList(CoDeptclEntity.class);
		return listCoDeptcls;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		CoDeptclEntity task = coDeptclService.get(CoDeptclEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody CoDeptclEntity coDeptcl, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<CoDeptclEntity>> failures = validator.validate(coDeptcl);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		try{
			coDeptclService.save(coDeptcl);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = coDeptcl.getId();
		URI uri = uriBuilder.path("/rest/coDeptclController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody CoDeptclEntity coDeptcl) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<CoDeptclEntity>> failures = validator.validate(coDeptcl);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		try{
			coDeptclService.saveOrUpdate(coDeptcl);
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
		coDeptclService.deleteEntityById(CoDeptclEntity.class, id);
	}
	
	/**
	 *查询拼接科室分类树
	 *@author chencp
	 *@date 2017年8月31日
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "deptclTree")
	@ResponseBody
	public List<ComboTree> deptclTree(HttpServletRequest request) {
		List<ComboTree> rootLists=new ArrayList<ComboTree>();
		//查询上级科室为空的所有科室
		String firstHql="from CoDeptclEntity a where a.vdef1 IS NULL or a.vdef1 ='' order by a.vcode";
		List<CoDeptclEntity> firstList = coDeptclService.findHql(firstHql);
		if(firstList!=null&&firstList.size()>0){
			//将所有 科室拼成科室树
			ComboTree comT=new ComboTree();
			ComboTree comboTree = loadAllChildrenTree(firstList, comT);
			comboTree.setId("");
			comboTree.setText("科室分类");
			comboTree.setState("opend");
			rootLists.add(comboTree);
		}
		return rootLists;
//		List<?> list = coDeptclService.getList(CoDeptclEntity.class);
//		List<Map<String, String>> listMap=new ArrayList<Map<String, String>>();
//		for(Object obj : list){
//			Map<String, String> map = new HashMap<String, String>();
//			String id = ((CoDeptclEntity) obj).getId();
//			String name = ((CoDeptclEntity) obj).getVname();
//			if(!map.containsKey(id)){
//				map.put(id, name);
//			}
//			listMap.add(map);
//		}
//		List<ComboTree> rootList=PubToolUtil.deptclTree(listMap) ;
//		return rootList;
	}
	public ComboTree loadAllChildrenTree(List<CoDeptclEntity> list,ComboTree combotree){
		if(list!=null&&list.size()>0){
			List<ComboTree> comTreeList=new ArrayList<ComboTree>();
			for(CoDeptclEntity costdept:list){
				ComboTree com=new ComboTree();
				com.setId(costdept.getId());
				com.setText(costdept.getVname());
				//查询所有下级科室
				String hql="from CoDeptclEntity a where a.vdef1= ? order by a.vcode";
				List<CoDeptclEntity> coCostdeptList = coDeptclService.findHql(hql,costdept.getId());
				if(coCostdeptList.size()>0){
					com.setState("closed");
					loadAllChildrenTree(coCostdeptList, com);
				}else{
					com.setState("open");
				}
				comTreeList.add(com);
				combotree.setChildren(comTreeList);
			}
		}
		return combotree;
	}
	
	/**
	 * 科室分类模糊查询
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
		String hql="from CoDeptclEntity where vname like '%"+q+"%' or vcode like '%"+q+"%' ";
		List<CoDeptclEntity> list=systemService.findHql(hql);
		json = JSONObject.toJSON(list);
		//str = json.toString();
		return json;
	}
	

	
	
	/**
	 * 
	 *@author zhuhd
	 *@date 2017年9月11日
	 * @param list
	 * @param rootList
	 * @return
	 */
/*	public ComboTree loadAllChildrenTree(List<CoDeptclEntity> list,ComboTree combotree){
		if(list!=null&&list.size()>0){
			List<ComboTree> comTreeList=new ArrayList<ComboTree>();
			for(CoDeptclEntity costdept:list){
				ComboTree com=new ComboTree();
				com.setId(costdept.getId());
				com.setText(costdept.getVname());
				//查询所有下级科室
				String hql="select distinct a from coDeptclEntity a where a.pkParent= ?";
				List<CoDeptclEntity> coCostdeptList = coDeptclService.findHql(hql,costdept.getVname());
				if(coCostdeptList.size()>0){
					com.setState("closed");
					loadAllChildrenTree(coCostdeptList, com);
				}else{
					com.setState("open");
				}
				comTreeList.add(com);
				combotree.setChildren(comTreeList);
			}
		}
		return combotree;
	}*/
}
