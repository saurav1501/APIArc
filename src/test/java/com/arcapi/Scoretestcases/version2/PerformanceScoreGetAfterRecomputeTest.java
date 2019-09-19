package com.arcapi.Scoretestcases.version2;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class PerformanceScoreGetAfterRecomputeTest extends BaseClass {

	@Test
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void AssetPerformanceScoreListAPI(String SheetName,String ProjectTypeColumn, int rownumber) throws IOException {

		url = "/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/scores/";

		CommonMethod.res= MethodCall.GETRequest(url);
		

		String energy_score = CommonMethod.res.path("energy").toString();
		String water_score = CommonMethod.res.path("water").toString();
		String waste_score = CommonMethod.res.path("waste").toString();
		String humanexp_score = CommonMethod.res.path("human_experience").toString();
		String tansport_score = CommonMethod.res.path("transport").toString();
		String base_score = CommonMethod.res.path("base").toString();
		
		Assert.assertEquals(energy_score, data.getCellData("Score", "EnergyScore", rownumber));
		Assert.assertEquals(water_score, data.getCellData("Score", "WaterScore", rownumber));
		Assert.assertEquals(waste_score, data.getCellData("Score", "WasteScore", rownumber));
		Assert.assertEquals(humanexp_score, data.getCellData("Score", "HumanExpScore", rownumber));
		Assert.assertEquals(tansport_score, data.getCellData("Score", "TransportScore", rownumber));
		Assert.assertEquals(base_score, data.getCellData("Score", "BaseScore", rownumber));
		
		Assertion.verifyStatusCode(CommonMethod.res, 200);
	}

	
}