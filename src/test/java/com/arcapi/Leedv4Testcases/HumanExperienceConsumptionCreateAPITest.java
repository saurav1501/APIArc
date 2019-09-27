package com.arcapi.Leedv4Testcases;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.MethodCall;
import com.Utill.Model.MeterPayload;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;
import com.fasterxml.jackson.core.JsonProcessingException;

public class HumanExperienceConsumptionCreateAPITest extends BaseClass {

	@Test(groups="CheckMeter")
	@Parameters({ "SheetName", "ProjectTypeColumn", "rownumber" })
	public void HumanExperienceConsumptionCreateAPI(String SheetName, String ProjectTypeColumn, int rownumber)
	 {


			try {
				int RowNum = 10;
				for (int i = 2; i <= RowNum; i++) {
				payload = MeterPayload.meterData2();
				url = "/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/meters/ID:"
						+ data.getCellData("DataInput", "HumanExperienceMeterID", i) + "/consumption/?recompute_score=false";
				
				CommonMethod.res = MethodCall.POSTRequest(url, payload);

				
				CommonMethod.res.then().assertThat().statusCode(201);

				CommonMethod.fetchedID = CommonMethod.res.path("id").toString();

				data.setCellData("DataInput", "HumanExperiencePK", i, CommonMethod.fetchedID);

				
}
			} catch (JsonProcessingException e) {

				e.printStackTrace();
			}
	}

}