package com.arcapi.TrailFlowtestcases.Building;

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

public class WaterConsumptionUpdateAPITest extends BaseClass {

	@Test//(dependsOnMethods = { "com.arcapi.testcases.CreateAssetPOSTAPITest.CreateAssetPOSTAPI" })
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void WaterConsumptionUpdateAPI(String SheetName,String ProjectTypeColumn, int rownumber) throws IOException {

		CommonMethod.ExtentReportConfig();

		//CommonMethod.GeneratingAuthCode();
		
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		
        int RowNum = data.getRowCountbyColNum("DataInput", 3);
        //int PKRowNum = data.getRowCountbyColNum("DataInput", 1);
		
		for (int i =2; i<= RowNum;i++) {

		JSONObject jsonAsMap = new JSONObject();
		jsonAsMap.put("start_date", "2017-09-22");
		jsonAsMap.put("end_date", "2017-09-26");
		jsonAsMap.put("reading", "60");

		CommonMethod.res = given().log().all().header("Ocp-Apim-Subscription-Key", CommonMethod.SubscriptionKey)
				.header("Content-type", "application/json").header("Authorization", header).spec(reqSpec)
				.body(jsonAsMap)
				.when()
				.put("/assets/LEED:" +data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/meters/ID:"
						+ data.getCellData("DataInput", "WaterMeterID", i) + "/consumption/ID:"
						+ data.getCellData("DataInput", "WaterPK", i) + "/")
				.then().extract().response();
		
		CommonMethod.responsetime = CommonMethod.res.getTimeIn(TimeUnit.MILLISECONDS);

		System.out.println(CommonMethod.responsetime);


		CommonMethod.test = CommonMethod.extent
				.startTest("WaterConsumption Update API" + CommonMethod.getLabel(CommonMethod.responsetime),
						"Verifies Consumption updation")
				.assignCategory("CheckConsumption");

		CommonMethod.testlog("Pass", "Authorization Token generated" + "<br>" + header);

		System.out.println(CommonMethod.res.asString());
		
		CommonMethod.testlog("Pass", "Verifies response from API" + "<br>" + CommonMethod.res.asString());

		CommonMethod.testlog("Info", "API responded in " + CommonMethod.responsetime + " Milliseconds");
		
		CommonMethod.res.then().spec(respSpec);

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