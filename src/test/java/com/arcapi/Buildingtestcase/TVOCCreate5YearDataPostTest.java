package com.arcapi.Buildingtestcase;


import static com.jayway.restassured.RestAssured.given;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.Utill.MeterData;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class TVOCCreate5YearDataPostTest extends BaseClass {

		@Test(dataProvider="MeterTestDataFiveYear")
		public void TVOCCreate5YearDataPost(String start_date,String end_date, String reading) throws IOException {

			String projectType = data.getCellData(sheetName, "ProjectIDBuildingNone",rowNumTwo);
			String meterID =  data.getCellData(sheetName, "MeterID", rowNumTwo);
			  	
			MeterData meterData = new MeterData();
			meterData.setStart_date(start_date);
			meterData.setEnd_date(end_date);
			meterData.setReading(reading);
			
			CommonMethod.res = given().log().all()
					.headers(headerMap)
					.header("Authorization", header).spec(reqSpec)
					.body(meterData).when()
				    .post("/assets/LEED:"+projectType +"/meters/ID:"+meterID+"/consumption/").then()
					.extract().response();
					
			CommonMethod.responsetime = CommonMethod.res.getTimeIn(TimeUnit.MILLISECONDS);

			System.out.println(CommonMethod.responsetime);

			CommonMethod.test = CommonMethod.extent
					.startTest("TVOC 5Year Data Post Test" + CommonMethod.getLabel(CommonMethod.responsetime),
							"Verifies List of Assets")
					.assignCategory("CheckAsset");

			System.out.println(CommonMethod.res.asString());
			
			CommonMethod.res.then().assertThat().statusCode(201);
			CommonMethod.testlog("Pass", "Verifies response from API" + "<br>" + CommonMethod.res.asString());
			CommonMethod.testlog("Info", "API responded in " + CommonMethod.responsetime + " Milliseconds");

		}


		@DataProvider(name="MeterTestDataFiveYear")
		String [][] getMeterData()
		{
			
			String meterData[][] = {{"2015-01-01","2016-01-01","150"},{"2016-01-01","2017-01-01","150"},{"2017-01-01","2018-01-01","150"},{"2018-01-01","2019-01-01","150"},{"2019-01-01","2020-01-01","150"}};
				
		
			return(meterData);
		

		}
		
	
}

