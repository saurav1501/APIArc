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

public class EnergyMeterBulkConsumptionPOSTEmissionTest extends BaseClass {

	@Test//(dependsOnMethods = { "com.arcapi.testcases.CreateAssetPOSTAPITest.CreateAssetPOSTAPI" })
    @Parameters({ "SheetName","CustomSheetName","ProjectTypeColumn","rownumber" })
	public void MeterBulkConsumptionPOSTAPI(String SheetName,String CustomSheetName,String ProjectTypeColumn, int rownumber) throws IOException {

	
		JSONObject jsonAsMap1 = new JSONObject();
		jsonAsMap1.put("start_date", "2019-01-01");
		jsonAsMap1.put("end_date", "2019-01-31");
		jsonAsMap1.put("reading", "120");
		
		JSONObject jsonAsMap2 = new JSONObject();
		jsonAsMap2.put("start_date", "2019-02-01");
		jsonAsMap2.put("end_date", "2019-02-28");
		jsonAsMap2.put("reading", "150");
		
		JSONObject jsonAsMap3 = new JSONObject();
		jsonAsMap3.put("start_date", "2019-03-01");
		jsonAsMap3.put("end_date", "2019-03-31");
		jsonAsMap3.put("reading", "200.005");
		
		ArrayList<JSONObject> list = new ArrayList<JSONObject>();
        list.add(jsonAsMap1);
        list.add(jsonAsMap2);
        list.add(jsonAsMap3);

		CommonMethod.res = given().log().all().header("Ocp-Apim-Subscription-Key", CommonMethod.SubscriptionKey)
				.header("content-type", "application/json").header("Authorization", header).spec(reqSpec)
				.body(list).when()
				.post("/assets/LEED:" + data.getCellData(CustomSheetName, ProjectTypeColumn, rownumber) + "/meters/ID:"
						+ data.getCellData(CustomSheetName, "MeterID", rownumber) + "/bulkconsumption/")
				.then().extract().response();

		CommonMethod.responsetime = CommonMethod.res.getTimeIn(TimeUnit.MILLISECONDS);

		System.out.println(CommonMethod.responsetime);

		CommonMethod.test = CommonMethod.extent
				.startTest("MeterBulkConsumption POST API Test" + CommonMethod.getLabel(CommonMethod.responsetime),
						"Verifies consumption creation")
				.assignCategory("CheckConsumption");

		CommonMethod.testlog("Pass", "Authorization Token generated" + "<br>" + header);

		System.out.println(CommonMethod.res.asString());
	
		CommonMethod.testlog("Pass", "Verifies response from API" + "<br>" + CommonMethod.res.asString());

		CommonMethod.testlog("Info", "API responded in " + CommonMethod.responsetime + " Milliseconds");
		
		CommonMethod.res.then().assertThat().statusCode(200);

	}

	

}