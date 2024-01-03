package com.nursery.management.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
public class Rating {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Transient
	private String nurseryId;

	@Transient
	private String patientId;

	@Transient
	private String caretakerId;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "patientId")
	private Patient patient;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "caretaker_id")
	private CareTaker caretaker;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "nursery_id")
	private Nursery nursery;

	private double rating;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
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

	public Nursery getNursery() {
		return nursery;
	}

	public void setNursery(Nursery nursery) {
		this.nursery = nursery;
	}

	public Rating(Long id, Patient patient, CareTaker caretaker, Nursery nursery, String nurseryId, String patientId, String caretakerId,
			double rating) {
		super();
		this.id = id;
		this.patient = patient;
		this.caretaker = caretaker;
		this.nursery = nursery;
		this.nurseryId = nurseryId;
		this.patientId = patientId;
		this.caretakerId = caretakerId;
		this.rating = rating;
	}

	public Rating() {
		super();
		// TODO Auto-generated constructor stub
	}

}
