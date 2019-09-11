package com.arcapi.Buildingtestcases;

import static com.jayway.restassured.RestAssured.given;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;
import com.jayway.restassured.http.ContentType;

public class FeedbackSubmitPostAPITest extends BaseClass {

	@Test(groups="CheckFeedback")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void FeedbackSubmitPostAPI(String SheetName,String ProjectTypeColumn, int rownumber) throws IOException {

		
		Map<String, Object> jsonAsMap = new HashMap<>();
		jsonAsMap.put("OSName", "Linux");
		jsonAsMap.put("browser_name", "Chrome");
		jsonAsMap.put("browser_version", "53");
		jsonAsMap.put("description", "hello world");
		jsonAsMap.put("informative_content", "sad");
		jsonAsMap.put("performance_speed", "happy");
		jsonAsMap.put("well_organized", "satisfied");
		jsonAsMap.put("user_name", data.getCellData(SheetName, "NormalUserName", rownumber));

		CommonMethod.res = given().log().all().parameters(jsonAsMap)
				.header("Ocp-Apim-Subscription-Key", CommonMethod.SubscriptionKey)
				.header("content-type", ContentType.URLENC).header("Authorization", header).spec(reqSpec)
				.when().post("/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/feedbacks/").then()
				.extract().response();

		CommonMethod.responsetime = CommonMethod.res.getTimeIn(TimeUnit.MILLISECONDS);
		System.out.println(CommonMethod.responsetime);

		CommonMethod.test = CommonMethod.extent
				.startTest("Feedback Submit Post API Test  " + CommonMethod.getLabel(CommonMethod.responsetime),
						"Verifies feedback submission")
				.assignCategory("CheckFeedback");

		CommonMethod.testlog("Pass", "Authorization Token generated" + "<br>" + header);

		System.out.println(CommonMethod.res.asString());
		
		CommonMethod.testlog("Pass", "Verifies response from API" + "<br>" + CommonMethod.res.asString());

		CommonMethod.testlog("Info", "API responded in " + CommonMethod.responsetime + " Milliseconds");
		
		CommonMethod.res.then().spec(respSpec);

	}


}