package com.sirding.test;

import com.sirding.annotation.AssertKey;
import com.sirding.annotation.Option;

public class Persion2 {

	@Option(isSection = true)
	private String secName;
	@Option(assertKeys = {
			@AssertKey(ev = {"a"}, flag = true, params = {"name_a"}),
			@AssertKey(ev = {"b"}, flag = true, params = {"nameb"})
			})
	private String protool;
	
	@Option(key = "name_a", notSure = true)
	private String namea;
	@Option(notSure = true)
	private String pwda;
	
	@Option(notSure = true)
	private String nameb;
	@Option(notSure = true)
	private String pwdb;
	
	
	public String getSecName() {
		return secName;
	}
	public void setSecName(String secName) {
		this.secName = secName;
	}
	public String getProtool() {
		return protool;
	}
	public void setProtool(String protool) {
		this.protool = protool;
	}
	public String getNamea() {
		return namea;
	}
	public void setNamea(String namea) {
		this.namea = namea;
	}
	public String getPwda() {
		return pwda;
	}
	public void setPwda(String pwda) {
		this.pwda = pwda;
	}
	public String getNameb() {
		return nameb;
	}
	public void setNameb(String nameb) {
		this.nameb = nameb;
	}
	public String getPwdb() {
		return pwdb;
	}
	public void setPwdb(String pwdb) {
		this.pwdb = pwdb;
	}
	
}
