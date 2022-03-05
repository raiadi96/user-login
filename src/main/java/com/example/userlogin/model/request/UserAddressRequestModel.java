package com.example.userlogin.model.request;

public class UserAddressRequestModel {
	
		private long id;
	  	public String city;
		public String state;
	    public String country;
	    public String pincode;
	    public String type;
	    
	    public String getCity() {
			return city;
		}
		public void setCity(String city) {
			this.city = city;
		}
		public String getState() {
			return state;
		}
		public void setState(String state) {
			this.state = state;
		}
		public String getCountry() {
			return country;
		}
		public void setCountry(String country) {
			this.country = country;
		}
		public String getPincode() {
			return pincode;
		}
		public void setPincode(String pincode) {
			this.pincode = pincode;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public long getId() {
			return id;
		}
		public void setId(long id) {
			this.id = id;
		}
}
