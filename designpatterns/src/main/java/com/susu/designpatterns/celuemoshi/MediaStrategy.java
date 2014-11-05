package com.susu.designpatterns.celuemoshi;

public class MediaStrategy implements Strategy {

	public double calPrice(double booksPrice) {
		System.out.println("中级策略启动");
		return booksPrice * 0.9;
	}

}
