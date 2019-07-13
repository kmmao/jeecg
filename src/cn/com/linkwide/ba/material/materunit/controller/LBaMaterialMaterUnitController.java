package cn.com.linkwide.ba.material.materunit.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
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
import org.jeecgframework.web.system.pojo.base.TSBaseUser;
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

import cn.com.linkwide.ba.material.materunit.entity.LBaMaterialMaterUnitEntity;
import cn.com.linkwide.ba.material.materunit.entity.LBaMaterialMaterUnitEntityVo;
import cn.com.linkwide.ba.material.materunit.service.LBaMaterialMaterUnitServiceI;
import cn.com.linkwide.ba.material.measureunit.entity.LBaMaterialMeasureUnitEntity;
import cn.com.linkwide.ba.material.record.entity.LBaMaterialEntity;
import cn.com.linkwide.common.util.controller.FilePathDefault;

/**
 * @Title: Controller
 * @Description: l_ba_material_mater_unit
 * @author onlineGenerator
 * @date 2017-11-14 13:37:29
 * @version V1.0
 *
 */
@Controller
@RequestMapping("/lBaMaterialMaterUnitController")
public class LBaMaterialMaterUnitController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(LBaMaterialMaterUnitController.class);

	@Autowired
	private LBaMaterialMaterUnitServiceI lBaMaterialMaterUnitService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;

	/**
	 * 计量单位列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("cn/com/linkwide/ba/material/materunit/lBaMaterialMaterUnitList");
	}

	/**
	 * 计量单位列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "listForSelect")
	public ModelAndView listForSelect(HttpServletRequest request) {
		return new ModelAndView("cn/com/linkwide/ba/material/materunit/lBaMaterialMaterUnitListForSelect");
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
	public void datagrid(LBaMaterialMaterUnitEntity lBaMaterialMaterUnit, HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(LBaMaterialMaterUnitEntity.class, dataGrid);
		// 查询条件组装器
		if (StringUtils.isNotEmpty(lBaMaterialMaterUnit.getTypeName())) {
			lBaMaterialMaterUnit.setTypeName("*" + lBaMaterialMaterUnit.getTypeName() + "*");
		}

		if (StringUtils.isNotEmpty(lBaMaterialMaterUnit.getEnglishNameComplex())) {
			lBaMaterialMaterUnit.setEnglishNameComplex("*" + lBaMaterialMaterUnit.getEnglishNameComplex() + "*");
		}

		if (StringUtils.isNotEmpty(lBaMaterialMaterUnit.getEnglishNameSingular())) {
			lBaMaterialMaterUnit.setEnglishNameSingular("*" + lBaMaterialMaterUnit.getEnglishNameSingular() + "*");
		}
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, lBaMaterialMaterUnit,request.getParameterMap());
		try {
			// 自定义追加查询条件
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.lBaMaterialMaterUnitService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除计量单位
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(LBaMaterialMaterUnitEntity lBaMaterialMaterUnit, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		lBaMaterialMaterUnit = systemService.getEntity(LBaMaterialMaterUnitEntity.class, lBaMaterialMaterUnit.getId());
		message = "计量单位删除成功";
		try {
			// 删除校验
			AjaxJson jj = valiDel(lBaMaterialMaterUnit.getId());
			if (!jj.isSuccess()) {
				jj.setSuccess(true);
				return jj;
			}

			lBaMaterialMaterUnitService.delete(lBaMaterialMaterUnit);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "计量单位删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	public AjaxJson valiDel(String id) {
		AjaxJson j = new AjaxJson();
		// 物资中
		List<LBaMaterialEntity> lBaMaterialEntities = systemService.findByQueryString(" from LBaMaterialEntity where materUnitId = '" + id + "'");
		if (lBaMaterialEntities.size() > 0) {
			j.setMsg("计量单位已经被引用，不可删除");
			j.setSuccess(false);
			return j;
		}
		// 辅助计量单文
		List<LBaMaterialMeasureUnitEntity> list1 = systemService.findByQueryString(" from LBaMaterialMeasureUnitEntity where materUnitId = '" + id + "'");
		if (list1.size() > 0) {
			j.setMsg("计量单位已经被引用，不可删除");
			j.setSuccess(false);
			return j;
		}

		return j;
	}

	/**
	 * 批量删除计量单位
	 * 
	 * @return
	 */
	@RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "计量单位删除成功";
		try {
			for (String id : ids.split(",")) {
				LBaMaterialMaterUnitEntity lBaMaterialMaterUnit = systemService.getEntity(LBaMaterialMaterUnitEntity.class, id);
				// 删除校验
				AjaxJson jj = valiDel(lBaMaterialMaterUnit.getId());
				if (!jj.isSuccess()) {
					jj.setSuccess(true);
					return jj;
				}
				lBaMaterialMaterUnitService.delete(lBaMaterialMaterUnit);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		} catch (Exception e) {
			e.printStackTrace();
			message = "计量单位删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 添加计量单位
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(LBaMaterialMaterUnitEntity lBaMaterialMaterUnit, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "计量单位添加成功";
		try {
			List<LBaMaterialMaterUnitEntity> l1 = lBaMaterialMaterUnitService.findByProperty(LBaMaterialMaterUnitEntity.class, "typeCode", lBaMaterialMaterUnit.getTypeCode());

			if (l1.size() > 0) {
				message = "编码已存在";
				j.setSuccess(false);
				j.setMsg(message);
				return j;
			}

			lBaMaterialMaterUnitService.save(lBaMaterialMaterUnit);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "计量单位添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 更新计量单位
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(LBaMaterialMaterUnitEntity lBaMaterialMaterUnit, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "计量单位更新成功";
		LBaMaterialMaterUnitEntity t = lBaMaterialMaterUnitService.get(LBaMaterialMaterUnitEntity.class,lBaMaterialMaterUnit.getId());
		try {
			List<LBaMaterialMaterUnitEntity> l1 = lBaMaterialMaterUnitService.findByProperty(LBaMaterialMaterUnitEntity.class, "typeCode", lBaMaterialMaterUnit.getTypeCode());
			if (l1.size() > 0) {
				if (!t.getId().equals(l1.get(0).getId())) {
					message = "编码已存在";
					j.setSuccess(false);
					j.setMsg(message);
					return j;
				}
			}

			MyBeanUtils.copyBeanNotNull2Bean(lBaMaterialMaterUnit, t);
			lBaMaterialMaterUnitService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "计量单位更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 计量单位新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(LBaMaterialMaterUnitEntity lBaMaterialMaterUnit, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(lBaMaterialMaterUnit.getId())) {
			lBaMaterialMaterUnit = lBaMaterialMaterUnitService.getEntity(LBaMaterialMaterUnitEntity.class,lBaMaterialMaterUnit.getId());
			req.setAttribute("lBaMaterialMaterUnitPage", lBaMaterialMaterUnit);
		}
		return new ModelAndView("cn/com/linkwide/ba/material/materunit/lBaMaterialMaterUnit-add");
	}

	/**
	 * 计量单位编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(LBaMaterialMaterUnitEntity lBaMaterialMaterUnit, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(lBaMaterialMaterUnit.getId())) {
			lBaMaterialMaterUnit = lBaMaterialMaterUnitService.getEntity(LBaMaterialMaterUnitEntity.class,lBaMaterialMaterUnit.getId());
			req.setAttribute("lBaMaterialMaterUnitPage", lBaMaterialMaterUnit);
		}
		return new ModelAndView("cn/com/linkwide/ba/material/materunit/lBaMaterialMaterUnit-update");
	}

	/**
	 * 启用 停用
	 * 
	 * @return
	 */
	@RequestMapping(params = "doEnDisAble")
	@ResponseBody
	public AjaxJson doEnable(String ids, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		String message = "修改成功";
		try {
			String status = request.getParameter("status");

			for (String id : ids.split(",")) {
				LBaMaterialMaterUnitEntity lBaMaterialMaterUnitEntity = lBaMaterialMaterUnitService.getEntity(LBaMaterialMaterUnitEntity.class, id);

				// 是否停用 0：否 1：是
				if ("1".equals(status) && "0".equals(lBaMaterialMaterUnitEntity.getStatus())) {

					// 删除校验
					AjaxJson jj = valiDel(lBaMaterialMaterUnitEntity.getId());
					if (!jj.isSuccess()) {
						jj.setMsg("计量单位已经被引用，不可停用");
						jj.setSuccess(true);
						return jj;
					}

					lBaMaterialMaterUnitEntity.setStatus(status);
					lBaMaterialMaterUnitService.saveOrUpdate(lBaMaterialMaterUnitEntity);
				} else if ("0".equals(status) && "1".equals(lBaMaterialMaterUnitEntity.getStatus())) {
					lBaMaterialMaterUnitEntity.setStatus(status);
					lBaMaterialMaterUnitService.saveOrUpdate(lBaMaterialMaterUnitEntity);
				}

				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			}
		} catch (Exception e) {
			e.printStackTrace();
			message = "修改失败";
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
		req.setAttribute("controller_name", "lBaMaterialMaterUnitController");
		req.setAttribute("datagridList_name", "lBaMaterialMaterUnitList");
		req.setAttribute("filePath", FilePathDefault.MATERUNITPATH);
		// return new ModelAndView("common/upload/pub_excel_upload");
		return new ModelAndView("common/upload/excelImport");
	}

	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(String ids,LBaMaterialMaterUnitEntity lBaMaterialMaterUnit, HttpServletRequest request,HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(LBaMaterialMaterUnitEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, lBaMaterialMaterUnit,request.getParameterMap());
		//List<LBaMaterialMaterUnitEntity> lBaMaterialMaterUnits = this.lBaMaterialMaterUnitService.getListByCriteriaQuery(cq, false);
		ids = ids.replace(",", "','");
		List<LBaMaterialMaterUnitEntity> lBaMaterialMaterUnits = lBaMaterialMaterUnitService.getDataForExport(ids);
		lBaMaterialMaterUnits = this.getExportData(lBaMaterialMaterUnits);
		modelMap.put(NormalExcelConstants.FILE_NAME, "计量单位");
		modelMap.put(NormalExcelConstants.CLASS, LBaMaterialMaterUnitEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("计量单位列表", "导出人:" + ResourceUtil.getSessionUserName().getUserName(), "导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST, lBaMaterialMaterUnits);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}

	/**
	 * 封装导出的数据
	 */
	private List<LBaMaterialMaterUnitEntity> getExportData(List<LBaMaterialMaterUnitEntity> lBaMaterialMaterUnits) {
		for (LBaMaterialMaterUnitEntity lBaMaterialMaterUnit : lBaMaterialMaterUnits) {
			lBaMaterialMaterUnit.setStatus(transData(lBaMaterialMaterUnit.getStatus()));
		}
		return lBaMaterialMaterUnits;
	}

	/**
	 * 将0,1与是否之间的转换
	 */
	private String transData(String str) {
		String s = null;
		if ("0".equals(str)) {
			s = "否";
		} else if ("1".equals(str)) {
			s = "是";
		} else if ("是".equals(str)) {
			s = "1";
		} else {
			s = "0";
		}
		return s;
	}

	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(LBaMaterialMaterUnitEntity lBaMaterialMaterUnit, HttpServletRequest request,HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
		List<LBaMaterialMaterUnitEntity> arrayList = new ArrayList<LBaMaterialMaterUnitEntity>();
		LBaMaterialMaterUnitEntity lu = new LBaMaterialMaterUnitEntity();
		lu.setTypeCode("请设置单元格格式为文本");
		arrayList.add(lu);
		modelMap.put(NormalExcelConstants.FILE_NAME, "计量单位");
		modelMap.put(NormalExcelConstants.CLASS, LBaMaterialMaterUnitEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("计量单位列表", "导出人:" + ResourceUtil.getSessionUserName().getUserName(), "导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST, arrayList);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(params = "importExcel", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson importExcel(HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		// 信息提示
		String msg = "文件导入成功！";
		// 标识
		boolean flag = true;
		// 返回参数
		Map<String, Object> map = new HashMap<String, Object>();
		// 设置随机UUID
		String uuid = UUID.randomUUID().toString();
		uuid = uuid.replace("-", "");
		String filePath = request.getParameter("filePath");
		String realPath = request.getSession().getServletContext().getRealPath("/") + "/" + filePath; // 文件硬盘的真实路径

		File f = new File(realPath);
		FileInputStream fin = null;

		ImportParams params = new ImportParams();
		params.setTitleRows(2);
		params.setHeadRows(1);
		params.setNeedSave(true);
		try {
			fin = new FileInputStream(f);
			List<LBaMaterialMaterUnitEntity> listLBaMaterialMaterUnitEntitys = ExcelImportUtil.importExcel(fin,LBaMaterialMaterUnitEntity.class, params);
			// 存放错误信息
			List<LBaMaterialMaterUnitEntityVo> errMsgList = new ArrayList<LBaMaterialMaterUnitEntityVo>();
			// 查询所有的计量单位
			List<LBaMaterialMaterUnitEntity> allUnitList = systemService.getList(LBaMaterialMaterUnitEntity.class);

			if (listLBaMaterialMaterUnitEntitys.size() == 0) {
				msg = "请先维护计量单位信息;";
				j.setMsg(msg);
				return j;
			}
			for (LBaMaterialMaterUnitEntity lBaMaterialMaterUnit : listLBaMaterialMaterUnitEntitys) {
				// 存放信息描述
				String errMsg = "";
				// 非空校验
				errMsg = this.vailNull(lBaMaterialMaterUnit, errMsg);
				// 进行重复校验
				errMsg = this.vailRepetData(allUnitList, lBaMaterialMaterUnit, errMsg);
				// 校验是否停用输入的为是或否
				errMsg = this.vailExcelData(lBaMaterialMaterUnit, errMsg);
				if (!errMsg.equals("")) {
					LBaMaterialMaterUnitEntityVo lmuVo = this.getMaterUnitErrMsg(lBaMaterialMaterUnit, errMsg);
					errMsgList.add(lmuVo);
					msg = "文件导入存在部分失败";
					map.put("fileUuid", uuid);
				} else {
					lBaMaterialMaterUnit.setStatus(transData(lBaMaterialMaterUnit.getStatus()));
					lBaMaterialMaterUnitService.save(lBaMaterialMaterUnit);
				}
			}
			request.getSession().setAttribute(uuid, errMsgList);
			// j.setMsg("文件导入成功！");
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

		List<LBaMaterialMaterUnitEntityVo> lvList = (List<LBaMaterialMaterUnitEntityVo>) request.getSession().getAttribute(fileUuid);

		modelMap.put(NormalExcelConstants.FILE_NAME, "导入结果");
		modelMap.put(NormalExcelConstants.CLASS, LBaMaterialMaterUnitEntityVo.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("导入结果列表", "导出人：" + ResourceUtil.getSessionUserName().getUserName(), "导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST, lvList);
		request.getSession().removeAttribute(fileUuid);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}

	/**
	 * 组装错误信息
	 * 
	 * @param lBaMaterialMaterUnit
	 * @param errMsg
	 * @return
	 */
	private LBaMaterialMaterUnitEntityVo getMaterUnitErrMsg(LBaMaterialMaterUnitEntity lBaMaterialMaterUnit,String errMsg) {
		LBaMaterialMaterUnitEntityVo lv = new LBaMaterialMaterUnitEntityVo();
		lv.setTypeCode(lBaMaterialMaterUnit.getTypeCode());
		lv.setTypeName(lBaMaterialMaterUnit.getTypeName());
		lv.setEnglishNameSingular(lBaMaterialMaterUnit.getEnglishNameSingular());
		lv.setEnglishNameComplex(lBaMaterialMaterUnit.getEnglishNameComplex());
		lv.setStatus(lBaMaterialMaterUnit.getStatus());
		lv.setErrMsg(errMsg);
		return lv;
	}

	/**
	 * 导入的信息进行非空校验
	 * 
	 * @param lBaMaterialMaterUnit
	 * @param errMsg
	 * @return
	 */
	private String vailNull(LBaMaterialMaterUnitEntity lBaMaterialMaterUnit, String errMsg) {
		String typeCode = lBaMaterialMaterUnit.getTypeCode();
		if (StringUtil.isEmpty(typeCode)) {
			errMsg = errMsg + "计量单位编码不能为空;";
		}
		String typeName = lBaMaterialMaterUnit.getTypeName();
		if (StringUtil.isEmpty(typeName)) {
			errMsg = errMsg + "计量单位名称不能为空;";
		}
		String englishNameSingular = lBaMaterialMaterUnit.getEnglishNameSingular();
		if (StringUtil.isEmpty(englishNameSingular)) {
			errMsg = errMsg + "英文名称单数不能为空;";
		}
		String englishNameComplex = lBaMaterialMaterUnit.getEnglishNameComplex();
		if (StringUtil.isEmpty(englishNameComplex)) {
			errMsg = errMsg + "英文名称复数不能为空;";
		}
		String status = lBaMaterialMaterUnit.getStatus();
		if (StringUtil.isEmpty(status)) {
			errMsg = errMsg + "是否停用不能为空,请填写是或否;";
		}
		return errMsg;
	}

	/**
	 * 根据计量单位编码或计量单位名称查询数据库中的数据
	 * 
	 * @return
	 */
	private LBaMaterialMaterUnitEntity getMaterUnitByTypeCodeOrName(List<LBaMaterialMaterUnitEntity> allUnitList,String typeCode, String typeName) {
		LBaMaterialMaterUnitEntity lBaMaterialMaterUnit = null;
		if (StringUtil.isNotEmpty(typeCode)) {
			for (LBaMaterialMaterUnitEntity lBaMaterialMaterUnitEntity : allUnitList) {
				if (typeCode.equals(lBaMaterialMaterUnitEntity.getTypeCode())) {
					lBaMaterialMaterUnit = lBaMaterialMaterUnitEntity;
				}
			}
		}
		if (StringUtil.isNotEmpty(typeName)) {
			for (LBaMaterialMaterUnitEntity lBaMaterialMaterUnitEntity : allUnitList) {
				if (typeName.equals(lBaMaterialMaterUnitEntity.getTypeName())) {
					lBaMaterialMaterUnit = lBaMaterialMaterUnitEntity;
				}
			}
		}
		return lBaMaterialMaterUnit;
	}

	/**
	 * 校验重复数据
	 * 
	 * @return
	 */
	private String vailRepetData(List<LBaMaterialMaterUnitEntity> allUnitList,LBaMaterialMaterUnitEntity lBaMaterialMaterUnit, String errMsg) {
		LBaMaterialMaterUnitEntity lmCode = this.getMaterUnitByTypeCodeOrName(allUnitList,lBaMaterialMaterUnit.getTypeCode(), null);
		if (lmCode != null) {
			errMsg = errMsg + "计量单位编码已存在;";
		}
		LBaMaterialMaterUnitEntity lmName = this.getMaterUnitByTypeCodeOrName(allUnitList, null,lBaMaterialMaterUnit.getTypeName());
		if (lmName != null) {
			errMsg = errMsg + "计量单位名称已存在;";
		}
		return errMsg;
	}
	/**
	 * 校验是否停用输入的为是或否
	 * @return
	 */
	private String vailExcelData(LBaMaterialMaterUnitEntity lBaMaterialMaterUnit,String errMsg){
		String status = lBaMaterialMaterUnit.getStatus();
		if (!"是".equals(status) && !"否".equals(status)) {
			errMsg = errMsg + "是否停用请输入是或否;";
		}
		return errMsg;
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<LBaMaterialMaterUnitEntity> list() {
		List<LBaMaterialMaterUnitEntity> listLBaMaterialMaterUnits = lBaMaterialMaterUnitService.getList(LBaMaterialMaterUnitEntity.class);
		return listLBaMaterialMaterUnits;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		LBaMaterialMaterUnitEntity task = lBaMaterialMaterUnitService.get(LBaMaterialMaterUnitEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody LBaMaterialMaterUnitEntity lBaMaterialMaterUnit,
			UriComponentsBuilder uriBuilder) {
		// 调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<LBaMaterialMaterUnitEntity>> failures = validator.validate(lBaMaterialMaterUnit);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		// 保存
		try {
			lBaMaterialMaterUnitService.save(lBaMaterialMaterUnit);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		// 按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = lBaMaterialMaterUnit.getId();
		URI uri = uriBuilder.path("/rest/lBaMaterialMaterUnitController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody LBaMaterialMaterUnitEntity lBaMaterialMaterUnit) {
		// 调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<LBaMaterialMaterUnitEntity>> failures = validator.validate(lBaMaterialMaterUnit);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		// 保存
		try {
			lBaMaterialMaterUnitService.saveOrUpdate(lBaMaterialMaterUnit);
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
		lBaMaterialMaterUnitService.deleteEntityById(LBaMaterialMaterUnitEntity.class, id);
	}
	
	/**
	 * 计量单位模糊查询
	 * 
	 * @author zhuhd 2018-3-27
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(params = "incl")
	@ResponseBody
	public void incl(LBaMaterialMaterUnitEntity lBaMaterialMaterUnit,HttpServletRequest request,DataGrid dataGrid, HttpServletResponse response) throws Exception {
		String q = request.getParameter("q") != null ? request.getParameter("q") : "";
		CriteriaQuery cq = new CriteriaQuery(LBaMaterialMaterUnitEntity.class, dataGrid);
		dataGrid.setField("id,typeCode,typeName,");
		String defaultVal = request.getParameter("defaultVal") != null ? request.getParameter("defaultVal") : "";
		// 查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, lBaMaterialMaterUnit);
		try {
			// 自定义追加查询条件
			cq.add(Restrictions.eq("status", "0"));
			Disjunction disjunction = Restrictions.disjunction();
			disjunction.add(Restrictions.like("typeCode", q,MatchMode.ANYWHERE).ignoreCase());
			disjunction.add(Restrictions.like("typeName", q,MatchMode.ANYWHERE).ignoreCase());
			cq.add(disjunction);
			cq.add();
			this.lBaMaterialMaterUnitService.getDataGridReturn(cq, true);
			if(StringUtil.isNotEmpty(defaultVal) && StringUtil.isEmpty(q)){
				String[]  defArray = defaultVal.split(",");
				if(defArray.length>1 && StringUtil.isNotEmpty(defArray[1]) ){
					LBaMaterialMaterUnitEntity d = systemService.findUniqueByProperty(LBaMaterialMaterUnitEntity.class, defArray[0], defArray[1]);
					dataGrid.getResults().add(d);
				} 
			}
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除计量单位
	 * 
	 * @return
	 */
	@RequestMapping(params = "queryAllUnit")
	@ResponseBody
	public AjaxJson queryAllUnit(LBaMaterialMaterUnitEntity lBaMaterialMaterUnit, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "计量单位查询成功";
		try {
			List<Map<String,Object>> list = systemService.findForJdbc("select id \"id\",type_code \"typeCode\",type_name \"typeName\" from l_ba_material_mater_unit ");
			Map<String,Map<String,Object>> map = systemService.list2Map(list, "id");
			j.setObj(map);
		} catch (Exception e) {
			e.printStackTrace();
			message = "计量单位查询失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

}
