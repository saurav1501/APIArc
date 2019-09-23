package com.arcapi.Buildingtestcases;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.Utill.Model.MeterPayload;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class OtherFuelConsumptionUpdateAPITest extends BaseClass {

	@Test(groups="CheckMeter")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void OtherFuelConsumptionUpdateAPI(String SheetName,String ProjectTypeColumn, int rownumber)  {

	      try {
			int RowNum = data.getRowCountbyColNum("DataInput", 0);
			
			  	for (int i =2; i<= RowNum;i++) {

				payload = MeterPayload.meterData2();

				url = "/assets/LEED:" +data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/meters/ID:"
						+ data.getCellData("DataInput", "OtherFuelMeterID", i) + "/consumption/ID:"
						+ data.getCellData("DataInput", "OtherFuelPK", i) + "/";

			CommonMethod.res =MethodCall.PUTRequest(url,payload);
			Assertion.verifyStatusCode(CommonMethod.res, 200);

}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

	

}