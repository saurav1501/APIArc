package com.arcapi.Transittestcases;

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

public class ExcelTemplateMetersHEAPITest extends BaseClass {

	@Test
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void ExcelTemplateMetersHEAPI(String SheetName,String ProjectTypeColumn, int rownumber) throws IOException {

		CommonMethod.ExtentReportConfig();

		//CommonMethod.GeneratingAuthCodeForLOUser(SheetName,rownumber);
		
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		
	    TestName = Thread.currentThread().getStackTrace()[1].getMethodName();
		
			data.setCellData("Report", "TestCaseName", reportrownum, TestName);

		
		int row = 2;

		CommonMethod.res = given().log().all().header("Ocp-Apim-Subscription-Key", CommonMethod.SubscriptionKey)
				.header("Authorization", header).spec(reqSpec).when()
				.get("/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/meters/?page_size=20").then()
				.extract().response();
		
		CommonMethod.responsetime = CommonMethod.res.getTimeIn(TimeUnit.MILLISECONDS);

		System.out.println(CommonMethod.responsetime);

		CommonMethod.test = CommonMethod.extent
				.startTest("ExcelTemplateMetersHEAPITest  " + CommonMethod.getLabel(CommonMethod.responsetime),
						"Verifies Meter details")
				.assignCategory("CheckMeter");

		System.out.println(CommonMethod.res.asString());
		
		for (int i=2;i<10;i++) {
		
		CommonMethod.fetchedID = CommonMethod.res.path("results.id["+i+"]").toString();
		
		data.setCellData("Graphs", "TransitHEMeterID", row, CommonMethod.fetchedID);
		
        CommonMethod.fetchedID = CommonMethod.res.path("results.name["+i+"]").toString();
		
		data.setCellData("Graphs", "TransitHEMeterName", row, CommonMethod.fetchedID);
		
		CommonMethod.testlog("Pass", "Response received from API" + "<br>" + CommonMethod.res.asString());

		CommonMethod.testlog("Info", "API responded in " + CommonMethod.responsetime + " Milliseconds");
		
		CommonMethod.res.then().spec(respSpec);
		
		
		row++;
		}
	}

}