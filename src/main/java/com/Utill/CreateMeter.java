package com.Utill;

public class CreateMeter {

	public String name;
	public String native_unit;
	public String type;
    boolean included;
	

	public boolean isIncluded() {
		return included;
	}
	public void setIncluded(boolean included) {
		this.included = included;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNative_unit() {
		return native_unit;
	}
	public void setNative_unit(String native_unit) {
		this.native_unit = native_unit;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
