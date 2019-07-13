
package cn.com.linkwide.cont.negotiation.conreqinstructionsmain.controller;
import java.io.IOException;
import java.text.SimpleDateFormat;
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
import org.jeecgframework.jwt.util.ResponseMessage;
import org.jeecgframework.jwt.util.Result;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.tag.core.easyui.TagUtil;
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

import cn.com.linkwide.common.tools.BillCodeUtil;
import cn.com.linkwide.cont.negotiation.conofferbill.entity.ConOfferBillEntity;
import cn.com.linkwide.cont.negotiation.conreqinstructionsdetail.entity.ConReqInstructionsDetailEntity;
import cn.com.linkwide.cont.negotiation.conreqinstructionsmain.entity.ConReqInstructionsMainEntity;
import cn.com.linkwide.cont.negotiation.conreqinstructionsmain.page.ConReqInstructionsMainPage;
import cn.com.linkwide.cont.negotiation.conreqinstructionsmain.service.ConReqInstructionsMainServiceI;
import cn.com.linkwide.cont.negotiation.conwarrantybill.entity.ConWarrantyBillEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**   
 * @Title: Controller
 * @Description: 请示单主表
 * @author onlineGenerator
 * @date 2019-04-26 11:11:09
 * @version V1.0   
 *
 */
