package cn.com.linkwide.common.custom.documentNum.manage;

import javax.servlet.http.HttpServletRequest;

import org.jeecgframework.core.common.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Title: Controller  
 * @Description: 单据号管理
 * @author chencp
 * @date 2017-09-11 17:00:00
 * @version V1.0 
 *
 */
@Controller
@RequestMapping("/docNumManageController")
public class DocNumManageController extends BaseController{
/**
 * 跳转到单据管理列表
 *@author chencp
 *2017年9月11日下午5:55:20
 * @param request
 * @return
 */
@RequestMapping(params="list")	
public ModelAndView list(HttpServletRequest request){
	return new ModelAndView("cn/com/linkwide/common/custom/documentNum/manage/docNumManageList");
}

}
