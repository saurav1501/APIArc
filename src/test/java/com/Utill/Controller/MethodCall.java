package com.Utill.Controller;

import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

import com.jayway.restassured.specification.RequestSpecification;
import static com.jayway.restassured.RestAssured.given;
public class MethodCall extends BaseClass{
	
	public static Response GETRequest(String uRI) {
		
		log.info("Inside GETRequest call");
		RequestSpecification requestSpecification = RestAssured.given();
		requestSpecification.contentType(ContentType.JSON);
		Response response = requestSpecification.get(uRI);
		log.debug(requestSpecification.log().all());
		return response;
	}

	public static Response POSTRequest(String uRI, Object strJSON) {
		log.info("Inside POSTRequest call");
		 Response response= given().log().all()
		.headers(headerMap)
		.header("Authorization",header).spec(reqSpec).when().body(strJSON).post(uRI);
		 log.info(CommonMethod.res.toString());
		 return  response;
	}
	
	public static Response POSTRequest(String uRI, String strJSON, String sessionID) {
		log.info("Inside POSTRequest call");
		RequestSpecification requestSpecification = RestAssured.given().body(strJSON);
		requestSpecification.contentType(ContentType.JSON);
		requestSpecification.header("cookie", "JSESSIONID=" + sessionID+"");
		Response response = requestSpecification.post(uRI);
		log.debug(requestSpecification.log().all());
		return response;
	}
	

	public static Response PUTRequest(String uRI, String strJSON) {
		log.info("Inside PUTRequest call");
		RequestSpecification requestSpecification = RestAssured.given().body(strJSON);
		requestSpecification.contentType(ContentType.JSON);
		Response response = requestSpecification.put(uRI);
		log.debug(requestSpecification.log().all());
		return response;
	}

	public static Response DELETERequest(String uRI, String strJSON) {
		log.info("Inside DELETERequest call");
		RequestSpecification requestSpecification = RestAssured.given().body(strJSON);
		requestSpecification.contentType(ContentType.JSON);
		Response response = requestSpecification.delete(uRI);
		log.debug(requestSpecification.log().all());
		return response;
	}

}
