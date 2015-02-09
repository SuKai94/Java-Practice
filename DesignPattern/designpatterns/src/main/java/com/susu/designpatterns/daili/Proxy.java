package com.susu.designpatterns.daili;

public class Proxy extends Subject {
	
	RealSubject realSubject;
	
	@Override
	public void Request() {
		if (realSubject == null) {
			realSubject = new RealSubject();
		}
		System.out.println("Proxy Do!");
		realSubject.Request();
	}

}
