package com.Utill.Controller;



import com.arc.driver.BaseClass;

public class URL extends BaseClass {

	public static final String URL = "http://localhost:8080";
	
	public static String getEndPoint(){
		log.info("Base URI : " + URL);
		return URL; 
	}
	
	public static String getEndPoint(String resource){
		log.info("URI End Point : " + URL + resource);
		return URL + resource;
	}	
}
