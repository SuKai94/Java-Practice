package com.susu.designpatterns.daili;

public class RealSubject extends Subject {

	@Override
	public void Request() {
		System.out.println("Real Request!");
	}

}
