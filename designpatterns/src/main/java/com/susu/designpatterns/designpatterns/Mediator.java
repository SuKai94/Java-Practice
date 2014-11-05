package com.susu.designpatterns.designpatterns;

public class Mediator extends AbstractMediator {

	@Override
	public void execute(String name, String method) {

		if ("self".equals(method)) {
			if ("ColleagueA".equals(name)) {
				ColleagueA colleague = (ColleagueA) super.colleagues
						.get("ColleagueA");
				colleague.self();
			} else {
				ColleagueB colleague = (ColleagueB) super.colleagues
						.get("ColleagueB");
				colleague.self();
			}
		} else {
			if ("ColleagueA".equals(name)) {
				ColleagueA colleague = (ColleagueA) super.colleagues
						.get("ColleagueA");
				colleague.out();
			} else {
				ColleagueB colleague = (ColleagueB) super.colleagues
						.get("ColleagueB");
				colleague.out();
			}
		}
	}
}
