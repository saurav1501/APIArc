package com.arcapi.Portfoliostestcases;
import static com.jayway.restassured.RestAssured.given;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;


public class PortfoliosEnergy5YearMTCO2DataVerifyGetAPITest extends BaseClass {
	
	@Test
	public void PortfoliosEnergy3YearMTCO2DataVerifyGetAPI()throws IOException {
	
	CommonMethod.res = given().log().all().header("Ocp-Apim-Subscription-Key", CommonMethod.SubscriptionKey)
			.header("Authorization", header).spec(reqSpec).when()
			.get("/portfolios/ID:" + data.getCellData(sheetName, "PortfolioID", rowNumTwo) + "/analytics/energy/?period=5").then()
			.extract().response();
	
	CommonMethod.responsetime = CommonMethod.res.getTimeIn(TimeUnit.MILLISECONDS);

	CommonMethod.test = CommonMethod.extent
			.startTest("Portfolios Energy 5 Year MTCO2 Data GetAPITest API Test" + CommonMethod.getLabel(CommonMethod.responsetime),
					"Verifies Portfolios")
			.assignCategory("Portfolios");
	
	  System.out.println(CommonMethod.res.asString());
  
	  List<Object> responseBody = CommonMethod.res.path("reading");
	  List<Object> responseBodystart_date = CommonMethod.res.path("start_date");
	  List<Object> responseBodyend_date = CommonMethod.res.path("end_date");
	  
	  
	  CommonMethod.testlog("Info","***********Verifying the Size of Respose Body should be 61 for 5 years data **********");
	  Assert.assertTrue(responseBody!=null);
	  int sizeofReading = responseBody.size();
	  Assert.assertEquals(sizeofReading,61 );
	  System.out.println(responseBody.size()); 
	  CommonMethod.testlog("Pass", "Verified successfully" + "<br>" +responseBody.size());
	  
 
	  CommonMethod.testlog("Info","*********** Verifying Respose Body reading Should Not Be Null **********");
	  Assert.assertTrue(responseBody!=null); 

      CommonMethod.testlog("Pass", "Verified successfully" + "<br>" +responseBody.toString());
      
	  CommonMethod.testlog("Info","*********** Verifying Respose Body start_date Should Not Be Null **********");
	  Assert.assertTrue(responseBodystart_date!=null); 
	  CommonMethod.testlog("Pass", "Verified successfully" + "<br>" +responseBodystart_date.toString());
	  
	  
	  CommonMethod.testlog("Info","*********** Verifying Respose Body end_date Should Not Be Null **********");
	  Assert.assertTrue(responseBodyend_date!=null); 
	  CommonMethod.testlog("Pass", "Verified successfully" + "<br>" +responseBodyend_date.toString());
	  
	  System.out.println(responseBody.toString());

	  CommonMethod.testlog("Info","***********Verifying calcuated values for 36 months reading **********");
	    for (Object readingData : responseBody)
	    {
	    
	      float reading1 = 200.0f;
	      System.out.println(readingData.toString());    
	      Assert.assertEquals(readingData, reading1);
	    }
    
	CommonMethod.testlog("Pass", "Verified calcuated values for 36 months reading " + "<br>" + responseBody.toString());
	CommonMethod.testlog("Pass", "Verifies response from API" + "<br>" + CommonMethod.res.asString());
	
	
	CommonMethod.testlog("Info", "API responded in " + CommonMethod.responsetime + " Milliseconds");
	CommonMethod.res.then().assertThat().statusCode(200);

}
}