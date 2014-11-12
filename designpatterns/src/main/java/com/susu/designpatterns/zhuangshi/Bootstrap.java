package com.susu.designpatterns.zhuangshi;

public class Bootstrap {
	
	public static void main(String[] args) {
		ConcreteComponent c = new ConcreteComponent();
		ConcreteDecoratorA a = new ConcreteDecoratorA();
		ConcreteDecoratorB b = new ConcreteDecoratorB();
		
		a.SetComponent(c);
		b.SetComponent(a);
		b.Operation();
	}
}
