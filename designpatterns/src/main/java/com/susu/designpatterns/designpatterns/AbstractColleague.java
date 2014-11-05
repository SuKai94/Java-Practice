package com.susu.designpatterns.designpatterns;

/**
 * Hello world!
 *
 */
public abstract class AbstractColleague {
	
	protected AbstractMediator mediator;
	
	public AbstractColleague(AbstractMediator mediator) {
		this.mediator = mediator;
	}
	
	public void setMediator(AbstractMediator mediator) {
		this.mediator = mediator;
	}
}
