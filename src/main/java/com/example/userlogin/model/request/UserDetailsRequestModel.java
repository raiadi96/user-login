package com.example.userlogin.model.request;

import java.util.List;

public class UserDetailsRequestModel {

	private String first_name;
	private String last_name;
	private String email;
	private String password;
	public List<UserAddressRequestModel> addresses;

	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<UserAddressRequestModel> getAddresses() {
		return addresses;
	}
	public void setAddresses(List<UserAddressRequestModel> addresses) {
		this.addresses = addresses;
	}
	
	
}
