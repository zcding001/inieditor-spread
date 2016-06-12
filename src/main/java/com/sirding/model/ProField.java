package com.sirding.model;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProField {
	private String key;
	private String value;
	private List<Field> proFieldList = new ArrayList<Field>();
	//key为属性中@SePro中 key值 、 value为属性的值
	private Map<String, Object> proFieldMap = new HashMap<String, Object>();
	//key为属性中@SePro中的key值、value为属性的对象
	private Map<String, Field> fieldMap = new HashMap<String, Field>();
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public List<Field> getProFieldList() {
		return proFieldList;
	}
	public void setProFieldList(List<Field> proFieldList) {
		this.proFieldList = proFieldList;
	}
	public Map<String, Object> getProFieldMap() {
		return proFieldMap;
	}
	public void setProFieldMap(Map<String, Object> proFieldMap) {
		this.proFieldMap = proFieldMap;
	}
	public Map<String, Field> getFieldMap() {
		return fieldMap;
	}
	public void setFieldMap(Map<String, Field> fieldMap) {
		this.fieldMap = fieldMap;
	}
}
