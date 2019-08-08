package com.arcapi.Scoretestcases.version3;


import static com.jayway.restassured.RestAssured.given;
import static org.junit.Assert.assertThat;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.skyscreamer.jsonassert.Customization;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.comparator.CustomComparator;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.jayway.restassured.path.json.JsonPath;
import com.relevantcodes.extentreports.LogStatus;

import junit.framework.Assert;

import org.skyscreamer.jsonassert.Customization;
import org.skyscreamer.jsonassert.comparator.CustomComparator;

public class VOCGetTest extends BaseClass {

	@Test
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void VOCGet(String SheetName,String ProjectTypeColumn, int rownumber) throws IOException, ParseException {
		
		Double reading = 0.0;
		String reading1 = null;

		CommonMethod.ExtentReportConfig();
		
		
		//CommonMethod.GeneratingAuthCode();
		
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());

		CommonMethod.res= given().log().all().header("Ocp-Apim-Subscription-Key", CommonMethod.SubscriptionKey)
				.header("Authorization", header).spec(reqSpec).when()
				.get("/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber)
						+ "/voc/list/")
				.then().extract().response();
		
		CommonMethod.responsetime = CommonMethod.res.getTimeIn(TimeUnit.MILLISECONDS);

		System.out.println(CommonMethod.responsetime);

		CommonMethod.test = CommonMethod.extent
				.startTest("VOC Get API Test  " + CommonMethod.getLabel(CommonMethod.responsetime), "Verifies VOC Data")
				.assignCategory("VOC");

		System.out.println(CommonMethod.res.asString());
		
		 int RowNum = data.getRowCountbyColNum("Score", 0);
		 
		 for(int i=2;i<RowNum;i++) {
			 
			 String a = data.getCellData("Score", "VOCReading", i);
			 
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
				         
				         
				        // System.out.println(reading);
				        // Assert.assertEquals(a, reading1);
				     }
				       
		 }
		
		
		/*JSONParser parser = new JSONParser();
        //Use JSONObject for simple JSON and JSONArray for array of JSON.
        JSONArray data = (JSONArray) parser.parse(new FileReader(System.getProperty("user.dir") + "/src/main/resources/Response.json"));//path to the JSON file.

        String json = data.toJSONString();
        
       // JsonPath jsonPathEvaluator = data.jsonPath();
		
		//JsonObject obj = new JsonParser().parse(CommonMethod.res.asString()).getAsJsonObject();
        
       
        
        JSONAssert.assertEquals(json,  CommonMethod.res.asString(),
                new CustomComparator(JSONCompareMode.LENIENT,
                    new Customization("end_date", (o1, o2) -> true)));*/
		
       /* JSONAssert.assertEquals("Json Doesn't match", CommonMethod.res.asString(), json, JSONCompareMode.LENIENT, new CustomComparator(JSONCompareMode.LENIENT,
                new Customization("timestamp", (o1, o2) -> true)));*/
	
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