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
}
