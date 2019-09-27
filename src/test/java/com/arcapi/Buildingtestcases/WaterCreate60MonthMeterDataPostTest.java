package com.arcapi.Buildingtestcases;

import java.io.IOException;

import org.testng.annotations.Test;

import com.Utill.MeterData;
import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;
import com.fasterxml.jackson.core.JsonProcessingException;



public class WaterCreate60MonthMeterDataPostTest extends BaseClass {

	@Test(groups="CheckMeter")
	public void WaterCreate60MonthMeterDataPost()  {
			
		try {
			String projectType = data.getCellData(sheetName, "ProjectIDBuildingNone", rowNumTwo);
			String meterID =  data.getCellData(sheetName, "WaterMeterID", rowNumTwo);
					
			for(int i=2;i<=63;i++) {
				
				String start_date =  data.getCellData("MeterData", "startDate", i);
				String end_date =  data.getCellData("MeterData", "endDate", i);
				String reading =  data.getCellData("MeterData", "reading1", i);
				
				MeterData meterData= new MeterData();
				meterData.setEnd_date(end_date);
				meterData.setStart_date(start_date);
				meterData.setReading(reading);


			url = "/assets/LEED:" +projectType+ "/meters/ID:" +meterID+"/consumption/?recompute_score=false";
			CommonMethod.res = MethodCall.POSTRequest(url, meterData);
			Assertion.verifyStatusCode(CommonMethod.res, 201);	

			}
		} catch (JsonProcessingException e) {
			
			e.printStackTrace();
		}
	}
	   
	/*@DataProvider(name="MeterTestData")
	String [][] getMeterData()
	{
		int getRowCount = data.getRowCount("MeterData");
			
		int getColumnCount = data.getColumnCount("MeterData");
		System.out.println(getRowCount);
		System.out.println(getColumnCount);
		
		String meterData[][]= new String[getRowCount][getColumnCount];
		System.out.println(meterData);
		for(int i=1;i<=getRowCount;i++)
		{
			for(int j=0;j<getColumnCount;j++)
			{
				meterData[ i-1][j]= data.getCellData("MeterData",j,i);
			
			}
			
		}
		
		return(meterData);
	
	    *//************Using JSON Approach*****************************************//*
		
		String meterData[][] = {{"2017-01-01","2017-02-01","100"},{"2017-02-01","2017-03-01","100"},{"2017-03-01","2017-04-01","100"},{"2017-04-01","2017-05-01","100"},
		{"2017-05-01","2017-06-01","100"},{"2017-06-01","2017-07-01","100"},{"2017-07-01","2017-08-01","100"},{"2017-08-01","2017-09-01","100"},
		{"2017-09-01","2017-10-01","100"},{"2017-10-01","2017-11-01","100"},{"2017-11-01","2017-12-01","100"}
		};
		
	
		
	}
	*/
	
	

}