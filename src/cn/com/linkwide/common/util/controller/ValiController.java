package cn.com.linkwide.common.util.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
@RequestMapping("/valiController")
public class ValiController {
	
	@Autowired
	private SystemService systemService;
	
	@RequestMapping(params = "checkCodeRepeat")
	@ResponseBody
	public AjaxJson doDel(HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		
		String id = request.getParameter("id");
		if(StringUtils.isEmpty(id)){
			id = "null";
		}
		String code = request.getParameter("code");
		if(StringUtils.isEmpty(code)){
			j.setSuccess(false);
			return j;
		}
		
		String tableName = request.getParameter("tableName");
		if(StringUtils.isEmpty(tableName)){
			j.setSuccess(false);
			return j;
		}
		
		String codeName = request.getParameter("codeName");
		if(StringUtils.isEmpty(codeName)){
			j.setSuccess(false);
			return j;
		}
		
		try {
			List<String> ids = systemService.findListbySql("select id from " + tableName + " where " + codeName + " = '" + code
					+ "' and id != '" + id + "' ");
			if(ids.size() > 0){
				j.setSuccess(false);//编码重复
			}else{
				j.setSuccess(true);//编码不重复
			}
		} catch (Exception e) {
			j.setSuccess(false);//编码重复
		}
		
		
		return j;
	}
}
