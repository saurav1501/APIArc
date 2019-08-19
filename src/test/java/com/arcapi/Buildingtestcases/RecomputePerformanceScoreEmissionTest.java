package com.arcapi.Buildingtestcases;

import static com.jayway.restassured.RestAssured.given;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;
import com.jayway.restassured.path.json.JsonPath;

public class RecomputePerformanceScoreEmissionTest extends BaseClass {

	@Test
	@Parameters({ "SheetName", "CustomSheetName", "ProjectTypeColumn", "rownumber" })
	public void RecomputePerformanceScoreGet(String SheetName, String CustomSheetName, String ProjectTypeColumn,
			int rownumber) throws IOException {

		
		CommonMethod.res = given().log().all().header("Ocp-Apim-Subscription-Key", CommonMethod.SubscriptionKey)
				.header("Authorization", header).spec(reqSpec).when().get("/assets/LEED:"
						+ data.getCellData(CustomSheetName, ProjectTypeColumn, rownumber) + "/scores/recompute/")
				.then().extract().response();

		CommonMethod.responsetime = CommonMethod.res.getTimeIn(TimeUnit.MILLISECONDS);

		System.out.println(CommonMethod.responsetime);

		CommonMethod.test = CommonMethod.extent.startTest(
				"RecomputePerformanceScore Get API Test  " + CommonMethod.getLabel(CommonMethod.responsetime),
				"Recompute Score").assignCategory("Recompute");

		System.out.println(CommonMethod.res.asString());

		CommonMethod.testlog("Pass", "Response received from API" + "<br>" + CommonMethod.res.asString());

		CommonMethod.testlog("Info", "API responded in " + CommonMethod.responsetime + " Milliseconds");

		CommonMethod.res.then().spec(respSpec);
		
		String json = CommonMethod.res.body().asString();
	    JsonPath jp = new JsonPath(json);

	    System.out.println(jp.getString("scores.energy"));
		//CommonMethod.fetchedID = CommonMethod.res.path("scores.energy").toString();

		Assert.assertNull(jp.getString("scores.energy"));

		/*CommonMethod.fetchedID = CommonMethod.res.path("scores.water").toString();

		Assert.assertNull(CommonMethod.fetchedID);

		CommonMethod.fetchedID = CommonMethod.res.path("scores.waste").toString();

		Assert.assertNull(CommonMethod.fetchedID);

		CommonMethod.fetchedID = CommonMethod.res.path("scores.transport").toString();

		Assert.assertNull(CommonMethod.fetchedID);

		CommonMethod.fetchedID = CommonMethod.res.path("scores.human_experience").toString();

		Assert.assertNull(CommonMethod.fetchedID);*/
	}

	
}