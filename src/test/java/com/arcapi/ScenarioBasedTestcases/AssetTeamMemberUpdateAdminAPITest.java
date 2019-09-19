package com.arcapi.ScenarioBasedTestcases;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;
import com.jayway.restassured.http.ContentType;

import net.minidev.json.JSONObject;

public class AssetTeamMemberUpdateAdminAPITest extends BaseClass {

	@Test(groups="CheckTeam")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void AssetTeamMemberUpdateAdminAPI(String SheetName,String ProjectTypeColumn, int rownumber) throws IOException {
		
		String Role[] = {"ZRPO80","ZRPO81"};
		
		for (String str : Role) {

		JSONObject jsonAsMap = new JSONObject();
		jsonAsMap.put("user_email", data.getCellData(SheetName, "NormalUserName", rownumber));
		jsonAsMap.put("Reltyp", str);
		jsonAsMap.put("Responsibility", data.getCellData(SheetName, ProjectTypeColumn, rownumber));

		url= "/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/teams/update/";

		CommonMethod.res = MethodCall.PUTRequest(url,jsonAsMap);

		
	  }

	Assertion.verifyStatusCode(CommonMethod.res, 403);
	CommonMethod.res.then().assertThat().contentType(ContentType.JSON);
}
	
}