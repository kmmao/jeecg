package org.jeecgframework.web.system.company.controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.PasswordUtil;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.company.entity.TSCompany;
import org.jeecgframework.web.system.company.service.TSCompanyServiceI;
import org.jeecgframework.web.system.dao.DepartAuthGroupDao;
import org.jeecgframework.web.system.manager.ClientManager;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.pojo.base.TSRole;
import org.jeecgframework.web.system.pojo.base.TSRoleUser;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.web.system.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.mchange.v1.util.ListUtils;


/**   
 * @Title: Controller  
 * @Description: 单位
 * @author onlineGenerator
 * @date 2019-05-23 16:08:19
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/tSCompanyController")
public class TSCompanyController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(TSCompanyController.class);

	@Autowired
	private TSCompanyServiceI tSCompanyService;
	@Autowired
	private SystemService systemService;
	
	private UserService userService;   
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}


	/**
	 * 单位列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("org/jeecgframework/web/system/company/tSCompanyList");
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
	public void datagrid(TSCompany tSCompany,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TSCompany.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, tSCompany, request.getParameterMap());
		try{
			//自定义追加查询条件
			if(StringUtil.isNotEmpty(ResourceUtil.getSessionUser().getSysCompanyCode())){
				cq.eq("sysCompanyCode", ResourceUtil.getSessionUser().getSysCompanyCode());
			}
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.tSCompanyService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除单位
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(TSCompany tSCompany, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		tSCompany = systemService.getEntity(TSCompany.class, tSCompany.getId());
		message = "单位删除成功";
		try{
			List<TSCompany> l = systemService.findByProperty(TSCompany.class, "parentId", tSCompany.getId());
			if(!org.jeecgframework.core.util.ListUtils.isNullOrEmpty(l) && l.size() >0){
				j.setSuccess(false);
				j.setMsg("不能直接删除父级单位!");
				return j;
			}
			//删除单位管理员
			String userName = tSCompany.getAdministrator();
			TSUser user = systemService.findUniqueByProperty(TSUser.class, "userName",userName);
			if (user != null) {
				// 同步删除用户角色关联表
				List<TSRoleUser> roleUserList = systemService.findByProperty(TSRoleUser.class, "TSUser.id", user.getId());
				if (roleUserList.size() >= 1) {
					for (TSRoleUser tRoleUser : roleUserList) {
						systemService.delete(tRoleUser);
					}
				}
				systemService.delete(user);
			}
			//删除单位管理员角色
			TSRole role = systemService.findUniqueByProperty(TSRole.class, "roleCode", userName);
			if(role != null){
				systemService.delete(role);
			}
			//删除科室
			List<TSDepart> departs = systemService.findHql(" from TSDepart where orgCode = ? and sysCompanyCode = ? ", tSCompany.getCompanyCode(),tSCompany.getCompanyCode());
			if(departs != null  && departs.size() >0){
				TSDepart d = departs.get(0);
				systemService.delete(d);
			}
			tSCompanyService.delete(tSCompany);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "单位删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除单位
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "单位删除成功";
		try{
			for(String id:ids.split(",")){
				TSCompany tSCompany = systemService.getEntity(TSCompany.class, 
				id
				);
				tSCompanyService.delete(tSCompany);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "单位删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加单位
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(TSCompany tSCompany, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "单位添加成功";
		try{
			if(StringUtil.isNotEmpty(tSCompany.getProvince())){
				tSCompany.setProvince(tSCompany.getProvince().split(",")[tSCompany.getProvince().split(",").length-1]);
			}
			if(StringUtil.isNotEmpty(tSCompany.getCity())){
				tSCompany.setCity(tSCompany.getCity().split(",")[tSCompany.getCity().split(",").length-1]);
			}
			if(StringUtil.isNotEmpty(tSCompany.getArea())){
				tSCompany.setArea(tSCompany.getArea().split(",")[tSCompany.getArea().split(",").length-1]);
			}
			tSCompanyService.save(tSCompany);
			//创建科室
			TSDepart depart = new TSDepart();
			depart.setOrgCode(tSCompany.getCompanyCode());
			depart.setDepartname(tSCompany.getCompanyName());
			depart.setSysCompanyCode(tSCompany.getCompanyCode());
			depart.setOrgType("1");
			systemService.save(depart);
			String userName = tSCompany.getAdministrator();
			//创建单位理员权限
			TSRole  role =  new TSRole();
			role.setRoleCode(userName);
			role.setRoleName("单位"+tSCompany.getCompanyCode()+"管理员");
			role.setSysCompanyCode(tSCompany.getCompanyCode());
			role.setRoleType("0");
			systemService.save(role);
			//创建单位管理员
			TSUser users = systemService.findUniqueByProperty(TSUser.class, "userName",userName);
			TSUser user = new TSUser();
			if (users != null) {
				message = "用户: " + users.getUserName() + "已经存在";
			} else {
				user.setUserName(userName);
				user.setRealName("单位"+tSCompany.getCompanyCode()+"管理员");
				user.setSysCompanyCode(tSCompany.getCompanyCode());
				user.setDepartid(depart.getId());
				user.setDevFlag("0");
				user.setPassword(PasswordUtil.encrypt(user.getUserName(), oConvertUtils.getString("123456"), PasswordUtil.getStaticSalt()));
				user.setStatus(Globals.User_Normal);
				user.setDeleteFlag(Globals.Delete_Normal);
				//默认添加为系统用户
				user.setUserType(Globals.USER_TYPE_SYSTEM);
				this.userService.saveOrUpdate(user, depart.getId().split(","), role.getId().split(","));				
				message = "用户: " + user.getUserName() + "添加成功";
			}
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "单位添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新单位
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(TSCompany tSCompany, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "单位更新成功";
		TSCompany t = tSCompanyService.get(TSCompany.class, tSCompany.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(tSCompany, t);
			if(StringUtil.isNotEmpty(t.getProvince())){
				t.setProvince(t.getProvince().split(",")[t.getProvince().split(",").length-1]);
			}
			if(StringUtil.isNotEmpty(t.getCity())){
				t.setCity(t.getCity().split(",")[t.getCity().split(",").length-1]);
			}
			if(StringUtil.isNotEmpty(t.getArea())){
				t.setArea(t.getArea().split(",")[t.getArea().split(",").length-1]);
			}
			TSCompany parent = tSCompanyService.get(TSCompany.class, t.getParentId());
			if(parent != null && t.getId().equals(parent.getParentId())){
				j.setSuccess(false);
				j.setMsg("不能互为上级单位!");
				return j;
			}
			
			tSCompanyService.saveOrUpdate(t);
			List<TSDepart> departs = systemService.findHql(" from TSDepart where orgCode = sysCompanyCode and orgCode = ? ", t.getCompanyCode());
			if(departs != null  && departs.size() >0){
				TSDepart d = departs.get(0);
				d.setDepartname(t.getCompanyName());
				systemService.save(d);
			}
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "单位更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 单位新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(TSCompany tSCompany, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(tSCompany.getId())) {
			tSCompany = tSCompanyService.getEntity(TSCompany.class, tSCompany.getId());
			req.setAttribute("tSCompanyPage", tSCompany);
		}
		return new ModelAndView("org/jeecgframework/web/system/company/tSCompany-add");
	}
	/**
	 * 单位编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(TSCompany tSCompany, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(tSCompany.getId())) {
			tSCompany = tSCompanyService.getEntity(TSCompany.class, tSCompany.getId());
			req.setAttribute("tSCompanyPage", tSCompany);
		}
		return new ModelAndView("org/jeecgframework/web/system/company/tSCompany-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","tSCompanyController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(TSCompany tSCompany,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(TSCompany.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, tSCompany, request.getParameterMap());
		List<TSCompany> tSCompanys = this.tSCompanyService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"单位");
		modelMap.put(NormalExcelConstants.CLASS,TSCompany.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("单位列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,tSCompanys);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(TSCompany tSCompany,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"单位");
    	modelMap.put(NormalExcelConstants.CLASS,TSCompany.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("单位列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<TSCompany> listTSCompanys = ExcelImportUtil.importExcel(file.getInputStream(),TSCompany.class,params);
				for (TSCompany tSCompany : listTSCompanys) {
					tSCompanyService.save(tSCompany);
				}
				j.setMsg("文件导入成功！");
			} catch (Exception e) {
				j.setMsg("文件导入失败！");
				logger.error(e.getMessage());
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
	
	@RequestMapping(params="getTreeData",method ={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public AjaxJson getTreeDemoData(TSCompany company,HttpServletResponse response,HttpServletRequest request ){
		AjaxJson j = new AjaxJson();
		try{
			List<TSCompany> companyList = new ArrayList<TSCompany>();
			StringBuffer hql = new StringBuffer(" from TSCompany t where 1=1 ");
			if(StringUtil.isNotEmpty(ResourceUtil.getSessionUser().getSysCompanyCode())){
				hql.append(" and t.companyCode = '"+ResourceUtil.getSessionUser().getSysCompanyCode()+"' " );
			}
			companyList = this.systemService.findHql(hql.toString());
			List<Map<String,Object>> dataList = new ArrayList<Map<String,Object>>();
			Map<String,Object> map = null;
			for (TSCompany tscompany : companyList) {
				map = new HashMap<String,Object>();
				map.put("chkDisabled",false);
				map.put("click", true);
				map.put("id", tscompany.getId());
				map.put("name", tscompany.getCompanyName());
				map.put("nocheck", false);
				map.put("struct","TREE");
				map.put("title",tscompany.getCompanyName());
				if (tscompany.getParentId() != null) {
					map.put("parentId",tscompany.getParentId());
				}else {
					map.put("parentId","0");
				}
				dataList.add(map);
			}
			if(dataList.isEmpty()){
				Map<String,Object> m  = new HashMap<String,Object>();
					m.put("chkDisabled",false);
					m.put("click", true);
					m.put("id", "");
					m.put("name", "单位维护");
					m.put("nocheck", false);
					m.put("struct","TREE");
					m.put("title","单位维护");
					m.put("parentId","0");
					dataList.add(m);
			}
		j.setObj(dataList);
		}catch(Exception e){
			e.printStackTrace();
		}
		return j;
	}
}
