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
	private Long caretakerId;

	@Transient
	private Long patientId;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "caretaker_id")
	private CareTaker caretaker;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "patientId")
	private Patient patient;

	private String careTakerEmail;

	private String patientEmail;

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

	public Long getCaretakerId() {
		return caretakerId;
	}

	public void setCaretakerId(Long caretakerId) {
		this.caretakerId = caretakerId;
	}

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
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

	public CareTaker getCaretaker() {
		return caretaker;
	}

	public void setCaretaker(CareTaker caretaker) {
		this.caretaker = caretaker;
	}

	public String getCareTakerEmail() {
		return careTakerEmail;
	}

	public void setCareTakerEmail(String careTakerEmail) {
		this.careTakerEmail = careTakerEmail;
	}

	public String getPatientEmail() {
		return patientEmail;
	}

	public void setPatientEmail(String patientEmail) {
		this.patientEmail = patientEmail;
	}

	public Test(Long testId, String testName, String result, LocalDate testTakenDate, LocalTime testTakenTime,
			Long patientId, String nurseryId, Long caretakerId, String careTakerEmail, String patientEmail) {
		super();
		this.testId = testId;
		this.testName = testName;
		this.result = result;
		this.testTakenDate = testTakenDate;
		this.testTakenTime = testTakenTime;
		this.patientId = patientId;
		this.nurseryId = nurseryId;
		this.caretakerId = caretakerId;
		this.careTakerEmail = careTakerEmail;
		this.patientEmail = patientEmail;
		
	}

	public Test() {
		super();
		// TODO Auto-generated constructor stub
	}

}
