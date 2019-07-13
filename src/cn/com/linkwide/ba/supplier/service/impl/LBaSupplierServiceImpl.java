package cn.com.linkwide.ba.supplier.service.impl;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.ApplicationContextUtil;
import org.jeecgframework.core.util.MyClassLoader;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.web.cgform.enhance.CgformEnhanceJavaInter;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.buss.activiti.util.BillStatus;

import cn.com.linkwide.ba.supplier.entity.LBaSupplierEntity;
import cn.com.linkwide.ba.supplier.service.LBaSupplierServiceI;
import cn.com.linkwide.ba.suppliertype.entity.LBaSupplierTypeEntity;
import cn.com.linkwide.ba.syn.SynchronousBa;
import cn.com.linkwide.common.callwebservice.CallWebServiceHttp;
import cn.com.linkwide.common.callwebservice.WebServiceModel;

@Service("lBaSupplierService")
@Transactional
public class LBaSupplierServiceImpl extends CommonServiceImpl implements LBaSupplierServiceI {
	@Autowired
	private SystemService systemService;
	
 	public void delete(LBaSupplierEntity entity) throws Exception{
 		super.delete(entity);
 		//执行删除操作增强业务
		this.doDelBus(entity);
		String sql1 =SynchronousBa.synDelete(entity);
		if(StringUtil.isNotEmpty(sql1)){
			this.updateBySqlString(sql1);
		}
 	}
 	
 	public Serializable save(LBaSupplierEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		//执行新增操作增强业务
 		this.doAddBus(entity);
 		//同步数据
		String sql =SynchronousBa.synSave(entity);
		if(StringUtil.isNotEmpty(sql)){
			this.updateBySqlString(sql);
		}
		//向u8推送数据
		List<LBaSupplierEntity> list = new ArrayList<>();
		list.add(entity);
		sendVouch(list);
 		return t;
 	}
 	
 	public void saveOrUpdate(LBaSupplierEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 		//执行更新操作增强业务
 		this.doUpdateBus(entity);
 		//同步数据
		String sql1 =SynchronousBa.synDelete(entity);
		if(StringUtil.isNotEmpty(sql1)){
			this.updateBySqlString(sql1);
		}
		String sql2 =SynchronousBa.synSave(entity);
		if(StringUtil.isNotEmpty(sql2)){
			this.updateBySqlString(sql2);
		}
		//向u8推送数据
		List<LBaSupplierEntity> list = new ArrayList<>();
		list.add(entity);
		sendVouch(list);
 	}
 	
 	/**
 	 * 向u8推送数据
 	 * @param entity
 	 * @return
 	 */
 	public boolean sendVouch(List<LBaSupplierEntity> list){
 		//拼接xml格式
 		/*StringBuffer str = new StringBuffer();
 		splicingXML(list,str);
 		List<WebServiceModel> modeList = new ArrayList<WebServiceModel>();
 		WebServiceModel mode = null;
		mode = new WebServiceModel();
		mode.setParameterName("ctype");
		mode.setParameterValue("ven");
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
		}*/
 		return true;
 	}
 	
 	/**
 	 * 根据实体拼接对应的xml格式
 	 * @param entity
 	 */
 	public void splicingXML(List<LBaSupplierEntity> list, StringBuffer str){
	 	// 头
		str.append("<data>");
		str.append("<body>");
		for (LBaSupplierEntity entity : list) {
			str.append("<entry>");
			//查询供应商分类
			String supplyTypeId =entity.getSupplierTypeId();
			LBaSupplierTypeEntity lBaSupplierTypeEntity=systemService.get(LBaSupplierTypeEntity.class, supplyTypeId);
			//供应商分类编码
			str.append("<cvenclassbh>"+lBaSupplierTypeEntity.getTypeCode()+"</cvenclassbh>");
			//供应商编码
			str.append("<cvencode>"+entity.getSupplierCode()+"</cvencode>");
			//供应商名称
			str.append("<cvenname>"+entity.getSupplierFullName()+"</cvenname>");
			//供应商简称
			str.append("<cvenabbname>"+entity.getSupplierShortName()+"</cvenabbname>");
			str.append("</entry>");
		}
		// 尾
		str.append("</body>");
		str.append("</data>");
 	}
 	
 	/**
	 * 新增操作增强业务
	 * @param t
	 * @return
	 */
	private void doAddBus(LBaSupplierEntity t) throws Exception{
 	}
 	/**
	 * 更新操作增强业务
	 * @param t
	 * @return
	 */
	private void doUpdateBus(LBaSupplierEntity t) throws Exception{
 	}
 	/**
	 * 删除操作增强业务
	 * @param id
	 * @return
	 */
	private void doDelBus(LBaSupplierEntity t) throws Exception{
 	}
 	
