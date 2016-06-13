package com.sirding.service.impl;

import java.lang.reflect.Field;
import java.util.List;

import com.sirding.IniEditor;
import com.sirding.annotation.AssertKey;
import com.sirding.annotation.IgnoreKey;
import com.sirding.annotation.Option;
import com.sirding.model.Options;
import com.sirding.model.Section;
import com.sirding.model.SectionField;
import com.sirding.service.SecService;
import com.sirding.util.ReflectUtil;


public class SecServiceImpl implements SecService {
	private static final String FILE_PATH = "CONF_FILE_PATH";
	
	public void saveSec(Object obj) throws Exception{
		String filePath = (String)ReflectUtil.getFieldValue(obj, FILE_PATH);
		if(filePath == null || filePath.length() <= 0){
			System.out.println("ERROR-Exception=======没有CONF_FILE_PATH属性或配置文件不存在======");
		}else{
			this.saveSec(obj, filePath);
		}
	}

	public void saveSec(Object obj, String filePath) throws Exception {
		IniEditor iniEditor = new IniEditor(filePath);
		this.saveSec(obj, iniEditor);
		iniEditor.save(filePath);
	}

	public synchronized void saveSec(Object obj, IniEditor iniEditor) throws Exception {
		
	}

	public synchronized List<Object> loadSec(Class<?> clazz) throws Exception {
		Object obj = clazz.newInstance();
		String filePath = (String)ReflectUtil.getFieldValue(obj, FILE_PATH);
		return this.loadSec(clazz, filePath);
	}

	public synchronized List<Object> loadSec(Class<?> clazz, String flag, String... params) throws Exception {
		Object obj = clazz.newInstance();
		String filePath = (String)ReflectUtil.getFieldValue(obj, FILE_PATH);
		return this.loadSec(clazz, filePath, flag, params);
	}

	public List<Object> loadSec(Class<?> clazz, String filePath) throws Exception {
		IniEditor iniEditor = new IniEditor(filePath);
		List<Object> list = this.loadSec(clazz, iniEditor);
		iniEditor.save(filePath);
		return list;
	}

	public List<Object> loadSec(Class<?> clazz, String filePath, String flag, String... params) throws Exception {
		IniEditor iniEditor = new IniEditor(filePath);
		List<Object> list = this.loadSec(clazz, iniEditor, flag, params);
		iniEditor.save(filePath);
		return list;
	}

	public List<Object> loadSec(Class<?> clazz, IniEditor iniEditor) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Object> loadSec(Class<?> clazz, IniEditor iniEditor, String flag, String... params) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Object> loadSec(Object obj) throws Exception {
		if(obj != null){
			String filePath = (String)ReflectUtil.getFieldValue(obj, FILE_PATH);
			List<Object> list = this.loadSec(obj, filePath);
			return list;
		}
		return null;
	}

	public List<Object> loadSec(Object obj, String flag, String... params) throws Exception {
		if(obj != null){
			String filePath = (String)ReflectUtil.getFieldValue(obj, FILE_PATH);
			List<Object> list = this.loadSec(obj, filePath, flag, params);
			return list;
		}
		return null;
	}

	public List<Object> loadSec(Object obj, String filePath) throws Exception {
		if(obj != null){
			IniEditor iniEditor = new IniEditor(filePath);
			List<Object> list = this.loadSec(obj, iniEditor);
			iniEditor.save(filePath);
			return list;
		}
		return null;
	}

	public List<Object> loadSec(Object obj, String filePath, String flag, String... params) throws Exception {
		if(obj != null){
			IniEditor iniEditor = new IniEditor(filePath);
			List<Object> list = this.loadSec(obj, iniEditor, flag, params);
			iniEditor.save(filePath);
			return list;
		}
		return null;
	}

