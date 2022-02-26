package com.example.userlogin.model.response;

public enum ExceptionResponseEnum {
	
	MISSING_REQUIRED_FIELD("Missing Required Field. Please check the documentation for the requried Field."),
	RECORD_ALREADY_EXISTS("Record already Exists"),
	INTERNAL_SERVER_ERROR("Internal Server Error"),
	NO_RECORD_FOUND("No record Found"),
	AUTHENTICATION_FAILED("Authentication Failed"),
	COULD_NOT_UPDATE_RECORD("Could not Update Record"),
	COULD_NOT_DELETE_RECORD("Could Not Delete Record"),
	EMAIL_ID_NOT_VERIFIED("Email ID Not Verified");
	
	private String message;
	
	ExceptionResponseEnum(String message) {
		this.message = message;
	}
	

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
