package com.sirding.model;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * obj					含有{ @link Se }注解的对象
 * sectionNameField 	对象中对应节点名称的属性字段
 * sectionOptionsList	对象中对应节点下所有属性的字段
 * 
 * sectionOptionsMap	Se注解中name对应的属性字段，不包括sectionNameField属性字段
 * 
 * @author surpassE
 *
 */
public class SectionField {

	private Object obj;
	private Field sectionNameField;
	private List<Field> sectionOptionsList = new ArrayList<Field>();
	
	private Map<String, Field> sectionOptionsMap = new HashMap<String, Field>();
	
	
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
	public Field getSectionNameField() {
		return sectionNameField;
	}
	public void setSectionNameField(Field sectionNameField) {
		this.sectionNameField = sectionNameField;
	}
	public List<Field> getSectionOptionsList() {
		return sectionOptionsList;
	}
	public void setSectionOptionsList(List<Field> sectionOptionsList) {
		this.sectionOptionsList = sectionOptionsList;
	}
	public Map<String, Field> getSectionOptionsMap() {
		return sectionOptionsMap;
	}
	public void setSectionOptionsMap(Map<String, Field> sectionOptionsMap) {
		this.sectionOptionsMap = sectionOptionsMap;
	}
}
