package cn.com.linkwide.cont.coninformation.controller;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.criterion.Conjunction;
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
import org.jeecgframework.jwt.util.ResponseMessage;
import org.jeecgframework.jwt.util.Result;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.tag.vo.datatable.SortDirection;
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

import cn.com.linkwide.ba.material.record.entity.LBaMaterialEntity;
import cn.com.linkwide.common.tools.BillCodeUtil;
import cn.com.linkwide.cont.condemage.entity.ConDemageEntity;
import cn.com.linkwide.cont.coninformation.entity.ConInformationEntity;
import cn.com.linkwide.cont.coninformation.page.ConInformationPage;
import cn.com.linkwide.cont.coninformation.service.ConInformationServiceI;
import cn.com.linkwide.cont.coninformationdetial.entity.ConMarkEntity;
import cn.com.linkwide.cont.conmemorabilia.entity.ConMemorabiliaEntity;
import cn.com.linkwide.cont.contbank.entity.ContBankEntity;
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
@Api(value="ConInformation",description="收款合同信息",tags="ConInformationFrameController")
@Controller
@RequestMapping("/conInformationFrameController")
public class ConInformationFrameController extends BaseController {
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

	/**
	 * 合同信息列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("cn/com/linkwide/cont/framecont/coninformation/conInformationList");
	}
	
	/**
	 * 合同变更列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "listbg")
	public ModelAndView listbg(HttpServletRequest request) {
		return new ModelAndView("cn/com/linkwide/cont/framecont/coninformation/coninformationexchange");
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
		if(StringUtil.isNotEmpty(query_signedDate_begin)){
			cq.ge("signedDate", new SimpleDateFormat("yyyy-MM-dd").parse(query_signedDate_begin));
		}
		if(StringUtil.isNotEmpty(query_signedDate_end)){
			cq.le("signedDate", new SimpleDateFormat("yyyy-MM-dd").parse(query_signedDate_end));
		}
		if(StringUtil.isNotEmpty(con_type)){
			cq.eq("conType", con_type);
		}
		cq.addOrder("conNumber", SortDirection.desc);
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
			String conNo=request.getParameter("conNo");
			String conName=request.getParameter("conName");
			if(StringUtil.isNotEmpty(conNo)){
				cq.add(Restrictions.like("conNo", conNo,MatchMode.ANYWHERE));
			}
			if(StringUtil.isNotEmpty(conName)){
				cq.add(Restrictions.like("conName", conName,MatchMode.ANYWHERE));
			}
		String con_type=request.getParameter("con_type");
		String isExchange=request.getParameter("isExchange");
		if(StringUtil.isNotEmpty(con_type)){
			cq.eq("conType", con_type);
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
			TSFunction function = this.conInformationService.findUniqueByProperty(TSFunction.class, "functionUrl", "conInformationFrameController.do?list");
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
			return new ModelAndView("cn/com/linkwide/cont/framecont/coninformation/conwyList");
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
			return new ModelAndView("cn/com/linkwide/cont/framecont/coninformation/conInformation-alteration");
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
			return new ModelAndView("cn/com/linkwide/cont/framecont/coninformation/conInformation-alteration");
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
			return new ModelAndView("cn/com/linkwide/cont/framecont/coninformation/conInformation-alteration-examine");
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
			return new ModelAndView("cn/com/linkwide/cont/framecont/coninformation/conInformation-alteration-update");
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
					if("2".equals(conInformation.getConfermState()) && w==1){
						
						message = "已审核的合同不能复审！";
						j.setMsg(message);
						return j;
					}
					if(!"2".equals(conInformation.getConfermState()) && w==2){
						
						message = "不是已审核的合同不能弃审！";
						j.setMsg(message);
						return j;
					}
					if(!"10".equals(conInformation.getConState()) && w==2){
						
						message = "不是起草状态的合同不能弃审！";
						j.setMsg(message);
						return j;
					}
					if(!"2".equals(conInformation.getConfermState()) && w==3){
						
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
						conInformation.setConState(state);
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
					if(w==3){
						conInformation.setConState(state);
					}
					if(w==4){
						conInformation.setQxZz(conInformation.getConState());
						conInformation.setConState(state);
						conInformation.setStateDate5(new Date(System.currentTimeMillis()));
					}
					if(w==5){
						conInformation.setConState(state);
						conInformation.setStateDate5(new Date(System.currentTimeMillis()));
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
				/*List<PayPlanEntity> payPlanList =  conInformationPage.getPayPlanList();
				List<ConMemorabiliaEntity> conMemorabiliaList =  conInformationPage.getConMemorabiliaList();
				List<ContBankEntity> contBankList =  conInformationPage.getContBankList();*/
				AjaxJson j = new AjaxJson();
		  
