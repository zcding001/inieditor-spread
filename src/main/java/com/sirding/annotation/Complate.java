package com.sirding.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 作用实体类上，在解析{@link Option}注解之前(保存配置)调用，或是在解析注解(加载配置)之后调用的指定接口方法
 * @author zc.ding
 * @date 2016-06-26
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Complate {
	/**
	 * 用于在解析注解之前调用
	 * @return
	 */
	String before() default "";
	
	/**
	 * 用于在解析完注解之后调用
	 * @return
	 */
	String after() default "";
	
	/**
	 * Option注解中notSure属性的集合
	 * @default{}
	 * @return
	 */
	String[] notSure() default{};

}
