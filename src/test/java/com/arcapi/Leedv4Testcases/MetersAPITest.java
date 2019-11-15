package com.arcapi.Leedv4Testcases;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class MetersAPITest extends BaseClass {

	@Test(groups="CheckMeter")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void MetersAPI(String SheetName,String ProjectTypeColumn, int rownumber){
		
		try {
			int row = 2;
			url = "/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/meters/?page_size=20";
			CommonMethod.res = MethodCall.GETRequest(url);

			for (int i=0;i<20;i++) {
			
			CommonMethod.fetchedID = CommonMethod.res.path("results.id["+i+"]").toString();
			
			data.setCellData("DataInput", "HumanExperienceMeterID", row, CommonMethod.fetchedID);
			
			CommonMethod.fetchedID = CommonMethod.res.path("results.name["+i+"]").toString();
			
			data.setCellData("DataInput", "HumanExperienceMeterName", row, CommonMethod.fetchedID);
			Assertion.verifyStatusCode(CommonMethod.res, 200);
			
			row++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}