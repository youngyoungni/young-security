package socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Socket通信步骤：（简单分为4步）

	1.建立服务端ServerSocket和客户端Socket
	
	2.打开连接到Socket的输出输入流
	
	3.按照协议进行读写操作
	
	4.关闭相对应的资源
	
	双向通信
 * @author Youngni
 *
 */
public class TwoServer {
	/**
	 *	SocketServer 
	 *		1、建立服务器监听 socket，等待并接收客户端的连接请求
	 *		2、接收请求后，连接到 Socket
	 *		3、进行IO通信
	 *		4、关闭 socket、IO 等相关资源
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		listenClient(9999);
	}

	/**
	 *	1基础版
	 * @throws IOException 
	 */
	public static void listenClient(int port) throws IOException {
		ServerSocket server = new ServerSocket(port);
		
		System.out.println("Server将一直等待连接的到来");
		Socket socket = server.accept();	//监听客户端的连接，开启后，进入堵塞，直到客户端访问
		
		//建立好连接之后，从 socket 中获取输入流，并建立缓冲区进行读取
		InputStream inputStream = socket.getInputStream();
		byte[] b = new byte[1024];
		int len = 0 ;
		StringBuilder sb = new StringBuilder();	//效率更快,不需要加锁，不具备多线程安全
		do {
			System.out.println("有数据吗");
			len = inputStream.read(b);
			sb.append(new String(b,0,len,"UTF-8"));
		}while(inputStream.available() != 0);
			
		System.out.println("服务器从客户端获取到的信息："+sb);
		
		OutputStream outputStream = socket.getOutputStream();
		outputStream.write("服务器返回给客户端的信息".getBytes("UTF-8"));
		outputStream.flush();
		
		inputStream.close();
		outputStream.close();
		socket.close();
		server.close();
	}
}

