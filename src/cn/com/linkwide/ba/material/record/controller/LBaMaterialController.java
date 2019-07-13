package cn.com.linkwide.ba.material.record.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.apache.log4j.Logger;
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
import org.jeecgframework.core.util.PinyinUtil;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.poi.exception.excel.ExcelImportException;
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
import cn.com.linkwide.ba.material.appatype.entity.LBaMaterialAppaTypeEntity;
import cn.com.linkwide.ba.material.financetype.entity.LBaMaterialFinanceTypeEntity;
import cn.com.linkwide.ba.material.materunit.entity.LBaMaterialMaterUnitEntity;
import cn.com.linkwide.ba.material.record.entity.LBaMaterialEntity;
import cn.com.linkwide.ba.material.record.entity.LBaMaterialEntityErrMsgVo;
import cn.com.linkwide.ba.material.record.entity.LBaMaterialEntityVo;
import cn.com.linkwide.ba.material.record.entity.LBaMaterialPage;
import cn.com.linkwide.ba.material.record.service.LBaMaterialServiceI;
import cn.com.linkwide.ba.material.standtype.entity.LBaMaterialStandTypeEntity;
import cn.com.linkwide.ba.material.type.entity.LBaMaterialTypeEntity;
import cn.com.linkwide.ba.supplier.entity.LBaSupplierEntity;
import cn.com.linkwide.ba.warehouse.entity.LBaWarehouseEntity;
import cn.com.linkwide.common.util.controller.FilePathDefault;
import net.sf.json.JSONObject;

/**
 * @Title: Controller
 * @Description: 物资档案
 * @author onlineGenerator
 * @date 2017-11-15 10:28:43
 * @version V1.0
 *
 */
