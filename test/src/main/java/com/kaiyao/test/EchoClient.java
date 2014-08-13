package com.kaiyao.test;

import java.net.InetSocketAddress;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;

public class EchoClient 
{
	private final String host;
	private final int port;
	
	public EchoClient(String host, int port)
	{
		this.host = host;
		this.port = port;
	}
	
	public void start() throws Exception
	{
		EventLoopGroup group = new NioEventLoopGroup();
		try
		{
			Bootstrap b = new Bootstrap();
			b.group(group)
			.channel(NioSocketChannel.class)
			.remoteAddress(new InetSocketAddress(host, port))
			.handler(new ChannelInitializer<SocketChannel>(){
				public void initChannel(SocketChannel ch) throws Exception
				{
					ch.pipeline().addLast(new EchoClientHandler());
				}
			});
			ChannelFuture f = b.connect().sync();
			f.channel().closeFuture().sync();
		}
		finally
		{
			group.shutdownGracefully().sync();
		}
	}
	
	public static void main(String[] args) throws Exception
	{
		if(args.length != 2)
		{
			System.err.println("Usage: " + EchoClient.class.getSimpleName() + "<host> <port>");
		}
		final String host = args[0];
		final int port = Integer.parseInt(args[1]);
		new EchoClient(host, port).start();
	}
	
	static class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf>
	{
		public void channelActive(ChannelHandlerContext ctx)
		{
			ctx.write(Unpooled.copiedBuffer("Netty rocks", CharsetUtil.UTF_8));
		}
		
		public void channelRead0(ChannelHandlerContext ctx, ByteBuf in)
		{
			System.out.println("Client received: " + ByteBufUtil.hexDump(in.readBytes(in.readableBytes())));
		}
		
		public void exceptinCaught(ChannelHandlerContext ctx, Throwable cause)
		{
			cause.printStackTrace();
			ctx.close();
		}
	}
}
