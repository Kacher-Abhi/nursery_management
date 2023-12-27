package com.nursery.management.entity;

import java.util.List;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "CareTaker")
public class CareTaker {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true)
	private Long caretakerId;

	private String name;

	private int age;

	private String phoneNumber;

	private String email;

	private String sex;
	
	private String password;

	private int yearsOfExperience;

	private String designation;
	private String role;

	@Transient
	private String nurseryId;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "nursery_id")
	private Nursery nursery;

//	@OneToMany(mappedBy = "caretaker", cascade = CascadeType.ALL)
//	private List<Test> test;

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
	public String getAuthorities() {
		return role;
	}

	public void setAuthorities(String authorities) {
		this.role = authorities;
	}
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.phoneNumber = password;
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

	
//	public List<Test> getTest() {
//		return test;
//	}
//
//	public void setTest(List<Test> test) {
//		this.test = test;
//	}

	public CareTaker(Long caretakerId, String name, int age, String phoneNumber, String email, String sex,
			int yearsOfExperience, String designation, String nurseryId, Nursery nursery) {
		super();
		this.caretakerId = caretakerId;
		this.name = name;
		this.age = age;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.sex = sex;
		this.yearsOfExperience = yearsOfExperience;
		this.designation = designation;
		this.nurseryId = nurseryId;
		this.nursery = nursery;
	}

	public CareTaker() {
		super();
		// TODO Auto-generated constructor stub
	}

}
