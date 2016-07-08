package com.sirding.service.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sirding.annotation.AssertKey;
import com.sirding.annotation.Complate;
import com.sirding.annotation.Option;
import com.sirding.model.Options;
import com.sirding.model.Section;
import com.sirding.model.SectionField;
import com.sirding.service.SecService;
import com.sirding.thirdjar.IniEditor;
import com.sirding.util.ReflectUtil;


public class SecServiceImpl implements SecService {
	private static final String FILE_PATH = "CONF_FILE_PATH";
	private static Logger logger = Logger.getLogger(SecServiceImpl.class);
	
	public void saveSec(Object obj) throws Exception{
		String filePath = (String)ReflectUtil.getFieldValue(obj, FILE_PATH);
		if(this.filePathIsExist(filePath)){
			this.saveSec(obj, filePath);
		}
	}

	public void saveSec(Object obj, String filePath) throws Exception {
		logger.debug("配置信息将保存到【" + filePath + "】文件中");
		IniEditor iniEditor = new IniEditor(true);
		iniEditor.load(filePath);
		this.saveSec(obj, iniEditor);
		iniEditor.save(filePath);
	}

	/**
	 * 配置保存的核心入口
	 */
	public synchronized void saveSec(Object obj, IniEditor iniEditor) throws Exception {
		//判断传入的对象是不是集合
		if(obj instanceof List<?>){	
			List<?> objList = (List<?>)obj;
			for(Object objTmp : objList){
				this.saveObj(objTmp, iniEditor);
			}
		}else{
			this.saveObj(obj, iniEditor);
		}
		logger.debug("配置信息已成功保存到文件中......");
	}

	public synchronized <E> List<E> loadSec(Class<?> clazz) throws Exception {
		Object obj = clazz.newInstance();
		String filePath = (String)ReflectUtil.getFieldValue(obj, FILE_PATH);
		if(this.filePathIsExist(filePath)){
			return this.loadSec(clazz, filePath);
		}
		return new ArrayList<E>();
	}

	public synchronized <E> List<E> loadSec(Class<?> clazz, boolean flag, String... params) throws Exception {
		Object obj = clazz.newInstance();
		String filePath = (String)ReflectUtil.getFieldValue(obj, FILE_PATH);
		if(this.filePathIsExist(filePath)){
			return this.loadSec(clazz, filePath, flag, params);
		}
		return new ArrayList<E>();
	}

	public <E> List<E> loadSec(Class<?> clazz, String filePath) throws Exception {
		logger.debug("从【" + filePath + "】文件中加载配置信息");
		IniEditor iniEditor = new IniEditor(true);
		iniEditor.load(filePath);
		List<E> list = this.loadSec(clazz, iniEditor);
		return list;
	}
	
	public <E> List<E> loadSec(Class<?> clazz, String filePath, boolean flag, String... params) throws Exception {
		logger.debug("从【" + filePath + "】文件中加载配置信息");
		IniEditor iniEditor = new IniEditor(true);
		iniEditor.load(filePath);
		List<E> list = this.loadSec(clazz, iniEditor, flag, params);
		return list;
	}

	public <E> List<E> loadSec(Class<?> clazz, IniEditor iniEditor) throws Exception {
		return this.getSections(iniEditor, null, clazz, null);
	}

	public <E> List<E> loadSec(Class<?> clazz, IniEditor iniEditor, boolean flag, String... params) throws Exception {
		String flagTmp = "0";
		if(flag){
			flagTmp = "1";
		}
		return this.getSections(iniEditor, null, clazz, flagTmp, params);
	}

	public <E> List<E> loadSec(Object obj) throws Exception {
		if(obj != null){
			String filePath = (String)ReflectUtil.getFieldValue(obj, FILE_PATH);
			if(this.filePathIsExist(filePath)){
				return this.loadSec(obj, filePath);
			}
		}
		return new ArrayList<E>();
	}

	public <E> List<E> loadSec(Object obj, boolean flag, String... params) throws Exception {
		if(obj != null){
			String filePath = (String)ReflectUtil.getFieldValue(obj, FILE_PATH);
			if(this.filePathIsExist(filePath)){
				return this.loadSec(obj, filePath, flag, params);
			}
		}
		return new ArrayList<E>();
	}

