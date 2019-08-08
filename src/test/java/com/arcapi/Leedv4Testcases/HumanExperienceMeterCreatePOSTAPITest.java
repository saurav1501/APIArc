package com.arcapi.Leedv4Testcases;

import static com.jayway.restassured.RestAssured.given;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;
import com.relevantcodes.extentreports.LogStatus;

import net.minidev.json.JSONObject;

public class HumanExperienceMeterCreatePOSTAPITest extends BaseClass {

	@Test// (dependsOnMethods={"com.arcapi.testcases.CreateAssetPOSTAPITest.CreateAssetPOSTAPI"})
	@Parameters({ "LOSheetName","LOProjectTypeColumn","rownumber" })
	public void MeterCreatePOSTAPI(String SheetName,String ProjectTypeColumn, int rownumber) throws IOException {

		CommonMethod.ExtentReportConfig();

	    CommonMethod.GeneratingAuthCodeForLOUser(SheetName,rownumber);
		
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		
	    TestName = Thread.currentThread().getStackTrace()[1].getMethodName();
		
			data.setCellData("Report", "TestCaseName", reportrownum, TestName);

		
        int row = 2;
		
		String[] Unit = {"�/m3","mg/m3", "ppm","ppb"};
		String[] Type = {"263","260","258","565","566","567","568","569","570","571","572"};
		
		String MeterName = null;
		
		
		for (int i=0;i < Unit.length;i++) {
			for(int j=0;j<Type.length;j++) {
				
				System.out.println(j);
				
				switch(j)
				{
				           
				   // case statements
				   // values must be of same type of expression
				   case 0 : MeterName = "CarbonMonoOxide";
				      break; // break is optional
				   
				   case 1 : MeterName = "Ozone";
				      break; // break is optional
				      
				   case 2 : MeterName = "PM2.5";
				      break; // break is optional
				      
				   case 3 : MeterName = "Acetaldehyde";
				      break; // break is optional
				      
				   case 4 : MeterName = "Benzene";
				      break; // break is optional
				      
				   case 5 : MeterName = "Styrene";
				      break; // break is optional
				      
				   case 6 : MeterName = "Toluene";
				      break; // break is optional
				      
				   case 7 : MeterName = "Naphthalene";
				      break; // break is optional
				      
				   case 8 : MeterName = "Dichlorobenzene";
				      break; // break is optional
				      
				   case 9 : MeterName = "Xylenes-total";
				      break; // break is optional
				      
				   case 10 : MeterName = "Formaldehyde";
				      break; // break is optional
				   default : 
				      System.out.println("Something went wrong");
				
				}
			
		JSONObject jsonAsMap = new JSONObject();
		jsonAsMap.put("name", MeterName);
		jsonAsMap.put("native_unit", Unit[i]);
		jsonAsMap.put("type", Type[i]);

		CommonMethod.res = given().log().all().header("Ocp-Apim-Subscription-Key", CommonMethod.SubscriptionKey)
				.header("content-type", "application/json").header("Authorization", header).spec(reqSpec)
				.body(jsonAsMap).when()
				.post("/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/meters/").then()
				.extract().response();

		CommonMethod.responsetime = CommonMethod.res.getTimeIn(TimeUnit.MILLISECONDS);

		System.out.println(CommonMethod.responsetime);

		CommonMethod.test = CommonMethod.extent
				.startTest("Meter Create Post API Test  " + CommonMethod.getLabel(CommonMethod.responsetime),
						"Verifies Meter Creation")
				.assignCategory("CheckMeter");

		CommonMethod.testlog("Pass", "Authorization Token generated" + "<br>" + header);

		System.out.println(CommonMethod.res.asString());
		
		CommonMethod.testlog("Pass", "Verifies response from API" + "<br>" + CommonMethod.res.asString());

		CommonMethod.fetchedID = CommonMethod.res.path("id").toString();

		data.setCellData("DataInput", "HumanExperienceMeterID", row, CommonMethod.fetchedID);

		CommonMethod.testlog("Info", "API responded in " + CommonMethod.responsetime + " Milliseconds");
		
		CommonMethod.res.then().assertThat().statusCode(201);
		
		row++;

	}
		}
	}
	
}