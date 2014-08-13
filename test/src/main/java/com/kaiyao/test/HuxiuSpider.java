package com.kaiyao.test;

import java.io.IOException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


public class HuxiuSpider 
{
	
	public static void main(String[] args) throws IOException, InterruptedException
	{
		//Spider spider = new HuxiuSpider();
		MultiThreadedHttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();
		HttpClient httpClient = new HttpClient(connectionManager);
		String url = "http://www.huxiu.com/article/101/1/html";
		HttpMethod getMethod = new GetMethod(url);
		int status = httpClient.executeMethod(getMethod);
		System.out.println("status: " + status);
		if(HttpStatus.SC_OK == status)
		{
			String response = getMethod.getResponseBodyAsString();
			OutputStreamWriter outs = new OutputStreamWriter(new FileOutputStream("/home/sk/test/"+"101.html"));
			outs.write(response);
			outs.close();
			System.out.println("successfully!");
		}
	}
	
	public void start(HttpClient httpClient, String startUrl) throws InterruptedException
	{
		ThreadPoolExecutor threadPool = new ThreadPoolExecutor(100, 500, 3, TimeUnit.SECONDS,
				new ArrayBlockingQueue<Runnable>(10000), new ThreadPoolExecutor.DiscardOldestPolicy());
		String baseUrl = "http://www.huxiu.com/article/";
		HttpMethod getMethod = null;
		String id = startUrl.split("/")[4];
		Integer count = Integer.valueOf(id);
		for(int i = count; i > 100; i--)
		{
			getMethod = new GetMethod(baseUrl + i + "/1.html");
			threadPool.execute(new ThreadPoolTask(httpClient, getMethod, i));
			Thread.sleep(1 * 1000);
		}
		while(true)
		{
			Thread.sleep(10 * 1000);
			if(threadPool.getActiveCount() == 0)
			{
				threadPool.shutdown();
				break;
			}
		}
	}
	
	static class ThreadPoolTask implements Runnable
	{
		private static final long serialVersionUID = 0;
		HttpClient httpClient = null;
		HttpMethod getMethod = null;
		int count = 0;
		
		ThreadPoolTask(HttpClient httpClient, HttpMethod getMethod, int count)
		{
			this.httpClient = httpClient;
			this.getMethod = getMethod;
			this.count = count;
		}
		
		public void run()
		{
			try
			{
				System.out.println("Executing: " + getMethod.getURI());
				int status = httpClient.executeMethod(getMethod);
				if(status == HttpStatus.SC_OK)
				{
					Document doc = null;
					try
					{
						doc = Jsoup.parse(getMethod.getResponseBodyAsStream(), "utf-8", "");
					}
					catch(Exception e)
					{
						System.out.printf("Jsoup parsed %s failed, more: %s", getMethod.getURI(), e.getMessage());
					}
					crawl(doc, count);
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			finally
			{
				getMethod.releaseConnection();
			}
		}
	}
	
	static void crawl(Document doc, int count) throws IOException
	{
		//...
	}
	
}
