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
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
}
