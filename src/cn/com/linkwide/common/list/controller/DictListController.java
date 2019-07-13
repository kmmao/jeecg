package cn.com.linkwide.common.list.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Validator;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.ComboTree;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.util.SqlUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.tag.vo.datatable.SortDirection;
import org.jeecgframework.web.system.pojo.base.TSBaseUser;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.linkwide.ba.baacctsubj.entity.BaAcctSubjEntity;
import cn.com.linkwide.ba.baarea.entity.BaAreaEntity;
import cn.com.linkwide.ba.babankrollsource.entity.BaBankrollSourceEntity;
import cn.com.linkwide.ba.baeconomics.entity.BaEconomicsEntity;
import cn.com.linkwide.ba.bafunction.entity.BaFunctionEntity;
import cn.com.linkwide.ba.baitemfile.entity.BaItemfileEntity;
import cn.com.linkwide.ba.baitemincom.entity.BaItemIncomEntity;
import cn.com.linkwide.ba.baprotype.entity.BaProtypeEntity;
import cn.com.linkwide.ba.budg.entity.BudgExpendItemEntity;
import cn.com.linkwide.ba.material.materunit.entity.LBaMaterialMaterUnitEntity;
import cn.com.linkwide.ba.material.record.entity.LBaMaterialEntity;
import cn.com.linkwide.ba.person.entity.HrPersonMainEntity;
import cn.com.linkwide.ba.supplier.entity.LBaSupplierEntity;
import cn.com.linkwide.ba.warehouse.entity.LBaWarehouseEntity;
import cn.com.linkwide.common.list.service.DictListServiceI;
import cn.com.linkwide.common.util.DictUtil;
import io.swagger.annotations.Api;
/**   
 * @Title: Controller  
 * @Description: 公用下拉
 * @author onlineGenerator
 * @date 2018-05-17 10:30:56
 * @version V1.0   
 *
 */
