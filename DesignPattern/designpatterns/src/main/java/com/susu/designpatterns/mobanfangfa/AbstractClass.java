package com.susu.designpatterns.mobanfangfa;

public abstract class AbstractClass {
	
	public abstract void primitiveOPeration1();
	public abstract void primitiveOPeration2();

	public void templateMethod() {
		primitiveOPeration1();
		primitiveOPeration2();
	}
}
