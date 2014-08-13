package com.kaiyao.test;

interface Reverser{
	public String reverse(String str);
}

public class JdkReverser implements Reverser{
	
	public String reverse(String str){
		if((str == null) || (str.length() <= 1)){
			return str;
		}
		StringBuffer reverse = new StringBuffer(str.length());
		for(int i=str.length()-1; i>=0 ; i--){
			reverse.append(str.charAt(i));
		}
		return reverse.toString();
	}
}
