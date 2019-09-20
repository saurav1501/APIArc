package com.arcapi.Buildingtestcases;


import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.Utill.MeterData;
import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class CarbonDioxideCreate5YearDataPostTest extends BaseClass {

		@Test(dataProvider="MeterTestDataFiveYear")
		public void CarbonDioxideCreate5YearDataPost(String start_date,String end_date, String reading) throws IOException {
			
			String projectType = data.getCellData(sheetName, "ProjectIDBuildingNone",rowNumTwo);
			String meterID =  data.getCellData("DataInput", "CO2PK", rowNumTwo);
		  	
			MeterData meterData = new MeterData();
			meterData.setStart_date(start_date);
			meterData.setEnd_date(end_date);
			meterData.setReading(reading);
			
			url = "/assets/LEED:"+projectType +"/meters/ID:"+meterID+"/consumption/";

			CommonMethod.res = MethodCall.POSTRequest(url,meterData);
			
			Assertion.verifyStatusCode(	CommonMethod.res, 201);
		}
		
		@DataProvider(name="MeterTestDataFiveYear")
		String [][] getMeterData()
		{
			String meterData[][] = {{"2015-01-01","2016-01-01","200"},{"2016-01-01","2017-01-01","200"},{"2017-01-01","2018-01-01","200"},{"2018-01-01","2019-01-01","200"},{"2019-01-01","2020-01-01","200"}};
			return(meterData);
		

		}
		
	
}

