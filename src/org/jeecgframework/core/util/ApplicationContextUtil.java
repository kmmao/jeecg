package org.jeecgframework.core.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 
 * @author  张代浩
 *
 */
public class ApplicationContextUtil implements ApplicationContextAware {

	private static ApplicationContext context;

	
	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		this.context = context;
	}

	public static ApplicationContext getContext() {
		return context;
	}
	/**
	 * 从静态变量ApplicationContext中取得Bean, 自动转型为所赋值对象的类型.
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name) {
		return (T) context.getBean(name);
	}
}
