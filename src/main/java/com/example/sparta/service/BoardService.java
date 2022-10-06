package com.example.sparta.service;

import com.example.sparta.domain.Board;
import com.example.sparta.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class BoardService {

    private final BoardRepository boardRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    // 조회
    public List<Board> findBoard() {return boardRepository.findAllByOrderByModifiedAtDesc(); }
    public Optional<Board> findOne(String boardTitle) {return boardRepository.findByTitle(boardTitle);}

    public Long delete(@PathVariable Long id) {
        boardRepository.deleteById(id);
        return id;
    }

    public Optional<Board> findById(Long id) {
        return boardRepository.findById(id);
    }

    public Long revise(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(
                ()->new IllegalStateException("아이디가 존재하지 않습니다.")
        );
        board.revise();
        return board.getId();
    }
}
