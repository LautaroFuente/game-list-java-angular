package com.game_list.game_list.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String email;
	private String password;
	private LocalDateTime registration_date;
	
	public User(String email, String password, LocalDateTime registration_date) {
		super();
		this.email = email;
		this.password = password;
		this.registration_date = registration_date;
	}

	public User() {
		super();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDateTime getRegistration_date() {
		return registration_date;
	}

	public void setRegistration_date(LocalDateTime registration_date) {
		this.registration_date = registration_date;
	}

	public Long getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}
	
	
}
