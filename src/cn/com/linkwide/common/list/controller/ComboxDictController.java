package cn.com.linkwide.common.list.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSFunction;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import cn.com.linkwide.common.tools.BillCodeUtil;
import io.swagger.annotations.Api;
/**   
 * @Title: Controller  
 * @Description: 公用下拉
 * @author onlineGenerator
 * @date 2018-05-17 10:30:56
 * @version V1.0   
 *
 */
@Api(value="Dict",description="下拉列表",tags="dictListController")
@Controller
@RequestMapping("/comboxDictController")
public class ComboxDictController extends BaseController {
	
	@Autowired
	private SystemService systemService;
	/**
	 * 生成单据号
	 * @return
	 *2017年12月4日
	 *@author 
	 */
	public String generateBillCode(){
		String	billcode="";
		TSFunction function = this.systemService.findUniqueByProperty(TSFunction.class, "functionUrl", "payApplyMainController.do?list");
		if(function==null){
			throw new BusinessException("未设置薪资项目的单据类型");
		}
		billcode=BillCodeUtil.productBillCode(function.getBillType());
		return billcode;
	}
	/**
	 * 用户信息模糊查询
	 * 
	 * @author  2018-3-27
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "incl")
	@ResponseBody
	public Object incl(HttpServletRequest request) {
		String q = request.getParameter("q") != null ? request.getParameter("q") : "";
		
		StringBuffer sb = new StringBuffer();
		sb.append("select id, emp_code as empCode,emp_name as empName")
		.append(" from hr_person ")
		.append(" where emp_code like '%"+q+"%'")
		.append(" or emp_name like '%"+q+"%'");
		System.out.println(sb.toString());
		List<Map<String,Object>> list = this.systemService.findForJdbc(sb.toString(), 1, 10);
	    //List list = this.hrDeptBonReportService.findListbySql(sb.toString());			
	    Object json = JSONObject.toJSON(list);
		return json;
	}
	/**
	 * 部门模糊查询
	 * 
	 * @author  2018-3-27
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "orgCode")
	@ResponseBody
	public Object orgCode(HttpServletRequest request) {
		String q = request.getParameter("q") != null ? request.getParameter("q") : "";
		
		StringBuffer sb = new StringBuffer();
		sb.append("select id, org_code as orgCode,departname as departname")
		.append(" from t_s_depart ")
		.append(" where org_code like '%"+q+"%'")
		.append(" or departname like '%"+q+"%'");
		System.out.println(sb.toString());
		List<Map<String,Object>> list = this.systemService.findForJdbc(sb.toString(), 1, 10);
	    //List list = this.hrDeptBonReportService.findListbySql(sb.toString());			
	    Object json = JSONObject.toJSON(list);
		return json;
	}
	/**
	 * 科目模糊查询
	 * 
	 * @author  2018-3-27
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "subCode")
	@ResponseBody
	public Object subCode(HttpServletRequest request) {
		String q = request.getParameter("q") != null ? request.getParameter("q") : "";
		
		StringBuffer sb = new StringBuffer();
		sb.append("select id, sub_code as subCode,sub_name as subName")
		.append(" from ba_acct_subj ")
		.append(" where sub_code like '%"+q+"%'")
		.append(" or sub_name like '%"+q+"%'");
		System.out.println(sb.toString());
		List<Map<String,Object>> list = this.systemService.findForJdbc(sb.toString(), 1, 10);
	    //List list = this.hrDeptBonReportService.findListbySql(sb.toString());			
	    Object json = JSONObject.toJSON(list);
		return json;
	}
	/**
	 * 项目模糊查询
	 * 
	 * @author  2018-3-27
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "baItemfile")
	@ResponseBody
	public Object baItemfile(HttpServletRequest request) {
		String q = request.getParameter("q") != null ? request.getParameter("q") : "";
		
		StringBuffer sb = new StringBuffer();
		sb.append("select id, vcode as vcode,vname as vname")
		.append(" from ba_itemfile ")
		.append(" where vcode like '%"+q+"%'")
		.append(" or vname like '%"+q+"%'");
		System.out.println(sb.toString());
		List<Map<String,Object>> list = this.systemService.findForJdbc(sb.toString(), 1, 10);
	    //List list = this.hrDeptBonReportService.findListbySql(sb.toString());			
	    Object json = JSONObject.toJSON(list);
		return json;
	}
	
	
	/**
	 * 资产卡片下拉
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "cardList")
	@ResponseBody
	public void cardList(HttpServletRequest request, DataGrid dataGrid, HttpServletResponse response) {
		String q = request.getParameter("q") != null ? request.getParameter("q") : "";
		String OrgCode= request.getParameter("OrgCode") !=null ? request.getParameter("OrgCode") : "";
		String defaultVal = request.getParameter("defaultVal") != null ? request.getParameter("defaultVal") : "";
		try {
			StringBuffer sb = new StringBuffer();
			sb.append(" from ppe_cards where 1=1");
			if(request.getParameter("q") != null){
				sb.append(" and ( asset_coding like '%"+q+"%' ");
				sb.append(" or asset_name like '%"+q+"%' )");
			}
			String sbq= "";
			if(StringUtil.isNotEmpty(OrgCode)){
				  sbq= " and administration_depart='"+OrgCode+"'";
			}
			/*sb.append("  order by asset_coding ");*/
			String sql="select id \"id\", asset_coding \"assetCoding\",asset_name \"assetName\",spec_model \"specModel\",original_value \"originalValue\"";
			List<Map<String,Object>> results = systemService.findForJdbc(sql+sb.toString()+sbq.toString(), dataGrid.getPage(), dataGrid.getRows());
			//设置默认值
		 	if(StringUtil.isNotEmpty(defaultVal) && StringUtil.isEmpty(q)){
				Map<String,Map<String,Object>> mMap = systemService.list2Map(results, "id");
				String[] def = defaultVal.split(",");
				if(def.length >1 && !mMap.containsKey(def[1])){
					Map<String,Object> val = systemService.findOneForJdbc(sql+sb.toString()+" and  "+def[0]+" = ?",def[1]);
					results.add(val);
				}
			} 
			dataGrid.setResults(results);
			dataGrid.setTotal(Integer.valueOf(systemService.findForJdbc("select count(id) as cou "+sb.toString()+sbq.toString()).get(0).get("cou").toString()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		TagUtil.datagrid(response, dataGrid);
	}
}
