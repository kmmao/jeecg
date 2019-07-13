package cn.com.linkwide.common.util.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**   
 * @Title: Controller  
 * @Description: 产品分类
 * @author onlineGenerator
 * @date 2016-10-22 14:41:13
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/lsController")
public class LsController extends BaseController {
    /**
     * Logger for this class
     */
    private static final Logger logger = Logger.getLogger(LsController.class);

    @Autowired
    private SystemService       systemService;


    /**
     * 更新授权码分类
     * 
     * @param ids
     * @return
     */
    @RequestMapping(params = "doUpdate")
    @ResponseBody
    public AjaxJson doUpdate(HttpServletRequest request) {
        String message = null;
        
        AjaxJson j = new AjaxJson();
        message = "授权码更新成功";
        
        String zsxx = (String)request.getParameter("zsxx");
        try {
        	String flag = ValiLicense.jmzs(zsxx);
    		
        	if(!flag.equals("ok")){
                message = flag;
                j.setMsg(message);
                return j;
        	}else{
        		//验证无误后，写入txt文件
    			String realPath = request.getSession().getServletContext().getRealPath("/") + "/upload/ls/sqm.txt";// 文件的硬盘真实路径

    			File file = new File(realPath);
    			if (!file.exists()) {
    				file.mkdirs();// 创建根目录
    			}

    			BufferedWriter output = new BufferedWriter(new FileWriter(file));  
    			output.write(zsxx);  
    			output.close();  
        	}


            systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "授权码更新失败";
            j.setMsg(message);
        }
        j.setMsg(message);
        return j;
    }


    /**
     * 产品分类编辑页面跳转
     * 
     * @return
     * @throws Exception 
     */
    @RequestMapping(params = "goUpdate")
    public ModelAndView goUpdate(HttpServletRequest req) throws Exception {
		String macAddress = new  MacAddress().getMACAddress();
		
		req.setAttribute("macAddress", macAddress);
        return new ModelAndView("cn/com/linkwide/mate/ls/ls-update");
    }

	/**
	 * ch
	 * 
	 * @return
	 */
	@RequestMapping(params = "getLsFlag")
	@ResponseBody
	public AjaxJson getLsFlag(HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		Map<String, Object> attrMap = new HashMap<String, Object>();

		message = "授权查询成功";
		try{
			//license验证
			String flag = ValiLicense.jmzsFromFile(request);

			attrMap.put("lsFlag", flag);
			
			j.setAttributes(attrMap);
		}catch(Exception e){
			e.printStackTrace();
			message = "授权查询失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
}
