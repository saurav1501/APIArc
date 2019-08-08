package com.arcapi.Leedv4Testcases;

import static com.jayway.restassured.RestAssured.given;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.json.JSONObject;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;
import com.jayway.restassured.http.ContentType;
import com.relevantcodes.extentreports.LogStatus;

public class LOAuthenticateAPITest extends BaseClass {

	@Test//(groups = { "Certification", "Precertification","PerformanceScore","Recertification" })
	@Parameters({ "LOSheetName","rownumber" })
	public void LOAuthenticateAPI(String SheetName,int rownumber) throws IOException {

		CommonMethod.ExtentReportConfig();

		//CommonMethod.GeneratingAuthCode(SheetName,rownumber);
		
	    TestName = Thread.currentThread().getStackTrace()[1].getMethodName();
		
			data.setCellData("Report", "TestCaseName", reportrownum, TestName);

		
		String UserName = data.getCellData(SheetName, "LODEVUSER", rownumber);
		String Password = data.getCellData(SheetName, "LODEVPASS", rownumber);
		
		CommonMethod.res = given().log().all()
				.header("Content-Type","application/x-www-form-urlencoded")
				.params(
						"username", UserName,
						"password", Password,
						"guid", "")
				.when()
				.post("https://leedonline-api-qas.usgbc.org/v1/json/authenticate")
				.then()
				.extract()
				.response();
		

		CommonMethod.responsetime = CommonMethod.res.getTimeIn(TimeUnit.MILLISECONDS);

		System.out.println(CommonMethod.res.asString());

		System.out.println(CommonMethod.responsetime);

		CommonMethod.test = CommonMethod.extent
				.startTest("LOAuthenticateAPI Test" + CommonMethod.getLabel(CommonMethod.responsetime),
						"Verifies Add asset")
				.assignCategory("CheckAsset");
		
		CommonMethod.fetchedID = CommonMethod.res.path("token").toString();

		System.out.println(CommonMethod.fetchedID);

		header = "Basic " + CommonMethod.fetchedID;
		System.out.println(header);


		CommonMethod.testlog("Pass", "Authorization Token generated" + "<br>" + header);

		CommonMethod.res.then().assertThat().contentType(ContentType.JSON);

		CommonMethod.testlog("Pass", "Verifies response from API" + "<br>" + CommonMethod.res.asString());
		
		CommonMethod.testlog("Info", "API responded in " + CommonMethod.responsetime + " Milliseconds");

	}

	

}