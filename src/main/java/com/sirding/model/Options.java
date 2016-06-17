package com.sirding.model;
/**
 * 节点下对应的属性值eg
 * [jtsec]
 * name = ding
 * 
 * #password is required
 * pwd = 12345
 * 
 * 上述配置包含2个Options对象
 * name = ding是一个,其中name=name，value=ding、isBlanLine=true、comment=null
 * pwd=12345是另一个对象，其中name=pwd、value=12345、isBlank=false、comment=#password is required
 * 
 * @author surpassE
 * @version 1.0.0
 * @since 2015-04-01
 *
 */
public class Options {

	private String name;
	private Object value;
	private boolean isBlankLine;
	private String comment;
	private int priority = 0;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	public boolean isBlankLine() {
		return isBlankLine;
	}
	public void setBlankLine(boolean isBlankLine) {
		this.isBlankLine = isBlankLine;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
}
