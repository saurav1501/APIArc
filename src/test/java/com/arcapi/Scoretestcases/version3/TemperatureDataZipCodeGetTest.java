package com.arcapi.Scoretestcases.version3;

import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

import junit.framework.Assert;

public class TemperatureDataZipCodeGetTest extends BaseClass {

	@Test
	public void TemperatureDataZipCodeGet() throws IOException, ParseException {

		Double reading = 0.0;
		String reading1 = null;
		
		url = "/assets/ZIP:" + CommonMethod.ZipCode + "/temperature/list/";

		CommonMethod.res =MethodCall.GETRequest(url);
		Assertion.verifyStatusCode(CommonMethod.res, 200);
		
		int RowNum = data.getRowCountbyColNum("Score", 6);
		 
		 for(int i=2;i<RowNum;i++) {
			 
			 String a = data.getCellData("Score", "TemperatureZipReading", i);
			 
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
	
}

	

}