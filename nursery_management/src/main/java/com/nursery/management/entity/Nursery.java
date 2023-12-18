package com.nursery.management.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;

@Entity
public class Nursery {

	@Id
	@Column(name = "nursery_id", unique = true, nullable = false)
	private String nurseryId;

	@Column(name = "Nursery_name", unique = true, nullable = false)
	private String nurseryName;

	@Column(name = "Primary_Color", nullable = false)
	private String primaryColor;

	@Column(name = "Secondary_Color", nullable = false)
	private String secondaryColor;

	@Column(name = "Email", nullable = false)
	private String email;

	@Column(name = "PhoneNumber", nullable = false)
	private String phoneNumber;

	@OneToMany(mappedBy = "nursery", cascade = CascadeType.ALL)
	private List<Admin> admins;

	@OneToMany(mappedBy = "nursery", cascade = CascadeType.ALL)
	private List<CareTaker> caretakers;

	@OneToMany(mappedBy = "nursery", cascade = CascadeType.ALL)
	private List<Patient> patient;

	@OneToMany(mappedBy = "nursery", cascade = CascadeType.ALL)
	private List<Test> test;

	@JsonIgnore
	@Lob
	@Column(name = "Image", length = Integer.MAX_VALUE, nullable = true)
	private byte[] image;

	public String getNurseryId() {
		return nurseryId;
	}

	public void setNurseryId(String nurseryId) {
		this.nurseryId = nurseryId;
	}

	public String getNurseryName() {
		return nurseryName;
	}

	public void setNurseryName(String nurseryName) {
		this.nurseryName = nurseryName;
	}

	public String getPrimaryColor() {
		return primaryColor;
	}

	public void setPrimaryColor(String primaryColor) {
		this.primaryColor = primaryColor;
	}

	public String getSecondaryColor() {
		return secondaryColor;
	}

	public void setSecondaryColor(String secondaryColor) {
		this.secondaryColor = secondaryColor;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public List<Admin> getAdmins() {
		return admins;
	}

	public void setAdmins(List<Admin> admins) {
		this.admins = admins;
	}

	public void setCaretakers(List<CareTaker> caretakers) {
		this.caretakers = caretakers;
	}

	public List<CareTaker> getCaretakers() {
		return caretakers;
	}

	public List<Patient> getPatient() {
		return patient;
	}

	public void setPatient(List<Patient> patient) {
		this.patient = patient;
	}

//	public List<Test> getTest() {
//		return test;
//	}
//
//	public void setTest(List<Test> test) {
//		this.test = test;
//	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public Nursery(String nurseryId, String nurseryName, String primaryColor, String secondaryColor, String email, String phoneNumber) {
		super();
		this.nurseryId = nurseryId;
		this.nurseryName = nurseryName;
		this.primaryColor = primaryColor;
		this.secondaryColor = secondaryColor;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}

	public Nursery() {
		super();
		// TODO Auto-generated constructor stub
	}

}
