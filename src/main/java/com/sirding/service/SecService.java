package com.sirding.service;

import java.util.List;

import com.sirding.IniEditor;

/**
 * 读、写.conf、.ini文件
 * @author zc.ding
 * @date 2016-06-12
 *
 */
public interface SecService {

	/**
	 * 保存.conf .ini数据到文件中
	 * @param obj 对象中含有CONF_FILE_PATH属性存放文件路径
	 */
	public void saveSec(Object obj) throws Exception;
	
	/**
	 * 保存.conf .ini数据到文件中
	 * @param obj
	 * @param filePath 文件路径
	 */
	public void saveSec(Object obj, String filePath) throws Exception;
	
	/**
	 * 保存.conf .ini数据到文件中
	 * @param obj
	 * @param iniEditor 含有文件路径的IniEditor对象
	 */
	public void saveSec(Object obj, IniEditor iniEditor) throws Exception;
	
	/**
	 * 将文件中内容加载到对象中，clazz类型的对象中含有CONF_FILE_PATH属性存放文件路径
	 * @param clazz 新对象的class类型
	 * @return
	 * @throws Exception
	 */
	public List<Object> loadSec(Class<?> clazz) throws Exception;
	
	/**
	 * 将文件中内容加载到对象中, clazz类型的对象中含有CONF_FILE_PATH属性存放文件路径
	 * @param clazz 新对象的class类型
	 * @param flag 0:去除params中匹配的数据
	 * 			   1:只加载params中匹配的节点数据
	 * @param params sec节点名称数组
	 * @return
	 * @throws Exception
	 */
	public List<Object> loadSec(Class<?> clazz, String flag, String... params) throws Exception;
	
	/**
	 * 将文件中内容加载到对象中
	 * @param clazz 新对象的class类型
	 * @param filePath 文件路径
	 * @return
	 * @throws Exception
	 */
	public List<Object> loadSec(Class<?> clazz, String filePath) throws Exception;
	/**
	 * 将文件中内容加载到对象中
	 * @param clazz 新对象的class类型
	 * @param filePath 文件路径
	 * @param flag 0:去除params中匹配的数据
	 * 			   1:只加载params中匹配的节点数据
	 * @param params sec节点名称数组
	 * @return
	 * @throws Exception
	 */
	public List<Object> loadSec(Class<?> clazz, String filePath, String flag, String... params) throws Exception;

	/**
	 * 将文件中内容加载到对象中
	 * @param clazz 新对象的class类型
	 * @param iniEditor 含有文件路径的IniEditor对象
	 * @return
	 * @throws Exception
	 */
	public List<Object> loadSec(Class<?> clazz, IniEditor iniEditor) throws Exception;
	/**
	 * 将文件中内容加载到对象中
	 * @param clazz 新对象的class类型
	 * @param iniEditor 含有文件路径的IniEditor对象
	 * @param flag 0:去除params中匹配的数据
	 * 			   1:只加载params中匹配的节点数据
	 * @param params sec节点名称数组
	 * @return
	 * @throws Exception
	 */
	public List<Object> loadSec(Class<?> clazz, IniEditor iniEditor, String flag, String... params) throws Exception;
	
	/**
	 * 将文件中内容加载到已存在的对象中, obj中含有CONF_FILE_PATH属性存放文件路径
	 * @param obj 已有值的对象
	 * @return
	 * @throws Exception
	 */
	public List<Object> loadSec(Object obj) throws Exception;
	/**
	 * 将文件中内容加载到已存在的对象中, obj中含有CONF_FILE_PATH属性存放文件路径
	 * @param obj 已有值的对象
	 * @param flag 0:去除params中匹配的数据
	 * 			   1:只加载params中匹配的节点数据
	 * @param params sec节点名称数组
	 * @return
	 * @throws Exception
	 */
	public List<Object> loadSec(Object obj, String flag, String... params) throws Exception;
	/**
	 * 将文件中内容加载到已存在的对象中
	 * @param obj 已有值的对象
	 * @param filePath 文件路径
	 * @return
	 * @throws Exception
	 */
	public List<Object> loadSec(Object obj, String filePath) throws Exception;
	/**
	 * 将文件中内容加载到已存在的对象中
	 * @param obj 已有值的对象
	 * @param filePath 文件路径
	 * @param flag 0:去除params中匹配的数据
	 * 			   1:只加载params中匹配的节点数据
	 * @param params sec节点名称数组
	 * @return
	 * @throws Exception
	 */
	public List<Object> loadSec(Object obj, String filePath, String flag, String... params) throws Exception;

	/**
	 * 将文件中内容加载到已存在的对象中
	 * @param obj 已有值的对象
	 * @param iniEditor 含有文件路径的IniEditor对象
	 * @return
	 * @throws Exception
	 */
	public List<Object> loadSec(Object obj, IniEditor iniEditor) throws Exception;
	/**
	 * 将文件中内容加载到已存在的对象中
	 * @param obj 已有值的对象
	 * @param iniEditor 含有文件路径的IniEditor对象
	 * @param flag 0:去除params中匹配的数据
	 * 			   1:只加载params中匹配的节点数据
	 * @param params sec节点名称数组
	 * @return
	 * @throws Exception
	 */
	public List<Object> loadSec(Object obj, IniEditor iniEditor, String flag, String... params) throws Exception;
	
}