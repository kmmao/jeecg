package cn.com.linkwide.ba.material.qual.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.apache.commons.beanutils.BeanUtils;
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
import org.jeecgframework.core.util.ListUtils;
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
import com.alibaba.fastjson.JSONObject;

import cn.com.linkwide.ba.material.qual.entity.LSuMaterialQualEntity;
import cn.com.linkwide.ba.material.qual.entity.LSuMaterialQualInvEntity;
import cn.com.linkwide.ba.material.qual.page.LSuMaterialQualPage;
import cn.com.linkwide.ba.material.qual.service.LSuMaterialQualServiceI;
import cn.com.linkwide.ba.material.qualType.entity.LBaMaterialQualTypeEntity;
import cn.com.linkwide.ba.material.record.entity.LBaMaterialEntity;
import cn.com.linkwide.ba.supplier.entity.LBaSupplierEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @Title: Controller
 * @Description: 材料证件表
 * @author onlineGenerator
 * @date 2018-05-17 11:13:05
 * @version V1.0
 *
 */
@Api(value = "lSuMaterialQual", description = "材料证件表", tags = "lSuMaterialQualController")
@Controller
@RequestMapping("/lSuMaterialQualController")
public class LSuMaterialQualController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(LSuMaterialQualController.class);

	@Autowired
	private LSuMaterialQualServiceI LSuMaterialQualService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;

	/**
	 * 材料证件表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("cn/com/linkwide/ba/material/cert/lSuMaterialQualList");
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
	public void datagrid(LSuMaterialQualEntity LSuMaterialQual, HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(LSuMaterialQualEntity.class, dataGrid);
		// 查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, LSuMaterialQual);
		try {
			// 自定义追加查询条件
			String query_startDate_begin = request.getParameter("startDate_begin");
			String query_startDate_end = request.getParameter("startDate_end");
			if (StringUtil.isNotEmpty(query_startDate_begin)) {
				cq.ge("startDate", new SimpleDateFormat("yyyy-MM-dd").parse(query_startDate_begin));
			}
			if (StringUtil.isNotEmpty(query_startDate_end)) {
				cq.le("startDate", new SimpleDateFormat("yyyy-MM-dd").parse(query_startDate_end));
			}
			cq.add();
			this.LSuMaterialQualService.getDataGridReturn(cq, true);
			List<LSuMaterialQualEntity> result = dataGrid.getResults();
			if(result != null && !result.isEmpty()){
				Map<String, LBaMaterialEntity> mp = new HashMap();
				Map<String, LBaSupplierEntity> sp = new HashMap();
				List<String> mIds = ListUtils.extractProperty(result, "mateId");
				List<LBaMaterialEntity> mList = systemService.findByProperty(LBaMaterialEntity.class, "id", mIds.toArray());
				mp = systemService.list2Map(LBaMaterialEntity.class, mList, "id");
				
				List<String> sIds = ListUtils.extractProperty(result, "supplierId");
				List<LBaSupplierEntity> sList = systemService.findByProperty(LBaSupplierEntity.class, "id", sIds.toArray());
				sp = systemService.list2Map(LBaSupplierEntity.class, sList, "id");
				for(LSuMaterialQualEntity e :result ){
					if(mp.get(e.getMateId()) != null){
						e.setMaterialCode(mp.get(e.getMateId()).getMaterialCode());
						e.setMaterialName(mp.get(e.getMateId()).getMaterialName());
					}
					if(sp.get(e.getSupplierId()) != null){
						e.setSupplierName(sp.get(e.getSupplierId()).getSupplierFullName());
					}
				}
			} 
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}

		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除材料证件表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(LSuMaterialQualEntity LSuMaterialQual, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		LSuMaterialQual = systemService.getEntity(LSuMaterialQualEntity.class, LSuMaterialQual.getId());
		String message = "材料证件表删除成功";
		try {
			LSuMaterialQualService.delMain(LSuMaterialQual);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "材料证件表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 批量删除材料证件表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		String message = "材料证件表删除成功";
		try {
			for (String id : ids.split(",")) {
				LSuMaterialQualEntity LSuMaterialQual = systemService.getEntity(LSuMaterialQualEntity.class, id);
				LSuMaterialQualService.delMain(LSuMaterialQual);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		} catch (Exception e) {
			e.printStackTrace();
			message = "材料证件表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 添加材料证件表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(LSuMaterialQualEntity LSuMaterialQual, LSuMaterialQualPage LSuMaterialQualPage,
			HttpServletRequest request) {
		List<LSuMaterialQualInvEntity> lSuMaterialQualInvList = LSuMaterialQualPage.getLSuMaterialQualInvList();
		AjaxJson j = new AjaxJson();
		String message = "添加成功";
		try {
			LSuMaterialQualService.addMain(LSuMaterialQual, lSuMaterialQualInvList);
			j.setObj(LSuMaterialQual);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "材料证件表添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 更新材料证件表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(LSuMaterialQualEntity LSuMaterialQual, LSuMaterialQualPage LSuMaterialQualPage,
			HttpServletRequest request) {
		List<LSuMaterialQualInvEntity> lSuMaterialQualInvList = LSuMaterialQualPage.getLSuMaterialQualInvList();
		AjaxJson j = new AjaxJson();
		String message = "更新成功";
		try {
			LSuMaterialQualService.updateMain(LSuMaterialQual, lSuMaterialQualInvList);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "更新材料证件表失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 材料证件表新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(LSuMaterialQualEntity LSuMaterialQual, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(LSuMaterialQual.getId())) {
			LSuMaterialQual = LSuMaterialQualService.getEntity(LSuMaterialQualEntity.class, LSuMaterialQual.getId());
			req.setAttribute("LSuMaterialQualPage", LSuMaterialQual);
		}
		return new ModelAndView("cn/com/linkwide/ba/material/cert/lSuMaterialQual-add");
	}

	/**
	 * 材料证件表编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(LSuMaterialQualEntity LSuMaterialQual, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(LSuMaterialQual.getId())) {
			LSuMaterialQual = LSuMaterialQualService.getEntity(LSuMaterialQualEntity.class, LSuMaterialQual.getId());
			req.setAttribute("LSuMaterialQualPage", LSuMaterialQual);
		}
		return new ModelAndView("cn/com/linkwide/ba/material/cert/lSuMaterialQual-update");
	}

	/**
	 * 加载明细列表[证件材料对应关系]
	 * 
	 * @return
	 */
	@RequestMapping(params = "lSuMaterialQualInvList")
	public ModelAndView lSuMaterialQualInvList(LSuMaterialQualEntity LSuMaterialQual, HttpServletRequest req) {

		// ===================================================================================
		// 获取参数
		Object id0 = LSuMaterialQual.getId();
		String id = req.getParameter("id");
		// ===================================================================================
		// 查询-材料证件对应关系
		String hql0 = "from LSuMaterialQualInvEntity where 1 = 1 AND cERT_ID = ? ";
		String sql = "select DISTINCT dict.material_code materialCode,dict.material_name materialName ,dict.spec_model specModel,dict.mater_unit_id materUnitId   from l_su_material_qual_inv ci  left join l_ba_material  dict on ci.inv_code = dict.material_code where ci.cert_id = '"
				+ id0 + "'";
		try {
			// 获取物资字典的list集合
			List<Map<String, Object>> list = systemService.findForJdbc(sql, null);
			List<LBaMaterialEntity> lBaMaterialList = new ArrayList();
			BeanUtils beanUtils = new BeanUtils();
			for (Map map : list) {
				LBaMaterialEntity vendorInvDictEntity = new LBaMaterialEntity();
				BeanUtils.populate(vendorInvDictEntity, map);
				lBaMaterialList.add(vendorInvDictEntity);
			}
			// 获取材料证件对应关系的集合
			List<LSuMaterialQualInvEntity> LSuMaterialQualInvEntityList = systemService.findHql(hql0, id0);
			req.setAttribute("lSuMaterialQualInvList", LSuMaterialQualInvEntityList);
			req.setAttribute("lBaMaterialList", lBaMaterialList);
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return new ModelAndView("cn/com/linkwide/ba/material/cert/lSuMaterialQualInvList");
	}

	/**
	 * 导出excel
	 *
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(LSuMaterialQualEntity LSuMaterialQual, HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid, ModelMap map) {
		CriteriaQuery cq = new CriteriaQuery(LSuMaterialQualEntity.class, dataGrid);
		// 查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, LSuMaterialQual);
		try {
			// 自定义追加查询条件
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		List<LSuMaterialQualEntity> list = this.LSuMaterialQualService.getListByCriteriaQuery(cq, false);
		List<LSuMaterialQualPage> pageList = new ArrayList<LSuMaterialQualPage>();
		if (list != null && list.size() > 0) {
			for (LSuMaterialQualEntity entity : list) {
				try {
					LSuMaterialQualPage page = new LSuMaterialQualPage();
					MyBeanUtils.copyBeanNotNull2Bean(entity, page);
					Object id0 = entity.getId();
					String hql0 = "from LSuMaterialQualInvEntity where 1 = 1 AND cERT_ID = ? ";
					List<LSuMaterialQualInvEntity> LSuMaterialQualInvEntityList = systemService.findHql(hql0, id0);
					page.setLSuMaterialQualInvList(LSuMaterialQualInvEntityList);
					pageList.add(page);
				} catch (Exception e) {
					logger.info(e.getMessage());
				}
			}
		}
		
		Map<String, LBaMaterialEntity> mMap = null;
		Map<String, LBaSupplierEntity> supMap = null;
		try {
			//物资
			List<String> mids  = ListUtils.extractProperty(pageList, "mateId");
			List<LBaMaterialEntity> mList = systemService.findByProperty(LBaMaterialEntity.class, "id", mids.toArray());
			mMap = systemService.list2Map(LBaMaterialEntity.class, mList, "id");//物资
			//供应商
			List<String> sids  = ListUtils.extractProperty(pageList, "supplierId");
			List<LBaSupplierEntity> sList = systemService.findByProperty(LBaSupplierEntity.class, "id", sids.toArray());
			supMap = systemService.list2Map(LBaSupplierEntity.class, sList, "id");//物资
			
			for(LSuMaterialQualPage page : pageList){
				if(mMap.containsKey(page.getMateId())){
					page.setMaterialCode(mMap.get(page.getMateId()).getMaterialCode());
				}
				if(supMap.containsKey(page.getSupplierId())){
					page.setSupplierName(supMap.get(page.getSupplierId()).getSupplierFullName());
				}
			}
			
		} catch (Exception e) {
			e.getMessage();
		}
		map.put(NormalExcelConstants.FILE_NAME, "材料证件表");
		map.put(NormalExcelConstants.CLASS, LSuMaterialQualPage.class);
		map.put(NormalExcelConstants.PARAMS, new ExportParams("材料证件表列表", "导出人:Jeecg", "导出信息"));
		map.put(NormalExcelConstants.DATA_LIST, pageList);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}

	/**
	 * 通过excel导入数据
	 * 
	 * @param request
	 * @param
	 * @return
	 */
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
			params.setHeadRows(2);
			params.setNeedSave(true);
			try {
				List<LSuMaterialQualPage> list = ExcelImportUtil.importExcel(file.getInputStream(),
						LSuMaterialQualPage.class, params);
				LSuMaterialQualEntity entity1 = null;
				//资质分类
				List<LBaMaterialQualTypeEntity> tList = systemService.getList(LBaMaterialQualTypeEntity.class);
				Map<String, LBaMaterialQualTypeEntity> tMap = systemService.list2Map(LBaMaterialQualTypeEntity.class, tList, "typeCode");//物资
				//物资
				List<LBaMaterialEntity> mList = systemService.getList(LBaMaterialEntity.class);
				Map<String, LBaMaterialEntity> mMap = systemService.list2Map(LBaMaterialEntity.class, mList, "materialCode");//物资
				//供应商
				List<LBaSupplierEntity> sList = systemService.getList(LBaSupplierEntity.class);
				Map<String, LBaSupplierEntity> supMap = systemService.list2Map(LBaSupplierEntity.class, sList, "supplierFullName");//物资
				
				for(LSuMaterialQualPage page : list){
					if(tMap.containsKey(page.getCertType())){
						page.setCertType(tMap.get(page.getCertType()).getId());
					}else{
						j.setMsg("资质分类"+page.getMaterialCode()+"不存在！");
						return j;
					}
					if(mMap.containsKey(page.getMaterialCode())){
						page.setMateId(mMap.get(page.getMaterialCode()).getId());
					}else{
						j.setMsg("物资"+page.getMaterialCode()+"不存在！");
						return j;
					}
					if(supMap.containsKey(page.getSupplierName())){
						page.setSupplierId(supMap.get(page.getSupplierName()).getId());
					}else{
						j.setMsg("供应商"+page.getSupplierName()+"不存在！");
						return j;
					}
				}
				
				for (LSuMaterialQualPage page : list) {
					entity1 = new LSuMaterialQualEntity();
					MyBeanUtils.copyBeanNotNull2Bean(page, entity1);
					LSuMaterialQualService.addMain(entity1, page.getLSuMaterialQualInvList());
				}
				j.setMsg("文件导入成功！");
			} catch (Exception e) {
				j.setMsg("文件导入失败！");
				logger.error(ExceptionUtil.getExceptionMessage(e));
			} finally {
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
	 * 导出excel 使模板
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(ModelMap map) {
		map.put(NormalExcelConstants.FILE_NAME, "材料证件表");
		map.put(NormalExcelConstants.CLASS, LSuMaterialQualPage.class);
		map.put(NormalExcelConstants.PARAMS,
				new ExportParams("材料证件表列表", "导出人:" + ResourceUtil.getSessionUser().getRealName(), "导出信息"));
		map.put(NormalExcelConstants.DATA_LIST, new ArrayList());
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}

	/**
	 * 导入功能跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name", "lSuMaterialQualController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "材料证件表列表信息", produces = "application/json", httpMethod = "GET")
	public ResponseMessage<List<LSuMaterialQualPage>> list() {
		List<LSuMaterialQualEntity> list = LSuMaterialQualService.getList(LSuMaterialQualEntity.class);
		List<LSuMaterialQualPage> pageList = new ArrayList<LSuMaterialQualPage>();
		if (list != null && list.size() > 0) {
			for (LSuMaterialQualEntity entity : list) {
				try {
					LSuMaterialQualPage page = new LSuMaterialQualPage();
					MyBeanUtils.copyBeanNotNull2Bean(entity, page);
					Object id0 = entity.getId();
					String hql0 = "from LSuMaterialQualInvEntity where 1 = 1 AND cERT_ID = ? ";
					List<LSuMaterialQualInvEntity> vendorCertInvOldList = this.LSuMaterialQualService.findHql(hql0,
							id0);
					page.setLSuMaterialQualInvList(vendorCertInvOldList);
					pageList.add(page);
				} catch (Exception e) {
					logger.info(e.getMessage());
				}
			}
		}
		return Result.success(pageList);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "根据ID获取材料证件表信息", notes = "根据ID获取材料证件表信息", httpMethod = "GET", produces = "application/json")
	public ResponseMessage<?> get(@ApiParam(required = true, name = "id", value = "ID") @PathVariable("id") String id) {
		LSuMaterialQualEntity task = LSuMaterialQualService.get(LSuMaterialQualEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取材料证件表信息为空");
		}
		LSuMaterialQualPage page = new LSuMaterialQualPage();
		try {
			MyBeanUtils.copyBeanNotNull2Bean(task, page);
			Object id0 = task.getId();
			String hql0 = "from LSuMaterialQualInvEntity where 1 = 1 AND cERT_ID = ? ";
			List<LSuMaterialQualInvEntity> vendorCertInvOldList = this.LSuMaterialQualService.findHql(hql0, id0);
			page.setLSuMaterialQualInvList(vendorCertInvOldList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Result.success(page);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value = "创建材料证件表")
	public ResponseMessage<?> create(@ApiParam(name = "材料证件表对象") @RequestBody LSuMaterialQualPage LSuMaterialQualPage,
			UriComponentsBuilder uriBuilder) {
		// 调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<LSuMaterialQualPage>> failures = validator.validate(LSuMaterialQualPage);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		// 保存
		List<LSuMaterialQualInvEntity> lSuMaterialQualInvList = LSuMaterialQualPage.getLSuMaterialQualInvList();

		LSuMaterialQualEntity LSuMaterialQual = new LSuMaterialQualEntity();
		try {
			MyBeanUtils.copyBeanNotNull2Bean(LSuMaterialQualPage, LSuMaterialQual);
		} catch (Exception e) {
			logger.info(e.getMessage());
			return Result.error("保存材料证件表失败");
		}
		LSuMaterialQualService.addMain(LSuMaterialQual, lSuMaterialQualInvList);

		return Result.success(LSuMaterialQual);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value = "更新材料证件表", notes = "更新材料证件表")
	public ResponseMessage<?> update(@RequestBody LSuMaterialQualPage LSuMaterialQualPage) {
		// 调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<LSuMaterialQualPage>> failures = validator.validate(LSuMaterialQualPage);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		// 保存
		List<LSuMaterialQualInvEntity> lSuMaterialQualInvList = LSuMaterialQualPage.getLSuMaterialQualInvList();

		LSuMaterialQualEntity LSuMaterialQual = new LSuMaterialQualEntity();
		try {
			MyBeanUtils.copyBeanNotNull2Bean(LSuMaterialQualPage, LSuMaterialQual);
		} catch (Exception e) {
			logger.info(e.getMessage());
			return Result.error("材料证件表更新失败");
		}
		LSuMaterialQualService.updateMain(LSuMaterialQual, lSuMaterialQualInvList);

		// 按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "删除材料证件表")
	public ResponseMessage<?> delete(
			@ApiParam(name = "id", value = "ID", required = true) @PathVariable("id") String id) {
		logger.info("delete[{}]" + id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			LSuMaterialQualEntity LSuMaterialQual = LSuMaterialQualService.get(LSuMaterialQualEntity.class, id);
			LSuMaterialQualService.delMain(LSuMaterialQual);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("材料证件表删除失败");
		}

		return Result.success();
	}
	/**
	 * 物资字典模糊查询
	 * 
	 * @author zhuhd 2018-3-27
	 * @param request
	 * @return
	 *//*
		 * @RequestMapping(params = "invDict")
		 * 
		 * @ResponseBody public Object invDict(HttpServletRequest request) {
		 * String q = request.getParameter("q") != null ?
		 * request.getParameter("q") : ""; String hql =
		 * "FROM VendorInvDictEntity WHERE invCode LIKE '%" + q +
		 * "%' OR invName LIKE '%" + q + "%' "; // System.out.println(hql);
		 * List<VendorInvDictEntity> list = systemService.findHql(hql); Object
		 * json = JSONObject.toJSON(list); return json; }
		 */

	/**
	 * 批量停用材料资质证件表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doStop")
	@ResponseBody
	public AjaxJson doStop(String ids, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		String message = "材料资质证件表停用成功";
		try {
			for (String id : ids.split(",")) {
				LSuMaterialQualEntity LSuMaterialQual = systemService.getEntity(LSuMaterialQualEntity.class, id);
				LSuMaterialQual.setCertState(4);
				LSuMaterialQualService.saveOrUpdate(LSuMaterialQual);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		} catch (Exception e) {
			e.printStackTrace();
			message = "材料资质证件表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 批量启用材料资质证件表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doStart")
	@ResponseBody
	public AjaxJson doStart(String ids, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		String message = "材料资质证件表启用成功";
		try {
			for (String id : ids.split(",")) {
				LSuMaterialQualEntity LSuMaterialQual = systemService.getEntity(LSuMaterialQualEntity.class, id);
				LSuMaterialQual.setCertState(1);
				LSuMaterialQualService.saveOrUpdate(LSuMaterialQual);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		} catch (Exception e) {
			e.printStackTrace();
			message = "材料资质证件表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 批量启用材料资质证件表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doCheck")
	@ResponseBody
	public AjaxJson doCheck(String ids, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		String message = "材料资质证件表修改成功";
		Integer state = Integer.valueOf(request.getParameter("state"));
		try {
			for (String id : ids.split(",")) {
				LSuMaterialQualEntity LSuMaterialQual = systemService.getEntity(LSuMaterialQualEntity.class, id);
				LSuMaterialQual.setCertState(state);
				LSuMaterialQualService.updateMain(LSuMaterialQual,null);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		} catch (Exception e) {
			e.printStackTrace();
			message = "材料资质证件表修改失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 物资字典模糊查询
	 * 
	 * @author zhuhd 2018-3-27
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "materialDict")
	@ResponseBody
	public Object materialDict(HttpServletRequest request) {
		String q = request.getParameter("q") != null ? request.getParameter("q") : "";
		String supplierId = request.getParameter("supplierId") != null ? request.getParameter("supplierId") : "";
		String hql = "FROM LBaMaterialEntity WHERE materialCode LIKE '%" + q + "%' OR materialName LIKE '%" + q + "%' ";
		if (StringUtil.isNotEmpty(supplierId)) {
			hql = "select a FROM LBaMaterialEntity  a ,LBaContSupplierMaterialEntity  b  "
					+ "WHERE a.id = b.materialId and b.supplierId='" + supplierId + "' and ( a.materialCode LIKE '%" + q
					+ "%' OR a.materialName LIKE '%" + q + "%' )";
		}

		List<LBaMaterialEntity> list = systemService.findHqlPage(hql, 1, 30, null);
		Object json = JSONObject.toJSON(list);
		return json;
	}

	/**
	 * 加载树形结构
	 * 
	 * @return
	 */
	@RequestMapping(params = "certTypeTree")
	@ResponseBody
	public List<ComboTree> certTypeTree(HttpServletRequest request, ComboTree comboTree) {
		String parentId = request.getParameter("parentId");
		String pId = request.getParameter("code");
		if (StringUtil.isEmpty(pId)) {
			pId = parentId;
		}
		List<ComboTree> rootLists = new ArrayList<ComboTree>();
		this.LSuMaterialQualService.certTypeTree(rootLists, comboTree.getId() == null ? pId : comboTree.getId());
		return rootLists;
	}
}
