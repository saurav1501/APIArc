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

public class MetersListOccupancyTrackerTest extends BaseClass {

	@Test
	@Parameters({ "SheetName", "CustomSheetName", "ProjectTypeColumn", "rownumber" })
	public void MetersAPI(String SheetName, String CustomSheetName, String ProjectTypeColumn, int rownumber)
			throws IOException {

		CommonMethod.ExtentReportConfig();

		// CommonMethod.GeneratingAuthCode();

		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		
		CommonMethod.test = CommonMethod.extent
				.startTest("Meter API Test",
						"Verifies Meter details")
				.assignCategory("CheckMeter");


		String ColName = null;

		String Meters[] = { "operating_hours", "operational_days", "occupancy", "gross_area", "emission_factor" };

		for (String meter : Meters) {

			switch (meter) {
			case "operating_hours":
				ColName = "OphoursMeterID";
				break;
			case "operational_days":
				ColName = "OpDaysMeterID";
				break;
			case "occupancy":
				ColName = "OccupancyMeterID";
				break;
			case "gross_area":
				ColName = "GrossAreaMeterID";
				break;
			case "emission_factor":
				ColName = "EmissionFactorMeterID";
				break;
			default:
				System.out.println("Invalid value");
			}

			CommonMethod.res = given().log().all().header("Ocp-Apim-Subscription-Key", CommonMethod.SubscriptionKey)
					.header("Authorization", header).spec(reqSpec).when()
					.get("/assets/LEED:" + data.getCellData(CustomSheetName, ProjectTypeColumn, rownumber)
							+ "/meters/?kind=" + meter)
					.then().extract().response();

			CommonMethod.responsetime = CommonMethod.res.getTimeIn(TimeUnit.MILLISECONDS);

			System.out.println(CommonMethod.responsetime);

			
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