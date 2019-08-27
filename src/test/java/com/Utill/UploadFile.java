package com.Utill;

import java.io.File;

import com.arc.driver.CommonMethod;

public class UploadFile {

	private String agreement;
	private File SoReference;
	public String getAgreement() {
		return agreement;
	}
	public void setAgreement(String agreement) {
		this.agreement = agreement;
	}
	public Object getSoReference() {
		return SoReference;
	}
	public void setSoReference(File file) {
		SoReference = file;
	}

	
	public static UploadFile uploadFile() {
		
		UploadFile upload = new UploadFile();
		
		upload.setAgreement("REGISTRATION");
		//upload.setSoReference(CommonMethod.file);
		
		return upload;
		
	}
	
}
