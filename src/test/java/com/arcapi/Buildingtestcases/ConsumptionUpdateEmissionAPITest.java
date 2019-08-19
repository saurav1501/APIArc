package com.arcapi.Buildingtestcases;

import static com.jayway.restassured.RestAssured.given;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

import net.minidev.json.JSONObject;

public class ConsumptionUpdateEmissionAPITest extends BaseClass {

	@Test//(dependsOnMethods = { "com.arcapi.testcases.CreateAssetPOSTAPITest.CreateAssetPOSTAPI" })
	@Parameters({ "SheetName","CustomSheetName","ProjectTypeColumn","rownumber" })
	public void ConsumptionUpdatePUTAPI(String SheetName,String CustomSheetName,String ProjectTypeColumn, int rownumber) throws IOException {

			
		String reading = data.getCellData(CustomSheetName, "IncreasedEmissionFactor", rownumber);

		JSONObject jsonAsMap = new JSONObject();
		jsonAsMap.put("start_date", "2019-01-01");
		jsonAsMap.put("reading", reading);

		CommonMethod.res = given().log().all().header("Ocp-Apim-Subscription-Key", CommonMethod.SubscriptionKey)
				.header("Content-type", "application/json").header("Authorization", header).spec(reqSpec)
				.body(jsonAsMap)
				.when()
				.put("/assets/LEED:" +data.getCellData(CustomSheetName, ProjectTypeColumn, rownumber) + "/meters/ID:"
						+ data.getCellData(CustomSheetName, "EmissionMeterID", rownumber) + "/consumption/ID:"
						+ data.getCellData(CustomSheetName, "EmissionMeterPK", rownumber) + "/")
				.then().extract().response();
		
		CommonMethod.responsetime = CommonMethod.res.getTimeIn(TimeUnit.MILLISECONDS);

		System.out.println(CommonMethod.responsetime);


		CommonMethod.test = CommonMethod.extent
				.startTest("Consumption Update PUT API Test  " + CommonMethod.getLabel(CommonMethod.responsetime),
						"Verifies Consumption updation")
				.assignCategory("CheckConsumption");

		CommonMethod.testlog("Pass", "Authorization Token generated" + "<br>" + header);

		System.out.println(CommonMethod.res.asString());
	
		CommonMethod.testlog("Pass", "Verifies response from API" + "<br>" + CommonMethod.res.asString());

		CommonMethod.testlog("Info", "API responded in " + CommonMethod.responsetime + " Milliseconds");
		
		CommonMethod.res.then().spec(respSpec);

	}

	
}