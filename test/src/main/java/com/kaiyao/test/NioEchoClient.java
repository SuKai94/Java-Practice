package com.kaiyao.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;


public class NioEchoClient 
{
	public static void main(String[] args) throws Exception
	{
		Socket client = new Socket("127.0.0.1", 9500);
		BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
        System.out.println("Client start!");
		while(true)
		{
			int i = 0;
			String msg = "Hello Server" + i;
			writer.write(msg);
			writer.flush();
			System.out.println("Client send!");
			String str = reader.readLine();
			System.out.println("Client第" + i + "次从Server读取数据：" + str);
			i++;
			if(i == 5)
			{
				break;
			}
		}
		writer.close();
		client.close();
	}
}
