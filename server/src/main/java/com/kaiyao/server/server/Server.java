package com.kaiyao.server.server;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;

public class Server {
	private int port;

	public Server(int port) {
		this.port = port;
	}

	public static void main(String[] args) {
		try {
			new Server(9500).serve();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void serve() throws Exception {
		// NioEventLoopGroup是一个处理I/O的多线程事件环，即为Netty4里的线程池
		// EventLoopGroup代替4.0之前的ChannelFactory
		// boos用于处理连接请求，worker用于处理连接的信息
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup, workerGroup)
					.channel(NioServerSocketChannel.class)
					.childHandler(new ChannelInitializer<SocketChannel>() {
						public void initChannel(SocketChannel ch)
								throws Exception {
							ch.pipeline().addLast(new ServerHandler());
						}
					}).option(ChannelOption.SO_BACKLOG, 128)
					.childOption(ChannelOption.SO_KEEPALIVE, true);
			// Bind and start to accept incoming connections
			ChannelFuture f = b.bind(this.port).sync();
			System.out.println(Server.class.getName()
					+ " started and listen on" + f.channel().localAddress());
			f.channel().closeFuture().sync();
		} finally {
			workerGroup.shutdownGracefully();
			bossGroup.shutdownGracefully();
		}
	}

	static class ServerHandler extends ChannelInboundHandlerAdapter {
		public void channelRead(ChannelHandlerContext ctx, Object msg)
				throws UnsupportedEncodingException {
			try {
				ByteBuf in = (ByteBuf) msg;
				String str = in.toString(CharsetUtil.UTF_8);
				str = str.trim();
				if (!(str.equals("exit"))) {
					System.out.println("Receive message: " + str + " ,From "
							+ ctx.channel().remoteAddress());
					byte[] responseByteArray = ("You said: " + str + "\n")
							.getBytes("UTF-8");
					ByteBuf out = ctx.alloc().buffer(responseByteArray.length);
					out.writeBytes(responseByteArray);
					ctx.writeAndFlush(out);
				} else {
					// ctx.write(msg);
					// ctx.flush();
					byte[] responseByteArray = "Goodbye, Client\n"
							.getBytes("UTF-8");
					ByteBuf out = ctx.alloc().buffer(responseByteArray.length);
					out.writeBytes(responseByteArray);
					ctx.writeAndFlush(out);
					ctx.close();
				}
			} finally {
				ReferenceCountUtil.release(msg);
			}
		}

		public void channelActive(ChannelHandlerContext ctx) {
			System.out.println("New Comer: " + ctx.channel().remoteAddress());
		}

		public void channelInactive(ChannelHandlerContext ctx) {
			System.out.println("Comer : " + ctx.channel().remoteAddress()
					+ " leave!");
		}

		public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
			cause.printStackTrace();
			ctx.close();
		}
	}
}
