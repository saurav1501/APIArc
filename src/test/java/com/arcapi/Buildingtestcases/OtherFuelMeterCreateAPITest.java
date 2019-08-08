package com.arcapi.Buildingtestcases;

import static com.jayway.restassured.RestAssured.given;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;
import com.relevantcodes.extentreports.LogStatus;

import net.minidev.json.JSONObject;

public class OtherFuelMeterCreateAPITest extends BaseClass {

	@Test// (dependsOnMethods={"com.arcapi.testcases.CreateAssetPOSTAPITest.CreateAssetPOSTAPI"})
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void OtherFuelMeterCreateAPI(String SheetName,String ProjectTypeColumn, int rownumber) throws IOException {

		CommonMethod.ExtentReportConfig();

		//CommonMethod.GeneratingAuthCode();
		
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		
		int row = 2;
		
		/*
		 * District Steam - 40
		 * District Hot Water -41
		 * District Chilled Water - 42,43,44
		 * Natural Gas - 1
		 * Fuel Oil - 2,7,8
		 * Wood - 3
		 * Propane - 4
		 * Liquid Propane - 5
		 * Kerosene - 6 
		 * Coal - 9,10
		 * Coke - 11
		 * Diesel - 13
		 */
		
		String[] Unit = {"gal","kGal", "MGal","cf","ccf","kcf","mcf","l","cu m","GJ","MBtu", "kBtu","therms","MWh","kWh"};
		String[] Type = {"40","41","42","43","44","1","2","7","8","3","4","5","6","9","10","11","13"};
		
		for (int i=0;i < Unit.length;i++) {
			for(int j=0;j<Type.length;j++) {

		JSONObject jsonAsMap = new JSONObject();
		jsonAsMap.put("name", "Test Other Fuel Meter");
		jsonAsMap.put("native_unit", Unit[i]);
		jsonAsMap.put("type", Type[j]);

		CommonMethod.res = given().log().all().header("Ocp-Apim-Subscription-Key", CommonMethod.SubscriptionKey)
				.header("content-type", "application/json").header("Authorization", header).spec(reqSpec)
				.body(jsonAsMap).when()
				.post("/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/meters/").then()
				.extract().response();

		CommonMethod.responsetime = CommonMethod.res.getTimeIn(TimeUnit.MILLISECONDS);

		System.out.println(CommonMethod.responsetime);

		CommonMethod.test = CommonMethod.extent
				.startTest("OtherFuelMeter Create API" + CommonMethod.getLabel(CommonMethod.responsetime),
						"Verifies Meter Creation")
				.assignCategory("CheckMeter");

		CommonMethod.testlog("Pass", "Authorization Token generated" + "<br>" + header);

		System.out.println(CommonMethod.res.asString());
		
		CommonMethod.testlog("Pass", "Verifies response from API" + "<br>" + CommonMethod.res.asString());
		
		CommonMethod.fetchedID = CommonMethod.res.path("id").toString();

		data.setCellData("DataInput", "OtherFuelMeterID", row, CommonMethod.fetchedID);

		CommonMethod.testlog("Info", "API responded in " + CommonMethod.responsetime + " Milliseconds");
		
		CommonMethod.res.then().assertThat().statusCode(201);
		
		row++;
			}
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