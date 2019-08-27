package com.arcapi.Buildingtestcase;

import static com.jayway.restassured.RestAssured.given;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.Utill.WasteMeterData;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;



public class WasteCreate60MonthMeterDataBulkPostTest extends BaseClass {

	@Test(dataProvider="MeterTestData")
	
	public void CreateWasteMeterDataPost(String unit,String start_date,String end_date,String waste_generated,String waste_diverted) throws IOException {
	    test.assignCategory("CheckConsumption");
		
	    String projectType = data.getCellData(sheetName, "ProjectIDBuildingNone",rowNumTwo);
			
		WasteMeterData meterData= new WasteMeterData();
		meterData.setUnit(unit);
		meterData.setStart_date(start_date);
		meterData.setEnd_date(end_date);
		meterData.setWaste_generated(waste_generated);
		meterData.setWaste_diverted(waste_diverted);
		
		CommonMethod.res = given().log().all()
				.headers(headerMap)
				.header("Authorization", header).spec(reqSpec)
				.body(meterData).when()
			    .post("/assets/LEED:" + projectType + "/waste/").then()
				.extract().response();
				
		CommonMethod.responsetime = CommonMethod.res.getTimeIn(TimeUnit.MILLISECONDS);

		log.info(CommonMethod.responsetime);
		
		CommonMethod.testlog("Pass", "Authorization Token generated" + "<br>" + header);

		log.info(CommonMethod.res.asString());
		
		CommonMethod.fetchedID = CommonMethod.res.path("id").toString();
		data.setCellData(sheetName, "WasteID",2, CommonMethod.fetchedID);


		CommonMethod.res.then().assertThat().statusCode(201);
		CommonMethod.testlog("Pass", "Verifies response from API" + "<br>" + CommonMethod.res.asString());
		CommonMethod.testlog("Info", "API responded in " + CommonMethod.responsetime + " Milliseconds");
		
		
	}
	   
	@DataProvider(name="MeterTestData")
	String [][] getMeterData()
	{
		int getRowCount = data.getRowCount("WasteData");
			
		int getColumnCount = data.getColumnCount("WasteData");
		
		System.out.println(getRowCount);
		System.out.println(getColumnCount);
		
		String meterData[][]= new String[getRowCount][getColumnCount];
		
		for(int i=1;i<=getRowCount;i++)
		{
			for(int j=0;j<getColumnCount;j++)
			{
				meterData[i-1][j]= data.getCellData("WasteData",j,i);
			
			}
			
		}
		
		return(meterData);
	
		
	}
	
	

}