package com.Utill.Model;

import java.util.HashMap;

import com.Utill.Certification;
import com.Utill.ExcelUpload;
import com.Utill.Notification;
import com.Utill.UserDetail;
import com.arc.driver.BaseClass;
import com.arc.driver.CommonMethod;

public class OtherDetails extends BaseClass{
	static UserDetail details;
	static Notification notification;

	public static HashMap<Object,Object> detalis() {
	map = new HashMap<Object,Object>();
	map.put("user_email",data.getCellData(sheetName, "AssetTeamMember", rowNumTwo));
	map.put("Reltyp", "ZRPO81");
	map.put("Responsibility", projectID);
	return map;
	}
	
	
	public static HashMap<Object,Object> nonRegdetalis() {
		map = new HashMap<Object,Object>();
		map.put("user_email","ttttttttttttttttttttttttttt@gmail.com");
		map.put("Reltyp", "ZRPO81");
		map.put("Responsibility", projectID);
		return map;
		}
	
	public static HashMap<Object,Object> detalisManager() {
	map = new HashMap<Object,Object>();
	map.put("user_email",data.getCellData(sheetName, "STG03", rowNumTwo));
	map.put("Reltyp", "ZRPO82");
	map.put("Responsibility", projectID);
	return map;
	}
	
	

	public static HashMap<Object,Object> arcAdmindetalis() {
	map = new HashMap<Object,Object>();
	map.put("user_email",data.getCellData(sheetName, "AssetTeamMember", rowNumTwo));
	map.put("Reltyp", "ZRPO80");
	map.put("Responsibility", projectID);
	return map;
	}
	
	public static HashMap<Object,Object> detalisUpdate() {
		map = new HashMap<Object,Object>();
		map.put("user_email",data.getCellData(sheetName, "TeamMemberUpdate", rowNumTwo));
		map.put("Reltyp", "ZRPO81");
		map.put("Responsibility", projectID);
		return map;
			
	}
	
	public static HashMap<Object,Object> detalisInvalid() {
		map = new HashMap<Object,Object>();
		map.put("user_email",data.getCellData(sheetName, "NormalUserName", rowNumTwo));
		map.put("Reltyp", "ZRPO80");
		map.put("Responsibility", projectID);
		return map;
			
	}
	public static UserDetail Dropbox() {
		details = new UserDetail();
		details.setApp("2");
		return details;
	}
	public static UserDetail OneDrive() {
		details = new UserDetail();
		details.setApp("3");
		return details;
	}
	public static UserDetail GoogleDrive() {
		details = new UserDetail();
		details.setApp("4");
		return details;
	}
	public static UserDetail excelUpload() {
		details = new UserDetail();
		details.setUpload_category(CommonMethod.SubscriptionKey);
		return details;
	}
	public static UserDetail teamPermission() {
		details = new UserDetail();
		details.setUser_name(data.getCellData(sheetName, "TeamPermissionEmail", rowNumTwo));
		details.setIs_granted(true);
		return details;
	}
	
	public static UserDetail teamPermissionPost() {
		details = new UserDetail();
		details.setUser_name(data.getCellData(sheetName, "TeamPermissionEmail", rowNumTwo));
		return details;
	}
	
	public static Notification notification() {
		notification = new Notification();
		notification.setDestination(data.getCellData(sheetName, "NormalUserName", rowNumTwo));
		notification.setDaily_score_notification(false);
		notification.setScore_change_notification(true);
		notification.setStype("email");
		notification.setActivity("data_input_aqi");
		return notification;
	
	}
	
	
	public static Notification notificationPut() {
		notification = new Notification();
		notification.setDestination(data.getCellData(sheetName, "NormalUserName", rowNumTwo));
		notification.setDaily_score_notification(false);
		notification.setStype("email");
		return notification;
	
	}
	public static Notification activity(){
		notification = new Notification();
		notification.setActivity("skipped_payment");
		return notification;
	}
	
	public static Certification addPoint() {
		Certification certification = new Certification();
		certification.setCertification_level("platinum");
		certification.setCertification_points("78");
		certification.setDate_certified("2018-05-11");
		certification.setRating_system("84");	
		return certification;
		
		
	}
	
	public static UserDetail uploadS3() {
		details = new UserDetail();
	    details.setDoc_id(data.getCellData(sheetName, "S3DocID", rowNumTwo));
		return details;
	}
	
	public static HashMap<Object,Object> submitFeedback() {
	map = new HashMap<>();
	map.put("OSName", "Linux");
	map.put("browser_name", "Chrome");
	map.put("browser_version", "53");
	map.put("description", "hello world");
	map.put("informative_content", "sad");
	map.put("performance_speed", "happy");
	map.put("well_organized", "satisfied");
	map.put("user_name", data.getCellData(sheetName, "NormalUserName", rowNumTwo));
	return map;
}
	
	public static ExcelUpload uploadExcel() {
		ExcelUpload upload = new ExcelUpload();
		upload.setUpload_category("excel");
		return upload;
	}
}