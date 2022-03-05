package com.example.userlogin.io.entity;

import java.io.Serializable;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.example.userlogin.dto.UserDto;

@Entity(name = "addresses")
public class AddressEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9101570768161344526L;

	@Id
	@GeneratedValue
	public long id;

	@Column(length = 255, nullable = false)
	private String addressId;

	@Column(length = 30, nullable = false)
	public String city;

	@Column(length = 30, nullable = false)
	public String state;

	@Column(length = 30, nullable = false)
	public String country;

	@Column(length = 30, nullable = false)
	public String pincode;

	@Column(length = 30, nullable = false)
	public String type;

	@ManyToOne
	@JoinColumn(name = "users_id")
	private UserEntity userDetails;

	public String getAddressId() {
		return addressId;
	}

	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}

	public UserEntity getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserEntity userDetails) {
		this.userDetails = userDetails;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

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
}
