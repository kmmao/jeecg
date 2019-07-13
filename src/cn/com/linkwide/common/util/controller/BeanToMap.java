package cn.com.linkwide.common.util.controller;

import java.util.HashMap;
import java.util.Map;

import net.sf.cglib.beans.BeanMap;

public class BeanToMap {

	public static <T> Map<String, Object> beanToMap(T bean){
		Map<String,Object> map = new HashMap<String,Object>();
		if (bean != null) {
			BeanMap beanMap = BeanMap.create(bean);
			for(Object key:beanMap.keySet()){
				map.put(key+"", beanMap.get(key));
			}
		}
		return map;
	}
}
