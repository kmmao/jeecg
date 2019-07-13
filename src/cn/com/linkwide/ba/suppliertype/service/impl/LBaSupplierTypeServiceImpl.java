package cn.com.linkwide.ba.suppliertype.service.impl;
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
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.linkwide.ba.suppliertype.entity.LBaSupplierTypeEntity;
import cn.com.linkwide.ba.suppliertype.service.LBaSupplierTypeServiceI;
import cn.com.linkwide.common.callwebservice.CallWebServiceHttp;
import cn.com.linkwide.common.callwebservice.WebServiceModel;

@Service("lBaSupplierTypeService")
@Transactional
public class LBaSupplierTypeServiceImpl extends CommonServiceImpl implements LBaSupplierTypeServiceI {

	@Autowired
	private SystemService systemService;
	
 	public void delete(LBaSupplierTypeEntity entity) throws Exception{
 		super.delete(entity);
 		//执行删除操作增强业务
		this.doDelBus(entity);
 	}
 	
 	public Serializable save(LBaSupplierTypeEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		//执行新增操作增强业务
 		this.doAddBus(entity);
 		sendVouch(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(LBaSupplierTypeEntity entity) throws Exception{
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
 	public boolean sendVouch(LBaSupplierTypeEntity entity){
 		//if(true) return true;
 		//拼接xml格式
 		StringBuffer str = new StringBuffer();
 		splicingXML(entity,str);
 		List<WebServiceModel> modeList = new ArrayList<WebServiceModel>();
 		WebServiceModel mode = null;
		mode = new WebServiceModel();
		mode.setParameterName("ctype");
		mode.setParameterValue("venclass");
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
 	public void splicingXML(LBaSupplierTypeEntity entity, StringBuffer str){
	 	// 头
		str.append("<data>");
		str.append("<body>");
		str.append("<entry>");
		String isLast ="1";
		LBaSupplierTypeEntity type =systemService.findUniqueByProperty(LBaSupplierTypeEntity.class, "typeCode", entity.getTypeCode());
		List<LBaSupplierTypeEntity> list =systemService.findByProperty(LBaSupplierTypeEntity.class, "parentId", type.getId());
		if(list!= null && list.size()>0){
			isLast="0";
		}
		//供应商分类编码
		str.append("<cvenclassbh>"+entity.getTypeCode()+"</cvenclassbh>");
		//分类名称
		str.append("<cvenclassname>"+entity.getTypeName()+"</cvenclassname>");
		//是否末级
		str.append("<bend>"+isLast+"</bend>");
		//编码级次（3-2-2）
		str.append("<ivcgrade>"+Integer.valueOf((entity.getTypeCode().length()-3)/2)+1+"</ivcgrade>");
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
	private void doAddBus(LBaSupplierTypeEntity t) throws Exception{
 	}
 	/**
	 * 更新操作增强业务
	 * @param t
	 * @return
	 */
	private void doUpdateBus(LBaSupplierTypeEntity t) throws Exception{
 	}
 	/**
	 * 删除操作增强业务
	 * @param id
	 * @return
	 */
	private void doDelBus(LBaSupplierTypeEntity t) throws Exception{
 	}
 	
 	private Map<String,Object> populationMap(LBaSupplierTypeEntity t){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", t.getId());
		map.put("type_code", t.getTypeCode());
		map.put("type_name", t.getTypeName());
		map.put("parent_id", t.getParentId());
		map.put("status", t.getStatus());
		return map;
	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @param t
	 * @return
	 */
 	public String replaceVal(String sql,LBaSupplierTypeEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{type_code}",String.valueOf(t.getTypeCode()));
 		sql  = sql.replace("#{type_name}",String.valueOf(t.getTypeName()));
 		sql  = sql.replace("#{parent_id}",String.valueOf(t.getParentId()));
 		sql  = sql.replace("#{status}",String.valueOf(t.getStatus()));
 		sql  = sql.replace("#{is_last}",String.valueOf(t.getIsLast()));
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
					javaInter.execute(data);
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("执行JAVA增强出现异常！");
			} 
		}
 	}
}