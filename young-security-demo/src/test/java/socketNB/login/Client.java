package socketNB.login;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 	基于TCP协议的Socket通信，实现用户登录
 * @author Youngni
 *
 */
public class Client {

	public static void main(String[] args) {
		
		try {
			Socket socket = new Socket("127.0.0.1", 8888);
			
			OutputStream outputStream = socket.getOutputStream();
			PrintWriter printWriter = new PrintWriter(outputStream);
			printWriter.write("用户名：admin;密码：123\ncode:123789");
			printWriter.flush();
			socket.shutdownOutput();
			
			
			
			InputStream inputStream = socket.getInputStream();
			InputStreamReader reader = new InputStreamReader(inputStream);
			BufferedReader br = new BufferedReader(reader);
			
			String info ;
			while( (info = br.readLine()) != null) {	//循环读取
				System.out.println("我是客户端，服务器响应的信息是："+info);
			}
			socket.shutdownInput();
			
			br.close();
			reader.close();
			inputStream.close();
			printWriter.close();
			outputStream.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
