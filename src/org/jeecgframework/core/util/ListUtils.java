package org.jeecgframework.core.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;

public class ListUtils {

	/**
	 * 按新的Class对象复制
	 * 
	 * @param source list, example: list
	 * @param dest class, example: UserEntity.class;
	 */
	public static <E> List<E> copyTo(List<?> source, Class<E> destinationClass)
			throws Exception {
		if (source.size() == 0)
			return new ArrayList();
		List<E> res = new ArrayList<E>(source.size());
		for (Object o : source) {
			E e = destinationClass.newInstance();
			BeanUtils.copyProperties(e, o);
			res.add(e);
		}
		return res;
	}
	
	public static boolean isNullOrEmpty(List list)
	{
		return list == null || list.isEmpty();
	}
	
	/**
	 * 从list<实体> 中获取某个一个属性值
	 * @param source				操作list
	 * @param prop					属性名
	 * @return						返回获取后的list
	 * @throws Exception
	 */
	@SuppressWarnings("all")
	public static <E> List<E> extractProperty(List<?> source,String prop)throws Exception {
		if (source.size() == 0) {
			return new ArrayList();
		}
		List<E> res = new ArrayList<E>();
		for (Object o : source) {
			Class c = (Class) o.getClass();
			//获取属性集合
			Field[] fields = c.getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
	            Field f = fields[i];
	            f.setAccessible(true); // 设置些属性是可以访问的
	            try {
	            
	                if (f.getName().endsWith(prop)) {
	                	res.add((E) f.get(o));
	                }
	            } catch (IllegalArgumentException e) {
	                e.printStackTrace();
	            } catch (IllegalAccessException e) {
	                e.printStackTrace();
	            }
	        }
		}
		return res;
	}
	/**
	 * 集合查重
	 * @param datas
	 * @author heyc
	 * @return
	 */
	public static boolean isRepeat(Collection<?> col) {
		if (col == null) {// 为空则认为不含重复元素
			return false;
		}
		if (col instanceof Set) {// 如果是set则不含有重复元素
			return false;
		}
		Set<?> noRepeatSet = new HashSet<>(col);
		return !(col.size() == noRepeatSet.size());
	}
	
	/**
     * Map转成实体对象
     * @param map map实体对象包含属性
     * @param clazz 实体对象类型
     * @return
     */
    public static <T> T map2Object(Map<String, Object> map, Class<T> clazz) {
        if (map == null) {
            return null;
        }
        T obj = null;
        try {
            obj = clazz.newInstance();
            Field[] fields = obj.getClass().getDeclaredFields();
            for (Field field : fields) {
                int mod = field.getModifiers();
                if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
                    continue;
                }
                field.setAccessible(true);
                if(StringUtil.isNotEmpty(map.get(field.getName()))) {
                	field.set(obj,map.get(field.getName()).toString() );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return obj;
    }
    
	/**
	 * list分组求和
	 * @param entityClass
	 * @param list
	 * @param key
	 * @param val 求和属性
	 * @return
	 */
	public static  Map<String, Double> listSum(Class<?> entityClass,List<?> list, String key,String val) {
		if(list == null ||list.isEmpty()){
			return new HashMap<String, Double>();
		}
		if(key == null || "".equals(key)){
			key = "id";
		}
		String[] keyArray = key.split(",");
		Field[] keyField = new Field[keyArray.length]; 
		Field valField = null ; 
		Map<String, Double> map = new HashMap<String, Double>();
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
					if(field.getName().equals(val)){
						valField  =field;
			         }
				}
		    } 
			for(Object obj :list){ 
				String keyStr = "";
				for(int j=0;j<keyArray.length;j++){ 
					if(keyField[j].get(obj) != null)
					keyStr+=keyField[j].get(obj).toString(); 
				}
				Double v = 0.0;
				if(valField.get(obj) != null ){
					v = Double.valueOf(valField.get(obj).toString());
				}
				
				if(!"".equals(keyStr)){
					if(map.containsKey(keyStr)){
						v += map.get(keyStr);
					} 
					map.put(keyStr, v); 
				}
			}
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
		return map;
	}
	
	public static List<String> ListMap2ListStr(List<Map<String,Object>> list ,String key){
		List<String> li = new ArrayList();
		for(Map<String,Object> m : list){
			if(m.get(key)!= null){
				li.add(m.get(key).toString());
			}
		}
		return li;
	}
}