	public <E> List<E> loadSec(Object obj, String filePath) throws Exception {
		logger.debug("从【" + filePath + "】文件中加载配置信息");
		if(obj != null){
			IniEditor iniEditor = new IniEditor(true);
			iniEditor.load(filePath);
			List<E> list = this.loadSec(obj, iniEditor);
			return list;
		}
		return new ArrayList<E>();
	}

	public <E> List<E> loadSec(Object obj, String filePath, boolean flag, String... params) throws Exception {
		logger.debug("从【" + filePath + "】文件中加载配置信息");
		if(obj != null){
			IniEditor iniEditor = new IniEditor(true);
			iniEditor.load(filePath);
			List<E> list = this.loadSec(obj, iniEditor, flag, params);
			return list;
		}
		return new ArrayList<E>();
	}

	/**
	 * 加载配置文件关键接口
	 */
	public <E> List<E> loadSec(Object obj, IniEditor iniEditor) throws Exception {
		return this.getSections(iniEditor, obj, null, null);
	}

	/**
	 * 加载配置文件关键接口
	 */
	public <E> List<E> loadSec(Object obj, IniEditor iniEditor, boolean flag, String... params) throws Exception {
		String flagTmp = "0";
		if(flag){
			flagTmp = "1";
		}
		return this.getSections(iniEditor, obj, null, flagTmp, params);
	}
	
	/**
	 * 将对象保存到配置文件中
	 * @param obj
	 * @param conf
	 */
	private void saveObj(Object obj, IniEditor conf){
		SectionField sectionField = this.getSectionField(obj);
		Section section = this.getSection(sectionField);
		String sectionName = section.getSectionName();
		//删除旧的配置信息
		if(conf.hasSection(sectionName)){
			List<String> optionList = conf.optionNames(sectionName);
			if(optionList != null && optionList.size() > 0){
				for(String optionName : optionList){
					conf.remove(sectionName, optionName);
				}
			}
		}
		//添加新的节点属性配置信息
		conf.addSection(sectionName);
		List<String> optList = section.getList();
		for(String name : optList){
			Options opt = section.getMap().get(name);
			Options ignoreOpt = section.getIgnoreMap().get(name);
			//如果此属性在需要忽略的map中那么不将此属性信息保存到配置文件中
			if(ignoreOpt != null && ignoreOpt.getPriority() >= opt.getPriority()){
				continue;
			}
			if(opt.isBlankLine()){
				conf.addBlankLine(sectionName);
			}
			if(opt.getComment() != null && opt.getComment().length() > 0){
				conf.addComment(sectionName, opt.getComment());
			}
			conf.set(sectionName, opt.getName(), opt.getValue() == null?"":opt.getValue().toString());
		}
		if(section.isBlankLine()){
			conf.addBlankLine(sectionName);
		}
	}
	
	
	/**
	 * 
	 * 获得obj中含有@Option注解的所有属性
	 * 
	 * @param obj
	 * @return
	 */
	private SectionField getSectionField(Object obj){
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
							if(option.key() != null && option.key().length() > 0){
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
	 * 调用Complate注解中方法
	 * @param obj
	 * @param flag 0:before、after都调用， 1：只调用before方法 2：只调用after方法
	 */
	private void callComplateMethod(Object obj, String flag){
		Complate complate = obj.getClass().getAnnotation(Complate.class);
		if(complate != null){
			if("0".equals(flag) || "1".equals(flag)){
				String beforeMethod = complate.before();
				if(beforeMethod != null && beforeMethod.length() > 0){
					ReflectUtil.callAssignedMethod(obj, beforeMethod);
				}
			}
			if("0".equals(flag) || "2".equals(flag)){
				String afterMethod = complate.after();
				if(afterMethod != null && afterMethod.length() > 0){
					ReflectUtil.callAssignedMethod(obj, afterMethod);
				}
			}
		}
	}

	/**
	 * 通过属性及属性上的注解获得要持久化到配置的name和value
	 * 
	 * @param sectionField
	 * @return
	 */
	private Section getSection(SectionField sectionField){
		Section section = new Section();
		Object obj = sectionField.getObj();
		//解析处理Complate注解的before属性
		this.callComplateMethod(obj, "1");
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
				//设置name=value中name值,如果注解中没有指定，那么name为属性名称，否则name为注解中name值
				opt.setName(optField.getName());
				String key = option.key();
				if(key != null && key.length() > 0){
					opt.setName(key);
				}
				//获得属性值
				Object value = ReflectUtil.callIsOrGetMethod(obj, optField);
				//设置name=value中value值
				opt.setValue(value);
				int saveFlag = option.saveFlag();
				if(this.isSaveFlag(saveFlag, value)){
					continue;
				}
				AssertKey[] akArr = option.assertKeys();
				if(akArr != null){
					for(AssertKey ak : akArr){
						String[] ev = ak.ev();
						//判断属性值是否与期望值相等
						if(ev != null && ev.length > 0 && value != null && value.toString().length() > 0){
							for(String tmp : ev){
								//期望值与属性相等
								if(tmp != null && tmp.equals(value.toString())){
									String[] params = ak.params();
									if(params != null && params.length > 0){
										for(String param : params){
											Options assertOpt = new Options();
											assertOpt.setName(param);
											assertOpt.setPriority(ak.priority());
											//判断是否包含
											if(!ak.flag()){
												section.getIgnoreMap().put(assertOpt.getName(), assertOpt);
											}else{
												if(!section.getMap().containsKey(assertOpt.getName())){
													section.getList().add(assertOpt.getName());
												}
												section.getMap().put(assertOpt.getName(), assertOpt);
											}
										}
									}
								}
							}
						}
					}
				}
				opt.setBlankLine(option.blankLine());
				section.setComment(option.comment());
				
				boolean notSureFlag = false;
				Complate complate = obj.getClass().getAnnotation(Complate.class);
				if(complate != null){
					String[] notSureArr = complate.notSure();
					if(notSureArr != null && notSureArr.length > 0){
						for(String tmp : notSureArr){
							if(tmp != null && (tmp.equalsIgnoreCase(opt.getName()) || tmp.equalsIgnoreCase(optField.getName()))){
								if(section.getMap().containsKey(opt.getName())){
									opt.setPriority(section.getMap().get(opt.getName()).getPriority());
									section.getMap().put(opt.getName(), opt);
								}
								notSureFlag = true;
								break;
							}
						}
					}
				}
				if(notSureFlag){
					continue;
				}
				//不确定属性
				if(option.notSure()){
					if(section.getMap().containsKey(opt.getName())){
						opt.setPriority(section.getMap().get(opt.getName()).getPriority());
						section.getMap().put(opt.getName(), opt);
					}
				}else{
					if(!section.getMap().containsKey(key)){
						section.getList().add(opt.getName());
					}
					section.getMap().put(opt.getName(), opt);
				}
			}
		}
		return section;
	}
	
