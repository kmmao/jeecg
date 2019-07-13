package cn.com.linkwide.ba.setaccountperiod.controller;
import java.io.IOException;
import java.net.URI;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import cn.com.linkwide.ba.setaccountperiod.entity.BaSetAccountPeriodEntity;
import cn.com.linkwide.ba.setaccountperiod.service.BaSetAccountPeriodServiceI;
//import com.buss.logi.stock.monthlyperiod.entity.LStMonthlyPeriodEntity;

/**   
 * @Title: Controller  
 * @Description: 会计期间
 * @author onlineGenerator
 * @date 2018-01-17 10:57:51
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/baSetAccountPeriodController")
public class BaSetAccountPeriodController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(BaSetAccountPeriodController.class);

	@Autowired
	private BaSetAccountPeriodServiceI baSetAccountPeriodService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 会计期间列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("cn/com/linkwide/ba/setaccountperiod/baSetAccountPeriodList");
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
	public void datagrid(BaSetAccountPeriodEntity baSetAccountPeriod,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(BaSetAccountPeriodEntity.class, dataGrid);
		//查询条件组装器
		if(StringUtil.isNotEmpty(baSetAccountPeriod.getPeriodYear())){
			baSetAccountPeriod.setPeriodYear("*"+baSetAccountPeriod.getPeriodYear()+"*");
		}
		
		if(StringUtil.isNotEmpty(baSetAccountPeriod.getPeriodMonth())){
			baSetAccountPeriod.setPeriodMonth("*"+baSetAccountPeriod.getPeriodMonth()+"*");
		}
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, baSetAccountPeriod, request.getParameterMap());
		try{
		//自定义追加查询条件
			cq.addOrder("periodMonth", SortDirection.asc);
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.baSetAccountPeriodService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	@RequestMapping(params = "datagridForMonthlyPeriod")
	public void datagridForMonthlyPeriod(BaSetAccountPeriodEntity baSetAccountPeriod,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(BaSetAccountPeriodEntity.class, dataGrid);
		
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String dateS = simpleDateFormat.format(date);
		String year = dateS.split("-")[0];
		
		
		//查询条件组装器
		if(StringUtil.isNotEmpty(baSetAccountPeriod.getPeriodYear())){
			baSetAccountPeriod.setPeriodYear("*"+baSetAccountPeriod.getPeriodYear()+"*");
		}else{
			baSetAccountPeriod.setPeriodYear(year);
		}
		
		if(StringUtil.isNotEmpty(baSetAccountPeriod.getPeriodMonth())){
			baSetAccountPeriod.setPeriodMonth("*"+baSetAccountPeriod.getPeriodMonth()+"*");
		}
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, baSetAccountPeriod, request.getParameterMap());
		try{
		//自定义追加查询条件
			cq.addOrder("periodMonth", SortDirection.asc);
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.baSetAccountPeriodService.getDataGridReturn(cq, true);
		
		List<BaSetAccountPeriodEntity> lAccountPeriodEntities = dataGrid.getResults();
		for(BaSetAccountPeriodEntity baSetAccountPeriodEntity : lAccountPeriodEntities){
//			List<LStMonthlyPeriodEntity> lStMonthlyPeriodEntities = systemService.findByQueryString(" from LStMonthlyPeriodEntity where monthlyPeriod = '"+baSetAccountPeriodEntity.getPeriodMonth()+"'");
//			if(lStMonthlyPeriodEntities.size() > 0){
//				baSetAccountPeriodEntity.setMonthlyPeriodStatus("1");
//			}else{
//				baSetAccountPeriodEntity.setMonthlyPeriodStatus("0");
//			} 
		}
		
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除校验
	 * @param baSetAccountPeriod
	 * @return
	 */
	public AjaxJson valiDel(BaSetAccountPeriodEntity baSetAccountPeriod){
		AjaxJson j = new AjaxJson();
		
//		List<LStMonthlyPeriodEntity> l1 = systemService.findByQueryString(" from LStMonthlyPeriodEntity where monthlyPeriod = '"+baSetAccountPeriod.getPeriodMonth()+"'");
//		if(l1.size() > 0){
//			j.setMsg("已经月结，不能删除会计期间");
//			j.setSuccess(false);
//			return j;
//		}
		
		return j;
	}
	
	/**
	 * 删除会计期间
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(BaSetAccountPeriodEntity baSetAccountPeriod, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		baSetAccountPeriod = systemService.getEntity(BaSetAccountPeriodEntity.class, baSetAccountPeriod.getId());
		message = "会计期间删除成功";
		try{
			
			AjaxJson j1 = valiDel(baSetAccountPeriod);
			if (!j1.isSuccess()) {
				j1.setSuccess(true);
				return j1;
			}
			
			baSetAccountPeriodService.delete(baSetAccountPeriod);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "会计期间删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除会计期间
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "会计期间删除成功";
		try{
			for(String id:ids.split(",")){
				BaSetAccountPeriodEntity baSetAccountPeriod = systemService.getEntity(BaSetAccountPeriodEntity.class, 
				id
				);
				
				AjaxJson j1 = valiDel(baSetAccountPeriod);
				if (!j1.isSuccess()) {
					j1.setSuccess(true);
					return j1;
				}
				
				baSetAccountPeriodService.delete(baSetAccountPeriod);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "会计期间删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加会计期间
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAddForYear")
	@ResponseBody
	public AjaxJson doAddForYear(BaSetAccountPeriodEntity baSetAccountPeriod, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "会计期间添加成功";
		try{
			
			List<BaSetAccountPeriodEntity> lAccountPeriodEntities = systemService.findByQueryString(" from BaSetAccountPeriodEntity where periodYear = '"+baSetAccountPeriod.getPeriodYear()+"'");
			
			if(lAccountPeriodEntities.size() > 0){
				j.setSuccess(false);
				j.setMsg("选择年份的会计期间已存在");
				return j;
			}
			
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String year = baSetAccountPeriod.getPeriodYear();
			TSUser tsUser = ResourceUtil.getSessionUserName();
			for(int i=1;i<13;i++){
				
				BaSetAccountPeriodEntity baSetAccountPeriodEntity = new BaSetAccountPeriodEntity();
				Date beginDate = null;
				if(i<10){
					beginDate = simpleDateFormat.parse(year + "-0" + i + "-" + 1);
					baSetAccountPeriodEntity.setPeriodMonth(baSetAccountPeriod.getPeriodYear()+"-0"+i);
				}else{
					beginDate = simpleDateFormat.parse(year + "-" + i + "-" + 1);
					baSetAccountPeriodEntity.setPeriodMonth(baSetAccountPeriod.getPeriodYear()+"-"+i);
				}
				
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(beginDate);
				calendar.set(calendar.DAY_OF_MONTH, calendar.getActualMaximum(calendar.DAY_OF_MONTH));
				
				Date endDate = calendar.getTime();
				baSetAccountPeriodEntity.setPeriodYear(baSetAccountPeriod.getPeriodYear());
				baSetAccountPeriodEntity.setBeginDate(beginDate);
				baSetAccountPeriodEntity.setEndDate(endDate);
				baSetAccountPeriodEntity.setCreateBy(tsUser.getId());
				baSetAccountPeriodEntity.setCreateDate(new Date());
				
				baSetAccountPeriodService.save(baSetAccountPeriodEntity);
			}
			
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "会计期间添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 添加会计期间
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(BaSetAccountPeriodEntity baSetAccountPeriod, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "会计期间新增成功";
		try {
			List<BaSetAccountPeriodEntity> lAccountPeriodEntities = systemService.findByQueryString(" from BaSetAccountPeriodEntity where periodMonth = '"+baSetAccountPeriod.getPeriodMonth()+"'");
			
			if(lAccountPeriodEntities.size() > 0){
				j.setSuccess(false);
				j.setMsg("选择月份的会计期间已存在");
				return j;
			}
			
			TSUser tsUser = ResourceUtil.getSessionUserName();
			baSetAccountPeriod.setPeriodYear(baSetAccountPeriod.getPeriodMonth().split("-")[0]);
			baSetAccountPeriod.setCreateBy(tsUser.getId());
			baSetAccountPeriod.setCreateDate(new Date());
			baSetAccountPeriodService.save(baSetAccountPeriod);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "会计期间新增失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	
	/**
	 * 更新会计期间
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(BaSetAccountPeriodEntity baSetAccountPeriod, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "会计期间更新成功";
		BaSetAccountPeriodEntity t = baSetAccountPeriodService.get(BaSetAccountPeriodEntity.class, baSetAccountPeriod.getId());
		try {
			TSUser tsUser = ResourceUtil.getSessionUserName();
			//会计期间月
			String periodMonth = baSetAccountPeriod.getPeriodMonth().split("-")[1];
			//会计期间年
			String periodYear = baSetAccountPeriod.getPeriodYear();
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			//开始日期
			Date beginDate = baSetAccountPeriod.getBeginDate();
			String beginDateStr = sdf.format(beginDate);
			String beginDateArr[] =beginDateStr.split("-");
			//截止日期
			Date endDate = baSetAccountPeriod.getEndDate();
			String endDateStr = sdf.format(endDate);
			String endDateArr[] =endDateStr.split("-");
			//开始年月日
			String beginYear =beginDateArr[0];
//			String beignMonth = beginDateArr[1];
			//截止年月日
			String endYear =endDateArr[0];
//			String endMonth=endDateArr[1];
			//判断会计期间
			//判断年份
			if(!periodYear.equals(beginYear) || !periodYear.equals(endYear)){
				throw new Exception("当前会计期间年份为"+periodYear+"年");
			}
			
			//校验会计期间的查询条件
			String queryPeriodYear;
			String queryPeriodMonth;
			//01月份的会计期间需要与上一年12月份的会计期间判断是否衔接上
			if("01".equals(periodMonth)){
				queryPeriodYear = String.valueOf(Integer.valueOf(periodYear)-1);
				queryPeriodMonth = String.valueOf(queryPeriodYear+"-"+"12");
			}else{ //与当前年的前一个月的会计期间校验是否衔接
				queryPeriodYear = periodYear;
				String month = Integer.valueOf(periodMonth)-1<10?"0"+String.valueOf(Integer.valueOf(periodMonth)-1):String.valueOf(Integer.valueOf(periodMonth)-1);
				queryPeriodMonth = periodYear+"-"+month;
			}
			//判断日
			String hql ="from BaSetAccountPeriodEntity where periodYear=? and periodMonth=?";
			List<BaSetAccountPeriodEntity> list =  systemService.findHql(hql, new Object[]{queryPeriodYear,queryPeriodMonth});
			if(list != null &&list.size()>0){
				for (BaSetAccountPeriodEntity baSetAccountPeriodEntity : list) {
					//上一个会计期间的截止日期
					Date end = baSetAccountPeriodEntity.getEndDate();
					//相差天数  (开始日期与上一个会计期间的截止日期相差天数)
					int days = (int) ((beginDate.getTime() - end.getTime()) / (1000*3600*24));
					if(days!=1){
						message="该会计期间与上一个会计期间相差天数大于1 ";
						j.setMsg(message);
						return j;
//						throw new BusinessException("该会计期间与上一个会计期间相差天数大于1 ");
					}
				}
			}
			String hql2 = "from BaSetAccountPeriodEntity where periodYear=? and periodMonth>? order by periodMonth ";
			List<BaSetAccountPeriodEntity> list2 =  systemService.findHql(hql2, new Object[]{periodYear,baSetAccountPeriod.getPeriodMonth()});
			DateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
			if(list2 != null &&list2.size()>0){
				//当前跨级期间下个月的开始时间
				Date nextBeginDate = list2.get(0).getBeginDate();
//				Date nextEndDate = list2.get(0).getEndDate();
				String nextDayStr= dft.format(endDate).split("-")[2];
				
				int days = (int) ((nextBeginDate.getTime() - endDate.getTime()) / (1000*3600*24));
//				if(days>1){
				Date nextMonthBeginDate=endDate;
					for (BaSetAccountPeriodEntity entity : list2) {
			                Calendar cld = Calendar.getInstance();
			                cld.setTime(nextMonthBeginDate);
			                cld.add(Calendar.DATE, 1);
			                nextMonthBeginDate = cld.getTime();
			                entity.setBeginDate(nextMonthBeginDate);
			                String nextMonthendDate = dft.format(entity.getEndDate()).split("-")[0]+"-"+ dft.format(entity.getEndDate()).split("-")[1]+"-"+nextDayStr;
			                nextMonthBeginDate=dft.parse(nextMonthendDate);
			               entity.setEndDate(dft.parse(nextMonthendDate));
			               systemService.save(entity);
					}
//				}
				
			}
			baSetAccountPeriod.setUpdateBy(tsUser.getId());
			baSetAccountPeriod.setUpdateDate(new Date());
			MyBeanUtils.copyBeanNotNull2Bean(baSetAccountPeriod, t);
			baSetAccountPeriodService.saveOrUpdate(t);
			
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
//			message = "会计期间更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 会计期间新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(BaSetAccountPeriodEntity baSetAccountPeriod, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(baSetAccountPeriod.getId())) {
			baSetAccountPeriod = baSetAccountPeriodService.getEntity(BaSetAccountPeriodEntity.class, baSetAccountPeriod.getId());
			req.setAttribute("baSetAccountPeriodPage", baSetAccountPeriod);
		}
		return new ModelAndView("cn/com/linkwide/ba/setaccountperiod/baSetAccountPeriod-add");
	}
	
	/**
	 * 会计期间新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAddForYear")
	public ModelAndView goAddForYear(BaSetAccountPeriodEntity baSetAccountPeriod, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(baSetAccountPeriod.getId())) {
			baSetAccountPeriod = baSetAccountPeriodService.getEntity(BaSetAccountPeriodEntity.class, baSetAccountPeriod.getId());
			req.setAttribute("baSetAccountPeriodPage", baSetAccountPeriod);
		}
		return new ModelAndView("cn/com/linkwide/ba/setaccountperiod/baSetAccountPeriod-addForYear");
	}
	/**
	 * 会计期间编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(BaSetAccountPeriodEntity baSetAccountPeriod, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(baSetAccountPeriod.getId())) {
			baSetAccountPeriod = baSetAccountPeriodService.getEntity(BaSetAccountPeriodEntity.class, baSetAccountPeriod.getId());
			req.setAttribute("baSetAccountPeriodPage", baSetAccountPeriod);
		}
		return new ModelAndView("cn/com/linkwide/ba/setaccountperiod/baSetAccountPeriod-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","baSetAccountPeriodController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(BaSetAccountPeriodEntity baSetAccountPeriod,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(BaSetAccountPeriodEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, baSetAccountPeriod, request.getParameterMap());
		List<BaSetAccountPeriodEntity> baSetAccountPeriods = this.baSetAccountPeriodService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"会计期间");
		modelMap.put(NormalExcelConstants.CLASS,BaSetAccountPeriodEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("会计期间列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,baSetAccountPeriods);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(BaSetAccountPeriodEntity baSetAccountPeriod,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"会计期间");
    	modelMap.put(NormalExcelConstants.CLASS,BaSetAccountPeriodEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("会计期间列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
    	"导出信息"));
    	modelMap.put(NormalExcelConstants.DATA_LIST,new ArrayList());
    	return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	
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
			params.setHeadRows(1);
			params.setNeedSave(true);
			try {
				List<BaSetAccountPeriodEntity> listBaSetAccountPeriodEntitys = ExcelImportUtil.importExcel(file.getInputStream(),BaSetAccountPeriodEntity.class,params);
				for (BaSetAccountPeriodEntity baSetAccountPeriod : listBaSetAccountPeriodEntitys) {
					baSetAccountPeriodService.save(baSetAccountPeriod);
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
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<BaSetAccountPeriodEntity> list() {
		List<BaSetAccountPeriodEntity> listBaSetAccountPeriods=baSetAccountPeriodService.getList(BaSetAccountPeriodEntity.class);
		return listBaSetAccountPeriods;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		BaSetAccountPeriodEntity task = baSetAccountPeriodService.get(BaSetAccountPeriodEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody BaSetAccountPeriodEntity baSetAccountPeriod, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<BaSetAccountPeriodEntity>> failures = validator.validate(baSetAccountPeriod);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		try{
			baSetAccountPeriodService.save(baSetAccountPeriod);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = baSetAccountPeriod.getId();
		URI uri = uriBuilder.path("/rest/baSetAccountPeriodController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody BaSetAccountPeriodEntity baSetAccountPeriod) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<BaSetAccountPeriodEntity>> failures = validator.validate(baSetAccountPeriod);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		try{
			baSetAccountPeriodService.saveOrUpdate(baSetAccountPeriod);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		baSetAccountPeriodService.deleteEntityById(BaSetAccountPeriodEntity.class, id);
	}
	/**
	 * 获取当前日期所在会计期间
	 */
	@RequestMapping(params = "getCurrentPeriod")
	@ResponseBody
	public String getCurrentPeriod(HttpServletRequest request) {
		String currentPeriod = baSetAccountPeriodService.getPeriod(new Date());
		return currentPeriod;
	}
	/**
	 * 获取当前日期所在会计期间
	 */
	@RequestMapping(params = "getMinPeriod")
	@ResponseBody
	public String getMinPeriod(HttpServletRequest request) {
		String currentPeriod = baSetAccountPeriodService.getMinPeriod();
		return currentPeriod;
	}
}
