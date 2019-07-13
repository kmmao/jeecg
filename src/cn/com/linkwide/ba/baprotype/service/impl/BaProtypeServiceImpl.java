package cn.com.linkwide.ba.baprotype.service.impl;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.encoding.XMLType;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.ApplicationContextUtil;
import org.jeecgframework.core.util.MyClassLoader;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.web.cgform.enhance.CgformEnhanceJavaInter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.linkwide.ba.baprotype.entity.BaProtypeEntity;
import cn.com.linkwide.ba.baprotype.service.BaProtypeServiceI;
import cn.com.linkwide.common.callwebservice.CallWebServiceHttp;
import cn.com.linkwide.common.callwebservice.WebServiceModel;

@Service("baProtypeService")
@Transactional
public class BaProtypeServiceImpl extends CommonServiceImpl implements BaProtypeServiceI {

	
 	public void delete(BaProtypeEntity entity) throws Exception{
 		super.delete(entity);
 		//执行删除操作增强业务
		this.doDelBus(entity);
 	}
 	
 	public Serializable save(BaProtypeEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		//执行新增操作增强业务
 		this.doAddBus(entity);
 		sendVouch(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(BaProtypeEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 		//执行更新操作增强业务
 		this.doUpdateBus(entity);
 		sendVouch(entity);
 	}
 	
 	
 	/**
 	 * 向u8推送数据
 	 * @param entity
 	 * @return
 	 */
 	public boolean sendVouch(BaProtypeEntity entity){
 		//拼接xml格式
 		StringBuffer str = new StringBuffer();
 		splicingXML(entity,str);
 		List<WebServiceModel> modeList = new ArrayList<WebServiceModel>();
 		WebServiceModel mode = null;
		mode = new WebServiceModel();
		mode.setParameterName("ctype");
		mode.setParameterValue("itemclass");
		mode.setParameterType(XMLType.XSD_STRING);
		mode.setInputAndOutput(ParameterMode.IN);
		modeList.add(mode);
		mode = new WebServiceModel();
		mode.setParameterName("strxml");
		mode.setParameterValue(str.toString());
		mode.setParameterType(XMLType.XSD_STRING);
		mode.setInputAndOutput(ParameterMode.IN);
		modeList.add(mode);
		mode = new WebServiceModel();
		mode.setParameterName("sdatabase");
		mode.setParameterValue("UFDATA_888_2019");
		mode.setParameterType(XMLType.XSD_STRING);
		mode.setInputAndOutput(ParameterMode.IN);
		modeList.add(mode);
		Document xml = null;
		
		try {
			xml = CallWebServiceHttp.multipleParameterAccessInterfaceReturnXML("bU8Web", modeList,XMLType.XSD_STRING);
		} catch (DocumentException e) {
			e.printStackTrace();
			return false;
		}
		Element root = xml.getRootElement();
		Element success = root.element("entry").element("success");
		if("0".equals(success.getText())) { //失败
			throw new BusinessException(root.element("entry").element("message").getText());
		}
 		return true;
 	}
 	
 	/**
 	 * 根据实体拼接对应的xml格式
 	 * @param entity
 	 */
 	public void splicingXML(BaProtypeEntity entity, StringBuffer str){
	 	// 头
		str.append("<data>");
		str.append("<body>");
		str.append("<entry>");
		//项目大类编码
		str.append("<cxmitem>"+entity.getPkbaitemtype()+"</cxmitem>");
		//项目分类编码
		str.append("<citemccode>"+entity.getVcode()+"</citemccode>");
		//项目分类名称
		str.append("<citemcname>"+entity.getVname()+"</citemcname>");
		//是否末级
		String isLast = ("Y".equals(entity.getViflater()))?"1":"0";
		str.append("<bitemcend>"+isLast+"</bitemcend>");
		//编码级次（2-2-2）
		str.append("<itemcgrade>"+(entity.getVcode().length())/2+"</itemcgrade>");
		str.append("</entry>");
		// 尾
		str.append("</body>");
		str.append("</data>");
 	}
 	/**
	 * 新增操作增强业务
	 * @param t
	 * @return
	 */
	private void doAddBus(BaProtypeEntity t) throws Exception{
		//-----------------sql增强 start----------------------------
	 	//-----------------sql增强 end------------------------------
	 	
	 	//-----------------java增强 start---------------------------
	 	//-----------------java增强 end-----------------------------
 	}
 	/**
	 * 更新操作增强业务
	 * @param t
	 * @return
	 */
	private void doUpdateBus(BaProtypeEntity t) throws Exception{
		//-----------------sql增强 start----------------------------
	 	//-----------------sql增强 end------------------------------
	 	
	 	//-----------------java增强 start---------------------------
	 	//-----------------java增强 end-----------------------------
 	}
 	/**
	 * 删除操作增强业务
	 * @param id
	 * @return
	 */
	private void doDelBus(BaProtypeEntity t) throws Exception{
	    //-----------------sql增强 start----------------------------
	 	//-----------------sql增强 end------------------------------
	 	
	 	//-----------------java增强 start---------------------------
	 	//-----------------java增强 end-----------------------------
 	}
 	
 	private Map<String,Object> populationMap(BaProtypeEntity t){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", t.getId());
		map.put("create_name", t.getCreateName());
		map.put("create_by", t.getCreateBy());
		map.put("create_date", t.getCreateDate());
		map.put("update_name", t.getUpdateName());
		map.put("update_by", t.getUpdateBy());
		map.put("update_date", t.getUpdateDate());
		map.put("sys_org_code", t.getSysOrgCode());
		map.put("sys_company_code", t.getSysCompanyCode());
		map.put("bpm_status", t.getBpmStatus());
		map.put("vcode", t.getVcode());
		map.put("vname", t.getVname());
		map.put("voutcode", t.getVoutcode());
		map.put("voutname", t.getVoutname());
		map.put("pkparent", t.getPkparent());
		map.put("pkbaitemtype", t.getPkbaitemtype());
		map.put("viflater", t.getViflater());
		map.put("extend1", t.getExtend1());
		map.put("extend2", t.getExtend2());
		map.put("extend3", t.getExtend3());
		map.put("extend4", t.getExtend4());
		map.put("extend5", t.getExtend5());
		map.put("extend6", t.getExtend6());
		map.put("extend7", t.getExtend7());
		map.put("extend8", t.getExtend8());
		map.put("extend9", t.getExtend9());
		map.put("extend10", t.getExtend10());
		return map;
	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @param t
	 * @return
	 */
 	public String replaceVal(String sql,BaProtypeEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{create_name}",String.valueOf(t.getCreateName()));
 		sql  = sql.replace("#{create_by}",String.valueOf(t.getCreateBy()));
 		sql  = sql.replace("#{create_date}",String.valueOf(t.getCreateDate()));
 		sql  = sql.replace("#{update_name}",String.valueOf(t.getUpdateName()));
 		sql  = sql.replace("#{update_by}",String.valueOf(t.getUpdateBy()));
 		sql  = sql.replace("#{update_date}",String.valueOf(t.getUpdateDate()));
 		sql  = sql.replace("#{sys_org_code}",String.valueOf(t.getSysOrgCode()));
 		sql  = sql.replace("#{sys_company_code}",String.valueOf(t.getSysCompanyCode()));
 		sql  = sql.replace("#{bpm_status}",String.valueOf(t.getBpmStatus()));
 		sql  = sql.replace("#{vcode}",String.valueOf(t.getVcode()));
 		sql  = sql.replace("#{vname}",String.valueOf(t.getVname()));
 		sql  = sql.replace("#{voutcode}",String.valueOf(t.getVoutcode()));
 		sql  = sql.replace("#{voutname}",String.valueOf(t.getVoutname()));
 		sql  = sql.replace("#{pkparent}",String.valueOf(t.getPkparent()));
 		sql  = sql.replace("#{pkbaitemtype}",String.valueOf(t.getPkbaitemtype()));
 		sql  = sql.replace("#{viflater}",String.valueOf(t.getViflater()));
 		sql  = sql.replace("#{extend1}",String.valueOf(t.getExtend1()));
 		sql  = sql.replace("#{extend2}",String.valueOf(t.getExtend2()));
 		sql  = sql.replace("#{extend3}",String.valueOf(t.getExtend3()));
 		sql  = sql.replace("#{extend4}",String.valueOf(t.getExtend4()));
 		sql  = sql.replace("#{extend5}",String.valueOf(t.getExtend5()));
 		sql  = sql.replace("#{extend6}",String.valueOf(t.getExtend6()));
 		sql  = sql.replace("#{extend7}",String.valueOf(t.getExtend7()));
 		sql  = sql.replace("#{extend8}",String.valueOf(t.getExtend8()));
 		sql  = sql.replace("#{extend9}",String.valueOf(t.getExtend9()));
 		sql  = sql.replace("#{extend10}",String.valueOf(t.getExtend10()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}
 	
 	/**
	 * 执行JAVA增强
	 */
 	private void executeJavaExtend(String cgJavaType,String cgJavaValue,Map<String,Object> data) throws Exception {
 		if(StringUtil.isNotEmpty(cgJavaValue)){
			Object obj = null;
			try {
				if("class".equals(cgJavaType)){
					//因新增时已经校验了实例化是否可以成功，所以这块就不需要再做一次判断
					obj = MyClassLoader.getClassByScn(cgJavaValue).newInstance();
				}else if("spring".equals(cgJavaType)){
					obj = ApplicationContextUtil.getContext().getBean(cgJavaValue);
				}
				if(obj instanceof CgformEnhanceJavaInter){
					CgformEnhanceJavaInter javaInter = (CgformEnhanceJavaInter) obj;
					javaInter.execute("ba_protype",data);
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("执行JAVA增强出现异常！");
			} 
		}
 	}
}