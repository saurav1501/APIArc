package com.arcapi.TrailFlowtestcases.Transit;

import static com.jayway.restassured.RestAssured.given;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;
import com.relevantcodes.extentreports.LogStatus;

import net.minidev.json.JSONObject;

public class VerifyEnergyGraphValuesAPITest extends BaseClass {

	@Test
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void VerifyEnergyGraphValuesAPI(String SheetName,String ProjectTypeColumn, int rownumber) throws IOException {

		String ColName = null;
		CommonMethod.ExtentReportConfig();

		//CommonMethod.GeneratingAuthCodeForLOUser(SheetName, rownumber);
		
		CommonMethod.test = CommonMethod.extent
				.startTest("VerifyEnergyGraphValuesAPI",
						"Verifies Waste Detail")
				.assignCategory("CheckWaste");
		
		System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
		
	    TestName = Thread.currentThread().getStackTrace()[1].getMethodName();
		
			data.setCellData("Report", "TestCaseName", reportrownum, TestName);

       String Energy_Meter_ID = data.getCellData("DataInput", "ExcelTemplateMeterID", rownumber+1);
       
       String Energy_Meter_Name = data.getCellData("DataInput", "ExcelTemplateMeterName", rownumber+1);
       
      String Unit[] = {"kBtu","kWh","MWh","MBtu","GJ"};
       
       for (String unit : Unit) {
    	   
       JSONObject jsonAsMap = new JSONObject();
		jsonAsMap.put("name", Energy_Meter_Name);
		jsonAsMap.put("native_unit", unit);
		jsonAsMap.put("type", "29");

		CommonMethod.res = given().log().all().header("Ocp-Apim-Subscription-Key", CommonMethod.SubscriptionKey)
				.header("Content-type", "application/json").header("Authorization", header).spec(reqSpec)
				.body(jsonAsMap).when()
				.put("/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/meters/ID:"
						+ Energy_Meter_ID + "/")
				.then().extract().response();
		
		CommonMethod.res.then().spec(respSpec);
			
		CommonMethod.res = given().log().all().header("Ocp-Apim-Subscription-Key", CommonMethod.SubscriptionKey)
				.header("Authorization", header).spec(reqSpec).when()
				.get("/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/resampled/"+Energy_Meter_ID+"/").then()
				.extract().response();
		
		CommonMethod.responsetime = CommonMethod.res.getTimeIn(TimeUnit.MILLISECONDS);

		System.out.println(CommonMethod.responsetime);
		
		CommonMethod.testlog("Pass", "Authorization Token generated" + "<br>" + header);

		System.out.println(CommonMethod.res.asString());
		
		 switch(unit){  
		    //Case statements  
		    case "kBtu": ColName = "EnergykBtu";  
		    break;  
		    case "MBtu": ColName = "EnergymBtu";  
		    break;  
		    case "MWh": ColName = "EnergymWh";  
		    break;  
		    case "kWh": ColName = "EnergykWh";  
		    break;  
		    case "GJ": ColName = "EnergyGJ";  
		    break;  
		    //Default case statement  
		    default:System.out.println("Not in List");  
		    }  
		 
		 for(int j=0;j<12;j++) {
			 
				CommonMethod.fetchedID = CommonMethod.res.path("reading["+j+"]").toString();
				
				String reading = CommonMethod.fetchedID.replaceAll(".0$", "");
				System.out.println(reading);
				//data.setCellData("Graphs", "EnergyGJ", j+2 , reading);
				String Act_Reading = data.getCellData("Graphs", ColName, j+2);
				Assert.assertEquals(Act_Reading, reading);
				
				/*CommonMethod.fetchedID = CommonMethod.res.path("results.waste_diverted["+j+"]").toString();
				String wastedivreading = CommonMethod.fetchedID.replaceAll(".0$", "");
				System.out.println(wastedivreading);
				String actual_Reading = data.getCellData("DataInput", "ExcelWasteDivReading", j+2);
				Assert.assertEquals(actual_Reading, wastedivreading);
				}
				CommonMethod.testlog("Pass", "Response received from API" + "<br>" + CommonMethod.res.asString());

				CommonMethod.testlog("Info", "API responded in " + CommonMethod.responsetime + " Milliseconds");
				*/
				CommonMethod.res.then().spec(respSpec);
			}
			
}}}