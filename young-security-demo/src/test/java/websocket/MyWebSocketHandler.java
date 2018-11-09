package websocket;

import java.util.Date;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.websocketx.CloseWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PingWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PongWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshakerFactory;
import io.netty.util.CharsetUtil;

/**'
 * 接收处理响应客户端webSocket请求的核心业务处理类
 * @author Youngni
 *
 */
public class MyWebSocketHandler extends SimpleChannelInboundHandler<Object> {

	private WebSocketServerHandshaker handshaker;
	private static final String WEB_SOCKET_URL = "ws://localhost:8888/websocket";
	
	//服务器处理客户端 websocket 请求的核心方法
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
		//处理客户端向服务器发起 HTTP 握手请求的业务
		if( msg instanceof FullHttpRequest) {
			
			handHttpRequest(ctx, (FullHttpRequest)msg);
			
		}else if( msg instanceof WebSocketFrame) {	//处理 WebSocket 连接业务
			
			handWebSocketFrame(ctx,(WebSocketFrame)msg);
		}
	}
	
	/**
	 * 处理客户端和服务端之间的 webSocket 业务
	 * @param ctx
	 * @param frame
	 */
	private void handWebSocketFrame(ChannelHandlerContext ctx , WebSocketFrame frame) {
		
		//判断是否是关闭 webSocket 的指令
		if( frame instanceof CloseWebSocketFrame) {
			handshaker.close(ctx.channel(), (CloseWebSocketFrame) frame.retain());
		}
		//判断是否是 ping 消息
		if ( frame instanceof PingWebSocketFrame ) {
			ctx.channel().write(new PongWebSocketFrame(frame.content().retain()));
			return;
		}
		//判断是否是 二进制 消息，是二进制抛出消息
		if( !(frame instanceof TextWebSocketFrame) ) {
			System.out.println("目前我们不支持二进制消息");
			throw new RuntimeException("【"+this.getClass().getName()+"】不支持消息");
		}
		//返回应答消息
		String request = ((TextWebSocketFrame) frame).text();	//获取客户端发送给服务端的消息
		System.out.println("服务端收到客户端的消息："+request);
		TextWebSocketFrame tws = new TextWebSocketFrame(new Date().toString() 
													+ ctx.channel().getClass() 
													+" ====> "
													+ request);
		//群发，服务端向每个连接上来的客户端群发消息
		NettyConfig.group.writeAndFlush(tws);
		
	}
	
	/**
	 * 处理客户端向服务端发起HTTP握手请求的业务
	 * @param ctx
	 * @param request
	 */
	private void handHttpRequest(ChannelHandlerContext ctx ,FullHttpRequest request) {
		// 不是WebSocket 请求
		if (!request.getDecoderResult().isSuccess() || !("websocket".equals(request.headers().get("Upgrade")))) {
			sendHttpResqonse(ctx, request, 
					new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));
			return;
		}
		// 是 WebSocket 请求
		WebSocketServerHandshakerFactory wsFactory = 
				new WebSocketServerHandshakerFactory(WEB_SOCKET_URL,null,false);	//工厂类
		handshaker = wsFactory.newHandshaker(request);
		
		if( handshaker == null) {
			WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
		}else {
			handshaker.handshake(ctx.channel(), request);
		}
	}
	

	/**
	 *  服务器对客户端的响应
	 * @param ctx
	 * @param request
	 * @param response
	 */
	private void sendHttpResqonse(ChannelHandlerContext ctx, FullHttpRequest request ,
			DefaultFullHttpResponse response) {
		if (response.getStatus().code() != 200) {
			//响应失败，将失败信息写入 response 对象中
			ByteBuf buf = Unpooled.copiedBuffer(response.getStatus().toString(),CharsetUtil.UTF_8);
			response.content().writeBytes(buf);
			buf.release();
		}
		//服务端对客户端发送数据
		ChannelFuture f = ctx.channel().writeAndFlush(response);
		if (response.getStatus().code() != 200) {
			f.addListener(ChannelFutureListener.CLOSE);
		}
	}

	//客户端和服务器初建连接时候调用
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		NettyConfig.group.add(ctx.channel());
		System.out.println("客户端和服务端连接开始........");
	}

	//客户端和服务器连接断开的时候调用
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		NettyConfig.group.remove(ctx.channel());
		System.out.println("客户端和服务端连接断开.........");
	}
	
	//服务端接收客户端发送过来的数据接收之后调用
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}

	//工程出现异常的时候调用
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}


	
	
}
