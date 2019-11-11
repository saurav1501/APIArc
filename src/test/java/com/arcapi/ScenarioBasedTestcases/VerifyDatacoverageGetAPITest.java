package com.arcapi.ScenarioBasedTestcases;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class VerifyDatacoverageGetAPITest extends BaseClass{
	
	@Test(groups="CheckDatacoverageData")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void VerifyDatacoverageGetAPI(String SheetName,String ProjectTypeColumn, int rownumber) {

	url = "/assets/LEED:" +data.getCellData(SheetName, ProjectTypeColumn, rownumber)+"/datacoverage/";
	CommonMethod.res =MethodCall.GETRequest(url);
	
	Assertion.verifyStatusCode(CommonMethod.res, 200);
	
	String energy = CommonMethod.res.path("energy.percentage").toString();
	String water = CommonMethod.res.path("water.percentage").toString();
	String waste = CommonMethod.res.path("waste.percentage").toString();
	String transport = CommonMethod.res.path("transport.percentage").toString();
	String humanexperience = CommonMethod.res.path("humanexperience.percentage").toString();

	
	double expectedEnergy1 = (180.0*100)/(1*365);
	BigDecimal expectedEnergy2 = new BigDecimal(expectedEnergy1);
	expectedEnergy2 = expectedEnergy2.setScale(2,RoundingMode.HALF_UP);
	System.out.println(expectedEnergy2);
	String expectedEnergy = expectedEnergy2.toString();
	
	double expectedWater1 = (180.0*100)/(1*365);
	BigDecimal expectedWater2 = new BigDecimal(expectedWater1);
	expectedWater2 = expectedWater2.setScale(2,RoundingMode.HALF_UP);
	String expectedWater = expectedWater2.toString();
	
	
	double expectedTransport1 = (2*100.0)/3;
	BigDecimal expectedTransport2 = new BigDecimal(expectedTransport1);
	expectedTransport2 = expectedTransport2.setScale(2, RoundingMode.HALF_UP);
	String expectedTransport = expectedTransport2.toString();
	
	double transport1 = Double.parseDouble(transport);
	double Humanexperience = (200.0 + transport1)/3;
	BigDecimal expectedHumanexperience = new BigDecimal(Humanexperience);
	expectedHumanexperience = expectedHumanexperience.setScale(2, RoundingMode.HALF_UP);
	String expectedHumanexperience1 = expectedHumanexperience.toString();

	
	Assertion.verifyDataContinueOnFaliluare(energy, expectedEnergy);
	Assertion.verifyDataContinueOnFaliluare(water, expectedWater);
	Assertion.verifyDataContinueOnFaliluare(waste, "100");
	Assertion.verifyDataContinueOnFaliluare(humanexperience, expectedHumanexperience1);
	Assertion.verifyDataContinueOnFaliluare(transport, expectedTransport);
	
	softAssert.assertAll();
		
}

}
