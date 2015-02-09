package com.susu.designpatterns.guanchazhe;

public class Bootstrap {
	
	public static void main(String[] args) {
		
		Subject subject = new ConcreteSubject();
		Observer observer1 = new ConcreteObserver();
		Observer observer2 = new ConcreteObserver();
		Observer observer3 = new ConcreteObserver();
		Observer observer4 = new ConcreteObserver();
		
		subject.addObserver(observer1);
		subject.addObserver(observer2);
		subject.addObserver(observer3);
		subject.addObserver(observer4);
		
		subject.notifyObserver("Hello World");
	}

}