	/**
	 * 判断属性值 null empty
	 * @param saveFlag
	 * @param value
	 * @return
	 */
	private boolean isSaveFlag(int saveFlag, Object value){
		boolean flag = false;
		switch(saveFlag){
			case 1:
				flag = false;
				break;
			case 2:
				flag = false;
				if(value == null || value.toString().length() <= 0){
					flag = true;
				}
				break;
			default:
				flag = true;
				break;
		}
		return flag;
	}
	
	/**
	 * 
	 * 解析section并设置obj属性值
	 * 
	 * @param iniEditor 
	 * @param obj 存储属性值的现有对象
	 * @param clazz 对象的Class类型
	 * @param flag 0：删除params中指定的节点名称 1：保留params中指定的节点名称
	 * @param params 节点名称
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private <E> List<E> getSections(IniEditor iniEditor, Object existObj, Class<?> clazz, String flag, String... params){
		List<E> objList = new ArrayList<E>();
		List<Section> sectionList = this.getSectionAssignedOrExcept(iniEditor, flag, params);
		try {
			for(Section section : sectionList){
				Object obj = null;
				if(existObj == null && clazz != null){
					obj = clazz.newInstance();
				}else{
					obj = existObj;
				}
				//获得obj下所有含@Option注解的属性及对应关系
				SectionField sectionField = this.getSectionField(obj);
				
				//设置默认的sectionName值
				Field sectionNameField = sectionField.getSectionNameField();
				ReflectUtil.callSetMethod(obj, sectionNameField, section.getSectionName());
				
				//设置section节点下对应属性的值
				Map<String, Options> map = section.getMap();
				for(String name : map.keySet()){
					Object value = map.get(name).getValue();
					Field sectionOptionsField = sectionField.getSectionOptionsMap().get(name);
					if(sectionOptionsField == null){
						logger.error("找不到【" + name +"】对应的属性");
					}
					ReflectUtil.callSetMethod(obj, sectionOptionsField, value);
				}
				//解析complate
				this.callComplateMethod(obj, "2");
				objList.add((E)obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return objList;
	}
	
	
	/**
	 * 取config中包含params中的节点对象，或是取config配置文件中剔除params中指定的节点名的对象
	 * 
	 * @param iniEditor
	 * @param flag 0：剔除params中指定的节点对象 1：只保存params中指定的节点对象
	 * @param params 节点名称的集合
	 * @return
	 */
	private List<Section> getSectionAssignedOrExcept(IniEditor iniEditor, String flag, String... params){
		List<Section> sectionList = new ArrayList<Section>();
		List<String> sectionNameList = iniEditor.sectionNames();
		if(sectionNameList != null){
			for(String sectionName : sectionNameList){
				if(this.containSection(sectionName, flag, params)){
					sectionList.add(this.getSingleSection(iniEditor, sectionName));
				}
			}
		}
		return sectionList;
	}
	
