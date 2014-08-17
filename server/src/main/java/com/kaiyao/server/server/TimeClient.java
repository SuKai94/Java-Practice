package com.kaiyao.server.server;

import java.util.Date;
import java.util.List;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.ByteToMessageDecoder;

public class TimeClient
{
	public static void main(String[] args) throws Exception
	{
		String host = "127.0.0.1";
		int port = 9500;
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try
		{
			Bootstrap b = new Bootstrap();
			b.group(workerGroup);
			b.channel(NioSocketChannel.class);
			b.option(ChannelOption.SO_KEEPALIVE, true);
			b.handler(new ChannelInitializer<SocketChannel>(){
				public void initChannel(SocketChannel ch) throws Exception{
					ch.pipeline().addLast(new TimeDecoder(), new TimeClientHandler());
				}
			});
			
			ChannelFuture f = b.connect(host, port).sync();
			f.channel().closeFuture().sync();
		}
		finally
		{
			workerGroup.shutdownGracefully();
		}
	}
	
	static class TimeClientHandler extends ChannelInboundHandlerAdapter
	{
		public void channelRead(ChannelHandlerContext ctx, Object msg)
		{
			ByteBuf m = (ByteBuf)msg;
			try
			{
				long currentTimeMillis = (m.readUnsignedInt() - 2208988800L) * 1000L;
				System.out.println(new Date(currentTimeMillis));
				ctx.close();
			}
			finally
			{
				m.release();
			}
		}
		
		public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
		{
			cause.printStackTrace();
			ctx.close();
		}
	}
	
	static class TimeDecoder extends ByteToMessageDecoder
	{

		@Override
		protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception
		{
			if(in.readableBytes() < 4)
			{
				return;
			}
			out.add(in.readBytes(4));
		}
		
	}
}
