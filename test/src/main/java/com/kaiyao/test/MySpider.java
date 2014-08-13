package com.kaiyao.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;


public class MySpider 
{
	private final static HttpClient httpclient = new DefaultHttpClient();
	
	public static void main(String[] args) throws IOException
	{
		login("用户名", "密码", false);
		get("http://.....");
	}
	
	static void login(String user, String passwd, boolean debug) throws IOException
	{
		HttpPost post = new HttpPost("http://dict.cn/login.php");
		post.setHeader("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US) AppleWebKit/534.3 (KHTML, like Gecko) Chrome/6.0.472.63 Safari/534.3");
			
		//构造post方法的<name: value>参数对，即填写表单信息
		List<NameValuePair> qparams = new ArrayList<NameValuePair>();
		qparams.add(new BasicNameValuePair("username", user));
		qparams.add(new BasicNameValuePair("password", passwd));
		qparams.add(new BasicNameValuePair("url", "http://www16.dict.cn/bdc/141"));
		qparams.add(new BasicNameValuePair("loginforever", "1"));
			
		UrlEncodedFormEntity params = new UrlEncodedFormEntity(qparams, "utf-8");
		post.setEntity(params);
			
		HttpResponse response = httpclient.execute(post);
			
		if(debug)
		{
			System.out.println(response.getStatusLine());
			HttpEntity entity = response.getEntity();
			deal(entity);
		}
	}
	
	static void get(String url) throws IOException
	{
		HttpGet get = new HttpGet(url);
		HttpResponse response = httpclient.execute(get);
		System.out.println(response.getStatusLine());
		HttpEntity entity = response.getEntity();
		deal(entity);
	}
	
	private static void deal(HttpEntity entity) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(entity.getContent(), "GBK"));
		System.out.println(IOUtils.toString(br));
	}

}
