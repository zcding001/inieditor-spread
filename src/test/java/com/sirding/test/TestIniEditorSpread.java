package com.sirding.test;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;

import com.sirding.singleton.IniTool;
import com.sirding.singleton.IniToolImp;

public class TestIniEditorSpread {
	static Logger logger = Logger.getLogger(TestIniEditorSpread.class);
	private static String filePath;
	private static IniTool iniTool = null;
	
	@BeforeClass
	public static void init(){
		iniTool = IniToolImp.newInstance();
		filePath = TestIniEditorSpread.class.getResource("/").toString();
		filePath = filePath + "test.ini";
		filePath = filePath.replaceFirst("file:/", "");
	}
	
	@Test
	public void testGet(){
		try {
			List<Persion> list = iniTool.loadSec(Persion.class, filePath);
			if(list != null){
				for(Persion obj : list){
					logger.info(obj.getSecName() + "\t" + obj.getPwd() + "\t" + obj.getAge());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info(filePath);
	}
	
	/**
	 * 测试简单的保存操作
	 */
	@Test
	public void testAdd1(){
		try {
			Persion obj = new Persion();
			obj.setSecName("sirding");
			obj.setPwd("a12345");
			obj.setAge(26);
			obj.setLove("girl");
			iniTool.saveSec(obj, filePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAdd2(){
		try {
			Persion2 obj = new Persion2();
			obj.setSecName("testAAA");
			obj.setProtool("a");
			obj.setNamea("a");
			obj.setPwda("pwda");;
			obj.setNameb("b");
			obj.setPwdb("b");
			iniTool.saveSec(obj, filePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 测试枚举类型
	 */
	@Test
	public void testFx(){
		List<Persion> list = iniTool.loadList(Persion.class);
		for(Persion obj : list){
			logger.info(obj.getName());
		}
	}
}
