package com.game_list.game_list.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.game_list.game_list.entities.Game;

public interface GameRepository extends JpaRepository<Game, Long>{

}
