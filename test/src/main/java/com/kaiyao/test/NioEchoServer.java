package com.kaiyao.test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NioEchoServer 
{
	
	public static void serve(int port) throws IOException
	{
		System.out.println("Listening for connections on port " + port);
		ServerSocketChannel serverChannel = ServerSocketChannel.open();
		ServerSocket ss = serverChannel.socket();
		InetSocketAddress address = new InetSocketAddress(port);
		ss.bind(address);
		serverChannel.configureBlocking(false);
		Selector selector = Selector.open();
		serverChannel.register(selector, SelectionKey.OP_ACCEPT);
		while(true)
		{
			try
			{
				selector.select();
			}
			catch(IOException e)
			{
				e.printStackTrace();
				break;
			}
			Set readyKeys = selector.selectedKeys();
			Iterator iterator = readyKeys.iterator();
			while(iterator.hasNext())
			{
				SelectionKey key = (SelectionKey)(iterator.next());
				try
				{
					if(key.isAcceptable())
					{
						ServerSocketChannel server = (ServerSocketChannel)key.channel();
						SocketChannel client = server.accept();
						System.out.println("Accepted connection from " + client);
						client.configureBlocking(false);
						client.register(selector, SelectionKey.OP_WRITE | SelectionKey.OP_READ, ByteBuffer.allocate(100));
					}
					if(key.isReadable())
					{
						ByteBuffer byteBuffer = ByteBuffer.allocate(100);
						SocketChannel client = (SocketChannel)key.channel();
						try
						{
							int len = client.read(byteBuffer);
							if(len > 0)
							{	
								String msg = new String(byteBuffer.array(), 0 , byteBuffer.limit(), "UTF-8");
								System.out.println("接受到client消息：" + msg);
//								byteBuffer.flip();
//								client.write(byteBuffer);
							}
							byteBuffer.flip();
							ByteBuffer re2client = byteBuffer.duplicate();
							key.attach(re2client);
							byteBuffer.clear();
							key.interestOps(SelectionKey.OP_WRITE);
						}
						catch(Exception e)
						{
							e.printStackTrace();
						}
					}
					if(key.isWritable())
					{
						SocketChannel client = (SocketChannel)key.channel();
						ByteBuffer output = (ByteBuffer)key.attachment();
						//output.flip();
						if(output.hasRemaining())
						{
							client.write(output);
						}
						output.clear();
						key.interestOps(SelectionKey.OP_READ);
					}
				}
				catch(Exception e)
				{
					key.cancel();
					try
					{
						key.channel().close();
					}
					catch(IOException ex)
					{
						
					}
				}
				iterator.remove();
			}
		}
	}
	
	public static void main(String[] args) throws IOException
	{
		serve(9500);
	}
}
