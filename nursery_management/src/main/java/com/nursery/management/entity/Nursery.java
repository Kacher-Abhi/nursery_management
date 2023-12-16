package com.nursery.management.entity;

import java.util.List;

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
	
	@OneToMany(mappedBy = "nursery", cascade = CascadeType.ALL)
    private List<Admin> admins;
	
	@OneToMany(mappedBy = "nursery", cascade = CascadeType.ALL)
	private List<CareTaker> caretakers;
	
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
    
    public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}
	

	public Nursery(String nurseryId, String nurseryName, String primaryColor, String secondaryColor, List<Admin> admins,
			List<CareTaker> caretakers) {
		super();
		this.nurseryId = nurseryId;
		this.nurseryName = nurseryName;
		this.primaryColor = primaryColor;
		this.secondaryColor = secondaryColor;
		this.admins = admins;
		this.caretakers = caretakers;
	}

	public Nursery() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	
}
