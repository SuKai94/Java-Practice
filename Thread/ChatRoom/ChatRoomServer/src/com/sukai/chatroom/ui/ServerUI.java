package com.sukai.chatroom.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.sukai.chatroom.socket.Server;

public class ServerUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 启动服务器按钮
	private JButton btStart;
	// 向客户端发送信息按钮
	private JButton btSend;
	// 需要发送的文本信息
	private JTextField tfSend;
	// 消息展示
	public JTextArea taShow;
	// 用来监听客户端连接
	private Server server;

	public ServerUI() {
		super("服务器端");
	}
	
	public void init() {
		btStart = new JButton("启动服务器");
		btSend = new JButton("发送信息");
		tfSend = new JTextField(10);
		taShow = new JTextArea();

		btStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				server = new Server(ServerUI.this);
			}
		});

		btSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				server.sendMsg(tfSend.getText());
				tfSend.setText("");
			}
		});

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				int a = JOptionPane.showConfirmDialog(null, "确定关闭吗？", "温馨提示",
						JOptionPane.YES_NO_OPTION);
				if (a == 1) {
					server.closeServer();
					// 关闭
					System.exit(0);
				}
			}
		});

		JPanel top = new JPanel(new FlowLayout());
		top.add(tfSend);
		top.add(btSend);
		top.add(btStart);
		this.add(top, BorderLayout.SOUTH);
		final JScrollPane sp = new JScrollPane();
		sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		sp.setViewportView(this.taShow);
		this.taShow.setEditable(false);
		this.add(sp, BorderLayout.CENTER);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(400, 300);
		this.setLocation(100, 200);
		this.setVisible(true);
	}
}