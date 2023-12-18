package com.nursery.management.entity;

import java.time.LocalDate;

import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
public class Test {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long testId;

	@Column(nullable = false)
	private String testName;

	@Column(nullable = false)
	private String result;
	
	@Transient
	private String nurseryId;

	@Transient
	private String caretakerId;

	@Transient
	private String patientId;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "caretaker_id")
	private CareTaker caretaker;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "patientId")
	private Patient patient;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "nursery_id")
	private Nursery nursery;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate testTakenDate;

	@JsonFormat(pattern = "HH:mm:ss")
	private LocalTime testTakenTime;

	public Long getTestId() {
		return testId;
	}

	public void setTestId(Long testId) {
		this.testId = testId;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public CareTaker getCareTaker() {
		return caretaker;
	}

	public void setCareTaker(CareTaker careTaker) {
		this.caretaker = careTaker;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public String getNurseryId() {
		return nurseryId;
	}

	public void setNurseryId(String nurseryId) {
		this.nurseryId = nurseryId;
	}

	public String getCaretakerId() {
		return caretakerId;
	}

	public void setCaretakerId(String caretakerId) {
		this.caretakerId = caretakerId;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public Nursery getNursery() {
		return nursery;
	}

	public void setNursery(Nursery nursery) {
		this.nursery = nursery;
	}

	public LocalDate getTestTakenDate() {
		return testTakenDate;
	}

	public void setTestTakenDate(LocalDate testTakenDate) {
		this.testTakenDate = testTakenDate;
	}

	public LocalTime getTestTakenTime() {
		return testTakenTime;
	}

	public void setTestTakenTime(LocalTime testTakenTime) {
		this.testTakenTime = testTakenTime;
	}

	public Test(Long testId, String testName, String result, String nurseryId, LocalDate testTakenDate,
			LocalTime testTakenTime) {
		super();
		this.testId = testId;
		this.testName = testName;
		this.result = result;
		this.nurseryId = nurseryId;
		this.testTakenDate = testTakenDate;
		this.testTakenTime = testTakenTime;
	}

	public Test() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
