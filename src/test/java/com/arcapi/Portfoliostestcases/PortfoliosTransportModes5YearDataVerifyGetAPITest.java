package com.arcapi.Portfoliostestcases;
import java.io.IOException;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class PortfoliosTransportModes5YearDataVerifyGetAPITest extends BaseClass {
	
	@Test(groups="CheckPortflio")
	public void PortfoliosTransportModes5YearDataVerifyGetAPI()throws IOException {
	
		Object expectedRoute = 4;
		float expAvg = 10.0f;
		url = "/portfolios/ID:" + data.getCellData(sheetName, "PortfolioID", rowNumTwo) + "/analytics/transport/?period=5";

		 CommonMethod.res = MethodCall.GETRequest(url);
		 Assertion.verifyStatusCode(CommonMethod.res, 200); 	 
		 List<Integer> response =  CommonMethod.res.jsonPath().getList("result.responses");
    	   int size = response.size();
    	   CommonMethod.testlog("Info","*Verifying the Size should not more than Five for 5 year period*");
    	   Assert.assertEquals(5, size);
    	   CommonMethod.testlog("Pass","*Verified size is *--> " +response.size());
       	 
    	   for (int i = 0; i < size; i++)
    	      {
    	         if(i==0) {
    	        	
    	        	Object firstIndex =  response.get(0);
    	        	CommonMethod.testlog("Info","*Verifying the Total Number of Occupant submitted the Survey**");
    	        	Assert.assertEquals(expectedRoute, firstIndex);
    	        	CommonMethod.testlog("Pass", "*Verified successfully Occupant submitted the Survey* -->"  + response.get(0).toString()); 	 
    	      }
    	      else
    	      {
    	       
    	        CommonMethod.testlog("Info","*Verifying Previous year Total Number of Occupant submitted the Survey -->*");
    	        int Index =  response.get(i);
    	        Assert.assertEquals(0,Index);
 	        	CommonMethod.testlog("Pass", "Verified successfully Occupant submitted the Survey -->"  + response.get(i).toString()); 
    	  
           }
    	   }
	
    	   List<Object> responsesbus = CommonMethod.res.jsonPath().getList("result.modes.bus");
    	   int bussize = responsesbus.size();
    	   CommonMethod.testlog("Info","*Verifying the Routes Size should not more than Five for  5 year period*");
    	   Assert.assertEquals(5, bussize);
    	   CommonMethod.testlog("Pass","*Verified Routes size is *-->" +responsesbus.size());
      
    	   
    	   for (int i = 0; i <bussize; i++)
 	       {
 	         if(i==0) {
 	        	Object firstIndex =  responsesbus.get(0);
 	        	CommonMethod.testlog("Info","*Verifying Bus Total Sum of Average Distance Travel*");
 	        	Assert.assertEquals(expAvg, firstIndex);
 	        	CommonMethod.testlog("Pass", "*Verified successfully Bus Total Sum of Average Distance Travel is*-->"  + responsesbus.get(0).toString()); 	 
 	         }
 	        else
 	        {
 	            CommonMethod.testlog("Info","*Verifying Bus Total Sum of Average Distance Travel for previous year*");
 	            Object Index =  responsesbus.get(i);
   	            Assert.assertEquals(0,Index);
	        	CommonMethod.testlog("Pass", "*Verified successfully Bus Total Sum of Average Distance Travel for previous year is*-->"  + responsesbus.get(i).toString()); 
 	  
 	        }
 	       }

	
    	   List<Object> responsescar = CommonMethod.res.jsonPath().getList("result.modes.car");
    	   int carsize = responsescar.size();
    	      	   
    	   for (int i = 0; i <carsize; i++)
    	   {
    		   if(i==0){
    	        	Object firstIndex =  responsescar.get(0);
    	        	CommonMethod.testlog("Info","*Verifying Car Total Sum of Average Distance Travel*");
    	        	Assert.assertEquals(expAvg, firstIndex);
    	        	CommonMethod.testlog("Pass", "*Verified successfully Car Total Sum of Average Distance Travel is*-->"  + responsescar.get(0).toString()); 	 
    		     }
    		   else
    	  	     {
    	        CommonMethod.testlog("Info","*Verifying Car Total Sum of Average Distance Travel for previous year*");
    	        Object Index =  responsescar.get(i);
   	            Assert.assertEquals(0,Index);
   	        	CommonMethod.testlog("Pass", "*Verified successfully Verifying Car Total Sum of Average Distance Travel is*-->"  + responsescar.get(i).toString()); 
    	  
           }
 	       }
	
    	   List<Object> responsescar23 = CommonMethod.res.jsonPath().getList("result.modes.car23");
    	   int car23size = responsescar23.size();
    	      	   
    	   for (int i = 0; i <car23size; i++)
 	       {
    		   if(i==0){
    	        	Object firstIndex =  responsescar23.get(0);
    	        	CommonMethod.testlog("Info","*Verifying Car23 Total Sum of Average Distance Travel*");
    	        	Assert.assertEquals(expAvg, firstIndex);
    	        	CommonMethod.testlog("Pass", "*Verified successfully Verifying Car23 Total Sum of Average Distance Travel is*-->"  + responsescar23.get(0).toString()); 	 
    	      }
    		   else {
    	        CommonMethod.testlog("Info","*Verifying Car23 Total Sum of Average Distance Travel*");
    	        Object Index =  responsescar23.get(i);
   	            Assert.assertEquals(0,Index);
   	        	CommonMethod.testlog("Pass", "*Verified successfully Car23 Total Sum of Average Distance Travel is*-->"  + responsescar23.get(i).toString()); 
    	  
               }
 	       }
	
    	   List<Object> responsescars4 = CommonMethod.res.jsonPath().getList("result.modes.cars4");
    	   int car4size = responsescars4.size(); 	   
    	   for (int i = 0; i <car4size; i++)
 	       {
    		   if(i==0) {
    	        	Object firstIndex =  responsescars4.get(0);
    	        	CommonMethod.testlog("Info","*Verifying Car4 Total Sum of Average Distance Travel*");
    	        	Assert.assertEquals(expAvg, firstIndex);
    		   	CommonMethod.testlog("Pass", "*Verified successfully Verifying Car4 Total Sum of Average Distance Travel is*-->"  + responsescars4.get(0).toString()); 	 
    		   }
    		   else
    		   	{
    	        CommonMethod.testlog("Info","*Verifying Car4 Total Sum of Average Distance Travel*");
    	        Object Index =  responsescars4.get(i);
   	            Assert.assertEquals(0,Index);
   	        	CommonMethod.testlog("Pass", "*Verified successfully Car4 Total Sum of Average Distance Travel is*-->"  +  responsescars4.get(i).toString()); 
    	  
           }
    	}
 
    	   List<Object> responsesmotorcycle = CommonMethod.res.jsonPath().getList("result.modes.motorcycle");
    	   int sizeMoterCycle = responsesmotorcycle.size();
    	      	   
    	   for (int i = 0; i <sizeMoterCycle;i++)
 	       {
    		   if(i==0) {
    	        	Object firstIndex =  responsesmotorcycle.get(0);
    	        	CommonMethod.testlog("Info","*Verifying Motorcycle or scooter Total Sum of Average Distance Travel*");
    	        	Assert.assertEquals(expAvg, firstIndex);
    	        	CommonMethod.testlog("Pass", "*Verified successfully Verifying Motorcycle or scooter Total Sum of Average Distance Travel is*-->"  + responsesmotorcycle.get(0).toString()); 	 
    	       }else {
    	   
    	        CommonMethod.testlog("Info","*Verifying Motorcycle or scooter Total Sum of Average Distance Travel*");
    	        Object Index =  responsesmotorcycle.get(i);
   	            Assert.assertEquals(0,Index);
   	        	CommonMethod.testlog("Pass", "*Verified successfully Motorcycle or scooter Total Sum of Average Distance Travel is*-->"  + responsesmotorcycle.get(i).toString()); 
    	  
           }
 	       }
    	   
    	   List<Object> responseswalk = CommonMethod.res.jsonPath().getList("result.modes.walk");
    	   int sizeresponseswalk = responseswalk.size();
    	      	   
    	   for (int i = 0; i <sizeresponseswalk; i++)
 	       {
    		   if(i==0) {
    	        	Object firstIndex =  responseswalk.get(0);
    	        	CommonMethod.testlog("Info","*Verifying Walk Total Sum of Average Distance Travel*");
    	        	Assert.assertEquals(expAvg, firstIndex);
    	        	CommonMethod.testlog("Pass", "*Verified successfully Walk Total Sum of Average Distance Travel is*-->"  + responseswalk.get(0).toString()); 	 
    	      }else {
    	   
    	        CommonMethod.testlog("Info","*Verifying Walk Total Sum of Average Distance Travel*");
    	        Object Index =  responseswalk.get(i);
   	            Assert.assertEquals(0,Index);
   	        	CommonMethod.testlog("Pass", "*Verified successfully Walk Total Sum of Average Distance Travel is*-->"  + responseswalk.get(i).toString()); 
    	  
           }
 	       }
    	   
    	   List<Object> responsestelecommute = CommonMethod.res.jsonPath().getList("result.modes.telecommute");
    	   int sizeresponsestelecommute = responsestelecommute.size();
    	      	   
    	   for (int i = 0;i<sizeresponsestelecommute; i++)
 	       {
    		   if(i==0){
    	        	Object firstIndex =  responsestelecommute.get(0);
    	        	CommonMethod.testlog("Info","*Verifying Telecommute Total Sum of Average Distance Travel*");
    	        	Assert.assertEquals(expAvg, firstIndex);
    	        	CommonMethod.testlog("Pass", "*Verified successfully Telecommute Total Sum of Average Distance Travel is*-->" + responsestelecommute.get(0).toString()); 	 
    	        }else
    	        {
    	         CommonMethod.testlog("Info","*Verifying Telecommute Total Sum of Average Distance Travel*");
    		     Object Index =  responsestelecommute.get(i);
    	         Assert.assertEquals(0,Index);
   	        	 CommonMethod.testlog("Pass", "*Verified successfully Telecommute Total Sum of Average Distance Travel is*-->"  + responsestelecommute.get(i).toString()); 
    	  
                }
    	   
 	       }
    	   List<Object> responsesheavy_rail = CommonMethod.res.jsonPath().getList("result.modes.heavy_rail");
    	   int sizeresponsesheavy_rail = responsesheavy_rail.size();
    	      	   
    	   for (int i = 0; i <sizeresponsesheavy_rail; i++)
 	       {
    		   if(i==0) {
    	        	Object firstIndex =  responsesheavy_rail.get(0);
    	        	CommonMethod.testlog("Info","*Verifying Rapid transit (subway, metro) Total Sum of Average Distance Travel*");
    	        	Assert.assertEquals(expAvg, firstIndex);
    	        	CommonMethod.testlog("Pass", "*Verified successfully Rapid transit (subway, metro) Total Sum of Average Distance Travel is*-->"  + responsesheavy_rail.get(0).toString()); 	 
    	      }else
    	      {
    	        CommonMethod.testlog("Info","*Verifying Rapid transit (subway, metro) Total Sum of Average Distance Travel*");
    		    Object Index =  responsesheavy_rail.get(i);
  	            Assert.assertEquals(0,Index);
   	        	CommonMethod.testlog("Pass", "*Verified successfully Rapid transit (subway, metro) Total Sum of Average Distance Travel is * -->"  + responsesheavy_rail.get(i).toString()); 
    	  
           }
 	       }   
    	   List<Object> responseslight_rail = CommonMethod.res.jsonPath().getList("result.modes.light_rail");
    	   int sizeresponseslight_rail = responseslight_rail.size();
    	      	   
    	   for (int i = 0; i <sizeresponseslight_rail; i++)
 	        {
    		   if(i==0) {
    	        	Object firstIndex =  responseslight_rail.get(0);
    	        	CommonMethod.testlog("Info","*Verifying Light rail Total Sum of Average Distance Travel*");
    	        	Assert.assertEquals(expAvg, firstIndex);
    	        	CommonMethod.testlog("Pass", "*Verified successfully Light rail Total Sum of Average Distance Travel is*-->"  + responseslight_rail.get(0).toString()); 	 
    	      }else
    	      {
    	        CommonMethod.testlog("Info","*Verifying Light rail Total Sum of Average Distance Travel*");
    		    Object Index =  responseslight_rail.get(i);
 	            Assert.assertEquals(0,Index);
  	       
   	        	CommonMethod.testlog("Pass", "*Verified successfully Light rail Total Sum of Average Distance Travel is* -->"  + responseslight_rail.get(i).toString()); 
    	  
           }
 	       }
    		

}
}