package com.game_list.game_list.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.GeneratedValue;

@Entity
public class Game {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private String genre;
	private int release_year;
	private String img_url;
	
	@ManyToOne
    @JoinColumn(name = "user_id")
	private User user;
	private LocalDateTime added_date;
	
	public Game(String name, String genre, int release_year, String img_url, User user, LocalDateTime added_date) {
		super();
		this.name = name;
		this.genre = genre;
		this.release_year = release_year;
		this.img_url = img_url;
		this.user = user;
		this.added_date = added_date;
	}

	public Game() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getRelease_year() {
		return release_year;
	}

	public void setRelease_year(int release_year) {
		this.release_year = release_year;
	}

	public String getImg_url() {
		return img_url;
	}

	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LocalDateTime getAdded_date() {
		return added_date;
	}

	public void setAdded_date(LocalDateTime added_date) {
		this.added_date = added_date;
	}

	public Long getId() {
		return id;
	}
	
	
	
}
