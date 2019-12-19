package com.Utill.Controller;

import static com.jayway.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Headers;
import com.jayway.restassured.response.Response;
public class MethodCall extends BaseClass{
    static String baseURL = prop.getProperty("env");
    static String baseURLLEED = prop.getProperty("envleed");

	public static Response GETRequest(String uRI) {
		CommonMethod.testlog("Info", "GET Request Call URL "+baseURL+uRI);
		log.info("GET Request Call URL "+baseURL+uRI);
		
		CommonMethod.res= given().log().all()
				.headers(headerMap)
				.header("Authorization",header).spec(reqSpec).when().get(uRI).then().extract().response();
		
		CommonMethod.responsetime = CommonMethod.res.getTimeIn(TimeUnit.MILLISECONDS);
		log.info("Get Response Time In milliseconds" +CommonMethod.responsetime);
		
		CommonMethod.testlog("Info", "API responded in " + CommonMethod.responsetime + " Milliseconds");
		CommonMethod.testlog("Pass", "Response from API" + "<br>" + CommonMethod.res.asString());

		return CommonMethod.res;
	}
	
	public static Response GETRequestWithoutHeader(String uRI) {
		CommonMethod.testlog("Info", "GET Request Call URL "+baseURL+uRI);
		
		log.info("GET Request Call URL "+baseURL+uRI);
		
		CommonMethod.res= given().log().all().spec(reqSpec).when().get(uRI).then().extract().response();
		
		CommonMethod.responsetime = CommonMethod.res.getTimeIn(TimeUnit.MILLISECONDS);
		log.info("Get Response Time In milliseconds" +CommonMethod.responsetime);
		
		CommonMethod.testlog("Info", "API responded in " + CommonMethod.responsetime + " Milliseconds");
		CommonMethod.testlog("Pass", "Response from API" + "<br>" + CommonMethod.res.asString());

		return CommonMethod.res;
	}
	
	public static Response GETRequest(String uRI,Object strJSON ) {
		CommonMethod.testlog("Info", "GET Request Call URL "+baseURL+uRI);
		log.info("GET Request Call URL "+baseURL+uRI);
		
		CommonMethod.res= given().log().all()
				.headers(headerMap)
				.header("Authorization",header).spec(reqSpec).when().body(strJSON).get(uRI).then().extract().response();
		
		CommonMethod.responsetime = CommonMethod.res.getTimeIn(TimeUnit.MILLISECONDS);
		log.info("Get Response Time In milliseconds" +CommonMethod.responsetime);
		
		Gson gson = new Gson();
		String jsonString = gson.toJson(strJSON);
		log.info("Payload is  "+jsonString.toString());
		CommonMethod.testlog("Pass", "Payload is : "+"<br>"+jsonString.toString());
	
		
		CommonMethod.testlog("Info", "API responded in " + CommonMethod.responsetime + " Milliseconds");
		CommonMethod.testlog("Pass", "Response from API" + "<br>" + CommonMethod.res.asString());

		return CommonMethod.res;
	}

	public static Response POSTRequest(String uRI, Object strJSON) throws JsonProcessingException {
		CommonMethod.testlog("Info", "POST Request Call URL "+baseURL+uRI);
		
		log.info("POST Request Call URL "+baseURL+uRI);
		
		CommonMethod.res= given().log().all().headers(headerMap).header("content-type", "application/json")
				.header("Authorization",header).spec(reqSpec).when().body(strJSON).post(uRI).then().extract().response();

		log.info("Post Response Time In milliseconds" +CommonMethod.responsetime);
		
		CommonMethod.testlog("Info", "API responded in " + CommonMethod.responsetime + " Milliseconds");
		
		Gson gson = new Gson();
		String jsonString = gson.toJson(strJSON);
		
		log.info("Payload is  "+jsonString.toString());
		CommonMethod.testlog("Pass", "Payload is : "+"<br>"+jsonString.toString());		
		CommonMethod.testlog("Pass","Response from API" + "<br>" + CommonMethod.res.asString());
		
		return CommonMethod.res;
	}

