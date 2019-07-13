package cn.com.linkwide.ba.contwarehousematerial.controller;

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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import cn.com.linkwide.ba.contsuppliermaterial.entity.LBaContSupplierMaterialEntity;
import cn.com.linkwide.ba.contwarehousematerial.entity.LBaContWarehouseMaterialEntity;
import cn.com.linkwide.ba.contwarehousematerial.entity.LBaContWarehouseMaterialEntityVo;
import cn.com.linkwide.ba.contwarehousematerial.service.LBaContWarehouseMaterialServiceI;
import cn.com.linkwide.ba.material.record.entity.LBaMaterialEntity;
import cn.com.linkwide.ba.warehouse.entity.LBaWarehouseEntity;
import cn.com.linkwide.common.util.controller.FilePathDefault;

/**
 * @Title: Controller
 * @Description: 仓库物资对照
 * @author onlineGenerator
 * @date 2017-11-21 15:14:05
 * @version V1.0
 *
 */
@Controller
@RequestMapping("/lBaContWarehouseMaterialController")
public class LBaContWarehouseMaterialController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(LBaContWarehouseMaterialController.class);

	@Autowired
	private LBaContWarehouseMaterialServiceI lBaContWarehouseMaterialService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
//	@Autowired
//	private LStOutWareServiceI lStOutWareService;

	/**
	 * 仓库物资对照列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("cn/com/linkwide/ba/contwarehousematerial/lBaContWarehouseMaterialList");
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
	public void datagrid(LBaContWarehouseMaterialEntity lBaContWarehouseMaterial, HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid) {
		String material = lBaContWarehouseMaterial.getMaterialId();
		String wareHouseId = lBaContWarehouseMaterial.getWarehouseId();
		try {
			
			
			String head = "select a.id  \"id\" ,a.warehouse_id \"warehouseId\" ,a.material_id \"materialId\" ,s.warehouse_name \"warehouseName\"  , s.warehouse_code \"warehouseCode\"  "
					+ " ,b.material_name \"materialName\" ,b.material_code  \"materialCode\" " ;
			String sql ="from l_ba_cont_warehouse_material  a ,l_ba_material  b ,l_ba_warehouse  s  "
					+ "where a.material_id = b.id  and a.warehouse_id = s.id  ";
			
			if(StringUtil.isNotEmpty(wareHouseId)){
				sql +=" and a.warehouse_id ='"+wareHouseId+"'";
			} 
			if(StringUtil.isNotEmpty(material)){
				sql += " and ( b.material_code like '%"+ material+"%'";
				sql += " or b.material_name like '%"+ material+"%'";
				sql += " or b.monm_code like '%"+ material+"%' )";  
			}
			sql += " order by a.create_date desc  "; 
			//集团化业务过滤
			/*String sql ="select DISTINCT warehouse_id from l_ba_warehouse_org_map where org_code like '"+ResourceUtil.getUserComponyCode()+"%'";
			List<Map<String,Object>> list = systemService.findForJdbc(sql);
			String ids = ""; 
			for(Map<String,Object> map : list){
				ids+=map.get("warehouse_id").toString()+","; 
			}
			hql += "and a.warehouseId  in ('"+ids.replace(",", "','")+"') ";*/
		    List<Map<String,Object>> list = systemService.findForJdbc(head+sql, dataGrid.getPage(), dataGrid.getRows());
		    Long total = systemService.getCountForJdbc("select count(a.id) "+sql);
		    dataGrid.setResults(list);
		    dataGrid.setTotal(Integer.valueOf(total.toString()));
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		} 

		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除仓库物资对照
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(LBaContWarehouseMaterialEntity lBaContWarehouseMaterial, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		lBaContWarehouseMaterial = systemService.getEntity(LBaContWarehouseMaterialEntity.class,lBaContWarehouseMaterial.getId());
		message = "仓库物资对照删除成功";
		try {
//			int i = lStOutWareService.getWareHouseMaterialQuantity(lBaContWarehouseMaterial.getWarehouseId(),lBaContWarehouseMaterial.getMaterialId());
//			if (i > 0) {
//				message = "仓库物资对照已经被引用，不可删除";
//				j.setMsg(message);
//				return j;
//			}
			lBaContWarehouseMaterialService.delete(lBaContWarehouseMaterial);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "仓库物资对照删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 批量删除仓库物资对照
	 * 
	 * @return
	 */
	@RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "仓库物资对照删除成功";
		try {
			for (String id : ids.split(",")) {
				LBaContWarehouseMaterialEntity lBaContWarehouseMaterial = systemService.getEntity(LBaContWarehouseMaterialEntity.class, id);

				/*int i = lStOutWareService.getWareHouseMaterialQuantity(lBaContWarehouseMaterial.getWarehouseId(),lBaContWarehouseMaterial.getMaterialId());
				if (i > 0) {
					message = "仓库物资对照已经被引用，不可删除";
					j.setMsg(message);
					return j;
				}*/

				lBaContWarehouseMaterialService.delete(lBaContWarehouseMaterial);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		} catch (Exception e) {
			e.printStackTrace();
			message = "仓库物资对照删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 添加仓库物资对照
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(LBaContWarehouseMaterialEntity lBaContWarehouseMaterial, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "仓库物资对照添加成功";
		try {
			TSUser tsUser = ResourceUtil.getSessionUserName();
			String ids = request.getParameter("ids");
			String warehouseId = request.getParameter("warehouseId");
			String[] idArray = ids.split(",");
			for(int i=0;i<idArray.length;i++){
				LBaContWarehouseMaterialEntity hm = new LBaContWarehouseMaterialEntity();
				hm.setMaterialId(idArray[i]);
				hm.setWarehouseId(warehouseId);
				// 校验
				AjaxJson ajaxJson = vailRep(hm);
				if (!ajaxJson.isSuccess()) {
					return ajaxJson;
				}
				hm.setCreateBy(tsUser.getId());
				hm.setDepartId(tsUser.getDepartid());
				hm.setCreateDate(new Date());
				lBaContWarehouseMaterialService.save(hm);
				systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			message = "仓库物资对照添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	public AjaxJson vailRep(LBaContWarehouseMaterialEntity l) {
		AjaxJson j = new AjaxJson();

		List<LBaContWarehouseMaterialEntity> lBaContWarehouseMaterialEntities = systemService.findByQueryString(
				" from LBaContWarehouseMaterialEntity where id != '" + l.getId() + "' and warehouseId = '"
						+ l.getWarehouseId() + "' and materialId = '" + l.getMaterialId() + "' ");
		if (lBaContWarehouseMaterialEntities.size() > 0) {
			j.setMsg("该仓库物资对照已经存在");
			j.setSuccess(false);
		}

		return j;
	}

	/**
	 * 更新仓库物资对照
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(LBaContWarehouseMaterialEntity lBaContWarehouseMaterial, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "仓库物资对照更新成功";
		LBaContWarehouseMaterialEntity t = lBaContWarehouseMaterialService.get(LBaContWarehouseMaterialEntity.class,lBaContWarehouseMaterial.getId());
		try {
			// 校验
			AjaxJson ajaxJson = vailRep(lBaContWarehouseMaterial);
			if (!ajaxJson.isSuccess()) {
				return ajaxJson;
			}
			TSUser tsUser = ResourceUtil.getSessionUserName();
			lBaContWarehouseMaterial.setUpdateBy(tsUser.getId());
			lBaContWarehouseMaterial.setUpdateDate(new Date());
			MyBeanUtils.copyBeanNotNull2Bean(lBaContWarehouseMaterial, t);
			lBaContWarehouseMaterialService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "仓库物资对照更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 仓库物资对照新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(LBaContWarehouseMaterialEntity lBaContWarehouseMaterial, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(lBaContWarehouseMaterial.getId())) {
			lBaContWarehouseMaterial = lBaContWarehouseMaterialService.getEntity(LBaContWarehouseMaterialEntity.class,lBaContWarehouseMaterial.getId());
			req.setAttribute("lBaContWarehouseMaterialPage", lBaContWarehouseMaterial);
		}
		return new ModelAndView("cn/com/linkwide/ba/contwarehousematerial/lBaContWarehouseMaterial-add");
	}

	/**
	 * 仓库物资对照编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(LBaContWarehouseMaterialEntity lBaContWarehouseMaterial, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(lBaContWarehouseMaterial.getId())) {
			lBaContWarehouseMaterial = lBaContWarehouseMaterialService.getEntity(LBaContWarehouseMaterialEntity.class,lBaContWarehouseMaterial.getId());

			LBaWarehouseEntity lBaWarehouseEntity = systemService.getEntity(LBaWarehouseEntity.class,lBaContWarehouseMaterial.getWarehouseId());
			lBaContWarehouseMaterial.setWarehouseCode(lBaWarehouseEntity.getWarehouseCode());
			lBaContWarehouseMaterial.setWarehouseName(lBaWarehouseEntity.getWarehouseName());

			LBaMaterialEntity lBaMaterialEntity = systemService.getEntity(LBaMaterialEntity.class,lBaContWarehouseMaterial.getMaterialId());
			lBaContWarehouseMaterial.setMaterialCode(lBaMaterialEntity.getMaterialCode());
			lBaContWarehouseMaterial.setMaterialName(lBaMaterialEntity.getMaterialName());

			req.setAttribute("lBaContWarehouseMaterialPage", lBaContWarehouseMaterial);

		}
		return new ModelAndView("cn/com/linkwide/ba/contwarehousematerial/lBaContWarehouseMaterial-update");
	}

	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name", "lBaContWarehouseMaterialController");
		req.setAttribute("datagridList_name", "lBaContWarehouseMaterialList");
		req.setAttribute("filePath", FilePathDefault.MATEHOUSEPATH);
		return new ModelAndView("common/upload/excelImport");
	}

	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(String ids, LBaContWarehouseMaterialEntity lBaContWarehouseMaterial, HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(LBaContWarehouseMaterialEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, lBaContWarehouseMaterial,request.getParameterMap());
		//List<LBaContWarehouseMaterialEntity> lBaContWarehouseMaterials = this.lBaContWarehouseMaterialService.getListByCriteriaQuery(cq, false);
		ids = ids.replace(",", "','");
		List<LBaContWarehouseMaterialEntity> lBaContWarehouseMaterials = lBaContWarehouseMaterialService.getDataForExport(ids);
		for (LBaContWarehouseMaterialEntity le : lBaContWarehouseMaterials) {
			// 仓库信息
			LBaWarehouseEntity lBaWarehouseEntity = systemService.getEntity(LBaWarehouseEntity.class,le.getWarehouseId());
			le.setWarehouseCode(lBaWarehouseEntity.getWarehouseCode());

			// 物资信息
			LBaMaterialEntity lBaMaterialEntity = systemService.getEntity(LBaMaterialEntity.class, le.getMaterialId());
			le.setMaterialCode(lBaMaterialEntity.getMaterialCode());
		}
		modelMap.put(NormalExcelConstants.FILE_NAME, "仓库物资对照");
		modelMap.put(NormalExcelConstants.CLASS, LBaContWarehouseMaterialEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("仓库物资对照列表", "导出人:" + ResourceUtil.getSessionUserName().getUserName(), "导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST, lBaContWarehouseMaterials);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}

	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(LBaContWarehouseMaterialEntity lBaContWarehouseMaterial, HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
		List<LBaContWarehouseMaterialEntity> arrayList = new ArrayList<LBaContWarehouseMaterialEntity>();
		LBaContWarehouseMaterialEntity le = new LBaContWarehouseMaterialEntity();
		le.setWarehouseCode("请设置单元格格式为文本");
		le.setMaterialCode("请设置单元格格式为文本");
		arrayList.add(le);
		modelMap.put(NormalExcelConstants.FILE_NAME, "仓库物资对照");
		modelMap.put(NormalExcelConstants.CLASS, LBaContWarehouseMaterialEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("仓库物资对照列表", "导出人:" + ResourceUtil.getSessionUserName().getUserName(), "导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST, arrayList);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}

	@RequestMapping(params = "importExcel")
	@ResponseBody
	public AjaxJson importExcel(HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		// 信息提示
		String msg = "文件导入成功";
		// 标识
		boolean flag = true;
		// 返回参数
		Map<String, Object> map = new HashMap<String, Object>();
		// 随机生成UUID
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
		try {
			fin = new FileInputStream(f);

			List<LBaContWarehouseMaterialEntity> listLBaContWarehouseMaterialEntitys = ExcelImportUtil.importExcel(fin,LBaContWarehouseMaterialEntity.class, params);
			// 存放错误信息
			List<LBaContWarehouseMaterialEntityVo> lmErrList = new ArrayList<LBaContWarehouseMaterialEntityVo>();
			// 数据校验
			if (listLBaContWarehouseMaterialEntitys.size() == 0) {
				msg = "请先维护操作员仓库对照信息;";
				j.setMsg(msg);
				return j;
			}
			for (LBaContWarehouseMaterialEntity lBaContWarehouseMaterial : listLBaContWarehouseMaterialEntitys) {
				// 错误信息
				String errMsg = "";

				String warehouseCode = lBaContWarehouseMaterial.getWarehouseCode(); // 仓库编码
				String materialCode = lBaContWarehouseMaterial.getMaterialCode(); // 物资编码

				// 校验仓库编码是否正确
				LBaWarehouseEntity lBaWarehouseEntity = systemService.findUniqueByProperty(LBaWarehouseEntity.class,"warehouseCode", warehouseCode);
				if (lBaWarehouseEntity == null) {
					errMsg = errMsg + "仓库编码有误;";
				} else {
					lBaContWarehouseMaterial.setWarehouseId(lBaWarehouseEntity.getId());
				}
				// 校验物资编码是否正确
				LBaMaterialEntity lBaMaterialEntity = systemService.findUniqueByProperty(LBaMaterialEntity.class,"materialCode", materialCode);
				if (lBaMaterialEntity == null) {
					errMsg = errMsg + "物资编码有误;";
				} else {
					lBaContWarehouseMaterial.setMaterialId(lBaMaterialEntity.getId());

					TSUser user = ResourceUtil.getSessionUserName();
					lBaContWarehouseMaterial.setUpdateBy(user.getId()); // 更新人
					lBaContWarehouseMaterial.setUpdateDate(new Date()); // 更新时间
					lBaContWarehouseMaterial.setDepartId(user.getDepartid()); // 登录部门
				}

				// 校验仓库编码是否为空
				if (StringUtil.isEmpty(warehouseCode)) {
					errMsg = "";
					errMsg = errMsg + "仓库编码不能为空;";
				}
				// 校验物资编码是否为空
				if (StringUtil.isEmpty(materialCode)) {
					errMsg = errMsg + "物资编码不能为空;";
				}

				// 重复校验
				List<LBaContWarehouseMaterialEntity> lBaContWarehouseMaterialEntities = systemService.findByQueryString(
						" from LBaContWarehouseMaterialEntity where id != '" + lBaContWarehouseMaterial.getId()
								+ "' and warehouseId = '" + lBaContWarehouseMaterial.getWarehouseId()
								+ "' and materialId = '" + lBaContWarehouseMaterial.getMaterialId() + "' ");
				if (lBaContWarehouseMaterialEntities.size() > 0) {
					errMsg = errMsg + "数据已存在;";
				}

				if (!errMsg.equals("")) {
					LBaContWarehouseMaterialEntityVo lv = new LBaContWarehouseMaterialEntityVo();

					lv.setWarehouseCode(warehouseCode);
					lv.setMaterialCode(materialCode);
					lv.setErrMsg(errMsg); // 错误信息

					lmErrList.add(lv);
					msg = "文件导入存在部分失败！";
					map.put("fileUuid", uuid);
				} else {
					lBaContWarehouseMaterialService.save(lBaContWarehouseMaterial);
				}
			}
			request.getSession().setAttribute(uuid, lmErrList);
			//j.setMsg(msg);
		} catch (Exception e) {
			flag = false;
			j.setSuccess(flag);

			msg = "文件导入失败，请联系管理员!";
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
		j.setSuccess(flag);
		j.setMsg(msg);
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

		List<LBaContWarehouseMaterialEntityVo> lvList = (List<LBaContWarehouseMaterialEntityVo>) request.getSession().getAttribute(fileUuid);

		modelMap.put(NormalExcelConstants.FILE_NAME, "导入结果");
		modelMap.put(NormalExcelConstants.CLASS, LBaContWarehouseMaterialEntityVo.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("导入结果列表", "导出人：" + ResourceUtil.getSessionUserName().getUserName(), "导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST, lvList);
		request.getSession().removeAttribute(fileUuid);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<LBaContWarehouseMaterialEntity> list() {
		List<LBaContWarehouseMaterialEntity> listLBaContWarehouseMaterials = lBaContWarehouseMaterialService.getList(LBaContWarehouseMaterialEntity.class);
		return listLBaContWarehouseMaterials;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		LBaContWarehouseMaterialEntity task = lBaContWarehouseMaterialService.get(LBaContWarehouseMaterialEntity.class,id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody LBaContWarehouseMaterialEntity lBaContWarehouseMaterial,UriComponentsBuilder uriBuilder) {
		// 调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<LBaContWarehouseMaterialEntity>> failures = validator.validate(lBaContWarehouseMaterial);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		// 保存
		try {
			lBaContWarehouseMaterialService.save(lBaContWarehouseMaterial);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		// 按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = lBaContWarehouseMaterial.getId();
		URI uri = uriBuilder.path("/rest/lBaContWarehouseMaterialController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody LBaContWarehouseMaterialEntity lBaContWarehouseMaterial) {
		// 调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<LBaContWarehouseMaterialEntity>> failures = validator.validate(lBaContWarehouseMaterial);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		// 保存
		try {
			lBaContWarehouseMaterialService.saveOrUpdate(lBaContWarehouseMaterial);
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
		lBaContWarehouseMaterialService.deleteEntityById(LBaContWarehouseMaterialEntity.class, id);
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
	public void mateDatagrid(LBaContWarehouseMaterialEntity lBahousemate,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		try{
		//自定义追加查询条件
			String wareHouseId = lBahousemate.getWarehouseId();
			String mateId = request.getParameter("mateId");
			if(StringUtil.isEmpty(wareHouseId)){
				TagUtil.datagrid(response, dataGrid);
				return;
			}
			StringBuffer sql = new StringBuffer();
			sql.append(" from l_ba_material a WHERE id not in ( select material_id from  l_ba_cont_warehouse_material b where b.warehouse_id = '"+wareHouseId+"' ) ");
			if(StringUtil.isNotEmpty(mateId)){
				sql.append(" and  ( a.material_code like '%"+mateId+"%' or  a.material_name like '%"+mateId+"%' )");
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
