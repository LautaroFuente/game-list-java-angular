package com.game_list.game_list.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.game_list.game_list.entities.Game;
import com.game_list.game_list.repository.GameRepository;

@Service
public class GameService {
	
	@Autowired
	private GameRepository gameRepository;
	
	public List<Game> getGamesFromOneUser() {
		return null;
	}
	
	public void addGameToOneUser() {
		
	}
	
	public void deleteGameFromOneUser() {
		
	}

}