	public static Response POSTRequest(String uRI) {
		CommonMethod.testlog("Info", "POST Request Call URL "+baseURL+uRI);
		log.info("POST Request Call URL "+baseURL+uRI);
		
		CommonMethod.res= given().log().all().headers(headerMap).header("content-type", "application/json")
				.header("Authorization",header).spec(reqSpec).when().post(uRI).then().extract().response();

		log.info("Post Response Time In milliseconds" +CommonMethod.responsetime);
		
		CommonMethod.testlog("Info", "API responded in " + CommonMethod.responsetime + " Milliseconds");
		
		CommonMethod.testlog("Info", "Content Type is : " + CommonMethod.res.getContentType());
		
		CommonMethod.testlog("Pass","Response from API" + "<br>" + CommonMethod.res.asString());
		
		return CommonMethod.res;
	}
	public static Response POSTRequestLogin(String uRI) {
		CommonMethod.testlog("Info", "POST Request Call URL "+baseURL+uRI);
		log.info("POST Request Call URL "+baseURL+uRI);
		
		CommonMethod.res= given().log().all().headers(headerMap).header("content-type", "application/json")
				.spec(reqSpec).when().body(loginArc).post(uRI).then().extract().response();
		
		Assertion.verifyStatusCode(CommonMethod.res, 200);
		
		log.info("Payload is  "+loginArc.getUsername().toString()+loginArc.getPassword().toString());
		CommonMethod.testlog("Pass", "Payload is : "+"username "+loginArc.getUsername().toString()+" password "+loginArc.getPassword().toString());
	
		Headers ResponseHeader = CommonMethod.res.getHeaders();
		log.info(ResponseHeader.getValue("X-Frame-Options"));
		
		CommonMethod.fetchedID = CommonMethod.res.path("authorization_token").toString();
		log.info(CommonMethod.res.getDetailedCookies());
		log.info(CommonMethod.fetchedID);
		Token = CommonMethod.fetchedID;
		
		header = "Bearer " + CommonMethod.fetchedID;
		data.setCellData(sheetName, "BearerToken", rowNumTwo, header);
		
		log.info(CommonMethod.res.path("token_type"));	
		log.info("Post Response Time In milliseconds" +CommonMethod.responsetime);
		
			
		log.info("Header is "+header.toString());
		CommonMethod.testlog("Pass", "Header is : "+header.toString());
		
		log.info("Ocp-Apim-Subscription-Key"+SubscriptionKey);
		CommonMethod.testlog("Pass", "Ocp-Apim-Subscription-Key :"+SubscriptionKey.toString());
		
		CommonMethod.testlog("Info", "API responded in " + CommonMethod.responsetime + " Milliseconds");
		CommonMethod.testlog("Pass", "Response from API" + "<br>" + CommonMethod.res.asString());

		return CommonMethod.res;
	}

	public static Response POSTRequestLogin2(String uRI,String SheetName,String UserName,String Password) {
		CommonMethod.testlog("Info", "POST Request Call URL "+baseURL+uRI);
		log.info("POST Request Call URL "+baseURL+uRI);
		
		CommonMethod.res = given().log().all().header("Ocp-Apim-Subscription-Key", CommonMethod.SubscriptionKey).spec(reqSpec)
				.parameters("username", data.getCellData(SheetName, UserName, 2), "password",
						data.getCellData(SheetName, Password, 2))
				.when().post(url).then().extract().response();
				
		Headers ResponseHeader = CommonMethod.res.getHeaders();
		log.info(ResponseHeader.getValue("X-Frame-Options"));
		
		CommonMethod.fetchedID = CommonMethod.res.path("authorization_token").toString();
		log.info(CommonMethod.res.getDetailedCookies());
		log.info(CommonMethod.fetchedID);
		Token = CommonMethod.fetchedID;
		
		header = "Bearer " + CommonMethod.fetchedID;
		data.setCellData(SheetName, "BearerToken", rowNumTwo, header);
		
		log.info(CommonMethod.res.path("token_type"));	
		log.info("Post Response Time In milliseconds" +CommonMethod.responsetime);
		
		log.info("Payload is  "+loginArc.getUsername().toString()+loginArc.getPassword().toString());
		CommonMethod.testlog("Pass", "Payload is : "+"username "+data.getCellData(SheetName, UserName, 2)+" password "+data.getCellData(SheetName, Password, 2));
		
		log.info("Header is "+header.toString());
		CommonMethod.testlog("Pass", "Header is : "+header.toString());
		
		log.info("Ocp-Apim-Subscription-Key"+SubscriptionKey);
		CommonMethod.testlog("Pass", "Ocp-Apim-Subscription-Key :"+SubscriptionKey.toString());
		
		CommonMethod.testlog("Info", "API responded in " + CommonMethod.responsetime + " Milliseconds");
		CommonMethod.testlog("Pass", "Response from API" + "<br>" + CommonMethod.res.asString());

		return CommonMethod.res;
	}
	@SuppressWarnings("unchecked")
	public static Response POSTRequest(String uRI, Object strJSON ,String key, Object file) {
		CommonMethod.testlog("Info", "POST Request Call URL "+baseURL+uRI);
		log.info("POST Request Call URL "+baseURL+uRI);

		HashMap<String,String> map = new HashMap<String,String>();
		map= (HashMap<String, String>) strJSON;
		CommonMethod.res=  given().log().all().headers(headerMap).header("Authorization",header).multiPart(key,file).parameters(map).spec(reqSpec).when().post(uRI).then().extract().response();
		CommonMethod.responsetime = CommonMethod.res.getTimeIn(TimeUnit.MILLISECONDS);
		
		log.info("Post Response Time In milliseconds" +CommonMethod.responsetime);
		CommonMethod.testlog("Info", "API responded in " + CommonMethod.responsetime + " Milliseconds");
		CommonMethod.testlog("Pass", "Response from API" + "<br>" + CommonMethod.res.asString());

		return CommonMethod.res;
	}
	
