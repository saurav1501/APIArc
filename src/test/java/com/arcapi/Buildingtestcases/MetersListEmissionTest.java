package com.arcapi.Buildingtestcases;

import static com.jayway.restassured.RestAssured.given;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class MetersListEmissionTest extends BaseClass {

	@Test
	@Parameters({ "SheetName","CustomSheetName","ProjectTypeColumn","rownumber" })
	public void MetersAPI(String SheetName,String CustomSheetName,String ProjectTypeColumn, int rownumber) throws IOException, InterruptedException {

	
		CommonMethod.res = given().log().all().header("Ocp-Apim-Subscription-Key", CommonMethod.SubscriptionKey)
				.header("Authorization", header).spec(reqSpec).when()
				.get("/assets/LEED:" + data.getCellData(CustomSheetName, ProjectTypeColumn, rownumber) + "/meters/?kind=emission_factor").then()
				.extract().response();
		
		CommonMethod.responsetime = CommonMethod.res.getTimeIn(TimeUnit.MILLISECONDS);

		System.out.println(CommonMethod.responsetime);

		CommonMethod.test = CommonMethod.extent
				.startTest("Meter API Test  " + CommonMethod.getLabel(CommonMethod.responsetime),
						"Verifies Meter details")
				.assignCategory("CheckMeter");

		System.out.println(CommonMethod.res.asString());
		
		CommonMethod.testlog("Pass", "Response received from API" + "<br>" + CommonMethod.res.asString());

		CommonMethod.testlog("Info", "API responded in " + CommonMethod.responsetime + " Milliseconds");
		
		CommonMethod.res.then().spec(respSpec);
		
		CommonMethod.fetchedID = CommonMethod.res.path("results.id").toString().replaceAll("\\[", "").replaceAll("\\]","");

		System.out.println(CommonMethod.fetchedID);

		data.setCellData(CustomSheetName, "EmissionMeterID", rownumber, CommonMethod.fetchedID);

	}

	
}