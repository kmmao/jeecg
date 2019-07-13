package cn.com.linkwide.cont.coninformation.controller;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
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
import org.jeecgframework.core.util.ContextHolderUtils;
import org.jeecgframework.core.util.DateUtils;
import org.jeecgframework.core.util.ExceptionUtil;
import org.jeecgframework.core.util.FtpUtil;
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
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.pojo.base.TSFunction;
import org.jeecgframework.web.system.pojo.base.TSUser;
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
import com.buss.activiti.service.IWorkFlowQueryService;
import com.buss.activiti.service.IWorkFlowService;
import com.buss.activiti.util.BILL_STATE;

import cn.com.linkwide.ba.departmate.entity.LBaDepartMateEntity;
import cn.com.linkwide.ba.material.record.entity.LBaMaterialEntity;
import cn.com.linkwide.ba.supplier.entity.LBaSupplierEntity;
import cn.com.linkwide.common.fileUpload.entity.FileDictEntity;
import cn.com.linkwide.common.tools.BillCodeUtil;
import cn.com.linkwide.cont.condemage.entity.ConDemageEntity;
import cn.com.linkwide.cont.coninformation.entity.ConInformationEntity;
import cn.com.linkwide.cont.coninformation.page.ConInformationPage;
import cn.com.linkwide.cont.coninformation.service.ConInformationServiceI;
import cn.com.linkwide.cont.coninformationdetial.entity.ConMarkEntity;
import cn.com.linkwide.cont.conmemorabilia.entity.ConMemorabiliaEntity;
import cn.com.linkwide.cont.contbank.entity.ContBankEntity;
import cn.com.linkwide.cont.desgin.conruledeasgin.entity.ConruleDeasginEntity;
import cn.com.linkwide.cont.desgin.procolcont.entity.ProcolContEntity;
import cn.com.linkwide.cont.desgin.templateexport.entity.TemplateExportEntity;
import cn.com.linkwide.cont.negotiation.confinalconfirmbill.entity.ConFinalConfirmbillEntity;
import cn.com.linkwide.cont.payplan.entity.PayPlanEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**   
 * @Title: Controller
 * @Description: 合同信息
 * @author onlineGenerator
 * @date 2018-08-23 14:40:50
 * @version V1.0   
 *
 */