@Controller
@RequestMapping("/lBaMaterialController")
public class LBaMaterialController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(LBaMaterialController.class);

	@Autowired
	private LBaMaterialServiceI lBaMaterialService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	/**
	 * 物资档案列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("cn/com/linkwide/ba/material/record/lBaMaterialList");
	}

	@RequestMapping(params = "listForSelectMuli")
	public ModelAndView listForSelectMuli(HttpServletRequest request) {
		return new ModelAndView("cn/com/linkwide/ba/material/record/lBaMaterialListForSelectMuli");
	}

	@RequestMapping(params = "listForSelect")
	public ModelAndView listForSelect(HttpServletRequest request) {
		String sid = request.getParameter("sid");// 供应商id
		request.setAttribute("sid", sid);
		String wid = request.getParameter("wid");// 仓库id
		request.setAttribute("wid", wid);
		String isHighCons = request.getParameter("isHighCons"); // 是否是高值耗材
		request.setAttribute("isHighCons", isHighCons);
		return new ModelAndView("cn/com/linkwide/ba/material/record/lBaMaterialListForSelect");
	}

	/**
	 * easyui AJAX请求数据 根据资质过滤 资质过期的不展示
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(LBaMaterialEntity lBaMaterial, HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(LBaMaterialEntity.class, dataGrid);
		// 查询条件组装器
		if (StringUtil.isNotEmpty(lBaMaterial.getMaterialName())) {
			lBaMaterial.setMaterialName("*" + lBaMaterial.getMaterialName() + "*");
		}

		if (StringUtil.isNotEmpty(lBaMaterial.getMaterialCode())) {
			lBaMaterial.setMaterialCode("*" + lBaMaterial.getMaterialCode() + "*");
		}

		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, lBaMaterial, request.getParameterMap());
		try {
			// 自定义追加查询条件
			cq.addOrder("createDate", SortDirection.desc);
			String wid = request.getParameter("wid");// 仓库id

			Set<String> bmid = new HashSet<String>();
			if (StringUtil.isNotEmpty(wid)) {
				List<String> mid = systemService.findListbySql(" select material_id from l_ba_cont_warehouse_material where warehouse_id = '" + wid + "'");
				bmid.addAll(mid);
			}

			String sid = request.getParameter("sid");// 仓库id
			if (StringUtil.isNotEmpty(sid)) {
				List<String> smids = systemService.findListbySql(" select material_id from l_ba_cont_supplier_material where supplier_id = '" + sid + "'");
				bmid.addAll(smids);
			}
			List<String> mmid = lBaMaterialService.getOverTimeMaterialIds(null);
			bmid.addAll(mmid);

			if (bmid.size() > 0) {
				cq.in("id", bmid.toArray());
			}

			String materialTypeIds = request.getParameter("materialTypeIds");
			if (StringUtil.isNotEmpty(materialTypeIds)) {
				cq.in("materialTypeId", materialTypeIds.split(","));
			}
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.lBaMaterialService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	@RequestMapping(params = "datagridAll")
	public void datagridAll(LBaMaterialEntity lBaMaterial, HttpServletRequest request, HttpServletResponse response,DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(LBaMaterialEntity.class, dataGrid);
		// 查询条件组装器
		if (StringUtil.isNotEmpty(lBaMaterial.getMaterialName())) {
			lBaMaterial.setMaterialName("*" + lBaMaterial.getMaterialName() + "*");
		}

		if (StringUtil.isNotEmpty(lBaMaterial.getMaterialCode())) {
			lBaMaterial.setMaterialCode("*" + lBaMaterial.getMaterialCode() + "*");
		}

		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, lBaMaterial, request.getParameterMap());
		try {
			// 自定义追加查询条件
			cq.addOrder("createDate", SortDirection.desc);
			String wid = request.getParameter("wid");// 仓库id
			Set<String> bmid = new HashSet<String>();
			if (StringUtil.isNotEmpty(wid)) {
				List<String> wmids = systemService.findListbySql(" select material_id from l_ba_cont_warehouse_material where warehouse_id = '" + wid + "'");
				bmid.addAll(wmids);
			}

			String sid = request.getParameter("sid");// 仓库id
			if (StringUtil.isNotEmpty(sid)) {
				List<String> smids = systemService.findListbySql(" select material_id from l_ba_cont_supplier_material where supplier_id = '" + sid + "'");
				bmid.addAll(smids);
			}

			if (bmid.size() > 0) {
				cq.in("id", bmid.toArray());
			}

			String materialTypeIds = request.getParameter("materialTypeIds");
			if (StringUtil.isNotEmpty(materialTypeIds)) {
				cq.in("materialTypeId", materialTypeIds.split(","));
			}

		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.lBaMaterialService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 蓝单入库选择物资
	 * 
	 * @param lBaMaterial
	 * @param request
	 * @param response
	 * @param dataGrid
	 */
	@RequestMapping(params = "datagridBlue")
	@ResponseBody
	public List<LBaMaterialEntity> datagridBlue(LBaMaterialEntity lBaMaterial, HttpServletRequest request,HttpServletResponse response, DataGrid dataGrid) {
		String wid = request.getParameter("wid");// 仓库id	
		String sid = request.getParameter("sid");// 供应商id
		List<LBaMaterialEntity> list1 = new ArrayList<LBaMaterialEntity>();
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	 		String date1 = simpleDateFormat.format(new Date());
			StringBuffer sql = new StringBuffer();
			sql.append("select material.id  \"id\",material_code  \"materialCode\",material_name  \"materialName\",material_type_id  \"materialTypeId\",spec_model  \"specModel\",");
			sql.append("monm_code  \"monmCode\",unit.type_name  \"materUnitName\",appa_type_id  \"appaTypeId\",finance_type_id  \"financeTypeId\",");
			sql.append(" stand_type_id  \"standTypeId\",is_cons  \"isCons\",is_assets  \"isAssets\",is_mater  \"isMater\",max_stock  \"maxStock\",");
			sql.append("safe_stock  \"safeStock\",min_stock  \"minStock\",is_batch  \"isBatch\",is_shelf_life  \"isShelfLife\",shelf_life  \"shelfLife\",");
			sql.append(" is_quality  \"isQuality\",unit_price  \"unitPrice\",is_high_cons  \"isHighCons\",is_bar_code  \"isBarCode\",bartype  \"bartype\",bar_code  \"barCode\",is_sequence_manage  \"isSequenceManage\", ");
			sql.append("value_method  \"valueMethod\",manufacturer \"manufacturer\",material_save_mode  \"materialSaveMode\" ,save_temperature  \"saveTemperature\" ");
			if(StringUtil.isNotEmpty(sid)){
				sql.append(",csm.supplier_id  supplierId");
			}
			if(StringUtil.isNotEmpty(wid)){
				sql.append(",cwm.warehouse_id  warehouseId");
			}
			sql.append(" from l_ba_material material ");
			if (StringUtil.isNotEmpty(sid)) {
				sql.append(" INNER JOIN l_ba_cont_supplier_material csm on (material.ID = csm.material_id and csm.supplier_id = '"
								+ sid + "') ");
			}

			if (StringUtil.isNotEmpty(wid)) {
				sql.append(" INNER JOIN l_ba_cont_warehouse_material cwm on (material.ID = cwm.material_id and cwm.warehouse_id = '"
								+ wid + "') ");
			}
			sql.append(" LEFT JOIN l_ba_material_mater_unit unit ON material.mater_unit_id = unit.ID ");
			sql.append(" where 1=1  ");
			String isBarCode = request.getParameter("isBarCode");
			if(StringUtil.isNotEmpty(isBarCode)){
				sql.append(" and is_bar_code = '"+isBarCode+"'");
			}
			String isHighCons = request.getParameter("isHighCons");
			if(StringUtil.isNotEmpty(isHighCons)){
				sql.append(" and is_high_cons = '"+isHighCons+"'");
			}
			String isAssets = request.getParameter("isAssets");
			if(StringUtil.isNotEmpty(isAssets)){
				sql.append(" and is_assets = '"+isAssets+"'");
			}
			//下面是物资的资质验证由于资质改动此处先屏蔽掉
	/*		sql.append("and  material.id not IN ( ");
			sql.append(" select DISTINCT qual.material_id ");
			sql.append(" from l_su_material_qual qual ");
			sql.append(" LEFT JOIN l_su_material_qual_item item on item.material_qual_id = qual.ID ");
			sql.append(" LEFT JOIN l_su_qual_type type on type.id = item.qual_type_id ");
			sql.append(" where qual.audit_status = '"+BillStatus.APPROVED+"' ");
			sql.append(" and (item.effect_date > '"+date1+"' OR item.over_date < '"+date1+"' ) ");
			sql.append(" GROUP BY type.id,qual.material_id ");
			sql.append(" )");*/
			List<Map<String, Object>> list = systemService.findForJdbc(sql.toString());

			for (Map<String, Object> map : list) {
				JSONObject jsonObject = JSONObject.fromObject(map);
				LBaMaterialEntity l = (LBaMaterialEntity) JSONObject.toBean(jsonObject, LBaMaterialEntity.class); 
				//l.setMonmCode(l.getId());
				list1.add(l);

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list1;
	}

	/**
	 * 红单入库选择物资
	 * 
	 * @param lBaMaterial
	 * @param request
	 * @param response
	 * @param dataGrid
	 */
	/*基础字典表引用的其他业务屏蔽掉zxl
	 * @RequestMapping(params = "datagridRed")
	@ResponseBody
	public List<LStInventoryDetailVO> datagridRed(LBaMaterialEntity lBaMaterial, HttpServletRequest request,HttpServletResponse response, DataGrid dataGrid) {
		String wid = request.getParameter("wid");// 仓库id
		String sid = request.getParameter("sid");// 供应商id
		String isBarCode = request.getParameter("isBarCode");// 供应商id
		List<LStInventoryDetailVO> list;
		List<LStInventoryDetailVO> list1 = new ArrayList<LStInventoryDetailVO>();
		try {
			list = lStInventoryService.getNotEmptyAllMaterialByBarCode(wid, "2", isBarCode);11

			List<String> mids = systemService.findListbySql("select material_id from l_ba_cont_supplier_material where supplier_id = '" + sid + "'");

			for (LStInventoryDetailVO vo : list) {
				if (mids.indexOf(vo.getMaterialId()) > -1) {
					vo.setId(vo.getMaterialId());
					list1.add(vo);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list1;
	}
*/
	public AjaxJson validel(LBaMaterialEntity lBaMaterial) {

		AjaxJson json = new AjaxJson();
		json.setMsg("校验成功");

		String sql = "select '条形码'  bil from l_ba_barcode where material_id = '" + lBaMaterial.getId() + "' "
				+ "UNION select '供应商物资参照'  bil from l_ba_cont_supplier_material where material_id = '"+ lBaMaterial.getId() + "' "
				+ "UNION select '仓库物资参照'  bil from l_ba_cont_warehouse_material where material_id = '"+ lBaMaterial.getId() + "' "
				+ "UNION select '领用申请'  bil from l_good_request_detail where material_id = '" + lBaMaterial.getId()
				+ "' " + "UNION select '高值耗材与HIS收费项目对照'  bil from l_hi_hischarge_mate_rela where material_id = '"+ lBaMaterial.getId() + "' "
				+ "UNION select '需求申请'  bil from l_pu_demand_apply_detail where material_id = '" + lBaMaterial.getId()
				+ "' " + "UNION select '采购订单'  bil from l_pu_order_detail where material_id = '" + lBaMaterial.getId()
				+ "' " + "UNION select '采购计划'  bil from l_pu_plan_detail where material_id = '" + lBaMaterial.getId()
				+ "' " + "UNION select '入库单'  bil from l_st_in_ware_detail where material_id = '"+ lBaMaterial.getId() + "' "
				+ "UNION select '到货验收单'  bil from l_st_arri_acce_detail where material_id = '" + lBaMaterial.getId()
				+ "' " + "UNION select '盘点单'  bil from l_st_inventory_detail where material_id = '"+ lBaMaterial.getId() + "' "
				+ "UNION select '调拨申请'  bil from l_st_mobilise_apply_detail where material_id = '"+ lBaMaterial.getId() + "' "
				+ "UNION select '调拨单'  bil from l_st_mobilise_detail where material_id = '" + lBaMaterial.getId()
				+ "' " + "UNION select '月结'  bil from l_st_monthly_period_balance where material_id = '"+ lBaMaterial.getId() + "' "
				+ "UNION select '出库单'  bil from l_st_out_ware_detail where material_id = '" + lBaMaterial.getId()
				+ "' " + "UNION select '快捷出入库'  bil from l_st_quickinout_detail where material_id = '"+ lBaMaterial.getId() + "' "
				+ "UNION select '物资资质'  bil from l_su_material_qual where mate_id = '" + lBaMaterial.getId()
				+ "' " + "UNION select '库存期初'  bil from l_st_ware_balance_detail where material_id = '"+ lBaMaterial.getId() + "' "
				+ "UNION select '物资条形码规则对照'  bil from l_ba_cont_material_barcoderule where material_id = '" + lBaMaterial.getId()+"' ";
		

		List<String> list = systemService.findListbySql(sql);
		if (list.size() > 0) {
			json.setMsg(list.toString() + "引用物资，删除失败");
			json.setSuccess(false);
			return json;
		}

		return json;
	}

	/**
	 * 删除物资档案
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(LBaMaterialEntity lBaMaterial, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		lBaMaterial = systemService.getEntity(LBaMaterialEntity.class, lBaMaterial.getId());
		message = "物资档案删除成功";
		try {
			AjaxJson j1 = validel(lBaMaterial);
			if (!j1.isSuccess()) {
				j1.setSuccess(true);
				return j1;
			}

			lBaMaterialService.delete(lBaMaterial);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			//向集成平台推送数据
			Map operateMap= new HashMap();
			operateMap.put("operation", "DEL");
			lBaMaterialService.invoke(lBaMaterial, operateMap);
		} catch (Exception e) {
			e.printStackTrace();
			message = "物资档案删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 批量删除物资档案
	 * 
	 * @return
	 */
	@RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "物资档案删除成功";
		try {
			for (String id : ids.split(",")) {
				LBaMaterialEntity lBaMaterial = systemService.getEntity(LBaMaterialEntity.class, id);

				AjaxJson j1 = validel(lBaMaterial);
				if (!j1.isSuccess()) {
					j1.setSuccess(true);
					return j1;
				}

				lBaMaterialService.delete(lBaMaterial);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
				//向集成平台推送数据
				Map operateMap= new HashMap();
				operateMap.put("operation", "DEL");
				lBaMaterialService.invoke(lBaMaterial, operateMap);
			}
		} catch (Exception e) {
			e.printStackTrace();
			message = "物资档案删除失败";
			//throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	public AjaxJson valiSub(LBaMaterialEntity lBaMaterial) {
		AjaxJson json = new AjaxJson();
		json.setMsg("校验成功");

		List<LBaMaterialEntity> l1 = systemService.findByQueryString(" from LBaMaterialEntity where materialCode = '"+ lBaMaterial.getMaterialCode() + "' and id != '" + lBaMaterial.getId() + "'");
		if (l1.size() > 0) {
			json.setSuccess(false);
			json.setMsg("编码存在，不能保存");
			return json;
		}

		/*List<LBaMaterialEntity> l2 = systemService.findByQueryString(" from LBaMaterialEntity where materialName = '"
				+ lBaMaterial.getMaterialName() + "' and id != '" + lBaMaterial.getId() + "'");
		if (l2.size() > 0) {
			json.setSuccess(false);
			json.setMsg("名称存在，不能保存");
			return json;
		}*/
		
		if("0".equals(lBaMaterial.getIsHighCons())  && "1".equals(lBaMaterial.getIsBarCode())){
			json.setSuccess(false);
			json.setMsg("只有选择高值才能选择个体码");
			return json;
		}

		return json;
	}

	/**
	 * 添加物资档案
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(LBaMaterialEntity lBaMaterial, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "物资档案添加成功";
		try {
			// 对checkbox类型数据做处理
			getCheckBoxVal(lBaMaterial);
			// 保存校验
			AjaxJson j1 = valiSub(lBaMaterial);
			if (!j1.isSuccess()) {
				return j1;
			}
			// 获取当前登录人所在部门
			String curDepartId = ResourceUtil.getSessionUserName().getDepartid();
			lBaMaterial.setDepartId(curDepartId);
			lBaMaterialService.save(lBaMaterial);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
			//向集成平台推送数据
			Map operateMap= new HashMap();
			operateMap.put("operation", "ADD");
			lBaMaterialService.invoke(lBaMaterial, operateMap);
			
		} catch (Exception e) {
			e.printStackTrace();
			message = "物资档案添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	
	
	/**
	 * 更新物资档案
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(LBaMaterialEntity lBaMaterial, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "物资档案更新成功";
		LBaMaterialEntity t = lBaMaterialService.get(LBaMaterialEntity.class, lBaMaterial.getId());
		try {
			// 对checkbox类型数据做处理
			getCheckBoxVal(lBaMaterial);
			// 保存校验
			AjaxJson j1 = valiSub(lBaMaterial);
			if (!j1.isSuccess()) {
				return j1;
			}
			/*// 验证仓库和供应商
			if (StringUtil.isNotEmpty(lBaMaterial.getSupplierId())) {
				List<LBaContSupplierMaterialEntity> lBaContSupplierMaterialEntities = systemService
												.findByQueryString(" from LBaContSupplierMaterialEntity where supplierId = '"
														+ lBaMaterial.getSupplierId() + "' and materialId = '" + lBaMaterial.getId() + "'");
				//如果没有对应关系则自动补上
				if (lBaContSupplierMaterialEntities.size() <= 0) {
					LBaContSupplierMaterialEntity supMate = new LBaContSupplierMaterialEntity();
					supMate.setSupplierId(lBaMaterial.getSupplierId());
					supMate.setMaterialId(lBaMaterial.getId());
					supMate.setDepartId(ResourceUtil.getSessionUser().getDepartid());
					supMate.setCreateBy(ResourceUtil.getSessionUser().getUserName());
					supMate.setCreateDate(new Date());
					systemService.save(supMate);
					message = "物资和供应商无参照关系，保存失败";
					j.setMsg(message);
					return j;
				}
			}

			if (StringUtil.isNotEmpty(lBaMaterial.getWarehouseId())) {
				List<LBaContWarehouseMaterialEntity> lBaContWarehouseMaterialEntities = systemService
												.findByQueryString(" from LBaContWarehouseMaterialEntity where warehouseId = '"
													+ lBaMaterial.getWarehouseId() + "' and materialId = '" + lBaMaterial.getId() + "'");
				if (lBaContWarehouseMaterialEntities.size() <= 0) {
					//增加仓库物资对照
			 		if(StringUtil.isNotEmpty(lBaMaterial.getWarehouseId())){ 
				 			LBaContWarehouseMaterialEntity sm = new LBaContWarehouseMaterialEntity();
				 			sm.setWarehouseId( lBaMaterial.getWarehouseId());
				 			sm.setMaterialId(lBaMaterial.getId());
				 			sm.setCreateBy(ResourceUtil.getSessionUser().getUserName());
				 			sm.setDepartId(ResourceUtil.getSessionUser().getDepartid());
				 			sm.setCreateDate(new Date());
				 			systemService.save(sm);
				 	}
				}
			}*/
			MyBeanUtils.copyBeanNotNull2Bean(lBaMaterial, t);
			lBaMaterialService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			
			//向集成平台推送数据
			Map operateMap= new HashMap();
			operateMap.put("operation", "UPDATE");
			lBaMaterialService.invoke(t, operateMap);
		} catch (Exception e) {
			e.printStackTrace();
			message = "物资档案更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	// 对checkbox类型数据做处理
	private void getCheckBoxVal(LBaMaterialEntity entity) {
		// TODO Auto-generated method stub
		if (entity.getIsCons() == null) {
			entity.setIsCons(0);
		}
		if (entity.getIsAssets() == null) {
			entity.setIsAssets(0);
			entity.setFinanceTypeId("");
			entity.setStandTypeId("");
			entity.setAppaTypeId("");
		}
		if (entity.getIsMater() == null) {
			entity.setIsMater(0);
		}
		if (entity.getIsBatch() ==null) {
			entity.setIsBatch(0);
		}
		if (entity.getIsShelfLife() == null) {
			entity.setIsShelfLife(0);
		}
		if (entity.getIsHighCons() ==null) {
			entity.setIsHighCons(0);
		}
		if (entity.getIsBarCode()==null) {
			entity.setIsBarCode(0);
		}
		if (entity.getIsInstead() == null) {
			entity.setIsInstead(0);
		}
		if (entity.getIsQuality() == null) {
			entity.setIsQuality(0);
		}
		
		if(entity.getIsControl() == null){
			entity.setIsControl(0);
		}
	}

	/**
	 * 获取助记码
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "getPinYinHead")
	@ResponseBody
	public AjaxJson getPinYinHead(HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "转换成功";
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String mName = request.getParameter("mName");

			if (StringUtil.isNotEmpty(mName)) {
				map.put("zjm", PinyinUtil.getPinYinHeadChar(mName));
			}

			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "转换助记码失败";
			throw new BusinessException(e.getMessage());
		}
		j.setAttributes(map);
		j.setMsg(message);
		return j;
	}

	/**
	 * 物资档案新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(LBaMaterialEntity lBaMaterial, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(lBaMaterial.getId())) {
			lBaMaterial = lBaMaterialService.getEntity(LBaMaterialEntity.class, lBaMaterial.getId());
			req.setAttribute("lBaMaterialPage", lBaMaterial);
		}

		String materialTypeId = req.getParameter("materialTypeId");
		req.setAttribute("materialTypeId", materialTypeId);
		return new ModelAndView("cn/com/linkwide/ba/material/record/lBaMaterial-add");
	}

	/**
	 * 物资档案新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAddbid")
	public ModelAndView goAddbid(LBaMaterialEntity lBaMaterial, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(lBaMaterial.getId())) {
			lBaMaterial = lBaMaterialService.getEntity(LBaMaterialEntity.class, lBaMaterial.getId());
			req.setAttribute("lBaMaterialPage", lBaMaterial);
		}

		String materialTypeId = req.getParameter("materialTypeId");
		req.setAttribute("materialTypeId", materialTypeId);
		return new ModelAndView("cn/com/linkwide/ba/material/record/lBaMaterial-addbid");
	}
	
	/**
	 * 中标物资下拉
	 * 
	 * @author  2018-3-27
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "incl")
	@ResponseBody
	
	public void incl(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		
		try{
			String q = request.getParameter("q") != null ? request.getParameter("q") : "";
			//处理查询条件
			StringBuffer sql = new StringBuffer();
			sql.append(" from mate_supmate_main a");
			sql.append(" left join  l_ba_supplier c on a.supplier_id=c.id  ");
			sql.append(" where ( a.inv_name like '%"+q+"%'");
			sql.append(" or a.spec_model like '%"+q+"%'");
			sql.append(" or a.price ='"+q+"'");
			sql.append(" or c.supplier_full_name like '%"+q+"%')");
			sql.append("and a.state='6'  ");
			String head =" select a.id  id, a.inv_name  invName,a.spec_model  specModel,a.price  price,a.supplier_id  supplierId,c.supplier_full_name  supplierFullName ";
			dataGrid.setResults(systemService.findForJdbc(head+sql.toString(), dataGrid.getPage(), dataGrid.getRows()));
			Integer cont = Integer.valueOf(systemService.getCountForJdbc("select count(a.id) "+sql.toString()).toString());
			dataGrid.setTotal(cont);
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		} 
		TagUtil.datagrid(response, dataGrid);
	}
	
	
	/**
	 * 物资档案编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(LBaMaterialEntity lBaMaterial, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(lBaMaterial.getId())) {
			lBaMaterial = lBaMaterialService.getEntity(LBaMaterialEntity.class, lBaMaterial.getId());
			req.setAttribute("lBaMaterialPage", lBaMaterial);

			LBaMaterialMaterUnitEntity lBaMaterialMaterUnitEntity = systemService.getEntity(LBaMaterialMaterUnitEntity.class, lBaMaterial.getMaterUnitId());
			
		}
		return new ModelAndView("cn/com/linkwide/ba/material/record/lBaMaterial-update");
	}

	/**
	 * 物资分项维护
	 * 
	 * @return
	 */
	@RequestMapping(params = "goBaseInfo")
	public ModelAndView goBaseInfo(LBaMaterialEntity lBaMaterial, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(lBaMaterial.getId())) {
			lBaMaterial = lBaMaterialService.getEntity(LBaMaterialEntity.class, lBaMaterial.getId());
			req.setAttribute("lBaMaterialPage", lBaMaterial);
		}
		return new ModelAndView("cn/com/linkwide/ba/material/record/lBaMaterialBaseInfo");
	}

	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name", "lBaMaterialController");
		req.setAttribute("datagridList_name", "lBaMaterialList");
		req.setAttribute("filePath", FilePathDefault.MATERIALPATH);
		// return new ModelAndView("common/upload/pub_excel_upload");
		return new ModelAndView("common/upload/excelImport");
	}
	
	/**
	 * 更新式导入功能跳转
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "updateUpload")
	public ModelAndView updateUpload(HttpServletRequest req) {
		req.setAttribute("controller_name", "lBaMaterialController");
		req.setAttribute("method_name", "updateImportExcel");
		req.setAttribute("datagridList_name", "lBaMaterialList");
		req.setAttribute("filePath", FilePathDefault.MATERIALPATH);
//		return new ModelAndView("common/upload/pub_excel_upload");
		return new ModelAndView("common/upload/excelImport");
	}

	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(LBaMaterialEntity lBaMaterial,String ids, HttpServletRequest request, HttpServletResponse response,DataGrid dataGrid, ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(LBaMaterialEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, lBaMaterial, request.getParameterMap());
		ids = ids.replace(",", "','");
		//List<LBaMaterialEntity> lBaMaterials = this.lBaMaterialService.getListByCriteriaQuery(cq, false);
		List<LBaMaterialEntity> lBaMaterials = this.lBaMaterialService.getListForExportXls(ids);
		List<LBaMaterialPage> list = this.getExportData(lBaMaterials);
		modelMap.put(NormalExcelConstants.FILE_NAME, "物资档案"); 
		modelMap.put(NormalExcelConstants.CLASS, LBaMaterialPage.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("物资档案列表", "导出人:" + ResourceUtil.getSessionUserName().getUserName(), "导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST, list);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}

	/**
	 * 封装导出的数据
	 */
	private List<LBaMaterialPage> getExportData(List<LBaMaterialEntity> entitys) {
		// 存放封装后的数据
		List<LBaMaterialPage> arrayList = new ArrayList<LBaMaterialPage>();
		// 物资分类所有信息
		List<LBaMaterialTypeEntity> list1 = systemService.getList(LBaMaterialTypeEntity.class);
		// 计量单位所有信息
		List<LBaMaterialMaterUnitEntity> list2 = systemService.getList(LBaMaterialMaterUnitEntity.class);
		// 供应商所有信息
		List<LBaSupplierEntity> list3 = systemService.getList(LBaSupplierEntity.class);
		// 仓库所有信息
		List<LBaWarehouseEntity> list4 = systemService.getList(LBaWarehouseEntity.class);
		// 器械分类所有信息
		List<LBaMaterialAppaTypeEntity> list5 = systemService.getList(LBaMaterialAppaTypeEntity.class);
		// 财务分类所有信息
		List<LBaMaterialFinanceTypeEntity> list6 = systemService.getList(LBaMaterialFinanceTypeEntity.class);
		// 国标分类所有信息
		List<LBaMaterialStandTypeEntity> list7 = systemService.getList(LBaMaterialStandTypeEntity.class);
		for (LBaMaterialEntity entity : entitys) {
			LBaMaterialPage lBaMaterial = new LBaMaterialPage();
			try {
				MyBeanUtils.copyBeanNotNull2Bean(entity, lBaMaterial);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String materialTypeId = lBaMaterial.getMaterialTypeId();
			if (StringUtil.isNotEmpty(materialTypeId)) {
				LBaMaterialTypeEntity materialType = this.getMaterialTypeByIdOrName(list1, materialTypeId, null);
				lBaMaterial.setMaterialTypeId(materialType.getTypeName());
			}
			String appaTypeId = lBaMaterial.getAppaTypeId();
			if (StringUtil.isNotEmpty(appaTypeId)) {
				LBaMaterialAppaTypeEntity appaType = this.getAppaTypeByIdOrName(list5, appaTypeId, null);
				lBaMaterial.setAppaTypeId(appaType.getTypeName());
			}
			String financeTypeId = lBaMaterial.getFinanceTypeId();
			if (StringUtil.isNotEmpty(financeTypeId)) {
				LBaMaterialFinanceTypeEntity financeType = this.getFinanceTypeByIdOrName(list6, financeTypeId, null);
				lBaMaterial.setFinanceTypeId(financeType.getTypeName());
			}
			String standTypeId = lBaMaterial.getStandTypeId();
			if (StringUtil.isNotEmpty(standTypeId)) {
				LBaMaterialStandTypeEntity standType = this.getStandTypeByIdOrName(list7, standTypeId, null);
				lBaMaterial.setStandTypeId(standType.getTypeName());
			}
			
			// 将0,1与是否之间的转换
			lBaMaterial.setIsCons(lBaMaterial.getIsCons());
			lBaMaterial.setIsMater(lBaMaterial.getIsMater());
			lBaMaterial.setIsAssets(lBaMaterial.getIsAssets());
			lBaMaterial.setIsBatch(lBaMaterial.getIsBatch()); 
			lBaMaterial.setIsShelfLife(lBaMaterial.getIsShelfLife());
			lBaMaterial.setIsHighCons(lBaMaterial.getIsHighCons());
			lBaMaterial.setIsBarCode(lBaMaterial.getIsBarCode());
			lBaMaterial.setIsInstead(lBaMaterial.getIsInstead());
			lBaMaterial.setIsQuality(lBaMaterial.getIsQuality());

			arrayList.add(lBaMaterial);
		}
		return arrayList;
	}

	/**
	 * 根据物资编码或物资名称查询
	 */
	private LBaMaterialEntity getMaterial(List<LBaMaterialEntity> list, String code, String name) {
		LBaMaterialEntity le = null;
		if (StringUtil.isNotEmpty(code)) {
			for (LBaMaterialEntity lBaMaterialEntity : list) {
				if (lBaMaterialEntity.getMaterialCode().equals(code)) {
					le = lBaMaterialEntity;
				}
			}
		} else if (StringUtil.isNotEmpty(name)) {
			for (LBaMaterialEntity lBaMaterialEntity : list) {
				if (lBaMaterialEntity.getMaterialName().equals(name)) {
					le = lBaMaterialEntity;
				}
			}
		}
		return le;
	}

	/**
	 * 根据物资分类id或名称查询
	 */
	private LBaMaterialTypeEntity getMaterialTypeByIdOrName(List<LBaMaterialTypeEntity> list, String id, String name) {
		LBaMaterialTypeEntity le = null;
		if (StringUtil.isNotEmpty(id)) {
			for (LBaMaterialTypeEntity lBaMaterialType : list) {
				if (lBaMaterialType.getId().equals(id)) {
					le = lBaMaterialType;
				}
			}
		} else if (StringUtil.isNotEmpty(name)) {
			for (LBaMaterialTypeEntity lBaMaterialType : list) {
				if (lBaMaterialType.getTypeName().equals(name)) {
					le = lBaMaterialType;
				}
			}
		}

		return le;
	}

	
	/**
	 * 根据器械分类id或名称查询
	 */
	private LBaMaterialAppaTypeEntity getAppaTypeByIdOrName(List<LBaMaterialAppaTypeEntity> list, String id,String name) {
		LBaMaterialAppaTypeEntity le = null;
		if (StringUtil.isNotEmpty(id)) {
			for (LBaMaterialAppaTypeEntity lBaMaterialAppaType : list) {
				if (lBaMaterialAppaType.getId().equals(id)) {
					le = lBaMaterialAppaType;
				}
			}
		} else if (StringUtil.isNotEmpty(name)) {
			for (LBaMaterialAppaTypeEntity lBaMaterialAppaType : list) {
				if (lBaMaterialAppaType.getTypeName().equals(name)) {
					le = lBaMaterialAppaType;
				}
			}
		}
		return le;
	}

	/**
	 * 根据国标分类id或名称查询
	 */
	private LBaMaterialStandTypeEntity getStandTypeByIdOrName(List<LBaMaterialStandTypeEntity> list, String id,String name) {
		LBaMaterialStandTypeEntity le = null;
		if (StringUtil.isNotEmpty(id)) {
			for (LBaMaterialStandTypeEntity lBaMaterialStandType : list) {
				if (lBaMaterialStandType.getId().equals(id)) {
					le = lBaMaterialStandType;
				}
			}
		} else if (StringUtil.isNotEmpty(name)) {
			for (LBaMaterialStandTypeEntity lBaMaterialStandType : list) {
				if (lBaMaterialStandType.getTypeName().equals(name)) {
					le = lBaMaterialStandType;
				}
			}
		}
		return le;
	}

	/**
	 * 根据财务分类id或名称查询
	 */
	private LBaMaterialFinanceTypeEntity getFinanceTypeByIdOrName(List<LBaMaterialFinanceTypeEntity> list, String id,String name) {
		LBaMaterialFinanceTypeEntity le = null;
		if (StringUtil.isNotEmpty(id)) {
			for (LBaMaterialFinanceTypeEntity lBaMaterialFinanceType : list) {
				if (lBaMaterialFinanceType.getId().equals(id)) {
					le = lBaMaterialFinanceType;
				}
			}
		} else if (StringUtil.isNotEmpty(name)) {
			for (LBaMaterialFinanceTypeEntity lBaMaterialFinanceType : list) {
				if (lBaMaterialFinanceType.getTypeName().equals(name)) {
					le = lBaMaterialFinanceType;
				}
			}
		}
		return le;
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
	public String exportXlsByT(LBaMaterialEntityVo lBaMaterial, HttpServletRequest request,HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
		List<LBaMaterialEntityVo> arrayList = new ArrayList<LBaMaterialEntityVo>();
		LBaMaterialEntityVo lv = new LBaMaterialEntityVo();
		lv.setMaterialCode("请设置单元格格式为文本");
		lv.setMaxStock("请设置单元格格式为文本");
		lv.setSafeStock("请设置单元格格式为文本");
		lv.setMinStock("请设置单元格格式为文本");
		//lv.setShelfLife("请设置单元格格式为文本");
		lv.setWarningDay("请设置单元格格式为文本");
		lv.setUnitPrice("请设置单元格格式为文本");
		arrayList.add(lv);
		modelMap.put(NormalExcelConstants.FILE_NAME, "物资档案");
		modelMap.put(NormalExcelConstants.CLASS, LBaMaterialEntityVo.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("物资档案列表", "导出人:" + ResourceUtil.getSessionUserName().getUserName(), "导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST, arrayList);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}

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
		int row =4;
		// 存放信息描述
		String errMsg = "";
		try {
			System.out.println("开始导入:"+StringUtil.tj(new Date()));
			fin = new FileInputStream(f);
			List<LBaMaterialEntityVo> listLBaMaterialEntitys = ExcelImportUtil.importExcel(fin,LBaMaterialEntityVo.class, params);
			System.out.println("Excel解析完成:"+StringUtil.tj(new Date()));
			// 保存错误数据
			List<LBaMaterialEntityErrMsgVo> arrayList = new ArrayList<LBaMaterialEntityErrMsgVo>();
			// 物资分类所有信息
			List<LBaMaterialTypeEntity> list1 = systemService.getList(LBaMaterialTypeEntity.class);
			Map<String,LBaMaterialTypeEntity> mateType = systemService.list2Map(LBaMaterialTypeEntity.class, list1, "typeCode");
			// 计量单位所有信息
			List<LBaMaterialMaterUnitEntity> list2 = systemService.getList(LBaMaterialMaterUnitEntity.class);
			Map<String,LBaMaterialMaterUnitEntity> mateUnit = systemService.list2Map(LBaMaterialMaterUnitEntity.class, list2, "typeName");
			// 供应商所有信息
			List<LBaSupplierEntity> list3 = systemService.getList(LBaSupplierEntity.class);
			Map<String,LBaSupplierEntity> supMap = systemService.list2Map(LBaSupplierEntity.class, list3, "supplierFullName");
			// 仓库所有信息
			List<LBaWarehouseEntity> list4 = systemService.getList(LBaWarehouseEntity.class);
			Map<String,LBaWarehouseEntity> housMap = systemService.list2Map(LBaWarehouseEntity.class, list4, "warehouseName");
			// 器械分类所有信息
			List<LBaMaterialAppaTypeEntity> list5 = systemService.getList(LBaMaterialAppaTypeEntity.class); 
			Map<String,LBaMaterialAppaTypeEntity> appTypeMap = systemService.list2Map(LBaMaterialAppaTypeEntity.class, list5, "typeName");
			// 财务分类所有信息
			List<LBaMaterialFinanceTypeEntity> list6 = systemService.getList(LBaMaterialFinanceTypeEntity.class);
			Map<String,LBaMaterialFinanceTypeEntity> finTypeMap = systemService.list2Map(LBaMaterialFinanceTypeEntity.class, list6, "typeName");
			// 国标分类所有信息
			List<LBaMaterialStandTypeEntity> list7 = systemService.getList(LBaMaterialStandTypeEntity.class);
			Map<String,LBaMaterialStandTypeEntity> standTypeMap = systemService.list2Map(LBaMaterialStandTypeEntity.class, list7, "typeName");
			// 物资所有信息
			List<LBaMaterialEntity> list8 = systemService.getList(LBaMaterialEntity.class);
			Map<String,LBaMaterialEntity> mateMap = systemService.list2Map(LBaMaterialEntity.class, list8, "materialCode");
			List<LBaMaterialEntity> materialList = new ArrayList<LBaMaterialEntity>();
			for (LBaMaterialEntityVo lBaMaterial : listLBaMaterialEntitys) {
				lBaMaterial.setMonmCode(PinyinUtil.getPinYinHeadChar(lBaMaterial.getMaterialName()));
				// 非空校验
				errMsg = this.vailNull(lBaMaterial, errMsg);
				// 校验天数
				errMsg = this.vailDate(lBaMaterial, errMsg);
				// 校验条形码
				errMsg = this.vailSuppWareBarUnit(lBaMaterial, errMsg);
				// 校验库存信息以及预估单价
				errMsg = this.vailStock(lBaMaterial, errMsg);
				// 校验耗材，资产
				errMsg = this.vailConsAndAssets(appTypeMap, finTypeMap, standTypeMap, lBaMaterial, errMsg);
				// 校验物资编码
				if (mateMap.get(lBaMaterial.getMaterialCode()) != null) {
					errMsg = errMsg + "物资编码“"+lBaMaterial.getMaterialCode()+"”已存在;";
				} 
				//校验物资分类
				if(mateType.get(lBaMaterial.getMaterialTypeId()) ==null){ 
					errMsg = errMsg + "物资分类编码“"+lBaMaterial.getMaterialTypeId()+"”不存在;";
				}else{
					lBaMaterial.setMaterialTypeId(mateType.get(lBaMaterial.getMaterialTypeId()).getId());
				}
				// 校验计量单位 
				if (mateUnit.get(lBaMaterial.getMaterUnitName()) ==null) {
					errMsg = errMsg + "计量单位“"+lBaMaterial.getMaterUnitName()+"”不存在;";
				}else{
					lBaMaterial.setMaterUnitId(mateUnit.get(lBaMaterial.getMaterUnitName()).getId());
				}
				// 校验供应商 
				if (StringUtil.isNotEmpty(lBaMaterial.getSupplierName())){
					if (supMap.get(lBaMaterial.getSupplierName()) ==null) {
						errMsg = errMsg + "供应商“"+lBaMaterial.getSupplierName()+"”不存在;";
					}else{
						lBaMaterial.setSupplierId(supMap.get(lBaMaterial.getSupplierName()).getId());
					}
				}
				// 校验仓库
				if (StringUtil.isNotEmpty(lBaMaterial.getWarehouseName())){
					if ( housMap.get(lBaMaterial.getWarehouseName()) ==null) {
						errMsg = errMsg + "仓库“"+lBaMaterial.getWarehouseName()+"”不存在;";
					}else{
						lBaMaterial.setWarehouseId(housMap.get(lBaMaterial.getWarehouseName()).getId());
					}
				}
				if (!errMsg.equals("")) {
					LBaMaterialEntityErrMsgVo msgVo = this.getMaterialErrMsg(lBaMaterial, "第"+row+"行数据异常:"+errMsg);
					arrayList.add(msgVo);
					msg = errMsg;
					j.setSuccess(false);
					j.setMsg("第"+row+"行"+errMsg); 
					map.put("fileUuid", uuid);
					return j; 
				} else {
					// 封装后的数据(校验通过)
					LBaMaterialEntity material = this.getMaterial(lBaMaterial);
					material.setMonmCode(PinyinUtil.getPinYinHeadChar(lBaMaterial.getMaterialName()));
					material.setCreateBy(ResourceUtil.getSessionUser().getUserName());
					if(StringUtil.isNotEmpty(material.getExpUnit())){
						if(material.getExpUnit().equals("年")){
							material.setExpUnit("Y");
						}
						if(material.getExpUnit().equals("月")){
							material.setExpUnit("M");
						}
						if(material.getExpUnit().equals("日")){
							material.setExpUnit("D");
						}
					}
					materialList.add(material);
				}
				row ++;
			}
			//开始保存数据
			lBaMaterialService.saveMaterialByList(materialList);
			System.out.println("导入完成:"+StringUtil.tj(new Date()));
			request.getSession().setAttribute(uuid, arrayList);
			j.setMsg("文件导入成功！");
			materialList =null;
			arrayList =null;
			list1 = null;
			mateType = null;
			list2 = null;
			mateUnit = null;
			list3 = null;
			supMap = null;
			list4 = null;
			housMap = null;
			list5 = null; 
			appTypeMap =null;
			list6 = null;
			finTypeMap = null;
			list7 = null;
			standTypeMap = null;
			list8 = null;
		}catch (ExcelImportException e) {
			j.setSuccess(false);
			j.setMsg(e.getErrorMsg());
			logger.error(ExceptionUtil.getExceptionMessage(e)); 
			return j;
		} catch (Exception e) {
			flag = false;
			j.setSuccess(flag);
			msg = "第"+row+"行数据异常:"+errMsg+e.getMessage()+",文件导入失败，请检查!";
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
	 * 物资档案更新式导入
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "updateImportExcel", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson updateImportExcel(HttpServletRequest request, HttpServletResponse response) {
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
		int row =4;
		// 存放信息描述
		String errMsg = "";
		try {
			System.out.println("开始导入:"+StringUtil.tj(new Date()));
			fin = new FileInputStream(f);
			List<LBaMaterialEntityVo> listLBaMaterialEntitys = ExcelImportUtil.importExcel(fin,LBaMaterialEntityVo.class, params);
			System.out.println("Excel解析完成:"+StringUtil.tj(new Date()));
			// 保存错误数据
			List<LBaMaterialEntityErrMsgVo> arrayList = new ArrayList<LBaMaterialEntityErrMsgVo>();
			// 物资分类所有信息
			List<LBaMaterialTypeEntity> list1 = systemService.getList(LBaMaterialTypeEntity.class);
			Map<String,LBaMaterialTypeEntity> mateType = systemService.list2Map(LBaMaterialTypeEntity.class, list1, "typeCode");
			// 计量单位所有信息
			List<LBaMaterialMaterUnitEntity> list2 = systemService.getList(LBaMaterialMaterUnitEntity.class);
			Map<String,LBaMaterialMaterUnitEntity> mateUnit = systemService.list2Map(LBaMaterialMaterUnitEntity.class, list2, "typeName");
			// 供应商所有信息
			List<LBaSupplierEntity> list3 = systemService.getList(LBaSupplierEntity.class);
			Map<String,LBaSupplierEntity> supMap = systemService.list2Map(LBaSupplierEntity.class, list3, "supplierFullName");
			// 仓库所有信息
			List<LBaWarehouseEntity> list4 = systemService.getList(LBaWarehouseEntity.class);
			Map<String,LBaWarehouseEntity> housMap = systemService.list2Map(LBaWarehouseEntity.class, list4, "warehouseName");
			// 器械分类所有信息
			List<LBaMaterialAppaTypeEntity> list5 = systemService.getList(LBaMaterialAppaTypeEntity.class); 
			Map<String,LBaMaterialAppaTypeEntity> appTypeMap = systemService.list2Map(LBaMaterialAppaTypeEntity.class, list5, "typeName");
			// 财务分类所有信息
			List<LBaMaterialFinanceTypeEntity> list6 = systemService.getList(LBaMaterialFinanceTypeEntity.class);
			Map<String,LBaMaterialFinanceTypeEntity> finTypeMap = systemService.list2Map(LBaMaterialFinanceTypeEntity.class, list6, "typeName");
			// 国标分类所有信息
			List<LBaMaterialStandTypeEntity> list7 = systemService.getList(LBaMaterialStandTypeEntity.class);
			Map<String,LBaMaterialStandTypeEntity> standTypeMap = systemService.list2Map(LBaMaterialStandTypeEntity.class, list7, "typeName");
			// 物资所有信息
			List<LBaMaterialEntity> list8 = systemService.getList(LBaMaterialEntity.class);
			Map<String,LBaMaterialEntity> mateMap = systemService.list2Map(LBaMaterialEntity.class, list8, "materialCode");
			List<LBaMaterialEntity> materialList = new ArrayList<LBaMaterialEntity>();
			for (LBaMaterialEntityVo lBaMaterial : listLBaMaterialEntitys) {
				// 非空校验
				errMsg = this.vailNull(lBaMaterial, errMsg);
				// 校验天数
				errMsg = this.vailDate(lBaMaterial, errMsg);
				// 校验条形码
				errMsg = this.vailSuppWareBarUnit(lBaMaterial, errMsg);
				// 校验库存信息以及预估单价
				errMsg = this.vailStock(lBaMaterial, errMsg);
				// 校验耗材，资产
				errMsg = this.vailConsAndAssets(appTypeMap, finTypeMap, standTypeMap, lBaMaterial, errMsg);
				
				//校验物资分类
				if(mateType.get(lBaMaterial.getMaterialTypeId()) ==null){ 
					errMsg = errMsg + "物资分类编码“"+lBaMaterial.getMaterialTypeId()+"”不存在;";
				}else{
					lBaMaterial.setMaterialTypeId(mateType.get(lBaMaterial.getMaterialTypeId()).getId());
				}
				// 校验计量单位 
				if (mateUnit.get(lBaMaterial.getMaterUnitName()) ==null) {
					errMsg = errMsg + "计量单位“"+lBaMaterial.getMaterUnitName()+"”不存在;";
				}else{
					lBaMaterial.setMaterUnitId(mateUnit.get(lBaMaterial.getMaterUnitName()).getId());
				}
				// 校验供应商 
				if (StringUtil.isNotEmpty(lBaMaterial.getSupplierName())){
					if (supMap.get(lBaMaterial.getSupplierName()) ==null) {
						errMsg = errMsg + "供应商“"+lBaMaterial.getSupplierName()+"”不存在;";
					}else{
						lBaMaterial.setSupplierId(supMap.get(lBaMaterial.getSupplierName()).getId());
					}
				}
				// 校验仓库
				if (StringUtil.isNotEmpty(lBaMaterial.getWarehouseName())){
					if ( housMap.get(lBaMaterial.getWarehouseName()) ==null) {
						errMsg = errMsg + "仓库“"+lBaMaterial.getWarehouseName()+"”不存在;";
					}else{
						lBaMaterial.setWarehouseId(housMap.get(lBaMaterial.getWarehouseName()).getId());
					}
				}
				
				if (!errMsg.equals("")) {
					LBaMaterialEntityErrMsgVo msgVo = this.getMaterialErrMsg(lBaMaterial, "第"+row+"行数据异常:"+errMsg);
					arrayList.add(msgVo);
					msg = errMsg;
					j.setSuccess(false);
					j.setMsg("第"+row+"行"+errMsg); 
					map.put("fileUuid", uuid);
					return j; 
				} else {
					// 封装后的数据(校验通过)
					LBaMaterialEntity material = this.getMaterial(lBaMaterial);
					material.setMonmCode(PinyinUtil.getPinYinHeadChar(lBaMaterial.getMaterialName()));
					// 根据物资编码判断是更新操作还是添加操作
					if (mateMap.get(lBaMaterial.getMaterialCode()) != null) { //更新
						lBaMaterial.setId(mateMap.get(lBaMaterial.getMaterialCode()).getId());
						LBaMaterialEntity t =systemService.get(LBaMaterialEntity.class, mateMap.get(lBaMaterial.getMaterialCode()).getId());
						MyBeanUtils.copyBeanNotNull2Bean(lBaMaterial, t);
						lBaMaterialService.saveOrUpdate(t);
					}else{ //新增
						materialList.add(material);
					}
				}
				row ++;
			}
			//开始保存数据
			if(materialList!= null && materialList.size()>0){
				lBaMaterialService.saveMaterialByList(materialList);
			}
			System.out.println("导入完成:"+StringUtil.tj(new Date()));
			request.getSession().setAttribute(uuid, arrayList);
			j.setMsg("文件导入成功！");
			materialList =null;
			arrayList =null;
			list1 = null;
			mateType = null;
			list2 = null;
			mateUnit = null;
			list3 = null;
			supMap = null;
			list4 = null;
			housMap = null;
			list5 = null; 
			appTypeMap =null;
			list6 = null;
			finTypeMap = null;
			list7 = null;
			standTypeMap = null;
			list8 = null;
		}catch (ExcelImportException e) {
			j.setSuccess(false);
			j.setMsg(e.getErrorMsg());
			logger.error(ExceptionUtil.getExceptionMessage(e)); 
			return j;
		} catch (Exception e) {
			flag = false;
			j.setSuccess(flag);
			msg = "第"+row+"行数据异常:"+errMsg+e.getMessage()+",文件导入失败，请检查!";
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

		List<LBaMaterialEntityErrMsgVo> lvList = (List<LBaMaterialEntityErrMsgVo>) request.getSession().getAttribute(fileUuid);

		modelMap.put(NormalExcelConstants.FILE_NAME, "导入结果");
		modelMap.put(NormalExcelConstants.CLASS, LBaMaterialEntityErrMsgVo.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("导入结果列表", "导出人：" + ResourceUtil.getSessionUserName().getUserName(), "导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST, lvList);
		request.getSession().removeAttribute(fileUuid);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}

	/**
	 * 组装导入错误的数据
	 */
	private LBaMaterialEntityErrMsgVo getMaterialErrMsg(LBaMaterialEntityVo lv, String errMsg) {
		LBaMaterialEntityErrMsgVo msgVo = new LBaMaterialEntityErrMsgVo();
		msgVo.setIsQuality(lv.getIsQuality());
		msgVo.setUnitPrice(lv.getUnitPrice());
		msgVo.setIsInstead(lv.getIsInstead());
		msgVo.setValueMethod(lv.getValueMethod());
		msgVo.setBartype(lv.getBartype());
		msgVo.setIsBarCode(lv.getIsBarCode());
		msgVo.setIsHighCons(lv.getIsHighCons());
		msgVo.setWarningDay(lv.getWarningDay());
		msgVo.setShelfLife(lv.getShelfLife());
		msgVo.setIsShelfLife(lv.getIsShelfLife());
		msgVo.setIsOutIn(lv.getIsOutIn());
		msgVo.setIsBatch(lv.getIsBatch());
		msgVo.setMinStock(lv.getMinStock());
		msgVo.setSafeStock(lv.getSafeStock());
		msgVo.setMaxStock(lv.getMaxStock());
		msgVo.setWarehouseName(lv.getWarehouseName());
		msgVo.setSupplierName(lv.getSupplierName());
		msgVo.setIsMater(lv.getIsMater());
		msgVo.setIsAssets(lv.getIsAssets());
		msgVo.setIsCons(lv.getIsCons());
		msgVo.setStandTypeId(lv.getStandTypeId());
		msgVo.setFinanceTypeId(lv.getFinanceTypeId());
		msgVo.setAppaTypeId(lv.getAppaTypeId());
		msgVo.setMaterUnitName(lv.getMaterUnitName());
		msgVo.setMonmCode(lv.getMonmCode());
		msgVo.setSpecModel(lv.getSpecModel());
		msgVo.setMaterialTypeId(lv.getMaterialTypeId());
		msgVo.setMaterialName(lv.getMaterialName());
		msgVo.setMaterialCode(lv.getMaterialCode());
		msgVo.setErrMsg(errMsg);
		return msgVo;
	}

	/**
	 * 组装导入数据
	 */
	private LBaMaterialEntity getMaterial( LBaMaterialEntityVo lv) {
		String materialCode = lv.getMaterialCode();
		String materialName = lv.getMaterialName();
		String specModel = lv.getSpecModel();
		String isCons = lv.getIsCons();
		String isAssets = lv.getIsAssets();
		String isMater = lv.getIsMater(); 
		String maxStock = lv.getMaxStock();
		String safeStock = lv.getSafeStock();
		String minStock = lv.getMinStock();
		String isBatch = lv.getIsBatch();
		String isShelfLife = lv.getIsShelfLife();
		String shelfLife = lv.getShelfLife();
		String isHighCons = lv.getIsHighCons();
		String isBarCode = lv.getIsBarCode();
		String bartype = lv.getBartype();
		String valueMethod = lv.getValueMethod();
		String isInstead = lv.getIsInstead();
		String unitPrice = lv.getUnitPrice();
		String isQuality = lv.getIsQuality();
		LBaMaterialEntity material = new LBaMaterialEntity();
		material.setMaterialTypeId(lv.getMaterialTypeId());
		material.setMaterUnitId(lv.getMaterUnitId());
		material.setSupplierId(lv.getSupplierId());
		material.setWarehouseId(lv.getWarehouseId());
		material.setMaterialCode(materialCode);
		material.setMaterialName(materialName);
		material.setSpecModel(specModel);
		material.setBartype(bartype);
		material.setValueMethod(valueMethod); 
		material.setIsInstead(Integer.valueOf((transData(isInstead))));
		material.setIsBarCode(Integer.valueOf(transData(isBarCode)));
		material.setIsHighCons(Integer.valueOf(transData(isHighCons)));
		material.setIsQuality(Integer.valueOf(transData(isQuality)));
		material.setIsShelfLife(Integer.valueOf(transData(isShelfLife))); 
		material.setIsPurc(Integer.valueOf(transData(lv.getIsPurc()))); 
		material.setIsBatch(Integer.valueOf(transData(isBatch)));
		material.setIsMater(Integer.valueOf(transData(isMater)));
		material.setIsAssets(Integer.valueOf(transData(isAssets)));
		material.setIsCons(Integer.valueOf(transData(isCons))); 
		material.setStandTypeId(lv.getStandTypeId());
		material.setFinanceTypeId(lv.getFinanceTypeId());
		material.setAppaTypeId(lv.getAppaTypeId());
		TSUser tsUser = ResourceUtil.getSessionUserName();
		material.setDepartId(tsUser.getDepartid());
		material.setUpdateBy(tsUser.getId());
		material.setUpdateDate(new Date());
		if(StringUtil.isNotEmpty(unitPrice)){
			material.setUnitPrice(new BigDecimal(unitPrice));
		} 
		if (StringUtil.isNotEmpty(shelfLife)) {
			material.setShelfLife(Integer.valueOf(shelfLife));
		} 
		if (StringUtil.isNotEmpty(minStock)) {
			material.setMinStock(Double.valueOf(minStock));
		}
		if (StringUtil.isNotEmpty(safeStock)) {
			material.setSafeStock(Double.valueOf(safeStock));
		}
		if (StringUtil.isNotEmpty(maxStock)) {
			material.setMaxStock(Double.valueOf(maxStock));
		}
		return material;
	}

	/**
	 * 校验条形码
	 */
	private String vailSuppWareBarUnit( LBaMaterialEntityVo lBaMaterial, String errMsg) {
		// 校验条形码
		String isBarCode = lBaMaterial.getIsBarCode();
		if ("是".equals(isBarCode)) {
			String bartype = lBaMaterial.getBartype();
			if (StringUtil.isEmpty(bartype)) {
				errMsg = errMsg + "必须填写条形码类型;";
			} else {
				if (!"个体码".equals(bartype) && !"品种码".equals(bartype)) {
					errMsg = errMsg + "条形码类型有误;";
				}
			}

		}
		if ("否".equals(isBarCode)) {
			String bartype = lBaMaterial.getBartype();
			/*if (StringUtil.isNotEmpty(bartype)) {
				errMsg = errMsg + "是否条形码管理为是再选择条形码类型;";
			}*/
		}
		return errMsg;
	}

	/**
	 * 校验耗材与资产
	 */
	private String vailConsAndAssets(Map<String,LBaMaterialAppaTypeEntity> appTypeMap, Map<String,LBaMaterialFinanceTypeEntity> finTypeMap,
			Map<String,LBaMaterialStandTypeEntity> standTypeMap, LBaMaterialEntityVo lBaMaterial, String errMsg) {
		String isCons = lBaMaterial.getIsCons();
		if ("是".equals(isCons)) {
			String isAssets = lBaMaterial.getIsAssets();
			if (!"否".equals(isAssets)) {
				errMsg = errMsg + "是否资产跟是否耗材必须只能有一个为是;";
			}
			String isMater = lBaMaterial.getIsMater();
			if (!"否".equals(isMater)) {
				errMsg = errMsg + "是否耗材为是,是否计量必须为否;";
			}
			if ("否".equals(isAssets)) {
				String appaTypeId = lBaMaterial.getAppaTypeId();
				if (StringUtil.isNotEmpty(appaTypeId)) {
					errMsg = errMsg + "是否资产为是器械分类才可填;";
				}
				String financeTypeId = lBaMaterial.getFinanceTypeId();
				if (StringUtil.isNotEmpty(financeTypeId)) {
					errMsg = errMsg + "是否资产为是财务分类才可填;";
				}
				String standTypeId = lBaMaterial.getStandTypeId();
				if (StringUtil.isNotEmpty(standTypeId)) {
					errMsg = errMsg + "是否资产为是国际分类才可填;";
				}
			}
		}
		if ("否".equals(isCons)) {
			String isHighCons = lBaMaterial.getIsHighCons();
			if ("是".equals(isHighCons)) {
				errMsg = errMsg + "是否高值耗材为是，是否耗材必须为是;";
			}
			String isAssets = lBaMaterial.getIsAssets();
			if (!"是".equals(isAssets)) {
				errMsg = errMsg + "是否资产跟是否耗材必须只能有一个为是;";
			}
			if ("是".equals(isAssets)) {
				String appaTypeId = lBaMaterial.getAppaTypeId();
				if (StringUtil.isNotEmpty(appaTypeId)) {
					if (appTypeMap.get(lBaMaterial.getAppaTypeId()) != null) { 
						lBaMaterial.setAppaTypeId(appTypeMap.get(lBaMaterial.getAppaTypeId()).getId());
					}else{
						errMsg = errMsg + "器械分类“"+appaTypeId+"”不存在;";
					}
				}
				String financeTypeId = lBaMaterial.getFinanceTypeId();
				if (StringUtil.isNotEmpty(financeTypeId)) {
					if (finTypeMap.get(lBaMaterial.getFinanceTypeId()) != null) { 
						lBaMaterial.setFinanceTypeId(finTypeMap.get(lBaMaterial.getFinanceTypeId()).getId());
					}else{
						errMsg = errMsg + "财务“"+financeTypeId+"”分类不存在;";
					}
				}
				String standTypeId = lBaMaterial.getStandTypeId();
				if (StringUtil.isNotEmpty(standTypeId)) {
					if(standTypeMap.get(lBaMaterial.getStandTypeId()) !=null){
						lBaMaterial.setStandTypeId(standTypeMap.get(lBaMaterial.getStandTypeId()).getId());
					}else{
						errMsg = errMsg + "国际分类“"+standTypeId+"”不存在;";
					}
				}
				if (StringUtil.isNotEmpty(isHighCons)) {
					if ("是".equals(isHighCons)) {
						errMsg = errMsg + "是否资产为是,是否高值耗材必须为否;";
					}
				}

			}

		}
		return errMsg;
	}

	/**
	 * 校验库存数据以及预估单价
	 */
	private String vailStock(LBaMaterialEntityVo lBaMaterial, String errMsg) {
		String maxStock = lBaMaterial.getMaxStock();
		if (StringUtil.isNotEmpty(maxStock)) {
			if (isNum(maxStock)) {
				int parseInt = Integer.parseInt(maxStock);
				if (parseInt <= 0) {
					errMsg = errMsg + "最高库存必须大于0;";
				}
			}else{
				errMsg = errMsg + "最高库存必须为数字;";
			}
		}
		String safeStock = lBaMaterial.getSafeStock();
		if (StringUtil.isNotEmpty(safeStock)) {
			if (isNum(safeStock)) {
				int parseInt = Integer.parseInt(safeStock);
				if (parseInt <= 0) {
					errMsg = errMsg + "安全库存必须大于0;";
				}
			}else{
				errMsg = errMsg + "安全库存必须为数字;";
			}
		}
		String minStock = lBaMaterial.getMinStock();
		if (StringUtil.isNotEmpty(minStock)) {
			if (isNum(minStock)) {
				int parseInt = Integer.parseInt(minStock);
				if (parseInt <= 0) {
					errMsg = errMsg + "最低库存必须大于0;";
				}
			}else{
				errMsg = errMsg + "最低库存必须为数字;";
			}
		}
		// 校验预估单价
		/*String unitPrice = lBaMaterial.getUnitPrice();
		if (StringUtil.isNotEmpty(unitPrice)) {
			if (isNum(unitPrice)) {
				BigDecimal bigDecimal = new BigDecimal(unitPrice);
				int result = bigDecimal.compareTo(BigDecimal.ZERO);
				if (result == 0 || result == -1) {
					errMsg = errMsg + "预估单价必须大于0;";
				}
			}else{
				errMsg = errMsg + "预估单价必须为数字;";
			}
		}*/
		return errMsg;
	}
	/**
	 * 校验接受的是否是数字
	 */
	private boolean isNum(String str){
		Pattern pattern = Pattern.compile("[-+]?[0-9]+(\\.[0-9]{1,2})?$");
		return pattern.matcher(str).matches();
	}

	/**
	 * 校验天数
	 */
	private String vailDate(LBaMaterialEntityVo lBaMaterial, String errMsg) {
		String shelfLife = lBaMaterial.getShelfLife()==null?null:lBaMaterial.getShelfLife().toString();
		if (StringUtil.isNotEmpty(shelfLife)) {
			if (isNum(shelfLife)) {
				int shelfLifeNum = Integer.parseInt(shelfLife);
				if (shelfLifeNum < 0) {
					errMsg = errMsg + "保质期必须大于0;";
				}
			}else{
				errMsg = errMsg + "保质期天数必须为数字;";
			}
		}
		String warningDay = lBaMaterial.getWarningDay();
		if (StringUtil.isNotEmpty(warningDay)) {
			if (isNum(warningDay)) {
				int warningDayNum = Integer.parseInt(warningDay);
				if (warningDayNum < 0) {
					errMsg = errMsg + "预警天数必须大于等于0;";
				}
			}else{
				errMsg = errMsg + "预警天数必须为数字;";
			}
		}
		return errMsg;
	}

	/**
	 * 对导入的数据进行非空校验
	 */
	private String vailNull(LBaMaterialEntityVo lBaMaterial, String errMsg) {
		String materialCode = lBaMaterial.getMaterialCode();
		if (StringUtil.isEmpty(materialCode)) {
			errMsg = errMsg + "物资编码不能为空;";
		}
		String materialName = lBaMaterial.getMaterialName();
		if (StringUtil.isEmpty(materialName)) {
			errMsg = errMsg + "物资名称不能为空;";
		}
		String materialTypeId = lBaMaterial.getMaterialTypeId();
		if (StringUtil.isEmpty(materialTypeId)) {
			errMsg = errMsg + "物资分类不能为空;";
		}
		/*String monmCode = lBaMaterial.getMonmCode();
		if (StringUtil.isEmpty(monmCode)) {
			errMsg = errMsg + "助记码不能为空;";
		}*/
		String materUnitName = lBaMaterial.getMaterUnitName();
		if (StringUtil.isEmpty(materUnitName)) {
			errMsg = errMsg + "计量单位不能为空;";
		}
		/*String unitPrice = lBaMaterial.getUnitPrice();
		if (StringUtil.isEmpty(unitPrice)) {
			errMsg = errMsg + "预估单价不能为空;";
		}*/
		String isCons = lBaMaterial.getIsCons();
		if (StringUtil.isEmpty(isCons)) {
			errMsg = errMsg + "是否耗材不能为空,请填写是或否;";
		}
		String isAssets = lBaMaterial.getIsAssets();
		if (StringUtil.isEmpty(isAssets)) {
			errMsg = errMsg + "是否资产不能为空,请填写是或否;";
		}
		String isMater = lBaMaterial.getIsMater();
		if (StringUtil.isEmpty(isMater)) {
			errMsg = errMsg + "是否计量不能为空,请填写是或否;";
		}
		String isBatch = lBaMaterial.getIsBatch();
		if (StringUtil.isEmpty(isBatch)) {
			errMsg = errMsg + "是否批次管理不能为空,请填写是或否;";
		}
		/*String isOutIn = lBaMaterial.getIsOutIn();
		if (StringUtil.isEmpty(isOutIn)) {
			errMsg = errMsg + "是否出库跟踪入库不能为空,请填写是或否;";
		}*/
		String isShelfLife = lBaMaterial.getIsShelfLife();
		if (StringUtil.isEmpty(isShelfLife)) {
			errMsg = errMsg + "是否保质期管理不能为空,请填写是或否;";
		}
		String isHighCons = lBaMaterial.getIsHighCons();
		if (StringUtil.isEmpty(isHighCons)) {
			errMsg = errMsg + "是否高值耗材不能为空,请填写是或否;";
		}
		String isBarCode = lBaMaterial.getIsBarCode();
		if (StringUtil.isEmpty(isBarCode)) {
			errMsg = errMsg + "是否条形码管理不能为空,请填写是或否;";
		}
		if (StringUtil.isNotEmpty(isBarCode) && "是".equals(isBarCode)) {
			String bartype = lBaMaterial.getBartype();
			if (StringUtil.isEmpty(bartype)) {
				errMsg = errMsg + "条形码类型不能为空;";
			}
		}
		String isInstead = lBaMaterial.getIsInstead();
		if (StringUtil.isEmpty(isInstead)) {
			errMsg = errMsg + "是否代管不能为空,请填写是或否;";
		}
		String isQuality = lBaMaterial.getIsQuality();
		if (StringUtil.isEmpty(isQuality)) {
			errMsg = errMsg + "是否质检不能为空,请填写是或否;";
		}
		return errMsg;
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<LBaMaterialEntity> list() {
		List<LBaMaterialEntity> listLBaMaterials = lBaMaterialService.getList(LBaMaterialEntity.class);
		return listLBaMaterials;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		LBaMaterialEntity task = lBaMaterialService.get(LBaMaterialEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody LBaMaterialEntity lBaMaterial, UriComponentsBuilder uriBuilder) {
		// 调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<LBaMaterialEntity>> failures = validator.validate(lBaMaterial);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		// 保存
		try {
			lBaMaterialService.save(lBaMaterial);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		// 按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = lBaMaterial.getId();
		URI uri = uriBuilder.path("/rest/lBaMaterialController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody LBaMaterialEntity lBaMaterial) {
		// 调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<LBaMaterialEntity>> failures = validator.validate(lBaMaterial);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		// 保存
		try {
			lBaMaterialService.saveOrUpdate(lBaMaterial);
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
		lBaMaterialService.deleteEntityById(LBaMaterialEntity.class, id);
	}

	/**
	 * 查询物资信息-名称，规格，计量单位，仓库，供应商信息
	 * 
	 * @param userid
	 * @return
	 */
	@RequestMapping(params = "getMaterialMsg")
	@ResponseBody
	public LBaMaterialEntity getMaterialMsg(String id) {
		LBaMaterialEntity baMaterialEntity = systemService.getEntity(LBaMaterialEntity.class, id);
		// 计量单位
		LBaMaterialMaterUnitEntity uentity = systemService.getEntity(LBaMaterialMaterUnitEntity.class,baMaterialEntity.getMaterUnitId());
		baMaterialEntity.setMaterUnitName(uentity == null ? "" : uentity.getTypeName());

		// 主供应商
		if (!StringUtil.isEmpty(baMaterialEntity.getSupplierId())) {
			LBaSupplierEntity sentity = systemService.getEntity(LBaSupplierEntity.class,baMaterialEntity.getSupplierId());
			baMaterialEntity.setSupplierName(sentity.getSupplierFullName());
		}

		// 默认仓库
		if (!StringUtil.isEmpty(baMaterialEntity.getWarehouseId())) {
			LBaWarehouseEntity wentity = systemService.getEntity(LBaWarehouseEntity.class,baMaterialEntity.getWarehouseId());
			baMaterialEntity.setWarehouseName(wentity.getWarehouseName());
		}

		return baMaterialEntity;
	}

	/**
	 * 查询物资信息-名称，规格，计量单位，仓库，供应商信息
	 * 
	 * @param userid
	 * @return
	 */
	@RequestMapping(params = "getMaterialMsgByBarCode")
	@ResponseBody
	public LBaMaterialEntity getMaterialMsgByBarCode(String barCode, String wareHouseId, String supplierId) {
		if (StringUtil.isEmpty(barCode.trim())) {
			return null;
		}
		LBaMaterialEntity baMaterialEntity = null;
		try {
			baMaterialEntity = lBaMaterialService.getMaterialMsgByBarCode(barCode, wareHouseId, supplierId);
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		return baMaterialEntity;
	}

	/**
	 * 查询物资信息-名称，规格，计量单位，仓库，供应商信息
	 * 
	 * @param userid
	 * @return
	 */
	@RequestMapping(params = "getInWareMaterialMsgByBarCode")
	@ResponseBody
	public LBaMaterialEntity getInWareMaterialMsgByBarCode(String barCode, String wareHouseId) {
		if (StringUtil.isEmpty(barCode.trim())) {
			return null;
		}
		LBaMaterialEntity baMaterialEntity = null;
		try {
			baMaterialEntity = lBaMaterialService.getInWareMaterialMsgByBarCode(barCode, wareHouseId);
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		return baMaterialEntity;
	}
/**
 * 中标物资维护
 */
	
	@RequestMapping(params = "listMain")
	public ModelAndView listMain(HttpServletRequest request) {
		return new ModelAndView("cn/com/linkwide/ba/material/record/IBaMaterialMaintenanceList");
	}
	
	@RequestMapping(params = "datagridMain")
	public void datagridMain(LBaMaterialEntity lBaMaterial, HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(LBaMaterialEntity.class, dataGrid);
		// 查询条件组装器
		if (StringUtil.isNotEmpty(lBaMaterial.getMaterialName())) {
			lBaMaterial.setMaterialName("*" + lBaMaterial.getMaterialName() + "*");
		}

		if (StringUtil.isNotEmpty(lBaMaterial.getMaterialCode())) {
			lBaMaterial.setMaterialCode("*" + lBaMaterial.getMaterialCode() + "*");
		}

		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, lBaMaterial, request.getParameterMap());
		try {
			// 自定义追加查询条件
			cq.addOrder("createDate", SortDirection.desc);
			String wid = request.getParameter("wid");// 仓库id

			Set<String> bmid = new HashSet<String>();
			if (StringUtil.isNotEmpty(wid)) {
				List<String> mid = systemService.findListbySql(" select material_id from l_ba_cont_warehouse_material where warehouse_id = '" + wid + "'");
				bmid.addAll(mid);
			}

			String sid = request.getParameter("sid");// 仓库id
			if (StringUtil.isNotEmpty(sid)) {
				List<String> smids = systemService.findListbySql(" select material_id from l_ba_cont_supplier_material where supplier_id = '" + sid + "'");
				bmid.addAll(smids);
			}
			List<String> mmid = lBaMaterialService.getOverTimeMaterialIds(null);
			bmid.addAll(mmid);

			if (bmid.size() > 0) {
				cq.in("id", bmid.toArray());
			}

			String materialTypeIds = request.getParameter("materialTypeIds");
			if (StringUtil.isNotEmpty(materialTypeIds)) {
				cq.in("materialTypeId", materialTypeIds.split(","));
			}
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.lBaMaterialService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	@RequestMapping(params = "datagridAllMain")
	public void datagridAllMain(LBaMaterialEntity lBaMaterial, HttpServletRequest request, HttpServletResponse response,DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(LBaMaterialEntity.class, dataGrid);
		// 查询条件组装器
		if (StringUtil.isNotEmpty(lBaMaterial.getMaterialName())) {
			lBaMaterial.setMaterialName("*" + lBaMaterial.getMaterialName() + "*");
		}

		if (StringUtil.isNotEmpty(lBaMaterial.getMaterialCode())) {
			lBaMaterial.setMaterialCode("*" + lBaMaterial.getMaterialCode() + "*");
		}
		
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, lBaMaterial, request.getParameterMap());
		try {
			// 自定义追加查询条件
			cq.addOrder("createDate", SortDirection.desc);
			String wid = request.getParameter("wid");// 仓库id
			Set<String> bmid = new HashSet<String>();
			if (StringUtil.isNotEmpty(wid)) {
				List<String> wmids = systemService.findListbySql(" select material_id from l_ba_cont_warehouse_material where warehouse_id = '" + wid + "'");
				bmid.addAll(wmids);
			}

			String sid = request.getParameter("sid");// 仓库id
			if (StringUtil.isNotEmpty(sid)) {
				List<String> smids = systemService.findListbySql(" select material_id from l_ba_cont_supplier_material where supplier_id = '" + sid + "'");
				bmid.addAll(smids);
			}
			
		
			if (bmid.size() > 0) {
				cq.in("id", bmid.toArray());
			}

			String materialTypeIds = request.getParameter("materialTypeIds");
			if (StringUtil.isNotEmpty(materialTypeIds)) {
				cq.in("materialTypeId", materialTypeIds.split(","));
			}
			cq.add(Restrictions.eq("materialStatus", "1"));
			System.out.println("==========");
			
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.lBaMaterialService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}	
	@RequestMapping(params = "inclMain")
	@ResponseBody
	public void inclMain(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		
		try{
			String q = request.getParameter("q") != null ? request.getParameter("q") : "";
			//处理查询条件
			StringBuffer sql = new StringBuffer();
			sql.append(" from mate_supmate_main a");
			sql.append(" left join  l_ba_supplier c on a.supplier_id=c.id  ");
			sql.append(" left join mate_sup_bid b ON a.sup_id=b.id  ");
			sql.append(" where ( a.inv_name like '%"+q+"%'");
			sql.append(" or a.spec_model like '%"+q+"%'");
			sql.append(" or a.price ='"+q+"'");
			sql.append(" or c.supplier_full_name like '%"+q+"%')");
			sql.append("and b.sup_state='4'  ");
			sql.append(" AND (a.inv_id IS NULL or inv_id ='')  ");
			String head =" select a.id  id, a.inv_name  invName,a.spec_model  specModel,a.price  price,a.supplier_id  supplierId,c.supplier_full_name  supplierFullName,b.sup_state  supState ";
			dataGrid.setResults(systemService.findForJdbc(head+sql.toString(), dataGrid.getPage(), dataGrid.getRows()));
			Integer cont = Integer.valueOf(systemService.getCountForJdbc("select count(a.id) "+sql.toString()).toString());
			dataGrid.setTotal(cont);
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		} 
		TagUtil.datagrid(response, dataGrid);
	}
	
	
	/**
	 * 添加物资档案
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAddMain")
	@ResponseBody
	public AjaxJson doAddMain(LBaMaterialEntity lBaMaterial, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "物资档案添加成功";
		try {
			// 对checkbox类型数据做处理
			getCheckBoxVal(lBaMaterial);
			// 保存校验
			AjaxJson j1 = valiSub(lBaMaterial);
			if (!j1.isSuccess()) {
				return j1;
			}
			// 获取当前登录人所在部门
			String curDepartId = ResourceUtil.getSessionUserName().getDepartid();
			lBaMaterial.setDepartId(curDepartId);
			lBaMaterial.setMaterialStatus("1");
			lBaMaterialService.save(lBaMaterial);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
			//向集成平台推送数据
			Map operateMap= new HashMap();
			operateMap.put("operation", "ADD");
			lBaMaterialService.invoke(lBaMaterial, operateMap);
			
		} catch (Exception e) {
			e.printStackTrace();
			message = "物资档案添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	
	/**
	 * 物资档案新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAddMain")
	public ModelAndView goAddMain(LBaMaterialEntity lBaMaterial, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(lBaMaterial.getId())) {
			lBaMaterial = lBaMaterialService.getEntity(LBaMaterialEntity.class, lBaMaterial.getId());
			req.setAttribute("lBaMaterialPage", lBaMaterial);
		}

		String materialTypeId = req.getParameter("materialTypeId");
		req.setAttribute("materialTypeId", materialTypeId);
		return new ModelAndView("cn/com/linkwide/ba/material/record/IBaMaterialMaintenance");
	}
	
	/**
	 * 获取符合条件的物资基本信息
	 */
    @RequestMapping(params = "datagridBase")
	@ResponseBody
	public List<LBaMaterialEntity> datagridBase(LBaMaterialEntity lBaMaterial) {
    	StringBuffer sql0 = new StringBuffer();
    	sql0.append("select m.id \"id\", m.material_code \"materialCode\", m.material_name \"materialName\", m.material_type_id \"materialTypeId\", m.spec_model \"specModel\", m.monm_code \"monmCode\"");
    	sql0.append(", m.mater_unit_id \"materUnitId\", m.appa_type_id \"appaTypeId\", m.finance_type_id \"financeTypeId\", m.stand_type_id \"standTypeId\", m.is_durable \"isDurable\", m.is_cons \"isCons\"");
    	sql0.append(", m.is_assets \"isAssets\", m.is_mater \"isMater\", m.max_stock \"maxStock\", m.safe_stock \"safeStock\", m.min_stock \"minStock\", m.is_batch \"isBatch\", m.is_shelf_life \"isShelfLife\"");
    	sql0.append(", m.shelf_life \"shelfLife\", m.is_install \"isInstall\", m.is_quality \"isQuality\", m.is_high_cons \"isHighCons\", m.is_bar_code \"isBarCode\", m.bar_code \"barCode\", m.is_control \"isControl\"");
    	sql0.append(",u.type_name \"materUnitName\",s.supplier_full_name \"supplierName\" ,w.warehouse_name \"warehouseName\" ,m.unit_price \"unitPrice\" ");
    	sql0.append(" from l_ba_material   m ");
    	sql0.append(" left join l_ba_material_mater_unit u on u.id = m.MATER_UNIT_ID ");
    	sql0.append(" left join l_ba_warehouse w on w.id = m.warehouse_id ");
    	sql0.append(" left join l_ba_supplier s on s.id = m.supplier_id ");
    	sql0.append(" where 1=1 ");
    	
		if (!StringUtil.isEmpty(lBaMaterial.getWarehouseId())) {
			sql0.append(" and exists (select 1 from  L_BA_CONT_WAREHOUSE_MATERIAL w where w.MATERIAL_ID = m.id and w.WAREHOUSE_ID ='"+lBaMaterial.getWarehouseId()+"' ) " ); 
		}
		if (!StringUtil.isEmpty(lBaMaterial.getSupplierId())) {
			sql0.append(" and exists (select 1 from  L_BA_CONT_SUPPLIER_MATERIAL s where s.MATERIAL_ID = m.id and s.SUPPLIER_ID ='"+lBaMaterial.getSupplierId()+"' ) " ); 
		}
		List<Map<String,Object>> mateList = systemService.findForJdbc(sql0.toString());
		List<LBaMaterialEntity> List0 = new ArrayList<LBaMaterialEntity>();
		for(Map<String,Object> m : mateList){
			LBaMaterialEntity e = new LBaMaterialEntity();
			try {
				MyBeanUtils.copyMap2Bean(e, m);
				List0.add(e);
			} catch (IllegalAccessException e1) { 
				System.out.println(e1.getMessage());
			} catch (InvocationTargetException e1) { 
				System.out.println(e1.getMessage());
			}
		}
		return List0;
	}
    
    /**
	 * 添加物资档案
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "getExpirydate")
	@ResponseBody
	public AjaxJson getExpirydate(LBaMaterialEntity lBaMaterial, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		String makeDate = request.getParameter("makeDate");
		try {
			lBaMaterial = systemService.get(LBaMaterialEntity.class, lBaMaterial.getId() );
			if( 1 == lBaMaterial.getIsShelfLife() && StringUtil.isNotEmpty(makeDate)){
				SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyy-MM-dd");
				Date date=sDateFormat.parse(makeDate);
				Calendar ca = Calendar.getInstance();
				ca.setTime(date);
				if("Y".equals(lBaMaterial.getExpUnit())){
					ca.add(Calendar.YEAR, lBaMaterial.getShelfLife());
				}else if("M".equals(lBaMaterial.getExpUnit())){
					ca.add(Calendar.MONTH, lBaMaterial.getShelfLife());
				}else if("D".equals(lBaMaterial.getExpUnit())){
					ca.add(Calendar.DATE, lBaMaterial.getShelfLife());
				}
				String enddate = sDateFormat.format(ca.getTime());
				j.setObj(enddate);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
}
