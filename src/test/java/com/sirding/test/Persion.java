package com.sirding.test;

import com.sirding.annotation.Option;

public class Persion {

	@Option(isSection = true)
	private String secName;
	@Option
	private String name;
	@Option
	private String pwd;
	@Option
	private int age;
	@Option(saveFlag = 2)
	private String love;
	public String getSecName() {
		return secName;
	}
	public void setSecName(String secName) {
		this.secName = secName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getLove() {
		return love;
	}
	public void setLove(String love) {
		this.love = love;
	}
}
