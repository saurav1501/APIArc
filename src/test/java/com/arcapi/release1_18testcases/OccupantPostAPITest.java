package com.arcapi.release1_18testcases;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

import net.minidev.json.JSONObject;

public class OccupantPostAPITest extends BaseClass {

	@Test(groups="CheckSurvey")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void OccupantPostAPI(String SheetName,String ProjectTypeColumn, int rownumber) throws IOException {

			
		String val[] = {"transport","human"};
		
		for(String value : val) {
			
			JSONObject jsonAsMap = new JSONObject();
			jsonAsMap.put("start_date", "2019-01-01");
			jsonAsMap.put("total_occupant", 200);
			jsonAsMap.put("regular_occupant", 130);
			jsonAsMap.put("visitor_occupant", 70);

			url = "/assets/LEED:"+ data.getCellData(SheetName, ProjectTypeColumn, rownumber)+ "/occupant/"+value+"/";
		
			CommonMethod.res = MethodCall.POSTRequest(url,jsonAsMap);
			Assertion.verifyStatusCode(CommonMethod.res, 201);
	}

	}



}