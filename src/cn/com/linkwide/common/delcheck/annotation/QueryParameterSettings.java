package cn.com.linkwide.common.delcheck.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 查询参数设置
 * @author asus
 * 增加对照关系---李伟华
 *
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface QueryParameterSettings {
	
	/**
	 * 属性值    
	 * PS：设置值是为了增加查询条件
	 * @return
	 */
	String propertyValue() default "";
	
	/**
	 * 提示消息
	 * @return
	 */
	String message() default "";
	/***
	 * 实体对照关系
	 * 类型 String
	 * @return
	 */
	String entityStr() default "";
	/***
	 * 主实体上的参数名
	 * 作用:指定参数名获取参数值做校验
	 * 示例:A表为主表     B表为参照A表中的1字段     C表为参照A表中的2字段
	 * 在调用执行校验有默认属性  B表可不写该注解属性    C表实体必须写改注解属性为标识
	 * 校验B表的时候获取默认属性的值
	 * 校验C表的时候获取C表上的该属性,没有该属性直接抛异常
	 * @return
	 */
	String mainEntityParameterName() default "";
}
