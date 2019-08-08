package com.arcapi.Citytestcases;

import static com.jayway.restassured.RestAssured.given;

import java.io.FileReader;
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


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;






public class AdditionalDataCreateAPICityCommTest extends BaseClass {

	@Test// (dependsOnMethods={"com.arcapi.testcases.CreateAssetPOSTAPITest.CreateAssetPOSTAPI"})
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void AdditionalDataCreateAPICityComm(String SheetName,String ProjectTypeColumn, int rownumber) throws IOException, ParseException {

		CommonMethod.ExtentReportConfig();

	    //CommonMethod.GeneratingAuthCodeForLOUser(SheetName,rownumber);
		
		CommonMethod.test = CommonMethod.extent
				.startTest("Meter Create Post API Test  " + CommonMethod.getLabel(CommonMethod.responsetime),
						"Verifies Meter Creation")
				.assignCategory("CheckMeter");
		
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		
        int row = 2;
                
        //CommonMethod.Jsonfileread();
        
        JSONParser parser = new JSONParser();
		 
       // Object object = parser.parse(new FileReader(CommonMethod.Jsonfile));
	
       // JSONObject jsonObject = (JSONObject)object;
        JSONArray a = (JSONArray) parser.parse(new FileReader(CommonMethod.Jsonfile));

        for(Object fuel : a)
        {
        	JSONObject jsonObject = (JSONObject)fuel;
        	Long fuel1 = (Long) jsonObject.get("fuel_type_id");
        	String MeterName = (String) jsonObject.get("name");
        	 //JSONArray fuel1 = (JSONArray) jsonObject.get("fuel_type_id");
        	 String id = fuel1.toString();
        	 data.setCellData("DataInput", "AdditionalDataType", row, id);
         
		
		JSONObject jsonAsMap = new JSONObject();
	    jsonAsMap.put("name", MeterName);
		jsonAsMap.put("native_unit", "");
		jsonAsMap.put("type", id);

		CommonMethod.res = given().log().all().header("Ocp-Apim-Subscription-Key", CommonMethod.SubscriptionKey)
				.header("content-type", "application/json").header("Authorization", header).spec(reqSpec)
				.body(jsonAsMap).when()
				.post("/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/meters/").then()
				.extract().response();

		CommonMethod.responsetime = CommonMethod.res.getTimeIn(TimeUnit.MILLISECONDS);

		System.out.println(CommonMethod.responsetime);

		CommonMethod.testlog("Pass", "Authorization Token generated" + "<br>" + header);

		System.out.println(CommonMethod.res.asString());
		
		CommonMethod.testlog("Pass", "Verifies response from API" + "<br>" + CommonMethod.res.asString());

		CommonMethod.fetchedID = CommonMethod.res.path("id").toString();

		data.setCellData("DataInput", "AdditionalMeterID", row, CommonMethod.fetchedID);

		CommonMethod.testlog("Info", "API responded in " + CommonMethod.responsetime + " Milliseconds");
		
		CommonMethod.res.then().assertThat().statusCode(201);
		
		System.out.println("count= " + row);
		row++;

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