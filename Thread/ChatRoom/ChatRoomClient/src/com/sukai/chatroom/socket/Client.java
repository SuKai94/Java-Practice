package com.sukai.chatroom.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import com.sukai.chatroom.ui.ClientUI;

public class Client extends Thread {
	private ClientUI ui;
	private BufferedReader reader;
	private BufferedWriter writer;
	private Socket client;

	public Client(ClientUI ui) {
		this.ui = ui;
		try {
			client = new Socket("127.0.0.1", 9600);//这里设置连接服务器端的IP的端口
            uiShow("连接到服务器成功：端口9600");
            reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
			writer = new BufferedWriter(new OutputStreamWriter(
					client.getOutputStream()));
		} catch (IOException e) {
			uiShow("连接服务器失败：端口9600");
			uiShow(e.toString());
		}
		this.start();
	}

	public void run() {
		String recMsg = null;
		while (true) {
			try {
				recMsg = reader.readLine();
				if (recMsg == null) {
					uiShow("服务端已经退出！");
					break;
				}
				uiShow("Server Say: " + recMsg);
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
			uiShow("Client Say: " + msg);
		} catch (Exception e) {
			uiShow(e.toString());
		}
	}

	public void uiShow(String str) {
		if (str != null) {
			this.ui.taShow.setText(this.ui.taShow.getText() + str + "\n");
		}
	}
}