package cn.com.linkwide.common.delcheck.service;

public interface DelCheckServiceI {
	
	/**
	 * 普通校验
	 * @param c
	 * @param obj
	 * @return
	 */
	public String generalDeleteCheck(Class c,Object obj);
	/**
	 * 集团化校验
	 * @param c
	 * @param obj
	 * @return
	 */
	public String collectivizeDeleteCheck(Class c,Object obj);
	/***
	 * 指定参数过滤条件
	 * @param c			主实体
	 * @param obj		主实体值
	 * @param defaultParameterName		默认属性
	 * @return
	 */
	public String specifiedParameterDeleteCheck(Class c,Object obj,String defaultParameterName)throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException;
}
