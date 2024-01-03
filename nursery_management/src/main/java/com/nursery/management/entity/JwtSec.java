package com.nursery.management.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class JwtSec {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String nursery;
	private String secretKey;
	public JwtSec(String tenantId, String secretKey) {
		// TODO Auto-generated constructor stub
		super();
		this.nursery = tenantId;
		this.secretKey = secretKey;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNursery() {
		return nursery;
	}
	public void setNursery(String nursery) {
		this.nursery = nursery;
	}
	public String getSecretKey() {
		return secretKey;
	}
	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}
	public JwtSec() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