	/**
	 * 加载指定sectionname的节点对象
	 * 
	 * @param iniEditor
	 * @param sectionName 节点名称
	 * @return
	 */
	private Section getSingleSection(IniEditor iniEditor, String sectionName){
		Section section = new Section();
		section.setSectionName(sectionName);
		List<String> optList = iniEditor.optionNames(sectionName);
		if(optList != null){
			for(String optName : optList){
				Options opt = new Options();
				opt.setName(optName);
				opt.setValue(iniEditor.get(sectionName, optName));
				section.getMap().put(optName, opt);
				section.getList().add(optName);
			}
		}
		return section;
	}
	
	/**
	 * 根据flag判断sectionName是否为要查询的节点名称，true：是  false：否
	 * @param sectionName 节点名称
	 * @param flag 保留或是提出params中指定的节点信息
	 * @param params 要保留或是删除的属性值
	 * @return
	 */
	private boolean containSection(String sectionName, String flag, String... params){
		if((params != null && params.length > 0) && flag != null && flag.length() >= 0){
			if("1".equals(flag)){
				for(String tmp : params){
					if(sectionName != null && sectionName.equals(tmp)){
						return true;
					}
				}
				return false;
			}else if("0".equals(flag)){
				for(int i = 0; i < params.length; i++){
					if(sectionName != null && sectionName.equals(params[i])){
						return false;
					}
				}
				return true;
			}
		}
		return true;
	}
	
	/**
	 * 判断要操作的文件是否存在
	 * @param filePath
	 * @return
	 */
	private boolean filePathIsExist(String filePath){
		if(filePath == null || filePath.length() <= 0){
			logger.error("ERROR-Exception=======没有CONF_FILE_PATH属性或配置文件不存在======");
			return false;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Deprecated
	public <E> List<E> loadListForTest(Class<?> clazz) {
		try {
			List<E> list = new ArrayList<E>();
			for(int i = 0; i < 5; i++){
				list.add((E)(clazz.newInstance()));
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<E>();
	}

	@Override
	public <E> E loadSingleSec(Class<?> clazz) throws Exception {
		List<E> list = this.loadSec(clazz);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public <E> E loadSingleSec(Class<?> clazz, boolean flag, String... params) throws Exception {
		List<E> list = this.loadSec(clazz, flag, params);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public <E> E loadSingleSec(Class<?> clazz, String filePath) throws Exception {
		List<E> list = this.loadSec(clazz, filePath);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public <E> E loadSingleSec(Class<?> clazz, String filePath, boolean flag, String... params) throws Exception {
		List<E> list = this.loadSec(clazz, filePath, flag, params);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public <E> E loadSingleSec(Class<?> clazz, IniEditor iniEditor) throws Exception {
		List<E> list = this.loadSec(clazz, iniEditor);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public <E> E loadSingleSec(Class<?> clazz, IniEditor iniEditor, boolean flag, String... params) throws Exception {
		List<E> list = this.loadSec(clazz, iniEditor, flag, params);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}
}
