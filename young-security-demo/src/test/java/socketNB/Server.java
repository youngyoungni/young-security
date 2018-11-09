package socketNB;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

	public static void main(String[] args) throws Exception {
		startServer(7777);
	}

	/*
	 * 	长度+类型+数据模式的传输方式
	 * */
	
	private static void startServer(int port) throws Exception {
		
		ServerSocket serverSocket = new ServerSocket(port);
		System.out.println("等待客户端的连接......");
		
		//如果需要使用多线程，那就需要线程池，防止并发过高时创建过多线程耗尽资源
		ExecutorService threadPool = Executors.newFixedThreadPool(100);
		
		while(true) {
			//接收客户端的信息
			while (true) {
				Socket socket = serverSocket.accept();
			      Runnable runnable=()->{
			        try {
			        	InputStream inputStream = socket.getInputStream();
			        	byte[] b ;
			        	while(true) {
			        		//首先读取两个字节表示的长度
			        		int first = inputStream.read();
			        		//如果读取的值为-1，说明到了流的末尾，Socket已经关闭，此时不能再去读取
			        		if( first == -1) {
			        			break;
			        		}
			        		int second = inputStream.read();
			        		int length = (first << 8 ) + second;
			        		
			        		//构造一个指定长度的byte数组
			        		b = new byte[length];
			        		//读取指定长度的消息即可
			        		inputStream.read(b);
			        		System.out.println("客户端发来的信息是："+ new String(b,"UTF-8"));
			        	}
			        	inputStream.close();
		        		socket.close();
			        } catch (Exception e) {
			          e.printStackTrace();
			        }
			      };
			      threadPool.submit(runnable);
			  }

		}

	}

}