	public static Response POSTRequestExcelUpload(String uRI,String key, Object file) {
		CommonMethod.testlog("Info", "POST Request Call URL "+baseURL+uRI);
		log.info("POST Request Call URL "+baseURL+uRI);
		
		CommonMethod.res=  given().log().all().headers(headerMap).header("Authorization",header).parameter("upload_category", "excel").multiPart(key,file).spec(reqSpec).when().post(uRI).then().extract().response();
		CommonMethod.responsetime = CommonMethod.res.getTimeIn(TimeUnit.MILLISECONDS);
		
		log.info("Post Response Time In milliseconds" +CommonMethod.responsetime);
		CommonMethod.testlog("Info", "API responded in " + CommonMethod.responsetime + " Milliseconds");
		CommonMethod.testlog("Pass", "Response from API" + "<br>" + CommonMethod.res.asString());

		return CommonMethod.res;
	}

	public static Response POSTRequestHTMLFileUpload(String uRI) {
		
		
		CommonMethod.testlog("Info", "POST Request Call URL "+baseURL+uRI);
		log.info("POST Request Call URL "+baseURLLEED+uRI);
	
		CommonMethod.res= given().log().all().multiPart("file", CommonMethod.certification)
				.headers(headerMap)
				.header("Authorization",header).spec(reqSpec).post(url).then().extract().response();

        log.info("Post Response Time In milliseconds" +CommonMethod.responsetime);
		
		CommonMethod.testlog("Info", "API responded in " + CommonMethod.responsetime + " Milliseconds");
	
		return CommonMethod.res;
		
	}
	public static Response POSTRequestHTMLFileUpload(String uRI,Object strJSON) {
			
		CommonMethod.testlog("Info", "POST Request Call URL "+baseURL+uRI);
		log.info("POST Request Call URL "+baseURLLEED+uRI);
	
		CommonMethod.res= given().log().all().multiPart("file", CommonMethod.certification)
				.headers(headerMap)
				.header("Authorization",header).spec(reqSpec).body(strJSON).post(url).then().extract().response();

		Gson gson = new Gson();
		String jsonString = gson.toJson(strJSON);
		log.info("Payload is  "+jsonString.toString());
		
        log.info("Post Response Time In milliseconds" +CommonMethod.responsetime);
        log.info("Payload is  "+jsonString.toString());
        
		CommonMethod.testlog("Info", "API responded in " + CommonMethod.responsetime + " Milliseconds");
	
		return CommonMethod.res;
		
	}
	public static Response PUTRequest(String uRI, Object strJSON) {
		
		CommonMethod.testlog("Info", "PUT Request Call URL "+baseURL+uRI);
		log.info("PUT Request Call URL "+baseURL+uRI);
		
		CommonMethod.res= given().log().all()
				.headers(headerMap)
				.header("Authorization",header).header("content-type", "application/json").spec(reqSpec).when().body(strJSON).put(uRI).then().extract().response();
	
		CommonMethod.responsetime = CommonMethod.res.getTimeIn(TimeUnit.MILLISECONDS);
		log.info("Put Response Time In milliseconds" +CommonMethod.responsetime);
		CommonMethod.testlog("Info", "API responded in " + CommonMethod.responsetime + " Milliseconds");
		Gson gson = new Gson();
		String jsonString = gson.toJson(strJSON);
		log.info("Payload is  "+jsonString.toString());
		CommonMethod.testlog("Pass", "Payload is : "+"<br>"+jsonString.toString());
	
		CommonMethod.testlog("Pass", "Verifies response from API" + "<br>" + CommonMethod.res.asString());

		return   CommonMethod.res;

	}

