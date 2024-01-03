package com.nursery.management.entity;

import java.util.List;
import java.util.Random;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;

@Entity
public class Patient {

	@Id
	private String patientId;

	private String firstName;

	private String lastName;

	private int age;

	private String phoneNumber;

	private String email;

	private String sex;

	private String role;

	private String password;

	@Transient
	private String nurseryId;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "nursery_id")
	private Nursery nursery;

//	@JsonIgnore
//	@ManyToOne
//	@JoinColumn(name = "caretaker_id")
//	private CareTaker caretaker;

	@JsonIgnore
	@OneToMany(mappedBy = "patient")
	private List<Rating> ratings;
	
	@OneToMany(mappedBy = "patient")
	private List<Test> test;


	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
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

//	public CareTaker getCaretaker() {
//		return caretaker;
//	}
//
//	public void setCaretaker(CareTaker caretaker) {
//		this.caretaker = caretaker;
//	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String passowrd) {
		this.password = passowrd;
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

	public void setTest(List<Test> test) {
		this.test = test;
	}

	public Patient(String patientId, String firstName, String lastName, int age, String phoneNumber, String email,
			String sex, String nurseryId, String role, String passowrd) {
		super();
		this.patientId = patientId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.sex = sex;
		this.nurseryId = nurseryId;
		this.role = role;
		this.password = passowrd;
	}

	public Patient() {
		this.patientId = generateRandomId();
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
