package com.arcapi.Leedv4Testcases;

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

public class HumanExperienceConsumptionUpdateAPITest extends BaseClass {

	@Test//(dependsOnMethods = { "com.arcapi.testcases.CreateAssetPOSTAPITest.CreateAssetPOSTAPI" })
	@Parameters({ "LOSheetName","LOProjectTypeColumn","rownumber" })
	public void HumanExperienceConsumptionUpdateAPI(String SheetName,String ProjectTypeColumn, int rownumber) throws IOException {

		CommonMethod.ExtentReportConfig();

		CommonMethod.GeneratingAuthCodeForLOUser(SheetName,rownumber);
		
		CommonMethod.test = CommonMethod.extent
				.startTest("HumanExperience Consumption Update API" + CommonMethod.getLabel(CommonMethod.responsetime),
						"Verifies Consumption updation")
				.assignCategory("CheckConsumption");
		
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		
	    TestName = Thread.currentThread().getStackTrace()[1].getMethodName();
		
			data.setCellData("Report", "TestCaseName", reportrownum, TestName);

		
        int RowNum = data.getRowCountbyColNum("DataInput", 8);
        //int PKRowNum = data.getRowCountbyColNum("DataInput", 1);
		
		for (int i =2; i<= RowNum;i++) {

		JSONObject jsonAsMap = new JSONObject();
		jsonAsMap.put("start_date", "2018-09-22");
		jsonAsMap.put("end_date", "2018-09-26");
		jsonAsMap.put("reading", "60");

		CommonMethod.res = given().log().all().header("Ocp-Apim-Subscription-Key", CommonMethod.SubscriptionKey)
				.header("Content-type", "application/json").header("Authorization", header).spec(reqSpec)
				.body(jsonAsMap)
				.when()
				.put("/assets/LEED:" +data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/meters/ID:"
						+ data.getCellData("DataInput", "HumanExperienceMeterID", i) + "/consumption/ID:"
						+ data.getCellData("DataInput", "HumanExperiencePK", i) + "/")
				.then().extract().response();
		
		CommonMethod.responsetime = CommonMethod.res.getTimeIn(TimeUnit.MILLISECONDS);

		System.out.println(CommonMethod.responsetime);

		CommonMethod.testlog("Pass", "Authorization Token generated" + "<br>" + header);

		System.out.println(CommonMethod.res.asString());
		
		CommonMethod.testlog("Pass", "Verifies response from API" + "<br>" + CommonMethod.res.asString());

		CommonMethod.testlog("Info", "API responded in " + CommonMethod.responsetime + " Milliseconds");
		
		CommonMethod.res.then().spec(respSpec);

	}
	}
}