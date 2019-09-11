package com.Utill;

public class Notification {
	private boolean score_change_notification;
	private boolean  daily_score_notification;
	private String destination;
	private String stype;
	private String activity;
	
	public String getActivity() {
		return activity;
	}
	public void setActivity(String activity) {
		this.activity = activity;
	}
	public boolean isScore_change_notification() {
		return score_change_notification;
	}
	public void setScore_change_notification(boolean score_change_notification) {
		this.score_change_notification = score_change_notification;
	}
		
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public boolean isDaily_score_notification() {
		return daily_score_notification;
	}
	public void setDaily_score_notification(boolean daily_score_notification) {
		this.daily_score_notification = daily_score_notification;
	}
	public String getStype() {
		return stype;
	}
	public void setStype(String stype) {
		this.stype = stype;
	}

	


}
