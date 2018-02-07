package com.example.test;

import java.net.URI;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.databene.benerator.anno.Source;
import org.databene.feed4testng.FeedTest;
import org.testng.Assert;
import org.testng.annotations.Test;



public class FromTest extends FeedTest {
	
	@Test(dataProvider="feeder")
	@Source("./data/add.csv")
	public static void doGet(String a,String b, String expected) throws Exception {
		
		CloseableHttpClient client = HttpClients.createDefault();
		URI uri = new URIBuilder()
				.setScheme("http")
				.setHost("192.168.146.132")
				.setPort(8080)
				.setPath("/project158/FromServlet")
				.setParameter("a", a)
				.setParameter("b", b)
				.build();
		HttpGet request = new HttpGet(uri);
		CloseableHttpResponse response = client.execute(request);
		String actual = EntityUtils.toString(response.getEntity());
		System.out.println(actual);
		Assert.assertEquals(actual,expected);
	}
}
