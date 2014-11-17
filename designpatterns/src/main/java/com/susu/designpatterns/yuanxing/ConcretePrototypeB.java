package com.susu.designpatterns.yuanxing;

public class ConcretePrototypeB extends Prototype {

	public ConcretePrototypeB(String id) {
		super(id);
	}

	@Override
	public Prototype cloneType() {
		Prototype type = null;
		try {
			type = (Prototype)this.clone();
		} catch(CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return type;
	}

}
