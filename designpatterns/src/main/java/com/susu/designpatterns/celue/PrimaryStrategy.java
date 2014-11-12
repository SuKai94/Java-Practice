package com.susu.designpatterns.celue;

public class PrimaryStrategy implements Strategy {

	public double calPrice(double booksPrice) {
		System.out.println("初级策略启动");
		return booksPrice;
	}

}
