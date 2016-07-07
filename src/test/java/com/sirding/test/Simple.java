package com.sirding.test;

import com.sirding.annotation.Complate;
import com.sirding.annotation.Option;
/**
 * 
 * 简单的存取ini、conf配置信息
 * @author zc.ding
 * @date 2016-06-26
 *
 */
@Complate(before = "escape", after = "rollback")
public class Simple {

	@Option(isSection = true)
	private String secName;
	@Option()
	private String name;
	@Option()
	private int id;
	@Option()
	private String msg;
	
	public Simple(){}
	
	public Simple(String secName, String name, int id, String msg){
		this.secName = secName;
		this.name = name;
		this.id = id;
		this.msg = msg;
	}

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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public void escape(){
		System.out.println("======用于做数据转义处理========");
	}
	
	public void rollback(){
		System.out.println("======对象数据加载完成后调研那个此方法 =================");
	}
	
}
