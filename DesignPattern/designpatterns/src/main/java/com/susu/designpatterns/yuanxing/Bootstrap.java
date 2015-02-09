package com.susu.designpatterns.yuanxing;

public class Bootstrap {
	
	public static void main(String[] args) {
		ConcretePrototypeA a1 = new ConcretePrototypeA("xtt");
		ConcretePrototypeA a2 = (ConcretePrototypeA) a1.cloneType();
		System.out.println("a2.id: " + a2.getId());
	}
}
