package com.nursery.management.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class CareTaker {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, length = 7)
	private Long caretakerId;

	private String name;

	private int age;

	private String phoneNumber;

	private String email;

	private String sex;

	private int yearsOfExperience;

	private String designation;

	@ManyToOne
	@JoinColumn(name = "nursery_id")
	private Nursery nursery;

	@ManyToOne
	@JoinColumn(name = "admin_id")
	private Admin admin;

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public Long getCaretakerId() {
		return caretakerId;
	}

	public void setCaretakerId(Long caretakerId) {
		this.caretakerId = caretakerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getYearsOfExperience() {
		return yearsOfExperience;
	}

	public void setYearsOfExperience(int yearsOfExperience) {
		this.yearsOfExperience = yearsOfExperience;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public Nursery getNursery() {
		return nursery;
	}

	public void setNursery(Nursery nursery) {
		this.nursery = nursery;
	}

	public CareTaker(Long caretakerId, String name, int age, String phoneNumber, String email, String sex,
			int yearsOfExperience, String designation, Nursery nursery, Admin admin) {
		super();
		this.caretakerId = caretakerId;
		this.name = name;
		this.age = age;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.sex = sex;
		this.yearsOfExperience = yearsOfExperience;
		this.designation = designation;
		this.nursery = nursery;
		this.admin = admin;
	}

	public CareTaker() {
		super();
		// TODO Auto-generated constructor stub
	}

}
