package com.arcapi.Common.CityCom;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.MeterData;
import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;
import com.fasterxml.jackson.core.JsonProcessingException;

public class ConsumptionCreateAPITest extends BaseClass {

	@Test(groups="CheckMeter")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void ConsumptionCreatePOSTAPI(String SheetName,String ProjectTypeColumn, int rownumber){
		
		try {
			int RowNum = 14;
			for (int i =2; i<= RowNum;i++) {
			String Reading = data.getCellData("DataInput", "CityMeterReading", i);
			
			MeterData meterData = new MeterData();
			meterData.setStart_date( "2018-01-01");
			meterData.setEnd_date("2019-01-31");
			meterData.setReading(Reading);
			url = "/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/meters/ID:" + data.getCellData("DataInput", "CityMeterID", i) + "/consumption/";
			CommonMethod.res = MethodCall.POSTRequest(url, meterData);
			
			Assertion.verifyStatusCode(CommonMethod.res, 201);
			CommonMethod.fetchedID = CommonMethod.res.path("id").toString();
			data.setCellData("DataInput", "CityMeterPK", i, CommonMethod.fetchedID);

}
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
}
