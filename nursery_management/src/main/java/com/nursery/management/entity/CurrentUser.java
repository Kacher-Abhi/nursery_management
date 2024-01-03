package com.nursery.management.entity;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Transient;

public class CurrentUser implements UserDetails {
	private static final long serialVersionUID = 1L;
	private String id;
	private String email;
	private String password;
	private boolean active = true;
	private String nursery_id;
	private List<GrantedAuthority> role;
	private String userRole;



	public CurrentUser(Admin user) {
		this.id = user.getAdminId();
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.nursery_id = user.getNurseryId();
		this.role = Arrays.asList(new SimpleGrantedAuthority("ROLE_"+user.getRole()));
	}

	public CurrentUser(Patient user) {
		this.id = user.getPatientId();
		this.email = user.getEmail();
		this.password = user.getPassowrd();
		this.nursery_id = user.getNurseryId();
		this.role = Arrays.asList(new SimpleGrantedAuthority("ROLE_"+user.getRole()));
	}

	public CurrentUser(CareTaker user) {
		this.id = user.getCaretakerId();
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.nursery_id = user.getNurseryId();
		this.role = Arrays.asList(new SimpleGrantedAuthority("ROLE_"+user.getAuthorities()));
	}
	
	public CurrentUser(String username, String role, String nursery_id) {
		this.email = username;
		this.role = Arrays.asList(new SimpleGrantedAuthority("ROLE_"+role));
		this.nursery_id = nursery_id;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return role;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}
	public String getTenant() {
		return nursery_id;
	}
	
	public String getUserRole() {
		return role.get(0).toString();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return active;
	}
}
