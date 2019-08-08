package com.arcapi.Citytestcases;

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

public class CityCommConsumptionCustomWaterCreateAPITest extends BaseClass {

	@Test//(dependsOnMethods = { "com.arcapi.testcases.CreateAssetPOSTAPITest.CreateAssetPOSTAPI" })
    @Parameters({ "SheetName","CustomSheetName", "rownumber","Reading" })
	public void ConsumptionCreatePOSTAPI(String SheetName,String CustomSheetName, int rownumber, String Reading) throws IOException {

		CommonMethod.ExtentReportConfig();

		CommonMethod.GeneratingAuthCode(SheetName,rownumber);
		
		CommonMethod.test = CommonMethod.extent
				.startTest("Consumption create Post API Test",
						"Verifies consumption creation")
				.assignCategory("CheckConsumption");

		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		
        int maxrow = Customdata.getRowCountbyColNum(CustomSheetName, 1);
		
		for(int i=2;i<=maxrow;i++) {
			
		String reading = Customdata.getCellData(CustomSheetName, Reading, i);
		
		double var = Double.parseDouble(reading)*365;
		
		reading = Double.toString(var);

		JSONObject jsonAsMap1 = new JSONObject();
		jsonAsMap1.put("start_date", "2018-01-01");
		jsonAsMap1.put("end_date", "2018-12-31");
		jsonAsMap1.put("reading", reading);
		CommonMethod.res = given().log().all().header("Ocp-Apim-Subscription-Key", CommonMethod.SubscriptionKey)
				.header("content-type", "application/json").header("Authorization", header).spec(reqSpec)
				.body(jsonAsMap1).when()
				.post("/assets/LEED:" + Customdata.getCellData(CustomSheetName, "ProjectID", i) + "/meters/ID:"
						+ Customdata.getCellData(CustomSheetName, "CityMeterID", i) + "/consumption/")
				.then().extract().response();

		CommonMethod.responsetime = CommonMethod.res.getTimeIn(TimeUnit.MILLISECONDS);

		System.out.println(CommonMethod.responsetime);

		CommonMethod.testlog("Pass", "Authorization Token generated" + "<br>" + header);

		System.out.println(CommonMethod.res.asString());
		
		CommonMethod.testlog("Pass", "Verifies response from API" + "<br>" + CommonMethod.res.asString());
		
		CommonMethod.res.then().assertThat().statusCode(201);

		CommonMethod.fetchedID = CommonMethod.res.path("id").toString();

		Customdata.setCellData(CustomSheetName, "CityMeterPK", i, CommonMethod.fetchedID);

		CommonMethod.testlog("Info", "API responded in " + CommonMethod.responsetime + " Milliseconds");

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