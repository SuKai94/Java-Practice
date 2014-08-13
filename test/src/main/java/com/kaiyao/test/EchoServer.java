package com.kaiyao.test;

import java.net.InetSocketAddress;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class EchoServer
{
	private final int port;
	
	public EchoServer(int port)
	{
		this.port = port;
	}
	
	public void start() throws Exception
	{
		EventLoopGroup group = new NioEventLoopGroup();
		try
		{
			ServerBootstrap b = new ServerBootstrap();
			b.group(group)
			.channel(NioServerSocketChannel.class)
			.localAddress(new InetSocketAddress(port))
			.childHandler(new ChannelInitializer<SocketChannel>(){
				public void initChannel(SocketChannel ch) throws Exception
				{
					ch.pipeline().addLast(new EchoServerHandler());
				}
			});
			
			ChannelFuture f = b.bind().sync();
			System.out.println(EchoServer.class.getName() + "started and listen on" + f.channel().localAddress());
			f.channel().closeFuture().sync();
		}
		finally
		{
			group.shutdownGracefully().sync();
		}
	}
	
	public static void main(String[] args) throws Exception
	{
		if(args.length != 1)
		{
			System.err.println("Usage: " + EchoServer.class.getSimpleName() + "<port>");
		}
		int port = Integer.parseInt(args[0]);
		new EchoServer(port).start();
	}
	
	static class EchoServerHandler extends ChannelInboundHandlerAdapter
	{
		public void channelRead(ChannelHandlerContext ctx, Object msg)
		{
			System.out.println("Server received: " + msg);
			ctx.write(msg);
		}
		
		public void channleReadComplete(ChannelHandlerContext ctx)
		{
			ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
		}
		
		public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
		{
			cause.printStackTrace();
			ctx.close();
		}
	}
}
