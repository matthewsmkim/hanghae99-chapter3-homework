package com.example.sparta.repository;

import com.example.sparta.domain.Board;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryBoardRepository implements BoardRepository {

    private static Map<Long, Board> store = new HashMap<>();

    private static long sequence = 0L;

    @Override
    public Board save(Board board) {
        board.setId(++sequence);
        store.put(board.getId(), board);
        return board;
    }

    @Override
    public Optional<Board> findByTitle(String title) {
        return Optional.empty();
    }

    @Override
    public Optional<Board> findByName(String name) {
        return store.values().stream()
                .filter(board -> board.getName().equals(name))
                .findAny();
    }

    @Override
    public Optional<Board> findByContext(String context) {
        return store.values().stream()
                .filter(board -> board.getContext().equals(context))
                .findAny();
    }

    @Override
    public Optional<Board> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Board> findAllByOrderByModifiedAtDesc() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Optional<Board> deleteById(Long id) {
        return Optional.empty();
    }
}
