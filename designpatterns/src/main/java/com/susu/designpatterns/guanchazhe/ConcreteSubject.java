package com.susu.designpatterns.guanchazhe;

public class ConcreteSubject extends Subject {
	
	private String state;
	
	public String getState() {
		return state;
	}
	
	public void change(String newState) {
		state = newState;
		System.out.println("主题状态为：" + state);
		this.nodifyOBservers(state);
	}

	@Override
	public void attach(Observer observer) {
		// TODO Auto-generated method stub
		
	}
}
