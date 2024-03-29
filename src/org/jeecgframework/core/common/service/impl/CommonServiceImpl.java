package org.jeecgframework.core.common.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.jeecgframework.core.common.dao.ICommonDao;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.hibernate.qbc.HqlQuery;
import org.jeecgframework.core.common.hibernate.qbc.PageList;
import org.jeecgframework.core.common.model.common.DBTable;
import org.jeecgframework.core.common.model.common.UploadFile;
import org.jeecgframework.core.common.model.json.ComboTree;
import org.jeecgframework.core.common.model.json.ImportFile;
import org.jeecgframework.core.common.model.json.TreeGrid;
import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.tag.vo.datatable.DataTableReturn;
import org.jeecgframework.tag.vo.easyui.Autocomplete;
import org.jeecgframework.tag.vo.easyui.ComboTreeModel;
import org.jeecgframework.tag.vo.easyui.TreeGridModel;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("commonService")
@Transactional
public class CommonServiceImpl implements CommonService {
	public ICommonDao commonDao = null;

	/**
	 * 获取所有数据库表
	 * 
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<DBTable> getAllDbTableName() {
		return commonDao.getAllDbTableName();
	}

	@Transactional(readOnly = true)  
	public Integer getAllDbTableSize() {
		return commonDao.getAllDbTableSize();
	}

	@Resource
	public void setCommonDao(ICommonDao commonDao) {
		this.commonDao = commonDao;
	}

	
	public <T> Serializable save(T entity) {
		return commonDao.save(entity);
	}

	
	public <T> void saveOrUpdate(T entity) {
		commonDao.saveOrUpdate(entity);

	}

	
	public <T> void delete(T entity) {
		commonDao.delete(entity);

	}

	/**
	 * 删除实体集合
	 * 
	 * @param <T>
	 * @param entities
	 */
	public <T> void deleteAllEntitie(Collection<T> entities) {
		commonDao.deleteAllEntitie(entities);
	}

	/**
	 * 根据实体名获取对象
	 */
	@Transactional(readOnly = true)  
	public <T> T get(Class<T> class1, Serializable id) {
		return commonDao.get(class1, id);
	}

	/**
	 * 根据实体名返回全部对象
	 * 
	 * @param <T>
	 * @param hql
	 * @param size
	 * @return
	 */
    @Transactional(readOnly = true)
	public <T> List<T> getList(Class clas) {
		return commonDao.loadAll(clas);
	}

	/**
	 * 根据实体名获取对象
	 */
    @Transactional(readOnly = true)
	public <T> T getEntity(Class entityName, Serializable id) {
		return commonDao.getEntity(entityName, id);
	}

	/**
	 * 根据实体名称和字段名称和字段值获取唯一记录
	 * 
	 * @param <T>
	 * @param entityClass
	 * @param propertyName
	 * @param value
	 * @return
	 */
    @Transactional(readOnly = true)
	public <T> T findUniqueByProperty(Class<T> entityClass,
			String propertyName, Object value) {
		return commonDao.findUniqueByProperty(entityClass, propertyName, value);
	}

	/**
	 * 按属性查找对象列表.
	 */
    @Transactional(readOnly = true)
	public <T> List<T> findByProperty(Class<T> entityClass,
			String propertyName, Object value) {

		return commonDao.findByProperty(entityClass, propertyName, value);
	}

	/**
	 * 加载全部实体
	 * 
	 * @param <T>
	 * @param entityClass
	 * @return
	 */
    @Transactional(readOnly = true)
	public <T> List<T> loadAll(final Class<T> entityClass) {
		return commonDao.loadAll(entityClass);
	}

    @Transactional(readOnly = true)
	public <T> T singleResult(String hql) {
		return commonDao.singleResult(hql);
	}

	/**
	 * 删除实体主键ID删除对象
	 * 
	 * @param <T>
	 * @param entities
	 */
	public <T> void deleteEntityById(Class entityName, Serializable id) {
		commonDao.deleteEntityById(entityName, id);
	}

	/**
	 * 更新指定的实体
	 * 
	 * @param <T>
	 * @param pojo
	 */
	public <T> void updateEntitie(T pojo) {
		commonDao.updateEntitie(pojo);

	}

	/**
	 * 通过hql 查询语句查找对象
	 * 
	 * @param <T>
	 * @param query
	 * @return
	 */
	@Transactional(readOnly = true)
	public <T> List<T> findByQueryString(String hql) {
		return commonDao.findByQueryString(hql);
	}

	/**
	 * 根据sql更新
	 * 
	 * @param query
	 * @return
	 */
	public int updateBySqlString(String sql) {
		return commonDao.updateBySqlString(sql);
	}

	/**
	 * 根据sql查找List
	 * 
	 * @param <T>
	 * @param query
	 * @return
	 */
	@Transactional(readOnly = true)
	public <T> List<T> findListbySql(String query) {
		return commonDao.findListbySql(query);
	}

