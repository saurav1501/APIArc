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

public class HumanExperienceMeterUpdateAPITest extends BaseClass {

	@Test//(dependsOnMethods = { "com.arcapi.testcases.CreateAssetPOSTAPITest.CreateAssetPOSTAPI" })
	@Parameters({ "LOSheetName","LOProjectTypeColumn","rownumber" })
	public void HumanExperienceMeterUpdateAPI(String SheetName,String ProjectTypeColumn, int rownumber) throws IOException {

		CommonMethod.ExtentReportConfig();

		CommonMethod.GeneratingAuthCodeForLOUser(SheetName,rownumber);
		
		CommonMethod.test = CommonMethod.extent
				.startTest("HumanExperience Meter Update API" + CommonMethod.getLabel(CommonMethod.responsetime),
						"Verifies Existing Meter Update details")
				.assignCategory("CheckMeter");
		
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		
	    TestName = Thread.currentThread().getStackTrace()[1].getMethodName();
		
			data.setCellData("Report", "TestCaseName", reportrownum, TestName);

		
        int RowNum = data.getRowCountbyColNum("DataInput", 6);
        
        String[] Unit = {"µg/m3","mg/m3", "ppm","ppb"};
        
        String[] Type = {"263","260","258","565","566","567","568","569","570","571","572"};
		
		for (int i =2; i<= 2;i++) {
			for (int j =0; j< Unit.length;j++) {
				for (int k =0; k< Type.length;k++) {
			
       
		JSONObject jsonAsMap = new JSONObject();
		jsonAsMap.put("name", "Formaldehyde Updated");
		jsonAsMap.put("native_unit", Unit[j]);
		jsonAsMap.put("type", Type[k]);

		CommonMethod.res = given().log().all().header("Ocp-Apim-Subscription-Key", CommonMethod.SubscriptionKey)
				.header("Content-type", "application/json").header("Authorization", header).spec(reqSpec)
				.body(jsonAsMap).when()
				.put("/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/meters/ID:"
						+ data.getCellData("DataInput", "HumanExperienceMeterID", i) + "/")
				.then().extract().response();
		
		CommonMethod.responsetime = CommonMethod.res.getTimeIn(TimeUnit.MILLISECONDS);

		System.out.println(CommonMethod.responsetime);

		CommonMethod.testlog("Pass", "Authorization Token generated" + "<br>" + header);

		
		System.out.println(CommonMethod.res.asString());
		
		CommonMethod.testlog("Pass", "Verifies response from API" + "<br>" + CommonMethod.res.asString());

		CommonMethod.testlog("Info", "API responded in " + CommonMethod.responsetime + " Milliseconds");
		
		
		if(j==0 && k==0){
			System.out.println("Hi");
		CommonMethod.res.then().assertThat().statusCode(400);
		}
		
		else if(j==1 && k>0){
			
			CommonMethod.res.then().assertThat().statusCode(400);
			}
		
		else if(j==2 && k>1){
			
			CommonMethod.res.then().assertThat().statusCode(400);
			}
		
		else if(j==3 && k>=0){
			if(k==10) {
				System.out.println("inside");
				CommonMethod.res.then().spec(respSpec);
			}
			else {
			CommonMethod.res.then().assertThat().statusCode(400);
			}
		}
        else {
        	CommonMethod.res.then().spec(respSpec);
        }
		
		}
	}
		}}
	
}