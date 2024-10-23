package com.game_list.game_list.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.game_list.game_list.entities.Game;
import com.game_list.game_list.repository.GameRepository;
import org.springframework.data.domain.Sort;


@Service
public class GameService {
	
	@Autowired
	private GameRepository gameRepository;
	
	public List<Game> getAllGamesFromAllUsers(int from, int to, String sortBy, boolean descending){
		
		if (!isValidSortField(sortBy)) {
	        throw new IllegalArgumentException("Campo de ordenación no válido: " + sortBy);
	    }
	    
	    Sort sort = descending ? Sort.by(Sort.Order.desc(sortBy)) : Sort.by(Sort.Order.asc(sortBy));
	    
		Pageable pageable = PageRequest.of(from, to, sort);
		return this.gameRepository.findAll(pageable).getContent();
	}
	
	public List<Game> getGamesFromOneUser(int from, int to, Long id, String sortBy, boolean descending) {
		
		if (!isValidSortField(sortBy)) {
	        throw new IllegalArgumentException("Campo de ordenación no válido: " + sortBy);
	    }
	    
	    Sort sort = descending ? Sort.by(Sort.Order.desc(sortBy)) : Sort.by(Sort.Order.asc(sortBy));
	    
		Pageable pageable = PageRequest.of(from, to, sort);
		return this.gameRepository.findByUser_Id(id, pageable).getContent();
	}
	
	public void addGameFromOneUser(Game game) {
		this.gameRepository.save(game);
	}
	
	public void deleteGameFromOneUser(Long id) {
		this.gameRepository.deleteById(id);
	}
	
	public Game getOneGameInUserList(String name, String email) {
		return gameRepository.findByNameAndUserEmail(name, email);
	}
	
	private boolean isValidSortField(String sortBy) {
	    return sortBy.equals("name") || sortBy.equals("genre") || sortBy.equals("release_year");
	}


}
