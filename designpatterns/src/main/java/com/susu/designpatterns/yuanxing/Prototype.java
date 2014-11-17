package com.susu.designpatterns.yuanxing;

public abstract class Prototype implements Cloneable {
	
	private String id;
	
	public Prototype(String id) {
		this.id = id;
	}
	
	public String getId() {
		return this.id;
	}
	
	public abstract Prototype cloneType();
}
