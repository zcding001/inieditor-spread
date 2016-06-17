package com.sirding.test;

import com.sirding.annotation.AssertKey;
import com.sirding.annotation.Option;

public class Persion2 {

	@Option(isSection = true)
	private String secName;
	@Option(assertKeys = {
			@AssertKey(ev = "a", flag = true, params = {"aa"}),
			@AssertKey(ev = "b", flag = true, params = {"aa"})
			})
	private String protool;
	
	@Option(key = "neme_a")
	private String namea;
	@Option()
	private String pwda;
	@Option()
	private String nameb;
	@Option()
	private String pwd;
	
	
	
}