@Api(value="ConInformation",description="合同信息",tags="conInformationController")
@Controller
@RequestMapping("/conInformationController")
public class ConInformationController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ConInformationController.class);

	@Autowired
	private ConInformationServiceI conInformationService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	@Autowired
	private IWorkFlowService workFlowService;
	@Autowired 
	private IWorkFlowQueryService workFlowQueryService;

	/**
	 * 合同信息列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		String yjbusIds = request.getParameter("yjbusIds");
		request.setAttribute("yjbusIds", yjbusIds);
		return new ModelAndView("cn/com/linkwide/cont/coninformation/conInformationList");
	}
	
	
	/**
	 * 新增信息合同列表跳转（参照后勤合同开发）
	 * 李丽
	 * 2019-4-9
	 * @return
	 */
	@RequestMapping(params = "messageList")
	public ModelAndView messageList(HttpServletRequest request) {
		String yjbusIds = request.getParameter("yjbusIds");
		request.setAttribute("yjbusIds", yjbusIds);
		return new ModelAndView("cn/com/linkwide/cont/coninformation/conInformationMessageList");
	}
	
	/**
	 * 新增信息合同维护跳转（参照后勤合同开发）
	 * 李丽
	 * 2019-4-9
	 * @return
	 */
	@RequestMapping(params = "messageAdd")
	public ModelAndView messageAdd(ConInformationEntity conInformation, HttpServletRequest req) {
		String dept_code=	ResourceUtil.getSessionUser().getDepartid();
		req.setAttribute("deptId", dept_code);
		req.setAttribute("signedDate",new Date(System.currentTimeMillis()));
		req.setAttribute("startDate", new Date(System.currentTimeMillis()));
		req.setAttribute("flag", req.getParameter("flag"));
	/*	String uid = UUID.randomUUID().toString();
		req.setAttribute("fj", uid);*/
		
		if (StringUtil.isNotEmpty(conInformation.getId())) {
			conInformation = conInformationService.getEntity(ConInformationEntity.class, conInformation.getId());
			req.setAttribute("conInformationPage", conInformation);
			
		}
		return new ModelAndView("cn/com/linkwide/cont/coninformation/conInformationMessageAdd");
	}
	
	/**
	 * 黎莉莉
	 * 信息合同信息列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "listHq")
	public ModelAndView listHq(HttpServletRequest request) {
		String yjbusIds = request.getParameter("yjbusIds");
		request.setAttribute("yjbusIds", yjbusIds);
		return new ModelAndView("cn/com/linkwide/cont/coninformation/conInformationHqList");
	}
	
	
	
	/**
	 *基建合同信息列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "listJj")
	public ModelAndView listJj(HttpServletRequest request) {
		String yjbusIds = request.getParameter("yjbusIds");
		request.setAttribute("yjbusIds", yjbusIds);
		return new ModelAndView("cn/com/linkwide/cont/coninformation/conInformationJjList");
	}
	/**
	 *安保合同信息列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "listAb")
	public ModelAndView listAb(HttpServletRequest request) {
		String yjbusIds = request.getParameter("yjbusIds");
		request.setAttribute("yjbusIds", yjbusIds);
		return new ModelAndView("cn/com/linkwide/cont/coninformation/conInformationAbList");
	}
	/**
	 *消防合同信息列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "listXf")
	public ModelAndView listXf(HttpServletRequest request) {
		String yjbusIds = request.getParameter("yjbusIds");
		request.setAttribute("yjbusIds", yjbusIds);
		return new ModelAndView("cn/com/linkwide/cont/coninformation/conInformationXfList");
	}
	
	
	
	/**
	 * 合同变更列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "listbg")
	public ModelAndView listbg(HttpServletRequest request) {
		return new ModelAndView("cn/com/linkwide/cont/coninformation/coninformationexchange");
	}
	
	
	
	/**
	 * 合同界面报表
	 * 
	 * @return
	 */
	@RequestMapping(params = "importExec")
	public ModelAndView importExec(HttpServletRequest request) {
		return new ModelAndView("cn/com/linkwide/cont/coninformation/conInformationExecImport");
	}
	
	
	/**
	 * 资产合同信息列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "assetList")
	public ModelAndView assetList(HttpServletRequest request) {
		String yjbusIds = request.getParameter("yjbusIds");
		request.setAttribute("yjbusIds", yjbusIds);
		return new ModelAndView("cn/com/linkwide/cont/coninformation/conInformationAsset/conInformationAssetList");
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
	public void datagrid(ConInformationEntity conInformation,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ConInformationEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, conInformation);
		try{
		//自定义追加查询条件
		String query_signedDate_begin = request.getParameter("signedDate_begin");
		String query_signedDate_end = request.getParameter("signedDate_end");
		String con_type=request.getParameter("con_type");
		String yjbusIds=request.getParameter("yjbusIds");
		String isZcsb=request.getParameter("isZcsb");
		if(StringUtil.isNotEmpty(query_signedDate_begin)){
			cq.ge("signedDate", new SimpleDateFormat("yyyy-MM-dd").parse(query_signedDate_begin));
		}
		if(StringUtil.isNotEmpty(query_signedDate_end)){
			cq.le("signedDate", new SimpleDateFormat("yyyy-MM-dd").parse(query_signedDate_end));
		}
		if(StringUtil.isNotEmpty(con_type)){
			cq.eq("conType", con_type);
		}
		if(StringUtil.isNotEmpty(yjbusIds)){
			cq.in("id", yjbusIds.split(","));
		}
		if(StringUtil.isNotEmpty(isZcsb)){
			cq.eq("isZcsb", isZcsb);
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("confermState", "asc");
		cq.setOrder(map);
				
		Map<String,Object> map1 = new HashMap<String,Object>();
		map1.put("conNumber", "desc");
		cq.setOrder(map1);
/*		cq.addOrder("conNumber", SortDirection.desc);
		cq.addOrder("confermState", SortDirection.desc);*/
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		
		try {
			this.conInformationService.getDataGridReturn(cq, true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<ConInformationEntity> cont=dataGrid.getResults();
		for(ConInformationEntity req :cont ){
			if(("".equals(req.getConNumnerZ())||null==req.getConNumnerZ()||"0".equals(req.getConNumnerZ()))&& !"1".equals(req.getIsBg())){
				req.setRenewId(req.getId());
			}
			
		}
		
		TagUtil.datagrid(response, dataGrid);
	}
	
	
	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "datagridbg")
	public void datagridbg(ConInformationEntity conInformation,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ConInformationEntity.class, dataGrid);
		//查询条件组装器
		//org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, conInformation);
		try{
		//自定义追加查询条件
			String query_signedDate_begin = request.getParameter("stateDate4_begin");
			String query_signedDate_end = request.getParameter("stateDate4_end");
			if(StringUtil.isNotEmpty(query_signedDate_begin)){
				cq.ge("stateDate4", new SimpleDateFormat("yyyy-MM-dd").parse(query_signedDate_begin));
			}
			if(StringUtil.isNotEmpty(query_signedDate_end)){
				cq.le("stateDate4", new SimpleDateFormat("yyyy-MM-dd").parse(query_signedDate_end));
			}
		String con_type=request.getParameter("con_type");
		String isExchange=request.getParameter("isExchange");
		if(StringUtil.isNotEmpty(con_type)){
			cq.eq("conType", con_type);
		}
		String conNo=request.getParameter("conNo");
		String conName=request.getParameter("conName");
		if(StringUtil.isNotEmpty(conNo)){
			cq.add(Restrictions.like("conNo", conNo,MatchMode.ANYWHERE));
		}
		if(StringUtil.isNotEmpty(conName)){
			cq.add(Restrictions.like("conName", conName,MatchMode.ANYWHERE));
		}
	/*	if(StringUtil.isNotEmpty(con_type)&& StringUtil.isNotEmpty(flag)){
			String stringArray[]={con_type,flag};
			Conjunction criteria =  Restrictions.conjunction();
			criteria.add(Restrictions.in("conType",con_type));
			cq.add(criteria);
		}*/
		if(StringUtil.isNotEmpty(isExchange)){
			cq.eq("isBg", isExchange);
		}
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		
		this.conInformationService.getDataGridReturn(cq, true);
		List<ConInformationEntity> cont=dataGrid.getResults();
		for(ConInformationEntity req :cont ){
			if(("".equals(req.getConNumnerZ())||null==req.getConNumnerZ()||"0".equals(req.getConNumnerZ()))&& !"1".equals(req.getIsBg())){
				req.setRenewId(req.getId());
			}
			
		}
		
		TagUtil.datagrid(response, dataGrid);
	}
	

	/**
	 * 删除合同信息
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(ConInformationEntity conInformation, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		conInformation = systemService.getEntity(ConInformationEntity.class, conInformation.getId());
		String message = "合同信息删除成功";
		try{
			conInformationService.delMain(conInformation);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "合同信息删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 批量删除合同信息
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		String message = "合同信息删除成功";
		try{
			for(String id:ids.split(",")){
				ConInformationEntity conInformation = systemService.getEntity(ConInformationEntity.class,
				id
				);
				conInformationService.delMain(conInformation);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "合同信息删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	 
	 
		/*@RequestMapping(params="cleanRedundantData")
		@ResponseBody
		public AjaxJson cleanRedundantData(){
			AjaxJson j=new AjaxJson();
			try {
				String hql="select distinct fj from ConInformationEntity";
				List<String> identList = systemService.findHql(hql);
				if(identList!=null&&identList.size()>0){
					String[] idents = identList.toArray(new String [0]);
					cleanFjData(idents);
				
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return j;
		}
		*//**
		 * 
		 * 清除附件上传的多余数据
		 * *//*
		public void cleanFjData(String[] idents){
			String carHql="select * from file_dict  where fj not in ("+getsqlString(idents)+")";
			List<Object> objList = systemService.findListbySql(carHql);
			if(objList!=null&&objList.size()>0){
				List<ConInformationEntity> conInformationList=new ArrayList<ConInformationEntity>();
				for (Object obj : objList) {
					Object [] objArr=(Object[]) obj;
					ConInformationEntity conInformation=new ConInformationEntity();
					conInformation.setId(objArr[0].toString());
					conInformationList.add(conInformation);
				}
				systemService.deleteAllEntitie(conInformationList);
			}
		}
		*//**
		 * 将String[]转化成sql中'','',''形式
		 *//*
		public String getsqlString(String[] keys){
			String sqlkey = "";
			for(int i = 0; i < keys.length; i++){
				if(i == 0){
					sqlkey = "'" + keys[i] + "'";
				}else{
					sqlkey = sqlkey + "," + "'" + keys[i] + "'" ;
				}
				
			}
			return sqlkey;
		}*/
		/**
		 * 生成单据号
		 * @return
		 *2017年12月4日
		 *@author 
		 */
		public String generateBillCode(){
			String	billcode="";
		//	String hql=" from ";
			TSFunction function = this.conInformationService.findUniqueByProperty(TSFunction.class, "functionUrl", "conInformationController.do?list");
			if(function==null){
				throw new BusinessException("未设置合同信息的单据类型");
			}
			billcode=BillCodeUtil.productBillCode(function.getBillType());
			return billcode;
		}
		
		
		/**
		 * 合同违约跳转页面
		 * 
		 * @return
		 */
		@RequestMapping(params = "confirmNum")
		public ModelAndView confirmNum(ConInformationEntity conInformation, HttpServletRequest req) {
			if (StringUtil.isNotEmpty(conInformation.getId())) {
				conInformation = conInformationService.getEntity(ConInformationEntity.class, conInformation.getId());
				req.setAttribute("conInformationPage", conInformation);
				conInformation.setDefoutDate(new Date(System.currentTimeMillis()));
			}
			return new ModelAndView("cn/com/linkwide/cont/coninformation/conwyList");
		}
		
		/**
		 * 合同变更跳转页面
		 * 
		 * @return
		 */
		@RequestMapping(params = "confirmbgjl")
		public ModelAndView confirmbgjl(ConInformationEntity conInformation, HttpServletRequest req) {
			if (StringUtil.isNotEmpty(conInformation.getId())) {
				conInformation = conInformationService.getEntity(ConInformationEntity.class, conInformation.getId());
				req.setAttribute("conInformationPage", conInformation);
				String flag=req.getParameter("flag");
				String ids=req.getParameter("ids");
				req.setAttribute("flag",flag);
				req.setAttribute("ids", ids);
						
				//conInformation.setDefoutDate(new Date(System.currentTimeMillis()));
			}
			return new ModelAndView("cn/com/linkwide/cont/coninformation/conInformation-alteration");
		}
		
		/**
		 * 合同变更跳转页面
		 * 
		 * @return
		 */
		@RequestMapping(params = "confirmbg")
		public ModelAndView confirmbg(ConInformationEntity conInformation, HttpServletRequest req) {
			if (StringUtil.isNotEmpty(conInformation.getId())) {
				conInformation = conInformationService.getEntity(ConInformationEntity.class, conInformation.getId());
				String flag=req.getParameter("flag");
				String a=flag==null?"2":flag.toString();
				req.setAttribute("flag", a);
				req.setAttribute("conInformationPage", conInformation);
				//conInformation.setDefoutDate(new Date(System.currentTimeMillis()));
			}
			return new ModelAndView("cn/com/linkwide/cont/coninformation/conInformation-alteration");
		}
		/**
		 * 合同变更跳转左边页面
		 * 
		 * @return
		 */
		@RequestMapping(params = "confirmbg11")
		public ModelAndView confirmbg11(ConInformationEntity conInformation, HttpServletRequest req) {
			if (StringUtil.isNotEmpty(conInformation.getId())) {
				conInformation = conInformationService.getEntity(ConInformationEntity.class, conInformation.getId());
				if("".equals(conInformation.getConExec6())||"0".equals(conInformation.getConExec6())||null==conInformation.getConExec6()){
					req.setAttribute("conExec6","0");
				}else{
					req.setAttribute("conExec6",conInformation.getConExec6());
				}
				req.setAttribute("conInformationPage", conInformation);
				//conInformation.setDefoutDate(new Date(System.currentTimeMillis()));
			}
			return new ModelAndView("cn/com/linkwide/cont/coninformation/conInformation-alteration-examine");
		}
		/**
		 * 合同变更跳转右边页面
		 * 
		 * @return
		 */
		@RequestMapping(params = "confirmbg22")
		public ModelAndView confirmbg22(ConInformationEntity conInformation, HttpServletRequest req) {
			if (StringUtil.isNotEmpty(conInformation.getId())) {
				String flag=req.getParameter("flag");
				conInformation = conInformationService.getEntity(ConInformationEntity.class, conInformation.getId());
				if("1".equals(flag)){
					String a=conInformation.getConExec6();
					req.setAttribute("conExec6", a);
				}else{
				if("".equals(conInformation.getConExec6())||"0".equals(conInformation.getConExec6())||null==conInformation.getConExec6()){
					req.setAttribute("conExec6","1");
					//conInformation.setRenewId(conInformation.getId());
				
				}else{
					Integer a=Integer.parseInt(conInformation.getConExec6());
					String b =String.valueOf(a+1);
					req.setAttribute("conExec6", b);
				
				}
				}
				req.setAttribute("conInformationPage", conInformation);
				//conInformation.setDefoutDate(new Date(System.currentTimeMillis()));
			
			}
			return new ModelAndView("cn/com/linkwide/cont/coninformation/conInformation-alteration-update");
		}
		
		/**
		 * 合同违约
		 * 
		 * @return
		 */
		@RequestMapping(params = "doWeiYue")
		@ResponseBody
		public AjaxJson doWeiYue(ConInformationEntity conInformation, HttpServletRequest request) {
			String message="";
			AjaxJson j = new AjaxJson();
			try{
				
				ConInformationEntity conInfor = conInformationService.getEntity(ConInformationEntity.class, conInformation.getId());
				conInfor.setDefoutDate(conInformation.getDefoutDate());
				conInfor.setIsDefout("1");
				conInfor.setConState("06");
				conInfor.setDefoutCase(conInformation.getDefoutCase());
				message = "违约成功";
				MyBeanUtils.copyBean2Bean(conInformation, conInfor);
				conInformationService.updateEntitie(conInfor);
				
				//在索赔业务表中新增违约的合同
				ConDemageEntity conDemageEntity=new ConDemageEntity();
				conDemageEntity.setConId(conInformation.getId());
				systemService.save(conDemageEntity);
			//	systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
			}catch(Exception e){
				e.printStackTrace();
				message = "合同违约失败";
				throw new BusinessException(e.getMessage());
			}
			j.setMsg(message);
			return j;
		}
		
		
		
		
		@RequestMapping(params = "conNameList")
		@ResponseBody
		public void conNameList(ConruleDeasginEntity entity,HttpServletRequest request,DataGrid dataGrid, HttpServletResponse response) {
			String q = request.getParameter("q") != null ? request.getParameter("q") : "";
			CriteriaQuery cq = new CriteriaQuery(ConruleDeasginEntity.class, dataGrid);
			String defaultVal = request.getParameter("defaultVal") != null ? request.getParameter("defaultVal") : "";
			String bidCode=request.getParameter("bidCode");
			String flag=request.getParameter("flag");
			String assie=request.getParameter("assie");
			if(StringUtil.isNotEmpty(bidCode)){
				bidCode=(bidCode.replace("-",""))+"ZB";
			}
			// 查询条件组装器
			org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, entity);
			try {
				Disjunction disjunction = Restrictions.disjunction();
				disjunction.add(Restrictions.like("contName", q,MatchMode.ANYWHERE).ignoreCase());
				disjunction.add(Restrictions.like("spell", q,MatchMode.ANYWHERE).ignoreCase());
				cq.add(disjunction);
				if("1".equals(flag)){
					cq.eq("isHq", "1");
				}else if("0".equals(flag)){
					cq.eq("isHq", "0");
				}
				if("1".equals(assie)){
					cq.eq("isAssie","1");
				}else {
					cq.eq("isAssie","0");
				}
				cq.eq("isBid", "0");
				cq.add();
				this.systemService.getDataGridReturn(cq, true); 
				String[]  defArray = defaultVal.split(",");
				if(defArray.length>1 && StringUtil.isNotEmpty(defArray[1]) ){
					ConruleDeasginEntity d = systemService.findUniqueByProperty(ConruleDeasginEntity.class, defArray[0], defArray[1]);
					List<ConruleDeasginEntity> results = dataGrid.getResults();
					if(d !=null){
						for(ConruleDeasginEntity dept : results){
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
			List<ConruleDeasginEntity> result = dataGrid.getResults();
			List<ConruleDeasginEntity> resultr=new ArrayList<ConruleDeasginEntity>();
			if(!"1".equals(flag)){
			for (ConruleDeasginEntity conruleDeasginEntity : result) {
				String spell=conruleDeasginEntity.getSpell();
				String code=getApplyCode(spell);
				String fCode=spell+"-"+(bidCode==null?"":bidCode)+code;
				conruleDeasginEntity.setDesain(fCode);
			
				}
			}
			TagUtil.datagrid(response, dataGrid);
			
		}
		/**
		 * 自动生成单号   当前日期+0000
		 * @return
		 */
		
		public  String getApplyCode(String flag){
		
		
			String code;
			List<?> list=null;
			NumberFormat f=new DecimalFormat("0000");
			SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
			SimpleDateFormat sb = new SimpleDateFormat("yyyy");
			String date = sdf.format(new Date());
			String min=date+"-"+"0001";
			String year=sb.format(new Date());
			String sql="";
			if(	StringUtil.isOrcla()){
		     sql= "select con_no  from con_information where con_no like  '"+flag+"%' and to_char(create_date,'yyyy')='"+year+"' order by substr(con_no,-4,4)  asc";//sql,date+"%"
			}else{
				 sql= "select con_no  from con_information where con_no like  '"+flag+"%' and date_format(create_date,'%Y')='"+year+"'  order by right(con_no, 4) asc";
			}
			
		    try {
		    	list= this.systemService.findListbySql(sql);
			} catch (Exception e) {
				list = new ArrayList<>();
			}
		    if (list.size()==0) {
				code=min;
			}else{  
				    String maxCode= list.get(list.size()-1).toString();
		            String number=  maxCode.substring(maxCode.length()-4,maxCode.length());
		            int i=Integer.parseInt(number);
		            i++;
		            String s = String.valueOf(i);
		            code= date+"-"+f.format(i);
			}
		  
			return code;
		}
		
	 
		 /**
		 * 批量操作合同信息
		 * 
		 * @return
		 */
		 @RequestMapping(params = "doBatchCheck")
		@ResponseBody
		public AjaxJson doBatchCheck(String ids,HttpServletRequest request){
			AjaxJson j = new AjaxJson();
			String message = "";
			Integer w=Integer.valueOf(request.getParameter("w"));
			String flag=request.getParameter("flag");
				if(w==1){
					
				message = "合同审核成功！";
						}
				if(w==2){
					message="合同弃审成功！";
				}
				if(w==3){
					message="合同履行成功！";
				}
				if(w==4){
					message="合同终止成功！";
				}
				if(w==5){
					message="合同作废成功！";
				}
				if(w==6){
					message="合同解除成功！";
				}
				if(w==7){
					message="合同取消终止成功！";
				}
				if(w==8){
					message="合同取消解除成功！";
				}
				if(w==9){
					message="合同取消作废成功！";
				}
			String  state =request.getParameter("state");
			try{
				for(String id:ids.split(",")){
					ConInformationEntity conInformation = systemService.getEntity(ConInformationEntity.class,id); 
					if(!"1".equals(flag)){
					if("1".equals(conInformation.getIsDefout())){
						message="已违约的合同不能做其他操作！";
						if(w==4){
							message="违约合同请到索赔界面做终止操作！";
						}
						if(w==6){
							message="违约合同请到索赔界面做解除操作！";
						}
						if(w==7){
							message="违约合同请到索赔界面做取消终止操作！";
							if(!"03".equals(conInformation.getConState())){
								message="不是终止状态的合同不能做取消终止操作！";
							}
							
						}
						if(w==8){
							message="违约合同请到索赔界面做取消解除操作！";
							if(!"07".equals(conInformation.getConState())){
								message="不是解除状态的合同不能做取消终止操作！";
							}
						}
						
						j.setMsg(message);
						return j;
					}
					}
					if("03".equals(conInformation.getConState())&& w!=7){
						message="已终止的合同不能做其他操作！";
						j.setMsg(message);
						return j;
					}
					if("04".equals(conInformation.getConState())&& w!=9){
						message="已作废的合同不能做其他操作！";
						j.setMsg(message);
						return j;
					}
					if("07".equals(conInformation.getConState())&& w!=8){
						message="已解除的合同不能做其他操作！";
						j.setMsg(message);
						return j;
					}
			/*		if("已审核".equals(conInformation.getBpmStatus()) && w==1){
						
						message = "已审核的合同不能复审！";
						j.setMsg(message);
						return j;
					}
					if(!"已审核".equals(conInformation.getBpmStatus()) && w==2){
						
						message = "不是已审核的合同不能弃审！";
						j.setMsg(message);
						return j;
					}*/
					if(!"10".equals(conInformation.getConState()) && w==2){
						
						message = "不是起草状态的合同不能弃审！";
						j.setMsg(message);
						return j;
					}
					if(!"已审核".equals(conInformation.getConfermState()) && w==3){
						
						message = "不是审核的合同不能履行！";
						j.setMsg(message);
						return j;
					}
				/*	if(w==4&&!"02".equals( conInformation.getConState())){
						message="不是履行状态的合同不能做合同终止操作！";
						j.setMsg(message);
						return j;
					}*/
				if (!"06".equals(conInformation.getConState())){
					if(w==6 && !"02".equals(conInformation.getConState())){
						message="不是履行状态的合同不能做解除操作！";
						j.setMsg(message);
						return j;
					}
				}
				
				if(!"03".equals(conInformation.getConState())&& w==7){
					message="不是终止状态的合同不能做取消终止操作！";
					j.setMsg(message);
					return j;
				}
			
				if(!"04".equals(conInformation.getConState())&& w==9){
					message="不是作废状态的合同不能做取消作废操作！";
					j.setMsg(message);
					return j;
				}
				if(!"07".equals(conInformation.getConState())&& w==8){
					message="不是解除状态的合同不能做取消解除操作！";
					j.setMsg(message);
					return j;
				}
				if("06".equals(conInformation.getConState())&& w==7 && state=="02"){
					message="违约状态下终止的合同请到索赔界面取消终止！";
					j.setMsg(message);
					return j;
				}
				if("06".equals(conInformation.getConState())&& w==8 && state=="02"){
					message="违约状态下解除的合同请到索赔界面取消解除！";
					j.setMsg(message);
					return j;
				}
				if(w==7){
					conInformation.setConState(conInformation.getQxZz());
					conInformation.setStateDate5(null);
				}
				if(w==8){
					conInformation.setConState(conInformation.getQxJc());
					conInformation.setStateDate3(null);
				}
				if(w==9){
					conInformation.setConState(conInformation.getConExec9());
					conInformation.setStateDate6(null);
				}
				
					if(w==1){
						conInformation.setConfermState(state);
						conInformation.setConfermDate(new Date(System.currentTimeMillis()));
						conInformation.setEffectDate(new Date(System.currentTimeMillis()));
						conInformation.setEffectEmp(ResourceUtil.getSessionUser().getId());
					}
					if(w==2){
						conInformation.setConfermState(state);
						conInformation.setConfermDate(null);
						conInformation.setEffectDate(null);
						conInformation.setEffectEmp("");
					}
					/*if(w==3){
						conInformation.setConState(state);
						//更新资产的保修到期日 开始
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
						if("医疗设备保修合同".equals(conInformation.getConName())){
							List<ConMarkEntity> conMarkList = this.systemService.findByProperty(ConMarkEntity.class, "conMainId", conInformation.getId());
							if(conMarkList != null && conMarkList.size() > 0){
								for(ConMarkEntity conMark : conMarkList){
									List<PpeCardsEntity> ppeCards = this.systemService.findByProperty(PpeCardsEntity.class, "assetCoding", conMark.getMarkNo());
									if(ppeCards != null && ppeCards.size() > 0){
										//查询无保修的内容   当前日期大于保修到期日 或 保修到期日为空的为无保修   
										Date guaranteeDate = ppeCards.get(0).getGuaranteeDate();
										if((ppeCards.get(0).getGuaranteeDate() == null || new Date().getTime() > ppeCards.get(0).getGuaranteeDate().getTime()) 
												//是否保修为：否  或保修到期小于合同开始日期  保修到期日=合同开始日期+保修期
												|| (ppeCards.get(0).getGuaranteeDate() != null &&  ppeCards.get(0).getGuaranteeDate().getTime() < conInformation.getStartDate().getTime())){
											String calculationDate = DateUtils.calculationDate(sdf, sdf.format(conInformation.getStartDate()), conInformation.getPerstate(), null, null);
											ppeCards.get(0).setGuaranteeDate(sdf.parse(calculationDate));
										}
										//保修到期日大于合同开始日期  保修到期日=原保修到期日+保修期
										if(guaranteeDate != null && guaranteeDate.getTime() > conInformation.getStartDate().getTime()){
											String calculationDate = DateUtils.calculationDate(sdf, sdf.format(ppeCards.get(0).getGuaranteeDate()), conInformation.getPerstate(), null, null);
											ppeCards.get(0).setGuaranteeDate(sdf.parse(calculationDate));
										}
										this.systemService.updateEntitie(ppeCards.get(0));
									}
								}
							}
						}
						//结束
					}*/
					if(w==4){
						conInformation.setQxZz(conInformation.getConState());
						conInformation.setConState(state);
						conInformation.setStateDate5(new Date(System.currentTimeMillis()));
					}
					
					if(w==5){
						conInformation.setConExec9(conInformation.getConState());
						conInformation.setConState(state);
						conInformation.setStateDate6(new Date(System.currentTimeMillis()));
					}
					if(w==6){
						conInformation.setQxJc(conInformation.getConState());
						conInformation.setConState(state);
						conInformation.setStateDate3(new Date(System.currentTimeMillis()));
					}
					
					conInformationService.saveOrUpdate(conInformation);
					
				 
					systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
				}
			}catch(Exception e){
				e.printStackTrace();
				if(w==1){
					
					message = "合同审核失败";
							}
				if(w==2){
					message="合同弃审失败";
				}
				if(w==3){
					message="合同履行失败！";
				}
				if(w==4){
					message="合同终止失败！";
				}
				if(w==5){
					message="合同作废失败！";
				}
				if(w==6){
					message="合同解除失败！";
				}
				if(w==7){
					message="合同取消终止失败！";
				}
				if(w==8){
					message="合同取消解除失败！";
				}
				if(w==9){
					message="合同取消作废失败！";
				}
				throw new BusinessException(e.getMessage());
			}
			j.setMsg(message);
			return j;
		}
		
		
		
			/**
			 * 合同变更操作
			 * 
			 * @param ids
			 * @return
			 */
			@RequestMapping(params = "doAddbg")
			@ResponseBody
			public AjaxJson doAddbg(ConInformationEntity conInformation,ConInformationPage conInformationPage, HttpServletRequest request) {
				List<ConMarkEntity> conMarkList =  conInformationPage.getConMarkList();
				List<PayPlanEntity> payPlanList =  conInformationPage.getPayPlanList();
				List<ConMemorabiliaEntity> conMemorabiliaList =  conInformationPage.getConMemorabiliaList();
				List<ContBankEntity> contBankList =  conInformationPage.getContBankList();
				AjaxJson j = new AjaxJson();
		  
		      String message = "合同变更成功";
		    	 String sql="update con_information set con_type='"+conInformationPage.getConExec4()+"' where id='"+conInformationPage.getConExec5()+"'";
		    	 try {
					conInformationService.updateBySqlString(sql);
				} catch (Exception e) {
					e.printStackTrace();
				}
		    	
		
				try{ 
					List<PayPlanEntity> payPlan = new  ArrayList<PayPlanEntity>();
					if(payPlanList==null||payPlanList.size()==0){
						  String hql1 = "from PayPlanEntity where 1 = 1 AND cON_ID = ? ";
						  payPlanList = conInformationService.findHql(hql1,conInformation.getConExec5());
						
					}
					for (PayPlanEntity payPlanEntity : payPlanList) {
						double money=0.00;
						money=Double.valueOf(conInformation.getConExec1()==null?"0.00":conInformation.getConExec1().toString())*(payPlanEntity.getPayRate()==null?0.00:payPlanEntity.getPayRate());
						money=money/100;
						payPlanEntity.setPayMoney(money);
						payPlan.add(payPlanEntity);
					}
					conInformation.setStateDate4(new Date(System.currentTimeMillis()));
					conInformation.setIsBg("1");
					conInformation.setIsDefout("0");
					conInformationService.addMain(conInformation, conMarkList,payPlan,conMemorabiliaList,contBankList);
					systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
				}catch(Exception e){
					e.printStackTrace();
					message = "合同信息变更失败";
					throw new BusinessException(e.getMessage());
				}
				j.setMsg(message);
				return j;
			}
		
		
	 

	/**
	 * 添加合同信息
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(ConInformationEntity conInformation,ConInformationPage conInformationPage, HttpServletRequest request) {
		List<ConMarkEntity> conMarkList =  conInformationPage.getConMarkList();
		List<PayPlanEntity> payPlanList =  conInformationPage.getPayPlanList();
		List<ConMemorabiliaEntity> conMemorabiliaList =  conInformationPage.getConMemorabiliaList();
		List<ContBankEntity> contBankList =  conInformationPage.getContBankList();
		AjaxJson j = new AjaxJson();
     String flag =request.getParameter("flag");
     String message = "添加成功";
     if("1".equals(flag)){
    	 message = "合同续签成功";
    	 conInformation.setConState("02");
    	 conInformation.setConType(request.getParameter("con_type"));
    	 String sql="update con_information set con_state='05' where id='"+conInformationPage.getConExec3()+"'";
    	 try {
			conInformationService.updateBySqlString(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
     }
		try{
			List<PayPlanEntity> payPlan = new  ArrayList<PayPlanEntity>();
			if(payPlanList==null||payPlanList.size()==0){
				  String hql1 = "from PayPlanEntity where 1 = 1 AND cON_ID = ? ";
				  if("1".equals(flag)){
				  payPlanList = conInformationService.findHql(hql1,conInformation.getConExec3());
				  }else{
					  payPlanList = conInformationService.findHql(hql1,conInformation.getId());
				  }
				
			}
			for (PayPlanEntity payPlanEntity : payPlanList) {
				double money=0.00;
				money=Double.valueOf(conInformation.getConExec1()==null?"0.00":conInformation.getConExec1().toString())*((payPlanEntity.getPayRate()==null?0.00:payPlanEntity.getPayRate()));
				money=money/100;
				payPlanEntity.setPayMoney(money);
				payPlan.add(payPlanEntity);
			}
			
			
			conInformation.setIsDefout("0");
			if(!"1".equals(flag)){
			conInformation.setConNumnerZ("0");
			conInformation.setIsBg("0");
			}
			conInformation.setConNumber(generateBillCode());
			conInformationService.addMain(conInformation, conMarkList,payPlan,conMemorabiliaList,contBankList);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "合同信息添加失败";
			 if("1".equals(flag)){
		    	 message = "合同续签失败";
		     }
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	/**
	 * 更新合同信息
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(ConInformationEntity conInformation,ConInformationPage conInformationPage, HttpServletRequest request) {
		List<ConMarkEntity> conMarkList =  conInformationPage.getConMarkList();
		List<PayPlanEntity> payPlanList =  conInformationPage.getPayPlanList();
		List<ConMemorabiliaEntity> conMemorabiliaList =  conInformationPage.getConMemorabiliaList();
		List<ContBankEntity> contBankList =  conInformationPage.getContBankList();
		List<PayPlanEntity> payPlan = new  ArrayList<PayPlanEntity>();
		if(payPlanList==null||payPlanList.size()==0){
			  String hql1 = "from PayPlanEntity where 1 = 1 AND cON_ID = ? ";
			  payPlanList = conInformationService.findHql(hql1,conInformation.getId());
			
		}
		
		for (PayPlanEntity payPlanEntity : payPlanList) {
			double money=0.00;
			money=Double.valueOf(conInformation.getConExec1()==null?"0.00":conInformation.getConExec1().toString())*(payPlanEntity.getPayRate()==null?0.00:payPlanEntity.getPayRate());
			money=money/100;
			payPlanEntity.setPayMoney(money);
			payPlan.add(payPlanEntity);
		}
		AjaxJson j = new AjaxJson();
		String message = "更新成功";
		try{
			conInformationService.updateMain(conInformation, conMarkList,payPlan,conMemorabiliaList,contBankList);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "更新合同信息失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 合同信息新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(ConInformationEntity conInformation, HttpServletRequest req) {
		String dept_code=	ResourceUtil.getSessionUser().getDepartid();
		req.setAttribute("deptId", dept_code);
		req.setAttribute("signedDate",new Date(System.currentTimeMillis()));
		req.setAttribute("startDate", new Date(System.currentTimeMillis()));
	/*	String uid = UUID.randomUUID().toString();
		req.setAttribute("fj", uid);*/
		
		if (StringUtil.isNotEmpty(conInformation.getId())) {
			conInformation = conInformationService.getEntity(ConInformationEntity.class, conInformation.getId());
			req.setAttribute("conInformationPage", conInformation);
			
		}
		return new ModelAndView("cn/com/linkwide/cont/coninformation/conInformation-add");
	}
	
	
	
	/**
	 * 合同信息新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAddAsse")
	public ModelAndView goAddAsse(ConInformationEntity conInformation, HttpServletRequest req) {
		String dept_code=	ResourceUtil.getSessionUser().getDepartid();
		req.setAttribute("deptId", dept_code);
		req.setAttribute("signedDate",new Date(System.currentTimeMillis()));
		req.setAttribute("startDate", new Date(System.currentTimeMillis()));
	/*	String uid = UUID.randomUUID().toString();
		req.setAttribute("fj", uid);*/
		
		if (StringUtil.isNotEmpty(conInformation.getId())) {
			conInformation = conInformationService.getEntity(ConInformationEntity.class, conInformation.getId());
			req.setAttribute("conInformationPage", conInformation);
			
		}
		return new ModelAndView("cn/com/linkwide/cont/coninformation/conInformationAsset/conInformation-add");
	}
	
	
	
	/**
	 * 合同信息新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAddCgAsse")
	public ModelAndView goAddCgAsse(ConInformationEntity conInformation, HttpServletRequest req) {
		String dept_code=	ResourceUtil.getSessionUser().getDepartid();
		req.setAttribute("deptId", dept_code);
		req.setAttribute("signedDate",new Date(System.currentTimeMillis()));
		req.setAttribute("startDate", new Date(System.currentTimeMillis()));
	/*	String uid = UUID.randomUUID().toString();
		req.setAttribute("fj", uid);*/
		
		if (StringUtil.isNotEmpty(conInformation.getId())) {
			conInformation = conInformationService.getEntity(ConInformationEntity.class, conInformation.getId());
			req.setAttribute("conInformationPage", conInformation);
			
		}
		return new ModelAndView("cn/com/linkwide/cont/coninformation/conInformationAsset/conInformationCg-add");
	}
	/**
	 * 合同信息新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAddWxAsse")
	public ModelAndView goAddWxAsse(ConInformationEntity conInformation, HttpServletRequest req) {
		String dept_code=	ResourceUtil.getSessionUser().getDepartid();
		req.setAttribute("deptId", dept_code);
		req.setAttribute("signedDate",new Date(System.currentTimeMillis()));
		req.setAttribute("startDate", new Date(System.currentTimeMillis()));
	/*	String uid = UUID.randomUUID().toString();
		req.setAttribute("fj", uid);*/
		
		if (StringUtil.isNotEmpty(conInformation.getId())) {
			conInformation = conInformationService.getEntity(ConInformationEntity.class, conInformation.getId());
			req.setAttribute("conInformationPage", conInformation);
			
		}
		return new ModelAndView("cn/com/linkwide/cont/coninformation/conInformationAsset/conInformationWx-add");
	}
	
	
	/**
	 * 参照中标单页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAddZbAsse")
	public ModelAndView goAddZbAsse(ConInformationEntity conInformation, HttpServletRequest req) {
		req.setAttribute("conInformationPage", conInformation);
		return new ModelAndView("cn/com/linkwide/cont/coninformation/conInformationAsset/conInformationZb-add");
	}
	
	
	
	/**
	 * 合同信息新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAddXf")
	public ModelAndView goAddXf(ConInformationEntity conInformation, HttpServletRequest req) {
		String dept_code=	ResourceUtil.getSessionUser().getDepartid();
		req.setAttribute("deptId", dept_code);
		req.setAttribute("signedDate",new Date(System.currentTimeMillis()));
		req.setAttribute("startDate", new Date(System.currentTimeMillis()));
		req.setAttribute("flag", req.getParameter("flag"));
	/*	String uid = UUID.randomUUID().toString();
		req.setAttribute("fj", uid);*/
		
		if (StringUtil.isNotEmpty(conInformation.getId())) {
			conInformation = conInformationService.getEntity(ConInformationEntity.class, conInformation.getId());
			req.setAttribute("conInformationPage", conInformation);
			
		}
		return new ModelAndView("cn/com/linkwide/cont/coninformation/conInformationJx-add");
	}
	
	
	
	
	/**
	 * 合同信息编辑页面跳转  
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(ConInformationEntity conInformation, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(conInformation.getId())) {
			
			
			
			conInformation = conInformationService.getEntity(ConInformationEntity.class, conInformation.getId());
			if("".equals(conInformation.getConNumnerZ())||null==conInformation.getConNumnerZ()||"0".equals(conInformation.getConNumnerZ())){
				conInformation.setRenewId(conInformation.getId());
			}
			req.setAttribute("conInformationPage", conInformation);
		}
		return new ModelAndView("cn/com/linkwide/cont/coninformation/conInformation-update");
	}
	

	/**
	 * 提交
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "goSubmit")
	@ResponseBody
	public AjaxJson goSubmit(HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		String ids=request.getParameter("ids");
		String workflowId ="";
		String actReModelCode = request.getParameter("actReModelCode"); //工作流id
		
				  List<Map<String,Object>> l1 = systemService.findForJdbc("select id_ id from act_re_procdef where version_=(select max(version_) from act_re_procdef where name_=?) and  name_=?",actReModelCode,actReModelCode);
	//	  List<Map<String,Object>> l1 = systemService.findForJdbc("select id_ id,max() from act_re_procdef where name_=?",actReModelCode);
			if(l1.size() > 0 ){
				workflowId = l1.get(0).get("id").toString();
				}else{
					j.setMsg("操作失败：审批流"+actReModelCode+"不存在!");
					return j;
				}
		if(StringUtil.isNotEmpty(ids) && StringUtil.isNotEmpty(workflowId)){
			String[] idsArr = ids.split(",");
			for (int i = 0; i < idsArr.length; i++) {
				ConInformationEntity entity = systemService.get(ConInformationEntity.class, idsArr[i]);
				if(!"退回".equals(entity.getConfermState())){
					entity.setWorkflowId(workflowId);
				}
			/*	 String hql0 = "from ConMarkEntity where 1 = 1 AND cON_MAIN_ID = ? ";
				 List<ConMarkEntity> conMarkEntityList = systemService.findHql(hql0,entity.getId());
				 int  number=0;
				 for (ConMarkEntity conMarkEntity : conMarkEntityList) {
					 if(StringUtil.isNotEmpty(conMarkEntity.getExten4())){
					 LBaMaterialEntity lBaMaterialEntity = systemService.getEntity(LBaMaterialEntity.class, conMarkEntity.getExten4());
					 if(lBaMaterialEntity.getIsAssets()==1   ){
						 number=number+1;
					 }
					 }
				}
			
				 if(number>0){
						entity.setIsAssier("1");
				 }else{
					 entity.setIsAssier("0");
				 }
				    try {
						conInformationService.saveOrUpdate(entity);
						systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}*/
				//提交审批，启动工作流
				conInformationService.approve(entity,actReModelCode);
			}
		}
		message="提交成功";
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 审批页面跳转前查询任务id
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "goAudit")
	@ResponseBody
	public AjaxJson goAudit(HttpServletRequest request) {
		String taskIds = null;
		AjaxJson j = new AjaxJson();
		String ids=request.getParameter("ids");
		if(StringUtil.isNotEmpty(ids)){
			String[] idsArr = ids.split(",");
			for (int i = 0; i < idsArr.length; i++) {
				ConInformationEntity entity = systemService.get(ConInformationEntity.class, idsArr[i]);
				String workflowId = entity.getWorkflowId();//工作流id
				String sql = " SELECT DISTINCT a.ID_ \"taskId\" from act_ru_task a "
						+ " where a.PROC_DEF_ID_='"+workflowId+"' "
						+ " and a.PROC_INST_ID_ in (SELECT PROC_INST_ID_ from act_ru_execution b  "
						+ " where PROC_DEF_ID_='"+workflowId+"' and b.BUSINESS_KEY_ like '%"+idsArr[i]+"' )  ";
				List<Map<String,Object>> list = systemService.findForJdbc(sql, new Object[]{});
				if(list != null && list.size()>0){
					taskIds=taskIds == null?String.valueOf(list.get(0).get("taskId")):taskIds+","+String.valueOf(list.get(0).get("taskId"));
				}
			}
		}
		j.setMsg(taskIds);
		return j;
	}
	
	/**
	 * 取消审核页面跳转
	 * @param taskId
	 * @param req
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "goHandTask")
	public ModelAndView goHandTask(String taskId, HttpServletRequest req) throws Exception {
		String busiID = req.getParameter("busiID");
		String controllerName = req.getParameter("controllerName");
		List<String> comeList = new ArrayList<>();
		comeList.add("退回");
		req.setAttribute("comeList", comeList);
		req.setAttribute("busiID", busiID);
		req.setAttribute("controller_name", controllerName);
		//查询历史审批意见
		String sql =" select c.realname \"auditUser\",a.message_ \"comment\" from act_hi_comment a "
				+ "inner join act_ru_task b on a.proc_inst_id_=b.proc_inst_id_  "
				+ "left join t_s_base_user c on a.user_id_=c.id "
				+ "where b.id_='"+taskId+"' order by a.time_ ";
		List<Map<String,Object>> commentList = systemService.findForJdbc(sql, new Object[]{});
		
		req.setAttribute("commentList", commentList);
		return new ModelAndView("cn/com/linkwide/ef/budgincom/budgdeptmainincom/busiDocApproveBack");
	}
	
	
	
	
	
	
	
	
	/**
	 * 合同信息续签跳转界面
	 * 
	 * @return
	 */
	@RequestMapping(params = "confirmRenew")
	public ModelAndView confirmRenew(ConInformationEntity conInformation, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(conInformation.getId())) {
			
			conInformation = conInformationService.getEntity(ConInformationEntity.class, conInformation.getId());
			if("".equals(conInformation.getConNumnerZ())||"0".equals(conInformation.getConNumnerZ())||null==conInformation.getConNumnerZ()){
				req.setAttribute("conNumnerZ","1");
				//conInformation.setRenewId(conInformation.getId());
			
			}else{
				Integer a=Integer.parseInt(conInformation.getConNumnerZ());
				String b =String.valueOf(a+1);
				req.setAttribute("conNumnerZ", b);
			
			}
			
			conInformation.setStartDate(new Date(System.currentTimeMillis()));
			req.setAttribute("conInformationPage", conInformation);
		}
		return new ModelAndView("cn/com/linkwide/cont/coninformation/conInformationRenew");
	}
	
	
	/**
	 * 加载明细列表[合同标的]
	 * 
	 * @return
	 */
	@RequestMapping(params = "conMarkList")
	public ModelAndView conMarkList(ConInformationEntity conInformation, HttpServletRequest req) {
	
		//===================================================================================
		//获取参数
		Object id0 = conInformation.getId();
		//===================================================================================
		//查询-合同标的
	    String hql0 = "from ConMarkEntity where 1 = 1 AND cON_MAIN_ID = ? ";
	    try{
	    	List<ConMarkEntity> conMarkEntityList = systemService.findHql(hql0,id0);
			req.setAttribute("conMarkList", conMarkEntityList);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return new ModelAndView("cn/com/linkwide/cont/coninformationdetial/conMarkList");
	}
	
	
	/**
	 * 加载明细列表[合同标的]
	 * 
	 * @return
	 */
	@RequestMapping(params = "conMarkEqList")
	public ModelAndView conMarkEqList(ConInformationEntity conInformation, HttpServletRequest req) {
	
		//===================================================================================
		//获取参数
		Object id0 = conInformation.getId();
		//===================================================================================
		//查询-合同标的
	    String hql0 = "from ConMarkEntity where 1 = 1 AND cON_MAIN_ID = ? ";
	    try{
	    	List<ConMarkEntity> conMarkEntityList = systemService.findHql(hql0,id0);
			req.setAttribute("conMarkList", conMarkEntityList);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return new ModelAndView("cn/com/linkwide/cont/coninformationdetial/conMarkEqList");
	}
	
	/**
	 * 加载明细列表[合同标的]
	 * 
	 * @return
	 */
	@RequestMapping(params = "conMarkZbList")
	public ModelAndView conMarkZbList(ConInformationEntity conInformation, HttpServletRequest req) {
	
		//===================================================================================
		//获取参数
		String id0 = conInformation.getId();
		//===================================================================================
		//查询-合同标的
	    try{
	    	ConFinalConfirmbillEntity conFinalConfirmbill = this.conInformationService.getEntity(ConFinalConfirmbillEntity.class, id0);
	    	/*ConReqInstructionsDetailEntity conReqInstructionsDetail = this.conInformationService.getEntity(ConReqInstructionsDetailEntity.class, conFinalConfirmbill.getReqId());
	    	String equeCode = conReqInstructionsDetail.getEqueCode();
	    	String sql = " select t.SPEC_MODEL,t.UNIT_PRICE  from PPE_CARDS t where t.ASSET_CODING = ? ";
	    	List<Map<String, Object>> findForJdbc = this.conInformationService.findForJdbc(sql, equeCode);
	    	if(findForJdbc != null && findForJdbc.size() > 0){
	    		
	    	}*/
	    	List<ConMarkEntity>  list = new ArrayList<ConMarkEntity>();
	    	ConMarkEntity conMarkEntity = new ConMarkEntity();
	    	conMarkEntity.setConMainId(id0);  //主键
	    	conMarkEntity.setMarkNo(conFinalConfirmbill.getEquCode());  //编码
	    	conMarkEntity.setMarkName(conFinalConfirmbill.getEquName()); //名称
	    	conMarkEntity.setMarkBz("01");  //标的标志
	    	conMarkEntity.setMarkSpece(conFinalConfirmbill.getSpecType());  //规格型号
	    	conMarkEntity.setMarkNumber(1d);   //数量
	    	conMarkEntity.setMarkPrice(Double.valueOf(conFinalConfirmbill.getOfferPrice()));  //单价
	    	conMarkEntity.setMarkMoney(Double.valueOf(conFinalConfirmbill.getOfferPrice()));  //金额
	    	conMarkEntity.setDeptID(conFinalConfirmbill.getDeptId());  //部门id
	    	conMarkEntity.setDeptName(conFinalConfirmbill.getDeptName());  //部门名称
	    	conMarkEntity.setExten1(conFinalConfirmbill.getId());  //中标id
	    	list.add(conMarkEntity);
	    	req.setAttribute("conMarkList", list);
	    	
	    }catch(Exception e){
			logger.info(e.getMessage());
		}
		return new ModelAndView("cn/com/linkwide/cont/coninformationdetial/conMarkZbList");
	}
	
	
	/**
	 * 加载明细列表[合同付款计划]
	 * 
	 * @return
	 */
	@RequestMapping(params = "payPlanList")
	public ModelAndView payPlanList(ConInformationEntity conInformation, HttpServletRequest req) {
	
		//===================================================================================
		//获取参数
		Object id0 = conInformation.getId();
		//===================================================================================
		//查询-合同付款计划
		   String hql0 = "from PayPlanEntity where 1 = 1 AND cON_ID = ? ";
		    try{
		    	List<PayPlanEntity> payPlanEntityList = systemService.findHql(hql0,id0);
				req.setAttribute("payPlanList", payPlanEntityList);
			}catch(Exception e){
				logger.info(e.getMessage());
			}
		return new ModelAndView("cn/com/linkwide/cont/coninformationdetial/payPlanList");
	}
	/**
	 * 加载明细列表[合同付款大事记]
	 * 
	 * @return
	 */
	@RequestMapping(params = "conMemorabiliaList")
	public ModelAndView conMemorabiliaList(ConInformationEntity conInformation, HttpServletRequest req) {
	
		//===================================================================================
		//获取参数
		Object id0 = conInformation.getId();
		//===================================================================================
		//查询-合同标的
	    String hql0 = "from ConMemorabiliaEntity where 1 = 1 AND cON_ID = ? ";
	    try{
	    	List<ConMemorabiliaEntity> conMemorabiliaEntityList = systemService.findHql(hql0,id0);
			req.setAttribute("conMemorabiliaList", conMemorabiliaEntityList);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return new ModelAndView("cn/com/linkwide/cont/coninformationdetial/conMemorabiliaList");
	}
	/**
	 * 加载明细列表[银行保函]
	 * 
	 * @return
	 */
	@RequestMapping(params = "contBankList")
	public ModelAndView contBankList(ConInformationEntity conInformation, HttpServletRequest req) {
	
		//===================================================================================
		//获取参数
		Object id0 = conInformation.getId();
		//===================================================================================
		//查询-合同标的
	    String hql0 = "from ContBankEntity where 1 = 1 AND cON_ID = ? ";
	    try{
	    	List<ContBankEntity> contBankEntityList = systemService.findHql(hql0,id0);
			req.setAttribute("contBankList", contBankEntityList);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return new ModelAndView("cn/com/linkwide/cont/coninformationdetial/contBankList");
	}
	
	
	/**
	 * 弹出引入招标计划页面
	 * @param request
	 * @return
	 */
	@RequestMapping(params="pkplanSelect")
	public ModelAndView pkplanSelect(HttpServletRequest request){
		String ven_id=request.getParameter("ven_id");
		request.setAttribute("ven_code", ven_id);
		return new ModelAndView("cn/com/linkwide/cont/coninformation/implBidconfirmList");
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
	public void mateDatagrid(LBaDepartMateEntity lBaDepartMate,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		try{
		//自定义追加查询条件
		
			StringBuffer sql = new StringBuffer();
			sql.append(" from mate_supmate_main a left join l_ba_material b on a.inv_id=b.id  ");
		
				sql.append(" left join l_ba_material_mater_unit c on b.mater_unit_id=c.ID");
				sql.append(" left join mate_sup_bid d on a.sup_id=d.id");
				sql.append(" where a.id not IN ( select exten1 from con_mark where exten1 <>'' )");
				sql.append(" and d.sup_state='4'");
				sql.append(" and a.inv_id is not null");
				sql.append(" and a.supplier_id='"+request.getParameter("ven_id")+"'");
			
			List<Map<String,Object>> list = systemService.findForJdbc("select a.id as id,b.material_code as materialCode,a.inv_name as invName,a.spec_model as specModel,d.conf_num as num,a.amount_money as amoutMoney,a.price as price,b.mater_unit_id  as materUnitId,c.type_code as typeCode,c.type_name as typeNme,a.inv_id as invId "+sql.toString(), dataGrid.getPage(), dataGrid.getRows());
			dataGrid.setResults(list);
			List<Map<String,Object>> listC = systemService.findForJdbc("select count(a.id) as con "+sql.toString());
			dataGrid.setTotal(Integer.valueOf(listC.get(0).get("con").toString()));
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		TagUtil.datagrid(response, dataGrid);
	
	}
	
	
	
    /**
    * 导出excel
    *
    * @param request
    * @param response
    */
    @RequestMapping(params = "exportXls")
    public String exportXls(ConInformationEntity conInformation,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid,ModelMap map) {
    	CriteriaQuery cq = new CriteriaQuery(ConInformationEntity.class, dataGrid);
    	//查询条件组装器
    	org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, conInformation);
    	try{
    	//自定义追加查询条件
    	}catch (Exception e) {
    		throw new BusinessException(e.getMessage());
    	}
    	cq.add();
    	List<ConInformationEntity> list=this.conInformationService.getListByCriteriaQuery(cq, false);
    	List<ConInformationPage> pageList=new ArrayList<ConInformationPage>();
        if(list!=null&&list.size()>0){
        	for(ConInformationEntity entity:list){
        		try{
        		ConInformationPage page=new ConInformationPage();
        		   MyBeanUtils.copyBeanNotNull2Bean(entity,page);
            	    Object id0 = entity.getId();
				    String hql0 = "from ConMarkEntity where 1 = 1 AND cON_MAIN_ID = ? ";
        	        List<ConMarkEntity> conMarkEntityList = systemService.findHql(hql0,id0);
            		page.setConMarkList(conMarkEntityList);
            		if(!StringUtil.isNotEmpty(page.getConExec10())){
            			page.setConExec10("");
            		}
            		if(!StringUtil.isNotEmpty(page.getDeptId())){
            			page.setDeptId("");
            		}
            		pageList.add(page);
            	}catch(Exception e){
            		logger.info(e.getMessage());
            	}
            }
        }
        map.put(NormalExcelConstants.FILE_NAME,"合同信息");
        map.put(NormalExcelConstants.CLASS,ConInformationPage.class);
        map.put(NormalExcelConstants.PARAMS,new ExportParams("合同信息列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<ConInformationPage> list =  ExcelImportUtil.importExcel(file.getInputStream(), ConInformationPage.class, params);
				ConInformationEntity entity1=null;
				for (ConInformationPage page : list) {
					entity1=new ConInformationEntity();
					MyBeanUtils.copyBeanNotNull2Bean(page,entity1);
		            conInformationService.addMain(entity1, page.getConMarkList(),page.getPayPlanList(),page.getConMemorabiliaList(),page.getContBankList());
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
		map.put(NormalExcelConstants.FILE_NAME,"合同信息");
		map.put(NormalExcelConstants.CLASS,ConInformationPage.class);
		map.put(NormalExcelConstants.PARAMS,new ExportParams("合同信息列表", "导出人:"+ ResourceUtil.getSessionUser().getRealName(),
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
		req.setAttribute("controller_name", "conInformationController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}

 	
 	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="合同信息列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<ConInformationPage>> list() {
		List<ConInformationEntity> list= conInformationService.getList(ConInformationEntity.class);
    	List<ConInformationPage> pageList=new ArrayList<ConInformationPage>();
        if(list!=null&&list.size()>0){
        	for(ConInformationEntity entity:list){
        		try{
        			ConInformationPage page=new ConInformationPage();
        		   MyBeanUtils.copyBeanNotNull2Bean(entity,page);
					Object id0 = entity.getId();
				     String hql0 = "from ConMarkEntity where 1 = 1 AND cON_MAIN_ID = ? ";
	    			List<ConMarkEntity> conMarkOldList = this.conInformationService.findHql(hql0,id0);
            		page.setConMarkList(conMarkOldList);
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
	@ApiOperation(value="根据ID获取合同信息信息",notes="根据ID获取合同信息信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		ConInformationEntity task = conInformationService.get(ConInformationEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取合同信息信息为空");
		}
		ConInformationPage page = new ConInformationPage();
		try {
			MyBeanUtils.copyBeanNotNull2Bean(task, page);
				Object id0 = task.getId();
		    String hql0 = "from ConMarkEntity where 1 = 1 AND cON_MAIN_ID = ? ";
			List<ConMarkEntity> conMarkOldList = this.conInformationService.findHql(hql0,id0);
    		page.setConMarkList(conMarkOldList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Result.success(page);
	}
 	
 	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建合同信息")
	public ResponseMessage<?> create(@ApiParam(name="合同信息对象")@RequestBody ConInformationPage conInformationPage, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ConInformationPage>> failures = validator.validate(conInformationPage);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		List<ConMarkEntity> conMarkList =  conInformationPage.getConMarkList();
		List<PayPlanEntity> payPlanList =  conInformationPage.getPayPlanList();
		List<ConMemorabiliaEntity> conMemorabiliaList =  conInformationPage.getConMemorabiliaList();
		List<ContBankEntity> contBankList =  conInformationPage.getContBankList();
		ConInformationEntity conInformation = new ConInformationEntity();
		try{
			MyBeanUtils.copyBeanNotNull2Bean(conInformationPage,conInformation);
		}catch(Exception e){
            logger.info(e.getMessage());
            return Result.error("保存合同信息失败");
        }
		conInformationService.addMain(conInformation, conMarkList,payPlanList,conMemorabiliaList,contBankList);

		return Result.success(conInformation);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新合同信息",notes="更新合同信息")
	public ResponseMessage<?> update(@RequestBody ConInformationPage conInformationPage) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<ConInformationPage>> failures = validator.validate(conInformationPage);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		List<ConMarkEntity> conMarkList =  conInformationPage.getConMarkList();
		List<PayPlanEntity> payPlanList =  conInformationPage.getPayPlanList();
		List<ConMemorabiliaEntity> conMemorabiliaList =  conInformationPage.getConMemorabiliaList();
		List<ContBankEntity> contBankList =  conInformationPage.getContBankList();
		ConInformationEntity conInformation = new ConInformationEntity();
		try{
			MyBeanUtils.copyBeanNotNull2Bean(conInformationPage,conInformation);
		}catch(Exception e){
            logger.info(e.getMessage());
            return Result.error("合同信息更新失败");
        }
		conInformationService.updateMain(conInformation, conMarkList,payPlanList,conMemorabiliaList,contBankList);

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除合同信息")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" + id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			ConInformationEntity conInformation = conInformationService.get(ConInformationEntity.class, id);
			conInformationService.delMain(conInformation);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("合同信息删除失败");
		}

		return Result.success();
	}
	
	@RequestMapping(params = "GetTable")
	@ResponseBody
	public AjaxJson GetTable( HttpServletRequest req){
		AjaxJson ja = new AjaxJson();
		String id=req.getParameter("busId");
		String workflowId=req.getParameter("workflowId");
		StringBuilder sb = new StringBuilder();
		ConInformationEntity t=null;
		LBaSupplierEntity ba=null;
		ProcolContEntity procolCont=null;
		TSDepart dept=null;
		String supplier="";
		try {
			if(StringUtil.isNotEmpty(id)){
				t= conInformationService.get(ConInformationEntity.class,id);
			}
			if(StringUtil.isNotEmpty(t.getOtherCompy())){
				 supplier=t.getOtherCompy();
			}
			if(StringUtil.isNotEmpty(t.getConExec10())){
				procolCont=systemService.get(ProcolContEntity.class, t.getConExec10());
			}
			if(StringUtil.isNotEmpty(t.getDeptId())){
				dept=systemService.get(TSDepart.class, t.getDeptId());
			}
			String isBid="";
			if("1".equals(t.getIsBid())){
				isBid="是";
			}else{
				
				isBid="否";
			}
			//NumberFormat numberFormat1 = NumberFormat.getNumberInstance();
			DecimalFormat df =new DecimalFormat("###,##0.00");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String sql=" select a.mark_name markName,a.mark_spece markSpece,b.type_name typeName,a.mark_number markNumber,a.mark_price markPrice ,a.mark_money markMoney from con_mark a LEFT JOIN l_ba_material_mater_unit b on a.mark_unit=b.type_code where con_main_id='"+id+"'";
			List<Map<String, Object>> markList=systemService.findForJdbc(sql, null);
			//查询审批记录
			Map params = new HashMap();
			params.put("busId", id);
			params.put("workflowId", workflowId);
			params.put("isComplete", "true"); //只查询审批通过和退回的数据
			List<Map<String,Object>> splList1=workFlowService.queryProcessRecord(params);
//			String spl="SELECT A.name_ name,A.assignee_ assign,D.realname \"realName\",A.start_time_ startTime,b.time_ time,TO_CHAR (b.message_) message,A.end_time_ endTime,A .delete_reason_ deleteReason FROM act_hi_taskinst A";
//			spl+=" LEFT JOIN act_hi_comment b ON A .proc_inst_id_ = b.proc_inst_id_ AND A .id_ = b.task_id_ INNER JOIN act_hi_procinst c ON A .proc_inst_id_ = c.proc_inst_id_ AND A .proc_def_id_ = c.proc_def_id_";
//			spl+=" LEFT JOIN t_s_base_user D ON D .username = A .assignee_";
//			spl+=" WHERE c.business_key_ LIKE '%"+id+"' ";
//			spl+=" and A.assignee_ is not null ";
//			spl+=" and ((a.start_time_ is not null and a.end_time_ is not null and a.delete_reason_ is not null) ";
//			spl+="  or (a.start_time_ is not null and  a.end_time_ is null and to_char(b.message_) is not null ))";
//			spl+=" AND c.proc_def_id_ = '"+workflowId+"' ORDER BY A .start_time_";
//			List<Map<String,Object>> splList1= systemService.findForJdbc(spl, null);
			//去重，将上次审批通过的节点只展示一次
			List<Map<String,Object>> splList=new ArrayList<Map<String,Object>>();
			Map reMap= new HashMap();
			if(splList1!= null && splList1.size()>0){
				for (int j = 0; j < splList1.size(); j++) {
					//节点名称
					String name1=splList1.get(j).get("name")==null?null:splList1.get(j).get("name").toString();
					//代理人
					String assign1 = splList1.get(j).get("assign")==null?null:splList1.get(j).get("assign").toString();
					//审批意见
					String message1 =splList1.get(j).get("message")==null?null:splList1.get(j).get("message").toString();
					//审批状态
					String auditStatus1 =splList1.get(j).get("auditStatus")==null?null:splList1.get(j).get("auditStatus").toString();
					if(splList!= null && splList.size()>0){
						for (int i = 0; i < splList.size(); i++) {
							String name =splList.get(i).get("name")==null?"":splList.get(i).get("name").toString();
							String assign =splList.get(i).get("assign")==null?"":splList.get(i).get("assign").toString();
							String message =splList.get(i).get("message")==null?"":splList.get(i).get("message").toString();
							String auditStatus=splList.get(i).get("auditStatus")==null?"":splList.get(i).get("auditStatus").toString();
							if(name.equals(name1)&&assign.equals(assign1)&&"通过".equals(auditStatus1)&&"通过".equals(auditStatus)){
							}else{
								String key = name1+assign1+auditStatus1;
								if(reMap.get(key)==null){
									splList.add(splList1.get(j));
									reMap.put(name1+assign1+auditStatus1, message1);
								}
							}
						}
					}else{
						splList.add(splList1.get(j));
						reMap.put(name1+assign1+auditStatus1, message1);
					}
				}
			}
			String a="";
			if(procolCont !=null){
			a=procolCont.getTypeName();
			}
			String b=t.getBidNo()==null?"":t.getBidNo();
			
				
				sb.append("<table width='90%'  style='border-collapse:collapse;margin-left:4%;'>" );
				sb.append("<thead> <tr> <th width='33%' align='left' colspan='6'>  "); 
				sb.append("<table style='border:0px;rules:none;cellSpacing=1' width='100%'>  <tbody><br> <tr> ");
				//sb.append(" <br><br><td rowspan='2' style=' width:180 '  ><img src='http://172.16.11.60/upload/hrp/file/20181226/201812262112407p3svDPj.png'width=160 height=30 >  </td> <td colspan='4' align='center'   ><h2>吉林大学第一医院</h2></td>  <td style='  width:180 ' ></td> </tr>  ");
				//sb.append(" <tr> <br><br>  <td colspan='4'  align='center'><h3>"+t.getConName()+"审核会签单</h3></td>    <td style=' width:180 ' ></td> </tr></tbody></table> </th></tr><tr >");
				sb.append(" <br><br><td colspan='6' align='center'   ><h2><b>吉林大学第一医院"+t.getConName()+"审核会签单</b></h2></td>    </tr>  ");
				//吉大医院现场文件服务器路径
				sb.append(" <br><br><td colspan='6' align='center'   ><img src='http://172.16.11.60/upload/hrp/file/conload/jdyy.png'   style='z-index: -1; position: absolute; left:150px;top:350px '> &nbsp; </td>   </tr>  ");
				//本机图片路径
				//sb.append(" <br><br><td colspan='6' align='center'   ><img src='F:/lwzk/soft/conload/jdyy.png'   style='z-index: -1; position: absolute; left:150px;top:350px '> &nbsp; </td>   </tr>  ");
				sb.append("    <td  style='border:1px solid black; background:#CCCCCC' width='250'    align='center' ><b>合同类型</b></td>");
				sb.append(" <td style='border:1px solid black;'>"+a+"</td> ");
				sb.append("  <td style='border:1px solid black;background:#CCCCCC' align='center' ><b>是否招标</b></td>");  
				sb.append(" <td style='border:1px solid black;' width='120'  align='center' >"+isBid+"</td> ");  
				sb.append("<td style='border:1px solid black;background:#CCCCCC' ><b>招标编号</b></td>");  
				sb.append("  <td style='border:1px solid black;'>"+b+"</td> </tr>  <tr>  ");
				sb.append("   <td  style='border:1px solid black;background:#CCCCCC'  align='center'><b>合同号</b></td>");
				sb.append("  <td  style='border:1px solid black;' colspan='2' >"+t.getConNo()+"</td> ");
				sb.append(" <td  style='border:1px solid black;background:#CCCCCC'  align='center' ><b>供应商</b></td> ");
				sb.append("<td  style='border:1px solid black;'  colspan='2'  >"+supplier+"</td> </tr>  <tr>");  
				sb.append("<td  style='border:1px solid black;background:#CCCCCC' width='250px'  align='center'><b>合同金额</b></td>");   
				sb.append("<td  style='border:1px solid black;' align='center' colspan='2' >"+df.format(Double.parseDouble(t.getConExec1().toString()))+"</td> ");  
				sb.append("<td style='border:1px solid black;background:#CCCCCC'  align='center'><b>科室</b></td> ");
				sb.append("<td style='border:1px solid black;'  colspan='2' > "+dept.getDepartname()+"</td> </tr>");   
				sb.append("<tr><td style='border:1px solid black;background:#CCCCCC' width='250px'  align='center'><b>物资名称</b></td>");  
				sb.append(" <td style='border:1px solid black;background:#CCCCCC'  align='center'><b>规格型号</b></td> ");
				sb.append("<td style='border:1px solid black;background:#CCCCCC'  align='center'><b>计量单位</b></td> ");
				sb.append("<td style='border:1px solid black;background:#CCCCCC' width='110'  align='center'><b>数量</b></td> ");
				sb.append("<td style='border:1px solid black;background:#CCCCCC'  align='center'><b>单价</b></td> ");
				sb.append("<td style='border:1px solid black;background:#CCCCCC' width='200'   align='center'><b>金额</b></td> </tr>");
				int i=0;
				for (Map<String, Object> map : markList) {
					String typeName=   map.get("typeName")==null?"":map.get("typeName").toString();
					String markSpece=  map.get("markSpece")==null?"":map.get("markSpece").toString();
					String markName=map.get("markName")==null?"":map.get("markName").toString();
					sb.append(" <tr><td style='border:1px solid black;'  width='250'>"+markName+"</td> ");
					if(StringUtil.isNotEmpty(map.get("markSpece"))){
					sb.append("<td style='border:1px solid black;'>"+markSpece+"</td>");
					}else{
						sb.append("<td style='border:1px solid black;'></td>");
					}
					sb.append(" <td style='border:1px solid black;'>"+typeName+"</td>");
					sb.append(" <td style='border:1px solid black;' align='center'>"+map.get("markNumber")+"</td>");
					sb.append(" <td style='border:1px solid black;' align='center'>"+df.format(Double.parseDouble(map.get("markPrice").toString()))+"</td>");
					sb.append(" <td style='border:1px solid black;' align='center'>"+df.format(Double.parseDouble(map.get("markMoney").toString()))+"</td>");
				} 
				sb.append("<tr><td style='border:1px solid black;background:#CCCCCC'colspan='6'><b>承办部门意见:</b></td></tr>");
					Map lxt=new HashMap<>();
					Map lx=new HashMap<>();
				for (Map<String, Object> map : splList) {
					if(!"内部".equals(map.get("name").toString().substring(0,2))){
					if(StringUtil.isNotEmpty(map.get("message")))
					{	
					lxt.put(map.get("assign"), map.get("message"));
					}
					if(StringUtil.isNotEmpty(map.get("time"))){
					lx.put(map.get("assign"), map.get("time"));
					}
					if("律师".equals(map.get("name"))&& i==0){
						i=i+1;
						sb.append("<tr><td style='border:1px solid black;background:#CCCCCC'colspan='6'><b>会签部门意见:</b></td></tr>");
					}
					sb.append("<tr colspan='6'><td rowspan='2' align='center' style='border:1px solid black;background:#CCCCCC  ' width='250px'><b>"+map.get("name")+"</b></td>");
					if(StringUtil.isNotEmpty(map.get("message"))){
						if("退回".equals(map.get("auditStatus").toString())){
						sb.append("<td  rowspan='2' style='border:1px solid black; '   colspan='4'><div style='width:400px;min-height:70px;margin-left:30px'>"+"<font  color='red'>退回：</font>"+map.get("message").toString()+"</div></td>");
						}else{
							sb.append("<td  rowspan='2' style='border:1px solid black; '  colspan='4'><div style='width:400px;min-height:70px; margin-left:30px'>"+ map.get("message").toString()+"</div></td>");
						}
					}else{
						if(StringUtil.isNotEmpty(lxt.get(map.get("assign")))){
							sb.append("<td  rowspan='2' style='border:1px solid black; '   colspan='4' ><div style='width:400px;margin-left:30px'>"+lxt.get(map.get("assign"))+"</div></td>");
						}
						else{
							sb.append("<td  rowspan='2' style='border:1px solid black; '   colspan='4'  ><div style='width:400px;margin-left:30px'>&nbsp;&nbsp;&nbsp;&nbsp;</div></td>");
						}
					}
					if(StringUtil.isNotEmpty(map.get("realName"))){
						//吉大医院现场文件服务器
						sb.append(" <td  style='border:1px solid black; ' align='left'  width='120px' ><img src='http://172.16.11.60/upload/hrp/file/conload/"+map.get("realName").toString()+".png' width='70px' style='z-index: -1; position: absolute;margin-top:-5px; margin-left:30px '>&nbsp;</td></tr> <tr colspan='6' > ");
						//本地图片路径
						//有名字有签名
						//sb.append(" <td  style='border:1px solid black; ' align='left'  width='120px' ><img src='F:/lwzk/soft/conload/"+map.get("realName").toString()+".png' width='60px' style='z-index: -1; position: absolute;margin-top:-5px; margin-left:40px '>"+map.get("realName").toString()+"</td></tr> <tr colspan='6' > ");
						//有签名没有名字
						//sb.append(" <td  style='border:1px solid black; ' align='left'  width='120px' ><img src='F:/lwzk/soft/conload/"+map.get("realName").toString()+".png' width='80px' style='z-index: -1; position: absolute;margin-top:-5px; margin-left:30px '>&nbsp;</td></tr> <tr colspan='6' > ");
						
					}else{  
						sb.append(" <td  style='border:1px solid black; '  width='120px'>"+" "+"</td></tr> <tr colspan='6' > ");
					}
					if(StringUtil.isNotEmpty(map.get("time"))){
					sb.append(" <td style='border:1px solid black; 'align='center' width='120px'>"+sdf.format(map.get("time"))+"</td>");
					}else{
						if(StringUtil.isNotEmpty(lx.get(map.get("assign")))){
						sb.append(" <td style='border:1px solid black; ' align='center' width='120px'>"+sdf.format(lx.get(map.get("assign")))+"</td>");
						}else{
							sb.append(" <td style='border:1px solid black; '  width='120px'>&nbsp;&nbsp;&nbsp;&nbsp;</td>");
						}
					}
					sb.append("  </tr>");
					sb.append("</thead>");
				}
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		ja.setMsg(sb.toString());
		return ja;	   
			  
	}
	
	
	
	
	/**
	 * 打开单独的附件页面
	 * 
	 * @return
	 */
@RequestMapping(params = "fileEdit")
public ModelAndView fileEdit(FileDictEntity fileDict, HttpServletRequest req) {

	//===================================================================================
	//获取参数 
	String tableName = req.getParameter("tableName");
	String tableId = req.getParameter("tableId");
	String isEdit = "0";
	String modName = req.getParameter("modName");
	String confermState=req.getParameter("confermState");
	if("新建".equals(confermState)){
		isEdit="1";
	}if("退回".equals(confermState)){
		isEdit="1";
	}
	//===================================================================================
	//查询-附件
   String hql0 = "from FileDictEntity where 1 = 1 AND  tableName ='"+tableName+"' AND tableId = ? ";
   try{
   	List<FileDictEntity> FileDictEntityList = systemService.findHql(hql0,tableId);
   		String realPath = "http://"+FtpUtil.getFtpUrl()+"/upload/";
	   	if(!FtpUtil.isStart()){
    		realPath ="";
    	}
		req.setAttribute("fileDictList", FileDictEntityList);
		req.setAttribute("tableName", tableName);
		req.setAttribute("tableId", tableId);
		req.setAttribute("isEdit", isEdit);
		req.setAttribute("modName", modName);
		req.setAttribute("realPath", realPath);
		req.setAttribute("isShow", "1");
		req.setAttribute("sessionId",ContextHolderUtils.getSession().getId());
	}catch(Exception e){
		logger.info(e.getMessage());
	} 
	return new ModelAndView("cn/com/linkwide/common/fileUpload/fileEdit");
}
	
/**
 * 合同信息审批流页面跳转
 * 
 * @return
 */
@RequestMapping(params = "goUpdateconfirm")
public ModelAndView goUpdateconfirm(ConInformationEntity conInformation, HttpServletRequest req) {
	if (StringUtil.isNotEmpty(conInformation.getId())) {
		
		conInformation = conInformationService.getEntity(ConInformationEntity.class, conInformation.getId());
		if("".equals(conInformation.getConNumnerZ())||null==conInformation.getConNumnerZ()||"0".equals(conInformation.getConNumnerZ())){
			conInformation.setRenewId(conInformation.getId());
		}
		String userCode=req.getParameter("auditUser");
		req.setAttribute("userCode",userCode );
		req.setAttribute("conInformationPage", conInformation);
	}
	return new ModelAndView("cn/com/linkwide/cont/coninformation/conInformation-updateconfirm");
}
/**
 * 合同信息审批流页面跳转
 * 
 * @return
 */
@RequestMapping(params = "conSelectExpot")
public ModelAndView conSelectExpot(ConInformationEntity conInformation, HttpServletRequest req) {
	if (StringUtil.isNotEmpty(conInformation.getId())) {
		
		conInformation = conInformationService.getEntity(ConInformationEntity.class, conInformation.getId());
		if("".equals(conInformation.getConNumnerZ())||null==conInformation.getConNumnerZ()||"0".equals(conInformation.getConNumnerZ())){
			conInformation.setRenewId(conInformation.getId());
		}
		req.setAttribute("conInformationPage", conInformation);
	}
	req.setAttribute("conType", req.getParameter("conType"));//合同导出模板 中  的 模板所属类别 
	return new ModelAndView("cn/com/linkwide/cont/coninformation/confirmdcdome");
}


/**
 * 供应商分页参照模糊查询
 * 
 * @author zhuhd 2018-3-27
 * @param request
 * @return
 */
@RequestMapping(params = "templateExport")
@ResponseBody
public void templateExport(TemplateExportEntity depart,HttpServletRequest request,DataGrid dataGrid, HttpServletResponse response) {
	String q = request.getParameter("q") != null ? request.getParameter("q") : "";
	CriteriaQuery cq = new CriteriaQuery(TemplateExportEntity.class, dataGrid);
	String defaultVal = request.getParameter("defaultVal") != null ? request.getParameter("defaultVal") : "";
	// 查询条件组装器
	org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, depart);
	try {
		
		// 自定义追加查询条件  
		Conjunction conjunction = Restrictions.conjunction();
		//合同导出模板 ：模板所属类别过滤
		conjunction.add(Restrictions.eq("templateExtent1", request.getParameter("conType"))); 		
		//根据 模板编码和模板名称模糊查询
		Disjunction disjunction = Restrictions.disjunction();
		disjunction.add(Restrictions.like("templateCode", q,MatchMode.ANYWHERE).ignoreCase());
		disjunction.add(Restrictions.like("templateName", q,MatchMode.ANYWHERE).ignoreCase());
		conjunction.add(disjunction);
		cq.add(conjunction); 
		//供应商编码不能为空
	/*	Conjunction criteria =  Restrictions.conjunction();
		  String dogs[] = {"01","02","03","04","05","06","07","22","29"};
		criteria.add(Restrictions.in("templateCode", dogs));
		cq.add(criteria);*/ 
		cq.add();
		this.systemService.getDataGridReturn(cq, true); 
		String[]  defArray = defaultVal.split(",");
		if(defArray.length>1 && StringUtil.isNotEmpty(defArray[1]) ){
			TemplateExportEntity d = systemService.findUniqueByProperty(TemplateExportEntity.class, defArray[0], defArray[1]);
			List<TemplateExportEntity> results = dataGrid.getResults();
			if( results ==null) return;
			if(d !=null  ){
				for(TemplateExportEntity dept : results){
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
 * 添加物流投标保证金
 * 
 * @param ids
 * @return
 */
@RequestMapping(params = "chekFiled")
@ResponseBody
public AjaxJson chekFiled(FileDictEntity fileDict, HttpServletRequest req) {
	String message = "请先上传合同附件再提交";
	AjaxJson j = new AjaxJson();
	
	try{

		//===================================================================================
		//获取参数 
		String tableName = req.getParameter("tableName");
		String tableId = req.getParameter("tableId");
		//===================================================================================
		//查询-附件
	   String hql0 = "from FileDictEntity where 1 = 1 AND  tableName ='"+tableName+"' AND tableId = ? ";
	 	List<FileDictEntity> FileDictEntityList = systemService.findHql(hql0,tableId);
	 	if(FileDictEntityList==null  || FileDictEntityList.size() ==0 ){
	 		message="a";
	 	}
		systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
	}catch(Exception e){
		e.printStackTrace();
		message = "请先上传合同附件再提交";
		j.setMsg(message);
		return j;
	}
	j.setMsg(message);
	return j;
}


/**
	 * 批量操作合同信息
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doCeHui")
	@ResponseBody
	public AjaxJson doCeHui(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		String message = "";
			message = "合同撤回成功！";
		
		try{
			for(String id:ids.split(",")){
				ConInformationEntity conInformation = systemService.getEntity(ConInformationEntity.class,id); 
				if(!"待审核".equals(conInformation.getConfermState())){
					message="不是已提交的数据不能撤回";
					j.setMsg(message);
					return j;
				}
				Map map=new HashMap();
				map.put("busId", conInformation.getId());
				map.put("workflowId", conInformation.getWorkflowId());
				String returnMsg =workFlowService.revokeWorkflowProcess(map);
				if(StringUtil.isEmpty(returnMsg)){
					conInformation.setConfermState("新建");
					conInformation.setConfermDate(null);
					conInformationService.saveOrUpdate(conInformation);
				}else{
					j.setMsg(returnMsg);
					return j;
				}
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
				message = "合同撤回失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	 
	 @RequestMapping(params = "chekCreateBy")
	 @ResponseBody
	 public AjaxJson chekCreateBy( ConInformationEntity conInformation, HttpServletRequest req) {
	 	String message = "";
	 	AjaxJson j = new AjaxJson();
	 	
	 	try{
	 		String id = req.getParameter("id");
	 		if(StringUtil.isNotEmpty(id)){
	 		conInformation = conInformationService.getEntity(ConInformationEntity.class, id);
	 		message=conInformation.getCreateBy();
	 		}
	 	}catch(Exception e){
	 		e.printStackTrace();
	 		message = "";
	 		j.setMsg(message);
	 		return j;
	 	}
	 	j.setMsg(message);
	 	return j;
	 }
	 
	 /**
		 * 加载明细列表[合同标的]
		 * 
		 * @return
		 */
		@RequestMapping(params = "conMarkConfirmList")
		public ModelAndView conMarkConfirmList(ConInformationEntity conInformation, HttpServletRequest req) {
		
			//===================================================================================
			//获取参数
			Object id0 = conInformation.getId();
			//===================================================================================
			//查询-合同标的
		    String hql0 = "from ConMarkEntity where 1 = 1 AND cON_MAIN_ID = ? ";
		    try{
		    	List<ConMarkEntity> conMarkEntityList = systemService.findHql(hql0,id0);
				req.setAttribute("conMarkList", conMarkEntityList);
			}catch(Exception e){
				logger.info(e.getMessage());
			}
			return new ModelAndView("cn/com/linkwide/cont/coninformationdetial/coninformationconfirmdetial/conMarkConfirmList");
		}
		/**
		 * 加载明细列表[合同付款计划]
		 * 
		 * @return
		 */
		@RequestMapping(params = "payPlanConfirmList")
		public ModelAndView payPlanConfirmList(ConInformationEntity conInformation, HttpServletRequest req) {
		
			//===================================================================================
			//获取参数
			Object id0 = conInformation.getId();
			//===================================================================================
			//查询-合同付款计划
			   String hql0 = "from PayPlanEntity where 1 = 1 AND cON_ID = ? ";
			    try{
			    	List<PayPlanEntity> payPlanEntityList = systemService.findHql(hql0,id0);
					req.setAttribute("payPlanList", payPlanEntityList);
				}catch(Exception e){
					logger.info(e.getMessage());
				}
			return new ModelAndView("cn/com/linkwide/cont/coninformationdetial/coninformationconfirmdetial/payPlanConfirmList");
		}
		/**
		 * 加载明细列表[合同付款大事记]
		 * 
		 * @return
		 */
		@RequestMapping(params = "conMemorabiliaConfirmList")
		public ModelAndView conMemorabiliaConfirmList(ConInformationEntity conInformation, HttpServletRequest req) {
		
			//===================================================================================
			//获取参数
			Object id0 = conInformation.getId();
			//===================================================================================
			//查询-合同标的
		    String hql0 = "from ConMemorabiliaEntity where 1 = 1 AND cON_ID = ? ";
		    try{
		    	List<ConMemorabiliaEntity> conMemorabiliaEntityList = systemService.findHql(hql0,id0);
				req.setAttribute("conMemorabiliaList", conMemorabiliaEntityList);
			}catch(Exception e){
				logger.info(e.getMessage());
			}
			return new ModelAndView("cn/com/linkwide/cont/coninformationdetial/coninformationconfirmdetial/conMemorabiliaConfirmList");
		}
		/**
		 * 加载明细列表[银行保函]
		 * 
		 * @return
		 */
		@RequestMapping(params = "contBankConfirmList")
		public ModelAndView contBankConfirmList(ConInformationEntity conInformation, HttpServletRequest req) {
		
			//===================================================================================
			//获取参数
			Object id0 = conInformation.getId();
			//===================================================================================
			//查询-合同标的
		    String hql0 = "from ContBankEntity where 1 = 1 AND cON_ID = ? ";
		    try{
		    	List<ContBankEntity> contBankEntityList = systemService.findHql(hql0,id0);
				req.setAttribute("contBankList", contBankEntityList);
			}catch(Exception e){
				logger.info(e.getMessage());
			}
			return new ModelAndView("cn/com/linkwide/cont/coninformationdetial/coninformationconfirmdetial/contBankConfirmList");
		}
		
	 
		
		 /**
		 * 加载明细列表[附件]
		 * 
		 * @return
		 */
	@RequestMapping(params = "fileDictList")
	public ModelAndView fileDictList( FileDictEntity fileDict, HttpServletRequest req) {

		//===================================================================================
		//获取参数 
		String tableName = req.getParameter("tableName");
		String tableId = req.getParameter("tableId");
		String isEdit = req.getParameter("isEdit");
		String modName = req.getParameter("modName");
		String userCode=req.getParameter("userCode");
		//===================================================================================
		//查询-附件
	    String hql0 = "from  FileDictEntity where 1 = 1 AND  tableName ='"+tableName+"' AND tableId = ? ";
	    try{
	    	List<FileDictEntity> FileDictEntityList = systemService.findHql(hql0,tableId);
	    	String realPath = "http://"+FtpUtil.getFtpUrl()+"/upload/";
	    	if(!FtpUtil.isStart()){
	    		realPath ="";
	    	}
			req.setAttribute("fileDictList", FileDictEntityList);
			req.setAttribute("tableName", tableName);
			req.setAttribute("tableId", tableId);
			if("LYLS01".equals(userCode)||"Q0200".equals(userCode)){
				isEdit="1";
			}
			req.setAttribute("isEdit", isEdit);
			req.setAttribute("modName", modName);
			req.setAttribute("realPath", realPath);
			req.setAttribute("isShow", "1");
			req.setAttribute("sessionId",ContextHolderUtils.getSession().getId());
		}catch(Exception e){
			logger.info(e.getMessage());
		} 
	    if("LYLS01".equals(userCode)||"Q0200".equals(userCode)){
	    	return new ModelAndView("cn/com/linkwide/cont/coninformation/confileileTablePage");
	    }else{
	    
		return new ModelAndView("cn/com/linkwide/common/fileUpload/fileileTablePage");
	    }
	}

	/**
	 * 收入预算审批
	 * @param budgDeptMainIncom
	 * @param request
	 * @param response
	 * @param dataGrid
	 */
	@RequestMapping(params = "auditDatagrid")
	public void auditDatagrid(ConInformationEntity conInformation ,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		//单据状态
		String deptIdQu=request.getParameter("deptIdQu");
		String budgState =conInformation.getConfermState();
		if(StringUtil.isNotEmpty(budgState)){
			conInformation.setConfermState("*"+budgState+"*");
		}
		CriteriaQuery cq = new CriteriaQuery(ConInformationEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, conInformation);
		String ids = null; //需要审批的业务单据的id字符串
		String hisIds = null; //已审批过的业务单据id字符串
		TSUser currentUser = ResourceUtil.getSessionUserName(); //获取当前登录用户
		try{
			//自定义追加查询条件
			//cq.eq("budgState", BILL_STATE.TO_BE_CHECK); //查询待审批状态的单据
			//cq.eq("conType", "04");
			cq.add(Restrictions.in("confermState", new Object[]{BILL_STATE.TO_BE_CHECK,BILL_STATE.CHECKED,BILL_STATE.REJECT}));
		if(StringUtil.isEmpty(deptIdQu)){
			String stringArray[]={"40288186658420d501658424b08d0183","40288186658420d5016584248de500d2","40288186658420d501658424cb7d0205","40288186658420d5016584247095004e","40288186658420d50165842471ed0055","40288186658420d501658424933600ea","40288186658420d501658424b241018a","40288186658420d501658424a11e013b"};
			Conjunction criteria =  Restrictions.conjunction();
			criteria.add(Restrictions.in("deptId",stringArray));
			cq.add(criteria);
		}if(StringUtil.isNotEmpty(deptIdQu)){
			cq.eq("deptId", deptIdQu);
		}
			if("admin".equals(currentUser.getUserName())||"jdyy".equals(currentUser.getUserName())){
				cq.add();
				this.conInformationService.getDataGridReturn(cq, true);
				TagUtil.datagrid(response, dataGrid);
				return;
			}
			//查询需要当前用户审批的待办任务
			List<Task> todoList = workFlowQueryService.queryTaskByUserId(currentUser.getUserName());
	        String taskIds = null; //所有待办任务的任务id字符串
	        if(todoList != null && todoList.size()>0){
	        	for (Task task : todoList) {
	        		taskIds=taskIds==null?"'"+task.getId()+"'":taskIds+",'"+task.getId()+"'";
	        	}
	        }
			if(StringUtil.isNotEmpty(taskIds)){
				//根据待办任务id查询需要审批的业务单据id
				String sql = " SELECT a.BUSINESS_KEY_  \"bus\" from act_ru_execution a left join "
						+ " act_ru_task b on a.PROC_DEF_ID_=b.PROC_DEF_ID_ and a.PROC_INST_ID_=b.PROC_INST_ID_ "
						+ " where b.ID_ in("+taskIds+")";
				List<Map<String,Object>> list = systemService.findForJdbc(sql, new Object[]{});
				if(list != null && list.size()>0){
					for (Map<String, Object> map : list) {
						String busStr = map.get("bus")==null?null:map.get("bus").toString();
						if(StringUtil.isNotEmpty(busStr)){
							String [] businessKeyArr = busStr.split("\\.");
							String busId=businessKeyArr[businessKeyArr.length-1];
							ids=ids==null?""+busId+"":ids+","+busId+"";
						}
					}
				}
			} 
			//查询当前用户已经审批过的单据
			String sql = "SELECT DISTINCT RES.*,C.BUSINESS_KEY_  \"bus\", VAR.ID_ AS VAR_ID_ FROM ACT_HI_TASKINST RES "
					+ " LEFT OUTER JOIN ACT_HI_VARINST VAR ON RES.PROC_INST_ID_ = VAR.EXECUTION_ID_ AND VAR.TASK_ID_ IS NULL "
					+ " left join ACT_HI_PROCINST c on RES.PROC_INST_ID_  =c.PROC_INST_ID_ and RES.PROC_DEF_ID_=C.PROC_DEF_ID_"
					+ " WHERE RES.ASSIGNEE_ = '"+currentUser.getUserName()+"' "
				    + " AND RES.END_TIME_ IS NOT NULL";
			List<Map<String,Object>> hisList =systemService.findForJdbc(sql, new Object[]{});
			if(hisList!= null && hisList.size()>0){
				for (Map<String, Object> map : hisList) {
					String busStr = map.get("bus")==null?null:map.get("bus").toString();
					String [] businessKeyArr = busStr.split("\\.");
					String busId=businessKeyArr[businessKeyArr.length-1];
					hisIds=hisIds==null?""+busId+"":hisIds+","+busId+"";
				}
			}
			if(StringUtil.isNotEmpty(hisIds)){
				ids=ids==null?hisIds:ids+","+hisIds;
			}
				//待审批单据，已审批单据，审批完成单据
				cq.add((Restrictions.in("id", ids.split(","))));
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.conInformationService.getDataGridReturn(cq, true);
		if(StringUtil.isNotEmpty(hisIds)){
			if(StringUtil.isNotEmpty(ids)){
				ids =ids.replace(hisIds, "");
			}
 List<ConInformationEntity> dataList = dataGrid.getResults();
 /*
			//根据当前用户待审批的主键id字符串  判断是否有权限审批待审批的单据
			if(dataList !=null && dataList.size()>0){
				for (ConInformationEntity in : dataList) {
					String id = in.getId();
					if(hisIds.indexOf(id)>-1){ //没有权限审批待审批的单子
						if(StringUtil.isNotEmpty(ids) && ids.indexOf(id)==-1){
							in.setExtend1("N");
						}
					}
				}
			}*/
			dataGrid.setResults(dataList);
		}
		TagUtil.datagrid(response, dataGrid);
	}
	
	
	
	
	/**
	 * 物资卡片分页参照模糊查询
	 * 
	 * @author zhuhd 2018-3-27
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "departEqueList")
	@ResponseBody
	public void departMateList(LBaMaterialEntity depart,HttpServletRequest request,DataGrid dataGrid, HttpServletResponse response) {
		String q = request.getParameter("q") != null ? request.getParameter("q") : "";
		String defaultVal = request.getParameter("defaultVal") != null ? request.getParameter("defaultVal") : "";
		try {
			// 自定义追加查询条件 
			StringBuffer sql = new StringBuffer();
			sql.append("FROM ppe_cards m  left  join t_s_depart t on m.administration_depart =t.org_code  where 1=1  ");
			if(request.getParameter("q") != null){
				sql.append(" and ( m.card_number like '"+q+"%' or m.asset_coding like '"+q+"%' or m.asset_name like '%"+q+"%' ) ");
			}
			String filed ="select m.id id, m.asset_coding assteCoding,m.card_number cardNumber,m.asset_name assetName,m.spec_model specModel,m.service_life_month serviceLifeMonth,m.quantum quantum,"
						  +"m.original_value originalValue,m.serial_number serialNumber,t.id Tid,t.departname departName ";
			List<Map<String,Object>> results = systemService.findForJdbc(filed+sql.toString(), dataGrid.getPage(), dataGrid.getRows());
			//设置默认值
			if(StringUtil.isNotEmpty(defaultVal) && StringUtil.isEmpty(q)){
				Map<String,Map<String,Object>> mMap = systemService.list2Map(results, "id");
				String[] def = defaultVal.split(",");
				
				if(def.length>1 && !mMap.containsKey(def[1])){
					if("cardNumber".equals(def[0])){
						def[0]="card_number";
					}
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
			String stringArray[]={"40288186658420d501658424b08d0183","40288186658420d5016584248de500d2","40288186658420d501658424cb7d0205","40288186658420d5016584247095004e","40288186658420d50165842471ed0055","40288186658420d501658424933600ea","40288186658420d501658424b241018a","40288186658420d501658424a11e013b"};
			Conjunction criteria =  Restrictions.conjunction();
			criteria.add(Restrictions.in("id",stringArray));
			cq.add(criteria);
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
	 * 参照中标单页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "conSelectWinn")
	public ModelAndView conSelectWinn(ConInformationEntity conInformation, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(conInformation.getId())) {
			
			conInformation = conInformationService.getEntity(ConInformationEntity.class, conInformation.getId());
			if("".equals(conInformation.getConNumnerZ())||null==conInformation.getConNumnerZ()||"0".equals(conInformation.getConNumnerZ())){
				conInformation.setRenewId(conInformation.getId());
			}
			req.setAttribute("conInformationPage", conInformation);
		}
		req.setAttribute("conType", req.getParameter("conType"));//合同导出模板 中  的 模板所属类别 
		return new ModelAndView("cn/com/linkwide/cont/coninformation/confirmSeleWinn");
	}
	
	
	

}
