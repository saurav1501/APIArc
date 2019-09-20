package com.arcapi.Transittestcases;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class VerifyAgreementDeleteAPITest extends BaseClass {

	@Test(groups="CheckAgreement")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void VerifyAgreementDeleteAPI(String SheetName,String ProjectTypeColumn, int rownumber) throws IOException {
		
		try {
			String arr1[] = {data.getCellData(SheetName, "CreditAgreementDocID1", rownumber),data.getCellData(SheetName, "DocumentAgreement1", rownumber),data.getCellData(SheetName, "FileIDAgreement1", rownumber),data.getCellData(SheetName, "ApplicationIDAgreement1", rownumber)};
			url = "/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/actions/ID:"
					+ arr1[0]
					+ "/uploads/?Description="+value[0]+"&Documenttype=Z02&Documentnumber="
					+ arr1[1]
					+ "&Documentpart=000&Documentversion=00&ApplicationId="
					+ arr1[3] + "&FileId="
					+ arr1[2];
			CommonMethod.res= MethodCall.DELETERequest(url);
			Assertion.verifyStatusCode(CommonMethod.res, 403);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
}