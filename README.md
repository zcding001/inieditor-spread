# inieditor-spread
基于注解的方式操作.ini类型格式的文件

-----------------------------------------------------------------------------------------
详细应用配置见单元测试demo
-----------------------------------------------------------------------------------------
实例1；
[one]
name = one
id = 1
msg = add one
[two]
name = two
id = 2
msg = add 第二条配置信息
[three]
name = three
id = 3
msg = hello world，你好，世界


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


@Test
public void testAddSimple(){
	try {
		Simple obj = new Simple("three444", "three", 3, "myhello world，你好，世界");
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

---------------------------------------------------------------------------------------------
示例2：
[one]
name = one_before
tcp_ip = 192.168.8.1
tcp_port = 11
flag = tcp
[two]
name = two_before
udp_ip = 192.168.8.2
udp_port = 22
flag = udp
msg = 测试添加udp配置信息
[three]
name = three_before
flag = tcp
tcp_ip = 192.168.8.1
tcp_port = 11
msg = 测试添加tcp/udp配置信息
[four]
name = four_before
flag = udp
udp_ip = 192.168.8.2
udp_port = 22
msg = 测试添加tcp/udp配置信息
[five]
name = five_before
tcp_ip = 192.168.8.1
tcp_port = 11
flag = tcp
msg = 测试添加tcp/udp配置信息
[six]
name = six_before
udp_ip = 192.168.8.2
udp_port = 22
flag = udp
msg = 测试添加tcp/udp配置信息

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
