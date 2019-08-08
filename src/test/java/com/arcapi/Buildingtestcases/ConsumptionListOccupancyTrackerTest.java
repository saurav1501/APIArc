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

public class ConsumptionListOccupancyTrackerTest extends BaseClass {

	@Test
	@Parameters({ "SheetName","CustomSheetName","ProjectTypeColumn","rownumber" })
	public void ConsumptionListAPI(String SheetName,String CustomSheetName,String ProjectTypeColumn, int rownumber) throws IOException {

		CommonMethod.ExtentReportConfig();

		CommonMethod.GeneratingAuthCode(SheetName,rownumber);
		
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		
		String ColName =null;
		
		String MeterColName[] = {"OphoursMeterID","OpDaysMeterID","OccupancyMeterID","GrossAreaMeterID","EmissionFactorMeterID"};
		
		for(String meter : MeterColName) {
			
			switch (meter) {
			case "OphoursMeterID":
				ColName = "OphoursMeterPK";
				break;
			case "OpDaysMeterID":
				ColName = "OpDaysMeterPK";
				break;
			case "OccupancyMeterID":
				ColName = "OccupancyMeterPK";
				break;
			case "GrossAreaMeterID":
				ColName = "GrossAreaMeterPK";
				break;
			case "EmissionFactorMeterID":
				ColName = "EmissionFactorMeterPK";
				break;
			default:
				System.out.println("Invalid value");
			}
		
		String MeterID = data.getCellData(CustomSheetName, meter, rownumber);

		CommonMethod.res = given().log().all().header("Ocp-Apim-Subscription-Key", CommonMethod.SubscriptionKey)
				.header("Authorization", header).spec(reqSpec).expect().statusCode(200).when()
				.get("/assets/LEED:" + data.getCellData(CustomSheetName, ProjectTypeColumn, rownumber) + "/meters/ID:"
						+ MeterID + "/consumption/")
				.then().extract().response();

		CommonMethod.responsetime = CommonMethod.res.getTimeIn(TimeUnit.MILLISECONDS);

		System.out.println(CommonMethod.responsetime);

		CommonMethod.test = CommonMethod.extent
				.startTest("Consumption List API Test  " + CommonMethod.getLabel(CommonMethod.responsetime),
						"Verifies consumption list")
				.assignCategory("CheckConsumption");

		System.out.println(CommonMethod.res.asString());
		

		
		CommonMethod.testlog("Pass", "Response received from API" + "<br>" + CommonMethod.res.asString());

		CommonMethod.testlog("Info", "API responded in " + CommonMethod.responsetime + " Milliseconds");
		
		CommonMethod.res.then().spec(respSpec);
		
		CommonMethod.fetchedID = CommonMethod.res.path("results.id").toString().replaceAll("\\[", "")
				.replaceAll("\\]", "");

		System.out.println(CommonMethod.fetchedID);

		data.setCellData(CustomSheetName, ColName, rownumber, CommonMethod.fetchedID);
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