package com.game_list.game_list.DTO;

public class AuthResponseDTO {

	private String token;
	private String name;
	private String email;
	
	public AuthResponseDTO(String token, String name, String email) {
		super();
		this.token = token;
		this.name = name;
		this.email = email;
	}

	public AuthResponseDTO() {
		super();
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
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
	
}
