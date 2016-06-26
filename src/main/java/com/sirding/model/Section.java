package com.sirding.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 配置样例eg
 * [jtsec]
 * name=ding
 * pwd=12345
 * 
 * #jtsec is company
 * 
 * [surpass]
 * name=chao
 * pwd=11111
 * 
 * 解释如下
 * sectionName 为 jtsec
 * isBlankLine 表示 jtsec与surpass之间的空行，也就是surpass上一行的空行
 * comment 表示为“#jtsec is company”注释
 * sectionOptMap key为name，如[jtsec]下的name、pwd， value为{@link Options}对象
 * sectionOptOrder 存放name，保证存储顺序
 * ignoreSet	需要忽略的属性集合
 * 
 * @author surpassE
 * @version 1.0.0
 * @since 2015-04-01
 */
public class Section {

	private String sectionName;
	private boolean isBlankLine;
	private String comment;
	
	//所有要保存属性的结合
	private Map<String, Options> map = new HashMap<String, Options>();
	//要忽略的属性集合
	private Map<String, Options> ignoreMap = new HashMap<String, Options>();
	//用于存储节点属性的顺序
	private List<String> list = new ArrayList<String>();
	private Set<String> ignoreSet = new HashSet<String>();

	public String getSectionName() {
		return sectionName;
	}
	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
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
	public Set<String> getIgnoreSet() {
		return ignoreSet;
	}
	public void setIgnoreSet(Set<String> ignoreSet) {
		this.ignoreSet = ignoreSet;
	}
	public Map<String, Options> getIgnoreMap() {
		return ignoreMap;
	}
	public void setIgnoreMap(Map<String, Options> ignoreMap) {
		this.ignoreMap = ignoreMap;
	}
	public Map<String, Options> getMap() {
		return map;
	}
	public void setMap(Map<String, Options> map) {
		this.map = map;
	}
	public List<String> getList() {
		return list;
	}
	public void setList(List<String> list) {
		this.list = list;
	}
}
