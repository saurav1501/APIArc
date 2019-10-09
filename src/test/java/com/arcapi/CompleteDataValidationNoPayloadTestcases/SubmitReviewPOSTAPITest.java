package com.arcapi.CompleteDataValidationNoPayloadTestcases;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;
import com.jayway.restassured.http.ContentType;

public class SubmitReviewPOSTAPITest extends BaseClass {

	@Test(groups="CheckReview") 
	@Parameters({ "SheetName", "ProjectTypeColumn", "rownumber", "ProjectType", "SoReferenceSR" })
	public void SubmitReviewPOSTAPI(String SheetName, String ProjectTypeColumn, int rownumber, String ProjectType,
			String SoReferenceSR) {

		/*String s;
		String LeedID = data.getCellData(SheetName, ProjectTypeColumn, rownumber);

		if (ProjectType.equalsIgnoreCase("building")) {
			s = "ACS2SS103L-" + LeedID + ",ACS2WE103L-" + LeedID + ",ACS2EA102L-" + LeedID + ",ACS2EA105L-" + LeedID
					+ ",ACS2EA109L-" + LeedID + ",ACS2MR105L-" + LeedID + ",ACS2MR106L-" + LeedID + ",ACS2EQ103L-"
					+ LeedID + ",ACS2EQ106L-" + LeedID + ",ACS2EQ108L-" + LeedID;
		} else if (ProjectType.equalsIgnoreCase("city")) {
			s = "CTP1PR901L-" + LeedID + ",CTP1PR902L-" + LeedID + ",CTP1PR903L-" + LeedID + ",CTP1PR904L-" + LeedID
					+ ",CTP1PR905L-" + LeedID + ",CTP1PR906L-" + LeedID;
		}

		else if (ProjectType.equalsIgnoreCase("community")) {
			s = "CMP1PR901L-" + LeedID + ",CMP1PR902L-" + LeedID + ",CMP1PR903L-" + LeedID + ",CMP1PR904L-" + LeedID
					+ ",CMP1PR905L-" + LeedID + ",CMP1PR906L-" + LeedID;
		}

		else {
			s = "MTS2SS103L-" + LeedID + ",MTS2WE103L-" + LeedID + ",MTS2EA102L-" + LeedID + ",MTS2EA105L-" + LeedID
					+ ",MTS2EA109L-" + LeedID + ",MTS2MR105L-" + LeedID + ",MTS2MR106L-" + LeedID + ",MTS2EQ103L-"
					+ LeedID + ",MTS2EQ106L-" + LeedID + ",MTS2EQ108L-" + LeedID;

		}
*/
		try {
			url = "/assets/LEED:" + data.getCellData(SheetName, ProjectTypeColumn, rownumber) + "/review/";
			CommonMethod.res = MethodCall.POSTRequest(url);
			Assertion.verifyStatusCode(CommonMethod.res, 400);
			CommonMethod.res.then().assertThat().contentType(ContentType.JSON);
		} catch (Exception e) {
			
			e.printStackTrace();
		}

	}

	
}