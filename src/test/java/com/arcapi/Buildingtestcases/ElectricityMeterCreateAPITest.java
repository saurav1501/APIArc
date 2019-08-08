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

public class ElectricityMeterCreateAPITest extends BaseClass {

	@Test// (dependsOnMethods={"com.arcapi.testcases.CreateAssetPOSTAPITest.CreateAssetPOSTAPI"})
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void ElectricityMeterCreateAPI(String SheetName,String ProjectTypeColumn, int rownumber) throws IOException {

		CommonMethod.ExtentReportConfig();

		//CommonMethod.GeneratingAuthCode();
		
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		
		int row = 2;
		
		String[] Unit = {"kWh","MWh", "MBtu","kBtu","GJ"};
		String[] Type = {"25","46"};
		
		for (int i=0;i < Unit.length;i++) {
			for(int j=0;j<Type.length;j++) {

		JSONObject jsonAsMap = new JSONObject();
		jsonAsMap.put("name", "Test Energy Meter");
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
				.startTest("ElectricityMeter Create API" + CommonMethod.getLabel(CommonMethod.responsetime),
						"Verifies Meter Creation")
				.assignCategory("CheckMeter");

		CommonMethod.testlog("Pass", "Authorization Token generated" + "<br>" + header);

		System.out.println(CommonMethod.res.asString());
		
		CommonMethod.testlog("Pass", "Verifies response from API" + "<br>" + CommonMethod.res.asString());
		
		CommonMethod.fetchedID = CommonMethod.res.path("id").toString();

		data.setCellData("DataInput", "ElectricityMeterID", row, CommonMethod.fetchedID);

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