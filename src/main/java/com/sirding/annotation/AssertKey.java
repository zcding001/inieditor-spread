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
	 * 期待的值 数组ev = [expectedValue]
	 * @return
	 */
	String[] ev() default {};
	
	/**
	 * 优先级值越大，优先级越高，优先级相等时，以不保存(删除)优先
	 * eg：Section的map中含有ip的Options对象其优先级priority = 0(将ip写入配置文件中)，
	 * Section的ignoreMap含有ip的Options对象其优先级priority = 1(不将IP写入配置文件中)，由于要不保存
	 * ip的ignoreMap中优先级高，所有ip的Options对象最终将忽略掉
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
