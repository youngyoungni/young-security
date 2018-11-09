package socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 	双向通信
 * @author Youngni
 *
 */
public class TwoClient {
	
	/**
	 *	SocketClient
	 *		1、建立连接服务器对象 socket 发送连接请求
	 *		2、进行IO通信
	 *		3、关闭 socket、IO 等相关资源
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		sendRequest("127.0.0.1", 9999);
	}

	/**
	 * 
	 * @param host	：主机名
	 * @param port	：端口号
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public static void sendRequest(String host,Integer port) throws UnknownHostException, IOException {
		Socket socket = new Socket(host,port);
		
		//发送信息给服务器
		OutputStream outputStream = socket.getOutputStream();
		String message = "\n你好 Socket Server";
		outputStream.write(message.getBytes("UTF-8"));
		//通过shutdownOutput高速服务器已经发送完数据，后续只能接受数据
		outputStream.flush();
	    socket.shutdownOutput();
		
		//接收服务器的信息
		InputStream inputStream = socket.getInputStream();
		byte[] b = new byte[1024];
		int len = 0 ;
		StringBuilder sb = new StringBuilder();
		do {
			len = inputStream.read(b);
			sb.append(new String(b,0,len,"UTF-8"));
		}while( inputStream.available() != 0);
		System.out.println("客户端从服务器拿到的数据："+sb);
		
		inputStream.close();
		outputStream.close();
		socket.close();
	}
}
