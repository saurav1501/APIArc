package com.arcapi.Buildingtestcase;

import static com.jayway.restassured.RestAssured.given;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.Utill.MeterData;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;



public class WaterCreate60MonthMeterDataPostTest extends BaseClass {

	@Test(dataProvider="MeterTestData")
	
	public void WaterCreate60MonthMeterDataPost(String end_date,String reading,String start_date) throws IOException {
				
		String projectType = data.getCellData(sheetName, "ProjectIDBuildingNone", rowNumTwo);
		String meterID =  data.getCellData(sheetName, "WaterMeterID", rowNumTwo);
				
		MeterData meterData= new MeterData();
		
		meterData.setEnd_date(end_date);
		meterData.setStart_date(start_date);
		meterData.setReading(reading);

	
		CommonMethod.res = given().log().all().headers(headerMap).header("Authorization", header).spec(reqSpec)
				.body(meterData).when().post("/assets/LEED:" +projectType+ "/meters/ID:"
						+meterID+"/consumption/")
				.then().extract().response();

		CommonMethod.responsetime = CommonMethod.res.getTimeIn(TimeUnit.MILLISECONDS);
		
		System.out.println(CommonMethod.responsetime);

		CommonMethod.test = CommonMethod.extent
				.startTest("Create 60Months Meter Data Post API Test" + CommonMethod.getLabel(CommonMethod.responsetime),
						"Verifies consumption creation")
				.assignCategory("CheckConsumption");

		CommonMethod.testlog("Pass", "Authorization Token generated" + "<br>" + header);

		System.out.println(CommonMethod.res.asString());
		CommonMethod.fetchedID = CommonMethod.res.path("id").toString();
		data.setCellData(sheetName, "PK", rowNumTwo, CommonMethod.fetchedID);
		
		CommonMethod.res.then().assertThat().statusCode(201);
		CommonMethod.testlog("Pass", "Verifies response from API" + "<br>" + CommonMethod.res.asString());
		CommonMethod.testlog("Info", "API responded in " + CommonMethod.responsetime + " Milliseconds");
		

		
	}
	   
	@DataProvider(name="MeterTestData")
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
	
	    /************Using JSON Approach*****************************************//*
		
		String meterData[][] = {{"2017-01-01","2017-02-01","100"},{"2017-02-01","2017-03-01","100"},{"2017-03-01","2017-04-01","100"},{"2017-04-01","2017-05-01","100"},
		{"2017-05-01","2017-06-01","100"},{"2017-06-01","2017-07-01","100"},{"2017-07-01","2017-08-01","100"},{"2017-08-01","2017-09-01","100"},
		{"2017-09-01","2017-10-01","100"},{"2017-10-01","2017-11-01","100"},{"2017-11-01","2017-12-01","100"}
		};
		*/
	
		
	}
	
	
	

}