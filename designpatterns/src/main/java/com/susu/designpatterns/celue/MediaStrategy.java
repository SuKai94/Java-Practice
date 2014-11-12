package com.susu.designpatterns.celue;

public class MediaStrategy implements Strategy {

	public double calPrice(double booksPrice) {
		System.out.println("中级策略启动");
		return booksPrice * 0.9;
	}

}