	public List<Object> loadSec(Object obj, IniEditor iniEditor) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Object> loadSec(Object obj, IniEditor iniEditor, String flag, String... params) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 
	 * 获得obj中含有@Se注解的所有属性
	 * 
	 * @param obj
	 * @return
	 */
	public SectionField getSectionField(Object obj){
		SectionField sectionField = null;
		try {
			sectionField = new SectionField();
			Class<?> clazz = obj.getClass();
			Field[] fieldArr = clazz.getDeclaredFields();
			if(fieldArr != null){
				for(Field field : fieldArr){
					//获得属性上的@Option注解
					Option option = field.getAnnotation(Option.class);
					if(option != null){
						//判断属性是不section节点属性
						if(option.isSection()){
							sectionField.setSectionNameField(field);
						}else{
							sectionField.getSectionOptionsList().add(field);
							//将属性的名称或是se中的name作为key field作为value，存入map中
							//以便在调那个set方法时可以直接通过name获得对应的属性
							if(option.key() != null && option.key() != "" && option.key().length() > 0){
								sectionField.getSectionOptionsMap().put(option.key(), field);
							}else{
								sectionField.getSectionOptionsMap().put(field.getName(), field);
							}
						}
						sectionField.setObj(obj);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sectionField;
	}

	/**
	 * 通过属性及属性上的注解获得要持久化到配置的name和value
	 * 
	 * @param sectionField
	 * @return
	 */
	public Section getSection(SectionField sectionField){
		Section section = new Section();
		Object obj = sectionField.getObj();
		//获得节点name的属性
		Field sectionNameField = sectionField.getSectionNameField();
		Option option = sectionNameField.getAnnotation(Option.class);
		//调用注解中指定方法名称的方法，获得的返回值暂时不做处理，一般情况下，是不会有返回值的
		ReflectUtil.callAssignedMethod(obj, option.beforeMethod());
		//获得节点name的值
		String sectionName = ReflectUtil.callIsOrGetMethod(obj, sectionNameField).toString();
		section.setSectionName(sectionName);
		section.setBlankLine(option.blankLine());
		//解析除节点以外的所有属性的值及注解含义
		if(sectionField.getSectionOptionsList() != null){
			for(Field optField : sectionField.getSectionOptionsList()){
				Options opt = new Options();
				option = optField.getAnnotation(Option.class);
				//调用注解中assignedMethod指定的方法
				ReflectUtil.callAssignedMethod(obj, option.beforeMethod());
				//获得属性值
				Object value = ReflectUtil.callIsOrGetMethod(obj, optField);
				//如果value为空，且注解中nullToSave=false则忽略此属性
				if((value == null || value.toString().length() <= 0)){
					continue;
				}
				//如果需要忽略属性的set集合中包含这个属性，那么忽略此属性
				if(section.getIgnoreSet().contains(optField.getName())){
					section.getSectionOptOrder().remove(optField.getName());
					section.getSectionOptMap().remove(optField.getName());
					continue;
				}
				//设置name=value中name值,如果注解中没有指定，那么name为属性名称，否则name为注解中name值
				opt.setName(optField.getName());
				String name = option.key();
				if(name != null && name != "" && name.length() > 0){
					opt.setName(name);
				}
				//设置name=value中value值
				opt.setValue("");
				if(value != null){
					opt.setValue(value.toString());
				}
				//通过value与注解中flag比较判断 ，如果匹配，params中指定的属性值将不会被保存到配置文件中
				IgnoreKey[] iproArr = option.ignoreKey();
				if(iproArr != null){
					for(IgnoreKey ipro : iproArr){
						String flag = ipro.flag();
						String[] params = ipro.params();
						if(params != null){
							//如果flag定义为NULL那么判断value是不是为null如果为null那么操作需要忽略的属性
							//如果flag定义为非NULL值，那么判断value值时一定要要判断value是否为null，因为要执行value.toString()方法
							if("NULL".equals(flag)){
								if(value == null){
									this.addParamsToSet(section, params);
								}
							}else{
								if(value != null && flag.equals(value.toString())){
									this.addParamsToSet(section, params);
								}
							}
						}
					}
				}
				//通过name对应的值与values数组的中的对比，如果name对应的成员变量的在期望的值中，那么将此field的值写入到配置文件中
				AssertKey apro = option.assertKey();
				boolean flag = false;
				if(apro != null){
					String fields = apro.name();
					if(fields != null && fields.length() > 0){
						String[] arr = apro.values();
						if(arr != null){
							//获得参考的属性的值
							Object fieldsValue = ReflectUtil.callIsOrGetMethod(obj, sectionField.getSectionOptionsMap().get(fields));
							if(fieldsValue != null){
								for(String tmp : arr){
									if(fieldsValue.toString().equals(tmp) && tmp.length() > 0){
										flag = true;
										break;
									}
								}
							}
						}
					}else{
						flag = true;
					}
				}else{
					flag = true;
				}
				if(!flag){
					continue;
				}
				//无论属性为何值都不执行保存的操作
//				if(!option.isSave()){
//					continue;
//				}
				opt.setBlankLine(option.blankLine());
				section.setComment(option.comment());
				section.getSectionOptOrder().add(opt.getName());
				section.getSectionOptMap().put(opt.getName(), opt);
			}
		}
		return section;
	}
	
	/**
	 * 将集合中参数存储到section属性的ignoreSet中
	 * @param section
	 * @param params
	 */
	private void addParamsToSet(Section section, String[] params){
		if(params != null){
			for(String param : params){
				section.getIgnoreSet().add(param);
			}
		}
	}

}
