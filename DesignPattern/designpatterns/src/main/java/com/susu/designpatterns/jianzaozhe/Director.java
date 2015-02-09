package com.susu.designpatterns.jianzaozhe;

public class Director {
	
	public void construct(Builder builder) {
		
		builder.buildPartA();
		builder.buildPartB();
	}

}
