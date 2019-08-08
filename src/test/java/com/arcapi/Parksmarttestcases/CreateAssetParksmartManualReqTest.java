package com.arcapi.Parksmarttestcases;

import static com.jayway.restassured.RestAssured.given;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;
import com.jayway.restassured.http.ContentType;
import com.relevantcodes.extentreports.LogStatus;

public class CreateAssetParksmartManualReqTest extends BaseClass {

	@Test(groups = { "Certification", "Precertification","PerformanceScore","Recertification" })
	@Parameters({ "SheetName", "ProjectType","ProjectTypeColumn","rownumber" })
	public void CreateAssetPOSTAPI(String SheetName, String ProjectType,String ProjectTypeColumn, int rownumber) throws IOException {

		CommonMethod.ExtentReportConfig();

		CommonMethod.GeneratingAuthCode(SheetName,rownumber);
		
		CommonMethod.test = CommonMethod.extent
				.startTest("Create New Parksmart Test",
						"Verifies Add asset")
				.assignCategory("CheckAsset");
		
		String OwnerType = null;
		
		 int RowNum = data.getRowCountbyColNum("Manual", 0);
		
		for(int i=2;i<=RowNum;i++) {
		
		String[] OwnerTypeOptions  = {"Business Improvement District","Community Development Corporation or Non-profit developer","Corporate: Privately Held","Corporate: Publicly Traded","Educational: College, Private","Educational: College, Public","Educational: Community College, Private","Educational: Community College, Public","Educational: Early Childhood Education/Daycare","Educational: K-12 School, Private","Educational: K-12 School, Public","Educational: University, Private","Educational: University, public","Government Use: Federal","Government Use: Local, City","Government Use: Local, Public Housing Authority","Government Use: Other (utility, airport, etc.)",
				"Government Use: State","Investor: Bank","Investor: Endowment","Investor: Equity Fund","Investor: Individual/Family","Investor: Insurance Company","Investor: Investment Manager","Investor: Pension Fund","Investor: REIT, Non-traded","Investor: REIT, Publicly traded","Investor: ROEC","Main Street Organization","Non-Profit (that do not fit into other categories)","Religious"};
		
		OwnerType = (OwnerTypeOptions[new Random().nextInt(OwnerTypeOptions.length)]);
		
		//data.setCellData(SheetName, "OwnerType", rownumber, OwnerType);
		
		String ProjectName = "MachineTestProject-Parksmart-" + data.getCellData("Manual", "CountryCode", i);
		
		//data.setCellData(SheetName, "ProjectName", rownumber, ProjectName);
		
		String CountryCode = data.getCellData("Manual", "CountryCode", i);
		
		String StateCode = data.getCellData("Manual", "StateCode", i);
		
		String ZipCode = data.getCellData("Manual", "ZipCode", i);
		
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		CommonMethod.res = given().log().all()
				.parameters(
					    "name", ProjectName,
					    "project_type", ProjectType,
					    "rating_system","PARKSMART", 
					    "ownerType", OwnerType,
					    "organization","A & A Dealers",
					    "owner_email","stg-01@gmail.com",
					    "manageEntityCountry",CountryCode,
					    "confidential", false,
					    "noOfParkingSpace", "15", 
					    "noOfParkingLevels","2",
					    "year_constructed", "2018-04-30",
					    "street", "1 Mount Road", 
					    "city","Test City", 
					    "country", CountryCode, 
					    "state", StateCode,
					    "zip_code",ZipCode
					    )
				.header("Ocp-Apim-Subscription-Key", CommonMethod.SubscriptionKey)
				.header("Authorization", header).spec(reqSpec).when().post("/assets/").then()
				.extract().response();
		
		System.out.println("This is " + CountryCode + " " + "Project");

		CommonMethod.responsetime = CommonMethod.res.getTimeIn(TimeUnit.MILLISECONDS);

		System.out.println(CommonMethod.res.asString());

		System.out.println(CommonMethod.responsetime);

		CommonMethod.testlog("Pass", "Authorization Token generated" + "<br>" + header);

		CommonMethod.res.then().assertThat().statusCode(200);
		
		CommonMethod.res.then().assertThat().contentType(ContentType.JSON);

		CommonMethod.testlog("Pass", "Verifies response from API" + "<br>" + CommonMethod.res.asString());

		CommonMethod.fetchedID = CommonMethod.res.path("leed_id").toString();

		System.out.println(CommonMethod.fetchedID);

		data.setCellData("Manual", ProjectTypeColumn, i, CommonMethod.fetchedID);

		CommonMethod.testlog("Info", "API responded in " + CommonMethod.responsetime + " Milliseconds");

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