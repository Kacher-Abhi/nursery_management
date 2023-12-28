package com.nursery.management.entity;

public class LoginRequest {
	private String username;
	private String password;
	private String tenantId;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String passowrd) {
		this.password = passowrd;
	}
	public String getTenantId() {
		return tenantId;
	}
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	public LoginRequest(String username, String passowrd, String tenantId) {
		super();
		this.username = username;
		this.password = passowrd;
		this.tenantId = tenantId;
	}
	public LoginRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
}
