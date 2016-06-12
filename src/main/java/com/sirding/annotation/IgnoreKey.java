package com.sirding.annotation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
/**
 * 当属性的值与flag值相等时，那么忽略掉params中定义的属性字段
 * 
 * @author surpassE
 *
 */
@Target({ })
@Retention(RUNTIME)
public @interface IgnoreKey {

	/**
	 * 如果是0(-1或是其他自定义的值)表示忽略params定义的属性值
	 * @return
	 */
	String flag() default "-1";
	
	/**
	 * 不需要操作的属性值
	 * @return
	 */
	String[] params();
	
	
}
