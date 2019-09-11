package com.Utill.Model;

import java.io.IOException;
import java.util.HashMap;

import com.arc.driver.BaseClass;

public class ReviewPayload extends BaseClass{
	public static ReviewPayload review ;

	public static HashMap<Object,Object> submitReview(String ProjectType,String ProjectTypeColumn,String SoReferenceSR) throws IOException {	
		String s;
		
		String LeedID = data.getCellData(sheetName, ProjectTypeColumn, rowNumTwo);
		
		if (ProjectType.equalsIgnoreCase("building")) {
			s = "PF901,PF902,PF903,PF904,PF905,PF906";
			
		} else if (ProjectTypeColumn.equalsIgnoreCase("ProjectIDCityLeed")) {
			s = "CTP1PR901L-" + LeedID + ",CTP1PR902L-" + LeedID + ",CTP1PR903L-" + LeedID + ",CTP1PR904L-" + LeedID
					+ ",CTP1PR905L-" + LeedID + ", CTP1PR906L-" + LeedID;
		}
		
		 else if (ProjectTypeColumn.equalsIgnoreCase("ProjectIDCityOther")) {
			 s = "PF901,PF902,PF903,PF904,PF905,PF906";
			}
		
		 else if (ProjectTypeColumn.equalsIgnoreCase("ProjectIDCityNone")) {
			 s = "PF901,PF902,PF903,PF904,PF905,PF906";
			}

		else if (ProjectTypeColumn.equalsIgnoreCase("ProjectIDCommunityLeed")) {
			s = "CMP1PR901L-" + LeedID + ",CMP1PR902L-" + LeedID + ",CMP1PR903L-" + LeedID + ",CMP1PR904L-" + LeedID
					+ ",CMP1PR905L-" + LeedID + ",CMP1PR906L-" + LeedID;
		}
		
		 else if (ProjectTypeColumn.equalsIgnoreCase("ProjectIDCommunityOther")) {
			 s = "PF901,PF902,PF903,PF904,PF905,PF906";
			}
		
		 else if (ProjectTypeColumn.equalsIgnoreCase("ProjectIDCommunityNone")) {
			 s = "PF901,PF902,PF903,PF904,PF905,PF906";
			}

		else {
			s = "MTS2SS103L-" + LeedID + ",MTS2WE103L-" + LeedID + ",MTS2EA102L-" + LeedID + ",MTS2EA105L-" + LeedID
					+ ",MTS2EA109L-" + LeedID + ",MTS2MR105L-" + LeedID + ",MTS2MR106L-" + LeedID + ",MTS2EQ103L-"
					+ LeedID + ",MTS2EQ106L-" + LeedID + ",MTS2EQ108L-" + LeedID;

		}
		
		map = new HashMap<Object,Object>();
		map.put("first_name", "Micheal");
		map.put("last_name", "Schumakar");
		map.put("email", "testuser@gmail.com");
		map.put("street", "123-A/5");
		map.put("city", "Gurgaon");
		map.put("state", "07");
		map.put("country", "IN");
		map.put("zip_code", "122001");
		map.put("company_code", data.getCellData(sheetName, "CompanyCode", rowNumTwo));
		map.put("base_score", "10");
		map.put("energy_score", "40");
		map.put("water_score", "40");
		map.put("waste_score", "40");
		map.put("human_score", "40");
		map.put("transport_score", "40");
		map.put("certification_payterm", "5");
		map.put("annual_payterm", "5");
		map.put("review_type", "certification");
		map.put("certification_level", "gold");
		map.put("certification_type", "building");
		map.put("review_start_date", "2016-04-15");
		map.put("review_expiry_date", "2017-04-14");
		map.put("score_submitted", "87");
		map.put("target_score", "87");
		map.put("target_level", "platinum");
		map.put("soreference", SoReferenceSR);
		map.put("material_code", data.getCellData(sheetName, "MaterialCode", rowNumTwo));
		map.put("unit_price", "0.0456");
		map.put("actual_price", "6059.328");
		map.put("annual_price", "10");
		map.put("quantity", "5000");
		map.put("material_desc", data.getCellData(sheetName, "MaterialDescription", rowNumTwo));
		map.put("creditId", s);
		map.put("paymetric_r","PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0idXRmLTgiPz48UGF5bWV0cmljUmVzcG9uc2U-PFN0YXR1cz5Ub2tlbml6ZWQ8L1N0YXR1cz48WGlQYXlOZXRUcmFuc0lEPjA8L1hpUGF5TmV0VHJhbnNJRD48TWVyY2hhbnRSZWZlcmVuY2VDb2RlIC8-PEF1dGhDb2RlPjA8L0F1dGhDb2RlPjxDYXJkSW5mbz48Q0NUb2tlbj4tRTgwMy0wMDAwLVdZODdUR0cwQlJCQzRZPC9DQ1Rva2VuPjxDQ0V4cGlyYXRpb25EYXRlPjxNb250aD4wMTwvTW9udGg-PFllYXI-MTk8L1llYXI-PC9DQ0V4cGlyYXRpb25EYXRlPjxDVlY-OTk5PC9DVlY-PENDVHlwZT40MDAwPC9DQ1R5cGU-PEJpblJhbmdlVHlwZT40MDAwPC9CaW5SYW5nZVR5cGU-PC9DYXJkSW5mbz48QW1vdW50PjA8L0Ftb3VudD48L1BheW1ldHJpY1Jlc3BvbnNlPg==");
		map.put("paymetric_s","9pVBS5jQtnNbRGzfciD03OoBN7fP1aSFigXogT7LOaw=");
		return map;
		
	}
	
}
