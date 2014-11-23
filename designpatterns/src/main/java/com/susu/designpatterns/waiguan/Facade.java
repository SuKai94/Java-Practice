package com.susu.designpatterns.waiguan;

/*
 * 外观类
 * 知道哪些子系统负责处理请求，将客户的请求代理给适当的子系统对象
 * 他需要了解所有子系统的方法或属性，进行组合，以备外界调用
 */
public class Facade {
	
	private SubSystemOne one;
	private SubSystemTwo two;
	private SubSystemThree three;
	private SubSystemFour four;
	
	public Facade() {
		one = new SubSystemOne();
		two = new SubSystemTwo();
		three = new SubSystemThree();
		four = new SubSystemFour();
	}
	
	public void methodA() {
		System.out.println("---方法组A---");
		one.methodOne();
		two.methodTwo();
		four.methodFour();
	}

	public void methodB() {
		System.out.println("---方法组A---");
		two.methodTwo();
		three.methodThree();
	}
}
