package com.sirding.test;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;

import com.sirding.singleton.IniTool;

public class TestIniEditorSpread {
	static Logger logger = Logger.getLogger(TestIniEditorSpread.class);
	private static String filePath;
	private static String simplePath;
	private static String complexPath;
	private static IniTool iniTool = null;
	
	@BeforeClass
	public static void init(){
		iniTool = IniTool.newInstance();
		filePath = TestIniEditorSpread.class.getResource("/").toString();
		filePath = filePath.replaceFirst("file:/", "");
//		filePath = filePath + "test.ini";
		simplePath = filePath + "simple.ini";
		complexPath = filePath + "complex.ini";
	}
	
	@Test
	public void testAddSimple(){
		try {
			Simple obj = new Simple("three", "three", 3, "hello world，你好，世界");
			iniTool.saveSec(obj, simplePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testLoadSimplet(){
		try {
			List<Simple> list = iniTool.loadSec(Simple.class, simplePath);
			for(Simple obj : list){
				logger.debug("节点：" + obj.getSecName() + "\tname:" + obj.getName() + "\t msg:" + obj.getMsg());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAddComplex(){
		try {
			Complex obj = new Complex("four", "four", "udp");
			obj.setTcpIp("192.168.8.1");
			obj.setTcpPort("11");
			obj.setUdpIp("192.168.8.2");
			obj.setUdpPort("22");
			obj.setMsg("测试添加tcp/udp配置信息");
			iniTool.saveSec(obj, complexPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAddComplex2(){
		try {
			Complex2 obj = new Complex2("six", "six", "udp");
			obj.setTcpIp("192.168.8.1");
			obj.setTcpPort("11");
			obj.setUdpIp("192.168.8.2");
			obj.setUdpPort("22");
			obj.setMsg("测试添加tcp/udp配置信息");
			iniTool.saveSec(obj, complexPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testLoadComplex(){
		try {
			List<Complex> list = iniTool.loadSec(Complex.class, complexPath);
			for(Complex obj : list){
				logger.debug("secname:" + obj.getMySecName() + "\tname=" + obj.getName() + "\tflag=" + obj.getFlag() + "\t"
						+ "\ntcp_ip:" + obj.getTcpIp() + "\n"
						+ "tcp_port:" + obj.getTcpPort() + "\n"
						+ "udp_ip:" + obj.getUdpIp() + "\n"
						+ "udp_port" + obj.getUdpPort() + "\n"
						+ "msg:" + obj.getMsg());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 测试枚举类型
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void testFx(){
		List<Complex> list = iniTool.loadListForTest(Complex.class);
		for(Complex obj : list){
			logger.info(obj.getName()  + "==" + obj.getMsg());
		}
	}
}
