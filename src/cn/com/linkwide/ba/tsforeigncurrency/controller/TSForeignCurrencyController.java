package cn.com.linkwide.ba.tsforeigncurrency.controller;
import java.io.IOException;
import java.util.ArrayList;
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
import org.jeecgframework.core.common.model.json.ComboTree;
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

import cn.com.linkwide.ba.tscurrencyexch.entity.TSCurrencyExchEntity;
import cn.com.linkwide.ba.tsforeigncurrency.entity.TSForeignCurrencyEntity;
import cn.com.linkwide.ba.tsforeigncurrency.page.TSForeignCurrencyPage;
import cn.com.linkwide.ba.tsforeigncurrency.service.TSForeignCurrencyServiceI;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**   
 * @Title: Controller
 * @Description: 币种档案
 * @author onlineGenerator
 * @date 2018-07-12 16:57:06
 * @version V1.0   
 *
 */
@Api(value="TSForeignCurrency",description="币种档案",tags="tSForeignCurrencyController")
@Controller
@RequestMapping("/tSForeignCurrencyController")
public class TSForeignCurrencyController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(TSForeignCurrencyController.class);

	@Autowired
	private TSForeignCurrencyServiceI tSForeignCurrencyService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;

	/**
	 * 币种档案列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("cn/com/linkwide/ba/tsforeigncurrency/tSForeignCurrencyList");
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
	public void datagrid(TSForeignCurrencyEntity tSForeignCurrency,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TSForeignCurrencyEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, tSForeignCurrency);
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.tSForeignCurrencyService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除币种档案
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(TSForeignCurrencyEntity tSForeignCurrency, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		tSForeignCurrency = systemService.getEntity(TSForeignCurrencyEntity.class, tSForeignCurrency.getId());
		String message = "币种档案删除成功";
		try{
			tSForeignCurrencyService.delMain(tSForeignCurrency);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "币种档案删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 批量删除币种档案
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		String message = "币种档案删除成功";
		try{
			for(String id:ids.split(",")){
				TSForeignCurrencyEntity tSForeignCurrency = systemService.getEntity(TSForeignCurrencyEntity.class,
				id
				);
				tSForeignCurrencyService.delMain(tSForeignCurrency);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "币种档案删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 添加币种档案
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(TSForeignCurrencyEntity tSForeignCurrency,TSForeignCurrencyPage tSForeignCurrencyPage, HttpServletRequest request) {
		List<TSCurrencyExchEntity> tSCurrencyExchList =  tSForeignCurrencyPage.getTSCurrencyExchList();
		AjaxJson j = new AjaxJson();
		String message = "添加成功";
		try{
			tSForeignCurrencyService.addMain(tSForeignCurrency, tSCurrencyExchList);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "币种档案添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	/**
	 * 更新币种档案
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(TSForeignCurrencyEntity tSForeignCurrency,TSForeignCurrencyPage tSForeignCurrencyPage, HttpServletRequest request) {
		List<TSCurrencyExchEntity> tSCurrencyExchList =  tSForeignCurrencyPage.getTSCurrencyExchList();
		AjaxJson j = new AjaxJson();
		String message = "更新成功";
		try{
			tSForeignCurrencyService.updateMain(tSForeignCurrency, tSCurrencyExchList);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "更新币种档案失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 币种档案新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(TSForeignCurrencyEntity tSForeignCurrency, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(tSForeignCurrency.getId())) {
			tSForeignCurrency = tSForeignCurrencyService.getEntity(TSForeignCurrencyEntity.class, tSForeignCurrency.getId());
			req.setAttribute("tSForeignCurrencyPage", tSForeignCurrency);
		}
		String currencyId= req.getParameter("currencyId");
		req.setAttribute("currencyId", currencyId);
		return new ModelAndView("cn/com/linkwide/ba/tsforeigncurrency/tSForeignCurrency-add");
	}
	
	/**
	 * 币种档案编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(TSForeignCurrencyEntity tSForeignCurrency, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(tSForeignCurrency.getId())) {
			tSForeignCurrency = tSForeignCurrencyService.getEntity(TSForeignCurrencyEntity.class, tSForeignCurrency.getId());
			req.setAttribute("tSForeignCurrencyPage", tSForeignCurrency);
		}
		return new ModelAndView("cn/com/linkwide/ba/tsforeigncurrency/tSForeignCurrency-update");
	}
	
	
	/**
	 * 加载明细列表[币种汇率]
	 * 
	 * @return
	 */
	@RequestMapping(params = "tSCurrencyExchList")
	public ModelAndView tSCurrencyExchList(TSForeignCurrencyEntity tSForeignCurrency, HttpServletRequest req) {
	
		//===================================================================================
		//获取参数
		Object id0 = tSForeignCurrency.getId();
		//===================================================================================
		//查询-币种汇率
	    String hql0 = "from TSCurrencyExchEntity where 1 = 1 AND cURRENCY_ID = ? ";
	    try{
	    	List<TSCurrencyExchEntity> tSCurrencyExchEntityList = systemService.findHql(hql0,id0);
			req.setAttribute("tSCurrencyExchList", tSCurrencyExchEntityList);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return new ModelAndView("cn/com/linkwide/ba/tscurrencyexch/tSCurrencyExchList");
	}

    /**
    * 导出excel
    *
    * @param request
    * @param response
    */
    @RequestMapping(params = "exportXls")
    public String exportXls(TSForeignCurrencyEntity tSForeignCurrency,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid,ModelMap map) {
    	CriteriaQuery cq = new CriteriaQuery(TSForeignCurrencyEntity.class, dataGrid);
    	//查询条件组装器
    	org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, tSForeignCurrency);
    	try{
    	//自定义追加查询条件
    	}catch (Exception e) {
    		throw new BusinessException(e.getMessage());
    	}
    	cq.add();
    	List<TSForeignCurrencyEntity> list=this.tSForeignCurrencyService.getListByCriteriaQuery(cq, false);
    	List<TSForeignCurrencyPage> pageList=new ArrayList<TSForeignCurrencyPage>();
        if(list!=null&&list.size()>0){
        	for(TSForeignCurrencyEntity entity:list){
        		try{
        		TSForeignCurrencyPage page=new TSForeignCurrencyPage();
        		   MyBeanUtils.copyBeanNotNull2Bean(entity,page);
            	    Object id0 = entity.getId();
				    String hql0 = "from TSCurrencyExchEntity where 1 = 1 AND cURRENCY_ID = ? ";
        	        List<TSCurrencyExchEntity> tSCurrencyExchEntityList = systemService.findHql(hql0,id0);
            		page.setTSCurrencyExchList(tSCurrencyExchEntityList);
            		pageList.add(page);
            	}catch(Exception e){
            		logger.info(e.getMessage());
            	}
            }
        }
        map.put(NormalExcelConstants.FILE_NAME,"币种档案");
        map.put(NormalExcelConstants.CLASS,TSForeignCurrencyPage.class);
        map.put(NormalExcelConstants.PARAMS,new ExportParams("币种档案列表", "导出人:Jeecg",
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
				List<TSForeignCurrencyPage> list =  ExcelImportUtil.importExcel(file.getInputStream(), TSForeignCurrencyPage.class, params);
				TSForeignCurrencyEntity entity1=null;
				for (TSForeignCurrencyPage page : list) {
					entity1=new TSForeignCurrencyEntity();
					MyBeanUtils.copyBeanNotNull2Bean(page,entity1);
		            tSForeignCurrencyService.addMain(entity1, page.getTSCurrencyExchList());
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
		map.put(NormalExcelConstants.FILE_NAME,"币种档案");
		map.put(NormalExcelConstants.CLASS,TSForeignCurrencyPage.class);
		map.put(NormalExcelConstants.PARAMS,new ExportParams("币种档案列表", "导出人:"+ ResourceUtil.getSessionUser().getRealName(),
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
		req.setAttribute("controller_name", "tSForeignCurrencyController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}

 	
 	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="币种档案列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<TSForeignCurrencyPage>> list() {
		List<TSForeignCurrencyEntity> list= tSForeignCurrencyService.getList(TSForeignCurrencyEntity.class);
    	List<TSForeignCurrencyPage> pageList=new ArrayList<TSForeignCurrencyPage>();
        if(list!=null&&list.size()>0){
        	for(TSForeignCurrencyEntity entity:list){
        		try{
        			TSForeignCurrencyPage page=new TSForeignCurrencyPage();
        		   MyBeanUtils.copyBeanNotNull2Bean(entity,page);
					Object id0 = entity.getId();
				     String hql0 = "from TSCurrencyExchEntity where 1 = 1 AND cURRENCY_ID = ? ";
	    			List<TSCurrencyExchEntity> tSCurrencyExchOldList = this.tSForeignCurrencyService.findHql(hql0,id0);
            		page.setTSCurrencyExchList(tSCurrencyExchOldList);
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
	@ApiOperation(value="根据ID获取币种档案信息",notes="根据ID获取币种档案信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		TSForeignCurrencyEntity task = tSForeignCurrencyService.get(TSForeignCurrencyEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取币种档案信息为空");
		}
		TSForeignCurrencyPage page = new TSForeignCurrencyPage();
		try {
			MyBeanUtils.copyBeanNotNull2Bean(task, page);
				Object id0 = task.getId();
		    String hql0 = "from TSCurrencyExchEntity where 1 = 1 AND cURRENCY_ID = ? ";
			List<TSCurrencyExchEntity> tSCurrencyExchOldList = this.tSForeignCurrencyService.findHql(hql0,id0);
    		page.setTSCurrencyExchList(tSCurrencyExchOldList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Result.success(page);
	}
 	
 	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建币种档案")
	public ResponseMessage<?> create(@ApiParam(name="币种档案对象")@RequestBody TSForeignCurrencyPage tSForeignCurrencyPage, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<TSForeignCurrencyPage>> failures = validator.validate(tSForeignCurrencyPage);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		List<TSCurrencyExchEntity> tSCurrencyExchList =  tSForeignCurrencyPage.getTSCurrencyExchList();
		
		TSForeignCurrencyEntity tSForeignCurrency = new TSForeignCurrencyEntity();
		try{
			MyBeanUtils.copyBeanNotNull2Bean(tSForeignCurrencyPage,tSForeignCurrency);
		}catch(Exception e){
            logger.info(e.getMessage());
            return Result.error("保存币种档案失败");
        }
		tSForeignCurrencyService.addMain(tSForeignCurrency, tSCurrencyExchList);

		return Result.success(tSForeignCurrency);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新币种档案",notes="更新币种档案")
	public ResponseMessage<?> update(@RequestBody TSForeignCurrencyPage tSForeignCurrencyPage) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<TSForeignCurrencyPage>> failures = validator.validate(tSForeignCurrencyPage);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		List<TSCurrencyExchEntity> tSCurrencyExchList =  tSForeignCurrencyPage.getTSCurrencyExchList();
		
		TSForeignCurrencyEntity tSForeignCurrency = new TSForeignCurrencyEntity();
		try{
			MyBeanUtils.copyBeanNotNull2Bean(tSForeignCurrencyPage,tSForeignCurrency);
		}catch(Exception e){
            logger.info(e.getMessage());
            return Result.error("币种档案更新失败");
        }
		tSForeignCurrencyService.updateMain(tSForeignCurrency, tSCurrencyExchList);

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除币种档案")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" + id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			TSForeignCurrencyEntity tSForeignCurrency = tSForeignCurrencyService.get(TSForeignCurrencyEntity.class, id);
			tSForeignCurrencyService.delMain(tSForeignCurrency);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("币种档案删除失败");
		}

		return Result.success();
	}
	
	/**
	 *加载币种
	 *@author heyc
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping(params="menuTree")
	@ResponseBody
	private List<ComboTree> menuTree(HttpServletRequest request){
		List<ComboTree> comboLists=new ArrayList<ComboTree>();
		ComboTree comT=new ComboTree();
		comT.setId("");
		comT.setText("币种");
		List<ComboTree> treeList=new ArrayList<ComboTree>();
		//查询所有设置有编码方案的菜单
		String hql="from TSForeignCurrencyEntity  ";
		List<TSForeignCurrencyEntity> firstList = systemService.findHql(hql);
		if(firstList!=null&&firstList.size()>0){
			for (TSForeignCurrencyEntity fun:firstList) {
				ComboTree tree=new ComboTree();
				tree.setId(fun.getId());
				tree.setText(fun.getCurrencyName());
				treeList.add(tree);
			}
		}
		comT.setChildren(treeList);
		comboLists.add(comT);
		return comboLists;
	}
}
