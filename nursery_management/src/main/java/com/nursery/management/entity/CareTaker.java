package com.nursery.management.entity;

import java.util.List;
import java.util.Random;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "CareTaker")
public class CareTaker {
	@Id
	@Column(name = "caretaker_id", unique = true)
	private String caretakerId;

	private String name;

	private int age;

	private String phoneNumber;

	private String email;

	private String sex;

	private String password;

	private int yearsOfExperience;

	private String designation;

	private String role;

	private double averageRating;

	@Transient
	private String nurseryId;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "nursery_id")
	private Nursery nursery;

	@OneToMany(mappedBy = "caretaker")
	private List<Rating> ratings;

	@JsonIgnore
	@OneToMany(mappedBy = "caretaker")
	private List<Test> test;
	
	@JsonIgnore
	@OneToMany(mappedBy = "caretaker")
	private List<Patient> patient;

	public String getCaretakerId() {
		return caretakerId;
	}

	public void setCaretakerId(String caretakerId) {
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
		this.password = password;
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

	public List<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}

	public List<Test> getTest() {
		return test;
	}

	public List<Patient> getPatient() {
		return patient;
	}

	public void setPatient(List<Patient> patient) {
		this.patient = patient;
	}

	public void setTest(List<Test> test) {
		this.test = test;
	}

	public double getAverageRating() {
		return averageRating;
	}

	public void setAverageRating(double averageRating) {
		this.averageRating = averageRating;
	}

	public CareTaker(String caretakerId, String name, int age, String phoneNumber, String email, String password,
			String sex, int yearsOfExperience, String designation, String nurseryId, Nursery nursery,
			double averageRating) {
		super();
		this.caretakerId = caretakerId;
		this.name = name;
		this.age = age;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.password = password;
		this.sex = sex;
		this.yearsOfExperience = yearsOfExperience;
		this.designation = designation;
		this.nurseryId = nurseryId;
		this.nursery = nursery;
		this.averageRating = averageRating;
	}

	public CareTaker() {
		this.caretakerId = generateRandomId();
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
