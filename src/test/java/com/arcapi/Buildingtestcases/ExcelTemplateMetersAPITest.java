package com.arcapi.Buildingtestcases;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class ExcelTemplateMetersAPITest extends BaseClass {

	@Test(groups="CheckExcelData")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void ExcelTemplateMetersAPI(String SheetName,String ProjectTypeColumn, int rownumber) throws IOException {
		
		int row = 2;
		url =  "/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/meters/?page_size=20";
		CommonMethod.res = MethodCall.GETRequest(url);
		
		for (int i=0;i<4;i++) {
		
		CommonMethod.fetchedID = CommonMethod.res.path("results.id["+i+"]").toString();
		
		data.setCellData("DataInput", "ExcelTemplateMeterID", row, CommonMethod.fetchedID);
		
        CommonMethod.fetchedID = CommonMethod.res.path("results.name["+i+"]").toString();
		data.setCellData("DataInput", "ExcelTemplateMeterName", row, CommonMethod.fetchedID);		
	    Assertion.verifyStatusCode(CommonMethod.res, 200);
		row++;
		}
	}

}