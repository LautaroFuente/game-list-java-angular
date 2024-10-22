package com.game_list.game_list.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.game_list.game_list.service.GameService;

@RestController
@RequestMapping("/api")
public class GameController {

	@Autowired
	private GameService gameService;
	
	/*@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("game/all")
	public ResponseEntity<ApiResponseDTO<List<User>>> getAllGamesFromOneUser(){
		
		try {
			List<User> users = this.userservice.getAllUsers();
			return ResponseEntity.ok(new ApiResponseDTO<>(true, "Usuarios obtenidos exitosamente", users));
		} catch (Exception e) {
			return ResponseEntity.status(500).body(new ApiResponseDTO<>(false, "Error al obtener todos los usuarios: " + e.getMessage(), null));
		}

	}*/
}
