package com.nursery.management.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Notes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long notesId;

	private String notes;

	@Transient
	private String nurseryId;

	@Transient
	private String patientId;

	@Transient
	private String caretakerId;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "nursery_id")
	private Nursery nursery;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "patientId")
	private Patient patient;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "caretaker_id")
	private CareTaker caretaker;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate date;

	@JsonFormat(pattern = "HH:mm")
	private LocalTime time;
	
	@Lob
	@Column(name = "Image", length = Integer.MAX_VALUE, nullable = true)
	private byte[] image;

	private int month;
	
	private int year;

	// Constructors
	public Notes() {
		// Default constructor
	}

	public Notes(Long notesId, String notes, String nurseryId, String patientId, String caretakerId, LocalDate date,
			LocalTime time, int month) {
		this.notesId = notesId;
		this.notes = notes;
		this.nurseryId = nurseryId;
		this.patientId = patientId;
		this.caretakerId = caretakerId;
		this.date = date;
		this.time = time;
		this.month = date.getMonthValue();
	}

	// Getters and Setters
	public Long getNotesId() {
		return notesId;
	}

	public void setNotesId(Long notesId) {
		this.notesId = notesId;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getNurseryId() {
		return nurseryId;
	}

	public void setNurseryId(String nurseryId) {
		this.nurseryId = nurseryId;
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

	public Nursery getNursery() {
		return nursery;
	}

	public void setNursery(Nursery nursery) {
		this.nursery = nursery;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public void setMonth(int month) {
		this.month = date.getMonthValue();
	}

	public int getMonth() {
		return date.getMonthValue();
	}
	
	public void setYear(int year) {
		this.year = date.getYear();
	}

	public int get_Year() {
		return date.getYear();
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}
	
}
