package socketNB.inetaddress;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

public class InetAddressTest {

	public static void main(String[] args) throws UnknownHostException {
		InetAddress address = InetAddress.getLocalHost();
		System.out.println("计算机名："+address.getHostName());
		System.out.println("IP名："+address.getHostAddress());
		byte[] b = address.getAddress();
		System.out.println("字节数组形式的IP："+Arrays.toString(b));
		System.out.println(address);
		
		//根据机器名获取InetAddress对象
		InetAddress address2 = InetAddress.getByName("DESKTOP-E7PFIEI");
		System.out.println("计算机名："+address2.getHostName());
		System.out.println("IP名："+address2.getHostAddress());
		
		//根据IP地址获取InetAddress对象
		InetAddress address3 = InetAddress.getByName("169.254.203.57");
		System.out.println("计算机名："+address3.getHostName());
		System.out.println("IP名："+address3.getHostAddress());
		
	}
}
