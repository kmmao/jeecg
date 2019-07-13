package cn.com.linkwide.common.list.service.impl;
import java.util.List;
import java.util.Map;

import org.jeecgframework.core.common.model.json.ComboTree;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.linkwide.common.list.service.DictListServiceI; 
@Service("dictListService")
@Transactional
public class DictListServiceImpl extends CommonServiceImpl implements DictListServiceI {

	@Override
	public void departTree(List<org.jeecgframework.core.common.model.json.ComboTree> rootLists, String code) {
		List<Map<String, Object>> list = this.commonDao.findForJdbc("select id as id ,departname as name,parentdepartid as  parentId from t_s_depart where parentdepartid  ='"+(code ==null ? "" :code)+"' or parentdepartid is null   ", null);
		ComboTree comboTree = null; 
		for (Map<String, Object> typeEntity : list) {
			// 因处理的数据不是特别大采用查询方式
			//查询有没有子节点
			List<Map<String, Object>> list2 = this.commonDao.findForJdbc("select id as id ,departname as name,parentdepartid as  parentId from t_s_depart where parentdepartid ='"+typeEntity.get("id")+"'  ", null);
			if(list2.size()>0 && list2.get(0)!=null){ 
				comboTree = new ComboTree();
				comboTree.setState("closed");
				comboTree.setId(typeEntity.get("id").toString());
				comboTree.setText(typeEntity.get("name").toString());
			}else{
				comboTree = new ComboTree();
				comboTree.setState("open");
				comboTree.setId(typeEntity.get("id").toString());
				comboTree.setText(typeEntity.get("name").toString()); 
			}
			rootLists.add(comboTree); 
		
		}
		
	}

	 

 
}