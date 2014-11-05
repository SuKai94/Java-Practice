package com.susu.designpatterns.designpatterns;

public class Main {
	
	public static void main(String[] args) {
		
		AbstractMediator mediator = new Mediator();
		
		ColleagueA colleagueA = new ColleagueA(mediator);
		ColleagueB colleagueB = new ColleagueB(mediator);
		
		mediator.addColleague("ColleagueA", colleagueA);
		mediator.addColleague("ColleagueB", colleagueB);
		
		colleagueA.self();
		colleagueA.out();
		
		colleagueB.self();
		colleagueB.out();
	}
	
}
