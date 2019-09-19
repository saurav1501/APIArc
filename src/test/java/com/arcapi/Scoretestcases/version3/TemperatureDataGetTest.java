package com.arcapi.Scoretestcases.version3;

import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

import junit.framework.Assert;

public class TemperatureDataGetTest extends BaseClass {

	@Test
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void TemperatureDataGet(String SheetName,String ProjectTypeColumn, int rownumber) throws IOException, ParseException {

		Double reading = 0.0;
		String reading1 = null;
		
		url = "/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/temperature/list/";

		CommonMethod.res = MethodCall.GETRequest(url);
		
			
		int RowNum = data.getRowCountbyColNum("Score", 5);
		 
		 for(int i=2;i<RowNum;i++) {
			 
			 String a = data.getCellData("Score", "TemperatureReading", i);
			 
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