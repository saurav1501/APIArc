package com.Utill.Controller;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.path.xml.XmlPath;
import com.jayway.restassured.response.Response;

public class TestUtils {
	
	private static Logger log = LogManager.getLogger(TestUtils.class.getName());
	
	public static String getResposeString(Response response){
		log.info("Converting Response to String");
		String strResponse = response.getBody().asString();
		log.debug(strResponse);
		return strResponse;
	}
	
	public static JsonPath jsonParser(String response){
		log.info("Parsing String Response to JSON");
		JsonPath jsonResponse = new JsonPath(response);
		log.debug(jsonResponse);
		return jsonResponse;
	}
	

	public static XmlPath xmlParser(String response){
		log.info("Parsing String Response to XML");
		XmlPath xmlResponse = new XmlPath(response);
		log.debug(xmlResponse);
		return xmlResponse;
	}
	
	public static int getStatusCode(Response response){
		log.info("Getting Response Code");
		int statusCode = response.getStatusCode();
		log.info(statusCode);
		return statusCode;
	}
	
	public static String getStatusMessage(Response response){
		log.info("Getting Response Message");
		String responseMessage = response.getStatusLine();
		log.info(responseMessage);
		return responseMessage;
	}
	
	
	
}