	public static Response PUTRequest(String uRI) {
		
		CommonMethod.testlog("Info", "PUT Request Call URL "+baseURL+uRI);
		log.info("PUT Request Call URL "+baseURL+uRI);
		
		CommonMethod.res= given().log().all()
				.headers(headerMap)
				.header("Authorization",header).header("content-type", "application/json").spec(reqSpec).when().put(uRI).then().extract().response();

		CommonMethod.responsetime = CommonMethod.res.getTimeIn(TimeUnit.MILLISECONDS);
		log.info("Put Response Time In milliseconds" +CommonMethod.responsetime);
		CommonMethod.testlog("Info", "API responded in " + CommonMethod.responsetime + " Milliseconds");

		CommonMethod.testlog("Pass", "Verifies response from API" + "<br>" + CommonMethod.res.asString());

		return   CommonMethod.res;

	}
	public static Response DELETERequest(String uRI, Object strJSON) {
		
		CommonMethod.testlog("Info", "DELETE Request Call URL "+baseURL+uRI);
		log.info("DELETE Request Call URL "+baseURL+uRI);
		
		log.info("Passed Payload is -> "+strJSON.toString());
		CommonMethod.testlog("Pass", "Passed Payload is ->" + "<br>" +strJSON.toString());

		CommonMethod.res= given().log().all()
				.headers(headerMap)
				.header("Authorization",header).header("content-type", "application/json").spec(reqSpec).when().body(strJSON).delete(uRI).then().extract().response();

		CommonMethod.responsetime = CommonMethod.res.getTimeIn(TimeUnit.MILLISECONDS);
		log.info("Delete Response Time In milliseconds" +CommonMethod.responsetime);
		
		Gson gson = new Gson();
		String jsonString = gson.toJson(strJSON);
		log.info("Payload is  "+jsonString.toString());
		CommonMethod.testlog("Pass", "Payload is : "+"<br>"+jsonString.toString());
	
		CommonMethod.testlog("Info", "API responded in " + CommonMethod.responsetime + " Milliseconds");	
		CommonMethod.testlog("Pass", "Verifies response from API" + "<br>" + CommonMethod.res.asString());
		return   CommonMethod.res;
	}
	public static Response DELETERequest(String uRI) {
		
		CommonMethod.testlog("Info", "DELETE Request Call URL "+baseURL+uRI);
		log.info("DELETE Request Call URL "+baseURL+uRI);
		
		CommonMethod.res= given().log().all()
				.headers(headerMap)
				.header("Authorization",header).header("content-type", "application/json").spec(reqSpec).when().delete(uRI).then().extract().response();

		CommonMethod.responsetime = CommonMethod.res.getTimeIn(TimeUnit.MILLISECONDS);
		log.info("Delete Response Time In milliseconds" +CommonMethod.responsetime);
		CommonMethod.testlog("Info", "API responded in " + CommonMethod.responsetime + " Milliseconds");	
		CommonMethod.testlog("Pass", "Verifies response from API" + "<br>" + CommonMethod.res.asString());
		CommonMethod.testlog("Info", "Content Type is : " + CommonMethod.res.getContentType());
		
		return   CommonMethod.res;
	}
	
	public static Response POSTRequestLEED(String uRI, Object strJSON) throws JsonProcessingException {
		CommonMethod.testlog("Info", "POST Request Call URL "+baseURLLEED+uRI);
		
		log.info("POST Request Call URL "+baseURLLEED+uRI);
		
		CommonMethod.res=  given().log().all()
				.header("Content-type", "application/json")
				.header("Authorization", header)
				.header("X-Caller-Id", "20297672fa1247ccf00ce8e0a14013ac")
				.spec(reqSpecLEED)
			    .when().body(strJSON)
				.post(uRI)
				.then()
				.extract()
				.response();
      
		log.info("Post Response Time In milliseconds" +CommonMethod.responsetime);
		
		CommonMethod.testlog("Info", "API responded in " + CommonMethod.responsetime + " Milliseconds");
		
		Gson gson = new Gson();
		String jsonString = gson.toJson(strJSON);
		log.info("Payload is  "+jsonString.toString());
		CommonMethod.testlog("Pass", "Payload is : "+"<br>"+jsonString.toString());
		
		CommonMethod.testlog("Pass","Response from API" + "<br>" + CommonMethod.res.asString());
		CommonMethod.res.then().assertThat().contentType(ContentType.JSON);
		return CommonMethod.res;
	}

	public static Response POSTUploadFile(String uRI) {
		CommonMethod.testlog("Info", "POST Request Call URL "+baseURL+uRI);
		log.info("POST Request Call URL "+baseURLLEED+uRI);
	
		CommonMethod.res= given().log().all().multiPart("file", CommonMethod.gresb)
				.headers(headerMap)
				.header("Authorization",header).spec(reqSpec).post(url).then().extract().response();

       log.info("Post Response Time In milliseconds" +CommonMethod.responsetime);
		
		CommonMethod.testlog("Info", "API responded in " + CommonMethod.responsetime + " Milliseconds");
	
		return CommonMethod.res;
	}
}


