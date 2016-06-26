package com.sirding.test;

import com.sirding.annotation.AssertKey;
import com.sirding.annotation.Complate;
import com.sirding.annotation.Option;

/**
 * 用于测试assertKeys
 * @author Administrator
 *
 */
@Complate(before = "before", after = "after")
public class Complex {
	@Option(isSection = true)
	private String mySecName;
	@Option()
	private String name;
	@Option(assertKeys = {
			@AssertKey(ev = "udp", flag = false, params ={"tcp_ip", "tcp_port"}),
			@AssertKey(ev = "tcp", flag = false, params ={"udp_ip", "udp_port"})
			})
	private String flag;
	@Option(key = "tcp_ip")
	private String tcpIp;
	@Option(key = "tcp_port")
	private String tcpPort;
	@Option(key = "udp_ip")
	private String udpIp;
	@Option(key = "udp_port")
	private String udpPort;
	@Option(saveFlag = 2)
	private String msg;
	
	public Complex(){}
	
	public Complex(String mySecName, String name, String flag){
		this.mySecName = mySecName;
		this.name = name;
		this.flag = flag;
	}
	
	
	public String getMySecName() {
		return mySecName;
	}
	public void setMySecName(String mySecName) {
		this.mySecName = mySecName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getTcpIp() {
		return tcpIp;
	}
	public void setTcpIp(String tcpIp) {
		this.tcpIp = tcpIp;
	}
	public String getTcpPort() {
		return tcpPort;
	}
	public void setTcpPort(String tcpPort) {
		this.tcpPort = tcpPort;
	}
	public String getUdpIp() {
		return udpIp;
	}
	public void setUdpIp(String udpIp) {
		this.udpIp = udpIp;
	}
	public String getUdpPort() {
		return udpPort;
	}
	public void setUdpPort(String udpPort) {
		this.udpPort = udpPort;
	}
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public void before(){
		this.name = this.name + "_before";
	}
	
	public void after(){
		this.name = this.name.replace("_before", "_after");
	}
}
