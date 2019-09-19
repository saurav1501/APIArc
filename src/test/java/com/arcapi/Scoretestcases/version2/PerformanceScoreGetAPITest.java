package com.arcapi.Scoretestcases.version2;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class PerformanceScoreGetAPITest extends BaseClass {

	@Test
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void AssetPerformanceScoreListAPI(String SheetName,String ProjectTypeColumn, int rownumber) throws IOException {
		url = "/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/scores/";
		
		
		CommonMethod.res= MethodCall.GETRequest(url);
		Assertion.verifyStatusCode(CommonMethod.res, 200);
				
		String energy_score = CommonMethod.res.path("energy").toString();
		String water_score = CommonMethod.res.path("water").toString();
		String waste_score = CommonMethod.res.path("waste").toString();
		String humanexp_score = CommonMethod.res.path("human_experience").toString();
		String tansport_score = CommonMethod.res.path("transport").toString();
		String base_score = CommonMethod.res.path("base").toString();
		
		data.setCellData("Score", "EnergyScore", rownumber, energy_score);
		data.setCellData("Score", "WaterScore", rownumber, water_score);
		data.setCellData("Score", "WasteScore", rownumber, waste_score);
		data.setCellData("Score", "HumanExpScore", rownumber, humanexp_score);
		data.setCellData("Score", "TransportScore", rownumber, tansport_score);
		data.setCellData("Score", "BaseScore", rownumber, base_score);
		
			}

	
}