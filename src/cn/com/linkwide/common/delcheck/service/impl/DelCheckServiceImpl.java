package cn.com.linkwide.common.delcheck.service.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;
import org.jeecgframework.core.constant.DataBaseConstant;
import org.jeecgframework.core.util.ReflectHelper;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.springframework.stereotype.Service;

import cn.com.linkwide.common.delcheck.annotation.DetailedEntity;
import cn.com.linkwide.common.delcheck.annotation.QueryParameterSettings;
import cn.com.linkwide.common.delcheck.service.DelCheckServiceI;


/**
 * 处理参照查询
 * @author asus
 *
 */
@Service("delCheck")
public class DelCheckServiceImpl extends CommonServiceImpl implements DelCheckServiceI{

	/**
	 * 普通删除时的删除校验
	 * @param c
	 * @param obj
	 * @return
	 */
	public String generalDeleteCheck(Class c,Object obj) {
		return del(c,obj,false);
	}
	
	public String collectivizeDeleteCheck(Class c,Object obj) {
		return del(c,obj,true);
	}
	
	@SuppressWarnings("all")
	public String del(Class c,Object obj,boolean isCollectivize) {
		String message = "";
		DetailedEntity detailedEntity = (DetailedEntity) c.getAnnotation(DetailedEntity.class);
		if(StringUtil.isNotEmpty(detailedEntity)) {
			DetachedCriteria dc = null;
			Class[] entity = entityNameType(detailedEntity);
			boolean isSelect;
			if(StringUtil.isNotEmpty(entity)) {
				for (Class class1 : entity) {
					dc = DetachedCriteria.forClass(class1);
					isSelect = false;
					Field[] declaredFields = class1.getDeclaredFields();
					for (Field field : declaredFields) {
						QueryParameterSettings queryParam = field.getAnnotation(QueryParameterSettings.class);
						if(StringUtil.isNotEmpty(queryParam)) {
							if(StringUtil.isNotEmpty(queryParam.message())) {
								message = queryParam.message();
							}
							// 处理对照关系
							if(StringUtil.isNotEmpty(queryParam.entityStr())) {
								if(queryParam.entityStr().equals(c.getSimpleName())) {
									isSelect = true;
									// 判断是否是正在要处理的参照查询    propertyValue不等于空是查询条件
									if(StringUtil.isNotEmpty(queryParam.propertyValue())) {
										dc.add(Restrictions.eq(field.getName(), queryParam.propertyValue()));
									}else {
										dc.add(Restrictions.eq(field.getName(), obj));
									}
								}
							}else {
									isSelect = true;
									// 判断是否是正在要处理的参照查询    propertyValue不等于空是查询条件
									if(StringUtil.isNotEmpty(queryParam.propertyValue())) {
										dc.add(Restrictions.eq(field.getName(), queryParam.propertyValue()));
									}else {
										dc.add(Restrictions.eq(field.getName(), obj));
									}
							}
						}
					}
					if(isSelect) {
						Criteria criteria = dc.getExecutableCriteria(this.commonDao.getSession());
						// 查询存在的数据
						int allCounts = ((Long) criteria.setProjection(
								Projections.rowCount()).uniqueResult()).intValue();
						if(allCounts>0) {
							break;
						}else {
							// 清空
							message ="";
						}
					}else {
						// 清空
						message ="";
					}
				}
			}
		}
		return message;
	}

	@Override
	public String specifiedParameterDeleteCheck(Class c, Object obj, String defaultParameterName) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		if(StringUtil.isEmpty(defaultParameterName)) {
			throw new BusinessException("默认属性名不能为空!");
		}
		String message = "";
		DetailedEntity detailedEntity = (DetailedEntity) c.getAnnotation(DetailedEntity.class);
		if(StringUtil.isNotEmpty(detailedEntity)) {
			DetachedCriteria dc = null;
			Class[] entity = entityNameType(detailedEntity);
			boolean isSelect;
			if(StringUtil.isNotEmpty(entity)) {
				for (Class class1 : entity) {
					dc = DetachedCriteria.forClass(class1);
					isSelect = false;
					Field[] declaredFields = class1.getDeclaredFields();
					for (Field field : declaredFields) {
						QueryParameterSettings queryParam = field.getAnnotation(QueryParameterSettings.class);
						if(StringUtil.isNotEmpty(queryParam)) {
							if(StringUtil.isNotEmpty(queryParam.message())) {
								message = queryParam.message();
							}
							// 处理对照关系
							if(StringUtil.isNotEmpty(queryParam.entityStr())) {
								if(queryParam.entityStr().equals(c.getSimpleName())) {
									isSelect = true;
									// 判断是否是正在要处理的参照查询    propertyValue不等于空是查询条件
									combinatorialQuery(queryParam,field.getName(),obj,dc,defaultParameterName);
								}
							}else {
//								if(queryParam.entityStr().equals(c.getSimpleName())) {
									isSelect = true;
									// 判断是否是正在要处理的参照查询    propertyValue不等于空是查询条件
									combinatorialQuery(queryParam,field.getName(),obj,dc,defaultParameterName);
//								}
							}
						}
					}
					if(isSelect) {
						Criteria criteria = dc.getExecutableCriteria(this.commonDao.getSession());
						// 查询存在的数据
						int allCounts = ((Long) criteria.setProjection(
								Projections.rowCount()).uniqueResult()).intValue();
						if(allCounts>0) {
							break;
						}else {
							// 清空
							message ="";
						}
					}else {
						// 清空
						message ="";
					}
				}
			}
		}
		return message;
	}
	/**
	 * 处理实体类型
	 * @param detailedEntity
	 * @param entity
	 */
	public Class[] entityNameType(DetailedEntity detailedEntity) {
		Class[] entity = detailedEntity.entity();
		if(entity.length==0) {
			List<Class> list = new ArrayList<Class>();
			String[] strEntity = detailedEntity.strEntity();
			for (String str : strEntity) {
				if(ResourceUtil.classMap.containsKey(str)) {
					list.add(ResourceUtil.classMap.get(str));
				}
			}
			if(list.size()!=0) {
				entity = list.toArray(new Class[list.size()]);
			}
		}
		return entity;
	}
	// 判断是否是正在要处理的参照查询    propertyValue不等于空是查询条件
	public void combinatorialQuery(QueryParameterSettings queryParam,String parameterName,Object obj,DetachedCriteria dc,String mainParameterName) {
		if(StringUtil.isNotEmpty(queryParam.propertyValue())) {
			dc.add(Restrictions.eq(parameterName, queryParam.propertyValue()));
		}else {
			if(!StringUtil.isNotEmpty(queryParam.mainEntityParameterName())) {
				dc.add(Restrictions.eq(parameterName, ReflectHelper.getFieldValueByName(mainParameterName, obj)));
			}else {
				dc.add(Restrictions.eq(parameterName, ReflectHelper.getFieldValueByName(queryParam.mainEntityParameterName(), obj)));
			}
		}
	}
	
}
