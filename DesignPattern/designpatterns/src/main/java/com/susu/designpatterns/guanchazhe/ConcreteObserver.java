package com.susu.designpatterns.guanchazhe;

public class ConcreteObserver implements Observer {

	@Override
	public void update(String str) {
		System.out.println("收到的通知为：" + str);
	}
}
