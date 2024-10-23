package com.game_list.game_list.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.game_list.game_list.entities.User;
import com.game_list.game_list.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;

	public List<User> getAllUsers(){
		return this.userRepository.findAll();
	}
	
	public User getOneUser(String email) {
		Optional<User> usuario = this.userRepository.findByEmail(email);
	    return usuario.orElse(null);
	}
	
	public void addUser(User user) {
		this.userRepository.save(user);
	}
	
	public void deleteUser(Long id) {
		this.userRepository.deleteById(id);
	}
}
