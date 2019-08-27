package com.Utill.Controller;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.path.xml.XmlPath;
import com.jayway.restassured.response.Response;

public class TestUtils extends BaseClass{
	
	String response;
	String responseBodyAsString;
	
	public static String getResposeString(Response response){
		log.info("Complete Response");
		response =  (Response) response.getBody();
		String responseBodyAsString =response.asString();
		CommonMethod.testlog("Info",responseBodyAsString);
		log.info(responseBodyAsString);	
		return responseBodyAsString;
	}
	
	public static JsonPath jsonParser(String response){
		log.info("Parsing String Response to JSON");
		JsonPath jsonResponse = new JsonPath(response);
		CommonMethod.testlog("Info",jsonResponse.toString());
		log.info(jsonResponse);
		return jsonResponse;
	}
	

	public static XmlPath xmlParser(String response){
		log.info("Parsing String Response to XML");
		XmlPath xmlResponse = new XmlPath(response);
		
		CommonMethod.testlog("Info",xmlResponse.toString());
		log.info(xmlResponse);
		return xmlResponse;
	}
	
	public static int getStatusCode(Response response){
	
		int statusCode = response.getStatusCode();
		log.info("API Responded With Status Code : " +statusCode);
		CommonMethod.testlog("Info","API Responded With Status Code : " + statusCode);
		return statusCode;
	}
	
	public static String getStatusMessage(Response response){
		String responseMessage = response.getStatusLine();
		log.info("API Responded With Status Message : " +responseMessage );
		CommonMethod.testlog("Info","API Responded With Status Message : " + responseMessage);
		return responseMessage;
	}
	
	
	
}
