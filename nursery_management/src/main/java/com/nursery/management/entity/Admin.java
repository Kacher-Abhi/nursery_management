package com.nursery.management.entity;

import java.util.List;
import java.util.Random;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
public class Admin {

	@Id
	private String adminId;

	@Column(nullable = false)
	private String firstName;

	@Column(nullable = false)
	private String lastName;

	@Column(nullable = false)
	private String email;

	@Column(nullable = false)
	private String phone_number;

	@Column(nullable = false)
	private String password;

	private String role;

	@Transient
	private String nurseryId;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "nursery_id")
	private Nursery nursery;

	private boolean isSuperAdmin;

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNurseryId() {
		return nurseryId;
	}

	public void setNurseryId(String nurseryId) {
		this.nurseryId = nurseryId;
	}

	public Nursery getNursery() {
		return nursery;
	}

	public void setNursery(Nursery nursery) {
		this.nursery = nursery;
	}

	public boolean isSuperAdmin() {
		return isSuperAdmin;
	}

	public void setSuperAdmin(boolean isSuperAdmin) {
		this.isSuperAdmin = isSuperAdmin;
	}

	public void setRole(String authorities) {
		this.role = authorities;
	}

	public String getRole() {
		return role;
	}

	public Admin(String adminId,String firstName, String lastName, String email, String phone_number, String password,
			String nurseryId, boolean isSuperAdmin, Nursery nursery, List<GrantedAuthority> authorities) {
		super();
		this.adminId = adminId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone_number = phone_number;
		this.password = password;
		this.nurseryId = nurseryId;
		this.isSuperAdmin = isSuperAdmin;
		this.nursery = nursery;
	}

	
    public Admin() {
        this.adminId = generateRandomId();
    }

	private String generateRandomId() {
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		StringBuilder randomId = new StringBuilder();
		Random random = new Random();

		for (int i = 0; i < 6; i++) {
			randomId.append(characters.charAt(random.nextInt(characters.length())));
		}

		return randomId.toString();
	}
}
