package com.arcapi.ScenarioBasedTestcases;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

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
	
	Assertion.verifyStatusCode(CommonMethod.res, 200);
	
	String energy = CommonMethod.res.path("energy").toString();
	String water = CommonMethod.res.path("water").toString();
	String waste = CommonMethod.res.path("waste").toString();
	String transport = CommonMethod.res.path("transport").toString();
	String satisfaction = CommonMethod.res.path("satisfaction").toString();
	String co2 = CommonMethod.res.path("co2").toString();
	String voc = CommonMethod.res.path("voc").toString();
	
	
	Assertion.verifyDataContinueOnFaliluare(energy, "0");
	Assertion.verifyDataContinueOnFaliluare(water, "0");
	Assertion.verifyDataContinueOnFaliluare(waste, "0");
	Assertion.verifyDataContinueOnFaliluare(co2, "0");
	Assertion.verifyDataContinueOnFaliluare(voc,"0");
	Assertion.verifyDataContinueOnFaliluare(transport, "0.0");
	Assertion.verifyDataContinueOnFaliluare(satisfaction, "0.0");
	
	softAssert.assertAll();
		
}

}
