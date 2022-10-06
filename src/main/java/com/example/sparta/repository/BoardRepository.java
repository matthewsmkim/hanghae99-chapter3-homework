package com.example.sparta.repository;

import com.example.sparta.domain.Board;

import java.util.List;
import java.util.Optional;

public interface BoardRepository {

    Board save(Board board);

    Optional<Board> findByTitle(String title);
    Optional<Board> findByName(String name);
    Optional<Board> findByContext(String context);
    Optional<Board> findById(Long id);





    List<Board> findAllByOrderByModifiedAtDesc();


    Optional<Board> deleteById(Long id);
}
