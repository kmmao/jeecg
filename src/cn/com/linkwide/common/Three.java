package cn.com.linkwide.common;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.jeecgframework.core.util.NumberComparator;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.web.system.pojo.base.TSFunction;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Title: Controller
 * @Description: 三级菜单
 * @author onlineGenerator
 * @date 2018-04-13 13:24:22
 * @version V1.0
 *
 */
@Controller
@RequestMapping("/three")
public class Three {
	
	@Autowired
	private SystemService systemService;
	
	/**
	 * 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		TSUser user = ResourceUtil.getSessionUser();
		ModelAndView modelAndView = new ModelAndView();
		String id = request.getParameter("clickFunctionId");
		StringBuilder hql =new StringBuilder("select distinct f from TSFunction f,TSRoleFunction rf,TSRoleUser ru  ")
				.append("where ru.TSRole.id=rf.TSRole.id and rf.TSFunction.id=f.id and ru.TSUser.id=? and f.TSFunction.id = ?");
        List<TSFunction> tsFunctions = this.systemService.findHql(hql.toString(),user.getId(),id);
		modelAndView.setViewName("cn/common/three");
		// 排序
		Collections.sort(tsFunctions, new NumberComparator());
		modelAndView.addObject("tsfList", tsFunctions);
		return modelAndView;
	}

}
