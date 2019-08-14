package com.arcapi.CompleteDataValidationNoPayloadTestcases;

import static com.jayway.restassured.RestAssured.given;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.Test;

import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;
import com.jayway.restassured.http.ContentType;

public class AssetValidationInfoPOSTAPITest extends BaseClass {

	@Test
	public void AssetValidationInfoPOSTAPI() {

	
		CommonMethod.test = CommonMethod.extent
				.startTest("Asset Validation Info Post API Test  ", "Validates asset Info")
				.assignCategory("CheckAsset");

			CommonMethod.res = given()
					.header("Ocp-Apim-Subscription-Key", CommonMethod.SubscriptionKey).header("Authorization", header)
					.spec(reqSpec).when().post("/assets/validation/").then().extract().response();

			CommonMethod.responsetime = CommonMethod.res.getTimeIn(TimeUnit.MILLISECONDS);

			System.out.println(CommonMethod.responsetime);

			CommonMethod.testlog("Pass", "Authorization Token generated" + "<br>" + header);

			
			CommonMethod.testlog("Info", "Response from API" + CommonMethod.res.asString());
			CommonMethod.testlog("Info", "Content Type is : " + CommonMethod.res.getContentType());
			CommonMethod.testlog("Info", "Status Code is : " + CommonMethod.res.getStatusCode());
			System.out.println(CommonMethod.res.asString());
			System.out.println("Content Type is : " + CommonMethod.res.getContentType());
			System.out.println("Status Code is : " + CommonMethod.res.getStatusCode());

		

		CommonMethod.res.then().assertThat().statusCode(400);

		CommonMethod.res.then().assertThat().contentType(ContentType.JSON);
	}

	
}