package com.arcapi.Portfoliostestcases;

import static com.jayway.restassured.RestAssured.given;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.Test;

import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class PortfoliosGetAPITest extends BaseClass {

	@Test
	public void PortfoliosGetAPI() throws IOException {

		CommonMethod.res = given().log().all().header("Ocp-Apim-Subscription-Key", CommonMethod.SubscriptionKey)
				.header("Authorization", header).spec(reqSpec).when().get("/portfolios/").then().extract()
				.response();

		CommonMethod.responsetime = CommonMethod.res.getTimeIn(TimeUnit.MILLISECONDS);
		
		System.out.println(CommonMethod.responsetime);

		CommonMethod.test = CommonMethod.extent.startTest(
				"Portfolios Get API Test  " + CommonMethod.getLabel(CommonMethod.responsetime),
				"Verifies List of Portfolios");

		System.out.println(CommonMethod.res.asString());
		
		CommonMethod.testlog("Pass", "Response received from API" + "<br>" + CommonMethod.res.asString());

		CommonMethod.testlog("Info", "API responded in " + CommonMethod.responsetime + " Milliseconds");
		
		CommonMethod.res.then().spec(respSpec);
	}

}