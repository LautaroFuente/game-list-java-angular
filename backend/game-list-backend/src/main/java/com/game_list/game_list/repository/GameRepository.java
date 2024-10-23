package com.game_list.game_list.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.game_list.game_list.entities.Game;

public interface GameRepository extends JpaRepository<Game, Long>{

	Page<Game> findByUser_Id(Long userId, Pageable pageable);
	
	@Query("SELECT g FROM Game g JOIN g.user u WHERE g.name = :name AND u.email = :email")
    Game findByNameAndUserEmail(@Param("name") String name, @Param("email") String email);
}