 	private Map<String,Object> populationMap(LBaSupplierEntity t){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", t.getId());
		map.put("supplier_code", t.getSupplierCode());
		map.put("supplier_full_name", t.getSupplierFullName());
		map.put("supplier_short_name", t.getSupplierShortName());
		map.put("supplier_type_id", t.getSupplierTypeId());
		map.put("contacts", t.getContacts());
		map.put("telphone", t.getTelphone());
		map.put("address", t.getAddress());
		map.put("is_control", t.getIsControl());
		map.put("depart_id", t.getDepartId());
		map.put("create_by", t.getCreateBy());
		map.put("create_date", t.getCreateDate());
		map.put("update_by", t.getUpdateBy());
		map.put("update_date", t.getUpdateDate());
		return map;
	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @param t
	 * @return
	 */
 	public String replaceVal(String sql,LBaSupplierEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{supplier_code}",String.valueOf(t.getSupplierCode()));
 		sql  = sql.replace("#{supplier_full_name}",String.valueOf(t.getSupplierFullName()));
 		sql  = sql.replace("#{supplier_short_name}",String.valueOf(t.getSupplierShortName()));
 		sql  = sql.replace("#{supplier_type_id}",String.valueOf(t.getSupplierTypeId()));
 		sql  = sql.replace("#{contacts}",String.valueOf(t.getContacts()));
 		sql  = sql.replace("#{telphone}",String.valueOf(t.getTelphone()));
 		sql  = sql.replace("#{address}",String.valueOf(t.getAddress()));
 		sql  = sql.replace("#{is_control}",String.valueOf(t.getIsControl()));
 		sql  = sql.replace("#{depart_id}",String.valueOf(t.getDepartId()));
 		sql  = sql.replace("#{create_by}",String.valueOf(t.getCreateBy()));
 		sql  = sql.replace("#{create_date}",String.valueOf(t.getCreateDate()));
 		sql  = sql.replace("#{update_by}",String.valueOf(t.getUpdateBy()));
 		sql  = sql.replace("#{update_date}",String.valueOf(t.getUpdateDate()));
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

	@Override
	public AjaxJson checkQual(List<String> supplierIds) throws BusinessException {
		AjaxJson json = new AjaxJson();
		if(supplierIds==null || supplierIds.size()==0)
 			return json;
 		StringBuffer supplierIdBF = new StringBuffer(); 
 		for (String supplierId : supplierIds) {
 			supplierIdBF.append("'"+supplierId+"',");
		} 
 		String materialStr = supplierIdBF.substring(0,supplierIdBF.length()-1);
 		StringBuffer  sql = new StringBuffer();
 		
 		sql.append("select qitem.supplier_id,supplier.supplier_full_name ");
 		sql.append("from( ");
 		sql.append("SELECT MAX(item.effect_date),qual.supplier_id,qtype.ID ");
 		sql.append("FROM l_su_supplier_qual_item item ");
 		sql.append("LEFT JOIN l_su_supplier_qual qual on item.supplier_qual_id = qual.ID ");
 		sql.append("left JOIN l_su_qual_type qtype on item.qual_type_id = qtype.ID ");
 		sql.append("where qual.supplier_id in ( " +materialStr+ ") ");
 		sql.append("and qual.audit_status = '"+BillStatus.APPROVED+"' ");
 		sql.append(" AND item.is_control = '1' ");
 		sql.append("and (item.effect_date > "+StringUtil.tjDate(new Date())+" ");
 		sql.append("or item.over_date < "+StringUtil.tjDate(new Date())+") ");
 		sql.append("GROUP BY qtype.ID,qual.supplier_id ");
 		sql.append(") qitem ");
 		sql.append("LEFT JOIN l_ba_supplier supplier on qitem.supplier_id = supplier.ID ");
 		sql.append(" where supplier.is_control = '1' ");
 		
 		List<Map<String, Object>> list = this.findForJdbc(sql.toString());
 		
 		if(list.size() > 0){
 			String msg = "";
 			for(Map<String, Object> map:list){
 				msg = map.get("supplier_full_name") + "的资质," + msg;
 			}
 			msg = msg + "已过期,操作失败";
 			json.setMsg(msg);
 			json.setSuccess(false);
 			return json;
 		}
		
		return json;
	}

	@Override
	public Serializable save(List<LBaSupplierEntity> list) throws Exception {
		for(LBaSupplierEntity sup :list){
			this.save(sup);
		}
		return null;
	}
	

	
}