package com.arcapi.ScenarioBasedTestcases;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

import net.minidev.json.JSONObject;

public class AssetTeamMemberUpdateAPITest extends BaseClass {

	@Test(groups="CheckTeam")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber","UserName" })
	public void AssetTeamMemberUpdateAPI(String SheetName,String ProjectTypeColumn, int rownumber,String UserName)  {

	    String Role[] = {"ZRPO80","ZRPO81"};
		
		for (String str : Role) {

		JSONObject jsonAsMap = new JSONObject();
		jsonAsMap.put("user_email", data.getCellData(SheetName, UserName, rownumber));
		jsonAsMap.put("Reltyp", str);
		jsonAsMap.put("Responsibility", data.getCellData(SheetName, ProjectTypeColumn, rownumber));

		url = "/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/teams/update/";

		CommonMethod.res = MethodCall.PUTRequest(url,jsonAsMap);
		Assertion.verifyStatusCode(CommonMethod.res, 403);

		}
}}