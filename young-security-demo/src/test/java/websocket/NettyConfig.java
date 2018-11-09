package websocket;

import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * Netty 配置类
 * Netty是由JBOSS提供的一个java开源框架。
 * Netty提供异步的、事件驱动的网络应用程序框架和工具，
 * 用以快速开发高性能、高可靠性的网络服务器和客户端程序
 * @author Youngni
 *
 */
public class NettyConfig {
/*
  	IO 通信模型：同步，异步，阻塞，非阻塞
  	1、阻塞式I/O：blocking IO
	2、非阻塞式I/O： nonblocking IO
	3、I/O复用（select，poll，epoll...）：IO multiplexing
	4、信号驱动式I/O（SIGIO）：signal driven IO
	5、异步I/O（POSIX的aio_系列函数）：asynchronous IO
 */
	/**
	 * 存储每个客户端接入进来的 channel 对象
	 */
	public static ChannelGroup group = 
			new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
}
