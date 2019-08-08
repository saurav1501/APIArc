package com.arcapi.Buildingtestcases;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.Test;

import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;
import com.jayway.restassured.response.Headers;

public class LoginAPITest extends BaseClass {

	@Test
	public void LoginAPI() {

		CommonMethod.res = given().log().all().spec(reqSpec).headers(headerMap)
				.body(loginArc).when().post("/auth/login/").then().extract().response();
		
		Headers ResponseHeader = CommonMethod.res.getHeaders();
		System.out.println(ResponseHeader.getValue("X-Frame-Options"));
		CommonMethod.responsetime = CommonMethod.res.getTimeIn(TimeUnit.MILLISECONDS);
		
		CommonMethod.test = CommonMethod.extent
				.startTest("Login API Test  " + CommonMethod.getLabel(CommonMethod.responsetime),"Verifies Login successfully")
				.assignCategory("CheckLogin");

		System.out.println(CommonMethod.res.asString());

		CommonMethod.fetchedID = CommonMethod.res.path("authorization_token").toString();
		System.out.println(CommonMethod.res.getDetailedCookies());
		System.out.println(CommonMethod.fetchedID);

		header = "Bearer " + CommonMethod.fetchedID;
		System.out.println(header);

		data.setCellData(sheetName, "BearerToken", rowNumTwo, header);

		CommonMethod.testlog("Pass", "Verifies response from API" + "<br>" + CommonMethod.res.asString());
		
		System.out.println(CommonMethod.res.path("token_type"));
		
		CommonMethod.res.then().assertThat().body("token_type", equalTo("Bearer"));
		
		CommonMethod.res.then().assertThat().statusCode(200);

	}

	
}
