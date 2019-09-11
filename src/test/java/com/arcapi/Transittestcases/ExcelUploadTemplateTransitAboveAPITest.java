package com.arcapi.Transittestcases;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class ExcelUploadTemplateTransitAboveAPITest extends BaseClass {

	@Test(groups="UploadMeter")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void ExcelUploadTemplateTransitAboveAP(String SheetName,String ProjectTypeColumn, int rownumber) throws IOException {
		url = "/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber)
		+ "/excelupload/uploadS3/";
		
		
		CommonMethod.res = MethodCall.POSTRequestExcelUpload(url, "action_file", CommonMethod.excelfileTransitAbove);
		Assertion.verifyStatusCode(CommonMethod.res , 200);
	}


}