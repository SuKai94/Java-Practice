package com.susu.designpatterns.zhuangshi;

public abstract class Decorator implements Component {

	protected Component component;

	public void SetComponent(Component component) {
		this.component = component;
	}

	public void Operation() {
		if (component != null) {
			component.Operation();
		}
	}

}
