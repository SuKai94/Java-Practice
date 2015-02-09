package com.susu.designpatterns.zhuangshi;

public class ConcreteDecoratorA extends Decorator {
	
	private String addedState;
	
	public void Operation() {
		super.Operation();
		this.addedState = "New State";
		System.out.println("装饰者A");
	}
}
