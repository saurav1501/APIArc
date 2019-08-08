package com.arcapi.TrailFlowtestcases.City;

import static com.jayway.restassured.RestAssured.given;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;
import com.relevantcodes.extentreports.LogStatus;

import net.minidev.json.JSONObject;

public class MeterCreateAPICityCommTest extends BaseClass {

	@Test// (dependsOnMethods={"com.arcapi.testcases.CreateAssetPOSTAPITest.CreateAssetPOSTAPI"})
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void MeterCreatePOSTAPI(String SheetName,String ProjectTypeColumn, int rownumber) throws IOException {

		CommonMethod.ExtentReportConfig();

	    //CommonMethod.GeneratingAuthCodeForLOUser(SheetName,rownumber);
		
		CommonMethod.test = CommonMethod.extent
				.startTest("Meter Create Post API Test  " + CommonMethod.getLabel(CommonMethod.responsetime),
						"Verifies Meter Creation")
				.assignCategory("CheckMeter");
		
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		
        int row = 2;
        String Unit = null;
		
		//String[] Unit = {"�/m3","mg/m3", "ppm","ppb"};
		String[] Type = {"265","266","267","268","269","270","271","272","273","274","275","276","277","278"};
	
			for(String type : Type) {
				
				if(type.equals("273") || type.equals("271") || type.equals("272") || type.equals("276") || type.equals("278")) {
					Unit = "person";
				}
				
				if(type.equals("275") || type.equals("274")) {
					Unit = "dollar";
				}
				
				if(type.equals("270")) {
					Unit = "ppm";
				}
				
				if(type.equals("269")) {
					Unit = "miles";
				}
				
				if(type.equals("266")) {
					Unit = "gal";
				}
				
				if(type.equals("277")) {
					Unit = "days";
				}
				
				if(type.equals("268") || type.equals("267") || type.equals("265")) {
					Unit = "tons";
				}
				
			
			
		JSONObject jsonAsMap = new JSONObject();
		jsonAsMap.put("name", "Meter");
		jsonAsMap.put("native_unit", Unit);
		jsonAsMap.put("type", type);

		CommonMethod.res = given().log().all().header("Ocp-Apim-Subscription-Key", CommonMethod.SubscriptionKey)
				.header("content-type", "application/json").header("Authorization", header).spec(reqSpec)
				.body(jsonAsMap).when()
				.post("/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/meters/").then()
				.extract().response();

		CommonMethod.responsetime = CommonMethod.res.getTimeIn(TimeUnit.MILLISECONDS);

		System.out.println(CommonMethod.responsetime);

		CommonMethod.testlog("Pass", "Authorization Token generated" + "<br>" + header);

		System.out.println(CommonMethod.res.asString());
		
		CommonMethod.testlog("Pass", "Verifies response from API" + "<br>" + CommonMethod.res.asString());

		CommonMethod.fetchedID = CommonMethod.res.path("id").toString();

		data.setCellData("DataInput", "CityMeterID", row, CommonMethod.fetchedID);

		CommonMethod.testlog("Info", "API responded in " + CommonMethod.responsetime + " Milliseconds");
		
		CommonMethod.res.then().assertThat().statusCode(201);
		
		row++;

	}
		}

	
	@AfterMethod
	public void teardown(ITestResult result) {

		if (result.getStatus() == ITestResult.FAILURE) {
			CommonMethod.test.log(LogStatus.FAIL, result.getThrowable());
		} else if (result.getStatus() == ITestResult.SKIP) {
			CommonMethod.test.log(LogStatus.SKIP, "Test skipped " + result.getThrowable());
		} else {
			CommonMethod.test.log(LogStatus.PASS, "Test passed");
		}

		CommonMethod.extent.endTest(CommonMethod.test);
		CommonMethod.extent.flush();

	}
}