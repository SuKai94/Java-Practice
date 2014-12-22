package com.susu.designpatterns.guanchazhe;

public class Bootstrap {
	
	ConcreteSubject subject = new ConcreteSubject();
	
	Observer observer = new ConcreteObserver();
	
	subject.attach(observer);

}
