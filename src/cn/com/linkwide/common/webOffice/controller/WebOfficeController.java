package cn.com.linkwide.common.webOffice.controller; 
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
@Api(value="FileDict",description="附件表",tags="webOfficeController")
@Controller
@RequestMapping("/webOfficeController")
public class WebOfficeController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(WebOfficeController.class);

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
	@RequestMapping(params = "goEdit")
	public ModelAndView list(HttpServletRequest request) {
		String id = request.getParameter("id");
		String path = getFilePath(id,request);
	   	request.setAttribute("path", path);
		return new ModelAndView("cn/com/linkwide/common/webOffice/officeEdit");
	}
	/**
	 * 附件表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "openFile")
	public ModelAndView openFile(HttpServletRequest request) {
		return new ModelAndView("cn/com/linkwide/common/webOffice/openOffice");
	}
	
	public String getFilePath(String id,HttpServletRequest request){
		String realPath = "http://"+FtpUtil.getFtpUrl()+"/upload/";
	   	if(!FtpUtil.isStart()){
    		realPath =request.getRequestURL().substring(0,request.getRequestURL().lastIndexOf("/"));
    		
    	}
	   	FileDictEntity dict  =	systemService.getEntity(FileDictEntity.class, id);
		return realPath+dict.getFilePath();
	}
}
