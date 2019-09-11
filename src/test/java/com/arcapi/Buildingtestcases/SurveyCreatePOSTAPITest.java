package com.arcapi.Buildingtestcases;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Utill.Controller.Assertion;
import com.Utill.Controller.MethodCall;
import com.Utill.Model.MeterPayload;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class SurveyCreatePOSTAPITest extends BaseClass {

	@Test(groups = "CheckSurvey")
	@Parameters({ "SheetName","ProjectTypeColumn","rownumber" })
	public void SurveyCreatePOSTAPI(String SheetName,String ProjectTypeColumn, int rownumber)throws IOException{
		
		url ="/assets/LEED:" + data.getCellData(sheetName, ProjectTypeColumn, rownumber) +"/survey/?key="+ data.getCellData(sheetName, "BuildingKeyID", rownumber);		
		List<String> str = Arrays.asList("dirty","views to outdoors","sound","privacy", "smelly", "air quality","cleanliness","stuffy","loud","hot","cold","dark","glare","drafty","bright","humid","thermal comfort","light","daylight",""); 
		for(int value=-3;value<4;value++) {
	    for(String compl : str) {
	    payload = MeterPayload.surveyMode();
		Map<Object,Object> surveyDetails = new HashMap<Object, Object>();	
		if (value==0) {
        	CommonMethod.testlog("Info", "Submiting regular occupant Survey");
            surveyDetails.put("tenant_name", "Saurav Sinha");
    		surveyDetails.put("response_method", "web");
    		surveyDetails.put("satisfaction",value);
    		surveyDetails.put("location","Delhi");
    		surveyDetails.put("occupant_category", "regular_occupant");
    		surveyDetails.put("other_complaint", "Test Completent");
    		surveyDetails.put("language","English");
    		surveyDetails.put("routes",Arrays.asList(payload));
    		surveyDetails.put("feedbacks", "[]");	
    		CommonMethod.testlog("Pass", "Submitted visitor regular Survey Successfully");
        }
        else {
        	CommonMethod.testlog("Info", "Submiting visitor occupant Survey");
            surveyDetails.put("tenant_name", "Saurav Sinha");
    		surveyDetails.put("response_method", "web");
    		surveyDetails.put("satisfaction",value);
    		surveyDetails.put("location","Delhi");
    		surveyDetails.put("occupant_category","visitor_occupant");
    		surveyDetails.put("other_complaint", "Test Completent");
    		surveyDetails.put("language","English");
    		surveyDetails.put("routes",Arrays.asList(payload));
    		surveyDetails.put("feedbacks", "['"+compl+"']");
    		CommonMethod.testlog("Pass", "Submitted visitor occupant Survey Successfully");
        }
        CommonMethod.res = MethodCall.POSTRequest(url,surveyDetails);
		if(value==0 || value<0 && (compl.equals("sound") || compl.equals("privacy") || compl.equals("views to outdoors") || compl.equals("dirty") || compl.equals("smelly") || compl.equals("stuffy") || compl.equals("loud") || compl.equals("cold") || compl.equals("hot") || compl.equals("dark") || compl.equals("glare") || compl.equals("drafty") || compl.equals("bright") || compl.equals("humid")) || 
		 value>0 && (compl.equals("sound") || compl.equals("privacy") || compl.equals("views to outdoors") || compl.equals("air quality") || compl.equals("cleanliness") || compl.equals("thermal comfort") || compl.equals("light") || compl.equals("daylight"))) {
		
		Assertion.verifyStatusCode(CommonMethod.res, 201);
		}
		
		else {
			Assertion.verifyStatusCode(CommonMethod.res, 400);
		}
	    }
        }
		}
	
}