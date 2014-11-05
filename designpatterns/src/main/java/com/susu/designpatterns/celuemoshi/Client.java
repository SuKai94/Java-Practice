package com.susu.designpatterns.celuemoshi;

public class Client {
	
	public static void main(String[] args) {
		PriceContext priceContext = new PriceContext("Primary");
		double FinalPrice = priceContext.getFinalPrice(10.24);
		System.out.println(FinalPrice);
	}
}
