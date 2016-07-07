package com.sirding.singleton;

import com.sirding.service.impl.SecServiceImpl;
/**
 * 
 * @author zc.ding
 *
 */
public class IniToolImp extends SecServiceImpl implements IniTool{
	
	private static IniToolImp INITOOL = new IniToolImp();
	private IniToolImp(){}
	
	public static IniTool newInstance(){
		return INITOOL;
	}
	
	
}