		      String message = "合同变更成功";
		    	 String sql="update con_information set con_type='"+conInformationPage.getConExec4()+"' where id='"+conInformationPage.getConExec5()+"'";
		    	 try {
					conInformationService.updateBySqlString(sql);
				} catch (Exception e) {
					e.printStackTrace();
				}
		    	
		
				try{
					conInformation.setStateDate4(new Date(System.currentTimeMillis()));
					conInformation.setIsBg("1");
					conInformation.setIsDefout("0");
					conInformationService.addMain(conInformation, conMarkList);
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
		/*List<PayPlanEntity> payPlanList =  conInformationPage.getPayPlanList();
		List<ConMemorabiliaEntity> conMemorabiliaList =  conInformationPage.getConMemorabiliaList();
		List<ContBankEntity> contBankList =  conInformationPage.getContBankList();*/
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
		
			conInformation.setIsDefout("0");
			if(!"1".equals(flag)){
			conInformation.setConNumnerZ("0");
			conInformation.setIsBg("0");
			}
			conInformation.setConNumber(generateBillCode());
			conInformationService.addMain(conInformation, conMarkList);
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
		/*List<PayPlanEntity> payPlanList =  conInformationPage.getPayPlanList();
		List<ConMemorabiliaEntity> conMemorabiliaList =  conInformationPage.getConMemorabiliaList();
		List<ContBankEntity> contBankList =  conInformationPage.getContBankList();*/
		AjaxJson j = new AjaxJson();
		String message = "更新成功";
		try{
			conInformationService.updateMain(conInformation, conMarkList);
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
		return new ModelAndView("cn/com/linkwide/cont/framecont/coninformation/conInformation-add");
	}
	
	/**
	 * 合同信息编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(ConInformationEntity conInformation, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(conInformation.getId())) {
			
			if("".equals(conInformation.getConNumnerZ())||null==conInformation.getConNumnerZ()||"0".equals(conInformation.getConNumnerZ())){
				conInformation.setRenewId(conInformation.getId());
			}
			
			conInformation = conInformationService.getEntity(ConInformationEntity.class, conInformation.getId());
			req.setAttribute("conInformationPage", conInformation);
		}
		return new ModelAndView("cn/com/linkwide/cont/framecont/coninformation/conInformation-update");
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
			//	conInformation.setRenewId(conInformation.getId());
			
			}else{
				Integer a=Integer.parseInt(conInformation.getConNumnerZ());
				String b =String.valueOf(a+1);
				req.setAttribute("conNumnerZ", b);
			
			}
			
			conInformation.setStartDate(new Date(System.currentTimeMillis()));
			req.setAttribute("conInformationPage", conInformation);
		}
		return new ModelAndView("cn/com/linkwide/cont/framecont/coninformation/conInformationRenew");
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
		return new ModelAndView("cn/com/linkwide/cont/framecont/coninformationdetial/conMarkList");
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
		return new ModelAndView("cn/com/linkwide/cont/framecont/coninformationdetial/payPlanList");
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
		return new ModelAndView("cn/com/linkwide/cont/framecont/coninformationdetial/conMemorabiliaList");
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
		return new ModelAndView("cn/com/linkwide/cont/framecont/coninformationdetial/contBankList");
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
            		pageList.add(page);
            	}catch(Exception e){
            		logger.info(e.getMessage());
            	}
            }
        }
        map.put(NormalExcelConstants.FILE_NAME,"合同信息");
        map.put(NormalExcelConstants.CLASS,ConInformationPage.class);
        map.put(NormalExcelConstants.PARAMS,new ExportParams("合同信息列表", "导出人:Jeecg",
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
			/*if("1".equals(map.get("option_parameter"))){
				sql.append(" and m.id in (select mate_id from  l_ba_depart_mate d where   d.depart_id='"+departId+"' )   ");
			}*/
			String filed ="select m.id as id ,m.material_code as materialCode,m.material_name as materialName ,m.monm_code as monmCode,m.spec_model as specModel ,t.type_name as mateType,m.mater_unit_id as unitId,u.type_name as unit,u.type_code as typeCode"
					+ ",m.unit_price as unitPrice ,s.supplier_full_name as supplierName,m.supplier_id as supplierId,h.warehouse_name  as warehouseName ,m.warehouse_id as warehouseId,m.is_batch as isBatch,m.is_shelf_life as isShelfLife,m.is_bar_code as isBarCode ";
			List<Map<String,Object>> results = systemService.findForJdbc(filed+sql.toString(), dataGrid.getPage(), dataGrid.getRows());
			//设置默认值
			if(StringUtil.isNotEmpty(defaultVal) && StringUtil.isEmpty(q)){
				Map<String,Map<String,Object>> mMap = systemService.list2Map(results, "id");
				String[] def = defaultVal.split(",");
				if(!mMap.containsKey(def[1])){
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
}
