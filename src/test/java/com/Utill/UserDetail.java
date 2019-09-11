package com.Utill;

public class UserDetail {
	
	private String user_email;
	private String app;
	private String upload_category;
	private String user_name;
	private boolean is_granted;
	private String doc_id;
	
	public String getDoc_id() {
		return doc_id;
	}
	public void setDoc_id(String doc_id) {
		this.doc_id = doc_id;
	}
	public String getUpload_category() {
		return upload_category;
	}
	public void setUpload_category(String upload_category) {
		this.upload_category = upload_category;
	}
	public String getUser_email() {
		return user_email;
	}
	public String getApp() {
		return app;
	}
	public void setApp(String app) {
		this.app = app;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public boolean isIs_granted() {
		return is_granted;
	}
	public void setIs_granted(boolean is_granted) {
		this.is_granted = is_granted;
	}
	

}
