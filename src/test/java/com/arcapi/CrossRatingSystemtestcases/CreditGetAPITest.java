package com.arcapi.CrossRatingSystemtestcases;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class CreditGetAPITest extends BaseClass {

	@Test(groups="CreditGet")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void CreditGetAPI(String SheetName,String ProjectTypeColumn, int rownumber) throws IOException {

		try {
			url = "/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/actions/";

			CommonMethod.res = MethodCall.GETRequest(url);
			
			Assertion.verifyStatusCode(CommonMethod.res, 403);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}}
