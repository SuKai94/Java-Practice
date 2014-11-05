package com.susu.designpatterns.designpatterns;

public class ColleagueB extends AbstractColleague {
	
	public ColleagueB(AbstractMediator mediator) {
		super(mediator);
	}
	
	public void self() {
		System.out.println("同事B：做好自己份内事情");
	}
	
	public void out() {
		System.out.println("同事B：请求同事A做好自己份内事");
		super.mediator.execute("ColleagueA", "self");
	}
}
