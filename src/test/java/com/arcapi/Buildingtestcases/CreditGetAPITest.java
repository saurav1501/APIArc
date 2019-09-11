package com.arcapi.Buildingtestcases;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class CreditGetAPITest extends BaseClass {

	@Test(groups="CheckCredit")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void CreditGetAPI(String SheetName,String ProjectTypeColumn, int rownumber) throws IOException {

		url = "/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/actions/?document=true";

		CommonMethod.res = MethodCall.GETRequest(url);
			
		JSONObject jsonObject = new JSONObject(CommonMethod.res.asString());
		JSONArray CreditID = jsonObject.getJSONArray("EtScorecardList");
		for (int index=0; index<CreditID.length(); ++index){
		        JSONObject currentFriend = CreditID.getJSONObject(index);
		        String id = currentFriend.getString("CreditId");
		        data.setCellData(SheetName, "CreditID", rownumber, id);
		       
		        
		}
		
	}


}
