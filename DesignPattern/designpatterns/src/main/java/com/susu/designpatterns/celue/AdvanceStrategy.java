package com.susu.designpatterns.celue;

public class AdvanceStrategy implements Strategy {

	public double calPrice(double booksPrice) {
		System.out.println("高级策略启动");
		return booksPrice * 0.8;
	}

}
