package com.arcapi.Citytestcases;

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

public class MeterCreateAPICustom2CityCommTest extends BaseClass {

	@Test// (dependsOnMethods={"com.arcapi.testcases.CreateAssetPOSTAPITest.CreateAssetPOSTAPI"})
	@Parameters({ "SheetName","CustomSheetName","Unit", "rownumber" })
	public void MeterCreatePOSTAPI(String SheetName,String CustomSheetName, String Unit, int rownumber) throws IOException {

		CommonMethod.ExtentReportConfig();

		CommonMethod.GeneratingAuthCode(SheetName,rownumber);
		
		CommonMethod.test = CommonMethod.extent
				.startTest("Meter Create Post API Test  " + CommonMethod.getLabel(CommonMethod.responsetime),
						"Verifies Meter Creation")
				.assignCategory("CheckMeter");
		
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		
        int maxrow = Customdata.getRowCountbyColNum(CustomSheetName, 1);
		
		for(int i=2;i<=maxrow;i++) {
		
		JSONObject jsonAsMap = new JSONObject();
		jsonAsMap.put("name", "Meter");
		jsonAsMap.put("native_unit", Unit);
		jsonAsMap.put("type", "268");

		CommonMethod.res = given().log().all().header("Ocp-Apim-Subscription-Key", CommonMethod.SubscriptionKey)
				.header("content-type", "application/json").header("Authorization", header).spec(reqSpec)
				.body(jsonAsMap).when()
				.post("/assets/LEED:" + Customdata.getCellData(CustomSheetName, "ProjectID", i) + "/meters/").then()
				.extract().response();

		CommonMethod.responsetime = CommonMethod.res.getTimeIn(TimeUnit.MILLISECONDS);

		System.out.println(CommonMethod.responsetime);

		CommonMethod.testlog("Pass", "Authorization Token generated" + "<br>" + header);

		System.out.println(CommonMethod.res.asString());
		
		CommonMethod.testlog("Pass", "Verifies response from API" + "<br>" + CommonMethod.res.asString());

		CommonMethod.fetchedID = CommonMethod.res.path("id").toString();

		Customdata.setCellData(CustomSheetName, "CityMeterID2", i, CommonMethod.fetchedID);

		CommonMethod.testlog("Info", "API responded in " + CommonMethod.responsetime + " Milliseconds");
		
		CommonMethod.res.then().assertThat().statusCode(201);
		
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