package cn.com.linkwide.ba.baacctsubj.controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.criterion.Restrictions;
import org.jeecgframework.core.beanvalidator.BeanValidators;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.DynamicDBUtil;
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

import cn.com.linkwide.ba.baacctsubj.entity.BaAcctSubjEntity;
import cn.com.linkwide.ba.baacctsubj.service.BaAcctSubjServiceI;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**   
 * @Title: Controller  
 * @Description: 会计科目
 * @author onlineGenerator
 * @date 2018-07-10 14:29:40
 * @version V1.0   
 *
 */
@Api(value="BaAcctSubj",description="会计科目",tags="baAcctSubjController")
@Controller
@RequestMapping("/baAcctSubjController")
public class BaAcctSubjController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(BaAcctSubjController.class);

	@Autowired
	private BaAcctSubjServiceI baAcctSubjService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 会计科目列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("cn/com/linkwide/ba/baacctsubj/baAcctSubjList");
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
	public void datagrid(BaAcctSubjEntity baAcctSubj,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		//模糊查询 科目编码  科目名称
		String subCode =baAcctSubj.getSubCode();
		String subName = baAcctSubj.getSubName();
		if(StringUtil.isNotEmpty(subCode)){
			baAcctSubj.setSubCode("*"+subCode+"*");
		}
		if(StringUtil.isNotEmpty(subName)){
			baAcctSubj.setSubName("*"+subName+"*");
		}
		CriteriaQuery cq = new CriteriaQuery(BaAcctSubjEntity.class, dataGrid);
		if(StringUtil.isEmpty(baAcctSubj.getId())){
//			cq.add(Restrictions.in("parentId", new Object[]{null,""}));
			cq.isNull("parentId");
		}else{
			cq.add(Restrictions.eq("parentId", systemService.get(BaAcctSubjEntity.class, baAcctSubj.getId()).getSubCode()));
			baAcctSubj.setId(null);
		}
		
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, baAcctSubj, request.getParameterMap());
		try{
			//编码排序
			cq.addOrder("subCode", SortDirection.asc);
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.baAcctSubjService.getDataGridReturn(cq, true);
		TagUtil.treegrid(response, dataGrid);
	}
	
	/**
	 * 删除会计科目
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(BaAcctSubjEntity baAcctSubj, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		baAcctSubj = systemService.getEntity(BaAcctSubjEntity.class, baAcctSubj.getId());
		message = "会计科目删除成功";
		try{
			//校验是否存在下级科目
			String subCode =baAcctSubj.getSubCode();
			List<BaAcctSubjEntity> list = systemService.findByProperty(BaAcctSubjEntity.class, "parentId", subCode);
			if(list!= null && list.size()>0){
				message = "存在下级科目，不能删除";
				j.setMsg(message);
				return j;
			}
			baAcctSubjService.delete(baAcctSubj);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "会计科目删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除会计科目
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "会计科目删除成功";
		try{
			for(String id:ids.split(",")){
				BaAcctSubjEntity baAcctSubj = systemService.getEntity(BaAcctSubjEntity.class,id);
				//校验是否存在下级科目
				String subCode =baAcctSubj.getSubCode();
				List<BaAcctSubjEntity> list = systemService.findByProperty(BaAcctSubjEntity.class, "parentId", subCode);
				if(list!= null && list.size()>0){
					String subName = list.get(0).getSubName();
					message = subName+"存在下级科目，不能删除";
					j.setMsg(message);
					return j;
				}
				baAcctSubjService.delete(baAcctSubj);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "会计科目删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加会计科目
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(BaAcctSubjEntity baAcctSubj, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "会计科目添加成功";
		try{
			//校验编码重复
			String code = baAcctSubj.getSubCode();
			List<BaAcctSubjEntity> list = systemService.findByProperty(BaAcctSubjEntity.class, "subCode", code);
			if(list!= null && list.size()>0){
				j.setMsg("编码已存在");
				return j;
			}
			baAcctSubjService.save(baAcctSubj);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "会计科目添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新会计科目
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(BaAcctSubjEntity baAcctSubj, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "会计科目更新成功";
		BaAcctSubjEntity t = baAcctSubjService.get(BaAcctSubjEntity.class, baAcctSubj.getId());
		try {
			//校验编码重复
			String code = t.getSubCode();
			String hql  =" from BaAcctSubjEntity where subCode=? and id <> ?";
			List<BaAcctSubjEntity> list = systemService.findHql(hql, new Object[]{code,t.getId()});
			if(list!= null && list.size()>0){
				j.setMsg("编码已存在");
				return j;
			}
			MyBeanUtils.copyBeanNotNull2Bean(baAcctSubj, t);
			baAcctSubjService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "会计科目更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 会计科目新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(BaAcctSubjEntity baAcctSubj, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(baAcctSubj.getId())) {
			baAcctSubj = baAcctSubjService.getEntity(BaAcctSubjEntity.class, baAcctSubj.getId());
			req.setAttribute("baAcctSubjPage", baAcctSubj);
		}
		return new ModelAndView("cn/com/linkwide/ba/baacctsubj/baAcctSubj-add");
	}
	/**
	 * 会计科目编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(BaAcctSubjEntity baAcctSubj, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(baAcctSubj.getId())) {
			baAcctSubj = baAcctSubjService.getEntity(BaAcctSubjEntity.class, baAcctSubj.getId());
			req.setAttribute("baAcctSubjPage", baAcctSubj);
		}
		return new ModelAndView("cn/com/linkwide/ba/baacctsubj/baAcctSubj-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","baAcctSubjController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(BaAcctSubjEntity baAcctSubj,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(BaAcctSubjEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, baAcctSubj, request.getParameterMap());
		List<BaAcctSubjEntity> baAcctSubjs = this.baAcctSubjService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"会计科目");
		modelMap.put(NormalExcelConstants.CLASS,BaAcctSubjEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("会计科目列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,baAcctSubjs);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(BaAcctSubjEntity baAcctSubj,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"会计科目");
    	modelMap.put(NormalExcelConstants.CLASS,BaAcctSubjEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("会计科目列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<BaAcctSubjEntity> listBaAcctSubjEntitys = ExcelImportUtil.importExcel(file.getInputStream(),BaAcctSubjEntity.class,params);
				for (BaAcctSubjEntity baAcctSubj : listBaAcctSubjEntitys) {
					baAcctSubjService.save(baAcctSubj);
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
	@ApiOperation(value="会计科目列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<BaAcctSubjEntity>> list() {
		List<BaAcctSubjEntity> listBaAcctSubjs=baAcctSubjService.getList(BaAcctSubjEntity.class);
		return Result.success(listBaAcctSubjs);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取会计科目信息",notes="根据ID获取会计科目信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		BaAcctSubjEntity task = baAcctSubjService.get(BaAcctSubjEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取会计科目信息为空");
		}
		return Result.success(task);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建会计科目")
	public ResponseMessage<?> create(@ApiParam(name="会计科目对象")@RequestBody BaAcctSubjEntity baAcctSubj, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<BaAcctSubjEntity>> failures = validator.validate(baAcctSubj);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			baAcctSubjService.save(baAcctSubj);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("会计科目信息保存失败");
		}
		return Result.success(baAcctSubj);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新会计科目",notes="更新会计科目")
	public ResponseMessage<?> update(@ApiParam(name="会计科目对象")@RequestBody BaAcctSubjEntity baAcctSubj) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<BaAcctSubjEntity>> failures = validator.validate(baAcctSubj);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			baAcctSubjService.saveOrUpdate(baAcctSubj);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("更新会计科目信息失败");
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success("更新会计科目信息成功");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除会计科目")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" + id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			baAcctSubjService.deleteEntityById(BaAcctSubjEntity.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("会计科目删除失败");
		}

		return Result.success();
	}
	/**
	 * 根据上级科目编码 查询下级科目的最大编码（4-2-2-2-2-2-2）
	 * @param entity
	 * @param req
	 * @return
	 */
	@RequestMapping(params="getSubCode")
	@ResponseBody
	public AjaxJson getSubCode(BaAcctSubjEntity entity,HttpServletRequest req){
		AjaxJson j = new AjaxJson();
		String subCode = req.getParameter("subCode"); 
		if(StringUtil.isNotEmpty(subCode)){
			String maxSubCode="";
			String sql = " SELECT max(sub_code) \"maxCode\" from ba_acct_subj where parent_id =?";
			List<Map<String,Object>> list =  systemService.findForJdbc(sql, new Object[]{subCode});
			if(list != null && list.size()>0){
				String maxCode1 =list.get(0).get("maxCode")==null?"":list.get(0).get("maxCode").toString();
				if(StringUtil.isNotEmpty(maxCode1)){
					//截取下级最大编码的最后两位  加一生成最新的下级编码
					String lastTwoCode = maxCode1.substring(maxCode1.length()-2, maxCode1.length());
					String newLastCode = String.valueOf(Integer.valueOf(lastTwoCode)+1);
					maxSubCode = subCode+newLastCode;
				}else{
					maxSubCode = subCode+"01";
				}
			}else{
				maxSubCode = subCode+"01";
			}
			j.setMsg(maxSubCode);
		}else{
			j.setMsg("1001");
		}
		return j;
	}
	
	/**
	 * 同步u8会计科目
	 * @param baAcctSubj
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "catchU8Sub")
	@ResponseBody
	public AjaxJson catchU8Sub(BaAcctSubjEntity baAcctSubj, HttpServletRequest request) {
		String year = request.getParameter("year");
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "会计科目同步成功";
		try{
			if(StringUtil.isEmpty(year)){
				year=String.valueOf(new Date().getYear());
			}
			//本地已有的会计科目编码字符串
//			String baCodes = null;
//			//查询本地的会计科目
//			String baSql =" select distinct sub_code \"subCode\" from ba_acct_subj where acct_year=? ";
//			List<Map<String,Object>> listMap = systemService.findForJdbc(baSql, new Object[]{year});
//			if(listMap!= null && listMap.size()>0){
//				for (Map<String, Object> map : listMap) {
//					String subCode = map.get("subCode")==null?null:map.get("subCode").toString();
//					baCodes=baCodes==null?"'"+subCode+"'":baCodes+",'"+subCode+"'";
//				}
//			}
			//遇到跨年度未开账获取不到新年度科目时同步上年度会计科目
			//删除本地会计科目sql
			String delSql =" delete from ba_acct_subj ";
			//查询u8的会计科目
			String sql = " select iyear acctYear,sub_code \"subCode\",sub_name \"subName\",parent_id \"parentId\","
					+ " case when is_depart_calculate='true' then 'Y' else 'N' end \"isDepartCalculate\","
					+ " case when is_personal_calculate='true' then 'Y' else 'N' end \"isPersonalCalculate\", "
					+ " case when is_supplier_calculate='true' then 'Y' else 'N' end \"isSupplierCalculate\","
					+ " case when is_customer_calculate='true' then 'Y' else 'N' end \"isCustomerCalculate\","
					+ " case when is_project_calculate='true' then 'Y' else 'N' end \"isProjectCalculate\","
					+ " case when bcDefine3='1' then 'Y' else 'N' end \"extend2\","
					+ " case when bcDefine8='1' then 'Y' else 'N' end \"extend3\","
					+ " case when bcDefine10='1' then 'Y' else 'N' end \"extend4\","
					+ " case when balance_direction='true' then '借' else '贷' end \"balanceDirection\","
					+ " case when is_last='true' then 'Y' else 'N' end \"extend1\",spell,cass_item \"pacId\","
					+ " case when bCashItem='1' then 'Y' else 'N' end \"bcash\" "
					+ " from v_code where iyear='#iyear#' ";
//			if(StringUtil.isNotEmpty(baCodes)){
//				sql+=" and sub_code not in("+baCodes+") ";
//			}
			String sql1=sql.replace("#iyear#", year);
			List<BaAcctSubjEntity> list = DynamicDBUtil.findListEntrys("U8", sql1, BaAcctSubjEntity.class);
			if(list!= null && list.size()>0){
				systemService.executeSql(delSql, new Object[]{});
				systemService.batchSave(list);
			}else{ //同步上一年数据
				systemService.executeSql(delSql, new Object[]{}); //String.valueOf(Integer.valueOf(year)-1)
				
				String sql2=sql.replace("#iyear#", String.valueOf(Integer.valueOf(year)-1));
				List<BaAcctSubjEntity> lastList = DynamicDBUtil.findListEntrys("U8", sql2, BaAcctSubjEntity.class);
				if(lastList!= null && lastList.size()>0){
					systemService.batchSave(lastList);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "会计科目同步失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	} 
	
}
