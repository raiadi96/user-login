package com.example.userlogin.io.entity;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.*;;

@Entity(name = "users")
public class UserEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	public long id;

	@Column(name = "user_id", nullable = false, length = 255)
	public String userId;
	
	@Column(name ="first_name",nullable = false, length = 50)
	public String firstName;
	
	@Column(name = "last_name", nullable = false, length = 50)
	public String lastName;
	
	@Column(nullable = false, length = 170, unique = true)
	public String email;
	
	@Column(name = "encrypted_password",nullable = false)
	public String encryptedPassword;
	
	public String emailVerificationToken;
	
	@Column(nullable = false)
	public boolean emailVerificationStatus = false;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUser_id() {
		return userId;
	}

	public void setUser_id(String uuid) {
		this.userId = uuid;
	}

	public String getFirst_name() {
		return firstName;
	}

	public void setFirst_name(String first_name) {
		this.firstName = first_name;
	}

	public String getLast_name() {
		return lastName;
	}

	public void setLast_name(String last_name) {
		this.lastName = last_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEncrypted_password() {
		return encryptedPassword;
	}

	public void setEncrypted_password(String encrypted_password) {
		this.encryptedPassword = encrypted_password;
	}

	public String getEmailVerificationToken() {
		return emailVerificationToken;
	}

	public void setEmailVerificationToken(String emailVerificationToken) {
		this.emailVerificationToken = emailVerificationToken;
	}

	public boolean isEmailVerificationStatus() {
		return emailVerificationStatus;
	}

	public void setEmailVerificationStatus(boolean emailVerificationStatus) {
		this.emailVerificationStatus = emailVerificationStatus;
	}
	
	

}
