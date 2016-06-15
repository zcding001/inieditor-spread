package com.sirding.test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.sirding.service.SecService;
import com.sirding.service.impl.SecServiceImpl;

public class TestIniEditorSpread {
	static Logger logger = Logger.getLogger(TestIniEditorSpread.class);
	@Test
	public void testGet(){
		String filePath = TestIniEditorSpread.class.getResource("/").toString();
		filePath = filePath + "test.ini";
		filePath = filePath.replaceFirst("file:/", "");
		File file = new File(filePath);
		SecService secService = new SecServiceImpl();
		try {
			List<Object> list = secService.loadSec(Section.class, filePath);
			List<Section> rtList = new ArrayList<Section>();
			if(list != null){
				for(Object obj : list){
					rtList.add((Section)obj);
				}
			}
			for(Section obj : rtList){
				logger.info(obj.getSecName() + "\t" + obj.getPwd() + "\t" + obj.getAge());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		logger.info(file.exists());
		logger.info(filePath);
	}
}
