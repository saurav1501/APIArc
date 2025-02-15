package com.arcapi.Portfoliostestcases;
import java.io.IOException;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;


public class PortfoliosWasteDiversion5YearLBSDataVerifyGetAPITest extends BaseClass {
	
	@Test(groups="CheckPortfolio")
	public void PortfoliosWasteDiversion3YearLBSDataVerifyGetAPI()throws IOException {
	
		  url= "/portfolios/ID:" + data.getCellData(sheetName, "PortfolioID", rowNumTwo) + "/analytics/waste/?period=5";
		
		  CommonMethod.res = MethodCall.GETRequest(url);
		  Assertion.verifyStatusCode(CommonMethod.res, 200);
			
		  List<Object> responseBody = CommonMethod.res.path("diverted");
		  List<Object> responseBodystart_date = CommonMethod.res.path("start_date");
		  List<Object> responseBodyend_date = CommonMethod.res.path("end_date");
		  

		  CommonMethod.testlog("Info","*Verifying the Size of Respose Body should be 61 for 5 years data **");
		  Assert.assertTrue(responseBody!=null);
		  int sizeofReading = responseBody.size();
		  Assert.assertEquals(sizeofReading,61);
		  System.out.println(responseBody.size()); 
		  CommonMethod.testlog("Pass", "*Verified successfully" + "<br>" +responseBody.size());
		  
	 
		  CommonMethod.testlog("Info","** Verifying Respose Body reading Should Not Be Null *");
		  Assert.assertTrue(responseBody!=null); 
	      CommonMethod.testlog("Pass", "Verified successfully" + "<br>" +responseBody.toString());
	      
		  CommonMethod.testlog("Info","*Verifying Respose Body start_date Should Not Be Null *");
		  Assert.assertTrue(responseBodystart_date!=null); 
		  CommonMethod.testlog("Pass", "Verified successfully" + "<br>" +responseBodystart_date.toString());
		  
		  
		  CommonMethod.testlog("Info","* Verifying Respose Body end_date Should Not Be Null *");
		  Assert.assertTrue(responseBodyend_date!=null); 
		  CommonMethod.testlog("Pass", "Verified successfully" + "<br>" +responseBodyend_date.toString());
		  
		  CommonMethod.testlog("Info","*Verifying calcuated values for 60 months reading *");


		    for (Object readingData : responseBody)
		    {
		    
		      float reading1 = 180.0f;
		      System.out.println(readingData.toString());    
		      Assert.assertEquals(readingData, reading1);
		    }
		CommonMethod.testlog("Pass", "Verified successfully" + "<br>" +responseBody.toString());
			
	}
}