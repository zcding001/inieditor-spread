package com.sirding.singleton;

import com.sirding.service.SecService;
import com.sirding.service.impl.SecServiceImpl;
/**
 * 入口程序
 * @author zc.ding
 * @date 2016-06-23
 *
 */
public class IniTool extends SecServiceImpl implements SecService{
	
	private static IniTool INITOOL = new IniTool();
	private IniTool(){}
	
	public static IniTool newInstance(){
		return INITOOL;
	}
}
