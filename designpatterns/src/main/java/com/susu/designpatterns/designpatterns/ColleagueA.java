package com.susu.designpatterns.designpatterns;

public class ColleagueA extends AbstractColleague {

	public ColleagueA(AbstractMediator mediator) {
		super(mediator);
	}
	
	public void self() {
		System.out.println("同事A：做好自己份内事情");
	}
	
	public void out() {
		System.out.println("同事A：请求同事B做好自己份内事");
		super.mediator.execute("ColleagueB", "self");
	}
}
