package com.arcapi.Citytestcases;

import static com.jayway.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;
import com.relevantcodes.extentreports.LogStatus;

public class VerifyProjectRegisteredAssetListTest extends BaseClass {

	@Test
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void VerifyProjectRegisteredAssetList(String SheetName,String ProjectTypeColumn, int rownumber) {

		CommonMethod.ExtentReportConfig();

		//CommonMethod.GeneratingAuthCodeForLOUser(SheetName,rownumber);
		
		String LeedID = data.getCellData(SheetName, ProjectTypeColumn, rownumber);
		
	    TestName = Thread.currentThread().getStackTrace()[1].getMethodName();
		
			data.setCellData("Report", "TestCaseName", reportrownum, TestName);

		
		System.out.println(LeedID);
		
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		CommonMethod.res= given().log().all().header("Ocp-Apim-Subscription-Key", CommonMethod.SubscriptionKey)
				.header("Authorization", header).spec(reqSpec).when().get("/assets/").then()
				.extract().response();
		
		CommonMethod.responsetime = CommonMethod.res.getTimeIn(TimeUnit.MILLISECONDS);

		System.out.println(CommonMethod.responsetime);

		CommonMethod.test = CommonMethod.extent
				.startTest("Verify ProjectRegistered AssetList" + CommonMethod.getLabel(CommonMethod.responsetime),
						"Verifies List of Assets")
				.assignCategory("CheckAsset");

		System.out.println(CommonMethod.res.asString());
		
        ArrayList<String> Leed_ID_List = CommonMethod.res.path("results.leed_id");
        
        System.out.println(Leed_ID_List);
        
        Set<String> set = new HashSet<String>(Leed_ID_List);
        
        Assert.assertTrue((set.toString().contains(LeedID)),"List does not have LEED ID");
		
		CommonMethod.testlog("Pass", "Response received from API" + "<br>" + CommonMethod.res.asString());

		CommonMethod.testlog("Info", "API responded in " + CommonMethod.responsetime + " Milliseconds");
		
		CommonMethod.res.then().spec(respSpec);
	}

}