package com.sirding.annotation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 
 * @author surpassE
 * @time 2015-01-19
 *
 */
@Target({ })
@Retention(RUNTIME)
public @interface AssertKey {

	/**
	 * 成员变量的名称
	 * @return
	 */
	String name() default "";
	
	/**
	 * 属性期待的值得数组
	 * @return
	 */
	String[] values() default {};
	
	/**
	 * 期待的值 ev = expectedValue
	 * @return
	 */
	String ev() default "";
	
	/**
	 * 优先级值越大，优先级越高
	 * priority = 0 删除ip属性
	 * priority = 1 保存ip属性 那么最终的处理逻辑就是保留ip属性
	 * @return
	 */
	int priority() default 0;

	/**
	 * true 保留params中的所有的属性值
	 * false 删除与params中匹配的属性值
	 * @return
	 */
	boolean flag() default true;
	
	/**
	 * 成员变量的名称
	 * @return
	 */
	String[] params() default {};
}
