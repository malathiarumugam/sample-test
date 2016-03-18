package com.bluemeric.connection;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

public class HttpConnection {

	/**
	 * @param args
	 */
	public int statusCode ;
	public String responseString ;

	private HttpClient client;

	public HttpConnection() throws Exception {
		this.client = new HttpClient();
	}

	public int get(String url) throws Exception
	{
		System.out.println("URL : GET " + url);
		GetMethod gm = new GetMethod(url);  
		statusCode = client.executeMethod(gm);
		System.out.println(" statusCode = " + statusCode ); 
		responseString = gm.getResponseBodyAsString();
		System.out.println(" Response = " +  responseString); 
		gm.releaseConnection();
		return statusCode;
	}


	//	public static void main(String[] args) throws Exception {
	//		// TODO Auto-generated method stub
	//		HttpConnection con = new HttpConnection();
	//
	//	}

}
