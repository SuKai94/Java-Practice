package com.susu.designpatterns.celue;

/**
*
* @author SuSu
*
*/

/*
 * Java设计模式之策略模式
 */
public class Client {
	
	public static void main(String[] args) {
		PriceContext priceContext = new PriceContext("Primary");
		double FinalPrice = priceContext.getFinalPrice(10.24);
		System.out.println(FinalPrice);
	}
}
