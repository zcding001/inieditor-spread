package com.sirding.test;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;

import com.sirding.service.SecService;
import com.sirding.service.impl.SecServiceImpl;

public class TestIniEditorSpread {
	static Logger logger = Logger.getLogger(TestIniEditorSpread.class);
	private static SecService secService;
	private static String filePath;
	
	@BeforeClass
	public static void init(){
		secService = new SecServiceImpl();
		filePath = TestIniEditorSpread.class.getResource("/").toString();
		filePath = filePath + "test.ini";
		filePath = filePath.replaceFirst("file:/", "");
	}
	
	@Test
	public void testGet(){
		try {
			List<TestSection> list = secService.loadSec(TestSection.class, filePath);
			if(list != null){
				for(TestSection obj : list){
					logger.info(obj.getSecName() + "\t" + obj.getPwd() + "\t" + obj.getAge());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info(filePath);
	}
	
	@Test
	public void testAdd(){
		try {
			TestSection obj = new TestSection();
			obj.setSecName("sirding");
			obj.setPwd("a12345");
			obj.setAge(26);
//			secService.saveSec(obj, filePath);
			secService.saveSec(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFx(){
		List<TestSection> list = secService.loadList(TestSection.class);
		for(TestSection obj : list){
			logger.info(obj.getName());
		}
	}
}
