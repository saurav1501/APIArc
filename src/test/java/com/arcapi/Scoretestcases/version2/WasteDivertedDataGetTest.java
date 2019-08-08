package com.arcapi.Scoretestcases.version2;

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
						+ "/waste/diverted/?units=lbs&resample="
						+ "2017-10-01/P1D,2017-10-02/P1D,2017-10-03/P1D,2017-10-04/P1D,2017-10-05/P1D,2017-10-06/P1D,2017-10-07/P1D,2017-10-08/P1D,2017-10-09/P1D,2017-10-10/P1D,2017-10-11/P1D,2017-10-12/P1D,2017-10-13/P1D,2017-10-14/P1D,2017-10-15/P1D,2017-10-16/P1D,2017-10-17/P1D,2017-10-18/P1D,2017-10-19/P1D,2017-10-20/P1D,2017-10-21/P1D,2017-10-22/P1D,2017-10-23/P1D,2017-10-24/P1D,2017-10-25/P1D,2017-10-26/P1D,2017-10-27/P1D,2017-10-28/P1D,2017-10-29/P1D,2017-10-30/P1D,2017-10-31/P1D,2017-11-01/P1D,2017-11-02/P1D,2017-11-03/P1D,2017-11-04/P1D,2017-11-05/P1D,2017-11-06/P1D,2017-11-07/P1D,2017-11-08/P1D,2017-11-09/P1D,2017-11-10/P1D,2017-11-11/P1D,2017-11-12/P1D,2017-11-13/P1D,2017-11-14/P1D,2017-11-15/P1D,2017-11-16/P1D,2017-11-17/P1D,2017-11-18/P1D,2017-11-19/P1D,2017-11-20/P1D,2017-11-21/P1D,2017-11-22/P1D,2017-11-23/P1D,2017-11-24/P1D,2017-11-25/P1D,2017-11-26/P1D,2017-11-27/P1D,2017-11-28/P1D,2017-11-29/P1D,2017-11-30/P1D,2017-12-01/P1D,2017-12-02/P1D,2017-12-03/P1D,2017-12-04/P1D,2017-12-05/P1D,2017-12-06/P1D,2017-12-07/P1D,2017-12-08/P1D,2017-12-09/P1D,2017-12-10/P1D,2017-12-11/P1D,2017-12-12/P1D,2017-12-13/P1D,2017-12-14/P1D,2017-12-15/P1D,2017-12-16/P1D,2017-12-17/P1D,2017-12-18/P1D,2017-12-19/P1D,2017-12-20/P1D,2017-12-21/P1D,2017-12-22/P1D,2017-12-23/P1D,2017-12-24/P1D,2017-12-25/P1D,2017-12-26/P1D,2017-12-27/P1D,2017-12-28/P1D,2017-12-29/P1D,2017-12-30/P1D,2017-12-31/P1D,2018-01-01/P1D,2018-01-02/P1D,2018-01-03/P1D,2018-01-04/P1D,2018-01-05/P1D,2018-01-06/P1D,2018-01-07/P1D,2018-01-08/P1D,2018-01-09/P1D,2018-01-10/P1D,2018-01-11/P1D,2018-01-12/P1D,2018-01-13/P1D,2018-01-14/P1D,2018-01-15/P1D,2018-01-16/P1D,2018-01-17/P1D,2018-01-18/P1D,2018-01-19/P1D,2018-01-20/P1D,2018-01-21/P1D,2018-01-22/P1D,2018-01-23/P1D,2018-01-24/P1D,2018-01-25/P1D,2018-01-26/P1D,2018-01-27/P1D,2018-01-28/P1D,2018-01-29/P1D,2018-01-30/P1D,2018-01-31/P1D,2018-02-01/P1D,2018-02-02/P1D,2018-02-03/P1D,2018-02-04/P1D,2018-02-05/P1D,2018-02-06/P1D,2018-02-07/P1D,2018-02-08/P1D,2018-02-09/P1D,2018-02-10/P1D,2018-02-11/P1D,2018-02-12/P1D,2018-02-13/P1D,2018-02-14/P1D,2018-02-15/P1D,2018-02-16/P1D,2018-02-17/P1D,2018-02-18/P1D,2018-02-19/P1D,2018-02-20/P1D,2018-02-21/P1D,2018-02-22/P1D,2018-02-23/P1D,2018-02-24/P1D,2018-02-25/P1D,2018-02-26/P1D,2018-02-27/P1D,2018-02-28/P1D,2018-03-01/P1D,2018-03-02/P1D,2018-03-03/P1D,2018-03-04/P1D,2018-03-05/P1D,2018-03-06/P1D,2018-03-07/P1D,2018-03-08/P1D,2018-03-09/P1D,2018-03-10/P1D,2018-03-11/P1D,2018-03-12/P1D,2018-03-13/P1D,2018-03-14/P1D,2018-03-15/P1D,2018-03-16/P1D,2018-03-17/P1D,2018-03-18/P1D,2018-03-19/P1D,2018-03-20/P1D,2018-03-21/P1D,2018-03-22/P1D,2018-03-23/P1D,2018-03-24/P1D,2018-03-25/P1D,2018-03-26/P1D,2018-03-27/P1D,2018-03-28/P1D,2018-03-29/P1D,2018-03-30/P1D,2018-03-31/P1D,2018-04-01/P1D,2018-04-02/P1D,2018-04-03/P1D,2018-04-04/P1D,2018-04-05/P1D,2018-04-06/P1D,2018-04-07/P1D,2018-04-08/P1D,2018-04-09/P1D,2018-04-10/P1D,2018-04-11/P1D,2018-04-12/P1D,2018-04-13/P1D,2018-04-14/P1D,2018-04-15/P1D,2018-04-16/P1D,2018-04-17/P1D,2018-04-18/P1D,2018-04-19/P1D,2018-04-20/P1D,2018-04-21/P1D,2018-04-22/P1D,2018-04-23/P1D,2018-04-24/P1D,2018-04-25/P1D,2018-04-26/P1D,2018-04-27/P1D,2018-04-28/P1D,2018-04-29/P1D,2018-04-30/P1D,2018-05-01/P1D,2018-05-02/P1D,2018-05-03/P1D,2018-05-04/P1D,2018-05-05/P1D,2018-05-06/P1D,2018-05-07/P1D,2018-05-08/P1D,2018-05-09/P1D,2018-05-10/P1D,2018-05-11/P1D,2018-05-12/P1D,2018-05-13/P1D,2018-05-14/P1D,2018-05-15/P1D,2018-05-16/P1D,2018-05-17/P1D,2018-05-18/P1D,2018-05-19/P1D,2018-05-20/P1D,2018-05-21/P1D,2018-05-22/P1D,2018-05-23/P1D,2018-05-24/P1D,2018-05-25/P1D,2018-05-26/P1D,2018-05-27/P1D,2018-05-28/P1D,2018-05-29/P1D,2018-05-30/P1D,2018-05-31/P1D,2018-06-01/P1D,2018-06-02/P1D,2018-06-03/P1D,2018-06-04/P1D,2018-06-05/P1D,2018-06-06/P1D,2018-06-07/P1D,2018-06-08/P1D,2018-06-09/P1D,2018-06-10/P1D,2018-06-11/P1D,2018-06-12/P1D,2018-06-13/P1D,2018-06-14/P1D,2018-06-15/P1D,2018-06-16/P1D,2018-06-17/P1D,2018-06-18/P1D,2018-06-19/P1D,2018-06-20/P1D,2018-06-21/P1D,2018-06-22/P1D,2018-06-23/P1D,2018-06-24/P1D,2018-06-25/P1D,2018-06-26/P1D,2018-06-27/P1D,2018-06-28/P1D,2018-06-29/P1D,2018-06-30/P1D,2018-07-01/P1D,2018-07-02/P1D,2018-07-03/P1D,2018-07-04/P1D,2018-07-05/P1D,2018-07-06/P1D,2018-07-07/P1D,2018-07-08/P1D,2018-07-09/P1D,2018-07-10/P1D,2018-07-11/P1D,2018-07-12/P1D,2018-07-13/P1D,2018-07-14/P1D,2018-07-15/P1D,2018-07-16/P1D,2018-07-17/P1D,2018-07-18/P1D,2018-07-19/P1D,2018-07-20/P1D,2018-07-21/P1D,2018-07-22/P1D,2018-07-23/P1D,2018-07-24/P1D,2018-07-25/P1D,2018-07-26/P1D,2018-07-27/P1D,2018-07-28/P1D,2018-07-29/P1D,2018-07-30/P1D,2018-07-31/P1D,2018-08-01/P1D,2018-08-02/P1D,2018-08-03/P1D,2018-08-04/P1D,2018-08-05/P1D,2018-08-06/P1D,2018-08-07/P1D,2018-08-08/P1D,2018-08-09/P1D,2018-08-10/P1D,2018-08-11/P1D,2018-08-12/P1D,2018-08-13/P1D,2018-08-14/P1D,2018-08-15/P1D,2018-08-16/P1D,2018-08-17/P1D,2018-08-18/P1D,2018-08-19/P1D,2018-08-20/P1D,2018-08-21/P1D,2018-08-22/P1D,2018-08-23/P1D,2018-08-24/P1D,2018-08-25/P1D,2018-08-26/P1D,2018-08-27/P1D,2018-08-28/P1D,2018-08-29/P1D,2018-08-30/P1D,2018-08-31/P1D,2018-09-01/P1D,2018-09-02/P1D,2018-09-03/P1D,2018-09-04/P1D,2018-09-05/P1D,2018-09-06/P1D,2018-09-07/P1D,2018-09-08/P1D,2018-09-09/P1D,2018-09-10/P1D,2018-09-11/P1D,2018-09-12/P1D,2018-09-13/P1D,2018-09-14/P1D,2018-09-15/P1D,2018-09-16/P1D,2018-09-17/P1D,2018-09-18/P1D,2018-09-19/P1D,2018-09-20/P1D,2018-09-21/P1D,2018-09-22/P1D,2018-09-23/P1D,2018-09-24/P1D,2018-09-25/P1D,2018-09-26/P1D,2018-09-27/P1D,2018-09-28/P1D,2018-09-29/P1D,2018-09-30/P1D")
				.then().extract().response();
		
		CommonMethod.responsetime = CommonMethod.res.getTimeIn(TimeUnit.MILLISECONDS);

		System.out.println(CommonMethod.responsetime);

		CommonMethod.test = CommonMethod.extent
				.startTest("WasteDivertedData Get API Test  " + CommonMethod.getLabel(CommonMethod.responsetime),
						"Get Waste Diverted data")
				.assignCategory("Waste");

		System.out.println(CommonMethod.res.asString());
		
        String response = CommonMethod.res.asString().replaceAll("NaN", "null");
		
		JSONParser parser = new JSONParser();
		JSONArray obj = (JSONArray) parser.parse(response);

		if(obj.size()>0){
		     for (Object user : obj) {            
		         JSONObject jsonrow=(JSONObject)parser.parse(String.valueOf(user));
		         if(!(jsonrow.get("reading")==null)) {
			         reading= (Double)jsonrow.get("reading");
			         reading1 = reading.toString();
			         data.setCellData("Score", "WasteDivertedReading", rownumber, reading1);
			         rownumber++;
			         }
			         else {
			        	 
			        reading1 = "NoValue";
			        data.setCellData("Score", "WasteDivertedReading", rownumber, reading1);
			         rownumber++;
			         }
			         
			     }}
		
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