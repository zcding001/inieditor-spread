package com.sirding.singleton;

import com.sirding.service.SecService;
import com.sirding.service.impl.SecServiceImpl;
/**
 * 
 * @author zc.ding
 *
 */
public class IniTool extends SecServiceImpl implements SecService{
	
	private static IniTool INITOOL = new IniTool();
	private IniTool(){}
	
	public static IniTool newInstance(){
		return INITOOL;
	}
	
	
}
