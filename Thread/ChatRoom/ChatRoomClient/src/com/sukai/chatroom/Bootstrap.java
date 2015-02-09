package com.sukai.chatroom;

import com.sukai.chatroom.ui.ClientUI;

public class Bootstrap {

	public static void main(String[] args) {
		ClientUI clientUI = new ClientUI();
		clientUI.init();
	}
}