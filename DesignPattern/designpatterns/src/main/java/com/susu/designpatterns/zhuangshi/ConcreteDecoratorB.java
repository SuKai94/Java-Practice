package com.susu.designpatterns.zhuangshi;

public class ConcreteDecoratorB extends Decorator {
	
	public void Operation() {
		super.Operation();
		AddedBehavior();
		System.out.println("装饰者B");
	}
	
	public void AddedBehavior() {
		System.out.println("装饰者B的额外功能");
	}
}
