package com.susu.designpatterns.mobanfangfa;

public class Bootstrap {
	
	public static void main(String[] args) {
		AbstractClass a = new ConcreteClassA();
		a.templateMethod();
		
		AbstractClass b = new ConcreteClassB();
		b.templateMethod();
	}
}