@Api(value="Dict",description="下拉列表",tags="dictListController")
@Controller
@RequestMapping("/dictListController")
public class DictListController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(DictListController.class);

	@Autowired
	private DictListServiceI dictListService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	
	/**
	 * 加载科室树形结构
	 * 
	 * @return
	 */
	@RequestMapping(params = "departTree")
	@ResponseBody
	public List<ComboTree> departTree(HttpServletRequest request,ComboTree comboTree) {
		List<ComboTree> rootLists = new ArrayList<ComboTree>(); 
		this.dictListService.departTree(rootLists, comboTree.getId() == null ? request.getParameter("code") :  comboTree.getId());
		return rootLists;
	}

	/**
	 * 科室分页参照模糊查询末级科室
	 * 
	 * @author zhuhd 2018-3-27
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "departList")
	@ResponseBody
	public void departList(TSDepart depart,HttpServletRequest request,DataGrid dataGrid, HttpServletResponse response) {
		String q = request.getParameter("q") != null ? request.getParameter("q") : "";
		CriteriaQuery cq = new CriteriaQuery(TSDepart.class, dataGrid);
		String defaultVal = request.getParameter("defaultVal") != null ? request.getParameter("defaultVal") : "";
		//过滤是否归口
		String isMana = request.getParameter("isMana");
		// 查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, depart);
		try {
			// 自定义追加查询条件
			String iflater = request.getParameter("isLast"); //是否末级
			if(StringUtil.isEmpty(iflater)){
				cq.eq("iflater", "Y");
			}
			
			Disjunction disjunction = Restrictions.disjunction();
			disjunction.add(Restrictions.like("orgCode", q,MatchMode.ANYWHERE).ignoreCase());
			disjunction.add(Restrictions.like("departname", q,MatchMode.ANYWHERE).ignoreCase());
			cq.add(disjunction);
			//添加是否归口查询条件
			if(StringUtil.isNotEmpty(isMana)){
				cq.add(Restrictions.eq("isMana", "Y"));
			}
			cq.add();
			this.systemService.getDataGridReturn(cq, true); 
			String[]  defArray = defaultVal.split(",");
			if(defArray.length>1 && StringUtil.isNotEmpty(defArray[1]) ){
				TSDepart d = systemService.findUniqueByProperty(TSDepart.class, defArray[0], defArray[1]);
				List<TSDepart> results = dataGrid.getResults();
				if(d !=null){
					for(TSDepart dept : results){
						//如果有就结束
						if(dept.getId().equals(d.getId())){
							TagUtil.datagrid(response, dataGrid);
							return ;
						}
					}
					results.add(d);
				}
				dataGrid.setResults(results);
			} 
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 科室分页参照模糊查询全部科室
	 * 
	 * @author zhuhd 2018-3-27
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "departList2")
	@ResponseBody
	public void departList2(TSDepart depart,HttpServletRequest request,DataGrid dataGrid, HttpServletResponse response) {
		String q = request.getParameter("q") != null ? request.getParameter("q") : "";
		CriteriaQuery cq = new CriteriaQuery(TSDepart.class, dataGrid);
		String defaultVal = request.getParameter("defaultVal") != null ? request.getParameter("defaultVal") : "";
		//过滤是否归口
		String isMana = request.getParameter("isMana");
		// 查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, depart);
		try {
			// 自定义追加查询条件
//			String iflater = request.getParameter("isLast"); //是否末级
//			if(StringUtil.isEmpty(iflater)){
//				cq.eq("iflater", "Y");
//			}
			
			Disjunction disjunction = Restrictions.disjunction();
			disjunction.add(Restrictions.like("orgCode", q,MatchMode.ANYWHERE).ignoreCase());
			disjunction.add(Restrictions.like("departname", q,MatchMode.ANYWHERE).ignoreCase());
			cq.add(disjunction);
			//添加是否归口查询条件
			if(StringUtil.isNotEmpty(isMana)){
				cq.add(Restrictions.eq("isMana", "Y"));
			}
			cq.addOrder("orgCode", SortDirection.asc); //按编码排序
			cq.add();
			this.systemService.getDataGridReturn(cq, true); 
			String[]  defArray = defaultVal.split(",");
			if(defArray.length>1 && StringUtil.isNotEmpty(defArray[1]) ){
				TSDepart d = systemService.findUniqueByProperty(TSDepart.class, defArray[0], defArray[1]);
				List<TSDepart> results = dataGrid.getResults();
				if(d !=null){
					for(TSDepart dept : results){
						//如果有就结束
						if(dept.getId().equals(d.getId())){
							TagUtil.datagrid(response, dataGrid);
							return ;
						}
					}
					results.add(d);
				}
				dataGrid.setResults(results);
			} 
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 用户分页参照模糊查询
	 * 
	 * @author zhuhd 2018-3-27
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "userList")
	@ResponseBody
	public void userList(TSBaseUser depart,HttpServletRequest request,DataGrid dataGrid, HttpServletResponse response) {
		String q = request.getParameter("q") != null ? request.getParameter("q") : "";
		String defaultVal = request.getParameter("defaultVal") != null ? request.getParameter("defaultVal") : "";
		CriteriaQuery cq = new CriteriaQuery(TSBaseUser.class, dataGrid);
		// 查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, depart);
		try {
			// 自定义追加查询条件 
			if(request.getParameter("q") != null){
				Disjunction disjunction = Restrictions.disjunction();
				disjunction.add(Restrictions.like("userName", q,MatchMode.ANYWHERE).ignoreCase());
				disjunction.add(Restrictions.like("realName", q,MatchMode.ANYWHERE).ignoreCase());
				cq.add(disjunction);
			}
			cq.add();
			this.systemService.getDataGridReturn(cq, true);
			if(StringUtil.isNotEmpty(defaultVal)){
				String[]  defArray = defaultVal.split(",");
				if(defArray.length>1 && StringUtil.isNotEmpty(defArray[1]) ){
					TSBaseUser d = systemService.findUniqueByProperty(TSBaseUser.class, defArray[0], defArray[1]);
					List<TSBaseUser> results = dataGrid.getResults();
					if(d !=null){
						for(TSBaseUser dept : results){
							//如果有就结束
							if(dept.getId().equals(d.getId())){
								TagUtil.datagrid(response, dataGrid);
								return ;
							}
						}
						results.add(d);
					}
					dataGrid.setResults(results);
				} 
			}
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 仓库分页参照模糊查询
	 * 
	 * @author zhuhd 2018-3-27
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "wareHouseList")
	@ResponseBody
	public void wareHouseList(LBaWarehouseEntity depart,HttpServletRequest request,DataGrid dataGrid, HttpServletResponse response) {
		String q = request.getParameter("q") != null ? request.getParameter("q") : "";
		String isAssets = request.getParameter("isAssets");
		String defaultVal = request.getParameter("defaultVal") != null ? request.getParameter("defaultVal") : "";
		CriteriaQuery cq = new CriteriaQuery(LBaWarehouseEntity.class, dataGrid);
		// 查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, depart);
		try {
			// 自定义追加查询条件 
			if(request.getParameter("q") != null){
				Disjunction disjunction = Restrictions.disjunction();
				disjunction.add(Restrictions.like("warehouseCode", q,MatchMode.ANYWHERE).ignoreCase());
				disjunction.add(Restrictions.like("warehouseName", q,MatchMode.ANYWHERE).ignoreCase());
				cq.add(disjunction);
			}
			//条件与
			Conjunction criteria =  Restrictions.conjunction();
			if("1".equals(isAssets)){
				criteria.add(Restrictions.eq("isAssets", "1"));  
			}else if("0".equals(isAssets)){
				criteria.add(Restrictions.eq("isAssets", "0")); 
			}
			cq.add(criteria);
			cq.add();
			this.systemService.getDataGridReturn(cq, true);
			if(StringUtil.isNotEmpty(defaultVal)){
				String[]  defArray = defaultVal.split(",");
				if(defArray.length>1 && StringUtil.isNotEmpty(defArray[1]) ){
					LBaWarehouseEntity d = systemService.findUniqueByProperty(LBaWarehouseEntity.class, defArray[0], defArray[1]);
					List<LBaWarehouseEntity> results = dataGrid.getResults();
					if(d !=null){
						for(LBaWarehouseEntity dept : results){
							//如果有就结束
							if(dept.getId().equals(d.getId())){
								TagUtil.datagrid(response, dataGrid);
								return ;
							}
						}
						results.add(d);
					}
					dataGrid.setResults(results);
				} 
			}
			TagUtil.datagrid(response, dataGrid);
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		
	}

	/**
	 * 物资分页参照模糊查询
	 * 
	 * @author zhuhd 2018-3-27
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "mateList")
	@ResponseBody
	public void mateList(LBaMaterialEntity depart,HttpServletRequest request,DataGrid dataGrid, HttpServletResponse response) {
		String q = request.getParameter("q") != null ? request.getParameter("q") : "";
		String defaultVal = request.getParameter("defaultVal") != null ? request.getParameter("defaultVal") : "";
		String isBarCode = request.getParameter("isBarCode");
		String supplierId = request.getParameter("supplierId");
		String mateType = request.getParameter("mateType");
		String isLabourService = request.getParameter("isLabourService");
		
		String head = "select m.id, m.material_code materialCode, m.material_name materialName, m.material_type_id materialTypeId, m.spec_model specModel, m.monm_code monmCode"
				+ ", m.mater_unit_id materUnitId, m.appa_type_id appaTypeId, m.finance_type_id financeTypeId, m.stand_type_id standTypeId, m.is_durable isDurable, m.is_cons isCons"
				+ ", m.is_assets isAssets, m.is_mater isMater, m.max_stock maxStock, m.safe_stock safeStock, m.min_stock minStock, m.is_batch isBatch, m.is_shelf_life isShelfLife"
				+ ", m.shelf_life shelfLife, m.is_install isInstall, m.is_quality isQuality, m.is_high_cons isHighCons, m.is_bar_code isBarCode, m.bar_code barCode, m.is_control isControl"
				+ ", m.unit_price unitPrice from l_ba_material   m where 1=1 ";
		StringBuffer sql0 = new StringBuffer(head);
		try {
			// 自定义追加查询条件 
			if(request.getParameter("q") != null){
				sql0.append(" and ( m.material_code like '%"+q+"%' or m.material_name like '%"+q+"%' ) ");
			}
			if(StringUtil.isNotEmpty(isBarCode)){
				sql0.append(" and  m.is_bar_code ='"+isBarCode+"' ");
			}
			if(StringUtil.isNotEmpty(isLabourService)){
				sql0.append(" and  m.IS_LABOUR_SERVICE ='"+isLabourService+"' ");
			}
			if(StringUtil.isNotEmpty(mateType)){
				sql0.append(" and  m.material_type_id ='"+mateType+"' ");
			}
			//根据供应商筛选物资
			if(StringUtil.isNotEmpty(supplierId)){
				sql0.append(" and  exists (select 1 from l_ba_cont_supplier_material  where supplier_id ='"+supplierId+"' and material_id = m.id ) ");
			}
			systemService.queryDataGrid(sql0.toString(), dataGrid);
			//设置默认值
			if(StringUtil.isNotEmpty(defaultVal) && StringUtil.isEmpty(q) && !defaultVal.endsWith(",")){ 
				String[] def = defaultVal.split(","); 
				List<Map<String,Object>> list = systemService.findForJdbc(head+" and "+def[0]+" = ?", def[1]);
				if(list !=null && list.size() >0)
				dataGrid.getResults().addAll(list); 
			}
			List<Map<String,Object>> mList = dataGrid.getResults();
			for(Map<String,Object> m : mList){
				if(DictUtil.allMateUnits.containsKey(m.get("materUnitId"))){ 
					m.put("materUnitName", DictUtil.allMateUnits.get(m.get("materUnitId")).getTypeName());
				}
			}
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		TagUtil.datagrid(response, dataGrid);
	}
	
	
	/**
	 * 会计科目下拉分页查询
	 * @param entity
	 * @param request
	 * @param dataGrid
	 * @param response
	 */
	@RequestMapping(params = "acctSubjList")
	@ResponseBody
	public void acctSubjList(BaAcctSubjEntity entity,HttpServletRequest request,DataGrid dataGrid, HttpServletResponse response) {
		String q = request.getParameter("q") != null ? request.getParameter("q") : "";
		CriteriaQuery cq = new CriteriaQuery(BaAcctSubjEntity.class, dataGrid);
		String defaultVal = request.getParameter("defaultVal") != null ? request.getParameter("defaultVal") : "";
		String subCode = request.getParameter("subCode");
		if(StringUtil.isNotEmpty(subCode) && !StringUtil.isNotEmpty(q)){
			q = subCode;
		}
		//是否末级
		String isLast = request.getParameter("isLast")==null?null:request.getParameter("isLast").toString();
		// 查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, entity);
		try {
			if(StringUtil.isNotEmpty(isLast)){
				cq.eq("isLast", "Y");
			}
			Disjunction disjunction = Restrictions.disjunction();
			disjunction.add(Restrictions.like("subCode", q,MatchMode.ANYWHERE).ignoreCase());
			disjunction.add(Restrictions.like("subName", q,MatchMode.ANYWHERE).ignoreCase());
			cq.add(disjunction);
			cq.add();
			this.systemService.getDataGridReturn(cq, true); 
			String[]  defArray = defaultVal.split(",");
			if(defArray.length>1 && StringUtil.isNotEmpty(defArray[1]) ){
				String hql = " from BaAcctSubjEntity where 1=1 and "+defArray[0]+"= ? ";
				if(StringUtil.isNotEmpty(entity.getAcctYear())){
					 hql += " and acctYear ='"+entity.getAcctYear()+"' ";
				}
				List<BaAcctSubjEntity>  d = systemService.findHql(hql, defArray[1]);
				List<BaAcctSubjEntity> results = dataGrid.getResults();
				if(d !=null && d.size() >0){
					for(BaAcctSubjEntity dept : results){
						//如果有就结束
						if(dept.getId().equals(d.get(0).getId())){
							TagUtil.datagrid(response, dataGrid);
							return ;
						}
					}
					results.add(d.get(0));
				}
				dataGrid.setResults(results);
			} 
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 项目档案下拉分页查询
	 * @param entity
	 * @param request
	 * @param dataGrid
	 * @param response
	 */
	@RequestMapping(params = "itemList")
	@ResponseBody
	public void itemList(BaItemfileEntity entity,HttpServletRequest request,DataGrid dataGrid, HttpServletResponse response) {
		String q = request.getParameter("q") != null ? request.getParameter("q") : "";
		CriteriaQuery cq = new CriteriaQuery(BaItemfileEntity.class, dataGrid);
		String defaultVal = request.getParameter("defaultVal") != null ? request.getParameter("defaultVal") : "";
		//项目分类
		String itemType = request.getParameter("itemType");
		// 查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, entity);
		try {
			
			Disjunction disjunction = Restrictions.disjunction();
			disjunction.add(Restrictions.like("vcode", q,MatchMode.ANYWHERE).ignoreCase());
			disjunction.add(Restrictions.like("vname", q,MatchMode.ANYWHERE).ignoreCase());
			//项目分类
			if(StringUtil.isNotEmpty(itemType)){
				cq.add(Restrictions.like("pkbaprotype", itemType+"%"));
			}
			cq.add(disjunction);
			cq.addOrder("vcode", SortDirection.asc);
			cq.add();
			this.systemService.getDataGridReturn(cq, true); 
			String[]  defArray = defaultVal.split(",");
			if(defArray.length>1 && StringUtil.isNotEmpty(defArray[1]) ){
				BaItemfileEntity d = systemService.findUniqueByProperty(BaItemfileEntity.class, defArray[0], defArray[1]);
				List<BaItemfileEntity> results = dataGrid.getResults();
				if(d !=null){
					for(BaItemfileEntity dept : results){
						//如果有就结束
						if(dept.getId().equals(d.getId())){
							TagUtil.datagrid(response, dataGrid);
							return ;
						}
					}
					results.add(d);
				}
				dataGrid.setResults(results);
			} 
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 项目下拉分页查询
	 * @param entity
	 * @param request
	 * @param dataGrid
	 * @param response
	 */
	@RequestMapping(params = "baItemfile")
	@ResponseBody
	public void baItemfile(BaItemfileEntity entity,HttpServletRequest request,DataGrid dataGrid, HttpServletResponse response) {
		String q = request.getParameter("q") != null ? request.getParameter("q") : "";
		CriteriaQuery cq = new CriteriaQuery(BaItemfileEntity.class, dataGrid);
		String defaultVal = request.getParameter("defaultVal") != null ? request.getParameter("defaultVal") : "";
		//项目
		String itemType = request.getParameter("itemType");
		// 查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, entity);
		try {
			Disjunction disjunction = Restrictions.disjunction();
			disjunction.add(Restrictions.like("vcode", q,MatchMode.ANYWHERE).ignoreCase());
			disjunction.add(Restrictions.like("vname", q,MatchMode.ANYWHERE).ignoreCase());
			cq.add(disjunction);
			//项目
			if(StringUtil.isNotEmpty(itemType)){
				cq.add(Restrictions.like("vcode", itemType,MatchMode.START));
			}
			cq.add();
			this.systemService.getDataGridReturn(cq, true); 
			String[]  defArray = defaultVal.split(",");
			if(defArray.length>1 && StringUtil.isNotEmpty(defArray[1]) ){
				BaItemfileEntity d = systemService.findUniqueByProperty(BaItemfileEntity.class, defArray[0], defArray[1]);
				List<BaItemfileEntity> results = dataGrid.getResults();
				if(d !=null){
					for(BaItemfileEntity dept : results){
						//如果有就结束
						if(dept.getId().equals(d.getId())){
							TagUtil.datagrid(response, dataGrid);
							return ;
						}
					}
					results.add(d);
				}
				dataGrid.setResults(results);
			} 
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		TagUtil.datagrid(response, dataGrid);
	}
	/**
	 * 项目分类档案下拉分页查询
	 * @param entity
	 * @param request
	 * @param dataGrid
	 * @param response
	 */
	@RequestMapping(params = "itemTypeList")
	@ResponseBody
	public void itemTypeList(BaProtypeEntity entity,HttpServletRequest request,DataGrid dataGrid, HttpServletResponse response) {
		String q = request.getParameter("q") != null ? request.getParameter("q") : "";
		CriteriaQuery cq = new CriteriaQuery(BaProtypeEntity.class, dataGrid);
		String defaultVal = request.getParameter("defaultVal") != null ? request.getParameter("defaultVal") : "";
		//项目分类
		String itemType = request.getParameter("itemType");
		//是否末级
		String isLast = request.getParameter("isLast");
		//是否去除已被使用的
		String isRemoveUsed = request.getParameter("isRemoveUsed");
		// 查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, entity);
		try {
			Disjunction disjunction = Restrictions.disjunction();
			disjunction.add(Restrictions.like("vcode", q,MatchMode.ANYWHERE).ignoreCase());
			disjunction.add(Restrictions.like("vname", q,MatchMode.ANYWHERE).ignoreCase());
			cq.add(disjunction);
			//项目分类
			if(StringUtil.isNotEmpty(itemType)){
				cq.add(Restrictions.like("vcode", itemType,MatchMode.START));
			}
			//去除分类下已有数据的分类
			if("Y".equals(isRemoveUsed)){
				String sql =" select distinct pkbaprotype \"typeCode\" from ba_itemfile order by pkbaprotype";
				List<Map<String,Object>> list =systemService.findForJdbc(sql, null);
				String typeCodes=null;
				if(list!= null && list.size()>0){
					for (Map<String, Object> map : list) {
						String typeCode = map.get("typeCode")==null?"":map.get("typeCode").toString();
						typeCodes=(typeCodes==null)?typeCode:typeCodes+","+typeCode;
					}
					if(StringUtil.isNotEmpty(typeCodes)){
						cq.add(Restrictions.not(Restrictions.in("vcode", typeCodes.split(","))));
					}
				}
			}
			if(StringUtil.isNotEmpty(isLast)){
				cq.eq("viflater", isLast);
			}
			cq.add();
			this.systemService.getDataGridReturn(cq, true); 
			String[]  defArray = defaultVal.split(",");
			if(defArray.length>1 && StringUtil.isNotEmpty(defArray[1]) ){
				BaProtypeEntity d = systemService.findUniqueByProperty(BaProtypeEntity.class, defArray[0], defArray[1]);
				List<BaProtypeEntity> results = dataGrid.getResults();
				if(d !=null){
					for(BaProtypeEntity dept : results){
						//如果有就结束
						if(dept.getId().equals(d.getId())){
							TagUtil.datagrid(response, dataGrid);
							return ;
						}
					}
					results.add(d);
				}
				dataGrid.setResults(results);
			} 
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 人员信息下拉分页查询
	 * @param entity
	 * @param request
	 * @param dataGrid
	 * @param response
	 */
	@RequestMapping(params = "hrPersonMain")
	@ResponseBody
	public void hrPersonMain(HrPersonMainEntity entity,HttpServletRequest request,DataGrid dataGrid, HttpServletResponse response) {
		String q = request.getParameter("q") != null ? request.getParameter("q") : "";
		CriteriaQuery cq = new CriteriaQuery(HrPersonMainEntity.class, dataGrid);
		String defaultVal = request.getParameter("defaultVal") != null ? request.getParameter("defaultVal") : "";
		// 查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, entity);
		try {
			Disjunction disjunction = Restrictions.disjunction();
			disjunction.add(Restrictions.like("empCode", q,MatchMode.ANYWHERE).ignoreCase());
			disjunction.add(Restrictions.like("empName", q,MatchMode.ANYWHERE).ignoreCase());
			cq.add(disjunction);
			cq.add();
			this.systemService.getDataGridReturn(cq, true); 
			String[]  defArray = defaultVal.split(",");
			if(defArray.length>1 && StringUtil.isNotEmpty(defArray[1]) ){
				HrPersonMainEntity d = systemService.findUniqueByProperty(HrPersonMainEntity.class, defArray[0], defArray[1]);
				List<HrPersonMainEntity> results = dataGrid.getResults();
				if(d !=null){
					for(HrPersonMainEntity dept : results){
						//如果有就结束
						if(dept.getId().equals(d.getId())){
							TagUtil.datagrid(response, dataGrid);
							return ;
						}	
					}
					results.add(d);
				}
				dataGrid.setResults(results);
			} 
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		TagUtil.datagrid(response, dataGrid);
	}
	/**
	 * 科室物资分页参照模糊查询
	 * 
	 * @author zhuhd 2018-3-27
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "departMateList")
	@ResponseBody
	public void departMateList(LBaMaterialEntity depart,HttpServletRequest request,DataGrid dataGrid, HttpServletResponse response) {
		String q = request.getParameter("q") != null ? request.getParameter("q") : "";
		String departId = request.getParameter("departId") != null ? request.getParameter("departId") : "";
		String defaultVal = request.getParameter("defaultVal") != null ? request.getParameter("defaultVal") : "";
		try {
			// 自定义追加查询条件 
			StringBuffer sql = new StringBuffer();
			sql.append("FROM l_ba_material m  ");
			sql.append("left join l_ba_material_type t on t.ID= m.material_type_id ");
			sql.append("left join l_ba_material_mater_unit u on u.id = m.mater_unit_id  ");
			sql.append("left join l_ba_supplier s on s.id = m.supplier_id  ");
			sql.append("left join l_ba_warehouse h on h.id = m.warehouse_id where 1=1 ");
			if(request.getParameter("q") != null){
				sql.append(" and ( m.monm_code like '"+q+"%' or m.material_code like '"+q+"%' or m.material_name like '%"+q+"%' ) ");
			}
			//是否过滤科室物资对照
			Map<String,Object> map = systemService.findOneForJdbc("select option_parameter from option_control where modular_identification='mate' and  option_identification ='isUseDepartMate'");
			if("1".equals(map.get("option_parameter"))){
				sql.append(" and m.id in (select mate_id from  l_ba_depart_mate d where   d.depart_id='"+departId+"' )   ");
			}
			String filed ="select m.id as id ,m.material_code as materialCode,m.material_name as materialName ,m.monm_code as monmCode,m.spec_model as specModel ,t.type_name as mateType,u.type_name as unit"
					+ ",m.unit_price as unitPrice ,s.supplier_full_name as supplierName,m.supplier_id as supplierId,h.warehouse_name  as warehouseName ,m.warehouse_id as warehouseId,m.is_batch as isBatch,m.is_shelf_life as isShelfLife,m.is_bar_code as isBarCode ";
			List<Map<String,Object>> results = systemService.findForJdbc(filed+sql.toString(), dataGrid.getPage(), dataGrid.getRows());
			//设置默认值
			if(StringUtil.isNotEmpty(defaultVal) && StringUtil.isEmpty(q)){
				Map<String,Map<String,Object>> mMap = systemService.list2Map(results, "id");
				String[] def = defaultVal.split(",");
				if(def.length >1 && !mMap.containsKey(def[1])){
					Map<String,Object> val = systemService.findOneForJdbc(filed+sql.toString()+" and m."+def[0]+" = ?",def[1]);
					results.add(val);
				}
			}
			dataGrid.setResults(results);
			dataGrid.setTotal(Integer.valueOf(systemService.findForJdbc("select count(m.id) as cou "+sql.toString()).get(0).get("cou").toString()));
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		TagUtil.datagrid(response, dataGrid);
	}
	
	
	/**
	 * 供应商物资分页参照模糊查询
	 * 
	 * @author zhuhd 2018-3-27
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "supplierMateList")
	@ResponseBody
	public void supplierMateList(LBaMaterialEntity depart,HttpServletRequest request,DataGrid dataGrid, HttpServletResponse response) {
		String q = request.getParameter("q") != null ? request.getParameter("q") : "";
		String supplierId = request.getParameter("supplierId") != null ? request.getParameter("supplierId") : "";
		try {
			// 自定义追加查询条件 
			StringBuffer sql = new StringBuffer();
			sql.append("FROM l_ba_material m  ");
			sql.append("inner join l_ba_cont_supplier_material d on  d.supplier_id='"+supplierId+"'  and d.material_id = m.id  ");
			sql.append("left join l_ba_material_type t on t.ID= m.material_type_id ");
			sql.append("left join l_ba_material_mater_unit u on u.id = m.mater_unit_id  ");
			sql.append("left join l_ba_supplier s on s.id = m.supplier_id  ");
			sql.append("left join l_ba_warehouse h on h.id = m.warehouse_id ");
			if(request.getParameter("q") != null){
				sql.append("where ( m.monm_code like '"+q+"%' or m.material_code like '"+q+"%' or m.material_name like '%"+q+"%' ) ");
			}
			String filed ="select m.id as id ,m.material_code as materialCode,m.material_name as materialName ,m.monm_code as monmCode,m.spec_model as specModel ,t.type_name as typeName,u.type_name as unit"
					+ ",m.unit_price as unitPrice ,s.supplier_full_name as supplierName,m.supplier_id as supplierId,h.warehouse_name  as warehouseName ,m.warehouse_id as warehouseId,m.is_batch as isBatch,m.is_shelf_life as isShelfLife,m.is_bar_code as isBarCode ";
			List<Map<String,Object>> results = systemService.findForJdbc(filed+sql.toString(), dataGrid.getPage(), dataGrid.getRows());
			dataGrid.setResults(results);
			dataGrid.setTotal(Integer.valueOf(systemService.findForJdbc("select count(m.id) as cou "+sql.toString()).get(0).get("cou").toString()));
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		TagUtil.datagrid(response, dataGrid);
	}
	
	
	/**
	 * 仓库物资分页参照模糊查询
	 * 
	 * @author zhuhd 2018-3-27
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "warsHouseMateList")
	@ResponseBody
	public void warsHouseMateList(LBaWarehouseEntity house,HttpServletRequest request,DataGrid dataGrid, HttpServletResponse response) {
		String q = request.getParameter("q") != null ? request.getParameter("q") : "";
		String warsHouseId = request.getParameter("warsHouseId") != null ? request.getParameter("warsHouseId") : "";
		String showBalance = request.getParameter("showBalance");
		try {
			// 自定义追加查询条件 
			StringBuffer sql = new StringBuffer();
			sql.append("FROM l_ba_material m  ");
			sql.append("inner join l_ba_cont_warehouse_material d on  d.warehouse_id='"+warsHouseId+"'  and d.material_id = m.id  ");
			sql.append("left join l_ba_material_type t on t.ID= m.material_type_id ");
			sql.append("left join l_ba_material_mater_unit u on u.id = m.mater_unit_id  ");
			sql.append("left join l_ba_supplier s on s.id = m.supplier_id  ");
			sql.append("left join l_ba_warehouse h on h.id = m.warehouse_id ");
			if(request.getParameter("q") != null){
				sql.append("where ( m.monm_code like '"+q+"%' or m.material_code like '"+q+"%' or m.material_name like '%"+q+"%' ) ");
			}
			String filed ="select m.id as id ,m.material_code as materialCode,m.material_name as materialName ,m.monm_code as monmCode,m.spec_model as specModel ,t.type_name as typeName,u.type_name as unit"
					+ ",m.unit_price as unitPrice ,s.supplier_full_name as supplierName,m.supplier_id as supplierId,h.warehouse_name  as warehouseName ,m.warehouse_id as warehouseId,m.is_batch as isBatch,m.is_shelf_life as isShelfLife,m.is_bar_code as isBarCode ";
			if("1".equals(showBalance)){
				sql.append("left join (select  warehouse_id,material_id,sum(num) num from L_BA_MATE_INVENTORY where warehouse_id ='"+warsHouseId+"' group by warehouse_id,material_id) b1 on b1.material_id = m.id ");
				sql.append("left join (select warehouse_id,material_id,sum(quantity) num from L_ST_OCCUPY_VIEW where warehouse_id ='"+warsHouseId+"' group by warehouse_id,material_id) b2 on b2.material_id = m.id ");
				sql.append(" where b1.num  >0 ");
				filed += ",b1.num quantity, "+SqlUtil.isNull("b1.num ","0")+" - "+SqlUtil.isNull("b2.num ","0")+ " justquantity ";
			}
			List<Map<String,Object>> results = systemService.findForJdbc(filed+sql.toString(), dataGrid.getPage(), dataGrid.getRows());
			dataGrid.setResults(results);
			dataGrid.setTotal(Integer.valueOf(systemService.findForJdbc("select count(m.id) as cou "+sql.toString()).get(0).get("cou").toString()));
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		TagUtil.datagrid(response, dataGrid);
	}
	/**
	 * 供应商分页参照模糊查询
	 * 
	 * @author zhuhd 2018-3-27
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "supplierList")
	@ResponseBody
	public void supplierList(LBaSupplierEntity depart,HttpServletRequest request,DataGrid dataGrid, HttpServletResponse response) {
		String q = request.getParameter("q") != null ? request.getParameter("q") : "";
		CriteriaQuery cq = new CriteriaQuery(LBaSupplierEntity.class, dataGrid);
		String defaultVal = request.getParameter("defaultVal") != null ? request.getParameter("defaultVal") : "";
		String materialIds = request.getParameter("materialIds");
		// 查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, depart);
		try {
			
			// 自定义追加查询条件  
			Disjunction disjunction = Restrictions.disjunction();
			disjunction.add(Restrictions.like("supplierCode", q,MatchMode.ANYWHERE).ignoreCase());
			disjunction.add(Restrictions.like("supplierFullName", q,MatchMode.ANYWHERE).ignoreCase());
			disjunction.add(Restrictions.like("spllCode", q,MatchMode.ANYWHERE).ignoreCase());
			cq.add(disjunction); 
			//供应商编码不能为空
			Conjunction criteria =  Restrictions.conjunction();
			criteria.add(Restrictions.isNotNull("supplierCode"));
			criteria.add(Restrictions.eq("isPurchase", "1"));
			criteria.add(Restrictions.eq("isCease", "0")); 
			//根据物资筛选供应商
			if(StringUtil.isNotEmpty(materialIds)){
				String[] mateArray = materialIds.split(",");
				StringBuffer sql = new StringBuffer();
				sql.append(" select supplier_id \"id\",count(id) from l_ba_cont_supplier_material c where  1 = 1");
				sql.append(" and material_id in ('"+materialIds.replace(",", "','")+"')");
				sql.append(" group by supplier_id HAVING count(id) >" +(mateArray.length-1) ); 
				List<Map<String,Object>> list = systemService.findForJdbc(sql.toString());
				if(list != null && list.size() >0){
					String[] ids = new String[list.size()];
					int j=0;
					for(Map<String,Object> map :list){
						ids[j]=map.get("id").toString();
						j++;
					}
					criteria.add(Restrictions.in("id", ids));
				}else{
					return;
				}
			}
			cq.add(criteria); 
			cq.add();
			this.systemService.getDataGridReturn(cq, true); 
			String[]  defArray = defaultVal.split(",");
			if(defArray.length>1 && StringUtil.isNotEmpty(defArray[1]) ){
				LBaSupplierEntity d = systemService.findUniqueByProperty(LBaSupplierEntity.class, defArray[0], defArray[1]);
				List<LBaSupplierEntity> results = dataGrid.getResults();
				if( results ==null) return;
				if(d !=null  ){
					for(LBaSupplierEntity dept : results){
						//如果有就结束
						if(dept.getId().equals(d.getId())){
							TagUtil.datagrid(response, dataGrid);
							return ;
						}
					}
					results.add(d);
				}
				dataGrid.setResults(results);
			} 
			TagUtil.datagrid(response, dataGrid);
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		
	}
	
	
	/**
	 * 根据仓库和供应商查询物资
	 * 
	 * @author zxl 2018-7-20
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "warsSupMateList")
	@ResponseBody
	public void warsSupMateList(LBaWarehouseEntity house,HttpServletRequest request,DataGrid dataGrid, HttpServletResponse response) {
		String q = request.getParameter("q") != null ? request.getParameter("q") : "";
		String warsHouseId = request.getParameter("warsHouseId") != null ? request.getParameter("warsHouseId") : "";
		String supplierId = request.getParameter("supplierId") != null ? request.getParameter("supplierId") : "";
		String isHighCons = request.getParameter("isHighCons") != null ? request.getParameter("isHighCons") : "";
		String isBarCode = request.getParameter("isBarCode") != null ? request.getParameter("isBarCode") : "";
		String isAssets = request.getParameter("isAssets") != null ? request.getParameter("isAssets") : "";
		String isLabourService = request.getParameter("isLabourService") ;
		String id = request.getParameter("id") != null ? request.getParameter("id") : "";
		try {
			// 自定义追加查询条件 
			StringBuffer sql = new StringBuffer();
			sql.append("FROM l_ba_material m  ");
			if(StringUtil.isNotEmpty(warsHouseId)){
				sql.append("inner join l_ba_cont_warehouse_material d on  d.warehouse_id='"+warsHouseId+"'  and d.material_id = m.id  ");
			}else{
				sql.append("left join l_ba_cont_warehouse_material d on  d.warehouse_id='"+warsHouseId+"'  and d.material_id = m.id  ");
			}
			sql.append("left join l_ba_cont_supplier_material sm  on   sm.material_id = m.id  ");
			sql.append("left join l_ba_material_type t on t.ID= m.material_type_id ");
			sql.append("left join l_ba_material_mater_unit u on u.id = m.mater_unit_id  ");
			sql.append("left join l_ba_supplier s on s.id = sm.supplier_id  ");
			sql.append("left join l_ba_warehouse h on h.id = d.warehouse_id ");
			sql.append(" where 1=1 ");
			if(request.getParameter("supplierId") != null){
				sql.append(" and sm.supplier_id='"+supplierId+"' ");
			}
			if(StringUtil.isNotEmpty(isBarCode)){
				sql.append(" and m.is_bar_code='"+isBarCode+"' ");
			}
			if(StringUtil.isNotEmpty(isHighCons)){
				sql.append(" and m.is_high_cons='"+isHighCons+"' ");
			}
			if(StringUtil.isNotEmpty(isAssets)){
				sql.append(" and m.is_assets ='"+isAssets+"' ");
			}
			if(StringUtil.isNotEmpty(isLabourService)){
				sql.append(" and m.IS_LABOUR_SERVICE ='"+isLabourService+"' ");
			}
			if(StringUtil.isNotEmpty(id)){
				sql.append(" and m.id ='"+id+"' ");
			}
			if(request.getParameter("q") != null){
				sql.append("and ( m.monm_code like '"+q+"%' or m.material_code like '"+q+"%' or m.material_name like '%"+q+"%' ) ");
			}
		
			String filed ="select m.id as id ,m.material_code as materialCode,m.material_name as materialName ,m.monm_code as monmCode,m.spec_model as specModel ,t.type_name as typeName,u.type_name as unit"
					+ ",m.unit_price as unitPrice ,s.supplier_full_name as supplierName,m.supplier_id as supplierId,h.warehouse_name  as warehouseName ,m.warehouse_id as warehouseId,m.is_batch as isBatch"
					+ ",m.is_shelf_life as isShelfLife,m.is_bar_code as isBarCode,m.shelf_life shelfLife ,m.exp_unit expUnit ";
			List<Map<String,Object>> results = systemService.findForJdbc(filed+sql.toString(), dataGrid.getPage(), dataGrid.getRows());
			dataGrid.setResults(results);
			dataGrid.setTotal(Integer.valueOf(systemService.findForJdbc("select count(m.id) as cou "+sql.toString()).get(0).get("cou").toString()));
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 经济分类下拉分页查询
	 * @param entity
	 * @param request
	 * @param dataGrid
	 * @param response
	 */
	@RequestMapping(params = "baEconList")
	@ResponseBody
	public void baEconList(BaEconomicsEntity entity,HttpServletRequest request,DataGrid dataGrid, HttpServletResponse response) {
		String q = request.getParameter("q") != null ? request.getParameter("q") : "";
		CriteriaQuery cq = new CriteriaQuery(BaEconomicsEntity.class, dataGrid);
		String defaultVal = request.getParameter("defaultVal") != null ? request.getParameter("defaultVal") : "";
		//是否末级
		String isLast = request.getParameter("isLast")==null?null:request.getParameter("isLast").toString();
		// 查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, entity);
		try {
			if(StringUtil.isNotEmpty(isLast)){
				cq.eq("viflater", "1");
			}
			Disjunction disjunction = Restrictions.disjunction();
			disjunction.add(Restrictions.like("vcode", q,MatchMode.ANYWHERE).ignoreCase());
			disjunction.add(Restrictions.like("vname", q,MatchMode.ANYWHERE).ignoreCase());
			cq.add(disjunction);
			cq.add();
			this.systemService.getDataGridReturn(cq, true); 
			String[]  defArray = defaultVal.split(",");
			if(defArray.length>1 && StringUtil.isNotEmpty(defArray[1]) ){
				BaEconomicsEntity d = systemService.findUniqueByProperty(BaEconomicsEntity.class, defArray[0], defArray[1]);
				List<BaEconomicsEntity> results = dataGrid.getResults();
				if(d !=null){
					for(BaEconomicsEntity ba : results){
						//如果有就结束
						if(ba.getId().equals(d.getId())){
							TagUtil.datagrid(response, dataGrid);
							return ;
						}
					}
					results.add(d);
				}
				dataGrid.setResults(results);
			} 
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 自定义费用项目下拉分页查询
	 * @param entity
	 * @param request
	 * @param dataGrid
	 * @param response
	 */
	@RequestMapping(params = "baExpendItemList")
	@ResponseBody
	public void baExpendItemList(BudgExpendItemEntity entity,HttpServletRequest request,DataGrid dataGrid, HttpServletResponse response) {
		String q = request.getParameter("q") != null ? request.getParameter("q") : "";
		CriteriaQuery cq = new CriteriaQuery(BudgExpendItemEntity.class, dataGrid);
		String defaultVal = request.getParameter("defaultVal") != null ? request.getParameter("defaultVal") : "";
		//是否末级
		String isLast = request.getParameter("isLast")==null?null:request.getParameter("isLast").toString();
		// 查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, entity);
		try {
			Disjunction disjunction = Restrictions.disjunction();
			disjunction.add(Restrictions.like("itemCode", q,MatchMode.ANYWHERE).ignoreCase());
			disjunction.add(Restrictions.like("itemName", q,MatchMode.ANYWHERE).ignoreCase());
			cq.add(disjunction);
			
			cq.eq("budgYear", Integer.valueOf(9999));
			cq.eq("extend3", "N"); //未停用
			if(StringUtil.isNotEmpty(isLast)){
				cq.eq("isLast", "Y");
			}
			cq.addOrder("itemCode", SortDirection.asc);
			cq.add();
			this.systemService.getDataGridReturn(cq, true); 
			String[]  defArray = defaultVal.split(",");
			if(defArray.length>1 && StringUtil.isNotEmpty(defArray[1]) ){
				String hql = " from BudgExpendItemEntity where budgYear=9999 and "+defArray[0]+" = '"+defArray[1]+"'";
				List<BudgExpendItemEntity> d = systemService.findHql(hql, new Object[]{});
				List<BudgExpendItemEntity> results = dataGrid.getResults();
				if(d !=null){
					for(BudgExpendItemEntity ba : results){
						//如果有就结束
						if(ba.getId().equals(d.get(0).getId())){
							TagUtil.datagrid(response, dataGrid);
							return ;
						}
					}
					results.add(d.get(0));
				}
				dataGrid.setResults(results);
			} 
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 收入项目下拉
	 * @param entity
	 * @param request
	 * @param dataGrid
	 * @param response
	 */
	@RequestMapping(params = "baItemIncom")
	@ResponseBody
	public void baItemIncom(BaItemIncomEntity entity,HttpServletRequest request,DataGrid dataGrid, HttpServletResponse response) {
		String q = request.getParameter("q") != null ? request.getParameter("q") : "";
		CriteriaQuery cq = new CriteriaQuery(BaItemIncomEntity.class, dataGrid);
		String defaultVal = request.getParameter("defaultVal") != null ? request.getParameter("defaultVal") : "";
		//是否末级
		String isLast = request.getParameter("isLast")==null?null:request.getParameter("isLast").toString();
		// 查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, entity);
		try {
			Disjunction disjunction = Restrictions.disjunction();
			disjunction.add(Restrictions.like("vcode", q,MatchMode.ANYWHERE).ignoreCase());
			disjunction.add(Restrictions.like("vname", q,MatchMode.ANYWHERE).ignoreCase());
			cq.add(disjunction);
			if(StringUtil.isEmpty(isLast)){ //默认查询末级
				cq.eq("isLast", "Y");
			}
			cq.eq("isStop", "N"); //未停用
			cq.add();
			this.systemService.getDataGridReturn(cq, true); 
			String[]  defArray = defaultVal.split(",");
			if(defArray.length>1 && StringUtil.isNotEmpty(defArray[1]) ){
				BaItemIncomEntity d = systemService.findUniqueByProperty(BaItemIncomEntity.class, defArray[0], defArray[1]);
				List<BaItemIncomEntity> results = dataGrid.getResults();
				if(d !=null){
					for(BaItemIncomEntity dept : results){
						//如果有就结束
						if(dept.getId().equals(d.getId())){
							TagUtil.datagrid(response, dataGrid);
							return ;
						}
					}
					results.add(d);
				}
				dataGrid.setResults(results);
			} 
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 计量单位下拉查询
	 * @param entity
	 * @param request
	 * @param dataGrid
	 * @param response
	 */
	@RequestMapping(params = "unitList")
	@ResponseBody
	public void unitList(LBaMaterialMaterUnitEntity entity,HttpServletRequest request,DataGrid dataGrid, HttpServletResponse response) {
		String q = request.getParameter("q") != null ? request.getParameter("q") : "";
		CriteriaQuery cq = new CriteriaQuery(LBaMaterialMaterUnitEntity.class, dataGrid);
		String defaultVal = request.getParameter("defaultVal") != null ? request.getParameter("defaultVal") : "";
		// 查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, entity);
		try {
			
			Disjunction disjunction = Restrictions.disjunction();
			disjunction.add(Restrictions.like("typeCode", q,MatchMode.ANYWHERE).ignoreCase());
			disjunction.add(Restrictions.like("typeName", q,MatchMode.ANYWHERE).ignoreCase());
			
			cq.add(disjunction);
			cq.add();
			cq.addOrder("typeCode", SortDirection.asc);
			this.systemService.getDataGridReturn(cq, true); 
			String[]  defArray = defaultVal.split(",");
			if(defArray.length>1 && StringUtil.isNotEmpty(defArray[1]) ){
				LBaMaterialMaterUnitEntity d = systemService.findUniqueByProperty(LBaMaterialMaterUnitEntity.class, defArray[0], defArray[1]);
				List<LBaMaterialMaterUnitEntity> results = dataGrid.getResults();
				if(d !=null){
					for(LBaMaterialMaterUnitEntity ba : results){
						//如果有就结束
						if(ba.getId().equals(d.getId())){
							TagUtil.datagrid(response, dataGrid);
							return ;
						}
					}
					results.add(d);
				}
				dataGrid.setResults(results);
			} 
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 资金来源下拉查询
	 * @param entity
	 * @param request
	 * @param dataGrid
	 * @param response
	 */
	@RequestMapping(params = "fundSourceList")
	@ResponseBody
	public void fundSourceList(BaBankrollSourceEntity entity,HttpServletRequest request,DataGrid dataGrid, HttpServletResponse response) {
		String q = request.getParameter("q") != null ? request.getParameter("q") : "";
		CriteriaQuery cq = new CriteriaQuery(BaBankrollSourceEntity.class, dataGrid);
		String defaultVal = request.getParameter("defaultVal") != null ? request.getParameter("defaultVal") : "";
		// 查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, entity);
		try {
			
			Disjunction disjunction = Restrictions.disjunction();
			disjunction.add(Restrictions.like("code", q,MatchMode.ANYWHERE).ignoreCase());
			disjunction.add(Restrictions.like("name", q,MatchMode.ANYWHERE).ignoreCase());
			cq.eq("iflater", "Y"); //默认查询末级
			cq.add(disjunction);
			cq.add();
			//cq.addOrder("code", SortDirection.asc);
			this.systemService.getDataGridReturn(cq, true); 
			String[]  defArray = defaultVal.split(",");
			if(defArray.length>1 && StringUtil.isNotEmpty(defArray[1]) ){
				BaBankrollSourceEntity d = systemService.findUniqueByProperty(BaBankrollSourceEntity.class, defArray[0], defArray[1]);
				List<BaBankrollSourceEntity> results = dataGrid.getResults();
				if(d !=null){
					for(BaBankrollSourceEntity ba : results){
						//如果有就结束
						if(ba.getId().equals(d.getId())){
							TagUtil.datagrid(response, dataGrid);
							return ;
						}
					}
					results.add(d);
				}
				dataGrid.setResults(results);
			} 
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 功能分类下拉查询
	 * @param entity
	 * @param request
	 * @param dataGrid
	 * @param response
	 */
	@RequestMapping(params = "baFunctionList")
	@ResponseBody
	public void baFunctionList(BaFunctionEntity entity,HttpServletRequest request,DataGrid dataGrid, HttpServletResponse response) {
		String q = request.getParameter("q") != null ? request.getParameter("q") : "";
		CriteriaQuery cq = new CriteriaQuery(BaFunctionEntity.class, dataGrid);
		String defaultVal = request.getParameter("defaultVal") != null ? request.getParameter("defaultVal") : "";
		//是否末级
		String isLast = request.getParameter("isLast")==null?null:request.getParameter("isLast").toString();
		// 查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, entity);
		try {
			if(StringUtil.isNotEmpty(isLast)){
				cq.eq("viflater", "1");
			}
			Disjunction disjunction = Restrictions.disjunction();
			disjunction.add(Restrictions.like("vcode", q,MatchMode.ANYWHERE).ignoreCase());
			disjunction.add(Restrictions.like("vname", q,MatchMode.ANYWHERE).ignoreCase());
			cq.add(disjunction);
			cq.addOrder("vcode", SortDirection.asc);
			cq.add();
			this.systemService.getDataGridReturn(cq, true); 
			String[]  defArray = defaultVal.split(",");
			if(defArray.length>1 && StringUtil.isNotEmpty(defArray[1]) ){
				BaFunctionEntity d = systemService.findUniqueByProperty(BaFunctionEntity.class, defArray[0], defArray[1]);
				List<BaFunctionEntity> results = dataGrid.getResults();
				if(d !=null){
					for(BaFunctionEntity ba : results){
						//如果有就结束
						if(ba.getId().equals(d.getId())){
							TagUtil.datagrid(response, dataGrid);
							return ;
						}
					}
					results.add(d);
				}
				dataGrid.setResults(results);
			} 
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		TagUtil.datagrid(response, dataGrid);
	}
	
	@RequestMapping(params = "areaList")
	@ResponseBody
	public void areaList(BaAreaEntity depart,HttpServletRequest request,DataGrid dataGrid, HttpServletResponse response) {
		String q = request.getParameter("q") != null ? request.getParameter("q") : "";
		String defaultVal = request.getParameter("defaultVal") != null ? request.getParameter("defaultVal") : "";
		CriteriaQuery cq = new CriteriaQuery(BaAreaEntity.class, dataGrid);
		// 查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, depart);
		try {
			// 自定义追加查询条件 
			if(request.getParameter("q") != null){
				Disjunction disjunction = Restrictions.disjunction();
				disjunction.add(Restrictions.like("areaCode", q,MatchMode.ANYWHERE).ignoreCase());
				disjunction.add(Restrictions.like("areaName", q,MatchMode.ANYWHERE).ignoreCase());
				//disjunction.add(Restrictions.sqlRestriction("(is_Last='Y' or area_name in('北京市','重庆市','上海市','天津市'))"));
				cq.add(disjunction);
			}
			cq.add();
			this.systemService.getDataGridReturn(cq, true);
			if(StringUtil.isNotEmpty(defaultVal)){
				String[]  defArray = defaultVal.split(",");
				if(defArray.length>1 && StringUtil.isNotEmpty(defArray[1]) ){
					BaAreaEntity d = systemService.findUniqueByProperty(BaAreaEntity.class, defArray[0], defArray[1]);
					List<BaAreaEntity> results = dataGrid.getResults();
					if(d !=null){
						for(BaAreaEntity dept : results){
							//如果有就结束
							if(dept.getId().equals(d.getId())){
								TagUtil.datagrid(response, dataGrid);
								return ;
							}
						}
						results.add(d);
					}
					dataGrid.setResults(results);
				} 
			}
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		TagUtil.datagrid(response, dataGrid);
	}
	
	
	
	
	
	/**
	 * 资产卡片下拉
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "cardList")
	@ResponseBody
	public void cardList(HttpServletRequest request, DataGrid dataGrid, HttpServletResponse response) {
		String q = request.getParameter("q") != null ? request.getParameter("q") : "";
		String defaultVal = request.getParameter("defaultVal") != null ? request.getParameter("defaultVal") : "";
		try {
			StringBuffer sb = new StringBuffer();
			sb.append(" from ppe_cards where 1=1");
			if(request.getParameter("q") != null){
				sb.append(" and ( asset_coding like '%"+q+"%' ");
				sb.append(" or asset_name like '%"+q+"%' )");
			}
			/*sb.append("  order by asset_coding ");*/
			String sql="select asset_coding ,asset_name,spec_model";
			List<Map<String,Object>> results = systemService.findForJdbc(sql+sb.toString(), dataGrid.getPage(), dataGrid.getRows());
			//设置默认值
			if(StringUtil.isNotEmpty(defaultVal) && StringUtil.isEmpty(q)){
				Map<String,Map<String,Object>> mMap = systemService.list2Map(results, "id");
				String[] def = defaultVal.split(",");
				if(def.length >1 && !mMap.containsKey(def[1])){
					Map<String,Object> val = systemService.findOneForJdbc(sql+sb.toString()+" and  "+def[0]+" = ?",def[1]);
					results.add(val);
				}
			}
			dataGrid.setResults(results);
			dataGrid.setTotal(Integer.valueOf(systemService.findForJdbc("select count(id) as cou "+sb.toString()).get(0).get("cou").toString()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		TagUtil.datagrid(response, dataGrid);
	}
	
}
