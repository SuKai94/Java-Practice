package com.susu.designpatterns.zhongjiezhe;

/**
*
* @author SuSu
*
*/

/*
 * Java设计模式之中介者模式
 */
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
