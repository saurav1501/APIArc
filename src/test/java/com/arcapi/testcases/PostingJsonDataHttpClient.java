package com.arcapi.testcases;

import java.io.FileReader;
import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;

import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;

public class PostingJsonDataHttpClient extends BaseClass{
	
	
	@Test
	public void whenPostJsonUsingHttpClient_thenCorrect() 
	  throws ClientProtocolException, IOException, ParseException {
		
		/* JSONParser parser = new JSONParser();
	      
	      Object object = parser.parse(new FileReader(CommonMethod.fetchedID));
	      
	      JSONObject jsonObject = (JSONObject)object;*/
	      
	      System.out.println("HIII");
	      try {
	    	  
	    CloseableHttpClient client = HttpClients.createDefault();
	    HttpGet httpPost = new HttpGet("https://api.usgbc.org/projectdetails/?type=latlng&lat=39.7048051&lng=-121.8203702&subscription-key=b8310c02efdd4b54ab2ca02eaff8a878");
	 
	   //JSONObject json = jsonObject;
	   //StringEntity entity = new StringEntity(jsonObject.toString());
	   // httpPost.setEntity(entity);
	    httpPost.setHeader("Accept", "application/json");
	    httpPost.setHeader("Content-type", "application/json");
	 
	    CloseableHttpResponse response = client.execute(httpPost);
	    System.out.println(response.getStatusLine());
	    client.close();
	}

	  


catch(IOException ex) {
    
    ex.printStackTrace();
} catch(Exception e) {  
    e.printStackTrace();  
}
}
  }