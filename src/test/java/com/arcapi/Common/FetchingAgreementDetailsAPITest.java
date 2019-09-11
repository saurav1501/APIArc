package com.arcapi.Common;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class FetchingAgreementDetailsAPITest extends BaseClass {

	@Test(groups="CheckAgreement")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void FetchingAgreementDetailsAPI(String SheetName,String ProjectTypeColumn, int rownumber) throws IOException {

		try {
			url = "/assets/LEED:" +data.getCellData(SheetName, ProjectTypeColumn, rownumber)+"/documents/";		
			CommonMethod.res = MethodCall.GETRequest(url);
			String Creditagreement1 = CommonMethod.res.path("EtFile.ObjectId[0]").toString();
			data.setCellData(SheetName, "CreditAgreementDocID1", rownumber, Creditagreement1);
			String DocNumberAg1 = CommonMethod.res.path("EtFile.Documentnumber[0]").toString();
			data.setCellData(SheetName, "DocumentAgreement1", rownumber, DocNumberAg1);
			String FileIDAg1 = CommonMethod.res.path("EtFile.FileId[0]").toString();
			data.setCellData(SheetName, "FileIDAgreement1", rownumber, FileIDAg1);
			String AppIDAg1 = CommonMethod.res.path("EtFile.ApplicationId[0]").toString();
			data.setCellData(SheetName, "ApplicationIDAgreement1", rownumber, AppIDAg1);
			Assertion.verifyStatusCode(CommonMethod.res , 200);
		} catch (Exception e) {
			e.printStackTrace();
		}
        
	}
	
}