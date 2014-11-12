package com.susu.designpatterns.zhongjiezhe;

import java.util.Hashtable;

public abstract class AbstractMediator {
	
	protected Hashtable<String, AbstractColleague> colleagues = new Hashtable<String, AbstractColleague>();
	
	public void addColleague(String name, AbstractColleague colleague) {
		this.colleagues.put(name, colleague);
	}
	
	public void deleteColleague(String name) {
		this.colleagues.remove(name);
	}
	
	public abstract void execute(String name, String method);
	
}
