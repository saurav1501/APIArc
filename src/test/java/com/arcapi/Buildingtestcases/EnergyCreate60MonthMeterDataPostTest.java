package com.arcapi.Buildingtestcases;

import java.io.IOException;

import org.testng.annotations.Test;

import com.Utill.MeterData;
import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;



public class EnergyCreate60MonthMeterDataPostTest extends BaseClass {

	@Test(groups="CheckMeter")
	public void EnergyCreate60MonthMeterDataPost() throws IOException {
			
		String projectType = data.getCellData(sheetName, "ProjectIDBuildingNone", rowNumTwo);
		String meterID =  data.getCellData(sheetName, "MeterID", rowNumTwo);
		
		for(int i=2;i<=63;i++) {
		
		String start_date =  data.getCellData("MeterData", "startDate", i);
		String end_date =  data.getCellData("MeterData", "endDate", i);
		String reading =  data.getCellData("MeterData", "reading1", i);
		
		MeterData meterData= new MeterData();
		meterData.setEnd_date(end_date);
		meterData.setStart_date(start_date);
		meterData.setReading(reading);
	
		url = "/assets/LEED:" + projectType + "/meters/ID:" + meterID + "/consumption/";
		
		CommonMethod.res = MethodCall.POSTRequest(url, meterData);
		Assertion.verifyStatusCode(CommonMethod.res, 201);
		}
	}
	   
	/*@DataProvider(name="MeterTestData")
	String [][] getMeterData()
	{
		int getRowCount = data.getRowCount("MeterData");
			
		int getColumnCount = data.getColumnCount("MeterData");
			
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
		
	}
	
*/	
	

}