	/**
	 * 通过属性称获取实体带排序
	 * 
	 * @param <T>
	 * @param clas
	 * @return
	 */
	@Transactional(readOnly = true)
	public <T> List<T> findByPropertyisOrder(Class<T> entityClass,
			String propertyName, Object value, boolean isAsc) {
		return commonDao.findByPropertyisOrder(entityClass, propertyName,
				value, isAsc);
	}

	/**
	 * 
	 * cq方式分页
	 * 
	 * @param cq
	 * @param isOffset
	 * @return
	 */
	@Transactional(readOnly = true)
	public PageList getPageList(final CriteriaQuery cq, final boolean isOffset) {
		return commonDao.getPageList(cq, isOffset);
	}

	/**
	 * 返回DataTableReturn模型
	 * 
	 * @param cq
	 * @param isOffset
	 * @return
	 */
	@Transactional(readOnly = true)
	public DataTableReturn getDataTableReturn(final CriteriaQuery cq,
			final boolean isOffset) {
		return commonDao.getDataTableReturn(cq, isOffset);
	}

	/**
	 * 返回easyui datagrid模型
	 * 
	 * @param cq
	 * @param isOffset
	 * @return
	 */

	@Transactional(readOnly = true)
	public void getDataGridReturn(final CriteriaQuery cq,
			final boolean isOffset) {
		commonDao.getDataGridReturn(cq, isOffset);

	}

	/**
	 * 
	 * hqlQuery方式分页
	 * 
	 * @param cq
	 * @param isOffset
	 * @return
	 */
	@Transactional(readOnly = true)
	public PageList getPageList(final HqlQuery hqlQuery,
			final boolean needParameter) {
		return commonDao.getPageList(hqlQuery, needParameter);
	}

	/**
	 * 
	 * sqlQuery方式分页
	 * 
	 * @param cq
	 * @param isOffset
	 * @return
	 */
	@Transactional(readOnly = true)
	public PageList getPageListBySql(final HqlQuery hqlQuery,
			final boolean isToEntity) {
		return commonDao.getPageListBySql(hqlQuery, isToEntity);
	}

	public Session getSession()

	{
		return commonDao.getSession();
	}

	@Transactional(readOnly = true)
	public List findByExample(final String entityName,
			final Object exampleEntity) {
		return commonDao.findByExample(entityName, exampleEntity);
	}

	/**
	 * 通过cq获取全部实体
	 * 
	 * @param <T>
	 * @param cq
	 * @return
	 */
	@Transactional(readOnly = true)
	public <T> List<T> getListByCriteriaQuery(final CriteriaQuery cq,
			Boolean ispage) {
		return commonDao.getListByCriteriaQuery(cq, ispage);
	}

	/**
	 * 文件上传
	 * 
	 * @param request
	 */
	public <T> T uploadFile(UploadFile uploadFile) {
		return commonDao.uploadFile(uploadFile);
	}

	@Transactional(readOnly = true)
	public HttpServletResponse viewOrDownloadFile(UploadFile uploadFile)

	{
		return commonDao.viewOrDownloadFile(uploadFile);
	}

	/**
	 * 生成XML文件
	 * 
	 * @param fileName
	 *            XML全路径
	 * @return
	 */
	public HttpServletResponse createXml(ImportFile importFile) {
		return commonDao.createXml(importFile);
	}

	/**
	 * 解析XML文件
	 * 
	 * @param fileName
	 *            XML全路径
	 */
	public void parserXml(String fileName) {
		commonDao.parserXml(fileName);
	}

	@Transactional(readOnly = true)
	public List<ComboTree> comTree(List<TSDepart> all, ComboTree comboTree) {
		return commonDao.comTree(all, comboTree);
	}

	@Transactional(readOnly = true)
	public List<ComboTree> ComboTree(List all, ComboTreeModel comboTreeModel, List in, boolean recursive) {
        return commonDao.ComboTree(all, comboTreeModel, in, recursive);
	}

	/**
	 * 构建树形数据表
	 */
	@Transactional(readOnly = true)
	public List<TreeGrid> treegrid(List<?> all, TreeGridModel treeGridModel) {
		return commonDao.treegrid(all, treeGridModel);
	}

	/**
	 * 获取自动完成列表
	 * 
	 * @param <T>
	 * @return
	 */
	@Transactional(readOnly = true)
	public <T> List<T> getAutoList(Autocomplete autocomplete) {
		StringBuffer sb = new StringBuffer("");
		for (String searchField : autocomplete.getSearchField().split(",")) {
			sb.append("  or " + searchField + " like '%"+ autocomplete.getTrem() + "%' ");
		}
		String hql = "from " + autocomplete.getEntityName() + " where 1!=1 "+ sb.toString();
		return commonDao.getSession().createQuery(hql).setFirstResult(autocomplete.getCurPage() - 1).setMaxResults(autocomplete.getMaxRows()).list();
	}

	
	public Integer executeSql(String sql, List<Object> param) {
		return commonDao.executeSql(sql, param);
	}

	
	public Integer executeSql(String sql, Object... param) {
		return commonDao.executeSql(sql, param);
	}

	
	public Integer executeSql(String sql, Map<String, Object> param) {
		return commonDao.executeSql(sql, param);
	}
	
