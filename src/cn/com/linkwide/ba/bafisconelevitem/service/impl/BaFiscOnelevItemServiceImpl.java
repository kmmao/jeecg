package cn.com.linkwide.ba.bafisconelevitem.service.impl;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.jeecgframework.core.common.model.json.ComboTree;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.util.ApplicationContextUtil;
import org.jeecgframework.core.util.MyClassLoader;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.web.cgform.enhance.CgformEnhanceJavaInter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.linkwide.ba.bafisconelevitem.entity.BaFiscOnelevItemEntity;
import cn.com.linkwide.ba.bafisconelevitem.service.BaFiscOnelevItemServiceI;

@Service("baFiscOnelevItemService")
@Transactional
public class BaFiscOnelevItemServiceImpl extends CommonServiceImpl implements BaFiscOnelevItemServiceI {

	
 	public void delete(BaFiscOnelevItemEntity entity) throws Exception{
 		super.delete(entity);
 		//执行删除操作增强业务
		this.doDelBus(entity);
 	}
 	
 	public Serializable save(BaFiscOnelevItemEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		//执行新增操作增强业务
 		this.doAddBus(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(BaFiscOnelevItemEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 		//执行更新操作增强业务
 		this.doUpdateBus(entity);
 	}
 	
 	/**
	 * 新增操作增强业务
	 * @param t
	 * @return
	 */
	private void doAddBus(BaFiscOnelevItemEntity t) throws Exception{
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
	private void doUpdateBus(BaFiscOnelevItemEntity t) throws Exception{
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
	private void doDelBus(BaFiscOnelevItemEntity t) throws Exception{
	    //-----------------sql增强 start----------------------------
	 	//-----------------sql增强 end------------------------------
	 	
	 	//-----------------java增强 start---------------------------
	 	//-----------------java增强 end-----------------------------
 	}
 	
 	private Map<String,Object> populationMap(BaFiscOnelevItemEntity t){
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
		map.put("parent_code", t.getParentCode());
		map.put("is_last", t.getIsLast());
		map.put("is_stop", t.getIsStop());
		map.put("item_memo", t.getItemMemo());
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
 	public String replaceVal(String sql,BaFiscOnelevItemEntity t){
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
 		sql  = sql.replace("#{parent_code}",String.valueOf(t.getParentCode()));
 		sql  = sql.replace("#{is_last}",String.valueOf(t.getIsLast()));
 		sql  = sql.replace("#{is_stop}",String.valueOf(t.getIsStop()));
 		sql  = sql.replace("#{item_memo}",String.valueOf(t.getItemMemo()));
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
					javaInter.execute("ba_fisc_onelev_item",data);
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("执行JAVA增强出现异常！");
			} 
		}
 	}

 	@Override
	public void tree1(List<ComboTree> rootLists, HttpServletRequest request) {
		
		String hql = "from BaFiscOnelevItemEntity where (parentCode is null or parentCode ='') and isStop = 'N' ";
		List<BaFiscOnelevItemEntity> onelevItems = this.findByQueryString(hql);
//		List<BaFiscOnelevItemEntity> onelevItems = this.findListbySql(hql);
		if(onelevItems!=null&&onelevItems.size()>0){
			//将所有 预算科室拼成预算科室树
			ComboTree comT=new ComboTree();
			comT.setText("所属一级项目");
			ComboTree comboTree = loadAllFundsSource(onelevItems, comT);
			rootLists.add(comboTree);
		}
	}

	public ComboTree loadAllFundsSource(List<BaFiscOnelevItemEntity> list,ComboTree combotree){
		if(list!=null&&list.size()>0){
			List<ComboTree> comTreeList=new ArrayList<ComboTree>();
			for(BaFiscOnelevItemEntity ba:list){
				ComboTree com=new ComboTree();
				com.setId(ba.getVcode());
				com.setText(ba.getVname());
				//查询所有下级地区
				String hql="from BaFiscOnelevItemEntity a where a.parentCode= ? ";
				List<BaFiscOnelevItemEntity> comebasList = this.findHql(hql,ba.getVcode());
				if(comebasList.size()>0){
					loadAllFundsSource(comebasList, com);
				}
				comTreeList.add(com);
				combotree.setChildren(comTreeList);
			}
		}
		return combotree;
	}

	@Override
	public void treeOnelev(List<org.jeecgframework.core.common.model.json.ComboTree> rootLists,
			HttpServletRequest request) {
		
		String budgYear = request.getParameter("budgYear");
		String hql = "from BaFiscOnelevItemEntity where (parentCode is null or parentCode ='') and isStop = 'N' and budgYear = "+budgYear+" ";
		List<BaFiscOnelevItemEntity> onelevItems = this.findByQueryString(hql);
		if(onelevItems!=null&&onelevItems.size()>0){
			//将所有 预算科室拼成预算科室树
			ComboTree comT=new ComboTree();
			comT.setText("所属一级项目");
			ComboTree comboTree = loadAllFundsSource(onelevItems, comT, budgYear);
			rootLists.add(comboTree);
		}
		
	}

	private org.jeecgframework.core.common.model.json.ComboTree loadAllFundsSource(
			List<BaFiscOnelevItemEntity> onelevItems, org.jeecgframework.core.common.model.json.ComboTree combotree,
			String budgYear) {
		
		if(onelevItems!=null&&onelevItems.size()>0){
			List<ComboTree> comTreeList=new ArrayList<ComboTree>();
			for(BaFiscOnelevItemEntity ba:onelevItems){
				ComboTree com=new ComboTree();
				com.setId(ba.getVcode());
				com.setText(ba.getVname());
				//查询所有下级地区
				String hql="from BaFiscOnelevItemEntity a where a.parentCode= ? and budgYear = "+budgYear+" ";
				List<BaFiscOnelevItemEntity> comebasList = this.findHql(hql,ba.getVcode());
				if(comebasList.size()>0){
					loadAllFundsSource(comebasList, com);
				}
				comTreeList.add(com);
				combotree.setChildren(comTreeList);
			}
		}
		return combotree;
		
	}
}