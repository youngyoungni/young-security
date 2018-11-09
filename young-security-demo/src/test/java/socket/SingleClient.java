package socket;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 	单向通信
 * @author Youngni
 *
 */
public class SingleClient {
	
	/**
	 *	SocketClient
	 *		1、建立连接服务器对象 socket 发送连接请求
	 *		2、进行IO通信
	 *		3、关闭 socket、IO 等相关资源
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		sendRequest("127.0.0.1", 8888);
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
		OutputStream outputStream = socket.getOutputStream();
		String message = "你好 Socket Server";
		outputStream.write(message.getBytes("UTF-8"));
		outputStream.close();
		socket.close();
	}
}
