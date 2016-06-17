package com.sirding.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 应用于Object属性注解之上，以解决Object与.conf或是.ini文件中属性之间对应的逻辑关系
 * 
 * @author zc.ding
 *
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
	 * 2：null 不保存
	 * 3：null 非empty保存
	 * 4：保存
	 * 5：不保存
	 * @return
	 */
	int saveFlag() default 1;
	
	/**
	 * 不确定是否保存的元素
	 * @return
	 */
	boolean notSure() default false;
	
	/**
	 * 断言指定的成员变量的值是否在期望的值相等，相等时通过flag、priority判断是否保留params中相对应的属性
	 * eg
	 * @Option
	 * @Option(assertKeys = {
	 *		@AssertKey(ev = "a", priority = 1, params = {"aa"}),
	 *		@AssertKey(ev = "b", flag = false, params = {"bb"})
			})
	 * private String key;
	 * 如果key=a那么保存成员变量aa的值到文件中，如果key=b那么将不保存bb属性值到配置文件中
	 * 如果注解中不包含assertKey那么直接将key值写入到文件中
	 * @return
	 */
	AssertKey[] assertKeys() default {};
	
}
