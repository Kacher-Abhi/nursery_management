package com.nursery.management.entity;

import java.util.Date;

public class JwtResponse {
	private String token;
	private Date expires;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Date getExpires() {
		return expires;
	}
	public void setExpires(Date expires) {
		this.expires = expires;
	}
	public JwtResponse(String token, Date expires) {
		super();
		this.token = token;
		this.expires = expires;
	}
	public JwtResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

}
