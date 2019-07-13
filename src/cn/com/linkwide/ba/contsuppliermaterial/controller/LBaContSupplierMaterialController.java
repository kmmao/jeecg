package cn.com.linkwide.ba.contsuppliermaterial.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.jeecgframework.core.beanvalidator.BeanValidators;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
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
import org.jeecgframework.web.system.pojo.base.TSUser;
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

import cn.com.linkwide.ba.departmate.entity.LBaDepartMateEntity;

import cn.com.linkwide.ba.contsuppliermaterial.entity.LBaContSupplierMaterialEntity;
import cn.com.linkwide.ba.contsuppliermaterial.entity.LBaContSupplierMaterialEntityVo;
import cn.com.linkwide.ba.contsuppliermaterial.service.LBaContSupplierMaterialServiceI;
import cn.com.linkwide.ba.material.record.entity.LBaMaterialEntity;
import cn.com.linkwide.ba.supplier.entity.LBaSupplierEntity;
import cn.com.linkwide.common.util.controller.FilePathDefault;

/**
 * @Title: Controller
 * @Description: 供应商物资对照
 * @author onlineGenerator
 * @date 2017-11-16 16:05:12
 * @version V1.0
 * 
 */
@Controller
@RequestMapping("/lBaContSupplierMaterialController")
public class LBaContSupplierMaterialController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(LBaContSupplierMaterialController.class);

	@Autowired
	private LBaContSupplierMaterialServiceI lBaContSupplierMaterialService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;

	/**
	 * 供应商物资对照列表 页面跳转
	 * /lms/WebContent/webpage/cn/com/linkwide/mate/base/base/contsuppliermaterial/
	 * lBaContSupplierMaterial-add.jsp
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("cn/com/linkwide/ba/contsuppliermaterial/lBaContSupplierMaterialList");
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
	public void datagrid(LBaContSupplierMaterialEntity lBaContSupplierMaterial, HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid) { 
		String material = request.getParameter("materialIdQ");
		String supplierId = request.getParameter("supplierId");
		try {
			
			String head = "select a.id  \"id\",a.supplier_id  \"supplierId\",a.material_id  \"materialId\" ,s.supplier_full_name  \"supplierName\"  , s.supplier_code  \"supplierCode\" "
					+ ",b.material_name  \"materialName\",b.material_code  \"materialCode\" " ;
			String sql ="from l_ba_cont_supplier_material  a ,l_ba_material  b ,l_ba_supplier  s  "
					+ "where a.material_id = b.id  and a.supplier_id = s.id ";
			if(StringUtil.isNotEmpty(supplierId)){
				sql +=" and a.supplier_id ='"+supplierId+"'";
			}
			if(StringUtil.isNotEmpty(material)){
				sql += " and ( b.material_code like '%"+ material+"%'";
				sql += " or b.material_name like '%"+ material+"%'";
				sql += " or b.monm_code like '%"+ material+"%' )";  
			}
			sql += " order by a.create_date desc "; 
			List<Map<String,Object>> list = systemService.findForJdbc(head+sql,dataGrid.getPage(),dataGrid.getRows());
			dataGrid.setResults(list);
			Long total = systemService.getCountForJdbc("select count(a.id) "+sql);
			dataGrid.setTotal(Integer.valueOf(total.toString()));
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		} 

		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除供应商物资对照
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(LBaContSupplierMaterialEntity lBaContSupplierMaterial, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		lBaContSupplierMaterial = systemService.getEntity(LBaContSupplierMaterialEntity.class,lBaContSupplierMaterial.getId());
		message = "供应商物资对照删除成功";
		try {
			lBaContSupplierMaterialService.delete(lBaContSupplierMaterial);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "供应商物资对照删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 批量删除供应商物资对照
	 * 
	 * @return
	 */
	@RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "供应商物资对照删除成功";
		try {
			for (String id : ids.split(",")) {
				LBaContSupplierMaterialEntity lBaContSupplierMaterial = systemService.getEntity(LBaContSupplierMaterialEntity.class, id);
				lBaContSupplierMaterialService.delete(lBaContSupplierMaterial);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		} catch (Exception e) {
			e.printStackTrace();
			message = "供应商物资对照删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 添加供应商物资对照
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(LBaContSupplierMaterialEntity lBaContSupplierMaterial, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "供应商物资对照添加成功";
		try {
			
			String ids = request.getParameter("ids");
			String supplierId = request.getParameter("supplierId");
			String[] idArray = ids.split(",");
			TSUser tsUser = ResourceUtil.getSessionUserName();
			for(int i=0;i<idArray.length;i++){
				LBaContSupplierMaterialEntity supMate = new LBaContSupplierMaterialEntity();
				supMate.setSupplierId(supplierId);
				supMate.setMaterialId(idArray[i]);
				// 校验
				AjaxJson ajaxJson = vailRep(supMate);
				if (!ajaxJson.isSuccess()) {
					return ajaxJson;
				}
				supMate.setCreateDate(new Date());
				supMate.setDepartId(tsUser.getDepartid());
				supMate.setCreateBy(tsUser.getId());
				lBaContSupplierMaterialService.save(supMate);
			}
			
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "供应商物资对照添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	public AjaxJson vailRep(LBaContSupplierMaterialEntity l) {
		AjaxJson j = new AjaxJson();

		List<LBaContSupplierMaterialEntity> lBaContSupplierMaterialEntities = systemService.findByQueryString(
				" from LBaContSupplierMaterialEntity where id != '" + l.getId() + "' and supplierId = '"
						+ l.getSupplierId() + "' and materialId = '" + l.getMaterialId() + "' ");
		if (lBaContSupplierMaterialEntities.size() > 0) {
			j.setMsg("该供应商物资对照已经存在");
			j.setSuccess(false);
		}

		return j;
	}

	/**
	 * 更新供应商物资对照
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(LBaContSupplierMaterialEntity lBaContSupplierMaterial, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "供应商物资对照更新成功";
		LBaContSupplierMaterialEntity t = lBaContSupplierMaterialService.get(LBaContSupplierMaterialEntity.class,lBaContSupplierMaterial.getId());
		try {
			// 校验
			AjaxJson ajaxJson = vailRep(lBaContSupplierMaterial);
			if (!ajaxJson.isSuccess()) {
				return ajaxJson;
			}

			TSUser tsUser = ResourceUtil.getSessionUserName();
			lBaContSupplierMaterial.setUpdateBy(tsUser.getId());
			lBaContSupplierMaterial.setUpdateDate(new Date());
			MyBeanUtils.copyBeanNotNull2Bean(lBaContSupplierMaterial, t);
			lBaContSupplierMaterialService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "供应商物资对照更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 供应商物资对照新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(LBaContSupplierMaterialEntity lBaContSupplierMaterial, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(lBaContSupplierMaterial.getId())) {
			lBaContSupplierMaterial = lBaContSupplierMaterialService.getEntity(LBaContSupplierMaterialEntity.class,lBaContSupplierMaterial.getId());
			req.setAttribute("lBaContSupplierMaterialPage", lBaContSupplierMaterial);
		}
		return new ModelAndView("cn/com/linkwide/ba/contsuppliermaterial/lBaContSupplierMaterial-add");
	}

	/**
	 * 供应商物资对照编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(LBaContSupplierMaterialEntity lBaContSupplierMaterial, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(lBaContSupplierMaterial.getId())) {
			lBaContSupplierMaterial = lBaContSupplierMaterialService.getEntity(LBaContSupplierMaterialEntity.class,lBaContSupplierMaterial.getId());

			LBaSupplierEntity lBaSupplierEntity = systemService.getEntity(LBaSupplierEntity.class,lBaContSupplierMaterial.getSupplierId());
			lBaContSupplierMaterial.setSupplierCode(lBaSupplierEntity.getSupplierCode());
			lBaContSupplierMaterial.setSupplierName(lBaSupplierEntity.getSupplierFullName());

			LBaMaterialEntity lBaMaterialEntity = systemService.getEntity(LBaMaterialEntity.class,lBaContSupplierMaterial.getMaterialId());
			lBaContSupplierMaterial.setMaterialCode(lBaMaterialEntity.getMaterialCode());
			lBaContSupplierMaterial.setMaterialName(lBaMaterialEntity.getMaterialName());

			req.setAttribute("lBaContSupplierMaterialPage", lBaContSupplierMaterial);
		}
		return new ModelAndView("cn/com/linkwide/ba/contsuppliermaterial/lBaContSupplierMaterial-update");
	}

	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name", "lBaContSupplierMaterialController");
		req.setAttribute("datagridList_name", "lBaContSupplierMaterialList");
		req.setAttribute("filePath", FilePathDefault.SUPPMATEPATH);

		// return new
		// ModelAndView("cn/com/linkwide/ba/contsuppliermaterial/excelImport");
		return new ModelAndView("common/upload/excelImport");
	}

	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(String ids,LBaContSupplierMaterialEntity lBaContSupplierMaterial, HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {

		CriteriaQuery cq = new CriteriaQuery(LBaContSupplierMaterialEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, lBaContSupplierMaterial,request.getParameterMap());
		//List<LBaContSupplierMaterialEntity> lBaContSupplierMaterials = this.lBaContSupplierMaterialService.getListByCriteriaQuery(cq, false);
		List<LBaContSupplierMaterialEntity> lBaContSupplierMaterials = null;
		if(StringUtil.isNotEmpty(ids)){
			ids = ids.replace(",", "','");
			lBaContSupplierMaterials = lBaContSupplierMaterialService.getExportData(ids);
		}else{
			lBaContSupplierMaterials = systemService.getList(LBaContSupplierMaterialEntity.class);
		} 
		for (LBaContSupplierMaterialEntity le : lBaContSupplierMaterials) {
			// 获取供应商信息
			LBaSupplierEntity lBaSupplierEntity = systemService.getEntity(LBaSupplierEntity.class, le.getSupplierId());
			if(lBaSupplierEntity != null){
				le.setSupplierCode(lBaSupplierEntity.getSupplierCode());
				le.setSupplierId(lBaSupplierEntity.getId());
			}
			// 获取物资信息
			LBaMaterialEntity lBaMaterialEntity = systemService.getEntity(LBaMaterialEntity.class, le.getMaterialId());
			if( lBaMaterialEntity != null){
				le.setMaterialCode(lBaMaterialEntity.getMaterialCode());
				le.setMaterialId(lBaMaterialEntity.getId());
			}
		}
		modelMap.put(NormalExcelConstants.FILE_NAME, "供应商物资对照");
		modelMap.put(NormalExcelConstants.CLASS, LBaContSupplierMaterialEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("供应商物资对照列表", "导出人:" + ResourceUtil.getSessionUserName().getUserName(), "导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST, lBaContSupplierMaterials);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}

	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(LBaContSupplierMaterialEntity lBaContSupplierMaterial, HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
		List<LBaContSupplierMaterialEntity> arrayList = new ArrayList<LBaContSupplierMaterialEntity>();
		LBaContSupplierMaterialEntity le = new LBaContSupplierMaterialEntity();
		le.setSupplierCode("请设置单元格格式为文本");
		le.setMaterialCode("请设置单元格格式为文本");
		arrayList.add(le);
		modelMap.put(NormalExcelConstants.FILE_NAME, "供应商物资对照");
		modelMap.put(NormalExcelConstants.CLASS, LBaContSupplierMaterialEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("供应商物资对照列表", "导出人:" + ResourceUtil.getSessionUserName().getUserName(), "导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST, arrayList);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}

	@RequestMapping(params = "importExcel")
	@ResponseBody
	public AjaxJson importExcel(HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		String msg = "文件导入成功";
		boolean successFlag = true;
		// 返回参数
		Map<String, Object> map = new HashMap<String, Object>();
		// 随机UUID
		String uuid = UUID.randomUUID().toString();
		uuid = uuid.replace("-", "");

		String filePath = request.getParameter("filePath");
		String realPath = request.getSession().getServletContext().getRealPath("/") + "/" + filePath;// 文件的硬盘真实路径

		File f = new File(realPath);
		FileInputStream fin = null;

		ImportParams params = new ImportParams();
		params.setTitleRows(2);
		params.setHeadRows(1);
		params.setNeedSave(true);
		int i = 4;
		try {
			fin = new FileInputStream(f);
			List<LBaContSupplierMaterialEntity> listLBaContSupplierMaterialEntity = ExcelImportUtil.importExcel(fin,LBaContSupplierMaterialEntity.class, params);

			// 存放数据错误信息
			List<LBaContSupplierMaterialEntityVo> lvErrorList = new ArrayList<LBaContSupplierMaterialEntityVo>();

			if (listLBaContSupplierMaterialEntity.size() == 0) {
				j.setMsg("请先维护供货商物资对照信息");
				return j;
			}
			List<LBaSupplierEntity> slist = systemService.getList(LBaSupplierEntity.class);
			Map<String,LBaSupplierEntity> sMap = systemService.list2Map(LBaSupplierEntity.class, slist, "supplierCode");
			List<LBaMaterialEntity> mlist = systemService.getList(LBaMaterialEntity.class);
			Map<String,LBaMaterialEntity> mMap = systemService.list2Map(LBaMaterialEntity.class, mlist, "materialCode");
			for (LBaContSupplierMaterialEntity lBaContSupplierMaterial : listLBaContSupplierMaterialEntity) {
				// 数据校验
				String errMsg = "";

				String supplierCode = lBaContSupplierMaterial.getSupplierCode(); // 供应商编码
				String materialCode = lBaContSupplierMaterial.getMaterialCode(); // 物资编码
				// 根据供应商编码查询供应商所有信息
				if (!sMap.containsKey(supplierCode)) {
					errMsg = errMsg + "供应商编码有误" + ";";
				} else {
					lBaContSupplierMaterial.setSupplierId(sMap.get( supplierCode).getId() );
				}
				// 根据物资编码查询物资所有信息
				if (!mMap.containsKey(materialCode)) {
					errMsg = errMsg + "物资编码有误" + ";";
				} else {
					lBaContSupplierMaterial.setMaterialId(mMap.get(materialCode).getId());
					TSUser tsUser = ResourceUtil.getSessionUserName();
					lBaContSupplierMaterial.setUpdateBy(tsUser.getId()); // 更新人
					lBaContSupplierMaterial.setUpdateDate(new Date()); // 更新时间
					lBaContSupplierMaterial.setDepartId(tsUser.getDepartid()); // 登录部门
				}

				// 对供应商编码进行检查
				if (StringUtil.isEmpty(supplierCode)) {
					errMsg = "";
					errMsg = errMsg + "供应商编码不能为空" + ";";
				}
				// 对物资编码进行检查
				if (StringUtil.isEmpty(materialCode)) {
					errMsg = errMsg + "物资编码不能为空" + ";";
				}

				List<LBaContSupplierMaterialEntity> lBaContSupplierMaterialEntities = systemService.findByQueryString(
						" from LBaContSupplierMaterialEntity where id != '" + lBaContSupplierMaterial.getId()
								+ "' and supplierId = '" + lBaContSupplierMaterial.getSupplierId()
								+ "' and materialId = '" + lBaContSupplierMaterial.getMaterialId() + "' ");
				if (lBaContSupplierMaterialEntities.size() > 0) {
					errMsg = errMsg + "数据已存在" + ";";
				}

				if (!errMsg.equals("")) {
					LBaContSupplierMaterialEntityVo le = new LBaContSupplierMaterialEntityVo();

					le.setSupplierCode(supplierCode); // 供应商编码

					le.setMaterialCode(materialCode); // 物资编码

					le.setErrMsg(errMsg); // 错误描述

					lvErrorList.add(le);

					msg = "文件导入存在部分失败";
					map.put("fileUuid", uuid);
				}
				i++;
			}
			i=4;
			for (LBaContSupplierMaterialEntity lBaContSupplierMaterial : listLBaContSupplierMaterialEntity) {
				if(StringUtil.isNotEmpty(lBaContSupplierMaterial.getSupplierId()) && StringUtil.isNotEmpty(lBaContSupplierMaterial.getMaterialId())){
					lBaContSupplierMaterialService.save(lBaContSupplierMaterial);
				}
				i++;
			}
			request.getSession().setAttribute(uuid, lvErrorList);
			//j.setMsg(msg);

		} catch (Exception e) {
			successFlag = false;
			j.setSuccess(successFlag);
			msg = "第“" + i + "”行数据错误!"+e.getMessage();
			j.setMsg(msg);
			logger.error(ExceptionUtil.getExceptionMessage(e));
			return j;
		} finally {
			try {
				fin.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		j.setMsg(msg);
		j.setSuccess(successFlag);

		j.setAttributes(map);
		return j;
	}

	/**
	 * 
	 * 导出excel 错误信息
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "importResult")
	public String exportResult(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid,ModelMap modelMap) {
		String fileUuid = request.getParameter("fileUuid");

		List<LBaContSupplierMaterialEntityVo> lvList = (List<LBaContSupplierMaterialEntityVo>) request.getSession().getAttribute(fileUuid);

		modelMap.put(NormalExcelConstants.FILE_NAME, "导入结果");
		modelMap.put(NormalExcelConstants.CLASS, LBaContSupplierMaterialEntityVo.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("导入结果列表", "导出人：" + ResourceUtil.getSessionUserName().getUserName(), "导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST, lvList);
		request.getSession().removeAttribute(fileUuid);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<LBaContSupplierMaterialEntity> list() {
		List<LBaContSupplierMaterialEntity> listLBaContSupplierMaterials = lBaContSupplierMaterialService.getList(LBaContSupplierMaterialEntity.class);
		return listLBaContSupplierMaterials;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		LBaContSupplierMaterialEntity task = lBaContSupplierMaterialService.get(LBaContSupplierMaterialEntity.class,id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody LBaContSupplierMaterialEntity lBaContSupplierMaterial,UriComponentsBuilder uriBuilder) {
		// 调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<LBaContSupplierMaterialEntity>> failures = validator.validate(lBaContSupplierMaterial);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		// 保存
		try {
			lBaContSupplierMaterialService.save(lBaContSupplierMaterial);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		// 按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = lBaContSupplierMaterial.getId();
		URI uri = uriBuilder.path("/rest/lBaContSupplierMaterialController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody LBaContSupplierMaterialEntity lBaContSupplierMaterial) {
		// 调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<LBaContSupplierMaterialEntity>> failures = validator.validate(lBaContSupplierMaterial);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		// 保存
		try {
			lBaContSupplierMaterialService.saveOrUpdate(lBaContSupplierMaterial);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}

		// 按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		lBaContSupplierMaterialService.deleteEntityById(LBaContSupplierMaterialEntity.class, id);
	}
	
	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "mateDatagrid")
	public void mateDatagrid(LBaContSupplierMaterialEntity lBaSuppmate,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		try{
		//自定义追加查询条件
			String supId = lBaSuppmate.getSupplierId();
			String materialId = request.getParameter("mateId");
			if(StringUtil.isEmpty(supId)){
				TagUtil.datagrid(response, dataGrid);
				return;
			}
			if(StringUtil.isNotEmpty(materialId)){
				lBaSuppmate.setMaterialId(materialId);
			}
			StringBuffer sql = new StringBuffer();
			sql.append(" from l_ba_material a WHERE id  not in ( select material_id from  l_ba_cont_supplier_material b where b.supplier_id = '"+supId+"' )");
			if(StringUtil.isNotEmpty(lBaSuppmate.getMaterialId())){
				sql.append(" and  ( a.material_code like '%"+lBaSuppmate.getMaterialId()+"%' or  a.material_name like '%"+lBaSuppmate.getMaterialId()+"%' )");
			}
			List<Map<String,Object>> list = systemService.findForJdbc("select a.id  id ,a.material_code  code,a.material_name  name "+sql.toString(), dataGrid.getPage(), dataGrid.getRows());
			dataGrid.setResults(list);
			List<Map<String,Object>> listC = systemService.findForJdbc("select count(a.id)  con "+sql.toString());
			dataGrid.setTotal(Integer.valueOf(listC.get(0).get("con").toString()));
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		TagUtil.datagrid(response, dataGrid);
	}
}
