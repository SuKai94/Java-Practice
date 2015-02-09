package com.susu.designpatterns.waiguan;

public class Bootstrap {
	
	public static void main(String[] args) {
		Facade facade = new Facade();
		
		facade.methodA();
		facade.methodB();
	}
}
