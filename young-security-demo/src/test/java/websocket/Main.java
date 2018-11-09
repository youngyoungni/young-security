package websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 程序入口，负责启动应用
 * @author Youngni
 *
 */
public class Main {

	public static void main(String[] args) {
		EventLoopGroup boosGroup = new NioEventLoopGroup();
		EventLoopGroup workGroup = new NioEventLoopGroup();
		
		try {
			ServerBootstrap b = new ServerBootstrap();
			b.group(boosGroup,workGroup);
			b.channel(NioServerSocketChannel.class);
			b.childHandler(new MyWebSocketHandler());
			System.out.println("服务端开启等待客户端连接....");
			
			Channel channel = b.bind(8888).sync().channel();
			channel.closeFuture().sync();
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			//优雅退出程序
			boosGroup.shutdownGracefully();
			workGroup.shutdownGracefully();
		}
	}

}
