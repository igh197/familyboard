package com.example.familyboard.repository;

import com.example.familyboard.model.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board,Long> {
    Optional<Board> findById(Long id);

    Optional<Board> findByTitle(String title);
}
