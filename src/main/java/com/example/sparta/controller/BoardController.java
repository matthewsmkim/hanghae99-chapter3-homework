package com.example.sparta.controller;

import com.example.sparta.domain.Board;
import com.example.sparta.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

public class BoardController {

    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }


    @GetMapping("/boards")
    public String list(Model model) {
        List<Board> boards = boardService.findBoard();
        model.addAttribute("boards",boards);
        return "boards/";
    }

    @GetMapping("/boards/new")
    public String createForm() {return "boards/createBoardForm"; }

    @PostMapping("/boards/new")
    public String create(BoardForm form) {
        Board board = new Board();
        board.setId(form.getId());
        board.setTitle(form.getTitle());
        board.setName(form.getName());
        board.setContext(form.getContext());

        return "redirect:/";

    }

    @DeleteMapping("boards/delete/{id}")
    public Long delete(@PathVariable Long id) {
        boardService.delete(id);

        return id;
    }

    @PutMapping("boards/revise/{id}")
    public Optional<Board> reviseBoard(@PathVariable Long id) {
        Optional<Board> board = boardService.findById(id);
        if(board.isPresent()) {
            Board isboard = board.get();
            if(board.getPassword().equals(id.getPassword())); {
                boardService.revise(id);

            }
            else {
                throw new IllegalStateException("비밀번호가 맞지 않습니다");
            }
        }
        return boardService.findById(id);
    }



}
