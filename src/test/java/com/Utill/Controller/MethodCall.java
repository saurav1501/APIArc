package com.Utill.Controller;

import static com.jayway.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;
import com.jayway.restassured.response.Headers;
import com.jayway.restassured.response.Response;
public class MethodCall extends BaseClass{
    static String baseURL = prop.getProperty("env");

	public static Response GETRequest(String uRI) {
		CommonMethod.testlog("Info", "GET Request Call URL "+baseURL+uRI);
		log.info("GET Request Call URL "+baseURL+uRI);
		
		CommonMethod.res= given().log().all()
				.headers(headerMap)
				.header("Authorization",header).spec(reqSpec).when().get(uRI).then().extract().response();
		
		CommonMethod.responsetime = CommonMethod.res.getTimeIn(TimeUnit.MILLISECONDS);
		log.info("Get Response Time In milliseconds" +CommonMethod.responsetime);
		CommonMethod.testlog("Info", "API responded in " + CommonMethod.responsetime + " Milliseconds");
		CommonMethod.testlog("Pass", "Verifies response from API" + "<br>" + CommonMethod.res.asString());

		return CommonMethod.res;
	}

	public static Response POSTRequest(String uRI, Object strJSON) {
		CommonMethod.testlog("Info", "POST Request Call URL "+baseURL+uRI);
		log.info("POST Request Call URL "+baseURL+uRI);

		CommonMethod.res= given().log().all().headers(headerMap).header("content-type", "application/json")
				.header("Authorization",header).spec(reqSpec).when().body(strJSON).post(uRI).then().extract().response();

		log.info("Post Response Time In milliseconds" +CommonMethod.responsetime);
		CommonMethod.testlog("Info", "API responded in " + CommonMethod.responsetime + " Milliseconds");
		CommonMethod.testlog("Pass", "Verifies response from API" + "<br>" + CommonMethod.res.asString());

		return CommonMethod.res;
	}

	public static Response POSTRequest(String uRI) {
		CommonMethod.testlog("Info", "POST Request Call URL "+baseURL+uRI);
		log.info("POST Request Call URL "+baseURL+uRI);
		
		CommonMethod.res= given().log().all().headers(headerMap).header("content-type", "application/json")
				.spec(reqSpec).when().body(loginArc).post(uRI).then().extract().response();
		
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
		CommonMethod.testlog("Info", "API responded in " + CommonMethod.responsetime + " Milliseconds");
		CommonMethod.testlog("Pass", "Verifies response from API" + "<br>" + CommonMethod.res.asString());

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
		CommonMethod.testlog("Pass", "Verifies response from API" + "<br>" + CommonMethod.res.asString());

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

		CommonMethod.testlog("Pass", "Verifies response from API" + "<br>" + CommonMethod.res.asString());

		return   CommonMethod.res;

	}

	public static Response DELETERequest(String uRI, String strJSON) {
		
		CommonMethod.testlog("Info", "DELETE Request Call URL "+baseURL+uRI);
		log.info("DELETE Request Call URL "+baseURL+uRI);
		
		CommonMethod.res= given().log().all()
				.headers(headerMap)
				.header("Authorization",header).header("content-type", "application/json").spec(reqSpec).when().body(strJSON).delete(uRI).then().extract().response();

		CommonMethod.responsetime = CommonMethod.res.getTimeIn(TimeUnit.MILLISECONDS);
		log.info("Delete Response Time In milliseconds" +CommonMethod.responsetime);
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

		return   CommonMethod.res;
	}

}
