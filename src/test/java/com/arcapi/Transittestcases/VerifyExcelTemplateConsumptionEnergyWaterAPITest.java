package com.arcapi.Transittestcases;

import java.io.IOException;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class VerifyExcelTemplateConsumptionEnergyWaterAPITest extends BaseClass {

	@Test(groups="CheckMeter")
	@Parameters({ "SheetName", "ProjectTypeColumn", "rownumber" })
	public void VerifyExcelTemplateConsumptionEnergyWaterAPI(String SheetName, String ProjectTypeColumn, int rownumber)
		{

		String ColName = null;
		for (int i = 2; i <= 3; i++) {

			url = "/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/meters/ID:"
					+ data.getCellData("DataInput", "ExcelTemplateMeterID", i) + "/consumption/";
			
			CommonMethod.res = MethodCall.GETRequest(url);
			Assertion.verifyStatusCode(CommonMethod.res, 200);

			String MeterName = data.getCellData("DataInput", "ExcelTemplateMeterName", i);

			if (MeterName.equalsIgnoreCase("UploadWaterMeterTest")) {

				ColName = "ExcelWaterReading";
			}

			else {

				ColName = "ExcelEnergyReading";
			}

			for (int j = 0; j < 12; j++) {
				CommonMethod.fetchedID = CommonMethod.res.path("results.reading[" + j + "]").toString();
				String reading = CommonMethod.fetchedID.replaceAll(".0$", "");
				String Act_Reading = data.getCellData("DataInput", ColName, j + 2);
				Assertion.verifyData(reading, Act_Reading);
			}
		
			 
		}
	}

}