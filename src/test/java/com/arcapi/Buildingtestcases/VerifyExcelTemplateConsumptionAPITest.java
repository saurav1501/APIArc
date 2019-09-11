package com.arcapi.Buildingtestcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class VerifyExcelTemplateConsumptionAPITest extends BaseClass {

	@Test(groups="CheckExcelData")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void VerifyExcelTemplateConsumptionAPI(String SheetName,String ProjectTypeColumn, int rownumber) throws IOException {

		String ColName = null;		
        int RowNum = 5;
		
		for (int i =2; i<= RowNum;i++) {
			
		url = "/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/meters/ID:"
					+ data.getCellData("DataInput", "ExcelTemplateMeterID", i) + "/consumption/";

		CommonMethod.res = MethodCall.GETRequest(url);
		String MeterName = data.getCellData("DataInput", "ExcelTemplateMeterName", i);
		
		if(MeterName.equalsIgnoreCase("UploadWaterMeterTest")) {
			
			ColName = "ExcelWaterReading";
		}
		
        else if(MeterName.equalsIgnoreCase("EnergyFileUploadTestMeter")) {
			
			ColName = "ExcelEnergyReading";
		}
		
		else if(MeterName.equalsIgnoreCase("Default TVOC Meter")) {
			
			ColName = "ExcelTVOCReading";
		}
		
        else  {
			
			ColName = "ExcelCO2Reading";
		}
		
		for(int j=0;j<12;j++) {
		CommonMethod.fetchedID = CommonMethod.res.path("results.reading["+j+"]").toString();
		String reading = CommonMethod.fetchedID.replaceAll(".0$", "");
		String Act_Reading = data.getCellData("DataInput", ColName, j+2);
		Assert.assertEquals(Act_Reading, reading);
		}
		Assertion.verifyStatusCode(CommonMethod.res, 200);
	}
	}
	
}