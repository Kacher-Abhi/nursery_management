package com.nursery.management.entity;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;

@Entity
public class Patient{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private Long patientId;

	@Column(nullable = false)
	private String firstName;

	@Column(nullable = true)
	private String lastName;

	@Column(nullable = false)
	private int age;

	@Column(nullable = false)
	private String phoneNumber;

	@Column(nullable = false)
	private String email;

	@Column(nullable = false)
	private String sex;
	private String role;
	private String passowrd;

	@Transient
	private String nurseryId;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "nursery_id")
	private Nursery nursery;

	@OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
	private List<Test> test;

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
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

	public List<Test> getTest() {
		return test;
	}

	public void setTest(List<Test> test) {
		this.test = test;
	}

	public Patient(Long patientId, String firstName, String lastName, int age, String phoneNumber, String email,
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
		this.passowrd = passowrd;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPassowrd() {
		return passowrd;
	}

	public void setPassowrd(String passowrd) {
		this.passowrd = passowrd;
	}

	public Patient() {
		super();
		// TODO Auto-generated constructor stub
	}


}