	public Object executeSqlReturnKey(String sql, Map<String, Object> param) {
		return commonDao.executeSqlReturnKey(sql, param);
	}
	
	@Transactional(readOnly = true)
	public List<Map<String, Object>> findForJdbc(String sql, int page, int rows) {
		return commonDao.findForJdbc(sql, page, rows);
	}

	@Transactional(readOnly = true)
	public List<Map<String, Object>> findForJdbc(String sql, Object... objs) {
		return commonDao.findForJdbc(sql, objs);
	}

	@Transactional(readOnly = true)
	public List<Map<String, Object>> findForJdbcParam(String sql, int page,
			int rows, Object... objs) {
		return commonDao.findForJdbcParam(sql, page, rows, objs);
	}

	@Transactional(readOnly = true)
	public <T> List<T> findObjForJdbc(String sql, int page, int rows,
			Class<T> clazz) {
		return commonDao.findObjForJdbc(sql, page, rows, clazz);
	}

	@Transactional(readOnly = true)
	public Map<String, Object> findOneForJdbc(String sql, Object... objs) {
		return commonDao.findOneForJdbc(sql, objs);
	}

	@Transactional(readOnly = true)
	public Long getCountForJdbc(String sql) {
		return commonDao.getCountForJdbc(sql);
	}
	@Transactional(readOnly = true)
	public Long getCountForJdbcParam(String sql, Object... objs) {
		return commonDao.getCountForJdbcParam(sql,objs);
	}

	
	public <T> void batchSave(List<T> entitys) {
		this.commonDao.batchSave(entitys);
	}

	/**
	 * 通过hql 查询语句查找对象
	 * 
	 * @param <T>
	 * @param query
	 * @return
	 */
	@Transactional(readOnly = true)
	public <T> List<T> findHql(String hql, Object... param) {
		return this.commonDao.findHql(hql, param);
	}

	@Transactional(readOnly = true)
	public <T> List<T> pageList(DetachedCriteria dc, int firstResult,
			int maxResult) {
		return this.commonDao.pageList(dc, firstResult, maxResult);
	}

	@Transactional(readOnly = true)
	public <T> List<T> findByDetached(DetachedCriteria dc) {
		return this.commonDao.findByDetached(dc);
	}

	/**
	 * 调用存储过程
	 */
	public <T> List<T> executeProcedure(String procedureSql,Object... params) {
		return this.commonDao.executeProcedure(procedureSql, params);
	}
	@Override
	public Integer executeHql(String hql) {
		return this.commonDao.executeHql(hql);
	}
	
	
	public <T> Map<String, T> getMapByHql(Class<T> entityClass, String hql, String key, Object... param) { 
		List<T> list = commonDao.findHql(hql, param);
		return list2Map(entityClass,list,key);
	}

	public Map<String, Map<String, Object>> getMapBySql(String sql, String key, Object... objs) {
		List<Map<String, Object>> list = commonDao.findForJdbc(sql, objs);
		return list2Map(list,key);
	}

	public <T> Map<String, T> list2Map(Class<T> entityClass,List<T> list, String key) {
		if(list == null ||list.isEmpty()){
			return new HashMap<String, T>();
		}
		if(key == null || "".equals(key)){
			key = "id";
		}
		String[] keyArray = key.split(",");
		Field[] keyField = new Field[keyArray.length]; 
		Map<String, T> map = new HashMap<String, T>();
		try{
			Field[] fs = entityClass.getDeclaredFields();
		    Field field =fs[0];
			for(int i = 0 ; i < fs.length; i++){
				field = fs[i];
				field.setAccessible(true); //设置些属性是可以访问的
				for(int j=0;j<keyArray.length;j++){
					if(field.getName().equals(keyArray[j])){
						 keyField[j] =field;
			         }
				}
		    } 
			for(T obj :list){ 
				String keyStr = "";
				for(int j=0;j<keyArray.length;j++){ 
					if(keyField[j].get(obj) != null)
					keyStr+=keyField[j].get(obj).toString(); 
				}
				if(!"".equals(keyStr))
					map.put(keyStr, obj); 
			}
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
		return map;
	}

	public Map<String, Map<String, Object>> list2Map(List<Map<String, Object>> list, String key) {
		if(list == null ||list.isEmpty()){
			return new HashMap<String, Map<String, Object>>();
		}
		if(key == null || "".equals(key)){
			key = "id";
		}
		String[] keyArray = key.split(",");
		Map<String, Map<String, Object>> map = new HashMap<String, Map<String, Object>>();
		for(Map<String, Object> map0 :list){
			String keyStr = "";
			for(int j=0;j<keyArray.length;j++){ 
				keyStr+=map0.get(keyArray[j]).toString(); 
			}
			map.put(keyStr, map0);
		}
		return map;
	}
	
	/**
	 * 通过hql 分页查询语句查找对象
	 * 
	 * @param <T>
	 * @param query
	 * @return
	 */
	public <T> List<T> findHqlPage(String hql,int page,int rows, Object... param) {
		return this.commonDao.findHqlPage(hql, page,rows, param);
	}
}
