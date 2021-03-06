package com.virtusa.denorm.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class LoginDetail {
	@Id
	@GeneratedValue
	private int userId;
	@NotEmpty
	private String username;
	private String password;
	private String role;
	private Boolean isEnable;
	@Override
	public String toString() {
		return "LoginDetail [userId=" + userId + ", username=" + username + ", password=" + password + ", role=" + role
				+ ", isEnable=" + isEnable + "]";
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Boolean getIsEnable() {
		return isEnable;
	}
	public void setIsEnable(Boolean isEnable) {
		this.isEnable = isEnable;
	}
	public LoginDetail(int userId, String username, String password, String role, Boolean isEnable) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.role = role;
		this.isEnable = isEnable;
	}
	public LoginDetail(String username, String password, String role, Boolean isEnable) {
		super();
		this.username = username;
		this.password = password;
		this.role = role;
		this.isEnable = isEnable;
	}
	public LoginDetail() {
		super();
	}

	
}
