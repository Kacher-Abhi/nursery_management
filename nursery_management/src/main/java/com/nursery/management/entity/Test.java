package com.nursery.management.entity;

import java.time.LocalDate;

import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

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
	private String patientId;

	@Transient
	private String caretakerId;

	@ManyToOne
	@JoinColumn(name = "patientId")
	private Patient patient;

	@ManyToOne
	@JoinColumn(name = "caretaker_id")
	private CareTaker caretaker;

	@ManyToOne
	@JoinColumn(name = "nursery_id")
	private Nursery nursery;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate testTakenDate;

	@JsonFormat(pattern = "HH:mm")
	private LocalTime testTakenTime;

//	@JsonIgnore
	@Lob
	@Column(name = "TestResult", length = Integer.MAX_VALUE, nullable = true)
	private byte[] testResult;

//	@JsonIgnore
	@Lob
	@Column(name = "Prescription", length = Integer.MAX_VALUE, nullable = true)
	private byte[] prescription;

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

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getCaretakerId() {
		return caretakerId;
	}

	public void setCaretakerId(String caretakerId) {
		this.caretakerId = caretakerId;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public CareTaker getCaretaker() {
		return caretaker;
	}

	public void setCaretaker(CareTaker caretaker) {
		this.caretaker = caretaker;
	}

	public byte[] getTestResult() {
		return testResult;
	}

	public void setTestResult(byte[] testResult) {
		this.testResult = testResult;
	}

	public byte[] getPrescription() {
		return prescription;
	}

	public void setPrescription(byte[] prescription) {
		this.prescription = prescription;
	}

	public Test(Long testId, String testName, String result, LocalDate testTakenDate, LocalTime testTakenTime,
			String nurseryId, String patientId, String caretakerId) {
		super();
		this.testId = testId;
		this.testName = testName;
		this.result = result;
		this.testTakenDate = testTakenDate;
		this.testTakenTime = testTakenTime;
		this.nurseryId = nurseryId;
		this.patientId = patientId;
		this.caretakerId = caretakerId;
//		this.careTakerEmail = careTakerEmail;
//		this.patientEmail = patientEmail;

	}

	public Test() {
		super();
		// TODO Auto-generated constructor stub
	}

}
