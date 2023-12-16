package com.nursery.management.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;

@Entity
public class Admin {
    
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adminId;

    private String name;
    private String email;
    private String phone_number;
    private String password;
    
    private int age;
    
    @Transient
    private String nurseryId;

    @ManyToOne
    @JoinColumn(name = "nursery_id")
    private Nursery nursery;
    
    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL)
    private List<CareTaker> caretakers;
    


    private boolean isSuperAdmin;

	
    
    public Long getAdminId() {
		return adminId;
	}

	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public boolean isSuperAdmin() {
		return isSuperAdmin;
	}

	public void setSuperAdmin(boolean isSuperAdmin) {
		this.isSuperAdmin = isSuperAdmin;
	}
	
	
	public List<CareTaker> getCaretakers() {
		return caretakers;
	}

	public void setCaretakers(List<CareTaker> caretakers) {
		this.caretakers = caretakers;
	}


	public Admin(Long adminId, String name, String email, String phone_number, String password, String nurseryId,
			Nursery nursery, List<CareTaker> caretakers, boolean isSuperAdmin) {
		super();
		this.adminId = adminId;
		this.name = name;
		this.email = email;
		this.phone_number = phone_number;
		this.password = password;
		this.nurseryId = nurseryId;
		this.nursery = nursery;
		this.caretakers = caretakers;
		this.isSuperAdmin = isSuperAdmin;
	}

	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}

    
	

}
