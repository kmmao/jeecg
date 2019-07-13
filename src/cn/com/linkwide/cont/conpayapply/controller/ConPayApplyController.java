package cn.com.linkwide.cont.conpayapply.controller;
import java.io.IOException;
import java.text.DateFormat;
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
import cn.com.linkwide.cont.coninformation.entity.ConInformationEntity;
import cn.com.linkwide.cont.conpayapply.entity.ConPayApplyEntity;
import cn.com.linkwide.cont.conpayapply.page.ConPayApplyPage;
import cn.com.linkwide.cont.conpayapply.service.ConPayApplyServiceI;
import cn.com.linkwide.cont.conpayapplydetail.entity.ConPayApplyDetailEntity;
import cn.com.linkwide.cont.payplan.entity.PayPlanEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**   
 * @Title: Controller
 * @Description: 付款申请表
 * @author onlineGenerator
 * @date 2018-11-26 13:52:13
 * @version V1.0   
 *
 */
@Api(value="ConPayApply",description="付款申请表",tags="conPayApplyController")
@Controller
@RequestMapping("/conPayApplyController")
public class ConPayApplyController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ConPayApplyController.class);

	@Autowired
	private ConPayApplyServiceI conPayApplyService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;

	/**
	 * 付款申请表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("cn/com/linkwide/cont/conpayapply/conPayApplyList");
	}

	
	/**
	 * 参照采购订单生成采购订单页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "refPlan")
	public ModelAndView refPlanAdd(ConPayApplyEntity conPayApply, HttpServletRequest req) {
		
		return new ModelAndView("cn/com/linkwide/cont/conpayapply/arrilPuContFrame");
	}
	/**
	 * 订单主表
	 * 
	 * @return
	 */
	@RequestMapping(params = "arridoAddExportMain")
	public ModelAndView arridoAddExportMain(HttpServletRequest request) { 
		return new ModelAndView("cn/com/linkwide/cont/conpayapply/arrilPuContM");
	}
	
	/**
	 * 验收单明细表
	 * 
	 * @return
	 */
	@RequestMapping(params = "arridoAddExportDetail")
	public ModelAndView arridoAddExportDetail(HttpServletRequest request) {
		return new ModelAndView("cn/com/linkwide/cont/conpayapply/arrilPucontD");
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
	public void datagrid(ConPayApplyEntity conPayApply,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		/*try{
			//自定义追加查询条件
			
				StringBuffer sql = new StringBuffer();
				sql.append("  from  con_information a left join pay_plan b on a.id=b.con_id  left join con_pay_apply c on a.id=c.con_id  ");
			
					sql.append(" where a.con_type='04' and a.con_state='02' and b.pay_conditions='02' ");
					if(StringUtil.isOrcla()){
						sql.append("  and nvl(b.pay_money,0)<>nvl(b.apply_money,0)");
					}else{
						sql.append("  and ifnull(b.pay_money,0)<>ifnull(b.apply_money,0)");
					}
				List<Map<String,Object>> list = systemService.findForJdbc("select distinct a.id conId,a.con_number conNumber,a.con_no conNo,a.con_name conName,a.other_compy otherCompy   "+sql.toString(), dataGrid.getPage(), dataGrid.getRows());
				dataGrid.setResults(list);
				List<Map<String,Object>> listC = systemService.findForJdbc("select count(distinct(a.id)) as con "+sql.toString());
				dataGrid.setTotal(Integer.valueOf(listC.get(0).get("con").toString()));
			}catch (Exception e) {
				throw new BusinessException(e.getMessage());
			}
			TagUtil.datagrid(response, dataGrid);*/
		CriteriaQuery cq = new CriteriaQuery(ConPayApplyEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, conPayApply);
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.conPayApplyService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	
	/**
	 * 根据仓库和供应商查询物资
	 * 
	 * @author zxl 2018-7-20
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "contronList")
	@ResponseBody
	public void contronList(ConPayApplyEntity conPayApply,HttpServletRequest request,DataGrid dataGrid, HttpServletResponse response) {
		String q = request.getParameter("q") != null ? request.getParameter("q") : "";
		try {
			// 自定义追加查询条件 
			StringBuffer sql = new StringBuffer();
			sql.append(" from  con_information a left join pay_plan b on a.id=b.con_id ");
					//sql.append("		 left join l_ba_supplier l on a.other_compy=l.id  ");
			
				sql.append("  where a.con_type='04' and a.con_state='02' and b.pay_conditions in ('01','02')  ");
				sql.append(" and a.create_by='"+ResourceUtil.getSessionUser().getUserName()+"'");
				if(StringUtil.isOrcla()){
					sql.append("  and nvl(b.pay_money,0)<>nvl(b.apply_money,0)");
				}else{
					sql.append("  and ifnull(b.pay_money,0)<>ifnull(b.apply_money,0)");
				}
				if(request.getParameter("q") != null){
					sql.append(" and ( a.con_number like '"+q+"%' or a.con_no like '"+q+"%' or a.con_name like '%"+q+"%' ) ");
				}
			
			String filed ="select distinct a.id id,a.con_number conNumber,a.con_no conNo,a.con_name conName,a.other_compy otherCompy  ";
			List<Map<String,Object>> results = systemService.findForJdbc(filed+sql.toString(), dataGrid.getPage(), dataGrid.getRows());
			dataGrid.setResults(results);
			dataGrid.setTotal(Integer.valueOf(systemService.findForJdbc("select count(a.id) as cou "+sql.toString()).get(0).get("cou").toString()));
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 合同生成采购订单页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goContToOrderAdd")
	public ModelAndView goContToOrderAdd( HttpServletRequest req) {
		req.setAttribute("startDate", new Date(System.currentTimeMillis()));
		String tableids=req.getParameter("contDetails");
		String[] split = tableids.split(",");
		PayPlanEntity d = systemService.getEntity(PayPlanEntity.class, split[0]);
		ConInformationEntity cont= systemService.getEntity(ConInformationEntity.class,d.getConId());
		req.setAttribute("otherCompy",cont.getOtherCompy());
		req.setAttribute("conName",cont.getConName());
		req.setAttribute("conId",cont.getId());
		req.setAttribute("conNo",cont.getConNo());
		req.setAttribute("conNumber",cont.getConNumber());
		req.setAttribute("tableids",tableids);
		return new ModelAndView("cn/com/linkwide/cont/conpayapply/conPayApply-add");
	}
	
	
	
	/**
	 * 添加招标计划
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "goimportCarAdd")
	@ResponseBody
	public AjaxJson goimportCarAdd( HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		String tableids=request.getParameter("contDetails");
		message = "付款申请添加成功";
try{    String[] split = tableids.split(",");
		ConPayApplyEntity c=new ConPayApplyEntity();
		PayPlanEntity d = systemService.getEntity(PayPlanEntity.class, split[0]);
		ConInformationEntity cont= systemService.getEntity(ConInformationEntity.class,d.getConId());
		c.setApplyNo(generateBillCode());
		c.setOtherCompy(cont.getOtherCompy());
		c.setApplyDate(new Date(System.currentTimeMillis()));
		c.setConName(cont.getConName());
		c.setConNo(cont.getConNo());
		c.setConNumber(cont.getConNumber());
		c.setConId(cont.getId());
		conPayApplyService.save(c);
		double sumMoney=0.00;
		for (int i = 0; i < split.length; i++) {
		PayPlanEntity t = systemService.getEntity(PayPlanEntity.class, split[i]);
			ConPayApplyDetailEntity conPayApplyDetailEntity = new ConPayApplyDetailEntity();
			conPayApplyDetailEntity.setApplyDate(t.getApplyTime());
			if(StringUtil.isNotEmpty(t.getPayMoney())){
			conPayApplyDetailEntity.setPlanMoney(t.getPayMoney().toString());
			}
			conPayApplyDetailEntity.setPaymentType(t.getPayConditions());
			conPayApplyDetailEntity.setPlanId(t.getId());
			conPayApplyDetailEntity.setFundsSource(t.getMoneySource());
			conPayApplyDetailEntity.setMianId(c.getId());
			double lt=(t.getPayMoney()==null?0.00:t.getPayMoney())-(t.getApplyMoney()==null?0.00:t.getApplyMoney());
			conPayApplyDetailEntity.setApplyMoney(String.valueOf(lt));
			sumMoney=sumMoney+t.getPayMoney();
			systemService.save(conPayApplyDetailEntity); 
			String SQL="";
			if(StringUtil.isOrcla()){
			 SQL=" update pay_plan set apply_money=nvl(apply_money,0)+"+Double.parseDouble(conPayApplyDetailEntity.getApplyMoney())+" where id='"+t.getId()+"'";
			}else{
				 SQL=" update pay_plan set apply_money=ifnull(apply_money,0)+"+Double.parseDouble(conPayApplyDetailEntity.getApplyMoney())+" where id='"+t.getId()+"'";
			}
			systemService.updateBySqlString(SQL);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
	
		}
		c.setSumMoney(String.valueOf(sumMoney));
		systemService.updateEntitie(c);
         }catch(Exception e){
			e.printStackTrace();
			message = "招标计划添加失败";
			throw new BusinessException(e.getMessage());
		}
		
	
		j.setMsg(message);
		return j;
	}
	
	
	
	
	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "acceDetailExporDatagrid")
	public void acceDetailExporDatagrid(ConPayApplyEntity conPayApply,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		//===================================================================================
		//获取参数 
		String acceId = request.getParameter("acceId");
		//===================================================================================
		 
	    try{
	    	 StringBuffer sql = new StringBuffer();
	    	 if(StringUtil.isOrcla()){
	    	 sql.append(" select id id,con_id conId,pay_conditions paymentType,nvl(pay_money,0)-nvl(apply_money,0) applyMoney,pay_money planMoney,money_source fundsSource,pay_date applyDate from pay_plan ");
			 sql.append(" where 1=1 and pay_conditions in('01','02') and nvl(pay_money,0)<>nvl(apply_money,0)  ");
	    	 sql.append("  and  con_id in ('"+acceId.replace(",", "','")+"') ");
	    	 }else{
	    		 sql.append(" select id id,con_id conId,pay_conditions paymentType,ifnull(pay_money,0)-ifnull(apply_money,0) applyMoney,pay_money planMoney,money_source fundsSource,pay_date applyDate from pay_plan ");
				 sql.append(" where 1=1 and pay_conditions  in('01','02') and ifnull(pay_money,0)<>ifnull(apply_money,0)  ");
		    	 sql.append("  and  con_id in ('"+acceId.replace(",", "','")+"') ");
	    	 }
	    	 List<Map<String,Object>> conPayApplyDetailEntityList = systemService.findForJdbcParam(sql.toString(), dataGrid.getPage(), dataGrid.getRows());
	    	 String contSql="";
	    	 if(StringUtil.isOrcla()){
	    		  contSql =" select count(id) from pay_plan where pay_conditions in('01','02') and nvl(pay_money,0)<>nvl(apply_money,0) and con_id in ('"+acceId.replace(",", "','")+"')  "; 
	    	 }else{
	    	  contSql =" select count(id) from pay_plan where pay_conditions in('01','02') and ifnull(pay_money,0)<>ifnull(apply_money,0) and con_id in ('"+acceId.replace(",", "','")+"')  ";
	    	 }
	    	 Integer cont = Integer.valueOf(systemService.getCountForJdbc(contSql).toString());
	    	 dataGrid.setTotal(cont);
	    	dataGrid.setResults(conPayApplyDetailEntityList);
	    }catch(Exception e){
	    	
	    }
		TagUtil.datagrid(response, dataGrid); 
	}
	
	
	

	/**
	 * 删除付款申请表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(ConPayApplyEntity conPayApply, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		conPayApply = systemService.getEntity(ConPayApplyEntity.class, conPayApply.getId());
		String message = "付款申请表删除成功";
		try{
			conPayApplyService.delMain(conPayApply);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "付款申请表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 批量删除付款申请表
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		String message = "付款申请表删除成功";
		try{
			for(String id:ids.split(",")){
				ConPayApplyEntity conPayApply = systemService.getEntity(ConPayApplyEntity.class,
				id
				);
				conPayApplyService.delMain(conPayApply);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "付款申请表删除失败";
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
			TSFunction function = this.conPayApplyService.findUniqueByProperty(TSFunction.class, "functionUrl", "conPayApplyController.do?list");
			if(function==null){
				throw new BusinessException("未设置付款申请的单据类型");
			}
			billcode=BillCodeUtil.productBillCode(function.getBillType());
			return billcode;
		}

	/**
	 * 添加付款申请表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(ConPayApplyEntity conPayApply,ConPayApplyPage conPayApplyPage, HttpServletRequest request) {
		List<ConPayApplyDetailEntity> conPayApplyDetailList =  conPayApplyPage.getConPayApplyDetailList();
		AjaxJson j = new AjaxJson();
		String message = "添加成功";
		try{
			conPayApply.setApplyNo(generateBillCode());
			conPayApplyService.addMain(conPayApply, conPayApplyDetailList);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "付款申请表添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	/**
	 * 更新付款申请表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(ConPayApplyEntity conPayApply,ConPayApplyPage conPayApplyPage, HttpServletRequest request) {
		List<ConPayApplyDetailEntity> conPayApplyDetailList =  conPayApplyPage.getConPayApplyDetailList();
		AjaxJson j = new AjaxJson();
		String message = "更新成功";
		try{
			conPayApplyService.updateMain(conPayApply, conPayApplyDetailList);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "更新付款申请表失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	
	

	 /**
	 * 批量提交预算申请
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
				
			message = "付款申请提交成功";
					}
		if(w==1){
						
			message = "付款申请撤回成功";
					}
		
					
		String state = request.getParameter("state");
		try{
			for(String id:ids.split(",")){
				ConPayApplyEntity payPlan = systemService.getEntity(ConPayApplyEntity.class,id); 
				
			
				
				if(payPlan.getIsDesend()== "1" && w==0 ){
					
					message = "已提交的申请不能重复提交！";
					j.setMsg(message);
					return j;
				}
					if((payPlan.getIsDesend() == "0"|| payPlan.getIsDesend()==null) && w==1){
				
					message = "不是提交状态的单据不能撤回！";
					j.setMsg(message);
					return j;
				}
			
					payPlan.setIsDesend(state);		
					conPayApplyService.saveOrUpdate(payPlan);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			if(w==0){
				
				message = "付款申请提交失败";
						}
			if(w==1){
							
				message = "付款申请撤回失败";
						}
		
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 付款申请表新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(ConPayApplyEntity conPayApply, HttpServletRequest req) {
		req.setAttribute("startDate", new Date(System.currentTimeMillis()));
		if (StringUtil.isNotEmpty(conPayApply.getId())) {
			conPayApply = conPayApplyService.getEntity(ConPayApplyEntity.class, conPayApply.getId());
			req.setAttribute("conPayApplyPage", conPayApply);
		}
		return new ModelAndView("cn/com/linkwide/cont/conpayapply/conPayApply-add");
	}
	
	/**
	 * 付款申请表编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(ConPayApplyEntity conPayApply, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(conPayApply.getId())) {
			conPayApply = conPayApplyService.getEntity(ConPayApplyEntity.class, conPayApply.getId());
			req.setAttribute("conPayApplyPage", conPayApply);
		}
		return new ModelAndView("cn/com/linkwide/cont/conpayapply/conPayApply-update");
	}
	
	
	/**
	 * 加载明细列表[付款申请明细]
	 * 
	 * @return
	 */
	@RequestMapping(params = "conPayApplyDetailList")
	public ModelAndView conPayApplyDetailList(ConPayApplyEntity conPayApply, HttpServletRequest req) {
	
		//===================================================================================
		//获取参数
		Object id0 = conPayApply.getId();
		//===================================================================================
		//查询-付款申请明细
	    String hql0 = "from ConPayApplyDetailEntity where 1 = 1 AND mIAN_ID = ? ";
	    try{
	    	List<ConPayApplyDetailEntity> conPayApplyDetailEntityList = systemService.findHql(hql0,id0);
	    	List<ConPayApplyDetailEntity>DetailEntityList= new ArrayList<ConPayApplyDetailEntity>();
	    	if(conPayApplyDetailEntityList!= null && conPayApplyDetailEntityList.size()>0){
	    		for (ConPayApplyDetailEntity conPayApplyDetailEntity : conPayApplyDetailEntityList) {
	    			PayPlanEntity t = systemService.get(PayPlanEntity.class, conPayApplyDetailEntity.getPlanId());
	    			double  lt=(t.getPayMoney()==null?0.00:t.getPayMoney())-(t.getApplyMoney()==null?0.00:t.getApplyMoney());
	    			conPayApplyDetailEntity.setExtendone(String.valueOf(lt));
	    			DetailEntityList.add(conPayApplyDetailEntity);
	    		}
	    	}
			req.setAttribute("conPayApplyDetailList", DetailEntityList);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return new ModelAndView("cn/com/linkwide/cont/conpayapplydetail/conPayApplyDetailList");
	}
	
	
	/**
	 * 加载明细列表[合同付款计划]
	 * 
	 * @return
	 */
	@RequestMapping(params = "payPlanList")
	public ModelAndView payPlanList( HttpServletRequest req) {
	
		//===================================================================================
		//获取参数
		String tableids=req.getParameter("id");
		String id=req.getParameter("conId");
		String sql="";
		if(StringUtil.isOrcla()){
			sql="select id planId,pay_conditions paymentType,nvl(pay_money,0)-nvl(apply_money,0) applyMoney,pay_money planMoney,money_source fundsSource,pay_date applyDate from pay_plan";
			sql+=" where 1=1 and pay_conditions in('01','02') and nvl(pay_money,0)<>nvl(apply_money,0) and id in ('"+tableids.replace(",", "','")+"')"; 
		}else{
			sql="select id planId,pay_conditions paymentType,ifnull(pay_money,0)-ifnull(apply_money,0) applyMoney,pay_money planMoney,money_source fundsSource,pay_date applyDate from pay_plan";
			sql+=" where 1=1 and pay_conditions in ('01','02') and ifnull(pay_money,0)<>ifnull(apply_money,0)  and id in ('"+tableids.replace(",", "','")+"')";
		}
		
		//===================================================================================
		//查询-合同付款计划
		    try{
		    	DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		    	List<ConPayApplyDetailEntity> conPayApplyDetailEntityList = new ArrayList<>();
		    	List<Map<String,Object>> listMap=systemService.findForJdbc(sql, null);
		    	if(listMap!= null && listMap.size()>0){
		    		for (Map<String, Object> map : listMap) {
		    			ConPayApplyDetailEntity entity= new ConPayApplyDetailEntity();
		    			if(StringUtil.isNotEmpty(map.get("applyDate"))){
		    			entity.setApplyDate(format1.parse(map.get("applyDate").toString()));
		    			}
		    			if(StringUtil.isNotEmpty(map.get("applyDate"))){
		    			entity.setApplyMoney(map.get("applyMoney").toString());
		    			entity.setExtendone(map.get("applyMoney").toString());
		    			}
		    			if(StringUtil.isNotEmpty(map.get("planId"))){
		    				entity.setPlanId(map.get("planId").toString());
		    			}
		    			if(StringUtil.isNotEmpty(map.get("paymentType"))){
		    				entity.setPaymentType(map.get("paymentType").toString());
	    				}
		    			if(StringUtil.isNotEmpty(map.get("fundsSource"))){
		    				entity.setFundsSource(map.get("fundsSource").toString());
	    				}
		    			if(StringUtil.isNotEmpty(map.get("planMoney"))){
		    				entity.setPlanMoney(map.get("planMoney").toString());
	    				}
		    			
		    			
		    			conPayApplyDetailEntityList.add(entity);
					}
		    	}
				req.setAttribute("conPayApplyDetailList", conPayApplyDetailEntityList);
			}catch(Exception e){
				logger.info(e.getMessage());
			}
		return new ModelAndView("cn/com/linkwide/cont/conpayapplydetail/conPayApplyDetailList");
	}

    /**
    * 导出excel
    *
    * @param request
    * @param response
    */
    @RequestMapping(params = "exportXls")
    public String exportXls(ConPayApplyEntity conPayApply,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid,ModelMap map) {
    	CriteriaQuery cq = new CriteriaQuery(ConPayApplyEntity.class, dataGrid);
    	//查询条件组装器
    	org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, conPayApply);
    	try{
    	//自定义追加查询条件
    	}catch (Exception e) {
    		throw new BusinessException(e.getMessage());
    	}
    	cq.add();
    	List<ConPayApplyEntity> list=this.conPayApplyService.getListByCriteriaQuery(cq, false);
    	List<ConPayApplyPage> pageList=new ArrayList<ConPayApplyPage>();
        if(list!=null&&list.size()>0){
        	for(ConPayApplyEntity entity:list){
        		try{
        		ConPayApplyPage page=new ConPayApplyPage();
        		   MyBeanUtils.copyBeanNotNull2Bean(entity,page);
            	    Object id0 = entity.getId();
				    String hql0 = "from ConPayApplyDetailEntity where 1 = 1 AND mIAN_ID = ? ";
        	        List<ConPayApplyDetailEntity> conPayApplyDetailEntityList = systemService.findHql(hql0,id0);
            		page.setConPayApplyDetailList(conPayApplyDetailEntityList);
            		pageList.add(page);
            	}catch(Exception e){
            		logger.info(e.getMessage());
            	}
            }
        }
        map.put(NormalExcelConstants.FILE_NAME,"付款申请表");
        map.put(NormalExcelConstants.CLASS,ConPayApplyPage.class);
        map.put(NormalExcelConstants.PARAMS,new ExportParams("付款申请表列表", "导出人:Jeecg",
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
				List<ConPayApplyPage> list =  ExcelImportUtil.importExcel(file.getInputStream(), ConPayApplyPage.class, params);
				ConPayApplyEntity entity1=null;
				for (ConPayApplyPage page : list) {
					entity1=new ConPayApplyEntity();
					MyBeanUtils.copyBeanNotNull2Bean(page,entity1);
		            conPayApplyService.addMain(entity1, page.getConPayApplyDetailList());
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
		map.put(NormalExcelConstants.FILE_NAME,"付款申请表");
		map.put(NormalExcelConstants.CLASS,ConPayApplyPage.class);
		map.put(NormalExcelConstants.PARAMS,new ExportParams("付款申请表列表", "导出人:"+ ResourceUtil.getSessionUser().getRealName(),
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
		req.setAttribute("controller_name", "conPayApplyController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}

 	
 	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="付款申请表列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<ConPayApplyPage>> list() {
		List<ConPayApplyEntity> list= conPayApplyService.getList(ConPayApplyEntity.class);
    	List<ConPayApplyPage> pageList=new ArrayList<ConPayApplyPage>();
        if(list!=null&&list.size()>0){
        	for(ConPayApplyEntity entity:list){
        		try{
        			ConPayApplyPage page=new ConPayApplyPage();
        		   MyBeanUtils.copyBeanNotNull2Bean(entity,page);
					Object id0 = entity.getId();
				     String hql0 = "from ConPayApplyDetailEntity where 1 = 1 AND mIAN_ID = ? ";
	    			List<ConPayApplyDetailEntity> conPayApplyDetailOldList = this.conPayApplyService.findHql(hql0,id0);
            		page.setConPayApplyDetailList(conPayApplyDetailOldList);
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
	@ApiOperation(value="根据ID获取付款申请表信息",notes="根据ID获取付款申请表信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		ConPayApplyEntity task = conPayApplyService.get(ConPayApplyEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取付款申请表信息为空");
		}
		ConPayApplyPage page = new ConPayApplyPage();
		try {
			MyBeanUtils.copyBeanNotNull2Bean(task, page);
				Object id0 = task.getId();
		    String hql0 = "from ConPayApplyDetailEntity where 1 = 1 AND mIAN_ID = ? ";
			List<ConPayApplyDetailEntity> conPayApplyDetailOldList = this.conPayApplyService.findHql(hql0,id0);
    		page.setConPayApplyDetailList(conPayApplyDetailOldList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Result.success(page);
	}
 	
 	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建付款申请表")
	public ResponseMessage<?> create(@ApiParam(name="付款申请表对象")@RequestBody ConPayApplyPage conPayApplyPage, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ConPayApplyPage>> failures = validator.validate(conPayApplyPage);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		List<ConPayApplyDetailEntity> conPayApplyDetailList =  conPayApplyPage.getConPayApplyDetailList();
		
		ConPayApplyEntity conPayApply = new ConPayApplyEntity();
		try{
			MyBeanUtils.copyBeanNotNull2Bean(conPayApplyPage,conPayApply);
		}catch(Exception e){
            logger.info(e.getMessage());
            return Result.error("保存付款申请表失败");
        }
		conPayApplyService.addMain(conPayApply, conPayApplyDetailList);

		return Result.success(conPayApply);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新付款申请表",notes="更新付款申请表")
	public ResponseMessage<?> update(@RequestBody ConPayApplyPage conPayApplyPage) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ConPayApplyPage>> failures = validator.validate(conPayApplyPage);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		List<ConPayApplyDetailEntity> conPayApplyDetailList =  conPayApplyPage.getConPayApplyDetailList();
		
		ConPayApplyEntity conPayApply = new ConPayApplyEntity();
		try{
			MyBeanUtils.copyBeanNotNull2Bean(conPayApplyPage,conPayApply);
		}catch(Exception e){
            logger.info(e.getMessage());
            return Result.error("付款申请表更新失败");
        }
		conPayApplyService.updateMain(conPayApply, conPayApplyDetailList);

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除付款申请表")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" + id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			ConPayApplyEntity conPayApply = conPayApplyService.get(ConPayApplyEntity.class, id);
			conPayApplyService.delMain(conPayApply);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("付款申请表删除失败");
		}

		return Result.success();
	}
}
