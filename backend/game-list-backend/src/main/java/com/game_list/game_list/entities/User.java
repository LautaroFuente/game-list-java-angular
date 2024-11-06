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
	
	private String name;
	private String email;
	private String password;
	private LocalDateTime registration_date;
	private String image_url;
	
	public User(String name, String email, String password, LocalDateTime registration_date, String image_url) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.registration_date = registration_date;
		this.image_url = image_url;
	}

	public User() {
		super();
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
	
	public void setPassword(String password) {
		this.password = password;
	}

	public String getImage_url() {
		return image_url;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}
	
	
	
	
}
