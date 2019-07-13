package cn.com.linkwide.ba.material.images.controller;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletInputStream;
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

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.com.linkwide.ba.material.images.entity.LBaMaterialImagesEntity;
import cn.com.linkwide.ba.material.images.service.LBaMaterialImagesServiceI;
import cn.com.linkwide.common.util.controller.FilePathDefault;

/**   
 * @Title: Controller  
 * @Description: 物资图片
 * @author onlineGenerator
 * @date 2017-12-04 15:52:53
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/lBaMaterialImagesController")
public class LBaMaterialImagesController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(LBaMaterialImagesController.class);

	@Autowired
	private LBaMaterialImagesServiceI lBaMaterialImagesService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;
	


	/**
	 * 物资图片列表 页面跳转
	 * cn/com/linkwide/ba/material/images
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		String lBaMaterialId = request.getParameter("lBaMaterialId");
		request.setAttribute("lBaMaterialId", lBaMaterialId);
		return new ModelAndView("cn/com/linkwide/ba/material/images/lBaMaterialImagesList");
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
	public void datagrid(LBaMaterialImagesEntity lBaMaterialImages,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(LBaMaterialImagesEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, lBaMaterialImages, request.getParameterMap());
		try{
		//自定义追加查询条件
			cq.addOrder("createDate", SortDirection.desc);
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.lBaMaterialImagesService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除物资图片
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(LBaMaterialImagesEntity lBaMaterialImages, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		lBaMaterialImages = systemService.getEntity(LBaMaterialImagesEntity.class, lBaMaterialImages.getId());
		message = "物资图片删除成功";
		try{
			lBaMaterialImagesService.delete(lBaMaterialImages);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "物资图片删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除物资图片
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "物资图片删除成功";
		try{
			for(String id:ids.split(",")){
				LBaMaterialImagesEntity lBaMaterialImages = systemService.getEntity(LBaMaterialImagesEntity.class, 
				id
				);
				lBaMaterialImagesService.delete(lBaMaterialImages);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "物资图片删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加物资图片
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(LBaMaterialImagesEntity lBaMaterialImages, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "物资图片添加成功";
		try{
			TSUser tsUser = ResourceUtil.getSessionUserName();
			lBaMaterialImages.setCreateBy(tsUser.getId());
			lBaMaterialImages.setCreateDate(new Date());
			lBaMaterialImages.setDepartId(tsUser.getDepartid());
			lBaMaterialImagesService.save(lBaMaterialImages);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "物资图片添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 更新物资图片
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(LBaMaterialImagesEntity lBaMaterialImages, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "物资图片更新成功";
		LBaMaterialImagesEntity t = lBaMaterialImagesService.get(LBaMaterialImagesEntity.class, lBaMaterialImages.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(lBaMaterialImages, t);
			TSUser tsUser = ResourceUtil.getSessionUserName();
			t.setUpdateBy(tsUser.getId());
			t.setUpdateDate(new Date());
			lBaMaterialImagesService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "物资图片更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 物资图片新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(LBaMaterialImagesEntity lBaMaterialImages, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(lBaMaterialImages.getId())) {
			lBaMaterialImages = lBaMaterialImagesService.getEntity(LBaMaterialImagesEntity.class, lBaMaterialImages.getId());
			req.setAttribute("lBaMaterialImagesPage", lBaMaterialImages);
		}
		String lBaMaterialId = req.getParameter("lBaMaterialId");
		req.setAttribute("lBaMaterialId", lBaMaterialId);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
		String string = simpleDateFormat.format(new Date());
		req.setAttribute("filePath", FilePathDefault.MATERIALIMG+string+"/");
		return new ModelAndView("cn/com/linkwide/ba/material/images/lBaMaterialImages-add");
	}
	/**
	 * 物资图片编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(LBaMaterialImagesEntity lBaMaterialImages, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(lBaMaterialImages.getId())) {
			lBaMaterialImages = lBaMaterialImagesService.getEntity(LBaMaterialImagesEntity.class, lBaMaterialImages.getId());
			req.setAttribute("lBaMaterialImagesPage", lBaMaterialImages);
		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
		String string = simpleDateFormat.format(new Date());
		req.setAttribute("filePath", FilePathDefault.MATERIALIMG+string+"/");
		return new ModelAndView("cn/com/linkwide/ba/material/images/lBaMaterialImages-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","lBaMaterialImagesController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(LBaMaterialImagesEntity lBaMaterialImages,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(LBaMaterialImagesEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, lBaMaterialImages, request.getParameterMap());
		List<LBaMaterialImagesEntity> lBaMaterialImagess = this.lBaMaterialImagesService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"物资图片");
		modelMap.put(NormalExcelConstants.CLASS,LBaMaterialImagesEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("物资图片列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,lBaMaterialImagess);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(LBaMaterialImagesEntity lBaMaterialImages,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"物资图片");
    	modelMap.put(NormalExcelConstants.CLASS,LBaMaterialImagesEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("物资图片列表", "导出人:"+ResourceUtil.getSessionUserName().getRealName(),
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
				List<LBaMaterialImagesEntity> listLBaMaterialImagesEntitys = ExcelImportUtil.importExcel(file.getInputStream(),LBaMaterialImagesEntity.class,params);
				for (LBaMaterialImagesEntity lBaMaterialImages : listLBaMaterialImagesEntitys) {
					lBaMaterialImagesService.save(lBaMaterialImages);
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
	public List<LBaMaterialImagesEntity> list() {
		List<LBaMaterialImagesEntity> listLBaMaterialImagess=lBaMaterialImagesService.getList(LBaMaterialImagesEntity.class);
		return listLBaMaterialImagess;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		LBaMaterialImagesEntity task = lBaMaterialImagesService.get(LBaMaterialImagesEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody LBaMaterialImagesEntity lBaMaterialImages, UriComponentsBuilder uriBuilder) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<LBaMaterialImagesEntity>> failures = validator.validate(lBaMaterialImages);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		try{
			lBaMaterialImagesService.save(lBaMaterialImages);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		//按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = lBaMaterialImages.getId();
		URI uri = uriBuilder.path("/rest/lBaMaterialImagesController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody LBaMaterialImagesEntity lBaMaterialImages) {
		//调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<LBaMaterialImagesEntity>> failures = validator.validate(lBaMaterialImages);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//保存
		try{
			lBaMaterialImagesService.saveOrUpdate(lBaMaterialImages);
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

	
	}
	
	/**
	 * pda查询物资图片
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "pdaGetMaterialImgs")
	@ResponseBody
	public AjaxJson pdaGetMaterialImgs( HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		String message = "查询成功";
		String materialId = request.getParameter("materialId");
		try{ 
			if(StringUtil.isEmpty(materialId)){
				//获取请求数据
				StringBuffer sb = new StringBuffer();
				BufferedReader br = new BufferedReader(
						new InputStreamReader((ServletInputStream) request.getInputStream(), "utf-8"));
				String temp;
				while ((temp = br.readLine()) != null) {
					sb.append(temp);
				}
				br.close();
				if (StringUtil.isEmpty(sb.toString())) {
					j.setSuccess(false);
					j.setMsg("物资id不能为空");
					return  j;
				}
				JSONObject parseObject = JSONObject.parseObject(sb.toString());
				materialId  = parseObject.getString("materialId");
			}
			//查询物资图片
			JSONArray imgArray = new JSONArray();
			List<LBaMaterialImagesEntity> imgList = systemService.findByProperty(LBaMaterialImagesEntity.class, "materialId", materialId);
			for(LBaMaterialImagesEntity img:imgList){
				JSONObject imgObj = new JSONObject();
				imgObj.put("fileName", img.getFileName());
				imgObj.put("filePath", img.getFilePath());
				imgArray.add(imgObj);
			}
			j.setObj(imgArray);
			j.setSuccess(true);  
		}catch(Exception e){
			e.printStackTrace();
			message = "盘点单添加失败";
			throw new BusinessException(e.getMessage());
		}
		return j;
	}

}
