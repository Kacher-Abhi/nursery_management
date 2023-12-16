package com.nursery.management.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;

@Entity
public class Patient {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private Long patientId;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private int age;

	@Column(nullable = false)
	private String phoneNumber;

	@Column(nullable = false)
	private String email;

	@Column(nullable = false)
	private String sex;

	@Transient
	private String nurseryId;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "nursery_id")
	private Nursery nursery;

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
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

	public Patient(Long patientId, String name, int age, String phoneNumber, String email, String sex) {
		super();
		this.patientId = patientId;
		this.name = name;
		this.age = age;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.sex = sex;
	}

	public Patient() {
		super();
		// TODO Auto-generated constructor stub
	}

}
