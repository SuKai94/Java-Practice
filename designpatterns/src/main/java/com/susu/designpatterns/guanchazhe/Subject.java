package com.susu.designpatterns.guanchazhe;

import java.util.ArrayList;
import java.util.List;

public abstract class Subject {

	private List<Observer> list = new ArrayList<Observer>();

	public abstract void attach(Observer observer);

	public void detach(Observer observer) {
		list.remove(observer);
	}

	public void nodifyOBservers(String newState) {
		for (Observer observer : list) {
			observer.update(newState);
		}
	}

}
