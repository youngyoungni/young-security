package udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {

	public static void main(String[] args) throws Exception {
		
		DatagramSocket socket = new DatagramSocket();
		
		//1.定义服务器的地址，端口号，数据
		InetAddress address = InetAddress.getByName("127.0.0.1");
		int port = 9999;
		byte[] b = "用户名：admin;密码：123".getBytes();
		
		//2.创建数据报，包含发送的数据信息
		DatagramPacket datagramPacket = new DatagramPacket(b, b.length,address,port);
		
		//3.向服务器发送数据包
		socket.send(datagramPacket);
		
		/**
		 * 接收服务器端响应的数据
		 */
		//1.创建数据包对象，用于接收服务器响应的数据
		byte[] b1 = new byte[1024];
		DatagramPacket datagramPacket1 = new DatagramPacket(b1, b1.length);
		
		//2.接收服务器响应的数据
		socket.receive(datagramPacket1);
		
		//3.读取数据
		String reply = new String(b1,0,datagramPacket1.getLength());
		System.out.println("我是客户端，我收到服务器的信息："+reply);
		
		socket.close();
	}
}
