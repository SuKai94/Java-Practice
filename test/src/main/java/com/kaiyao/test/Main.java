package com.kaiyao.test;

import java.io.*;
import java.net.*;

public class Main 
{
	
	public static void main(String[] args)
	{
		try
		{
			InetAddress ind = InetAddress.getLocalHost();
			System.out.println(ind.toString());
			String strName = ind.getHostName();
			System.out.println(strName);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
