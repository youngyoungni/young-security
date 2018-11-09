package udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPServer {

	/*
	 *	tcp协议和udp协议的差别 
					TCP   	UDP 
		是否连接	 面向连接	 	面向非连接 	
		传输可靠性 		可靠  		不可靠 
		应用场合 	传输大量数据 	少量数据 
		速度 			慢 		快 
	 */
	public static void main(String[] args) throws Exception {
		
		/**
		 * 服务器接收请求
		 */
		//1.创建服务器端的 DatagramSocket 对象，指定端口
		DatagramSocket datagramSocket = new DatagramSocket(9999);
		
		//2.创建数据包，用于接收客户端发送的数据
		byte[] b = new byte[1024];	//创建字节数组，指定接收数据包的大小
		DatagramPacket datagramPacket = new DatagramPacket(b, b.length);
		
		//3.监听客户端访问服务器
		System.out.println("服务端已经启动，等待客户端发送的信息");
		datagramSocket.receive(datagramPacket); //此方法在接收到数据报之前会一直堵塞
		
		//4.读取数据
		String info = new String(b,0,datagramPacket.getLength());
		System.out.println("我是服务器，客户端说："+info);
		
		/**
		 * 向客户端响应数据
		 */
		InetAddress address = datagramPacket.getAddress();
		int port = datagramPacket.getPort();
		byte[] b1 = "欢迎你.".getBytes();
		//把数据发送给客户端
		DatagramPacket datagramPacket2 = new DatagramPacket(b1, b1.length,address,port);
		datagramSocket.send(datagramPacket2);
		
		datagramSocket.close();
	}
}
