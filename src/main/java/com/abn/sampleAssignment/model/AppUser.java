package com.abn.sampleAssignment.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.userdetails.User;

@Entity
@Table(name = "Users")
public class AppUser {
	
	@Id
	private String userId;
	private String firstName;
	private String lastName;
	private String preferance;
	private String role;
	private String passwd;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPreferance() {
		return preferance;
	}
	public void setPreferance(String preferance) {
		this.preferance = preferance;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
}
