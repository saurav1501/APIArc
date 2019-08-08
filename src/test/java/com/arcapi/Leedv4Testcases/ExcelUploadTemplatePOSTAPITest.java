package com.arcapi.Leedv4Testcases;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.*;

import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;
import com.relevantcodes.extentreports.LogStatus;

import net.minidev.json.JSONObject;

public class ExcelUploadTemplatePOSTAPITest extends BaseClass {

	@Test
	@Parameters({ "LOSheetName","LOProjectTypeColumn","rownumber" })
	public void ExcelUploadTemplatePOSTAPI(String SheetName,String ProjectTypeColumn, int rownumber) throws IOException {

		CommonMethod.ExtentReportConfig();

	    CommonMethod.GeneratingAuthCodeForLOUser(SheetName, rownumber);
		
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		
	    TestName = Thread.currentThread().getStackTrace()[1].getMethodName();
		
			data.setCellData("Report", "TestCaseName", reportrownum, TestName);

		
		CommonMethod.res = given().log().all().multiPart("action_file", CommonMethod.excelfile)
				.parameter("upload_category","excel")
				.header("Ocp-Apim-Subscription-Key", CommonMethod.SubscriptionKey)
				.header("Authorization", header).spec(reqSpec).when()
				.post("/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber)
						+ "/excelupload/uploadS3/")
				.then().extract().response();

		CommonMethod.responsetime = CommonMethod.res.getTimeIn(TimeUnit.MILLISECONDS);

		CommonMethod.test = CommonMethod.extent
				.startTest("Excel Upload Template Post API Test  " + CommonMethod.getLabel(CommonMethod.responsetime),
						"Verifies Excel upload template")
				.assignCategory("CheckExcelupload");

		CommonMethod.testlog("Pass", "Authorization Token generated" + "<br>" + header);

		System.out.println(CommonMethod.res.asString());
	
		CommonMethod.testlog("Pass", "Verifies response from API" + "<br>" + CommonMethod.res.asString());

		CommonMethod.testlog("Info", "API responded in " + CommonMethod.responsetime + " Milliseconds");
		
		CommonMethod.res.then().spec(respSpec);

	}

}