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

import com.sukai.chatroom.socket.Client;

public class ClientUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JButton btStart;// 启动服务器
	public JButton btSend;// 发送信息按钮
	public JTextField tfSend;// 需要发送的文本信息
	public JTextArea taShow;// 信息展示
	public Client client;// 用来监听客户端连接

	public ClientUI() {
		super("客户端");
	}
	
	public void init() {
		btStart = new JButton("连接到服务器");
		btSend = new JButton("发送信息");
		tfSend = new JTextField(10);
		taShow = new JTextArea();

		btStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				client = new Client(ClientUI.this);
			}
		});
		
		btSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				client.sendMsg(tfSend.getText());
				tfSend.setText("");
			}
		});
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				int a = JOptionPane.showConfirmDialog(null, "确定关闭吗？", "温馨提示",
						JOptionPane.YES_NO_OPTION);
				if (a == 1) {
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