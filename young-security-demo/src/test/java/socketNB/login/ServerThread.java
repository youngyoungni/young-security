package socketNB.login;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * ServerSocketThread : socket 服务端线程类
 * @author Youngni
 *
 */
public class ServerThread extends Thread {

	//和本线程相关的Socket
	Socket socket = null;
	
	public ServerThread(Socket socket) {
		// TODO Auto-generated constructor stub
		this.socket = socket;
	}
	
	/**
	 * 	线程执行的操作，响应客户端的请求
	 */
	@Override
	public void run() {
		try (
			InputStream inputStream = this.socket.getInputStream();
			InputStreamReader reader = new InputStreamReader(inputStream);
			BufferedReader br = new BufferedReader(reader);
			
			OutputStream outputStream = socket.getOutputStream();
			PrintWriter printWriter = new PrintWriter(outputStream);
				){
			
			String info ;
			while( (info = br.readLine()) != null) {	//循环读取
				System.out.println("我是服务器，客户端提交的信息是："+info);
			}
			socket.shutdownInput();
			

			printWriter.write("登录成功");
			printWriter.flush();
			
			this.socket.shutdownOutput();
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if( this.socket != null ) {
				try {
					this.socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
