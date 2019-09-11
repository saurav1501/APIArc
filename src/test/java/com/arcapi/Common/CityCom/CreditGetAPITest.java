package com.arcapi.Common.CityCom;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class CreditGetAPITest extends BaseClass {

	@Test(groups="CheckCredit")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber","CreditColumn" })
	public void CreditGetAPI(String SheetName,String ProjectTypeColumn, int rownumber, String CreditColumn) {

			url = "/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/actions/?document=true";
			CommonMethod.res = MethodCall.GETRequest(url);
			JSONObject jsonObject = new JSONObject(CommonMethod.res.asString());
			JSONArray CreditID = jsonObject.getJSONArray("EtScorecardList");

			for (int index=0; index<CreditID.length(); ++index){
		    JSONObject currentFriend = CreditID.getJSONObject(index);
		    Assertion.verifyStatusCode(CommonMethod.res, 200);
		    String id = currentFriend.getString("CreditId");
		    data.setCellData("Credit", CreditColumn, rownumber, id);
		    rownumber++;  		         
		}
		
	}

}
