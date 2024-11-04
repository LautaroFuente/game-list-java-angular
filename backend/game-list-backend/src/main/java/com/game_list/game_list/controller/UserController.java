package com.game_list.game_list.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.game_list.game_list.DTO.ApiResponseDTO;
import com.game_list.game_list.entities.User;
import com.game_list.game_list.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserService userService;
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("user/all")
	public ResponseEntity<ApiResponseDTO<List<User>>> getAllUsers(){
		
		try {
			List<User> users = this.userService.getAllUsers();
			return ResponseEntity.ok(new ApiResponseDTO<>(true, "Usuarios obtenidos exitosamente", users));
		} catch (Exception e) {
			return ResponseEntity.status(500).body(new ApiResponseDTO<>(false, "Error al obtener todos los usuarios: " + e.getMessage(), null));
		}

	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("user")
	public ResponseEntity<ApiResponseDTO<User>> getOneUser(@RequestParam String email){
		if(email == null || email.isEmpty()) {
			return ResponseEntity.badRequest().body(new ApiResponseDTO<>(false, "El email para buscar no puede ser vacio o ser nulo", null));
		}
		
		try {
			User user = this.userService.getOneUser(email);
			return ResponseEntity.ok(new ApiResponseDTO<>(true, "Usuario obtenido exitosamente", user));
		} catch (Exception e) {
			return ResponseEntity.status(500).body(new ApiResponseDTO<>(false, "Error al obtener al usuario: " + e.getMessage(), null));
		}

	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("user")
	public ResponseEntity<ApiResponseDTO<User>> addUser(@RequestBody User user){
		
		if(user == null) {
			return ResponseEntity.badRequest().body(new ApiResponseDTO<>(false, "El usuario a guardar no puede ser nulo", null));
		}
		try {
			User existUser = this.userService.getOneUser(user.getEmail());
			if( existUser == null) {
				user.setRegistration_date(LocalDateTime.now());
				this.userService.addUser(user);
				return ResponseEntity.ok(new ApiResponseDTO<>(true, "Usuario guardado exitosamente", user));	
			}else {
				return ResponseEntity.ok(new ApiResponseDTO<>(false, "Usuario existente", user));
			}			
			
		} catch (Exception e) {
			return ResponseEntity.status(500).body(new ApiResponseDTO<>(false, "Error al agregar el usuario: " + e.getMessage(), null));
		}

	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@DeleteMapping("user/delete/{email}")
	public ResponseEntity<ApiResponseDTO<List<User>>> deleteUser(@PathVariable String email){
		if(email == null || email == "") {
			return ResponseEntity.badRequest().body(new ApiResponseDTO<>(false, "El email del usuario no puede ser nulo o vacio", null));
		}
		
		try {
			Long id = this.userService.getOneUser(email).getId();
	        this.userService.deleteUser(id);
	        return ResponseEntity.ok(new ApiResponseDTO<>(true, "Usuario eliminado exitosamente", null));
	    } catch (Exception e) {
	        return ResponseEntity.status(500).body(new ApiResponseDTO<>(false, "Error al eliminar el usuario: " + e.getMessage(), null));
	    }

	}
}
