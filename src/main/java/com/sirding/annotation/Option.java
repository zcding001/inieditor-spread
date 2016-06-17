package com.sirding.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 应用于Object属性注解之上，以解决Object与.conf或是.ini文件中属性之间对应的逻辑关系
 * 
 * @author zc.ding
 * @date 20160-06-17
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Option {

	/**
	 * 属性是不是section节点
	 * 默认不是节点
	 * @return
	 */
	boolean isSection() default false;
	
	/**
	 * key值
	 * @return
	 */
	String key() default "";
	
	/**
	 * 解析属性时先执行指定的方法
	 * @return
	 */
	String beforeMethod() default "";
	
	/**
	 * 属性的注释信息
	 * @return
	 */
	String comment() default "";
	
	/**
	 * 是否添加空白行
	 * @return
	 */
	boolean blankLine() default false;
	
	/**
	 * 屬性值保存标识符 默认值
	 * 1：null empty不保存
	 * 2：null不保存
	 * 3：empty不保存
	 * 4：保存
	 * 5：不保存
	 * @return
	 */
	String saveFlag() default "1";
	
	/**
	 * 属性是null、false、
	 * @return
	 */
	IgnoreKey[] ignoreKey() default { };
	
	/**
	 * 断言指定的成员变量的值是否在期望的值得数组中
	 * eg
	 * @Option
	 * private String name;
	 * @Option(assertKey = @assertKey(name = "name", values = {"1"}))
	 * private String key;
	 * 如果name的值为“1”,那么将key的值写入到配置文件中如果name不为“1”，那么不将key的值写入到文件中
	 * 如果注解中不包含assertKey那么直接将key值写入到文件中
	 * @return
	 */
	AssertKey assertKey() default @AssertKey();
	
	AssertKey[] assertKeys() default {};
	
}
