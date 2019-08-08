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

public class VerifyAgreementDeleteAPITest extends BaseClass {

	@Test
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void VerifyAgreementDeleteAPI(String SheetName,String ProjectTypeColumn, int rownumber) throws IOException {

		CommonMethod.ExtentReportConfig();

		//CommonMethod.GeneratingAuthCodeForLOUser(SheetName, rownumber);

		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		
	    TestName = Thread.currentThread().getStackTrace()[1].getMethodName();
		
			data.setCellData("Report", "TestCaseName", reportrownum, TestName);

		
		CommonMethod.test = CommonMethod.extent
				.startTest("VerifyAgreementDelete API Test",
						"Verifies uploaded credit form delete")
				.assignCategory("CheckCredit");
		
		String arr1[] = {data.getCellData(SheetName, "CreditAgreementDocID1", rownumber),data.getCellData(SheetName, "DocumentAgreement1", rownumber),data.getCellData(SheetName, "FileIDAgreement1", rownumber),data.getCellData(SheetName, "ApplicationIDAgreement1", rownumber)};
		//String arr2[] = {data.getCellData(SheetName, "CreditAgreementDocID2", rownumber),data.getCellData(SheetName, "DocumentAgreement2", rownumber),data.getCellData(SheetName, "FileIDAgreement2", rownumber),data.getCellData(SheetName, "ApplicationIDAgreement2", rownumber)};
		
		//String[][] arr3 = {arr2,arr1};

		CommonMethod.res = given().log().all().header("Ocp-Apim-Subscription-Key", CommonMethod.SubscriptionKey)
				.header("Authorization", header).header("content-type", "application/json").spec(reqSpec)
				.when()
				.delete("/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/actions/ID:"
						+ arr1[0]
						+ "/uploads/?Description="+value[0]+"&Documenttype=Z02&Documentnumber="
						+ arr1[1]
						+ "&Documentpart=000&Documentversion=00&ApplicationId="
						+ arr1[3] + "&FileId="
						+ arr1[2])
				.then().extract().response();
		

		CommonMethod.responsetime = CommonMethod.res.getTimeIn(TimeUnit.MILLISECONDS);
		
		System.out.println(CommonMethod.responsetime);

		CommonMethod.testlog("Pass", "Authorization Token generated" + "<br>" + header);

		System.out.println(CommonMethod.res.asString());
		
		CommonMethod.testlog("Pass", "Verifies response from API" + "<br>" + CommonMethod.res.asString());

		CommonMethod.testlog("Info", "API responded in " + CommonMethod.responsetime + " Milliseconds");
		
		CommonMethod.res.then().assertThat().statusCode(403);

	}
	
}