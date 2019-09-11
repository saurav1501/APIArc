package com.Utill.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.Utill.CreateMeter;
import com.Utill.MeterData;
import com.Utill.SurveyModes;
import com.Utill.WasteMeterData;
import com.arc.driver.BaseClass;

import net.minidev.json.JSONObject;

public class MeterPayload extends BaseClass{
	
	private static final String jsonAsMap1 = null;
	public static CreateMeter meterData() {
	CreateMeter meterData = new CreateMeter();
	meterData.setName("ElectricityPurchasedFromGridMeter");
	meterData.setNative_unit("kBtu");
	meterData.setType("37");
    return meterData;

}
	public static CreateMeter meterUpdate() {
		CreateMeter meterData = new CreateMeter();
		meterData.setName("ElectricityUpdateMeter");
		meterData.setNative_unit("kBtu");
		meterData.setType("37");
	    return meterData;
	}
	
	public static ArrayList<JSONObject> meterData1() {
		/*MeterData meterData = new MeterData();
		meterData.setStart_date("2018-11-02");
		meterData.setEnd_date("2018-12-04");
		meterData.setReading("120");*/
		
		JSONObject jsonAsMap1 = new JSONObject();
		jsonAsMap1.put("start_date", "2017-11-02");
		jsonAsMap1.put("end_date", "2017-11-04");
		jsonAsMap1.put("reading", "120");
		
		JSONObject jsonAsMap2 = new JSONObject();
		jsonAsMap2.put("start_date", "2017-12-02");
		jsonAsMap2.put("end_date", "2017-12-04");
		jsonAsMap2.put("reading", "200");
		
		ArrayList<JSONObject> list = new ArrayList<JSONObject>();
        list.add(jsonAsMap1);
        list.add(jsonAsMap2);
		return list;
	}
	
	public static MeterData meterData2() {
		MeterData meterData = new MeterData();
		meterData.setStart_date("2019-01-01");
		meterData.setEnd_date("2019-02-01");
		meterData.setReading("120");
		return meterData;
	}
	public static MeterData meterData3() {
		MeterData meterData = new MeterData();
		meterData.setStart_date("2017-01-01");
		meterData.setEnd_date("2017-02-01");
		meterData.setReading("60");
		return meterData;
	}
	
	public static ArrayList<JSONObject> wasteMeter() {
	JSONObject jsonAsMap = new JSONObject();
	jsonAsMap.put("start_date", "2017-01-06");
	jsonAsMap.put("end_date", "2017-01-07");
	jsonAsMap.put("reading", "100");
	jsonAsMap.put("unit", "US tons");
	JSONObject jsonAsMap1 = new JSONObject();
	jsonAsMap1.put("start_date", "2017-01-07");
	jsonAsMap1.put("end_date", "2017-01-08");
	jsonAsMap1.put("reading", "200");
	jsonAsMap1.put("unit", "US tons");
	ArrayList<JSONObject> list = new ArrayList<JSONObject>();
    list.add(jsonAsMap);
    list.add(jsonAsMap1);
	return list;
}
	public static WasteMeterData wasteMeter1() {
	WasteMeterData meterData= new WasteMeterData();
	meterData.setUnit("lbs");
	meterData.setStart_date("2018-01-01");
	meterData.setEnd_date("2018-02-01");
	meterData.setWaste_generated("100");
	meterData.setWaste_diverted("99");
	return meterData;
}
	
	public static WasteMeterData wasteMeter2() {
		WasteMeterData meterData= new WasteMeterData();
		meterData.setUnit("lbs");
		meterData.setStart_date("2016-01-01");
		meterData.setEnd_date("2016-02-01");
		meterData.setWaste_generated("100");
		meterData.setWaste_diverted("99");
		return meterData;
	}
	
	public static SurveyModes surveyMode() {
		SurveyModes survey = new SurveyModes();
		survey.setBike(modeValue1);
		survey.setBus(modeValue1);
		survey.setCar(modeValue1);
		survey.setCar23(modeValue1);
		survey.setCars4(modeValue1);
		survey.setLight_rail(modeValue1);
		survey.setHeavy_rail(modeValue1);
		survey.setMotorcycle(modeValue1);
		survey.setWalk(modeValue1);
		survey.setTelecommute(modeValue1);
		return survey;
	}
	public static  HashMap<Object,Object> submitSurveyV2(){
		map =new HashMap<>();
		payload = MeterPayload.surveyMode();
		map.put("tenant_name", "Saurav Sinha");
		map.put("response_method","web");
		map.put("routes",payload);
		map.put("satisfaction", "2");
		map.put("location", "Gurgaon");
		map.put("complaints", "[]");
		map.put("other_complaint", "Other complaint");
		
		return map;
	}
	public static  HashMap<Object,Object> submitSurveyV2Old(){
		map =new HashMap<>();
		payload = MeterPayload.surveyMode();
		map.put("tenant_name", "Saurav Sinha");
		map.put("response_method","web");
		map.put("routes", Arrays.asList(payload));	
		return map;
	}
}