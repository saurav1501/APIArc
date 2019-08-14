package com.arcapi.Buildingtestcases;

import static com.jayway.restassured.RestAssured.given;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

import net.minidev.json.JSONObject;

public class WasteDivertedBulkDataPostTest extends BaseClass {

	@Test
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void WasteDivertedBulkDataPost(String SheetName,String ProjectTypeColumn, int rownumber) throws IOException {

		JSONObject jsonAsMap = new JSONObject();
		jsonAsMap.put("start_date", "2017-01-06");
		jsonAsMap.put("end_date", "2017-01-07");
		jsonAsMap.put("reading", "80");
		jsonAsMap.put("unit", "US tons");
		
		JSONObject jsonAsMap1 = new JSONObject();
		jsonAsMap1.put("start_date", "2017-01-07");
		jsonAsMap1.put("end_date", "2017-01-08");
		jsonAsMap1.put("reading", "150");
		jsonAsMap1.put("unit", "US tons");
		
		ArrayList<JSONObject> list = new ArrayList<JSONObject>();
        list.add(jsonAsMap);
        list.add(jsonAsMap1);

		CommonMethod.res = given().log().all().header("Ocp-Apim-Subscription-Key", CommonMethod.SubscriptionKey)
				.header("content-type", "application/json").header("Authorization", header).spec(reqSpec)
				.body(jsonAsMap).when()
				.post("/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/waste/diverted/").then()
				.extract().response();

		CommonMethod.responsetime = CommonMethod.res.getTimeIn(TimeUnit.MILLISECONDS);

		System.out.println(CommonMethod.responsetime);

		CommonMethod.test = CommonMethod.extent
				.startTest("WasteDivertedBulkData Post API Test  " + CommonMethod.getLabel(CommonMethod.responsetime),
						"Post Waste Diverted data")
				.assignCategory("Waste");

		CommonMethod.testlog("Pass", "Authorization Token generated" + "<br>" + header);

		System.out.println(CommonMethod.res.asString());
		
		CommonMethod.testlog("Pass", "Verifies response from API" + "<br>" + CommonMethod.res.asString());

		CommonMethod.testlog("Info", "API responded in " + CommonMethod.responsetime + " Milliseconds");
		
		CommonMethod.res.then().assertThat().statusCode(200);

	}

	
}