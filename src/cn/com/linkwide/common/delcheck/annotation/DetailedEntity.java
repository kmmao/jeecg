package cn.com.linkwide.common.delcheck.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * 参照表的主实体
 * 2018-9-18  修改  修改人:李伟华
 * 修改原因:参照公共实体无法确保其他模块实体是在项目中存在的会出现找不到类的问题
 * 解决方案:系统执行已存放项目下的所有实体,可根据实体名称寻找实体整体路径、没有在项目中存在的直接跳过进行下一个实体校验
 * PS：兼容之前代码
 * @author asus
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD})
@Documented
public @interface DetailedEntity {
	/***
	 * 参照表的实体整体路径
	 * @return
	 */
	Class[] entity() default {};
	/***
	 * 参照表的实体整体路径
	 * 增加原因:根据实体名称寻找实体
	 * @return
	 */
	String[] strEntity() default {};
}
