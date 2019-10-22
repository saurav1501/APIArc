package com.arcapi.ScenarioBasedTestcases;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class VerifyGeneralVisitorOccupantDatacoverageAPITest extends BaseClass {
	
	@Test(groups="VerifyDatacoverage")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })

	public void VerifyGeneralVisitorOccupantDatacoverageAPI(String SheetName,String ProjectTypeColumn, int rownumber) {

		url = "/assets/LEED:" +data.getCellData(SheetName, ProjectTypeColumn, rownumber)+"/datacoverage/";
		CommonMethod.res =MethodCall.GETRequest(url);
		
		Assertion.verifyStatusCode(CommonMethod.res, 200);
		
		String energy = CommonMethod.res.path("energy").toString();
		String water = CommonMethod.res.path("water").toString();
		String waste = CommonMethod.res.path("waste").toString();
		String transport = CommonMethod.res.path("transport").toString();
		String satisfaction = CommonMethod.res.path("satisfaction").toString();
		String co2 = CommonMethod.res.path("co2").toString();
		String voc = CommonMethod.res.path("voc").toString();
		
		System.out.println(energy);
		
		double expectedEnergy1 = (180.0*100)/(1*365);
		BigDecimal expectedEnergy2 = new BigDecimal(expectedEnergy1);
		expectedEnergy2 = expectedEnergy2.setScale(2,RoundingMode.HALF_UP);
		System.out.println(expectedEnergy2);
		String expectedEnergy = expectedEnergy2.toString();
		
		double expectedWater1 = (180.0*100)/(1*365);
		BigDecimal expectedWater2 = new BigDecimal(expectedWater1);
		expectedWater2 = expectedWater2.setScale(2,RoundingMode.HALF_UP);
		String expectedWater = expectedWater2.toString();
		
		
		double expectedTransport1 = (2*100.0)/4;
		BigDecimal expectedTransport2 = new BigDecimal(expectedTransport1);
		expectedTransport2 = expectedTransport2.setScale(1, RoundingMode.HALF_UP);
		String expectedTransport = expectedTransport2.toString();

		Assertion.verifyDataContinueOnFaliluare(energy, expectedEnergy);
		Assertion.verifyDataContinueOnFaliluare(water, expectedWater);
		Assertion.verifyDataContinueOnFaliluare(waste, "100");
		Assertion.verifyDataContinueOnFaliluare(co2, "100");
		Assertion.verifyDataContinueOnFaliluare(voc, "100");
		Assertion.verifyDataContinueOnFaliluare(transport, expectedTransport);
		Assertion.verifyDataContinueOnFaliluare(satisfaction, expectedTransport);
		
		softAssert.assertAll();
			
	}

	}

	






