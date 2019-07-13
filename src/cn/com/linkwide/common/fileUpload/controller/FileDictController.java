package cn.com.linkwide.common.fileUpload.controller; 
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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
import org.jeecgframework.core.util.ContextHolderUtils;
import org.jeecgframework.core.util.ExceptionUtil;
import org.jeecgframework.core.util.FileUtils;
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
import org.jeecgframework.web.cgform.entity.upload.CgUploadEntity;
import org.jeecgframework.web.cgform.service.config.CgFormFieldServiceI;
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

import cn.com.linkwide.common.fileUpload.entity.FileDictEntity;
import cn.com.linkwide.common.fileUpload.service.FileDictServiceI;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
/**   
 * @Title: Controller  
 * @Description: 附件表
 * @author onlineGenerator
 * @date 2018-05-17 10:30:56
 * @version V1.0   
 *
 */
@Api(value="FileDict",description="附件表",tags="fileDictController")
@Controller
@RequestMapping("/fileDictController")
public class FileDictController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(FileDictController.class);

	@Autowired
	private FileDictServiceI fileDictService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	@Autowired
	private CgFormFieldServiceI cgFormFieldService;
	


	/**
	 * 附件表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("cn/com/linkwide/vendor/venfiledict/fileDictList");
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
	public void datagrid(FileDictEntity fileDict,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(FileDictEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, fileDict, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.fileDictService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除附件表
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(FileDictEntity fileDict, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		fileDict = systemService.getEntity(FileDictEntity.class, fileDict.getId());
		message = "附件表删除成功";
		try{ 
			//获取部署项目绝对地址
			String filePaht = fileDict.getFilePath();
			String realPath = ContextHolderUtils.getSession().getServletContext().getRealPath("/");
			if(FtpUtil.isStart()){
				Boolean flag = false;
				flag =FtpUtil.delFtpFile(filePaht);
				if(!flag){
					j.setMsg("文件删除失败");
					j.setSuccess(false);
					return j;
				}
			}else{
				FileUtils.delete(realPath+filePaht);
				if(filePaht.endsWith(".pdf") || filePaht.endsWith(".swf")){
					filePaht = filePaht.substring(0,filePaht.length()-4);
					FileUtils.delete(realPath+filePaht+".pdf");
					FileUtils.delete(realPath+filePaht+".swf");
				}
			}
			
			fileDictService.delete(fileDict);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "附件表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除附件表
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "附件表删除成功";
		try{
			for(String id:ids.split(",")){
				FileDictEntity fileDict = systemService.getEntity(FileDictEntity.class, 
				id
				);
				fileDictService.delete(fileDict);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "附件表删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加附件表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(FileDictEntity fileDict, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "附件表添加成功";
		try{
			fileDictService.save(fileDict);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "附件表添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		j.setObj(fileDict);
		return j;
	}
	
	
	/**
	 * 添加附件表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAddFile") 
	@ResponseBody
	public AjaxJson doAddFile(FileDictEntity fileDict, HttpServletRequest request) {
		String filePath = request.getParameter("filePath"); 
		String fileName = request.getParameter("fileName");
		String tableName = request.getParameter("tableName");
		String tableId = request.getParameter("tableId");
		if(tableId == null || "".equals(tableId) || filePath == null || "".equals(filePath)){ 
			throw new BusinessException("附件表添加失败");
		}
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "附件表添加成功";
		try{
			fileDict.setFileName(fileName);
			fileDict.setFilePath(filePath); 
			fileDict.setTableName(tableName);
			fileDict.setTableId(tableId);
			fileDictService.save(fileDict);
			j.setObj(fileDict);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "附件表添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		j.setObj(fileDict);
		return j;
	}
	/**
	 * 更新附件表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(FileDictEntity fileDict, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "附件表更新成功";
		FileDictEntity t = fileDictService.get(FileDictEntity.class, fileDict.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(fileDict, t);
			fileDictService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "附件表更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 附件表新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(FileDictEntity fileDict, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(fileDict.getId())) {
			fileDict = fileDictService.getEntity(FileDictEntity.class, fileDict.getId());
			req.setAttribute("fileDictPage", fileDict);
		}
		return new ModelAndView("cn/com/linkwide/vendor/venfiledict/fileDict-add");
	}
	/**
	 * 附件表编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(FileDictEntity fileDict, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(fileDict.getId())) {
			fileDict = fileDictService.getEntity(FileDictEntity.class, fileDict.getId());
			req.setAttribute("fileDictPage", fileDict);
		}
		return new ModelAndView("cn/com/linkwide/vendor/venfiledict/fileDict-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","FileDictController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(FileDictEntity fileDict,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(FileDictEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, fileDict, request.getParameterMap());
		List<FileDictEntity> fileDicts = this.fileDictService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"附件表");
		modelMap.put(NormalExcelConstants.CLASS,FileDictEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("附件表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,fileDicts);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(FileDictEntity fileDict,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"附件表");
    	modelMap.put(NormalExcelConstants.CLASS,FileDictEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("附件表列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<FileDictEntity> listFileDictEntitys = ExcelImportUtil.importExcel(file.getInputStream(),FileDictEntity.class,params);
				for (FileDictEntity fileDict : listFileDictEntitys) {
					fileDictService.save(fileDict);
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
	 * 获取文件附件信息
	 * 
	 * @param id fileDict主键id
	 */
	@RequestMapping(params = "getFiles")
	@ResponseBody
	public AjaxJson getFiles(String id){
		List<CgUploadEntity> uploadBeans = cgFormFieldService.findByProperty(CgUploadEntity.class, "cgformId", id);
		List<Map<String,Object>> files = new ArrayList<Map<String,Object>>(0);
		for(CgUploadEntity b:uploadBeans){
			String title = b.getAttachmenttitle();//附件名
			String fileKey = b.getId();//附件主键
			String path = b.getRealpath();//附件路径
			String field = b.getCgformField();//表单中作为附件控件的字段
			Map<String, Object> file = new HashMap<String, Object>();
			file.put("title", title);
			file.put("fileKey", fileKey);
			file.put("path", path);
			file.put("field", field==null?"":field);
			files.add(file);
		}
		AjaxJson j = new AjaxJson();
		j.setObj(files);
		return j;
	}
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="附件表列表信息",produces="application/json",httpMethod="GET")
	public ResponseMessage<List<FileDictEntity>> list() {
		List<FileDictEntity> listfileDicts=fileDictService.getList(FileDictEntity.class);
		return Result.success(listfileDicts);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value="根据ID获取附件表信息",notes="根据ID获取附件表信息",httpMethod="GET",produces="application/json")
	public ResponseMessage<?> get(@ApiParam(required=true,name="id",value="ID")@PathVariable("id") String id) {
		FileDictEntity task = fileDictService.get(FileDictEntity.class, id);
		if (task == null) {
			return Result.error("根据ID获取附件表信息为空");
		}
		return Result.success(task);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="创建附件表")
	public ResponseMessage<?> create(@ApiParam(name="附件表对象")@RequestBody FileDictEntity fileDict, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<FileDictEntity>> failures = validator.validate(fileDict);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			fileDictService.save(fileDict);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("附件表信息保存失败");
		}
		return Result.success(fileDict);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value="更新附件表",notes="更新附件表")
	public ResponseMessage<?> update(@ApiParam(name="附件表对象")@RequestBody FileDictEntity fileDict) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<FileDictEntity>> failures = validator.validate(fileDict);
		if (!failures.isEmpty()) {
			return Result.error(JSONArray.toJSONString(BeanValidators.extractPropertyAndMessage(failures)));
		}

		//保存
		try{
			fileDictService.saveOrUpdate(fileDict);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("更新附件表信息失败");
		}

		//按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return Result.success("更新附件表信息成功");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value="删除附件表")
	public ResponseMessage<?> delete(@ApiParam(name="id",value="ID",required=true)@PathVariable("id") String id) {
		logger.info("delete[{}]" + id);
		// 验证
		if (StringUtils.isEmpty(id)) {
			return Result.error("ID不能为空");
		}
		try {
			FileDictEntity deletFile = systemService.findUniqueByProperty(FileDictEntity.class, "id", id);
			//获取部署项目绝对地址
			String filePaht = deletFile.getFilePath();
			String realPath = ContextHolderUtils.getSession().getServletContext().getRealPath("/");
			FileUtils.delete(realPath+filePaht);
			if(filePaht.endsWith(".pdf") || filePaht.endsWith(".swf")){
				filePaht = filePaht.substring(0,filePaht.length()-4);
				FileUtils.delete(realPath+filePaht+".pdf");
				FileUtils.delete(realPath+filePaht+".swf");
			}
			
			fileDictService.deleteEntityById(FileDictEntity.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("附件表删除失败");
		}

		return Result.success();
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
	String isShow =req.getParameter("isShow");
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
		req.setAttribute("isEdit", isEdit);
		req.setAttribute("modName", modName);
		req.setAttribute("realPath", realPath);
		if(StringUtil.isNotEmpty(isShow)){
			req.setAttribute("isShow", "1");
			}
		req.setAttribute("sessionId",ContextHolderUtils.getSession().getId());
	}catch(Exception e){
		logger.info(e.getMessage());
	} 
	return new ModelAndView("cn/com/linkwide/common/fileUpload/fileileTablePage");
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
		String isEdit = req.getParameter("isEdit");
		String modName = req.getParameter("modName");
		String isShow =req.getParameter("isShow");
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
			if(StringUtil.isNotEmpty(isShow)){
			req.setAttribute("isShow", "1");
			}
			req.setAttribute("sessionId",ContextHolderUtils.getSession().getId());
		}catch(Exception e){
			logger.info(e.getMessage());
		} 
		return new ModelAndView("cn/com/linkwide/common/fileUpload/fileEdit");
	}
	
	@RequestMapping(params = "xiazai")
	public void xiazai(HttpServletRequest request,HttpServletResponse response){
		/*String id = request.getParameter("id");
		String ftpName = request.getParameter("ftpName");
		JobBaseInfoEntity jobBaseInfoEntity = this.systemService.findUniqueByProperty(JobBaseInfoEntity.class, "accessory", id);
		try {
			String name = jobBaseInfoEntity.getEmpName();
//			if (isIE(request)) {
//				name = java.net.URLEncoder.encode(name, "UTF8");
//			} else {
//			}
			String urlftp = "http://"+FtpUtil.ftpUrl+"//"+"upload"+"//"+ftpName;
			System.out.println(urlftp);
			URL url = new URL(urlftp);
	        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
	        //设置超时间为3秒
	        conn.setConnectTimeout(3*1000);
	        //防止屏蔽程序抓取而返回403错误
	        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
			name = new String(name.getBytes("UTF-8"), "ISO-8859-1");
			InputStream fileInputStream = conn.getInputStream();
			response.setHeader("content-disposition", "attachment;filename=" + name+".pdf");
			ServletOutputStream out = response.getOutputStream();
			byte[] bytes = new byte[2048];
			int len = 0;
			while ((len = fileInputStream.read(bytes)) > 0) {
				out.write(bytes, 0, len);
				out.flush();
			}
			fileInputStream.close();
			out.close();
//			FileUtils.delete(tomcatPath);// 删除
		} catch (Exception e) {
			System.out.println(e.getMessage());
			this.logger.error("Error downloading card:" + e.getMessage());
		}*/
		
	}
	
	
	
	
	/**
	 * 查询ftp服务器路径
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "getRealPath")
	@ResponseBody
	public AjaxJson getRealPath(HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		try {
				String realPath = "http://"+FtpUtil.getFtpUrl()+"/upload/";
			   	if(!FtpUtil.isStart()){
		    		realPath ="";
		    	}
			   	j.setObj(realPath);
		} catch (Exception e) { 
		}
		j.setMsg(message);
		return j;
	}
}
