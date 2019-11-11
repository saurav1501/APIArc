package com.arcapi.ScenarioBasedTestcases;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class VerifyDatacoverage2108DataGetAPITest extends BaseClass{
	
	@Test(groups="CheckDatacoverageData")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void VerifyDatacoverage2108DataGetAPI(String SheetName,String ProjectTypeColumn, int rownumber) {

	url = "/assets/LEED:" +data.getCellData(SheetName, ProjectTypeColumn, rownumber)+"/datacoverage/?at=2018-08-02";
	CommonMethod.res =MethodCall.GETRequest(url);
   
	SoftAssert softAssert = new SoftAssert();
	Assertion.verifyStatusCode(CommonMethod.res, 200);
	
	String energy = CommonMethod.res.path("energy.percentage").toString();
	String water = CommonMethod.res.path("water.percentage").toString();
	String waste = CommonMethod.res.path("waste.percentage").toString();
	String transport = CommonMethod.res.path("transport.percentage").toString();
	String humanexperience = CommonMethod.res.path("humanexperience.percentage").toString();
	
	Assertion.verifyDataContinueOnFaliluare(energy, "0");
	Assertion.verifyDataContinueOnFaliluare(water, "0");
	Assertion.verifyDataContinueOnFaliluare(waste, "0");
	Assertion.verifyDataContinueOnFaliluare(transport, "0.0");
	Assertion.verifyDataContinueOnFaliluare(humanexperience,"0.0");

	softAssert.assertAll();
		
}

}
