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

public class AdditionalDataConsumptionCreateAPITest extends BaseClass {

	@Test//(dependsOnMethods = { "com.arcapi.testcases.CreateAssetPOSTAPITest.CreateAssetPOSTAPI" })
    @Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void ConsumptionCreatePOSTAPI(String SheetName,String ProjectTypeColumn, int rownumber) throws IOException, InterruptedException {

		CommonMethod.ExtentReportConfig();

		//CommonMethod.GeneratingAuthCode(SheetName,rownumber);
		
		CommonMethod.test = CommonMethod.extent
				.startTest("Consumption create Post API Test  " + CommonMethod.getLabel(CommonMethod.responsetime),
						"Verifies consumption creation")
				.assignCategory("CheckConsumption");
		
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		
		int RowNum = data.getRowCountbyColNum("DataInput", 17);
		
		for (int i =2; i<= RowNum;i++) {

		JSONObject jsonAsMap1 = new JSONObject();
		jsonAsMap1.put("start_date", "2018-01-01");
		jsonAsMap1.put("end_date", "2018-12-31");
		jsonAsMap1.put("reading", CommonMethod.randomNumberMeterReading());
		jsonAsMap1.put("unit", "U");
		CommonMethod.res = given().log().all().header("Ocp-Apim-Subscription-Key", CommonMethod.SubscriptionKey)
				.header("content-type", "application/json").header("Authorization", header).spec(reqSpec)
				.body(jsonAsMap1).when()
				.post("/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/meters/ID:"
						+ data.getCellData("DataInput", "AdditionalMeterID", i) + "/consumption/")
				.then().extract().response();

		CommonMethod.responsetime = CommonMethod.res.getTimeIn(TimeUnit.MILLISECONDS);

		System.out.println(CommonMethod.responsetime);

		CommonMethod.testlog("Pass", "Authorization Token generated" + "<br>" + header);

		System.out.println(CommonMethod.res.asString());
		
		CommonMethod.testlog("Pass", "Verifies response from API" + "<br>" + CommonMethod.res.asString());
		
		CommonMethod.res.then().assertThat().statusCode(201);

		CommonMethod.fetchedID = CommonMethod.res.path("id").toString();

		data.setCellData("DataInput", "AdditionalMeterPK", i, CommonMethod.fetchedID);

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