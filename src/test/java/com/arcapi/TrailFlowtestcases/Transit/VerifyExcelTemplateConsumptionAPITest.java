package com.arcapi.TrailFlowtestcases.Transit;

import static com.jayway.restassured.RestAssured.given;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;
import com.relevantcodes.extentreports.LogStatus;

public class VerifyExcelTemplateConsumptionAPITest extends BaseClass {

	@Test
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void VerifyExcelTemplateConsumptionAPI(String SheetName,String ProjectTypeColumn, int rownumber) throws IOException {

		String ColName = null;
		CommonMethod.ExtentReportConfig();

		//CommonMethod.GeneratingAuthCodeForLOUser(SheetName, rownumber);
		
		CommonMethod.test = CommonMethod.extent
				.startTest("VerifyExcelTemplateConsumptionAPI Test",
						"Verifies consumption list")
				.assignCategory("CheckConsumption");
		
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		
	    TestName = Thread.currentThread().getStackTrace()[1].getMethodName();
		
			data.setCellData("Report", "TestCaseName", reportrownum, TestName);

		
        int RowNum = data.getRowCountbyColNum("DataInput", 21);
		
		for (int i =2; i<= RowNum;i++) {

		CommonMethod.res = given().log().all().header("Ocp-Apim-Subscription-Key", CommonMethod.SubscriptionKey)
				.header("Authorization", header).spec(reqSpec).when()
				.get("/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/meters/ID:"
						+ data.getCellData("DataInput", "ExcelTemplateMeterID", i) + "/consumption/")
				.then().extract().response();

		CommonMethod.responsetime = CommonMethod.res.getTimeIn(TimeUnit.MILLISECONDS);

		System.out.println(CommonMethod.res.asString());
		
		String MeterName = data.getCellData("DataInput", "ExcelTemplateMeterName", i);
		
		if(MeterName.equalsIgnoreCase("UploadWaterMeterTest")) {
			
			ColName = "ExcelWaterReading";
		}
		
        else if(MeterName.equalsIgnoreCase("EnergyFileUploadTestMeter")) {
			
			ColName = "ExcelEnergyReading";
		}
		
		else if(MeterName.equalsIgnoreCase("Default TVOC Meter")) {
			
			ColName = "ExcelTVOCReading";
		}
		
        else  {
			
			ColName = "ExcelCO2Reading";
		}
		
		System.out.println(ColName);
		for(int j=0;j<12;j++) {
		CommonMethod.fetchedID = CommonMethod.res.path("results.reading["+j+"]").toString();
		String reading = CommonMethod.fetchedID.replaceAll(".0$", "");
		String Act_Reading = data.getCellData("DataInput", ColName, j+2);
		Assert.assertEquals(Act_Reading, reading);
		}
		CommonMethod.testlog("Pass", "Response received from API" + "<br>" + CommonMethod.res.asString());

		CommonMethod.testlog("Info", "API responded in " + CommonMethod.responsetime + " Milliseconds");
		
		CommonMethod.res.then().spec(respSpec);
	}
	}
	
}