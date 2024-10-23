package com.game_list.game_list.controller;

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
import com.game_list.game_list.entities.Game;
import com.game_list.game_list.service.GameService;
import com.game_list.game_list.service.UserService;

@RestController
@RequestMapping("/api")
public class GameController {

	@Autowired
	private GameService gameService;
	
	@Autowired
	private UserService userService;
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("game/all")
	public ResponseEntity<ApiResponseDTO<List<Game>>> getAllGamesFromAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "7") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "false") boolean descending)
	{
		
		try {
			List<Game> games = this.gameService.getAllGamesFromAllUsers(page, size, sortBy, descending);
			return ResponseEntity.ok(new ApiResponseDTO<>(true, "Todos los juegos obtenidos exitosamente", games));
		} catch (Exception e) {
			return ResponseEntity.status(500).body(new ApiResponseDTO<>(false, "Error al obtener todos los juegos: " + e.getMessage(), null));
		}

	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("game/user")
	public ResponseEntity<ApiResponseDTO<List<Game>>> getGamesFromOneUser(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "7") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "false") boolean descending,
            @RequestParam(defaultValue = "empty") String email)
	{
		if(email.equals("empty")) {
			return ResponseEntity.badRequest().body(new ApiResponseDTO<>(false, "El email no puede ser vacio o ser nulo", null));
		}
		
		try {
			Long userId = this.userService.getOneUser(email).getId();
			List<Game> games = this.gameService.getGamesFromOneUser(page, size, userId, sortBy, descending);
			return ResponseEntity.ok(new ApiResponseDTO<>(true, "Todos los juegos del usuario obtenidos exitosamente", games));
		} catch (Exception e) {
			return ResponseEntity.status(500).body(new ApiResponseDTO<>(false, "Error al obtener todos los juegos del usuario: " + e.getMessage(), null));
		}

	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("game/add/{email}")
	public ResponseEntity<ApiResponseDTO<Game>> addGameFromOneUser(@RequestBody Game game, @PathVariable String email)
	{
		if(game == null) {
			return ResponseEntity.badRequest().body(new ApiResponseDTO<>(false, "El juego a guardar no puede ser nulo", null));
		}
		
		try {
			Game existGame = this.gameService.getOneGameInUserList(game.getName(), email);
			if( existGame == null) {
				this.gameService.addGameFromOneUser(game);
				return ResponseEntity.ok(new ApiResponseDTO<>(true, "Juego guardado exitosamente", game));
			}else {
				return ResponseEntity.ok(new ApiResponseDTO<>(false, "Juego ya existe en la lista del usuario", game));
			}
		} catch (Exception e) {
			return ResponseEntity.status(500).body(new ApiResponseDTO<>(false, "Error al obtener todos los juegos del usuario: " + e.getMessage(), null));
		}

	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@DeleteMapping("game/delete/{id}")
	public ResponseEntity<ApiResponseDTO<Void>> deleteGame(@PathVariable Long id) {
		if(id == null || id < 0) {
			return ResponseEntity.badRequest().body(new ApiResponseDTO<>(false, "El identificador del juego no puede ser nulo o menor a cero", null));
		}
		
	    try {
	        this.gameService.deleteGameFromOneUser(id);
	        return ResponseEntity.ok(new ApiResponseDTO<>(true, "Juego eliminado exitosamente", null));
	    } catch (Exception e) {
	        return ResponseEntity.status(500).body(new ApiResponseDTO<>(false, "Error al eliminar el juego: " + e.getMessage(), null));
	    }
	}

}
