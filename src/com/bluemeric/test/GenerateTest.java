package com.bluemeric.test;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.TestNG;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.Listeners;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.bluemeric.connection.HttpConnection;

@Listeners(org.uncommons.reportng.HTMLReporter.class)
public class GenerateTest extends TestNG {

	/**
	 * @param args
	 */
	static String projectHome = System.getProperty("PROJECT_HOME") + "/";
	static Logger logger = Logger.getLogger(GenerateTest.class);
	HttpConnection con ;

	public GenerateTest() throws Exception {
		con = new HttpConnection();
		PropertyConfigurator.configure(projectHome + "/log4j.properties");  
	}

	@Parameters({"endpoint", "url"})
	@Test(groups = { "get" })
	public void getTest(@Optional String endpoint, @Optional String uri) throws Exception {
		
		String url = "http://" + endpoint + ":8080/sampleapp/" +  uri ;
		int resCode = con.get(url);
		
		Reporter.log("\n\nGet path : " + url);
		Reporter.log("\n Response code received : " + con.statusCode);
		Reporter.log("\nResponse String received : " + con.responseString);
		
		if(uri.contains("?")){
			String[] param = uri.split("=");
			String temp = con.responseString.substring(12);
			System.out.println("param ==== " + param[1] );
			Reporter.log("\nPassed Query Parameter = " +  param[1] + ". Received Prameter = " + temp); 
	
			if(!con.responseString.contains(param[1]))
				Assert.fail("Passed query parameter is " + param[1] + ". But received reponse = " + temp + ". Parameter is mismatching. Hence failing the test.");
		}
		if (resCode != 200 )
			Assert.fail("Expected response code is 200. But got " + resCode + ". Hence failed the test.");

	}

}
