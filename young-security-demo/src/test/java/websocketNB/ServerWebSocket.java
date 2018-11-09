package websocketNB;

import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.apache.log4j.Logger;


/**
 * @ServerEndpoint：注解是一个类层次的注解，它的功能主要是将目前的类定义成一个 web socket 服务器端,
 * 注解的值将被用于监听用户连接的终端访问URL地址,客户端可以通过这个URL来连接到WebSocket服务器端
 * 可以把当前类变成 web socket 服务类
 * @author Youngni
 *
 */
@ServerEndpoint(value = "/websocket/{userno}")

public class ServerWebSocket {

	private static Logger logger = Logger.getLogger(ServerWebSocket.class);
	/**
	 * 记录连接数
	 */
	private static int onlineCount = 0 ;
	/**
	 * Concurrent包的线程安全Set，用户来存放每个客户端对应的 web socket 对象。
	 * 若要实现服务器与单一客户端通信，可以使用 Map 来存放，其中 Key 为用户标识
	 */
	private static ConcurrentHashMap<String, ServerWebSocket> webSocketSet =
			new ConcurrentHashMap<String, ServerWebSocket>();
	/**
	 * 于某个客户端的连接会话，需要通过它来给客户端发送数据
	 */
	private Session webSocketSession;
	/**
	 * 当前发消息的人员编号
	 */
	private String userno = "";
	
	
	
	public static synchronized int getOnlineCount() {
		return onlineCount;
	}
	public static synchronized void setOnlineCount() {
		ServerWebSocket.onlineCount++;
	}
}
