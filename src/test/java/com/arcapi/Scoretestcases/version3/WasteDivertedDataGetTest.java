package com.arcapi.Scoretestcases.version3;

import static com.jayway.restassured.RestAssured.given;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;
import com.relevantcodes.extentreports.LogStatus;

import junit.framework.Assert;

public class WasteDivertedDataGetTest extends BaseClass {

	@Test
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void WasteDivertedDataGet(String SheetName,String ProjectTypeColumn, int rownumber) throws IOException, ParseException {

		Double reading = 0.0;
		String reading1 = null;
		CommonMethod.ExtentReportConfig();

		//CommonMethod.GeneratingAuthCode();
		
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		CommonMethod.res = given().log().all().header("Ocp-Apim-Subscription-Key", CommonMethod.SubscriptionKey)
				.header("Authorization", header).spec(reqSpec).when()
				.get("/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber)
						+ "/waste/diverted/list/")
				.then().extract().response();
		
		CommonMethod.responsetime = CommonMethod.res.getTimeIn(TimeUnit.MILLISECONDS);

		System.out.println(CommonMethod.responsetime);

		CommonMethod.test = CommonMethod.extent
				.startTest("WasteDivertedData Get API Test  " + CommonMethod.getLabel(CommonMethod.responsetime),
						"Get Waste Diverted data")
				.assignCategory("Waste");

		System.out.println(CommonMethod.res.asString());
		
		int RowNum = data.getRowCountbyColNum("Score", 7);
		 
		 for(int i=2;i<RowNum;i++) {
			 
			 String a = data.getCellData("Score", "WasteDivertedReading", i);
			 
			 JSONParser parser = new JSONParser();
				JSONArray obj = (JSONArray) parser.parse(CommonMethod.res.asString());

				if(obj.size()>0){
				     for (Object user : obj) {            
				         JSONObject jsonrow=(JSONObject)parser.parse(String.valueOf(user));
				    
				         if(!(jsonrow.get("reading")==null)) {
					         reading= (Double)jsonrow.get("reading");
					         reading1 = reading.toString();
					         
					         }
					         else {
					        	 
					        reading1 = "NoValue";
					       
					         }
				         Assert.assertEquals(a, reading1);
				     }
				}	            
		 }
		
		CommonMethod.testlog("Pass", "Response received from API" + "<br>" + CommonMethod.res.asString());

		CommonMethod.testlog("Info", "API responded in " + CommonMethod.responsetime + " Milliseconds");
		
		CommonMethod.res.then().spec(respSpec);
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