@Api(value="ConReqInstructionsMain",description="请示单主表",tags="conReqInstructionsMainController")
@Controller
@RequestMapping("/conReqInstructionsMainController")
public class ConReqInstructionsMainController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ConReqInstructionsMainController.class);

	@Autowired
	private ConReqInstructionsMainServiceI conReqInstructionsMainService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;

	/**
	 * 请示单主表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("cn/com/linkwide/cont/negotiation/conreqinstructionsmain/conReqInstructionsMainList");
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
	public void datagrid(ConReqInstructionsMainEntity conReqInstructionsMain,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ConReqInstructionsMainEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, conReqInstructionsMain);
		try{
		//自定义追加查询条件
		String query_requDate_begin = request.getParameter("requDate_begin");
		String query_requDate_end = request.getParameter("requDate_end");
		if(StringUtil.isNotEmpty(query_requDate_begin)){
			cq.ge("requDate", new SimpleDateFormat("yyyy-MM-dd").parse(query_requDate_begin));
		}
		if(StringUtil.isNotEmpty(query_requDate_end)){
			cq.le("requDate", new SimpleDateFormat("yyyy-MM-dd").parse(query_requDate_end));
		}
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.conReqInstructionsMainService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除请示单主表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(ConReqInstructionsMainEntity conReqInstructionsMain, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		conReqInstructionsMain = systemService.getEntity(ConReqInstructionsMainEntity.class, conReqInstructionsMain.getId());
		String message = "请示单主表删除成功";
		try{
			conReqInstructionsMainService.delMain(conReqInstructionsMain);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "请示单主表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 批量删除请示单主表
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		String message = "请示单主表删除成功";
		try{
			for(String id:ids.split(",")){
				ConReqInstructionsMainEntity conReqInstructionsMain = systemService.getEntity(ConReqInstructionsMainEntity.class,
				id
				);
				conReqInstructionsMainService.delMain(conReqInstructionsMain);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "请示单主表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	 
	 /**
		 * 生成单据号
		 * @return
		 *2017年12月4日
		 *@author 
		 */
		public String generateBillCode(){
			String	billcode="";
		//	String hql=" from ";
			TSFunction function = this.conReqInstructionsMainService.findUniqueByProperty(TSFunction.class, "functionUrl", "conReqInstructionsMainController.do?list");
			if(function==null){
				throw new BusinessException("未设置合同信息的单据类型");
			}
			billcode=BillCodeUtil.productBillCode(function.getBillType());
			return billcode;
		}
	 
	 
	/**
	 * 添加请示单主表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(ConReqInstructionsMainEntity conReqInstructionsMain,ConReqInstructionsMainPage conReqInstructionsMainPage, HttpServletRequest request) {
		List<ConReqInstructionsDetailEntity> conReqInstructionsDetailList =  conReqInstructionsMainPage.getConReqInstructionsDetailList();
		AjaxJson j = new AjaxJson();
		String message = "添加成功";
		try{
			conReqInstructionsMain.setRequExtent1("0");
			conReqInstructionsMain.setRequNo(generateBillCode());
			conReqInstructionsMainService.addMain(conReqInstructionsMain, conReqInstructionsDetailList);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "请示单主表添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	/**
	 * 更新请示单主表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doConfirm")
	@ResponseBody
	public AjaxJson doConfirm(ConReqInstructionsMainEntity conReqInstructionsMain,ConReqInstructionsMainPage conReqInstructionsMainPage, HttpServletRequest request) {
		List<ConReqInstructionsDetailEntity> conReqInstructionsDetailList =  conReqInstructionsMainPage.getConReqInstructionsDetailList();
		AjaxJson j = new AjaxJson();
		String message = "请示单审核成功";
		if(conReqInstructionsDetailList==null||conReqInstructionsDetailList.size()==0 ){
			message="审核数据没有变化请重新操作";
			j.setMsg(message);
			return j;
		}
		
		try{
			
			int i=0;
			String venName="";
			String equName="";
			for (ConReqInstructionsDetailEntity conReqInstructionsDetail : conReqInstructionsDetailList) {
				ConOfferBillEntity conOfferBill  = systemService.getEntity(ConOfferBillEntity.class,conReqInstructionsDetail.getOfferId()); 
				if("1".equals(conReqInstructionsDetail.getIsPass())){
					conOfferBill.setBillState("请示通过");
					conReqInstructionsDetail.setIsFinalPass("0");
					i++;
					
				}
				if("0".equals(conReqInstructionsDetail.getIsPass())&& "1".equals(conReqInstructionsDetail.getIsFinalPass()) ){
					venName=venName+conReqInstructionsDetail.getVenName()+"、";
					equName=equName+conReqInstructionsDetail.getEquName()+"、";
					
					conReqInstructionsDetail.setIsPass("1");
				} 
				if("0".equals(conReqInstructionsDetail.getIsPass())&& !"1".equals(conReqInstructionsDetail.getIsFinalPass())){
					conOfferBill.setBillState("未中标");
				}
				systemService.saveOrUpdate(conReqInstructionsDetail);
				systemService.saveOrUpdate(conOfferBill);
			}
			if(!"".equals(venName)&& !"".equals(equName)){
				message="供应商名称为："+venName.substring(0, venName.length()-1)+"对资产名称为："+equName.substring(0, equName.length()-1)+"报价部门已终选不能取消审核";
			}
			if(i>0){
				String sql ="update con_req_instructions_main set requ_extent2='1' where id='"+conReqInstructionsMain.getId()+"'";
				systemService.updateBySqlString(sql);
			}else if(i==0){
				String sql ="update con_req_instructions_main set requ_extent2='0' where id='"+conReqInstructionsMain.getId()+"'";
				systemService.updateBySqlString(sql);
			}
			conReqInstructionsMainService.updateMain(conReqInstructionsMain, conReqInstructionsDetailList);
		}catch(Exception e){
			e.printStackTrace();
			message = "请示单审核失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	
	/**
	 * 更新请示单主表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doFinalChiose")
	@ResponseBody
	public AjaxJson doFinalChiose(ConReqInstructionsMainEntity conReqInstructionsMain,ConReqInstructionsMainPage conReqInstructionsMainPage, HttpServletRequest request) {
		List<ConReqInstructionsDetailEntity> conReqInstructionsDetailList =  conReqInstructionsMainPage.getConReqInstructionsDetailList();
		AjaxJson j = new AjaxJson();
		String message = "请示单部门终选成功";
		
		try{
			String venName="";
			String equName="";
			for (ConReqInstructionsDetailEntity conReqInstructionsDetail : conReqInstructionsDetailList) {
				ConOfferBillEntity conOfferBill  = systemService.getEntity(ConOfferBillEntity.class,conReqInstructionsDetail.getOfferId()); 
				if("1".equals(conReqInstructionsDetail.getIsFinalPass())){
					conOfferBill.setBillState("部门终选通过");
					
				}if("0".equals(conReqInstructionsDetail.getIsFinalPass())){
				 	String sql="select  count(id) from con_final_confirmBill where req_id='"+conReqInstructionsDetail.getId()+"'";
				 	Integer cont = Integer.valueOf(systemService.getCountForJdbc(sql.toString()).toString());
				 	if(cont>0){
				 		venName=venName+conReqInstructionsDetail.getVenName()+"、";
				 		equName=equName+conReqInstructionsDetail.getEquName()+"、";
				 	}else{
					conOfferBill.setBillState("未中标");  
				 	}
				} 
				
				systemService.saveOrUpdate(conOfferBill);
			}
			if(!"".equals(venName)&&!"".equals(equName)){
				message="供应商名称为："+venName.substring(0, venName.length()-1)+"对资产名称为："+equName.substring(0, equName.length()-1)+"院长审批单中已产生数据不能取消终选";
			}
			
			conReqInstructionsMainService.updateMain(conReqInstructionsMain, conReqInstructionsDetailList);
			
		}catch(Exception e){
			e.printStackTrace();
			message = "请示单终选失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	
	
	
	/**
	 * 更新请示单主表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(ConReqInstructionsMainEntity conReqInstructionsMain,ConReqInstructionsMainPage conReqInstructionsMainPage, HttpServletRequest request) {
		List<ConReqInstructionsDetailEntity> conReqInstructionsDetailList =  conReqInstructionsMainPage.getConReqInstructionsDetailList();
		AjaxJson j = new AjaxJson();
		String message = "更新成功";
		try{
			conReqInstructionsMainService.updateMain(conReqInstructionsMain, conReqInstructionsDetailList);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "更新请示单主表失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	
	/**
	 * 弹出审核后的报价单
	 * @param request
	 * @return
	 */
	@RequestMapping(params="pkplanSelect")
	public ModelAndView pkplanSelect(HttpServletRequest request){
	 request.setAttribute("flag", request.getParameter("flag"));
		return new ModelAndView("cn/com/linkwide/cont/negotiation/conreqinstructionsmain/implOfferList");
	}
	

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "datagridwh")
	public void datagridwh(ConOfferBillEntity conOfferBill,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		//查询条件组装器
		try{
		//自定义追加查询条件
			 String flag=request.getParameter("flag");
				 String payId="";
				  
				 if(StringUtil.isNotEmpty(flag)){
					 payId = "'"+flag.replace(",", "','")+"'";
					 
				 } 
		StringBuffer  sql = new StringBuffer();
		sql.append(" select a.id id ,b.id bId,b.apply_code applyCode,b.apply_date applyDate,b.equ_code equCode,a.equ_name equName,b.equ_card equCard,a.spec_typ specType,b.pur_price purPrice,a.offer_price offerPrice,a.pur_date purDate,a.birth_factory birthFactory, ");
		sql.append(" b.use_type useType,b.is_charge isCharge,b.average_cost averageCost,b.annual_check_number annualCheckNumber,b.annual_income annualIncome,b.apply_category ApplyCategory,b.guarantee_type guaranteeType,b.guarantee_type_qt guaranteeTypeQt,");
		sql.append(" b.guarantee_years guaranteeYears,a.service_conten ServiceConten,a.bill_state billState,b.bill_state pBillState,b.dept_code deptCode,b.dept_name deptName,a.sys_org_code sysOrgCode,c.supplier_full_name supplierFullName,b.release_date releaseDate,a.confirm_date confirmDate,a.is_confirm isConfirm,b.cost_group costGroup,a.offer_one OfferOne  ");
		sql.append(" from con_offer_bill a left join  con_warranty_bill b  on a.apply_id=b.id ");
		sql.append(" left join l_ba_supplier c on a.sys_org_code=c.id" );
		sql.append(" where 1=1      ");
		sql.append(" and a.id not in (select b.offer_id from con_req_instructions_detail b ) and a.conferm_state='已审核'");
		if(StringUtil.isNotEmpty(payId)){
		sql.append(" and a.id not in ("+payId+") ");
		}
		List<Map<String,Object>> result = systemService.findForJdbc(sql.toString(), dataGrid.getPage(), dataGrid.getRows());
		dataGrid.setResults(result);
		StringBuffer  contsql = new StringBuffer();
		contsql.append("select count(a.id)  con  ");
		contsql.append(" from con_offer_bill a ");
		contsql.append(" where 1=1      ");
		contsql.append(" and a.id not in (select b.offer_id from con_req_instructions_detail b ) and a.is_confirm='1'");
		if(StringUtil.isNotEmpty(payId)){
			sql.append(" and a.id not in ("+payId+") ");
			}
		dataGrid.setTotal(Integer.valueOf(systemService.getCountForJdbc(contsql.toString()).toString()));
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		
		TagUtil.datagrid(response, dataGrid);
	}
	
	
	
	
	
	
	
	
	
	

	/**
	 * 请示单主表新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(ConReqInstructionsMainEntity conReqInstructionsMain, HttpServletRequest req) {
		req.setAttribute("reqDate", new Date(System.currentTimeMillis()));
		if (StringUtil.isNotEmpty(conReqInstructionsMain.getId())) {
			conReqInstructionsMain = conReqInstructionsMainService.getEntity(ConReqInstructionsMainEntity.class, conReqInstructionsMain.getId());
			req.setAttribute("conReqInstructionsMainPage", conReqInstructionsMain);
		}
		return new ModelAndView("cn/com/linkwide/cont/negotiation/conreqinstructionsmain/conReqInstructionsMain-add");
	}
	
	/**
	 * 请示单主表编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(ConReqInstructionsMainEntity conReqInstructionsMain, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(conReqInstructionsMain.getId())) {
			conReqInstructionsMain = conReqInstructionsMainService.getEntity(ConReqInstructionsMainEntity.class, conReqInstructionsMain.getId());
			req.setAttribute("conReqInstructionsMainPage", conReqInstructionsMain);
		}
		return new ModelAndView("cn/com/linkwide/cont/negotiation/conreqinstructionsmain/conReqInstructionsMain-update");
	}
	
	
	
	
	/**
	 * 请示单主表编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goConfirm")
	public ModelAndView goConfirm(ConReqInstructionsMainEntity conReqInstructionsMain, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(conReqInstructionsMain.getId())) {
			conReqInstructionsMain = conReqInstructionsMainService.getEntity(ConReqInstructionsMainEntity.class, conReqInstructionsMain.getId());
			req.setAttribute("conReqInstructionsMainPage", conReqInstructionsMain);
		}
		return new ModelAndView("cn/com/linkwide/cont/negotiation/conreqinstructionsmain/conReqInstructionsMain-confirm");
	}
	
	/**
	 * 请示单主表编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goFinalChoice")
	public ModelAndView goFinalChoice(ConReqInstructionsMainEntity conReqInstructionsMain, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(conReqInstructionsMain.getId())) {
			conReqInstructionsMain = conReqInstructionsMainService.getEntity(ConReqInstructionsMainEntity.class, conReqInstructionsMain.getId());
			req.setAttribute("conReqInstructionsMainPage", conReqInstructionsMain);
		}
		return new ModelAndView("cn/com/linkwide/cont/negotiation/conreqinstructionsmain/conReqInstructionsMain-finalChiose");
	}
	
	/**
	 * 加载明细列表[请示单明细]
	 * 
	 * @return
	 */
	@RequestMapping(params = "conReqInstructionsDetailList")
	public ModelAndView conReqInstructionsDetailList(ConReqInstructionsMainEntity conReqInstructionsMain, HttpServletRequest req) {
	
		//===================================================================================
		//获取参数
		String flag=req.getParameter("flag");
		Object id0 = conReqInstructionsMain.getId();
		//===================================================================================
		//查询-请示单明细
	    String hql0 = "from ConReqInstructionsDetailEntity where 1 = 1 AND pK_ID = ? ";
	    try{
	    	List<ConReqInstructionsDetailEntity> conReqInstructionsDetailEntityList = systemService.findHql(hql0,id0);
			req.setAttribute("conReqInstructionsDetailList", conReqInstructionsDetailEntityList);
			req.setAttribute("flag", flag);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return new ModelAndView("cn/com/linkwide/cont/negotiation/conreqinstructionsdetail/conReqInstructionsDetailList");
	}

	
	
	/**
	 * 加载明细列表[请示单明细]
	 * 
	 * @return
	 */
	@RequestMapping(params = "conReqInstructionsDetailFinalList")
	public ModelAndView conReqInstructionsDetaiFinallList(ConReqInstructionsMainEntity conReqInstructionsMain, HttpServletRequest req) {
	
		//===================================================================================
		//获取参数
		Object id0 = conReqInstructionsMain.getId();
		//===================================================================================
		
		
		String deptId=ResourceUtil.getSessionUser().getDepartid();
		//查询-请示单明细
	    String hql0 = "from ConReqInstructionsDetailEntity where 1 = 1 and IS_PASS='1' AND pK_ID = ?  ";
	    try{
	    	List<ConReqInstructionsDetailEntity> conReqInstructionsDetailEntityList = systemService.findHql(hql0,id0);
			req.setAttribute("conReqInstructionsDetailList", conReqInstructionsDetailEntityList);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return new ModelAndView("cn/com/linkwide/cont/negotiation/conreqinstructionsdetail/conReqInstructionDetailFinalList");
	}

	
	
	
    /**
    * 导出excel
    *
    * @param request
    * @param response
    */
    @RequestMapping(params = "exportXls")
    public String exportXls(ConReqInstructionsMainEntity conReqInstructionsMain,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid,ModelMap map) {
    	CriteriaQuery cq = new CriteriaQuery(ConReqInstructionsMainEntity.class, dataGrid);
    	//查询条件组装器
    	org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, conReqInstructionsMain);
    	try{
    	//自定义追加查询条件
    	}catch (Exception e) {
    		throw new BusinessException(e.getMessage());
    	}
    	cq.add();
    	List<ConReqInstructionsMainEntity> list=this.conReqInstructionsMainService.getListByCriteriaQuery(cq, false);
    	List<ConReqInstructionsMainPage> pageList=new ArrayList<ConReqInstructionsMainPage>();
        if(list!=null&&list.size()>0){
        	for(ConReqInstructionsMainEntity entity:list){
        		try{
        		ConReqInstructionsMainPage page=new ConReqInstructionsMainPage();
        		   MyBeanUtils.copyBeanNotNull2Bean(entity,page);
            	    Object id0 = entity.getId();
				    String hql0 = "from ConReqInstructionsDetailEntity where 1 = 1 AND pK_ID = ? ";
        	        List<ConReqInstructionsDetailEntity> conReqInstructionsDetailEntityList = systemService.findHql(hql0,id0);
            		page.setConReqInstructionsDetailList(conReqInstructionsDetailEntityList);
            		pageList.add(page);
            	}catch(Exception e){
            		logger.info(e.getMessage());
            	}
            }
        }
        map.put(NormalExcelConstants.FILE_NAME,"请示单主表");
        map.put(NormalExcelConstants.CLASS,ConReqInstructionsMainPage.class);
        map.put(NormalExcelConstants.PARAMS,new ExportParams("请示单主表列表", "导出人:Jeecg",
            "导出信息"));
        map.put(NormalExcelConstants.DATA_LIST,pageList);
        return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}

    /**
	 * 通过excel导入数据
	 * @param request
	 * @param
	 * @return
	 */
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
				List<ConReqInstructionsMainPage> list =  ExcelImportUtil.importExcel(file.getInputStream(), ConReqInstructionsMainPage.class, params);
				ConReqInstructionsMainEntity entity1=null;
				for (ConReqInstructionsMainPage page : list) {
					entity1=new ConReqInstructionsMainEntity();
					MyBeanUtils.copyBeanNotNull2Bean(page,entity1);
		            conReqInstructionsMainService.addMain(entity1, page.getConReqInstructionsDetailList());
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
	/**
	* 导出excel 使模板
	*/
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(ModelMap map) {
		map.put(NormalExcelConstants.FILE_NAME,"请示单主表");
		map.put(NormalExcelConstants.CLASS,ConReqInstructionsMainPage.class);
		map.put(NormalExcelConstants.PARAMS,new ExportParams("请示单主表列表", "导出人:"+ ResourceUtil.getSessionUser().getRealName(),
		"导出信息"));
		map.put(NormalExcelConstants.DATA_LIST,new ArrayList());
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	* 导入功能跳转
	*
	* @return
	*/
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name", "conReqInstructionsMainController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}

 	
 	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="请示单主表列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<ConReqInstructionsMainPage>> list() {
		List<ConReqInstructionsMainEntity> list= conReqInstructionsMainService.getList(ConReqInstructionsMainEntity.class);
    	List<ConReqInstructionsMainPage> pageList=new ArrayList<ConReqInstructionsMainPage>();
        if(list!=null&&list.size()>0){
        	for(ConReqInstructionsMainEntity entity:list){
        		try{
        			ConReqInstructionsMainPage page=new ConReqInstructionsMainPage();
        		   MyBeanUtils.copyBeanNotNull2Bean(entity,page);
					Object id0 = entity.getId();
				     String hql0 = "from ConReqInstructionsDetailEntity where 1 = 1 AND pK_ID = ? ";
	    			List<ConReqInstructionsDetailEntity> conReqInstructionsDetailOldList = this.conReqInstructionsMainService.findHql(hql0,id0);
            		page.setConReqInstructionsDetailList(conReqInstructionsDetailOldList);
            		pageList.add(page);
            	}catch(Exception e){
            		logger.info(e.getMessage());
            	}
            }
        }
		return Result.success(pageList);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取请示单主表信息",notes="根据ID获取请示单主表信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		ConReqInstructionsMainEntity task = conReqInstructionsMainService.get(ConReqInstructionsMainEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取请示单主表信息为空");
		}
		ConReqInstructionsMainPage page = new ConReqInstructionsMainPage();
		try {
			MyBeanUtils.copyBeanNotNull2Bean(task, page);
				Object id0 = task.getId();
		    String hql0 = "from ConReqInstructionsDetailEntity where 1 = 1 AND pK_ID = ? ";
			List<ConReqInstructionsDetailEntity> conReqInstructionsDetailOldList = this.conReqInstructionsMainService.findHql(hql0,id0);
    		page.setConReqInstructionsDetailList(conReqInstructionsDetailOldList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Result.success(page);
	}
 	
 	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建请示单主表")
	public ResponseMessage<?> create(@ApiParam(name="请示单主表对象")@RequestBody ConReqInstructionsMainPage conReqInstructionsMainPage, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ConReqInstructionsMainPage>> failures = validator.validate(conReqInstructionsMainPage);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		List<ConReqInstructionsDetailEntity> conReqInstructionsDetailList =  conReqInstructionsMainPage.getConReqInstructionsDetailList();
		
		ConReqInstructionsMainEntity conReqInstructionsMain = new ConReqInstructionsMainEntity();
		try{
			MyBeanUtils.copyBeanNotNull2Bean(conReqInstructionsMainPage,conReqInstructionsMain);
		}catch(Exception e){
            logger.info(e.getMessage());
            return Result.error("保存请示单主表失败");
        }
		conReqInstructionsMainService.addMain(conReqInstructionsMain, conReqInstructionsDetailList);

		return Result.success(conReqInstructionsMain);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新请示单主表",notes="更新请示单主表")
	public ResponseMessage<?> update(@RequestBody ConReqInstructionsMainPage conReqInstructionsMainPage) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ConReqInstructionsMainPage>> failures = validator.validate(conReqInstructionsMainPage);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		List<ConReqInstructionsDetailEntity> conReqInstructionsDetailList =  conReqInstructionsMainPage.getConReqInstructionsDetailList();
		
		ConReqInstructionsMainEntity conReqInstructionsMain = new ConReqInstructionsMainEntity();
		try{
			MyBeanUtils.copyBeanNotNull2Bean(conReqInstructionsMainPage,conReqInstructionsMain);
		}catch(Exception e){
            logger.info(e.getMessage());
            return Result.error("请示单主表更新失败");
        }
		conReqInstructionsMainService.updateMain(conReqInstructionsMain, conReqInstructionsDetailList);

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除请示单主表")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" + id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			ConReqInstructionsMainEntity conReqInstructionsMain = conReqInstructionsMainService.get(ConReqInstructionsMainEntity.class, id);
			conReqInstructionsMainService.delMain(conReqInstructionsMain);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("请示单主表删除失败");
		}

		return Result.success();
	}
	
	 /**
		 * 批量审核议标申请
		 * 
		 * @return
		 */
		 @RequestMapping(params = "doBatchCheck")
		@ResponseBody
		public AjaxJson doBatchCheck(String ids,HttpServletRequest request){
			AjaxJson j = new AjaxJson();
			String message = "";
			Integer w=Integer.valueOf(request.getParameter("flag"));
				if(w==0){
					
				message = "请示单提交成功";
						}
			if(w==1){
							
				message = "请示单撤回成功";
						}
						
			String state = request.getParameter("state");
			try{
				for(String id:ids.split(",")){
					ConReqInstructionsMainEntity conReqInstructionsMain = systemService.getEntity(ConReqInstructionsMainEntity.class,id); 
				
					
					if( "1".equals(conReqInstructionsMain.getRequExtent1()) && w==0){
						
						message = "已提交的请示单不能重复提交！";
						j.setMsg(message);
						return j;
					}
				
			
					if( !"1".equals(conReqInstructionsMain.getRequExtent1()) && w==1){
						
						message = "不是已提交的请示单不能撤回！";
						j.setMsg(message);
						return j;
					}
				
					if("1".equals(conReqInstructionsMain.getRequExtent2())&& w==1){
						message = "该单据已有审核通过的报价单不能撤回！";
						j.setMsg(message);
						return j;
					}
				
					conReqInstructionsMain.setRequExtent1 (state);
					conReqInstructionsMainService.saveOrUpdate(conReqInstructionsMain);
						
					systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
				}
			}catch(Exception e){
				e.printStackTrace();
				if(w==0){
					
					message = "请示单提交失败";
							}
				if(w==1){
								
					message = "请示单撤回失败";
							}
				throw new BusinessException(e.getMessage());
			}
			j.setMsg(message);
			return j;
		}
	
	
	
	
	
}
