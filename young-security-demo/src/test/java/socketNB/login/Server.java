package socketNB.login;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *	基于TCP协议的Socket通信，实现用户登录
 * 
 * @author Youngni
 *
 */
public class Server {

	public static void main(String[] args) {
		
		try {
			
			ServerSocket serverSocket = new ServerSocket(8888);
			System.out.println("服务器即将启动，等待客户端的连接......");
			
			//定义客户端的数量
			int count = 0 ;
			Socket socket = null ;
			//循环监听等待客户端的连接
			while(true) {
					
				socket = serverSocket.accept();		//堵塞中...等待客户端的请求
				ServerThread serverThread = new ServerThread(socket);
				serverThread.start();
				
				count++;
				System.out.println("客户端的数量："+count);
				
				InetAddress inetAddress = socket.getInetAddress();
				System.out.println("当前客户端IP："+inetAddress.getHostName());
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
