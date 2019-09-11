package com.Utill.Model;
import java.util.HashMap;
import java.util.Map;
import com.Utill.CityCom;
import com.Utill.CreateMeter;
import com.arc.driver.BaseClass;

public class CityComPayload extends BaseClass{
	public static  CityCom payload;
	public static CreateMeter meterData;
	
public static CityCom checkMeasure(String base) {
		payload = new CityCom();	
		payload.setContent_type("checkbox");
		payload.setData("true");
		payload.setMeasure_id(base);
		return payload;
	}
		
public static Map<Object,Object> uploadMeasure() {
	map = new HashMap<Object,Object>();
	map.put("data", "false");
	map.put("measure_id", "base_score.A.1.1");
	return map;

}

public static CityCom readyReview() {
	payload = new CityCom();
	payload.setStatus("Ready for Review");
	payload.setAssigned_to(data.getCellData(sheetName, "NormalUserName", rowNumTwo));
	return payload;
}

public static CreateMeter createMeter(String type) {
	
	String Unit = null;
			if(type.equals("273") || type.equals("271") || type.equals("272") || type.equals("276") || type.equals("278")) {
				Unit = "person";
			}
			
			if(type.equals("275") || type.equals("274")) {
				Unit = "dollar";
			}
			
			if(type.equals("270")) {
				Unit = "ppm";
			}
			
			if(type.equals("269")) {
				Unit = "miles";
			}
			
			if(type.equals("266")) {
				Unit = "gal";
			}
			
			if(type.equals("277")) {
				Unit = "days";
			}
			
			if(type.equals("268") || type.equals("267") || type.equals("265")) {
				Unit = "tons";
			}	
	meterData = new CreateMeter();
	meterData.setName("TestMeter");
	meterData.setNative_unit(Unit);
	meterData.setType(type);
    return meterData;
}

public static HashMap<Object,Object>emailPayload() {
	map= new HashMap<Object,Object>();
	map.put("emails", data.getCellData(sheetName,"NormalUserName", rowNumTwo));
	return map;
	
}
}