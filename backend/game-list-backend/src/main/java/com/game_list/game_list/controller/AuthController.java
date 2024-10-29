package com.game_list.game_list.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.game_list.game_list.DTO.ApiResponseDTO;
import com.game_list.game_list.DTO.AuthResponseDTO;
import com.game_list.game_list.DTO.LoginRequestDTO;
import com.game_list.game_list.entities.User;
import com.game_list.game_list.service.JwtService;
import com.game_list.game_list.service.UserService;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    @Autowired
    private JwtService jwtService;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("login")
    public ResponseEntity<ApiResponseDTO<AuthResponseDTO>> login(@RequestBody LoginRequestDTO loginRequest) {
    	
    	if((loginRequest.getEmail() == null || loginRequest.getEmail() == "") || (loginRequest.getPassword() == null || loginRequest.getPassword() == "")) {
    		return ResponseEntity.badRequest().body(new ApiResponseDTO<>(false, "Error, algun campo es invalido", null));
    	}
    	
        User user = this.userService.getOneUser(loginRequest.getEmail());
        if (user != null && passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            String token = generateToken(user);
            return ResponseEntity.ok(new ApiResponseDTO<>(true, "Usuario logueado correctamente", new AuthResponseDTO(token,user.getName(), user.getEmail())));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiResponseDTO<>(false, "Error al loguear al usuario: ", null));
    }

    private String generateToken(User user) {
       return this.jwtService.generateToken(user.getName());
    }
}
