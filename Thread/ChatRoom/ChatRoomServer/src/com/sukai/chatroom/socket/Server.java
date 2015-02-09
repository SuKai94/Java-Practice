package com.sukai.chatroom.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import com.sukai.chatroom.ui.ServerUI;

public class Server extends Thread {
	private ServerUI ui;
	private ServerSocket server;
	private BufferedReader reader;
	private BufferedWriter writer;
	private Socket client;

	public Server(ServerUI ui) {
		this.ui = ui;
		this.start();
	}

	public void run() {
		String recMsg = null;
		try {
			server = new ServerSocket(9600);
			uiShow("启动服务器成功：端口9600");
			uiShow("等待客户端");
			client = server.accept();
			reader = new BufferedReader(new InputStreamReader(
					client.getInputStream()));
			writer = new BufferedWriter(new OutputStreamWriter(
					client.getOutputStream()));
			uiShow("连接成功" + client.toString());
		} catch (IOException e) {
			uiShow("启动服务器失败：端口9600");
			uiShow(e.toString());
		}
		// 一直监听客户端是否发送消息
		while (true) {
			try {
				recMsg = reader.readLine();
				if (recMsg == null) {
					uiShow("客户端已经退出！");
					break;
				}
				uiShow("Client Say: " + recMsg);
			} catch (IOException e) {
				uiShow(e.toString());
				break;
			}
		}
	}

	public void sendMsg(String msg) {
		try {
			writer.write(msg + "\n");
			writer.flush();
			uiShow("Server Say: " + msg);
		} catch (Exception e) {
			uiShow(e.toString());
		}
	}

	// 用于在UI界面显示信息
	public void uiShow(String str) {
		if (str != null) {
			this.ui.taShow.setText(this.ui.taShow.getText() + str + "\n");
		}
	}

	public void closeServer() {
		try {
			if (server != null)
				server.close();
			if (reader != null)
				reader.close();
			if (writer != null)
				writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}