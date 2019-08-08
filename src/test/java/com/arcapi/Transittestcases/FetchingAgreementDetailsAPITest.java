package com.arcapi.Transittestcases;

import static com.jayway.restassured.RestAssured.given;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;
import com.relevantcodes.extentreports.LogStatus;

public class FetchingAgreementDetailsAPITest extends BaseClass {

	@Test//(dependsOnMethods = { "com.arcapi.testcases.CreateAssetPOSTAPITest.CreateAssetPOSTAPI" })
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void FetchingAgreementDetailsAPI(String SheetName,String ProjectTypeColumn, int rownumber) throws IOException {

		CommonMethod.ExtentReportConfig();

		//CommonMethod.GeneratingAuthCodeForLOUser(SheetName, rownumber);
		
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		
	    TestName = Thread.currentThread().getStackTrace()[1].getMethodName();
		
			data.setCellData("Report", "TestCaseName", reportrownum, TestName);


		CommonMethod.res = given().log().all().header("Ocp-Apim-Subscription-Key", CommonMethod.SubscriptionKey)
				.header("Authorization", header).spec(reqSpec).when()
				.get("/assets/LEED:" +data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/documents/").then().extract()
				.response();

		CommonMethod.responsetime = CommonMethod.res.getTimeIn(TimeUnit.MILLISECONDS);

		System.out.println(CommonMethod.responsetime);

		CommonMethod.test = CommonMethod.extent
				.startTest("FetchingAgreementDetails API Test  " + CommonMethod.getLabel(CommonMethod.responsetime),
						"Verifies Asset document detail")
				.assignCategory("CheckAsset");

		System.out.println(CommonMethod.res.asString());
		

		
		CommonMethod.testlog("Pass", "Response received from API" + "<br>" + CommonMethod.res.asString());

		CommonMethod.testlog("Info", "API responded in " + CommonMethod.responsetime + " Milliseconds");
		
		CommonMethod.res.then().spec(respSpec);
		
		String Creditagreement1 = CommonMethod.res.path("EtFile.ObjectId[0]").toString();
		data.setCellData(SheetName, "CreditAgreementDocID1", rownumber, Creditagreement1);
		/*String Creditagreement2 = CommonMethod.res.path("EtFile.ObjectId[1]").toString();
		data.setCellData(SheetName, "CreditAgreementDocID2", rownumber, Creditagreement2);*/
		String DocNumberAg1 = CommonMethod.res.path("EtFile.Documentnumber[0]").toString();
		data.setCellData(SheetName, "DocumentAgreement1", rownumber, DocNumberAg1);
		/*String DocNumberAg2 = CommonMethod.res.path("EtFile.Documentnumber[1]").toString();
		data.setCellData(SheetName, "DocumentAgreement2", rownumber, DocNumberAg2);*/
		String FileIDAg1 = CommonMethod.res.path("EtFile.FileId[0]").toString();
		data.setCellData(SheetName, "FileIDAgreement1", rownumber, FileIDAg1);
		/*String FileIDAg2 = CommonMethod.res.path("EtFile.FileId[1]").toString();
		data.setCellData(SheetName, "FileIDAgreement2", rownumber, FileIDAg2);*/
		String AppIDAg1 = CommonMethod.res.path("EtFile.ApplicationId[0]").toString();
		data.setCellData(SheetName, "ApplicationIDAgreement1", rownumber, AppIDAg1);
		/*String AppIDAg2 = CommonMethod.res.path("EtFile.ApplicationId[1]").toString();
		data.setCellData(SheetName, "ApplicationIDAgreement2", rownumber, AppIDAg2);*/
	

	}
	
}