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

	@Column(nullable = false)
	public String user_id;
	
	@Column(nullable = false, length = 50)
	public String first_name;
	
	@Column(nullable = false, length = 50)
	public String last_name;
	
	@Column(nullable = false, length = 170, unique = true)
	public String email;
	
	@Column(nullable = false)
	public String encrypted_password;
	
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
		return user_id;
	}

	public void setUser_id(String uuid) {
		this.user_id = uuid;
	}

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

	public String getEncrypted_password() {
		return encrypted_password;
	}

	public void setEncrypted_password(String encrypted_password) {
		this.encrypted_password = encrypted_password;
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
