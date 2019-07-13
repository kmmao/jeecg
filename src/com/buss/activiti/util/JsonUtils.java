package com.buss.activiti.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class JsonUtils {

	/**
	 * 驼峰命名转为下划线命名
	 * 
	 * @param para
	 * @return
	 */
	public static String HumpToUnderline(String para) {
		StringBuilder sb = new StringBuilder(para);
		int temp = 0;// 定位
		for (int i = 0; i < para.length(); i++) {
			if (Character.isUpperCase(para.charAt(i))) {
				sb.insert(i + temp, "_");
				temp += 1;
			}
		}
		return sb.toString().toUpperCase();
	}

	/***
	 * 下划线命名转为驼峰命名
	 * 
	 * @param para
	 * @return
	 */
	public static String UnderlineToHump(String para) {
		StringBuilder result = new StringBuilder();
		String a[] = para.split("_");
		for (String s : a) {
			if (result.length() == 0) {
				result.append(s.toLowerCase());
			} else {
				result.append(s.substring(0, 1).toUpperCase());
				result.append(s.substring(1).toLowerCase());
			}
		}
		return result.toString();
	}

	/**
	 * 转换全小写
	 * 
	 * @param listMap
	 * @return
	 */
	public static List<Map<String, Object>> toUpperCase(List<Map<String, Object>> listMap) {
		// 再new一个Map类型的List集合放转换大写后的集合
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		// 将集合遍历
		for (int i = 0; i < listMap.size(); i++) {
			// 循环new map集合，
			Map<String, Object> obdmap = new HashMap<String, Object>();
			Set<String> se = listMap.get(i).keySet();
			for (String set : se) {
				// 在循环将大写的KEY和VALUE 放到新的Map
				obdmap.put(set.toLowerCase(), listMap.get(i).get(set));
				// 转换为大写
				// obdmap.put(set.toUpperCase(), listMap.get(i).get(set));

			}
			// 将Map放进List集合里
			list.add(obdmap);
		}
		// System.out.println("转换"+body);
		return list;
	}

	/**
	 * 将List中map的key值命名方式格式化为驼峰
	 *
	 * @param
	 * @return
	 */
	public static List<Map<String, Object>> formatHump(List<Map<String, Object>> list) {
		List<Map<String, Object>> newList = new ArrayList<Map<String, Object>>();
		for (Map<String, Object> o : list) {
			newList.add(formatHumpName(o));
		}
		return newList;
	}

	/**
	 * 将Map中的key由下划线转换为驼峰
	 *
	 * @param map
	 * @return
	 */
	public static Map<String, Object> formatHumpName(Map<String, Object> map) {
		Map<String, Object> newMap = new HashMap<String, Object>();
		Iterator<Map.Entry<String, Object>> it = map.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Object> entry = it.next();
			String key = entry.getKey();
			String newKey = toFormatCol(key);
			newMap.put(newKey, entry.getValue());
		}
		return newMap;
	}

	/**
	 * 单字段转换
	 * 
	 * @param colName
	 *            key
	 * @return
	 */
	public static String toFormatCol(String colName) {
		StringBuilder sb = new StringBuilder();
		String[] str = colName.toLowerCase().split("_");
		int i = 0;
		for (String s : str) {
			if (s.length() == 1) {
				s = s.toUpperCase();
			}
			i++;
			if (i == 1) {
				sb.append(s);
				continue;
			}
			if (s.length() > 0) {
				sb.append(s.substring(0, 1).toUpperCase());
				sb.append(s.substring(1));
			}
		}
		return sb.toString();
	}

}
