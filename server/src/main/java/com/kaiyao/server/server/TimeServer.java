package com.kaiyao.server.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;


public class TimeServer
{
	private int port;
	
	public TimeServer(int port)
	{
		this.port = port;
	}
	
	
	public static void main(String[] args)
	{
		try
		{
			new TimeServer(9500).serve();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void serve() throws Exception 
	{
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
							ch.pipeline().addLast(new TimeServerHandler());
						}
					})
					.option(ChannelOption.SO_BACKLOG, 128)
					.childOption(ChannelOption.SO_KEEPALIVE, true);
			// Bind and start to accept incoming connections
			ChannelFuture f = b.bind(this.port).sync();
			System.out.println(Server.class.getName() + " started and listen on" + f.channel().localAddress());
			f.channel().closeFuture().sync();
		} finally {
			workerGroup.shutdownGracefully();
			bossGroup.shutdownGracefully();
		}
	}
	
	static class TimeServerHandler extends ChannelInboundHandlerAdapter {

	    @Override
	    public void channelActive(final ChannelHandlerContext ctx) { // (1)
	        final ByteBuf time = ctx.alloc().buffer(4); // (2)
	        time.writeInt((int) (System.currentTimeMillis() / 1000L + 2208988800L));

	        final ChannelFuture f = ctx.writeAndFlush(time); // (3)
	        f.addListener(new ChannelFutureListener() {
	            public void operationComplete(ChannelFuture future) {
	                assert f == future;
	                ctx.close();
	            }
	        }); // (4)
	    }

	    @Override
	    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
	        cause.printStackTrace();
	        ctx.close();
	    }
	}

}
